package com.holidaysautos.models;

public class CarSearchCriteriaDto {
    private String location;
    private String pickupDate;
    private String pickupTime;
    private String returnDate;
    private String returnTime;

    public CarSearchCriteriaDto() {
        this.location = "TBD";
        this.pickupDate = "TBD";
        this.returnDate ="TBD";
        this.pickupTime = "TBD";
        this.returnTime = "TBD";
    }
    public CarSearchCriteriaDto(String location) {
        this.location = location;
        this.pickupDate = "TBD";
        this.returnDate ="TBD";
        this.pickupTime = "TBD";
        this.returnTime = "TBD";
    }

    @Override
    public String toString() {
        return String.format("location='%s'",location);
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
}
