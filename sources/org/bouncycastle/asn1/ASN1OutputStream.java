package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes12.dex */
public class ASN1OutputStream {

    /* renamed from: a  reason: collision with root package name */
    public OutputStream f14385a;

    /* loaded from: classes12.dex */
    public class a extends ASN1OutputStream {
        public boolean b;

        public a(ASN1OutputStream aSN1OutputStream, OutputStream outputStream) {
            super(outputStream);
            this.b = true;
        }

        @Override // org.bouncycastle.asn1.ASN1OutputStream
        public void c(int i) throws IOException {
            if (this.b) {
                this.b = false;
            } else {
                super.c(i);
            }
        }
    }

    public ASN1OutputStream(OutputStream outputStream) {
        this.f14385a = outputStream;
    }

    public ASN1OutputStream a() {
        return new DEROutputStream(this.f14385a);
    }

    public ASN1OutputStream b() {
        return new DLOutputStream(this.f14385a);
    }

    public void c(int i) throws IOException {
        this.f14385a.write(i);
    }

    public void close() throws IOException {
        this.f14385a.close();
    }

    public void d(byte[] bArr) throws IOException {
        this.f14385a.write(bArr);
    }

    public void e(byte[] bArr, int i, int i2) throws IOException {
        this.f14385a.write(bArr, i, i2);
    }

    public void f(int i, int i2, byte[] bArr) throws IOException {
        j(i, i2);
        i(bArr.length);
        d(bArr);
    }

    public void flush() throws IOException {
        this.f14385a.flush();
    }

    public void g(int i, byte[] bArr) throws IOException {
        c(i);
        i(bArr.length);
        d(bArr);
    }

    public void h(ASN1Primitive aSN1Primitive) throws IOException {
        if (aSN1Primitive == null) {
            throw new IOException("null object detected");
        }
        aSN1Primitive.encode(new a(this, this.f14385a));
    }

    public void i(int i) throws IOException {
        if (i <= 127) {
            c((byte) i);
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
        c((byte) (i3 | 128));
        for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
            c((byte) (i >> i4));
        }
    }

    public void j(int i, int i2) throws IOException {
        if (i2 < 31) {
            c(i | i2);
            return;
        }
        c(i | 31);
        if (i2 < 128) {
            c(i2);
            return;
        }
        byte[] bArr = new byte[5];
        int i3 = 4;
        bArr[4] = (byte) (i2 & 127);
        do {
            i2 >>= 7;
            i3--;
            bArr[i3] = (byte) ((i2 & 127) | 128);
        } while (i2 > 127);
        e(bArr, i3, 5 - i3);
    }

    public void writeNull() throws IOException {
        this.f14385a.write(5);
        this.f14385a.write(0);
    }

    public void writeObject(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable == null) {
            throw new IOException("null object detected");
        }
        aSN1Encodable.toASN1Primitive().encode(this);
    }
}
