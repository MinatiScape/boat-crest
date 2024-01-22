package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
/* loaded from: classes.dex */
public class StandardGifDecoder implements GifDecoder {
    public static final String v = "StandardGifDecoder";
    @ColorInt

    /* renamed from: a  reason: collision with root package name */
    public int[] f2320a;
    @ColorInt
    public final int[] b;
    public final GifDecoder.BitmapProvider c;
    public ByteBuffer d;
    public byte[] e;
    public GifHeaderParser f;
    public short[] g;
    public byte[] h;
    public byte[] i;
    public byte[] j;
    @ColorInt
    public int[] k;
    public int l;
    public GifHeader m;
    public Bitmap n;
    public boolean o;
    public int p;
    public int q;
    public int r;
    public int s;
    @Nullable
    public Boolean t;
    @NonNull
    public Bitmap.Config u;

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer) {
        this(bitmapProvider, gifHeader, byteBuffer, 1);
    }

    @ColorInt
    public final int a(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = i; i9 < this.q + i; i9++) {
            byte[] bArr = this.j;
            if (i9 >= bArr.length || i9 >= i2) {
                break;
            }
            int i10 = this.f2320a[bArr[i9] & 255];
            if (i10 != 0) {
                i4 += (i10 >> 24) & 255;
                i5 += (i10 >> 16) & 255;
                i6 += (i10 >> 8) & 255;
                i7 += i10 & 255;
                i8++;
            }
        }
        int i11 = i + i3;
        for (int i12 = i11; i12 < this.q + i11; i12++) {
            byte[] bArr2 = this.j;
            if (i12 >= bArr2.length || i12 >= i2) {
                break;
            }
            int i13 = this.f2320a[bArr2[i12] & 255];
            if (i13 != 0) {
                i4 += (i13 >> 24) & 255;
                i5 += (i13 >> 16) & 255;
                i6 += (i13 >> 8) & 255;
                i7 += i13 & 255;
                i8++;
            }
        }
        if (i8 == 0) {
            return 0;
        }
        return ((i4 / i8) << 24) | ((i5 / i8) << 16) | ((i6 / i8) << 8) | (i7 / i8);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void advance() {
        this.l = (this.l + 1) % this.m.c;
    }

    public final void b(a aVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int[] iArr = this.k;
        int i6 = aVar.d;
        int i7 = this.q;
        int i8 = i6 / i7;
        int i9 = aVar.b / i7;
        int i10 = aVar.c / i7;
        int i11 = aVar.f2321a / i7;
        boolean z = this.l == 0;
        int i12 = this.s;
        int i13 = this.r;
        byte[] bArr = this.j;
        int[] iArr2 = this.f2320a;
        Boolean bool = this.t;
        int i14 = 8;
        int i15 = 0;
        int i16 = 0;
        int i17 = 1;
        while (i15 < i8) {
            Boolean bool2 = bool;
            if (aVar.e) {
                if (i16 >= i8) {
                    i = i8;
                    int i18 = i17 + 1;
                    if (i18 == 2) {
                        i17 = i18;
                        i16 = 4;
                    } else if (i18 == 3) {
                        i17 = i18;
                        i14 = 4;
                        i16 = 2;
                    } else if (i18 != 4) {
                        i17 = i18;
                    } else {
                        i17 = i18;
                        i16 = 1;
                        i14 = 2;
                    }
                } else {
                    i = i8;
                }
                i2 = i16 + i14;
            } else {
                i = i8;
                i2 = i16;
                i16 = i15;
            }
            int i19 = i16 + i9;
            boolean z2 = i7 == 1;
            if (i19 < i13) {
                int i20 = i19 * i12;
                int i21 = i20 + i11;
                int i22 = i21 + i10;
                int i23 = i20 + i12;
                if (i23 < i22) {
                    i22 = i23;
                }
                i3 = i2;
                int i24 = i15 * i7 * aVar.c;
                if (z2) {
                    int i25 = i21;
                    while (i25 < i22) {
                        int i26 = i9;
                        int i27 = iArr2[bArr[i24] & 255];
                        if (i27 != 0) {
                            iArr[i25] = i27;
                        } else if (z && bool2 == null) {
                            bool2 = Boolean.TRUE;
                        }
                        i24 += i7;
                        i25++;
                        i9 = i26;
                    }
                } else {
                    i5 = i9;
                    int i28 = ((i22 - i21) * i7) + i24;
                    int i29 = i21;
                    while (true) {
                        i4 = i10;
                        if (i29 < i22) {
                            int a2 = a(i24, i28, aVar.c);
                            if (a2 != 0) {
                                iArr[i29] = a2;
                            } else if (z && bool2 == null) {
                                bool2 = Boolean.TRUE;
                            }
                            i24 += i7;
                            i29++;
                            i10 = i4;
                        }
                    }
                    bool = bool2;
                    i15++;
                    i9 = i5;
                    i10 = i4;
                    i8 = i;
                    i16 = i3;
                }
            } else {
                i3 = i2;
            }
            i5 = i9;
            i4 = i10;
            bool = bool2;
            i15++;
            i9 = i5;
            i10 = i4;
            i8 = i;
            i16 = i3;
        }
        Boolean bool3 = bool;
        if (this.t == null) {
            this.t = Boolean.valueOf(bool3 == null ? false : bool3.booleanValue());
        }
    }

    public final void c(a aVar) {
        a aVar2 = aVar;
        int[] iArr = this.k;
        int i = aVar2.d;
        int i2 = aVar2.b;
        int i3 = aVar2.c;
        int i4 = aVar2.f2321a;
        boolean z = this.l == 0;
        int i5 = this.s;
        byte[] bArr = this.j;
        int[] iArr2 = this.f2320a;
        int i6 = 0;
        byte b = -1;
        while (i6 < i) {
            int i7 = (i6 + i2) * i5;
            int i8 = i7 + i4;
            int i9 = i8 + i3;
            int i10 = i7 + i5;
            if (i10 < i9) {
                i9 = i10;
            }
            int i11 = aVar2.c * i6;
            int i12 = i8;
            while (i12 < i9) {
                byte b2 = bArr[i11];
                int i13 = i;
                int i14 = b2 & 255;
                if (i14 != b) {
                    int i15 = iArr2[i14];
                    if (i15 != 0) {
                        iArr[i12] = i15;
                    } else {
                        b = b2;
                    }
                }
                i11++;
                i12++;
                i = i13;
            }
            i6++;
            aVar2 = aVar;
        }
        Boolean bool = this.t;
        this.t = Boolean.valueOf((bool != null && bool.booleanValue()) || (this.t == null && z && b != -1));
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void clear() {
        this.m = null;
        byte[] bArr = this.j;
        if (bArr != null) {
            this.c.release(bArr);
        }
        int[] iArr = this.k;
        if (iArr != null) {
            this.c.release(iArr);
        }
        Bitmap bitmap = this.n;
        if (bitmap != null) {
            this.c.release(bitmap);
        }
        this.n = null;
        this.d = null;
        this.t = null;
        byte[] bArr2 = this.e;
        if (bArr2 != null) {
            this.c.release(bArr2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d(a aVar) {
        int i;
        int i2;
        short s;
        StandardGifDecoder standardGifDecoder = this;
        if (aVar != null) {
            standardGifDecoder.d.position(aVar.j);
        }
        if (aVar == null) {
            GifHeader gifHeader = standardGifDecoder.m;
            i = gifHeader.f;
            i2 = gifHeader.g;
        } else {
            i = aVar.c;
            i2 = aVar.d;
        }
        int i3 = i * i2;
        byte[] bArr = standardGifDecoder.j;
        if (bArr == null || bArr.length < i3) {
            standardGifDecoder.j = standardGifDecoder.c.obtainByteArray(i3);
        }
        byte[] bArr2 = standardGifDecoder.j;
        if (standardGifDecoder.g == null) {
            standardGifDecoder.g = new short[4096];
        }
        short[] sArr = standardGifDecoder.g;
        if (standardGifDecoder.h == null) {
            standardGifDecoder.h = new byte[4096];
        }
        byte[] bArr3 = standardGifDecoder.h;
        if (standardGifDecoder.i == null) {
            standardGifDecoder.i = new byte[4097];
        }
        byte[] bArr4 = standardGifDecoder.i;
        int h = h();
        int i4 = 1 << h;
        int i5 = i4 + 1;
        int i6 = i4 + 2;
        int i7 = h + 1;
        int i8 = (1 << i7) - 1;
        int i9 = 0;
        for (int i10 = 0; i10 < i4; i10++) {
            sArr[i10] = 0;
            bArr3[i10] = (byte) i10;
        }
        byte[] bArr5 = standardGifDecoder.e;
        int i11 = i7;
        int i12 = i6;
        int i13 = i8;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = -1;
        while (true) {
            if (i9 >= i3) {
                break;
            }
            if (i14 == 0) {
                i14 = g();
                if (i14 <= 0) {
                    standardGifDecoder.p = 3;
                    break;
                }
                i15 = 0;
            }
            i17 += (bArr5[i15] & 255) << i16;
            i15++;
            i14--;
            int i22 = i16 + 8;
            int i23 = i12;
            int i24 = i11;
            int i25 = i21;
            int i26 = i7;
            int i27 = i19;
            while (true) {
                if (i22 < i24) {
                    i21 = i25;
                    i12 = i23;
                    i16 = i22;
                    standardGifDecoder = this;
                    i19 = i27;
                    i7 = i26;
                    i11 = i24;
                    break;
                }
                int i28 = i6;
                int i29 = i17 & i13;
                i17 >>= i24;
                i22 -= i24;
                if (i29 == i4) {
                    i13 = i8;
                    i24 = i26;
                    i23 = i28;
                    i6 = i23;
                    i25 = -1;
                } else if (i29 == i5) {
                    i16 = i22;
                    i19 = i27;
                    i12 = i23;
                    i7 = i26;
                    i6 = i28;
                    i21 = i25;
                    i11 = i24;
                    standardGifDecoder = this;
                    break;
                } else if (i25 == -1) {
                    bArr2[i18] = bArr3[i29];
                    i18++;
                    i9++;
                    i25 = i29;
                    i27 = i25;
                    i6 = i28;
                    i22 = i22;
                } else {
                    if (i29 >= i23) {
                        bArr4[i20] = (byte) i27;
                        i20++;
                        s = i25;
                    } else {
                        s = i29;
                    }
                    while (s >= i4) {
                        bArr4[i20] = bArr3[s];
                        i20++;
                        s = sArr[s];
                    }
                    i27 = bArr3[s] & 255;
                    byte b = (byte) i27;
                    bArr2[i18] = b;
                    while (true) {
                        i18++;
                        i9++;
                        if (i20 <= 0) {
                            break;
                        }
                        i20--;
                        bArr2[i18] = bArr4[i20];
                    }
                    byte[] bArr6 = bArr4;
                    if (i23 < 4096) {
                        sArr[i23] = (short) i25;
                        bArr3[i23] = b;
                        i23++;
                        if ((i23 & i13) == 0 && i23 < 4096) {
                            i24++;
                            i13 += i23;
                        }
                    }
                    i25 = i29;
                    i6 = i28;
                    i22 = i22;
                    bArr4 = bArr6;
                }
            }
        }
        Arrays.fill(bArr2, i18, i3, (byte) 0);
    }

    @NonNull
    public final GifHeaderParser e() {
        if (this.f == null) {
            this.f = new GifHeaderParser();
        }
        return this.f;
    }

    public final Bitmap f() {
        Boolean bool = this.t;
        Bitmap obtain = this.c.obtain(this.s, this.r, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.u);
        obtain.setHasAlpha(true);
        return obtain;
    }

    public final int g() {
        int h = h();
        if (h <= 0) {
            return h;
        }
        ByteBuffer byteBuffer = this.d;
        byteBuffer.get(this.e, 0, Math.min(h, byteBuffer.remaining()));
        return h;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getByteSize() {
        return this.d.limit() + this.j.length + (this.k.length * 4);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getCurrentFrameIndex() {
        return this.l;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    @NonNull
    public ByteBuffer getData() {
        return this.d;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getDelay(int i) {
        if (i >= 0) {
            GifHeader gifHeader = this.m;
            if (i < gifHeader.c) {
                return gifHeader.e.get(i).i;
            }
        }
        return -1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getFrameCount() {
        return this.m.c;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getHeight() {
        return this.m.g;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    @Deprecated
    public int getLoopCount() {
        int i = this.m.m;
        if (i == -1) {
            return 1;
        }
        return i;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNetscapeLoopCount() {
        return this.m.m;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNextDelay() {
        int i;
        if (this.m.c <= 0 || (i = this.l) < 0) {
            return 0;
        }
        return getDelay(i);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    @Nullable
    public synchronized Bitmap getNextFrame() {
        if (this.m.c <= 0 || this.l < 0) {
            String str = v;
            if (Log.isLoggable(str, 3)) {
                Log.d(str, "Unable to decode frame, frameCount=" + this.m.c + ", framePointer=" + this.l);
            }
            this.p = 1;
        }
        int i = this.p;
        if (i != 1 && i != 2) {
            this.p = 0;
            if (this.e == null) {
                this.e = this.c.obtainByteArray(255);
            }
            a aVar = this.m.e.get(this.l);
            int i2 = this.l - 1;
            a aVar2 = i2 >= 0 ? this.m.e.get(i2) : null;
            int[] iArr = aVar.k;
            if (iArr == null) {
                iArr = this.m.f2318a;
            }
            this.f2320a = iArr;
            if (iArr == null) {
                String str2 = v;
                if (Log.isLoggable(str2, 3)) {
                    Log.d(str2, "No valid color table found for frame #" + this.l);
                }
                this.p = 1;
                return null;
            }
            if (aVar.f) {
                System.arraycopy(iArr, 0, this.b, 0, iArr.length);
                int[] iArr2 = this.b;
                this.f2320a = iArr2;
                iArr2[aVar.h] = 0;
                if (aVar.g == 2 && this.l == 0) {
                    this.t = Boolean.TRUE;
                }
            }
            return i(aVar, aVar2);
        }
        String str3 = v;
        if (Log.isLoggable(str3, 3)) {
            Log.d(str3, "Unable to decode frame, status=" + this.p);
        }
        return null;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getStatus() {
        return this.p;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getTotalIterationCount() {
        int i = this.m.m;
        if (i == -1) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        return i + 1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getWidth() {
        return this.m.f;
    }

    public final int h() {
        return this.d.get() & 255;
    }

    public final Bitmap i(a aVar, a aVar2) {
        int i;
        int i2;
        Bitmap bitmap;
        int[] iArr = this.k;
        int i3 = 0;
        if (aVar2 == null) {
            Bitmap bitmap2 = this.n;
            if (bitmap2 != null) {
                this.c.release(bitmap2);
            }
            this.n = null;
            Arrays.fill(iArr, 0);
        }
        if (aVar2 != null && aVar2.g == 3 && this.n == null) {
            Arrays.fill(iArr, 0);
        }
        if (aVar2 != null && (i2 = aVar2.g) > 0) {
            if (i2 == 2) {
                if (!aVar.f) {
                    GifHeader gifHeader = this.m;
                    int i4 = gifHeader.l;
                    if (aVar.k == null || gifHeader.j != aVar.h) {
                        i3 = i4;
                    }
                }
                int i5 = aVar2.d;
                int i6 = this.q;
                int i7 = i5 / i6;
                int i8 = aVar2.b / i6;
                int i9 = aVar2.c / i6;
                int i10 = aVar2.f2321a / i6;
                int i11 = this.s;
                int i12 = (i8 * i11) + i10;
                int i13 = (i7 * i11) + i12;
                while (i12 < i13) {
                    int i14 = i12 + i9;
                    for (int i15 = i12; i15 < i14; i15++) {
                        iArr[i15] = i3;
                    }
                    i12 += this.s;
                }
            } else if (i2 == 3 && (bitmap = this.n) != null) {
                int i16 = this.s;
                bitmap.getPixels(iArr, 0, i16, 0, 0, i16, this.r);
            }
        }
        d(aVar);
        if (!aVar.e && this.q == 1) {
            c(aVar);
        } else {
            b(aVar);
        }
        if (this.o && ((i = aVar.g) == 0 || i == 1)) {
            if (this.n == null) {
                this.n = f();
            }
            Bitmap bitmap3 = this.n;
            int i17 = this.s;
            bitmap3.setPixels(iArr, 0, i17, 0, 0, i17, this.r);
        }
        Bitmap f = f();
        int i18 = this.s;
        f.setPixels(iArr, 0, i18, 0, 0, i18, this.r);
        return f;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int read(@Nullable InputStream inputStream, int i) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr, 0, 16384);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(v, "Error reading data from stream", e);
            }
        } else {
            this.p = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(v, "Error closing stream", e2);
            }
        }
        return this.p;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void resetFrameIndex() {
        this.l = -1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull byte[] bArr) {
        setData(gifHeader, ByteBuffer.wrap(bArr));
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void setDefaultBitmapConfig(@NonNull Bitmap.Config config) {
        if (config != Bitmap.Config.ARGB_8888 && config != Bitmap.Config.RGB_565) {
            throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
        }
        this.u = config;
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
        this(bitmapProvider);
        setData(gifHeader, byteBuffer, i);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer) {
        setData(gifHeader, byteBuffer, 1);
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider) {
        this.b = new int[256];
        this.u = Bitmap.Config.ARGB_8888;
        this.c = bitmapProvider;
        this.m = new GifHeader();
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer, int i) {
        if (i > 0) {
            int highestOneBit = Integer.highestOneBit(i);
            this.p = 0;
            this.m = gifHeader;
            this.l = -1;
            ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
            this.d = asReadOnlyBuffer;
            asReadOnlyBuffer.position(0);
            this.d.order(ByteOrder.LITTLE_ENDIAN);
            this.o = false;
            Iterator<a> it = gifHeader.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().g == 3) {
                    this.o = true;
                    break;
                }
            }
            this.q = highestOneBit;
            int i2 = gifHeader.f;
            this.s = i2 / highestOneBit;
            int i3 = gifHeader.g;
            this.r = i3 / highestOneBit;
            this.j = this.c.obtainByteArray(i2 * i3);
            this.k = this.c.obtainIntArray(this.s * this.r);
        } else {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + i);
        }
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized int read(@Nullable byte[] bArr) {
        GifHeader parseHeader = e().setData(bArr).parseHeader();
        this.m = parseHeader;
        if (bArr != null) {
            setData(parseHeader, bArr);
        }
        return this.p;
    }
}
