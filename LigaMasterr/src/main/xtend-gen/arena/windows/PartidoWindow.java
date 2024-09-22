package arena.windows;

import arena.models.PartidoModel;
import arena.models.TorneoModel;
import java.awt.Color;
import master.DT;
import master.Jugador;
import master.Partido;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

@SuppressWarnings("all")
public class PartidoWindow extends SimpleWindow<PartidoModel> {
  public PartidoWindow(final WindowOwner parent, final TorneoModel model) {
    super(parent, new PartidoModel(model));
    PartidoModel _modelObject = this.getModelObject();
    Partido _partidoON = _modelObject.getPartidoON();
    DT _dtLocal = _partidoON.getDtLocal();
    String _nombreDT = _dtLocal.getNombreDT();
    String _plus = (_nombreDT + " vs. ");
    PartidoModel _modelObject_1 = this.getModelObject();
    Partido _partidoON_1 = _modelObject_1.getPartidoON();
    DT _dtVisitante = _partidoON_1.getDtVisitante();
    String _nombreDT_1 = _dtVisitante.getNombreDT();
    String _plus_1 = (_plus + _nombreDT_1);
    this.setTitle(_plus_1);
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    panel.setLayout(_horizontalLayout);
    Panel _panel = new Panel(panel);
    this.createNoDisponiblePanel(_panel);
    Panel _panel_1 = new Panel(panel);
    this.createEquipoPanel(_panel_1, "Local");
    Panel _panel_2 = new Panel(panel);
    this.createPartidoPanel(_panel_2);
    Panel _panel_3 = new Panel(panel);
    this.createEquipoPanel(_panel_3, "Visitante");
    Panel _panel_4 = new Panel(panel);
    this.createAmonestadosPanel(_panel_4);
  }
  
