package arena.components;

import arena.components.AbstractLabeledWidget;
import org.uqbar.arena.widgets.Container;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.Widget;
import org.uqbar.lacar.ui.model.ControlBuilder;

@SuppressWarnings("all")
public class LabeledTextBox extends AbstractLabeledWidget {
  private TextBox textBox;
  
  public LabeledTextBox(final Container container) {
    super(container);
    Label _label = this.getLabel();
    _label.setFontSize(12);
    this.textBox.setFontSize(10);
  }
  
  @Override
  public LabeledTextBox setWidth(final int ancho) {
    LabeledTextBox _xblockexpression = null;
    {
      this.textBox.setWidth(ancho);
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
  
  @Override
  public Widget createWidget(final AbstractLabeledWidget widget) {
    LabeledTextBox _xblockexpression = null;
    {
      TextBox _textBox = new TextBox(this);
      this.textBox = _textBox;
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
  
  @Override
  public AbstractLabeledWidget bindValueToProperty(final String property) {
    LabeledTextBox _xblockexpression = null;
    {
      this.textBox.<Object, ControlBuilder>bindValueToProperty(property);
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
  
  public LabeledTextBox setEnabled(final String property) {
    LabeledTextBox _xblockexpression = null;
    {
      this.textBox.<ControlBuilder>bindEnabledToProperty(property);
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
  
  public void bindTextToProperty(final String property) {
    Label _label = this.getLabel();
    _label.<Object, ControlBuilder>bindValueToProperty(property);
  }
}
