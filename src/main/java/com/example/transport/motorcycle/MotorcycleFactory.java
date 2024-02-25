package com.example.transport.motorcycle;

import com.example.transport.Transport;
import com.example.transport.TransportFactory;

public class MotorcycleFactory implements TransportFactory {

    public Transport createInstance(String brand, int modelsCount) {
        return new Motorcycle(brand, modelsCount);
    }
    
}