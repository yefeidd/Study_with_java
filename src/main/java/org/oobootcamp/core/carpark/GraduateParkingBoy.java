package org.oobootcamp.core.carpark;


import java.util.List;

public record GraduateParkingBoy(List<CarPark> carParks) {

    public CarPackResult requestParkCar(Car myCar) {
        for (CarPark carPark : carParks) {
            if (!carPark.isParkFull()) return carPark.requestParkCar(myCar);
        }
        return new FullPackTips();
    }

    public CarGetResult getCar(Ticket ticket) {
        for (CarPark carPark : carParks) {
            Car car = carPark.getCar(ticket);
            if (car != null) return car;
        }
        return new InvalidTicketTips();
    }
}
