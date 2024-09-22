package arena.models;

import arena.models.EquipoModel;
import datos.Mercado;
import java.util.List;
import java.util.Set;
import master.DT;
import master.Jugador;
import master.LigaMaster;
import master.Oferta;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class CrearOfertaModel {
  private Oferta oferta = new Oferta();
  
  private Jugador jugadorON;
  
  private double montoOfertado = 0.0;
  
  private Set<Jugador> listaJugadores = CollectionLiterals.<Jugador>newHashSet();
  
  public CrearOfertaModel(final EquipoModel equipoModel) {
    DT _dtActivo = equipoModel.getDtActivo();
    this.oferta.setDtOfertante(_dtActivo);
    Jugador _jugadorON = equipoModel.getJugadorON();
    this.oferta.setJugadorOfertado(_jugadorON);
    Jugador _jugadorON_1 = equipoModel.getJugadorON();
    DT _propietario = _jugadorON_1.getPropietario();
    this.oferta.setDtReceptor(_propietario);
  }
  
  public void addJugador() {
    this.listaJugadores.add(this.jugadorON);
  }
  
  public void removeJugador() {
    this.listaJugadores.remove(this.jugadorON);
  }
  
  public void enviarOferta() {
    this.oferta.setMonto(this.montoOfertado);
    List<Jugador> _jugadoresOfrecidos = this.oferta.getJugadoresOfrecidos();
    _jugadoresOfrecidos.addAll(this.listaJugadores);
    LigaMaster _instance = LigaMaster.getInstance();
    Mercado _mercado = _instance.getMercado();
    List<Oferta> _listaOfertas = _mercado.getListaOfertas();
    _listaOfertas.add(this.oferta);
    this.guardar();
  }
  
  public void guardar() {
    LigaMaster _instance = LigaMaster.getInstance();
    _instance.guardarBase();
  }
  
  @Pure
  public Oferta getOferta() {
    return this.oferta;
  }
  
  public void setOferta(final Oferta oferta) {
    this.oferta = oferta;
  }
  
  @Pure
  public Jugador getJugadorON() {
    return this.jugadorON;
  }
  
  public void setJugadorON(final Jugador jugadorON) {
    this.jugadorON = jugadorON;
  }
  
  @Pure
  public double getMontoOfertado() {
    return this.montoOfertado;
  }
  
  public void setMontoOfertado(final double montoOfertado) {
    this.montoOfertado = montoOfertado;
  }
  
  @Pure
  public Set<Jugador> getListaJugadores() {
    return this.listaJugadores;
  }
  
  public void setListaJugadores(final Set<Jugador> listaJugadores) {
    this.listaJugadores = listaJugadores;
  }
}
