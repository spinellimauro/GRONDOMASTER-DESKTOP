package datos;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class PremioEvento {
  private String evento;
  
  private double premio;
  
  public PremioEvento(final String string, final double valor) {
    this.evento = string;
    this.premio = valor;
  }
  
  @Pure
  public String getEvento() {
    return this.evento;
  }
  
  public void setEvento(final String evento) {
    this.evento = evento;
  }
  
  @Pure
  public double getPremio() {
    return this.premio;
  }
  
  public void setPremio(final double premio) {
    this.premio = premio;
  }
}
