package datos;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class PremioPosicion {
  private int posicion;
  
  private double premio;
  
  public PremioPosicion(final int integer, final double valor) {
    this.posicion = integer;
    this.premio = valor;
  }
  
  @Pure
  public int getPosicion() {
    return this.posicion;
  }
  
  public void setPosicion(final int posicion) {
    this.posicion = posicion;
  }
  
  @Pure
  public double getPremio() {
    return this.premio;
  }
  
  public void setPremio(final double premio) {
    this.premio = premio;
  }
}
