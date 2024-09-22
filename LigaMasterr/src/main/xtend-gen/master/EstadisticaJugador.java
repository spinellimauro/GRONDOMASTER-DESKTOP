package master;

import master.Jugador;
import master.Torneo;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class EstadisticaJugador {
  private int id;
  
  private String nombre;
  
  private int goles;
  
  public EstadisticaJugador(final Jugador jugador, final Torneo torneo) {
    int _id = jugador.getId();
    this.id = _id;
    String _nombre = jugador.getNombre();
    this.nombre = _nombre;
    int _goles = torneo.getGoles(jugador);
    this.goles = _goles;
  }
  
  @Pure
  public int getId() {
    return this.id;
  }
  
  public void setId(final int id) {
    this.id = id;
  }
  
  @Pure
  public String getNombre() {
    return this.nombre;
  }
  
  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }
  
  @Pure
  public int getGoles() {
    return this.goles;
  }
  
  public void setGoles(final int goles) {
    this.goles = goles;
  }
}
