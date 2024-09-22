package arena.windows;

import arena.components.LabeledTextBox;
import arena.models.PremiosTorneosModel;
import arena.models.TorneoModel;
import datos.PremioEvento;
import datos.PremioPosicion;
import org.apache.commons.collections15.Transformer;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.widgets.tables.labelprovider.PropertyLabelProvider;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ControlBuilder;

@SuppressWarnings("all")
public class PremiosTorneoWindow extends Dialog<PremiosTorneosModel> {
  public PremiosTorneoWindow(final WindowOwner owner, final TorneoModel model) {
    super(owner, new PremiosTorneosModel(model));
    this.setTitle("Premios");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    panel.setLayout(_horizontalLayout);
    Panel _panel = new Panel(panel);
    this.createPremiosPanel(_panel);
    Panel _panel_1 = new Panel(panel);
    this.createEventosPanel(_panel_1);
  }
  
  public void createPremiosPanel(final Panel panel) {
    Table<PremioPosicion> _table = new Table<PremioPosicion>(panel, PremioPosicion.class);
    final Procedure1<Table<PremioPosicion>> _function = new Procedure1<Table<PremioPosicion>>() {
      @Override
      public void apply(final Table<PremioPosicion> it) {
        it.bindItemsToProperty("listaPosiciones");
        it.<Object, ControlBuilder>bindValueToProperty("posicionON");
        it.setNumberVisibleRows(20);
        Column<PremioPosicion> _column = new Column<PremioPosicion>(it);
        final Procedure1<Column<PremioPosicion>> _function = new Procedure1<Column<PremioPosicion>>() {
          @Override
          public void apply(final Column<PremioPosicion> it) {
            it.setTitle("Posicion");
            PropertyLabelProvider<PremioPosicion> _bindContentsToProperty = it.bindContentsToProperty("posicion");
            final Transformer<Integer, String> _function = new Transformer<Integer, String>() {
              @Override
              public String transform(final Integer posicion) {
                return (Integer.valueOf(posicion) + "º");
              }
            };
            _bindContentsToProperty.setTransformer(_function);
            it.setFixedSize(60);
          }
        };
        ObjectExtensions.<Column<PremioPosicion>>operator_doubleArrow(_column, _function);
        Column<PremioPosicion> _column_1 = new Column<PremioPosicion>(it);
        final Procedure1<Column<PremioPosicion>> _function_1 = new Procedure1<Column<PremioPosicion>>() {
          @Override
          public void apply(final Column<PremioPosicion> it) {
            it.setTitle("Premio");
            it.bindContentsToProperty("premio");
            it.setFixedSize(60);
          }
        };
        ObjectExtensions.<Column<PremioPosicion>>operator_doubleArrow(_column_1, _function_1);
      }
    };
    ObjectExtensions.<Table<PremioPosicion>>operator_doubleArrow(_table, _function);
    LabeledTextBox _labeledTextBox = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function_1 = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.bindTextToProperty("posicionON.posicion");
        it.bindValueToProperty("posicionON.premio");
        it.setEnabled("esMaster");
        it.setWidth(50);
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox, _function_1);
  }
  
  public void createEventosPanel(final Panel panel) {
    Table<PremioEvento> _table = new Table<PremioEvento>(panel, PremioEvento.class);
    final Procedure1<Table<PremioEvento>> _function = new Procedure1<Table<PremioEvento>>() {
      @Override
      public void apply(final Table<PremioEvento> it) {
        it.bindItemsToProperty("listaEventos");
        it.<Object, ControlBuilder>bindValueToProperty("eventoON");
        it.setNumberVisibleRows(4);
        Column<PremioEvento> _column = new Column<PremioEvento>(it);
        final Procedure1<Column<PremioEvento>> _function = new Procedure1<Column<PremioEvento>>() {
          @Override
          public void apply(final Column<PremioEvento> it) {
            it.setTitle("Evento");
            it.bindContentsToProperty("evento");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<PremioEvento>>operator_doubleArrow(_column, _function);
        Column<PremioEvento> _column_1 = new Column<PremioEvento>(it);
        final Procedure1<Column<PremioEvento>> _function_1 = new Procedure1<Column<PremioEvento>>() {
          @Override
          public void apply(final Column<PremioEvento> it) {
            it.setTitle("Premio");
            it.bindContentsToProperty("premio");
            it.setFixedSize(60);
          }
        };
        ObjectExtensions.<Column<PremioEvento>>operator_doubleArrow(_column_1, _function_1);
      }
    };
    ObjectExtensions.<Table<PremioEvento>>operator_doubleArrow(_table, _function);
    LabeledTextBox _labeledTextBox = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function_1 = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.bindTextToProperty("eventoON.evento");
        it.bindValueToProperty("eventoON.premio");
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
