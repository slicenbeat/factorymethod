package com.example.transport.car;

import java.util.Arrays;

import com.example.transport.Transport;
import com.example.transport.exceptions.*;

public class Car implements Transport, Cloneable {
    private String brand;
    private Model[] models;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Car brand: ")
                .append(brand)
                .append("\n");
        sb.append("Models:\n");
        for (int i = 0; i < models.length; i++) {
            Model model = models[i];
            sb
                    .append("\t")
                    .append(model.getName())
                    .append(": ")
                    .append(model.getPrice())
                    .append("\n");
        }

        return sb.toString();
    }

    public Car(String brand, int modelsLength) {
        if (modelsLength < 0) {
            throw new ModelsLengthOutOfBoundsException(Integer.toString(modelsLength));
        }
        this.brand = brand;
        this.models = new Model[modelsLength];
        this.fillWithModels();

    }

    private void fillWithModels() {
        for (int i = 0; i < this.models.length; i++) {
            String name = brand + "Model " + (i + 1);
            double price = i + 1;
            models[i] = new Model(name, price);
        }
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getLength() {
        return this.models.length;
    }

    public void setNameByName(String name, String oldName)
            throws NoSuchModelNameException, DuplicateModelNameException {
        int toUpdateModelIndex = -1;
        for (int i = 0; i < this.models.length; i++) {
            if (models[i].name.equals(name)) {
                throw new DuplicateModelNameException(name);
            }
            if (models[i].name.equals(oldName)) {
                toUpdateModelIndex = i;
            }
        }
        if (toUpdateModelIndex == -1) {
            throw new NoSuchModelNameException(oldName);
        }
        models[toUpdateModelIndex].setName(name);
    }

    public void add(String name, double price) throws DuplicateModelNameException {
        if (this.getIndexByName(name) != -1) {
            throw new DuplicateModelNameException(name);
        }
        this.models = Arrays.copyOf(this.models, this.models.length + 1);
        this.models[this.models.length - 1] = new Model(name, price);
    }

    public void remove(String name) throws NoSuchModelNameException {
        int index = this.getIndexByName(name);

        if (index == -1) {
            throw new NoSuchModelNameException(name);
        }

        this.removeByIndex(index);
    }

    private Model getModelByName(String name) throws NoSuchModelNameException {
        int modelIndex = this.getIndexByName(name);

        if (modelIndex == -1) {
            throw new NoSuchModelNameException(name);
        }
        return this.models[modelIndex];
    }

    private void removeByIndex(int deletedModelIndex) {
        System.arraycopy(this.models, deletedModelIndex + 1, this.models, deletedModelIndex,
                this.models.length - 1 - deletedModelIndex);
        this.models = Arrays.copyOf(this.models, this.models.length - 1);
    }

    private int getIndexByName(String name) {
        for (int i = 0; i < this.models.length; i++) {
            if (this.models[i].isModelName(name)) {
                return i;
            }
        }
        return -1;
    }

    public String[] getNames() {
        String[] modelsNames = new String[this.models.length];
        for (int i = 0; i < this.models.length; i++) {
            modelsNames[i] = this.models[i].getName();
        }
        return modelsNames;
    }

    public double[] getPrices() {
        double[] modelsPrices = new double[this.models.length];
        for (int i = 0; i < this.models.length; i++) {
            modelsPrices[i] = this.models[i].getPrice();
        }
        return modelsPrices;
    }

    public double getPriceByName(String name) throws NoSuchModelNameException {
        Model model = this.getModelByName(name);
        return model.getPrice();
    }

    public void setPriceByName(double price, String name) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException(Double.toString(price));
        }
        Model model = this.getModelByName(name);
        model.setPrice(price);
    }

    @Override
    public Car clone() throws CloneNotSupportedException {
        Car car = (Car) super.clone();
        car.models = new Model[this.getLength()];
        for (int i = 0; i < this.getLength(); i++) {
            car.models[i] = this.models[i].clone();
        }
        return car;
    }

    private class Model implements Cloneable {
        private String name;
        private double price;

        public Model(String name, double price) {
            if (price < 0) {
                throw new ModelPriceOutOfBoundsException(Double.toString(price));
            }
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return this.price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public boolean isModelName(String modelName) {
            return modelName.equals(this.name);
        }

        @Override
        protected Model clone() {
            try {
                return (Model) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
