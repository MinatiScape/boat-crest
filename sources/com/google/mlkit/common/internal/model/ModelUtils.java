package com.google.mlkit.common.internal.model;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzag;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@KeepForSdk
@WorkerThread
/* loaded from: classes10.dex */
public class ModelUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final GmsLogger f11569a = new GmsLogger("ModelUtils", "");

    @KeepForSdk
    /* loaded from: classes10.dex */
    public static abstract class AutoMLManifest {
        @NonNull
        @KeepForSdk
        public abstract String getLabelsFile();

        @NonNull
        @KeepForSdk
        public abstract String getModelFile();

        @NonNull
        @KeepForSdk
        public abstract String getModelType();
    }

    @KeepForSdk
    /* loaded from: classes10.dex */
    public static abstract class ModelLoggingInfo {
        public static ModelLoggingInfo a(long j, @Nullable String str, boolean z) {
            return new b(j, zzag.zzb(str), z);
        }

        @NonNull
        @KeepForSdk
        public abstract String getHash();

        @KeepForSdk
        public abstract long getSize();

        @KeepForSdk
        public abstract boolean isManifestModel();
    }

    @Nullable
    public static String a(Context context, String str, boolean z) {
        AutoMLManifest parseManifestFile = parseManifestFile(str, z, context);
        if (parseManifestFile == null) {
            f11569a.e("ModelUtils", "Failed to parse manifest file.");
            return null;
        }
        return new File(new File(str).getParent(), parseManifestFile.getModelFile()).toString();
    }

    @Nullable
    public static String b(InputStream inputStream) {
        int i;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bArr = new byte[1048576];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
            }
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (IOException unused) {
            f11569a.e("ModelUtils", "Failed to read model file");
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            f11569a.e("ModelUtils", "Do not have SHA-256 algorithm");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0106 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v2 */
    @androidx.annotation.Nullable
    @com.google.android.gms.common.annotation.KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo getModelLoggingInfo(@androidx.annotation.NonNull android.content.Context r11, @androidx.annotation.NonNull com.google.mlkit.common.model.LocalModel r12) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.internal.model.ModelUtils.getModelLoggingInfo(android.content.Context, com.google.mlkit.common.model.LocalModel):com.google.mlkit.common.internal.model.ModelUtils$ModelLoggingInfo");
    }

    @Nullable
    @KeepForSdk
    public static String getSHA256(@NonNull File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            String b = b(fileInputStream);
            fileInputStream.close();
            return b;
        } catch (IOException e) {
            f11569a.e("ModelUtils", "Failed to create FileInputStream for model: ".concat(e.toString()));
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x002b, code lost:
        if (new java.io.File(r5).exists() == false) goto L5;
     */
    @androidx.annotation.Nullable
    @com.google.android.gms.common.annotation.KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest parseManifestFile(@androidx.annotation.NonNull java.lang.String r5, boolean r6, @androidx.annotation.NonNull android.content.Context r7) {
        /*
            java.lang.String r0 = java.lang.String.valueOf(r5)
            com.google.android.gms.common.internal.GmsLogger r1 = com.google.mlkit.common.internal.model.ModelUtils.f11569a
            java.lang.String r2 = "Manifest file path: "
            java.lang.String r0 = r2.concat(r0)
            java.lang.String r2 = "ModelUtils"
            r1.d(r2, r0)
            r0 = 0
            if (r6 == 0) goto L22
            android.content.res.AssetManager r3 = r7.getAssets()     // Catch: java.io.IOException -> L2d
            java.io.InputStream r3 = r3.open(r5)     // Catch: java.io.IOException -> L2d
            if (r3 == 0) goto L35
            r3.close()     // Catch: java.io.IOException -> L2d
            goto L35
        L22:
            java.io.File r3 = new java.io.File
            r3.<init>(r5)
            boolean r3 = r3.exists()
            if (r3 != 0) goto L35
        L2d:
            com.google.android.gms.common.internal.GmsLogger r5 = com.google.mlkit.common.internal.model.ModelUtils.f11569a
            java.lang.String r6 = "Manifest file does not exist."
            r5.e(r2, r6)
            return r0
        L35:
            boolean r3 = r5.isEmpty()     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            r4 = 0
            if (r3 == 0) goto L3f
            byte[] r5 = new byte[r4]     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            goto L62
        L3f:
            if (r6 == 0) goto L4a
            android.content.res.AssetManager r6 = r7.getAssets()     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            java.io.InputStream r5 = r6.open(r5)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            goto L55
        L4a:
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            java.io.File r7 = new java.io.File     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            r7.<init>(r5)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            r6.<init>(r7)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            r5 = r6
        L55:
            int r6 = r5.available()     // Catch: java.lang.Throwable -> L9a
            byte[] r7 = new byte[r6]     // Catch: java.lang.Throwable -> L9a
            r5.read(r7, r4, r6)     // Catch: java.lang.Throwable -> L9a
            r5.close()     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            r5 = r7
        L62:
            java.lang.String r6 = new java.lang.String     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            java.lang.String r7 = "UTF-8"
            r6.<init>(r5, r7)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            r5.<init>()     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            java.lang.String r7 = "Json string from the manifest file: "
            r5.append(r7)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            r5.append(r6)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            java.lang.String r5 = r5.toString()     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            r1.d(r2, r5)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            r5.<init>(r6)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            java.lang.String r6 = "modelType"
            java.lang.String r6 = r5.getString(r6)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            java.lang.String r7 = "modelFile"
            java.lang.String r7 = r5.getString(r7)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            java.lang.String r1 = "labelsFile"
            java.lang.String r5 = r5.getString(r1)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            com.google.mlkit.common.internal.model.a r1 = new com.google.mlkit.common.internal.model.a     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            r1.<init>(r6, r7, r5)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
            return r1
        L9a:
            r6 = move-exception
            if (r5 == 0) goto La5
            r5.close()     // Catch: java.lang.Throwable -> La1
            goto La5
        La1:
            r5 = move-exception
            com.google.mlkit.common.internal.model.zzh.zza(r6, r5)     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
        La5:
            throw r6     // Catch: java.io.IOException -> La6 org.json.JSONException -> La8
        La6:
            r5 = move-exception
            goto La9
        La8:
            r5 = move-exception
        La9:
            com.google.android.gms.common.internal.GmsLogger r6 = com.google.mlkit.common.internal.model.ModelUtils.f11569a
            java.lang.String r7 = "Error parsing the manifest file."
            r6.e(r2, r7, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.internal.model.ModelUtils.parseManifestFile(java.lang.String, boolean, android.content.Context):com.google.mlkit.common.internal.model.ModelUtils$AutoMLManifest");
    }

    public static boolean zza(@NonNull File file, @NonNull String str) {
        String sha256 = getSHA256(file);
        f11569a.d("ModelUtils", "Calculated hash value is: ".concat(String.valueOf(sha256)));
        return str.equals(sha256);
    }
}
