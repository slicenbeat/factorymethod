package com.example.transport;

public interface TransportFactory {
    Transport createInstance(String brand, int modelsCount);
}