package org.oobootcamp.core.carpark;

import java.util.HashMap;
import java.util.Map;

public class CarPark {
    private Map<CarPackResult, Car> carList;
    private int count = 0;
    private CarPackName carPackName;

    CarPark(CarPackName carPackName, int count) {
        this.count = count;
        this.carList = new HashMap<>(count);
        this.carPackName = carPackName;
    }

    public CarPackResult requestParkCar(Car car) {
        if (carList.size() == count) return new FullPackTips();
        Ticket ticket = new Ticket();
        carList.put(ticket, car);
        return ticket;
    }

    public Car getCar(Ticket ticket) {
        return carList.get(ticket);
    }
}
