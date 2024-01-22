package com.crrepa.m0;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public class h {
    public Handler e;
    public byte[] f;

    /* renamed from: a  reason: collision with root package name */
    public Semaphore f7764a = new Semaphore(0);
    public Semaphore b = new Semaphore(1);
    public BlockingQueue<Byte> c = new LinkedBlockingQueue();
    public String d = "TransOverBle";
    public boolean g = false;

    /* loaded from: classes9.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f7765a;
        public int b;
        public byte[] c = new byte[16];
    }

    public h() {
    }

    public h(Context context, Handler handler) {
        this.e = handler;
    }

    public byte a(byte[] bArr, int i) {
        if (bArr.length == 0) {
            return (byte) 0;
        }
        byte b = 0;
        for (int i2 = 0; i2 < i; i2++) {
            b = (byte) (b + bArr[i2]);
        }
        return b;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int a(g gVar, a aVar) {
        byte[] bArr = new byte[1024];
        bArr[0] = 0;
        byte b = gVar.f7763a;
        bArr[1] = b;
        bArr[2] = 0;
        int i = 21;
        switch (b) {
            case 0:
            case 2:
            case 3:
                if (aVar != null) {
                    int i2 = aVar.b;
                    if (i2 != 0) {
                        bArr[5] = 8;
                        bArr[6] = 0;
                        bArr[7] = 1;
                        bArr[8] = 0;
                        int i3 = aVar.f7765a;
                        bArr[9] = (byte) (i3 & 255);
                        bArr[10] = (byte) ((i3 >> 8) & 255);
                        bArr[11] = (byte) ((i3 >> 16) & 255);
                        bArr[12] = (byte) ((i3 >> 24) & 255);
                        bArr[13] = 8;
                        bArr[14] = 0;
                        bArr[15] = 2;
                        bArr[16] = 0;
                        bArr[17] = (byte) (i2 & 255);
                        bArr[18] = (byte) ((i2 >> 8) & 255);
                        bArr[19] = (byte) ((i2 >> 16) & 255);
                        bArr[20] = (byte) ((i2 >> 24) & 255);
                        bArr[3] = (byte) 21;
                        bArr[4] = (byte) 0;
                        bArr[2] = a(bArr, 21);
                        break;
                    } else {
                        return 0;
                    }
                } else {
                    return -1;
                }
            case 1:
            case 5:
            case 6:
                bArr[3] = (byte) 5;
                bArr[4] = (byte) 0;
                bArr[2] = a(bArr, 5);
                i = 5;
                break;
            case 4:
                bArr[5] = 20;
                bArr[6] = 0;
                bArr[7] = 4;
                bArr[8] = 0;
                byte[] bArr2 = aVar.c;
                System.arraycopy(bArr2, 0, bArr, 9, bArr2.length);
                bArr[3] = (byte) 25;
                bArr[4] = (byte) 0;
                bArr[2] = a(bArr, 25);
                i = 25;
                break;
            default:
                i = 5;
                break;
        }
        a();
        return c(bArr, i);
    }

    public int a(byte[] bArr, Handler handler) {
        if (bArr == null || bArr.length == 0) {
            return -1;
        }
        return new d().a(bArr, bArr.length, this, handler);
    }

    public void a() {
        this.c.clear();
    }

    public void a(byte[] bArr, boolean z) {
        this.f = bArr;
        this.g = z;
    }

    public boolean a(byte[] bArr) {
        byte b = (byte) (bArr[0] + bArr[1]);
        for (int i = 3; i < bArr.length; i++) {
            b = (byte) (b + bArr[i]);
        }
        return b == bArr[2];
    }

    public byte[] a(int i) {
        return c(i, 10000);
    }

    public void b(byte[] bArr) {
        if (bArr != null) {
            Message message = new Message();
            message.arg1 = 1000;
            message.obj = bArr;
            this.e.sendMessage(message);
        }
    }

    public byte[] b(int i) {
        return new d().a(i, this);
    }

    public byte[] b(int i, int i2) {
        return c(i, i2);
    }

    public int c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return -1;
        }
        return new d().a(bArr, bArr.length, this);
    }

    public int c(byte[] bArr, int i) {
        return d(bArr, i);
    }

    public final byte[] c(int i, int i2) {
        boolean z;
        byte[] bArr = new byte[i];
        try {
            z = this.f7764a.tryAcquire(i, i2, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            z = false;
        }
        if (!z) {
            Log.i(this.d, "receive timeout");
            return null;
        }
        for (int i3 = 0; i3 < i; i3++) {
            try {
                this.b.acquire(1);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if (this.c.isEmpty()) {
                return null;
            }
            bArr[i3] = this.c.poll().byteValue();
            this.b.release(1);
        }
        return bArr;
    }

    public final int d(byte[] bArr, int i) {
        if (bArr == null) {
            Log.i(this.d, "send data is null");
            return 0;
        }
        if (this.g) {
            f.b(bArr, bArr, i, this.f);
        }
        Message message = new Message();
        message.arg1 = 1004;
        message.arg2 = i;
        message.obj = bArr;
        this.e.sendMessage(message);
        return 0;
    }
}
