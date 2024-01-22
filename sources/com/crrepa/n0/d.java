package com.crrepa.n0;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.reflect.Field;
import java.util.Locale;
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes9.dex */
public abstract class d implements com.crrepa.n0.e {

    /* renamed from: a  reason: collision with root package name */
    public static final d f7774a;
    public static final d b;
    public static final d c;
    public static final d d;
    public static final d e;
    private static final /* synthetic */ d[] f;

    /* loaded from: classes9.dex */
    public enum a extends d {
        public a(String str, int i) {
            super(str, i, null);
        }

        @Override // com.crrepa.n0.e
        public String a(Field field) {
            return field.getName();
        }
    }

    static {
        a aVar = new a("IDENTITY", 0);
        f7774a = aVar;
        d dVar = new d("UPPER_CAMEL_CASE", 1) { // from class: com.crrepa.n0.d.b
            @Override // com.crrepa.n0.e
            public String a(Field field) {
                return d.a(field.getName());
            }
        };
        b = dVar;
        d dVar2 = new d("UPPER_CAMEL_CASE_WITH_SPACES", 2) { // from class: com.crrepa.n0.d.c
            @Override // com.crrepa.n0.e
            public String a(Field field) {
                return d.a(d.a(field.getName(), HexStringBuilder.DEFAULT_SEPARATOR));
            }
        };
        c = dVar2;
        d dVar3 = new d("LOWER_CASE_WITH_UNDERSCORES", 3) { // from class: com.crrepa.n0.d.d
            @Override // com.crrepa.n0.e
            public String a(Field field) {
                return d.a(field.getName(), "_").toLowerCase(Locale.ENGLISH);
            }
        };
        d = dVar3;
        d dVar4 = new d("LOWER_CASE_WITH_DASHES", 4) { // from class: com.crrepa.n0.d.e
            @Override // com.crrepa.n0.e
            public String a(Field field) {
                return d.a(field.getName(), "-").toLowerCase(Locale.ENGLISH);
            }
        };
        e = dVar4;
        f = new d[]{aVar, dVar, dVar2, dVar3, dVar4};
    }

    private d(String str, int i) {
    }

    public /* synthetic */ d(String str, int i, a aVar) {
        this(str, i);
    }

    private static String a(char c2, String str, int i) {
        if (i < str.length()) {
            return c2 + str.substring(i);
        }
        return String.valueOf(c2);
    }

    public static String a(String str) {
        char charAt;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            charAt = str.charAt(i);
            if (i >= str.length() - 1 || Character.isLetter(charAt)) {
                break;
            }
            sb.append(charAt);
            i++;
        }
        if (i == str.length()) {
            return sb.toString();
        }
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        sb.append(a(Character.toUpperCase(charAt), str, i + 1));
        return sb.toString();
    }

    public static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    public static d valueOf(String str) {
        return (d) Enum.valueOf(d.class, str);
    }

    public static d[] values() {
        return (d[]) f.clone();
    }
}
