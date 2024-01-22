package com.google.android.gms.internal.vision;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
/* loaded from: classes10.dex */
public class zzal {
    public static HashMap<String, String> c;
    public static Object h;
    public static boolean i;
    public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");

    /* renamed from: a  reason: collision with root package name */
    public static final Uri f10007a = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzev = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzew = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    public static final AtomicBoolean b = new AtomicBoolean();
    public static final HashMap<String, Boolean> d = new HashMap<>();
    public static final HashMap<String, Integer> e = new HashMap<>();
    public static final HashMap<String, Long> f = new HashMap<>();
    public static final HashMap<String, Float> g = new HashMap<>();
    public static String[] j = new String[0];

    public static Map<String, String> a(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(f10007a, null, null, strArr, null);
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

    public static void b(Object obj, String str, String str2) {
        synchronized (zzal.class) {
            if (obj == h) {
                c.put(str, str2);
            }
        }
    }

    public static String zza(ContentResolver contentResolver, String str, String str2) {
        synchronized (zzal.class) {
            if (c == null) {
                b.set(false);
                c = new HashMap<>();
                h = new Object();
                i = false;
                contentResolver.registerContentObserver(CONTENT_URI, true, new a(null));
            } else if (b.getAndSet(false)) {
                c.clear();
                d.clear();
                e.clear();
                f.clear();
                g.clear();
                h = new Object();
                i = false;
            }
            Object obj = h;
            if (c.containsKey(str)) {
                String str3 = c.get(str);
                return str3 != null ? str3 : null;
            }
            for (String str4 : j) {
                if (str.startsWith(str4)) {
                    if (!i || c.isEmpty()) {
                        c.putAll(a(contentResolver, j));
                        i = true;
                        if (c.containsKey(str)) {
                            String str5 = c.get(str);
                            return str5 != null ? str5 : null;
                        }
                    }
                    return null;
                }
            }
            Cursor query = contentResolver.query(CONTENT_URI, null, null, new String[]{str}, null);
            if (query == null) {
                if (query != null) {
                }
                return null;
            }
            try {
                if (!query.moveToFirst()) {
                    b(obj, str, null);
                    return null;
                }
                String string = query.getString(1);
                if (string != null && string.equals(null)) {
                    string = null;
                }
                b(obj, str, string);
                return string != null ? string : null;
            } finally {
                query.close();
            }
        }
    }
}
