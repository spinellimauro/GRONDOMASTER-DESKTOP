package arena.windows;

import arena.windows.Historial;
import com.google.common.base.Objects;
import java.util.List;
import master.DT;
import master.LigaMaster;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class HistorialModel {
  private List<Historial> listaHistoriales = CollectionLiterals.<Historial>newArrayList();
  
  public HistorialModel(final DT dt) {
    LigaMaster _instance = LigaMaster.getInstance();
    List<DT> _listaDT = _instance.getListaDT();
    for (final DT otroDT : _listaDT) {
      boolean _notEquals = (!Objects.equal(otroDT, dt));
      if (_notEquals) {
        Historial _historial = new Historial(dt, otroDT);
        this.listaHistoriales.add(_historial);
      }
    }
    final Function1<Historial, Integer> _function = new Function1<Historial, Integer>() {
      @Override
      public Integer apply(final Historial it) {
        return Integer.valueOf(it.getDiferencia());
      }
    };
    List<Historial> _sortBy = IterableExtensions.<Historial, Integer>sortBy(this.listaHistoriales, _function);
    List<Historial> _reverse = ListExtensions.<Historial>reverse(_sortBy);
    this.listaHistoriales = _reverse;
  }
  
  @Pure
  public List<Historial> getListaHistoriales() {
    return this.listaHistoriales;
  }
  
  public void setListaHistoriales(final List<Historial> listaHistoriales) {
    this.listaHistoriales = listaHistoriales;
  }
}
