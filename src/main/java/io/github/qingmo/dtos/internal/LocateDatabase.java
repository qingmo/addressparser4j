package io.github.qingmo.dtos.internal;

import java.util.List;
import java.util.Map;

public class LocateDatabase {
    /**
     * 区名及其简写
     */
    Map<String, Object> areaMap;

    /**
     * 城市名及其简写
     */
    Map<String, Object> cityMap;

    /**
     * (省名全称, 区名全称) -> 相关pca元组
     */
    Map<String, Object> provinceAreaMap;


    /**
     *  省名 -> 省全名
     */
    Map<String, Object> provinceMap;

    /**
     * todo: (省名，市名，区名）->(经度，纬度）
     */

    /**
     * 所有地址库的列表（大）
     */
    List<LocateItemDTO> items;

    public Map<String, Object> getAreaMap() {
        return areaMap;
    }

    public void setAreaMap(Map<String, Object> areaMap) {
        this.areaMap = areaMap;
    }

    public Map<String, Object> getCityMap() {
        return cityMap;
    }

    public void setCityMap(Map<String, Object> cityMap) {
        this.cityMap = cityMap;
    }

    public Map<String, Object> getProvinceAreaMap() {
        return provinceAreaMap;
    }

    public void setProvinceAreaMap(Map<String, Object> provinceAreaMap) {
        this.provinceAreaMap = provinceAreaMap;
    }

    public Map<String, Object> getProvinceMap() {
        return provinceMap;
    }

    public void setProvinceMap(Map<String, Object> provinceMap) {
        this.provinceMap = provinceMap;
    }

    public List<LocateItemDTO> getItems() {
        return items;
    }

    public void setItems(List<LocateItemDTO> items) {
        this.items = items;
    }
}
