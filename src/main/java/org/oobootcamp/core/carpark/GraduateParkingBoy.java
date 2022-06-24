package org.oobootcamp.core.carpark;


import java.util.List;

public class GraduateParkingBoy {
    protected List<ParkingLot> parkingLots;

    GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws FullParkException {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        throw new FullParkException();
    }

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.containsCar(ticket)) return parkingLot.pickUp(ticket);
        }
        throw new InvalidTicketException();
    }
}
