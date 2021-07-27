package org.apache.commons.lang;


import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lcl100
 * @create 2021-07-26 22:22
 *
 * <p>方便验证参数的工具类</p>
 * <p>当如果认为传入的参数值无效，则会抛出IllegalArgumentException异常，都是直接抛出异常。示例如下：</p>
 * <pre>
 *     Validate.isTrue(i>0, "参数i大于0");
 *     Validate.notNull(name, "name参数必须不能为null");
 * </pre>
 */
public class Validate {
    // Validate类目前没有对commons.lang中的其他类进行依赖

    /**
     * 构造函数，但通常不应该实例化此类，因为只需要使用该类中的静态方法即可，可以直接通过类名调用静态方法
     */
    public Validate() {
        super();
    }

    // isTrue方法
    //---------------------------------------------------------------------------------

    /**
     * <p>验证参数条件是否为true，如果不为true则抛出带有指定消息的异常。可以用于验证对象或使用您自己的自定义验证表达式。例如：</p>
     * <pre>
     *     Validate.isTrue(result.isOk(), "这个result不是Ok的：", result);
     * </pre>
     * <p>出于性能原因，对象值作为单独的参数传递并且仅仅只出现错误时附加到的异常信息</p>
     *
     * @param expression 待校验的布尔表达式，表达式的值必须是一个布尔值
     * @param message    当出现错误时，提示的错误消息
     * @param value      错误的对象值，即表达式无效时附加到消息的值
     * @throws IllegalArgumentException 如果表达式为false
     */
    public static void isTrue(boolean expression, String message, Object value) {
        // 判断给定的布尔表达式是否为false，如果为false则抛出IllegalArgumentException异常，并提示错误消息
        /*
            if (expression == false)
                等价于
            if (!expression)
         */
        if (expression == false) {
            throw new IllegalArgumentException(message + value);
        }
    }

    /**
     * <p>验证参数条件是否为true，如果不为true则抛出带有指定消息的异常。可以用于验证原始数字或使用您自己的自定义验证表达式。例如：</p>
     * <pre>
     *     Validate.isTrue(i > 1.5, "这个i没有大于1.5：", i);
     * </pre>
     * <p>出于性能原因，long值作为单独的参数传递并且仅仅只出现错误时附加到的异常信息。</p>
     * <p>同上一个方法一样，唯一不同的是第三个参数是long值，通常用于校验数字的有效性。</p>
     *
     * @param expression 待校验的布尔表达式，表达式的值必须是一个布尔值
     * @param message    当出现错误时，提示的错误消息
     * @param value      错误的值，即表达式无效时附加到消息的值
     * @throws IllegalArgumentException 如果表达式为false
     */
    public static void isTrue(boolean expression, String message, long value) {
        if (expression == false) {
            throw new IllegalArgumentException(message + value);
        }
    }

    /**
     * <p>验证参数条件是否为true，如果不为true则抛出带有指定消息的异常。可以用于验证原始数字或使用您自己的自定义验证表达式。例如：</p>
     * <pre>
     *     Validate.isTrue(i > 1.5, "这个i没有大于1.5：", i);
     * </pre>
     * <p>出于性能原因，double值作为单独的参数传递并且仅仅只出现错误时附加到的异常信息。</p>
     * <p>同上一个方法一样，唯一不同的是第三个参数是double值，精度更高，通常用于校验数字的有效性。</p>
     *
     * @param expression 待校验的布尔表达式，表达式的值必须是一个布尔值
     * @param message    当出现错误时，提示的错误消息
     * @param value      错误的值，即表达式无效时附加到消息的值
     * @throws IllegalArgumentException 如果表达式为false
     */
    public static void isTrue(boolean expression, String message, double value) {
        if (expression == false) {
            throw new IllegalArgumentException(message + value);
        }
    }

