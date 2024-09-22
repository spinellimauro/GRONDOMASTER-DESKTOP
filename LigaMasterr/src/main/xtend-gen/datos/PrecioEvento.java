package datos;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class PrecioEvento {
  private String evento;
  
  private double precio;
  
  public PrecioEvento(final String string, final double valor) {
    this.evento = string;
    this.precio = valor;
  }
  
  @Pure
  public String getEvento() {
    return this.evento;
  }
  
  public void setEvento(final String evento) {
    this.evento = evento;
  }
  
  @Pure
  public double getPrecio() {
    return this.precio;
  }
  
  public void setPrecio(final double precio) {
    this.precio = precio;
  }
}
