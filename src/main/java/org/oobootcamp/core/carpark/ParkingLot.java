package org.oobootcamp.core.carpark;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<Ticket, Car> ticketCarMap;
    private final int capacity;

    private int leftCapacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.ticketCarMap = new HashMap<>(capacity);
        this.leftCapacity = capacity;
    }

    public Ticket park(Car car) throws FullParkException {
        if (isFull()) throw new FullParkException();
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        leftCapacity = capacity - ticketCarMap.size();
        return ticket;
    }

    public boolean isFull() {
        return ticketCarMap.size() == capacity;
    }

    public boolean containsCar(Ticket ticket) {
        return ticketCarMap.containsKey(ticket);
    }

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        if (containsCar(ticket)) {
            Car car = ticketCarMap.get(ticket);
            ticketCarMap.remove(ticket, car);
            return car;
        }
        throw new InvalidTicketException();
    }

    public int getLeftCapacity() {
        return leftCapacity;
    }
}
