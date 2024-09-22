package arena.windows;

import java.util.List;
import master.DT;
import master.LigaMaster;
import master.Transferencia;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class TransferModel {
  private DT dtON;

  private List<Transferencia> transferencias = CollectionLiterals.<Transferencia>newArrayList();

  public TransferModel(final DT model) {
    this.dtON = model;
    this.transferencias = ListExtensions.<Transferencia>reverse(LigaMaster.getInstance().getMercado().getListaTraspasos());
  }

  @Pure
  public DT getDtON() {
    return this.dtON;
  }

  public void setDtON(final DT dtON) {
    this.dtON = dtON;
  }

  @Pure
  public List<Transferencia> getTransferencias() {
    return this.transferencias;
  }

  public void setTransferencias(final List<Transferencia> transferencias) {
    this.transferencias = transferencias;
  }
}
