package com.google.iot.cbor;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes10.dex */
public class l implements k {

    /* renamed from: a  reason: collision with root package name */
    public final InputStream f11529a;
    public int b = -2;
    public boolean c = false;
    public long d = 0;

    public l(InputStream inputStream) {
        this.f11529a = inputStream;
    }

    @Override // com.google.iot.cbor.k
    public short a() throws IOException {
        return (short) (((short) ((get() & 255) << 8)) | ((short) (get() & 255)));
    }

    @Override // com.google.iot.cbor.k
    public long bytesParsed() {
        return this.d;
    }

    @Override // com.google.iot.cbor.k
    public long c() throws IOException {
        return ((get() & 255) << 56) | ((get() & 255) << 48) | ((get() & 255) << 40) | ((get() & 255) << 32) | ((get() & 255) << 24) | ((get() & 255) << 16) | ((get() & 255) << 8) | (get() & 255);
    }

    @Override // com.google.iot.cbor.k
    public boolean e() throws IOException {
        if (!this.c) {
            peek();
        }
        return this.b >= 0;
    }

    @Override // com.google.iot.cbor.k
    public int f() throws IOException {
        return ((get() & 255) << 24) | ((get() & 255) << 16) | ((get() & 255) << 8) | (get() & 255);
    }

    @Override // com.google.iot.cbor.k
    public byte get() throws IOException {
        int read;
        if (this.c) {
            this.c = false;
            read = this.b;
        } else {
            read = this.f11529a.read();
        }
        if (read >= 0) {
            this.d++;
            return (byte) read;
        }
        throw new EOFException("No more bytes in input stream");
    }

    @Override // com.google.iot.cbor.k
    @CanIgnoreReturnValue
    public int peek() throws IOException {
        if (this.c) {
            return (byte) this.b;
        }
        int read = this.f11529a.read();
        this.b = read;
        this.c = true;
        return read;
    }
}
