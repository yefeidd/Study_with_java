package org.oobootcamp.core.carpark;

public interface PickUpAble {
    Car pickUp(Ticket ticket) throws InvalidTicketException;
}
