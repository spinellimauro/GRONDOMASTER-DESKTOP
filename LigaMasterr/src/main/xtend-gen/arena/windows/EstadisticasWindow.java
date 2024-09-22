package arena.windows;

import arena.windows.EstadisticaDT;
import arena.windows.EstadisticaJugador;
import arena.windows.EstadisticasModel;
import master.Torneo;
import org.apache.commons.collections15.Transformer;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.widgets.tables.labelprovider.PropertyLabelProvider;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("all")
public class EstadisticasWindow extends Dialog<EstadisticasModel> {
  public EstadisticasWindow(final WindowOwner owner, final Torneo model) {
    super(owner, new EstadisticasModel(model));
    this.setTitle("Estadísticas");
  }
  
  @Override
  protected void createMainTemplate(final Panel panel) {
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    panel.setLayout(_horizontalLayout);
    final Panel panelPosiciones = new Panel(panel);
    Label _label = new Label(panelPosiciones);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Posiciones");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    Table<EstadisticaDT> _table = new Table<EstadisticaDT>(panelPosiciones, EstadisticaDT.class);
    final Procedure1<Table<EstadisticaDT>> _function_1 = new Procedure1<Table<EstadisticaDT>>() {
      @Override
      public void apply(final Table<EstadisticaDT> it) {
        it.bindItemsToProperty("listaPosiciones");
        it.setNumberVisibleRows(20);
        Column<EstadisticaDT> _column = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("Nombre");
            it.bindContentsToProperty("nombreDT");
            it.setFixedSize(85);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column, _function);
        Column<EstadisticaDT> _column_1 = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function_1 = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("PJ");
            it.bindContentsToProperty("partJugados");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column_1, _function_1);
        Column<EstadisticaDT> _column_2 = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function_2 = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("PG");
            it.bindContentsToProperty("partGanados");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column_2, _function_2);
        Column<EstadisticaDT> _column_3 = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function_3 = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("PE");
            it.bindContentsToProperty("partEmpatados");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column_3, _function_3);
        Column<EstadisticaDT> _column_4 = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function_4 = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("PP");
            it.bindContentsToProperty("partPerdidos");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column_4, _function_4);
        Column<EstadisticaDT> _column_5 = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function_5 = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("GF");
            it.bindContentsToProperty("golesFavor");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column_5, _function_5);
        Column<EstadisticaDT> _column_6 = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function_6 = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("GC");
            it.bindContentsToProperty("golesContra");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column_6, _function_6);
        Column<EstadisticaDT> _column_7 = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function_7 = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("DIF");
            PropertyLabelProvider<EstadisticaDT> _bindContentsToProperty = it.bindContentsToProperty("difGol");
            final Transformer<Integer, String> _function = new Transformer<Integer, String>() {
              @Override
              public String transform(final Integer dif) {
                String _xifexpression = null;
                if ((dif > 0)) {
                  _xifexpression = ("+" + Integer.valueOf(dif));
                } else {
                  _xifexpression = Integer.valueOf(dif).toString();
                }
                return _xifexpression;
              }
            };
            _bindContentsToProperty.setTransformer(_function);
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column_7, _function_7);
        Column<EstadisticaDT> _column_8 = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function_8 = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("Puntos");
            it.bindContentsToProperty("puntos");
            it.setFixedSize(55);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column_8, _function_8);
      }
    };
    ObjectExtensions.<Table<EstadisticaDT>>operator_doubleArrow(_table, _function_1);
    final Panel panelGoleadores = new Panel(panel);
    Label _label_1 = new Label(panelGoleadores);
    final Procedure1<Label> _function_2 = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Goleadores");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label_1, _function_2);
    Table<EstadisticaJugador> _table_1 = new Table<EstadisticaJugador>(panelGoleadores, EstadisticaJugador.class);
    final Procedure1<Table<EstadisticaJugador>> _function_3 = new Procedure1<Table<EstadisticaJugador>>() {
      @Override
      public void apply(final Table<EstadisticaJugador> it) {
        it.bindItemsToProperty("listaGoleadores");
        it.setNumberVisibleRows(20);
        Column<EstadisticaJugador> _column = new Column<EstadisticaJugador>(it);
        final Procedure1<Column<EstadisticaJugador>> _function = new Procedure1<Column<EstadisticaJugador>>() {
          @Override
          public void apply(final Column<EstadisticaJugador> it) {
            it.setTitle("Nombre");
            it.bindContentsToProperty("nombre");
            it.setFixedSize(140);
          }
        };
        ObjectExtensions.<Column<EstadisticaJugador>>operator_doubleArrow(_column, _function);
        Column<EstadisticaJugador> _column_1 = new Column<EstadisticaJugador>(it);
        final Procedure1<Column<EstadisticaJugador>> _function_1 = new Procedure1<Column<EstadisticaJugador>>() {
          @Override
          public void apply(final Column<EstadisticaJugador> it) {
            it.setTitle("Goles");
            it.bindContentsToProperty("goles");
            it.setFixedSize(45);
          }
        };
        ObjectExtensions.<Column<EstadisticaJugador>>operator_doubleArrow(_column_1, _function_1);
      }
    };
    ObjectExtensions.<Table<EstadisticaJugador>>operator_doubleArrow(_table_1, _function_3);
    final Panel panelFairPlay = new Panel(panel);
    Label _label_2 = new Label(panelFairPlay);
    final Procedure1<Label> _function_4 = new Procedure1<Label>() {
      @Override
      public void apply(final Label it) {
        it.setText("Fair Play");
        it.setFontSize(12);
      }
    };
    ObjectExtensions.<Label>operator_doubleArrow(_label_2, _function_4);
    Table<EstadisticaDT> _table_2 = new Table<EstadisticaDT>(panelFairPlay, EstadisticaDT.class);
    final Procedure1<Table<EstadisticaDT>> _function_5 = new Procedure1<Table<EstadisticaDT>>() {
      @Override
      public void apply(final Table<EstadisticaDT> it) {
        it.bindItemsToProperty("listaFairPlay");
        it.setNumberVisibleRows(20);
        Column<EstadisticaDT> _column = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("Nombre");
            it.bindContentsToProperty("nombreDT");
            it.setFixedSize(85);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column, _function);
        Column<EstadisticaDT> _column_1 = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function_1 = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("R");
            it.bindContentsToProperty("rojas");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column_1, _function_1);
        Column<EstadisticaDT> _column_2 = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function_2 = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("A");
            it.bindContentsToProperty("amarillas");
            it.setFixedSize(35);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column_2, _function_2);
        Column<EstadisticaDT> _column_3 = new Column<EstadisticaDT>(it);
        final Procedure1<Column<EstadisticaDT>> _function_3 = new Procedure1<Column<EstadisticaDT>>() {
          @Override
          public void apply(final Column<EstadisticaDT> it) {
            it.setTitle("Puntos");
            it.bindContentsToProperty("puntosFairPlay");
            it.setFixedSize(55);
          }
        };
        ObjectExtensions.<Column<EstadisticaDT>>operator_doubleArrow(_column_3, _function_3);
      }
    };
    ObjectExtensions.<Table<EstadisticaDT>>operator_doubleArrow(_table_2, _function_5);
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
  }
}
