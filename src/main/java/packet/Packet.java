package packet;

public class Packet {
  private String data;
  private long id;
  private int length;

  public Packet(String data, long id, int length) {
    this.data = data;
    this.id = id;
    this.length = length;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }
}
