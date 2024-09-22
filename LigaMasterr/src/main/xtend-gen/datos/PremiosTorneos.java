package datos;

import datos.PremioEvento;
import datos.PremioPosicion;
import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class PremiosTorneos {
  private List<PremioEvento> listaEventos = CollectionLiterals.<PremioEvento>newArrayList(
    new PremioEvento("Victoria", 2000.0), 
    new PremioEvento("Gol", 1000.0), 
    new PremioEvento("Empate", 1000.0));
  
  private List<PremioPosicion> listaPosiciones = CollectionLiterals.<PremioPosicion>newArrayList(
    new PremioPosicion(1, 40000.0), 
    new PremioPosicion(2, 30000.0), 
    new PremioPosicion(3, 20000.0), 
    new PremioPosicion(4, 10000.0));
  
  public double getPremioEvento(final String string) {
    final Function1<PremioEvento, Boolean> _function = new Function1<PremioEvento, Boolean>() {
      @Override
      public Boolean apply(final PremioEvento it) {
        String _evento = it.getEvento();
        return Boolean.valueOf(_evento.equals(string));
      }
    };
    PremioEvento _findFirst = IterableExtensions.<PremioEvento>findFirst(this.listaEventos, _function);
    return _findFirst.getPremio();
  }
  
  public double getPremio(final Integer _posicion) {
    final Function1<PremioPosicion, Boolean> _function = new Function1<PremioPosicion, Boolean>() {
      @Override
      public Boolean apply(final PremioPosicion it) {
        int _posicion_1 = it.getPosicion();
        return Boolean.valueOf(Integer.valueOf(_posicion_1).equals(_posicion));
      }
    };
    PremioPosicion _findFirst = IterableExtensions.<PremioPosicion>findFirst(this.listaPosiciones, _function);
    return _findFirst.getPremio();
  }
  
  public boolean crearPremio(final double premio) {
    int _size = this.listaPosiciones.size();
    int _plus = (_size + 1);
    PremioPosicion _premioPosicion = new PremioPosicion(_plus, premio);
    return this.listaPosiciones.add(_premioPosicion);
  }
  
  public int getCantPremios() {
    return this.listaPosiciones.size();
  }
  
  @Pure
  public List<PremioEvento> getListaEventos() {
    return this.listaEventos;
  }
  
  public void setListaEventos(final List<PremioEvento> listaEventos) {
    this.listaEventos = listaEventos;
  }
  
  @Pure
  public List<PremioPosicion> getListaPosiciones() {
    return this.listaPosiciones;
  }
  
  public void setListaPosiciones(final List<PremioPosicion> listaPosiciones) {
    this.listaPosiciones = listaPosiciones;
  }
}
