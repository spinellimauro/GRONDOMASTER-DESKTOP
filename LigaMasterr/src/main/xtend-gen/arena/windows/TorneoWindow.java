package arena.windows;

import arena.components.LabeledSelector;
import arena.models.LoginModel;
import arena.models.TorneoModel;
import arena.windows.EquipoWindow;
import arena.windows.EstadisticasWindow;
import arena.windows.HistorialWindow;
import arena.windows.LoginWindow;
import arena.windows.OpcionesWindow;
import arena.windows.PartidoWindow;
import arena.windows.PremiosTorneoWindow;
import arena.windows.RankWindow;
import arena.windows.ReglasWindow;
import arena.windows.TorneoConfigWindow;
import arena.windows.TransferWindow;
import master.DT;
import master.Partido;
import master.Torneo;
import org.apache.commons.collections15.Transformer;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.widgets.tables.labelprovider.PropertyLabelProvider;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

@SuppressWarnings("all")
public class TorneoWindow extends SimpleWindow<TorneoModel> {
  public TorneoWindow(final WindowOwner parent, final LoginModel model) {
    super(parent, new TorneoModel(model));
    this.setTitle("Liga Master");
  }
  
  @Override
  public void createMainTemplate(final Panel mainPanel) {
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    mainPanel.setLayout(_horizontalLayout);
    Panel _panel = new Panel(mainPanel);
    this.createTorneoPanel(_panel);
    Panel _panel_1 = new Panel(mainPanel);
    this.createFechaPanel(_panel_1);
    Panel _panel_2 = new Panel(mainPanel);
    this.createMenuPanel(_panel_2);
  }
  
