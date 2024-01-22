package org.bouncycastle.crypto.tls;
/* loaded from: classes13.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    public long f14870a = -1;
    public long b = 0;

    public void a(long j) {
        if ((281474976710655L & j) != j) {
            throw new IllegalArgumentException("'seq' out of range");
        }
        long j2 = this.f14870a;
        if (j <= j2) {
            long j3 = j2 - j;
            if (j3 < 64) {
                this.b |= 1 << ((int) j3);
                return;
            }
            return;
        }
        long j4 = j - j2;
        if (j4 >= 64) {
            this.b = 1L;
        } else {
            long j5 = this.b << ((int) j4);
            this.b = j5;
            this.b = j5 | 1;
        }
        this.f14870a = j;
    }

    public boolean b(long j) {
        if ((281474976710655L & j) != j) {
            return true;
        }
        long j2 = this.f14870a;
        if (j <= j2) {
            long j3 = j2 - j;
            return j3 >= 64 || (this.b & (1 << ((int) j3))) != 0;
        }
        return false;
    }
}
