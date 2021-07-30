package org.apache.commons.lang;

import org.apache.commons.lang.math.NumberUtils;


/**
 * <p>基本数据类型boolean和包装类型对象Boolean的相关操作方法。</p>
 * <p>此类对null输入也有所处理，在传入null作为方法参数时不会抛出空指针异常。</p>
 * <p>#线程安全#</p>
 *
 * @author lcl100
 * @date 2021-07-29 15:42
 */
public class BooleanUtils {

    /**
     * BooleanUtils实例对象不应该在其他类中被构建，也就是说操作该类应该直接用类名调用该类中的
     * 方法如<code>BooleanUtils.toBooleanObject(true);</code>。此构造函数是公共的，以
     * 允许需要JavaBean实例才能操作的工具。
     */
    public BooleanUtils() {
        super();
    }

    // Boolean utilities，包装类的操作方法
    //--------------------------------------------------------------------------

    /**
     * <p>否定指定的布尔值。即如果输入的是true则返回false，如果输入的是false则返回true。</p>
     * <p>如果传入的参数为null，则返回null。示例如下：</p>
     * <pre>
     *     BooleanUtils.negate(Boolean.TRUE)  = Boolean.FALSE;
     *     BooleanUtils.negate(Boolean.FALSE) = Boolean.TRUE;
     *     BooleanUtils.negate(null)          = null;
     * </pre>
     *
     * @param bool 要被否定的布尔值，可能为null
     * @return 否定后的布尔值，如果输入为null则返回null
     */
    public static Boolean negate(Boolean bool) {
        // 参数校验，如果传入的是null则直接返回null
        if (bool == null) {
            return null;
        }
        // 利用三元表达式来实现否定布尔值
        return (bool.booleanValue() ? Boolean.FALSE : Boolean.TRUE);
    }

    // boolean Boolean methods，基本数据类型boolean与包装类Boolean之间的操作
    //-----------------------------------------------------------------------

    /**
     * <p>检查Boolean值是否为true，如果输入的是null则返回false。</p>
     * <pre>
     *     BooleanUtils.isTrue(Boolean.TRUE)   = true
     *     BBooleanUtils.isTrue(Boolean.FALSE) = false
     *     BBooleanUtils.isTrue(null)          = false
     * </pre>
     *
     * @param bool 待检查的布尔值，null则返回false
     * @return 仅当输入为非空并且为true时才为返回true
     */
    public static boolean isTrue(Boolean bool) {
        // 参数校验，如果输入为null则返回false
        if (bool == null) {
            return false;
        }
        // 检查输入是否为true，三元表达式
        return bool.booleanValue() ? true : false;
    }

    /**
     * <p>检查Boolean值是否不为true，如果输入为null则返回true。</p>
     * <pre>
     *     BooleanUtils.isNotTrue(Boolean.TRUE)  = false
     *     BooleanUtils.isNotTrue(Boolean.FALSE) = true
     *     BooleanUtils.isNotTrue(null)          = true
     * </pre>
     *
     * @param bool 待检查的布尔值，如果输入为null则返回true
     * @return 如果输入为null或为false则返回true
     */
    public static boolean isNotTrue(Boolean bool) {
        // 直接对isTrue()方法进行取反
        return !isTrue(bool);
    }

    /**
     * <p>检查Boolean值是否为false，如果输入为null则返回false处理。</p>
     * <pre>
     *     BooleanUtils.isFalse(Boolean.TRUE)  = false
     *     BooleanUtils.isFalse(Boolean.FALSE) = true
     *     BooleanUtils.isFalse(null)          = false
     * </pre>
     *
     * @param bool 待检查的布尔值，如果输入null则返回false
     * @return 仅当输入为非空且为false时才返回true
     */
    public static boolean isFalse(Boolean bool) {
        // 参数校验，对null输入的处理，如果输入为null则返回false
        if (bool == null) {
            return false;
        }
        // 如果输入为true则返回false，否则返回true
        return bool.booleanValue() ? false : true;
    }

    /**
     * <p>检查Boolean值是否不为false，通过返回true来处理null输入的情况。</p>
     * <pre>
     *     BooleanUtils.isNotFalse(Boolean.TRUE)  = true
     *     BooleanUtils.isNotFalse(Boolean.FALSE) = false
     *     BooleanUtils.isNotFalse(null)          = true
     * </pre>
     *
     * @param bool 待检查的布尔值，如果输入为null则返回true
     * @return 如果输入为空或为true，则返回true
     */
    public static boolean isNotFalse(Boolean bool) {
        return !isFalse(bool);
    }

    // 基本数据类型boolean与包装类Boolean的相互转换
    //-----------------------------------------------------------------------

