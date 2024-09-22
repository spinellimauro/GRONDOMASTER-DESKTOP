package arena.models;

import arena.models.TorneoModel;
import datos.PremioEvento;
import datos.PremioPosicion;
import datos.PremiosTorneos;
import java.util.List;
import master.DT;
import master.LigaMaster;
import master.Torneo;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class PremiosTorneosModel {
  private Torneo torneoON;
  
  private DT dtON;
  
  private PremioPosicion posicionON;
  
  private PremioEvento eventoON;
  
  public PremiosTorneosModel(final TorneoModel model) {
    Torneo _torneoON = model.getTorneoON();
    this.torneoON = _torneoON;
    DT _dtON = model.getDtON();
    this.dtON = _dtON;
    List<PremioPosicion> _listaPosiciones = this.getListaPosiciones();
    PremioPosicion _get = _listaPosiciones.get(0);
    this.posicionON = _get;
    List<PremioEvento> _listaEventos = this.getListaEventos();
    PremioEvento _get_1 = _listaEventos.get(0);
    this.eventoON = _get_1;
  }
  
  public List<PremioPosicion> getListaPosiciones() {
    PremiosTorneos _premios = this.torneoON.getPremios();
    return _premios.getListaPosiciones();
  }
  
  public List<PremioEvento> getListaEventos() {
    PremiosTorneos _premios = this.torneoON.getPremios();
    return _premios.getListaEventos();
  }
  
  public boolean esMaster() {
    LigaMaster _instance = LigaMaster.getInstance();
    DT _master = _instance.getMaster();
    return this.dtON.equals(_master);
  }
  
  @Pure
  public Torneo getTorneoON() {
    return this.torneoON;
  }
  
  public void setTorneoON(final Torneo torneoON) {
    this.torneoON = torneoON;
  }
  
  @Pure
  public DT getDtON() {
    return this.dtON;
  }
  
  public void setDtON(final DT dtON) {
    this.dtON = dtON;
  }
  
  @Pure
  public PremioPosicion getPosicionON() {
    return this.posicionON;
  }
  
  public void setPosicionON(final PremioPosicion posicionON) {
    this.posicionON = posicionON;
  }
  
  @Pure
  public PremioEvento getEventoON() {
    return this.eventoON;
  }
  
  public void setEventoON(final PremioEvento eventoON) {
    this.eventoON = eventoON;
  }
}
