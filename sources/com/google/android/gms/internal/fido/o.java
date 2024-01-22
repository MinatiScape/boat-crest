package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.math.RoundingMode;
import javax.annotation.CheckForNull;
/* loaded from: classes7.dex */
public class o extends zzbf {
    public final l b;
    @CheckForNull
    public final Character c;

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public o(com.google.android.gms.internal.fido.l r4, @javax.annotation.CheckForNull java.lang.Character r5) {
        /*
            r3 = this;
            r3.<init>()
            r3.b = r4
            r0 = 0
            r1 = 1
            if (r5 == 0) goto L17
            r5.charValue()
            r2 = 61
            boolean r4 = r4.b(r2)
            if (r4 != 0) goto L15
            goto L17
        L15:
            r4 = r0
            goto L18
        L17:
            r4 = r1
        L18:
            if (r4 == 0) goto L1d
            r3.c = r5
            return
        L1d:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r0] = r5
            java.lang.String r5 = "Padding character %s was already in alphabet"
            java.lang.String r5 = com.google.android.gms.internal.fido.zzan.zza(r5, r1)
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.o.<init>(com.google.android.gms.internal.fido.l, java.lang.Character):void");
    }

    @Override // com.google.android.gms.internal.fido.zzbf
    public void a(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzam.zze(0, i2, bArr.length);
        while (i3 < i2) {
            c(appendable, bArr, i3, Math.min(this.b.f, i2 - i3));
            i3 += this.b.f;
        }
    }

    @Override // com.google.android.gms.internal.fido.zzbf
    public final int b(int i) {
        l lVar = this.b;
        return lVar.e * zzbh.zza(i, lVar.f, RoundingMode.CEILING);
    }

    public final void c(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzam.zze(i, i + i2, bArr.length);
        int i3 = 0;
        zzam.zzc(i2 <= this.b.f);
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | (bArr[i + i4] & 255)) << 8;
        }
        int i5 = ((i2 + 1) * 8) - this.b.d;
        while (i3 < i2 * 8) {
            l lVar = this.b;
            appendable.append(lVar.a(lVar.c & ((int) (j >>> (i5 - i3)))));
            i3 += this.b.d;
        }
        if (this.c != null) {
            while (i3 < this.b.f * 8) {
                this.c.charValue();
                appendable.append('=');
                i3 += this.b.d;
            }
        }
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof o) {
            o oVar = (o) obj;
            if (this.b.equals(oVar.b)) {
                Character ch = this.c;
                Character ch2 = oVar.c;
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
        int hashCode = this.b.hashCode();
        Character ch = this.c;
        return hashCode ^ (ch == null ? 0 : ch.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.b);
        if (8 % this.b.d != 0) {
            if (this.c == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.c);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    public o(String str, String str2, @CheckForNull Character ch) {
        this(new l(str, str2.toCharArray()), ch);
    }
}
