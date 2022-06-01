package io.priyanka.coronavirustrac.model;

public class LocationStats {
    private String Province_State;
    private String Country_Region;
    private String Confirmed;

    public String getProvince_State() {
        return Province_State;
    }

    public void setProvince_State(String province_State) {
        Province_State = province_State;
    }

    public String getCountry_Region() {
        return Country_Region;
    }

    public void setCountry_Region(String country_Region) {
        Country_Region = country_Region;
    }

    public String getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(int confirmed) {
        Confirmed = String.valueOf(confirmed);
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "Province_State='" + Province_State + '\'' +
                ", Country_Region='" + Country_Region + '\'' +
                ", Confirmed=" + Confirmed +
                '}';
    }
}
