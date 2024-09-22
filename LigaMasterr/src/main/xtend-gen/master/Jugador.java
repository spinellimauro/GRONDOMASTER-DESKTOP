package master;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import datos.Precios;
import java.util.List;
import master.DT;
import master.LigaMaster;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public class Jugador {
  private int id = 0;
  
  private String nombre = "";
  
  private String nacionalidad = "";
  
  private List<String> posiciones = CollectionLiterals.<String>newArrayList();
  
  private int nivel = 0;
  
  private int potencial = 0;
  
  private int lesion = 0;
  
  private boolean habilitado = true;
  
  private double precioVenta = 0;
  
  private int vecesNoPagadas = 0;
  
  public double getImpuesto() {
    Precios _instance = Precios.getInstance();
    double _precio = _instance.getPrecio(this);
    Precios _instance_1 = Precios.getInstance();
    double _precio_1 = _instance_1.getPrecio("Impuesto");
    double _divide = (_precio_1 / 100);
    return (_precio * _divide);
  }
  
  public void noSePago() {
    this.vecesNoPagadas++;
    this.habilitado = false;
  }
  
  public void pagar() {
    this.vecesNoPagadas = 0;
    this.habilitado = true;
  }
  
  public boolean getPagaImpuesto() {
    return (this.nivel > 82);
  }
  
  @JsonProperty("precioMaquina")
  public double getPrecioMaquina() {
    Precios _instance = Precios.getInstance();
    return _instance.getPrecio(this);
  }
  
  public DT getPropietario() {
    LigaMaster _instance = LigaMaster.getInstance();
    return _instance.getPropietario(this);
  }
  
  @JsonProperty("propietario")
  public String getPropietarioNombre() {
    LigaMaster _instance = LigaMaster.getInstance();
    DT _propietario = _instance.getPropietario(this);
    return _propietario.getNombreDT();
  }
  
  public void update() {
    try {
      Connection _connect = Jsoup.connect(("http://sofifa.com/player/" + Integer.valueOf(this.id)));
      Connection _userAgent = _connect.userAgent("Mozilla");
      final Document instance = _userAgent.post();
      final Elements ratings = instance.select("section > div > div > span");
      Element _get = ratings.get(0);
      String _text = _get.text();
      int _parseInt = Integer.parseInt(_text);
      this.nivel = _parseInt;
      Element _get_1 = ratings.get(1);
      String _text_1 = _get_1.text();
      int _parseInt_1 = Integer.parseInt(_text_1);
      this.potencial = _parseInt_1;
      if ((this.potencial < 20)) {
        Element _get_2 = ratings.get(2);
        String _text_2 = _get_2.text();
        int _parseInt_2 = Integer.parseInt(_text_2);
        this.potencial = _parseInt_2;
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public boolean estaLesionado() {
    return (this.lesion > 0);
  }
  
  public void incLesion() {
    this.lesion++;
  }
  
  public void decLesion() {
    this.lesion--;
  }
  
  @Override
  public boolean equals(final Object obj) {
    boolean _xblockexpression = false;
    {
      boolean _equals = Objects.equal(obj, null);
      if (_equals) {
        return false;
      }
      Class<?> _class = obj.getClass();
      boolean _isAssignableFrom = Jugador.class.isAssignableFrom(_class);
      boolean _not = (!_isAssignableFrom);
      if (_not) {
        return false;
      }
      final Jugador otroJugador = ((Jugador) obj);
      _xblockexpression = (this.id == otroJugador.id);
    }
    return _xblockexpression;
  }
  
  @Override
  public int hashCode() {
    return this.id;
  }
  
  @Pure
  public int getId() {
    return this.id;
  }
  
  public void setId(final int id) {
    this.id = id;
  }
  
  @Pure
  public String getNombre() {
    return this.nombre;
  }
  
  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }
  
  @Pure
  public String getNacionalidad() {
    return this.nacionalidad;
  }
  
  public void setNacionalidad(final String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }
  
  @Pure
  public List<String> getPosiciones() {
    return this.posiciones;
  }
  
  public void setPosiciones(final List<String> posiciones) {
    this.posiciones = posiciones;
  }
  
  @Pure
  public int getNivel() {
    return this.nivel;
  }
  
  public void setNivel(final int nivel) {
    this.nivel = nivel;
  }
  
  @Pure
  public int getPotencial() {
    return this.potencial;
  }
  
  public void setPotencial(final int potencial) {
    this.potencial = potencial;
  }
  
  @Pure
  public int getLesion() {
    return this.lesion;
  }
  
  public void setLesion(final int lesion) {
    this.lesion = lesion;
  }
  
  @Pure
  public boolean isHabilitado() {
    return this.habilitado;
  }
  
  public void setHabilitado(final boolean habilitado) {
    this.habilitado = habilitado;
  }
  
  @Pure
  public double getPrecioVenta() {
    return this.precioVenta;
  }
  
  public void setPrecioVenta(final double precioVenta) {
    this.precioVenta = precioVenta;
  }
  
  @Pure
  public int getVecesNoPagadas() {
    return this.vecesNoPagadas;
  }
  
  public void setVecesNoPagadas(final int vecesNoPagadas) {
    this.vecesNoPagadas = vecesNoPagadas;
  }
}
