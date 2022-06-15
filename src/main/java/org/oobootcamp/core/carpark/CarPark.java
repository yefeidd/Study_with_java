package org.oobootcamp.core.carpark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarPark {
    Map<Integer, String> carList;

    CarPark(int count) {
        carList = new HashMap<>(count);
    }

    public Integer requestParkCar(String carNumber) {
        if (carList.size() == 2) return null;
        carList.put(carNumber.hashCode(), carNumber);
        return carNumber.hashCode();
    }

    public String getCar(Integer key) {
        return carList.get(key);
    }
}
