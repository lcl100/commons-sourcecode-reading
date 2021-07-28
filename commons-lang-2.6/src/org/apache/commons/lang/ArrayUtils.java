package org.apache.commons.lang;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>对对象数组、基本数据类型数组（如int[]）和包装对象类型（如Integer[]）的操作</p>
 * <p>该类尝试对null输入进行更优雅的处理，当输入null时不会抛出异常。但是，包含null元素的Object数组可能会引发异常。</p>
 * <p>线程安全</p>
 *
 * @author lcl100
 * @create 2021-07-27 21:03
 */
public class ArrayUtils {

    // 常量，Object、Class、String、八大基本数据类型和八大包装类的空数组
    // -----------------------------------------------------------------------

    /**
     * 常量，一个空的不可变Object数组。
     */
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    /**
     * 常量，一个空的不可变Class数组。
     */
    public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    /**
     * 常量，一个空的不可变String数组。
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    /**
     * 常量，一个空的不可变long数组。
     */
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    /**
     * 常量，一个空的不可变Long数组。
     */
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    /**
     * 常量，一个空的不可变int数组。
     */
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    /**
     * 常量，一个空的不可变Integer数组。
     */
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    /**
     * 常量，一个空的不可变short数组。
     */
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    /**
     * 常量，一个空的不可变Short数组。
     */
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    /**
     * 常量，一个空的不可变byte数组。
     */
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /**
     * 常量，一个空的不可变Byte数组。
     */
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    /**
     * 常量，一个空的不可变double数组。
     */
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    /**
     * 常量，一个空的不可变Double数组。
     */
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    /**
     * 常量，一个空的不可变float数组。
     */
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    /**
     * 常量，一个空的不可变Float数组。
     */
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    /**
     * 常量，一个空的不可变boolean数组。
     */
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    /**
     * 常量，一个空的不可变Boolean数组。
     */
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    /**
     * 常量，一个空的不可变char数组。
     */
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    /**
     * 常量，一个空的不可变Character数组。
     */
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];

    /**
     * 常量，当在集合或数组中找不到元素时返回的特殊索引值：-1。也是一个标记量，用来表示未找到索引时返回的索引值。
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * 构造方法，但该类中的方法都是静态方法，所以不应该创建该类的实例对象。
     */
    public ArrayUtils() {
        super();
    }

    // 处理多维数组的基本方法
    //-----------------------------------------------------------------------

    /**
     * <p>将数组作为字符串输出，将null视为空数组。正确处理多维数组，包括多维原始数组。</p>
     * <p>格式是Java源代码的格式，例如：{a, b}。</p>
     *
     * @param array 要将其输出为字符串的指定数组，可能为null
     * @return 数组的字符串表示形式，如果数组为空则输出"{}"
     */
    public static String toString(Object array) {
        // 当数组为null时，默认输出"{}"
        return toString(array, "{}");
    }

    /**
     * <p>将数组输出为String字符串处理。格式为Java源代码格式，例如：{a, b}。</p>
     *
     * @param array        待输出的数组，可能为null
     * @param stringIfNull 当数组为null时返回的字符串，例如array为null则返回空字符串"{}"
     * @return 数组的字符串表示
     */
    public static String toString(Object array, String stringIfNull) {
        // 如果传入的数组为null，则输出指定的字符串stringIfNull
        if (array == null) {
            return stringIfNull;
        }
        // 如果传入的数组不为null，则将数组输出为字符串
        return new ToStringBuilder(array, ToStringStyle.SIMPLE_STYLE).append(array).toString();
    }

    /**
     * <p>获取数组的hashCode值。还可以正确处理多维原始数组。</p>
     *
     * @param array 要为其获取hashCode值的数组
     * @return 给定数组的hashCode值，如果输入数组为null则返回0
     */
    public static int hashCode(Object array) {
        return new HashCodeBuilder().append(array).toHashCode();
    }

    /**
     * 比较两个数组是否相等，使用equals()方法
     *
     * @param array1 要比较的第一个数组，可能为null
     * @param array2 要比较的第二个数组，可能为null
     * @return 如果数组相等则返回true
     */
    public static boolean isEquals(Object array1, Object array2) {
        return new EqualsBuilder().append(array1, array2).isEquals();
    }

    // To map，将数组转换成Map集合的方法
    //-----------------------------------------------------------------------

    /**
     * <p>将给定的数组转换成{@link Map}。数组的每个元素都必须是{@link Map.Entry}或Array，至少包含两个元素，其中第一个元素用作Map中的键，第二个元素用作Map中的值。</p>
     * <p>此方法可以用于初始化：</p>
     * <pre>
     *     // Create a Map mapping colors.
     *     Map colorMap = MapUtils.toMap(new String[][] {{
     *         {"RED", "#FF0000"},
     *         {"GREEN", "#00FF00"},
     *         {"BLUE", "#0000FF"}});
     * </pre>
     * <p>当输入的数组array为null时，则返回null</p>
     *
     * @param array 元素为{@link Map.Entry}或包含至少两个元素的数组（即二维数组，应该是n行2列），可能为null
     * @return 返回根据数组创建的Map集合
     * @throws IllegalArgumentException 如果此Array的一个元素本身就是一个包含少于两个元素的Array（即如果传入的array参数是一个一维数组则抛出此异常）。
     * @throws IllegalArgumentException 如果数组包含除{@link Map.Entry}和数组之外的元素
     */
    public static Map toMap(Object[] array) {
        // 参数校验，如果为null则直接返回null
        if (array == null) {
            return null;
        }
        // 创建一个是输入数组1.5倍长度的Map集合
        // 因为传入的如果是一个二维数组，那么array.length获取的是二维数组的行数，有多少行就应该有多少个键值对
        final Map map = new HashMap((int) (array.length * 1.5));
        // 循环遍历输入数组
        for (int i = 0; i < array.length; i++) {
            // 如果传入的array是二维数组，那么array[i]就表示二维数组中的每一行（一维数组）
            // 如果传入的array是Map.Entry一维数组，那么array[i]就表示一维数组中的每个Map.Entry元素
            Object object = array[i];
            // 进行元素类型判断，判断是array[i]是Map.Entry还是一维数组
            if (object instanceof Map.Entry) {
                // 强制类型转换
                Map.Entry entry = (Map.Entry) object;
                // 添加到Map集合中
                map.put(entry.getKey(), entry.getValue());
            } else if (object instanceof Object[]) {
                // 强制类型转换
                Object[] entry = (Object[]) object;
                // 判断每行有几列，如果小于2列，那么就没有添加的必要，直接抛出异常
                if (entry.length < 2) {
                    throw new IllegalArgumentException("Array element " + i + ", '"
                            + object
                            + "', has a length less than 2");
                }
                // 执行到这，表示至少每行有2列，至于更多列也不会被添加到Map集合中，忽略即可
                // 将每行的第一列作为键值对中的键、第二列作为键值对中的值添加到Map集合中
                map.put(entry[0], entry[1]);
            } else {
                // 执行到这，表示array[i]既不是Map.Entry又不是一维数组，无法完成数组转换Map集合，抛出异常
                throw new IllegalArgumentException("Array element " + i + ", '"
                        + object
                        + "', is neither of type Map.Entry nor an Array");
            }
        }
        // 返回转换成功的Map集合
        return map;
    }

    // Clone，浅克隆数组
    //-----------------------------------------------------------------------

    /**
     * <p>浅克隆一个数组，返回一个类型转换结果并处理null。</p>
     * <p>数组中的对象不会被克隆，因此是浅克隆，对多维数组也没有特殊处理。</p>
     * <p><i>注：如果要想数组中的对象被克隆，那么需要使用深克隆。</i></p>
     * <p>如果传入的数组为null，则返回null</p>
     *
     * @param array 要进行浅克隆的数组，可能为null
     * @return 克隆后的数组，如果输入为null则返回null
     */
    public static Object[] clone(Object[] array) {
        // 判断数组是否为null，如果为null则直接返回null
        if (array == null) {
            return null;
        }
        // 浅克隆只需要直接调用对象类的clone()方法即可
        return (Object[]) array.clone();
    }

    /**
     * <p>浅克隆一个数组，返回一个类型转换结果并处理null。</p>
     * <p>数组中的对象不会被克隆，因此是浅克隆，对多维数组也没有特殊处理。</p>
     * <p><i>注：如果要想数组中的对象被克隆，那么需要使用深克隆。</i></p>
     * <p>如果传入的数组为null，则返回null</p>
     * <p><i>注：下面都是八大基本数据类型的浅克隆方法重载。</i></p>
     *
     * @param array 要进行浅克隆的数组，可能为null
     * @return 克隆后的数组，如果输入为null则返回null
     */
    public static long[] clone(long[] array) {
        if (array == null) {
            return null;
        }
        return (long[]) array.clone();
    }

    /**
     * <p>浅克隆一个数组，返回一个类型转换结果并处理null。</p>
     * <p>数组中的对象不会被克隆，因此是浅克隆，对多维数组也没有特殊处理。</p>
     * <p><i>注：如果要想数组中的对象被克隆，那么需要使用深克隆。</i></p>
     * <p>如果传入的数组为null，则返回null</p>
     * <p><i>注：下面都是八大基本数据类型的浅克隆方法重载。</i></p>
     *
     * @param array 要进行浅克隆的数组，可能为null
     * @return 克隆后的数组，如果输入为null则返回null
     */
    public static int[] clone(int[] array) {
        if (array == null) {
            return null;
        }
        return (int[]) array.clone();
    }

    /**
     * <p>浅克隆一个数组，返回一个类型转换结果并处理null。</p>
     * <p>数组中的对象不会被克隆，因此是浅克隆，对多维数组也没有特殊处理。</p>
     * <p><i>注：如果要想数组中的对象被克隆，那么需要使用深克隆。</i></p>
     * <p>如果传入的数组为null，则返回null</p>
     * <p><i>注：下面都是八大基本数据类型的浅克隆方法重载。</i></p>
     *
     * @param array 要进行浅克隆的数组，可能为null
     * @return 克隆后的数组，如果输入为null则返回null
     */
    public static short[] clone(short[] array) {
        if (array == null) {
            return null;
        }
        return (short[]) array.clone();
    }

    /**
     * <p>浅克隆一个数组，返回一个类型转换结果并处理null。</p>
     * <p>数组中的对象不会被克隆，因此是浅克隆，对多维数组也没有特殊处理。</p>
     * <p><i>注：如果要想数组中的对象被克隆，那么需要使用深克隆。</i></p>
     * <p>如果传入的数组为null，则返回null</p>
     * <p><i>注：下面都是八大基本数据类型的浅克隆方法重载。</i></p>
     *
     * @param array 要进行浅克隆的数组，可能为null
     * @return 克隆后的数组，如果输入为null则返回null
     */
    public static char[] clone(char[] array) {
        if (array == null) {
            return null;
        }
        return (char[]) array.clone();
    }

    /**
     * <p>浅克隆一个数组，返回一个类型转换结果并处理null。</p>
     * <p>数组中的对象不会被克隆，因此是浅克隆，对多维数组也没有特殊处理。</p>
     * <p><i>注：如果要想数组中的对象被克隆，那么需要使用深克隆。</i></p>
     * <p>如果传入的数组为null，则返回null</p>
     * <p><i>注：下面都是八大基本数据类型的浅克隆方法重载。</i></p>
     *
     * @param array 要进行浅克隆的数组，可能为null
     * @return 克隆后的数组，如果输入为null则返回null
     */
    public static byte[] clone(byte[] array) {
        if (array == null) {
            return null;
        }
        return (byte[]) array.clone();
    }

    /**
     * <p>浅克隆一个数组，返回一个类型转换结果并处理null。</p>
     * <p>数组中的对象不会被克隆，因此是浅克隆，对多维数组也没有特殊处理。</p>
     * <p><i>注：如果要想数组中的对象被克隆，那么需要使用深克隆。</i></p>
     * <p>如果传入的数组为null，则返回null</p>
     * <p><i>注：下面都是八大基本数据类型的浅克隆方法重载。</i></p>
     *
     * @param array 要进行浅克隆的数组，可能为null
     * @return 克隆后的数组，如果输入为null则返回null
     */
    public static double[] clone(double[] array) {
        if (array == null) {
            return null;
        }
        return (double[]) array.clone();
    }

    /**
     * <p>浅克隆一个数组，返回一个类型转换结果并处理null。</p>
     * <p>数组中的对象不会被克隆，因此是浅克隆，对多维数组也没有特殊处理。</p>
     * <p><i>注：如果要想数组中的对象被克隆，那么需要使用深克隆。</i></p>
     * <p>如果传入的数组为null，则返回null</p>
     * <p><i>注：下面都是八大基本数据类型的浅克隆方法重载。</i></p>
     *
     * @param array 要进行浅克隆的数组，可能为null
     * @return 克隆后的数组，如果输入为null则返回null
     */
    public static float[] clone(float[] array) {
        if (array == null) {
            return null;
        }
        return (float[]) array.clone();
    }

    /**
     * <p>浅克隆一个数组，返回一个类型转换结果并处理null。</p>
     * <p>数组中的对象不会被克隆，因此是浅克隆，对多维数组也没有特殊处理。</p>
     * <p><i>注：如果要想数组中的对象被克隆，那么需要使用深克隆。</i></p>
     * <p>如果传入的数组为null，则返回null</p>
     * <p><i>注：下面都是八大基本数据类型的浅克隆方法重载。</i></p>
     *
     * @param array 要进行浅克隆的数组，可能为null
     * @return 克隆后的数组，如果输入为null则返回null
     */
    public static boolean[] clone(boolean[] array) {
        if (array == null) {
            return null;
        }
        return (boolean[]) array.clone();
    }

    // nullToEmpty，检查null数组或空数组的方法，如果传入的数组为空或为null则返回一个空数组
    //-----------------------------------------------------------------------

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static Object[] nullToEmpty(Object[] array) {
        // 如果传入的数组为null或为空，则返回在该类定义的常量空数组
        if (array == null || array.length == 0) {
            return EMPTY_OBJECT_ARRAY;
        }
        // 如果不为null并且不为空，那么直接返回原数组即可
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static String[] nullToEmpty(String[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_STRING_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static long[] nullToEmpty(long[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static int[] nullToEmpty(int[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static short[] nullToEmpty(short[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static char[] nullToEmpty(char[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static byte[] nullToEmpty(byte[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static double[] nullToEmpty(double[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static float[] nullToEmpty(float[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static boolean[] nullToEmpty(boolean[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static Long[] nullToEmpty(Long[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_LONG_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static Integer[] nullToEmpty(Integer[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_INTEGER_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static Short[] nullToEmpty(Short[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_SHORT_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static Character[] nullToEmpty(Character[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static Byte[] nullToEmpty(Byte[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_BYTE_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static Double[] nullToEmpty(Double[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static Float[] nullToEmpty(Float[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_FLOAT_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>此方法为null输入数组，返回一个空数组。</p>
     * <p>作为内存优化技术，传入的空数组将被此类中的空public static引用覆盖。</p>
     *
     * @param array 要检查null或空的数组
     * @return 相同的数组，如果为null或空输入，则返回public static空数组
     */
    public static Boolean[] nullToEmpty(Boolean[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        }
        return array;
    }

    // Subarrays，获取指定数组的子数组的方法
    //-----------------------------------------------------------------------

    /**
     * <p>生成一个包含开始和结束索引之间的元素的新数组</p>
     * <p>开始索引是包含的，结束索引是不包含的，如[start, end)。空数组输入产生空输出。</p>
     * <p>子数组类型始终与输入数组类型相同。</p>
     *
     * @param array               完整的数组
     * @param startIndexInclusive 开始索引，如果输入值小于0则提升为0，如果输入值大于array.length则返回空数组
     * @param endIndexExclusive   返回的子数组中存在直到endIndex-1的元素，不包括结束索引所指向的元素。如果小于startIndex则返回空数组，如果大于array.length则被降为数组的长度
     * @return 返回包含开始和结束索引之间的元素的新数组
     */
    public static Object[] subarray(Object[] array, int startIndexInclusive, int endIndexExclusive) {
        // 参数校验
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            // 如果startIndex小于0，则提升到0
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            // 如果结束索引大于array.length，则下降到array.length
            endIndexExclusive = array.length;
        }
        // 计算子数组的新长度，即元素个数
        int newSize = endIndexExclusive - startIndexInclusive;

        // 由于传入的数组array是Object类型的，所以具体不清楚数组是什么类型的，可能是int、String等，所以需要通过如下方法获取到数组的真实类型
        Class type = array.getClass().getComponentType();
        // 再通过Array.newInstance()方法创建一个数组实例对象
        // 但如果newSize小于等于0，那么就创建一个空的数组返回
        if (newSize <= 0) {
            return (Object[]) Array.newInstance(type, 0);
        }
        // 如果不是，则创建指定长度的子数组出来
        Object[] subarray = (Object[]) Array.newInstance(type, newSize);
        // 调用System.arraycopy()方法将原数组startIndex索引出的newSize个元素复制到新的子数组中
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>生成一个包含开始和结束索引之间的元素的新的long类型的数组</p>
     * <p>开始索引是包含的，结束索引是不包含的，如[start, end)。空数组输入产生空输出。</p>
     * <p>子数组类型始终与输入数组类型相同。</p>
     * <p><i>下面的subarray重载方法代码大体相似，只是基本数据类型不同而已，只需要看一个即可。</i></p>
     *
     * @param array               完整的数组
     * @param startIndexInclusive 开始索引，如果输入值小于0则提升为0，如果输入值大于array.length则返回空数组
     * @param endIndexExclusive   返回的子数组中存在直到endIndex-1的元素，不包括结束索引所指向的元素。如果小于startIndex则返回空数组，如果大于array.length则被降为数组的长度
     * @return 返回包含开始和结束索引之间的元素的新数组
     */
    public static long[] subarray(long[] array, int startIndexInclusive, int endIndexExclusive) {
        // 参数校验
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            // 直接返回已经创建好的空long数组
            return EMPTY_LONG_ARRAY;
        }

        // 直接创建基本数据类型long类型的数组
        long[] subarray = new long[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>生成一个包含开始和结束索引之间的元素的新的int类型的数组</p>
     * <p>开始索引是包含的，结束索引是不包含的，如[start, end)。空数组输入产生空输出。</p>
     * <p>子数组类型始终与输入数组类型相同。</p>
     * <p><i>下面的subarray重载方法代码大体相似，只是基本数据类型不同而已，只需要看一个即可。</i></p>
     *
     * @param array               完整的数组
     * @param startIndexInclusive 开始索引，如果输入值小于0则提升为0，如果输入值大于array.length则返回空数组
     * @param endIndexExclusive   返回的子数组中存在直到endIndex-1的元素，不包括结束索引所指向的元素。如果小于startIndex则返回空数组，如果大于array.length则被降为数组的长度
     * @return 返回包含开始和结束索引之间的元素的新数组
     */
    public static int[] subarray(int[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_INT_ARRAY;
        }

        int[] subarray = new int[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>生成一个包含开始和结束索引之间的元素的新的short类型的数组</p>
     * <p>开始索引是包含的，结束索引是不包含的，如[start, end)。空数组输入产生空输出。</p>
     * <p>子数组类型始终与输入数组类型相同。</p>
     * <p><i>下面的subarray重载方法代码大体相似，只是基本数据类型不同而已，只需要看一个即可。</i></p>
     *
     * @param array               完整的数组
     * @param startIndexInclusive 开始索引，如果输入值小于0则提升为0，如果输入值大于array.length则返回空数组
     * @param endIndexExclusive   返回的子数组中存在直到endIndex-1的元素，不包括结束索引所指向的元素。如果小于startIndex则返回空数组，如果大于array.length则被降为数组的长度
     * @return 返回包含开始和结束索引之间的元素的新数组
     */
    public static short[] subarray(short[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_SHORT_ARRAY;
        }

        short[] subarray = new short[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>生成一个包含开始和结束索引之间的元素的新的char类型的数组</p>
     * <p>开始索引是包含的，结束索引是不包含的，如[start, end)。空数组输入产生空输出。</p>
     * <p>子数组类型始终与输入数组类型相同。</p>
     * <p><i>下面的subarray重载方法代码大体相似，只是基本数据类型不同而已，只需要看一个即可。</i></p>
     *
     * @param array               完整的数组
     * @param startIndexInclusive 开始索引，如果输入值小于0则提升为0，如果输入值大于array.length则返回空数组
     * @param endIndexExclusive   返回的子数组中存在直到endIndex-1的元素，不包括结束索引所指向的元素。如果小于startIndex则返回空数组，如果大于array.length则被降为数组的长度
     * @return 返回包含开始和结束索引之间的元素的新数组
     */
    public static char[] subarray(char[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_CHAR_ARRAY;
        }

        char[] subarray = new char[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>生成一个包含开始和结束索引之间的元素的新的byte类型的数组</p>
     * <p>开始索引是包含的，结束索引是不包含的，如[start, end)。空数组输入产生空输出。</p>
     * <p>子数组类型始终与输入数组类型相同。</p>
     * <p><i>下面的subarray重载方法代码大体相似，只是基本数据类型不同而已，只需要看一个即可。</i></p>
     *
     * @param array               完整的数组
     * @param startIndexInclusive 开始索引，如果输入值小于0则提升为0，如果输入值大于array.length则返回空数组
     * @param endIndexExclusive   返回的子数组中存在直到endIndex-1的元素，不包括结束索引所指向的元素。如果小于startIndex则返回空数组，如果大于array.length则被降为数组的长度
     * @return 返回包含开始和结束索引之间的元素的新数组
     */
    public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_BYTE_ARRAY;
        }

        byte[] subarray = new byte[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>生成一个包含开始和结束索引之间的元素的新的double类型的数组</p>
     * <p>开始索引是包含的，结束索引是不包含的，如[start, end)。空数组输入产生空输出。</p>
     * <p>子数组类型始终与输入数组类型相同。</p>
     * <p><i>下面的subarray重载方法代码大体相似，只是基本数据类型不同而已，只需要看一个即可。</i></p>
     *
     * @param array               完整的数组
     * @param startIndexInclusive 开始索引，如果输入值小于0则提升为0，如果输入值大于array.length则返回空数组
     * @param endIndexExclusive   返回的子数组中存在直到endIndex-1的元素，不包括结束索引所指向的元素。如果小于startIndex则返回空数组，如果大于array.length则被降为数组的长度
     * @return 返回包含开始和结束索引之间的元素的新数组
     */
    public static double[] subarray(double[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_DOUBLE_ARRAY;
        }

        double[] subarray = new double[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>生成一个包含开始和结束索引之间的元素的新的float类型的数组</p>
     * <p>开始索引是包含的，结束索引是不包含的，如[start, end)。空数组输入产生空输出。</p>
     * <p>子数组类型始终与输入数组类型相同。</p>
     * <p><i>下面的subarray重载方法代码大体相似，只是基本数据类型不同而已，只需要看一个即可。</i></p>
     *
     * @param array               完整的数组
     * @param startIndexInclusive 开始索引，如果输入值小于0则提升为0，如果输入值大于array.length则返回空数组
     * @param endIndexExclusive   返回的子数组中存在直到endIndex-1的元素，不包括结束索引所指向的元素。如果小于startIndex则返回空数组，如果大于array.length则被降为数组的长度
     * @return 返回包含开始和结束索引之间的元素的新数组
     */
    public static float[] subarray(float[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_FLOAT_ARRAY;
        }

        float[] subarray = new float[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>生成一个包含开始和结束索引之间的元素的新的boolean类型的数组</p>
     * <p>开始索引是包含的，结束索引是不包含的，如[start, end)。空数组输入产生空输出。</p>
     * <p>子数组类型始终与输入数组类型相同。</p>
     * <p><i>下面的subarray重载方法代码大体相似，只是基本数据类型不同而已，只需要看一个即可。</i></p>
     *
     * @param array               完整的数组
     * @param startIndexInclusive 开始索引，如果输入值小于0则提升为0，如果输入值大于array.length则返回空数组
     * @param endIndexExclusive   返回的子数组中存在直到endIndex-1的元素，不包括结束索引所指向的元素。如果小于startIndex则返回空数组，如果大于array.length则被降为数组的长度
     * @return 返回包含开始和结束索引之间的元素的新数组
     */
    public static boolean[] subarray(boolean[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }

        boolean[] subarray = new boolean[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    // isSameLength方法及其重载方法，判断给定的两个数组长度是否相同
    //-----------------------------------------------------------------------

    /**
     * <p>检查两个数组的长度是否相同，将null数组视为长度0。</p>
     * <pre>
     *     ArrayUtils.isSameLength(new Object[0], null);// true
     *     ArrayUtils.isSameLength((Object[]) null, (Object[]) null);// true
     *     ArrayUtils.isSameLength(new String[1], new String[2]);// false
     * </pre>
     *
     * @param array1 第一个待比较的数组，可能为null
     * @param array2 第二个待比较的数组，可能为null
     * @return 如果两个数组的长度相等，则返回true，将null视为空数组
     */
    public static boolean isSameLength(Object[] array1, Object[] array2) {
        if ((array1 == null && array2 != null && array2.length > 0) ||
                (array2 == null && array1 != null && array1.length > 0) ||
                (array1 != null && array2 != null && array1.length != array2.length)) {
            return false;
        }
        return true;
    }

    /**
     * <p>检查两个数组的长度是否相同，将null数组视为长度0。</p>
     * <pre>
     *     ArrayUtils.isSameLength(new long[0], new long[0]);// true
     *     ArrayUtils.isSameLength(new long[1], new long[2]);// false
     * </pre>
     * <p><i>注：下面的几种重载方法都是基本数据类型（byte、short、int、long、float、double、boolean、char）</i></p>
     *
     * @param array1 第一个待比较的数组，可能为null
     * @param array2 第二个待比较的数组，可能为null
     * @return 如果两个数组的长度相等，则返回true，将null视为空数组
     */
    public static boolean isSameLength(long[] array1, long[] array2) {
        if ((array1 == null && array2 != null && array2.length > 0) ||
                (array2 == null && array1 != null && array1.length > 0) ||
                (array1 != null && array2 != null && array1.length != array2.length)) {
            return false;
        }
        return true;
    }

    /**
     * <p>检查两个数组的长度是否相同，将null数组视为长度0。</p>
     * <pre>
     *     ArrayUtils.isSameLength(new int[0], new int[0]);// true
     *     ArrayUtils.isSameLength(new int[1], new int[2]);// false
     * </pre>
     * <p><i>注：下面的几种重载方法都是基本数据类型（byte、short、int、long、float、double、boolean、char）</i></p>
     *
     * @param array1 第一个待比较的数组，可能为null
     * @param array2 第二个待比较的数组，可能为null
     * @return 如果两个数组的长度相等，则返回true，将null视为空数组
     */
    public static boolean isSameLength(int[] array1, int[] array2) {
        if ((array1 == null && array2 != null && array2.length > 0) ||
                (array2 == null && array1 != null && array1.length > 0) ||
                (array1 != null && array2 != null && array1.length != array2.length)) {
            return false;
        }
        return true;
    }

    /**
     * <p>检查两个数组的长度是否相同，将null数组视为长度0。</p>
     * <pre>
     *     ArrayUtils.isSameLength(new short[0], new short[0]);// true
     *     ArrayUtils.isSameLength(new short[1], new short[2]);// false
     * </pre>
     * <p><i>注：下面的几种重载方法都是基本数据类型（byte、short、int、long、float、double、boolean、char）</i></p>
     *
     * @param array1 第一个待比较的数组，可能为null
     * @param array2 第二个待比较的数组，可能为null
     * @return 如果两个数组的长度相等，则返回true，将null视为空数组
     */
    public static boolean isSameLength(short[] array1, short[] array2) {
        if ((array1 == null && array2 != null && array2.length > 0) ||
                (array2 == null && array1 != null && array1.length > 0) ||
                (array1 != null && array2 != null && array1.length != array2.length)) {
            return false;
        }
        return true;
    }

    /**
     * <p>检查两个数组的长度是否相同，将null数组视为长度0。</p>
     * <pre>
     *     ArrayUtils.isSameLength(new char[0], new char[0]);// true
     *     ArrayUtils.isSameLength(new char[1], new char[2]);// false
     * </pre>
     * <p><i>注：下面的几种重载方法都是基本数据类型（byte、short、int、long、float、double、boolean、char）</i></p>
     *
     * @param array1 第一个待比较的数组，可能为null
     * @param array2 第二个待比较的数组，可能为null
     * @return 如果两个数组的长度相等，则返回true，将null视为空数组
     */
    public static boolean isSameLength(char[] array1, char[] array2) {
        if ((array1 == null && array2 != null && array2.length > 0) ||
                (array2 == null && array1 != null && array1.length > 0) ||
                (array1 != null && array2 != null && array1.length != array2.length)) {
            return false;
        }
        return true;
    }

    /**
     * <p>检查两个数组的长度是否相同，将null数组视为长度0。</p>
     * <pre>
     *     ArrayUtils.isSameLength(new byte[0], new byte[0]);// true
     *     ArrayUtils.isSameLength(new byte[1], new byte[2]);// false
     * </pre>
     * <p><i>注：下面的几种重载方法都是基本数据类型（byte、short、int、long、float、double、boolean、char）</i></p>
     *
     * @param array1 第一个待比较的数组，可能为null
     * @param array2 第二个待比较的数组，可能为null
     * @return 如果两个数组的长度相等，则返回true，将null视为空数组
     */
    public static boolean isSameLength(byte[] array1, byte[] array2) {
        if ((array1 == null && array2 != null && array2.length > 0) ||
                (array2 == null && array1 != null && array1.length > 0) ||
                (array1 != null && array2 != null && array1.length != array2.length)) {
            return false;
        }
        return true;
    }

    /**
     * <p>检查两个数组的长度是否相同，将null数组视为长度0。</p>
     * <pre>
     *     ArrayUtils.isSameLength(new double[0], new double[0]);// true
     *     ArrayUtils.isSameLength(new double[1], new double[2]);// false
     * </pre>
     * <p><i>注：下面的几种重载方法都是基本数据类型（byte、short、int、long、float、double、boolean、char）</i></p>
     *
     * @param array1 第一个待比较的数组，可能为null
     * @param array2 第二个待比较的数组，可能为null
     * @return 如果两个数组的长度相等，则返回true，将null视为空数组
     */
    public static boolean isSameLength(double[] array1, double[] array2) {
        if ((array1 == null && array2 != null && array2.length > 0) ||
                (array2 == null && array1 != null && array1.length > 0) ||
                (array1 != null && array2 != null && array1.length != array2.length)) {
            return false;
        }
        return true;
    }

    /**
     * <p>检查两个数组的长度是否相同，将null数组视为长度0。</p>
     * <pre>
     *     ArrayUtils.isSameLength(new float[0], new float[0]);// true
     *     ArrayUtils.isSameLength(new float[1], new float[2]);// false
     * </pre>
     * <p><i>注：下面的几种重载方法都是基本数据类型（byte、short、int、long、float、double、boolean、char）</i></p>
     *
     * @param array1 第一个待比较的数组，可能为null
     * @param array2 第二个待比较的数组，可能为null
     * @return 如果两个数组的长度相等，则返回true，将null视为空数组
     */
    public static boolean isSameLength(float[] array1, float[] array2) {
        if ((array1 == null && array2 != null && array2.length > 0) ||
                (array2 == null && array1 != null && array1.length > 0) ||
                (array1 != null && array2 != null && array1.length != array2.length)) {
            return false;
        }
        return true;
    }

    /**
     * <p>检查两个数组的长度是否相同，将null数组视为长度0。</p>
     * <pre>
     *     ArrayUtils.isSameLength(new boolean[0], new boolean[0]);// true
     *     ArrayUtils.isSameLength(new boolean[1], new boolean[2]);// false
     * </pre>
     * <p><i>注：下面的几种重载方法都是基本数据类型（byte、short、int、long、float、double、boolean、char）</i></p>
     *
     * @param array1 第一个待比较的数组，可能为null
     * @param array2 第二个待比较的数组，可能为null
     * @return 如果两个数组的长度相等，则返回true，将null视为空数组
     */
    public static boolean isSameLength(boolean[] array1, boolean[] array2) {
        if ((array1 == null && array2 != null && array2.length > 0) ||
                (array2 == null && array1 != null && array1.length > 0) ||
                (array1 != null && array2 != null && array1.length != array2.length)) {
            return false;
        }
        return true;
    }

    //-----------------------------------------------------------------------

    /**
     * <p>返回指定数组的长度，该方法可以处理对象数组和基本数据类型数组。</p>
     * <p>如果输入数组为null，则返回0。</p>
     * <pre>
     *     ArrayUtils.getLength(null)            = 0
     *     ArrayUtils.getLength([])              = 0
     *     ArrayUtils.getLength([null])          = 1
     *     ArrayUtils.getLength([true, false])   = 2
     *     ArrayUtils.getLength([1, 2, 3])       = 3
     *     ArrayUtils.getLength(["a", "b", "c"]) = 3
     * </pre>
     *
     * @param array 待获取长度的数组，可能为空
     * @return 返回数组的长度的长度，如果数组为null则返回0
     * @throws IllegalArgumentException 如果对象参数不是数组
     */
    public static int getLength(Object array) {
        // 先判断数组对象array是否为null，如果为null则直接返回0，表示数组长度为0
        if (array == null) {
            return 0;
        }
        // 调用Array.getLength()方法判断数组长度，这是一个"native"方法，所以看不到源码了
        return Array.getLength(array);
    }

    /**
     * <p>检查两个数组是否为同一类型，考虑到多维数组。</p>
     * <pre>
     *     ArrayUtils.isSameType(new int[0], new int[0]);// true
     *     ArrayUtils.isSameType(new int[0], new int[0][0]);// false
     *     ArrayUtils.isSameType(new int[0], new String[0][0]);// false
     * </pre>
     *
     * @param array1 第一个数组，不能为null
     * @param array2 第二个数组，不能为null
     * @return 如果数组类型匹配，则返回true
     * @throws IllegalArgumentException 如果任一数组为null
     */
    public static boolean isSameType(Object array1, Object array2) {
        // 参数校验
        if (array1 == null || array2 == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        /*
            array.getClass().getName()方法的执行情况如下：
                System.out.println((new int[0][0]).getClass().getName());// [[I
                System.out.println(new String[0].getClass().getName());// [Ljava.lang.String;
                System.out.println((new int[0]).getClass().getName());// [I
                System.out.println((new int[0][0]).getClass().getTypeName());// int[][]
                System.out.println(new String[0].getClass().getTypeName());// java.lang.String[]
                System.out.println((new int[0]).getClass().getTypeName());// int[]
            应该也可以根据getTypeName()方法的执行结果判断两个数组是否是同一类型吧？
         */
        return array1.getClass().getName().equals(array2.getClass().getName());
    }

    // Reverse，将数组中的所有元素反转
    //-----------------------------------------------------------------------

    /**
     * <p>反转指定数组的顺序。多维数组没有特殊处理，只能反转一维数组。</p>
     * <P>此方法对null输入数组没有作用</P>
     * <pre>
     *     ArrayUtils.reverse(new String[]{"a", "b", "c", "d"});// [a, b, c, d]  -> [d, c, b, a]
     * </pre>
     *
     * @param array 待反转的数组，可能为null
     */
    public static void reverse(Object[] array) {
        // 校验参数
        if (array == null) {
            return;
        }
        // 一个很不错的反转算法，从首尾两端向中间移动，不断交换元素
        int i = 0;// 首部索引，第一个元素，从前往后移动
        int j = array.length - 1;// 尾部索引，最后一个元素，从后往前移动
        Object tmp;// 充当两个元素交换的中间媒介
        while (j > i) {
            // 交换i和j所代表的元素
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            // 交换后，索引继续向中间移动
            j--;
            i++;
        }
    }

    /**
     * <p>反转指定数组的顺序。多维数组没有特殊处理，只能反转一维数组。</p>
     * <P>此方法对null输入数组没有作用</P>
     * <p><i>注：下面这些都是重载方法，八种基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array 待反转的数组，可能为null
     */
    public static void reverse(long[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        long tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>反转指定数组的顺序。多维数组没有特殊处理，只能反转一维数组。</p>
     * <P>此方法对null输入数组没有作用</P>
     * <p><i>注：下面这些都是重载方法，八种基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array 待反转的数组，可能为null
     */
    public static void reverse(int[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        int tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>反转指定数组的顺序。多维数组没有特殊处理，只能反转一维数组。</p>
     * <P>此方法对null输入数组没有作用</P>
     * <p><i>注：下面这些都是重载方法，八种基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array 待反转的数组，可能为null
     */
    public static void reverse(short[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        short tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>反转指定数组的顺序。多维数组没有特殊处理，只能反转一维数组。</p>
     * <P>此方法对null输入数组没有作用</P>
     * <p><i>注：下面这些都是重载方法，八种基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array 待反转的数组，可能为null
     */
    public static void reverse(char[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        char tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>反转指定数组的顺序。多维数组没有特殊处理，只能反转一维数组。</p>
     * <P>此方法对null输入数组没有作用</P>
     * <p><i>注：下面这些都是重载方法，八种基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array 待反转的数组，可能为null
     */
    public static void reverse(byte[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>反转指定数组的顺序。多维数组没有特殊处理，只能反转一维数组。</p>
     * <P>此方法对null输入数组没有作用</P>
     * <p><i>注：下面这些都是重载方法，八种基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array 待反转的数组，可能为null
     */
    public static void reverse(double[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        double tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>反转指定数组的顺序。多维数组没有特殊处理，只能反转一维数组。</p>
     * <P>此方法对null输入数组没有作用</P>
     * <p><i>注：下面这些都是重载方法，八种基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array 待反转的数组，可能为null
     */
    public static void reverse(float[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        float tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>反转指定数组的顺序。多维数组没有特殊处理，只能反转一维数组。</p>
     * <P>此方法对null输入数组没有作用</P>
     * <p><i>注：下面这些都是重载方法，八种基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array 待反转的数组，可能为null
     */
    public static void reverse(boolean[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        boolean tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    // IndexOf search，从数组中搜索指定元素，返回其索引
    // ----------------------------------------------------------------------

    // Object IndexOf，从数组中搜索指定对象，返回其索引
    //-----------------------------------------------------------------------

    /**
     * <p>查找指定对象在指定数组中的索引</p>
     *
     * @param array        要搜索对象的数组，可能为null
     * @param objectToFind 待查找的对象，可能为null
     * @return 返回对象在数组中的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}
     */
    public static int indexOf(Object[] array, Object objectToFind) {
        // 调用重载方法，从数组中的第一个元素（索引为0）开始找起
        return indexOf(array, objectToFind, 0);
    }

    /**
     * 在指定数组中从指定索引处开始查找指定的对象的索引
     *
     * @param array        指定数组，可能为null
     * @param objectToFind 待查找的指定对象，可能为null
     * @param startIndex   开始搜索的指定索引
     * @return 返回从指定索引开始对象在数组中的索引位置，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}
     */
    public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
        // 参数校验
        if (array == null) {
            // 如果传入的数组为null，则直接返回-1，其中INDEX_NOT_FOUND是定义的常量，表示索引未找到
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            // 如果传入的起始索引小于0，则提升到0，因为数组中的索引都是从0开始，到array.length-1结束
            startIndex = 0;
        }
        if (objectToFind == null) {
            // 如果传入的查找对象为null，也需要循环遍历整个数组，查找到第一个为null的元素，然后返回其索引
            for (int i = startIndex; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
            // 如果传入的查找对象不为null，并且对象类型和数组中的元素对象类型一致，才进行接下来的遍历
            // 从指定索引处开始查找指定对象，如果找到则返回对应的索引
            for (int i = startIndex; i < array.length; i++) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        /*
            那么上面这两个循环几乎一致，为什么不写一起呢？看看下面的代码：
                // 正常情况，成功运行
                String str1 = "abc";
                String str2 = "abc";
                boolean result1 = str1.equals(str2);
                System.out.println(result1);

                // obj1.equals(obj2)中的obj2为null，不会报错
                String str3 = "abc";
                String str4 = null;
                boolean result2 = str3.equals(str4);
                System.out.println(result2);

                // obj1.equals(obj2)中的obj1为null，会抛出空指针NullPointerException异常
                String str5 = null;
                String str6 = "abc";
                boolean result3 = str5.equals(str6);
                System.out.println(result3);
            即当对象可能为null时，再去调用equals()方法就可能发生空指针异常，所以要尽量避免。
            既然obj1.equals(obj2)可能抛出空指针异常，那么为什么不obj2.equals(obj1)呢？
            即objectToFind.equals(array[i])改为array[i].equals(objectToFind)，这样可以吗？
            事实上，这是不可以的，因为你不能确定数组中是否每个元素都是非null元素，如果数组中存在null元素，那么就也会抛出空指针异常。
         */
        // 如果没有找到则返回-1
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>在指定数组中查找指定对象的最后一个索引，同indexOf()方法不一样，前者是顺序查询，lastIndexOf()方法是倒序查找。</p>
     * <p>当输入数组为null时返回{@link #INDEX_NOT_FOUND}，即-1，表示没有找到指定对象</p>
     *
     * @param array        待遍历查找的数组，可能为null
     * @param objectToFind 待查找的对象，可能为null
     * @return 数组中对象的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}
     */
    public static int lastIndexOf(Object[] array, Object objectToFind) {
        // 调用lastIndexOf()重载方法，其中第三个参数传入Integer所能表示的最大值
        // 是从数组倒序开始遍历，虽然Integer.MAX_VALUE很大，但是在代码中会处理为数组中最后一个元素的索引
        return lastIndexOf(array, objectToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>从指定索引开始查找指定数组中给定对象的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     *
     * @param array        待遍历查找的数组，可能为null
     * @param objectToFind 待查找的对象，可能为null
     * @param startIndex   向后遍历的起始索引
     * @return 数组中对象的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(Object[] array, Object objectToFind, int startIndex) {
        // 参数校验
        if (array == null) {
            // 如果array数组为null，空数组你什么也查不到，所以直接返回-1即可
            return INDEX_NOT_FOUND;
        }
        // 校验startIndex的范围，如果小于0，表示是一个非法参数，因为会从数组倒序遍历，所以返回-1表示找不到指定对象；
        // 如果大于等于数组长度，加入从startIndex开始倒序遍历，会很浪费而且没有必要，所以将其置为array.length-1，从数组的最后一个元素索引开始倒序遍历
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        // 指定对象必须不为null，并且数据类型应该和数组的类型相同
        if (objectToFind == null) {
            // 从指定索引处倒序遍历数组数组判断
            for (int i = startIndex; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
            // 从指定索引处倒序遍历数组数组判断
            for (int i = startIndex; i >= 0; i--) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        // 如果没有找到，则返回INDEX_NOT_FOUND，即-1
        return INDEX_NOT_FOUND;
    }

    /**
     * 判断指定数组中是否包含指定对象
     *
     * @param array        要搜索的指定数组
     * @param objectToFind 要查找的指定对象
     * @return 如果数组包含对象，则为true
     */
    public static boolean contains(Object[] array, Object objectToFind) {
        // 原理其实是调用indexOf()方法，搜索指定对象在指定数组中的索引，如果索引不为-1表示存在，即返回true
        return indexOf(array, objectToFind) != INDEX_NOT_FOUND;
    }

    // long IndexOf
    //-----------------------------------------------------------------------

    /**
     * <p>在指定数组中查找给定值的索引。当输入的数组array为null时返回{@link #INDEX_NOT_FOUND}，即-1</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(long[] array, long valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>从给定索引开始查找数组中给定值的索引，顺序查找。如果array数组为null则返回{@link #INDEX_NOT_FOUND}，即-1。</p>
     * <p>如果startIndex为负数，则将其提升为0。</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @param startIndex  开始搜索的索引
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(long[] array, long valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            // 注意，如果是基本数据类型，那么不需要判断null操作，只需要使用"=="判断两个数值是否相等即可
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @return 数组中值的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(long[] array, long valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @param startIndex  向后遍历的起始索引
     * @return 数组中对象的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(long[] array, long valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 判断指定数组中是否包含指定值
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索的指定数组
     * @param valueToFind 要查找的指定值
     * @return 如果数组包含对象，则为true
     */
    public static boolean contains(long[] array, long valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    // int IndexOf
    //-----------------------------------------------------------------------

    /**
     * <p>在指定数组中查找给定值的索引。当输入的数组array为null时返回{@link #INDEX_NOT_FOUND}，即-1</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(int[] array, int valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>从给定索引开始查找数组中给定值的索引，顺序查找。如果array数组为null则返回{@link #INDEX_NOT_FOUND}，即-1。</p>
     * <p>如果startIndex为负数，则将其提升为0。</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @param startIndex  开始搜索的索引
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(int[] array, int valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @return 数组中值的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(int[] array, int valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @param startIndex  向后遍历的起始索引
     * @return 数组中对象的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(int[] array, int valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 判断指定数组中是否包含指定值
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索的指定数组
     * @param valueToFind 要查找的指定值
     * @return 如果数组包含对象，则为true
     */
    public static boolean contains(int[] array, int valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    // short IndexOf
    //-----------------------------------------------------------------------

    /**
     * <p>在指定数组中查找给定值的索引。当输入的数组array为null时返回{@link #INDEX_NOT_FOUND}，即-1</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(short[] array, short valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>从给定索引开始查找数组中给定值的索引，顺序查找。如果array数组为null则返回{@link #INDEX_NOT_FOUND}，即-1。</p>
     * <p>如果startIndex为负数，则将其提升为0。</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @param startIndex  开始搜索的索引
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(short[] array, short valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @return 数组中值的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(short[] array, short valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @param startIndex  向后遍历的起始索引
     * @return 数组中对象的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(short[] array, short valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 判断指定数组中是否包含指定值
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索的指定数组
     * @param valueToFind 要查找的指定值
     * @return 如果数组包含对象，则为true
     */
    public static boolean contains(short[] array, short valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    // char IndexOf
    //-----------------------------------------------------------------------

    /**
     * <p>在指定数组中查找给定值的索引。当输入的数组array为null时返回{@link #INDEX_NOT_FOUND}，即-1</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(char[] array, char valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>从给定索引开始查找数组中给定值的索引，顺序查找。如果array数组为null则返回{@link #INDEX_NOT_FOUND}，即-1。</p>
     * <p>如果startIndex为负数，则将其提升为0。</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @param startIndex  开始搜索的索引
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(char[] array, char valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @return 数组中值的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(char[] array, char valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @param startIndex  向后遍历的起始索引
     * @return 数组中对象的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(char[] array, char valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 判断指定数组中是否包含指定值
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索的指定数组
     * @param valueToFind 要查找的指定值
     * @return 如果数组包含对象，则为true
     */
    public static boolean contains(char[] array, char valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    // byte IndexOf
    //-----------------------------------------------------------------------

    /**
     * <p>在指定数组中查找给定值的索引。当输入的数组array为null时返回{@link #INDEX_NOT_FOUND}，即-1</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(byte[] array, byte valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>从给定索引开始查找数组中给定值的索引，顺序查找。如果array数组为null则返回{@link #INDEX_NOT_FOUND}，即-1。</p>
     * <p>如果startIndex为负数，则将其提升为0。</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @param startIndex  开始搜索的索引
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(byte[] array, byte valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @return 数组中值的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(byte[] array, byte valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @param startIndex  向后遍历的起始索引
     * @return 数组中对象的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(byte[] array, byte valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 判断指定数组中是否包含指定值
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索的指定数组
     * @param valueToFind 要查找的指定值
     * @return 如果数组包含对象，则为true
     */
    public static boolean contains(byte[] array, byte valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    // double IndexOf
    //-----------------------------------------------------------------------

    /**
     * <p>在指定数组中查找给定值的索引。当输入的数组array为null时返回{@link #INDEX_NOT_FOUND}，即-1</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(double[] array, double valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>在指定数值中的给定公差范围内查找给定值的索引，该方法将返回落在[valueToFind - tolerance, valueToFind + tolerance]区间范围内的第一个值的索引。</p>
     * <p>如果数组为null，则返回{@link #INDEX_NOT_FOUND}，即-1。</p>
     * <p><i>注：tolerance可以翻译成"容差"、"公差"等，但感觉都不太合适，反正就是在原数上增加或减少一个指定的数</i></p>
     *
     * @param array       要搜索指定值的数值，可能为null
     * @param valueToFind 待查找的指定值
     * @param tolerance   搜索的公差，也是一个double类型的数值
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(double[] array, double valueToFind, double tolerance) {
        // 调用indexOf()的重载方法，传入的起始索引为0
        return indexOf(array, valueToFind, 0, tolerance);
    }

    /**
     * <p>从给定索引开始查找数组中给定值的索引，顺序查找。如果array数组为null则返回{@link #INDEX_NOT_FOUND}，即-1。</p>
     * <p>如果startIndex为负数，则将其提升为0。</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @param startIndex  开始搜索的索引
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(double[] array, double valueToFind, int startIndex) {
        if (ArrayUtils.isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>在指定数组中的给定公差范围内查找给定值的索引，该方法将返回落在[valueToFind - tolerance, valueToFind + tolerance]区间范围内的第一个值的索引。</p>
     * <p>如果数组为null，则返回{@link #INDEX_NOT_FOUND}，即-1。</p>
     * <p>如果传入的startIndex为负数，那么提升到0；如果传入的startIndex大于数组长度则返回{@link #INDEX_NOT_FOUND}，即-1。</p>
     * <p><i>注：tolerance可以翻译成"容差"、"公差"等，但感觉都不太合适，反正就是在原数上增加或减少一个指定的数。</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 待查找的指定值
     * @param startIndex  开始搜索的索引
     * @param tolerance   搜索的公差，也是一个double类型的数值
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
        // 校验参数
        if (ArrayUtils.isEmpty(array)) {
            // 首先判断数组是否为null，如果是则返回-1，表示查找不到
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        // [valueToFind - tolerance, valueToFind + tolerance]，例如valueToFind为10，而tolerance为5，那么范围是[5, 15]
        // min=valueToFind - tolerance
        double min = valueToFind - tolerance;
        // max=valueToFind + tolerance
        double max = valueToFind + tolerance;
        // 从指定索引处顺序查找
        for (int i = startIndex; i < array.length; i++) {
            // 即查找数组中第一个在[min, max]范围内数值，并返回其对应的索引
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @return 数组中值的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(double[] array, double valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>在指定数组中的给定公差范围内查找给定值的最后一个索引（即倒序遍历的第一个索引），该方法将返回落在[valueToFind - tolerance, valueToFind + tolerance]区间范围内的最后一个值的索引。</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引，从数组的末尾开始搜索</p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @param tolerance   搜索的公差，也是一个double类型的数值
     * @return 数组中值的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(double[] array, double valueToFind, double tolerance) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE, tolerance);
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @param startIndex  向后遍历的起始索引
     * @return 数组中对象的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(double[] array, double valueToFind, int startIndex) {
        if (ArrayUtils.isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>在指定数组中的给定公差范围内查找给定值的最后一个索引（即倒序遍历的第一个索引），该方法将返回落在[valueToFind - tolerance, valueToFind + tolerance]区间范围内的最后一个值的索引。</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @param startIndex  向后遍历的起始索引
     * @param tolerance   搜索的公差，也是一个double类型的数值
     * @return 数组中对象的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
        // 参数校验
        if (ArrayUtils.isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        // 计算[valueToFind - tolerance, valueToFind + tolerance]区间范围
        double min = valueToFind - tolerance;
        double max = valueToFind + tolerance;
        for (int i = startIndex; i >= 0; i--) {
            // 搜寻合适的值
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>判断指定数组中是否包含指定值。</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索的指定数组
     * @param valueToFind 要查找的指定值
     * @return 如果数组包含对象，则为true
     */
    public static boolean contains(double[] array, double valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    /**
     * <p>检查在给定公差范围内的值是否在给定的数组中。如果数组包含由（valueToFind - tolerance）到（valueToFind + tolerance）定义的包含范围内的值。</p>
     * <p>如果传入的数组为null，则返回false。</p>
     *
     * @param array       要搜索的指定数组
     * @param valueToFind 要查找的指定值
     * @param tolerance   搜索的公差，也是一个double类型的数值
     * @return 如果数组包含指定的值，则为true
     */
    public static boolean contains(double[] array, double valueToFind, double tolerance) {
        return indexOf(array, valueToFind, 0, tolerance) != INDEX_NOT_FOUND;
    }

    // float IndexOf
    //-----------------------------------------------------------------------

    /**
     * <p>在指定数组中查找给定值的索引。当输入的数组array为null时返回{@link #INDEX_NOT_FOUND}，即-1</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(float[] array, float valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>从给定索引开始查找数组中给定值的索引，顺序查找。如果array数组为null则返回{@link #INDEX_NOT_FOUND}，即-1。</p>
     * <p>如果startIndex为负数，则将其提升为0。</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @param startIndex  开始搜索的索引
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(float[] array, float valueToFind, int startIndex) {
        if (ArrayUtils.isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @return 数组中值的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(float[] array, float valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @param startIndex  向后遍历的起始索引
     * @return 数组中对象的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(float[] array, float valueToFind, int startIndex) {
        if (ArrayUtils.isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 判断指定数组中是否包含指定值
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索的指定数组
     * @param valueToFind 要查找的指定值
     * @return 如果数组包含对象，则为true
     */
    public static boolean contains(float[] array, float valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    // boolean IndexOf
    //-----------------------------------------------------------------------

    /**
     * <p>在指定数组中查找给定值的索引。当输入的数组array为null时返回{@link #INDEX_NOT_FOUND}，即-1</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(boolean[] array, boolean valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>从给定索引开始查找数组中给定值的索引，顺序查找。如果array数组为null则返回{@link #INDEX_NOT_FOUND}，即-1。</p>
     * <p>如果startIndex为负数，则将其提升为0。</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索指定值的数组，可能为null
     * @param valueToFind 要查找的值
     * @param startIndex  开始搜索的索引
     * @return 数组中值的索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int indexOf(boolean[] array, boolean valueToFind, int startIndex) {
        if (ArrayUtils.isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @return 数组中值的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(boolean[] array, boolean valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>从指定索引开始查找指定数组中给定值的最后一个索引</p>
     * <p>如果起始索引startIndex为负数则直接返回{@link #INDEX_NOT_FOUND}，即-1；如果startIndex大于数组长度，则将startIndex置为数组中最后一个元素的索引</p>
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       待遍历查找的数组，可能为null
     * @param valueToFind 待查找的值
     * @param startIndex  向后遍历的起始索引
     * @return 数组中对象的最后一个索引，如果未找到或者null数组输入则返回{@link #INDEX_NOT_FOUND}，即-1
     */
    public static int lastIndexOf(boolean[] array, boolean valueToFind, int startIndex) {
        if (ArrayUtils.isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 判断指定数组中是否包含指定值
     * <p><i>注：下面的几个方法都是重载方法，传入的参数是八大基本数据类型：byte、short、int、long、float、double、boolean、char</i></p>
     *
     * @param array       要搜索的指定数组
     * @param valueToFind 要查找的指定值
     * @return 如果数组包含对象，则为true
     */
    public static boolean contains(boolean[] array, boolean valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    // Primitive/Object array converters，即包装数据类型与基本数据类型数组的互相转换
    // ----------------------------------------------------------------------

    // Character array converters，Character数组与char数组的相互转换
    // ----------------------------------------------------------------------

    /**
     * <p>将包装数据类型Character数组转换成基本数据类型char数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p><i>注：当array数组中存在null元素时，转换过程会抛出空指针异常。</i></p>
     *
     * @param array Character包装数据类型的数组
     * @return 返回char基本数据类型的数组
     */
    public static char[] toPrimitive(Character[] array) {
        // 参数校验，对于任何数组类型的参数，都需要判断数组是否为null并且元素个数是否为零个
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        // 创建一个基本数据类型char的数组，长度为Character数组的长度
        final char[] result = new char[array.length];
        // 然后循环遍历Character类型的数组，将每个元素填充到char类型的数组中
        for (int i = 0; i < array.length; i++) {
            // 调用Character类的charValue()方法将Character类型的值转换成char类型的值
            result[i] = array[i].charValue();
        }
        return result;
    }

    /**
     * <p>将包装数据类型Character数组转换成基本数据类型char数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p>增加了对数组中null元素的处理，当array数组中存在null元素时会进行处理而不会抛出空指针异常。</p>
     *
     * @param array        Character包装数据类型的数组
     * @param valueForNull 当遍历array数组元素找到null值，替代null值插入到char类型数组中的元素
     * @return 返回char基本数据类型的数组，如果输入的数组为null则返回空
     */
    public static char[] toPrimitive(Character[] array, char valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        final char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            Character b = array[i];
            // 这里添加了一个三元表达式，判断当前元素是否为null，如果是null则选择插入方法参数valueForNull，如果不为null则转换成char类型返回
            result[i] = (b == null ? valueForNull : b.charValue());
        }
        return result;
    }

    /**
     * <p>将基本数据类型char数组转换成包装数据类型Character数组。</p>
     *
     * @param array 一个char类型的数组
     * @return 返回一个Character类型的数组，如果输入为null则为空
     */
    public static Character[] toObject(char[] array) {
        // 参数校验
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        }
        // 创建一个包装数据类型的Character数组
        final Character[] result = new Character[array.length];
        // 循环遍历array数组，然后将数组中每个元素转换成Character类型然后赋给result数组，并返回
        for (int i = 0; i < array.length; i++) {
            result[i] = new Character(array[i]);
        }
        return result;
    }

    // Long array converters，包装数据类型Long数组与基本数据类型long数组之间的相互转换
    // ----------------------------------------------------------------------

    /**
     * <p>将包装数据类型Long数组转换成基本数据类型long数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p><i>注：当array数组中存在null元素时，转换过程会抛出空指针异常。</i></p>
     *
     * @param array Long包装数据类型的数组
     * @return 返回long基本数据类型的数组
     */
    public static long[] toPrimitive(Long[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        final long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].longValue();
        }
        return result;
    }

    /**
     * <p>将包装数据类型Long数组转换成基本数据类型long数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p>增加了对数组中null元素的处理，当array数组中存在null元素时会进行处理而不会抛出空指针异常。</p>
     *
     * @param array        Long包装数据类型的数组
     * @param valueForNull 当遍历array数组元素找到null值，替代null值插入到long类型数组中的元素
     * @return 返回long基本数据类型的数组，如果输入的数组为null则返回空
     */
    public static long[] toPrimitive(Long[] array, long valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        final long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            Long b = array[i];
            result[i] = (b == null ? valueForNull : b.longValue());
        }
        return result;
    }

    /**
     * <p>将基本数据类型long数组转换成包装数据类型Long数组。</p>
     *
     * @param array 一个long类型的数组
     * @return 返回一个Long类型的数组，如果输入为null则为空
     */
    public static Long[] toObject(long[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_LONG_OBJECT_ARRAY;
        }
        final Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = new Long(array[i]);
        }
        return result;
    }

    // Int array converters，包装数据类型Integer数组与基本数据类型int数组的相互转换
    // ----------------------------------------------------------------------

    /**
     * <p>将包装数据类型Integer数组转换成基本数据类型int数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p><i>注：当array数组中存在null元素时，转换过程会抛出空指针异常。</i></p>
     *
     * @param array Integer包装数据类型的数组
     * @return 返回int基本数据类型的数组
     */
    public static int[] toPrimitive(Integer[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        final int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].intValue();
        }
        return result;
    }

    /**
     * <p>将包装数据类型Integer数组转换成基本数据类型int数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p>增加了对数组中null元素的处理，当array数组中存在null元素时会进行处理而不会抛出空指针异常。</p>
     *
     * @param array        Integer包装数据类型的数组
     * @param valueForNull 当遍历array数组元素找到null值，替代null值插入到int类型数组中的元素
     * @return 返回int基本数据类型的数组，如果输入的数组为null则返回空
     */
    public static int[] toPrimitive(Integer[] array, int valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        final int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            Integer b = array[i];
            result[i] = (b == null ? valueForNull : b.intValue());
        }
        return result;
    }

    /**
     * <p>将基本数据类型int数组转换成包装数据类型Integer数组。</p>
     *
     * @param array 一个int类型的数组
     * @return 返回一个Integer类型的数组，如果输入为null则为空
     */
    public static Integer[] toObject(int[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_INTEGER_OBJECT_ARRAY;
        }
        final Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = new Integer(array[i]);
        }
        return result;
    }

    // Short array converters，包装数据类型Short数组与基本数据类型short数组的相互转换
    // ----------------------------------------------------------------------

    /**
     * <p>将包装数据类型Short数组转换成基本数据类型short数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p><i>注：当array数组中存在null元素时，转换过程会抛出空指针异常。</i></p>
     *
     * @param array Short包装数据类型的数组
     * @return 返回short基本数据类型的数组
     */
    public static short[] toPrimitive(Short[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        final short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].shortValue();
        }
        return result;
    }

    /**
     * <p>将包装数据类型Short数组转换成基本数据类型short数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p>增加了对数组中null元素的处理，当array数组中存在null元素时会进行处理而不会抛出空指针异常。</p>
     *
     * @param array        Short包装数据类型的数组
     * @param valueForNull 当遍历array数组元素找到null值，替代null值插入到short类型数组中的元素
     * @return 返回short基本数据类型的数组，如果输入的数组为null则返回空
     */
    public static short[] toPrimitive(Short[] array, short valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        final short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            Short b = array[i];
            result[i] = (b == null ? valueForNull : b.shortValue());
        }
        return result;
    }

    /**
     * <p>将基本数据类型short数组转换成包装数据类型Short数组。</p>
     *
     * @param array 一个short类型的数组
     * @return 返回一个Short类型的数组，如果输入为null则为空
     */
    public static Short[] toObject(short[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_SHORT_OBJECT_ARRAY;
        }
        final Short[] result = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = new Short(array[i]);
        }
        return result;
    }

    // Byte array converters，包装数据类型Byte数组与基本数据类型byte数组的相互转换
    // ----------------------------------------------------------------------

    /**
     * <p>将包装数据类型Byte数组转换成基本数据类型byte数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p><i>注：当array数组中存在null元素时，转换过程会抛出空指针异常。</i></p>
     *
     * @param array Byte包装数据类型的数组
     * @return 返回byte基本数据类型的数组
     */
    public static byte[] toPrimitive(Byte[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        final byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].byteValue();
        }
        return result;
    }

    /**
     * <p>将包装数据类型Byte数组转换成基本数据类型byte数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p>增加了对数组中null元素的处理，当array数组中存在null元素时会进行处理而不会抛出空指针异常。</p>
     *
     * @param array        Byte包装数据类型的数组
     * @param valueForNull 当遍历array数组元素找到null值，替代null值插入到byte类型数组中的元素
     * @return 返回byte基本数据类型的数组，如果输入的数组为null则返回空
     */
    public static byte[] toPrimitive(Byte[] array, byte valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        final byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            Byte b = array[i];
            result[i] = (b == null ? valueForNull : b.byteValue());
        }
        return result;
    }

    /**
     * <p>将基本数据类型byte数组转换成包装数据类型Byte数组。</p>
     *
     * @param array 一个byte类型的数组
     * @return 返回一个Byte类型的数组，如果输入为null则为空
     */
    public static Byte[] toObject(byte[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BYTE_OBJECT_ARRAY;
        }
        final Byte[] result = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = new Byte(array[i]);
        }
        return result;
    }

    // Double array converters，包装数据类型Double数组与基本数据类型double数组的相互转换
    // ----------------------------------------------------------------------

    /**
     * <p>将包装数据类型Double数组转换成基本数据类型double数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p><i>注：当array数组中存在null元素时，转换过程会抛出空指针异常。</i></p>
     *
     * @param array Double包装数据类型的数组
     * @return 返回double基本数据类型的数组
     */
    public static double[] toPrimitive(Double[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        final double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].doubleValue();
        }
        return result;
    }

    /**
     * <p>将包装数据类型Double数组转换成基本数据类型double数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p>增加了对数组中null元素的处理，当array数组中存在null元素时会进行处理而不会抛出空指针异常。</p>
     *
     * @param array        Double包装数据类型的数组
     * @param valueForNull 当遍历array数组元素找到null值，替代null值插入到double类型数组中的元素
     * @return 返回double基本数据类型的数组，如果输入的数组为null则返回空
     */
    public static double[] toPrimitive(Double[] array, double valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        final double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            Double b = array[i];
            result[i] = (b == null ? valueForNull : b.doubleValue());
        }
        return result;
    }

    /**
     * <p>将基本数据类型double数组转换成包装数据类型Double数组。</p>
     *
     * @param array 一个double类型的数组
     * @return 返回一个Double类型的数组，如果输入为null则为空
     */
    public static Double[] toObject(double[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        }
        final Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = new Double(array[i]);
        }
        return result;
    }

    //   Float array converters，包装数据类型Float数组与基本数据类型float数组的相互转换
    // ----------------------------------------------------------------------

    /**
     * <p>将包装数据类型Float数组转换成基本数据类型float数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p><i>注：当array数组中存在null元素时，转换过程会抛出空指针异常。</i></p>
     *
     * @param array Float包装数据类型的数组
     * @return 返回float基本数据类型的数组
     */
    public static float[] toPrimitive(Float[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        final float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].floatValue();
        }
        return result;
    }

    /**
     * <p>将包装数据类型Float数组转换成基本数据类型float数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p>增加了对数组中null元素的处理，当array数组中存在null元素时会进行处理而不会抛出空指针异常。</p>
     *
     * @param array        Float包装数据类型的数组
     * @param valueForNull 当遍历array数组元素找到null值，替代null值插入到float类型数组中的元素
     * @return 返回float基本数据类型的数组，如果输入的数组为null则返回空
     */
    public static float[] toPrimitive(Float[] array, float valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        final float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            Float b = array[i];
            result[i] = (b == null ? valueForNull : b.floatValue());
        }
        return result;
    }

    /**
     * <p>将基本数据类型float数组转换成包装数据类型Float数组。</p>
     *
     * @param array 一个float类型的数组
     * @return 返回一个Float类型的数组，如果输入为null则为空
     */
    public static Float[] toObject(float[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_FLOAT_OBJECT_ARRAY;
        }
        final Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = new Float(array[i]);
        }
        return result;
    }

    // Boolean array converters，包装数据类型Boolean数组与基本数据类型boolean数组的相互转换
    // ----------------------------------------------------------------------

    /**
     * <p>将包装数据类型Boolean数组转换成基本数据类型boolean数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p><i>注：当array数组中存在null元素时，转换过程会抛出空指针异常。</i></p>
     *
     * @param array Boolean包装数据类型的数组
     * @return 返回boolean基本数据类型的数组
     */
    public static boolean[] toPrimitive(Boolean[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        final boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].booleanValue();
        }
        return result;
    }

    /**
     * <p>将包装数据类型Boolean数组转换成基本数据类型boolean数组。</p>
     * <p>如果输入的数组array为null，则返回null。</p>
     * <p>增加了对数组中null元素的处理，当array数组中存在null元素时会进行处理而不会抛出空指针异常。</p>
     *
     * @param array        Boolean包装数据类型的数组
     * @param valueForNull 当遍历array数组元素找到null值，替代null值插入到boolean类型数组中的元素
     * @return 返回boolean基本数据类型的数组，如果输入的数组为null则返回空
     */
    public static boolean[] toPrimitive(Boolean[] array, boolean valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        final boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            Boolean b = array[i];
            result[i] = (b == null ? valueForNull : b.booleanValue());
        }
        return result;
    }

    /**
     * <p>将基本数据类型boolean数组转换成包装数据类型Boolean数组。</p>
     *
     * @param array 一个boolean类型的数组
     * @return 返回一个Boolean类型的数组，如果输入为null则为空
     */
    public static Boolean[] toObject(boolean[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        }
        final Boolean[] result = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (array[i] ? Boolean.TRUE : Boolean.FALSE);
        }
        return result;
    }

    // isEmpty，判断基本数据类型数组和对象数组是否为空
    // ----------------------------------------------------------------------

    /**
     * <p>检查对象数组是否为空或为null</p>
     *
     * @param array 待测试的数组
     * @return 如果数组为空或为null则返回true
     */
    public static boolean isEmpty(Object[] array) {
        // 即判断数组是否为null或数组的长度是否为0
        return array == null || array.length == 0;
    }

    /**
     * <p>检查基本数据类型数组是否为空或为null</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否为空。</i></p>
     *
     * @param array 待测试的数组
     * @return 如果数组为空或为null则返回true
     */
    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>检查基本数据类型数组是否为空或为null</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否为空。</i></p>
     *
     * @param array 待测试的数组
     * @return 如果数组为空或为null则返回true
     */
    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>检查基本数据类型数组是否为空或为null</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否为空。</i></p>
     *
     * @param array 待测试的数组
     * @return 如果数组为空或为null则返回true
     */
    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>检查基本数据类型数组是否为空或为null</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否为空。</i></p>
     *
     * @param array 待测试的数组
     * @return 如果数组为空或为null则返回true
     */
    public static boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>检查基本数据类型数组是否为空或为null</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否为空。</i></p>
     *
     * @param array 待测试的数组
     * @return 如果数组为空或为null则返回true
     */
    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>检查基本数据类型数组是否为空或为null</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否为空。</i></p>
     *
     * @param array 待测试的数组
     * @return 如果数组为空或为null则返回true
     */
    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>检查基本数据类型数组是否为空或为null</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否为空。</i></p>
     *
     * @param array 待测试的数组
     * @return 如果数组为空或为null则返回true
     */
    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>检查基本数据类型数组是否为空或为null</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否为空。</i></p>
     *
     * @param array 待测试的数组
     * @return 如果数组为空或为null则返回true
     */
    public static boolean isEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }

    // isNotEmpty，检查对象数组和基本数据类型数组是否不为空
    // ----------------------------------------------------------------------

    /**
     * <p>检查对象数组是否不为空或不为null。</p>
     *
     * @param array 要测试的数组
     * @return 如果数组不为空或不为null则返回true
     */
    public static boolean isNotEmpty(Object[] array) {
        // 需要同时满足两个条件：不为null和不为空
        return (array != null && array.length != 0);
    }

    /**
     * <p>检查基本数据类型数组是否不为空或不为null。</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否不为空。</i></p>
     *
     * @param array 要测试的数组
     * @return 如果数组不为空或不为null则返回true
     */
    public static boolean isNotEmpty(long[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>检查基本数据类型数组是否不为空或不为null。</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否不为空。</i></p>
     *
     * @param array 要测试的数组
     * @return 如果数组不为空或不为null则返回true
     */
    public static boolean isNotEmpty(int[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>检查基本数据类型数组是否不为空或不为null。</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否不为空。</i></p>
     *
     * @param array 要测试的数组
     * @return 如果数组不为空或不为null则返回true
     */
    public static boolean isNotEmpty(short[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>检查基本数据类型数组是否不为空或不为null。</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否不为空。</i></p>
     *
     * @param array 要测试的数组
     * @return 如果数组不为空或不为null则返回true
     */
    public static boolean isNotEmpty(char[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>检查基本数据类型数组是否不为空或不为null。</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否不为空。</i></p>
     *
     * @param array 要测试的数组
     * @return 如果数组不为空或不为null则返回true
     */
    public static boolean isNotEmpty(byte[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>检查基本数据类型数组是否不为空或不为null。</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否不为空。</i></p>
     *
     * @param array 要测试的数组
     * @return 如果数组不为空或不为null则返回true
     */
    public static boolean isNotEmpty(double[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>检查基本数据类型数组是否不为空或不为null。</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否不为空。</i></p>
     *
     * @param array 要测试的数组
     * @return 如果数组不为空或不为null则返回true
     */
    public static boolean isNotEmpty(float[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>检查基本数据类型数组是否不为空或不为null。</p>
     * <p><i>注：下面八个重载方法都是检查八个基本数据类型的数组是否不为空。</i></p>
     *
     * @param array 要测试的数组
     * @return 如果数组不为空或不为null则返回true
     */
    public static boolean isNotEmpty(boolean[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>将给定数组中的所有元素添加到一个新数组中。</p>
     * <p>新数组包含array1中的所有元素，也包含array2中的所有元素，当数组返回是，它始终是一个新数组。</p>
     * <p>因为是对象数组，可能是自定义对象如Person或其他的，所以需要更严格的检查判断。</p>
     * <pre>
     *     ArrayUtils.addAll(null, null)     = null
     *     ArrayUtils.addAll(array1, null)   = cloned copy of array1
     *     ArrayUtils.addAll(null, array2)   = cloned copy of array2
     *     ArrayUtils.addAll([], [])         = []
     *     ArrayUtils.addAll([null], [null]) = [null, null]
     *     ArrayUtils.addAll(["a", "b", "c"], ["1", "2", "3"]) = ["a", "b", "c", "1", "2", "3"]
     * </pre>
     *
     * @param array1 要将该数组所有元素添加到新数组的第一个数组，可能为null
     * @param array2 要将该数组所有元素添加到新数组的第二个数组，可能为null
     * @return 返回新的数组，如果两个数组都是null则返回null。新数组的类型是第一个数组的类型，除非第一个数组为空，在这种情况下，类型与第二个数组相同。
     * @throws IllegalArgumentException 如果数组类型不兼容
     */
    public static Object[] addAll(Object[] array1, Object[] array2) {
        // 参数校验，如果第一个数组array1为null，则直接复制返回第二个数组array2；如果第二个数组array2为null，则直接复制返回第一个数组array1。
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        // 利用Array.newInstance()方法创建一个以第一个数组类型，长度为两个数组总长度的新数组
        Object[] joinedArray = (Object[]) Array.newInstance(array1.getClass().getComponentType(),
                array1.length + array2.length);
        // 将第一个数组中的所有元素复制到新数组中
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        // 由于新数组的数据类型是根据第一个数组array1创建的，所以不会出现类型不兼容的情况，但第二个数组的类型可能第一个数组的类型不一样，所以可能会出现类型不兼容的情况，复制就会报错。
        try {
            // 所以使用try...catch来捕捉异常
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        } catch (ArrayStoreException ase) {
            // 检查问题是否由不兼容的类型引起
            /*
             * 我们在这里这样做，而不是在复制之前，因为：
             * - 大多数时候这将是一次浪费的检查
             * - 更安全，以防检查过于严格
             */
            // 获取两个数组的类类型
            final Class type1 = array1.getClass().getComponentType();
            final Class type2 = array2.getClass().getComponentType();
            // 判断类型是否相同，如果不相同，则抛出该异常
            if (!type1.isAssignableFrom(type2)) {
                throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of " + type1.getName());
            }
            throw ase; // No, so rethrow original
        }
        return joinedArray;
    }

    /**
     * <p>将给定数组中的所有元素添加到一个新数组中。</p>
     * <p>新数组包含array1和array2中的所有元素，当数组返回时，它始终是一个新数组。</p>
     * <p><i>注：下面都是重载方法，是八个基本数据类型的重载方法。</i></p>
     * <p><i>注：当array1和array2都为null时，仍然返回null。</i></p>
     * <pre>
     *     ArrayUtils.addAll(array1, null)  = cloned copy of array1
     *     ArrayUtils.addAll(null, array2)  = cloned copy of array2
     *     ArrayUtils.addAll(null, null)    = null
     *     ArrayUtils.addAll([], [])        = []
     * </pre>
     *
     * @param array1 要将该数组所有元素添加到新数组的第一个数组，可能为null
     * @param array2 要将该数组所有元素添加到新数组的第二个数组，可能为null
     * @return 返回新的boolean[]数组
     */
    public static boolean[] addAll(boolean[] array1, boolean[] array2) {
        // 参数校验
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        // 创建一个新的boolean类型数组，不需要考虑两个数组的数据类型是否不同，因为方法的参数规定了必须相同，都是boolean类型
        boolean[] joinedArray = new boolean[array1.length + array2.length];
        // 调用System.arraycopy()方法进行复制
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        // 返回复制的结果
        return joinedArray;
    }

    /**
     * <p>将给定数组中的所有元素添加到一个新数组中。</p>
     * <p>新数组包含array1和array2中的所有元素，当数组返回时，它始终是一个新数组。</p>
     * <p><i>注：下面都是重载方法，是八个基本数据类型的重载方法。</i></p>
     * <p><i>注：当array1和array2都为null时，仍然返回null。</i></p>
     * <pre>
     *     ArrayUtils.addAll(array1, null)  = cloned copy of array1
     *     ArrayUtils.addAll(null, array2)  = cloned copy of array2
     *     ArrayUtils.addAll(null, null)    = null
     *     ArrayUtils.addAll([], [])        = []
     * </pre>
     *
     * @param array1 要将该数组所有元素添加到新数组的第一个数组，可能为null
     * @param array2 要将该数组所有元素添加到新数组的第二个数组，可能为null
     * @return 返回新的char[]数组
     */
    public static char[] addAll(char[] array1, char[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        char[] joinedArray = new char[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>将给定数组中的所有元素添加到一个新数组中。</p>
     * <p>新数组包含array1和array2中的所有元素，当数组返回时，它始终是一个新数组。</p>
     * <p><i>注：下面都是重载方法，是八个基本数据类型的重载方法。</i></p>
     * <p><i>注：当array1和array2都为null时，仍然返回null。</i></p>
     * <pre>
     *     ArrayUtils.addAll(array1, null)  = cloned copy of array1
     *     ArrayUtils.addAll(null, array2)  = cloned copy of array2
     *     ArrayUtils.addAll(null, null)    = null
     *     ArrayUtils.addAll([], [])        = []
     * </pre>
     *
     * @param array1 要将该数组所有元素添加到新数组的第一个数组，可能为null
     * @param array2 要将该数组所有元素添加到新数组的第二个数组，可能为null
     * @return 返回新的byte[]数组
     */
    public static byte[] addAll(byte[] array1, byte[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>将给定数组中的所有元素添加到一个新数组中。</p>
     * <p>新数组包含array1和array2中的所有元素，当数组返回时，它始终是一个新数组。</p>
     * <p><i>注：下面都是重载方法，是八个基本数据类型的重载方法。</i></p>
     * <p><i>注：当array1和array2都为null时，仍然返回null。</i></p>
     * <pre>
     *     ArrayUtils.addAll(array1, null)  = cloned copy of array1
     *     ArrayUtils.addAll(null, array2)  = cloned copy of array2
     *     ArrayUtils.addAll(null, null)    = null
     *     ArrayUtils.addAll([], [])        = []
     * </pre>
     *
     * @param array1 要将该数组所有元素添加到新数组的第一个数组，可能为null
     * @param array2 要将该数组所有元素添加到新数组的第二个数组，可能为null
     * @return 返回新的short[]数组
     */
    public static short[] addAll(short[] array1, short[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        short[] joinedArray = new short[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>将给定数组中的所有元素添加到一个新数组中。</p>
     * <p>新数组包含array1和array2中的所有元素，当数组返回时，它始终是一个新数组。</p>
     * <p><i>注：下面都是重载方法，是八个基本数据类型的重载方法。</i></p>
     * <p><i>注：当array1和array2都为null时，仍然返回null。</i></p>
     * <pre>
     *     ArrayUtils.addAll(array1, null)  = cloned copy of array1
     *     ArrayUtils.addAll(null, array2)  = cloned copy of array2
     *     ArrayUtils.addAll(null, null)    = null
     *     ArrayUtils.addAll([], [])        = []
     * </pre>
     *
     * @param array1 要将该数组所有元素添加到新数组的第一个数组，可能为null
     * @param array2 要将该数组所有元素添加到新数组的第二个数组，可能为null
     * @return 返回新的int[]数组
     */
    public static int[] addAll(int[] array1, int[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        int[] joinedArray = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>将给定数组中的所有元素添加到一个新数组中。</p>
     * <p>新数组包含array1和array2中的所有元素，当数组返回时，它始终是一个新数组。</p>
     * <p><i>注：下面都是重载方法，是八个基本数据类型的重载方法。</i></p>
     * <p><i>注：当array1和array2都为null时，仍然返回null。</i></p>
     * <pre>
     *     ArrayUtils.addAll(array1, null)  = cloned copy of array1
     *     ArrayUtils.addAll(null, array2)  = cloned copy of array2
     *     ArrayUtils.addAll(null, null)    = null
     *     ArrayUtils.addAll([], [])        = []
     * </pre>
     *
     * @param array1 要将该数组所有元素添加到新数组的第一个数组，可能为null
     * @param array2 要将该数组所有元素添加到新数组的第二个数组，可能为null
     * @return 返回新的long[]数组
     */
    public static long[] addAll(long[] array1, long[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        long[] joinedArray = new long[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>将给定数组中的所有元素添加到一个新数组中。</p>
     * <p>新数组包含array1和array2中的所有元素，当数组返回时，它始终是一个新数组。</p>
     * <p><i>注：下面都是重载方法，是八个基本数据类型的重载方法。</i></p>
     * <p><i>注：当array1和array2都为null时，仍然返回null。</i></p>
     * <pre>
     *     ArrayUtils.addAll(array1, null)  = cloned copy of array1
     *     ArrayUtils.addAll(null, array2)  = cloned copy of array2
     *     ArrayUtils.addAll(null, null)    = null
     *     ArrayUtils.addAll([], [])        = []
     * </pre>
     *
     * @param array1 要将该数组所有元素添加到新数组的第一个数组，可能为null
     * @param array2 要将该数组所有元素添加到新数组的第二个数组，可能为null
     * @return 返回新的float[]数组
     */
    public static float[] addAll(float[] array1, float[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        float[] joinedArray = new float[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>将给定数组中的所有元素添加到一个新数组中。</p>
     * <p>新数组包含array1和array2中的所有元素，当数组返回时，它始终是一个新数组。</p>
     * <p><i>注：下面都是重载方法，是八个基本数据类型的重载方法。</i></p>
     * <p><i>注：当array1和array2都为null时，仍然返回null。</i></p>
     * <pre>
     *     ArrayUtils.addAll(array1, null)  = cloned copy of array1
     *     ArrayUtils.addAll(null, array2)  = cloned copy of array2
     *     ArrayUtils.addAll(null, null)    = null
     *     ArrayUtils.addAll([], [])        = []
     * </pre>
     *
     * @param array1 要将该数组所有元素添加到新数组的第一个数组，可能为null
     * @param array2 要将该数组所有元素添加到新数组的第二个数组，可能为null
     * @return 返回新的double[]数组
     */
    public static double[] addAll(double[] array1, double[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        double[] joinedArray = new double[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>复制给定数组并将给定元素添加到新数组的末尾。</p>
     * <p>新数组包含输入数组array的相同元素加上最后位置的给定元素。
     * 新数组的类型和输入数组的类型相同。如果输入数组为null，则返回
     * 一个新的单元素数组，其类型和给定元素的类型相同。除非元素本身
     * 为null，在这种情况下，返回数组的类型为Object[]。</p>
     * <pre>
     *     ArrayUtils.add(null, null)      = [null]
     *     ArrayUtils.add(null, "a")       = ["a"]
     *     ArrayUtils.add(["a"], null)     = ["a", null]
     *     ArrayUtils.add(["a"], "b")      = ["a", "b"]
     *     ArrayUtils.add(["a", "b"], "c") = ["a", "b", "c"]
     * </pre>
     *
     * @param array   指定元素数组，可能为null
     * @param element 待添加的对象，可能为null
     * @return 包含现有元素和新插入元素的新数组，返回的数组类型将是输入数组的类型（除非输入数组为空），在这种情况下，它将与给定元素具有相同的类型。
     */
    public static Object[] add(Object[] array, Object element) {
        Class type;
        if (array != null) {
            // 如果输入数组不为空，那么获取输入数组的类类型。
            type = array.getClass();
        } else if (element != null) {
            // 如果输入数组为空，但输入对象不为空，那么获取输入对象的类类型。
            type = element.getClass();
        } else {
            // 如果输入数组和输入元素都为空，那么将类类型设置为Object.class
            type = Object.class;
        }
        // 复制指定类型的数组，返回一个新数组
        Object[] newArray = (Object[]) copyArrayGrow1(array, type);
        // 将数组中的最后一个元素的位置赋给传入对象element
        newArray[newArray.length - 1] = element;
        // 然后返回添加成功的新数组
        return newArray;
    }

    /**
     * <p>复制给定数组并将给定元素添加到新数组的末尾。</p>
     * <p>新数组包含输入数组array的相同元素加上最后位置的给定元素。
     * 新数组的类型和输入数组的类型相同。如果输入数组为null，则返回
     * 一个新的单元素数组，其类型和给定元素的类型相同。除非元素本身
     * 为null，在这种情况下，返回数组的类型为Boolean[]。</p>
     * <pre>
     *     ArrayUtils.add(null, true)          = [true]
     *     ArrayUtils.add([true], false)       = [true, false]
     *     ArrayUtils.add([true, false], true) = [true, false, true]
     * </pre>
     *
     * @param array   指定元素数组，可能为null
     * @param element 待添加的对象，会插入到新数组最后一个索引处
     * @return 包含现有元素和新插入元素的新数组
     */
    public static boolean[] add(boolean[] array, boolean element) {
        // 创建输入数组的副本
        boolean[] newArray = (boolean[]) copyArrayGrow1(array, Boolean.TYPE);
        // 将数组的最后一个位置赋给待插入元素element
        newArray[newArray.length - 1] = element;
        // 返回添加成功的数组
        return newArray;
    }

    /**
     * <p>复制给定数组并将给定元素添加到新数组的末尾。</p>
     * <p>新数组包含输入数组array的相同元素加上最后位置的给定元素。
     * 新数组的类型和输入数组的类型相同。如果输入数组为null，则返回
     * 一个新的单元素数组，其类型和给定元素的类型相同。除非元素本身
     * 为null，在这种情况下，返回数组的类型为Byte[]。</p>
     * <pre>
     *     ArrayUtils.add(null, 0)   = [0]
     *     ArrayUtils.add([1], 0)    = [1, 0]
     *     ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   指定元素数组，可能为null
     * @param element 待添加的对象，会插入到新数组最后一个索引处
     * @return 包含现有元素和新插入元素的新数组
     */
    public static byte[] add(byte[] array, byte element) {
        byte[] newArray = (byte[]) copyArrayGrow1(array, Byte.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>复制给定数组并将给定元素添加到新数组的末尾。</p>
     * <p>新数组包含输入数组array的相同元素加上最后位置的给定元素。
     * 新数组的类型和输入数组的类型相同。如果输入数组为null，则返回
     * 一个新的单元素数组，其类型和给定元素的类型相同。除非元素本身
     * 为null，在这种情况下，返回数组的类型为Character[]。</p>
     * <pre>
     *     ArrayUtils.add(null, '0')   = ['0']
     *     ArrayUtils.add(['1'], '0')    = ['1', '0']
     *     ArrayUtils.add(['1', '0'], '1') = ['1', '0', '1']
     * </pre>
     *
     * @param array   指定元素数组，可能为null
     * @param element 待添加的对象，会插入到新数组最后一个索引处
     * @return 包含现有元素和新插入元素的新数组
     */
    public static char[] add(char[] array, char element) {
        char[] newArray = (char[]) copyArrayGrow1(array, Character.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>复制给定数组并将给定元素添加到新数组的末尾。</p>
     * <p>新数组包含输入数组array的相同元素加上最后位置的给定元素。
     * 新数组的类型和输入数组的类型相同。如果输入数组为null，则返回
     * 一个新的单元素数组，其类型和给定元素的类型相同。除非元素本身
     * 为null，在这种情况下，返回数组的类型为Double[]。</p>
     * <pre>
     *     ArrayUtils.add(null, 0)   = [0]
     *     ArrayUtils.add([1], 0)    = [1, 0]
     *     ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   指定元素数组，可能为null
     * @param element 待添加的对象，会插入到新数组最后一个索引处
     * @return 包含现有元素和新插入元素的新数组
     */
    public static double[] add(double[] array, double element) {
        double[] newArray = (double[]) copyArrayGrow1(array, Double.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>复制给定数组并将给定元素添加到新数组的末尾。</p>
     * <p>新数组包含输入数组array的相同元素加上最后位置的给定元素。
     * 新数组的类型和输入数组的类型相同。如果输入数组为null，则返回
     * 一个新的单元素数组，其类型和给定元素的类型相同。除非元素本身
     * 为null，在这种情况下，返回数组的类型为Float[]。</p>
     * <pre>
     *     ArrayUtils.add(null, 0)   = [0]
     *     ArrayUtils.add([1], 0)    = [1, 0]
     *     ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   指定元素数组，可能为null
     * @param element 待添加的对象，会插入到新数组最后一个索引处
     * @return 包含现有元素和新插入元素的新数组
     */
    public static float[] add(float[] array, float element) {
        float[] newArray = (float[]) copyArrayGrow1(array, Float.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>复制给定数组并将给定元素添加到新数组的末尾。</p>
     * <p>新数组包含输入数组array的相同元素加上最后位置的给定元素。
     * 新数组的类型和输入数组的类型相同。如果输入数组为null，则返回
     * 一个新的单元素数组，其类型和给定元素的类型相同。除非元素本身
     * 为null，在这种情况下，返回数组的类型为Integer[]。</p>
     * <pre>
     *     ArrayUtils.add(null, 0)   = [0]
     *     ArrayUtils.add([1], 0)    = [1, 0]
     *     ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   指定元素数组，可能为null
     * @param element 待添加的对象，会插入到新数组最后一个索引处
     * @return 包含现有元素和新插入元素的新数组
     */
    public static int[] add(int[] array, int element) {
        int[] newArray = (int[]) copyArrayGrow1(array, Integer.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>复制给定数组并将给定元素添加到新数组的末尾。</p>
     * <p>新数组包含输入数组array的相同元素加上最后位置的给定元素。
     * 新数组的类型和输入数组的类型相同。如果输入数组为null，则返回
     * 一个新的单元素数组，其类型和给定元素的类型相同。除非元素本身
     * 为null，在这种情况下，返回数组的类型为Long[]。</p>
     * <pre>
     *     ArrayUtils.add(null, 0)   = [0]
     *     ArrayUtils.add([1], 0)    = [1, 0]
     *     ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   指定元素数组，可能为null
     * @param element 待添加的对象，会插入到新数组最后一个索引处
     * @return 包含现有元素和新插入元素的新数组
     */
    public static long[] add(long[] array, long element) {
        long[] newArray = (long[]) copyArrayGrow1(array, Long.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>复制给定数组并将给定元素添加到新数组的末尾。</p>
     * <p>新数组包含输入数组array的相同元素加上最后位置的给定元素。
     * 新数组的类型和输入数组的类型相同。如果输入数组为null，则返回
     * 一个新的单元素数组，其类型和给定元素的类型相同。除非元素本身
     * 为null，在这种情况下，返回数组的类型为Short[]。</p>
     * <pre>
     *     ArrayUtils.add(null, 0)   = [0]
     *     ArrayUtils.add([1], 0)    = [1, 0]
     *     ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   指定元素数组，可能为null
     * @param element 待添加的对象，会插入到新数组最后一个索引处
     * @return 包含现有元素和新插入元素的新数组
     */
    public static short[] add(short[] array, short element) {
        short[] newArray = (short[]) copyArrayGrow1(array, Short.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>返回给定数组的副本，其大小（即数组的长度）大于第一个参数array的大小。数组的最后一个值保留为默认值，留给待插入的对象使用。</p>
     *
     * @param array                 要复制的数组，不能为null
     * @param newArrayComponentType 如果array为null，那么才创建此类型的数组，并且大小为1，通常是待插入对象的类型
     * @return 大小比输入数组array大1的数组的新副本
     */
    private static Object copyArrayGrow1(Object array, Class newArrayComponentType) {
        // 如果array不为null，则创建一个跟输入数组array相同类型的新数组
        if (array != null) {
            // 获取输入数组的长度，因为不确定类型，所以需要调用Array.getLength()方法来得到数组的长度
            int arrayLength = Array.getLength(array);
            // 创建一个长度为输入数组大小+1并且数组类型为输入数组类型的新数组
            Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            // 将输入数组中的所有元素复制到新数组中
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        // 执行到这，表示输入数组array为null，那么就需要根据传入的类类型来创建一个长度为1的新数组，用来存放待插入的对象
        return Array.newInstance(newArrayComponentType, 1);
    }

    /**
     * <p>在数组的指定索引位置插入指定的元素。并且将当前在该位置的元素（如果当前位置有元素）和其任何后续元素向右移动一位。</p>
     * <p>此方法返回一个新数组，该数组具有输入数组的相同元素加上位置上的给定元素。返回数组的数据类型与输入数组的数据类型相同。如果输入数组为null，则返回一个新的单元素数组，其类型和给定元素的类型相同。</p>
     * <pre>
     *     ArrayUtils.add(null, 0, null)      = [null]
     *     ArrayUtils.add(null, 0, "a")       = ["a"]
     *     ArrayUtils.add(["a"], 1, null)     = ["a", null]
     *     ArrayUtils.add(["a"], 1, "b")      = ["a", "b"]
     *     ArrayUtils.add(["a", "b"], 3, "c") = ["a", "b", "c"]
     * </pre>
     *
     * @param array   要添加元素的数组，可能为null
     * @param index   指定的索引位置，也是新待插入对象的位置
     * @param element 待添加的对象
     * @return 包含现有元素和新元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出指定范围（index < 0 || index > array.length）
     */
    public static Object[] add(Object[] array, int index, Object element) {
        // 参数校验
        Class clss = null;
        if (array != null) {
            // 如果array数组不为null，那么新数组的类型跟输入数组的类型一致
            clss = array.getClass().getComponentType();
        } else if (element != null) {
            // 到这，表示输入数组为null，那么新数组的类型跟待添加对象的类型一致
            clss = element.getClass();
        } else {
            // 到这，表示输入数组array和待添加对象element都为null，那么返回一个只有一个null元素的Object数组
            return new Object[]{null};
        }
        // 调用方法在指定数组的指定位置插入指定对象
        return (Object[]) add(array, index, element, clss);
    }

    /**
     * <p>在数组的指定索引位置插入指定的元素。并且将当前在该位置的元素（如果当前位置有元素）和其任何后续元素向右移动一位。</p>
     * <p>此方法返回一个新数组，该数组具有输入数组的相同元素加上位置上的给定元素。返回数组的数据类型与输入数组的数据类型相同。
     * 如果输入数组为null，则返回一个新的单元素数组，其类型和给定元素的类型相同。</p>
     * <p><i>注：下面的几个重载方法都是八大基本数据类型的添加方法。</i></p>
     * <pre>
     *     ArrayUtils.add(null, 0, true)      = [true]
     *     ArrayUtils.add([true], 0, false)     = [false, true]
     *     ArrayUtils.add([false], 1, true)      = [false, true]
     *     ArrayUtils.add([true, false], 1, true) = [true, true, false]
     * </pre>
     *
     * @param array   要添加元素的数组，可能为null
     * @param index   指定的索引位置，也是新待插入对象的位置
     * @param element 待添加的对象
     * @return 包含现有元素和新元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出指定范围（index < 0 || index > array.length）
     */
    public static boolean[] add(boolean[] array, int index, boolean element) {
        return (boolean[]) add(array, index, BooleanUtils.toBooleanObject(element), Boolean.TYPE);
    }

    /**
     * <p>在数组的指定索引位置插入指定的元素。并且将当前在该位置的元素（如果当前位置有元素）和其任何后续元素向右移动一位。</p>
     * <p>此方法返回一个新数组，该数组具有输入数组的相同元素加上位置上的给定元素。返回数组的数据类型与输入数组的数据类型相同。
     * 如果输入数组为null，则返回一个新的单元素数组，其类型和给定元素的类型相同。</p>
     * <p><i>注：下面的几个重载方法都是八大基本数据类型的添加方法。</i></p>
     * <pre>
     *     ArrayUtils.add(null, 0, 'a')            = ['a']
     *     ArrayUtils.add(['a'], 0, 'b')           = ['b', 'a']
     *     ArrayUtils.add(['a', 'b'], 0, 'c')      = ['c', 'a', 'b']
     *     ArrayUtils.add(['a', 'b'], 1, 'k')      = ['a', 'k', 'b']
     *     ArrayUtils.add(['a', 'b', 'c'], 1, 't') = ['a', 't', 'b', 'c']
     * </pre>
     *
     * @param array   要添加元素的数组，可能为null
     * @param index   指定的索引位置，也是新待插入对象的位置
     * @param element 待添加的对象
     * @return 包含现有元素和新元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出指定范围（index < 0 || index > array.length）
     */
    public static char[] add(char[] array, int index, char element) {
        return (char[]) add(array, index, new Character(element), Character.TYPE);
    }

    /**
     * <p>在数组的指定索引位置插入指定的元素。并且将当前在该位置的元素（如果当前位置有元素）和其任何后续元素向右移动一位。</p>
     * <p>此方法返回一个新数组，该数组具有输入数组的相同元素加上位置上的给定元素。返回数组的数据类型与输入数组的数据类型相同。
     * 如果输入数组为null，则返回一个新的单元素数组，其类型和给定元素的类型相同。</p>
     * <p><i>注：下面的几个重载方法都是八大基本数据类型的添加方法。</i></p>
     * <pre>
     *     ArrayUtils.add([1], 0, 2)         = [2, 1]
     *     ArrayUtils.add([2, 6], 2, 3)      = [2, 6, 3]
     *     ArrayUtils.add([2, 6], 0, 1)      = [1, 2, 6]
     *     ArrayUtils.add([2, 6, 3], 2, 1)   = [2, 6, 1, 3]
     * </pre>
     *
     * @param array   要添加元素的数组，可能为null
     * @param index   指定的索引位置，也是新待插入对象的位置
     * @param element 待添加的对象
     * @return 包含现有元素和新元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出指定范围（index < 0 || index > array.length）
     */
    public static byte[] add(byte[] array, int index, byte element) {
        return (byte[]) add(array, index, new Byte(element), Byte.TYPE);
    }

    /**
     * <p>在数组的指定索引位置插入指定的元素。并且将当前在该位置的元素（如果当前位置有元素）和其任何后续元素向右移动一位。</p>
     * <p>此方法返回一个新数组，该数组具有输入数组的相同元素加上位置上的给定元素。返回数组的数据类型与输入数组的数据类型相同。
     * 如果输入数组为null，则返回一个新的单元素数组，其类型和给定元素的类型相同。</p>
     * <p><i>注：下面的几个重载方法都是八大基本数据类型的添加方法。</i></p>
     * <pre>
     *     ArrayUtils.add([1], 0, 2)         = [2, 1]
     *     ArrayUtils.add([2, 6], 2, 10)     = [2, 6, 10]
     *     ArrayUtils.add([2, 6], 0, -4)     = [-4, 2, 6]
     *     ArrayUtils.add([2, 6, 3], 2, 1)   = [2, 6, 1, 3]
     * </pre>
     *
     * @param array   要添加元素的数组，可能为null
     * @param index   指定的索引位置，也是新待插入对象的位置
     * @param element 待添加的对象
     * @return 包含现有元素和新元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出指定范围（index < 0 || index > array.length）
     */
    public static short[] add(short[] array, int index, short element) {
        return (short[]) add(array, index, new Short(element), Short.TYPE);
    }

    /**
     * <p>在数组的指定索引位置插入指定的元素。并且将当前在该位置的元素（如果当前位置有元素）和其任何后续元素向右移动一位。</p>
     * <p>此方法返回一个新数组，该数组具有输入数组的相同元素加上位置上的给定元素。返回数组的数据类型与输入数组的数据类型相同。
     * 如果输入数组为null，则返回一个新的单元素数组，其类型和给定元素的类型相同。</p>
     * <p><i>注：下面的几个重载方法都是八大基本数据类型的添加方法。</i></p>
     * <pre>
     *     ArrayUtils.add([1], 0, 2)         = [2, 1]
     *     ArrayUtils.add([2, 6], 2, 10)     = [2, 6, 10]
     *     ArrayUtils.add([2, 6], 0, -4)     = [-4, 2, 6]
     *     ArrayUtils.add([2, 6, 3], 2, 1)   = [2, 6, 1, 3]
     * </pre>
     *
     * @param array   要添加元素的数组，可能为null
     * @param index   指定的索引位置，也是新待插入对象的位置
     * @param element 待添加的对象
     * @return 包含现有元素和新元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出指定范围（index < 0 || index > array.length）
     */
    public static int[] add(int[] array, int index, int element) {
        return (int[]) add(array, index, new Integer(element), Integer.TYPE);
    }

    /**
     * <p>在数组的指定索引位置插入指定的元素。并且将当前在该位置的元素（如果当前位置有元素）和其任何后续元素向右移动一位。</p>
     * <p>此方法返回一个新数组，该数组具有输入数组的相同元素加上位置上的给定元素。返回数组的数据类型与输入数组的数据类型相同。
     * 如果输入数组为null，则返回一个新的单元素数组，其类型和给定元素的类型相同。</p>
     * <p><i>注：下面的几个重载方法都是八大基本数据类型的添加方法。</i></p>
     * <pre>
     *     ArrayUtils.add([1L], 0, 2L)           = [2L, 1L]
     *     ArrayUtils.add([2L, 6L], 2, 10L)      = [2L, 6L, 10L]
     *     ArrayUtils.add([2L, 6L], 0, -4L)      = [-4L, 2L, 6L]
     *     ArrayUtils.add([2L, 6L, 3L], 2, 1L)   = [2L, 6L, 1L, 3L]
     * </pre>
     *
     * @param array   要添加元素的数组，可能为null
     * @param index   指定的索引位置，也是新待插入对象的位置
     * @param element 待添加的对象
     * @return 包含现有元素和新元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出指定范围（index < 0 || index > array.length）
     */
    public static long[] add(long[] array, int index, long element) {
        return (long[]) add(array, index, new Long(element), Long.TYPE);
    }

    /**
     * <p>在数组的指定索引位置插入指定的元素。并且将当前在该位置的元素（如果当前位置有元素）和其任何后续元素向右移动一位。</p>
     * <p>此方法返回一个新数组，该数组具有输入数组的相同元素加上位置上的给定元素。返回数组的数据类型与输入数组的数据类型相同。
     * 如果输入数组为null，则返回一个新的单元素数组，其类型和给定元素的类型相同。</p>
     * <p><i>注：下面的几个重载方法都是八大基本数据类型的添加方法。</i></p>
     * <pre>
     *     ArrayUtils.add([1.1f], 0, 2.2f)               = [2.2f, 1.1f]
     *     ArrayUtils.add([2.3f, 6.4f], 2, 10.5f)        = [2.3f, 6.4f, 10.5f]
     *     ArrayUtils.add([2.6f, 6.7f], 0, -4.8f)        = [-4.8f, 2.6f, 6.7f]
     *     ArrayUtils.add([2.9f, 6.0f, 0.3f], 2, 1.0f)   = [2.9f, 6.0f, 1.0f, 0.3f]
     * </pre>
     *
     * @param array   要添加元素的数组，可能为null
     * @param index   指定的索引位置，也是新待插入对象的位置
     * @param element 待添加的对象
     * @return 包含现有元素和新元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出指定范围（index < 0 || index > array.length）
     */
    public static float[] add(float[] array, int index, float element) {
        return (float[]) add(array, index, new Float(element), Float.TYPE);
    }

    /**
     * <p>在数组的指定索引位置插入指定的元素。并且将当前在该位置的元素（如果当前位置有元素）和其任何后续元素向右移动一位。</p>
     * <p>此方法返回一个新数组，该数组具有输入数组的相同元素加上位置上的给定元素。返回数组的数据类型与输入数组的数据类型相同。
     * 如果输入数组为null，则返回一个新的单元素数组，其类型和给定元素的类型相同。</p>
     * <p><i>注：下面的几个重载方法都是八大基本数据类型的添加方法。</i></p>
     * <pre>
     *     ArrayUtils.add([1.1], 0, 2.2)              = [2.2, 1.1]
     *     ArrayUtils.add([2.3, 6.4], 2, 10.5)        = [2.3, 6.4, 10.5]
     *     ArrayUtils.add([2.6, 6.7], 0, -4.8)        = [-4.8, 2.6, 6.7]
     *     ArrayUtils.add([2.9, 6.0, 0.3], 2, 1.0)    = [2.9, 6.0, 1.0, 0.3]
     * </pre>
     *
     * @param array   要添加元素的数组，可能为null
     * @param index   指定的索引位置，也是新待插入对象的位置
     * @param element 待添加的对象
     * @return 包含现有元素和新元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出指定范围（index < 0 || index > array.length）
     */
    public static double[] add(double[] array, int index, double element) {
        return (double[]) add(array, index, new Double(element), Double.TYPE);
    }

    /**
     * <p><code>add(array, index, element)</code>方法的底层实现。</p>
     * <p><i>注：可以利用debug调试来查看这个方法，可能更容易明白，而不是只看注释。</i></p>
     *
     * @param array   待添加元素的数组，可能为null
     * @param index   新对象的索引位置
     * @param element 待添加的对象
     * @param clss    被添加元素的类型
     * @return 包含现有元素和新元素的新数组
     * @throws IndexOutOfBoundsException 当索引越界后抛出此异常
     */
    private static Object add(Object array, int index, Object element, Class clss) {
        // 参数校验
        if (array == null) {
            // 如果传入的数组array为null，那么将创建一个类型为clss并且长度为1的新数组
            if (index != 0) {// 当插入的索引不为0，那么毫无意义，所以抛出异常
                throw new IndexOutOfBoundsException("Index: " + index + ", Length: 0");
            }
            // 创建一个类型为clss并且长度为1的新数组
            Object joinedArray = Array.newInstance(clss, 1);
            // 利用Array.set()方法将待添加的对象element插入到新数组中的第一个位置上
            Array.set(joinedArray, 0, element);
            return joinedArray;
        }
        // 执行到这，表示传入的数组array不为null，那么就需要将传入数组中的所有元素复制到新数组中
        // 获取对象数组的长度
        int length = Array.getLength(array);
        // 参数校验
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }
        // 创建一个指定数据类型并且长度为传入数组长度+1的心数组
        Object result = Array.newInstance(clss, length + 1);
        // 调用系统函数进行复制，复制array数组中的前index个元素（即[0,index)）到新数组result中
        System.arraycopy(array, 0, result, 0, index);
        // 先将指定索引位置的值赋为element
        Array.set(result, index, element);
        if (index < length) {
            // 最后将输入数组array指定索引index及之后的所有元素（[index, array.length-1]）复制到新数组中
            System.arraycopy(array, index, result, index + 1, length - index);
        }
        // 返回成功的数组
        return result;
    }

    /**
     * <p>Removes the element at the specified position from the specified array.
     * All subsequent elements are shifted to the left (substracts one from
     * their indices).</p>
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.</p>
     *
     * <p>If the input array is <code>null</code>, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.</p>
     *
     * <pre>
     * ArrayUtils.remove(["a"], 0)           = []
     * ArrayUtils.remove(["a", "b"], 0)      = ["b"]
     * ArrayUtils.remove(["a", "b"], 1)      = ["a"]
     * ArrayUtils.remove(["a", "b", "c"], 1) = ["a", "c"]
     * </pre>
     *
     * @param array the array to remove the element from, may not be <code>null</code>
     * @param index the position of the element to be removed
     * @return A new array containing the existing elements except the element
     * at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index < 0 || index >= array.length), or if the array is <code>null</code>.
     * @since 2.1
     */
    /**
     * <p>从指定数组中移除指定位置的元素，移除后，指定索引之后的所有元素向前（左）移动一位。</p>
     * <p>该方法会返回一个新的数组，新数组拥有与输入数组相同的元素，但指定位置的元素除外，因为会被删除。</p>
     * <p>返回数组的类型与输入数组的类型相同，但如果输入数组为null，将抛出IndexOutOfBoundsException异常，因为在这种情况下无法指定有效索引，也无法删除。</p>
     * <pre>
     *     ArrayUtils.remove(["a"], 0)           = []
     *     ArrayUtils.remove(["a", "b"], 0)      = ["b"]
     *     ArrayUtils.remove(["a", "b"], 1)      = ["a"]
     *     ArrayUtils.remove(["a", "b", "c"], 1) = ["a", "c"]
     * </pre>
     *
     * @param array 要被删除元素的数组，不能为null
     * @param index 待删除元素的索引位置
     * @return 包含除指定位置之外的现有元素的新数组
     */
    public static Object[] remove(Object[] array, int index) {
        // 直接调用remove()方法，传入两个参数
        return (Object[]) remove((Object) array, index);
    }

    /**
     * <p>从指定数组中删除第一次出现的指定元素。删除后，所有的后续元素向前（左）移动一位。</p>
     * <p>如果数组中不包含指定元素，那么则不会从数组中删除任何元素。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <pre>
     *     ArrayUtils.removeElement(null, "a")            = null
     *     ArrayUtils.removeElement([], "a")              = []
     *     ArrayUtils.removeElement(["a"], "b")           = ["a"]
     *     ArrayUtils.removeElement(["a", "b"], "a")      = ["b"]
     *     ArrayUtils.removeElement(["a", "b", "a"], "a") = ["b", "a"]
     * </pre>
     *
     * @param array   要从中移除元素的数组，可能为null
     * @param element 要删除的元素
     * @return 一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素
     */
    public static Object[] removeElement(Object[] array, Object element) {
        // 先查找待删除元素在输入数组array中第一次出现的索引
        int index = indexOf(array, element);
        // 如果index返回值为-1，表示该元素不存在于输入数组array中，所以还是返回原数组的副本
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        // 如果找到了索引，则调用remove()方法进行删除
        return remove(array, index);
    }

    /**
     * <p>从指定数组中移除指定位置的元素，删除后，指定索引位置之后的所有元素都向前（左）移动一位。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <p>如果输入数组为null，则将抛出IndexOutOfBoundsException索引越界异常，因为这种情况下无法指定有效索引。</p>
     * <pre>
     *     ArrayUtils.remove([true], 0)              = []
     *     ArrayUtils.remove([true, false], 0)       = [false]
     *     ArrayUtils.remove([true, false], 1)       = [true]
     *     ArrayUtils.remove([true, true, false], 1) = [true, false]
     * </pre>
     *
     * @param array 要从中删除元素的指定数组，不能为null
     * @param index 要被删除的元素的索引位置，即数组下标
     * @return 包含除被删除元素之外的所有元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出范围（index <0 || index >= array.length），或者数组为null
     */
    public static boolean[] remove(boolean[] array, int index) {
        // 移除boolean类型数组中指定索引位置的元素
        return (boolean[]) remove((Object) array, index);
    }

    /**
     * <p>从指定数组中删除第一次出现的指定元素。删除后，所有的后续元素向前（左）移动一位。</p>
     * <p>如果数组中不包含指定元素，那么则不会从数组中删除任何元素。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <pre>
     *     ArrayUtils.removeElement(null, true)                = null
     *     ArrayUtils.removeElement([], true)                  = []
     *     ArrayUtils.removeElement([true], false)             = [true]
     *     ArrayUtils.removeElement([true, false], false)      = [true]
     *     ArrayUtils.removeElement([true, false, true], true) = [false, true]
     * </pre>
     *
     * @param array   要从中移除元素的数组，可能为null
     * @param element 要删除的元素
     * @return 一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素
     */
    public static boolean[] removeElement(boolean[] array, boolean element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>从指定数组中移除指定位置的元素，删除后，指定索引位置之后的所有元素都向前（左）移动一位。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <p>如果输入数组为null，则将抛出IndexOutOfBoundsException索引越界异常，因为这种情况下无法指定有效索引。</p>
     * <pre>
     *     ArrayUtils.remove([1], 0)          = []
     *     ArrayUtils.remove([1, 0], 0)       = [0]
     *     ArrayUtils.remove([1, 0], 1)       = [1]
     *     ArrayUtils.remove([1, 0, 1], 1)    = [1, 1]
     * </pre>
     *
     * @param array 要从中删除元素的指定数组，不能为null
     * @param index 要被删除的元素的索引位置，即数组下标
     * @return 包含除被删除元素之外的所有元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出范围（index <0 || index >= array.length），或者数组为null
     */
    public static byte[] remove(byte[] array, int index) {
        return (byte[]) remove((Object) array, index);
    }

    /**
     * <p>从指定数组中删除第一次出现的指定元素。删除后，所有的后续元素向前（左）移动一位。</p>
     * <p>如果数组中不包含指定元素，那么则不会从数组中删除任何元素。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <pre>
     *     ArrayUtils.removeElement(null, 1)        = null
     *     ArrayUtils.removeElement([], 1)          = []
     *     ArrayUtils.removeElement([1], 0)         = [1]
     *     ArrayUtils.removeElement([1, 0], 0)      = [1]
     *     ArrayUtils.removeElement([1, 0, 1], 1)   = [0, 1]
     * </pre>
     *
     * @param array   要从中移除元素的数组，可能为null
     * @param element 要删除的元素
     * @return 一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素
     */
    public static byte[] removeElement(byte[] array, byte element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>从指定数组中移除指定位置的元素，删除后，指定索引位置之后的所有元素都向前（左）移动一位。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <p>如果输入数组为null，则将抛出IndexOutOfBoundsException索引越界异常，因为这种情况下无法指定有效索引。</p>
     * <pre>
     *     ArrayUtils.remove(['a'], 0)           = []
     *     ArrayUtils.remove(['a', 'b'], 0)      = ['b']
     *     ArrayUtils.remove(['a', 'b'], 1)      = ['a']
     *     ArrayUtils.remove(['a', 'b', 'c'], 1) = ['a', 'c']
     * </pre>
     *
     * @param array 要从中删除元素的指定数组，不能为null
     * @param index 要被删除的元素的索引位置，即数组下标
     * @return 包含除被删除元素之外的所有元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出范围（index <0 || index >= array.length），或者数组为null
     */
    public static char[] remove(char[] array, int index) {
        return (char[]) remove((Object) array, index);
    }

    /**
     * <p>从指定数组中删除第一次出现的指定元素。删除后，所有的后续元素向前（左）移动一位。</p>
     * <p>如果数组中不包含指定元素，那么则不会从数组中删除任何元素。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <pre>
     *     ArrayUtils.removeElement(null, 'a')            = null
     *     ArrayUtils.removeElement([], 'a')              = []
     *     ArrayUtils.removeElement(['a'], 'b')           = ['a']
     *     ArrayUtils.removeElement(['a', 'b'], 'a')      = ['b']
     *     ArrayUtils.removeElement(['a', 'b', 'a'], 'a') = ['b', 'a']
     * </pre>
     *
     * @param array   要从中移除元素的数组，可能为null
     * @param element 要删除的元素
     * @return 一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素
     */
    public static char[] removeElement(char[] array, char element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>从指定数组中移除指定位置的元素，删除后，指定索引位置之后的所有元素都向前（左）移动一位。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <p>如果输入数组为null，则将抛出IndexOutOfBoundsException索引越界异常，因为这种情况下无法指定有效索引。</p>
     * <pre>
     *     ArrayUtils.remove([1.1], 0)           = []
     *     ArrayUtils.remove([2.5, 6.0], 0)      = [6.0]
     *     ArrayUtils.remove([2.5, 6.0], 1)      = [2.5]
     *     ArrayUtils.remove([2.5, 6.0, 3.8], 1) = [2.5, 3.8]
     * </pre>
     *
     * @param array 要从中删除元素的指定数组，不能为null
     * @param index 要被删除的元素的索引位置，即数组下标
     * @return 包含除被删除元素之外的所有元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出范围（index <0 || index >= array.length），或者数组为null
     */
    public static double[] remove(double[] array, int index) {
        return (double[]) remove((Object) array, index);
    }

    /**
     * <p>从指定数组中删除第一次出现的指定元素。删除后，所有的后续元素向前（左）移动一位。</p>
     * <p>如果数组中不包含指定元素，那么则不会从数组中删除任何元素。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <pre>
     *     ArrayUtils.removeElement(null, 1.1)            = null
     *     ArrayUtils.removeElement([], 1.1)              = []
     *     ArrayUtils.removeElement([1.1], 1.2)           = [1.1]
     *     ArrayUtils.removeElement([1.1, 2.3], 1.1)      = [2.3]
     *     ArrayUtils.removeElement([1.1, 2.3, 1.1], 1.1) = [2.3, 1.1]
     * </pre>
     *
     * @param array   要从中移除元素的数组，可能为null
     * @param element 要删除的元素
     * @return 一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素
     */
    public static double[] removeElement(double[] array, double element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>从指定数组中移除指定位置的元素，删除后，指定索引位置之后的所有元素都向前（左）移动一位。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <p>如果输入数组为null，则将抛出IndexOutOfBoundsException索引越界异常，因为这种情况下无法指定有效索引。</p>
     * <pre>
     *     ArrayUtils.remove([1.1], 0)           = []
     *     ArrayUtils.remove([2.5, 6.0], 0)      = [6.0]
     *     ArrayUtils.remove([2.5, 6.0], 1)      = [2.5]
     *     ArrayUtils.remove([2.5, 6.0, 3.8], 1) = [2.5, 3.8]
     * </pre>
     *
     * @param array 要从中删除元素的指定数组，不能为null
     * @param index 要被删除的元素的索引位置，即数组下标
     * @return 包含除被删除元素之外的所有元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出范围（index <0 || index >= array.length），或者数组为null
     */
    public static float[] remove(float[] array, int index) {
        return (float[]) remove((Object) array, index);
    }

    /**
     * <p>从指定数组中删除第一次出现的指定元素。删除后，所有的后续元素向前（左）移动一位。</p>
     * <p>如果数组中不包含指定元素，那么则不会从数组中删除任何元素。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <pre>
     *     ArrayUtils.removeElement(null, 1.1)            = null
     *     ArrayUtils.removeElement([], 1.1)              = []
     *     ArrayUtils.removeElement([1.1], 1.2)           = [1.1]
     *     ArrayUtils.removeElement([1.1, 2.3], 1.1)      = [2.3]
     *     ArrayUtils.removeElement([1.1, 2.3, 1.1], 1.1) = [2.3, 1.1]
     * </pre>
     *
     * @param array   要从中移除元素的数组，可能为null
     * @param element 要删除的元素
     * @return 一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素
     */
    public static float[] removeElement(float[] array, float element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>从指定数组中移除指定位置的元素，删除后，指定索引位置之后的所有元素都向前（左）移动一位。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <p>如果输入数组为null，则将抛出IndexOutOfBoundsException索引越界异常，因为这种情况下无法指定有效索引。</p>
     * <pre>
     *     ArrayUtils.remove([1], 0)         = []
     *     ArrayUtils.remove([2, 6], 0)      = [6]
     *     ArrayUtils.remove([2, 6], 1)      = [2]
     *     ArrayUtils.remove([2, 6, 3], 1)   = [2, 3]
     * </pre>
     *
     * @param array 要从中删除元素的指定数组，不能为null
     * @param index 要被删除的元素的索引位置，即数组下标
     * @return 包含除被删除元素之外的所有元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出范围（index <0 || index >= array.length），或者数组为null
     */
    public static int[] remove(int[] array, int index) {
        return (int[]) remove((Object) array, index);
    }

    /**
     * <p>从指定数组中删除第一次出现的指定元素。删除后，所有的后续元素向前（左）移动一位。</p>
     * <p>如果数组中不包含指定元素，那么则不会从数组中删除任何元素。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <pre>
     *     ArrayUtils.removeElement(null, 1)      = null
     *     ArrayUtils.removeElement([], 1)        = []
     *     ArrayUtils.removeElement([1], 2)       = [1]
     *     ArrayUtils.removeElement([1, 3], 1)    = [3]
     *     ArrayUtils.removeElement([1, 3, 1], 1) = [3, 1]
     * </pre>
     *
     * @param array   要从中移除元素的数组，可能为null
     * @param element 要删除的元素
     * @return 一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素
     */
    public static int[] removeElement(int[] array, int element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>从指定数组中移除指定位置的元素，删除后，指定索引位置之后的所有元素都向前（左）移动一位。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <p>如果输入数组为null，则将抛出IndexOutOfBoundsException索引越界异常，因为这种情况下无法指定有效索引。</p>
     * <pre>
     *     ArrayUtils.remove([1], 0)         = []
     *     ArrayUtils.remove([2, 6], 0)      = [6]
     *     ArrayUtils.remove([2, 6], 1)      = [2]
     *     ArrayUtils.remove([2, 6, 3], 1)   = [2, 3]
     * </pre>
     *
     * @param array 要从中删除元素的指定数组，不能为null
     * @param index 要被删除的元素的索引位置，即数组下标
     * @return 包含除被删除元素之外的所有元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出范围（index <0 || index >= array.length），或者数组为null
     */
    public static long[] remove(long[] array, int index) {
        return (long[]) remove((Object) array, index);
    }

    /**
     * <p>从指定数组中删除第一次出现的指定元素。删除后，所有的后续元素向前（左）移动一位。</p>
     * <p>如果数组中不包含指定元素，那么则不会从数组中删除任何元素。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <pre>
     *     ArrayUtils.removeElement(null, 1)      = null
     *     ArrayUtils.removeElement([], 1)        = []
     *     ArrayUtils.removeElement([1], 2)       = [1]
     *     ArrayUtils.removeElement([1, 3], 1)    = [3]
     *     ArrayUtils.removeElement([1, 3, 1], 1) = [3, 1]
     * </pre>
     *
     * @param array   要从中移除元素的数组，可能为null
     * @param element 要删除的元素
     * @return 一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素
     */
    public static long[] removeElement(long[] array, long element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>从指定数组中移除指定位置的元素，删除后，指定索引位置之后的所有元素都向前（左）移动一位。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <p>如果输入数组为null，则将抛出IndexOutOfBoundsException索引越界异常，因为这种情况下无法指定有效索引。</p>
     * <pre>
     *     ArrayUtils.remove([1], 0)         = []
     *     ArrayUtils.remove([2, 6], 0)      = [6]
     *     ArrayUtils.remove([2, 6], 1)      = [2]
     *     ArrayUtils.remove([2, 6, 3], 1)   = [2, 3]
     * </pre>
     *
     * @param array 要从中删除元素的指定数组，不能为null
     * @param index 要被删除的元素的索引位置，即数组下标
     * @return 包含除被删除元素之外的所有元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出范围（index <0 || index >= array.length），或者数组为null
     */
    public static short[] remove(short[] array, int index) {
        return (short[]) remove((Object) array, index);
    }

    /**
     * <p>从指定数组中删除第一次出现的指定元素。删除后，所有的后续元素向前（左）移动一位。</p>
     * <p>如果数组中不包含指定元素，那么则不会从数组中删除任何元素。</p>
     * <p>该方法返回一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素。返回数组的类型与输入数组的类型始终相同。</p>
     * <pre>
     *     ArrayUtils.removeElement(null, 1)      = null
     *     ArrayUtils.removeElement([], 1)        = []
     *     ArrayUtils.removeElement([1], 2)       = [1]
     *     ArrayUtils.removeElement([1, 3], 1)    = [3]
     *     ArrayUtils.removeElement([1, 3, 1], 1) = [3, 1]
     * </pre>
     *
     * @param array   要从中移除元素的数组，可能为null
     * @param element 要删除的元素
     * @return 一个新数组，包含除第一次出现的指定元素之外的输入数组所有元素
     */
    public static short[] removeElement(short[] array, short element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>从指定数组中移除指定索引位置的元素，删除后所有后续元素均向前（左）移动。</p>
     * <p>该方法返回一个新数组，该数组具有除指定位置上的元素之外的输入数组的所有元素。返回的数组类型与输入数组的类型相同。</p>
     * <p>如果输入数组为null，则抛出IndexOutOfBoundsException异常，因为这种情况下无法指定有效索引。</p>
     *
     * @param array 要从中删除元素的数组，不能为null，该方法是一个私有方法，关于数组不为null的判断会在调用它的方法中进行处理
     * @param index 要删除的元素的索引位置
     * @return 包含除指定位置的元素之外的现有元素的新数组
     * @throws IndexOutOfBoundsException 如果索引超出范围（index < 0 || index >= array.length</>），或者数组为null
     */
    private static Object remove(Object array, int index) {
        // 获取数组的长度
        int length = getLength(array);
        // 参数校验，判断索引是否越界
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }

        // 创建一个同输入数组类型相同，并且长度减1的新数组，此时的输入数组一定不为null，所以没有相关的参数校验处理
        Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
        // 将输入数组array的前index个元素复制到新数组result中
        System.arraycopy(array, 0, result, 0, index);
        if (index < length - 1) {
            // 然后将array数组中index位置之后的所有元素复制到新数组result中
            System.arraycopy(array, index + 1, result, index, length - index - 1);
        }

        // 返回删除指定位置元素成功的新数组
        return result;
    }

}