package master;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class Transferencia {
  private String dtCompra;

  private String dtVende;

  private double monto;

  private String jugadorComprado;

  private int nivel;

  @Pure
  public String getDtCompra() {
    return this.dtCompra;
  }

  public void setDtCompra(final String dtCompra) {
    this.dtCompra = dtCompra;
  }

  @Pure
  public String getDtVende() {
    return this.dtVende;
  }

  public void setDtVende(final String dtVende) {
    this.dtVende = dtVende;
  }

  @Pure
  public double getMonto() {
    return this.monto;
  }

  public void setMonto(final double monto) {
    this.monto = monto;
  }

  @Pure
  public String getJugadorComprado() {
    return this.jugadorComprado;
  }

  public void setJugadorComprado(final String jugadorComprado) {
    this.jugadorComprado = jugadorComprado;
  }

  @Pure
  public int getNivel() {
    return this.nivel;
  }

  public void setNivel(final int nivel) {
    this.nivel = nivel;
  }
}
