package arena.windows;

import arena.models.ImpuestoModel;
import arena.models.TorneoConfigModel;
import master.Jugador;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;

@SuppressWarnings("all")
public class ImpuestosWindow extends Dialog<ImpuestoModel> {
  public ImpuestosWindow(final WindowOwner owner, final TorneoConfigModel model) {
    super(owner, new ImpuestoModel(model));
    this.setTitle("Impuestos");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    panel.setLayout(_horizontalLayout);
    Panel _panel = new Panel(panel);
    this.createSinPagarPanel(_panel);
    Panel _panel_1 = new Panel(panel);
    this.createPagarPanel(_panel_1);
    Panel _panel_2 = new Panel(panel);
    this.createAPagarPanel(_panel_2);
    Panel _panel_3 = new Panel(panel);
    this.createEstadoPanel(_panel_3);
  }
  
  public void createSinPagarPanel(final Panel panel) {
    Label _label = new Label(panel);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Sin Pagar");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    Table<Jugador> _table = new Table<Jugador>(panel, Jugador.class);
    final Procedure1<Table<Jugador>> _function_1 = new Procedure1<Table<Jugador>>() {
      @Override
      public void apply(final Table<Jugador> it) {
        it.bindItemsToProperty("jugadoresSinPagar");
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setNumberVisibleRows(8);
        Column<Jugador> _column = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Nombre");
            it.bindContentsToProperty("nombre");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column, _function);
        Column<Jugador> _column_1 = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function_1 = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Nivel");
            it.bindContentsToProperty("nivel");
            it.setFixedSize(40);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column_1, _function_1);
        Column<Jugador> _column_2 = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function_2 = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Impuesto");
            it.bindContentsToProperty("impuesto");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column_2, _function_2);
      }
    };
    ObjectExtensions.<Table<Jugador>>operator_doubleArrow(_table, _function_1);
  }
  
  public void createAPagarPanel(final Panel panel) {
    Label _label = new Label(panel);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("A Pagar");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    Table<Jugador> _table = new Table<Jugador>(panel, Jugador.class);
    final Procedure1<Table<Jugador>> _function_1 = new Procedure1<Table<Jugador>>() {
      @Override
      public void apply(final Table<Jugador> it) {
        it.bindItemsToProperty("jugadoresAPagar");
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setNumberVisibleRows(8);
        Column<Jugador> _column = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Nombre");
            it.bindContentsToProperty("nombre");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column, _function);
        Column<Jugador> _column_1 = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function_1 = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Nivel");
            it.bindContentsToProperty("nivel");
            it.setFixedSize(40);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column_1, _function_1);
        Column<Jugador> _column_2 = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function_2 = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Impuesto");
            it.bindContentsToProperty("impuesto");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column_2, _function_2);
      }
    };
    ObjectExtensions.<Table<Jugador>>operator_doubleArrow(_table, _function_1);
  }
  
  public void createPagarPanel(final Panel panel) {
    Label _label = new Label(panel);
    _label.setText("\n\n\n\n");
    Button _button = new Button(panel);
    final Procedure1<Button> _function = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("<=");
        final Action _function = new Action() {
          @Override
          public void execute() {
            ImpuestoModel _modelObject = ImpuestosWindow.this.getModelObject();
            _modelObject.addSinPagar();
          }
        };
        it.onClick(_function);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function);
    Button _button_1 = new Button(panel);
    final Procedure1<Button> _function_1 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("=>");
        final Action _function = new Action() {
          @Override
          public void execute() {
            ImpuestoModel _modelObject = ImpuestosWindow.this.getModelObject();
            _modelObject.addAPagar();
          }
        };
        it.onClick(_function);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_1);
  }
  
  public void createEstadoPanel(final Panel panel) {
    Panel _panel = new Panel(panel);
    VerticalLayout _verticalLayout = new VerticalLayout();
    final Panel panelPlata = _panel.setLayout(_verticalLayout);
    Label _label = new Label(panelPlata);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Plata Disponible");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    Label _label_1 = new Label(panelPlata);
    final Procedure1<Label> _function_1 = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.<Object, ControlBuilder>bindValueToProperty("dtON.plata");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label_1, _function_1);
    Panel _panel_1 = new Panel(panel);
    VerticalLayout _verticalLayout_1 = new VerticalLayout();
    final Panel panelImpuestos = _panel_1.setLayout(_verticalLayout_1);
    Label _label_2 = new Label(panelImpuestos);
    final Procedure1<Label> _function_2 = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Suma Impuestos");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label_2, _function_2);
    Label _label_3 = new Label(panelImpuestos);
    final Procedure1<Label> _function_3 = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.<Object, ControlBuilder>bindValueToProperty("impuestos");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label_3, _function_3);
    Button _button = new Button(panel);
    final Procedure1<Button> _function_4 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Pagar Impuestos");
        final Action _function = new Action() {
          @Override
          public void execute() {
            ImpuestoModel _modelObject = ImpuestosWindow.this.getModelObject();
            _modelObject.pagarImpuestos();
            ImpuestoModel _modelObject_1 = ImpuestosWindow.this.getModelObject();
            _modelObject_1.deshabilitar();
          }
        };
        it.onClick(_function);
        it.<ControlBuilder>bindEnabledToProperty("habilitado");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function_4);
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
