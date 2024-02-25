package com.example.transport;

import java.util.Arrays;

import com.example.transport.car.CarFactory;

public class TransportClient {
    private static TransportFactory factory = new CarFactory();

    public static void setTransportFactory(TransportFactory factory) {
        TransportClient.factory = factory;
    }

    public static Transport createInstance(String name, int size) {
        return TransportClient.factory.createInstance(name, size);
    }

    public static double calculatePricesArithmeticMean(Transport transport) {
        if (transport.getLength() == 0) {
            return 0;
        }

        double pricesAmount = 0;
        double[] prices = transport.getPrices();

        for (int i = 0; i < prices.length; i++) {
            pricesAmount += prices[i];
        }
        return pricesAmount / prices.length;
    }

    public static void printNames(Transport transport) {
        System.out.println(Arrays.toString(transport.getNames()));
    }

    public static void printPrices(Transport transport) {
        System.out.println(Arrays.toString(transport.getPrices()));
    }
}
