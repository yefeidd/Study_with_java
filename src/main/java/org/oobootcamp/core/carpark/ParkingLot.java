package org.oobootcamp.core.carpark;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements ParkAble {
    private final Map<Ticket, Car> parkingSpot;
    private final int capacity;

    private int leftCapacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingSpot = new HashMap<>(capacity);
        this.leftCapacity = capacity;
    }

    public Ticket park(Car car) throws FullParkException {
        if (isFull()) throw new FullParkException();
        Ticket ticket = new Ticket();
        parkingSpot.put(ticket, car);
        leftCapacity = capacity - parkingSpot.size();
        return ticket;
    }

    public boolean isFull() {
        return parkingSpot.size() == capacity;
    }

    public boolean containsCar(Ticket ticket) {
        return parkingSpot.containsKey(ticket);
    }

    @Override
    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        if (containsCar(ticket)) {
            Car car = parkingSpot.get(ticket);
            parkingSpot.remove(ticket, car);
            return car;
        }
        throw new InvalidTicketException();
    }

    public int getLeftCapacity() {
        return leftCapacity;
    }
}
