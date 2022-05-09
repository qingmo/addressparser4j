package io.github.qingmo.dtos.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LocateDatabase {
    /**
     * 区名及其简写
     */
    Map<String, ProvinceCityAreaTriple> areaMap = Collections.emptyMap();

    /**
     * 城市名及其简写
     */
    Map<String, ProvinceCityAreaTriple> cityMap = Collections.emptyMap();

    /**
     * (省名全称, 区名全称) -> 相关pca元组
     */
    Map<String, Object> provinceAreaMap = Collections.emptyMap();


    /**
     * 省名 -> 省全名
     */
    Map<String, String> provinceMap = Collections.emptyMap();

    /**
     * todo: (省名，市名，区名）->(经度，纬度）
     */
    Map<String, LatLng> latLngMap = Collections.emptyMap();

    /**
     * 所有地址库的列表（大）
     */
    List<LocateItemDTO> items;
    private final List<String> filterCityNames = Collections.singletonList("吉林市");
    private final List<String> filterAreaNames = Arrays.asList("河北区", "新城区");

    public LocateDatabase() {
    }

    public LocateDatabase(List<LocateItemDTO> items) {
        this.items = items;
        if (null == this.items || this.items.size() == 0) {
            return;
        }
        this.latLngMap = this.items.stream().collect(Collectors.toMap(k -> k.getProvince() + k.getCity() + k.getArea(),
                v -> new LatLng(v.getLongitude(), v.getLatitude())));
        this.provinceMap = fillProvinceMap(this.items);
        this.cityMap = fillCityMap(this.items);
        this.areaMap = fillAreaMap(this.items);
    }

    /**
     * 填充三级区划（区级）地名，包括简称
     *
     * @param items 地区列表
     * @return 区名映射
     */
    Map<String, ProvinceCityAreaTriple> fillAreaMap(List<LocateItemDTO> items) {
        Map<String, ProvinceCityAreaTriple> firstMap = items.stream().collect(Collectors.toMap(LocateItemDTO::getArea, v -> new ProvinceCityAreaTriple(v.getProvince(), v.getCity(), v.getArea())));
        Map<String, ProvinceCityAreaTriple> ret = new HashMap<>();
        ret.putAll(firstMap);
        for (Map.Entry<String, ProvinceCityAreaTriple> entry : firstMap.entrySet()) {
            // 自治区县划简称
            Map<String, String> shortAreaNames = new HashMap<>();
            if (shortAreaNames.keySet().contains(entry.getKey())) {
                ret.put(shortAreaNames.get(entry.getKey()), entry.getValue());
            } else if (isFourLengthName(entry.getKey())) {
                // 4字区划简称
                ret.put(entry.getKey().substring(0, entry.getKey().length() - 2), entry.getValue());
            } else if (filterAreaNames.contains(entry.getKey())) {
                // 过滤的区划名称
                continue;
            } else if (isThreeLengthName(entry.getKey())) {
                // 3字区划简称，'XX区'不简写
                ret.put(entry.getKey().substring(0, entry.getKey().length() - 1), entry.getValue());
            }
        }

        return ret;
    }

    private boolean isFourLengthName(String key) {
        return key.length() > 3 && (key.endsWith("新区") || key.endsWith("城区") || key.endsWith("林区"));
    }

    private boolean isThreeLengthName(String key) {
        return key.length() > 2 && (key.endsWith("市") || key.endsWith("县"));
    }

    /**
     * 填充二级区划（市级）地名，包括简称
     *
     * @param items 地区列表
     * @return 城市名映射
     */
    Map<String, ProvinceCityAreaTriple> fillCityMap(List<LocateItemDTO> items) {
        Map<String, ProvinceCityAreaTriple> firstMap = items.stream().collect(Collectors.toMap(LocateItemDTO::getCity, v -> new ProvinceCityAreaTriple(v.getProvince(), v.getCity(), v.getArea())));
        Map<String, ProvinceCityAreaTriple> ret = new HashMap<>();
        ret.putAll(firstMap);
        for (Map.Entry<String, ProvinceCityAreaTriple> entry : firstMap.entrySet()) {
//            fix 吉林省、吉林市的混淆
            if (filterCityNames.contains(entry.getKey())) {
                continue;
            } else if (entry.getKey().endsWith("市")) {
                ret.put(entry.getKey().substring(0, entry.getKey().length() - 1), entry.getValue());
            } else if ("香港特别行政区".equals(entry.getKey())) {
                ret.put("香港", entry.getValue());
            } else if ("澳门特别行政区".equals(entry.getKey())) {
                ret.put("澳门", entry.getValue());
            } else if (entry.getKey().length() > 3 && entry.getKey().endsWith("地区")) {
//                自治区下的二级区划，eg喀什地区
                ret.put(entry.getKey().substring(0, entry.getKey().length() - 2), entry.getValue());
            }
        }

        return ret;
    }

    /**
     * 填充一级区划（省级）地名，包括简称
     *
     * @param items 地区列表
     * @return 省名映射
     */
    Map<String, String> fillProvinceMap(List<LocateItemDTO> items) {
        Set<String> provinces = items.stream().map(LocateItemDTO::getProvince).collect(Collectors.toSet());
        Map<String, String> ret = new HashMap<>();
        for (String province : provinces) {
            ret.put(province, province);
            if (province.endsWith("省") || province.endsWith("市")) {
                ret.put(province.substring(0, province.length() - 1), province);
            } else if (province.equals("新疆维吾尔自治区")) {
                ret.put("新疆", province);
            } else if (province.equals("内蒙古自治区")) {
                ret.put("内蒙古", province);
            } else if (province.equals("广西壮族自治区")) {
                ret.put("广西", province);
            } else if (province.equals("西藏自治区")) {
                ret.put("西藏", province);
            } else if (province.equals("宁夏回族自治区")) {
                ret.put("宁夏", province);
            } else if (province.equals("香港特别行政区")) {
                ret.put("香港", province);
            } else if (province.equals("澳门特别行政区")) {
                ret.put("澳门", province);
            }
        }

        return ret;
    }

    public Map<String, ProvinceCityAreaTriple> getAreaMap() {
        return areaMap;
    }

    public void setAreaMap(Map<String, ProvinceCityAreaTriple> areaMap) {
        this.areaMap = areaMap;
    }

    public Map<String, ProvinceCityAreaTriple> getCityMap() {
        return cityMap;
    }

    public void setCityMap(Map<String, ProvinceCityAreaTriple> cityMap) {
        this.cityMap = cityMap;
    }

    public Map<String, Object> getProvinceAreaMap() {
        return provinceAreaMap;
    }

    public void setProvinceAreaMap(Map<String, Object> provinceAreaMap) {
        this.provinceAreaMap = provinceAreaMap;
    }

    public Map<String, String> getProvinceMap() {
        return provinceMap;
    }

    public void setProvinceMap(Map<String, String> provinceMap) {
        this.provinceMap = provinceMap;
    }

    public List<LocateItemDTO> getItems() {
        return items;
    }

    public void setItems(List<LocateItemDTO> items) {
        this.items = items;
    }
}
