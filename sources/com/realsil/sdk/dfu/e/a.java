package com.realsil.sdk.dfu.e;

import android.content.Context;
import androidx.core.view.ViewCompat;
import com.realsil.sdk.core.logger.ZLogger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
/* loaded from: classes12.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public String f13600a;
    public long b;
    public int c;
    public int d;

    public a(String str, int i, int i2, int i3) {
        this.f13600a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static a a(String str, int i, byte[] bArr) {
        a aVar = new a(str, i, ((bArr[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[2] << 16) & 16711680) | ((bArr[1] << 8) & 65280) | (bArr[0] & 255), bArr[4] & 1);
        ZLogger.v(aVar.toString());
        return aVar;
    }

    public InputStream b() {
        FileInputStream fileInputStream;
        IOException e;
        FileNotFoundException e2;
        try {
            fileInputStream = new FileInputStream(this.f13600a);
            try {
                fileInputStream.skip(this.b);
            } catch (FileNotFoundException e3) {
                e2 = e3;
                ZLogger.w(String.format(Locale.US, "FileNotFoundException:%s, %d", this.f13600a, Long.valueOf(this.b)));
                e2.printStackTrace();
                return fileInputStream;
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                return fileInputStream;
            }
        } catch (FileNotFoundException e5) {
            fileInputStream = null;
            e2 = e5;
        } catch (IOException e6) {
            fileInputStream = null;
            e = e6;
        }
        return fileInputStream;
    }

    public int c() {
        return this.c;
    }

    public long d() {
        return this.b;
    }

    public String toString() {
        return String.format(Locale.US, "skipOffset=%d, length=0x%02X, budRole=0x%02X", Long.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d));
    }

    public int a() {
        return this.d;
    }

    public InputStream a(Context context) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(this.f13600a);
            inputStream.skip(this.b);
            return inputStream;
        } catch (FileNotFoundException e) {
            ZLogger.w(String.format(Locale.US, "FileNotFoundException:%s, %d", this.f13600a, Long.valueOf(this.b)));
            e.printStackTrace();
            return inputStream;
        } catch (IOException e2) {
            e2.printStackTrace();
            return inputStream;
        }
    }
}
