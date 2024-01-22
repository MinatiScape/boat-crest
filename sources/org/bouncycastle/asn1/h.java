package org.bouncycastle.asn1;

import java.io.InputStream;
/* loaded from: classes12.dex */
public abstract class h extends InputStream {
    public final InputStream h;
    public int i;

    public h(InputStream inputStream, int i) {
        this.h = inputStream;
        this.i = i;
    }

    public int a() {
        return this.i;
    }

    public void b(boolean z) {
        InputStream inputStream = this.h;
        if (inputStream instanceof e) {
            ((e) inputStream).d(z);
        }
    }
}
