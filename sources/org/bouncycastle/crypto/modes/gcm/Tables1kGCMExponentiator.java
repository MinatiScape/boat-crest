package org.bouncycastle.crypto.modes.gcm;

import java.util.Vector;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class Tables1kGCMExponentiator implements GCMExponentiator {

    /* renamed from: a  reason: collision with root package name */
    public Vector f14782a;

    public final void a(int i) {
        int size = this.f14782a.size();
        if (size <= i) {
            long[] jArr = (long[]) this.f14782a.elementAt(size - 1);
            do {
                jArr = Arrays.clone(jArr);
                GCMUtil.square(jArr, jArr);
                this.f14782a.addElement(jArr);
                size++;
            } while (size <= i);
        }
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j, byte[] bArr) {
        long[] oneAsLongs = GCMUtil.oneAsLongs();
        int i = 0;
        while (j > 0) {
            if ((1 & j) != 0) {
                a(i);
                GCMUtil.multiply(oneAsLongs, (long[]) this.f14782a.elementAt(i));
            }
            i++;
            j >>>= 1;
        }
        GCMUtil.asBytes(oneAsLongs, bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        long[] asLongs = GCMUtil.asLongs(bArr);
        Vector vector = this.f14782a;
        if (vector == null || !Arrays.areEqual(asLongs, (long[]) vector.elementAt(0))) {
            Vector vector2 = new Vector(8);
            this.f14782a = vector2;
            vector2.addElement(asLongs);
        }
    }
}
