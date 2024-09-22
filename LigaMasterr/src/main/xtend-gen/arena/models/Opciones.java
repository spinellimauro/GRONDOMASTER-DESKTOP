package arena.models;

import com.google.common.base.Objects;
import master.DT;
import master.LigaMaster;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class Opciones {
  private DT dtON;
  
  private String passwordON = "";
  
  private String nuevaPassword = "";
  
  public Opciones(final DT model) {
    this.dtON = model;
  }
  
  public void cambiarContraseña() {
    String _password = this.dtON.getPassword();
    boolean _equals = Objects.equal(this.passwordON, _password);
    if (_equals) {
      this.dtON.setPassword(this.nuevaPassword);
      LigaMaster _instance = LigaMaster.getInstance();
      _instance.guardarBase();
    } else {
      throw new UserException("La contraseña es incorrecta");
    }
  }
  
  @Pure
  public DT getDtON() {
    return this.dtON;
  }
  
  public void setDtON(final DT dtON) {
    this.dtON = dtON;
  }
  
  @Pure
  public String getPasswordON() {
    return this.passwordON;
  }
  
  public void setPasswordON(final String passwordON) {
    this.passwordON = passwordON;
  }
  
  @Pure
  public String getNuevaPassword() {
    return this.nuevaPassword;
  }
  
  public void setNuevaPassword(final String nuevaPassword) {
    this.nuevaPassword = nuevaPassword;
  }
}
