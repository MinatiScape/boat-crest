package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes10.dex */
class zzej extends zzek {
    public final zzef zzb;
    @CheckForNull
    public final Character zzc;

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public zzej(com.google.android.recaptcha.internal.zzef r4, @javax.annotation.CheckForNull java.lang.Character r5) {
        /*
            r3 = this;
            r3.<init>()
            r3.zzb = r4
            r0 = 0
            r1 = 1
            if (r5 == 0) goto L17
            r5.charValue()
            r2 = 61
            boolean r4 = r4.zzd(r2)
            if (r4 != 0) goto L15
            goto L17
        L15:
            r4 = r0
            goto L18
        L17:
            r4 = r1
        L18:
            if (r4 == 0) goto L1d
            r3.zzc = r5
            return
        L1d:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r0] = r5
            java.lang.String r5 = "Padding character %s was already in alphabet"
            java.lang.String r5 = com.google.android.recaptcha.internal.zzdu.zza(r5, r1)
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzej.<init>(com.google.android.recaptcha.internal.zzef, java.lang.Character):void");
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzej) {
            zzej zzejVar = (zzej) obj;
            if (this.zzb.equals(zzejVar.zzb)) {
                Character ch = this.zzc;
                Character ch2 = zzejVar.zzc;
                if (ch == ch2) {
                    return true;
                }
                if (ch != null && ch.equals(ch2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.zzb.hashCode();
        Character ch = this.zzc;
        return hashCode ^ (ch == null ? 0 : ch.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.zzb);
        if (8 % this.zzb.zzb != 0) {
            if (this.zzc == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.zzc);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    @Override // com.google.android.recaptcha.internal.zzek
    public int zza(byte[] bArr, CharSequence charSequence) throws zzei {
        zzef zzefVar;
        Objects.requireNonNull(bArr);
        CharSequence zze = zze(charSequence);
        if (this.zzb.zzc(zze.length())) {
            int i = 0;
            int i2 = 0;
            while (i < zze.length()) {
                long j = 0;
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    zzefVar = this.zzb;
                    if (i3 >= zzefVar.zzc) {
                        break;
                    }
                    j <<= zzefVar.zzb;
                    if (i + i3 < zze.length()) {
                        j |= this.zzb.zzb(zze.charAt(i4 + i));
                        i4++;
                    }
                    i3++;
                }
                int i5 = zzefVar.zzd;
                int i6 = i5 * 8;
                int i7 = i4 * zzefVar.zzb;
                int i8 = (i5 - 1) * 8;
                while (i8 >= i6 - i7) {
                    bArr[i2] = (byte) ((j >>> i8) & 255);
                    i8 -= 8;
                    i2++;
                }
                i += this.zzb.zzc;
            }
            return i2;
        }
        throw new zzei("Invalid input length " + zze.length());
    }

    @Override // com.google.android.recaptcha.internal.zzek
    public void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzdr.zzd(0, i2, bArr.length);
        while (i3 < i2) {
            zzf(appendable, bArr, i3, Math.min(this.zzb.zzd, i2 - i3));
            i3 += this.zzb.zzd;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzek
    public final int zzc(int i) {
        return (int) (((this.zzb.zzb * i) + 7) / 8);
    }

    @Override // com.google.android.recaptcha.internal.zzek
    public final int zzd(int i) {
        zzef zzefVar = this.zzb;
        return zzefVar.zzc * zzem.zza(i, zzefVar.zzd, RoundingMode.CEILING);
    }

    @Override // com.google.android.recaptcha.internal.zzek
    public final CharSequence zze(CharSequence charSequence) {
        Objects.requireNonNull(charSequence);
        Character ch = this.zzc;
        if (ch == null) {
            return charSequence;
        }
        ch.charValue();
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                break;
            }
        } while (charSequence.charAt(length) == '=');
        return charSequence.subSequence(0, length + 1);
    }

    public final void zzf(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzdr.zzd(i, i + i2, bArr.length);
        int i3 = 0;
        zzdr.zza(i2 <= this.zzb.zzd);
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | (bArr[i + i4] & 255)) << 8;
        }
        int i5 = ((i2 + 1) * 8) - this.zzb.zzb;
        while (i3 < i2 * 8) {
            zzef zzefVar = this.zzb;
            appendable.append(zzefVar.zza(zzefVar.zza & ((int) (j >>> (i5 - i3)))));
            i3 += this.zzb.zzb;
        }
        if (this.zzc != null) {
            while (i3 < this.zzb.zzd * 8) {
                this.zzc.charValue();
                appendable.append('=');
                i3 += this.zzb.zzb;
            }
        }
    }

    public zzej(String str, String str2, @CheckForNull Character ch) {
        this(new zzef(str, str2.toCharArray()), ch);
    }
}
