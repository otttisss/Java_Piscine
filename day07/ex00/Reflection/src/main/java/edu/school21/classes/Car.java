package edu.school21.classes;

public class Car {
    private Long number;
    private String model;
    private Double price;
    private Boolean reserved;

    public Car() {
        this.number = 0L;
        this.model = "Default";
        this.price = 0.0;
        this.reserved = false;
    }

    public Car(Long number, String model, Double price, Boolean reserved) {
        this.number = number;
        this.model = model;
        this.price = price;
        this.reserved = reserved;
    }

    public void setNewPrice(Double newPrice) {
        price = newPrice;
    }

    @Override
    public String toString() {
        return "Car{" +
                "number=" + number +
                ", model=" + model + '\'' +
                ", price=" + price +
                ", reserved=" + reserved +
                '}';
    }
}
