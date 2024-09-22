package arena.windows;

import arena.components.LabeledNumericField;
import arena.components.LabeledTextBox;
import arena.models.EquipoModel;
import arena.models.TorneoModel;
import arena.windows.CrearOfertaWindow;
import arena.windows.OfertasWindow;
import arena.windows.TransferiblesWindow;
import java.awt.Color;
import master.DT;
import master.Jugador;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

@SuppressWarnings("all")
public class EquipoWindow extends SimpleWindow<EquipoModel> {
  public EquipoWindow(final WindowOwner owner, final TorneoModel model) {
    super(owner, new EquipoModel(model));
    this.setTitle("Equipos");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    panel.setLayout(_horizontalLayout);
    Panel _panel = new Panel(panel);
    this.createDTPanel(_panel);
    Panel _panel_1 = new Panel(panel);
    this.createEquipoPanel(_panel_1);
    Panel _panel_2 = new Panel(panel);
    this.createTransferPanel(_panel_2);
    Panel _panel_3 = new Panel(panel);
    this.createMaquinaPanel(_panel_3);
  }
  
  public Button createTransferPanel(final Panel panel) {
    Button _xblockexpression = null;
    {
      Label _label = new Label(panel);
      _label.setText("\n\n\n\n\n\n\n\n\n\n");
      Button _button = new Button(panel);
      final Procedure1<Button> _function = new Procedure1<Button>() {
        @Override
        public void apply(final Button it) {
          it.setCaption("<=");
          final Action _function = new Action() {
            @Override
            public void execute() {
              EquipoModel _modelObject = EquipoWindow.this.getModelObject();
              _modelObject.validar();
              EquipoModel _modelObject_1 = EquipoWindow.this.getModelObject();
              _modelObject_1.transferIn();
            }
          };
          it.onClick(_function);
          it.<ControlBuilder>bindEnabledToProperty("esMaster");
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
              EquipoModel _modelObject = EquipoWindow.this.getModelObject();
              _modelObject.validar();
              EquipoModel _modelObject_1 = EquipoWindow.this.getModelObject();
              _modelObject_1.transferOut();
            }
          };
          it.onClick(_function);
          it.<ControlBuilder>bindEnabledToProperty("esMaster");
        }
      };
      _xblockexpression = ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_1);
    }
    return _xblockexpression;
  }
  
  public void createDTPanel(final Panel panel) {
    LabeledTextBox _labeledTextBox = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.setText("Nombre: ");
        it.bindValueToProperty("dtON.nombreDT");
        it.setWidth(85);
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox, _function);
    LabeledTextBox _labeledTextBox_1 = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function_1 = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.setText("Equipo: ");
        it.bindValueToProperty("dtON.nombreEquipo");
        it.setWidth(95);
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox_1, _function_1);
    LabeledTextBox _labeledTextBox_2 = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function_2 = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.setText("Plata: ");
        it.bindValueToProperty("dtON.plata");
        it.setWidth(110);
        it.setEnabled("esMaster");
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox_2, _function_2);
    LabeledTextBox _labeledTextBox_3 = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function_3 = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.setText("Cantidad Jugadores: ");
        it.bindValueToProperty("dtON.cantJugadores");
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox_3, _function_3);
    LabeledTextBox _labeledTextBox_4 = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function_4 = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.setText("Torneos Restantes: ");
        it.bindValueToProperty("dtON.torneosDisponibles");
        it.setEnabled("esMaster");
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox_4, _function_4);
    LabeledTextBox _labeledTextBox_5 = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function_5 = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.setText("Slots: ");
        it.bindValueToProperty("dtON.slots");
        it.setEnabled("esMaster");
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox_5, _function_5);
    Button _button = new Button(panel);
    final Procedure1<Button> _function_6 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Comprar Slot");
        final Action _function = new Action() {
          @Override
          public void execute() {
            EquipoModel _modelObject = EquipoWindow.this.getModelObject();
            _modelObject.comprarSlot();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function_6);
    Button _button_1 = new Button(panel);
    final Procedure1<Button> _function_7 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Ofertas");
        final Action _function = new Action() {
          @Override
          public void execute() {
            EquipoModel _modelObject = EquipoWindow.this.getModelObject();
            DT _dtActivo = _modelObject.getDtActivo();
            OfertasWindow _ofertasWindow = new OfertasWindow(EquipoWindow.this, _dtActivo);
            _ofertasWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_7);
    Button _button_2 = new Button(panel);
    final Procedure1<Button> _function_8 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Mercado");
        final Action _function = new Action() {
          @Override
          public void execute() {
            EquipoModel _modelObject = EquipoWindow.this.getModelObject();
            DT _dtActivo = _modelObject.getDtActivo();
            TransferiblesWindow _transferiblesWindow = new TransferiblesWindow(EquipoWindow.this, _dtActivo);
            _transferiblesWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_2, _function_8);
    Button _button_3 = new Button(panel);
    final Procedure1<Button> _function_9 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Update");
        final Action _function = new Action() {
          @Override
          public void execute() {
            EquipoModel _modelObject = EquipoWindow.this.getModelObject();
            _modelObject.updatePlantel();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_3, _function_9);
    Label _label = new Label(panel);
    _label.setText("Jugadores Deshabilitados");
    Table<Jugador> _table = new Table<Jugador>(panel, Jugador.class);
    final Procedure1<Table<Jugador>> _function_10 = new Procedure1<Table<Jugador>>() {
      @Override
      public void apply(final Table<Jugador> it) {
        it.bindItemsToProperty("dtON.listaJugadoresDeshabilitados");
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
    ObjectExtensions.<Table<Jugador>>operator_doubleArrow(_table, _function_10);
  }
  
  public void createEquipoPanel(final Panel panel) {
    Selector<Object> _selector = new Selector<Object>(panel);
    final Procedure1<Selector<Object>> _function = new Procedure1<Selector<Object>>() {
      @Override
      public void apply(final Selector<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("listaDT");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(DT.class, "nombreDT");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.<Object, ControlBuilder>bindValueToProperty("dtON");
        it.setWidth(300);
      }
    };
    ObjectExtensions.<Selector<Object>>operator_doubleArrow(_selector, _function);
    Table<Jugador> _table = new Table<Jugador>(panel, Jugador.class);
    final Procedure1<Table<Jugador>> _function_1 = new Procedure1<Table<Jugador>>() {
      @Override
      public void apply(final Table<Jugador> it) {
        it.bindItemsToProperty("dtON.listaJugadores");
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setNumberVisibleRows(18);
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
            it.setTitle("Posiciones");
            it.bindContentsToProperty("posiciones");
            it.setFixedSize(125);
          }
        };
        ObjectExtensions.<Column<Jugador>>operator_doubleArrow(_column_4, _function_4);
      }
    };
    ObjectExtensions.<Table<Jugador>>operator_doubleArrow(_table, _function_1);
    Panel _panel = new Panel(panel);
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    final Panel buttonPanel = _panel.setLayout(_horizontalLayout);
    LabeledNumericField _labeledNumericField = new LabeledNumericField(buttonPanel);
    final Procedure1<LabeledNumericField> _function_2 = new Procedure1<LabeledNumericField>() {
      @Override
      public void apply(final LabeledNumericField it) {
        it.setText("Precio: ");
        it.bindValueToProperty("jugadorON.precioVenta");
        it.bindEnabledToProperty("jugadorPropio");
        it.setWidth(80);
      }
    };
    ObjectExtensions.<LabeledNumericField>operator_doubleArrow(_labeledNumericField, _function_2);
    Button _button = new Button(buttonPanel);
    final Procedure1<Button> _function_3 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Ofertar");
        it.setFontSize(10);
        final Action _function = new Action() {
          @Override
          public void execute() {
            EquipoModel _modelObject = EquipoWindow.this.getModelObject();
            _modelObject.validarOfertar();
            EquipoModel _modelObject_1 = EquipoWindow.this.getModelObject();
            CrearOfertaWindow _crearOfertaWindow = new CrearOfertaWindow(EquipoWindow.this, _modelObject_1);
            _crearOfertaWindow.open();
          }
        };
        it.onClick(_function);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function_3);
    Button _button_1 = new Button(buttonPanel);
    final Procedure1<Button> _function_4 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Vender A Máquina");
        it.setFontSize(10);
        final Action _function = new Action() {
          @Override
          public void execute() {
            EquipoModel _modelObject = EquipoWindow.this.getModelObject();
            _modelObject.venderAMaquina();
          }
        };
        it.onClick(_function);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_4);
  }
  
  public Button createMaquinaPanel(final Panel panel) {
    Button _xblockexpression = null;
    {
      Label _label = new Label(panel);
      final Procedure1<Label> _function = new Procedure1<Label>() {
        @Override
        public void apply(final Label it) {
          it.setText("Lista Máquina");
          it.setFontSize(12);
        }
      };
      ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
      Table<Jugador> _table = new Table<Jugador>(panel, Jugador.class);
      final Procedure1<Table<Jugador>> _function_1 = new Procedure1<Table<Jugador>>() {
        @Override
        public void apply(final Table<Jugador> it) {
          it.bindItemsToProperty("listaMaquina");
          it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
          it.setNumberVisibleRows(18);
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
              it.bindContentsToProperty("precioMaquina");
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
      ObjectExtensions.<Table<Jugador>>operator_doubleArrow(_table, _function_1);
      Panel _panel = new Panel(panel);
      HorizontalLayout _horizontalLayout = new HorizontalLayout();
      final Panel buttonPanel = _panel.setLayout(_horizontalLayout);
      TextBox _textBox = new TextBox(buttonPanel);
      final Procedure1<TextBox> _function_2 = new Procedure1<TextBox>() {
        @Override
        public void apply(final TextBox it) {
          it.<Object, ControlBuilder>bindValueToProperty("textoBusqueda");
          it.setWidth(180);
          it.setFontSize(12);
        }
      };
      ObjectExtensions.<TextBox>operator_doubleArrow(_textBox, _function_2);
      Button _button = new Button(buttonPanel);
      final Procedure1<Button> _function_3 = new Procedure1<Button>() {
        @Override
        public void apply(final Button it) {
          it.setCaption("Buscar");
          final Action _function = new Action() {
            @Override
            public void execute() {
              EquipoModel _modelObject = EquipoWindow.this.getModelObject();
              _modelObject.buscar();
            }
          };
          it.onClick(_function);
          it.setFontSize(10);
          it.setAsDefault();
        }
      };
      ObjectExtensions.<Button>operator_doubleArrow(_button, _function_3);
      Button _button_1 = new Button(buttonPanel);
      final Procedure1<Button> _function_4 = new Procedure1<Button>() {
        @Override
        public void apply(final Button it) {
          it.setCaption("Comprar A Máquina");
          final Action _function = new Action() {
            @Override
            public void execute() {
              EquipoModel _modelObject = EquipoWindow.this.getModelObject();
              _modelObject.validar();
              EquipoModel _modelObject_1 = EquipoWindow.this.getModelObject();
              _modelObject_1.comprarAMaquina();
            }
          };
          it.onClick(_function);
          it.setFontSize(10);
        }
      };
      _xblockexpression = ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_4);
    }
    return _xblockexpression;
  }
  
  @Override
  protected void addActions(final Panel actionsPanel) {
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
