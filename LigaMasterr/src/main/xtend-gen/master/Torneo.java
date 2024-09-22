package master;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Iterables;
import datos.PremiosTorneos;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import master.DT;
import master.EstadisticaFairPlay;
import master.EstadisticaJugador;
import master.EstadisticaTorneo;
import master.Jugador;
import master.Partido;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class Torneo {
  private String nombreTorneo = "";
  
  @JsonIgnore
  private List<DT> listaParticipantes = CollectionLiterals.<DT>newArrayList();
  
  private List<Partido> listaPartidos = CollectionLiterals.<Partido>newArrayList();
  
  private PremiosTorneos premios = new PremiosTorneos();
  
  private int limiteAmarillas = 3;
  
  private boolean terminado = false;
  
  public void sortearFechas() {
    this.listaPartidos.clear();
    Collections.shuffle(this.listaParticipantes);
    DT _dT = new DT();
    final Procedure1<DT> _function = new Procedure1<DT>() {
      @Override
      public void apply(final DT it) {
        it.setNombreDT("Libre");
      }
    };
    final DT libre = ObjectExtensions.<DT>operator_doubleArrow(_dT, _function);
    int _size = this.listaParticipantes.size();
    int _modulo = (_size % 2);
    boolean _notEquals = (_modulo != 0);
    if (_notEquals) {
      this.listaParticipantes.add(libre);
    }
    for (int fecha = 0; (fecha < this.getNumeroFechas()); fecha++) {
      for (int partido = 0; (partido < (this.listaParticipantes.size() / 2)); partido++) {
        {
          int _numeroFechas = this.getNumeroFechas();
          int local = ((fecha + partido) % _numeroFechas);
          int _xifexpression = (int) 0;
          if ((partido == 0)) {
            _xifexpression = this.getNumeroFechas();
          } else {
            int _numeroFechas_1 = this.getNumeroFechas();
            int _minus = (_numeroFechas_1 - partido);
            int _plus = (_minus + fecha);
            int _numeroFechas_2 = this.getNumeroFechas();
            _xifexpression = (_plus % _numeroFechas_2);
          }
          int visitante = _xifexpression;
          final Partido partidoNuevo = new Partido();
          partidoNuevo.setNumeroFecha((fecha + 1));
          DT _get = this.listaParticipantes.get(local);
          partidoNuevo.setDtLocal(_get);
          DT _get_1 = this.listaParticipantes.get(visitante);
          partidoNuevo.setDtVisitante(_get_1);
          boolean _jugoPartido = partidoNuevo.getJugoPartido(libre);
          boolean _not = (!_jugoPartido);
          if (_not) {
            this.addPartido(partidoNuevo);
          }
        }
      }
    }
    this.listaParticipantes.remove(libre);
  }
  
  public void addPartido(final Partido partido) {
    partido.setTorneo(this);
    this.listaPartidos.add(partido);
  }
  
  public int getNumeroFechas() {
    int _xblockexpression = (int) 0;
    {
      final int nroDts = this.listaParticipantes.size();
      int _xifexpression = (int) 0;
      if (((nroDts % 2) == 0)) {
        _xifexpression = (nroDts - 1);
      } else {
        _xifexpression = nroDts;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public List<Integer> getListaFechas() {
    int _numeroFechas = this.getNumeroFechas();
    IntegerRange _upTo = new IntegerRange(1, _numeroFechas);
    return IterableExtensions.<Integer>toList(_upTo);
  }
  
  public List<Partido> getFecha(final int entero) {
    final Function1<Partido, Boolean> _function = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        int _numeroFecha = it.getNumeroFecha();
        return Boolean.valueOf((_numeroFecha == entero));
      }
    };
    Iterable<Partido> _filter = IterableExtensions.<Partido>filter(this.listaPartidos, _function);
    return IterableExtensions.<Partido>toList(_filter);
  }
  
  @JsonIgnore
  public List<Jugador> getListaJugadores() {
    final Function1<DT, Set<Jugador>> _function = new Function1<DT, Set<Jugador>>() {
      @Override
      public Set<Jugador> apply(final DT it) {
        return it.getListaJugadores();
      }
    };
    List<Set<Jugador>> _map = ListExtensions.<DT, Set<Jugador>>map(this.listaParticipantes, _function);
    Iterable<Jugador> _flatten = Iterables.<Jugador>concat(_map);
    return IterableExtensions.<Jugador>toList(_flatten);
  }
  
  @JsonIgnore
  public List<DT> getListaPosiciones() {
    final Function1<DT, Integer> _function = new Function1<DT, Integer>() {
      @Override
      public Integer apply(final DT it) {
        return Integer.valueOf(Torneo.this.getPuntos(it));
      }
    };
    List<DT> _sortBy = IterableExtensions.<DT, Integer>sortBy(this.listaParticipantes, _function);
    return ListExtensions.<DT>reverse(_sortBy);
  }
  
  @JsonIgnore
  public List<Jugador> getListaGoleadores() {
    List<Jugador> _listaJugadores = this.getListaJugadores();
    final Function1<Jugador, Boolean> _function = new Function1<Jugador, Boolean>() {
      @Override
      public Boolean apply(final Jugador it) {
        int _goles = Torneo.this.getGoles(it);
        return Boolean.valueOf((_goles != 0));
      }
    };
    Iterable<Jugador> _filter = IterableExtensions.<Jugador>filter(_listaJugadores, _function);
    final Function1<Jugador, Integer> _function_1 = new Function1<Jugador, Integer>() {
      @Override
      public Integer apply(final Jugador it) {
        return Integer.valueOf(Torneo.this.getGoles(it));
      }
    };
    List<Jugador> _sortBy = IterableExtensions.<Jugador, Integer>sortBy(_filter, _function_1);
    return ListExtensions.<Jugador>reverse(_sortBy);
  }
  
  @JsonIgnore
  public List<DT> getListaFairPlay() {
    final Function1<DT, Integer> _function = new Function1<DT, Integer>() {
      @Override
      public Integer apply(final DT it) {
        return Integer.valueOf(Torneo.this.getPuntosFairPlay(it));
      }
    };
    return IterableExtensions.<DT, Integer>sortBy(this.listaParticipantes, _function);
  }
  
  public List<EstadisticaTorneo> getTablaPosiciones() {
    List<DT> _listaPosiciones = this.getListaPosiciones();
    final Function1<DT, EstadisticaTorneo> _function = new Function1<DT, EstadisticaTorneo>() {
      @Override
      public EstadisticaTorneo apply(final DT it) {
        return new EstadisticaTorneo(it, Torneo.this);
      }
    };
    return ListExtensions.<DT, EstadisticaTorneo>map(_listaPosiciones, _function);
  }
  
  public List<EstadisticaFairPlay> getTablaFairPlay() {
    List<DT> _listaFairPlay = this.getListaFairPlay();
    final Function1<DT, EstadisticaFairPlay> _function = new Function1<DT, EstadisticaFairPlay>() {
      @Override
      public EstadisticaFairPlay apply(final DT it) {
        return new EstadisticaFairPlay(it, Torneo.this);
      }
    };
    return ListExtensions.<DT, EstadisticaFairPlay>map(_listaFairPlay, _function);
  }
  
  public List<EstadisticaJugador> getTablaGoleadores() {
    List<Jugador> _listaGoleadores = this.getListaGoleadores();
    final Function1<Jugador, EstadisticaJugador> _function = new Function1<Jugador, EstadisticaJugador>() {
      @Override
      public EstadisticaJugador apply(final Jugador it) {
        return new EstadisticaJugador(it, Torneo.this);
      }
    };
    return ListExtensions.<Jugador, EstadisticaJugador>map(_listaGoleadores, _function);
  }
  
  public int getAmarillas(final DT dt) {
    Set<Jugador> _listaJugadores = dt.getListaJugadores();
    final Function2<Integer, Jugador, Integer> _function = new Function2<Integer, Jugador, Integer>() {
      @Override
      public Integer apply(final Integer acum, final Jugador jugador) {
        int _amarillas = Torneo.this.getAmarillas(jugador);
        return Integer.valueOf(((acum).intValue() + _amarillas));
      }
    };
    return (int) IterableExtensions.<Jugador, Integer>fold(_listaJugadores, Integer.valueOf(0), _function);
  }
  
  public int getRojas(final DT dt) {
    Set<Jugador> _listaJugadores = dt.getListaJugadores();
    final Function2<Integer, Jugador, Integer> _function = new Function2<Integer, Jugador, Integer>() {
      @Override
      public Integer apply(final Integer acum, final Jugador jugador) {
        int _rojas = Torneo.this.getRojas(jugador);
        return Integer.valueOf(((acum).intValue() + _rojas));
      }
    };
    return (int) IterableExtensions.<Jugador, Integer>fold(_listaJugadores, Integer.valueOf(0), _function);
  }
  
  public int getPuntosFairPlay(final DT dt) {
    int _amarillas = this.getAmarillas(dt);
    int _multiply = (_amarillas * 4);
    int _rojas = this.getRojas(dt);
    int _multiply_1 = (_rojas * 12);
    return (_multiply + _multiply_1);
  }
  
  public List<Partido> getPartidosJugados(final DT dt) {
    final Function1<Partido, Boolean> _function = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        return Boolean.valueOf(it.isTerminado());
      }
    };
    Iterable<Partido> _filter = IterableExtensions.<Partido>filter(this.listaPartidos, _function);
    final Function1<Partido, Boolean> _function_1 = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        return Boolean.valueOf(it.getJugoPartido(dt));
      }
    };
    Iterable<Partido> _filter_1 = IterableExtensions.<Partido>filter(_filter, _function_1);
    return IterableExtensions.<Partido>toList(_filter_1);
  }
  
  public int getGolesFavor(final DT dt) {
    List<Partido> _partidosJugados = this.getPartidosJugados(dt);
    final Function2<Integer, Partido, Integer> _function = new Function2<Integer, Partido, Integer>() {
      @Override
      public Integer apply(final Integer acum, final Partido partido) {
        int _golesFavor = partido.getGolesFavor(dt);
        return Integer.valueOf(((acum).intValue() + _golesFavor));
      }
    };
    return (int) IterableExtensions.<Partido, Integer>fold(_partidosJugados, Integer.valueOf(0), _function);
  }
  
  public int getGolesContra(final DT dt) {
    List<Partido> _partidosJugados = this.getPartidosJugados(dt);
    final Function2<Integer, Partido, Integer> _function = new Function2<Integer, Partido, Integer>() {
      @Override
      public Integer apply(final Integer acum, final Partido partido) {
        int _golesContra = partido.getGolesContra(dt);
        return Integer.valueOf(((acum).intValue() + _golesContra));
      }
    };
    return (int) IterableExtensions.<Partido, Integer>fold(_partidosJugados, Integer.valueOf(0), _function);
  }
  
  public int getPuntos(final DT dt) {
    List<Partido> _partidosJugados = this.getPartidosJugados(dt);
    final Function2<Integer, Partido, Integer> _function = new Function2<Integer, Partido, Integer>() {
      @Override
      public Integer apply(final Integer acum, final Partido partido) {
        int _puntos = partido.getPuntos(dt);
        return Integer.valueOf(((acum).intValue() + _puntos));
      }
    };
    return (int) IterableExtensions.<Partido, Integer>fold(_partidosJugados, Integer.valueOf(0), _function);
  }
  
  public int getGoles(final Jugador jugador) {
    int _xblockexpression = (int) 0;
    {
      final Function1<Partido, Iterable<Jugador>> _function = new Function1<Partido, Iterable<Jugador>>() {
        @Override
        public Iterable<Jugador> apply(final Partido it) {
          List<Jugador> _golesLocal = it.getGolesLocal();
          List<Jugador> _golesVisitante = it.getGolesVisitante();
          return Iterables.<Jugador>concat(_golesLocal, _golesVisitante);
        }
      };
      List<Iterable<Jugador>> _map = ListExtensions.<Partido, Iterable<Jugador>>map(this.listaPartidos, _function);
      Iterable<Jugador> _flatten = Iterables.<Jugador>concat(_map);
      final List<Jugador> listaGoles = IterableExtensions.<Jugador>toList(_flatten);
      _xblockexpression = Collections.frequency(listaGoles, jugador);
    }
    return _xblockexpression;
  }
  
  public int getAmarillas(final Jugador jugador) {
    int _xblockexpression = (int) 0;
    {
      final Function1<Partido, List<Jugador>> _function = new Function1<Partido, List<Jugador>>() {
        @Override
        public List<Jugador> apply(final Partido it) {
          return it.getListaAmarillas();
        }
      };
      List<List<Jugador>> _map = ListExtensions.<Partido, List<Jugador>>map(this.listaPartidos, _function);
      Iterable<Jugador> _flatten = Iterables.<Jugador>concat(_map);
      final List<Jugador> listaRojas = IterableExtensions.<Jugador>toList(_flatten);
      _xblockexpression = Collections.frequency(listaRojas, jugador);
    }
    return _xblockexpression;
  }
  
  public int getRojas(final Jugador jugador) {
    int _xblockexpression = (int) 0;
    {
      final Function1<Partido, List<Jugador>> _function = new Function1<Partido, List<Jugador>>() {
        @Override
        public List<Jugador> apply(final Partido it) {
          return it.getListaRojas();
        }
      };
      List<List<Jugador>> _map = ListExtensions.<Partido, List<Jugador>>map(this.listaPartidos, _function);
      Iterable<Jugador> _flatten = Iterables.<Jugador>concat(_map);
      final List<Jugador> listaRojas = IterableExtensions.<Jugador>toList(_flatten);
      _xblockexpression = Collections.frequency(listaRojas, jugador);
    }
    return _xblockexpression;
  }
  
  public boolean estaSuspendido(final Jugador jugador, final int fecha) {
    boolean _xblockexpression = false;
    {
      final List<Partido> fechaAnterior = this.getFecha((fecha - 1));
      boolean _or = false;
      final Function1<Partido, Boolean> _function = new Function1<Partido, Boolean>() {
        @Override
        public Boolean apply(final Partido it) {
          return Boolean.valueOf(it.fueExpulsado(jugador));
        }
      };
      boolean _exists = IterableExtensions.<Partido>exists(fechaAnterior, _function);
      if (_exists) {
        _or = true;
      } else {
        boolean _and = false;
        final Function1<Partido, Boolean> _function_1 = new Function1<Partido, Boolean>() {
          @Override
          public Boolean apply(final Partido it) {
            return Boolean.valueOf(it.fueAmonestado(jugador));
          }
        };
        boolean _exists_1 = IterableExtensions.<Partido>exists(fechaAnterior, _function_1);
        if (!_exists_1) {
          _and = false;
        } else {
          int _amarillas = this.getAmarillas(jugador);
          int _modulo = (_amarillas % this.limiteAmarillas);
          boolean _equals = (_modulo == 0);
          _and = _equals;
        }
        _or = _and;
      }
      _xblockexpression = _or;
    }
    return _xblockexpression;
  }
  
  public DT getPropietario(final Jugador jugador) {
    final Function1<DT, Boolean> _function = new Function1<DT, Boolean>() {
      @Override
      public Boolean apply(final DT it) {
        Set<Jugador> _listaJugadores = it.getListaJugadores();
        return Boolean.valueOf(_listaJugadores.contains(jugador));
      }
    };
    return IterableExtensions.<DT>findFirst(this.listaParticipantes, _function);
  }
  
  public void terminarTorneo() {
    try {
      if (this.terminado) {
        throw new Exception("El torneo ya terminó");
      }
      final Function1<Partido, Boolean> _function = new Function1<Partido, Boolean>() {
        @Override
        public Boolean apply(final Partido p) {
          boolean _isTerminado = p.isTerminado();
          return Boolean.valueOf((!_isTerminado));
        }
      };
      boolean _exists = IterableExtensions.<Partido>exists(this.listaPartidos, _function);
      if (_exists) {
        throw new Exception("Hay partidos sin terminar");
      }
      int _cantPremios = this.premios.getCantPremios();
      int _size = this.listaParticipantes.size();
      boolean _greaterThan = (_cantPremios > _size);
      if (_greaterThan) {
        int _cantPremios_1 = this.premios.getCantPremios();
        int _size_1 = this.listaParticipantes.size();
        int _minus = (_cantPremios_1 - _size_1);
        String _plus = ("Faltan " + Integer.valueOf(_minus));
        String _plus_1 = (_plus + " DT más");
        throw new Exception(_plus_1);
      }
      this.terminado = true;
      for (int i = 0; (i < this.premios.getCantPremios()); i++) {
        List<DT> _listaPosiciones = this.getListaPosiciones();
        DT _get = _listaPosiciones.get(i);
        double _premio = this.premios.getPremio(Integer.valueOf((i + 1)));
        _get.incPlata(_premio);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Pure
  public String getNombreTorneo() {
    return this.nombreTorneo;
  }
  
  public void setNombreTorneo(final String nombreTorneo) {
    this.nombreTorneo = nombreTorneo;
  }
  
  @Pure
  public List<DT> getListaParticipantes() {
    return this.listaParticipantes;
  }
  
  public void setListaParticipantes(final List<DT> listaParticipantes) {
    this.listaParticipantes = listaParticipantes;
  }
  
  @Pure
  public List<Partido> getListaPartidos() {
    return this.listaPartidos;
  }
  
  public void setListaPartidos(final List<Partido> listaPartidos) {
    this.listaPartidos = listaPartidos;
  }
  
  @Pure
  public PremiosTorneos getPremios() {
    return this.premios;
  }
  
  public void setPremios(final PremiosTorneos premios) {
    this.premios = premios;
  }
  
  @Pure
  public int getLimiteAmarillas() {
    return this.limiteAmarillas;
  }
  
  public void setLimiteAmarillas(final int limiteAmarillas) {
    this.limiteAmarillas = limiteAmarillas;
  }
  
  @Pure
  public boolean isTerminado() {
    return this.terminado;
  }
  
  public void setTerminado(final boolean terminado) {
    this.terminado = terminado;
  }
}
