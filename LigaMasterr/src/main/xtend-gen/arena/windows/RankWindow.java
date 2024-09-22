package arena.windows;

import arena.windows.RankModel;
import master.DT;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;

@SuppressWarnings("all")
public class RankWindow extends Dialog<RankModel> {
  public RankWindow(final WindowOwner parent, final DT model) {
    super(parent, new RankModel(model));
    this.setTitle("GrondoRanking");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    Table<DT> _table = new Table<DT>(panel, DT.class);
    final Procedure1<Table<DT>> _function = new Procedure1<Table<DT>>() {
      @Override
      public void apply(final Table<DT> it) {
        it.bindItemsToProperty("dts");
        it.setNumberVisibleRows(20);
        Column<DT> _column = new Column<DT>(it);
        final Procedure1<Column<DT>> _function = new Procedure1<Column<DT>>() {
          @Override
          public void apply(final Column<DT> it) {
            it.setTitle("DT");
            it.bindContentsToProperty("nombreDT");
            it.setFixedSize(120);
          }
        };
        ObjectExtensions.<Column<DT>>operator_doubleArrow(_column, _function);
        Column<DT> _column_1 = new Column<DT>(it);
        final Procedure1<Column<DT>> _function_1 = new Procedure1<Column<DT>>() {
          @Override
          public void apply(final Column<DT> it) {
            it.setTitle("PUNTOS");
            it.bindContentsToProperty("rank");
            it.setFixedSize(120);
          }
        };
        ObjectExtensions.<Column<DT>>operator_doubleArrow(_column_1, _function_1);
        Button _button = new Button(panel);
        final Procedure1<Button> _function_2 = new Procedure1<Button>() {
          @Override
          public void apply(final Button it) {
            it.setCaption("Iniciar Rank");
            final Action _function = new Action() {
              @Override
              public void execute() {
                RankModel _modelObject = RankWindow.this.getModelObject();
                _modelObject.iniciarRank();
              }
            };
            it.onClick(_function);
            it.setFontSize(10);
          }
        };
        ObjectExtensions.<Button>operator_doubleArrow(_button, _function_2);
      }
    };
    ObjectExtensions.<Table<DT>>operator_doubleArrow(_table, _function);
  }
  
  @Override
  protected void addActions(final Panel actionsPanel) {
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
