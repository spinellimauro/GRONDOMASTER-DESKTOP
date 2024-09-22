package controller;

@SuppressWarnings("all")
public class Greeting {
  private long id;
  
  private String content;
  
  public Greeting() {
  }
  
  public String Greeting(final long id, final String content) {
    String _xblockexpression = null;
    {
      this.id = id;
      _xblockexpression = this.content = content;
    }
    return _xblockexpression;
  }
  
  public long getId() {
    return this.id;
  }
  
  public String getContent() {
    return this.content;
  }
  
  public void setContent(final String content) {
    this.content = content;
  }
}
