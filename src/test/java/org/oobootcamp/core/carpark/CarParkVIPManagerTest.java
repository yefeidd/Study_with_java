package org.oobootcamp.core.carpark;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.oobootcamp.core.carpark.CarPackName.*;

public class CarParkVIPManagerTest {
    /**
     * Contxt:
     * 作为 银卡VIP用户
     * 我想要 一个初入职场的停车小弟来帮我停车和取车
     * 从而 节省我的时间
     * <p>
     * <p>
     * // 停车
     * ###AC： 三个停车场有空位，小弟在一号停车场停车成功
     * - Example
     * - Given 有人来停车, 三个停车场有空位, When 停车 ,Then 在一号停车场停车成功, 返回停车票
     * <p>
     * <p>
     * ###AC：仅有二三号停车场有空位，小弟在二号停车场停车成功
     * - Example
     * - Given 有人来停车, 仅有二三号停车场有空位, When 停车 ,Then 在二号停车场停车成功, 返回停车票
     * <p>
     * <p>
     * ###AC：仅有三号停车场有空位，小弟在三号停车场停车成功
     * - Example
     * - Given 有人来停车, 仅有三号停车场有空位, When 停车 ,Then 在三号停车场停车成功, 返回停车票
     * <p>
     * <p>
     * ###AC：车位已满，小弟无法停车
     * - Example
     * - Given 有人来停车, 车位已满, When 停车 ,Then 给出车位已满的提示
     * <p>
     * <p>
     * // 取车
     * ###AC：有停车票取车成功
     * - Example
     * - Given 有人来，有有效停车票,
     * - When 取车,
     * - Then 取车成功
     * <p>
     *
     * <p>
     * ###AC：有无效停车票无法取车
     * - Example
     * - Given 有人来，有有效停车票,
     * - When 取车,
     * - Then 取车成功
     **/


    /**
     * ###AC： 三个停车场有空位，小弟在一号停车场停车成功
     * Example
     * Given 有人来停车, 三个停车场有空位, When 停车 ,Then 在一号停车场停车成功, 返回停车票
     */
    @Test
    void should_get_a_ticket_and_current_parking_name_is_ONE_when_park_a_car_given_3_car_parks_and_all_have_lef_space() {
        // given
        ArrayList<CarPark> carParks = new ArrayList<>(Arrays.asList(new CarPark(ONE, 1), new CarPark(TWO, 1), new CarPark(THREE, 1)));
        CarPackVipManager carPackVipManager = new CarPackVipManager(carParks);

        Car myCar = new Car();

        // when
        Ticket ticket = (Ticket) carPackVipManager.requestParkCar(myCar);

        // then
        assertThat(ticket).isNotNull();

        assertThat(ticket.currentPackPosition).isEqualTo(ONE);
    }

    @Test
    void should_get_a_ticket_and_current_parking_name_is_TWO_when_park_a_car_given_3_car_parks_and_just_2_and_3_have_lef_space() {
        // given
        ArrayList<CarPark> carParks = new ArrayList<>(Arrays.asList(new CarPark(ONE, 1), new CarPark(TWO, 1), new CarPark(THREE, 1)));
        CarPackVipManager carPackVipManager = new CarPackVipManager(carParks);
        Car otherCar = new Car();
        carPackVipManager.requestParkCar(otherCar);

        Car myCar = new Car();

        // when
        Ticket ticket = (Ticket) carPackVipManager.requestParkCar(myCar);

        // then
        assertThat(ticket).isNotNull();

        assertThat(ticket.currentPackPosition).isEqualTo(TWO);
    }

    @Test
    void should_get_a_ticket_and_current_parking_name_is_THREE_when_park_a_car_given_3_car_parks_and_just_3_have_lef_space() {
        // given
        ArrayList<CarPark> carParks = new ArrayList<>(Arrays.asList(new CarPark(ONE, 1), new CarPark(TWO, 1), new CarPark(THREE, 1)));
        CarPackVipManager carPackVipManager = new CarPackVipManager(carParks);
        carPackVipManager.requestParkCar(new Car());
        carPackVipManager.requestParkCar(new Car());

        Car myCar = new Car();

        // when
        Ticket ticket = (Ticket) carPackVipManager.requestParkCar(myCar);

        // then
        assertThat(ticket).isNotNull();

        assertThat(ticket.currentPackPosition).isEqualTo(THREE);
    }


}