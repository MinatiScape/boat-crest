package com.google.crypto.tink.subtle.prf;

import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import com.google.errorprone.annotations.Immutable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
@Immutable
/* loaded from: classes10.dex */
public class HkdfStreamingPrf implements StreamingPrf {

    /* renamed from: a  reason: collision with root package name */
    public final Enums.HashType f11061a;
    public final byte[] b;
    public final byte[] c;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11062a;

        static {
            int[] iArr = new int[Enums.HashType.values().length];
            f11062a = iArr;
            try {
                iArr[Enums.HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11062a[Enums.HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11062a[Enums.HashType.SHA384.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11062a[Enums.HashType.SHA512.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public HkdfStreamingPrf(Enums.HashType hashType, byte[] bArr, byte[] bArr2) {
        this.f11061a = hashType;
        this.b = Arrays.copyOf(bArr, bArr.length);
        this.c = Arrays.copyOf(bArr2, bArr2.length);
    }

    public static String e(Enums.HashType hashType) throws GeneralSecurityException {
        int i = a.f11062a[hashType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return "HmacSha512";
                    }
                    throw new GeneralSecurityException("No getJavaxHmacName for given hash " + hashType + " known");
                }
                return "HmacSha384";
            }
            return "HmacSha256";
        }
        return "HmacSha1";
    }

    @Override // com.google.crypto.tink.subtle.prf.StreamingPrf
    public InputStream computePrf(byte[] bArr) {
        return new b(bArr);
    }

    /* loaded from: classes10.dex */
    public class b extends InputStream {
        public final byte[] h;
        public Mac i;
        public byte[] j;
        public ByteBuffer k;
        public int l = -1;

        public b(byte[] bArr) {
            this.h = Arrays.copyOf(bArr, bArr.length);
        }

        public final void a() throws GeneralSecurityException, IOException {
            try {
                this.i = EngineFactory.MAC.getInstance(HkdfStreamingPrf.e(HkdfStreamingPrf.this.f11061a));
                if (HkdfStreamingPrf.this.c == null || HkdfStreamingPrf.this.c.length == 0) {
                    this.i.init(new SecretKeySpec(new byte[this.i.getMacLength()], HkdfStreamingPrf.e(HkdfStreamingPrf.this.f11061a)));
                } else {
                    this.i.init(new SecretKeySpec(HkdfStreamingPrf.this.c, HkdfStreamingPrf.e(HkdfStreamingPrf.this.f11061a)));
                }
                this.i.update(HkdfStreamingPrf.this.b);
                this.j = this.i.doFinal();
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(0);
                this.k = allocateDirect;
                allocateDirect.mark();
                this.l = 0;
            } catch (GeneralSecurityException e) {
                throw new IOException("Creating HMac failed", e);
            }
        }

        public final void b() throws GeneralSecurityException, IOException {
            this.i.init(new SecretKeySpec(this.j, HkdfStreamingPrf.e(HkdfStreamingPrf.this.f11061a)));
            this.k.reset();
            this.i.update(this.k);
            this.i.update(this.h);
            int i = this.l + 1;
            this.l = i;
            this.i.update((byte) i);
            ByteBuffer wrap = ByteBuffer.wrap(this.i.doFinal());
            this.k = wrap;
            wrap.mark();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            byte[] bArr = new byte[1];
            int read = read(bArr, 0, 1);
            if (read == 1) {
                return bArr[0] & 255;
            }
            if (read == -1) {
                return read;
            }
            throw new IOException("Reading failed");
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            try {
                if (this.l == -1) {
                    a();
                }
                int i3 = 0;
                while (i3 < i2) {
                    if (!this.k.hasRemaining()) {
                        if (this.l == 255) {
                            return i3;
                        }
                        b();
                    }
                    int min = Math.min(i2 - i3, this.k.remaining());
                    this.k.get(bArr, i, min);
                    i += min;
                    i3 += min;
                }
                return i3;
            } catch (GeneralSecurityException e) {
                this.i = null;
                throw new IOException("HkdfInputStream failed", e);
            }
        }
    }
}
