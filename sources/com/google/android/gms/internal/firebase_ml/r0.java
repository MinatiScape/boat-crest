package com.google.android.gms.internal.firebase_ml;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes7.dex */
public final class r0 extends FilterInputStream {
    public long h;
    public final /* synthetic */ p0 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r0(p0 p0Var, InputStream inputStream) {
        super(inputStream);
        this.i = p0Var;
        this.h = 0L;
    }

    public final void a() throws IOException {
        long a2 = this.i.a();
        if (a2 == -1) {
            return;
        }
        long j = this.h;
        if (j == 0 || j >= a2) {
            return;
        }
        long j2 = this.h;
        StringBuilder sb = new StringBuilder(102);
        sb.append("Connection closed prematurely: bytesRead = ");
        sb.append(j2);
        sb.append(", Content-Length = ");
        sb.append(a2);
        throw new IOException(sb.toString());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int read = ((FilterInputStream) this).in.read(bArr, i, i2);
        if (read == -1) {
            a();
        } else {
            this.h += read;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) throws IOException {
        long skip = ((FilterInputStream) this).in.skip(j);
        this.h += skip;
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        int read = ((FilterInputStream) this).in.read();
        if (read == -1) {
            a();
        } else {
            this.h++;
        }
        return read;
    }
}
