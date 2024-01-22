package com.touchgui.sdk.internal;

import com.realsil.sdk.dfu.DfuException;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.TGProgressCallback;
import com.touchgui.sdk.bean.TGFileResult;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes12.dex */
public final class s3 extends z8 {
    public final File e;
    public final long f;
    public final int g;
    public RandomAccessFile h;
    public boolean i;
    public byte[] j;
    public long k;
    public int l;
    public long m;
    public int n;
    public final MessageDigest o;
    public final TGFileResult p;
    public z9 q;

    public s3(File file, int i, int i2) {
        super((byte) -47, (byte) 2, 1);
        this.i = false;
        this.k = 0L;
        this.l = 0;
        this.m = 0L;
        this.n = 0;
        this.p = new TGFileResult();
        this.e = file;
        this.f = i;
        this.g = i2;
        try {
            this.o = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override // com.touchgui.sdk.internal.z8, com.touchgui.sdk.internal.d8
    public final byte[] a(int i) {
        if (this.j == null) {
            this.j = new byte[i - 3];
        }
        try {
            if (this.h == null) {
                this.h = new RandomAccessFile(this.e, RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME);
                this.k = this.e.length();
                long j = this.f;
                if (j > 0) {
                    a(j);
                }
            }
            int read = this.h.read(this.j);
            if (read > 0) {
                int i2 = this.n;
                byte[] bArr = this.j;
                int i3 = 0;
                for (int i4 = 0; i4 < read; i4++) {
                    i3 += bArr[i4] & 255;
                }
                this.n = i2 + i3;
                this.l++;
                this.m += read;
                ByteBuffer allocate = ByteBuffer.allocate(read + 3);
                allocate.put(this.f13852a);
                allocate.put(this.b);
                allocate.put((byte) 0);
                allocate.put(this.j, 0, read);
                this.o.update(this.j, 0, read);
                if (this.m == this.k) {
                    this.i = true;
                    this.p.setCode(0);
                    this.p.setCheckSum(this.n);
                    TGFileResult tGFileResult = this.p;
                    byte[] digest = this.o.digest();
                    tGFileResult.setMd5(s.a(digest, 0, digest.length));
                }
                return allocate.array();
            }
        } catch (IOException e) {
            e.printStackTrace();
            RandomAccessFile randomAccessFile = this.h;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                    this.h = null;
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final Object b() {
        return this.p;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int onResponse(byte[] bArr) {
        if (this.l % this.g != 0) {
            TGLogger.w("This packet should not be received");
            return 0;
        }
        byte b = bArr[2];
        if (b == Byte.MAX_VALUE) {
            int b2 = s.b(bArr, 7, 4);
            TGLogger.d("Resume broken transfer: offset=" + b2);
            long j = (long) b2;
            a(j);
            long j2 = this.k;
            z9 z9Var = this.q;
            if (z9Var != null) {
                int i = (int) ((100 * j) / j2);
                int i2 = (int) j;
                int i3 = (int) j2;
                TGProgressCallback tGProgressCallback = z9Var.f13853a;
                if (tGProgressCallback != null) {
                    tGProgressCallback.onProgress(i, i2, i3);
                }
            }
            z9 z9Var2 = this.q;
            if (z9Var2 != null) {
                int i4 = this.n;
                com.touchgui.sdk.k kVar = z9Var2.b;
                kVar.h = i4;
                kVar.i = (int) this.m;
                kVar.j = 0;
            }
            this.p.setCode(bArr[2]);
            return 256;
        } else if (b == 126) {
            int b3 = s.b(bArr, 7, 4);
            TGLogger.d("file transferred: offset=" + b3);
            this.i = true;
            long j3 = this.k;
            z9 z9Var3 = this.q;
            if (z9Var3 != null) {
                int i5 = (int) ((100 * j3) / j3);
                int i6 = (int) j3;
                TGProgressCallback tGProgressCallback2 = z9Var3.f13853a;
                if (tGProgressCallback2 != null) {
                    tGProgressCallback2.onProgress(i5, i6, i6);
                }
            }
            this.p.setCode(bArr[2]);
            return DfuException.ERROR_CONNECTION_GATT_CONN_TIMEOUT;
        } else if (b != 0) {
            TGLogger.e("file transfer error:" + ((int) bArr[2]));
            this.p.setCode(bArr[2]);
            return 256;
        } else {
            if (this.n != s.b(bArr, 3, 4)) {
                TGLogger.e("file transfer error: check sum");
                this.p.setCode(257);
                return 256;
            }
            int b4 = s.b(bArr, 7, 4);
            long j4 = this.m;
            if (j4 != b4) {
                TGLogger.e("file transfer error: offset=" + b4 + ", sendSize=" + this.m);
                this.p.setCode(258);
                return 256;
            }
            z9 z9Var4 = this.q;
            if (z9Var4 != null) {
                int i7 = this.n;
                com.touchgui.sdk.k kVar2 = z9Var4.b;
                kVar2.h = i7;
                kVar2.i = (int) j4;
                kVar2.j = 0;
            }
            if (this.i) {
                return DfuException.ERROR_CONNECTION_GATT_CONN_TIMEOUT;
            }
            return 512;
        }
    }

    @Override // com.touchgui.sdk.internal.d8
    public final void release() {
        RandomAccessFile randomAccessFile = this.h;
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
                this.h = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int b(byte[] bArr) {
        String format;
        if (a(bArr)) {
            if (TGLogger.isDebug() && "universalTouch".toLowerCase().contains("touch")) {
                int i = this.l % this.g;
                if (i == 1) {
                    format = String.format(Locale.getDefault(), "=> %s", s.a(bArr));
                } else if (i == 0) {
                    format = String.format(Locale.getDefault(), "=> D1 02 ...(%d)", Integer.valueOf(this.l));
                }
                TGLogger.d(format);
            }
            long j = this.m;
            long j2 = this.k;
            z9 z9Var = this.q;
            if (z9Var != null) {
                int i2 = (int) ((100 * j) / j2);
                int i3 = (int) j;
                int i4 = (int) j2;
                TGProgressCallback tGProgressCallback = z9Var.f13853a;
                if (tGProgressCallback != null) {
                    tGProgressCallback.onProgress(i2, i3, i4);
                }
            }
            if (!this.i) {
                return this.l % this.g == 0 ? 2 : 4;
            } else if (this.l % this.g == 0) {
                return 2;
            } else {
                return DfuException.ERROR_CONNECTION_GATT_CONN_TIMEOUT;
            }
        }
        return 0;
    }

    public final void a(long j) {
        this.n = 0;
        this.m = 0L;
        this.o.reset();
        RandomAccessFile randomAccessFile = this.h;
        if (randomAccessFile == null) {
            return;
        }
        randomAccessFile.seek(0L);
        while (true) {
            long j2 = this.m;
            if (j2 >= j) {
                return;
            }
            RandomAccessFile randomAccessFile2 = this.h;
            byte[] bArr = this.j;
            int read = randomAccessFile2.read(bArr, 0, Math.min((int) (j - j2), bArr.length));
            int i = this.n;
            byte[] bArr2 = this.j;
            int i2 = 0;
            for (int i3 = 0; i3 < read; i3++) {
                i2 += bArr2[i3] & 255;
            }
            this.n = i2 + i;
            this.m += read;
            this.o.update(this.j, 0, read);
        }
    }
}
