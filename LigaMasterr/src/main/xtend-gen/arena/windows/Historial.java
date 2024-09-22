package arena.windows;

import java.util.List;
import master.DT;
import master.LigaMaster;
import master.Partido;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class Historial {
  private String dt;
  
  private int jugados;
  
  private int ganados;
  
  private int empatados;
  
  private int perdidos;
  
  private int diferencia;
  
  public Historial(final DT dt, final DT otroDT) {
    String _nombreDT = otroDT.getNombreDT();
    this.dt = _nombreDT;
    LigaMaster _instance = LigaMaster.getInstance();
    List<Partido> _partidosJugados = _instance.getPartidosJugados(dt, otroDT);
    int _size = _partidosJugados.size();
    this.jugados = _size;
    LigaMaster _instance_1 = LigaMaster.getInstance();
    int _partidosGanados = _instance_1.getPartidosGanados(dt, otroDT);
    this.ganados = _partidosGanados;
    LigaMaster _instance_2 = LigaMaster.getInstance();
    int _partidosEmpatados = _instance_2.getPartidosEmpatados(dt, otroDT);
    this.empatados = _partidosEmpatados;
    LigaMaster _instance_3 = LigaMaster.getInstance();
    int _partidosPerdidos = _instance_3.getPartidosPerdidos(dt, otroDT);
    this.perdidos = _partidosPerdidos;
    this.diferencia = (this.ganados - this.perdidos);
  }
  
  @Pure
  public String getDt() {
    return this.dt;
  }
  
  public void setDt(final String dt) {
    this.dt = dt;
  }
  
  @Pure
  public int getJugados() {
    return this.jugados;
  }
  
  public void setJugados(final int jugados) {
    this.jugados = jugados;
  }
  
  @Pure
  public int getGanados() {
    return this.ganados;
  }
  
  public void setGanados(final int ganados) {
    this.ganados = ganados;
  }
  
  @Pure
  public int getEmpatados() {
    return this.empatados;
  }
  
  public void setEmpatados(final int empatados) {
    this.empatados = empatados;
  }
  
  @Pure
  public int getPerdidos() {
    return this.perdidos;
  }
  
  public void setPerdidos(final int perdidos) {
    this.perdidos = perdidos;
  }
  
  @Pure
  public int getDiferencia() {
    return this.diferencia;
  }
  
  public void setDiferencia(final int diferencia) {
    this.diferencia = diferencia;
  }
}
