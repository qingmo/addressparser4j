package io.github.qingmo.dtos.internal

import spock.lang.Specification

class LocateDatabaseTest extends Specification {


    def "test fillProvinceMap"() {
        def database = new LocateDatabase()
        def item = new LocateItemDTO()
        def items = Collections.singletonList(item)
        expect:
        item.setProvince(a)
        def ret = database.fillProvinceMap(items)
        ret.size() == b
        ret.values().getAt(0) == c
        ret.keySet() == d as Set
        where:
        a          || b | c          | d
        "四川省"      || 2 | "四川省"      | Arrays.asList("四川省", "四川")
        "北京市"      || 2 | "北京市"      | Arrays.asList("北京市", "北京")
        "重庆市"      || 2 | "重庆市"      | Arrays.asList("重庆市", "重庆")
        "新疆维吾尔自治区" || 2 | "新疆维吾尔自治区" | Arrays.asList("新疆维吾尔自治区", "新疆")
        "内蒙古自治区"   || 2 | "内蒙古自治区"   | Arrays.asList("内蒙古自治区", "内蒙古")
        "广西壮族自治区"  || 2 | "广西壮族自治区"  | Arrays.asList("广西壮族自治区", "广西")
        "西藏自治区"    || 2 | "西藏自治区"    | Arrays.asList("西藏自治区", "西藏")
        "宁夏回族自治区"  || 2 | "宁夏回族自治区"  | Arrays.asList("宁夏回族自治区", "宁夏")
        "香港特别行政区"  || 2 | "香港特别行政区"  | Arrays.asList("香港特别行政区", "香港")
        "澳门特别行政区"  || 2 | "澳门特别行政区"  | Arrays.asList("澳门特别行政区", "澳门")
    }

    def "test fillCityMap"() {
        def database = new LocateDatabase()
        def item = new LocateItemDTO()
        def items = Collections.singletonList(item)
        expect:
        item.setProvince(a)
        item.setCity(b)
        item.setArea(c)
        def ret = database.fillCityMap(items)
        ret.size() == d
        ret.values().getAt(0) == e
        ret.keySet() == f as Set
        where:
        a          | b         | c             || d | e                                                             | f
        "吉林省"      | "吉林市"     | "吉林中国新加坡食品区"  || 1 | new ProvinceCityAreaTriple("吉林省", "吉林市", "吉林中国新加坡食品区")        | Arrays.asList("吉林市")
        "吉林省"      | "吉林市"     | "吉林经济开发区"     || 1 | new ProvinceCityAreaTriple("吉林省", "吉林市", "吉林经济开发区")           | Arrays.asList("吉林市")
        "吉林省"      | "吉林市"     | "吉林高新技术产业开发区" || 1 | new ProvinceCityAreaTriple("吉林省", "吉林市", "吉林高新技术产业开发区")       | Arrays.asList("吉林市")
        "吉林省"      | "吉林市"     | "昌邑区"         || 1 | new ProvinceCityAreaTriple("吉林省", "吉林市", "昌邑区")               | Arrays.asList("吉林市")
        "吉林省"      | "吉林市"     | "桦甸市"         || 1 | new ProvinceCityAreaTriple("吉林省", "吉林市", "桦甸市")               | Arrays.asList("吉林市")
        "吉林省"      | "吉林市"     | "永吉县"         || 1 | new ProvinceCityAreaTriple("吉林省", "吉林市", "永吉县")               | Arrays.asList("吉林市")
        "吉林省"      | "吉林市"     | "磐石市"         || 1 | new ProvinceCityAreaTriple("吉林省", "吉林市", "磐石市")               | Arrays.asList("吉林市")
        "北京市"      | "北京市"     | "房山区"         || 2 | new ProvinceCityAreaTriple("北京市", "北京市", "房山区")               | Arrays.asList("北京市", "北京")
        "北京市"      | "北京市"     | "昌平区"         || 2 | new ProvinceCityAreaTriple("北京市", "北京市", "昌平区")               | Arrays.asList("北京市", "北京")
        "北京市"      | "北京市"     | "朝阳区"         || 2 | new ProvinceCityAreaTriple("北京市", "北京市", "朝阳区")               | Arrays.asList("北京市", "北京")
        "四川省"      | "绵阳市"     | "游仙区"         || 2 | new ProvinceCityAreaTriple("四川省", "绵阳市", "游仙区")               | Arrays.asList("绵阳市", "绵阳")
        "四川省"      | "绵阳市"     | "盐亭县"         || 2 | new ProvinceCityAreaTriple("四川省", "绵阳市", "盐亭县")               | Arrays.asList("绵阳市", "绵阳")
        "香港特别行政区"  | "香港特别行政区" | "沙田区"         || 2 | new ProvinceCityAreaTriple("香港特别行政区", "香港特别行政区", "沙田区")       | Arrays.asList("香港特别行政区", "香港")
        "香港特别行政区"  | "香港特别行政区" | "油尖旺区"        || 2 | new ProvinceCityAreaTriple("香港特别行政区", "香港特别行政区", "油尖旺区")      | Arrays.asList("香港特别行政区", "香港")
        "香港特别行政区"  | "香港特别行政区" | "深水埗区"        || 2 | new ProvinceCityAreaTriple("香港特别行政区", "香港特别行政区", "深水埗区")      | Arrays.asList("香港特别行政区", "香港")
        "新疆维吾尔自治区" | "喀什地区"    | "叶城县"         || 2 | new ProvinceCityAreaTriple("新疆维吾尔自治区", "喀什地区", "叶城县")         | Arrays.asList("喀什地区", "喀什")
        "新疆维吾尔自治区" | "喀什地区"    | "喀什市"         || 2 | new ProvinceCityAreaTriple("新疆维吾尔自治区", "喀什地区", "喀什市")         | Arrays.asList("喀什地区", "喀什")
        "新疆维吾尔自治区" | "喀什地区"    | "塔什库尔干塔吉克自治县" || 2 | new ProvinceCityAreaTriple("新疆维吾尔自治区", "喀什地区", "塔什库尔干塔吉克自治县") | Arrays.asList("喀什地区", "喀什")
        "新疆维吾尔自治区" | "喀什地区"    | "岳普湖县"        || 2 | new ProvinceCityAreaTriple("新疆维吾尔自治区", "喀什地区", "岳普湖县")        | Arrays.asList("喀什地区", "喀什")
        "澳门特别行政区"  | "澳门特别行政区" | ""            || 2 | new ProvinceCityAreaTriple("澳门特别行政区", "澳门特别行政区", "")          | Arrays.asList("澳门特别行政区", "澳门")
    }

