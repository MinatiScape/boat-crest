package com.coveiot.mki;

import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public final class t {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f7293a = Pattern.compile("^DfuTarg.*$", 2);

    public static List a(int i, byte b, byte b2, List list) {
        List list2 = list;
        byte[] bArr = new byte[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            bArr[i2] = ((Byte) list2.get(i2)).byteValue();
        }
        short a2 = a(bArr);
        ArrayList arrayList = new ArrayList();
        int i3 = i - 4;
        if (list.size() <= i3) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(Byte.valueOf(b));
            arrayList2.add(Byte.valueOf(b2));
            byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(list.size() + 4).array();
            arrayList2.add(Byte.valueOf(array[0]));
            arrayList2.add(Byte.valueOf(array[1]));
            arrayList2.addAll(list2);
            arrayList.add(arrayList2);
        } else {
            int size = list.size();
            int i4 = size + 8;
            int i5 = i4 / i3;
            if (i4 % i3 != 0) {
                i5++;
            }
            for (int i6 = 0; i6 < i5; i6++) {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(Byte.MAX_VALUE);
                arrayList3.add(Byte.valueOf((byte) a2));
                ByteBuffer allocate = ByteBuffer.allocate(4);
                ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                byte[] array2 = allocate.order(byteOrder).putInt(i6).array();
                arrayList3.add(Byte.valueOf(array2[0]));
                arrayList3.add(Byte.valueOf(array2[1]));
                int i7 = 12;
                if (i6 == 0) {
                    byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt(i5).array();
                    arrayList3.add(Byte.valueOf(array3[0]));
                    arrayList3.add(Byte.valueOf(array3[1]));
                    byte[] array4 = ByteBuffer.allocate(2).order(byteOrder).putShort(a2).array();
                    arrayList3.add(Byte.valueOf(array4[0]));
                    arrayList3.add(Byte.valueOf(array4[1]));
                    arrayList3.add(Byte.valueOf(b));
                    arrayList3.add(Byte.valueOf(b2));
                    byte[] array5 = ByteBuffer.allocate(4).order(byteOrder).putInt(size + 12).array();
                    arrayList3.add(Byte.valueOf(array5[0]));
                    arrayList3.add(Byte.valueOf(array5[1]));
                } else {
                    i7 = 4;
                }
                int i8 = i - i7;
                if (list2.size() >= i8) {
                    arrayList3.addAll(list2.subList(0, i8));
                    list2 = list2.subList(i8, list2.size());
                } else {
                    arrayList3.addAll(list2.subList(0, list2.size()));
                }
                arrayList.add(arrayList3);
            }
        }
        return arrayList;
    }

    public static short a(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            int i2 = (((i << 8) | (i >>> 8)) & 65535) ^ (b & 255);
            int i3 = i2 ^ ((i2 & 255) >> 4);
            int i4 = i3 ^ ((i3 << 12) & 65535);
            i = i4 ^ (65535 & ((i4 & 255) << 5));
        }
        return (short) (i & 65535);
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return f7293a.matcher(str).matches();
    }
}
