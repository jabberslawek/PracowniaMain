package models;

import helpers.CitizenHelper;

public class Citizen {

    private String city;
    private String pesel;
    private String fullData;

    public Citizen(String city, String fullData) throws Exception {
        String pesel = CitizenHelper.cutPesel(fullData);
        if(pesel.length() != 11)
            return;
        this.city = city;
        this.pesel = pesel;
        this.fullData = fullData;
    }

    public String getCity() {
        return city;
    }

    public String getPesel() {
        return pesel;
    }

    public String getFullData() {
        return fullData;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setFullData(String fullData) {
        this.fullData = fullData;
    }
}



