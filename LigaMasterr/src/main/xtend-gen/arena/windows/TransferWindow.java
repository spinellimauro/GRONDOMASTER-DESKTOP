package arena.windows;

import arena.windows.TransferModel;
import master.DT;
import master.Transferencia;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("all")
public class TransferWindow extends Dialog<TransferModel> {
  public TransferWindow(final WindowOwner parent, final DT model) {
    super(parent, new TransferModel(model));
    this.setTitle("Transferencias");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    Table<Transferencia> _table = new Table<Transferencia>(panel, Transferencia.class);
    final Procedure1<Table<Transferencia>> _function = new Procedure1<Table<Transferencia>>() {
      @Override
      public void apply(final Table<Transferencia> it) {
        it.bindItemsToProperty("transferencias");
        it.setNumberVisibleRows(30);
        Column<Transferencia> _column = new Column<Transferencia>(it);
        final Procedure1<Column<Transferencia>> _function = new Procedure1<Column<Transferencia>>() {
          @Override
          public void apply(final Column<Transferencia> it) {
            it.setTitle("VENDEDOR");
            it.bindContentsToProperty("dtVende");
            it.setFixedSize(120);
          }
        };
        ObjectExtensions.<Column<Transferencia>>operator_doubleArrow(_column, _function);
        Column<Transferencia> _column_1 = new Column<Transferencia>(it);
        final Procedure1<Column<Transferencia>> _function_1 = new Procedure1<Column<Transferencia>>() {
          @Override
          public void apply(final Column<Transferencia> it) {
            it.setTitle("COMPRADOR");
            it.bindContentsToProperty("dtCompra");
            it.setFixedSize(120);
          }
        };
        ObjectExtensions.<Column<Transferencia>>operator_doubleArrow(_column_1, _function_1);
        Column<Transferencia> _column_2 = new Column<Transferencia>(it);
        final Procedure1<Column<Transferencia>> _function_2 = new Procedure1<Column<Transferencia>>() {
          @Override
          public void apply(final Column<Transferencia> it) {
            it.setTitle("MONTO");
            it.bindContentsToProperty("monto");
            it.setFixedSize(120);
          }
        };
        ObjectExtensions.<Column<Transferencia>>operator_doubleArrow(_column_2, _function_2);
        Column<Transferencia> _column_3 = new Column<Transferencia>(it);
        final Procedure1<Column<Transferencia>> _function_3 = new Procedure1<Column<Transferencia>>() {
          @Override
          public void apply(final Column<Transferencia> it) {
            it.setTitle("JUGADOR");
            it.bindContentsToProperty("jugadorComprado");
            it.setFixedSize(120);
          }
        };
        ObjectExtensions.<Column<Transferencia>>operator_doubleArrow(_column_3, _function_3);
        Column<Transferencia> _column_4 = new Column<Transferencia>(it);
        final Procedure1<Column<Transferencia>> _function_4 = new Procedure1<Column<Transferencia>>() {
          @Override
          public void apply(final Column<Transferencia> it) {
            it.setTitle("NIVEL");
            it.bindContentsToProperty("nivel");
            it.setFixedSize(120);
          }
        };
        ObjectExtensions.<Column<Transferencia>>operator_doubleArrow(_column_4, _function_4);
      }
    };
    ObjectExtensions.<Table<Transferencia>>operator_doubleArrow(_table, _function);
  }
  
  @Override
  protected void addActions(final Panel actionsPanel) {
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
