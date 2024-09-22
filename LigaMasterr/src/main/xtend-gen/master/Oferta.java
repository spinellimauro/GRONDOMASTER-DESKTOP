package master;

import datos.Mercado;
import java.util.List;
import java.util.function.Consumer;
import master.DT;
import master.Jugador;
import master.LigaMaster;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class Oferta {
  private DT dtOfertante;
  
  private DT dtReceptor;
  
  private double monto;
  
  private Jugador jugadorOfertado;
  
  private List<Jugador> jugadoresOfrecidos = CollectionLiterals.<Jugador>newArrayList();
  
  public void aceptar() {
    this.dtOfertante.comprarJugador(this.jugadorOfertado, this.monto);
    this.dtReceptor.venderJugador(this.jugadorOfertado, this.monto);
    final Consumer<Jugador> _function = new Consumer<Jugador>() {
      @Override
      public void accept(final Jugador it) {
        Oferta.this.dtReceptor.comprarJugador(it, 0);
      }
    };
    this.jugadoresOfrecidos.forEach(_function);
    final Consumer<Jugador> _function_1 = new Consumer<Jugador>() {
      @Override
      public void accept(final Jugador it) {
        Oferta.this.dtOfertante.venderJugador(it, 0);
      }
    };
    this.jugadoresOfrecidos.forEach(_function_1);
    LigaMaster _instance = LigaMaster.getInstance();
    Mercado _mercado = _instance.getMercado();
    List<Oferta> _listaOfertas = _mercado.getListaOfertas();
    LigaMaster _instance_1 = LigaMaster.getInstance();
    Mercado _mercado_1 = _instance_1.getMercado();
    List<Oferta> _ofertasRecibidas = _mercado_1.getOfertasRecibidas(this.jugadorOfertado);
    _listaOfertas.removeAll(_ofertasRecibidas);
  }
  
  public void rechazar() {
    LigaMaster _instance = LigaMaster.getInstance();
    Mercado _mercado = _instance.getMercado();
    List<Oferta> _listaOfertas = _mercado.getListaOfertas();
    _listaOfertas.remove(this);
  }
  
  @Pure
  public DT getDtOfertante() {
    return this.dtOfertante;
  }
  
  public void setDtOfertante(final DT dtOfertante) {
    this.dtOfertante = dtOfertante;
  }
  
  @Pure
  public DT getDtReceptor() {
    return this.dtReceptor;
  }
  
  public void setDtReceptor(final DT dtReceptor) {
    this.dtReceptor = dtReceptor;
  }
  
  @Pure
  public double getMonto() {
    return this.monto;
  }
  
  public void setMonto(final double monto) {
    this.monto = monto;
  }
  
  @Pure
  public Jugador getJugadorOfertado() {
    return this.jugadorOfertado;
  }
  
  public void setJugadorOfertado(final Jugador jugadorOfertado) {
    this.jugadorOfertado = jugadorOfertado;
  }
  
  @Pure
  public List<Jugador> getJugadoresOfrecidos() {
    return this.jugadoresOfrecidos;
  }
  
  public void setJugadoresOfrecidos(final List<Jugador> jugadoresOfrecidos) {
    this.jugadoresOfrecidos = jugadoresOfrecidos;
  }
}
