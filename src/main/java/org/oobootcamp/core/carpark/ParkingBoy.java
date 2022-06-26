package org.oobootcamp.core.carpark;

import java.util.List;

public abstract class ParkingBoy implements ParkAble {
    protected List<ParkingLot> parkingLots;

    ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public boolean isFull() {
        return parkingLots.stream().allMatch(ParkingLot::isFull);
    }

    public boolean containsCar(Ticket ticket) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.containsCar(ticket));
    }
    @Override
    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.containsCar(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }
}
