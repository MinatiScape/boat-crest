package com.crrepa.m0;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.jni.ecc256;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f7767a;
    public byte[] b;
    public byte[] c;
    public BlockingQueue<Byte> m;
    public Semaphore n;
    public Semaphore o;
    public h p;
    public k q;
    public Handler r;
    public byte[] g = new byte[32];
    public byte[] h = new byte[32];
    public byte[] i = new byte[32];
    public boolean j = false;
    public byte[] k = {1, 0, 0, 0};
    public byte[] l = {2, 0, 8, 0, 6, 0, 6, 0, 0, 0, -56, 0};
    public boolean f = false;
    public boolean d = false;
    public boolean e = false;

    public j(Context context, Handler handler) {
        this.r = handler;
        new a();
        this.p = new h(context, handler);
        this.q = new k();
    }

    public final int a() {
        return this.q.b(this.p);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a(byte[] r7, int r8) {
        /*
            Method dump skipped, instructions count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crrepa.m0.j.a(byte[], int):int");
    }

    public int a(byte[] bArr, String str) {
        if (bArr != null || bArr.length > 0) {
            Log.e("peng", "interval:" + this.f + "  reset link:" + this.d + "   verify:" + this.e);
            int parseInt = Integer.parseInt(str, 16);
            if (Pattern.matches("[a-f0-9A-F]{1,12}", str)) {
                if (!this.f) {
                    Message obtain = Message.obtain();
                    obtain.arg1 = 1000;
                    byte[] bArr2 = this.l;
                    obtain.arg2 = bArr2.length;
                    obtain.obj = bArr2;
                    this.r.sendMessage(obtain);
                    this.f = true;
                }
                if (!this.d) {
                    int a2 = a();
                    if (a2 < 0) {
                        this.f = false;
                        this.d = false;
                        return a2;
                    }
                    this.d = true;
                }
                if (!this.e) {
                    int c = c();
                    if (c < 0) {
                        this.f = false;
                        this.d = false;
                        this.e = false;
                        return c;
                    }
                    this.e = true;
                }
                try {
                    this.o.acquire();
                    int b = this.q.b(this.p, bArr, parseInt, 4, this.r);
                    Semaphore semaphore = this.o;
                    if (semaphore != null) {
                        semaphore.release();
                    }
                    if (b < 0) {
                        this.d = false;
                        this.e = false;
                        this.f = false;
                    }
                    return b;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Semaphore semaphore2 = this.o;
                    if (semaphore2 != null) {
                        semaphore2.release();
                    }
                    return -1;
                }
            }
            return b.s;
        }
        return -1;
    }

    public void a(int i) {
        byte[] bArr = {99, 0, 0, 0};
        byte[] bArr2 = {100, 0, 0, 0};
        Message obtain = Message.obtain();
        if (i == 1006) {
            obtain.arg1 = 1000;
            obtain.arg2 = 4;
            obtain.obj = bArr;
        } else if (i != 1007) {
            return;
        } else {
            obtain.arg1 = 1000;
            obtain.arg2 = 4;
            obtain.obj = bArr2;
        }
        this.r.sendMessage(obtain);
    }

    public void a(boolean z) {
        boolean z2;
        this.m = new LinkedBlockingQueue();
        this.n = new Semaphore(0);
        this.o = new Semaphore(1);
        this.j = z;
        if (!z) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 32; i++) {
            stringBuffer.append(Integer.toHexString(new Random().nextInt(16)) + Integer.toHexString(new Random().nextInt(16)));
        }
        this.c = i.e(stringBuffer.toString());
        f.b(0);
        f.a(0);
        ecc256 ecc256Var = new ecc256();
        byte[] bArr = new byte[32];
        this.f7767a = bArr;
        byte[] bArr2 = new byte[32];
        this.b = bArr2;
        ecc256Var.ecc_generate_public_key(this.c, bArr, bArr2);
        byte[] bArr3 = this.k;
        int length = bArr3.length;
        byte[] bArr4 = this.f7767a;
        int length2 = length + bArr4.length;
        byte[] bArr5 = this.b;
        int length3 = length2 + bArr5.length;
        byte[] bArr6 = new byte[length3];
        int length4 = bArr4.length + bArr5.length;
        bArr3[2] = (byte) (length4 & 255);
        bArr3[3] = (byte) ((length4 >> 8) & 255);
        System.arraycopy(bArr3, 0, bArr6, 0, bArr3.length);
        byte[] bArr7 = this.f7767a;
        System.arraycopy(bArr7, 0, bArr6, this.k.length, bArr7.length);
        byte[] bArr8 = this.b;
        System.arraycopy(bArr8, 0, bArr6, this.f7767a.length + this.k.length, bArr8.length);
        Message obtain = Message.obtain();
        obtain.arg1 = 1000;
        obtain.arg2 = length3;
        obtain.obj = bArr6;
        this.r.sendMessage(obtain);
        try {
            z2 = this.n.tryAcquire((this.g.length * 2) + 4, 10000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            z2 = false;
        }
        if (!z2) {
            e();
            this.j = false;
            Message obtain2 = Message.obtain();
            obtain2.arg1 = 1001;
            this.r.sendMessage(obtain2);
            return;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr9 = this.g;
            if (i2 >= (bArr9.length * 2) + 4) {
                ecc256Var.ecc_generate_dhkey(this.c, bArr9, this.h, this.i);
                this.p.a(this.i, this.j);
                return;
            }
            if (i2 < 4) {
                this.m.poll();
            } else if (i2 >= 4 && i2 < bArr9.length + 4) {
                Byte poll = this.m.poll();
                if (poll == null) {
                    this.j = false;
                    Message message = new Message();
                    message.arg1 = 1001;
                    this.r.sendMessage(message);
                }
                this.g[i2 - 4] = poll.byteValue();
            } else if (i2 >= bArr9.length + 4 && i2 < (bArr9.length * 2) + 4) {
                Byte poll2 = this.m.poll();
                if (poll2 == null) {
                    this.j = false;
                    Message message2 = new Message();
                    message2.arg1 = 1001;
                    this.r.sendMessage(message2);
                }
                this.h[(i2 - 4) - this.g.length] = poll2.byteValue();
            }
            i2++;
        }
    }

    public int b(int i) {
        k kVar;
        h hVar;
        Handler handler;
        int i2;
        if (!this.d) {
            int a2 = a();
            if (a2 < 0) {
                this.d = false;
                return a2;
            }
            this.d = true;
        }
        int i3 = 3;
        if (i != 3) {
            i3 = 4;
            if (i != 4) {
                i3 = 5;
                if (i != 5) {
                    return this.q.a(0, i, this.p, this.r);
                }
                kVar = this.q;
                hVar = this.p;
                handler = this.r;
                i2 = 97;
            } else {
                kVar = this.q;
                hVar = this.p;
                handler = this.r;
                i2 = 98;
            }
        } else {
            kVar = this.q;
            hVar = this.p;
            handler = this.r;
            i2 = 96;
        }
        return kVar.a(i3, i2, hVar, handler);
    }

    public final int b(byte[] bArr, int i, int i2) {
        return this.q.g(this.p, bArr, i, i2, this.r);
    }

    public void b(byte[] bArr, int i) {
        if (this.m == null || this.n == null) {
            return;
        }
        int i2 = 0;
        if (i == 1) {
            while (i2 < bArr.length) {
                try {
                    this.m.put(Byte.valueOf(bArr[i2]));
                    this.n.release(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i2++;
            }
        } else if (i != 2) {
        } else {
            if (this.j) {
                f.a(bArr, bArr, bArr.length, this.i);
            }
            while (i2 < bArr.length) {
                try {
                    this.p.c.put(Byte.valueOf(bArr[i2]));
                    this.p.f7764a.release(1);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                i2++;
            }
        }
    }

    public final int c() {
        return this.q.c(this.p, (byte) 1);
    }

    public void d() {
        this.d = false;
        this.e = false;
        this.f = false;
        e();
        Message obtain = Message.obtain();
        obtain.arg1 = 1000;
        obtain.arg2 = 4;
        obtain.obj = new byte[]{0, 0, 0, 0};
        this.r.sendMessage(obtain);
    }

    public final void e() {
        BlockingQueue<Byte> blockingQueue = this.m;
        if (blockingQueue != null) {
            blockingQueue.clear();
            this.m = null;
        }
        Semaphore semaphore = this.n;
        if (semaphore != null) {
            semaphore.release();
            this.n = null;
        }
        Semaphore semaphore2 = this.o;
        if (semaphore2 != null) {
            semaphore2.release();
            this.o = null;
        }
    }

    public void f() {
        e();
        this.d = false;
        this.e = false;
        this.f = false;
        b.y = true;
    }
}
