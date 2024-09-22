package master;

import master.DT;
import master.Torneo;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class EstadisticaFairPlay {
  private String nombre;
  
  private String equipo;
  
  private int amarillas;
  
  private int rojas;
  
  private int puntos;
  
  public EstadisticaFairPlay(final DT dt, final Torneo torneo) {
    String _nombreDT = dt.getNombreDT();
    this.nombre = _nombreDT;
    String _nombreEquipo = dt.getNombreEquipo();
    this.equipo = _nombreEquipo;
    int _amarillas = torneo.getAmarillas(dt);
    this.amarillas = _amarillas;
    int _rojas = torneo.getRojas(dt);
    this.rojas = _rojas;
    int _puntosFairPlay = torneo.getPuntosFairPlay(dt);
    this.puntos = _puntosFairPlay;
  }
  
  @Pure
  public String getNombre() {
    return this.nombre;
  }
  
  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }
  
  @Pure
  public String getEquipo() {
    return this.equipo;
  }
  
  public void setEquipo(final String equipo) {
    this.equipo = equipo;
  }
  
  @Pure
  public int getAmarillas() {
    return this.amarillas;
  }
  
  public void setAmarillas(final int amarillas) {
    this.amarillas = amarillas;
  }
  
  @Pure
  public int getRojas() {
    return this.rojas;
  }
  
  public void setRojas(final int rojas) {
    this.rojas = rojas;
  }
  
  @Pure
  public int getPuntos() {
    return this.puntos;
  }
  
  public void setPuntos(final int puntos) {
    this.puntos = puntos;
  }
}
