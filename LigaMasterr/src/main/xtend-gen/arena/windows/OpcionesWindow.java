package arena.windows;

import arena.models.Opciones;
import master.DT;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.PasswordField;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;

@SuppressWarnings("all")
public class OpcionesWindow extends Dialog<Opciones> {
  public OpcionesWindow(final WindowOwner parent, final DT model) {
    super(parent, new Opciones(model));
    this.setTitle("Historial");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    Label _label = new Label(panel);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Contraseña Actual: ");
        it.setWidth(95);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    PasswordField _passwordField = new PasswordField(panel);
    final Procedure1<PasswordField> _function_1 = new Procedure1<PasswordField>() {
      @Override
      public void apply(final PasswordField it) {
        it.<Object, ControlBuilder>bindValueToProperty("passwordON");
        it.setWidth(95);
      }
    };
    ObjectExtensions.<PasswordField>operator_doubleArrow(_passwordField, _function_1);
    Label _label_1 = new Label(panel);
    final Procedure1<Label> _function_2 = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Nueva Contraseña: ");
        it.setWidth(95);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label_1, _function_2);
    PasswordField _passwordField_1 = new PasswordField(panel);
    final Procedure1<PasswordField> _function_3 = new Procedure1<PasswordField>() {
      @Override
      public void apply(final PasswordField it) {
        it.<Object, ControlBuilder>bindValueToProperty("nuevaPassword");
        it.setWidth(95);
      }
    };
    ObjectExtensions.<PasswordField>operator_doubleArrow(_passwordField_1, _function_3);
    Button _button = new Button(panel);
    final Procedure1<Button> _function_4 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Cambiar Contraseña");
        final Action _function = new Action() {
          @Override
          public void execute() {
            Opciones _modelObject = OpcionesWindow.this.getModelObject();
            _modelObject.cambiarContraseña();
            OpcionesWindow.this.close();
          }
        };
        it.onClick(_function);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function_4);
  }
  
  @Override
  protected void addActions(final Panel actionsPanel) {
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