    /**
     * <p>始终避免创建新布尔对象的布尔工厂。</p>
     * <p>此方法已经添加到JDK1.4，但可在此处用于早期JDK。</p>
     * <pre>
     *     BooleanUtils.toBooleanObject(false) = Boolean.FALSE
     *     BooleanUtils.toBooleanObject(true)  = Boolean.TRUE
     * </pre>
     *
     * @param bool 要转换的基本数据类型布尔值
     * @return 如果是true则返回Boolean.TRUE，如果是false则返回Boolean.FALSE
     */
    public static Boolean toBooleanObject(boolean bool) {
        // Boolean.TRUE和Boolean.FALSE是Boolean类定义的常量
        return bool ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * <p>将包装类型Boolean转换成基本数据类型boolean。</p>
     * <p><i>注：由于输入参数属于引用数据类型，所以可能传入null，该方法的处理就是当输入null时返回false。</i></p>
     * <pre>
     *     BooleanUtils.toBoolean(Boolean.TRUE)  = true
     *     BooleanUtils.toBoolean(Boolean.FALSE) = false
     *     BooleanUtils.toBoolean(null)          = false
     * </pre>
     *
     * @param bool 要转换成基本数据类型的Boolean对象
     * @return 如果输入true则返回true，如果输入false或null则返回false
     */
    public static boolean toBoolean(Boolean bool) {
        // 参数校验，当输入为null时返回false
        if (bool == null) {
            return false;
        }
        return bool.booleanValue() ? true : false;
    }

    /**
     * <p>将包装类型Boolean转换成基本数据类型boolean。</p>
     * <p>该方法对于null输入的解决就是，再传入一个基本数据类型boolean参数valueIfNull，当Boolean输入参数bool为null时返回boolean输入参数valueIfNull。</p>
     * <pre>
     *     BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, false) = true
     *     BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, true) = false
     *     BooleanUtils.toBooleanDefaultIfNull(null, true)          = true
     * </pre>
     *
     * @param bool        要转换成包装类的基本数据类型布尔值
     * @param valueIfNull 当为null时指定返回的布尔值
     * @return true或false
     */
    public static boolean toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull) {
        // 参数校验，对null输入的处理
        if (bool == null) {
            return valueIfNull;
        }
        return bool.booleanValue() ? true : false;
    }

    // Integer to Boolean methods
    //-----------------------------------------------------------------------

    /**
     * <p>使用0为false的约定将int值转换成基本数据类型boolean值。即只有当输入0时返回false，其他任何int值都返回true。</p>
     * <pre>
     *     BooleanUtils.toBoolean(0) = false
     *     BooleanUtils.toBoolean(1) = true
     *     BooleanUtils.toBoolean(2) = true
     * </pre>
     *
     * @param value 要转换的int值
     * @return 非零时返回true，输入零则返回false
     */
    public static boolean toBoolean(int value) {
        // 直接判断value!=0即可
        return value == 0 ? false : true;
    }

