package master;

import java.util.List;
import master.DT;
import master.Partido;
import master.Torneo;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class EstadisticaTorneo {
  private String nombre;
  
  private String equipo;
  
  private int pj;
  
  private int g;
  
  private int e;
  
  private int p;
  
  private String goles;
  
  private int pts;
  
  public EstadisticaTorneo(final DT dt, final Torneo torneo) {
    String _nombreDT = dt.getNombreDT();
    this.nombre = _nombreDT;
    String _nombreEquipo = dt.getNombreEquipo();
    this.equipo = _nombreEquipo;
    List<Partido> _partidosJugados = torneo.getPartidosJugados(dt);
    int _size = _partidosJugados.size();
    this.pj = _size;
    List<Partido> _partidosJugados_1 = torneo.getPartidosJugados(dt);
    final Function1<Partido, Boolean> _function = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        int _puntos = it.getPuntos(dt);
        return Boolean.valueOf((_puntos == 3));
      }
    };
    Iterable<Partido> _filter = IterableExtensions.<Partido>filter(_partidosJugados_1, _function);
    int _size_1 = IterableExtensions.size(_filter);
    this.g = _size_1;
    List<Partido> _partidosJugados_2 = torneo.getPartidosJugados(dt);
    final Function1<Partido, Boolean> _function_1 = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        int _puntos = it.getPuntos(dt);
        return Boolean.valueOf((_puntos == 1));
      }
    };
    Iterable<Partido> _filter_1 = IterableExtensions.<Partido>filter(_partidosJugados_2, _function_1);
    int _size_2 = IterableExtensions.size(_filter_1);
    this.e = _size_2;
    List<Partido> _partidosJugados_3 = torneo.getPartidosJugados(dt);
    final Function1<Partido, Boolean> _function_2 = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        int _puntos = it.getPuntos(dt);
        return Boolean.valueOf((_puntos == 0));
      }
    };
    Iterable<Partido> _filter_2 = IterableExtensions.<Partido>filter(_partidosJugados_3, _function_2);
    int _size_3 = IterableExtensions.size(_filter_2);
    this.p = _size_3;
    int _golesFavor = torneo.getGolesFavor(dt);
    String _plus = (Integer.valueOf(_golesFavor) + ":");
    int _golesContra = torneo.getGolesContra(dt);
    String _plus_1 = (_plus + Integer.valueOf(_golesContra));
    this.goles = _plus_1;
    int _puntos = torneo.getPuntos(dt);
    this.pts = _puntos;
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
  public int getPj() {
    return this.pj;
  }
  
  public void setPj(final int pj) {
    this.pj = pj;
  }
  
  @Pure
  public int getG() {
    return this.g;
  }
  
  public void setG(final int g) {
    this.g = g;
  }
  
  @Pure
  public int getE() {
    return this.e;
  }
  
  public void setE(final int e) {
    this.e = e;
  }
  
  @Pure
  public int getP() {
    return this.p;
  }
  
  public void setP(final int p) {
    this.p = p;
  }
  
  @Pure
  public String getGoles() {
    return this.goles;
  }
  
  public void setGoles(final String goles) {
    this.goles = goles;
  }
  
  @Pure
  public int getPts() {
    return this.pts;
  }
  
  public void setPts(final int pts) {
    this.pts = pts;
  }
}
