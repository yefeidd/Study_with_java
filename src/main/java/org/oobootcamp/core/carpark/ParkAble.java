package org.oobootcamp.core.carpark;

public interface ParkAble {
    Ticket park(Car car) throws FullParkException;

    Car pickUp(Ticket ticket) throws InvalidTicketException;

    boolean containsCar(Ticket ticket);

    boolean isFull();
}
