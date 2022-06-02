package fr.ensim.interop.introrest.model.telegram.model;

public class Main {
    private Double temp;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = Double.valueOf(Math.round((temp-273) * 100.0)/100);
    }


}
