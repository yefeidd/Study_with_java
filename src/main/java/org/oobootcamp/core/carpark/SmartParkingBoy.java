package org.oobootcamp.core.carpark;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws FullParkException {
        return parkingLots
                .stream()
                .max(Comparator.comparingInt(ParkingLot::getLeftCapacity))
                .orElseThrow(FullParkException::new)
                .park(car);
    }
}
