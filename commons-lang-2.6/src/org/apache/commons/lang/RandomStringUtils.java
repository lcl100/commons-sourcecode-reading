package org.apache.commons.lang;

import java.util.Random;

/**
 * <p>随机字符串操作。</p>
 * <p>当前私有的高代理字符（<em>private high surrogate</em>）被忽略。
 * 这些是介于值56192（db80）和56319（dbff）之间的Unicode字符，因为我们
 * 不知道如何处理它们。正确处理高和低代理，也就是说，如果随机选择高代理，
 * 从55296（d800）到56191（db7f）然后是低代理。如果选择了低代理，从
 * 56320（dc00）到57343（dfff），则将其放置在随机选择的高代理之后。</p>
 * <p>线程安全</p>
 *
 * @author lcl100
 * @date 2021-07-29 11:27
 */
public class RandomStringUtils {

    /**
     * <p>随机方法使用的随机对象。必须不是不同的Random对象，因为不同的Random对象在有限的范围内同一毫秒内返回相同的值的概率更大，而共用同一个Random实例对象返回相同值的概率要小一些。</p>
     * <p>思考：为什么呢？</p>
     */
    private static final Random RANDOM = new Random();

    /**
     * <p><code>RandomStringUtils</code>实例对象不应该被创建，相反，该类应该直接调用静态方法，如<code>RandomStringUtils.random(5);</code></p>
     */
    public RandomStringUtils() {
        super();
    }

    // Random
    //-----------------------------------------------------------------------

    /**
     * <p>创建一个随机字符串，其长度为指定的字符数，即count个字符。</p>
     * <p>字符将从所有的字符集中选择。</p>
     * <p>该方法生成的随机字符串是从所有字符集中选择，但又不包括字母和数字，所以多是中文乱码字符串。</p>
     *
     * @param count 要创建的随机字符串的长度
     * @return 指定长度的随机字符串
     */
    public static String random(int count) {
        // 调用random()方法，letters参数表示生成的字符串是否只包含字母字符，这里默认传入false
        // numbers参数表示生成的字符串是否只包含数字字母，这里默认传入false，表示不包含
        return random(count, false, false);
    }

    /**
     * <p>创建一个随机字符串，其长度为指定的count。</p>
     * <p>将从ASCII值介于32和127（不包含127）之间的字符集合中选择字符。</p>
     * <p>注意，ASCII值在[32,127)之间的所有字符都属于可打印字符，具体参考ASCII码表。</p>
     *
     * @param count 要创建的随机字符串的长度
     * @return 随机字符串
     */
    public static String randomAscii(int count) {
        // 注意，这里传入的start和end参数分别是32和127，同样将letters和numbers置为false表示不只是字母和数字字符
        return random(count, 32, 127, false, false);
    }

    /**
     * <p>创建一个随机字符串，其长度为指定的count。随机字符串中的字符将从字母字符集合中选择。</p>
     *
     * @param count 要创建的随机字符串的长度
     * @return 生成的随机随机字符串
     */
    public static String randomAlphabetic(int count) {
        // 将letters置为true表示生成的随机字符串中只有字母字符；将numbers置为false表示随机字符串中不包含任何数字字符
        return random(count, true, false);
    }

    /**
     * <p>创建一个随机字符串，其长度为指定的count。随机字符串中的字符将从字母字符集合和数字字符集合中选择。</p>
     *
     * @param count 要创建的随机字符串的长度
     * @return 生成的随机字符串
     */
    public static String randomAlphanumeric(int count) {
        // 将letters和numbers都置为true，表示生成的字符串中的字符只在数字和字母中选择
        return random(count, true, true);
    }

    /**
     * 创建一个随机字符串，其长度为指定的count。随机字符串中的字符只从数字字符集合中选择字符。
     *
     * @param count 要创建的随机字符串的长度
     * @return 随机字符串
     */
    public static String randomNumeric(int count) {
        // 将numbers置为true表示只生成数字的随机字符串
        return random(count, false, true);
    }

