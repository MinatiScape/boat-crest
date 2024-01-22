package com.google.android.gms.internal.mlkit_common;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import com.clevertap.android.sdk.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes8.dex */
public final class zzj {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f9338a = {"com.android.", "com.google.", "com.chrome.", "com.nest.", "com.waymo.", "com.waze"};
    public static final String[] b;
    public static final String[] c;
    public static final /* synthetic */ int zza = 0;

    static {
        String[] strArr = new String[2];
        strArr[0] = Constants.KEY_MEDIA;
        String str = Build.HARDWARE;
        strArr[1] = true != (str.equals("goldfish") || str.equals("ranchu")) ? "" : "androidx.test.services.storage.runfiles";
        b = strArr;
        String[] strArr2 = new String[3];
        int i = Build.VERSION.SDK_INT;
        strArr2[0] = i <= 25 ? "com.google.android.inputmethod.latin.inputcontent" : "";
        strArr2[1] = i <= 25 ? "com.google.android.inputmethod.latin.dev.inputcontent" : "";
        strArr2[2] = "com.google.android.apps.docs.storage.legacy";
        c = strArr2;
    }

    public static Uri a(Uri uri) {
        return Build.VERSION.SDK_INT < 30 ? Uri.parse(uri.toString()) : uri;
    }

    public static Object b(Object obj) throws FileNotFoundException {
        if (obj != null) {
            return obj;
        }
        throw new FileNotFoundException("Content resolver returned null value.");
    }

