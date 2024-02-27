package com.example.transport;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import com.example.transport.exceptions.*;

public abstract class TransportTest {
    protected Transport transport;

    protected abstract Transport createTransport(String brand, int length) throws DuplicateModelNameException;

    @BeforeEach
    public void setUpTransport() throws DuplicateModelNameException {
        transport = createTransport("Toyota", 3);
    }

    @Test
    public void setAndGetCorrectBrand() {
        transport.setBrand("Lada");
        assertEquals(transport.getBrand(), "Lada");
    }

    @Test
    public void addCorrectModel() throws DuplicateModelNameException {
        transport.add("ToyotaModel 4", 80.0);
        assertEquals(4, transport.getLength());
    }

    @Test
    public void throwExceptionWhenTryAddModelWithDuplicateName() {
        assertThrows(DuplicateModelNameException.class, () -> {
            transport.add("ToyotaModel 1", 80.0);
        });
    }

    @Test
    public void throwExceptionWhenTryAddModelWithIncorrectPrice() {
        assertThrows(ModelPriceOutOfBoundsException.class, () -> {
            transport.add("Model 4", -80.0);
        });
    }

    @Test
    public void removeExistingModel() throws NoSuchModelNameException {
        transport.remove("ToyotaModel 1");
        assertEquals(2, transport.getLength());
    }

    @Test
    public void throwExceptionWhenTryRemoveNonExistingModel() {
        assertThrows(NoSuchModelNameException.class, () -> {
            transport.remove("Model 9999");
        });
    }

    @Test
    public void returnNames() {
        String[] expectedNames = { "ToyotaModel 1", "ToyotaModel 2", "ToyotaModel 3" };
        assertArrayEquals(expectedNames, transport.getNames());
    }

    @Test
    public void returnPrices() {
        double[] expectedPrices = { 1, 2, 3 };
        assertArrayEquals(expectedPrices, transport.getPrices());
    }

    @Test
    public void updateModelPriceWithCorrectValue() throws NoSuchModelNameException {
        transport.setPriceByName(90, "ToyotaModel 2");
        assertEquals(90, transport.getPriceByName("ToyotaModel 2"));
    }

    @Test
    public void throwExceptionWhenTryGetPriceFromNotExistingModel() {
        assertThrows(NoSuchModelNameException.class, () -> {
            transport.getPriceByName("ToyotaModel 2999");
        });
    }

    @Test
    public void throwExceptionWhenTrySetPriceToNonExistingModel() {
        assertThrows(NoSuchModelNameException.class, () -> {
            transport.setPriceByName(90, "SOME NOT EXISTING NAME");
        });
    }

    @Test
    public void throwExceptionWhenTrySetIncorrectPrice() {
        assertThrows(ModelPriceOutOfBoundsException.class, () -> {
            transport.setPriceByName(-15, "ToyotaModel 1");
        });
    }

    @Test
    public void setNewModelNameFromTheOldModelName() throws NoSuchModelNameException, DuplicateModelNameException {
        transport.setNameByName("new Model Name", "ToyotaModel 1");
        String[] expectedNames = { "new Model Name", "ToyotaModel 2", "ToyotaModel 3" };
        assertArrayEquals(expectedNames, transport.getNames());
    }

    @Test
    public void throwExceptionWhenTrySetDuplicatedName() {
        assertThrows(DuplicateModelNameException.class, () -> {
            transport.setNameByName("ToyotaModel 1", "ToyotaModel 2");
        });
    }

    @Test
    public void throwExceptionWhenTrySetDuplicatedNameOnSecond() {
        assertThrows(DuplicateModelNameException.class, () -> {
            transport.setNameByName("ToyotaModel 3", "ToyotaModel 2");
        });
    }

    @Test
    public void throwExceptionWhenSetNameByNotExistingName() {
        assertThrows(NoSuchModelNameException.class, () -> {
            transport.setNameByName("Model 9999", "NOT EXISTING MODEL NAME");
        });
    }

    

    @Test
    public void changePriceOnlyOnClonnedTransport()
            throws NoSuchModelNameException, DuplicateModelNameException, CloneNotSupportedException {
        Transport clonnedTransport = transport.clone();
        clonnedTransport.setPriceByName(90, "ToyotaModel 2");
        assertEquals(90, clonnedTransport.getPriceByName("ToyotaModel 2"));
        assertEquals(2, transport.getPriceByName("ToyotaModel 2"));
    }

}
