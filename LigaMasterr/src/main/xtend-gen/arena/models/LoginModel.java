package arena.models;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import master.DT;
import master.LigaMaster;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class LoginModel {
  private DT dtON;
  
  private String dtNuevo = "";
  
  private String dtEquipo = "";
  
  private String dtPassword = "";
  
  public LoginModel() {
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.leerBase();
    List<DT> _listaDT = this.getListaDT();
    DT _get = _listaDT.get(0);
    this.dtON = _get;
  }
  
  public List<DT> getListaDT() {
    ArrayList<DT> _newArrayList = CollectionLiterals.<DT>newArrayList();
    final Procedure1<ArrayList<DT>> _function = new Procedure1<ArrayList<DT>>() {
      @Override
      public void apply(final ArrayList<DT> it) {
        LigaMaster _instance = LigaMaster.getInstance();
        DT _master = _instance.getMaster();
        it.add(_master);
        LigaMaster _instance_1 = LigaMaster.getInstance();
        List<DT> _listaDT = _instance_1.getListaDT();
        final Function1<DT, String> _function = new Function1<DT, String>() {
          @Override
          public String apply(final DT it) {
            return it.getNombreDT();
          }
        };
        List<DT> _sortBy = IterableExtensions.<DT, String>sortBy(_listaDT, _function);
        it.addAll(_sortBy);
      }
    };
    return ObjectExtensions.<ArrayList<DT>>operator_doubleArrow(_newArrayList, _function);
  }
  
  public void addDT() {
    DT _dT = new DT();
    final Procedure1<DT> _function = new Procedure1<DT>() {
      @Override
      public void apply(final DT it) {
        it.setNombreDT(LoginModel.this.dtNuevo);
        it.setNombreEquipo(LoginModel.this.dtEquipo);
        it.setPassword("");
      }
    };
    DT dt = ObjectExtensions.<DT>operator_doubleArrow(_dT, _function);
    try {
      LigaMaster _instance = LigaMaster.getInstance();
      _instance.addDT(dt);
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        String _message = e.getMessage();
        throw new UserException(_message);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    ObservableUtils.firePropertyChanged(this, "listaDT");
    LigaMaster _instance_1 = LigaMaster.getInstance();
    _instance_1.guardarBase();
  }
  
  public boolean validar() {
    boolean _xifexpression = false;
    String _password = this.dtON.getPassword();
    boolean _equals = Objects.equal(_password, this.dtPassword);
    boolean _not = (!_equals);
    if (_not) {
      throw new UserException("Contraseña Incorrecta");
    } else {
      _xifexpression = true;
    }
    return _xifexpression;
  }
  
  @Pure
  public DT getDtON() {
    return this.dtON;
  }
  
  public void setDtON(final DT dtON) {
    this.dtON = dtON;
  }
  
  @Pure
  public String getDtNuevo() {
    return this.dtNuevo;
  }
  
  public void setDtNuevo(final String dtNuevo) {
    this.dtNuevo = dtNuevo;
  }
  
  @Pure
  public String getDtEquipo() {
    return this.dtEquipo;
  }
  
  public void setDtEquipo(final String dtEquipo) {
    this.dtEquipo = dtEquipo;
  }
  
  @Pure
  public String getDtPassword() {
    return this.dtPassword;
  }
  
  public void setDtPassword(final String dtPassword) {
    this.dtPassword = dtPassword;
  }
}
