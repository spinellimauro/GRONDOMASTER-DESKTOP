package arena.windows;

import arena.windows.LoginWindow;
import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

@SuppressWarnings("all")
public class LigaApplication extends Application {
  public static void main(final String[] args) {
    LigaApplication _ligaApplication = new LigaApplication();
    _ligaApplication.start();
  }
  
  @Override
  protected Window<?> createMainWindow() {
    return new LoginWindow(this);
  }
}
