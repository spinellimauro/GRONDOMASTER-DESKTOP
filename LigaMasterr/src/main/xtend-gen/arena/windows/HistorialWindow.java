package arena.windows;

import arena.windows.Historial;
import arena.windows.HistorialModel;
import master.DT;
import org.apache.commons.collections15.Transformer;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.widgets.tables.labelprovider.PropertyLabelProvider;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("all")
public class HistorialWindow extends Dialog<HistorialModel> {
  public HistorialWindow(final WindowOwner parent, final DT model) {
    super(parent, new HistorialModel(model));
    this.setTitle("Historial");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    Table<Historial> _table = new Table<Historial>(panel, Historial.class);
    final Procedure1<Table<Historial>> _function = new Procedure1<Table<Historial>>() {
      @Override
      public void apply(final Table<Historial> it) {
        it.bindItemsToProperty("listaHistoriales");
        it.setNumberVisibleRows(10);
        Column<Historial> _column = new Column<Historial>(it);
        final Procedure1<Column<Historial>> _function = new Procedure1<Column<Historial>>() {
          @Override
          public void apply(final Column<Historial> it) {
            it.setTitle("vs.");
            it.bindContentsToProperty("dt");
            it.setFixedSize(120);
          }
        };
        ObjectExtensions.<Column<Historial>>operator_doubleArrow(_column, _function);
        Column<Historial> _column_1 = new Column<Historial>(it);
        final Procedure1<Column<Historial>> _function_1 = new Procedure1<Column<Historial>>() {
          @Override
          public void apply(final Column<Historial> it) {
            it.setTitle("PJ");
            it.bindContentsToProperty("jugados");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<Historial>>operator_doubleArrow(_column_1, _function_1);
        Column<Historial> _column_2 = new Column<Historial>(it);
        final Procedure1<Column<Historial>> _function_2 = new Procedure1<Column<Historial>>() {
          @Override
          public void apply(final Column<Historial> it) {
            it.setTitle("PG");
            it.bindContentsToProperty("ganados");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<Historial>>operator_doubleArrow(_column_2, _function_2);
        Column<Historial> _column_3 = new Column<Historial>(it);
        final Procedure1<Column<Historial>> _function_3 = new Procedure1<Column<Historial>>() {
          @Override
          public void apply(final Column<Historial> it) {
            it.setTitle("PE");
            it.bindContentsToProperty("empatados");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<Historial>>operator_doubleArrow(_column_3, _function_3);
        Column<Historial> _column_4 = new Column<Historial>(it);
        final Procedure1<Column<Historial>> _function_4 = new Procedure1<Column<Historial>>() {
          @Override
          public void apply(final Column<Historial> it) {
            it.setTitle("PP");
            it.bindContentsToProperty("perdidos");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<Historial>>operator_doubleArrow(_column_4, _function_4);
        Column<Historial> _column_5 = new Column<Historial>(it);
        final Procedure1<Column<Historial>> _function_5 = new Procedure1<Column<Historial>>() {
          @Override
          public void apply(final Column<Historial> it) {
            it.setTitle("DIF");
            PropertyLabelProvider<Historial> _bindContentsToProperty = it.bindContentsToProperty("diferencia");
            final Transformer<Integer, String> _function = new Transformer<Integer, String>() {
              @Override
              public String transform(final Integer dif) {
                String _xifexpression = null;
                if ((dif > 0)) {
                  _xifexpression = ("+" + Integer.valueOf(dif));
                } else {
                  _xifexpression = Integer.valueOf(dif).toString();
                }
                return _xifexpression;
              }
            };
            _bindContentsToProperty.setTransformer(_function);
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<Historial>>operator_doubleArrow(_column_5, _function_5);
      }
    };
    ObjectExtensions.<Table<Historial>>operator_doubleArrow(_table, _function);
  }
  
  @Override
  protected void addActions(final Panel actionsPanel) {
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
