package packet;

public class Packet {
  private PlayerData data;
  private String id;
  private int length;
  private long correlationId;
  private String type;


  public Packet(PlayerData data, String id, int length, long correlationId, String type) {
    this.data = data;
    this.id = id;
    this.length = length;
    this.correlationId = correlationId;
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public PlayerData getData() {
    return data;
  }

  public void setData(PlayerData data) {
    this.data = data;
  }

  public long getCorrelationId() {
    return correlationId;
  }

  public void setCorrelationId(long correlationId) {
    this.correlationId = correlationId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  @Override
  public String toString() {
    return "{" +
        "\"data\":\"" + data + "\"" +
        ", \"id\":\"" + id + "\""+
        ", \"length\":" + length +
        ", \"correlationId\": \""+correlationId+"\""+
        '}';
  }
}
