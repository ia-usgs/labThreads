package org.example;

public class Consumer extends Thread {
    private Buffer buffer;
    private int consumerId;

    public Consumer(Buffer buffer, int consumerId) {
        this.buffer = buffer;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Packet packet = buffer.removePkt();
                System.out.println("Consumer " + consumerId + " consumed: " + packet.getData());
                Thread.sleep(1000); // Sleep for a fixed time (more than the producer)
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
