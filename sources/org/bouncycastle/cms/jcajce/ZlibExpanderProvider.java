package org.bouncycastle.cms.jcajce;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.InputExpander;
import org.bouncycastle.operator.InputExpanderProvider;
/* loaded from: classes12.dex */
public class ZlibExpanderProvider implements InputExpanderProvider {

    /* renamed from: a  reason: collision with root package name */
    public final long f14600a;

    /* loaded from: classes12.dex */
    public class a implements InputExpander {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f14601a;

        public a(AlgorithmIdentifier algorithmIdentifier) {
            this.f14601a = algorithmIdentifier;
        }

        @Override // org.bouncycastle.operator.InputExpander
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f14601a;
        }

        @Override // org.bouncycastle.operator.InputExpander
        public InputStream getInputStream(InputStream inputStream) {
            InflaterInputStream inflaterInputStream = new InflaterInputStream(inputStream);
            return ZlibExpanderProvider.this.f14600a >= 0 ? new b(inflaterInputStream, ZlibExpanderProvider.this.f14600a) : inflaterInputStream;
        }
    }

    /* loaded from: classes12.dex */
    public static class b extends FilterInputStream {
        public long h;

        public b(InputStream inputStream, long j) {
            super(inputStream);
            this.h = j;
        }

        /* JADX WARN: Code restructure failed: missing block: B:7:0x0019, code lost:
            if (r4 >= 0) goto L7;
         */
        @Override // java.io.FilterInputStream, java.io.InputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int read() throws java.io.IOException {
            /*
                r8 = this;
                long r0 = r8.h
                r2 = 0
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r0 < 0) goto L1c
                java.io.InputStream r0 = r8.in
                int r0 = r0.read()
                if (r0 < 0) goto L1b
                long r4 = r8.h
                r6 = 1
                long r4 = r4 - r6
                r8.h = r4
                int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                if (r1 < 0) goto L1c
            L1b:
                return r0
            L1c:
                org.bouncycastle.util.io.StreamOverflowException r0 = new org.bouncycastle.util.io.StreamOverflowException
                java.lang.String r1 = "expanded byte limit exceeded"
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cms.jcajce.ZlibExpanderProvider.b.read():int");
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (i2 < 1) {
                return super.read(bArr, i, i2);
            }
            long j = this.h;
            if (j < 1) {
                read();
                return -1;
            }
            if (j <= i2) {
                i2 = (int) j;
            }
            int read = ((FilterInputStream) this).in.read(bArr, i, i2);
            if (read > 0) {
                this.h -= read;
            }
            return read;
        }
    }

    public ZlibExpanderProvider() {
        this.f14600a = -1L;
    }

    public ZlibExpanderProvider(long j) {
        this.f14600a = j;
    }

    @Override // org.bouncycastle.operator.InputExpanderProvider
    public InputExpander get(AlgorithmIdentifier algorithmIdentifier) {
        return new a(algorithmIdentifier);
    }
}
