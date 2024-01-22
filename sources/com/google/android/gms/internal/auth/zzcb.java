package com.google.android.gms.internal.auth;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
/* loaded from: classes6.dex */
public final class zzcb {
    public static HashMap b;
    public static Object g;
    public static boolean h;
    public static final Uri zza = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri zzb = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzc = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzd = Pattern.compile("^(0|false|f|off|no|n)$", 2);

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicBoolean f8551a = new AtomicBoolean();
    public static final HashMap c = new HashMap();
    public static final HashMap d = new HashMap();
    public static final HashMap e = new HashMap();
    public static final HashMap f = new HashMap();
    public static final String[] i = new String[0];

    public static void b(Object obj, String str, String str2) {
        synchronized (zzcb.class) {
            if (obj == g) {
                b.put(str, str2);
            }
        }
    }

    public static String zza(ContentResolver contentResolver, String str, String str2) {
        synchronized (zzcb.class) {
            if (b == null) {
                f8551a.set(false);
                b = new HashMap();
                g = new Object();
                h = false;
                contentResolver.registerContentObserver(zza, true, new u(null));
            } else if (f8551a.getAndSet(false)) {
                b.clear();
                c.clear();
                d.clear();
                e.clear();
                f.clear();
                g = new Object();
                h = false;
            }
            Object obj = g;
            if (b.containsKey(str)) {
                String str3 = (String) b.get(str);
                if (str3 != null) {
                    r3 = str3;
                }
                return r3;
            }
            int length = i.length;
            Cursor query = contentResolver.query(zza, null, null, new String[]{str}, null);
            if (query == null) {
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