    def "test fillCityMap with emptyName"() {
        when:
        def database = new LocateDatabase()
        def item = new LocateItemDTO()
        def item1 = new LocateItemDTO()
        def item2 = new LocateItemDTO()
        def item3 = new LocateItemDTO()
        def item4 = new LocateItemDTO()
        def items = new LinkedList()
        item.setProvince("海南省")
        item.setCity("")
        item.setArea("陵水黎族自治县")
        items.add(item)
        item1.setProvince("新疆维吾尔自治区")
        item1.setCity("")
        item1.setArea("北屯市")
        items.add(item1)
        item2.setProvince("新疆维吾尔自治区")
        item2.setCity("")
        item2.setArea("双河市")
        items.add(item2)
        item3.setProvince("新疆维吾尔自治区")
        item3.setCity("")
        item3.setArea("可克达拉市")
        items.add(item3)
        item4.setProvince("新疆维吾尔自治区")
        item4.setCity("")
        item4.setArea("图木舒克市")
        items.add(item4)
        def ret = database.fillCityMap(items)
        then:
        ret.size() == 4
    }

    def "test fillAreaMap"() {
        def database = new LocateDatabase()
        def item = new LocateItemDTO()
        def items = Collections.singletonList(item)
        expect:
        item.setProvince(a)
        item.setCity(b)
        item.setArea(c)
        def ret = database.fillAreaMap(items)
        ret.size() == d
        ret.values().getAt(0) == e
        ret.keySet() == f as Set
        where:
        a          | b         | c             || d | e                                                             | f
        "吉林省"      | "吉林市"     | "吉林中国新加坡食品区"  || 1 | new ProvinceCityAreaTriple("吉林省", "吉林市", "吉林中国新加坡食品区")        | Arrays.asList("吉林中国新加坡食品区")
        "吉林省"      | "吉林市"     | "吉林经济开发区"     || 1 | new ProvinceCityAreaTriple("吉林省", "吉林市", "吉林经济开发区")           | Arrays.asList("吉林经济开发区")
        "吉林省"      | "吉林市"     | "吉林高新技术产业开发区" || 1 | new ProvinceCityAreaTriple("吉林省", "吉林市", "吉林高新技术产业开发区")       | Arrays.asList("吉林高新技术产业开发区")
        "吉林省"      | "吉林市"     | "昌邑区"         || 1 | new ProvinceCityAreaTriple("吉林省", "吉林市", "昌邑区")               | Arrays.asList("昌邑区")
        "吉林省"      | "吉林市"     | "桦甸市"         || 2 | new ProvinceCityAreaTriple("吉林省", "吉林市", "桦甸市")               | Arrays.asList("桦甸市", "桦甸")
        "吉林省"      | "吉林市"     | "永吉县"         || 2 | new ProvinceCityAreaTriple("吉林省", "吉林市", "永吉县")               | Arrays.asList("永吉县", "永吉")
        "吉林省"      | "吉林市"     | "磐石市"         || 2 | new ProvinceCityAreaTriple("吉林省", "吉林市", "磐石市")               | Arrays.asList("磐石市", "磐石")
        "北京市"      | "北京市"     | "房山区"         || 1 | new ProvinceCityAreaTriple("北京市", "北京市", "房山区")               | Arrays.asList("房山区")
        "北京市"      | "北京市"     | "昌平区"         || 1 | new ProvinceCityAreaTriple("北京市", "北京市", "昌平区")               | Arrays.asList("昌平区")
        "北京市"      | "北京市"     | "朝阳区"         || 1 | new ProvinceCityAreaTriple("北京市", "北京市", "朝阳区")               | Arrays.asList("朝阳区")
        "四川省"      | "绵阳市"     | "游仙区"         || 1 | new ProvinceCityAreaTriple("四川省", "绵阳市", "游仙区")               | Arrays.asList("游仙区")
        "四川省"      | "绵阳市"     | "盐亭县"         || 2 | new ProvinceCityAreaTriple("四川省", "绵阳市", "盐亭县")               | Arrays.asList("盐亭县", "盐亭")
        "香港特别行政区"  | "香港特别行政区" | "沙田区"         || 1 | new ProvinceCityAreaTriple("香港特别行政区", "香港特别行政区", "沙田区")       | Arrays.asList("沙田区")
        "香港特别行政区"  | "香港特别行政区" | "油尖旺区"        || 1 | new ProvinceCityAreaTriple("香港特别行政区", "香港特别行政区", "油尖旺区")      | Arrays.asList("油尖旺区")
        "香港特别行政区"  | "香港特别行政区" | "深水埗区"        || 1 | new ProvinceCityAreaTriple("香港特别行政区", "香港特别行政区", "深水埗区")      | Arrays.asList("深水埗区")
        "新疆维吾尔自治区" | "喀什地区"    | "叶城县"         || 2 | new ProvinceCityAreaTriple("新疆维吾尔自治区", "喀什地区", "叶城县")         | Arrays.asList("叶城县", "叶城")
        "新疆维吾尔自治区" | "喀什地区"    | "喀什市"         || 2 | new ProvinceCityAreaTriple("新疆维吾尔自治区", "喀什地区", "喀什市")         | Arrays.asList("喀什市", "喀什")
        "新疆维吾尔自治区" | "喀什地区"    | "塔什库尔干塔吉克自治县" || 2 | new ProvinceCityAreaTriple("新疆维吾尔自治区", "喀什地区", "塔什库尔干塔吉克自治县") | Arrays.asList("塔什库尔干塔吉克自治县", "塔什库尔干塔吉克自治")
        "新疆维吾尔自治区" | "喀什地区"    | "岳普湖县"        || 2 | new ProvinceCityAreaTriple("新疆维吾尔自治区", "喀什地区", "岳普湖县")        | Arrays.asList("岳普湖县", "岳普湖")
        "澳门特别行政区"  | "澳门特别行政区" | ""            || 1 | new ProvinceCityAreaTriple("澳门特别行政区", "澳门特别行政区", "")          | Arrays.asList("")
    }


}
