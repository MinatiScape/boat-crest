package com.htsmart.wristband2.dial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.htsmart.wristband2.dial.DialDrawer;
import com.htsmart.wristband2.utils.WristbandLog;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.image.constants.SubBinId;
import com.veryfit.multi.nativeprotocol.b;
import io.reactivex.disposables.Disposable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
/* loaded from: classes11.dex */
public class DialWriter {
    public static final int[] j = {0, 49345, 49537, 320, 49921, 960, 640, 49729, 50689, 1728, 1920, 51009, 1280, 50625, 50305, 1088, 52225, 3264, 3456, 52545, 3840, 53185, 52865, 3648, 2560, 51905, 52097, 2880, 51457, 2496, 2176, 51265, 55297, 6336, 6528, 55617, 6912, 56257, 55937, 6720, 7680, 57025, 57217, 8000, 56577, b.d5, 7296, 56385, 5120, 54465, 54657, 5440, 55041, 6080, 5760, 54849, 53761, 4800, 4992, 54081, 4352, 53697, 53377, 4160, SubBinId.BB2.USER_DATA_1, 12480, 12672, 61761, 13056, 62401, 62081, 12864, 13824, 63169, 63361, 14144, 62721, 13760, 13440, 62529, 15360, 64705, 64897, 15680, 65281, 16320, 16000, 65089, 64001, 15040, 15232, 64321, 14592, 63937, 63617, 14400, 10240, 59585, 59777, 10560, 60161, 11200, 10880, 59969, 60929, 11968, 12160, 61249, 11520, 60865, 60545, 11328, 58369, 9408, 9600, 58689, 9984, 59329, 59009, 9792, b.r5, 58049, 58241, 9024, 57601, 8640, 8320, 57409, 40961, 24768, 24960, 41281, 25344, 41921, 41601, 25152, 26112, 42689, 42881, 26432, 42241, 26048, 25728, 42049, 27648, 44225, 44417, 27968, 44801, 28608, 28288, 44609, 43521, 27328, 27520, 43841, 26880, 43457, 43137, 26688, 30720, 47297, 47489, 31040, 47873, 31680, 31360, 47681, 48641, 32448, 32640, 48961, DfuConstants.MAX_CONNECTION_LOCK_TIMEOUT, 48577, 48257, 31808, 46081, 29888, 30080, 46401, 30464, 47041, 46721, 30272, 29184, 45761, 45953, 29504, 45313, 29120, 28800, 45121, 20480, 37057, 37249, 20800, 37633, 21440, 21120, 37441, 38401, 22208, 22400, 38721, 21760, 38337, 38017, 21568, 39937, 23744, 23936, 40257, 24320, 40897, 40577, 24128, 23040, 39617, 39809, 23360, 39169, 22976, 22656, 38977, 34817, 18624, 18816, 35137, 19200, 35777, 35457, 19008, 19968, 36545, 36737, 20288, 36097, 19904, 19584, 35905, 17408, 33985, 34177, 17728, 34561, 18368, 18048, 34369, 33281, 17088, 17280, 33601, 16640, 33217, 32897, 16448};

    /* renamed from: a  reason: collision with root package name */
    public final File f12011a;
    public final Bitmap b;
    public final Bitmap c;
    public final DialDrawer.Position d;
    public final boolean e;
    public File f;
    public boolean g;
    public boolean h;
    public int i = -1;

    /* loaded from: classes11.dex */
    public static class WriterException extends Exception {
        public static final int ERROR_BACKGROUND_ACCESS = 2;
        public static final int ERROR_BACKGROUND_SIZE = 3;
        public static final int ERROR_FILE_ACCESS = 0;
        public static final int ERROR_FILE_SIZE = 1;
        public static final int ERROR_PREVIEW_ACCESS = 4;
        public static final int ERROR_PREVIEW_SIZE = 5;
        public static final int ERROR_UNKNOWN = -1;

        /* renamed from: a  reason: collision with root package name */
        private final int f12012a;

        public WriterException(int i) {
            super("Error code:" + i);
            this.f12012a = i;
        }

        public WriterException(int i, Throwable th) {
            super("Error code:" + i, th);
            this.f12012a = i;
        }

        public int getErrorCode() {
            return this.f12012a;
        }
    }

    /* loaded from: classes11.dex */
    public enum a {
        BACKGROUND,
        PREVIEW
    }

    public DialWriter(File file, Bitmap bitmap, Bitmap bitmap2, DialDrawer.Position position, boolean z) {
        this.f12011a = file;
        this.b = bitmap;
        this.c = bitmap2;
        this.d = position;
        this.e = z;
    }

