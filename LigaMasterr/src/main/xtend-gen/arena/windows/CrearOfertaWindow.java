package arena.windows;

import arena.components.LabeledNumericField;
import arena.models.CrearOfertaModel;
import arena.models.EquipoModel;
import master.Jugador;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

@SuppressWarnings("all")
public class CrearOfertaWindow extends SimpleWindow<CrearOfertaModel> {
  public CrearOfertaWindow(final WindowOwner parent, final EquipoModel model) {
    super(parent, new CrearOfertaModel(model));
    this.setTitle("Nueva Oferta");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    Label _label = new Label(panel);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.<Object, ControlBuilder>bindValueToProperty("oferta.jugadorOfertado.nombre");
        it.setFontSize(15);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    LabeledNumericField _labeledNumericField = new LabeledNumericField(panel);
    final Procedure1<LabeledNumericField> _function_1 = new Procedure1<LabeledNumericField>() {
      @Override
      public void apply(final LabeledNumericField it) {
        it.setText("Monto: ");
        it.bindValueToProperty("montoOfertado");
        it.setWidth(100);
      }
    };
    ObjectExtensions.<LabeledNumericField>operator_doubleArrow(_labeledNumericField, _function_1);
    Label _label_1 = new Label(panel);
    final Procedure1<Label> _function_2 = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Jugadores a Intercambiar");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label_1, _function_2);
    Panel _panel = new Panel(panel);
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    final Panel truequePanel = _panel.setLayout(_horizontalLayout);
    List<Object> _list = new List<Object>(truequePanel);
    final Procedure1<List<Object>> _function_3 = new Procedure1<List<Object>>() {
      @Override
      public void apply(final List<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("oferta.dtOfertante.listaJugadores");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(Jugador.class, "nombre");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setWidth(85);
        it.setHeight(60);
      }
    };
    ObjectExtensions.<List<Object>>operator_doubleArrow(_list, _function_3);
    List<Object> _list_1 = new List<Object>(truequePanel);
    final Procedure1<List<Object>> _function_4 = new Procedure1<List<Object>>() {
      @Override
      public void apply(final List<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("listaJugadores");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(Jugador.class, "nombre");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.<Object, ControlBuilder>bindValueToProperty("jugadorON");
        it.setWidth(85);
        it.setHeight(60);
      }
    };
    ObjectExtensions.<List<Object>>operator_doubleArrow(_list_1, _function_4);
    Panel _panel_1 = new Panel(panel);
    HorizontalLayout _horizontalLayout_1 = new HorizontalLayout();
    final Panel buttonPanel = _panel_1.setLayout(_horizontalLayout_1);
    Label _label_2 = new Label(buttonPanel);
    _label_2.setText("\t       ");
    Button _button = new Button(buttonPanel);
    final Procedure1<Button> _function_5 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("+");
        final Action _function = new Action() {
          @Override
          public void execute() {
            CrearOfertaModel _modelObject = CrearOfertaWindow.this.getModelObject();
            _modelObject.addJugador();
          }
        };
        it.onClick(_function);
        it.setHeight(30);
        it.setWidth(30);
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
            CrearOfertaModel _modelObject = CrearOfertaWindow.this.getModelObject();
            _modelObject.removeJugador();
          }
        };
        it.onClick(_function);
        it.setHeight(30);
        it.setWidth(30);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_6);
    Button _button_2 = new Button(panel);
    final Procedure1<Button> _function_7 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Enviar Oferta");
        final Action _function = new Action() {
          @Override
          public void execute() {
            CrearOfertaWindow.this.close();
            CrearOfertaModel _modelObject = CrearOfertaWindow.this.getModelObject();
            _modelObject.enviarOferta();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_2, _function_7);
  }
  
  @Override
  protected void addActions(final Panel actionsPanel) {
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
