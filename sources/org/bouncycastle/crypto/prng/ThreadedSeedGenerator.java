package org.bouncycastle.crypto.prng;
/* loaded from: classes13.dex */
public class ThreadedSeedGenerator {

    /* loaded from: classes13.dex */
    public class b implements Runnable {
        public volatile int h;
        public volatile boolean i;

        public b(ThreadedSeedGenerator threadedSeedGenerator) {
            this.h = 0;
            this.i = false;
        }

        public byte[] a(int i, boolean z) {
            Thread thread = new Thread(this);
            byte[] bArr = new byte[i];
            this.h = 0;
            this.i = false;
            thread.start();
            if (!z) {
                i *= 8;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                while (this.h == i2) {
                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException unused) {
                    }
                }
                i2 = this.h;
                if (z) {
                    bArr[i3] = (byte) (i2 & 255);
                } else {
                    int i4 = i3 / 8;
                    bArr[i4] = (byte) ((bArr[i4] << 1) | (i2 & 1));
                }
            }
            this.i = true;
            return bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!this.i) {
                this.h++;
            }
        }
    }

    public byte[] generateSeed(int i, boolean z) {
        return new b().a(i, z);
    }
}
