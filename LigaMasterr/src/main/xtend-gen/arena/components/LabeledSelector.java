package arena.components;

import arena.components.AbstractLabeledWidget;
import org.uqbar.arena.widgets.Container;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.Widget;
import org.uqbar.lacar.ui.model.ControlBuilder;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

@SuppressWarnings("all")
public class LabeledSelector<T extends Object> extends AbstractLabeledWidget {
  private Selector<T> selector;
  
  public LabeledSelector(final Container container) {
    super(container);
  }
  
  public Binding<?, Selector<T>, ListBuilder<T>> bindItemsToProperty(final String property) {
    return this.selector.bindItemsToProperty(property);
  }
  
  @Override
  public Widget createWidget(final AbstractLabeledWidget widget) {
    Selector<T> _selector = new Selector<T>(this);
    return this.selector = _selector;
  }
  
  @Override
  public AbstractLabeledWidget bindValueToProperty(final String property) {
    LabeledSelector<T> _xblockexpression = null;
    {
      this.selector.<Object, ControlBuilder>bindValueToProperty(property);
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
  
  @Override
  public Panel setWidth(final int integer) {
    LabeledSelector<T> _xblockexpression = null;
    {
      this.selector.setWidth(integer);
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
}
