package org.apache.commons.lang;

/**
 * <p>对基本数据类型char和包装类型Character对象的处理。</p>
 * <p>此类对null输入有处理，当输入null时不会抛出异常。</p>
 *
 * @author lcl100
 */
public class CharUtils {

    /**
     * 常量，实际上就是ASCII码表上的128位字符，只是这里使用的是unicode表示
     */
    private static final String CHAR_STRING =
            "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007" +
                    "\b\t\n\u000b\f\r\u000e\u000f" +
                    "\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017" +
                    "\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f" +
                    "\u0020\u0021\"\u0023\u0024\u0025\u0026\u0027" +
                    "\u0028\u0029\u002a\u002b\u002c\u002d\u002e\u002f" +
                    "\u0030\u0031\u0032\u0033\u0034\u0035\u0036\u0037" +
                    "\u0038\u0039\u003a\u003b\u003c\u003d\u003e\u003f" +
                    "\u0040\u0041\u0042\u0043\u0044\u0045\u0046\u0047" +
                    "\u0048\u0049\u004a\u004b\u004c\u004d\u004e\u004f" +
                    "\u0050\u0051\u0052\u0053\u0054\u0055\u0056\u0057" +
                    "\u0058\u0059\u005a\u005b\\\u005d\u005e\u005f" +
                    "\u0060\u0061\u0062\u0063\u0064\u0065\u0066\u0067" +
                    "\u0068\u0069\u006a\u006b\u006c\u006d\u006e\u006f" +
                    "\u0070\u0071\u0072\u0073\u0074\u0075\u0076\u0077" +
                    "\u0078\u0079\u007a\u007b\u007c\u007d\u007e\u007f";

    /**
     * 常量，存放ASCII的128个字符的字符串数组
     */
    private static final String[] CHAR_STRING_ARRAY = new String[128];
    /**
     * 常量，Character数组，用来存放字符
     */
    private static final Character[] CHAR_ARRAY = new Character[128];

    /**
     * <p><code>\u000a</code>表示换行符LF('\n')</p>
     * <p>在Java中的字符串中有"\n"会发生换行，字符串中有'\u000a'也会发生换行。</p>
     * <p>例如这句注释代码也会被执行到：<code>// 这是注释\u000a System.out.println("注释的代码不应该被执行");</code></p>
     * <p>常量，用LF来表示'\n'换行符。</p>
     */
    public static final char LF = '\n';

    /**
     * <p><code>\u000d</code>表示回车CR('\r')</p>
     * <p>常量，用CR来表示'\r'回车符。</p>
     */
    public static final char CR = '\r';


    static {
        // 静态代码块，初始化CHAR_STRING_ARRAY数组和CHAR_ARRAY数组
        for (int i = 127; i >= 0; i--) {
            // 其实也是ASCII码表中的128位字符，不过是放进字符串数组中
            CHAR_STRING_ARRAY[i] = CHAR_STRING.substring(i, i + 1);
            // 将ASCII码表中的128位字符放进该Character类型的数组中
            CHAR_ARRAY[i] = new Character((char) i);
        }
    }

    /**
     * <p>构造函数。虽然是public修饰的，但不应该构造该方法的实例对象，因为类中几乎都是静态方法，采用类名直接调用即可。</p>
     */
    public CharUtils() {
        super();
    }

    // toCharacterObject，转换成Character对象的方法
    //-----------------------------------------------------------------------

    /**
     * <p>将基本数据类型char的字符转换成包装类型Character字符。</p>
     * <p>对于ASCII码表中的字符（如'a'、'0'等），会使用缓存，每次都会返回相同的Character对象，它们的hashCode值相同。</p>
     * <pre>
     *     CharUtils.toCharacterObject(' ')  = ' '
     *     CharUtils.toCharacterObject('A')  = 'A'
     * </pre>
     *
     * @param ch 待转换的char类型的字符
     * @return 指定char类型的字符对应的Character类型的对象
     */
    public static Character toCharacterObject(char ch) {
        // 如果字符在ASCII码表中，则使用已经缓存好的CHAR_ARRAY数组中的Character对象，例如'a'、'1'等字符
        if (ch < CHAR_ARRAY.length) {
            return CHAR_ARRAY[ch];
        }
        // 如果不在，则创建新的Character，如'中'等
        return new Character(ch);
    }

