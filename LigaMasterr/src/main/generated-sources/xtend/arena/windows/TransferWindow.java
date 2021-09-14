package arena.windows;

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
    final Procedure1<Table<Transferencia>> _function = (Table<Transferencia> it) -> {
      it.bindItemsToProperty("transferencias");
      it.setNumberVisibleRows(30);
      Column<Transferencia> _column = new Column<Transferencia>(it);
      final Procedure1<Column<Transferencia>> _function_1 = (Column<Transferencia> it_1) -> {
        it_1.setTitle("VENDEDOR");
        it_1.bindContentsToProperty("dtVende");
        it_1.setFixedSize(120);
      };
      ObjectExtensions.<Column<Transferencia>>operator_doubleArrow(_column, _function_1);
      Column<Transferencia> _column_1 = new Column<Transferencia>(it);
      final Procedure1<Column<Transferencia>> _function_2 = (Column<Transferencia> it_1) -> {
        it_1.setTitle("COMPRADOR");
        it_1.bindContentsToProperty("dtCompra");
        it_1.setFixedSize(120);
      };
      ObjectExtensions.<Column<Transferencia>>operator_doubleArrow(_column_1, _function_2);
      Column<Transferencia> _column_2 = new Column<Transferencia>(it);
      final Procedure1<Column<Transferencia>> _function_3 = (Column<Transferencia> it_1) -> {
        it_1.setTitle("MONTO");
        it_1.bindContentsToProperty("monto");
        it_1.setFixedSize(120);
      };
      ObjectExtensions.<Column<Transferencia>>operator_doubleArrow(_column_2, _function_3);
      Column<Transferencia> _column_3 = new Column<Transferencia>(it);
      final Procedure1<Column<Transferencia>> _function_4 = (Column<Transferencia> it_1) -> {
        it_1.setTitle("JUGADOR");
        it_1.bindContentsToProperty("jugadorComprado");
        it_1.setFixedSize(120);
      };
      ObjectExtensions.<Column<Transferencia>>operator_doubleArrow(_column_3, _function_4);
      Column<Transferencia> _column_4 = new Column<Transferencia>(it);
      final Procedure1<Column<Transferencia>> _function_5 = (Column<Transferencia> it_1) -> {
        it_1.setTitle("NIVEL");
        it_1.bindContentsToProperty("nivel");
        it_1.setFixedSize(120);
      };
      ObjectExtensions.<Column<Transferencia>>operator_doubleArrow(_column_4, _function_5);
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
