package arena.models;

import arena.models.LoginModel;
import java.util.ArrayList;
import java.util.List;
import master.DT;
import master.LigaMaster;
import master.Partido;
import master.Torneo;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class TorneoModel {
  private DT dtON;
  
  private int fechaON;
  
  private Torneo torneoON;
  
  private Partido partidoON;
  
  private List<DT> dts = CollectionLiterals.<DT>newArrayList();
  
  public TorneoModel(final LoginModel loginModel) {
    DT _dtON = loginModel.getDtON();
    this.dtON = _dtON;
    Torneo _elvis = null;
    List<Torneo> _listaTorneos = this.getListaTorneos();
    Torneo _get = _listaTorneos.get(0);
    if (_get != null) {
      _elvis = _get;
    } else {
      Torneo _torneo = new Torneo();
      _elvis = _torneo;
    }
    this.setTorneoON(_elvis);
    LigaMaster _instance = LigaMaster.getInstance();
    List<DT> _listaDT = _instance.getListaDT();
    this.dts = _listaDT;
  }
  
  public List<Torneo> getListaTorneos() {
    LigaMaster _instance = LigaMaster.getInstance();
    return _instance.getListaTorneos();
  }
  
  public void setTorneoON(final Torneo torneo) {
    this.torneoON = torneo;
    this.fechaON = 1;
    ObservableUtils.firePropertyChanged(this, "fecha");
    ObservableUtils.firePropertyChanged(this, "listaFechas");
  }
  
  @Dependencies("fechaON")
  public List<Partido> getFecha() {
    List<Partido> _elvis = null;
    List<Partido> _fecha = this.torneoON.getFecha(this.fechaON);
    if (_fecha != null) {
      _elvis = _fecha;
    } else {
      ArrayList<Partido> _newArrayList = CollectionLiterals.<Partido>newArrayList();
      _elvis = _newArrayList;
    }
    return _elvis;
  }
  
  public List<Integer> getListaFechas() {
    int _numeroFechas = this.torneoON.getNumeroFechas();
    IntegerRange _upTo = new IntegerRange(1, _numeroFechas);
    return IterableExtensions.<Integer>toList(_upTo);
  }
  
  public void sortearFechas() {
    this.torneoON.sortearFechas();
    ObservableUtils.firePropertyChanged(this, "fecha");
    ObservableUtils.firePropertyChanged(this, "listaFechas");
    this.guardar();
  }
  
  public void addTorneo() {
    LigaMaster _instance = LigaMaster.getInstance();
    Torneo _torneo = new Torneo();
    final Procedure1<Torneo> _function = new Procedure1<Torneo>() {
      @Override
      public void apply(final Torneo it) {
        it.setNombreTorneo("Torneo");
      }
    };
    Torneo _doubleArrow = ObjectExtensions.<Torneo>operator_doubleArrow(_torneo, _function);
    _instance.addTorneo(_doubleArrow);
    List<Torneo> _listaTorneos = this.getListaTorneos();
    Torneo _last = IterableExtensions.<Torneo>last(_listaTorneos);
    this.setTorneoON(_last);
    ObservableUtils.firePropertyChanged(this, "listaTorneos");
    this.guardar();
  }
  
  public void removeTorneo() {
    List<Torneo> _listaTorneos = this.getListaTorneos();
    int _size = _listaTorneos.size();
    boolean _lessThan = (_size < 2);
    if (_lessThan) {
      throw new UserException("Debe haber al menos un torneo creado");
    }
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.removeTorneo(this.torneoON);
    Torneo _elvis = null;
    List<Torneo> _listaTorneos_1 = this.getListaTorneos();
    Torneo _get = _listaTorneos_1.get(0);
    if (_get != null) {
      _elvis = _get;
    } else {
      Torneo _torneo = new Torneo();
      _elvis = _torneo;
    }
    this.setTorneoON(_elvis);
    ObservableUtils.firePropertyChanged(this, "listaTorneos");
    this.guardar();
  }
  
  public void update() {
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.update();
    this.guardar();
  }
  
  public void terminarPartido() {
    try {
      this.partidoON.terminarPartido();
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        String _message = e.getMessage();
        throw new UserException(_message);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    this.guardar();
  }
  
  public void terminarTorneo() {
    try {
      this.torneoON.terminarTorneo();
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        String _message = e.getMessage();
        throw new UserException(_message);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    this.guardar();
  }
  
  public boolean esMaster() {
    LigaMaster _instance = LigaMaster.getInstance();
    DT _master = _instance.getMaster();
    return this.dtON.equals(_master);
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
  public int getFechaON() {
    return this.fechaON;
  }
  
  public void setFechaON(final int fechaON) {
    this.fechaON = fechaON;
  }
  
  @Pure
  public Torneo getTorneoON() {
    return this.torneoON;
  }
  
  @Pure
  public Partido getPartidoON() {
    return this.partidoON;
  }
  
  public void setPartidoON(final Partido partidoON) {
    this.partidoON = partidoON;
  }
  
  @Pure
  public List<DT> getDts() {
    return this.dts;
  }
  
  public void setDts(final List<DT> dts) {
    this.dts = dts;
  }
}
