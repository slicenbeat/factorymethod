package com.example.transport;

import com.example.transport.exceptions.DuplicateModelNameException;
import com.example.transport.exceptions.NoSuchModelNameException;

public interface Transport {
    public String[] getNames();

    public double[] getPrices();

    int getLength();

    String getBrand();

    public void setBrand(String brand);

    public double getPriceByName(String name) throws NoSuchModelNameException;

    public void setPriceByName(double price, String name) throws NoSuchModelNameException;

    public void setNameByName(String name, String oldName) throws DuplicateModelNameException, NoSuchModelNameException;

    public void add(String name, double price) throws DuplicateModelNameException;

    public void remove(String name) throws NoSuchModelNameException;

    public Transport clone() throws CloneNotSupportedException;

}
