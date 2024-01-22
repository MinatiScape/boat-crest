package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
/* loaded from: classes.dex */
public class SymbolTable {

    /* renamed from: a  reason: collision with root package name */
    public final a[] f2121a;
    public final int b;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final String f2122a;
        public final char[] b;
        public final int c;

        public a(String str, int i) {
            this.f2122a = str;
            this.b = str.toCharArray();
            this.c = i;
        }
    }

    public SymbolTable(int i) {
        this.b = i - 1;
        this.f2121a = new a[i];
        addSymbol("$ref", 0, 4, 1185263);
        addSymbol(JSON.DEFAULT_TYPE_KEY, 0, 5, 62680954);
    }

    public static String a(String str, int i, int i2) {
        char[] cArr = new char[i2];
        str.getChars(i, i2 + i, cArr, 0);
        return new String(cArr);
    }

    public String addSymbol(char[] cArr, int i, int i2, int i3) {
        int i4 = this.b & i3;
        a aVar = this.f2121a[i4];
        if (aVar != null) {
            boolean z = false;
            if (i3 == aVar.c && i2 == aVar.b.length) {
                int i5 = 0;
                while (true) {
                    if (i5 >= i2) {
                        z = true;
                        break;
                    } else if (cArr[i + i5] != aVar.b[i5]) {
                        break;
                    } else {
                        i5++;
                    }
                }
            }
            if (z) {
                return aVar.f2122a;
            }
            return new String(cArr, i, i2);
        }
        String intern = new String(cArr, i, i2).intern();
        this.f2121a[i4] = new a(intern, i3);
        return intern;
    }

    public String addSymbol(String str, int i, int i2, int i3) {
        int i4 = this.b & i3;
        a aVar = this.f2121a[i4];
        if (aVar != null) {
            if (i3 == aVar.c && i2 == aVar.b.length && str.regionMatches(i, aVar.f2122a, 0, i2)) {
                return aVar.f2122a;
            }
            return a(str, i, i2);
        }
        if (i2 != str.length()) {
            str = a(str, i, i2);
        }
        String intern = str.intern();
        this.f2121a[i4] = new a(intern, i3);
        return intern;
    }
}
