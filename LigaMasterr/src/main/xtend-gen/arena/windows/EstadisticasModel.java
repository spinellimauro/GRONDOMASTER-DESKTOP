package arena.windows;

import arena.windows.EstadisticaDT;
import arena.windows.EstadisticaJugador;
import java.util.List;
import java.util.function.Consumer;
import master.DT;
import master.Jugador;
import master.Torneo;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class EstadisticasModel {
  private List<EstadisticaDT> listaPosiciones = CollectionLiterals.<EstadisticaDT>newArrayList();
  
  private List<EstadisticaDT> listaFairPlay = CollectionLiterals.<EstadisticaDT>newArrayList();
  
  private List<EstadisticaJugador> listaGoleadores = CollectionLiterals.<EstadisticaJugador>newArrayList();
  
  public EstadisticasModel(final Torneo torneo) {
    List<DT> _listaPosiciones = torneo.getListaPosiciones();
    final Consumer<DT> _function = new Consumer<DT>() {
      @Override
      public void accept(final DT it) {
        EstadisticaDT _estadisticaDT = new EstadisticaDT(it, torneo);
        EstadisticasModel.this.listaPosiciones.add(_estadisticaDT);
      }
    };
    _listaPosiciones.forEach(_function);
    List<Jugador> _listaGoleadores = torneo.getListaGoleadores();
    final Consumer<Jugador> _function_1 = new Consumer<Jugador>() {
      @Override
      public void accept(final Jugador it) {
        EstadisticaJugador _estadisticaJugador = new EstadisticaJugador(it, torneo);
        EstadisticasModel.this.listaGoleadores.add(_estadisticaJugador);
      }
    };
    _listaGoleadores.forEach(_function_1);
    final Function1<EstadisticaDT, Integer> _function_2 = new Function1<EstadisticaDT, Integer>() {
      @Override
      public Integer apply(final EstadisticaDT it) {
        return Integer.valueOf(it.getPuntosFairPlay());
      }
    };
    List<EstadisticaDT> _sortBy = IterableExtensions.<EstadisticaDT, Integer>sortBy(this.listaPosiciones, _function_2);
    this.listaFairPlay = _sortBy;
  }
  
  @Pure
  public List<EstadisticaDT> getListaPosiciones() {
    return this.listaPosiciones;
  }
  
  public void setListaPosiciones(final List<EstadisticaDT> listaPosiciones) {
    this.listaPosiciones = listaPosiciones;
  }
  
  @Pure
  public List<EstadisticaDT> getListaFairPlay() {
    return this.listaFairPlay;
  }
  
  public void setListaFairPlay(final List<EstadisticaDT> listaFairPlay) {
    this.listaFairPlay = listaFairPlay;
  }
  
  @Pure
  public List<EstadisticaJugador> getListaGoleadores() {
    return this.listaGoleadores;
  }
  
  public void setListaGoleadores(final List<EstadisticaJugador> listaGoleadores) {
    this.listaGoleadores = listaGoleadores;
  }
}
