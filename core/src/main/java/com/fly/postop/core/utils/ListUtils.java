package com.fly.postop.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *           .----.
 *        _.'__    `.
 *    .--(Q)(OK)---/$\
 *  .' @          /$$$\
 *  :         ,   $$$$$
 *   `-..__.-' _.-\$$/
 *         `;_:    `"'
 *       .'"""""`.
 *      /,  FLY  ,\
 *     //         \\
 *     `-._______.-'
 *     ___`. | .'___
 *    (______|______)
 * </pre>
 * 包    名 : com.fly.postop.code.utils
 * 作    者 : FLY
 * 创建时间 : 2018/9/16
 * 描述: List工具类
 */

public class ListUtils {

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }


    public static <E> ArrayList<E> newArrayList(E... elements) {

        int capacity = computeArrayListCapacity(elements.length);
        ArrayList<E> list = new ArrayList<E>(capacity);
        Collections.addAll(list, elements);
        return list;
    }

    public static boolean isNotEmpty(List list) {
        return (list != null && list.size() > 0);
    }

    public static boolean isEmpty(List list) {
        return (list == null || list.size() == 0);
    }

    public static int getLength(final List array) {
        if (array == null) {
            return 0;
        }
        return array.size();
    }

    private static int computeArrayListCapacity(int arraySize) {

        return saturatedCast(5L + arraySize + (arraySize / 10));
    }

    private static int saturatedCast(long value) {
        if (value > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (value < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }


}
