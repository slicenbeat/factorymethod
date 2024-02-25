package com.example.transport;

import com.example.transport.car.Car;
import com.example.transport.exceptions.*;

public class CarTest extends TransportTest {
    @Override
    protected Transport createTransport(String brand, int length) throws DuplicateModelNameException {
        return new Car(brand, length);
    }
}