    public static int a(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            i = j[(i ^ bArr[i2 + i4]) & 255] ^ (i >> 8);
        }
        return i;
    }

    public static long b(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static void c(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    fileInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static void d(RandomAccessFile randomAccessFile) throws IOException {
        long length = randomAccessFile.length();
        WristbandLog.w("check_crc16 fileSize:%d", Long.valueOf(length));
        int i = (int) (length - 512);
        byte[] bArr = new byte[i];
        randomAccessFile.seek(512L);
        randomAccessFile.readFully(bArr, 0, i);
        for (int i2 = 372; i2 < 404; i2++) {
            bArr[i2] = -1;
        }
        int i3 = (int) ((length + 16) - 1536);
        int a2 = a(a(0, bArr, 12, 740), bArr, 1008, i3);
        if (a2 == 0) {
            bArr[372] = 0;
            a2 = a(a(0, bArr, 12, 740), bArr, 1008, i3);
        }
        randomAccessFile.seek(518L);
        randomAccessFile.write(new byte[]{(byte) (a2 & 255), (byte) ((a2 >> 8) & 255)});
    }

    public static void e(RandomAccessFile randomAccessFile, DialDrawer.Position position) throws IOException {
        WristbandLog.i("modifyStylePosition to %s", position.getName());
        randomAccessFile.seek(1572L);
        randomAccessFile.write(new byte[]{0, 0, 0, 0});
        if (position == DialDrawer.Position.BOTTOM) {
            return;
        }
        byte[] bArr = new byte[4];
        randomAccessFile.seek(1612L);
        randomAccessFile.read(bArr);
        int h = h(bArr, 0);
        int h2 = h(bArr, 2);
        WristbandLog.i("modifyStylePosition old[%d,%d]", Integer.valueOf(h), Integer.valueOf(h2));
        randomAccessFile.seek(position == DialDrawer.Position.TOP ? 1732L : position == DialDrawer.Position.LEFT ? 1736L : 1740L);
        randomAccessFile.read(bArr);
        int h3 = h(bArr, 0);
        int h4 = h(bArr, 2);
        WristbandLog.i("modifyStylePosition new[%d,%d]", Integer.valueOf(h3), Integer.valueOf(h4));
        int i = h3 - h;
        int i2 = h4 - h2;
        randomAccessFile.seek(1612L);
        randomAccessFile.write(bArr);
        long[] jArr = {1616, 1628, 1652};
        for (int i3 = 0; i3 < 3; i3++) {
            long j2 = jArr[i3];
            randomAccessFile.seek(j2);
            randomAccessFile.read(bArr);
            int h5 = h(bArr, 0) + i;
            int h6 = h(bArr, 2) + i2;
            bArr[0] = (byte) (h5 & 255);
            bArr[1] = (byte) ((h5 >> 8) & 255);
            bArr[2] = (byte) (h6 & 255);
            bArr[3] = (byte) ((h6 >> 8) & 255);
            randomAccessFile.seek(j2);
            randomAccessFile.write(bArr);
        }
    }

    public static void f(RandomAccessFile randomAccessFile, a aVar, Bitmap bitmap, boolean z) throws IOException, WriterException {
        int h;
        int i;
        Bitmap createBitmap;
        Canvas canvas;
        Paint paint;
        Bitmap bitmap2 = bitmap;
        a aVar2 = a.BACKGROUND;
        String str = aVar == aVar2 ? "Background" : "Preview";
        byte[] bArr = new byte[4];
        randomAccessFile.seek(1912L);
        randomAccessFile.read(bArr);
        long b = b(bArr, 0) + 1536;
        WristbandLog.i("modifyBitmap[%s] bmpAddrOffset:%d", str, Long.valueOf(b));
        randomAccessFile.seek(aVar == aVar2 ? 1830L : 1880L);
        randomAccessFile.read(bArr);
        WristbandLog.i("modifyBitmap[%s] bmpNum:%d", str, Integer.valueOf(h(bArr, 0)));
        long j2 = (h * 8) + 1920;
        randomAccessFile.seek(j2);
        randomAccessFile.read(bArr);
        int h2 = h(bArr, 0);
        int h3 = h(bArr, 2);
        WristbandLog.i("modifyBitmap[%s] need size[%d,%d]", str, Integer.valueOf(h2), Integer.valueOf(h3));
        if (bitmap2 == null || bitmap.isRecycled()) {
            throw new WriterException(aVar == aVar2 ? 2 : 4);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        WristbandLog.i("modifyBitmap[%s] bitmap size[%d,%d]", str, Integer.valueOf(width), Integer.valueOf(height));
        if (h2 != width || h3 != height) {
            if (!z) {
                throw new WriterException(aVar != aVar2 ? 5 : 3);
            }
            try {
                createBitmap = Bitmap.createBitmap(h2, h3, Bitmap.Config.RGB_565);
                canvas = new Canvas(createBitmap);
                float f = h2;
                float f2 = width;
                float f3 = h3;
                float f4 = height;
                float max = Math.max(f / f2, f3 / f4);
                canvas.scale(max, max);
                canvas.translate((f - (f2 * max)) / 2.0f, (f3 - (max * f4)) / 2.0f);
                paint = new Paint();
            } catch (Exception e) {
                e = e;
                i = 1;
            }
            try {
                paint.setFilterBitmap(true);
                canvas.drawBitmap(bitmap2, new Matrix(), paint);
                bitmap2 = createBitmap;
            } catch (Exception e2) {
                e = e2;
                i = 1;
                Object[] objArr = new Object[i];
                objArr[0] = str;
                WristbandLog.w(e, "Scale Bitmap[%s] failed", objArr);
                throw new WriterException(aVar == a.BACKGROUND ? 2 : 4);
            }
        }
        randomAccessFile.seek(j2 + 4);
        randomAccessFile.read(bArr);
        long b2 = b(bArr, 0) + b;
        WristbandLog.i("modifyBitmap[%s] bmpOffset:%s", str, Long.valueOf(b2));
        int[] iArr = new int[h2];
        byte[] bArr2 = new byte[h2 * 2];
        int i2 = 0;
        while (i2 < h3) {
            int i3 = h3;
            int i4 = h2;
            try {
                bitmap2.getPixels(iArr, 0, i4, 0, i2, i4, 1);
                h2 = i4;
                for (int i5 = 0; i5 < h2; i5++) {
                    int i6 = iArr[i5];
                    int i7 = i5 * 2;
                    bArr2[i7] = (byte) ((((i6 >> 19) & 31) << 3) | ((i6 >> 13) & 7));
                    bArr2[i7 + 1] = (byte) ((((i6 >> 10) & 7) << 5) | ((i6 >> 3) & 31));
                }
                randomAccessFile.seek((i2 * 4) + b2);
                randomAccessFile.read(bArr);
                randomAccessFile.seek(b(bArr, 0) + b + 4);
                randomAccessFile.write(bArr2);
                i2++;
                h3 = i3;
            } catch (Exception e3) {
                WristbandLog.w(e3, "Get Bitmap Pixels [%s] failed", str);
                throw new WriterException(aVar == a.BACKGROUND ? 2 : 4);
            }
        }
    }

    public static short g(byte[] bArr, int i) {
        return (short) (bArr[i] & 255);
    }

    public static int h(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    public File execute() throws WriterException {
        return execute(null);
    }

    public File execute(Disposable disposable) throws WriterException {
        File file = this.f12011a;
        if (file == null) {
            WristbandLog.w("mBinFile is null", new Object[0]);
            throw new WriterException(0, new NullPointerException("mBinFile is null"));
        }
        if (this.f != null) {
            try {
                WristbandLog.i("copy bin file from %s to %s", file.getAbsoluteFile(), this.f.getAbsolutePath());
                c(this.f12011a, this.f);
                file = this.f;
            } catch (IOException e) {
                WristbandLog.w("Copy bin failed", e);
                throw new WriterException(0, e);
            } catch (Exception e2) {
                WristbandLog.w("Copy bin failed", e2);
                throw new WriterException(-1, e2);
            }
        }
        WristbandLog.i("start modify bin file:%s", file.getAbsolutePath());
        if (disposable != null && disposable.isDisposed()) {
            WristbandLog.w("canceled after start", new Object[0]);
            return null;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                if (this.e) {
                    WriteGUI.c(randomAccessFile, this.b, this.c, this.d, this.g, this.h, this.i);
                } else {
                    e(randomAccessFile, this.d);
                    if (disposable != null && disposable.isDisposed()) {
                        WristbandLog.w("canceled after modifyStylePosition", new Object[0]);
                        randomAccessFile.close();
                        return null;
                    }
                    f(randomAccessFile, a.BACKGROUND, this.b, this.g);
                    if (disposable != null && disposable.isDisposed()) {
                        WristbandLog.w("canceled after modifyBackgroundBitmap", new Object[0]);
                        randomAccessFile.close();
                        return null;
                    }
                    f(randomAccessFile, a.PREVIEW, this.c, this.h);
                    if (disposable != null && disposable.isDisposed()) {
                        WristbandLog.w("canceled after modifyPreviewBitmap", new Object[0]);
                        randomAccessFile.close();
                        return null;
                    }
                    d(randomAccessFile);
                }
                randomAccessFile.close();
                return file;
            } finally {
            }
        } catch (WriterException e3) {
            throw e3;
        } catch (EOFException e4) {
            e = e4;
            WristbandLog.w("Modify bin failed", e);
            throw new WriterException(1, e);
        } catch (IOException e5) {
            WristbandLog.w("Modify bin failed", e5);
            throw new WriterException(0, e5);
        } catch (ArrayIndexOutOfBoundsException e6) {
            e = e6;
            WristbandLog.w("Modify bin failed", e);
            throw new WriterException(1, e);
        } catch (Exception e7) {
            WristbandLog.w("Modify bin failed", e7);
            throw new WriterException(-1, e7);
        }
    }

    public void setAutoScaleBackground(boolean z) {
        this.g = z;
    }

    public void setAutoScalePreview(boolean z) {
        this.h = z;
    }

    public void setCopyFile(File file) {
        this.f = file;
    }

    public void setCustomDialNum(int i) {
        if (i < 0 || i > 16777216) {
            throw new IllegalArgumentException("dialNum limit in [0-16,777,216]");
        }
        this.i = i;
    }
}
