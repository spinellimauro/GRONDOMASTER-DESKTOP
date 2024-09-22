package datos;

import java.util.List;
import master.DT;
import master.Jugador;
import master.Oferta;
import master.Transferencia;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class Mercado {
  private List<Oferta> listaOfertas = CollectionLiterals.<Oferta>newArrayList();
  
  private List<Transferencia> listaTraspasos = CollectionLiterals.<Transferencia>newArrayList();
  
  public List<Oferta> getOfertasRecibidas(final Jugador jugador) {
    final Function1<Oferta, Boolean> _function = new Function1<Oferta, Boolean>() {
      @Override
      public Boolean apply(final Oferta it) {
        Jugador _jugadorOfertado = it.getJugadorOfertado();
        return Boolean.valueOf(_jugadorOfertado.equals(jugador));
      }
    };
    Iterable<Oferta> _filter = IterableExtensions.<Oferta>filter(this.listaOfertas, _function);
    return IterableExtensions.<Oferta>toList(_filter);
  }
  
  public List<Oferta> getOfertasRecibidas(final DT dt) {
    final Function1<Oferta, Boolean> _function = new Function1<Oferta, Boolean>() {
      @Override
      public Boolean apply(final Oferta it) {
        DT _dtReceptor = it.getDtReceptor();
        return Boolean.valueOf(_dtReceptor.equals(dt));
      }
    };
    Iterable<Oferta> _filter = IterableExtensions.<Oferta>filter(this.listaOfertas, _function);
    return IterableExtensions.<Oferta>toList(_filter);
  }
  
  public List<Oferta> getOfertasEnviadas(final DT dt) {
    final Function1<Oferta, Boolean> _function = new Function1<Oferta, Boolean>() {
      @Override
      public Boolean apply(final Oferta it) {
        DT _dtOfertante = it.getDtOfertante();
        return Boolean.valueOf(_dtOfertante.equals(dt));
      }
    };
    Iterable<Oferta> _filter = IterableExtensions.<Oferta>filter(this.listaOfertas, _function);
    return IterableExtensions.<Oferta>toList(_filter);
  }
  
  public void agregarTransferencia(final Transferencia transferencia) {
    this.listaTraspasos.add(transferencia);
  }
  
  @Pure
  public List<Oferta> getListaOfertas() {
    return this.listaOfertas;
  }
  
  public void setListaOfertas(final List<Oferta> listaOfertas) {
    this.listaOfertas = listaOfertas;
  }
  
  @Pure
  public List<Transferencia> getListaTraspasos() {
    return this.listaTraspasos;
  }
  
  public void setListaTraspasos(final List<Transferencia> listaTraspasos) {
    this.listaTraspasos = listaTraspasos;
  }
}
