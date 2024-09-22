package arena.windows;

import arena.components.LabeledTextBox;
import arena.models.ReglasModel;
import datos.PrecioEvento;
import datos.PrecioNivel;
import master.DT;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ControlBuilder;

@SuppressWarnings("all")
public class ReglasWindow extends Dialog<ReglasModel> {
  public ReglasWindow(final WindowOwner owner, final DT model) {
    super(owner, new ReglasModel(model));
    this.setTitle("Reglas");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    panel.setLayout(_horizontalLayout);
    Panel _panel = new Panel(panel);
    this.createPreciosPanel(_panel);
    Panel _panel_1 = new Panel(panel);
    this.createEventosPanel(_panel_1);
  }
  
  public void createPreciosPanel(final Panel panel) {
    Table<PrecioNivel> _table = new Table<PrecioNivel>(panel, PrecioNivel.class);
    final Procedure1<Table<PrecioNivel>> _function = new Procedure1<Table<PrecioNivel>>() {
      @Override
      public void apply(final Table<PrecioNivel> it) {
        it.bindItemsToProperty("listaNiveles");
        it.<Object, ControlBuilder>bindValueToProperty("nivelON");
        it.setNumberVisibleRows(20);
        Column<PrecioNivel> _column = new Column<PrecioNivel>(it);
        final Procedure1<Column<PrecioNivel>> _function = new Procedure1<Column<PrecioNivel>>() {
          @Override
          public void apply(final Column<PrecioNivel> it) {
            it.setTitle("Nivel");
            it.bindContentsToProperty("nivel");
            it.setFixedSize(40);
          }
        };
        ObjectExtensions.<Column<PrecioNivel>>operator_doubleArrow(_column, _function);
        Column<PrecioNivel> _column_1 = new Column<PrecioNivel>(it);
        final Procedure1<Column<PrecioNivel>> _function_1 = new Procedure1<Column<PrecioNivel>>() {
          @Override
          public void apply(final Column<PrecioNivel> it) {
            it.setTitle("Precio");
            it.bindContentsToProperty("precio");
            it.setFixedSize(65);
          }
        };
        ObjectExtensions.<Column<PrecioNivel>>operator_doubleArrow(_column_1, _function_1);
      }
    };
    ObjectExtensions.<Table<PrecioNivel>>operator_doubleArrow(_table, _function);
    LabeledTextBox _labeledTextBox = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function_1 = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.bindTextToProperty("nivelON.nivel");
        it.bindValueToProperty("nivelON.precio");
        it.setEnabled("esMaster");
        it.setWidth(50);
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox, _function_1);
  }
  
  public void createEventosPanel(final Panel panel) {
    Table<PrecioEvento> _table = new Table<PrecioEvento>(panel, PrecioEvento.class);
    final Procedure1<Table<PrecioEvento>> _function = new Procedure1<Table<PrecioEvento>>() {
      @Override
      public void apply(final Table<PrecioEvento> it) {
        it.bindItemsToProperty("listaEventos");
        it.<Object, ControlBuilder>bindValueToProperty("eventoON");
        it.setNumberVisibleRows(7);
        Column<PrecioEvento> _column = new Column<PrecioEvento>(it);
        final Procedure1<Column<PrecioEvento>> _function = new Procedure1<Column<PrecioEvento>>() {
          @Override
          public void apply(final Column<PrecioEvento> it) {
            it.setTitle("Evento");
            it.bindContentsToProperty("evento");
            it.setFixedSize(100);
          }
        };
        ObjectExtensions.<Column<PrecioEvento>>operator_doubleArrow(_column, _function);
        Column<PrecioEvento> _column_1 = new Column<PrecioEvento>(it);
        final Procedure1<Column<PrecioEvento>> _function_1 = new Procedure1<Column<PrecioEvento>>() {
          @Override
          public void apply(final Column<PrecioEvento> it) {
            it.setTitle("Precio");
            it.bindContentsToProperty("precio");
            it.setFixedSize(65);
          }
        };
        ObjectExtensions.<Column<PrecioEvento>>operator_doubleArrow(_column_1, _function_1);
      }
    };
    ObjectExtensions.<Table<PrecioEvento>>operator_doubleArrow(_table, _function);
    LabeledTextBox _labeledTextBox = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function_1 = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.bindTextToProperty("eventoON.evento");
        it.bindValueToProperty("eventoON.precio");
        it.setEnabled("esMaster");
        it.setWidth(50);
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox, _function_1);
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
