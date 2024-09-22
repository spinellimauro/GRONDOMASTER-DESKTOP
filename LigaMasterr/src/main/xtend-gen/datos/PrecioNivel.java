package datos;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class PrecioNivel {
  private int nivel;
  
  private double precio;
  
  public PrecioNivel(final int integer, final double valor) {
    this.nivel = integer;
    this.precio = valor;
  }
  
  @Pure
  public int getNivel() {
    return this.nivel;
  }
  
  public void setNivel(final int nivel) {
    this.nivel = nivel;
  }
  
  @Pure
  public double getPrecio() {
    return this.precio;
  }
  
  public void setPrecio(final double precio) {
    this.precio = precio;
  }
}
