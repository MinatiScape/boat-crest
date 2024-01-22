package com.alibaba.fastjson;

import org.apache.commons.codec.language.Soundex;
/* loaded from: classes.dex */
public enum PropertyNamingStrategy {
    CamelCase,
    PascalCase,
    SnakeCase,
    KebabCase;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2112a;

        static {
            int[] iArr = new int[PropertyNamingStrategy.values().length];
            f2112a = iArr;
            try {
                iArr[PropertyNamingStrategy.SnakeCase.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2112a[PropertyNamingStrategy.KebabCase.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2112a[PropertyNamingStrategy.PascalCase.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2112a[PropertyNamingStrategy.CamelCase.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public String translate(String str) {
        char charAt;
        int i = a.f2112a[ordinal()];
        int i2 = 0;
        if (i == 1) {
            StringBuilder sb = new StringBuilder();
            while (i2 < str.length()) {
                char charAt2 = str.charAt(i2);
                if (charAt2 >= 'A' && charAt2 <= 'Z') {
                    char c = (char) (charAt2 + ' ');
                    if (i2 > 0) {
                        sb.append('_');
                    }
                    sb.append(c);
                } else {
                    sb.append(charAt2);
                }
                i2++;
            }
            return sb.toString();
        } else if (i == 2) {
            StringBuilder sb2 = new StringBuilder();
            while (i2 < str.length()) {
                char charAt3 = str.charAt(i2);
                if (charAt3 >= 'A' && charAt3 <= 'Z') {
                    char c2 = (char) (charAt3 + ' ');
                    if (i2 > 0) {
                        sb2.append(Soundex.SILENT_MARKER);
                    }
                    sb2.append(c2);
                } else {
                    sb2.append(charAt3);
                }
                i2++;
            }
            return sb2.toString();
        } else if (i != 3) {
            if (i == 4 && (charAt = str.charAt(0)) >= 'A' && charAt <= 'Z') {
                char[] charArray = str.toCharArray();
                charArray[0] = (char) (charArray[0] + ' ');
                return new String(charArray);
            }
            return str;
        } else {
            char charAt4 = str.charAt(0);
            if (charAt4 < 'a' || charAt4 > 'z') {
                return str;
            }
            char[] charArray2 = str.toCharArray();
            charArray2[0] = (char) (charArray2[0] - ' ');
            return new String(charArray2);
        }
    }
}