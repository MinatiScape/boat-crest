package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGDial;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.HashMap;
/* loaded from: classes12.dex */
public abstract class u8 {

    /* renamed from: a  reason: collision with root package name */
    public static final HashMap f13829a;

    static {
        HashMap hashMap = new HashMap();
        f13829a = hashMap;
        hashMap.put(2, 6);
        hashMap.put(3, 5);
        hashMap.put(4, 4);
        hashMap.put(5, 3);
        hashMap.put(6, 2);
        hashMap.put(7, 1);
    }

    public static o8 a(int i, int i2, TGDial tGDial) {
        byte b;
        o8 o8Var = new o8();
        ByteBuffer b2 = o8Var.b(tGDial != null ? 14 : 5);
        b2.put((byte) i2);
        b2.putInt(i);
        if (tGDial != null) {
            if (tGDial.isPhotoDial()) {
                b = 3;
            } else {
                b = (byte) (tGDial.isDynamicDial() ? 2 : 1);
            }
            b2.put(b);
            b2.putInt((int) new File(tGDial.getFilePath()).length());
            b2.putInt(tGDial.getReplacedDialId());
        }
        return o8Var;
    }
}
