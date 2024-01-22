package com.google.android.gms.internal.firebase_ml;

import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes7.dex */
public final class zzjy extends zzkd {
    public static final char[] c = {'+'};
    public static final char[] d = "0123456789ABCDEF".toCharArray();

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8784a;
    public final boolean[] b;

    public zzjy(String str, boolean z) {
        if (!str.matches(".*[0-9A-Za-z].*")) {
            if (z && str.contains(HexStringBuilder.DEFAULT_SEPARATOR)) {
                throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
            }
            if (!str.contains("%")) {
                this.f8784a = z;
                char[] charArray = str.toCharArray();
                int i = 122;
                for (char c2 : charArray) {
                    i = Math.max((int) c2, i);
                }
                boolean[] zArr = new boolean[i + 1];
                for (int i2 = 48; i2 <= 57; i2++) {
                    zArr[i2] = true;
                }
                for (int i3 = 65; i3 <= 90; i3++) {
                    zArr[i3] = true;
                }
                for (int i4 = 97; i4 <= 122; i4++) {
                    zArr[i4] = true;
                }
                for (char c3 : charArray) {
                    zArr[c3] = true;
                }
                this.b = zArr;
                return;
            }
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        }
        throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzkd
    public final int zza(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            boolean[] zArr = this.b;
            if (charAt >= zArr.length || !zArr[charAt]) {
                break;
            }
            i++;
        }
        return i;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzkd
    public final char[] zzam(int i) {
        boolean[] zArr = this.b;
        if (i >= zArr.length || !zArr[i]) {
            if (i == 32 && this.f8784a) {
                return c;
            }
            if (i <= 127) {
                char[] cArr = d;
                return new char[]{'%', cArr[i >>> 4], cArr[i & 15]};
            } else if (i <= 2047) {
                char[] cArr2 = d;
                char[] cArr3 = {'%', cArr2[(r14 >>> 4) | 12], cArr2[r14 & 15], '%', cArr2[(r14 & 3) | 8], cArr2[i & 15]};
                int i2 = i >>> 4;
                int i3 = i2 >>> 2;
                return cArr3;
            } else if (i <= 65535) {
                char[] cArr4 = d;
                char[] cArr5 = {'%', 'E', cArr4[r14 >>> 2], '%', cArr4[(r14 & 3) | 8], cArr4[r14 & 15], '%', cArr4[(r14 & 3) | 8], cArr4[i & 15]};
                int i4 = i >>> 4;
                int i5 = i4 >>> 2;
                int i6 = i5 >>> 4;
                return cArr5;
            } else if (i <= 1114111) {
                char[] cArr6 = d;
                char[] cArr7 = {'%', 'F', cArr6[(r14 >>> 2) & 7], '%', cArr6[(r14 & 3) | 8], cArr6[r14 & 15], '%', cArr6[(r14 & 3) | 8], cArr6[r14 & 15], '%', cArr6[(r14 & 3) | 8], cArr6[i & 15]};
                int i7 = i >>> 4;
                int i8 = i7 >>> 2;
                int i9 = i8 >>> 4;
                int i10 = i9 >>> 2;
                int i11 = i10 >>> 4;
                return cArr7;
            } else {
                StringBuilder sb = new StringBuilder(43);
                sb.append("Invalid unicode character value ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjz
    public final String zzaw(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            boolean[] zArr = this.b;
            if (charAt >= zArr.length || !zArr[charAt]) {
                return zza(str, i);
            }
        }
        return str;
    }
}
