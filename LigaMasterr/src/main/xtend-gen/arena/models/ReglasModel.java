package arena.models;

import datos.PrecioEvento;
import datos.PrecioNivel;
import datos.Precios;
import java.util.List;
import master.DT;
import master.LigaMaster;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class ReglasModel {
  private DT dtON;
  
  private PrecioNivel nivelON = this.getListaNiveles().get(0);
  
  private PrecioEvento eventoON = this.getListaEventos().get(0);
  
  public ReglasModel(final DT model) {
    this.dtON = model;
  }
  
  public List<PrecioNivel> getListaNiveles() {
    Precios _instance = Precios.getInstance();
    return _instance.getListaNiveles();
  }
  
  public List<PrecioEvento> getListaEventos() {
    Precios _instance = Precios.getInstance();
    return _instance.getListaEventos();
  }
  
  public boolean esMaster() {
    LigaMaster _instance = LigaMaster.getInstance();
    DT _master = _instance.getMaster();
    return this.dtON.equals(_master);
  }
  
  @Pure
  public DT getDtON() {
    return this.dtON;
  }
  
  public void setDtON(final DT dtON) {
    this.dtON = dtON;
  }
  
  @Pure
  public PrecioNivel getNivelON() {
    return this.nivelON;
  }
  
  public void setNivelON(final PrecioNivel nivelON) {
    this.nivelON = nivelON;
  }
  
  @Pure
  public PrecioEvento getEventoON() {
    return this.eventoON;
  }
  
  public void setEventoON(final PrecioEvento eventoON) {
    this.eventoON = eventoON;
  }
}
