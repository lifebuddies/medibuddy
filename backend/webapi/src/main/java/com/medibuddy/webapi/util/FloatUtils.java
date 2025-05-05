package com.medibuddy.webapi.util;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class FloatUtils {

    public static float[] toFloatArray(List<Float> list) {
        float[] result = new float[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static float[] toFloatArray(Stream<Float> stream, int count) {
        float[] result = new float[count];
        Iterator<Float> iterator = stream.iterator();

        for (int i = 0; i < count; i++) {
            result[i] = iterator.next();
        }

        return result;
    }

}