    /**
     * <p>使用0为false的约定将int值转换成包装类Boolean。即只有当输入0时返回false，其他任何int值都返回true。</p>
     * <pre>
     *   BooleanUtils.toBoolean(0) = Boolean.FALSE
     *   BooleanUtils.toBoolean(1) = Boolean.TRUE
     *   BooleanUtils.toBoolean(2) = Boolean.TRUE
     * </pre>
     *
     * @param value 要转换的int值
     * @return 如果输入非零则返回Boolean.TRUE，如果输入零则返回Boolean.FALSE
     */
    public static Boolean toBooleanObject(int value) {
        return value == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * <p>使用0为false的约定将Integer转换为Boolean。</p>
     * <p><i>注：如果输入为null，则返回null。</i></p>
     * <pre>
     *     BooleanUtils.toBoolean(new Integer(0))    = Boolean.FALSE
     *     BooleanUtils.toBoolean(new Integer(1))    = Boolean.TRUE
     *     BooleanUtils.toBoolean(new Integer(null)) = null
     * </pre>
     *
     * @param value 要转换的Integer值
     * @return 如果输入非零则返回Boolean.TRUE，如果输入为零则返回Boolean.FALSE，如果输入为null则返回null
     */
    public static Boolean toBooleanObject(Integer value) {
        // 参数校验，对于null输入则null返回
        if (value == null) {
            return null;
        }
        return value.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * <p>将int值转为指定转换值的布尔值。</p>
     * <p>即如果value==trueValue则返回true，如果value==falseValue则返回false。</p>
     * <pre>
     *     BooleanUtils.toBoolean(0, 1, 0) = false
     *     BooleanUtils.toBoolean(1, 1, 0) = true
     *     BooleanUtils.toBoolean(2, 1, 2) = false
     *     BooleanUtils.toBoolean(2, 2, 0) = true
     * </pre>
     *
     * @param value      要转换的整数
     * @param trueValue  与true匹配的值
     * @param falseValue 与false匹配的值
     * @return true或false
     * @throws IllegalArgumentException 如果value既没有匹配到trueValue又没有匹配到falseValue则抛出此异常
     */
    public static boolean toBoolean(int value, int trueValue, int falseValue) {
        // 参数匹配
        if (value == trueValue) {
            return true;
        } else if (value == falseValue) {
            return false;
        }
        // 两个值都没有匹配到则抛出异常
        throw new IllegalArgumentException("The Integer did not match either specified value");
    }

    /**
     * <p>将整数转换为指定转换值的布尔值。</p>
     * <p>即如果value==trueValue则返回true，如果value==falseValue则返回false。</p>
     * <p><i>注：该方法和上面的重载方法效果是一样的，但由于Integer属于包装类，会有null输入的情况，所以需要对null输入进行判断处理。</i></p>
     * <pre>
     *     BooleanUtils.toBoolean(new Integer(0), new Integer(1), new Integer(0)) = false
     *     BooleanUtils.toBoolean(new Integer(1), new Integer(1), new Integer(0)) = true
     *     BooleanUtils.toBoolean(new Integer(2), new Integer(1), new Integer(2)) = false
     *     BooleanUtils.toBoolean(new Integer(2), new Integer(2), new Integer(0)) = true
     *     BooleanUtils.toBoolean(null, null, new Integer(0))                     = true
     * </pre>
     *
     * @param value      要转换的整数
     * @param trueValue  与true匹配的值
     * @param falseValue 与false匹配的值
     * @return true或false
     * @throws IllegalArgumentException 如果value既没有匹配到trueValue又没有匹配到falseValue则抛出此异常
     */
    public static boolean toBoolean(Integer value, Integer trueValue, Integer falseValue) {
        // 参数校验，对null输入进行处理
        if (value == null) {
            if (trueValue == null) {
                // 如果value和trueValue都为null则返回true
                return true;
            } else if (falseValue == null) {
                // 如果value和falseValue都为null则返回false
                return false;
            }
        } else if (value.equals(trueValue)) {
            return true;
        } else if (value.equals(falseValue)) {
            return false;
        }
        // 如果都没有匹配到则抛出此异常
        throw new IllegalArgumentException("The Integer did not match either specified value");
    }

    /**
     * <p>将int转换为指定转换值的布尔值。</p>
     * <p>即如果value==trueValue则返回true，如果value==falseValue则返回false，如果value==nullValue则返回null。</p>
     * <pre>
     *     BooleanUtils.toBooleanObject(0, 0, 2, 3) = Boolean.TRUE
     *     BooleanUtils.toBooleanObject(2, 1, 2, 3) = Boolean.FALSE
     *     BooleanUtils.toBooleanObject(3, 1, 2, 3) = null
     * </pre>
     *
     * @param value      要转换的整数
     * @param trueValue  与true匹配的值
     * @param falseValue 与false匹配的值
     * @param nullValue  与null匹配的值
     * @return Boolean.TRUE、Boolean.FALSE或null
     * @throws IllegalArgumentException 如果没有匹配项
     */
    public static Boolean toBooleanObject(int value, int trueValue, int falseValue, int nullValue) {
        // 匹配指定值，然后根据条件判断返回
        if (value == trueValue) {
            return Boolean.TRUE;
        } else if (value == falseValue) {
            return Boolean.FALSE;
        } else if (value == nullValue) {
            return null;
        }
        // 如果都没有匹配到，则抛出异常
        throw new IllegalArgumentException("The Integer did not match any specified value");
    }

    /**
     * <p>将Integer转换为指定转换值的布尔值。</p>
     * <p>即如果value==trueValue则返回true，如果value==falseValue则返回false，如果value==nullValue则返回null。</p>
     * <p><i>注：该方法和上面的重载方法效果是一样的，但由于Integer属于包装类，会有null输入的情况，所以需要对null输入进行判断处理。</i></p>
     * <pre>
     *     BooleanUtils.toBooleanObject(new Integer(0), new Integer(0), new Integer(2), new Integer(3)) = Boolean.TRUE
     *     BooleanUtils.toBooleanObject(new Integer(2), new Integer(1), new Integer(2), new Integer(3)) = Boolean.FALSE
     *     BooleanUtils.toBooleanObject(new Integer(3), new Integer(1), new Integer(2), new Integer(3)) = null
     * </pre>
     *
     * @param value      要转换的整数
     * @param trueValue  与true匹配的值，可能为null
     * @param falseValue 与false匹配的值，可能为null
     * @param nullValue  与null匹配的值，可能为null
     * @return Boolean.TRUE、Boolean.FALSE或null
     * @throws IllegalArgumentException 如果没有匹配项
     */
    public static Boolean toBooleanObject(Integer value, Integer trueValue, Integer falseValue, Integer nullValue) {
        // 对null输入的处理
        if (value == null) {
            if (trueValue == null) {
                return Boolean.TRUE;
            } else if (falseValue == null) {
                return Boolean.FALSE;
            } else if (nullValue == null) {
                return null;
            }
        } else if (value.equals(trueValue)) {
            return Boolean.TRUE;
        } else if (value.equals(falseValue)) {
            return Boolean.FALSE;
        } else if (value.equals(nullValue)) {
            return null;
        }
        // 如果都没有匹配到则抛出此异常
        throw new IllegalArgumentException("The Integer did not match any specified value");
    }

    // Boolean to Integer methods，布尔值转换成整数的方法
    //-----------------------------------------------------------------------

    /**
     * <p>使用0为false的约定将布尔值转换为int值。</p>
     * <pre>
     *     BooleanUtils.toInteger(true)  = 1
     *     BooleanUtils.toInteger(false) = 0
     * </pre>
     *
     * @param bool 要转换的布尔值
     * @return 当输入为true时返回1，当输入为false时返回0
     */
    public static int toInteger(boolean bool) {
        return bool ? 1 : 0;
    }

    /**
     * <p>使用0为false的约定将布尔值转换为Integer整数</p>
     * <pre>
     *     BooleanUtils.toIntegerObject(true)  = new Integer(1)
     *     BooleanUtils.toIntegerObject(false) = new Integer(0)
     * </pre>
     *
     * @param bool 要转换的布尔值
     * @return 当输入为true时返回1，当输入为false时返回0
     */
    public static Integer toIntegerObject(boolean bool) {
        // 返回的是包装类Integer，直接返回NumberUtils类中已经定义好的常量
        return bool ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
    }

    /**
     * <p>使用0为false的约定将Boolean类型的布尔值转换为Integer类型的整数。</p>
     * <p><i>注：当输入为null时则返回null。</i></p>
     * <pre>
     *     BooleanUtils.toIntegerObject(Boolean.TRUE)  = new Integer(1)
     *     BooleanUtils.toIntegerObject(Boolean.FALSE) = new Integer(0)
     * </pre>
     *
     * @param bool 要转换的布尔值
     * @return 如果输入Boolean.TRUE则返回1，如果输入Boolean.FALSE则返回0，如果输入null则返回null
     */
    public static Integer toIntegerObject(Boolean bool) {
        // 对null输入的判断处理
        if (bool == null) {
            return null;
        }
        // 如果输入Boolean.TRUE则返回1，如果输入Boolean.FALSE则返回0
        return bool.booleanValue() ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
    }

    /**
     * <p>将布尔值转换为指定int值。</p>
     * <p>即如果输入参数bool为true则返回trueValue，如果输入参数bool为false则返回falseValue</p>
     * <pre>
     *   BooleanUtils.toInteger(true, 1, 0)  = 1
     *   BooleanUtils.toInteger(false, 1, 0) = 0
     * </pre>
     *
     * @param bool       待转换的布尔值
     * @param trueValue  如果为true要返回的int值
     * @param falseValue 如果为false要返回的int值
     * @return 恰当的值
     */
    public static int toInteger(boolean bool, int trueValue, int falseValue) {
        return bool ? trueValue : falseValue;
    }

    /**
     * <p>将包装类型的布尔值转换成指定的int整数。</p>
     * <pre>
     *     BooleanUtils.toInteger(Boolean.TRUE, 1, 0, 2)  = 1
     *     BooleanUtils.toInteger(Boolean.FALSE, 1, 0, 2) = 0
     *     BooleanUtils.toInteger(null, 1, 0, 2)          = 2
     * </pre>
     *
     * @param bool       要转换的布尔值
     * @param trueValue  如果为true则返回的int值
     * @param falseValue 如果为false则返回的int值
     * @param nullValue  如果为null则返回的值，因为输入参数bool是包装类型的，必须进行null输入判断
     * @return 恰当的值
     */
    public static int toInteger(Boolean bool, int trueValue, int falseValue, int nullValue) {
        // 对null参数进行处理
        if (bool == null) {
            return nullValue;
        }
        return bool.booleanValue() ? trueValue : falseValue;
    }

    /**
     * <p>将boolean值转换为指定的Integer值。</p>
     * <pre>
     *     BooleanUtils.toIntegerObject(true, new Integer(1), new Integer(0))  = new Integer(1)
     *     BooleanUtils.toIntegerObject(false, new Integer(1), new Integer(0)) = new Integer(0)
     * </pre>
     *
     * @param bool       要转换的boolean值
     * @param trueValue  如果为true则返回的值，可能为null
     * @param falseValue 如果为false则返回的值，可能为null
     * @return 恰当的值
     */
    public static Integer toIntegerObject(boolean bool, Integer trueValue, Integer falseValue) {
        return bool ? trueValue : falseValue;
    }

    /**
     * <p>将Boolean值转换成Integer整数。</p>
     * <p><i>注：因为输入参数bool是包装类型Boolean，可能有null输入，所以需要进行处理。</i></p>
     * <pre>
     *     BooleanUtils.toIntegerObject(Boolean.TRUE, new Integer(1), new Integer(0), new Integer(2))  = new Integer(1)
     *     BooleanUtils.toIntegerObject(Boolean.FALSE, new Integer(1), new Integer(0), new Integer(2)) = new Integer(0)
     *     BooleanUtils.toIntegerObject(null, new Integer(1), new Integer(0), new Integer(2))          = new Integer(2)
     * </pre>
     *
     * @param bool       要转换的布尔值
     * @param trueValue  如果为true则返回的Integer值，可能为null
     * @param falseValue 如果为false则返回的Integer值，可能为null
     * @param nullValue  如果为null则返回的null，可能为null
     * @return 恰当的值
     */
    public static Integer toIntegerObject(Boolean bool, Integer trueValue, Integer falseValue, Integer nullValue) {
        // 对null输入进行特殊处理
        if (bool == null) {
            return nullValue;
        }
        return bool.booleanValue() ? trueValue : falseValue;
    }

    // String to Boolean methods，字符串输入参数转换成Boolean类型的输出方法
    //-----------------------------------------------------------------------

    /**
     * <p>将字符串转换成布尔值。</p>
     * <p>"true"、"on"或"yes"（不区分大小写）将返回true。"false"、"off"或"no"（不区分大小写）则将返回false。否则返回null。</p>
     * <p>可以有如下五种输入情况：</p>
     * <ul>
     *     <li>当输入字符串只有一个字符时，不区分大小写，如果输入"y"、"Y"、"t"和"T"则返回true，如果输入"n"、"N"、"f"和"F"则返回false。</li>
     *     <li>当输入字符串有两个字符时，不区分大小写，如果输入的是"on"、"oN"、"On"或"ON"则返回true，如果输入的是"no"、"nO"、"No"或"NO"则返回false。</li>
     *     <li>当输入字符串有三个字符时，不区分大小写，如果输入的是"yes"、"YES"等则返回true，如果输入的是"off"、"OFF"则返回false。</li>
     *     <li>当输入字符串有四个字符时，不区分大小写，如果输入的是"true"则返回true。</li>
     *     <li>当输入字符串有五个字符时，不区分大小写，如果输入的是"false"则返回false。</li>
     * </ul>
     * <pre>
     *     BooleanUtils.toBooleanObject(null)    = null
     *     BooleanUtils.toBooleanObject("true")  = Boolean.TRUE
     *     BooleanUtils.toBooleanObject("false") = Boolean.FALSE
     *     BooleanUtils.toBooleanObject("on")    = Boolean.TRUE
     *     BooleanUtils.toBooleanObject("ON")    = Boolean.TRUE
     *     BooleanUtils.toBooleanObject("off")   = Boolean.FALSE
     *     BooleanUtils.toBooleanObject("oFf")   = Boolean.FALSE
     *     BooleanUtils.toBooleanObject("blue")  = null
     * </pre>
     *
     * @param str 待转换的字符串
     * @return 转换后的布尔值，如果没有匹配到或输入null则返回null
     */
    public static Boolean toBooleanObject(String str) {
        // Previously used equalsIgnoreCase, which was fast for interned 'true'.
        // Non interned 'true' matched 15 times slower.
        //
        // Optimisation provides same performance as before for interned 'true'.
        // Similar performance for null, 'false', and other strings not length 2/3/4.
        // 'true'/'TRUE' match 4 times slower, 'tRUE'/'True' 7 times slower.
        // 大多数情况下str为"true"，所以先匹配，然后快速直接返回。注意，不能用equals方法，否则可能出现空指针异常
        if (str == "true") {
            return Boolean.TRUE;
        }
        // 对null进行判断处理
        if (str == null) {
            return null;
        }
        // 根据输入字符串的长度来匹配不同的情况
        switch (str.length()) {
            // 当输入字符串只有一个字符时，不区分大小写，如果输入"y"、"Y"、"t"和"T"则返回true，如果输入"n"、"N"、"f"和"F"则返回false
            case 1: {
                // 获取第一个字符
                char ch0 = str.charAt(0);
                // 不区分大小写，"y"和"t"分别是"yes"和"true"的首字母，表示true
                if ((ch0 == 'y' || ch0 == 'Y') ||
                        (ch0 == 't' || ch0 == 'T')) {
                    return Boolean.TRUE;
                }
                // 不区分大小写，"n"和"f"分别是"no"和"false"的首字母，表示false
                if ((ch0 == 'n' || ch0 == 'N') ||
                        (ch0 == 'f' || ch0 == 'F')) {
                    return Boolean.FALSE;
                }
                break;
            }
            // 当输入字符串有两个字符时，不区分大小写，如果输入的是"on"、"oN"、"On"或"ON"则返回true，如果输入的是"no"、"nO"、"No"或"NO"则返回false
            case 2: {
                // 获取字符串第一个字符
                char ch0 = str.charAt(0);
                // 获取字符串第二个字符
                char ch1 = str.charAt(1);
                if ((ch0 == 'o' || ch0 == 'O') &&
                        (ch1 == 'n' || ch1 == 'N')) {
                    return Boolean.TRUE;
                }
                if ((ch0 == 'n' || ch0 == 'N') &&
                        (ch1 == 'o' || ch1 == 'O')) {
                    return Boolean.FALSE;
                }
                break;
            }
            // 当输入字符串有三个字符时，不区分大小写，如果输入的是"yes"、"YES"等则返回true，如果输入的是"off"、"OFF"则返回false
            case 3: {
                // 获取字符串中的三个字符
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char ch2 = str.charAt(2);
                if ((ch0 == 'y' || ch0 == 'Y') &&
                        (ch1 == 'e' || ch1 == 'E') &&
                        (ch2 == 's' || ch2 == 'S')) {
                    return Boolean.TRUE;
                }
                if ((ch0 == 'o' || ch0 == 'O') &&
                        (ch1 == 'f' || ch1 == 'F') &&
                        (ch2 == 'f' || ch2 == 'F')) {
                    return Boolean.FALSE;
                }
                break;
            }
            // 当输入字符串有四个字符时，不区分大小写，如果输入的是"true"则返回true
            case 4: {
                // 获取四个字符
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char ch2 = str.charAt(2);
                char ch3 = str.charAt(3);
                if ((ch0 == 't' || ch0 == 'T') &&
                        (ch1 == 'r' || ch1 == 'R') &&
                        (ch2 == 'u' || ch2 == 'U') &&
                        (ch3 == 'e' || ch3 == 'E')) {
                    return Boolean.TRUE;
                }
                break;
            }
            // 当输入字符串有五个字符时，不区分大小写，如果输入的是"false"则返回false
            case 5: {
                // 获取五个字符
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char ch2 = str.charAt(2);
                char ch3 = str.charAt(3);
                char ch4 = str.charAt(4);
                if ((ch0 == 'f' || ch0 == 'F') &&
                        (ch1 == 'a' || ch1 == 'A') &&
                        (ch2 == 'l' || ch2 == 'L') &&
                        (ch3 == 's' || ch3 == 'S') &&
                        (ch4 == 'e' || ch4 == 'E')) {
                    return Boolean.FALSE;
                }
                break;
            }
        }

        return null;
    }

    /**
     * <p>将字符串转换为布尔值，如果不匹配则抛出异常。</p>
     * <pre>
     *     BooleanUtils.toBooleanObject("true", "true", "false", "null")  = Boolean.TRUE
     *     BooleanUtils.toBooleanObject("false", "true", "false", "null") = Boolean.FALSE
     *     BooleanUtils.toBooleanObject("null", "true", "false", "null")  = null
     * </pre>
     *
     * @param str         待检查的字符串
     * @param trueString  与true匹配的字符串，区分大小写，可能为null
     * @param falseString 与false匹配的字符串，区分大小写，可能为null
     * @param nullString  与null匹配的字符串，区分大小写，可能为null
     * @return 字符串的布尔值，如果输入字符串为null或者匹配到null则返回null
     * @throws IllegalArgumentException 如果字符串不匹配
     */
    public static Boolean toBooleanObject(String str, String trueString, String falseString, String nullString) {
        // 参数校验
        if (str == null) {
            // 判断null输入的情况
            if (trueString == null) {
                return Boolean.TRUE;
            } else if (falseString == null) {
                return Boolean.FALSE;
            } else if (nullString == null) {
                return null;
            }
        } else if (str.equals(trueString)) {
            return Boolean.TRUE;
        } else if (str.equals(falseString)) {
            return Boolean.FALSE;
        } else if (str.equals(nullString)) {
            return null;
        }
        // 没有匹配到则抛出异常
        throw new IllegalArgumentException("The String did not match any specified value");
    }

    // String to boolean methods，将字符串转换成基本数据类型boolean
    //-----------------------------------------------------------------------

    /**
     * <p>将字符串转换为boolean值（针对性能进行了优化）。</p>
     * <p>"true"、"on"或"yes"（不区分大小写）将返回true，否则，返回false。</p>
     * <p>此方法的执行速度（JDK1.4）比Boolean.valueOf(String)快四倍。注意，此方法接受"on"和"yes"作为真值。</p>
     * <pre>
     *     BooleanUtils.toBoolean(null)    = false
     *     BooleanUtils.toBoolean("true")  = true
     *     BooleanUtils.toBoolean("TRUE")  = true
     *     BooleanUtils.toBoolean("tRUe")  = true
     *     BooleanUtils.toBoolean("on")    = true
     *     BooleanUtils.toBoolean("yes")   = true
     *     BooleanUtils.toBoolean("false") = false
     *     BooleanUtils.toBoolean("x gti") = false
     * </pre>
     *
     * @param str 待检查的字符串
     * @return 字符串的布尔值，如果没有匹配或者字符串为空则返回false
     */
    public static boolean toBoolean(String str) {
        return toBoolean(toBooleanObject(str));
    }

    /**
     * <p>将字符串转换为boolean值，如果找不到匹配则会抛出异常。</p>
     * <pre>
     *   BooleanUtils.toBoolean("true", "true", "false")  = true
     *   BooleanUtils.toBoolean("false", "true", "false") = false
     * </pre>
     *
     * @param str         待转换的字符串
     * @param trueString  与true匹配的字符串，区分大小写，可能为null
     * @param falseString 与false匹配的字符串，区分大小写，可能为null
     * @return 字符串的布尔值
     * @throws IllegalArgumentException 如果字符串不匹配则抛出此异常
     */
    public static boolean toBoolean(String str, String trueString, String falseString) {
        if (str == null) {
            if (trueString == null) {
                return true;
            } else if (falseString == null) {
                return false;
            }
        } else if (str.equals(trueString)) {
            return true;
        } else if (str.equals(falseString)) {
            return false;
        }
        // 如果没有匹配则抛出异常
        throw new IllegalArgumentException("The String did not match either specified value");
    }

    // Boolean to String methods，Boolean值转换成字符串的方法
    //-----------------------------------------------------------------------

    /**
     * <p>将Boolean值转换为"true"、"false"或null的字符串。</p>
     * <pre>
     *     BooleanUtils.toStringTrueFalse(Boolean.TRUE)  = "true"
     *     BooleanUtils.toStringTrueFalse(Boolean.FALSE) = "false"
     *     BooleanUtils.toStringTrueFalse(null)          = null;
     * </pre>
     *
     * @param bool 待转换的Boolean值
     * @return "true"、"false"或null
     */
    public static String toStringTrueFalse(Boolean bool) {
        return toString(bool, "true", "false", null);
    }

    /**
     * <p>将Boolean值转换为"on"、"off"或null的字符串。</p>
     * <pre>
     *     BooleanUtils.toStringOnOff(Boolean.TRUE)  = "on"
     *     BooleanUtils.toStringOnOff(Boolean.FALSE) = "off"
     *     BooleanUtils.toStringOnOff(null)          = null;
     * </pre>
     *
     * @param bool 待转换的Boolean值
     * @return "on"、"off"或null
     */
    public static String toStringOnOff(Boolean bool) {
        // "on"和"off"相当于true和false
        return toString(bool, "on", "off", null);
    }

    /**
     * <p>将Boolean值转换为"yes"、"no"或null的字符串。</p>
     * <pre>
     *     BooleanUtils.toStringYesNo(Boolean.TRUE)  = "yes"
     *     BooleanUtils.toStringYesNo(Boolean.FALSE) = "no"
     *     BooleanUtils.toStringYesNo(null)          = null;
     * </pre>
     *
     * @param bool 待转换的Boolean值
     * @return "yes"、"on"或null
     */
    public static String toStringYesNo(Boolean bool) {
        return toString(bool, "yes", "no", null);
    }

    /**
     * <p>将Boolean值转换为输入字符串之一返回。</p>
     * <pre>
     *     BooleanUtils.toString(Boolean.TRUE, "true", "false", null)   = "true"
     *     BooleanUtils.toString(Boolean.FALSE, "true", "false", null)  = "false"
     *     BooleanUtils.toString(null, "true", "false", null)           = null;
     * </pre>
     *
     * @param bool        待转换的Boolean值
     * @param trueString  如果为true则返回的字符串，可能为null
     * @param falseString 如果为false则返回的字符串，可能为null
     * @param nullString  如果为null则返回的字符串，可能为null
     * @return 三个输入字符串之一，可能为null
     */
    public static String toString(Boolean bool, String trueString, String falseString, String nullString) {
        // 如果输入的Boolean对象为null则返回nullString，当然nullString可能也是null
        if (bool == null) {
            return nullString;
        }
        // 根据true还是false返回对应的字符串
        return bool.booleanValue() ? trueString : falseString;
    }

    // boolean to String methods，基本数据类型boolean值转换成字符串的方法
    //-----------------------------------------------------------------------

    /**
     * <p>将基本数据类型boolean值转换成"true"或"false"的字符串。</p>
     * <pre>
     *     BooleanUtils.toStringTrueFalse(true)   = "true"
     *     BooleanUtils.toStringTrueFalse(false)  = "false"
     * </pre>
     *
     * @param bool 待转换的boolean值
     * @return "true"或"false"
     */
    public static String toStringTrueFalse(boolean bool) {
        // 如果bool为true则返回"true"，否则返回"false"
        return toString(bool, "true", "false");
    }

    /**
     * <p>将基本数据类型boolean值转换成"on"或"off"的字符串。</p>
     * <pre>
     *     BooleanUtils.toStringOnOff(true)   = "on"
     *     BooleanUtils.toStringOnOff(false)  = "off"
     * </pre>
     *
     * @param bool 待转换的boolean值
     * @return "on"或"off"
     */
    public static String toStringOnOff(boolean bool) {
        return toString(bool, "on", "off");
    }

    /**
     * <p>将基本数据类型boolean值转换成"yes"或"no"的字符串。</p>
     * <pre>
     *     BooleanUtils.toStringYesNo(true)   = "yes"
     *     BooleanUtils.toStringYesNo(false)  = "no"
     * </pre>
     *
     * @param bool 待转换的boolean值
     * @return "yes"或"no"
     */
    public static String toStringYesNo(boolean bool) {
        return toString(bool, "yes", "no");
    }

    /**
     * <p>将boolean值转换成输入字符串之一返回。</p>
     * <pre>
     *   BooleanUtils.toString(true, "true", "false")   = "true"
     *   BooleanUtils.toString(false, "true", "false")  = "false"
     * </pre>
     *
     * @param bool        待转换的字符串
     * @param trueString  如果为true则会返回的字符串，可能为null
     * @param falseString 如果为false则会返回的字符串，可能为null
     * @return 两个输入的字符串之一
     */
    public static String toString(boolean bool, String trueString, String falseString) {
        return bool ? trueString : falseString;
    }

    // xor methods，异或的方法
    // ----------------------------------------------------------------------

    /**
     * <p>对一组boolean类型的布尔值进行异或，并且返回值是boolean类型的。</p>
     * <p>有三种情况（相异为true，相同为false）：</p>
     * <ul>
     *     <li>true ^ true = false</li>
     *     <li>false ^ false = false</li>
     *     <li>true ^ false = true</li>
     * </ul>
     * <p>其实这样也能进行异或运算的，如：<code>System.out.println(true^true^false^true^false);</code>，但注意，这个代码有问题。如System.out.println(true^true^true);的结果与该方法xor(new boolean[]{true,true,true})的结果不一样。</p>
     * <p><i>注：经过查找资料，该方法的结果是错误的，请忽略。对于该问题的参考资料请查看
     * <a href="https://stackoverflow.com/questions/68587686/the-result-of-xortrue-true-true-of-the-booleanutils-class-under-the-commons">
     *     The result of xor(true, true, true) of the BooleanUtils class under the commons-lang toolkit under Apache is false, why?</a>。除此之外，
     *     你还可以在最新的<a href="https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/BooleanUtils.java">GitHub源码地址</a>看到已经修复了该问题。</i></p>
     *
     * @param array boolean数组
     * @return 如果异或成功则返回true，否则返回false
     * @throws IllegalArgumentException 如果array为null
     * @throws IllegalArgumentException 如果array为空，即零个元素但不为null
     */
    public static boolean xor(boolean[] array) {
        // 参数校验，校验数组array是否为null和空
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        // 计数器，记录数组中true的个数
        int trueCount = 0;
        // 循环遍历数组，比较每一项
        for (int i = 0; i < array.length; i++) {
            // 当array[i]为true时执行下面的代码
            if (array[i]) {
                // 当出现两个true时就会返回false，所以trueCount必须小于1才有可能返回true
                if (trueCount < 1) {
                    trueCount++;
                } else {
                    return false;
                }
            }
        }

        // 如果最后trueCount正好等于1则返回true
        return trueCount == 1;
    }

    /**
     * <p>对一组Boolean类型的布尔值进行异或，并且返回值是Boolean类型的。</p>
     * <p>有三种情况（相异为true，相同为false）：</p>
     * <ul>
     *     <li>Boolean.TRUE ^ Boolean.TRUE = Boolean.FALSE</li>
     *     <li>Boolean.FALSE ^ Boolean.FALSE = Boolean.FALSE</li>
     *     <li>Boolean.TRUE ^ Boolean.FALSE = Boolean.TRUE</li>
     * </ul>
     *
     * @param array Boolean数组
     * @return 如果异或成功则返回Boolean.TRUE，否则返回Boolean.FALSE
     * @throws IllegalArgumentException 如果array为null
     * @throws IllegalArgumentException 如果array为空，即零个元素但不为null
     * @throws IllegalArgumentException 如果array不为空但是包含null元素
     */
    public static Boolean xor(Boolean[] array) {
        // 参数校验，判断传入数组array为null和为空的情况，然后抛出异常
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        // 创建一个boolean类型的数组
        boolean[] primitive = null;
        try {
            // 调用ArrayUtils.toPrimitive()方法将Boolean类型数组转换成boolean类型数组
            primitive = ArrayUtils.toPrimitive(array);
        } catch (NullPointerException ex) {
            // 但数组中可能存在null元素，当将一个null赋给一个基本数据类型boolean时就会抛出异常
            throw new IllegalArgumentException("The array must not contain any null elements");
        }
        // 最后调用xor重载方法将boolean类型数组进行异或运算
        return xor(primitive) ? Boolean.TRUE : Boolean.FALSE;
    }

}
