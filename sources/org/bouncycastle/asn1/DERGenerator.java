package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes12.dex */
public abstract class DERGenerator extends ASN1Generator {

    /* renamed from: a  reason: collision with root package name */
    public boolean f14389a;
    public boolean b;
    public int c;

    public DERGenerator(OutputStream outputStream) {
        super(outputStream);
        this.f14389a = false;
    }

    public DERGenerator(OutputStream outputStream, int i, boolean z) {
        super(outputStream);
        this.f14389a = false;
        this.f14389a = true;
        this.b = z;
        this.c = i;
    }

    public void a(int i, byte[] bArr) throws IOException {
        if (!this.f14389a) {
            b(this._out, i, bArr);
            return;
        }
        int i2 = this.c;
        int i3 = i2 | 128;
        if (this.b) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            b(byteArrayOutputStream, i, bArr);
            b(this._out, i2 | 32 | 128, byteArrayOutputStream.toByteArray());
        } else if ((i & 32) != 0) {
            b(this._out, i3 | 32, bArr);
        } else {
            b(this._out, i3, bArr);
        }
    }

    public void b(OutputStream outputStream, int i, byte[] bArr) throws IOException {
        outputStream.write(i);
        c(outputStream, bArr.length);
        outputStream.write(bArr);
    }

    public final void c(OutputStream outputStream, int i) throws IOException {
        if (i <= 127) {
            outputStream.write((byte) i);
            return;
        }
        int i2 = i;
        int i3 = 1;
        while (true) {
            i2 >>>= 8;
            if (i2 == 0) {
                break;
            }
            i3++;
        }
        outputStream.write((byte) (i3 | 128));
        for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
            outputStream.write((byte) (i >> i4));
        }
    }
}
