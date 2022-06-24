package org.oobootcamp.core.carpark;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends GraduateParkingBoy {
    SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws FullParkException {
        ParkingLot currentCarParkLot =
                parkingLots.stream()
                        .max(Comparator.comparingInt(ParkingLot::getLeftCapacity))
                        .orElse(null);

        if (currentCarParkLot == null) {
            throw new FullParkException();
        }
        return currentCarParkLot.park(car);
    }
}
