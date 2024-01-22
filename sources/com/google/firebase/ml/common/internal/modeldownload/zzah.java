package com.google.firebase.ml.common.internal.modeldownload;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import java.io.File;
import java.io.IOException;
/* loaded from: classes10.dex */
public final class zzah {
    public static final GmsLogger b = new GmsLogger("RemoteModelUtils", "");

    /* renamed from: a  reason: collision with root package name */
    public final zzp f11390a;

    public zzah(@NonNull zzp zzpVar) {
        Preconditions.checkNotNull(zzpVar);
        this.f11390a = zzpVar;
    }

    public static boolean a(@NonNull File file, @NonNull String str) {
        String str2;
        try {
            str2 = c(file);
        } catch (IOException unused) {
            GmsLogger gmsLogger = b;
            String valueOf = String.valueOf(file.getAbsolutePath());
            gmsLogger.d("RemoteModelUtils", valueOf.length() != 0 ? "Failed to close the tmp FileInputStream: ".concat(valueOf) : new String("Failed to close the tmp FileInputStream: "));
            str2 = "";
        }
        GmsLogger gmsLogger2 = b;
        String valueOf2 = String.valueOf(str2);
        gmsLogger2.d("RemoteModelUtils", valueOf2.length() != 0 ? "Calculated hash value is: ".concat(valueOf2) : new String("Calculated hash value is: "));
        return str.equals(str2);
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0079: MOVE  (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:35:0x0079 */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007c  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(java.io.File r8) throws java.io.IOException {
        /*
            java.lang.String r0 = "RemoteModelUtils"
            r1 = 0
            java.lang.String r2 = "SHA-256"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e java.io.FileNotFoundException -> L5c java.security.NoSuchAlgorithmException -> L6a
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e java.io.FileNotFoundException -> L5c java.security.NoSuchAlgorithmException -> L6a
            r3.<init>(r8)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e java.io.FileNotFoundException -> L5c java.security.NoSuchAlgorithmException -> L6a
            r8 = 1024(0x400, float:1.435E-42)
            byte[] r8 = new byte[r8]     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
        L12:
            int r4 = r3.read(r8)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
            r5 = -1
            r6 = 0
            if (r4 == r5) goto L1e
            r2.update(r8, r6, r4)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
            goto L12
        L1e:
            byte[] r8 = r2.digest()     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
            r2.<init>()     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
        L27:
            int r4 = r8.length     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
            if (r6 >= r4) goto L44
            r4 = r8[r6]     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
            r4 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
            int r5 = r4.length()     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
            r7 = 1
            if (r5 != r7) goto L3e
            r5 = 48
            r2.append(r5)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
        L3e:
            r2.append(r4)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
            int r6 = r6 + 1
            goto L27
        L44:
            java.lang.String r8 = r2.toString()     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L5d java.security.NoSuchAlgorithmException -> L6b java.lang.Throwable -> L78
            r3.close()
            return r8
        L4c:
            r8 = move-exception
            goto L7a
        L4e:
            r3 = r1
        L4f:
            com.google.android.gms.common.internal.GmsLogger r8 = com.google.firebase.ml.common.internal.modeldownload.zzah.b     // Catch: java.lang.Throwable -> L78
            java.lang.String r2 = "Cannot read the temp file for SHA-256 check"
            r8.e(r0, r2)     // Catch: java.lang.Throwable -> L78
            if (r3 == 0) goto L77
            r3.close()
            goto L77
        L5c:
            r3 = r1
        L5d:
            com.google.android.gms.common.internal.GmsLogger r8 = com.google.firebase.ml.common.internal.modeldownload.zzah.b     // Catch: java.lang.Throwable -> L78
            java.lang.String r2 = "Temp file is not found"
            r8.e(r0, r2)     // Catch: java.lang.Throwable -> L78
            if (r3 == 0) goto L77
            r3.close()
            goto L77
        L6a:
            r3 = r1
        L6b:
            com.google.android.gms.common.internal.GmsLogger r8 = com.google.firebase.ml.common.internal.modeldownload.zzah.b     // Catch: java.lang.Throwable -> L78
            java.lang.String r2 = "Do not have SHA-256 algorithm"
            r8.e(r0, r2)     // Catch: java.lang.Throwable -> L78
            if (r3 == 0) goto L77
            r3.close()
        L77:
            return r1
        L78:
            r8 = move-exception
            r1 = r3
        L7a:
            if (r1 == 0) goto L7f
            r1.close()
        L7f:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.ml.common.internal.modeldownload.zzah.c(java.io.File):java.lang.String");
    }

    public final zzs b(File file, zzw zzwVar) {
        return this.f11390a.zza(file, zzwVar);
    }
}
