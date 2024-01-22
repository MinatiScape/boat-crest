package com.crrepa.a0;

import com.crrepa.f.d1;
import com.crrepa.m.f;
import java.io.File;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes9.dex */
public abstract class c {
    public static final int DEFAULT_START_INDEX = 0;
    public static final int DEFAULT_TIMEOUT_SECONDS = 30;
    public e mTransFileManager;
    public int timeout = 30;

    /* renamed from: a  reason: collision with root package name */
    public Timer f7635a = new Timer();
    public int b = 0;

    /* loaded from: classes9.dex */
    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.this.c();
        }
    }

    public final void b(int i) {
        e eVar = this.mTransFileManager;
        if (eVar == null) {
            onTransFileNull();
            return;
        }
        int c = eVar.c();
        com.crrepa.i0.c.c("receiveCRC: " + i);
        com.crrepa.i0.c.c("calcFileCrc: " + c);
        boolean z = i == c;
        sendFileCheckResult(z);
        if (z) {
            h();
        } else {
            onCrcFail();
        }
    }

    public final synchronized void c() {
        com.crrepa.i0.c.a("waitTime: " + this.b);
        int i = this.b;
        if (i < this.timeout) {
            this.b = i + 1;
        } else {
            com.crrepa.i0.c.a("trans time out!");
            onTimeoutError();
        }
    }

    public void createFileManager(File file, int i, int i2) {
        this.mTransFileManager = e.a(file, i, i2);
    }

    public final void d(int i) {
        if (this.mTransFileManager == null) {
            return;
        }
        e();
        onTransChanged((i * 100) / this.mTransFileManager.e());
    }

    public final synchronized void e() {
        this.b = 0;
    }

    public final void f(int i) {
        e eVar = this.mTransFileManager;
        if (eVar == null) {
            com.crrepa.i0.c.b("FileManager is null");
            onTransFileNull();
            return;
        }
        byte[] a2 = eVar.a(i);
        int b = this.mTransFileManager.b();
        if (a2 != null) {
            sendMessage(d.a(a2, b));
            return;
        }
        com.crrepa.i0.c.b("transBytes is null");
        onTransFileError();
    }

    public final void g() {
        Timer timer = this.f7635a;
        if (timer != null) {
            timer.cancel();
            this.f7635a = null;
        }
    }

    public abstract int getTransType();

    public final void h() {
        onTransComplete();
        release();
    }

    public boolean isStarted() {
        return this.mTransFileManager != null;
    }

    public abstract void onCrcFail();

    public abstract void onTimeoutError();

    public abstract void onTransChanged(int i);

    public abstract void onTransComplete();

    public abstract void onTransFileError();

    public abstract void onTransFileNull();

    public void release() {
        g();
        e eVar = this.mTransFileManager;
        if (eVar != null) {
            eVar.a();
            this.mTransFileManager = null;
        }
    }

    public void sendBleMessage(byte[] bArr) {
        f.d().a(bArr);
    }

    public void sendFileCheckResult(boolean z) {
        com.crrepa.i0.c.a("sendFileCheckResult: " + z);
        int transType = getTransType();
        if (transType <= 0) {
            return;
        }
        byte[] bArr = new byte[4];
        if (!z) {
            Arrays.fill(bArr, (byte) -1);
        }
        sendBleMessage(d1.a(transType, bArr));
    }

    public void sendMessage(byte[] bArr) {
        f d = f.d();
        int transType = getTransType();
        if (transType == 99) {
            d.c(bArr);
        } else if (transType == 108 || transType == 116) {
            d.d(bArr);
        }
    }

    public void setTransLength(int i) {
        this.mTransFileManager.b(i);
    }

    public void startTimer() {
        if (this.f7635a == null) {
            this.f7635a = new Timer();
        }
        e();
        this.f7635a.schedule(new a(), 1000L, 1000L);
    }

    public void startTrans() {
        long d = this.mTransFileManager.d();
        if (d < 0) {
            onTransFileError();
            return;
        }
        sendBleMessage(d1.a(getTransType(), com.crrepa.i0.e.a(d)));
    }

    public void transFileIndex(com.crrepa.e0.a aVar) {
        int b = aVar.b();
        com.crrepa.i0.c.c("trans offset: " + b);
        if (b < 0) {
            return;
        }
        if (b == 65535) {
            b(aVar.a());
            return;
        }
        f(b);
        d(b);
    }
}
