package com.google.iot.cbor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
/* loaded from: classes10.dex */
public class n implements m {

    /* renamed from: a  reason: collision with root package name */
    public final ByteBuffer f11530a;
    public int b = 0;

    public n(ByteBuffer byteBuffer) {
        this.f11530a = byteBuffer;
    }

    @Override // com.google.iot.cbor.m
    public int length() {
        return this.b;
    }

    @Override // com.google.iot.cbor.m
    public m put(byte b) throws IOException {
        try {
            this.f11530a.put(b);
            this.b++;
            return this;
        } catch (IndexOutOfBoundsException | ReadOnlyBufferException e) {
            throw new IOException(e);
        }
    }

    @Override // com.google.iot.cbor.m
    public m putInt(int i) throws IOException {
        try {
            this.f11530a.putInt(i);
            this.b += 4;
            return this;
        } catch (IndexOutOfBoundsException | ReadOnlyBufferException e) {
            throw new IOException(e);
        }
    }

    @Override // com.google.iot.cbor.m
    public m putLong(long j) throws IOException {
        try {
            this.f11530a.putLong(j);
            this.b += 8;
            return this;
        } catch (IndexOutOfBoundsException | ReadOnlyBufferException e) {
            throw new IOException(e);
        }
    }

    @Override // com.google.iot.cbor.m
    public m putShort(short s) throws IOException {
        try {
            this.f11530a.putShort(s);
            this.b += 2;
            return this;
        } catch (IndexOutOfBoundsException | ReadOnlyBufferException e) {
            throw new IOException(e);
        }
    }
}
