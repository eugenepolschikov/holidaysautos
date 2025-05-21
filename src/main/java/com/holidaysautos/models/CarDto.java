package com.holidaysautos.models;

import java.util.Objects;

public class CarDto {
    private String carPrice;
    private String carModelDescription;

    public CarDto() {
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarModelDescription() {
        return carModelDescription;
    }

    public void setCarModelDescription(String carModelDescription) {
        this.carModelDescription = carModelDescription;
    }

    @Override
    public String toString() {
        return "CarDto{" +
            "carPrice='" + carPrice + '\'' +
            ", carModelDescription='" + carModelDescription + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto carDto = (CarDto) o;
        return carPrice.equals(carDto.carPrice) && carModelDescription.equals(carDto.carModelDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carPrice, carModelDescription);
    }
}
