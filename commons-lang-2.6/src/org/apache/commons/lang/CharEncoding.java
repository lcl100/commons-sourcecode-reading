package org.apache.commons.lang;

import java.io.UnsupportedEncodingException;


/**
 * <p>Java平台的每个实现都需要的字符编码名称。</p>
 * <p>根据JRE字符编码编码名称，Java平台的每个实现都需要支持以下字符编码。</p>
 *
 * @author lcl100
 * @create 2021-07-26 21:29
 */
public class CharEncoding {

    /**
     * <P>ISO拉丁字母 #1，也称为ISO-LATIN-1。</P>
     * <p>Java平台的每个实现都需要支持这种字符编码。</p>
     */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /**
     * <p>七位ASCII，也称为ISO646-US，也称为Unicode字符集的基本拉丁块。</p>
     * <p><i>注：关于"拉丁块"这个词没有找到准确合适的翻译，采用的是机翻。</i></p>
     * <p>Java平台的每个实现都需要支持这种字符编码。</p>
     */
    public static final String US_ASCII = "US-ASCII";

    /**
     * <p>十六位Unicode转换格式，由强制初始字节顺序标记指定的字节的顺序（是高字节在前还是低字节在前，即大端还是小端）</p>
     * <p>UTF-16，没有指定后缀，即不知道其是大小端，所以其开始的两个字节表示该字节数组是大端还是小端。即FE FF表示大端，FF FE表示小端。</p>
     * <p>Java平台的每个实现都需要支持这种字符编码。</p>
     * <p>常量，UTF-8字符集名称，可以直接引用该常量，以免自己手写失误</p>
     */
    public static final String UTF_16 = "UTF-16";


    /**
     * <p>十六位Unicode转换格式，大端字节序。</p>
     * <p>UTF-16BE，其后缀是 BE 即 big-endian，大端的意思。大端就是将高位的字节放在低地址表示。</p>
     * <p>Java平台的每个实现都需要支持这种字符编码。</p>
     * <p>常量，UTF-8字符集名称，可以直接引用该常量，以免自己手写失误</p>
     */
    public static final String UTF_16BE = "UTF-16BE";

    /**
     * <p>十六位Unicode转换格式，小端字节序。</p>
     * <p>UTF-16LE，其后缀是 LE 即 little-endian，小端的意思。小端就是将高位的字节放在高地址表示。</p>
     * <p>Java平台的每个实现都需要支持这种字符编码。</p>
     * <p>常量，UTF-8字符集名称，可以直接引用该常量，以免自己手写失误</p>
     */
    public static final String UTF_16LE = "UTF-16LE";

    /**
     * <p>八位Unicode转换格式。</p>
     * <p>Java平台的每个实现都需要支持这种字符编码。</p>
     * <p>常量，UTF-8字符集名称，可以直接引用该常量，以免自己手写失误</p>
     */
    public static final String UTF_8 = "UTF-8";


    /**
     * <p>判断给定的字符集是否支持，示例：</p>
     * <pre>
     *     CharEncoding.isSupported("utf-8");// true
     *     CharEncoding.isSupported("gbk");// true
     *     CharEncoding.isSupported("abc");// false
     * </pre>
     * <p>通过如下代码可以查看当前虚拟机支持哪些字符集，如下：</p>
     * <pre>
     *     SortedMap<String, Charset> map = Charset.availableCharsets();
     *     for (String alias : map.keySet()) {
     *         System.out.println("别名：" + alias + "\t字符集对象：" + map.get(alias));
     *     }
     * </pre>
     * <p>建议使用{@link java.nio.charset.Charset#isSupported(String)}，因为该方法在编译时（即写代码时）就会提示你是否支持该字符集，而不必等到运行时。</p>
     *
     * @param name 指定的字符集名称，可以是规范名或别名
     * @return 仅当当前Java虚拟机支持该名称的字符集时返回true，否则返回false
     */
    public static boolean isSupported(String name) {
        // 参数是否为null校验
        if (name == null) {
            return false;
        }
        try {
            // 调用String(byte bytes[], String charsetName)构造方法，如果没有抛出异常表示该字符集名称有效
            new String(ArrayUtils.EMPTY_BYTE_ARRAY, name);
        } catch (UnsupportedEncodingException e) {
            // 如果抛出了异常，则捕捉异常，表示该字符集无效，返回false
            return false;
        }
        return true;
    }

}