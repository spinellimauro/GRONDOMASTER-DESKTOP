package master;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import datos.Precios;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class DT {
  private int id;

  private String nombreEquipo;

  private Set<Jugador> listaJugadores = CollectionLiterals.<Jugador>newHashSet();

  private String nombreDT;

  private String password;

  private double plata = 0.0;

  private int torneosDisponibles = 3;

  private int slots = 30;

  private double rank = 1.0;

  public void venderJugador(final Jugador jugador, final double precio) {
    try {
      boolean _contains = this.listaJugadores.contains(jugador);
      boolean _not = (!_contains);
      if (_not) {
        throw new Exception("Ese jugador no es tuyo");
      }
      this.incPlata(precio);
      this.removeJugador(jugador);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  public void comprarJugador(final Jugador jugador, final double precio) {
    try {
      if ((this.plata < precio)) {
        throw new Exception("No tenés suficiente plata");
      }
      int _cantJugadores = this.getCantJugadores();
      boolean _equals = (_cantJugadores == this.slots);
      if (_equals) {
        throw new Exception("No hay slots disponibles");
      }
      this.decPlata(precio);
      this.addJugador(jugador);
      jugador.setPrecioVenta(0);
      Transferencia _transferencia = new Transferencia();
      final Procedure1<Transferencia> _function = (Transferencia it) -> {
        it.setDtVende("Libre");
        it.setDtCompra(this.nombreDT);
        it.setMonto(jugador.getPrecioMaquina());
        it.setJugadorComprado(jugador.getNombre());
        it.setNivel(jugador.getNivel());
      };
      Transferencia compraMaquina = ObjectExtensions.<Transferencia>operator_doubleArrow(_transferencia, _function);
      LigaMaster.getInstance().getMercado().agregarTransferencia(compraMaquina);
      LigaMaster.getInstance().guardarBase();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @JsonIgnore
  public int getCantJugadores() {
    return this.listaJugadores.size();
  }

  public void addJugador(final Jugador jugador) {
    this.listaJugadores.add(jugador);
  }

  public void removeJugador(final Jugador jugador) {
    this.listaJugadores.remove(jugador);
  }

  public void comprarSlot() {
    try {
      final double precioSlot = Precios.getInstance().getPrecio("Slot");
      if ((this.plata < precioSlot)) {
        throw new Exception("No tenés suficiente plata");
      }
      this.slots++;
      this.decPlata(precioSlot);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  public void incPlata(final double monto) {
    double _plata = this.plata;
    this.plata = (_plata + monto);
  }

  public void decPlata(final double monto) {
    double _plata = this.plata;
    this.plata = (_plata - monto);
  }

  public void prestarPlata(final DT otro, final double monto) {
    try {
      if ((this.plata >= monto)) {
        double _plata = otro.plata;
        otro.plata = (_plata + monto);
        double _plata_1 = this.plata;
        this.plata = (_plata_1 - monto);
      } else {
        throw new Exception("No tenés suficiente plata");
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  public void incTorneos() {
    this.torneosDisponibles++;
  }

  public void decTorneos() {
    this.torneosDisponibles--;
  }

  public boolean getPagaImpuesto() {
    return (this.torneosDisponibles == 0);
  }

  @JsonIgnore
  public List<Jugador> getJugadoresConImpuesto() {
    final Function1<Jugador, Boolean> _function = (Jugador it) -> {
      return Boolean.valueOf(it.getPagaImpuesto());
    };
    return IterableExtensions.<Jugador>toList(IterableExtensions.<Jugador>filter(this.listaJugadores, _function));
  }

  public void pagarImpuesto(final List<Jugador> jugadoresAPagar) {
    final Consumer<Jugador> _function = (Jugador it) -> {
      this.pagarImpuesto(it);
    };
    jugadoresAPagar.forEach(_function);
    ArrayList<Jugador> jugadoresNoPagados = CollectionLiterals.<Jugador>newArrayList();
    jugadoresNoPagados.addAll(this.getJugadoresConImpuesto());
    jugadoresNoPagados.removeAll(jugadoresAPagar);
    final Consumer<Jugador> _function_1 = (Jugador it) -> {
      it.noSePago();
    };
    jugadoresNoPagados.forEach(_function_1);
    if ((this.torneosDisponibles != 0)) {
      this.decTorneos();
    } else {
      this.torneosDisponibles = 3;
    }
    final Function1<Jugador, Boolean> _function_2 = (Jugador it) -> {
      int _vecesNoPagadas = it.getVecesNoPagadas();
      return Boolean.valueOf((_vecesNoPagadas != 3));
    };
    boolean _forall = IterableExtensions.<Jugador>forall(jugadoresNoPagados, _function_2);
    if (_forall) {
    } else {
      final Function1<Jugador, Boolean> _function_3 = (Jugador it) -> {
        int _vecesNoPagadas = it.getVecesNoPagadas();
        return Boolean.valueOf((_vecesNoPagadas == 3));
      };
      List<Jugador> borrados = IterableExtensions.<Jugador>toList(IterableExtensions.<Jugador>filter(jugadoresNoPagados, _function_3));
      this.listaJugadores.removeAll(borrados);
      final Function1<Jugador, String> _function_4 = (Jugador it) -> {
        return it.getNombre();
      };
      List<String> borradosNombres = ListExtensions.<Jugador, String>map(borrados, _function_4);
      LigaMaster.getInstance().guardarBase();
      throw new UserException(("Se han borrado los siguientes jugadores" + borradosNombres));
    }
  }

  public void pagarImpuesto(final Jugador jugador) {
    this.decPlata(jugador.getImpuesto());
    jugador.pagar();
  }

  @JsonIgnore
  public List<Jugador> getListaJugadoresDeshabilitados() {
    final Function1<Jugador, Boolean> _function = (Jugador it) -> {
      boolean _isHabilitado = it.isHabilitado();
      return Boolean.valueOf((!_isHabilitado));
    };
    return IterableExtensions.<Jugador>toList(IterableExtensions.<Jugador>filter(this.listaJugadores, _function));
  }

  @Override
  public boolean equals(final Object obj) {
    boolean _xblockexpression = false;
    {
      boolean _equals = Objects.equal(obj, null);
      if (_equals) {
        return false;
      }
      boolean _isAssignableFrom = DT.class.isAssignableFrom(obj.getClass());
      boolean _not = (!_isAssignableFrom);
      if (_not) {
        return false;
      }
      final DT otroDT = ((DT) obj);
      _xblockexpression = this.nombreDT.equals(otroDT.nombreDT);
    }
    return _xblockexpression;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Pure
  public int getId() {
    return this.id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  @Pure
  public String getNombreEquipo() {
    return this.nombreEquipo;
  }

  public void setNombreEquipo(final String nombreEquipo) {
    this.nombreEquipo = nombreEquipo;
  }

  @Pure
  public Set<Jugador> getListaJugadores() {
    return this.listaJugadores;
  }

  public void setListaJugadores(final Set<Jugador> listaJugadores) {
    this.listaJugadores = listaJugadores;
  }

  @Pure
  public String getNombreDT() {
    return this.nombreDT;
  }

  public void setNombreDT(final String nombreDT) {
    this.nombreDT = nombreDT;
  }

  @Pure
  public String getPassword() {
    return this.password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  @Pure
  public double getPlata() {
    return this.plata;
  }

  public void setPlata(final double plata) {
    this.plata = plata;
  }

  @Pure
  public int getTorneosDisponibles() {
    return this.torneosDisponibles;
  }

  public void setTorneosDisponibles(final int torneosDisponibles) {
    this.torneosDisponibles = torneosDisponibles;
  }

  @Pure
  public int getSlots() {
    return this.slots;
  }

  public void setSlots(final int slots) {
    this.slots = slots;
  }

  @Pure
  public double getRank() {
    return this.rank;
  }

  public void setRank(final double rank) {
    this.rank = rank;
  }
}