    /**
     * <p>创建一个随机字符串，其长度为指定的字符数。</p>
     * <p>字符将从参数指示的字母和数字字符集中选择。所以生成的字符串是数字和字母混合的字符串，不包括其他字符。</p>
     *
     * @param count   创建的随机字符串的长度
     * @param letters 如果为true，生成的字符串将包含字母字符
     * @param numbers 如果为true，生成的字符串将包含数字字符
     * @return 随机字符串
     */
    public static String random(int count, boolean letters, boolean numbers) {
        return random(count, 0, 0, letters, numbers);
    }

    /**
     * <p>创建一个随机字符串，其长度为指定的字符数。</p>
     *
     * @param count   要创建的随机字符串的长度
     * @param start   字符集中开始的位置
     * @param end     在字符集中结束之前的位置
     * @param letters 如果为true，则生成的字符串将包含字母字符
     * @param numbers 如果为true，则生成的字符串将包含数字字符
     * @return 随机字符串
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers) {
        return random(count, start, end, letters, numbers, null, RANDOM);
    }

    /**
     * <p>使用提供的随机源根据各种选项创建随机字符串，使用默认的随机源（即常量RANDOM）</p>
     * <p>该方法与{@link #random(int, int, int, boolean, boolean, char[], Random)}具有完全相同的语义，但它使用内部静态{@link Random}实例对象，而不是使用外部提供的随机源。</p>
     *
     * @param count   要创建的随机字符串的长度
     * @param start   字符数组中开始的位置
     * @param end     在字符数组中结束之前的位置
     * @param letters 如果为true，则生成的字符串将只包含字母字符
     * @param numbers 如果为true，则生成的字符串将只包含数字字符，如果letters和numbers都为true表示只包含字母和数字字符
     * @param chars   从中选择随机字符的字符数组。如果为null，则它将使用所有字符的集合。例如chars为['a','b','c']，那么生成的随机字符串中的所有字符都是在该chars数组中随机选择的，即字符串里面的字符一定都在该字符串数组中找得到
     * @return 生成的随机字符串
     * @throws ArrayIndexOutOfBoundsException 如果set数组中没有(end - start) + 1字符
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars) {
        // 只有random参数是使用内部静态常量RANDOM
        return random(count, start, end, letters, numbers, chars, RANDOM);
    }

    /**
     * <p>使用提供的随机源根据各种选项创建随机字符串。</p>
     * <p>如果start和end都是0，那么start和end分别设置为空格字符' '和字母字符'z'，
     * 将使用ASCII可打印字符，除非字母和数字都为false，在这种情况下，start和end分别设置为0和且Integer.MAX_VALUE</p>
     * <p>如果set不为null，则选择start和end之间的字符。</p>
     * <p>此方法接受用户提供的{@link Random}实例用作{@link Random}源。通过使用固定种子播种单个{@link Random}实例
     * 并且将其用于每次调用，可以重复且可预测地生成相同的随机字符串序列。</p>
     *
     * @param count   要创建的随机字符串的长度
     * @param start   字符数组中开始的位置
     * @param end     在字符数组中结束之前的位置
     * @param letters 如果为true，则生成的字符串将只包含字母字符
     * @param numbers 如果为true，则生成的字符串将只包含数字字符，如果letters和numbers都为true表示只包含字母和数字字符
     * @param chars   从中选择随机字符的字符数组。如果为null，则它将使用所有字符的集合。例如chars为['a','b','c']，那么生成的随机字符串中的所有字符都是在该chars数组中随机选择的，即字符串里面的字符一定都在该字符串数组中找得到
     * @param random  随机性的来源
     * @return 生成的随机字符串
     * @throws ArrayIndexOutOfBoundsException 如果set数组中没有(end - start) + 1字符
     * @throws IllegalArgumentException       如果count<0
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers,
                                char[] chars, Random random) {
        // 参数校验，count可以为0则返回空字符串；count可以大于0表示生成指定长度的随机字符串；count不能小于0，因为你不可能生成一个负长度的字符串所以要抛出异常
        if (count == 0) {
            return "";
        } else if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        // 参数校验，校验start和end
        if ((start == 0) && (end == 0)) {
            end = 'z' + 1;
            start = ' ';
            // 如果letters和numbers都是false，表示可以是任意字符，那么在这种情况下，将start设置为0，end设置为Integer.MAX_VALUE
            if (!letters && !numbers) {
                start = 0;
                end = Integer.MAX_VALUE;
            }
        }

        // 创建一个指定长度的字符数组，用来存放生成的随机字符串
        char[] buffer = new char[count];
        int gap = end - start;// 91

        // 循环遍历长度，用来生成随机字符串中的每个字符
        while (count-- != 0) {
            // 局部变量，用来存放生成的随机字符
            char ch;
            // 生成随机字符，如果给定的字符数组chars为null，则将生成的随机数字转换成字符
            // 如果给定的字符数组chars不为null，那么将从chars数组中随机获取一个字符
            if (chars == null) {
                ch = (char) (random.nextInt(gap) + start);
            } else {
                ch = chars[random.nextInt(gap) + start];
            }
            /* ------------------------为什么呢？代理字符需要查询更多的资料才能回答这个问题，提供一个简单参考资料：https://www.cnblogs.com/benbenalin/p/7152570.html------------------------ */
            if ((letters && Character.isLetter(ch))
                    || (numbers && Character.isDigit(ch))
                    || (!letters && !numbers)) {
                if (ch >= 56320 && ch <= 57343) {
                    if (count == 0) {
                        count++;
                    } else {
                        // low surrogate, insert high surrogate after putting it in
                        buffer[count] = ch;
                        count--;
                        buffer[count] = (char) (55296 + random.nextInt(128));
                    }
                } else if (ch >= 55296 && ch <= 56191) {
                    if (count == 0) {
                        count++;
                    } else {
                        // high surrogate, insert low surrogate before putting it in
                        buffer[count] = (char) (56320 + random.nextInt(128));
                        count--;
                        buffer[count] = ch;
                    }
                } else if (ch >= 56192 && ch <= 56319) {
                    // private high surrogate, no effing clue, so skip it
                    count++;
                    /* ------------------------为什么呢？------------------------ */
                } else {
                    // 将产生的随机字符保存到字符数组中
                    buffer[count] = ch;
                }
            } else {
                count++;
            }
        }
        // 然后将字符数组转换成字符串返回
        return new String(buffer);
    }

    /**
     * <p>创建一个随机字符串，其长度为指定的count。随机字符串中的字符将从指定的字符集合中选择字符。</p>
     *
     * @param count 要创建的随机字符串的长度
     * @param chars 包含要使用的字符集合的字符串，可能为空，即随机字符串中的字符是从chars字符串中选择字符
     * @return 生成的随机字符串
     * @throws IllegalArgumentException 如果count<0
     */
    public static String random(int count, String chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        // 即将chars字符串转换成字符数组，然后调用下面的重载方法
        return random(count, chars.toCharArray());
    }

    /**
     * <p>创建一个随机字符串，其长度为指定的count。随机字符串中的字符将从指定的字符数组中选择字符。</p>
     *
     * @param count 要创建的随机字符串的长度
     * @param chars 指定的字符数组，生成的随机字符串中的字符都是在该字符数组中选择的，可能为空
     * @return 生成的随机字符串
     * @throws IllegalArgumentException 如果count<0
     */
    public static String random(int count, char[] chars) {
        // 参数校验，同时注意end参数和chars参数的不同取值
        if (chars == null) {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        // 将start和end设置为[0, chars.length)表示随机字符串中的字符将只从chars字符数组中选择字符
        return random(count, 0, chars.length, false, false, chars, RANDOM);
    }

}
