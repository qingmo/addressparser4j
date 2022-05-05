package io.github.qingmo.database


import spock.lang.Specification

class LocationParseImplTest extends Specification {

    def "test_read_file_from_absolution_path"() {
        when:
        String currentClassPath = getClass().getResource("/").getFile();
        var targetPath = currentClassPath.replaceAll("addressparser4j.*", "addressparser4j")
        var parseImpl = new LocationParseImpl(targetPath + "/src/test/groovy/io/github/qingmo/database/test_for_absolutePath.csv")
        def database = parseImpl.dataFromCsv()
        then:
        database != null
        database.items != null
        database.items.size() == 10
        def firstItem = database.items.get(0)
        firstItem.country == "中国"
        firstItem.province == "吉林省"
        firstItem.city == "吉林市"
        firstItem.area == ""
        firstItem.latitude == "43.87198833435933"
        firstItem.longitude == "126.56454398883336"
    }


    def "test read_file_from_classpath_path"() {
        when:
        var parseImpl = new LocationParseImpl("test.csv")
        def database = parseImpl.dataFromCsv()
        then:
        database != null
        database.items != null
        database.items.size() == 10
        def firstItem = database.items.get(0)
        firstItem.country == "中国"
        firstItem.province == "吉林省"
        firstItem.city == "吉林市"
        firstItem.area == ""
        firstItem.latitude == "43.87198833435933"
        firstItem.longitude == "126.56454398883336"
    }


}
