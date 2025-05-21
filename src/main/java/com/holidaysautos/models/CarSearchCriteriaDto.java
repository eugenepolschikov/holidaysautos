package com.holidaysautos.models;

import java.util.Objects;

public class CarSearchCriteriaDto {
    private String location;
    private String pickupDate;
    private String pickupTime;
    private String returnDate;
    private String returnTime;

    public CarSearchCriteriaDto() {
        this.location = "TBD";
        this.pickupDate = "TBD";
        this.returnDate = "TBD";
        this.pickupTime = "TBD";
        this.returnTime = "TBD";
    }

    public CarSearchCriteriaDto(String location) {
        this.location = location;
        this.pickupDate = "TBD";
        this.returnDate = "TBD";
        this.pickupTime = "TBD";
        this.returnTime = "TBD";
    }

    @Override
    public String toString() {
        return String.format("location='%s'", location);
    }

    public String toStringAllFields() {
        return "CarSearchCriteriaDto{" +
            "location='" + location + '\'' +
            ", pickupDate='" + pickupDate + '\'' +
            ", pickupTime='" + pickupTime + '\'' +
            ", returnDate='" + returnDate + '\'' +
            ", returnTime='" + returnTime + '\'' +
            '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarSearchCriteriaDto that = (CarSearchCriteriaDto) o;
        boolean mandatoryEqualFields = Objects.equals(location, that.location) && Objects.equals(pickupTime, that.pickupTime) && Objects.equals(returnTime, that.returnTime);
        boolean dates =
            (pickupDate.contains(that.pickupDate) || that.pickupDate.contains(pickupDate)) &&
                (returnDate.contains(that.returnDate) || that.returnDate.contains(returnDate));
        return mandatoryEqualFields && dates;
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, pickupDate, pickupTime, returnDate, returnTime);
    }
}
