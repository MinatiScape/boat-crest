package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes12.dex */
public class BEROctetStringGenerator extends BERGenerator {

    /* loaded from: classes12.dex */
    public class a extends OutputStream {
        public byte[] h;
        public int i = 0;
        public DEROutputStream j;

        public a(byte[] bArr) {
            this.h = bArr;
            this.j = new DEROutputStream(BEROctetStringGenerator.this._out);
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            int i = this.i;
            if (i != 0) {
                byte[] bArr = new byte[i];
                System.arraycopy(this.h, 0, bArr, 0, i);
                DEROctetString.d(this.j, bArr);
            }
            BEROctetStringGenerator.this.writeBEREnd();
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            byte[] bArr = this.h;
            int i2 = this.i;
            int i3 = i2 + 1;
            this.i = i3;
            bArr[i2] = (byte) i;
            if (i3 == bArr.length) {
                DEROctetString.d(this.j, bArr);
                this.i = 0;
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            while (i2 > 0) {
                int min = Math.min(i2, this.h.length - this.i);
                System.arraycopy(bArr, i, this.h, this.i, min);
                int i3 = this.i + min;
                this.i = i3;
                byte[] bArr2 = this.h;
                if (i3 < bArr2.length) {
                    return;
                }
                DEROctetString.d(this.j, bArr2);
                this.i = 0;
                i += min;
                i2 -= min;
            }
        }
    }

    public BEROctetStringGenerator(OutputStream outputStream) throws IOException {
        super(outputStream);
        writeBERHeader(36);
    }

    public BEROctetStringGenerator(OutputStream outputStream, int i, boolean z) throws IOException {
        super(outputStream, i, z);
        writeBERHeader(36);
    }

    public OutputStream getOctetOutputStream() {
        return getOctetOutputStream(new byte[1000]);
    }

    public OutputStream getOctetOutputStream(byte[] bArr) {
        return new a(bArr);
    }
}
