package master;

import com.google.common.collect.Iterables;
import com.thoughtworks.xstream.XStream;
import datos.Mercado;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import master.DT;
import master.Jugador;
import master.Partido;
import master.Torneo;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class LigaMaster {
  private static LigaMaster instance = new LigaMaster();
  
  private List<Torneo> listaTorneos = CollectionLiterals.<Torneo>newArrayList(ObjectExtensions.<Torneo>operator_doubleArrow(new Torneo(), new Procedure1<Torneo>() {
    @Override
    public void apply(final Torneo it) {
      it.setNombreTorneo("Nuevo Torneo");
    }
  }));
  
  private List<DT> listaDT = CollectionLiterals.<DT>newArrayList();
  
  private Mercado mercado = new Mercado();
  
  private LigaMaster() {
  }
  
  public DT getMaster() {
    DT _dT = new DT();
    final Procedure1<DT> _function = new Procedure1<DT>() {
      @Override
      public void apply(final DT it) {
        it.setNombreDT("Master");
        it.setPassword("ARG123");
      }
    };
    return ObjectExtensions.<DT>operator_doubleArrow(_dT, _function);
  }
  
  public Set<Jugador> getListaJugador() {
    final Function1<DT, Set<Jugador>> _function = new Function1<DT, Set<Jugador>>() {
      @Override
      public Set<Jugador> apply(final DT it) {
        return it.getListaJugadores();
      }
    };
    List<Set<Jugador>> _map = ListExtensions.<DT, Set<Jugador>>map(this.listaDT, _function);
    Iterable<Jugador> _flatten = Iterables.<Jugador>concat(_map);
    return IterableExtensions.<Jugador>toSet(_flatten);
  }
  
  public Set<DT> getDTsQuePagan() {
    final Function1<DT, Boolean> _function = new Function1<DT, Boolean>() {
      @Override
      public Boolean apply(final DT it) {
        int _torneosDisponibles = it.getTorneosDisponibles();
        return Boolean.valueOf((_torneosDisponibles == 0));
      }
    };
    Iterable<DT> _filter = IterableExtensions.<DT>filter(this.listaDT, _function);
    return IterableExtensions.<DT>toSet(_filter);
  }
  
  public Set<Jugador> getListaTransferibles() {
    final Function1<DT, Set<Jugador>> _function = new Function1<DT, Set<Jugador>>() {
      @Override
      public Set<Jugador> apply(final DT it) {
        return LigaMaster.this.getListaJugador();
      }
    };
    List<Set<Jugador>> _map = ListExtensions.<DT, Set<Jugador>>map(this.listaDT, _function);
    Iterable<Jugador> _flatten = Iterables.<Jugador>concat(_map);
    final Function1<Jugador, Boolean> _function_1 = new Function1<Jugador, Boolean>() {
      @Override
      public Boolean apply(final Jugador it) {
        double _precioVenta = it.getPrecioVenta();
        return Boolean.valueOf((_precioVenta > 0));
      }
    };
    Iterable<Jugador> _filter = IterableExtensions.<Jugador>filter(_flatten, _function_1);
    return IterableExtensions.<Jugador>toSet(_filter);
  }
  
  public void leerBase() {
    try {
      XStream _xStream = new XStream();
      FileReader _fileReader = new FileReader("data.xml");
      Object _fromXML = _xStream.fromXML(_fileReader);
      LigaMaster.instance = ((LigaMaster) _fromXML);
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  public void guardarBase() {
    try {
      final PrintWriter printer = new PrintWriter("data.xml");
      XStream _xStream = new XStream();
      _xStream.toXML(LigaMaster.instance, printer);
      printer.close();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void addDT(final DT dt) {
    try {
      final Function1<DT, Boolean> _function = new Function1<DT, Boolean>() {
        @Override
        public Boolean apply(final DT it) {
          String _nombreDT = it.getNombreDT();
          String _nombreDT_1 = dt.getNombreDT();
          return Boolean.valueOf(_nombreDT.equals(_nombreDT_1));
        }
      };
      boolean _exists = IterableExtensions.<DT>exists(this.listaDT, _function);
      if (_exists) {
        throw new Exception("Ese nombre de DT ya está en uso");
      }
      final Function1<DT, Boolean> _function_1 = new Function1<DT, Boolean>() {
        @Override
        public Boolean apply(final DT it) {
          String _nombreDT = it.getNombreDT();
          String _nombreDT_1 = dt.getNombreDT();
          return Boolean.valueOf(_nombreDT.equals(_nombreDT_1));
        }
      };
      boolean _exists_1 = IterableExtensions.<DT>exists(this.listaDT, _function_1);
      if (_exists_1) {
        throw new Exception("Ese nombre de Equipo ya está en uso");
      }
      this.listaDT.add(dt);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void addTorneo(final Torneo torneo) {
    this.listaTorneos.add(torneo);
  }
  
  public void removeTorneo(final Torneo torneo) {
    this.listaTorneos.remove(torneo);
  }
  
  public static LigaMaster getInstance() {
    return LigaMaster.instance;
  }
  
  public DT getPropietario(final Jugador jugador) {
    DT _xblockexpression = null;
    {
      DT _dT = new DT();
      final Procedure1<DT> _function = new Procedure1<DT>() {
        @Override
        public void apply(final DT it) {
          it.setNombreDT("Libre");
        }
      };
      final DT libre = ObjectExtensions.<DT>operator_doubleArrow(_dT, _function);
      DT _elvis = null;
      final Function1<DT, Boolean> _function_1 = new Function1<DT, Boolean>() {
        @Override
        public Boolean apply(final DT it) {
          Set<Jugador> _listaJugadores = it.getListaJugadores();
          return Boolean.valueOf(_listaJugadores.contains(jugador));
        }
      };
      DT _findFirst = IterableExtensions.<DT>findFirst(this.listaDT, _function_1);
      if (_findFirst != null) {
        _elvis = _findFirst;
      } else {
        _elvis = libre;
      }
      _xblockexpression = _elvis;
    }
    return _xblockexpression;
  }
  
  public void update() {
    Set<Jugador> _listaJugador = this.getListaJugador();
    final Consumer<Jugador> _function = new Consumer<Jugador>() {
      @Override
      public void accept(final Jugador it) {
        it.update();
      }
    };
    _listaJugador.forEach(_function);
    this.guardarBase();
  }
  
  public List<Partido> getPartidosJugados(final DT dt, final DT otroDT) {
    final Function1<Torneo, List<Partido>> _function = new Function1<Torneo, List<Partido>>() {
      @Override
      public List<Partido> apply(final Torneo it) {
        return it.getListaPartidos();
      }
    };
    List<List<Partido>> _map = ListExtensions.<Torneo, List<Partido>>map(this.listaTorneos, _function);
    Iterable<Partido> _flatten = Iterables.<Partido>concat(_map);
    final Function1<Partido, Boolean> _function_1 = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        boolean _and = false;
        boolean _and_1 = false;
        boolean _jugoPartido = it.getJugoPartido(dt);
        if (!_jugoPartido) {
          _and_1 = false;
        } else {
          boolean _jugoPartido_1 = it.getJugoPartido(otroDT);
          _and_1 = _jugoPartido_1;
        }
        if (!_and_1) {
          _and = false;
        } else {
          boolean _isTerminado = it.isTerminado();
          _and = _isTerminado;
        }
        return Boolean.valueOf(_and);
      }
    };
    Iterable<Partido> _filter = IterableExtensions.<Partido>filter(_flatten, _function_1);
    return IterableExtensions.<Partido>toList(_filter);
  }
  
  public int getPartidosGanados(final DT dt, final DT otroDT) {
    List<Partido> _partidosJugados = this.getPartidosJugados(dt, otroDT);
    final Function1<Partido, Boolean> _function = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        int _puntos = it.getPuntos(dt);
        return Boolean.valueOf((_puntos == 3));
      }
    };
    Iterable<Partido> _filter = IterableExtensions.<Partido>filter(_partidosJugados, _function);
    return IterableExtensions.size(_filter);
  }
  
  public int getPartidosEmpatados(final DT dt, final DT otroDT) {
    List<Partido> _partidosJugados = this.getPartidosJugados(dt, otroDT);
    final Function1<Partido, Boolean> _function = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        int _puntos = it.getPuntos(dt);
        return Boolean.valueOf((_puntos == 1));
      }
    };
    Iterable<Partido> _filter = IterableExtensions.<Partido>filter(_partidosJugados, _function);
    return IterableExtensions.size(_filter);
  }
  
  public int getPartidosPerdidos(final DT dt, final DT otroDT) {
    List<Partido> _partidosJugados = this.getPartidosJugados(dt, otroDT);
    final Function1<Partido, Boolean> _function = new Function1<Partido, Boolean>() {
      @Override
      public Boolean apply(final Partido it) {
        int _puntos = it.getPuntos(dt);
        return Boolean.valueOf((_puntos == 0));
      }
    };
    Iterable<Partido> _filter = IterableExtensions.<Partido>filter(_partidosJugados, _function);
    return IterableExtensions.size(_filter);
  }
  
  @Pure
  public List<Torneo> getListaTorneos() {
    return this.listaTorneos;
  }
  
  public void setListaTorneos(final List<Torneo> listaTorneos) {
    this.listaTorneos = listaTorneos;
  }
  
  @Pure
  public List<DT> getListaDT() {
    return this.listaDT;
  }
  
  public void setListaDT(final List<DT> listaDT) {
    this.listaDT = listaDT;
  }
  
  @Pure
  public Mercado getMercado() {
    return this.mercado;
  }
  
  public void setMercado(final Mercado mercado) {
    this.mercado = mercado;
  }
}
