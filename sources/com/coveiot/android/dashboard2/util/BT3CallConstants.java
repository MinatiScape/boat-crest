package com.coveiot.android.dashboard2.util;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class BT3CallConstants {

    /* renamed from: a  reason: collision with root package name */
    public static final short f4249a = 0;
    @NotNull
    public static final BT3CallConstants INSTANCE = new BT3CallConstants();
    public static final short b = 1;
    public static final short c = 2;
    public static final short d = 3;

    public final short getANSWER() {
        return c;
    }

    public final short getHANG_UP() {
        return d;
    }

    public final short getINCOMING_CALL() {
        return f4249a;
    }

    public final short getOUTGOING_CALL() {
        return b;
    }
}
