package arena.models;

import arena.models.TorneoModel;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import master.DT;
import master.Jugador;
import master.LigaMaster;
import master.Partido;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class PartidoModel {
  private DT dtON;
  
  private Partido partidoON;
  
  private Jugador jugadorON;
  
  public PartidoModel(final TorneoModel model) {
    Partido _partidoON = model.getPartidoON();
    this.partidoON = _partidoON;
    DT _dtON = model.getDtON();
    this.dtON = _dtON;
  }
  
  public List<Jugador> getEquipoLocal() {
    ArrayList<Jugador> _newArrayList = CollectionLiterals.<Jugador>newArrayList();
    final Procedure1<ArrayList<Jugador>> _function = new Procedure1<ArrayList<Jugador>>() {
      @Override
      public void apply(final ArrayList<Jugador> it) {
        DT _dtLocal = PartidoModel.this.partidoON.getDtLocal();
        Set<Jugador> _listaJugadores = _dtLocal.getListaJugadores();
        final Function1<Jugador, String> _function = new Function1<Jugador, String>() {
          @Override
          public String apply(final Jugador it) {
            return it.getNombre();
          }
        };
        List<Jugador> _sortBy = IterableExtensions.<Jugador, String>sortBy(_listaJugadores, _function);
        it.addAll(_sortBy);
        List<Jugador> _suspendidos = PartidoModel.this.getSuspendidos();
        it.removeAll(_suspendidos);
        List<Jugador> _lesionados = PartidoModel.this.getLesionados();
        it.removeAll(_lesionados);
      }
    };
    return ObjectExtensions.<ArrayList<Jugador>>operator_doubleArrow(_newArrayList, _function);
  }
  
  public List<Jugador> getEquipoVisitante() {
    ArrayList<Jugador> _newArrayList = CollectionLiterals.<Jugador>newArrayList();
    final Procedure1<ArrayList<Jugador>> _function = new Procedure1<ArrayList<Jugador>>() {
      @Override
      public void apply(final ArrayList<Jugador> it) {
        DT _dtVisitante = PartidoModel.this.partidoON.getDtVisitante();
        Set<Jugador> _listaJugadores = _dtVisitante.getListaJugadores();
        final Function1<Jugador, String> _function = new Function1<Jugador, String>() {
          @Override
          public String apply(final Jugador it) {
            return it.getNombre();
          }
        };
        List<Jugador> _sortBy = IterableExtensions.<Jugador, String>sortBy(_listaJugadores, _function);
        it.addAll(_sortBy);
        List<Jugador> _suspendidos = PartidoModel.this.getSuspendidos();
        it.removeAll(_suspendidos);
        List<Jugador> _lesionados = PartidoModel.this.getLesionados();
        it.removeAll(_lesionados);
      }
    };
    return ObjectExtensions.<ArrayList<Jugador>>operator_doubleArrow(_newArrayList, _function);
  }
  
  public List<Jugador> getSuspendidos() {
    return this.partidoON.getSuspendidos();
  }
  
  public List<Jugador> getLesionados() {
    return this.partidoON.getLesionados();
  }
  
  public List<Jugador> getListaJugadoresDeshabilitados() {
    ArrayList<Jugador> _newArrayList = CollectionLiterals.<Jugador>newArrayList();
    final Procedure1<ArrayList<Jugador>> _function = new Procedure1<ArrayList<Jugador>>() {
      @Override
      public void apply(final ArrayList<Jugador> it) {
        DT _dtVisitante = PartidoModel.this.partidoON.getDtVisitante();
        Set<Jugador> _listaJugadores = _dtVisitante.getListaJugadores();
        final Function1<Jugador, Boolean> _function = new Function1<Jugador, Boolean>() {
          @Override
          public Boolean apply(final Jugador it) {
            boolean _isHabilitado = it.isHabilitado();
            return Boolean.valueOf((_isHabilitado == false));
          }
        };
        Iterable<Jugador> _filter = IterableExtensions.<Jugador>filter(_listaJugadores, _function);
        Iterables.<Jugador>addAll(it, _filter);
        DT _dtLocal = PartidoModel.this.partidoON.getDtLocal();
        Set<Jugador> _listaJugadores_1 = _dtLocal.getListaJugadores();
        final Function1<Jugador, Boolean> _function_1 = new Function1<Jugador, Boolean>() {
          @Override
          public Boolean apply(final Jugador it) {
            boolean _isHabilitado = it.isHabilitado();
            return Boolean.valueOf((_isHabilitado == false));
          }
        };
        Iterable<Jugador> _filter_1 = IterableExtensions.<Jugador>filter(_listaJugadores_1, _function_1);
        Iterables.<Jugador>addAll(it, _filter_1);
      }
    };
    return ObjectExtensions.<ArrayList<Jugador>>operator_doubleArrow(_newArrayList, _function);
  }
  
  public void addGol() {
    this.partidoON.addGol(this.jugadorON);
    this.guardar();
  }
  
  public void removeGol() {
    this.partidoON.removeGol(this.jugadorON);
    this.guardar();
  }
  
  public void addAmarilla() {
    this.partidoON.addAmarilla(this.jugadorON);
    this.guardar();
  }
  
  public void removeAmarilla() {
    this.partidoON.removeAmarilla(this.jugadorON);
    this.guardar();
  }
  
  public void addRoja() {
    this.partidoON.addRoja(this.jugadorON);
    this.guardar();
  }
  
  public void removeRoja() {
    this.partidoON.removeRoja(this.jugadorON);
    this.guardar();
  }
  
  public void incLesion() {
    this.jugadorON.incLesion();
    ObservableUtils.firePropertyChanged(this, "lesionados");
    this.guardar();
  }
  
  public void decLesion() {
    this.jugadorON.decLesion();
    ObservableUtils.firePropertyChanged(this, "lesionados");
    this.guardar();
  }
  
  public boolean getLesionesON() {
    boolean _and = false;
    LigaMaster _instance = LigaMaster.getInstance();
    DT _master = _instance.getMaster();
    boolean _equals = this.dtON.equals(_master);
    if (!_equals) {
      _and = false;
    } else {
      boolean _isTerminado = this.partidoON.isTerminado();
      _and = _isTerminado;
    }
    return _and;
  }
  
  public boolean getEsMaster() {
    boolean _and = false;
    LigaMaster _instance = LigaMaster.getInstance();
    DT _master = _instance.getMaster();
    boolean _equals = this.dtON.equals(_master);
    if (!_equals) {
      _and = false;
    } else {
      boolean _isTerminado = this.partidoON.isTerminado();
      boolean _not = (!_isTerminado);
      _and = _not;
    }
    return _and;
  }
  
  public void guardar() {
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.guardarBase();
  }
  
  @Pure
  public DT getDtON() {
    return this.dtON;
  }
  
  public void setDtON(final DT dtON) {
    this.dtON = dtON;
  }
  
  @Pure
  public Partido getPartidoON() {
    return this.partidoON;
  }
  
  public void setPartidoON(final Partido partidoON) {
    this.partidoON = partidoON;
  }
  
  @Pure
  public Jugador getJugadorON() {
    return this.jugadorON;
  }
  
  public void setJugadorON(final Jugador jugadorON) {
    this.jugadorON = jugadorON;
  }
}
