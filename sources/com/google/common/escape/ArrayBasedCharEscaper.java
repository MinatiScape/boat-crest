package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Map;
import kotlin.jvm.internal.CharCompanionObject;
@Beta
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class ArrayBasedCharEscaper extends CharEscaper {
    public final char[][] b;
    public final int c;
    public final char d;
    public final char e;

    public ArrayBasedCharEscaper(Map<Character, String> map, char c, char c2) {
        this(ArrayBasedEscaperMap.create(map), c, c2);
    }

    @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
    public final String escape(String str) {
        Preconditions.checkNotNull(str);
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ((charAt < this.c && this.b[charAt] != null) || charAt > this.e || charAt < this.d) {
                return escapeSlow(str, i);
            }
        }
        return str;
    }

    public abstract char[] escapeUnsafe(char c);

    public ArrayBasedCharEscaper(ArrayBasedEscaperMap arrayBasedEscaperMap, char c, char c2) {
        Preconditions.checkNotNull(arrayBasedEscaperMap);
        char[][] b = arrayBasedEscaperMap.b();
        this.b = b;
        this.c = b.length;
        if (c2 < c) {
            c2 = 0;
            c = CharCompanionObject.MAX_VALUE;
        }
        this.d = c;
        this.e = c2;
    }

    @Override // com.google.common.escape.CharEscaper
    public final char[] escape(char c) {
        char[] cArr;
        if (c >= this.c || (cArr = this.b[c]) == null) {
            if (c < this.d || c > this.e) {
                return escapeUnsafe(c);
            }
            return null;
        }
        return cArr;
    }
}