    /**
     * <p>验证参数条件是否为true，如果不为true则抛出带有指定消息的异常。可以用于验证原始数字或使用您自己的自定义验证表达式。例如：</p>
     * <pre>
     *     Validate.isTrue(i > 1.5, "这个i没有大于1.5：");
     *     Validate.isTrue(result.isOk(), "这个result不是Ok的");
     * </pre>
     * <p>也是isTrue()的重载方法，但发生异常时只会给出提示消息，而不会给出是那个对象或数值发生了错误。</p>
     *
     * @param expression 待校验的布尔表达式，表达式的值必须是一个布尔值
     * @param message    当出现错误时，提示的错误消息
     * @throws IllegalArgumentException 如果表达式为false
     */
    public static void isTrue(boolean expression, String message) {
        if (expression == false) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>验证参数条件是否为true，如果不为true则抛出带有指定消息的异常。可以用于验证原始数字或使用您自己的自定义验证表达式。例如：</p>
     * <pre>
     *     Validate.isTrue(i > 1.5);
     *     Validate.isTrue(result.isOk());
     * </pre>
     * <p>也是isTrue()的重载方法，给出了默认的提示消息："待验证的表达式为假"</p>
     *
     * @param expression 待校验的布尔表达式，表达式的值必须是一个布尔值
     * @throws IllegalArgumentException 如果表达式为false
     */
    public static void isTrue(boolean expression) {
        if (expression == false) {
            // 当表达式为false时，抛出此异常，给出默认的提示消息
            throw new IllegalArgumentException("The validated expression is false");
        }
    }

    // notNull方法，判断指定对象是否不为null
    //---------------------------------------------------------------------------------

    /**
     * <p>验证指定的参数不为null，否则抛出异常</p>
     * <pre>
     *     Validate.notNull(obj);
     * </pre>
     * <p>当传入的参数为null时，则抛出IllegalArgumentException异常并给出默认的提示消息："验证的对象为空"。</p>
     *
     * @param object 待校验的对象
     * @throws IllegalArgumentException 如果对象为null
     */
    public static void notNull(Object object) {
        notNull(object, "The validated object is null");
    }

    /**
     * <p>验证指定的参数不为null，否则抛出异常</p>
     * <pre>
     *     Validate.notNull(obj, "这个对象必须不为null");
     * </pre>
     * <p>当传入的参数为null时，则抛出IllegalArgumentException异常并给出传入的异常消息。</p>
     *
     * @param object  待校验的对象
     * @param message 当发生异常时给出的提示消息
     * @throws IllegalArgumentException 如果对象为null
     */
    public static void notNull(Object object, String message) {
        // 直接判断object是否为null
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    // notEmpty方法，验证数组不为空
    //---------------------------------------------------------------------------------

    /**
     * <p>验证指定的参数数组不为null，也不是长度为零（数组中没有元素），否则抛出带有指定消息的异常。</p>
     * <pre>
     *     Validate.notEmpty(nums, "数组必须不能为空");
     * </pre>
     *
     * @param array   待检查验证的数组
     * @param message 当发生异常时给出的提示消息
     * @throws IllegalArgumentException 如果数组为空
     */
    public static void notEmpty(Object[] array, String message) {
        // 需要同时满足数组不为null和数组中的元素个数至少大于1两个条件，当有任何一个不满足时则抛出异常
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>验证指定的参数数组不为null，也不是长度为零（数组中没有元素），否则抛出带有指定消息的异常。</p>
     * <pre>
     *     Validate.notEmpty(nums);
     * </pre>
     * <p>当发生异常时抛出默认的提示消息："验证的数组为空"</p>
     *
     * @param array 待检查验证的数组
     * @throws IllegalArgumentException 如果数组为空
     */
    public static void notEmpty(Object[] array) {
        notEmpty(array, "The validated array is empty");
    }

    // notEmpty方法，验证集合不为空
    //---------------------------------------------------------------------------------

    /**
     * <p>验证指定的Collection集合既不能为null，又不能为空集合（集合中元素为零个），否则抛出带有指定消息的异常。</p>
     * <p>注意，不能校验Map集合</p>
     * <pre>
     *     Validate.notEmpty(list, "这个集合必须不能为空");
     * </pre>
     *
     * @param collection 待校验的Collection集合
     * @param message    当发生异常时，给出的提示消息
     * @throws IllegalArgumentException 如果集合为空
     */
    public static void notEmpty(Collection collection, String message) {
        // 判断给定的集合是否为null和集合中的元素个数是否为零
        if (collection == null || collection.size() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>验证指定的Collection集合既不能为null，又不能为空集合（集合中元素为零个），否则抛出带有指定消息的异常。</p>
     * <p>注意，不能校验Map集合</p>
     * <pre>
     *     Validate.notEmpty(list);
     * </pre>
     * <p>当发生异常时抛出默认的提示消息："验证的集合为空"</p>
     *
     * @param collection 待校验的Collection集合
     * @throws IllegalArgumentException 如果集合为空
     */
    public static void notEmpty(Collection collection) {
        notEmpty(collection, "The validated collection is empty");
    }

    // notEmpty方法，校验Map集合不为空
    //---------------------------------------------------------------------------------

    /**
     * <p>验证指定的Map集合是否为空，要求既不能是null，又不能元素个数为零个，否则抛出带有指定消息的异常。</p>
     * <pre>
     *     Validate.notEmpty(map, "这个Map集合必须不能为空");
     * </pre>
     *
     * @param map     待校验的Map集合
     * @param message 当发生异常时给出的指定提示消息
     * @throws IllegalArgumentException 如果Map集合为空
     */
    public static void notEmpty(Map map, String message) {
        if (map == null || map.size() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>验证指定的Map集合是否为空，要求既不能是null，又不能元素个数为零个，否则抛出带有指定消息的异常。</p>
     * <pre>
     *     Validate.notEmpty(map);
     * </pre>
     * <p>当发生异常时抛出默认消息："验证的Map集合为空"。</p>
     *
     * @param map 待校验的Map集合
     * @throws IllegalArgumentException 如果Map集合为空
     */
    public static void notEmpty(Map map) {
        notEmpty(map, "The validated map is empty");
    }

    // notEmpty方法，验证字符串不能为空
    //---------------------------------------------------------------------------------

    /**
     * <p>验证指定的参数字符串不能为空，要求既不能为null，也不能长度为零（即空字符串""），否则抛出带有指定消息的异常。</p>
     * <pre>
     *     Validate.notEmpty(str, "这个字符串必须不能为空");
     * </pre>
     *
     * @param string  指定的参数字符串
     * @param message 当发生异常时，会给出的指定提示信息
     * @throws IllegalArgumentException 如果字符串为null或者字符串为""
     */
    public static void notEmpty(String string, String message) {
        // 当传入的字符串参数string为null或者字符串中的字符个数为0，则抛出参数错误异常，并给出指定消息提示
        if (string == null || string.length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>验证指定的参数字符串不能为空，要求既不能为null，也不能长度为零（即空字符串""），否则抛出带有指定消息的异常。</p>
     * <pre>
     *     Validate.notEmpty(str);
     * </pre>
     * <p>当发生异常时，抛出异常，给出默认的提示消息："验证的字符串为空"</p>
     *
     * @param string 指定的参数字符串
     * @throws IllegalArgumentException 如果字符串为null或者字符串为""
     */
    public static void notEmpty(String string) {
        notEmpty(string, "The validated string is empty");
    }

    // notNullElements方法，判断数组中所有元素不为null
    //---------------------------------------------------------------------------------

    /**
     * <p>验证指定的参数数组既不能为null，并且也不包含任何为null的元素，否则抛出带有指定消息的异常。</p>
     * <pre>
     *     Validate.noNullElements(arr, "这个数组包含null元素");
     * </pre>
     *
     * @param array   待验证的数组
     * @param message 如果集合具有null元素则抛出指定的消息
     * @throws IllegalArgumentException 如果数组为null或者数组中有元素为null
     */
    public static void noNullElements(Object[] array, String message) {
        // 先校验数组是否为null
        Validate.notNull(array);
        // 再循环遍历数组中的所有元素
        for (int i = 0; i < array.length; i++) {
            // 验证每个数组元素是否为null，如果为null则抛出带有指定消息的异常
            if (array[i] == null) {
                throw new IllegalArgumentException(message);
            }
        }
    }

    /**
     * <p>验证指定的参数数组既不能为null，并且也不包含任何为null的元素，否则抛出带有指定消息的异常。</p>
     * <pre>
     *     Validate.noNullElements(arr);
     * </pre>
     * <p>如果数组具有null元素，则抛出的异常的指定消息是："验证的数组在索引处包含空元素："，后面跟着索引。</p>
     *
     * @param array 待验证的数组
     * @throws IllegalArgumentException 如果数组为null或者数组中有元素为null
     */
    public static void noNullElements(Object[] array) {
        Validate.notNull(array);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                throw new IllegalArgumentException("The validated array contains null element at index: " + i);
            }
        }
    }

    // notNullElements方法，判断集合中所有元素不能为空
    //---------------------------------------------------------------------------------

    /**
     * <p>验证指定的参数集合既不能为null，并且集合中的任何元素不能为null，否则抛出带有指定消息的异常。</p>
     * <pre>
     *     Validate.noNullElements(list, "这个集合包含null元素");
     * </pre>
     *
     * @param collection 待检查的Collection集合
     * @param message    如果集合发生异常，则抛出指定的异常提示消息
     * @throws IllegalArgumentException 如果集合为null，或者集合中有元素为null
     */
    public static void noNullElements(Collection collection, String message) {
        // 先判断集合不为null
        Validate.notNull(collection);
        // 再循环判断集合中的所有元素不能为null，注意下面的代码使用迭代器，并且使用的是for循环，通常我们遍历迭代器使用的是while循环
        for (Iterator it = collection.iterator(); it.hasNext(); ) {
            // 如果集合中存在元素为null，则抛出参数异常并给出指定的提示信息
            if (it.next() == null) {
                throw new IllegalArgumentException(message);
            }
        }
    }

    /**
     * <p>验证指定的参数集合既不能为null，并且集合中的任何元素不能为null，否则抛出带有指定消息的异常。</p>
     * <pre>
     *     Validate.noNullElements(list);
     * </pre>
     * <p>如果集合为null则给出集合异常提示；如果集合中具有null元素，则异常消息中的是："验证的集合在指定索引处包含null元素："，后面跟着索引。</p>
     *
     * @param collection 待检查的Collection集合
     * @throws IllegalArgumentException 如果集合为null，或者集合中有元素为null
     */
    public static void noNullElements(Collection collection) {
        // 先校验集合是否为null
        Validate.notNull(collection);
        // 再校验集合中是否有null元素
        int i = 0;// 索引，指向集合中每个元素的索引
        for (Iterator it = collection.iterator(); it.hasNext(); i++) {
            // 判断集合中是否有元素为null，如果有则抛出异常
            if (it.next() == null) {
                throw new IllegalArgumentException("The validated collection contains null element at index: " + i);
            }
        }
    }

    /**
     * <p>验证参数，如果参数集合为null，或者具有不是clazz类型或其子类型的元素，则抛出IllegalArgumentException异常并给出提示信息</p>
     * <pre>
     *     Validate.allElementsOfType(collection, String.class);
     * </pre>
     *
     * @param collection 待验证的集合
     * @param clazz      Class类，预计集合中的元素不为空
     * @param message    如果集合中具有非clazz类型的元素，则抛出异常时提示该异常信息
     * @throws IllegalArgumentException 如果collection为null，或者clazz为null，或者集合中有非clazz类型的元素
     */
    public static void allElementsOfType(Collection collection, Class clazz, String message) {
        // 校验集合不为null
        Validate.notNull(collection);
        // 校验clazz不为null
        Validate.notNull(clazz);
        // 校验集合中的所有元素都应该是clazz类型或其子类型的，否则抛出带有指定消息的异常
        for (Iterator it = collection.iterator(); it.hasNext(); ) {
            // clazz.isInstance(Object obj)方法判断clazz和obj是否是同一Class类型的，返回一个布尔值
            if (clazz.isInstance(it.next()) == false) {
                throw new IllegalArgumentException(message);
            }
        }
    }

    /**
     * <p>验证参数，如果参数集合为null，或者具有不是clazz类型或其子类型的元素，则抛出IllegalArgumentException异常并给出提示信息</p>
     * <pre>
     *     Validate.allElementsOfType(collection, String.class);
     * </pre>
     * <p>当发生异常时，抛出带有指定消息的异常："验证的集合在索引处包含一个不是clazz类型的元素："，后面跟着索引。</p>
     *
     * @param collection 待验证的集合
     * @param clazz      Class类，预计集合中的元素不为空
     * @throws IllegalArgumentException 如果collection为null，或者clazz为null，或者集合中有非clazz类型的元素
     */
    public static void allElementsOfType(Collection collection, Class clazz) {
        // 先校验集合不为null
        Validate.notNull(collection);
        // 接着校验clazz不为null
        Validate.notNull(clazz);
        // 再循环遍历集合中所有元素，验证每个元素都应该是clazz类型或其子类型
        int i = 0;
        for (Iterator it = collection.iterator(); it.hasNext(); i++) {
            if (clazz.isInstance(it.next()) == false) {
                throw new IllegalArgumentException("The validated collection contains an element not of type "
                        + clazz.getName() + " at index: " + i);
            }
        }
    }

}
