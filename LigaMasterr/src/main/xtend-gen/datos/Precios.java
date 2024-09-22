package datos;

import datos.PrecioEvento;
import datos.PrecioNivel;
import java.util.List;
import master.Jugador;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public final class Precios {
  private static Precios instance = new Precios();
  
  private List<PrecioNivel> listaNiveles = CollectionLiterals.<PrecioNivel>newArrayList(
    new PrecioNivel(76, 10000.0), 
    new PrecioNivel(77, 10000.0), 
    new PrecioNivel(78, 20000.0), 
    new PrecioNivel(79, 30000.0), 
    new PrecioNivel(80, 40000.0), 
    new PrecioNivel(81, 55000.0), 
    new PrecioNivel(82, 70000.0), 
    new PrecioNivel(83, 85000.0), 
    new PrecioNivel(84, 95000.0), 
    new PrecioNivel(85, 115000.0), 
    new PrecioNivel(86, 130000.0), 
    new PrecioNivel(87, 150000.0), 
    new PrecioNivel(88, 180000.0), 
    new PrecioNivel(89, 220000.0), 
    new PrecioNivel(90, 250000.0), 
    new PrecioNivel(91, 300000.0), 
    new PrecioNivel(92, 400000.0), 
    new PrecioNivel(93, 500000.0), 
    new PrecioNivel(94, 550000.0));
  
  private List<PrecioEvento> listaEventos = CollectionLiterals.<PrecioEvento>newArrayList(
    new PrecioEvento("Slot", 10000.0), 
    new PrecioEvento("Victoria", 2000.0), 
    new PrecioEvento("Gol", 1000.0), 
    new PrecioEvento("Empate", 1000.0), 
    new PrecioEvento("Impuesto", 10.0), 
    new PrecioEvento("PrecioMaquina", 4));
  
  public double getPrecio(final Jugador jugador) {
    double _xblockexpression = (double) 0;
    {
      final int nivelJugador = jugador.getNivel();
      double _xifexpression = (double) 0;
      if ((nivelJugador > 76)) {
        _xifexpression = this.getPrecio(nivelJugador);
      } else {
        _xifexpression = this.getPrecio(76);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public double getPrecio(final int integer) {
    final Function1<PrecioNivel, Boolean> _function = new Function1<PrecioNivel, Boolean>() {
      @Override
      public Boolean apply(final PrecioNivel it) {
        int _nivel = it.getNivel();
        return Boolean.valueOf((_nivel == integer));
      }
    };
    PrecioNivel _findFirst = IterableExtensions.<PrecioNivel>findFirst(this.listaNiveles, _function);
    return _findFirst.getPrecio();
  }
  
  public double getPrecio(final String string) {
    final Function1<PrecioEvento, Boolean> _function = new Function1<PrecioEvento, Boolean>() {
      @Override
      public Boolean apply(final PrecioEvento it) {
        String _evento = it.getEvento();
        return Boolean.valueOf(_evento.equals(string));
      }
    };
    PrecioEvento _findFirst = IterableExtensions.<PrecioEvento>findFirst(this.listaEventos, _function);
    return _findFirst.getPrecio();
  }
  
  public static Precios getInstance() {
    return Precios.instance;
  }
  
  @Pure
  public List<PrecioNivel> getListaNiveles() {
    return this.listaNiveles;
  }
  
  public void setListaNiveles(final List<PrecioNivel> listaNiveles) {
    this.listaNiveles = listaNiveles;
  }
  
  @Pure
  public List<PrecioEvento> getListaEventos() {
    return this.listaEventos;
  }
  
  public void setListaEventos(final List<PrecioEvento> listaEventos) {
    this.listaEventos = listaEventos;
  }
}
