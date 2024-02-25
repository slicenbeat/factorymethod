package com.example;

import com.example.transport.*;
import com.example.transport.car.*;
import com.example.transport.exceptions.*;
import com.example.transport.motorcycle.*;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, NoSuchModelNameException {
        // Create and work with cars
        Transport cars = TransportClient.createInstance("BMW", 4);
        System.out.println(cars.toString());
        System.out.println(cars.getClass());
        TransportClient.printNames(cars);
        TransportClient.printPrices(cars);
        System.out.println(TransportClient.calculatePricesArithmeticMean(cars));
        System.out.println("\n\n\n\n\n");

        // Create and work with motorcycles
        System.out.println("Motorcycle factory");
        TransportClient.setTransportFactory(new MotorcycleFactory());
        Transport moto = TransportClient.createInstance("Honda", 3);
        System.out.println(moto);
        System.out.println(moto.getClass());
        TransportClient.printNames(moto);
        TransportClient.printPrices(moto);
        System.out.println(TransportClient.calculatePricesArithmeticMean(moto));
        System.out.println("\n\n\n\n\n");

        // Create and clone car prototype
        System.out.println("Car prototype");
        Car sourceCars = new Car("Nissan", 4);
        Car clonnedCars = sourceCars.clone();
        clonnedCars.setPriceByName(9999, "NissanModel 1");

        System.out.println("Source cars" + "\n\n\n" + sourceCars);
        System.out.println("Clonned cars" + "\n\n\n" + clonnedCars);

        System.out.println("\n\n\n\n\n");

        // Create and clone motorcycle prototype
        System.out.println("Motorcycle prototype");
        Motorcycle motorcycle = new Motorcycle("Honda", 2);
        Motorcycle cloneMotorcycle = motorcycle.clone();
        cloneMotorcycle.setPriceByName(9999, "HondaModel 1");
        System.out.println(motorcycle);
        System.out.println(cloneMotorcycle);
    }
}
