package io.github.qingmo.dtos;

public class AddressInfoDTO {

    private String provience;
    private String city;
    private String area;
    private String location;
    private int provincePos = -1;
    private int cityPos = -1;
    private int areaPos = -1;

    public AddressInfoDTO() {
    }

    public AddressInfoDTO(String provience, String city, String area, String location) {
        this.provience = provience;
        this.city = city;
        this.area = area;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvience() {
        return provience;
    }

    public void setProvience(String provience) {
        this.provience = provience;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getProvincePos() {
        return provincePos;
    }

    public void setProvincePos(int provincePos) {
        this.provincePos = provincePos;
    }

    public int getCityPos() {
        return cityPos;
    }

    public void setCityPos(int cityPos) {
        this.cityPos = cityPos;
    }

    public int getAreaPos() {
        return areaPos;
    }

    public void setAreaPos(int areaPos) {
        this.areaPos = areaPos;
    }
}
