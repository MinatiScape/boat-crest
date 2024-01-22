package com.crrepa.s;
/* loaded from: classes9.dex */
public abstract class c extends com.crrepa.s.a {
    public String b;
    public String c;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ long h;
        public final /* synthetic */ long i;

        public a(long j, long j2) {
            this.h = j;
            this.i = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar = c.this;
            long j = this.i;
            cVar.a((((float) this.h) * 100.0f) / ((float) j), j);
        }
    }

    public c() {
    }

    public c(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    public void a(String str) {
        this.c = str;
    }

    public void b(String str) {
        this.b = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0070 A[Catch: IOException -> 0x0073, TRY_LEAVE, TryCatch #5 {IOException -> 0x0073, blocks: (B:30:0x006b, B:32:0x0070), top: B:58:0x006b }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0086 A[Catch: IOException -> 0x0089, TRY_LEAVE, TryCatch #0 {IOException -> 0x0089, blocks: (B:40:0x0081, B:42:0x0086), top: B:52:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    @Override // com.crrepa.s.a
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.io.File b(com.crrepa.r.c r19) {
        /*
            r18 = this;
            r7 = r18
            r0 = r19
            r1 = 8192(0x2000, float:1.14794E-41)
            byte[] r8 = new byte[r1]
            java.io.InputStream r10 = r0.f7835a     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L65
            long r11 = r0.d     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5e
            r0 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5e
            java.lang.String r3 = r7.b     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5e
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5e
            boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5e
            if (r3 != 0) goto L1e
            r2.mkdirs()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5e
        L1e:
            java.io.File r13 = new java.io.File     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5e
            java.lang.String r3 = r7.c     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5e
            r13.<init>(r2, r3)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5e
            java.io.FileOutputStream r14 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5e
            r14.<init>(r13)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5e
        L2a:
            int r2 = r10.read(r8)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r3 = -1
            if (r2 == r3) goto L4a
            long r3 = (long) r2     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            long r15 = r0 + r3
            r0 = 0
            r14.write(r8, r0, r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            android.os.Handler r0 = com.crrepa.s.a.f7838a     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            com.crrepa.s.c$a r5 = new com.crrepa.s.c$a     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r1 = r5
            r2 = r18
            r3 = r15
            r9 = r5
            r5 = r11
            r1.<init>(r3, r5)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r0.post(r9)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r0 = r15
            goto L2a
        L4a:
            r14.flush()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r14.close()     // Catch: java.io.IOException -> L53
            r10.close()     // Catch: java.io.IOException -> L53
        L53:
            r14.close()     // Catch: java.io.IOException -> L56
        L56:
            return r13
        L57:
            r0 = move-exception
            goto L81
        L59:
            r0 = move-exception
            goto L68
        L5b:
            r0 = move-exception
            r9 = r10
            goto L62
        L5e:
            r0 = move-exception
            goto L67
        L60:
            r0 = move-exception
            r9 = 0
        L62:
            r17 = 0
            goto L7e
        L65:
            r0 = move-exception
            r10 = 0
        L67:
            r14 = 0
        L68:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7a
            r14.close()     // Catch: java.io.IOException -> L73
            if (r10 == 0) goto L73
            r10.close()     // Catch: java.io.IOException -> L73
        L73:
            if (r14 == 0) goto L78
            r14.close()     // Catch: java.io.IOException -> L78
        L78:
            r1 = 0
            return r1
        L7a:
            r0 = move-exception
            r9 = r10
            r17 = r14
        L7e:
            r10 = r9
            r14 = r17
        L81:
            r14.close()     // Catch: java.io.IOException -> L89
            if (r10 == 0) goto L89
            r10.close()     // Catch: java.io.IOException -> L89
        L89:
            if (r14 == 0) goto L8e
            r14.close()     // Catch: java.io.IOException -> L8e
        L8e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crrepa.s.c.b(com.crrepa.r.c):java.io.File");
    }
}
