package org.oobootcamp.core.carpark;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {

    GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws FullParkException {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(FullParkException::new)
                .park(car);
    }
}
