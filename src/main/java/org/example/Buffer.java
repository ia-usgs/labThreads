package org.example;

import java.util.LinkedList;

public class Buffer {
    private LinkedList<Packet> packets;
    private int maxSize;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        packets = new LinkedList<>();
    }

    public synchronized <Packet> void insertPkt(Packet packet) throws InterruptedException {
        while (packets.size() >= maxSize) {
            wait();
        }
        packets.add((org.example.Packet) packet);
        notifyAll();
    }

    public synchronized Packet removePkt() throws InterruptedException {
        while (packets.isEmpty()) {
            wait();
        }
        Packet packet = packets.remove();
        notifyAll();
        return packet;
    }

    public synchronized int size() {
        return packets.size();
    }
}
