package com.example.transport;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import com.example.transport.car.Car;
import com.example.transport.exceptions.*;

public class CarTest extends TransportTest {
    @Override
    protected Transport createTransport(String brand, int length) throws DuplicateModelNameException {
        return new Car(brand, length);
    }
    
    @Test
    public void changeNameOnlyOnClonnedTransport()
            throws NoSuchModelNameException, DuplicateModelNameException, CloneNotSupportedException {
        Transport clonnedTransport = transport.clone();
        clonnedTransport.setNameByName("new Model Name", "ToyotaModel 1");
        String[] expectedNames = { "ToyotaModel 1", "ToyotaModel 2", "ToyotaModel 3" };
        String[] expectedNamesFromClonnedTransport = { "new Model Name", "ToyotaModel 2", "ToyotaModel 3" };

        assertArrayEquals(expectedNamesFromClonnedTransport, clonnedTransport.getNames());
        assertArrayEquals(expectedNames, transport.getNames());
    }
}
