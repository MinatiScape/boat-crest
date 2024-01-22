package com.realsil.sdk.dfu.e;

import android.content.Context;
import androidx.core.view.ViewCompat;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.exception.LoadFileException;
import com.realsil.sdk.dfu.image.LoadParams;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
/* loaded from: classes12.dex */
public class b extends BufferedInputStream {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f13601a = RtkCore.DEBUG;
    public static boolean b = RtkCore.VDBG;
    public int c;
    public int d;
    public int e;
    public ArrayList<a> f;
    public String g;

    public b(String str, InputStream inputStream) throws IOException {
        super(inputStream);
        if (available() >= 12) {
            this.g = str;
            return;
        }
        throw new IOException("The input file size is less to 12, please check!");
    }

    public static b a(LoadParams loadParams) {
        InputStream inputStream;
        b bVar = null;
        if (loadParams == null) {
            return null;
        }
        if (loadParams.c() == 1) {
            Context a2 = loadParams.a();
            if (a2 == null) {
                ZLogger.d("please set the context first.");
                return null;
            }
            try {
                inputStream = a2.getAssets().open(loadParams.d());
            } catch (IOException e) {
                ZLogger.w(e.toString());
                return null;
            }
        } else {
            try {
                inputStream = new BufferedInputStream(new FileInputStream(loadParams.d()));
            } catch (FileNotFoundException e2) {
                ZLogger.w(e2.toString());
                inputStream = null;
            }
        }
        if (inputStream == null) {
            return null;
        }
        try {
            b bVar2 = new b(loadParams.d(), inputStream);
            if (bVar2.a()) {
                bVar = bVar2;
            } else {
                bVar2.close();
            }
            try {
                inputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return bVar;
        } catch (IOException e4) {
            ZLogger.v(e4.toString());
            return null;
        }
    }

    public static com.realsil.sdk.dfu.f.a b(LoadParams loadParams) throws LoadFileException {
        com.realsil.sdk.dfu.f.a a2;
        Context a3 = loadParams.a();
        String d = loadParams.d();
        b a4 = a(loadParams);
        if (a4 == null) {
            ZLogger.v(b, "multiPackManager == null");
            try {
                a2 = com.realsil.sdk.dfu.f.a.a(a3, d, a3.getAssets().open(d), 0L);
                if (a2 != null) {
                    if (a2.d()) {
                        if (!loadParams.r()) {
                            ZLogger.w("not support pack file.");
                            throw new LoadFileException("not support pack file.", 4107);
                        }
                    } else {
                        ZLogger.w("pack for MP can not be OTA");
                        throw new LoadFileException("not support pack file.", LoadFileException.ERROR_PACK_MP_NOT_SUPPORTED);
                    }
                } else if (!loadParams.t()) {
                    ZLogger.w("not support single file.");
                    throw new LoadFileException("not support single file.", 4108);
                }
            } catch (IOException e) {
                ZLogger.w(e.toString());
                return null;
            }
        } else if (loadParams.n()) {
            int g = loadParams.g();
            ZLogger.v(a4.toString());
            a a5 = a4.a(g);
            if (a5 != null) {
                ZLogger.v(a5.toString());
                a2 = com.realsil.sdk.dfu.f.a.a(a3, d, a5.a(a3), a5.d());
                if (a2 != null) {
                    if (!a2.d()) {
                        ZLogger.w("pack for MP can not be OTA");
                        throw new LoadFileException("not support pack file.", LoadFileException.ERROR_PACK_MP_NOT_SUPPORTED);
                    }
                } else {
                    ZLogger.w("sub combined file must be a pack file, preferredBudRole=" + g);
                    throw new LoadFileException("sub combined file must be a pack file.", 4106);
                }
            } else {
                ZLogger.w("no bud item exist, preferredBudRole=" + g);
                throw new LoadFileException("no combine bud item exist.", 4105);
            }
        } else {
            ZLogger.w("not support combine pack file.");
            throw new LoadFileException("not support combine pack file.", 4103);
        }
        return a2;
    }

    public static com.realsil.sdk.dfu.f.a c(LoadParams loadParams) throws LoadFileException {
        com.realsil.sdk.dfu.f.a a2;
        loadParams.f();
        String d = loadParams.d();
        b a3 = a(loadParams);
        if (a3 == null) {
            ZLogger.v(b, "multiPackManager == null");
            a2 = com.realsil.sdk.dfu.f.a.a(d);
            if (a2 != null) {
                if (a2.d()) {
                    if (loadParams.r()) {
                        ZLogger.v(b, a2.toString());
                    } else {
                        ZLogger.w("not support pack file.");
                        throw new LoadFileException("not support pack file.", 4107);
                    }
                } else {
                    ZLogger.w("pack for MP can not be OTA");
                    throw new LoadFileException("not support pack file.", LoadFileException.ERROR_PACK_MP_NOT_SUPPORTED);
                }
            } else if (!loadParams.t()) {
                ZLogger.w("not support single file.");
                throw new LoadFileException("not support single file.", 4108);
            }
        } else if (loadParams.n()) {
            int g = loadParams.g();
            ZLogger.v(b, a3.toString());
            a a4 = a3.a(g);
            if (a4 != null) {
                ZLogger.v(b, a4.toString());
                a2 = com.realsil.sdk.dfu.f.a.a(d, a4.d(), a4.b());
                if (a2 != null) {
                    if (a2.d()) {
                        ZLogger.v(b, a2.toString());
                    } else {
                        ZLogger.w("pack for MP can not be OTA");
                        throw new LoadFileException("not support pack file.", LoadFileException.ERROR_PACK_MP_NOT_SUPPORTED);
                    }
                } else {
                    ZLogger.w("sub combined file must be a pack file, preferredBudRole=" + g);
                    throw new LoadFileException("sub combined file must be a pack file.", 4106);
                }
            } else {
                ZLogger.w("no bud item exist, preferredBudRole=" + g);
                throw new LoadFileException("no combine bud item exist.", 4105);
            }
        } else {
            ZLogger.w("not support combine pack file.");
            throw new LoadFileException("not support combine pack file.", 4103);
        }
        return a2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Multi-pack: signature=" + String.format("0x%04X", Integer.valueOf(this.c)) + ", version=" + String.format(Locale.US, "0x%08x(%d)", Integer.valueOf(this.d), Integer.valueOf(this.d)) + ", num=" + String.format("0x%04x", Integer.valueOf(this.e)));
        ArrayList<a> arrayList = this.f;
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<a> it = this.f.iterator();
            while (it.hasNext()) {
                sb.append("\n" + it.next().toString());
            }
        }
        return sb.toString();
    }

