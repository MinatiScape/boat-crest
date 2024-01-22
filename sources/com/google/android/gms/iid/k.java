package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import com.google.android.gms.internal.gcm.zzq;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;
/* loaded from: classes6.dex */
public final class k {
    @Nullable
    public static l a(SharedPreferences sharedPreferences, String str) throws m {
        String string = sharedPreferences.getString(zzak.e(str, "|P|"), null);
        String string2 = sharedPreferences.getString(zzak.e(str, "|K|"), null);
        if (string == null || string2 == null) {
            return null;
        }
        return new l(j(string, string2), f(sharedPreferences, str));
    }

    @Nullable
    public static l b(File file) throws m, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String property = properties.getProperty("pub");
            String property2 = properties.getProperty("pri");
            if (property != null && property2 != null) {
                try {
                    l lVar = new l(j(property, property2), Long.parseLong(properties.getProperty("cre")));
                    d(null, fileInputStream);
                    return lVar;
                } catch (NumberFormatException e) {
                    throw new m(e);
                }
            }
            d(null, fileInputStream);
            return null;
        } finally {
        }
    }

    public static void c(Context context, String str, l lVar) {
        String f;
        String g;
        long j;
        try {
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", "Writing key to properties file");
            }
            File p = p(context, str);
            p.createNewFile();
            Properties properties = new Properties();
            f = lVar.f();
            properties.setProperty("pub", f);
            g = lVar.g();
            properties.setProperty("pri", g);
            j = lVar.b;
            properties.setProperty("cre", String.valueOf(j));
            FileOutputStream fileOutputStream = new FileOutputStream(p);
            properties.store(fileOutputStream, (String) null);
            e(null, fileOutputStream);
        } catch (IOException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(valueOf.length() + 21);
            sb.append("Failed to write key: ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
        }
    }

    public static /* synthetic */ void d(Throwable th, FileInputStream fileInputStream) {
        if (th == null) {
            fileInputStream.close();
            return;
        }
        try {
            fileInputStream.close();
        } catch (Throwable th2) {
            zzq.zzd(th, th2);
        }
    }

    public static /* synthetic */ void e(Throwable th, FileOutputStream fileOutputStream) {
        if (th == null) {
            fileOutputStream.close();
            return;
        }
        try {
            fileOutputStream.close();
        } catch (Throwable th2) {
            zzq.zzd(th, th2);
        }
    }

    public static long f(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzak.e(str, "cre"), null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException unused) {
                return 0L;
            }
        }
        return 0L;
    }

    public static KeyPair j(String str, String str2) throws m {
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                return new KeyPair(keyFactory.generatePublic(new X509EncodedKeySpec(decode)), keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(valueOf.length() + 19);
                sb.append("Invalid key stored ");
                sb.append(valueOf);
                Log.w("InstanceID", sb.toString());
                throw new m(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new m(e2);
        }
    }

    public static void k(Context context, String str) {
        File p = p(context, str);
        if (p.exists()) {
            p.delete();
        }
    }

    public static void n(Context context) {
        File[] listFiles;
        for (File file : o(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    public static File o(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir == null || !noBackupFilesDir.isDirectory()) {
            Log.w("InstanceID", "noBackupFilesDir doesn't exist, using regular files directory instead");
            return context.getFilesDir();
        }
        return noBackupFilesDir;
    }

    public static File p(Context context, String str) {
        String sb;
        if (TextUtils.isEmpty(str)) {
            sb = "com.google.InstanceId.properties";
        } else {
            try {
                String encodeToString = Base64.encodeToString(str.getBytes("UTF-8"), 11);
                StringBuilder sb2 = new StringBuilder(String.valueOf(encodeToString).length() + 33);
                sb2.append("com.google.InstanceId_");
                sb2.append(encodeToString);
                sb2.append(".properties");
                sb = sb2.toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(o(context), sb);
    }

    @WorkerThread
    public final l g(Context context, String str) throws m {
        l l = l(context, str);
        return l != null ? l : i(context, str);
    }

    public final void h(Context context, String str, l lVar) {
        String f;
        String g;
        long j;
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (lVar.equals(a(sharedPreferences, str))) {
                return;
            }
        } catch (m unused) {
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Writing key to shared preferences");
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String e = zzak.e(str, "|P|");
        f = lVar.f();
        edit.putString(e, f);
        String e2 = zzak.e(str, "|K|");
        g = lVar.g();
        edit.putString(e2, g);
        String e3 = zzak.e(str, "cre");
        j = lVar.b;
        edit.putString(e3, String.valueOf(j));
        edit.commit();
    }

    @WorkerThread
    public final l i(Context context, String str) {
        l lVar = new l(zzd.zzl(), System.currentTimeMillis());
        try {
            l l = l(context, str);
            if (l != null) {
                if (Log.isLoggable("InstanceID", 3)) {
                    Log.d("InstanceID", "Loaded key after generating new one, using loaded one");
                }
                return l;
            }
        } catch (m unused) {
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Generated new key");
        }
        c(context, str, lVar);
        h(context, str, lVar);
        return lVar;
    }

    @Nullable
    public final l l(Context context, String str) throws m {
        l m;
        try {
            m = m(context, str);
        } catch (m e) {
            e = e;
        }
        if (m != null) {
            h(context, str, m);
            return m;
        }
        e = null;
        try {
            l a2 = a(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
            if (a2 != null) {
                c(context, str, a2);
                return a2;
            }
        } catch (m e2) {
            e = e2;
        }
        if (e == null) {
            return null;
        }
        throw e;
    }

    @Nullable
    public final l m(Context context, String str) throws m {
        File p = p(context, str);
        if (p.exists()) {
            try {
                return b(p);
            } catch (IOException e) {
                if (Log.isLoggable("InstanceID", 3)) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 40);
                    sb.append("Failed to read key from file, retrying: ");
                    sb.append(valueOf);
                    Log.d("InstanceID", sb.toString());
                }
                try {
                    return b(p);
                } catch (IOException e2) {
                    String valueOf2 = String.valueOf(e2);
                    StringBuilder sb2 = new StringBuilder(valueOf2.length() + 45);
                    sb2.append("IID file exists, but failed to read from it: ");
                    sb2.append(valueOf2);
                    Log.w("InstanceID", sb2.toString());
                    throw new m(e2);
                }
            }
        }
        return null;
    }
}
