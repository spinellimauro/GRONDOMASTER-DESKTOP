package arena.windows;

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
public class EstadisticaDT {
  private Torneo torneo;
  
  private DT dt;
  
  public EstadisticaDT(final DT dtON, final Torneo torneoON) {
    this.dt = dtON;
    this.torneo = torneoON;
  }
  
  public String getNombreDT() {
    return this.dt.getNombreDT();
  }
  
  public int getPuntos() {
    return this.torneo.getPuntos(this.dt);
  }
  
  public int getAmarillas() {
    return this.torneo.getAmarillas(this.dt);
  }
  
  public int getRojas() {
    return this.torneo.getRojas(this.dt);
  }
  
  public int getPuntosFairPlay() {
    return this.torneo.getPuntosFairPlay(this.dt);
  }
  
  public int getGolesFavor() {
    return this.torneo.getGolesFavor(this.dt);
  }
  
  public int getGolesContra() {
    return this.torneo.getGolesContra(this.dt);
  }
  
  public int getDifGol() {
    int _golesFavor = this.getGolesFavor();
    int _golesContra = this.getGolesContra();
    return (_golesFavor - _golesContra);
  }
  
  public int getPartJugados() {
    List<Partido> _partidosJugados = this.torneo.getPartidosJugados(this.dt);
    return _partidosJugados.size();
  }
  
  public int getPartGanados() {
    List<Partido> _partidosJugados = this.torneo.getPartidosJugados(this.dt);
    final Function1<Partido, Boolean> _function = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        int _puntos = it.getPuntos(EstadisticaDT.this.dt);
        return Boolean.valueOf((_puntos == 3));
      }
    };
    Iterable<Partido> _filter = IterableExtensions.<Partido>filter(_partidosJugados, _function);
    return IterableExtensions.size(_filter);
  }
  
  public int getPartEmpatados() {
    List<Partido> _partidosJugados = this.torneo.getPartidosJugados(this.dt);
    final Function1<Partido, Boolean> _function = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        int _puntos = it.getPuntos(EstadisticaDT.this.dt);
        return Boolean.valueOf((_puntos == 1));
      }
    };
    Iterable<Partido> _filter = IterableExtensions.<Partido>filter(_partidosJugados, _function);
    return IterableExtensions.size(_filter);
  }
  
  public int getPartPerdidos() {
    List<Partido> _partidosJugados = this.torneo.getPartidosJugados(this.dt);
    final Function1<Partido, Boolean> _function = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        int _puntos = it.getPuntos(EstadisticaDT.this.dt);
        return Boolean.valueOf((_puntos == 0));
      }
    };
    Iterable<Partido> _filter = IterableExtensions.<Partido>filter(_partidosJugados, _function);
    return IterableExtensions.size(_filter);
  }
  
  @Pure
  public Torneo getTorneo() {
    return this.torneo;
  }
  
  public void setTorneo(final Torneo torneo) {
    this.torneo = torneo;
  }
  
  @Pure
  public DT getDt() {
    return this.dt;
  }
  
  public void setDt(final DT dt) {
    this.dt = dt;
  }
}
