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
    final Function1<DT, Double> _function = (DT it) -> {
      return Double.valueOf(it.getRank());
    };
    this.dts = ListExtensions.<DT>reverse(IterableExtensions.<DT, Double>sortBy(LigaMaster.getInstance().getListaDT(), _function));
  }

  public void iniciarRank() {
    final Consumer<DT> _function = (DT it) -> {
      it.setRank(1.0);
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
