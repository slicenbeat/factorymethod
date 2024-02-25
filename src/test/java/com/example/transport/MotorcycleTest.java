package com.example.transport;
import com.example.transport.exceptions.*;
import com.example.transport.motorcycle.Motorcycle;

public class MotorcycleTest extends TransportTest {

    @Override
    protected Transport createTransport(String brand, int length) throws DuplicateModelNameException {
        return new Motorcycle(brand, length);
    }
}
