package arena.windows;

import arena.components.LabeledTextBox;
import arena.models.TorneoConfigModel;
import arena.windows.ImpuestosWindow;
import master.DT;
import master.Torneo;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

@SuppressWarnings("all")
public class TorneoConfigWindow extends Dialog<TorneoConfigModel> {
  public TorneoConfigWindow(final WindowOwner owner, final Torneo model) {
    super(owner, new TorneoConfigModel(model));
    this.setTitle("Configurar Torneo");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    LabeledTextBox _labeledTextBox = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.setText("Nombre: ");
        it.bindValueToProperty("textoTorneo");
        it.setWidth(100);
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox, _function);
    Panel _panel = new Panel(panel);
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    final Panel panelHorizontal = _panel.setLayout(_horizontalLayout);
    Panel _panel_1 = new Panel(panelHorizontal);
    this.createParticipantesPanel(_panel_1);
    Panel _panel_2 = new Panel(panelHorizontal);
    this.createTransferPanel(_panel_2);
    Panel _panel_3 = new Panel(panelHorizontal);
    this.createDTPanel(_panel_3);
  }
  
  public void createDTPanel(final Panel panel) {
    Label _label = new Label(panel);
    _label.setText("Disponibles");
    List<Object> _list = new List<Object>(panel);
    final Procedure1<List<Object>> _function = new Procedure1<List<Object>>() {
      @Override
      public void apply(final List<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("listaDT");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(DT.class, "nombreDT");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.<Object, ControlBuilder>bindValueToProperty("dtON");
        it.setHeight(100);
      }
    };
    ObjectExtensions.<List<Object>>operator_doubleArrow(_list, _function);
  }
  
  public void createTransferPanel(final Panel panel) {
    Label _label = new Label(panel);
    _label.setText("\n\n\n");
    Button _button = new Button(panel);
    final Procedure1<Button> _function = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("<=");
        final Action _function = new Action() {
          @Override
          public void execute() {
            TorneoConfigModel _modelObject = TorneoConfigWindow.this.getModelObject();
            DT _dtON = _modelObject.getDtON();
            boolean _pagaImpuesto = _dtON.getPagaImpuesto();
            if (_pagaImpuesto) {
              TorneoConfigModel _modelObject_1 = TorneoConfigWindow.this.getModelObject();
              ImpuestosWindow _impuestosWindow = new ImpuestosWindow(TorneoConfigWindow.this, _modelObject_1);
              _impuestosWindow.open();
            } else {
              TorneoConfigModel _modelObject_2 = TorneoConfigWindow.this.getModelObject();
              _modelObject_2.addDT();
            }
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
            TorneoConfigModel _modelObject = TorneoConfigWindow.this.getModelObject();
            _modelObject.removeDT();
          }
        };
        it.onClick(_function);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_1);
  }
  
  public void createParticipantesPanel(final Panel panel) {
    Label _label = new Label(panel);
    _label.setText("Participantes");
    List<Object> _list = new List<Object>(panel);
    final Procedure1<List<Object>> _function = new Procedure1<List<Object>>() {
      @Override
      public void apply(final List<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("torneoON.listaParticipantes");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(DT.class, "nombreDT");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.<Object, ControlBuilder>bindValueToProperty("dtON");
        it.setHeight(100);
      }
    };
    ObjectExtensions.<List<Object>>operator_doubleArrow(_list, _function);
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
