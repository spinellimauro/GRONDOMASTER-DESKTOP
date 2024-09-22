package arena.models;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import master.DT;
import master.LigaMaster;
import master.Torneo;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class TorneoConfigModel {
  private Torneo torneoON;
  
  private DT dtON;
  
  private String textoTorneo = "";
  
  public TorneoConfigModel(final Torneo model) {
    this.torneoON = model;
    String _nombreTorneo = model.getNombreTorneo();
    this.textoTorneo = _nombreTorneo;
  }
  
  public void setTextoTorneo(final String string) {
    this.torneoON.setNombreTorneo(string);
    this.textoTorneo = string;
    this.guardar();
  }
  
  public List<DT> getListaDT() {
    ArrayList<DT> _newArrayList = CollectionLiterals.<DT>newArrayList();
    final Procedure1<ArrayList<DT>> _function = new Procedure1<ArrayList<DT>>() {
      @Override
      public void apply(final ArrayList<DT> it) {
        LigaMaster _instance = LigaMaster.getInstance();
        List<DT> _listaDT = _instance.getListaDT();
        final Function1<DT, String> _function = new Function1<DT, String>() {
          @Override
          public String apply(final DT it) {
            return it.getNombreDT();
          }
        };
        List<DT> _sortBy = IterableExtensions.<DT, String>sortBy(_listaDT, _function);
        it.addAll(_sortBy);
        List<DT> _listaParticipantes = TorneoConfigModel.this.torneoON.getListaParticipantes();
        it.removeAll(_listaParticipantes);
      }
    };
    return ObjectExtensions.<ArrayList<DT>>operator_doubleArrow(_newArrayList, _function);
  }
  
  public void addDT() {
    boolean _equals = Objects.equal(this.dtON, null);
    if (_equals) {
      throw new UserException("Debe seleccionar un DT de la Lista");
    }
    List<DT> _listaParticipantes = this.torneoON.getListaParticipantes();
    boolean _contains = _listaParticipantes.contains(this.dtON);
    if (_contains) {
      throw new UserException("El DT ya está en el Torneo");
    }
    this.dtON.decTorneos();
    List<DT> _listaParticipantes_1 = this.torneoON.getListaParticipantes();
    _listaParticipantes_1.add(this.dtON);
    ObservableUtils.firePropertyChanged(this, "listaDT");
    this.guardar();
  }
  
  public void removeDT() {
    boolean _equals = Objects.equal(this.dtON, null);
    if (_equals) {
      throw new UserException("Debe seleccionar un DT de la Lista");
    }
    this.dtON.incTorneos();
    List<DT> _listaParticipantes = this.torneoON.getListaParticipantes();
    _listaParticipantes.remove(this.dtON);
    ObservableUtils.firePropertyChanged(this, "listaDT");
    this.guardar();
  }
  
  public void guardar() {
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.guardarBase();
  }
  
  @Pure
  public Torneo getTorneoON() {
    return this.torneoON;
  }
  
  public void setTorneoON(final Torneo torneoON) {
    this.torneoON = torneoON;
  }
  
  @Pure
  public DT getDtON() {
    return this.dtON;
  }
  
  public void setDtON(final DT dtON) {
    this.dtON = dtON;
  }
  
  @Pure
  public String getTextoTorneo() {
    return this.textoTorneo;
  }
}
