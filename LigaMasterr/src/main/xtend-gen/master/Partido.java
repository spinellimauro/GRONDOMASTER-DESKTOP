package master;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Iterables;
import datos.PremiosTorneos;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import master.DT;
import master.Jugador;
import master.LigaMaster;
import master.Torneo;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class Partido {
  private int numeroFecha = 0;
  
  private DT dtLocal;
  
  private DT dtVisitante;
  
  private boolean terminado = false;
  
  @JsonIgnore
  private Torneo torneo;
  
  @JsonIgnore
  private List<Jugador> golesLocal = CollectionLiterals.<Jugador>newArrayList();
  
  @JsonIgnore
  private List<Jugador> golesVisitante = CollectionLiterals.<Jugador>newArrayList();
  
  @JsonIgnore
  private List<Jugador> listaAmarillas = CollectionLiterals.<Jugador>newArrayList();
  
  @JsonIgnore
  private List<Jugador> listaRojas = CollectionLiterals.<Jugador>newArrayList();
  
  public boolean getJugoPartido(final DT dt) {
    boolean _or = false;
    boolean _equals = this.dtLocal.equals(dt);
    if (_equals) {
      _or = true;
    } else {
      boolean _equals_1 = this.dtVisitante.equals(dt);
      _or = _equals_1;
    }
    return _or;
  }
  
  public String getScore() {
    int _size = this.golesLocal.size();
    String _plus = (Integer.valueOf(_size) + " - ");
    int _size_1 = this.golesVisitante.size();
    return (_plus + Integer.valueOf(_size_1));
  }
  
  public int getGoles(final Jugador jugador) {
    Iterable<Jugador> _plus = Iterables.<Jugador>concat(this.golesLocal, this.golesVisitante);
    List<Jugador> _list = IterableExtensions.<Jugador>toList(_plus);
    return Collections.frequency(_list, jugador);
  }
  
  public void addGol(final Jugador jugador) {
    Set<Jugador> _listaJugadores = this.dtLocal.getListaJugadores();
    boolean _contains = _listaJugadores.contains(jugador);
    if (_contains) {
      this.golesLocal.add(jugador);
    } else {
      this.golesVisitante.add(jugador);
    }
    ObservableUtils.firePropertyChanged(this, "score");
  }
  
  public void removeGol(final Jugador jugador) {
    Set<Jugador> _listaJugadores = this.dtLocal.getListaJugadores();
    boolean _contains = _listaJugadores.contains(jugador);
    if (_contains) {
      this.golesLocal.remove(jugador);
    } else {
      this.golesVisitante.remove(jugador);
    }
    ObservableUtils.firePropertyChanged(this, "score");
  }
  
  public void addAmarilla(final Jugador jugador) {
    boolean _and = false;
    int _amarillas = this.getAmarillas(jugador);
    boolean _lessThan = (_amarillas < 2);
    if (!_lessThan) {
      _and = false;
    } else {
      int _rojas = this.getRojas(jugador);
      boolean _lessThan_1 = (_rojas < 1);
      _and = _lessThan_1;
    }
    if (_and) {
      this.listaAmarillas.add(jugador);
    }
  }
  
  public void removeAmarilla(final Jugador jugador) {
    this.listaAmarillas.remove(jugador);
  }
  
  public void addRoja(final Jugador jugador) {
    boolean _and = false;
    int _rojas = this.getRojas(jugador);
    boolean _lessThan = (_rojas < 1);
    if (!_lessThan) {
      _and = false;
    } else {
      int _amarillas = this.getAmarillas(jugador);
      boolean _lessThan_1 = (_amarillas < 2);
      _and = _lessThan_1;
    }
    if (_and) {
      this.listaRojas.add(jugador);
    }
  }
  
  public void removeRoja(final Jugador jugador) {
    this.listaRojas.remove(jugador);
  }
  
  public boolean fueAmonestado(final Jugador jugador) {
    int _amarillas = this.getAmarillas(jugador);
    return (_amarillas == 1);
  }
  
  public boolean fueExpulsado(final Jugador jugador) {
    boolean _or = false;
    int _amarillas = this.getAmarillas(jugador);
    boolean _equals = (_amarillas == 2);
    if (_equals) {
      _or = true;
    } else {
      int _rojas = this.getRojas(jugador);
      boolean _equals_1 = (_rojas == 1);
      _or = _equals_1;
    }
    return _or;
  }
  
  public int getAmarillas(final Jugador jugador) {
    return Collections.frequency(this.listaAmarillas, jugador);
  }
  
  public int getRojas(final Jugador jugador) {
    return Collections.frequency(this.listaRojas, jugador);
  }
  
  public int getPuntos(final DT dt) {
    int _xifexpression = (int) 0;
    boolean _equals = dt.equals(this.dtLocal);
    if (_equals) {
      int _xifexpression_1 = (int) 0;
      int _size = this.golesLocal.size();
      int _size_1 = this.golesVisitante.size();
      boolean _greaterThan = (_size > _size_1);
      if (_greaterThan) {
        _xifexpression_1 = 3;
      } else {
        int _xifexpression_2 = (int) 0;
        int _size_2 = this.golesLocal.size();
        int _size_3 = this.golesVisitante.size();
        boolean _lessThan = (_size_2 < _size_3);
        if (_lessThan) {
          _xifexpression_2 = 0;
        } else {
          _xifexpression_2 = 1;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    } else {
      int _xifexpression_3 = (int) 0;
      int _size_4 = this.golesLocal.size();
      int _size_5 = this.golesVisitante.size();
      boolean _lessThan_1 = (_size_4 < _size_5);
      if (_lessThan_1) {
        _xifexpression_3 = 3;
      } else {
        int _xifexpression_4 = (int) 0;
        int _size_6 = this.golesLocal.size();
        int _size_7 = this.golesVisitante.size();
        boolean _greaterThan_1 = (_size_6 > _size_7);
        if (_greaterThan_1) {
          _xifexpression_4 = 0;
        } else {
          _xifexpression_4 = 1;
        }
        _xifexpression_3 = _xifexpression_4;
      }
      _xifexpression = _xifexpression_3;
    }
    return _xifexpression;
  }
  
  public int getGolesFavor(final DT dt) {
    int _xifexpression = (int) 0;
    boolean _equals = dt.equals(this.dtLocal);
    if (_equals) {
      _xifexpression = this.golesLocal.size();
    } else {
      _xifexpression = this.golesVisitante.size();
    }
    return _xifexpression;
  }
  
  public int getGolesContra(final DT dt) {
    int _xifexpression = (int) 0;
    boolean _equals = dt.equals(this.dtLocal);
    if (_equals) {
      _xifexpression = this.golesVisitante.size();
    } else {
      _xifexpression = this.golesLocal.size();
    }
    return _xifexpression;
  }
  
  @JsonIgnore
  public List<Jugador> getSuspendidos() {
    Set<Jugador> _listaJugadores = this.dtLocal.getListaJugadores();
    Set<Jugador> _listaJugadores_1 = this.dtVisitante.getListaJugadores();
    Iterable<Jugador> _plus = Iterables.<Jugador>concat(_listaJugadores, _listaJugadores_1);
    final Function1<Jugador, Boolean> _function = new Function1<Jugador, Boolean>() {
      @Override
      public Boolean apply(final Jugador it) {
        return Boolean.valueOf(Partido.this.torneo.estaSuspendido(it, Partido.this.numeroFecha));
      }
    };
    Iterable<Jugador> _filter = IterableExtensions.<Jugador>filter(_plus, _function);
    return IterableExtensions.<Jugador>toList(_filter);
  }
  
  @JsonIgnore
  public List<Jugador> getLesionados() {
    Set<Jugador> _listaJugadores = this.dtLocal.getListaJugadores();
    Set<Jugador> _listaJugadores_1 = this.dtVisitante.getListaJugadores();
    Iterable<Jugador> _plus = Iterables.<Jugador>concat(_listaJugadores, _listaJugadores_1);
    final Function1<Jugador, Boolean> _function = new Function1<Jugador, Boolean>() {
      @Override
      public Boolean apply(final Jugador it) {
        return Boolean.valueOf(it.estaLesionado());
      }
    };
    Iterable<Jugador> _filter = IterableExtensions.<Jugador>filter(_plus, _function);
    return IterableExtensions.<Jugador>toList(_filter);
  }
  
  public void terminarPartido() {
    try {
      if (this.terminado) {
        throw new Exception("El partido ya terminó");
      }
      this.terminado = true;
      List<Jugador> _lesionados = this.getLesionados();
      final Consumer<Jugador> _function = new Consumer<Jugador>() {
        @Override
        public void accept(final Jugador it) {
          it.decLesion();
        }
      };
      _lesionados.forEach(_function);
      this.sumarRanking();
      double _premio = this.getPremio(this.dtLocal);
      this.dtLocal.incPlata(_premio);
      double _premio_1 = this.getPremio(this.dtVisitante);
      this.dtVisitante.incPlata(_premio_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public double getPremio(final DT dt) {
    double _xifexpression = (double) 0;
    int _puntos = this.getPuntos(dt);
    boolean _equals = (_puntos == 3);
    if (_equals) {
      PremiosTorneos _premios = this.torneo.getPremios();
      double _premioEvento = _premios.getPremioEvento("Victoria");
      PremiosTorneos _premios_1 = this.torneo.getPremios();
      double _premioEvento_1 = _premios_1.getPremioEvento("Gol");
      int _golesFavor = this.getGolesFavor(dt);
      int _golesContra = this.getGolesContra(dt);
      int _minus = (_golesFavor - _golesContra);
      double _multiply = (_premioEvento_1 * _minus);
      _xifexpression = (_premioEvento + _multiply);
    } else {
      double _xifexpression_1 = (double) 0;
      int _puntos_1 = this.getPuntos(dt);
      boolean _equals_1 = (_puntos_1 == 1);
      if (_equals_1) {
        PremiosTorneos _premios_2 = this.torneo.getPremios();
        _xifexpression_1 = _premios_2.getPremioEvento("Empate");
      } else {
        _xifexpression_1 = 0;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public boolean ganoLocal() {
    boolean _xifexpression = false;
    int _size = this.golesLocal.size();
    int _size_1 = this.golesVisitante.size();
    boolean _greaterThan = (_size > _size_1);
    if (_greaterThan) {
      _xifexpression = true;
    } else {
      _xifexpression = false;
    }
    return _xifexpression;
  }
  
  public boolean ganoVisitante() {
    boolean _xifexpression = false;
    int _size = this.golesLocal.size();
    int _size_1 = this.golesVisitante.size();
    boolean _lessThan = (_size < _size_1);
    if (_lessThan) {
      _xifexpression = true;
    } else {
      _xifexpression = false;
    }
    return _xifexpression;
  }
  
  public void sumarRanking() {
    boolean _ganoLocal = this.ganoLocal();
    if (_ganoLocal) {
      this.rankVictoria(this.dtLocal, this.dtVisitante);
    } else {
      boolean _ganoVisitante = this.ganoVisitante();
      if (_ganoVisitante) {
        this.rankVictoria(this.dtVisitante, this.dtLocal);
      } else {
        boolean _empataron = this.empataron();
        if (_empataron) {
          this.empate();
        }
      }
    }
  }
  
  public boolean empataron() {
    boolean _xifexpression = false;
    int _size = this.golesLocal.size();
    int _size_1 = this.golesVisitante.size();
    boolean _equals = (_size == _size_1);
    if (_equals) {
      _xifexpression = true;
    } else {
      _xifexpression = false;
    }
    return _xifexpression;
  }
  
  public void rankVictoria(final DT dtGanador, final DT dtPerdedor) {
    double PbeforeLocal = dtGanador.getRank();
    double PbeforeVisitante = dtPerdedor.getRank();
    final double I = 10.0;
    final double W = 1.0;
    final double D = 0.0;
    double dr = (PbeforeLocal - PbeforeVisitante);
    double _pow = Math.pow(10.0, ((-dr) / 6.0));
    double _plus = (_pow + 1.0);
    double WeG = (1.0 / _plus);
    double _pow_1 = Math.pow(10.0, ((-dr) / 600.0));
    double _plus_1 = (_pow_1 + 1.0);
    double WeP = (1.0 / _plus_1);
    double PA = (PbeforeLocal + (I * (W - WeG)));
    double PB = (PbeforeVisitante + (I * (D - WeP)));
    if ((PA < 0)) {
      PA = 0;
    }
    if ((PB < 0)) {
      PB = 0;
    }
    dtGanador.setRank(PA);
    dtPerdedor.setRank(PB);
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.guardarBase();
  }
  
  public void empate() {
    double PbeforeLocal = this.dtLocal.getRank();
    double PbeforeVisitante = this.dtVisitante.getRank();
    final double I = 10.0;
    final double E = 0.5;
    double dr = (PbeforeLocal - PbeforeVisitante);
    double We = ((1.0 / 10.0) * (((-dr) / 60.0) + 1.0));
    double PA = (PbeforeLocal + (I * (E - We)));
    double PB = (PbeforeVisitante + (I * (E - We)));
    if ((PA < 0)) {
      PA = 0;
    }
    if ((PB < 0)) {
      PB = 0;
    }
    this.dtLocal.setRank(PA);
    this.dtVisitante.setRank(PB);
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.guardarBase();
  }
  
  @Pure
  public int getNumeroFecha() {
    return this.numeroFecha;
  }
  
  public void setNumeroFecha(final int numeroFecha) {
    this.numeroFecha = numeroFecha;
  }
  
  @Pure
  public DT getDtLocal() {
    return this.dtLocal;
  }
  
  public void setDtLocal(final DT dtLocal) {
    this.dtLocal = dtLocal;
  }
  
  @Pure
  public DT getDtVisitante() {
    return this.dtVisitante;
  }
  
  public void setDtVisitante(final DT dtVisitante) {
    this.dtVisitante = dtVisitante;
  }
  
  @Pure
  public boolean isTerminado() {
    return this.terminado;
  }
  
  public void setTerminado(final boolean terminado) {
    this.terminado = terminado;
  }
  
  @Pure
  public Torneo getTorneo() {
    return this.torneo;
  }
  
  public void setTorneo(final Torneo torneo) {
    this.torneo = torneo;
  }
  
  @Pure
  public List<Jugador> getGolesLocal() {
    return this.golesLocal;
  }
  
  public void setGolesLocal(final List<Jugador> golesLocal) {
    this.golesLocal = golesLocal;
  }
  
  @Pure
  public List<Jugador> getGolesVisitante() {
    return this.golesVisitante;
  }
  
  public void setGolesVisitante(final List<Jugador> golesVisitante) {
    this.golesVisitante = golesVisitante;
  }
  
  @Pure
  public List<Jugador> getListaAmarillas() {
    return this.listaAmarillas;
  }
  
  public void setListaAmarillas(final List<Jugador> listaAmarillas) {
    this.listaAmarillas = listaAmarillas;
  }
  
  @Pure
  public List<Jugador> getListaRojas() {
    return this.listaRojas;
  }
  
  public void setListaRojas(final List<Jugador> listaRojas) {
    this.listaRojas = listaRojas;
  }
}
