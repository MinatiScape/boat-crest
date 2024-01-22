package com.crrepa.r;

import android.text.TextUtils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public Thread f7836a;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ String h;
        public final /* synthetic */ Map i;
        public final /* synthetic */ Map j;
        public final /* synthetic */ com.crrepa.s.a k;

        public a(String str, Map map, Map map2, com.crrepa.s.a aVar) {
            this.h = str;
            this.i = map;
            this.j = map2;
            this.k = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.crrepa.r.c c = new com.crrepa.r.b().c(d.this.c(this.h, this.i), this.j);
            if (c.c == 200) {
                this.k.c(c);
            } else {
                this.k.a(c);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public final /* synthetic */ String h;
        public final /* synthetic */ Map i;
        public final /* synthetic */ String j;
        public final /* synthetic */ Map k;
        public final /* synthetic */ com.crrepa.s.a l;

        public b(String str, Map map, String str2, Map map2, com.crrepa.s.a aVar) {
            this.h = str;
            this.i = map;
            this.j = str2;
            this.k = map2;
            this.l = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.crrepa.r.c b = new com.crrepa.r.b().b(this.h, d.this.e(this.i, this.j), d.this.k(this.i, this.j), this.k);
            if (b.c == 200) {
                this.l.c(b);
            } else {
                this.l.a(b);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class c implements Runnable {
        public final /* synthetic */ String h;
        public final /* synthetic */ File i;
        public final /* synthetic */ List j;
        public final /* synthetic */ Map k;
        public final /* synthetic */ String l;
        public final /* synthetic */ String m;
        public final /* synthetic */ Map n;
        public final /* synthetic */ Map o;
        public final /* synthetic */ com.crrepa.s.a p;

        public c(d dVar, String str, File file, List list, Map map, String str2, String str3, Map map2, Map map3, com.crrepa.s.a aVar) {
            this.h = str;
            this.i = file;
            this.j = list;
            this.k = map;
            this.l = str2;
            this.m = str3;
            this.n = map2;
            this.o = map3;
            this.p = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.crrepa.r.c a2 = new com.crrepa.r.b().a(this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p);
            if (a2.c == 200) {
                this.p.c(a2);
            } else {
                this.p.a(a2);
            }
        }
    }

    public d(String str, File file, List<File> list, Map<String, File> map, String str2, String str3, Map<String, String> map2, Map<String, String> map3, com.crrepa.s.a aVar) {
        g(str, file, list, map, str2, str3, map2, map3, aVar);
    }

    public d(String str, String str2, Map<String, String> map, com.crrepa.s.a aVar) {
        h(str, null, str2, map, aVar);
    }

    public d(String str, String str2, Map<String, String> map, Map<String, String> map2, com.crrepa.s.a aVar) {
        str.hashCode();
        if (str.equals("GET")) {
            i(str2, map, map2, aVar);
        } else if (str.equals("POST")) {
            h(str2, map, null, map2, aVar);
        }
    }

    public final String c(String str, Map<String, String> map) {
        if (map != null) {
            String str2 = str + "?";
            for (String str3 : map.keySet()) {
                str2 = str2 + str3 + "=" + map.get(str3) + "&";
            }
            return str2.substring(0, str2.length() - 1);
        }
        return str;
    }

    public final String d(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        try {
            boolean z = true;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public final String e(Map<String, String> map, String str) {
        if (map != null) {
            return d(map);
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    public void f() {
        Thread thread = this.f7836a;
        if (thread != null) {
            thread.start();
        }
    }

    public final void g(String str, File file, List<File> list, Map<String, File> map, String str2, String str3, Map<String, String> map2, Map<String, String> map3, com.crrepa.s.a aVar) {
        this.f7836a = new Thread(new c(this, str, file, list, map, str2, str3, map2, map3, aVar));
    }

    public final void h(String str, Map<String, String> map, String str2, Map<String, String> map2, com.crrepa.s.a aVar) {
        this.f7836a = new Thread(new b(str, map, str2, map2, aVar));
    }

    public final void i(String str, Map<String, String> map, Map<String, String> map2, com.crrepa.s.a aVar) {
        this.f7836a = new Thread(new a(str, map, map2, aVar));
    }

    public final String k(Map<String, String> map, String str) {
        if (map == null && !TextUtils.isEmpty(str)) {
            return "application/json;charset=utf-8";
        }
        return null;
    }
}
