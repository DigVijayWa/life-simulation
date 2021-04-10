package packet;

import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

public class PacketHandler {

  private static Queue<Packet> packetQueue = new SynchronousQueue<>();

  public boolean addPacket(Packet packet) {
    return packetQueue.add(packet);
  }

  public Packet getPacket() {
    return packetQueue.remove();
  }

  public boolean isEmpty() {
    return packetQueue.isEmpty();
  }
}
