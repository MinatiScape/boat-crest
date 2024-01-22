package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.common.internal.GmsLogger;
/* loaded from: classes7.dex */
public final class zzsd {

    /* renamed from: a  reason: collision with root package name */
    public static final GmsLogger f8804a = new GmsLogger("MLKitImageUtils", "");
    public static zzsd b = new zzsd();

    /* JADX WARN: Removed duplicated region for block: B:25:0x005f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(android.content.ContentResolver r5, android.net.Uri r6) {
        /*
            java.lang.String r0 = r6.getScheme()
            java.lang.String r1 = "content"
            boolean r0 = r1.equals(r0)
            r1 = 0
            if (r0 != 0) goto L1a
            java.lang.String r0 = r6.getScheme()
            java.lang.String r2 = "file"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L1a
            return r1
        L1a:
            r0 = 0
            java.io.InputStream r5 = r5.openInputStream(r6)     // Catch: java.io.IOException -> L39
            androidx.exifinterface.media.ExifInterface r2 = new androidx.exifinterface.media.ExifInterface     // Catch: java.lang.Throwable -> L2d
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L2d
            if (r5 == 0) goto L5d
            r5.close()     // Catch: java.io.IOException -> L2a
            goto L5d
        L2a:
            r5 = move-exception
            r0 = r2
            goto L3a
        L2d:
            r2 = move-exception
            if (r5 == 0) goto L38
            r5.close()     // Catch: java.lang.Throwable -> L34
            goto L38
        L34:
            r5 = move-exception
            com.google.android.gms.internal.firebase_ml.zzne.zza(r2, r5)     // Catch: java.io.IOException -> L39
        L38:
            throw r2     // Catch: java.io.IOException -> L39
        L39:
            r5 = move-exception
        L3a:
            com.google.android.gms.common.internal.GmsLogger r2 = com.google.android.gms.internal.firebase_ml.zzsd.f8804a
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r3 = r6.length()
            int r3 = r3 + 48
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "failed to open file to read rotation meta data: "
            r4.append(r3)
            r4.append(r6)
            java.lang.String r6 = r4.toString()
            java.lang.String r3 = "MLKitImageUtils"
            r2.e(r3, r6, r5)
            r2 = r0
        L5d:
            if (r2 != 0) goto L60
            return r1
        L60:
            r5 = 1
            java.lang.String r6 = "Orientation"
            int r5 = r2.getAttributeInt(r6, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzsd.a(android.content.ContentResolver, android.net.Uri):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004f A[Catch: FileNotFoundException -> 0x005e, TryCatch #0 {FileNotFoundException -> 0x005e, blocks: (B:2:0x0000, B:3:0x001d, B:15:0x004f, B:17:0x0059, B:6:0x0023, B:7:0x0027, B:8:0x002e, B:9:0x0032, B:10:0x0039, B:11:0x003d, B:13:0x0044), top: B:23:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap zza(android.content.ContentResolver r8, android.net.Uri r9) throws java.io.IOException {
        /*
            android.graphics.Bitmap r7 = android.provider.MediaStore.Images.Media.getBitmap(r8, r9)     // Catch: java.io.FileNotFoundException -> L5e
            int r8 = a(r8, r9)     // Catch: java.io.FileNotFoundException -> L5e
            android.graphics.Matrix r0 = new android.graphics.Matrix     // Catch: java.io.FileNotFoundException -> L5e
            r0.<init>()     // Catch: java.io.FileNotFoundException -> L5e
            int r3 = r7.getWidth()     // Catch: java.io.FileNotFoundException -> L5e
            int r4 = r7.getHeight()     // Catch: java.io.FileNotFoundException -> L5e
            r1 = -1028390912(0xffffffffc2b40000, float:-90.0)
            r2 = 1119092736(0x42b40000, float:90.0)
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            r6 = 1065353216(0x3f800000, float:1.0)
            switch(r8) {
                case 2: goto L44;
                case 3: goto L3d;
                case 4: goto L39;
                case 5: goto L32;
                case 6: goto L2e;
                case 7: goto L27;
                case 8: goto L23;
                default: goto L20;
            }     // Catch: java.io.FileNotFoundException -> L5e
        L20:
            r8 = 0
        L21:
            r5 = r8
            goto L4d
        L23:
            r0.postRotate(r1)     // Catch: java.io.FileNotFoundException -> L5e
            goto L42
        L27:
            r0.postRotate(r1)     // Catch: java.io.FileNotFoundException -> L5e
            r0.postScale(r5, r6)     // Catch: java.io.FileNotFoundException -> L5e
            goto L42
        L2e:
            r0.postRotate(r2)     // Catch: java.io.FileNotFoundException -> L5e
            goto L42
        L32:
            r0.postRotate(r2)     // Catch: java.io.FileNotFoundException -> L5e
            r0.postScale(r5, r6)     // Catch: java.io.FileNotFoundException -> L5e
            goto L42
        L39:
            r0.postScale(r6, r5)     // Catch: java.io.FileNotFoundException -> L5e
            goto L42
        L3d:
            r8 = 1127481344(0x43340000, float:180.0)
            r0.postRotate(r8)     // Catch: java.io.FileNotFoundException -> L5e
        L42:
            r5 = r0
            goto L4d
        L44:
            android.graphics.Matrix r8 = new android.graphics.Matrix     // Catch: java.io.FileNotFoundException -> L5e
            r8.<init>()     // Catch: java.io.FileNotFoundException -> L5e
            r8.postScale(r5, r6)     // Catch: java.io.FileNotFoundException -> L5e
            goto L21
        L4d:
            if (r5 == 0) goto L5d
            r1 = 0
            r2 = 0
            r6 = 1
            r0 = r7
            android.graphics.Bitmap r8 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r3, r4, r5, r6)     // Catch: java.io.FileNotFoundException -> L5e
            if (r7 == r8) goto L5d
            r7.recycle()     // Catch: java.io.FileNotFoundException -> L5e
            r7 = r8
        L5d:
            return r7
        L5e:
            r8 = move-exception
            com.google.android.gms.common.internal.GmsLogger r0 = com.google.android.gms.internal.firebase_ml.zzsd.f8804a
            java.lang.String r9 = java.lang.String.valueOf(r9)
            int r1 = r9.length()
            int r1 = r1 + 21
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "Could not open file: "
            r2.append(r1)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            java.lang.String r1 = "MLKitImageUtils"
            r0.e(r1, r9, r8)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzsd.zza(android.content.ContentResolver, android.net.Uri):android.graphics.Bitmap");
    }

    public static zzsd zzqq() {
        return b;
    }
}
