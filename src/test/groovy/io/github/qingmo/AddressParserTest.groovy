package io.github.qingmo

import spock.lang.Specification

class AddressParserTest extends Specification {


    def "test_error_area"() {
        expect:
        var b = AddressParser.parseAddress(a).orElseThrow(()->new RuntimeException())
        b.provience == c
        b.city == d
        b.area == e
        b.location == f
        where:
        a                              || c     | d     | e     | f
        "北京市昌平区昌平路97号新元科技园B座504"       || "北京市" | "北京市" | "昌平区" | "昌平路97号新元科技园B座504"
        "上海经静安区大田路靠近北京西路"              || "上海市" | "上海市" | "静安区" | "经静安区大田路靠近北京西路"
        "青岛市市南区浙江路14号2楼"               || "山东省" | "青岛市" | "市南区" | "浙江路14号2楼"
        "北京市朝阳区裕民路12号中国国际科技会展中心A座1005" || "北京市" | "北京市" | "朝阳区" | "裕民路12号中国国际科技会展中心A座1005"
        "杭州市下城区朝晖路168号钛合国际A座1204室"     || "浙江省" | "杭州市" | "下城区" | "朝晖路168号钛合国际A座1204室"
    }
}
