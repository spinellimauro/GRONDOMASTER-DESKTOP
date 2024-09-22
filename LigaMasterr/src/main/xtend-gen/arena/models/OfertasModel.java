package arena.models;

import datos.Mercado;
import java.util.List;
import master.DT;
import master.Jugador;
import master.LigaMaster;
import master.Oferta;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class OfertasModel {
  private DT dtON;
  
  private Oferta ofertaON;
  
  public OfertasModel(final DT dt) {
    this.dtON = dt;
  }
  
  public List<Oferta> getOfertasRecibidas() {
    LigaMaster _instance = LigaMaster.getInstance();
    Mercado _mercado = _instance.getMercado();
    return _mercado.getOfertasRecibidas(this.dtON);
  }
  
  public List<Oferta> getOfertasEnviadas() {
    LigaMaster _instance = LigaMaster.getInstance();
    Mercado _mercado = _instance.getMercado();
    return _mercado.getOfertasEnviadas(this.dtON);
  }
  
  public void aceptarOferta() {
    List<Oferta> _ofertasRecibidas = this.getOfertasRecibidas();
    boolean _contains = _ofertasRecibidas.contains(this.ofertaON);
    boolean _not = (!_contains);
    if (_not) {
      throw new UserException("No podás aceptar esa oferta");
    }
    DT _dtOfertante = this.ofertaON.getDtOfertante();
    int _slots = _dtOfertante.getSlots();
    DT _dtOfertante_1 = this.ofertaON.getDtOfertante();
    int _cantJugadores = _dtOfertante_1.getCantJugadores();
    int _plus = (_cantJugadores + 1);
    boolean _lessThan = (_slots < _plus);
    if (_lessThan) {
      throw new UserException("El DT ofertante no tiene suficiente slots");
    }
    DT _dtOfertante_2 = this.ofertaON.getDtOfertante();
    double _plata = _dtOfertante_2.getPlata();
    double _monto = this.ofertaON.getMonto();
    boolean _lessThan_1 = (_plata < _monto);
    if (_lessThan_1) {
      throw new UserException("El DT ofertante no tiene esa plata");
    }
    int _slots_1 = this.dtON.getSlots();
    int _cantJugadores_1 = this.dtON.getCantJugadores();
    List<Jugador> _jugadoresOfrecidos = this.ofertaON.getJugadoresOfrecidos();
    int _size = _jugadoresOfrecidos.size();
    int _plus_1 = (_cantJugadores_1 + _size);
    boolean _lessThan_2 = (_slots_1 < _plus_1);
    if (_lessThan_2) {
      throw new UserException("No tenés suficientes slots");
    }
    this.ofertaON.aceptar();
    ObservableUtils.firePropertyChanged(this, "ofertasRecibidas");
    this.guardar();
  }
  
  public void rechazarOferta() {
    this.ofertaON.rechazar();
    ObservableUtils.firePropertyChanged(this, "ofertasRecibidas");
    ObservableUtils.firePropertyChanged(this, "ofertasEnviadas");
    this.guardar();
  }
  
  public void guardar() {
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
  public Oferta getOfertaON() {
    return this.ofertaON;
  }
  
  public void setOfertaON(final Oferta ofertaON) {
    this.ofertaON = ofertaON;
  }
}
