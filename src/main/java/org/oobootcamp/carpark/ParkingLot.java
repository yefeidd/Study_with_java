package org.oobootcamp.carpark;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<Ticket, Car> ticketCarMap;
    private final int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.ticketCarMap = new HashMap<>(capacity);
    }

    public Ticket park(Car car) throws FullParkException {
        if (isFull()) throw new FullParkException();
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
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
            return ticketCarMap.get(ticket);
        }
        throw new InvalidTicketException();
    }
}
