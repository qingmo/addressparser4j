package io.github.qingmo;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import io.github.qingmo.database.LocationParse;
import io.github.qingmo.database.LocationParseImpl;
import io.github.qingmo.dtos.AddressInfoDTO;
import io.github.qingmo.utils.CharacterUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressParser {

    private final LocationParse locationParse;

    private AddressParser() throws IOException {
        locationParse = new LocationParseImpl("/Users/chaosmo/workspace/addressparser4j/src/main/resources/pca.csv");
    }

    /**
     * cut=False cut:是否使用分词，默认使用，分词模式速度较快，但是准确率可能会有所下降
     * lookahead = 8:只有在cut为false的时候有效，表示最多允许向前看的字符的数量
     * 默认值为8是为了能够发现"新疆维吾尔族自治区"这样的长地名
     * 如果你的样本中都是短地名的话，可以考虑把这个数字调小一点以提高性能
     */
    public static Optional<AddressInfoDTO> parseAddress(String fullAddressStr) {
        if (null == fullAddressStr || "".equals(fullAddressStr.trim())) {
            return Optional.empty();
        }
        // handle 全角，半角
        String fullAddress = fullAddressStr.chars().mapToObj(c -> (char) c).map(CharacterUtil::regularize).map(String::valueOf).collect(Collectors.joining());
        jieba_extract(fullAddress);
        return Optional.empty();
    }

    private static Optional<AddressInfoDTO> jieba_extract(String fullAddressStr) {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<SegToken> segTokens = segmenter.process(fullAddressStr, JiebaSegmenter.SegMode.INDEX);
        for (SegToken segToken : segTokens) {
            System.out.println(segToken.toString());
        }
        return Optional.empty();
    }
}
