package org.oobootcamp.core.carpark;

public class Ticket implements CarPackResult {
    CarPackName carPackName;

    Ticket(CarPackName carPackName) {
        this.carPackName = carPackName;
    }
}
