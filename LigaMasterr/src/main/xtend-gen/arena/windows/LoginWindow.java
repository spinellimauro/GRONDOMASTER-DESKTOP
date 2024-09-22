package arena.windows;

import arena.components.LabeledTextBox;
import arena.models.LoginModel;
import arena.windows.TorneoWindow;
import master.DT;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.PasswordField;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

@SuppressWarnings("all")
public class LoginWindow extends Dialog<LoginModel> {
  public LoginWindow(final WindowOwner parent) {
    super(parent, new LoginModel());
    this.setTitle("Login");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    Label _label = new Label(panel);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("LOGIN");
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    Selector<Object> _selector = new Selector<Object>(panel);
    final Procedure1<Selector<Object>> _function_1 = new Procedure1<Selector<Object>>() {
      @Override
      public void apply(final Selector<Object> it) {
        Binding<?, Selector<Object>, ListBuilder<Object>> _bindItemsToProperty = it.bindItemsToProperty("listaDT");
        PropertyAdapter _propertyAdapter = new PropertyAdapter(DT.class, "nombreDT");
        _bindItemsToProperty.setAdapter(_propertyAdapter);
        it.<Object, ControlBuilder>bindValueToProperty("dtON");
        it.setWidth(100);
      }
    };
    ObjectExtensions.<Selector<Object>>operator_doubleArrow(_selector, _function_1);
    PasswordField _passwordField = new PasswordField(panel);
    final Procedure1<PasswordField> _function_2 = new Procedure1<PasswordField>() {
      @Override
      public void apply(final PasswordField it) {
        it.<Object, ControlBuilder>bindValueToProperty("dtPassword");
      }
    };
    ObjectExtensions.<PasswordField>operator_doubleArrow(_passwordField, _function_2);
    Button _button = new Button(panel);
    final Procedure1<Button> _function_3 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Entrar");
        final Action _function = new Action() {
          @Override
          public void execute() {
            LoginModel _modelObject = LoginWindow.this.getModelObject();
            boolean _validar = _modelObject.validar();
            if (_validar) {
              LoginWindow.this.close();
              LoginModel _modelObject_1 = LoginWindow.this.getModelObject();
              TorneoWindow _torneoWindow = new TorneoWindow(LoginWindow.this, _modelObject_1);
              _torneoWindow.open();
            }
          }
        };
        it.onClick(_function);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function_3);
    LabeledTextBox _labeledTextBox = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function_4 = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.setText("Nombre: ");
        it.bindValueToProperty("dtNuevo");
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox, _function_4);
    LabeledTextBox _labeledTextBox_1 = new LabeledTextBox(panel);
    final Procedure1<LabeledTextBox> _function_5 = new Procedure1<LabeledTextBox>() {
      @Override
      public void apply(final LabeledTextBox it) {
        it.setText("Equipo: ");
        it.bindValueToProperty("dtEquipo");
      }
    };
    ObjectExtensions.<LabeledTextBox>operator_doubleArrow(_labeledTextBox_1, _function_5);
    Button _button_1 = new Button(panel);
    final Procedure1<Button> _function_6 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("CrearDT");
        final Action _function = new Action() {
          @Override
          public void execute() {
            LoginModel _modelObject = LoginWindow.this.getModelObject();
            _modelObject.addDT();
          }
        };
        it.onClick(_function);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_6);
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
