package com.google.iot.cbor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import kotlin.UShort;
/* loaded from: classes10.dex */
public class g implements CborReader {
    public static final Logger d = Logger.getLogger(CborReader.class.getCanonicalName());

    /* renamed from: a  reason: collision with root package name */
    public final k f11525a;
    public int b;
    public int c;

    public g(k kVar, int i) {
        this.c = -1;
        this.f11525a = kVar;
        this.b = i;
    }

    @Override // com.google.iot.cbor.CborReader
    public long bytesParsed() {
        return this.f11525a.bytesParsed();
    }

    @Override // com.google.iot.cbor.CborReader
    public boolean hasRemainingDataItems() {
        try {
            int i = this.b;
            if (i >= 0) {
                return i != 0;
            }
            if (this.f11525a.e()) {
                if (this.f11525a.peek() != -1) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.google.iot.cbor.CborReader
    public CborObject readDataItem() throws CborParseException, IOException {
        long j;
        int a2;
        if (hasRemainingDataItems()) {
            int i = this.c;
            this.c = -1;
            try {
                byte b = this.f11525a.get();
                int i2 = (b & 255) >> 5;
                byte b2 = (byte) (b & 31);
                if (b2 < 24) {
                    j = b2;
                } else {
                    if (b2 == 24) {
                        a2 = this.f11525a.get() & 255;
                    } else if (b2 == 25) {
                        a2 = this.f11525a.a() & UShort.MAX_VALUE;
                    } else if (b2 == 26) {
                        j = this.f11525a.f() & 4294967295L;
                    } else {
                        if (b2 == 27) {
                            j = this.f11525a.c();
                            if (j < 0 && i2 != 7) {
                                String format = String.format(Locale.ENGLISH, "Additional data value was too large: 0x%X", Long.valueOf(j));
                                if (i2 == 6) {
                                    d.warning(format + ", ignoring tag");
                                } else {
                                    d.warning(format + ", stopping");
                                    throw new CborParseException(format);
                                }
                            }
                        } else if (b2 != 31) {
                            throw new CborParseException("Undefined additional info value " + ((int) b2) + " for major type " + i2);
                        }
                        j = -1;
                    }
                    j = a2;
                }
                switch (i2) {
                    case 0:
                        if (j >= 0) {
                            int i3 = this.b;
                            if (i3 != -1) {
                                this.b = i3 - 1;
                            }
                            return CborInteger.create(j, i);
                        }
                        throw new CborParseException();
                    case 1:
                        if (j >= 0) {
                            int i4 = this.b;
                            if (i4 != -1) {
                                this.b = i4 - 1;
                            }
                            return CborInteger.create((-1) - j, i);
                        }
                        throw new CborParseException();
                    case 2:
                        if (j < 0) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            g gVar = new g(this.f11525a, (int) j);
                            while (gVar.hasRemainingDataItems()) {
                                CborObject readDataItem = gVar.readDataItem();
                                if ((readDataItem instanceof CborByteString) && readDataItem.getMajorType() == 2) {
                                    byteArrayOutputStream.write(((CborByteString) readDataItem).byteArrayValue());
                                } else {
                                    throw new CborParseException("Unexpected major type in byte string stream");
                                }
                            }
                            int i5 = this.b;
                            if (i5 != -1) {
                                this.b = i5 - 1;
                            }
                            if (this.f11525a.get() == -1) {
                                return CborByteString.create(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size(), i);
                            }
                            throw new CborParseException("Missing break");
                        }
                        int i6 = (int) j;
                        byte[] bArr = new byte[i6];
                        this.f11525a.d(bArr);
                        int i7 = this.b;
                        if (i7 != -1) {
                            this.b = i7 - 1;
                        }
                        return CborByteString.create(bArr, 0, i6, i);
                    case 3:
                        if (j < 0) {
                            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                            g gVar2 = new g(this.f11525a, (int) j);
                            while (gVar2.hasRemainingDataItems()) {
                                CborObject readDataItem2 = gVar2.readDataItem();
                                if (readDataItem2 instanceof CborTextString) {
                                    byteArrayOutputStream2.write(((CborTextString) readDataItem2).byteArrayValue());
                                } else {
                                    throw new CborParseException("Unexpected major type in text string stream");
                                }
                            }
                            int i8 = this.b;
                            if (i8 != -1) {
                                this.b = i8 - 1;
                            }
                            if (this.f11525a.get() == -1) {
                                return CborTextString.create(byteArrayOutputStream2.toByteArray(), 0, byteArrayOutputStream2.size(), i);
                            }
                            throw new CborParseException("Missing break");
                        }
                        int i9 = (int) j;
                        byte[] bArr2 = new byte[i9];
                        this.f11525a.d(bArr2);
                        int i10 = this.b;
                        if (i10 != -1) {
                            this.b = i10 - 1;
                        }
                        return CborTextString.create(bArr2, 0, i9, i);
                    case 4:
                        CborArray create = CborArray.create(i);
                        g gVar3 = new g(this.f11525a, (int) j);
                        while (gVar3.hasRemainingDataItems()) {
                            create.add(gVar3.readDataItem());
                        }
                        int i11 = this.b;
                        if (i11 != -1) {
                            this.b = i11 - 1;
                        }
                        if (j == -1 && this.f11525a.get() != -1) {
                            throw new CborParseException("Missing break");
                        }
                        return create;
                    case 5:
                        CborMap create2 = CborMap.create(i);
                        if (j != -1) {
                            j *= 2;
                        }
                        g gVar4 = new g(this.f11525a, (int) j);
                        while (gVar4.hasRemainingDataItems()) {
                            create2.mapValue().put(gVar4.readDataItem(), gVar4.readDataItem());
                        }
                        if (j == -1 && this.f11525a.get() != -1) {
                            throw new CborParseException("Missing break");
                        }
                        int i12 = this.b;
                        if (i12 != -1) {
                            this.b = i12 - 1;
                        }
                        return create2;
                    case 6:
                        if (CborTag.isValid(j)) {
                            this.c = (int) j;
                        } else {
                            d.warning("Ignoring invalid tag: " + j);
                        }
                        return readDataItem();
                    case 7:
                        if (b2 == 25) {
                            int i13 = this.b;
                            if (i13 != -1) {
                                this.b = i13 - 1;
                            }
                            return CborFloat.createHalf(q.b((short) j), i);
                        } else if (b2 == 26) {
                            int i14 = this.b;
                            if (i14 != -1) {
                                this.b = i14 - 1;
                            }
                            return CborFloat.create(Float.intBitsToFloat((int) j), i);
                        } else if (b2 == 27) {
                            int i15 = this.b;
                            if (i15 != -1) {
                                this.b = i15 - 1;
                            }
                            return CborFloat.create(Double.longBitsToDouble(j), i);
                        } else {
                            int i16 = this.b;
                            if (i16 != -1) {
                                this.b = i16 - 1;
                            }
                            return CborSimple.create((int) j, i);
                        }
                    default:
                        throw new CborParseException("Invalid major type value " + i2);
                }
            } catch (EOFException | IllegalArgumentException | BufferUnderflowException | NoSuchElementException e) {
                throw new CborParseException("CBOR data is truncated or corrupt", e);
            }
        }
        throw new NoSuchElementException();
    }

    public g(InputStream inputStream, int i) {
        this(k.b(inputStream), i);
    }

    public g(byte[] bArr, int i, int i2) {
        this(new ByteArrayInputStream(bArr, i, bArr.length - i), i2);
        if (i >= bArr.length) {
            throw new IndexOutOfBoundsException();
        }
    }
}
