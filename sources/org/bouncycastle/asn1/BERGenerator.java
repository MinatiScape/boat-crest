package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes12.dex */
public class BERGenerator extends ASN1Generator {

    /* renamed from: a  reason: collision with root package name */
    public boolean f14387a;
    public boolean b;
    public int c;

    public BERGenerator(OutputStream outputStream) {
        super(outputStream);
        this.f14387a = false;
    }

    public BERGenerator(OutputStream outputStream, int i, boolean z) {
        super(outputStream);
        this.f14387a = false;
        this.f14387a = true;
        this.b = z;
        this.c = i;
    }

    public final void a(int i) throws IOException {
        this._out.write(i);
        this._out.write(128);
    }

    @Override // org.bouncycastle.asn1.ASN1Generator
    public OutputStream getRawOutputStream() {
        return this._out;
    }

    public void writeBEREnd() throws IOException {
        this._out.write(0);
        this._out.write(0);
        if (this.f14387a && this.b) {
            this._out.write(0);
            this._out.write(0);
        }
    }

    public void writeBERHeader(int i) throws IOException {
        if (this.f14387a) {
            int i2 = this.c | 128;
            if (this.b) {
                a(i2 | 32);
            } else if ((i & 32) == 0) {
                a(i2);
                return;
            } else {
                i = i2 | 32;
            }
        }
        a(i);
    }
}
