package com.example.transport;

import com.example.transport.exceptions.DuplicateModelNameException;
import com.example.transport.exceptions.NoSuchModelNameException;

public interface Transport {
    String[] getNames();

    double[] getPrices();

    int getLength();

    String getBrand();

    void setBrand(String brand);

    double getPriceByName(String name) throws NoSuchModelNameException;

    void setPriceByName(double price, String name) throws NoSuchModelNameException;

    void setNameByName(String name, String oldName) throws DuplicateModelNameException, NoSuchModelNameException;

    void add(String name, double price) throws DuplicateModelNameException;

    void remove(String name) throws NoSuchModelNameException;

    Transport clone() throws CloneNotSupportedException;

}
