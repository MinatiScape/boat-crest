package com.google.iot.cbor;

import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemViewTypeComposer;
import kotlin.UShort;
/* loaded from: classes10.dex */
public final class q {

    /* renamed from: a  reason: collision with root package name */
    public static final float f11533a = Float.intBitsToFloat(1056964608);

    public static short a(float f) {
        int i;
        int i2;
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        int i3 = floatToRawIntBits >>> 31;
        int i4 = (floatToRawIntBits >>> 23) & 255;
        int i5 = floatToRawIntBits & ItemViewTypeComposer.MAX_WRAPPED_VIEW_TYPE;
        if (i4 == 255) {
            i = i5 != 0 ? 512 : 0;
            r3 = 31;
        } else {
            int i6 = (i4 - 127) + 15;
            if (i6 >= 31) {
                r3 = 49;
                i = 0;
            } else if (i6 > 0) {
                int i7 = i5 >> 13;
                if ((i5 & 4096) != 0) {
                    i2 = (((i6 << 10) | i7) + 1) | (i3 << 15);
                    return (short) i2;
                }
                i = i7;
                r3 = i6;
            } else if (i6 < -10) {
                i = 0;
            } else {
                int i8 = (i5 | 8388608) >> (1 - i6);
                if ((i8 & 4096) != 0) {
                    i8 += 8192;
                }
                i = i8 >> 13;
            }
        }
        i2 = i | (i3 << 15) | (r3 << 10);
        return (short) i2;
    }

    public static float b(short s) {
        int i;
        int i2 = s & UShort.MAX_VALUE;
        int i3 = 32768 & i2;
        int i4 = (i2 >>> 10) & 31;
        int i5 = i2 & 1023;
        int i6 = 0;
        if (i4 != 0) {
            int i7 = i5 << 13;
            i6 = i4 == 31 ? 255 : (i4 - 15) + 127;
            i = i7;
        } else if (i5 != 0) {
            float intBitsToFloat = Float.intBitsToFloat(i5 + 1056964608) - f11533a;
            return i3 == 0 ? intBitsToFloat : -intBitsToFloat;
        } else {
            i = 0;
        }
        return Float.intBitsToFloat(i | (i3 << 16) | (i6 << 23));
    }
}