  public void createNoDisponiblePanel(final Panel panel) {
    Label _label = new Label(panel);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Suspendidos");
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    List<Object> _list = new List<Object>(panel);
    final Procedure1<List<Object>> _function_1 = new Procedure1<List<Object>>() {
      @Override
      public void apply(final List<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("suspendidos");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(Jugador.class, "nombre");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.setHeight(65);
        it.setWidth(85);
      }
    };
    ObjectExtensions.<List<Object>>operator_doubleArrow(_list, _function_1);
    Label _label_1 = new Label(panel);
    _label_1.setText("Jugadores No pagos");
    Table<Jugador> _table = new Table<Jugador>(panel, Jugador.class);
    final Procedure1<Table<Jugador>> _function_2 = new Procedure1<Table<Jugador>>() {
      @Override
      public void apply(final Table<Jugador> it) {
        it.bindItemsToProperty("listaJugadoresDeshabilitados");
        it.setNumberVisibleRows(8);
        Column<Jugador> _column = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Nombre");
            it.bindContentsToProperty("nombre");
            it.setFixedSize(120);
            it.setForeground(Color.red);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column, _function);
      }
    };
    ObjectExtensions.<Table<Jugador>>operator_doubleArrow(_table, _function_2);
    Label _label_2 = new Label(panel);
    final Procedure1<Label> _function_3 = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Lesionados");
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label_2, _function_3);
    Table<Jugador> _table_1 = new Table<Jugador>(panel, Jugador.class);
    final Procedure1<Table<Jugador>> _function_4 = new Procedure1<Table<Jugador>>() {
      @Override
      public void apply(final Table<Jugador> it) {
        it.bindItemsToProperty("lesionados");
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setNumberVisibleRows(4);
        Column<Jugador> _column = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.bindContentsToProperty("nombre");
            it.setFixedSize(85);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column, _function);
        Column<Jugador> _column_1 = new Column<Jugador>(it);
        final Procedure1<Column<Jugador>> _function_1 = new Procedure1<Column<Jugador>>() {
          @Override
          public void apply(final Column<Jugador> it) {
            it.setTitle("Fechas");
            it.bindContentsToProperty("lesion");
            it.setFixedSize(50);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column_1, _function_1);
      }
    };
    ObjectExtensions.<Table<Jugador>>operator_doubleArrow(_table_1, _function_4);
    Panel _panel = new Panel(panel);
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    final Panel buttonPanel = _panel.setLayout(_horizontalLayout);
    Label _label_3 = new Label(buttonPanel);
    _label_3.setText("                  ");
    Button _button = new Button(buttonPanel);
    final Procedure1<Button> _function_5 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("+");
        final Action _function = new Action() {
          @Override
          public void execute() {
            PartidoModel _modelObject = PartidoWindow.this.getModelObject();
            _modelObject.incLesion();
          }
        };
        it.onClick(_function);
        it.<ControlBuilder>bindEnabledToProperty("lesionesON");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function_5);
    Button _button_1 = new Button(buttonPanel);
    final Procedure1<Button> _function_6 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("-");
        final Action _function = new Action() {
          @Override
          public void execute() {
            PartidoModel _modelObject = PartidoWindow.this.getModelObject();
            _modelObject.decLesion();
          }
        };
        it.onClick(_function);
        it.<ControlBuilder>bindEnabledToProperty("lesionesON");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_6);
  }
  
  public void createEquipoPanel(final Panel panel, final String dt) {
    Label _label = new Label(panel);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.<Object, ControlBuilder>bindValueToProperty((("partidoON.dt" + dt) + ".nombreDT"));
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    List<Object> _list = new List<Object>(panel);
    final Procedure1<List<Object>> _function_1 = new Procedure1<List<Object>>() {
      @Override
      public void apply(final List<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty(("equipo" + dt));
        PropertyAdapter _propertyAdapter = new PropertyAdapter(Jugador.class, "nombre");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setHeight(215);
        it.setWidth(85);
      }
    };
    ObjectExtensions.<List<Object>>operator_doubleArrow(_list, _function_1);
  }
  
  public void createPartidoPanel(final Panel panel) {
    Label _label = new Label(panel);
    _label.setText("\n");
    Label _label_1 = new Label(panel);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.<Object, ControlBuilder>bindValueToProperty("partidoON.score");
        it.setFontSize(30);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label_1, _function);
    Label _label_2 = new Label(panel);
    final Procedure1<Label> _function_1 = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Goles");
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label_2, _function_1);
    Panel _panel = new Panel(panel);
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    final Panel golesPanel = _panel.setLayout(_horizontalLayout);
    List<Object> _list = new List<Object>(golesPanel);
    final Procedure1<List<Object>> _function_2 = new Procedure1<List<Object>>() {
      @Override
      public void apply(final List<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("partidoON.golesLocal");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(Jugador.class, "nombre");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setHeight(100);
        it.setWidth(85);
      }
    };
    ObjectExtensions.<List<Object>>operator_doubleArrow(_list, _function_2);
    List<Object> _list_1 = new List<Object>(golesPanel);
    final Procedure1<List<Object>> _function_3 = new Procedure1<List<Object>>() {
      @Override
      public void apply(final List<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("partidoON.golesVisitante");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(Jugador.class, "nombre");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setHeight(100);
        it.setWidth(85);
      }
    };
    ObjectExtensions.<List<Object>>operator_doubleArrow(_list_1, _function_3);
    Panel _panel_1 = new Panel(panel);
    HorizontalLayout _horizontalLayout_1 = new HorizontalLayout();
    final Panel buttonPanel = _panel_1.setLayout(_horizontalLayout_1);
    Label _label_3 = new Label(buttonPanel);
    _label_3.setText("\t          ");
    Button _button = new Button(buttonPanel);
    final Procedure1<Button> _function_4 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("+");
        final Action _function = new Action() {
          @Override
          public void execute() {
            PartidoModel _modelObject = PartidoWindow.this.getModelObject();
            _modelObject.addGol();
          }
        };
        it.onClick(_function);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function_4);
    Button _button_1 = new Button(buttonPanel);
    final Procedure1<Button> _function_5 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("-");
        final Action _function = new Action() {
          @Override
          public void execute() {
            PartidoModel _modelObject = PartidoWindow.this.getModelObject();
            _modelObject.removeGol();
          }
        };
        it.onClick(_function);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_5);
  }
  
  public void createAmonestadosPanel(final Panel panel) {
    Label _label = new Label(panel);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Amarillas");
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    List<Object> _list = new List<Object>(panel);
    final Procedure1<List<Object>> _function_1 = new Procedure1<List<Object>>() {
      @Override
      public void apply(final List<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("partidoON.listaAmarillas");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(Jugador.class, "nombre");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setHeight(50);
        it.setWidth(85);
      }
    };
    ObjectExtensions.<List<Object>>operator_doubleArrow(_list, _function_1);
    Panel _panel = new Panel(panel);
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    final Panel amarillaPanel = _panel.setLayout(_horizontalLayout);
    Label _label_1 = new Label(amarillaPanel);
    _label_1.setText("         ");
    Button _button = new Button(amarillaPanel);
    final Procedure1<Button> _function_2 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("+");
        final Action _function = new Action() {
          @Override
          public void execute() {
            PartidoModel _modelObject = PartidoWindow.this.getModelObject();
            _modelObject.addAmarilla();
          }
        };
        it.onClick(_function);
        it.setBackground(Color.YELLOW);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function_2);
    Button _button_1 = new Button(amarillaPanel);
    final Procedure1<Button> _function_3 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("-");
        final Action _function = new Action() {
          @Override
          public void execute() {
            PartidoModel _modelObject = PartidoWindow.this.getModelObject();
            _modelObject.removeAmarilla();
          }
        };
        it.onClick(_function);
        it.setBackground(Color.YELLOW);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_3);
    Label _label_2 = new Label(panel);
    final Procedure1<Label> _function_4 = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Rojas");
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label_2, _function_4);
    List<Object> _list_1 = new List<Object>(panel);
    final Procedure1<List<Object>> _function_5 = new Procedure1<List<Object>>() {
      @Override
      public void apply(final List<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("partidoON.listaRojas");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(Jugador.class, "nombre");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setHeight(50);
        it.setWidth(85);
      }
    };
    ObjectExtensions.<List<Object>>operator_doubleArrow(_list_1, _function_5);
    Panel _panel_1 = new Panel(panel);
    HorizontalLayout _horizontalLayout_1 = new HorizontalLayout();
    final Panel rojaPanel = _panel_1.setLayout(_horizontalLayout_1);
    Label _label_3 = new Label(rojaPanel);
    _label_3.setText("         ");
    Button _button_2 = new Button(rojaPanel);
    final Procedure1<Button> _function_6 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("+");
        final Action _function = new Action() {
          @Override
          public void execute() {
            PartidoModel _modelObject = PartidoWindow.this.getModelObject();
            _modelObject.addRoja();
          }
        };
        it.onClick(_function);
        it.setBackground(Color.RED);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_2, _function_6);
    Button _button_3 = new Button(rojaPanel);
    final Procedure1<Button> _function_7 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("-");
        final Action _function = new Action() {
          @Override
          public void execute() {
            PartidoModel _modelObject = PartidoWindow.this.getModelObject();
            _modelObject.removeRoja();
          }
        };
        it.onClick(_function);
        it.setBackground(Color.RED);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_3, _function_7);
  }
  
  @Override
  protected void addActions(final Panel actionsPanel) {
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
