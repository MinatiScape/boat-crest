package com.realsil.sdk.dfu.image.stream;

import androidx.core.view.ViewCompat;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.RtkDfu;
import com.realsil.sdk.dfu.g.a;
import com.touchgui.sdk.TGEventListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import kotlin.UShort;
/* loaded from: classes12.dex */
public abstract class BaseBinInputStream extends BufferedInputStream {
    public static final int AUTH_HEADER_BUF_SIZE = 304;
    public static final int COMPARE_VERSION_EQUAL = 0;
    public static final int COMPARE_VERSION_HIGH = 1;
    public static final int COMPARE_VERSION_LOW = -1;
    public static final int DFU_HEADER_BUF_SIZE = 12;
    public static final int HEADER_SIZE = 12;
    public static final int IMAGE_SIZE_MECHANISM_IMAGE_HEADER = 0;
    public static final int IMAGE_SIZE_MECHANISM_MP_HEADER_DATA_LENGTH = 1;
    public static final int IMAGE_SIZE_MECHANISM_MP_HEADER_IMAGE_SIZE = 2;
    public static int MPHEADER_PARSE_FORMAT = 1;
    public static int MPHEADER_PARSE_HEADER = 0;
    public static int MPHEADER_PARSE_MARK = 1;
    public static final int MP_HEADER_BUF_SIZE = 512;
    public static final int PACKET_SIZE_DEF = 20;
    public static final int SHA256_LENGTH = 32;

    /* renamed from: a  reason: collision with root package name */
    public boolean f13606a;
    public boolean b;
    public final byte[] c;
    public List<a> d;
    public int e;
    public int f;
    public final byte[] g;
    public int h;
    public boolean i;
    public byte icType;
    public int imageVersion;
    public int j;
    public int k;
    public int l;
    public int m;
    public String n;
    public int o;
    public int otaTempBufferCheckOrder;
    public int otaVersion;
    public int p;
    public byte q;
    public short r;
    public byte[] s;
    public byte[] t;
    public int u;
    public final int v;
    public int versionCheckOrder;
    public int w;
    public int x;
    public int y;

    public BaseBinInputStream(InputStream inputStream) throws IOException {
        this(inputStream, 20);
    }

    public static int toUnsigned(short s) {
        return s & UShort.MAX_VALUE;
    }

    public boolean a() {
        List<a> list = this.d;
        return list != null && list.size() > 0;
    }

    public final void b() throws IOException {
        if (MPHEADER_PARSE_FORMAT == MPHEADER_PARSE_MARK && markSupported()) {
            f();
        } else {
            g();
        }
    }

    public void c() throws IOException {
        if (this.b) {
            ZLogger.v("dfuHeader=" + DataConverter.bytes2Hex(this.s));
        }
    }

