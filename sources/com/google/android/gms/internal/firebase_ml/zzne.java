package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class zzne {

    /* renamed from: a  reason: collision with root package name */
    public static final h2 f8789a;

    /* loaded from: classes7.dex */
    public static final class a extends h2 {
        @Override // com.google.android.gms.internal.firebase_ml.h2
        public final void a(Throwable th, Throwable th2) {
        }

        @Override // com.google.android.gms.internal.firebase_ml.h2
        public final void b(Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    static {
        /*
            java.lang.Integer r0 = a()     // Catch: java.lang.Throwable -> L2c
            if (r0 == 0) goto L14
            int r1 = r0.intValue()     // Catch: java.lang.Throwable -> L2a
            r2 = 19
            if (r1 < r2) goto L14
            com.google.android.gms.internal.firebase_ml.k2 r1 = new com.google.android.gms.internal.firebase_ml.k2     // Catch: java.lang.Throwable -> L2a
            r1.<init>()     // Catch: java.lang.Throwable -> L2a
            goto L5f
        L14:
            java.lang.String r1 = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic"
            boolean r1 = java.lang.Boolean.getBoolean(r1)     // Catch: java.lang.Throwable -> L2a
            r1 = r1 ^ 1
            if (r1 == 0) goto L24
            com.google.android.gms.internal.firebase_ml.i2 r1 = new com.google.android.gms.internal.firebase_ml.i2     // Catch: java.lang.Throwable -> L2a
            r1.<init>()     // Catch: java.lang.Throwable -> L2a
            goto L5f
        L24:
            com.google.android.gms.internal.firebase_ml.zzne$a r1 = new com.google.android.gms.internal.firebase_ml.zzne$a     // Catch: java.lang.Throwable -> L2a
            r1.<init>()     // Catch: java.lang.Throwable -> L2a
            goto L5f
        L2a:
            r1 = move-exception
            goto L2e
        L2c:
            r1 = move-exception
            r0 = 0
        L2e:
            java.io.PrintStream r2 = java.lang.System.err
            java.lang.Class<com.google.android.gms.internal.firebase_ml.zzne$a> r3 = com.google.android.gms.internal.firebase_ml.zzne.a.class
            java.lang.String r3 = r3.getName()
            int r4 = r3.length()
            int r4 = r4 + 133
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy "
            r5.append(r4)
            r5.append(r3)
            java.lang.String r3 = "will be used. The error is: "
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r2.println(r3)
            java.io.PrintStream r2 = java.lang.System.err
            r1.printStackTrace(r2)
            com.google.android.gms.internal.firebase_ml.zzne$a r1 = new com.google.android.gms.internal.firebase_ml.zzne$a
            r1.<init>()
        L5f:
            com.google.android.gms.internal.firebase_ml.zzne.f8789a = r1
            if (r0 != 0) goto L64
            goto L67
        L64:
            r0.intValue()
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzne.<clinit>():void");
    }

    public static Integer a() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    public static void zza(Throwable th, Throwable th2) {
        f8789a.a(th, th2);
    }

    public static void zzb(Throwable th) {
        f8789a.b(th);
    }
}
