package io.github.qingmo.utils;

public class CharacterUtil {
    /**
     * 全角 to 半角,大写 to 小写
     *
     * @param input
     *            输入字符
     * @return 转换后的字符
     */
    public static char regularize(char input) {
        if (input == 12288) {
            return 32;
        }
        else if (input > 65280 && input < 65375) {
            return (char) (input - 65248);
        }
        else if (input >= 'A' && input <= 'Z') {
            return (input += 32);
        }
        return input;
    }
}
