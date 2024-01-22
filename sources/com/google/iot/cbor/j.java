package com.google.iot.cbor;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
public class j implements CborWriter {

    /* renamed from: a  reason: collision with root package name */
    public final m f11528a;

    public j(OutputStream outputStream) {
        this.f11528a = m.d(outputStream);
    }

    public static int length(CborObject cborObject) {
        try {
            return new j().writeDataItem(cborObject).f11528a.length();
        } catch (IOException unused) {
            return 0;
        }
    }

    public final void a(int i, long j) throws IOException {
        if (j >= 0) {
            int a2 = CborInteger.a(j);
            b(i, a2);
            switch (a2) {
                case 24:
                    this.f11528a.put((byte) j);
                    return;
                case 25:
                    this.f11528a.putShort((short) j);
                    return;
                case 26:
                    this.f11528a.putInt((int) j);
                    return;
                case 27:
                    this.f11528a.putLong(j);
                    return;
                default:
                    return;
            }
        }
        throw new IllegalArgumentException("val cannot be negative");
    }

    public final void b(int i, int i2) throws IOException {
        this.f11528a.put((byte) ((i << 5) + (i2 & 31)));
    }

    @CanIgnoreReturnValue
    public final j c(CborArray cborArray) throws IOException {
        a(cborArray.getMajorType(), cborArray.size());
        Iterator<CborObject> it = cborArray.iterator();
        while (it.hasNext()) {
            writeDataItem(it.next());
        }
        return this;
    }

    @CanIgnoreReturnValue
    public final j d(CborByteString cborByteString) throws IOException {
        a(cborByteString.getMajorType(), cborByteString.byteArrayValue().length);
        this.f11528a.b(cborByteString.byteArrayValue());
        return this;
    }

    @CanIgnoreReturnValue
    public final j e(CborFloat cborFloat) throws IOException {
        b(cborFloat.getMajorType(), cborFloat.getAdditionalInformation());
        switch (cborFloat.getAdditionalInformation()) {
            case 25:
                this.f11528a.c(cborFloat.floatValue());
                break;
            case 26:
                this.f11528a.putFloat(cborFloat.floatValue());
                break;
            case 27:
                this.f11528a.putDouble(cborFloat.floatValue());
                break;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public final j f(CborInteger cborInteger) throws IOException {
        long longValue = cborInteger.longValue();
        if (longValue < 0) {
            longValue = (-longValue) - 1;
        }
        a(cborInteger.getMajorType(), longValue);
        return this;
    }

    @CanIgnoreReturnValue
    public final j g(CborMap cborMap) throws IOException {
        a(cborMap.getMajorType(), cborMap.mapValue().size());
        for (Map.Entry<CborObject, CborObject> entry : cborMap.mapValue().entrySet()) {
            writeDataItem(entry.getKey());
            writeDataItem(entry.getValue());
        }
        return this;
    }

    @Override // com.google.iot.cbor.CborWriter
    @CanIgnoreReturnValue
    /* renamed from: h */
    public j writeDataItem(CborObject cborObject) throws IOException {
        writeTag(cborObject.getTag());
        if (cborObject instanceof CborArray) {
            return c((CborArray) cborObject);
        }
        if (cborObject instanceof CborFloat) {
            return e((CborFloat) cborObject);
        }
        if (cborObject instanceof CborInteger) {
            return f((CborInteger) cborObject);
        }
        if (cborObject instanceof CborMap) {
            return g((CborMap) cborObject);
        }
        if (cborObject instanceof CborTextString) {
            return j((CborTextString) cborObject);
        }
        if (cborObject instanceof CborByteString) {
            return d((CborByteString) cborObject);
        }
        if (cborObject instanceof CborSimple) {
            return i((CborSimple) cborObject);
        }
        throw new CborRuntimeException("Can't encode \"" + cborObject + "\" of type " + cborObject.getClass());
    }

    @CanIgnoreReturnValue
    public final j i(CborSimple cborSimple) throws IOException {
        a(cborSimple.getMajorType(), cborSimple.getValue());
        return this;
    }

    @CanIgnoreReturnValue
    public final j j(CborTextString cborTextString) throws IOException {
        a(cborTextString.getMajorType(), cborTextString.byteArrayValue().length);
        this.f11528a.b(cborTextString.byteArrayValue());
        return this;
    }

    @Override // com.google.iot.cbor.CborWriter
    @CanIgnoreReturnValue
    public CborWriter writeTag(int i) throws IOException {
        if (i != -1) {
            a(6, i);
        }
        return this;
    }

    public j(ByteBuffer byteBuffer) {
        this.f11528a = m.a(byteBuffer);
    }

    public j() {
        this.f11528a = m.create();
    }
}
