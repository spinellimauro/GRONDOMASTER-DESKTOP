package arena.windows;

import java.util.List;
import java.util.function.Consumer;
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
public class RankModel {
  private DT dtON;
  
  private List<DT> dts = CollectionLiterals.<DT>newArrayList();
  
  public RankModel(final DT model) {
    this.dtON = model;
    LigaMaster _instance = LigaMaster.getInstance();
    List<DT> _listaDT = _instance.getListaDT();
    final Function1<DT, Double> _function = new Function1<DT, Double>() {
      @Override
      public Double apply(final DT it) {
        return Double.valueOf(it.getRank());
      }
    };
    List<DT> _sortBy = IterableExtensions.<DT, Double>sortBy(_listaDT, _function);
    List<DT> _reverse = ListExtensions.<DT>reverse(_sortBy);
    this.dts = _reverse;
  }
  
  public void iniciarRank() {
    final Consumer<DT> _function = new Consumer<DT>() {
      @Override
      public void accept(final DT it) {
        it.setRank(1.0);
      }
    };
    this.dts.forEach(_function);
  }
  
  @Pure
  public DT getDtON() {
    return this.dtON;
  }
  
  public void setDtON(final DT dtON) {
    this.dtON = dtON;
  }
  
  @Pure
  public List<DT> getDts() {
    return this.dts;
  }
  
  public void setDts(final List<DT> dts) {
    this.dts = dts;
  }
}
