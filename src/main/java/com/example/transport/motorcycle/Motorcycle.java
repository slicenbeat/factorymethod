package com.example.transport.motorcycle;

import com.example.transport.Transport;
import com.example.transport.exceptions.*;

public class Motorcycle implements Transport, Cloneable {
    private Model head;
    private String brand;
    private int length;

    public Motorcycle(String brand, int modelsLength) {
        if (modelsLength < 0) {
            throw new ModelsLengthOutOfBoundsException(Integer.toString(modelsLength));
        }
        this.brand = brand;
        this.length = modelsLength;
        this.fillWithModels();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Motorcycle brand: ")
                .append(brand)
                .append("\n")
                .append("Models:\n");

        Model current = this.head;
        for (int i = 0; i < this.length; i++) {
            sb
                    .append("\t")
                    .append("Model ")
                    .append(i + 1)
                    .append(": ")
                    .append(current.name)
                    .append(", Price: ")
                    .append(current.price)
                    .append("\n");

            current = current.next;
        }
        return sb.toString();
    }

    public Model getHead() {
        return head;
    }

    private void fillWithModels() {
        if (this.length == 0) {
            return;
        }

        this.initHead(brand + "Model 1", 1);
        Model current = this.head;
        for (int i = 1; i < this.length; i++) {
            current.next = new Model(brand + "Model " + (i + 1), i + 1);
            current.next.prev = current;
            current = current.next;
        }
    }

    private void initHead(String name, double price) {
        this.head = new Model(name, price);
        this.head.next = this.head;
        this.head.prev = this.head;
    }

    private class Model {
        private String name = null;
        private double price = Double.NaN;
        private Model prev = null;
        private Model next = null;

        Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    public String[] getNames() {
        if (this.length == 0) {
            return new String[] {};
        }
        String[] names = new String[this.length];
        Model current = this.head;
        for (int i = 0; i < this.length; i++) {
            names[i] = current.name;
            current = current.next;
        }
        return names;
    }

    public double[] getPrices() {
        if (this.length == 0) {
            return new double[] {};
        }
        double[] prices = new double[this.length];
        Model current = this.head;
        for (int i = 0; i < this.length; i++) {
            prices[i] = current.price;
            current = current.next;
        }
        return prices;
    }

    public String getBrand() {
        return this.brand;
    }

    public int getLength() {
        return this.length;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPriceByName(String name) throws NoSuchModelNameException {
        Model current = this.head;
        for (int modelIndex = 0; modelIndex < this.length; modelIndex++) {
            if (current.name.equals(name)) {
                return current.price;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException(name);
    }

    public void setPriceByName(double price, String name) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException(Double.toString(price));
        }
        Model current = this.head;
        for (int modelIndex = 0; modelIndex < this.length; modelIndex++) {
            if (current.name.equals(name)) {
                current.price = price;
                return;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException(name);
    }

    public void setNameByName(String name, String oldName)
            throws NoSuchModelNameException, DuplicateModelNameException {
        Model current = this.head;
        for (int modelIndex = 0; modelIndex < this.length; modelIndex++) {
            if (current.name.equals(name)) {
                throw new DuplicateModelNameException(name);
            }
            if (current.name.equals(oldName)) {
                current.name = name;
                return;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException(name);
    }

    public void add(String name, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException(Double.toString(price));
        }
        if (this.length == 0) {
            this.addHead(name, price);
            return;
        }
        this.addLast(name, price);

    }

    private void addHead(String name, double price) {
        this.initHead(name, price);
        this.length++;
    }

    private void addLast(String name, double price) throws DuplicateModelNameException {
        Model current = this.head;
        for (int modelIndex = 0; modelIndex < this.length; modelIndex++) {
            if (current.name.equals(name)) {
                throw new DuplicateModelNameException(name);
            }
            current = current.next;
        }
        Model last = this.head.prev;
        last.next = new Model(name, price);
        last.next.prev = last;
        last.next.next = this.head;
        this.length++;
    }

    public void remove(String name) throws NoSuchModelNameException {
        if (this.length == 0) {
            return;
        }
        Model current = this.head;
        for (int modelIndex = 0; modelIndex < this.length; modelIndex++) {
            if (current.name.equals(name)) {
                this.removeCurrent(current);
                return;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException(name);
    }

    private void removeCurrent(Model current) {
        if (current.next == current.prev) {
            this.head = null;
            this.length--;
            return;
        }

        Model next = current.next;
        Model prev = current.prev;
        prev.next = next;
        next.prev = prev;
        this.length--;
    }

    @Override
    public Motorcycle clone() throws CloneNotSupportedException {
        Motorcycle clonnedMotorcycle = (Motorcycle) super.clone();

        clonnedMotorcycle.head = new Model(this.head.name, this.head.price);
        clonnedMotorcycle.head.next = clonnedMotorcycle.head;
        clonnedMotorcycle.head.prev = clonnedMotorcycle.head;

        Model sourceNextNode = this.head.next;
        Model clonCurrentNode = clonnedMotorcycle.head;

        for (int i = 1; i < this.getLength(); i++) {
            clonCurrentNode.next = new Model(sourceNextNode.name, sourceNextNode.price);
            clonCurrentNode.next.prev = clonCurrentNode;
            sourceNextNode = sourceNextNode.next;
            clonCurrentNode = clonCurrentNode.next;
        }

        clonnedMotorcycle.head.prev = clonCurrentNode;
        clonCurrentNode.next = head;

        return clonnedMotorcycle;
    }

}
