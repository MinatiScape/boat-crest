package com.realsil.sdk.dfu.f;

import android.content.Context;
import androidx.core.view.ViewCompat;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.RtkDfu;
import com.realsil.sdk.dfu.image.pack.SubFileInfo;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public class a extends BufferedInputStream {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f13602a = RtkDfu.VDBG;
    public int b;
    public int c;
    public byte[] d;
    public int e;
    public int f;
    public boolean g;
    public boolean h;
    public int i;
    public byte[] j;
    public int k;
    public int l;
    public ArrayList<SubFileInfo> m;
    public String n;
    public long o;

    public a(String str, long j, InputStream inputStream) throws IOException {
        super(inputStream);
        this.f = 1;
        if (available() >= 44) {
            this.n = str;
            this.o = j;
            e();
            f();
            close();
            return;
        }
        throw new IOException("The input file size is less to 44, please check!");
    }

    public static a a(String str) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
            a a2 = a(str, 0L, bufferedInputStream);
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a2;
        } catch (IOException e2) {
            if (f13602a) {
                ZLogger.v(e2.toString());
                return null;
            }
            return null;
        }
    }

    public SubFileInfo b(int i) {
        Iterator<SubFileInfo> it = this.m.iterator();
        while (it.hasNext()) {
            SubFileInfo next = it.next();
            if (next.bitNumber == i) {
                return next;
            }
        }
        return null;
    }

    public ArrayList<SubFileInfo> c() {
        return this.m;
    }

    public boolean d() {
        return this.g;
    }

    public final void e() throws IOException {
        byte[] bArr = new byte[40];
        read(bArr, 0, 40);
        if (f13602a) {
            ZLogger.v(String.format(Locale.US, "PackHeader:(%d)%s", 40, DataConverter.bytes2Hex(bArr)));
        }
        int i = (((bArr[1] << 8) & 65280) | (bArr[0] & 255)) & 65535;
        this.b = i;
        if (i == 19783) {
            this.c = ((bArr[5] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[4] << 16) & 16711680) | ((bArr[3] << 8) & 65280) | (bArr[2] & 255);
            byte[] bArr2 = new byte[32];
            this.d = bArr2;
            System.arraycopy(bArr, 6, bArr2, 0, 32);
            int i2 = ((bArr[38] & 255) | (65280 & (bArr[39] << 8))) & 65535;
            this.e = i2;
            int i3 = i2 & 15;
            this.f = i3;
            this.h = ((byte) ((i2 >> 7) & 1)) == 1;
            this.i = (i2 >> 8) & 255;
            if (i3 <= 1) {
                this.g = true;
                this.k = 4;
            } else if (i3 == 2) {
                this.g = true;
                this.k = 32;
            } else {
                this.g = ((byte) ((i2 >> 6) & 1)) == 1;
                this.k = 32;
            }
            int i4 = this.k;
            byte[] bArr3 = new byte[i4];
            this.j = bArr3;
            read(bArr3, 0, i4);
            this.o += 40 + this.k;
            return;
        }
        throw new IOException(String.format("The signature(0x%04X) is not right", Integer.valueOf(this.b)));
    }

    public final void f() throws IOException {
        byte[] bArr;
        int i;
        this.l = 0;
        this.m = new ArrayList<>();
        byte[] bArr2 = this.j;
        int length = bArr2.length;
        for (byte b : bArr2) {
            for (int i2 = 0; i2 < 8; i2++) {
                if (((byte) (((byte) (b >> i2)) & 1)) == 1) {
                    this.l++;
                }
            }
        }
        long j = this.o + (this.l * 12);
        int i3 = 0;
        for (byte b2 : this.j) {
            int i4 = 0;
            while (i4 < 8) {
                if (((byte) (((byte) (b2 >> i4)) & 1)) == 1) {
                    byte[] bArr3 = new byte[12];
                    read(bArr3, 0, 12);
                    i = i4;
                    SubFileInfo builder = SubFileInfo.builder(this.i, this.n, i3, this.k * 4, j, bArr3);
                    if (f13602a) {
                        ZLogger.v(builder.toString());
                    }
                    this.m.add(builder);
                    j += builder.size;
                } else {
                    i = i4;
                }
                i3++;
                i4 = i + 1;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (f13602a) {
            sb.append(String.format("signature==0x%04X", Integer.valueOf(this.b)));
        }
        sb.append(String.format(Locale.US, "size=0x%08x(%d)", Integer.valueOf(this.c), Integer.valueOf(this.c)) + String.format("packVersion=0x%02X, dualBank=%b", Integer.valueOf(this.f), Boolean.valueOf(this.h)) + String.format(", icType=0x%02X", Integer.valueOf(this.i)) + String.format(", subFileNum=0x%04X, subFileIndicator=%s", Integer.valueOf(this.l), DataConverter.bytes2Hex(this.j)));
        return sb.toString();
    }

    public List<SubFileInfo> c(int i) {
        ArrayList arrayList = new ArrayList();
        ArrayList<SubFileInfo> arrayList2 = this.m;
        if (arrayList2 != null && arrayList2.size() > 0) {
            int i2 = this.f >= 2 ? 128 : 16;
            if (i == 1) {
                Iterator<SubFileInfo> it = this.m.iterator();
                while (it.hasNext()) {
                    SubFileInfo next = it.next();
                    int i3 = next.bitNumber;
                    if (i3 >= i2) {
                        arrayList.add(next);
                    } else if (this.i == 11 && i3 == 24) {
                        arrayList.add(next);
                    }
                }
            } else {
                Iterator<SubFileInfo> it2 = this.m.iterator();
                while (it2.hasNext()) {
                    SubFileInfo next2 = it2.next();
                    if (next2.bitNumber < i2) {
                        arrayList.add(next2);
                    }
                }
            }
        }
        return arrayList;
    }

    public BaseBinInputStream b(int i, int i2) {
        ArrayList<SubFileInfo> arrayList = this.m;
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<SubFileInfo> it = this.m.iterator();
            while (it.hasNext()) {
                SubFileInfo next = it.next();
                if (next.bitNumber == i) {
                    return next.getBinInputStream(next.icType);
                }
            }
        }
        return null;
    }

    public static a a(String str, long j, InputStream inputStream) {
        a aVar;
        try {
            aVar = new a(str, j, inputStream);
        } catch (IOException e) {
            if (f13602a) {
                ZLogger.d(e.toString());
            }
            aVar = null;
        }
        try {
            inputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return aVar;
    }

    public int b() {
        return this.i;
    }

    public a(Context context, String str, InputStream inputStream, long j) throws IOException {
        super(inputStream);
        this.f = 1;
        if (available() >= 44) {
            this.n = str;
            this.o = j;
            e();
            a(context);
            close();
            return;
        }
        throw new IOException("The input file size is less to 44, please check!");
    }

    public static a a(Context context, String str, InputStream inputStream, long j) {
        a aVar = null;
        if (inputStream == null) {
            ZLogger.w("InputStream can not be null");
            return null;
        }
        try {
            aVar = new a(context, str, inputStream, j);
        } catch (IOException e) {
            if (f13602a) {
                ZLogger.d(e.toString());
            }
        }
        try {
            inputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return aVar;
    }

    public final void a(Context context) throws IOException {
        byte[] bArr;
        int i;
        this.l = 0;
        this.m = new ArrayList<>();
        byte[] bArr2 = this.j;
        int length = bArr2.length;
        for (byte b : bArr2) {
            for (int i2 = 0; i2 < 8; i2++) {
                if (((byte) (((byte) (b >> i2)) & 1)) == 1) {
                    this.l++;
                }
            }
        }
        long j = this.o + (this.l * 12);
        int i3 = 0;
        for (byte b2 : this.j) {
            int i4 = 0;
            while (i4 < 8) {
                if (((byte) (((byte) (b2 >> i4)) & 1)) == 1) {
                    byte[] bArr3 = new byte[12];
                    read(bArr3, 0, 12);
                    i = i4;
                    SubFileInfo builderFromAssets = SubFileInfo.builderFromAssets(context, this.i, this.n, i3, this.k * 4, j, bArr3);
                    if (f13602a) {
                        ZLogger.v(builderFromAssets.toString());
                    }
                    this.m.add(builderFromAssets);
                    j += builderFromAssets.size;
                } else {
                    i = i4;
                }
                i3++;
                i4 = i + 1;
            }
        }
    }

    public SubFileInfo a(int i) {
        Iterator<SubFileInfo> it = this.m.iterator();
        while (it.hasNext()) {
            SubFileInfo next = it.next();
            if (next.binId == i) {
                return next;
            }
        }
        return null;
    }

    public boolean a(int i, int i2) {
        BaseBinInputStream b = b(i, i2);
        if (b == null) {
            return false;
        }
        try {
            b.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    public int a() {
        return this.k * 4;
    }
}
