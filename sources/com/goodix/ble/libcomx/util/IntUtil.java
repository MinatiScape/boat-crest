package com.goodix.ble.libcomx.util;
/* loaded from: classes6.dex */
public class IntUtil {
    public static int compareUnsignedInt(int i, int i2) {
        int i3 = (((i & 4294967295L) - (i2 & 4294967295L)) > 0L ? 1 : (((i & 4294967295L) - (i2 & 4294967295L)) == 0L ? 0 : -1));
        if (i3 > 0) {
            return 1;
        }
        return i3 < 0 ? -1 : 0;
    }

    public static boolean memoryOverlap(int i, int i2, int i3, int i4) {
        long j = i3 & 4294967295L;
        long j2 = i & 4294967295L;
        return (((long) i2) & 4294967295L) + j2 > j && j2 < (((long) i4) & 4294967295L) + j;
    }

    public static boolean rangeOverlap(int i, int i2, int i3, int i4) {
        return valueInRange(i, i2, i3) || valueInRange(i, i2, i4);
    }

    public static boolean rangeOverlapUnsignedInt(int i, int i2, int i3, int i4) {
        return valueInRangeUnsignedInt(i, i2, i3) || valueInRangeUnsignedInt(i, i2, i4);
    }

    public static boolean valueInRange(int i, int i2, int i3) {
        return i3 >= i && i3 <= i2;
    }

    public static boolean valueInRangeUnsignedInt(int i, int i2, int i3) {
        long j = 4294967295L & i3;
        return j >= (((long) i) & 4294967295L) && j <= (((long) i2) & 4294967295L);
    }
}
