package arena.components;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Container;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Widget;

@Accessors
@SuppressWarnings("all")
public abstract class AbstractLabeledWidget extends Panel {
  private Label label;
  
  public AbstractLabeledWidget(final Container container) {
    super(container);
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    this.setLayout(_horizontalLayout);
    Label _label = new Label(this);
    this.label = _label;
    this.createWidget(this);
  }
  
  public AbstractLabeledWidget setText(final String text) {
    AbstractLabeledWidget _xblockexpression = null;
    {
      this.label.setText(text);
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
  
  public abstract Widget createWidget(final AbstractLabeledWidget widget);
  
  public abstract AbstractLabeledWidget bindValueToProperty(final String property);
  
  @Pure
  public Label getLabel() {
    return this.label;
  }
  
  public void setLabel(final Label label) {
    this.label = label;
  }
}
