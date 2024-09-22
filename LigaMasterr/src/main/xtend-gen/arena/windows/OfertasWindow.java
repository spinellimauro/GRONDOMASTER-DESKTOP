package arena.windows;

import arena.models.OfertasModel;
import java.util.List;
import master.DT;
import master.Jugador;
import master.Oferta;
import org.apache.commons.collections15.Transformer;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.widgets.tables.labelprovider.PropertyLabelProvider;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;

@SuppressWarnings("all")
public class OfertasWindow extends Dialog<OfertasModel> {
  public OfertasWindow(final WindowOwner parent, final DT model) {
    super(parent, new OfertasModel(model));
    this.setTitle("Ofertas");
  }
  
  @Override
  public void createMainTemplate(final Panel panel) {
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    panel.setLayout(_horizontalLayout);
    Panel _panel = new Panel(panel);
    this.createRecibidosPanel(_panel);
    Panel _panel_1 = new Panel(panel);
    this.createEnviadosPanel(_panel_1);
  }
  
  public void createRecibidosPanel(final Panel panel) {
    Label _label = new Label(panel);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Recibidas");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    Table<Oferta> _table = new Table<Oferta>(panel, Oferta.class);
    final Procedure1<Table<Oferta>> _function_1 = new Procedure1<Table<Oferta>>() {
      @Override
      public void apply(final Table<Oferta> it) {
        it.bindItemsToProperty("ofertasRecibidas");
        it.<Object, ControlBuilder>bindValueToProperty("ofertaON");
        it.setNumberVisibleRows(8);
        Column<Oferta> _column = new Column<Oferta>(it);
        final Procedure1<Column<Oferta>> _function = new Procedure1<Column<Oferta>>() {
          @Override
          public void apply(final Column<Oferta> it) {
            it.setTitle("Ofertante");
            it.bindContentsToProperty("dtOfertante.nombreDT");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<Oferta>>operator_doubleArrow(_column, _function);
        Column<Oferta> _column_1 = new Column<Oferta>(it);
        final Procedure1<Column<Oferta>> _function_1 = new Procedure1<Column<Oferta>>() {
          @Override
          public void apply(final Column<Oferta> it) {
            it.setTitle("Monto");
            it.bindContentsToProperty("monto");
            it.setFixedSize(65);
          }
        };
        ObjectExtensions.<Column<Oferta>>operator_doubleArrow(_column_1, _function_1);
        Column<Oferta> _column_2 = new Column<Oferta>(it);
        final Procedure1<Column<Oferta>> _function_2 = new Procedure1<Column<Oferta>>() {
          @Override
          public void apply(final Column<Oferta> it) {
            it.setTitle("Jugador");
            it.bindContentsToProperty("jugadorOfertado.nombre");
            it.setFixedSize(150);
          }
        };
        ObjectExtensions.<Column<Oferta>>operator_doubleArrow(_column_2, _function_2);
        Column<Oferta> _column_3 = new Column<Oferta>(it);
        final Procedure1<Column<Oferta>> _function_3 = new Procedure1<Column<Oferta>>() {
          @Override
          public void apply(final Column<Oferta> it) {
            it.setTitle("Jugadores Ofrecidos");
            PropertyLabelProvider<Oferta> _bindContentsToProperty = it.bindContentsToProperty("jugadoresOfrecidos");
            final Transformer<List<Jugador>, String> _function = new Transformer<List<Jugador>, String>() {
              @Override
              public String transform(final List<Jugador> jugadores) {
                final Function1<Jugador, String> _function = new Function1<Jugador, String>() {
                  @Override
                  public String apply(final Jugador it) {
                    return it.getNombre();
                  }
                };
                List<String> _map = ListExtensions.<Jugador, String>map(jugadores, _function);
                return _map.toString();
              }
            };
            _bindContentsToProperty.setTransformer(_function);
            it.setFixedSize(150);
          }
        };
        ObjectExtensions.<Column<Oferta>>operator_doubleArrow(_column_3, _function_3);
      }
    };
    ObjectExtensions.<Table<Oferta>>operator_doubleArrow(_table, _function_1);
    Panel _panel = new Panel(panel);
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    final Panel buttonPanel = _panel.setLayout(_horizontalLayout);
    Button _button = new Button(buttonPanel);
    final Procedure1<Button> _function_2 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Aceptar");
        final Action _function = new Action() {
          @Override
          public void execute() {
            OfertasModel _modelObject = OfertasWindow.this.getModelObject();
            _modelObject.aceptarOferta();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function_2);
    Button _button_1 = new Button(buttonPanel);
    final Procedure1<Button> _function_3 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Rechazar");
        final Action _function = new Action() {
          @Override
          public void execute() {
            OfertasModel _modelObject = OfertasWindow.this.getModelObject();
            _modelObject.rechazarOferta();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button_1, _function_3);
  }
  
  public void createEnviadosPanel(final Panel panel) {
    Label _label = new Label(panel);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Enviadas");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    Table<Oferta> _table = new Table<Oferta>(panel, Oferta.class);
    final Procedure1<Table<Oferta>> _function_1 = new Procedure1<Table<Oferta>>() {
      @Override
      public void apply(final Table<Oferta> it) {
        it.bindItemsToProperty("ofertasEnviadas");
        it.<Object, ControlBuilder>bindValueToProperty("ofertaON");
        it.setNumberVisibleRows(8);
        Column<Oferta> _column = new Column<Oferta>(it);
        final Procedure1<Column<Oferta>> _function = new Procedure1<Column<Oferta>>() {
          @Override
          public void apply(final Column<Oferta> it) {
            it.setTitle("Receptor");
            it.bindContentsToProperty("dtReceptor.nombreDT");
            it.setFixedSize(80);
          }
        };
        ObjectExtensions.<Column<Oferta>>operator_doubleArrow(_column, _function);
        Column<Oferta> _column_1 = new Column<Oferta>(it);
        final Procedure1<Column<Oferta>> _function_1 = new Procedure1<Column<Oferta>>() {
          @Override
          public void apply(final Column<Oferta> it) {
            it.setTitle("Monto");
            it.bindContentsToProperty("monto");
            it.setFixedSize(65);
          }
        };
        ObjectExtensions.<Column<Oferta>>operator_doubleArrow(_column_1, _function_1);
        Column<Oferta> _column_2 = new Column<Oferta>(it);
        final Procedure1<Column<Oferta>> _function_2 = new Procedure1<Column<Oferta>>() {
          @Override
          public void apply(final Column<Oferta> it) {
            it.setTitle("Jugador");
            it.bindContentsToProperty("jugadorOfertado.nombre");
            it.setFixedSize(150);
          }
        };
        ObjectExtensions.<Column<Oferta>>operator_doubleArrow(_column_2, _function_2);
        Column<Oferta> _column_3 = new Column<Oferta>(it);
        final Procedure1<Column<Oferta>> _function_3 = new Procedure1<Column<Oferta>>() {
          @Override
          public void apply(final Column<Oferta> it) {
            it.setTitle("Jugadores Ofrecidos");
            PropertyLabelProvider<Oferta> _bindContentsToProperty = it.bindContentsToProperty("jugadoresOfrecidos");
            final Transformer<List<Jugador>, String> _function = new Transformer<List<Jugador>, String>() {
              @Override
              public String transform(final List<Jugador> jugadores) {
                final Function1<Jugador, String> _function = new Function1<Jugador, String>() {
                  @Override
                  public String apply(final Jugador it) {
                    return it.getNombre();
                  }
                };
                List<String> _map = ListExtensions.<Jugador, String>map(jugadores, _function);
                return _map.toString();
              }
            };
            _bindContentsToProperty.setTransformer(_function);
            it.setFixedSize(150);
          }
        };
        ObjectExtensions.<Column<Oferta>>operator_doubleArrow(_column_3, _function_3);
      }
    };
    ObjectExtensions.<Table<Oferta>>operator_doubleArrow(_table, _function_1);
    Panel _panel = new Panel(panel);
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    final Panel buttonPanel = _panel.setLayout(_horizontalLayout);
    Button _button = new Button(buttonPanel);
    final Procedure1<Button> _function_2 = new Procedure1<Button>() {
      @Override
      public void apply(final Button it) {
        it.setCaption("Cancelar");
        final Action _function = new Action() {
          @Override
          public void execute() {
            OfertasModel _modelObject = OfertasWindow.this.getModelObject();
            _modelObject.rechazarOferta();
          }
        };
        it.onClick(_function);
        it.setFontSize(10);
      }
    };
    ObjectExtensions.<Button>operator_doubleArrow(_button, _function_2);
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
