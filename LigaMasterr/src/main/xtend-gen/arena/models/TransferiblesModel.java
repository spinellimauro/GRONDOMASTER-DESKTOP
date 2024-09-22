package arena.models;

import com.google.common.base.Objects;
import java.util.List;
import java.util.Set;
import master.DT;
import master.Jugador;
import master.LigaMaster;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class TransferiblesModel {
  private DT dtON;
  
  private Jugador jugadorON;
  
  public TransferiblesModel(final DT dt) {
    this.dtON = dt;
  }
  
  public List<Jugador> getTransferibles() {
    LigaMaster _instance = LigaMaster.getInstance();
    Set<Jugador> _listaTransferibles = _instance.getListaTransferibles();
    final Function1<Jugador, String> _function = new Function1<Jugador, String>() {
      @Override
      public String apply(final Jugador it) {
        return it.getNombre();
      }
    };
    return IterableExtensions.<Jugador, String>sortBy(_listaTransferibles, _function);
  }
  
  public void validar() {
    try {
      Set<Jugador> _listaJugadores = this.dtON.getListaJugadores();
      boolean _contains = _listaJugadores.contains(this.jugadorON);
      if (_contains) {
        throw new Exception("Ese jugador ya es tuyo");
      }
      boolean _equals = Objects.equal(this.jugadorON, null);
      if (_equals) {
        throw new Exception("Debe seleccionar un Jugador");
      }
      double _plata = this.dtON.getPlata();
      double _precioVenta = this.jugadorON.getPrecioVenta();
      boolean _lessThan = (_plata < _precioVenta);
      if (_lessThan) {
        throw new Exception("No tenés suficiente plata");
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void comprarJugador() {
    final Jugador jugador = this.jugadorON;
    try {
      this.validar();
      DT _propietario = jugador.getPropietario();
      double _precioVenta = jugador.getPrecioVenta();
      _propietario.venderJugador(jugador, _precioVenta);
      double _precioVenta_1 = jugador.getPrecioVenta();
      this.dtON.comprarJugador(jugador, _precioVenta_1);
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        String _message = e.getMessage();
        throw new UserException(_message);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    ObservableUtils.firePropertyChanged(this, "transferibles");
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.guardarBase();
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
}
