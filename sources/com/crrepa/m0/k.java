package com.crrepa.m0;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
/* loaded from: classes9.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f7768a = new byte[ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED];
    public byte[] b = new byte[ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED];

    public int a(byte b, byte[] bArr, int i) {
        int i2 = i + 1;
        bArr[i] = b;
        return i2;
    }

    public int a(int i, int i2, h hVar, Handler handler) {
        byte[] bArr = new byte[1];
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        int a2 = a((byte) i2, this.f7768a, a((byte) 11, this.f7768a, 0));
        byte[] bArr4 = this.f7768a;
        byte[] bArr5 = this.b;
        int e = e(hVar, a2, bArr4, bArr5.length, bArr5, 5000);
        if (e < 0) {
            return e;
        }
        int h = h(bArr, this.b, 0);
        if (bArr[0] != 11) {
            return -1205;
        }
        int i3 = i(bArr2, this.b, h);
        int a3 = i.a(bArr2);
        i(bArr3, this.b, i3);
        int a4 = i.a(bArr3);
        Message obtain = Message.obtain();
        obtain.what = 1005;
        obtain.arg1 = a3;
        obtain.arg2 = a4;
        obtain.obj = Integer.valueOf(i);
        handler.sendMessage(obtain);
        return 0;
    }

    public int b(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i >> 8);
        int i6 = i5 + 1;
        bArr[i5] = (byte) i;
        return i6;
    }

    public int b(h hVar) {
        return new l().a(hVar);
    }

    public int b(h hVar, byte[] bArr, int i, int i2, Handler handler) {
        int length = bArr.length;
        byte[] bArr2 = new byte[1];
        byte[] bArr3 = new byte[4];
        int b = b(length, this.f7768a, b(i, this.f7768a, a((byte) i2, this.f7768a, a((byte) 12, this.f7768a, 0))));
        byte[] bArr4 = this.f7768a;
        byte[] bArr5 = this.b;
        int e = e(hVar, b, bArr4, bArr5.length, bArr5, 5000);
        if (e < 0) {
            return e;
        }
        int h = h(bArr2, this.b, 0);
        if (bArr2[0] != 12) {
            return -1205;
        }
        i(bArr3, this.b, h);
        int a2 = i.a(bArr3);
        if (a2 < 0) {
            return a2;
        }
        int d = d(hVar, a2, length, bArr, handler);
        if (bArr.length == d) {
            return 0;
        }
        return d == -8 ? b.q : d;
    }

    public int c(h hVar, byte b) {
        byte[] bArr = new byte[1];
        int a2 = a(b, this.f7768a, a((byte) 8, this.f7768a, 0));
        byte[] bArr2 = this.f7768a;
        byte[] bArr3 = this.b;
        int e = e(hVar, a2, bArr2, bArr3.length, bArr3, 5000);
        if (e < 0) {
            return e;
        }
        byte[] bArr4 = new byte[4];
        i(bArr4, this.b, h(bArr, this.b, 0));
        int a3 = i.a(bArr4);
        if (bArr[0] != 8) {
            return -1205;
        }
        return a3;
    }

    public int d(h hVar, int i, long j, byte[] bArr, Handler handler) {
        int i2 = 1024;
        byte[] bArr2 = new byte[1024];
        Message message = new Message();
        message.arg1 = 1002;
        long j2 = 0;
        int i3 = 0;
        message.arg2 = (int) ((j / 1024) + (j % 1024 == 0 ? 0 : 1));
        handler.sendMessage(message);
        long j3 = j;
        long currentTimeMillis = System.currentTimeMillis();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        float f = 0.0f;
        int i7 = i;
        while (j3 > j2) {
            int length = bArr.length - i4 > i2 ? i2 : bArr.length - i4;
            System.arraycopy(bArr, i4, bArr2, i3, length);
            int f2 = f(hVar, bArr2, i7, length);
            if (f2 <= 0) {
                Log.e("Otalib", "write error:" + length);
                return f2;
            }
            i7 += length;
            byte[] bArr3 = bArr2;
            j3 -= length;
            i5 += length;
            i4 += length;
            f += length;
            if (System.currentTimeMillis() - currentTimeMillis >= 1000) {
                long currentTimeMillis2 = System.currentTimeMillis();
                Message obtain = Message.obtain();
                obtain.arg1 = 1008;
                obtain.obj = Float.valueOf(f / 1024.0f);
                handler.sendMessage(obtain);
                currentTimeMillis = currentTimeMillis2;
                f = 0.0f;
            }
            int i8 = i6 + 1;
            Message message2 = new Message();
            message2.arg1 = 1003;
            message2.arg2 = i8;
            Log.i("JavaXmodem", "num:" + i8);
            handler.sendMessage(message2);
            if (length < 1024) {
                break;
            }
            i6 = i8;
            i2 = 1024;
            bArr2 = bArr3;
            j2 = 0;
            i3 = 0;
        }
        return i5;
    }

    public final int e(h hVar, int i, byte[] bArr, int i2, byte[] bArr2, int i3) {
        l lVar = new l();
        b.y = false;
        return lVar.d(hVar, i, bArr, i2, bArr2, i3);
    }

    public int f(h hVar, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[1];
        int b = b(i2, this.f7768a, b(i, this.f7768a, a((byte) 3, this.f7768a, 0)));
        int i3 = 0;
        while (i3 < i2) {
            this.f7768a[b] = bArr[i3];
            i3++;
            b++;
        }
        byte[] bArr3 = this.f7768a;
        byte[] bArr4 = this.b;
        int e = e(hVar, b, bArr3, bArr4.length, bArr4, 5000);
        if (e < 0) {
            Log.e("Otalib", "transaction error:" + e);
            return e;
        }
        int h = h(bArr2, this.b, 0);
        if (bArr2[0] != 3) {
            return -1205;
        }
        byte[] bArr5 = new byte[4];
        i(bArr5, this.b, h);
        return i.a(bArr5);
    }

    public int g(h hVar, byte[] bArr, int i, int i2, Handler handler) {
        StringBuilder sb;
        String str;
        byte[] bArr2 = new byte[1];
        byte[] bArr3 = new byte[4];
        if (bArr == null) {
            return -1;
        }
        long length = bArr.length;
        int b = b((int) length, this.f7768a, b(i, this.f7768a, a((byte) i2, this.f7768a, a((byte) 12, this.f7768a, 0))));
        byte[] bArr4 = this.f7768a;
        byte[] bArr5 = this.b;
        int e = e(hVar, b, bArr4, bArr5.length, bArr5, 5000);
        if (e < 0) {
            sb = new StringBuilder();
            str = "application addr error:";
        } else {
            int h = h(bArr2, this.b, 0);
            if (bArr2[0] != 12) {
                return -1205;
            }
            i(bArr3, this.b, h);
            int a2 = i.a(bArr3);
            if (a2 < 0) {
                Log.e("Otalib", "binary addr error:" + e);
                return a2;
            }
            e = d(hVar, a2, length, bArr, handler);
            if (length == e) {
                return 0;
            }
            if (e == -8) {
                e = b.q;
            }
            sb = new StringBuilder();
            str = "real_size = ";
        }
        sb.append(str);
        sb.append(e);
        Log.e("Otalib", sb.toString());
        return e;
    }

    public int h(byte[] bArr, byte[] bArr2, int i) {
        int i2 = i + 1;
        bArr[0] = bArr2[i];
        return i2;
    }

    public int i(byte[] bArr, byte[] bArr2, int i) {
        int i2 = i + 1;
        bArr[0] = bArr2[i];
        int i3 = i2 + 1;
        bArr[1] = bArr2[i2];
        int i4 = i3 + 1;
        bArr[2] = bArr2[i3];
        int i5 = i4 + 1;
        bArr[3] = bArr2[i4];
        return i5;
    }
}
