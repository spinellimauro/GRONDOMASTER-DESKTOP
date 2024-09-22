package datos;

import datos.Equipo;
import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.uqbar.commons.utils.Observable;

@Observable
@Accessors
@SuppressWarnings("all")
public final class SoFifa {
  private static SoFifa instance = new SoFifa();
  
  private ChromeOptions options;
  
  private List<WebElement> players;
  
  private WebDriver driver;
  
  private SoFifa() {
  }
  
  public void getJugadores(final String string) {
    try {
      InputOutput.<String>println("set driver");
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\Maurispi\\Documents\\GitHub\\GRONDOMASTER-DESKTOP\\LigaMasterr\\chromedriver.exe");
      InputOutput.<String>println("create driver");
      ChromeDriver _chromeDriver = new ChromeDriver();
      this.driver = _chromeDriver;
      InputOutput.<String>println("get driver");
      this.driver.get("https://sofifa.com/players?keyword=vini&hl=es-ES");
      InputOutput.<String>println("get url");
      Thread.sleep(5000);
      By _xpath = By.xpath("//td[@data-col=\'oa\']");
      List<WebElement> _findElements = this.driver.findElements(_xpath);
      this.players = _findElements;
      InputOutput.<String>println("get jugadores");
      for (final WebElement player : this.players) {
        String _text = player.getText();
        InputOutput.<String>println(_text);
      }
      this.driver.quit();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public List<Equipo> getEquipos(final String string) {
    try {
      List<Equipo> _xblockexpression = null;
      {
        Connection _connect = Jsoup.connect((("http://sofifa.com/teams?keyword=" + string) + "&hl=es-ES"));
        Connection _userAgent = _connect.userAgent("Mozilla");
        final Document document = _userAgent.post();
        final Elements tabla = document.select("tbody > tr > td > a[href*=team]");
        final Function1<Element, Equipo> _function = new Function1<Element, Equipo>() {
          @Override
          public Equipo apply(final Element it) {
            Equipo _xblockexpression = null;
            {
              final Equipo equipo = new Equipo();
              String _attr = it.attr("href");
              String _replaceAll = _attr.replaceAll("[^\\d-]", "");
              int _parseInt = Integer.parseInt(_replaceAll);
              equipo.setId(_parseInt);
              String _text = it.text();
              equipo.setNombre(_text);
              _xblockexpression = equipo;
            }
            return _xblockexpression;
          }
        };
        _xblockexpression = ListExtensions.<Element, Equipo>map(tabla, _function);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static SoFifa getInstance() {
    return SoFifa.instance;
  }
  
  @Pure
  public ChromeOptions getOptions() {
    return this.options;
  }
  
  public void setOptions(final ChromeOptions options) {
    this.options = options;
  }
  
  @Pure
  public List<WebElement> getPlayers() {
    return this.players;
  }
  
  public void setPlayers(final List<WebElement> players) {
    this.players = players;
  }
  
  @Pure
  public WebDriver getDriver() {
    return this.driver;
  }
  
  public void setDriver(final WebDriver driver) {
    this.driver = driver;
  }
}
