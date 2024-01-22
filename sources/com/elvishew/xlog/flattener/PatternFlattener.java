package com.elvishew.xlog.flattener;

import com.elvishew.xlog.LogLevel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes9.dex */
public class PatternFlattener implements Flattener, Flattener2 {
    public static final Pattern c = Pattern.compile("\\{([^{}]*)\\}");

    /* renamed from: a  reason: collision with root package name */
    public String f7868a;
    public List<d> b;

    /* loaded from: classes9.dex */
    public static class a extends d {
        public String b;
        public ThreadLocal<SimpleDateFormat> c;

        /* renamed from: com.elvishew.xlog.flattener.PatternFlattener$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public class C0361a extends ThreadLocal<SimpleDateFormat> {
            public C0361a() {
            }

            @Override // java.lang.ThreadLocal
            /* renamed from: a */
            public SimpleDateFormat initialValue() {
                return new SimpleDateFormat(a.this.b, Locale.US);
            }
        }

        public a(String str, String str2, String str3) {
            super(str, str2);
            C0361a c0361a = new C0361a();
            this.c = c0361a;
            this.b = str3;
            try {
                c0361a.get().format(new Date());
            } catch (Exception e) {
                throw new IllegalArgumentException("Bad date pattern: " + str3, e);
            }
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.d
        public String a(String str, long j, int i, String str2, String str3) {
            return str.replace(this.f7870a, this.c.get().format(new Date(j)));
        }
    }

    /* loaded from: classes9.dex */
    public static class b extends d {
        public boolean b;

        public b(String str, String str2, boolean z) {
            super(str, str2);
            this.b = z;
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.d
        public String a(String str, long j, int i, String str2, String str3) {
            if (this.b) {
                return str.replace(this.f7870a, LogLevel.getLevelName(i));
            }
            return str.replace(this.f7870a, LogLevel.getShortLevelName(i));
        }
    }

    /* loaded from: classes9.dex */
    public static class c extends d {
        public c(String str, String str2) {
            super(str, str2);
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.d
        public String a(String str, long j, int i, String str2, String str3) {
            return str.replace(this.f7870a, str3);
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class d {

        /* renamed from: a  reason: collision with root package name */
        public String f7870a;

        public d(String str, String str2) {
            this.f7870a = str;
        }

        public abstract String a(String str, long j, int i, String str2, String str3);
    }

    /* loaded from: classes9.dex */
    public static class e extends d {
        public e(String str, String str2) {
            super(str, str2);
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.d
        public String a(String str, long j, int i, String str2, String str3) {
            return str.replace(this.f7870a, str2);
        }
    }

    public PatternFlattener(String str) {
        Objects.requireNonNull(str, "Pattern should not be null");
        this.f7868a = str;
        List<d> e2 = e(f(str));
        this.b = e2;
        if (e2.size() != 0) {
            return;
        }
        throw new IllegalArgumentException("No recognizable parameter found in the pattern " + str);
    }

    public static a a(String str, String str2) {
        if (str2.startsWith("d ") && str2.length() > 2) {
            return new a(str, str2, str2.substring(2));
        }
        if (str2.equals("d")) {
            return new a(str, str2, "yyyy-MM-dd HH:mm:ss.SSS");
        }
        return null;
    }

    public static b b(String str, String str2) {
        if (str2.equals("l")) {
            return new b(str, str2, false);
        }
        if (str2.equals("L")) {
            return new b(str, str2, true);
        }
        return null;
    }

    public static c c(String str, String str2) {
        if (str2.equals("m")) {
            return new c(str, str2);
        }
        return null;
    }

    public static d d(String str) {
        String str2 = "{" + str + "}";
        String trim = str.trim();
        a a2 = a(str2, trim);
        if (a2 != null) {
            return a2;
        }
        b b2 = b(str2, trim);
        if (b2 != null) {
            return b2;
        }
        e g = g(str2, trim);
        if (g != null) {
            return g;
        }
        c c2 = c(str2, trim);
        if (c2 != null) {
            return c2;
        }
        return null;
    }

    public static List<d> e(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (String str : list) {
            d d2 = d(str);
            if (d2 != null) {
                arrayList.add(d2);
            }
        }
        return arrayList;
    }

    public static List<String> f(String str) {
        ArrayList arrayList = new ArrayList(4);
        Matcher matcher = c.matcher(str);
        while (matcher.find()) {
            arrayList.add(matcher.group(1));
        }
        return arrayList;
    }

    public static e g(String str, String str2) {
        if (str2.equals(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT)) {
            return new e(str, str2);
        }
        return null;
    }

    @Override // com.elvishew.xlog.flattener.Flattener
    public CharSequence flatten(int i, String str, String str2) {
        return flatten(System.currentTimeMillis(), i, str, str2);
    }

    @Override // com.elvishew.xlog.flattener.Flattener2
    public CharSequence flatten(long j, int i, String str, String str2) {
        String str3 = this.f7868a;
        String str4 = str3;
        for (d dVar : this.b) {
            str4 = dVar.a(str4, j, i, str, str2);
        }
        return str4;
    }
}