    public final boolean a() throws IOException {
        byte[] bArr = new byte[12];
        read(bArr, 0, 12);
        int i = ((bArr[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[2] << 16) & 16711680) | ((bArr[1] << 8) & 65280) | (bArr[0] & 255);
        this.c = i;
        if (i != -1768442424) {
            ZLogger.v(b, String.format("invalid multi-pack signature(0x%08X) ", Integer.valueOf(i)));
            return false;
        }
        this.d = ((bArr[7] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[6] << 16) & 16711680) | ((bArr[5] << 8) & 65280) | (bArr[4] & 255);
        this.e = (bArr[8] & 255) | ((bArr[11] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[10] << 16) & 16711680) | ((bArr[9] << 8) & 65280);
        this.f = new ArrayList<>();
        int i2 = (this.e * 8) + 12;
        byte[] bArr2 = new byte[8];
        for (int i3 = 0; i3 < this.e; i3++) {
            read(bArr2, 0, 8);
            a a2 = a.a(this.g, i2, bArr2);
            this.f.add(a2);
            i2 += a2.c();
        }
        return true;
    }

    public a a(int i) {
        ArrayList<a> arrayList = this.f;
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        Iterator<a> it = this.f.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (next.a() == i) {
                return next;
            }
        }
        return null;
    }
}