    public static String c(File file) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        return !canonicalPath.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR) ? canonicalPath.concat(MqttTopic.TOPIC_LEVEL_SEPARATOR) : canonicalPath;
    }

    public static void d(AssetFileDescriptor assetFileDescriptor, FileNotFoundException fileNotFoundException) {
        try {
            assetFileDescriptor.close();
        } catch (IOException e) {
            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(fileNotFoundException, e);
        }
    }

    public static void e(ParcelFileDescriptor parcelFileDescriptor, FileNotFoundException fileNotFoundException) {
        try {
            parcelFileDescriptor.close();
        } catch (IOException e) {
            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(fileNotFoundException, e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00c1 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void f(final android.content.Context r7, android.os.ParcelFileDescriptor r8, android.net.Uri r9, com.google.android.gms.internal.mlkit_common.zzi r10) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r9 = r9.getPath()
            r0.<init>(r9)
            java.lang.String r9 = r0.getCanonicalPath()
            java.io.FileDescriptor r8 = r8.getFileDescriptor()
            com.google.android.gms.internal.mlkit_common.i6 r8 = com.google.android.gms.internal.mlkit_common.i6.a(r8)
            com.google.android.gms.internal.mlkit_common.i6 r0 = com.google.android.gms.internal.mlkit_common.i6.b(r9)
            boolean r1 = r0.c
            java.lang.String r2 = "Can't open file: "
            if (r1 != 0) goto Lda
            long r3 = r8.f9219a
            long r5 = r0.f9219a
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto Lcc
            long r3 = r8.b
            long r0 = r0.b
            int r8 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r8 != 0) goto Lcc
            java.lang.String r8 = "/proc/"
            boolean r8 = r9.startsWith(r8)
            if (r8 != 0) goto Lc2
            java.lang.String r8 = "/data/misc/"
            boolean r8 = r9.startsWith(r8)
            if (r8 != 0) goto Lc2
            com.google.android.gms.internal.mlkit_common.zzi.a(r10)
            java.io.File r8 = androidx.core.content.ContextCompat.getDataDir(r7)
            r0 = 0
            r1 = 1
            if (r8 == 0) goto L57
            java.lang.String r8 = c(r8)
            boolean r8 = r9.startsWith(r8)
            if (r8 == 0) goto L66
        L54:
            r0 = r1
            goto Lbb
        L57:
            java.io.File r8 = android.os.Environment.getDataDirectory()
            java.lang.String r8 = c(r8)
            boolean r8 = r9.startsWith(r8)
            if (r8 == 0) goto L66
            goto L54
        L66:
            android.content.Context r8 = androidx.core.content.ContextCompat.createDeviceProtectedStorageContext(r7)
            if (r8 == 0) goto L7d
            java.io.File r8 = androidx.core.content.ContextCompat.getDataDir(r8)
            if (r8 == 0) goto L7d
            java.lang.String r8 = c(r8)
            boolean r8 = r9.startsWith(r8)
            if (r8 == 0) goto L7d
            goto L54
        L7d:
            com.google.android.gms.internal.mlkit_common.zzc r8 = new com.google.android.gms.internal.mlkit_common.zzc
            r8.<init>()
            java.io.File[] r8 = h(r8)
            int r3 = r8.length
            r4 = r0
        L88:
            if (r4 >= r3) goto L9c
            r5 = r8[r4]
            if (r5 == 0) goto L99
            java.lang.String r5 = c(r5)
            boolean r5 = r9.startsWith(r5)
            if (r5 == 0) goto L99
            goto L54
        L99:
            int r4 = r4 + 1
            goto L88
        L9c:
            com.google.android.gms.internal.mlkit_common.zzd r8 = new com.google.android.gms.internal.mlkit_common.zzd
            r8.<init>()
            java.io.File[] r7 = h(r8)
            int r8 = r7.length
            r3 = r0
        La7:
            if (r3 >= r8) goto Lbb
            r4 = r7[r3]
            if (r4 == 0) goto Lb8
            java.lang.String r4 = c(r4)
            boolean r4 = r9.startsWith(r4)
            if (r4 == 0) goto Lb8
            goto L54
        Lb8:
            int r3 = r3 + 1
            goto La7
        Lbb:
            boolean r7 = com.google.android.gms.internal.mlkit_common.zzi.b(r10)
            if (r0 != r7) goto Lc2
            return
        Lc2:
            java.io.FileNotFoundException r7 = new java.io.FileNotFoundException
            java.lang.String r8 = r2.concat(r9)
            r7.<init>(r8)
            throw r7
        Lcc:
            java.lang.String r7 = java.lang.String.valueOf(r9)
            java.io.FileNotFoundException r8 = new java.io.FileNotFoundException
            java.lang.String r7 = r2.concat(r7)
            r8.<init>(r7)
            throw r8
        Lda:
            java.lang.String r7 = java.lang.String.valueOf(r9)
            java.io.FileNotFoundException r8 = new java.io.FileNotFoundException
            java.lang.String r7 = r2.concat(r7)
            r8.<init>(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_common.zzj.f(android.content.Context, android.os.ParcelFileDescriptor, android.net.Uri, com.google.android.gms.internal.mlkit_common.zzi):void");
    }

    public static boolean g(Context context, Uri uri, int i, zzi zziVar) {
        boolean z;
        boolean z2;
        boolean z3;
        String authority = uri.getAuthority();
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(authority, 0);
        if (resolveContentProvider == null) {
            int lastIndexOf = authority.lastIndexOf(64);
            if (lastIndexOf >= 0) {
                authority = authority.substring(lastIndexOf + 1);
                resolveContentProvider = context.getPackageManager().resolveContentProvider(authority, 0);
            }
            if (resolveContentProvider == null) {
                z3 = zziVar.f9337a;
                return !z3;
            }
        }
        if (zzi.c(zziVar, context, new zzr(uri, resolveContentProvider, authority)) - 1 != 1) {
            if (context.getPackageName().equals(resolveContentProvider.packageName)) {
                z2 = zziVar.f9337a;
                return z2;
            }
            z = zziVar.f9337a;
            if (z) {
                return false;
            }
            if (context.checkUriPermission(uri, Process.myPid(), Process.myUid(), 1) != 0 && resolveContentProvider.exported) {
                String[] strArr = b;
                int length = strArr.length;
                for (int i2 = 0; i2 < 2; i2++) {
                    if (strArr[i2].equals(authority)) {
                        return true;
                    }
                }
                String[] strArr2 = c;
                int length2 = strArr2.length;
                for (int i3 = 0; i3 < 3; i3++) {
                    if (strArr2[i3].equals(authority)) {
                        return true;
                    }
                }
                String[] strArr3 = f9338a;
                for (int i4 = 0; i4 < 6; i4++) {
                    String str = strArr3[i4];
                    if (str.charAt(str.length() - 1) == '.') {
                        if (resolveContentProvider.packageName.startsWith(str)) {
                            return false;
                        }
                    } else if (resolveContentProvider.packageName.equals(str)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static File[] h(Callable callable) {
        try {
            return (File[]) callable.call();
        } catch (NullPointerException e) {
            if (Build.VERSION.SDK_INT < 22) {
                return new File[0];
            }
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static AssetFileDescriptor zza(Context context, Uri uri, String str) throws FileNotFoundException {
        zzi zziVar = zzi.zza;
        ContentResolver contentResolver = context.getContentResolver();
        Uri a2 = a(uri);
        String scheme = a2.getScheme();
        if ("android.resource".equals(scheme)) {
            return contentResolver.openAssetFileDescriptor(a2, RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME);
        }
        if ("content".equals(scheme)) {
            if (g(context, a2, 1, zziVar)) {
                AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(a2, RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME);
                b(openAssetFileDescriptor);
                return openAssetFileDescriptor;
            }
            throw new FileNotFoundException("Can't open content uri.");
        } else if ("file".equals(scheme)) {
            AssetFileDescriptor openAssetFileDescriptor2 = contentResolver.openAssetFileDescriptor(a2, RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME);
            b(openAssetFileDescriptor2);
            try {
                f(context, openAssetFileDescriptor2.getParcelFileDescriptor(), a2, zziVar);
                return openAssetFileDescriptor2;
            } catch (FileNotFoundException e) {
                d(openAssetFileDescriptor2, e);
                throw e;
            } catch (IOException e2) {
                FileNotFoundException fileNotFoundException = new FileNotFoundException("Validation failed.");
                fileNotFoundException.initCause(e2);
                d(openAssetFileDescriptor2, fileNotFoundException);
                throw fileNotFoundException;
            }
        } else {
            throw new FileNotFoundException("Unsupported scheme");
        }
    }

    public static InputStream zzb(Context context, Uri uri, zzi zziVar) throws FileNotFoundException {
        ContentResolver contentResolver = context.getContentResolver();
        Uri a2 = a(uri);
        String scheme = a2.getScheme();
        if ("android.resource".equals(scheme)) {
            return contentResolver.openInputStream(a2);
        }
        if ("content".equals(scheme)) {
            if (g(context, a2, 1, zziVar)) {
                InputStream openInputStream = contentResolver.openInputStream(a2);
                b(openInputStream);
                return openInputStream;
            }
            throw new FileNotFoundException("Can't open content uri.");
        } else if ("file".equals(scheme)) {
            try {
                ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(Uri.fromFile(new File(a2.getPath()).getCanonicalFile()), RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME);
                try {
                    f(context, openFileDescriptor, a2, zziVar);
                    return new ParcelFileDescriptor.AutoCloseInputStream(openFileDescriptor);
                } catch (FileNotFoundException e) {
                    e(openFileDescriptor, e);
                    throw e;
                } catch (IOException e2) {
                    FileNotFoundException fileNotFoundException = new FileNotFoundException("Validation failed.");
                    fileNotFoundException.initCause(e2);
                    e(openFileDescriptor, fileNotFoundException);
                    throw fileNotFoundException;
                }
            } catch (IOException e3) {
                FileNotFoundException fileNotFoundException2 = new FileNotFoundException("Canonicalization failed.");
                fileNotFoundException2.initCause(e3);
                throw fileNotFoundException2;
            }
        } else {
            throw new FileNotFoundException("Unsupported scheme");
        }
    }
}
