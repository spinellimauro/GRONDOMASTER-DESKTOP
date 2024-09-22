package arena.models;

import arena.models.TorneoConfigModel;
import java.util.List;
import master.DT;
import master.Jugador;
import master.LigaMaster;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class ImpuestoModel {
  private DT dtON;
  
  private Jugador jugadorON;
  
  private List<Jugador> jugadoresAPagar = CollectionLiterals.<Jugador>newArrayList();
  
  private List<Jugador> jugadoresSinPagar = CollectionLiterals.<Jugador>newArrayList();
  
  private boolean habilitado = true;
  
  public ImpuestoModel(final TorneoConfigModel model) {
    DT _dtON = model.getDtON();
    this.dtON = _dtON;
    List<Jugador> _jugadoresConImpuesto = this.dtON.getJugadoresConImpuesto();
    this.jugadoresSinPagar.addAll(_jugadoresConImpuesto);
  }
  
  public void addAPagar() {
    boolean _contains = this.jugadoresAPagar.contains(this.jugadorON);
    if (_contains) {
      throw new UserException("Ya esta en A Pagar");
    }
    this.jugadoresAPagar.add(this.jugadorON);
    this.jugadoresSinPagar.remove(this.jugadorON);
    this.actualizar();
  }
  
  public void addSinPagar() {
    boolean _contains = this.jugadoresSinPagar.contains(this.jugadorON);
    if (_contains) {
      throw new UserException("Ya esta en Sin Pagar");
    }
    this.jugadoresSinPagar.add(this.jugadorON);
    this.jugadoresAPagar.remove(this.jugadorON);
    this.actualizar();
  }
  
  public void pagarImpuestos() {
    this.dtON.pagarImpuesto(this.jugadoresAPagar);
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.guardarBase();
  }
  
  public void actualizar() {
    ObservableUtils.firePropertyChanged(this, "impuestos");
    ObservableUtils.firePropertyChanged(this, "jugadoresAPagar");
    ObservableUtils.firePropertyChanged(this, "jugadoresSinPagar");
  }
  
  public double getImpuestos() {
    final Function2<Double, Jugador, Double> _function = new Function2<Double, Jugador, Double>() {
      @Override
      public Double apply(final Double acum, final Jugador jugador) {
        double _impuesto = jugador.getImpuesto();
        return Double.valueOf(((acum).doubleValue() + _impuesto));
      }
    };
    return (double) IterableExtensions.<Jugador, Double>fold(this.jugadoresAPagar, Double.valueOf(0d), _function);
  }
  
  public void deshabilitar() {
    this.habilitado = false;
  }
  
  @Pure
  public DT getDtON() {
    return this.dtON;
  }
  
  public void setDtON(final DT dtON) {
    this.dtON = dtON;
  }
  
  @Pure
  public Jugador getJugadorON() {
    return this.jugadorON;
  }
  
  public void setJugadorON(final Jugador jugadorON) {
    this.jugadorON = jugadorON;
  }
  
  @Pure
  public List<Jugador> getJugadoresAPagar() {
    return this.jugadoresAPagar;
  }
  
  public void setJugadoresAPagar(final List<Jugador> jugadoresAPagar) {
    this.jugadoresAPagar = jugadoresAPagar;
  }
  
  @Pure
  public List<Jugador> getJugadoresSinPagar() {
    return this.jugadoresSinPagar;
  }
  
  public void setJugadoresSinPagar(final List<Jugador> jugadoresSinPagar) {
    this.jugadoresSinPagar = jugadoresSinPagar;
  }
  
  @Pure
  public boolean isHabilitado() {
    return this.habilitado;
  }
  
  public void setHabilitado(final boolean habilitado) {
    this.habilitado = habilitado;
  }
}