    /**
     * <p>将字符串的第一个字符转换成Character值，如果输入为null或空字符串则也返回null。</p>
     * <p>对于ASCII码表中的字符（如'a'、'0'等），会使用缓存，每次都会返回相同的Character对象，它们的hashCode值相同。</p>
     * <pre>
     *     CharUtils.toCharacterObject(null) = null
     *     CharUtils.toCharacterObject("")   = null
     *     CharUtils.toCharacterObject("A")  = 'A'
     *     CharUtils.toCharacterObject("BA") = 'B'
     * </pre>
     *
     * @param str 待转换的字符串
     * @return 字符串中第一个字母的Character值
     */
    public static Character toCharacterObject(String str) {
        // 处理空字符串或null的情况
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        // 转换字符串中的第一个字符
        return toCharacterObject(str.charAt(0));
    }

    // toChar，转换成基本数据类型char的方法
    //-----------------------------------------------------------------------

    /**
     * <p>将Character值转换成char值，如果输入为null则抛出异常。</p>
     * <pre>
     *     CharUtils.toChar(null) = IllegalArgumentException
     *     CharUtils.toChar(' ')  = ' '
     *     CharUtils.toChar('A')  = 'A'
     * </pre>
     *
     * @param ch 待转换的Character类型的字符
     * @return Character类型的值转换成char后的值
     * @throws IllegalArgumentException 如果输入的Character类型参数为null则抛出此异常
     */
    public static char toChar(Character ch) {
        // 处理null输入
        if (ch == null) {
            throw new IllegalArgumentException("The Character must not be null");
        }
        // 实际上是调用Character对象的charValue()方法，将Character值转换成char值
        return ch.charValue();
    }

    /**
     * <p>将Character值转换成char值，如果输入为null则返回默认值。</p>
     * <p><i>注：该重载方法通过配置的方式处理了null输入，而不是抛出异常。</i></p>
     * <pre>
     *     CharUtils.toChar(null, 'X') = 'X'
     *     CharUtils.toChar(' ', 'X')  = ' '
     *     CharUtils.toChar('A', 'X')  = 'A'
     * </pre>
     *
     * @param ch           待转换的Character类型的字符
     * @param defaultValue 当输入为null时返回的值
     * @return Character类型的值转换成char后的值或如果输入为null时返回的默认值
     */
    public static char toChar(Character ch, char defaultValue) {
        // 当输入为null时不会抛出异常，而是返回默认值
        if (ch == null) {
            return defaultValue;
        }
        return ch.charValue();
    }

    // toChar，转换成char类型的值的方法
    //-----------------------------------------------------------------------

