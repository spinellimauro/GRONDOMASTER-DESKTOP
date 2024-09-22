package arena.components;

import arena.components.AbstractLabeledWidget;
import org.uqbar.arena.widgets.Container;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Widget;
import org.uqbar.lacar.ui.model.ControlBuilder;

@SuppressWarnings("all")
public class LabeledNumericField extends AbstractLabeledWidget {
  private NumericField numericField;
  
  public LabeledNumericField(final Container container) {
    super(container);
    Label _label = this.getLabel();
    _label.setFontSize(12);
    this.numericField.setFontSize(10);
  }
  
  @Override
  public AbstractLabeledWidget bindValueToProperty(final String property) {
    LabeledNumericField _xblockexpression = null;
    {
      this.numericField.<Object, ControlBuilder>bindValueToProperty(property);
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
  
  public LabeledNumericField bindEnabledToProperty(final String property) {
    LabeledNumericField _xblockexpression = null;
    {
      this.numericField.<ControlBuilder>bindEnabledToProperty(property);
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
  
  @Override
  public Panel setWidth(final int ancho) {
    LabeledNumericField _xblockexpression = null;
    {
      this.numericField.setWidth(ancho);
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
  
  @Override
  public Widget createWidget(final AbstractLabeledWidget widget) {
    LabeledNumericField _xblockexpression = null;
    {
      NumericField _numericField = new NumericField(this);
      this.numericField = _numericField;
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
}
