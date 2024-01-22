package com.google.android.gms.internal.phenotype;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
/* loaded from: classes6.dex */
public class zzf {
    public static HashMap<String, String> f;
    public static Object k;
    public static boolean l;

    /* renamed from: a  reason: collision with root package name */
    public static final Uri f9959a = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri b = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern c = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern d = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    public static final AtomicBoolean e = new AtomicBoolean();
    public static final HashMap<String, Boolean> g = new HashMap<>();
    public static final HashMap<String, Integer> h = new HashMap<>();
    public static final HashMap<String, Long> i = new HashMap<>();
    public static final HashMap<String, Float> j = new HashMap<>();
    public static String[] m = new String[0];

    public static <T> T a(HashMap<String, T> hashMap, String str, T t) {
        synchronized (zzf.class) {
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
        if (f == null) {
            e.set(false);
            f = new HashMap<>();
            k = new Object();
            l = false;
            contentResolver.registerContentObserver(f9959a, true, new a(null));
        } else if (e.getAndSet(false)) {
            f.clear();
            g.clear();
            h.clear();
            i.clear();
            j.clear();
            k = new Object();
            l = false;
        }
    }

    public static void d(Object obj, String str, String str2) {
        synchronized (zzf.class) {
            if (obj == k) {
                f.put(str, str2);
            }
        }
    }

    public static Object e(ContentResolver contentResolver) {
        Object obj;
        synchronized (zzf.class) {
            c(contentResolver);
            obj = k;
        }
        return obj;
    }

    public static String zza(ContentResolver contentResolver, String str, String str2) {
        synchronized (zzf.class) {
            c(contentResolver);
            Object obj = k;
            if (f.containsKey(str)) {
                String str3 = f.get(str);
                return str3 != null ? str3 : null;
            }
            for (String str4 : m) {
                if (str.startsWith(str4)) {
                    if (!l || f.isEmpty()) {
                        f.putAll(b(contentResolver, m));
                        l = true;
                        if (f.containsKey(str)) {
                            String str5 = f.get(str);
                            return str5 != null ? str5 : null;
                        }
                    }
                    return null;
                }
            }
            Cursor query = contentResolver.query(f9959a, null, null, new String[]{str}, null);
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
        Object e2 = e(contentResolver);
        HashMap<String, Boolean> hashMap = g;
        Boolean bool = (Boolean) a(hashMap, str, Boolean.valueOf(z));
        if (bool != null) {
            return bool.booleanValue();
        }
        String zza = zza(contentResolver, str, (String) null);
        if (zza != null && !zza.equals("")) {
            if (c.matcher(zza).matches()) {
                z = true;
                bool = Boolean.TRUE;
            } else if (d.matcher(zza).matches()) {
                z = false;
                bool = Boolean.FALSE;
            } else {
                Log.w("Gservices", "attempt to read gservices key " + str + " (value \"" + zza + "\") as boolean");
            }
        }
        synchronized (zzf.class) {
            if (e2 == k) {
                hashMap.put(str, bool);
                f.remove(str);
            }
        }
        return z;
    }
}