    public final void d() {
        if (this.b) {
            ZLogger.v("mpHeaderBuf=" + DataConverter.bytes2Hex(this.c));
        }
        List<a> a2 = a.a(this.c);
        this.d = a2;
        if (a2 != null && a2.size() > 0) {
            for (a aVar : this.d) {
                byte[] a3 = aVar.a();
                if (a3 != null && a3.length > 0) {
                    int b = aVar.b();
                    if (b != 1) {
                        if (b != 2) {
                            if (b == 3) {
                                this.n = a(a3);
                            } else if (b != 4) {
                                switch (b) {
                                    case 17:
                                        this.otaVersion = a3[0] & 255;
                                        continue;
                                    case 18:
                                        if (a3.length >= 2) {
                                            this.h = ((a3[1] << 8) & 65280) | (a3[0] & 255);
                                            this.i = true;
                                            break;
                                        } else {
                                            continue;
                                        }
                                    case 19:
                                        if (a3.length >= 4) {
                                            this.o = ((a3[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((a3[2] << 16) & 16711680) | ((a3[1] << 8) & 65280) | (a3[0] & 255);
                                            break;
                                        } else {
                                            continue;
                                        }
                                    case 20:
                                        if (a3.length >= 4) {
                                            int i = ((a3[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((a3[2] << 16) & 16711680) | ((a3[1] << 8) & 65280) | (a3[0] & 255);
                                            this.f = i;
                                            if (this.k < 2) {
                                                this.k = 2;
                                                this.j = i - 12;
                                                break;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            continue;
                                        }
                                    case 21:
                                        if (a3.length >= 2) {
                                            this.p = ((a3[1] << 8) & 65280) | (a3[0] & 255);
                                            break;
                                        } else {
                                            continue;
                                        }
                                    case 22:
                                        if (a3.length >= 4) {
                                            this.imageVersion = ((a3[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((a3[2] << 16) & 16711680) | ((a3[1] << 8) & 65280) | (a3[0] & 255);
                                            break;
                                        } else {
                                            continue;
                                        }
                                }
                            } else if (a3.length >= 4) {
                                int i2 = ((a3[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((a3[2] << 16) & 16711680) | ((a3[1] << 8) & 65280) | (a3[0] & 255);
                                this.e = i2;
                                if (this.k < 1) {
                                    this.k = 1;
                                    this.j = i2 - 12;
                                }
                            }
                        } else if (a3.length != 4) {
                            ZLogger.d("invalid sub header, " + aVar.toString());
                            this.d = null;
                        } else {
                            this.m = ((a3[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((a3[2] << 16) & 16711680) | ((a3[1] << 8) & 65280) | (a3[0] & 255);
                        }
                    } else if (a3.length != 2) {
                        ZLogger.d("invalid sub header, " + aVar.toString());
                        this.d = null;
                    } else {
                        this.l = ((a3[1] << 8) & 65280) | (a3[0] & 255);
                    }
                }
            }
            if (this.f13606a) {
                Locale locale = Locale.US;
                ZLogger.d(String.format(locale, "MpHeader: binId=0x%04x, binVersion=0x%04x, partNumber=%s, mpDataLength=0x%08x(%d), otaVersion=0x%02x,  mImageSizeMechanism=0x%02x", Integer.valueOf(this.l), Integer.valueOf(this.m), this.n, Integer.valueOf(this.e), Integer.valueOf(this.e), Integer.valueOf(this.otaVersion), Integer.valueOf(this.k)));
                if (this.otaVersion > 0) {
                    ZLogger.d(String.format(locale, "imageId=0x%04x, flashAddr=0x%08x, mpImageSize=0x%08x(%d), secureVersion=0x%04x, imageVersion=0x%08x", Integer.valueOf(this.h), Integer.valueOf(this.o), Integer.valueOf(this.f), Integer.valueOf(this.f), Integer.valueOf(this.p), Integer.valueOf(this.imageVersion)));
                    return;
                }
                return;
            }
            return;
        }
        ZLogger.v("not found mp header");
    }

    public void e() throws IOException {
        if (this.b) {
            ZLogger.v("headBuf=" + DataConverter.bytes2Hex(this.g));
        }
    }

    public final void f() throws IOException {
        if (this.b) {
            ZLogger.v("markSupported");
        }
        mark(0);
        read(this.c, 0, 512);
        d();
        if (!a()) {
            ZLogger.v(this.b, "reset to begin");
            reset();
        }
        ZLogger.v(this.b, String.format(Locale.US, "isNeedReadDfuHeader()=%b, available()=%d", Boolean.valueOf(isNeedReadDfuHeader()), Integer.valueOf(available())));
        if (isNeedReadDfuHeader() && available() >= 316) {
            mark(0);
            try {
                skip(304L);
            } catch (IOException unused) {
                ZLogger.w("skip error");
            }
            read(this.s, 0, 12);
            c();
            reset();
            read(this.g, 0, 12);
        } else {
            read(this.g, 0, 12);
            System.arraycopy(this.g, 0, this.s, 0, 12);
        }
        e();
    }

    public final void g() throws IOException {
        read(this.g, 0, 12);
        byte[] bArr = this.g;
        if (bArr[0] == 1 && bArr[1] == 0 && bArr[2] == 2) {
            System.arraycopy(bArr, 0, this.c, 0, 12);
            read(this.c, 12, 500);
            d();
            read(this.g, 0, 12);
        }
        ZLogger.v(this.b, String.format(Locale.US, "isNeedReadDfuHeader()=%b, available()=%d", Boolean.valueOf(isNeedReadDfuHeader()), Integer.valueOf(available())));
        if (isNeedReadDfuHeader() && available() >= 316) {
            byte[] bArr2 = new byte[304];
            System.arraycopy(this.g, 0, bArr2, 0, 12);
            read(bArr2, 12, TGEventListener.OTA_COMPLETED);
            read(this.s, 0, 12);
            c();
        } else {
            System.arraycopy(this.g, 0, this.s, 0, 12);
        }
        e();
    }

    public int getActiveCompareVersionFlag() {
        return this.x;
    }

    public int getBinId() {
        return this.l;
    }

    public int getBinVersion() {
        return this.m;
    }

    public byte[] getDfuHeader() {
        return this.s;
    }

    public int getFlashAddr() {
        return this.o;
    }

    public byte[] getHeaderBuf() {
        return this.g;
    }

    public byte getIcType() {
        return this.icType;
    }

    public int getImageId() {
        return this.h;
    }

    public int getImageSize() {
        return this.j;
    }

    public int getImageVersion() {
        return this.imageVersion;
    }

    public int getInactiveCompareVersionFlag() {
        return this.y;
    }

    public int getOtaVersion() {
        return this.otaVersion;
    }

    public int getSecureVersion() {
        return this.p;
    }

    public byte[] getSha256() {
        return this.t;
    }

    public int getTotalImageSize() {
        return this.j + 12;
    }

    public boolean isNeedReadDfuHeader() {
        return false;
    }

    public void parseImageHeaderEx() {
        try {
            int i = this.icType == 11 ? 260 : 360;
            int i2 = i + 32;
            byte[] bArr = new byte[i2];
            read(bArr, 0, i2);
            byte[] bArr2 = new byte[32];
            this.t = bArr2;
            System.arraycopy(bArr, i, bArr2, 0, 32);
            boolean z = this.b;
            ZLogger.v(z, "sha256=" + DataConverter.bytes2Hex(this.t));
        } catch (Exception e) {
            ZLogger.e(e.toString());
        }
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() {
        throw new UnsupportedOperationException("Use readPacket() method instead");
    }

    public int readPacket(byte[] bArr) throws IOException {
        return read(bArr, this.v);
    }

    public int remainNumInPackets(int i) {
        int remainSizeInBytes = remainSizeInBytes();
        return (remainSizeInBytes / i) + (remainSizeInBytes % i > 0 ? 1 : 0);
    }

    public int remainSizeInBytes() {
        return this.j - this.w;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        super.reset();
        this.w = 0;
    }

    public void setActiveCompareVersionFlag(int i) {
        this.x = i;
    }

    public void setInactiveCompareVersionFlag(int i) {
        this.y = i;
    }

    public void setSha256(byte[] bArr) {
        this.t = bArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Locale locale = Locale.US;
        sb.append(String.format(locale, "otaVersion=0x%02X, icType=0x%02X, imageId=0x%04X,binId=0x%04X, imageVersion=0x%08X, imageSize=0x%08X(%d)", Integer.valueOf(this.otaVersion), Byte.valueOf(this.icType), Integer.valueOf(this.h), Integer.valueOf(this.l), Integer.valueOf(this.imageVersion), Integer.valueOf(this.j), Integer.valueOf(this.j)));
        sb.append(String.format(locale, ", sha256=%s, activeCompareVersionFlag=%d, inactiveCompareVersionFlag=%d,otaTempBufferCheckOrder=%d", DataConverter.bytes2Hex(this.t), Integer.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.otaTempBufferCheckOrder)));
        return sb.toString();
    }

    public BaseBinInputStream(InputStream inputStream, int i) throws IOException {
        super(new BufferedInputStream(inputStream));
        this.f13606a = true;
        this.b = RtkDfu.VDBG;
        this.otaVersion = 0;
        this.k = 0;
        this.u = 255;
        this.x = 1;
        this.y = 1;
        this.versionCheckOrder = 254;
        this.otaTempBufferCheckOrder = 1;
        this.f13606a = RtkDfu.DEBUG_ENABLE;
        this.v = i;
        this.c = new byte[512];
        this.g = new byte[12];
        this.s = new byte[12];
        this.w = 0;
        b();
    }

    public final String a(byte[] bArr) {
        int length = bArr.length;
        for (int length2 = bArr.length - 1; length2 >= 0; length2--) {
            if (bArr[length2] == -1 || bArr[length2] == 0) {
                length--;
            }
        }
        try {
            return new String(bArr, 0, length, "ascii");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, bArr.length);
    }

    public int read(byte[] bArr, int i) throws IOException {
        int read = read(bArr, 0, i);
        if (read > 0) {
            this.w += read;
        }
        return read;
    }
}
