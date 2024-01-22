package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Map;
import kotlin.jvm.internal.CharCompanionObject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class ArrayBasedUnicodeEscaper extends UnicodeEscaper {
    public final char[][] b;
    public final int c;
    public final int d;
    public final int e;
    public final char f;
    public final char g;

    public ArrayBasedUnicodeEscaper(Map<Character, String> map, int i, int i2, @NullableDecl String str) {
        this(ArrayBasedEscaperMap.create(map), i, i2, str);
    }

    @Override // com.google.common.escape.UnicodeEscaper, com.google.common.escape.Escaper
    public final String escape(String str) {
        Preconditions.checkNotNull(str);
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ((charAt < this.c && this.b[charAt] != null) || charAt > this.g || charAt < this.f) {
                return escapeSlow(str, i);
            }
        }
        return str;
    }

    public abstract char[] escapeUnsafe(int i);

    @Override // com.google.common.escape.UnicodeEscaper
    public final int nextEscapeIndex(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if ((charAt < this.c && this.b[charAt] != null) || charAt > this.g || charAt < this.f) {
                break;
            }
            i++;
        }
        return i;
    }

    public ArrayBasedUnicodeEscaper(ArrayBasedEscaperMap arrayBasedEscaperMap, int i, int i2, @NullableDecl String str) {
        Preconditions.checkNotNull(arrayBasedEscaperMap);
        char[][] b = arrayBasedEscaperMap.b();
        this.b = b;
        this.c = b.length;
        if (i2 < i) {
            i2 = -1;
            i = Integer.MAX_VALUE;
        }
        this.d = i;
        this.e = i2;
        if (i >= 55296) {
            this.f = CharCompanionObject.MAX_VALUE;
            this.g = (char) 0;
            return;
        }
        this.f = (char) i;
        this.g = (char) Math.min(i2, 55295);
    }

    @Override // com.google.common.escape.UnicodeEscaper
    public final char[] escape(int i) {
        char[] cArr;
        if (i >= this.c || (cArr = this.b[i]) == null) {
            if (i < this.d || i > this.e) {
                return escapeUnsafe(i);
            }
            return null;
        }
        return cArr;
    }
}
