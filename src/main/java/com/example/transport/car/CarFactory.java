package com.example.transport.car;

import com.example.transport.*;

public class CarFactory implements TransportFactory {
    public Transport createInstance(String brand, int modelsCount) {
        return new Car(brand, modelsCount);
    }
}