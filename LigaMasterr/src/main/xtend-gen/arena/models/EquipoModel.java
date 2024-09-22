package arena.models;

import arena.models.TorneoModel;
import com.google.common.base.Objects;
import datos.Mercado;
import datos.Precios;
import datos.SoFifa;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import master.DT;
import master.Jugador;
import master.LigaMaster;
import master.Torneo;
import master.Transferencia;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class EquipoModel {
  private Torneo torneoON;
  
  private DT dtActivo;
  
  private DT dtON;
  
  private Jugador jugadorON = new Jugador();
  
  private String textoBusqueda = "";
  
  private List<Jugador> listaMaquina = CollectionLiterals.<Jugador>newArrayList();
  
  public EquipoModel(final TorneoModel model) {
    Torneo _torneoON = model.getTorneoON();
    this.torneoON = _torneoON;
    DT _dtON = model.getDtON();
    this.dtActivo = _dtON;
    DT _dtON_1 = model.getDtON();
    this.dtON = _dtON_1;
  }
  
  public void validar() {
    boolean _equals = Objects.equal(this.jugadorON, null);
    if (_equals) {
      throw new UserException("Debe seleccionar un jugador");
    }
  }
  
  public void validarOfertar() {
    this.validar();
    Set<Jugador> _listaJugadores = this.dtActivo.getListaJugadores();
    boolean _contains = _listaJugadores.contains(this.jugadorON);
    if (_contains) {
      throw new UserException("Ese jugador es tuyo");
    }
    boolean _contains_1 = this.listaMaquina.contains(this.jugadorON);
    if (_contains_1) {
      throw new UserException("Ese jugador es de la Máquina");
    }
  }
  
  public List<DT> getListaDT() {
    LigaMaster _instance = LigaMaster.getInstance();
    List<DT> _listaDT = _instance.getListaDT();
    final Function1<DT, String> _function = new Function1<DT, String>() {
      @Override
      public String apply(final DT it) {
        return it.getNombreDT();
      }
    };
    return IterableExtensions.<DT, String>sortBy(_listaDT, _function);
  }
  
  @Dependencies("jugadorON")
  public boolean getJugadorPropio() {
    Set<Jugador> _listaJugadores = this.dtActivo.getListaJugadores();
    return _listaJugadores.contains(this.jugadorON);
  }
  
  public void comprarSlot() {
    try {
      this.dtActivo.comprarSlot();
      LigaMaster _instance = LigaMaster.getInstance();
      _instance.guardarBase();
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        String _message = e.getMessage();
        throw new UserException(_message);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  public void venderAMaquina() {
    final Jugador jugador = this.jugadorON;
    double _precioMaquina = jugador.getPrecioMaquina();
    Precios _instance = Precios.getInstance();
    double _precio = _instance.getPrecio("PrecioMaquina");
    double _divide = (_precioMaquina / _precio);
    this.dtActivo.venderJugador(jugador, _divide);
    Transferencia _transferencia = new Transferencia();
    final Procedure1<Transferencia> _function = new Procedure1<Transferencia>() {
      @Override
      public void apply(final Transferencia it) {
        String _nombreDT = EquipoModel.this.dtActivo.getNombreDT();
        it.setDtVende(_nombreDT);
        it.setDtCompra("Libre");
        double _precioMaquina = jugador.getPrecioMaquina();
        double _divide = (_precioMaquina / 4.0);
        it.setMonto(_divide);
        String _nombre = jugador.getNombre();
        it.setJugadorComprado(_nombre);
        int _nivel = jugador.getNivel();
        it.setNivel(_nivel);
      }
    };
    Transferencia compraMaquina = ObjectExtensions.<Transferencia>operator_doubleArrow(_transferencia, _function);
    LigaMaster _instance_1 = LigaMaster.getInstance();
    Mercado _mercado = _instance_1.getMercado();
    _mercado.agregarTransferencia(compraMaquina);
    LigaMaster _instance_2 = LigaMaster.getInstance();
    _instance_2.guardarBase();
  }
  
  public void transferIn() {
    this.validar();
    Set<Jugador> _listaJugadores = this.dtON.getListaJugadores();
    boolean _contains = _listaJugadores.contains(this.jugadorON);
    if (_contains) {
      throw new UserException("Ya tiene ese jugador");
    }
    DT _propietario = this.jugadorON.getPropietario();
    _propietario.removeJugador(this.jugadorON);
    this.dtON.addJugador(this.jugadorON);
    this.listaMaquina.remove(this.jugadorON);
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.guardarBase();
    ObservableUtils.firePropertyChanged(this, "listaMaquina");
    ObservableUtils.firePropertyChanged(this, "dtON");
  }
  
  public void transferOut() {
    this.validar();
    this.listaMaquina.add(this.jugadorON);
    this.dtON.removeJugador(this.jugadorON);
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.guardarBase();
    ObservableUtils.firePropertyChanged(this, "listaMaquina");
    ObservableUtils.firePropertyChanged(this, "dtON");
  }
  
  public boolean esMaster() {
    LigaMaster _instance = LigaMaster.getInstance();
    DT _master = _instance.getMaster();
    return this.dtON.equals(_master);
  }
  
  public void buscar() {
    this.listaMaquina.clear();
    SoFifa _instance = SoFifa.getInstance();
    _instance.getJugadores(this.textoBusqueda);
  }
  
  public void comprarAMaquina() {
    this.validar();
    boolean _contains = this.listaMaquina.contains(this.jugadorON);
    boolean _not = (!_contains);
    if (_not) {
      throw new UserException("Ese jugador no es de la Máquina");
    }
    DT _propietario = this.jugadorON.getPropietario();
    String _nombreDT = _propietario.getNombreDT();
    boolean _equals = _nombreDT.equals("Libre");
    boolean _not_1 = (!_equals);
    if (_not_1) {
      throw new UserException("Ese jugador no está Libre");
    }
    final Jugador jugadorMaquina = this.jugadorON;
    try {
      double _precioMaquina = jugadorMaquina.getPrecioMaquina();
      this.dtON.comprarJugador(jugadorMaquina, _precioMaquina);
      LigaMaster _instance = LigaMaster.getInstance();
      _instance.guardarBase();
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        String _message = e.getMessage();
        throw new UserException(_message);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    this.listaMaquina.remove(jugadorMaquina);
    LigaMaster _instance_1 = LigaMaster.getInstance();
    _instance_1.guardarBase();
  }
  
  public void updatePlantel() {
    try {
      Set<Jugador> _listaJugadores = this.dtON.getListaJugadores();
      final Consumer<Jugador> _function = new Consumer<Jugador>() {
        @Override
        public void accept(final Jugador it) {
          it.update();
        }
      };
      _listaJugadores.forEach(_function);
      LigaMaster _instance = LigaMaster.getInstance();
      _instance.guardarBase();
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        throw new UserException("No se completo el update. 1:Fijarse si hay jugadores que no estan en sofifa 2:Internet");
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  @Pure
  public Torneo getTorneoON() {
    return this.torneoON;
  }
  
  public void setTorneoON(final Torneo torneoON) {
    this.torneoON = torneoON;
  }
  
  @Pure
  public DT getDtActivo() {
    return this.dtActivo;
  }
  
  public void setDtActivo(final DT dtActivo) {
    this.dtActivo = dtActivo;
  }
  
  @Pure
  public DT getDtON() {
    return this.dtON;
  }
  
  public void setDtON(final DT dtON) {
    this.dtON = dtON;
  }
  
  @Pure
  public Jugador getJugadorON() {
    return this.jugadorON;
  }
  
  public void setJugadorON(final Jugador jugadorON) {
    this.jugadorON = jugadorON;
  }
  
  @Pure
  public String getTextoBusqueda() {
    return this.textoBusqueda;
  }
  
  public void setTextoBusqueda(final String textoBusqueda) {
    this.textoBusqueda = textoBusqueda;
  }
  
  @Pure
  public List<Jugador> getListaMaquina() {
    return this.listaMaquina;
  }
  
  public void setListaMaquina(final List<Jugador> listaMaquina) {
    this.listaMaquina = listaMaquina;
  }
}
