package arena.windows;

import arena.models.TransferiblesModel;
import master.DT;
import master.Jugador;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;

@SuppressWarnings("all")
public class TransferiblesWindow extends Dialog<TransferiblesModel> {
  public TransferiblesWindow(final WindowOwner owner, final DT model) {
    super(owner, new TransferiblesModel(model));
    this.setTitle("Transferibles");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    this.createListaPanel(panel);
    this.createActionsPanel(panel);
  }
  
  public void createListaPanel(final Panel panel) {
    Table<Jugador> _table = new Table<Jugador>(panel, Jugador.class);
    final Procedure1<Table<Jugador>> _function = new Procedure1<Table<Jugador>>() {
      @Override
      public void apply(final Table<Jugador> it) {
        it.bindItemsToProperty("transferibles");
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setNumberVisibleRows(8);
        Column<Jugador> _column = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Nombre");
            it.bindContentsToProperty("nombre");
            it.setFixedSize(150);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column, _function);
        Column<Jugador> _column_1 = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function_1 = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Nivel");
            it.bindContentsToProperty("nivel");
            it.setFixedSize(45);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column_1, _function_1);
        Column<Jugador> _column_2 = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function_2 = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Potencial");
            it.bindContentsToProperty("potencial");
            it.setFixedSize(65);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column_2, _function_2);
        Column<Jugador> _column_3 = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function_3 = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Precio");
            it.bindContentsToProperty("precioVenta");
            it.setFixedSize(65);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column_3, _function_3);
        Column<Jugador> _column_4 = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function_4 = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Propietario");
            it.bindContentsToProperty("propietario.nombreDT");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column_4, _function_4);
      }
    };
    ObjectExtensions.<Table<Jugador>>operator_doubleArrow(_table, _function);
  }
  
  @Override
  public void addActions(final Panel panel) {
    Button _button = new Button(panel);
    final Procedure1<Button> _function = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Comprar");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TransferiblesModel _modelObject = TransferiblesWindow.this.getModelObject();
            _modelObject.comprarJugador();
          }
        };
        it.onClick(_function);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function);
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
