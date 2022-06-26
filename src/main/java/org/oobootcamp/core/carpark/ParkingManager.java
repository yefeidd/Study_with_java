package org.oobootcamp.core.carpark;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager {
    private List<ParkAble> parkAbles;

    public ParkingManager(List<ParkAble> parkingLots) {
        this(new ArrayList<>(), parkingLots);
    }

    public ParkingManager(List<ParkAble> parkingBoys, List<ParkAble> parkingLots) {
        this.parkAbles = new ArrayList<>();
        parkAbles.addAll(parkingBoys);
        parkAbles.addAll(parkingLots);
    }

    public Ticket park(Car car) throws FullParkException {
        return parkAbles.stream().filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(FullParkException::new)
                .park(car);
    }

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        return parkAbles.stream().filter(parkingLot -> parkingLot.containsCar(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }
}
