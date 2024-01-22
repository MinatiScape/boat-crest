package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes12.dex */
public class ASN1InputStream extends FilterInputStream implements BERTags {
    public final int h;
    public final boolean i;
    public final byte[][] j;

    public ASN1InputStream(InputStream inputStream) {
        this(inputStream, i.c(inputStream));
    }

    public ASN1InputStream(InputStream inputStream, int i) {
        this(inputStream, i, false);
    }

    public ASN1InputStream(InputStream inputStream, int i, boolean z) {
        super(inputStream);
        this.h = i;
        this.i = z;
        this.j = new byte[11];
    }

    public ASN1InputStream(InputStream inputStream, boolean z) {
        this(inputStream, i.c(inputStream), z);
    }

    public ASN1InputStream(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public ASN1InputStream(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    public static ASN1Primitive c(int i, d dVar, byte[][] bArr) throws IOException {
        if (i != 10) {
            if (i != 12) {
                if (i != 30) {
                    switch (i) {
                        case 1:
                            return ASN1Boolean.d(e(dVar, bArr));
                        case 2:
                            return new ASN1Integer(dVar.c(), false);
                        case 3:
                            return ASN1BitString.d(dVar.a(), dVar);
                        case 4:
                            return new DEROctetString(dVar.c());
                        case 5:
                            return DERNull.INSTANCE;
                        case 6:
                            return ASN1ObjectIdentifier.e(e(dVar, bArr));
                        default:
                            switch (i) {
                                case 18:
                                    return new DERNumericString(dVar.c());
                                case 19:
                                    return new DERPrintableString(dVar.c());
                                case 20:
                                    return new DERT61String(dVar.c());
                                case 21:
                                    return new DERVideotexString(dVar.c());
                                case 22:
                                    return new DERIA5String(dVar.c());
                                case 23:
                                    return new ASN1UTCTime(dVar.c());
                                case 24:
                                    return new ASN1GeneralizedTime(dVar.c());
                                case 25:
                                    return new DERGraphicString(dVar.c());
                                case 26:
                                    return new DERVisibleString(dVar.c());
                                case 27:
                                    return new DERGeneralString(dVar.c());
                                case 28:
                                    return new DERUniversalString(dVar.c());
                                default:
                                    throw new IOException("unknown tag " + i + " encountered");
                            }
                    }
                }
                return new DERBMPString(d(dVar));
            }
            return new DERUTF8String(dVar.c());
        }
        return ASN1Enumerated.d(e(dVar, bArr));
    }

    public static char[] d(d dVar) throws IOException {
        int read;
        int a2 = dVar.a() / 2;
        char[] cArr = new char[a2];
        for (int i = 0; i < a2; i++) {
            int read2 = dVar.read();
            if (read2 < 0 || (read = dVar.read()) < 0) {
                break;
            }
            cArr[i] = (char) ((read2 << 8) | (read & 255));
        }
        return cArr;
    }

    public static byte[] e(d dVar, byte[][] bArr) throws IOException {
        int a2 = dVar.a();
        if (dVar.a() < bArr.length) {
            byte[] bArr2 = bArr[a2];
            if (bArr2 == null) {
                bArr2 = new byte[a2];
                bArr[a2] = bArr2;
            }
            Streams.readFully(dVar, bArr2);
            return bArr2;
        }
        return dVar.c();
    }

    public static int g(InputStream inputStream, int i) throws IOException {
        int read = inputStream.read();
        if (read >= 0) {
            if (read == 128) {
                return -1;
            }
            if (read > 127) {
                int i2 = read & 127;
                if (i2 > 4) {
                    throw new IOException("DER length more than 4 bytes: " + i2);
                }
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    int read2 = inputStream.read();
                    if (read2 < 0) {
                        throw new EOFException("EOF found reading length");
                    }
                    i3 = (i3 << 8) + read2;
                }
                if (i3 >= 0) {
                    if (i3 < i) {
                        return i3;
                    }
                    throw new IOException("corrupted stream - out of bounds length found");
                }
                throw new IOException("corrupted stream - negative length found");
            }
            return read;
        }
        throw new EOFException("EOF found when length expected");
    }

    public static int h(InputStream inputStream, int i) throws IOException {
        int i2 = i & 31;
        if (i2 == 31) {
            int i3 = 0;
            int read = inputStream.read();
            if ((read & 127) != 0) {
                while (read >= 0 && (read & 128) != 0) {
                    i3 = (i3 | (read & 127)) << 7;
                    read = inputStream.read();
                }
                if (read >= 0) {
                    return i3 | (read & 127);
                }
                throw new EOFException("EOF found inside tag value.");
            }
            throw new IOException("corrupted stream - invalid high tag number found");
        }
        return i2;
    }

    public ASN1EncodableVector a(d dVar) throws IOException {
        return new ASN1InputStream(dVar).b();
    }

    public ASN1EncodableVector b() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            ASN1Primitive readObject = readObject();
            if (readObject == null) {
                return aSN1EncodableVector;
            }
            aSN1EncodableVector.add(readObject);
        }
    }

    public ASN1Primitive buildObject(int i, int i2, int i3) throws IOException {
        boolean z = (i & 32) != 0;
        d dVar = new d(this, i3);
        if ((i & 64) != 0) {
            return new DERApplicationSpecific(z, i2, dVar.c());
        }
        if ((i & 128) != 0) {
            return new ASN1StreamParser(dVar).c(z, i2);
        }
        if (z) {
            if (i2 == 4) {
                ASN1EncodableVector a2 = a(dVar);
                int size = a2.size();
                ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[size];
                for (int i4 = 0; i4 != size; i4++) {
                    aSN1OctetStringArr[i4] = (ASN1OctetString) a2.get(i4);
                }
                return new BEROctetString(aSN1OctetStringArr);
            } else if (i2 != 8) {
                if (i2 == 16) {
                    return this.i ? new g(dVar.c()) : c.a(a(dVar));
                } else if (i2 == 17) {
                    return c.b(a(dVar));
                } else {
                    throw new IOException("unknown tag " + i2 + " encountered");
                }
            } else {
                return new DERExternal(a(dVar));
            }
        }
        return c(i2, dVar, this.j);
    }

    public int f() {
        return this.h;
    }

    public void readFully(byte[] bArr) throws IOException {
        if (Streams.readFully(this, bArr) != bArr.length) {
            throw new EOFException("EOF encountered in middle of object");
        }
    }

    public int readLength() throws IOException {
        return g(this, this.h);
    }

    public ASN1Primitive readObject() throws IOException {
        int read = read();
        if (read <= 0) {
            if (read != 0) {
                return null;
            }
            throw new IOException("unexpected end-of-contents marker");
        }
        int h = h(this, read);
        boolean z = (read & 32) != 0;
        int readLength = readLength();
        if (readLength >= 0) {
            try {
                return buildObject(read, h, readLength);
            } catch (IllegalArgumentException e) {
                throw new ASN1Exception("corrupted stream detected", e);
            }
        } else if (z) {
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new e(this, this.h), this.h);
            if ((read & 64) != 0) {
                return new BERApplicationSpecificParser(h, aSN1StreamParser).getLoadedObject();
            }
            if ((read & 128) != 0) {
                return new BERTaggedObjectParser(true, h, aSN1StreamParser).getLoadedObject();
            }
            if (h != 4) {
                if (h != 8) {
                    if (h != 16) {
                        if (h == 17) {
                            return new BERSetParser(aSN1StreamParser).getLoadedObject();
                        }
                        throw new IOException("unknown BER object encountered");
                    }
                    return new BERSequenceParser(aSN1StreamParser).getLoadedObject();
                }
                return new DERExternalParser(aSN1StreamParser).getLoadedObject();
            }
            return new BEROctetStringParser(aSN1StreamParser).getLoadedObject();
        } else {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
    }
}
