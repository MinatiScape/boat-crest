package com.coveiot.sdk.ble.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes9.dex */
public enum AlignmentEnum {
    CENTER,
    CENTER_VERTICAL,
    CENTER_HORIZONTAL,
    LEFT,
    RIGHT,
    TOP,
    BOTTOM;

    public static Integer log2(int i) {
        if (i == 0 || ((i - 1) & i) != 0) {
            return null;
        }
        int i2 = 0;
        while ((i & 1) == 0) {
            i >>= 1;
            i2++;
        }
        return Integer.valueOf(i2);
    }

    public static List<AlignmentEnum> valuesOf(int i) throws Exception {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = 1 << i2;
            if ((i & i3) != 0) {
                arrayList.add(valueOf(i3));
            }
        }
        return arrayList;
    }

    public int getValue() {
        return 1 << ordinal();
    }

    public static AlignmentEnum valueOf(int i) throws Exception {
        Integer log2 = log2(i);
        if (log2 != null && log2.intValue() >= 0 && log2.intValue() < values().length) {
            return values()[log2.intValue()];
        }
        throw new Exception(String.format(Locale.ENGLISH, "Invalid activity data type for value %d", Integer.valueOf(i)));
    }
}
