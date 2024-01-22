package com.coveiot.mki;

import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes9.dex */
public final class p {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f7292a = 0;

    static {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
    }

    public static void a(String str, Object... objArr) {
        String.format("E/ %s", String.format(str, objArr));
    }

    public static void b(String str, Object... objArr) {
        String.format("I/ %s", String.format(str, objArr));
    }
}