  public void createMenuPanel(final Panel panel) {
    Button _button = new Button(panel);
    final Procedure1<Button> _function = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Crear");
        it.setFontSize(10);
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            _modelObject.addTorneo();
            TorneoModel _modelObject_1 = TorneoWindow.this.getModelObject();
            Torneo _torneoON = _modelObject_1.getTorneoON();
            TorneoConfigWindow _torneoConfigWindow = new TorneoConfigWindow(TorneoWindow.this, _torneoON);
            _torneoConfigWindow.open();
          }
        };
        it.onClick(_function);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
        it.setWidth(150);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function);
    Button _button_1 = new Button(panel);
    final Procedure1<Button> _function_1 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Eliminar");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            _modelObject.removeTorneo();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_1);
    Button _button_2 = new Button(panel);
    final Procedure1<Button> _function_2 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Editar");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            Torneo _torneoON = _modelObject.getTorneoON();
            TorneoConfigWindow _torneoConfigWindow = new TorneoConfigWindow(TorneoWindow.this, _torneoON);
            _torneoConfigWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_2, _function_2);
    Button _button_3 = new Button(panel);
    final Procedure1<Button> _function_3 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Sortear Fixture");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            _modelObject.sortearFechas();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_3, _function_3);
    Button _button_4 = new Button(panel);
    final Procedure1<Button> _function_4 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Terminar Torneo");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            _modelObject.terminarTorneo();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_4, _function_4);
    Table<DT> _table = new Table<DT>(panel, DT.class);
    final Procedure1<Table<DT>> _function_5 = new Procedure1<Table<DT>>() {
      @Override
      public void apply(final Table<DT> it) {
        it.bindItemsToProperty("dts");
        it.setNumberVisibleRows(15);
        Column<DT> _column = new Column<DT>(it);
        final Procedure1<Column<DT>> _function = new Procedure1<Column<DT>>() {
          @Override
          public void apply(final Column<DT> it) {
            it.setTitle("DT");
            it.bindContentsToProperty("nombreDT");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<DT>>operator_doubleArrow(_column, _function);
        Column<DT> _column_1 = new Column<DT>(it);
        final Procedure1<Column<DT>> _function_1 = new Procedure1<Column<DT>>() {
          @Override
          public void apply(final Column<DT> it) {
            it.setTitle("Plata");
            it.bindContentsToProperty("plata");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<DT>>operator_doubleArrow(_column_1, _function_1);
        Column<DT> _column_2 = new Column<DT>(it);
        final Procedure1<Column<DT>> _function_2 = new Procedure1<Column<DT>>() {
          @Override
          public void apply(final Column<DT> it) {
            it.setTitle("Jugadores");
            it.bindContentsToProperty("cantJugadores");
            it.setFixedSize(40);
          }
        };
        ObjectExtensions.<Column<DT>>operator_doubleArrow(_column_2, _function_2);
        Column<DT> _column_3 = new Column<DT>(it);
        final Procedure1<Column<DT>> _function_3 = new Procedure1<Column<DT>>() {
          @Override
          public void apply(final Column<DT> it) {
            it.setTitle("Slots");
            it.bindContentsToProperty("slots");
            it.setFixedSize(40);
          }
        };
        ObjectExtensions.<Column<DT>>operator_doubleArrow(_column_3, _function_3);
      }
    };
    ObjectExtensions.<Table<DT>>operator_doubleArrow(_table, _function_5);
  }
  
  public void createFechaPanel(final Panel panel) {
    Panel _panel = new Panel(panel);
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    final Panel selectorPanel = _panel.setLayout(_horizontalLayout);
    LabeledSelector<Object> _labeledSelector = new LabeledSelector<Object>(selectorPanel);
    final Procedure1<LabeledSelector<Object>> _function = new Procedure1<LabeledSelector<Object>>() {
      @Override
      public void apply(final LabeledSelector<Object> it) {
        it.setText("Torneo: ");
        Label _label = it.getLabel();
        _label.setFontSize(12);
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("listaTorneos");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(Torneo.class, "nombreTorneo");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.bindValueToProperty("torneoON");
        it.setWidth(80);
      }
    };
    ObjectExtensions.<LabeledSelector<Object>>operator_doubleArrow(_labeledSelector, _function);
    LabeledSelector<Object> _labeledSelector_1 = new LabeledSelector<Object>(selectorPanel);
    final Procedure1<LabeledSelector<Object>> _function_1 = new Procedure1<LabeledSelector<Object>>() {
      @Override
      public void apply(final LabeledSelector<Object> it) {
        it.setText("Fecha: ");
        Label _label = it.getLabel();
        _label.setFontSize(12);
        it.bindItemsToProperty("listaFechas");
        it.bindValueToProperty("fechaON");
      }
    };
    ObjectExtensions.<LabeledSelector<Object>>operator_doubleArrow(_labeledSelector_1, _function_1);
    Table<Partido> _table = new Table<Partido>(panel, Partido.class);
    final Procedure1<Table<Partido>> _function_2 = new Procedure1<Table<Partido>>() {
      @Override
      public void apply(final Table<Partido> it) {
        it.bindItemsToProperty("fecha");
        it.<Object, ControlBuilder>bindValueToProperty("partidoON");
        it.setNumberVisibleRows(8);
        Column<Partido> _column = new Column<Partido>(it);
        final Procedure1<Column<Partido>> _function = new Procedure1<Column<Partido>>() {
          @Override
          public void apply(final Column<Partido> it) {
            it.setTitle("Local");
            it.bindContentsToProperty("dtLocal.nombreDT");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<Partido>>operator_doubleArrow(_column, _function);
        Column<Partido> _column_1 = new Column<Partido>(it);
        final Procedure1<Column<Partido>> _function_1 = new Procedure1<Column<Partido>>() {
          @Override
          public void apply(final Column<Partido> it) {
            it.bindContentsToProperty("score");
            it.setFixedSize(40);
          }
        };
        ObjectExtensions.<Column<Partido>>operator_doubleArrow(_column_1, _function_1);
        Column<Partido> _column_2 = new Column<Partido>(it);
        final Procedure1<Column<Partido>> _function_2 = new Procedure1<Column<Partido>>() {
          @Override
          public void apply(final Column<Partido> it) {
            it.setTitle("Visitante");
            it.bindContentsToProperty("dtVisitante.nombreDT");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<Partido>>operator_doubleArrow(_column_2, _function_2);
        Column<Partido> _column_3 = new Column<Partido>(it);
        final Procedure1<Column<Partido>> _function_3 = new Procedure1<Column<Partido>>() {
          @Override
          public void apply(final Column<Partido> it) {
            it.setTitle("Terminado");
            PropertyLabelProvider<Partido> _bindContentsToProperty = it.bindContentsToProperty("terminado");
            final Transformer<Boolean, String> _function = new Transformer<Boolean, String>() {
              @Override
              public String transform(final Boolean terminado) {
                String _xifexpression = null;
                if ((terminado).booleanValue()) {
                  _xifexpression = "Si";
                } else {
                  _xifexpression = "No";
                }
                return _xifexpression;
              }
            };
            _bindContentsToProperty.setTransformer(_function);
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<Partido>>operator_doubleArrow(_column_3, _function_3);
      }
    };
    ObjectExtensions.<Table<Partido>>operator_doubleArrow(_table, _function_2);
    Panel _panel_1 = new Panel(panel);
    HorizontalLayout _horizontalLayout_1 = new HorizontalLayout();
    final Panel buttonPanel = _panel_1.setLayout(_horizontalLayout_1);
    Button _button = new Button(buttonPanel);
    final Procedure1<Button> _function_3 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Ver Partido");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            PartidoWindow _partidoWindow = new PartidoWindow(TorneoWindow.this, _modelObject);
            _partidoWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function_3);
    Button _button_1 = new Button(buttonPanel);
    final Procedure1<Button> _function_4 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Terminar Partido");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            _modelObject.terminarPartido();
          }
        };
        it.onClick(_function);
        it.<ControlBuilder>bindEnabledToProperty("esMaster");
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_4);
  }
  
  public void createTorneoPanel(final Panel panel) {
    Button _button = new Button(panel);
    final Procedure1<Button> _function = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Equipos");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            EquipoWindow _equipoWindow = new EquipoWindow(TorneoWindow.this, _modelObject);
            _equipoWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
        it.setWidth(150);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function);
    Button _button_1 = new Button(panel);
    final Procedure1<Button> _function_1 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Reglas");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            DT _dtON = _modelObject.getDtON();
            ReglasWindow _reglasWindow = new ReglasWindow(TorneoWindow.this, _dtON);
            _reglasWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_1);
    Button _button_2 = new Button(panel);
    final Procedure1<Button> _function_2 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Premios");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            PremiosTorneoWindow _premiosTorneoWindow = new PremiosTorneoWindow(TorneoWindow.this, _modelObject);
            _premiosTorneoWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_2, _function_2);
    Button _button_3 = new Button(panel);
    final Procedure1<Button> _function_3 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Estadísticas");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            Torneo _torneoON = _modelObject.getTorneoON();
            EstadisticasWindow _estadisticasWindow = new EstadisticasWindow(TorneoWindow.this, _torneoON);
            _estadisticasWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_3, _function_3);
    Button _button_4 = new Button(panel);
    final Procedure1<Button> _function_4 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Historial");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            DT _dtON = _modelObject.getDtON();
            HistorialWindow _historialWindow = new HistorialWindow(TorneoWindow.this, _dtON);
            _historialWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_4, _function_4);
    Button _button_5 = new Button(panel);
    final Procedure1<Button> _function_5 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("GrondoRanking");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            DT _dtON = _modelObject.getDtON();
            RankWindow _rankWindow = new RankWindow(TorneoWindow.this, _dtON);
            _rankWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_5, _function_5);
    Button _button_6 = new Button(panel);
    final Procedure1<Button> _function_6 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Traspasos Hechos");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            DT _dtON = _modelObject.getDtON();
            TransferWindow _transferWindow = new TransferWindow(TorneoWindow.this, _dtON);
            _transferWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_6, _function_6);
    Button _button_7 = new Button(panel);
    final Procedure1<Button> _function_7 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Opciones");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            DT _dtON = _modelObject.getDtON();
            OpcionesWindow _opcionesWindow = new OpcionesWindow(TorneoWindow.this, _dtON);
            _opcionesWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_7, _function_7);
    Button _button_8 = new Button(panel);
    final Procedure1<Button> _function_8 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Update");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            _modelObject.update();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_8, _function_8);
    Button _button_9 = new Button(panel);
    final Procedure1<Button> _function_9 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Salir");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoModel _modelObject = TorneoWindow.this.getModelObject();
            _modelObject.guardar();
            TorneoWindow.this.close();
            LoginWindow _loginWindow = new LoginWindow(TorneoWindow.this);
            _loginWindow.open();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_9, _function_9);
  }
  
  @Override
  protected void addActions(final Panel actionsPanel) {
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
