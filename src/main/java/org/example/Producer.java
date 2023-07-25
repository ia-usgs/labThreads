package org.example;

import java.util.Random;
import org.example.Buffer;
public class Producer extends Thread {
    private Buffer buffer;
    private Random random;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String data = "Packet_" + System.currentTimeMillis();
                Packet packet = new Packet(data);
                buffer.insertPkt(packet);
                System.out.println("Produced: " + data);
                Thread.sleep(random.nextInt(5000)); // Sleep for a random time (more than consumers)
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
