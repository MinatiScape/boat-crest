package com.google.android.gms.internal.clearcut;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
/* loaded from: classes7.dex */
public class zzy {
    public static HashMap<String, String> d;
    public static Object i;
    public static boolean j;

    /* renamed from: a  reason: collision with root package name */
    public static final Uri f8623a = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri b = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzcr = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzcs = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    public static final AtomicBoolean c = new AtomicBoolean();
    public static final HashMap<String, Boolean> e = new HashMap<>();
    public static final HashMap<String, Integer> f = new HashMap<>();
    public static final HashMap<String, Long> g = new HashMap<>();
    public static final HashMap<String, Float> h = new HashMap<>();
    public static String[] k = new String[0];

    public static <T> T a(HashMap<String, T> hashMap, String str, T t) {
        synchronized (zzy.class) {
            if (hashMap.containsKey(str)) {
                T t2 = hashMap.get(str);
                if (t2 != null) {
                    t = t2;
                }
                return t;
            }
            return null;
        }
    }

    public static Map<String, String> b(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(b, null, null, strArr, null);
        TreeMap treeMap = new TreeMap();
        if (query == null) {
            return treeMap;
        }
        while (query.moveToNext()) {
            try {
                treeMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return treeMap;
    }

    public static void c(ContentResolver contentResolver) {
        if (d == null) {
            c.set(false);
            d = new HashMap<>();
            i = new Object();
            j = false;
            contentResolver.registerContentObserver(f8623a, true, new b4(null));
        } else if (c.getAndSet(false)) {
            d.clear();
            e.clear();
            f.clear();
            g.clear();
            h.clear();
            i = new Object();
            j = false;
        }
    }

    public static void d(Object obj, String str, String str2) {
        synchronized (zzy.class) {
            if (obj == i) {
                d.put(str, str2);
            }
        }
    }

    public static <T> void e(Object obj, HashMap<String, T> hashMap, String str, T t) {
        synchronized (zzy.class) {
            if (obj == i) {
                hashMap.put(str, t);
                d.remove(str);
            }
        }
    }

    public static Object f(ContentResolver contentResolver) {
        Object obj;
        synchronized (zzy.class) {
            c(contentResolver);
            obj = i;
        }
        return obj;
    }

    public static long getLong(ContentResolver contentResolver, String str, long j2) {
        Object f2 = f(contentResolver);
        long j3 = 0;
        Long l = (Long) a(g, str, 0L);
        if (l != null) {
            return l.longValue();
        }
        String zza = zza(contentResolver, str, (String) null);
        if (zza != null) {
            try {
                long parseLong = Long.parseLong(zza);
                l = Long.valueOf(parseLong);
                j3 = parseLong;
            } catch (NumberFormatException unused) {
            }
        }
        e(f2, g, str, l);
        return j3;
    }

    public static String zza(ContentResolver contentResolver, String str, String str2) {
        synchronized (zzy.class) {
            c(contentResolver);
            Object obj = i;
            if (d.containsKey(str)) {
                String str3 = d.get(str);
                return str3 != null ? str3 : null;
            }
            for (String str4 : k) {
                if (str.startsWith(str4)) {
                    if (!j || d.isEmpty()) {
                        d.putAll(b(contentResolver, k));
                        j = true;
                        if (d.containsKey(str)) {
                            String str5 = d.get(str);
                            return str5 != null ? str5 : null;
                        }
                    }
                    return null;
                }
            }
            Cursor query = contentResolver.query(f8623a, null, null, new String[]{str}, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(1);
                        if (string != null && string.equals(null)) {
                            string = null;
                        }
                        d(obj, str, string);
                        String str6 = string != null ? string : null;
                        query.close();
                        return str6;
                    }
                } finally {
                    if (query != null) {
                        query.close();
                    }
                }
            }
            d(obj, str, null);
            return null;
        }
    }

    public static boolean zza(ContentResolver contentResolver, String str, boolean z) {
        Object f2 = f(contentResolver);
        HashMap<String, Boolean> hashMap = e;
        Boolean bool = (Boolean) a(hashMap, str, Boolean.valueOf(z));
        if (bool != null) {
            return bool.booleanValue();
        }
        String zza = zza(contentResolver, str, (String) null);
        if (zza != null && !zza.equals("")) {
            if (zzcr.matcher(zza).matches()) {
                z = true;
                bool = Boolean.TRUE;
            } else if (zzcs.matcher(zza).matches()) {
                z = false;
                bool = Boolean.FALSE;
            } else {
                Log.w("Gservices", "attempt to read gservices key " + str + " (value \"" + zza + "\") as boolean");
            }
        }
        e(f2, hashMap, str, bool);
        return z;
    }
}
