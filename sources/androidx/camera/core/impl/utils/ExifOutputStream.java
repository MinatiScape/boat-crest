package androidx.camera.core.impl.utils;

import androidx.annotation.NonNull;
import androidx.camera.core.impl.utils.ExifData;
import androidx.core.util.Preconditions;
import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
/* loaded from: classes.dex */
public final class ExifOutputStream extends FilterOutputStream {
    public static final byte[] n = "Exif\u0000\u0000".getBytes(d.d);
    public final ExifData h;
    public final byte[] i;
    public final ByteBuffer j;
    public int k;
    public int l;
    public int m;

    /* loaded from: classes.dex */
    public static final class a {
        public static boolean a(short s) {
            return (s < -64 || s > -49 || s == -60 || s == -56 || s == -52) ? false : true;
        }
    }

    public ExifOutputStream(@NonNull OutputStream outputStream, @NonNull ExifData exifData) {
        super(new BufferedOutputStream(outputStream, 65536));
        this.i = new byte[1];
        this.j = ByteBuffer.allocate(4);
        this.k = 0;
        this.h = exifData;
    }

    public final int a(int i, byte[] bArr, int i2, int i3) {
        int min = Math.min(i3, i - this.j.position());
        this.j.put(bArr, i2, min);
        return min;
    }

    public final void b(@NonNull c cVar) throws IOException {
        e[] eVarArr;
        e[][] eVarArr2 = ExifData.h;
        int[] iArr = new int[eVarArr2.length];
        int[] iArr2 = new int[eVarArr2.length];
        for (e eVar : ExifData.f) {
            for (int i = 0; i < ExifData.h.length; i++) {
                this.h.a(i).remove(eVar.b);
            }
        }
        if (!this.h.a(1).isEmpty()) {
            this.h.a(0).put(ExifData.f[1].b, d.f(0L, this.h.getByteOrder()));
        }
        if (!this.h.a(2).isEmpty()) {
            this.h.a(0).put(ExifData.f[2].b, d.f(0L, this.h.getByteOrder()));
        }
        if (!this.h.a(3).isEmpty()) {
            this.h.a(1).put(ExifData.f[3].b, d.f(0L, this.h.getByteOrder()));
        }
        for (int i2 = 0; i2 < ExifData.h.length; i2++) {
            int i3 = 0;
            for (Map.Entry<String, d> entry : this.h.a(i2).entrySet()) {
                int m = entry.getValue().m();
                if (m > 4) {
                    i3 += m;
                }
            }
            iArr2[i2] = iArr2[i2] + i3;
        }
        int i4 = 8;
        for (int i5 = 0; i5 < ExifData.h.length; i5++) {
            if (!this.h.a(i5).isEmpty()) {
                iArr[i5] = i4;
                i4 += (this.h.a(i5).size() * 12) + 2 + 4 + iArr2[i5];
            }
        }
        int i6 = i4 + 8;
        if (!this.h.a(1).isEmpty()) {
            this.h.a(0).put(ExifData.f[1].b, d.f(iArr[1], this.h.getByteOrder()));
        }
        if (!this.h.a(2).isEmpty()) {
            this.h.a(0).put(ExifData.f[2].b, d.f(iArr[2], this.h.getByteOrder()));
        }
        if (!this.h.a(3).isEmpty()) {
            this.h.a(1).put(ExifData.f[3].b, d.f(iArr[3], this.h.getByteOrder()));
        }
        cVar.f(i6);
        cVar.write(n);
        cVar.d(this.h.getByteOrder() == ByteOrder.BIG_ENDIAN ? (short) 19789 : (short) 18761);
        cVar.a(this.h.getByteOrder());
        cVar.f(42);
        cVar.e(8L);
        for (int i7 = 0; i7 < ExifData.h.length; i7++) {
            if (!this.h.a(i7).isEmpty()) {
                cVar.f(this.h.a(i7).size());
                int size = iArr[i7] + 2 + (this.h.a(i7).size() * 12) + 4;
                for (Map.Entry<String, d> entry2 : this.h.a(i7).entrySet()) {
                    int i8 = ((e) Preconditions.checkNotNull(ExifData.Builder.f.get(i7).get(entry2.getKey()), "Tag not supported: " + entry2.getKey() + ". Tag needs to be ported from ExifInterface to ExifData.")).f742a;
                    d value = entry2.getValue();
                    int m2 = value.m();
                    cVar.f(i8);
                    cVar.f(value.f741a);
                    cVar.c(value.b);
                    if (m2 > 4) {
                        cVar.e(size);
                        size += m2;
                    } else {
                        cVar.write(value.c);
                        if (m2 < 4) {
                            while (m2 < 4) {
                                cVar.b(0);
                                m2++;
                            }
                        }
                    }
                }
                cVar.e(0L);
                for (Map.Entry<String, d> entry3 : this.h.a(i7).entrySet()) {
                    byte[] bArr = entry3.getValue().c;
                    if (bArr.length > 4) {
                        cVar.write(bArr, 0, bArr.length);
                    }
                }
            }
        }
        cVar.a(ByteOrder.BIG_ENDIAN);
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0106, code lost:
        if (r9 <= 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0108, code lost:
        ((java.io.FilterOutputStream) r6).out.write(r7, r8, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x010d, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
        return;
     */
    @Override // java.io.FilterOutputStream, java.io.OutputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void write(@androidx.annotation.NonNull byte[] r7, int r8, int r9) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.utils.ExifOutputStream.write(byte[], int, int):void");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.i;
        bArr[0] = (byte) (i & 255);
        write(bArr);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(@NonNull byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }
}
