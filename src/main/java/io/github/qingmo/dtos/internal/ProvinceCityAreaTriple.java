package io.github.qingmo.dtos.internal;

import java.util.Objects;

public class ProvinceCityAreaTriple {
    private String province;
    private String city;
    private String area;

    public ProvinceCityAreaTriple(String province, String city, String area) {
        this.province = province;
        this.city = city;
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvinceCityAreaTriple that = (ProvinceCityAreaTriple) o;
        return Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(area, that.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(province, city, area);
    }
}
