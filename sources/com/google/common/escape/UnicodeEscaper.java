package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
@Beta
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class UnicodeEscaper extends Escaper {
    public static char[] a(char[] cArr, int i, int i2) {
        if (i2 >= 0) {
            char[] cArr2 = new char[i2];
            if (i > 0) {
                System.arraycopy(cArr, 0, cArr2, 0, i);
            }
            return cArr2;
        }
        throw new AssertionError("Cannot increase internal buffer any further");
    }

    public static int codePointAt(CharSequence charSequence, int i, int i2) {
        Preconditions.checkNotNull(charSequence);
        if (i < i2) {
            int i3 = i + 1;
            char charAt = charSequence.charAt(i);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                String valueOf = String.valueOf(charSequence);
                StringBuilder sb = new StringBuilder(valueOf.length() + 88);
                sb.append("Unexpected low surrogate character '");
                sb.append(charAt);
                sb.append("' with value ");
                sb.append((int) charAt);
                sb.append(" at index ");
                sb.append(i3 - 1);
                sb.append(" in '");
                sb.append(valueOf);
                sb.append("'");
                throw new IllegalArgumentException(sb.toString());
            } else if (i3 == i2) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i3);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                String valueOf2 = String.valueOf(charSequence);
                StringBuilder sb2 = new StringBuilder(valueOf2.length() + 89);
                sb2.append("Expected low surrogate but got char '");
                sb2.append(charAt2);
                sb2.append("' with value ");
                sb2.append((int) charAt2);
                sb2.append(" at index ");
                sb2.append(i3);
                sb2.append(" in '");
                sb2.append(valueOf2);
                sb2.append("'");
                throw new IllegalArgumentException(sb2.toString());
            }
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    @Override // com.google.common.escape.Escaper
    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        int nextEscapeIndex = nextEscapeIndex(str, 0, length);
        return nextEscapeIndex == length ? str : escapeSlow(str, nextEscapeIndex);
    }

    public abstract char[] escape(int i);

    public final String escapeSlow(String str, int i) {
        int length = str.length();
        char[] a2 = a.a();
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int codePointAt = codePointAt(str, i, length);
            if (codePointAt >= 0) {
                char[] escape = escape(codePointAt);
                int i4 = (Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1) + i;
                if (escape != null) {
                    int i5 = i - i2;
                    int i6 = i3 + i5;
                    int length2 = escape.length + i6;
                    if (a2.length < length2) {
                        a2 = a(a2, i3, length2 + (length - i) + 32);
                    }
                    if (i5 > 0) {
                        str.getChars(i2, i, a2, i3);
                        i3 = i6;
                    }
                    if (escape.length > 0) {
                        System.arraycopy(escape, 0, a2, i3, escape.length);
                        i3 += escape.length;
                    }
                    i2 = i4;
                }
                i = nextEscapeIndex(str, i4, length);
            } else {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
        }
        int i7 = length - i2;
        if (i7 > 0) {
            int i8 = i7 + i3;
            if (a2.length < i8) {
                a2 = a(a2, i3, i8);
            }
            str.getChars(i2, length, a2, i3);
            i3 = i8;
        }
        return new String(a2, 0, i3);
    }

    public int nextEscapeIndex(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int codePointAt = codePointAt(charSequence, i, i2);
            if (codePointAt < 0 || escape(codePointAt) != null) {
                break;
            }
            i += Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1;
        }
        return i;
    }
}
