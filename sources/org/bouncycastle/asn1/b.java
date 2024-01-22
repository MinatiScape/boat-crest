package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes12.dex */
public class b extends InputStream {
    public final ASN1StreamParser h;
    public boolean i = true;
    public InputStream j;

    public b(ASN1StreamParser aSN1StreamParser) {
        this.h = aSN1StreamParser;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        ASN1OctetStringParser aSN1OctetStringParser;
        if (this.j == null) {
            if (!this.i || (aSN1OctetStringParser = (ASN1OctetStringParser) this.h.readObject()) == null) {
                return -1;
            }
            this.i = false;
            this.j = aSN1OctetStringParser.getOctetStream();
        }
        while (true) {
            int read = this.j.read();
            if (read >= 0) {
                return read;
            }
            ASN1OctetStringParser aSN1OctetStringParser2 = (ASN1OctetStringParser) this.h.readObject();
            if (aSN1OctetStringParser2 == null) {
                this.j = null;
                return -1;
            }
            this.j = aSN1OctetStringParser2.getOctetStream();
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        ASN1OctetStringParser aSN1OctetStringParser;
        int i3 = 0;
        if (this.j == null) {
            if (!this.i || (aSN1OctetStringParser = (ASN1OctetStringParser) this.h.readObject()) == null) {
                return -1;
            }
            this.i = false;
            this.j = aSN1OctetStringParser.getOctetStream();
        }
        while (true) {
            int read = this.j.read(bArr, i + i3, i2 - i3);
            if (read >= 0) {
                i3 += read;
                if (i3 == i2) {
                    return i3;
                }
            } else {
                ASN1OctetStringParser aSN1OctetStringParser2 = (ASN1OctetStringParser) this.h.readObject();
                if (aSN1OctetStringParser2 == null) {
                    this.j = null;
                    if (i3 < 1) {
                        return -1;
                    }
                    return i3;
                }
                this.j = aSN1OctetStringParser2.getOctetStream();
            }
        }
    }
}
