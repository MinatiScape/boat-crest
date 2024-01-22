package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes12.dex */
public class ASN1StreamParser {

    /* renamed from: a  reason: collision with root package name */
    public final InputStream f14386a;
    public final int b;
    public final byte[][] c;

    public ASN1StreamParser(InputStream inputStream) {
        this(inputStream, i.c(inputStream));
    }

    public ASN1StreamParser(InputStream inputStream, int i) {
        this.f14386a = inputStream;
        this.b = i;
        this.c = new byte[11];
    }

    public ASN1StreamParser(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public ASN1Encodable a(boolean z, int i) throws IOException {
        InputStream inputStream = this.f14386a;
        if (inputStream instanceof e) {
            if (z) {
                return b(i);
            }
            throw new IOException("indefinite-length primitive encoding encountered");
        }
        if (z) {
            if (i == 4) {
                return new BEROctetStringParser(this);
            }
            if (i == 16) {
                return new DERSequenceParser(this);
            }
            if (i == 17) {
                return new DERSetParser(this);
            }
        } else if (i == 4) {
            return new DEROctetStringParser((d) inputStream);
        } else {
            if (i == 16) {
                throw new ASN1Exception("sets must use constructed encoding (see X.690 8.11.1/8.12.1)");
            }
            if (i == 17) {
                throw new ASN1Exception("sequences must use constructed encoding (see X.690 8.9.1/8.10.1)");
            }
        }
        throw new ASN1Exception("implicit tagging not implemented");
    }

    public ASN1Encodable b(int i) throws IOException {
        if (i != 4) {
            if (i != 8) {
                if (i != 16) {
                    if (i == 17) {
                        return new BERSetParser(this);
                    }
                    throw new ASN1Exception("unknown BER object encountered: 0x" + Integer.toHexString(i));
                }
                return new BERSequenceParser(this);
            }
            return new DERExternalParser(this);
        }
        return new BEROctetStringParser(this);
    }

    public ASN1Primitive c(boolean z, int i) throws IOException {
        if (z) {
            ASN1EncodableVector d = d();
            return this.f14386a instanceof e ? d.size() == 1 ? new BERTaggedObject(true, i, d.get(0)) : new BERTaggedObject(false, i, a.a(d)) : d.size() == 1 ? new DERTaggedObject(true, i, d.get(0)) : new DERTaggedObject(false, i, c.a(d));
        }
        return new DERTaggedObject(false, i, new DEROctetString(((d) this.f14386a).c()));
    }

    public ASN1EncodableVector d() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            ASN1Encodable readObject = readObject();
            if (readObject == null) {
                return aSN1EncodableVector;
            }
            aSN1EncodableVector.add(readObject instanceof InMemoryRepresentable ? ((InMemoryRepresentable) readObject).getLoadedObject() : readObject.toASN1Primitive());
        }
    }

    public final void e(boolean z) {
        InputStream inputStream = this.f14386a;
        if (inputStream instanceof e) {
            ((e) inputStream).d(z);
        }
    }

    public ASN1Encodable readObject() throws IOException {
        int read = this.f14386a.read();
        if (read == -1) {
            return null;
        }
        e(false);
        int h = ASN1InputStream.h(this.f14386a, read);
        boolean z = (read & 32) != 0;
        int g = ASN1InputStream.g(this.f14386a, this.b);
        if (g < 0) {
            if (z) {
                ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new e(this.f14386a, this.b), this.b);
                return (read & 64) != 0 ? new BERApplicationSpecificParser(h, aSN1StreamParser) : (read & 128) != 0 ? new BERTaggedObjectParser(true, h, aSN1StreamParser) : aSN1StreamParser.b(h);
            }
            throw new IOException("indefinite-length primitive encoding encountered");
        }
        d dVar = new d(this.f14386a, g);
        if ((read & 64) != 0) {
            return new DERApplicationSpecific(z, h, dVar.c());
        }
        if ((read & 128) != 0) {
            return new BERTaggedObjectParser(z, h, new ASN1StreamParser(dVar));
        }
        if (!z) {
            if (h != 4) {
                try {
                    return ASN1InputStream.c(h, dVar, this.c);
                } catch (IllegalArgumentException e) {
                    throw new ASN1Exception("corrupted stream detected", e);
                }
            }
            return new DEROctetStringParser(dVar);
        } else if (h != 4) {
            if (h != 8) {
                if (h != 16) {
                    if (h == 17) {
                        return new DERSetParser(new ASN1StreamParser(dVar));
                    }
                    throw new IOException("unknown tag " + h + " encountered");
                }
                return new DERSequenceParser(new ASN1StreamParser(dVar));
            }
            return new DERExternalParser(new ASN1StreamParser(dVar));
        } else {
            return new BEROctetStringParser(new ASN1StreamParser(dVar));
        }
    }
}