    /**
     * <p>将字符串的第一个字符转换成char类型的值，如果是输入字符串为null或为空则抛出异常。</p>
     * <pre>
     *     CharUtils.toChar(null) = IllegalArgumentException
     *     CharUtils.toChar("")   = IllegalArgumentException
     *     CharUtils.toChar("A")  = 'A'
     *     CharUtils.toChar("BA") = 'B'
     * </pre>
     *
     * @param str 待转换的字符串
     * @return 字符串首字母的char值
     * @throws IllegalArgumentException 如果字符串为空或为null
     */
    public static char toChar(String str) {
        // 处理空输入的情况
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException("The String must not be empty");
        }
        // 获取字符串的第一个字符，通过charAt()方法，返回值就是char类型的
        return str.charAt(0);
    }

    /**
     * <p>将字符串的第一个字符转换成char类型的值，如果是输入字符串为null或为空则返回默认值。</p>
     * <pre>
     *     CharUtils.toChar(null, 'X') = 'X'
     *     CharUtils.toChar("", 'X')   = 'X'
     *     CharUtils.toChar("A", 'X')  = 'A'
     *     CharUtils.toChar("BA", 'X') = 'B'
     * </pre>
     *
     * @param str          待转换的字符串
     * @param defaultValue 输入字符串为空或为null时返回的默认值
     * @return 字符串首字母的char值或输入为null时返回的默认值
     */
    public static char toChar(String str, char defaultValue) {
        // 处理输入为null或为空的情况，返回配置的默认值
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        return str.charAt(0);
    }

    // toIntValue，将数字字符转换成对应的数字
    //-----------------------------------------------------------------------

    /**
     * <p>将字符转换成它所代表的整数，如果字符不是数字字符则抛出异常。</p>
     * <p>即该方法将<code>char '1'</code>转换为<code>int 1</code>，依此类推，但对于<code>char 'a'</code>这种字母字符无能为力。</p>
     * <pre>
     *     CharUtils.toIntValue('3')  = 3
     *     CharUtils.toIntValue('A')  = IllegalArgumentException
     * </pre>
     *
     * @param ch 待转换的字符，通常应该是数字字符，但可能会输入字母字符
     * @return 数字字符对应的整数值
     * @throws IllegalArgumentException 如果输入字符不是数字字符
     */
    public static int toIntValue(char ch) {
        // 判断是否是数字字符，如果不是则抛出异常
        if (isAsciiNumeric(ch) == false) {
            throw new IllegalArgumentException("The character " + ch + " is not in the range '0' - '9'");
        }
        // 因为ASCII码表中的第一个数字字符是'0'，所对应的十进制是48，49表示'1'，以此类推...
        return ch - 48;
    }

    /**
     * <p>将字符转换成它所代表的整数，如果字符不是数字字符则抛出异常。</p>
     * <p>即该方法将<code>char '1'</code>转换为<code>int 1</code>，依此类推，但对于<code>char 'a'</code>这种字母字符无能为力。</p>
     * <pre>
     *     CharUtils.toIntValue('3', -1)  = 3
     *     CharUtils.toIntValue('A', -1)  = -1
     * </pre>
     *
     * @param ch           待转换的字符
     * @param defaultValue 输入字符不是数字字符时返回的默认int值
     * @return 字符的int值
     */
    public static int toIntValue(char ch, int defaultValue) {
        // 判断是否是数字字符，如果不是则返回默认值
        if (isAsciiNumeric(ch) == false) {
            return defaultValue;
        }
        return ch - 48;
    }

    /**
     * <p>将指定字符（数字字符）转换为它所代表的整数，如果不是数字字符则返回默认int值。如果输入null则抛出异常。</p>
     * <p>此方法将<code>char '1'</code>转换成<code>int 1</code>，依此类推。</p>
     * <pre>
     *     CharUtils.toIntValue(null) = IllegalArgumentException
     *     CharUtils.toIntValue('3')  = 3
     *     CharUtils.toIntValue('A')  = IllegalArgumentException
     * </pre>
     *
     * @param ch 待转换的字符
     * @return 字符对应的int值
     * @throws IllegalArgumentException 如果字符不是ASCII码表中的数字字符或为null
     */
    public static int toIntValue(Character ch) {
        if (ch == null) {
            throw new IllegalArgumentException("The character must not be null");
        }
        return toIntValue(ch.charValue());
    }

    /**
     * <p>将指定字符（数字字符）转换为它所代表的整数，如果不是数字字符则返回默认值。如果输入null也返回默认int值。</p>
     * <p>此方法将<code>char '1'</code>转换成<code>int 1</code>，依此类推。</p>
     * <pre>
     *     CharUtils.toIntValue(null, -1) = -1
     *     CharUtils.toIntValue('3', -1)  = 3
     *     CharUtils.toIntValue('A', -1)  = -1
     * </pre>
     *
     * @param ch           待转换的数字字符
     * @param defaultValue 输入字符不是数字字符时返回的默认int值
     * @return 字符的int值
     */
    public static int toIntValue(Character ch, int defaultValue) {
        // 如果输入为null则返回默认int值
        if (ch == null) {
            return defaultValue;
        }
        // 调用重载方法
        return toIntValue(ch.charValue(), defaultValue);
    }

    // toString，将字符转换成字符串的方法
    //-----------------------------------------------------------------------

    /**
     * <p>将char类型的字符转换成字符串。</p>
     * <p>对于是在ASCII码表上的字符则会使用缓存数组，每次都会使用相同的String对象。</p>
     * <pre>
     *     CharUtils.toString(' ')  = " "
     *     CharUtils.toString('A')  = "A"
     * </pre>
     *
     * @param ch 待转换的字符
     * @return 返回一个指定字符的字符串
     */
    public static String toString(char ch) {
        // 单独处理ASCII码表上的字符，使用缓存数组，返回的字符串是同一个
        if (ch < 128) {
            return CHAR_STRING_ARRAY[ch];
        }
        // 利用String的构造器方法生成返回字符串
        return new String(new char[]{ch});
    }

    /**
     * <p>将Character类型的字符转换成字符串。</p>
     * <p>对于是在ASCII码表上的字符则会使用缓存数组，每次都会使用相同的String对象。</p>
     * <p>如果输入的参数为null，则将返回null。</p>
     * <pre>
     *     CharUtils.toString(null) = null
     *     CharUtils.toString(' ')  = " "
     *     CharUtils.toString('A')  = "A"
     * </pre>
     *
     * @param ch 待转换的字符
     * @return 返回一个指定字符的字符串
     */
    public static String toString(Character ch) {
        if (ch == null) {
            return null;
        }
        return toString(ch.charValue());
    }


    // unicodeEscaped，将指定字符转换成Unicode编码格式字符串的方法
    //--------------------------------------------------------------------------

    /**
     * <p>将指定char类型的字符转换成Unicode编码格式的字符串。</p>
     * <pre>
     *     CharUtils.unicodeEscaped(' ') = "\u0020"
     *     CharUtils.unicodeEscaped('A') = "\u0041"
     * </pre>
     *
     * @param ch 待转换的字符
     * @return 转换后的Unicode编码格式的字符串
     */
    public static String unicodeEscaped(char ch) {
        if (ch < 0x10) {
            return "\\u000" + Integer.toHexString(ch);
        } else if (ch < 0x100) {
            return "\\u00" + Integer.toHexString(ch);
        } else if (ch < 0x1000) {
            return "\\u0" + Integer.toHexString(ch);
        }
        return "\\u" + Integer.toHexString(ch);
    }

    /**
     * <p>将指定Character类型的字符转换成Unicode编码格式的字符串。</p>
     * <p>如果输入为null，则也将返回null。</p>
     * <pre>
     *     CharUtils.unicodeEscaped(null) = null
     *     CharUtils.unicodeEscaped(' ')  = "\u0020"
     *     CharUtils.unicodeEscaped('A')  = "\u0041"
     * </pre>
     *
     * @param ch 待转换的字符，可能为null
     * @return 转换后的Unicode编码格式的字符串，如果输入为null则返回null
     */
    public static String unicodeEscaped(Character ch) {
        if (ch == null) {
            return null;
        }
        return unicodeEscaped(ch.charValue());
    }

    // 检符是否是ASCII码表中的字符测输入字
    //--------------------------------------------------------------------------

    /**
     * <p>检查输入字符是否是ASCII码表中的字符。</p>
     * <pre>
     *     CharUtils.isAscii('a')  = true
     *     CharUtils.isAscii('A')  = true
     *     CharUtils.isAscii('3')  = true
     *     CharUtils.isAscii('-')  = true
     *     CharUtils.isAscii('\n') = true
     *     CharUtils.isAscii('&copy;') = false
     * </pre>
     *
     * @param ch 待检测的字符
     * @return 如果小于128则是ASCII码表中的字符返回true，否则返回false
     */
    public static boolean isAscii(char ch) {
        // ASCII码表上只有128位字符，分别对应的十进制数是从0到127
        return ch < 128;
    }

    /**
     * <p>检测输入字符是否是ASCII码表中的可打印字符。</p>
     * <p>数字 32–126 分配给了能在键盘上找到的字符，当您查看或打印文档时就会出现。数字127代表 DELETE 命令。</p>
     * <pre>
     *     CharUtils.isAsciiPrintable('a')  = true
     *     CharUtils.isAsciiPrintable('A')  = true
     *     CharUtils.isAsciiPrintable('3')  = true
     *     CharUtils.isAsciiPrintable('-')  = true
     *     CharUtils.isAsciiPrintable('\n') = false
     *     CharUtils.isAsciiPrintable('&copy;') = false
     * </pre>
     *
     * @param ch 待检测的字符
     * @return 如果输入字符介于[32, 126]之间则表示是可打印字符返回true，否则返回false
     */
    public static boolean isAsciiPrintable(char ch) {
        // ASCII码表规定了可打印字符是[32,127)
        // 其中32表示空格字符' '；其中127表示'~'字符
        return ch >= 32 && ch < 127;
    }

    /**
     * <p>检测输入字符是否是ASCII码表中的控制字符。</p>
     * <p>ASCII表上的数字0–31分配给了控制字符，用于控制像打印机等一些外围设备。127代表DELETE命令。</p>
     * <pre>
     *     CharUtils.isAsciiControl('a')  = false
     *     CharUtils.isAsciiControl('A')  = false
     *     CharUtils.isAsciiControl('3')  = false
     *     CharUtils.isAsciiControl('-')  = false
     *     CharUtils.isAsciiControl('\n') = true
     *     CharUtils.isAsciiControl('&copy;') = false
     * </pre>
     *
     * @param ch 待检测的字符
     * @return 如果小于32或等于127则返回true
     */
    public static boolean isAsciiControl(char ch) {
        return ch < 32 || ch == 127;
    }

    /**
     * <p>检测指定字符是否是ASCII码表中的字母字符（包括大写字母和小写字母）。</p>
     * <pre>
     *     CharUtils.isAsciiAlpha('a')  = true
     *     CharUtils.isAsciiAlpha('A')  = true
     *     CharUtils.isAsciiAlpha('3')  = false
     *     CharUtils.isAsciiAlpha('-')  = false
     *     CharUtils.isAsciiAlpha('\n') = false
     *     CharUtils.isAsciiAlpha('&copy;') = false
     * </pre>
     *
     * @param ch 待检测的字符
     * @return 如果是大写字母字符或小写字母字符则返回true
     */
    public static boolean isAsciiAlpha(char ch) {
        // 判断是否是字母字符
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    /**
     * <p>检测输入字符是否是ASCII码表中的大写字母字符。</p>
     * <pre>
     *     CharUtils.isAsciiAlphaUpper('a')  = false
     *     CharUtils.isAsciiAlphaUpper('A')  = true
     *     CharUtils.isAsciiAlphaUpper('3')  = false
     *     CharUtils.isAsciiAlphaUpper('-')  = false
     *     CharUtils.isAsciiAlphaUpper('\n') = false
     *     CharUtils.isAsciiAlphaUpper('&copy;') = false
     * </pre>
     *
     * @param ch 待检测的字符
     * @return 如果是大写字母字符则返回true
     */
    public static boolean isAsciiAlphaUpper(char ch) {
        // 大写字符：['A', 'Z']
        return ch >= 'A' && ch <= 'Z';
    }

    /**
     * <p>检测输入字符是否是ASCII码表中的小写字母字符。</p>
     * <pre>
     *     CharUtils.isAsciiAlphaUpper('a')  = true
     *     CharUtils.isAsciiAlphaUpper('A')  = false
     *     CharUtils.isAsciiAlphaUpper('3')  = false
     *     CharUtils.isAsciiAlphaUpper('-')  = false
     *     CharUtils.isAsciiAlphaUpper('\n') = false
     *     CharUtils.isAsciiAlphaUpper('&copy;') = false
     * </pre>
     *
     * @param ch 待检测的字符
     * @return 如果是小写字母字符则返回true
     */
    public static boolean isAsciiAlphaLower(char ch) {
        // 小写字母字符：['a', 'z']
        return ch >= 'a' && ch <= 'z';
    }

    /**
     * <p>判断输入的字符是否是ASCII码表中的数字字符（'0','1','2','3','4','5','6','7','8','9'）。</p>
     * <pre>
     *   CharUtils.isAsciiNumeric('a')  = false
     *   CharUtils.isAsciiNumeric('A')  = false
     *   CharUtils.isAsciiNumeric('3')  = true
     *   CharUtils.isAsciiNumeric('-')  = false
     *   CharUtils.isAsciiNumeric('\n') = false
     *   CharUtils.isAsciiNumeric('&copy;') = false
     * </pre>
     *
     * @param ch 待检查的字符
     * @return 如果是数字字符则返回true，否则返回false
     */
    public static boolean isAsciiNumeric(char ch) {
        // 数字字符即是'0','1','2','3','4','5','6','7','8','9'
        return ch >= '0' && ch <= '9';
    }

    /**
     * <p>检查输入字符是否为ASCII码表中的字母字符（包括大写字母字符和小写字母字符）和数字字符</p>
     * <pre>
     *     CharUtils.isAsciiAlphanumeric('a')  = true
     *     CharUtils.isAsciiAlphanumeric('A')  = true
     *     CharUtils.isAsciiAlphanumeric('3')  = true
     *     CharUtils.isAsciiAlphanumeric('-')  = false
     *     CharUtils.isAsciiAlphanumeric('\n') = false
     *     CharUtils.isAsciiAlphanumeric('&copy;') = false
     * </pre>
     *
     * @param ch 待检查的字符
     * @return 如果字符为位于['A','Z']或['a','z']或['0','9']之间则返回true，否则返回false
     */
    public static boolean isAsciiAlphanumeric(char ch) {
        // 判断输入字符是否是字母字符和数字字符
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
    }

    // ----------------- Following code copied from Apache Harmony (Character class)

    /**
     * Indicates whether {@code ch} is a high- (or leading-) surrogate code unit
     * that is used for representing supplementary characters in UTF-16
     * encoding.
     *
     * @param ch the character to test.
     * @return {@code true} if {@code ch} is a high-surrogate code unit;
     * {@code false} otherwise.
     */
    static boolean isHighSurrogate(char ch) {
        return ('\uD800' <= ch && '\uDBFF' >= ch);
    }

}
