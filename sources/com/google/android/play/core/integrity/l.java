package com.google.android.play.core.integrity;

import android.content.Context;
/* loaded from: classes10.dex */
final class l {

    /* renamed from: a  reason: collision with root package name */
    private static j f10456a;

    public static synchronized j a(Context context) {
        j jVar;
        synchronized (l.class) {
            if (f10456a == null) {
                h hVar = new h(null);
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
                hVar.a(context);
                f10456a = hVar.b();
            }
            jVar = f10456a;
        }
        return jVar;
    }
}
