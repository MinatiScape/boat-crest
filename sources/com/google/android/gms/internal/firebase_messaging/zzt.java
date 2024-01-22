package com.google.android.gms.internal.firebase_messaging;

import java.io.PrintStream;
/* loaded from: classes7.dex */
public final class zzt {

    /* renamed from: a  reason: collision with root package name */
    public static final l f8657a;

    static {
        l pVar;
        Integer num = null;
        try {
            try {
                num = (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Exception e) {
                System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
                e.printStackTrace(System.err);
            }
            if (num == null || num.intValue() < 19) {
                pVar = !Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ? new o() : new p();
            } else {
                pVar = new q();
            }
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = p.class.getName();
            StringBuilder sb = new StringBuilder(name.length() + 133);
            sb.append("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy ");
            sb.append(name);
            sb.append("will be used. The error is: ");
            printStream.println(sb.toString());
            th.printStackTrace(System.err);
            pVar = new p();
        }
        f8657a = pVar;
        if (num == null) {
            return;
        }
        num.intValue();
    }

    public static void zza(Throwable th, Throwable th2) {
        f8657a.a(th, th2);
    }
}
