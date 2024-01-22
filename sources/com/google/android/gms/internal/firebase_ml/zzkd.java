package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public abstract class zzkd extends zzjz {
    public static char[] a(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        }
        return cArr2;
    }

    public abstract int zza(CharSequence charSequence, int i, int i2);

    public final String zza(String str, int i) {
        int length = str.length();
        char[] a2 = j1.a();
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            if (i < length) {
                int i4 = i + 1;
                char charAt = str.charAt(i);
                char c = charAt;
                if (charAt >= 55296) {
                    c = charAt;
                    if (charAt <= 57343) {
                        if (charAt > 56319) {
                            StringBuilder sb = new StringBuilder(82);
                            sb.append("Unexpected low surrogate character '");
                            sb.append(charAt);
                            sb.append("' with value ");
                            sb.append((int) charAt);
                            sb.append(" at index ");
                            sb.append(i4 - 1);
                            throw new IllegalArgumentException(sb.toString());
                        } else if (i4 == length) {
                            c = -charAt;
                        } else {
                            char charAt2 = str.charAt(i4);
                            if (Character.isLowSurrogate(charAt2)) {
                                c = Character.toCodePoint(charAt, charAt2);
                            } else {
                                StringBuilder sb2 = new StringBuilder(83);
                                sb2.append("Expected low surrogate but got char '");
                                sb2.append(charAt2);
                                sb2.append("' with value ");
                                sb2.append((int) charAt2);
                                sb2.append(" at index ");
                                sb2.append(i4);
                                throw new IllegalArgumentException(sb2.toString());
                            }
                        }
                    }
                }
                if (c >= 0) {
                    char[] zzam = zzam(c);
                    int i5 = (Character.isSupplementaryCodePoint(c) ? 2 : 1) + i;
                    if (zzam != null) {
                        int i6 = i - i2;
                        int i7 = i3 + i6;
                        int length2 = zzam.length + i7;
                        if (a2.length < length2) {
                            a2 = a(a2, i3, ((length2 + length) - i) + 32);
                        }
                        if (i6 > 0) {
                            str.getChars(i2, i, a2, i3);
                            i3 = i7;
                        }
                        if (zzam.length > 0) {
                            System.arraycopy(zzam, 0, a2, i3, zzam.length);
                            i3 += zzam.length;
                        }
                        i2 = i5;
                    }
                    i = zza(str, i5, length);
                } else {
                    throw new IllegalArgumentException("Trailing high surrogate at end of input");
                }
            } else {
                throw new IndexOutOfBoundsException("Index exceeds specified range");
            }
        }
        int i8 = length - i2;
        if (i8 > 0) {
            int i9 = i8 + i3;
            if (a2.length < i9) {
                a2 = a(a2, i3, i9);
            }
            str.getChars(i2, length, a2, i3);
            i3 = i9;
        }
        return new String(a2, 0, i3);
    }

    public abstract char[] zzam(int i);
}
