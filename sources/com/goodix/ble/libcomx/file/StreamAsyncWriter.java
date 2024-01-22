package com.goodix.ble.libcomx.file;

import com.goodix.ble.libcomx.event.Event;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
/* loaded from: classes5.dex */
public abstract class StreamAsyncWriter implements IStreamWriter {
    public static final int EVT_ERROR = 206;

    /* renamed from: a  reason: collision with root package name */
    public final String f8038a = getClass().getName() + "-WriterThread";
    public Thread b = null;
    public int c = 0;
    public ArrayBlockingQueue<byte[]> d = new ArrayBlockingQueue<>(1024);
    public int e = 10;
    public int f = 1000;
    public boolean g = false;
    public boolean h = false;
    public Event<Throwable> i = new Event<>(this, 206);

    public void a() {
        OutputStream outputStream;
        try {
            outputStream = onPrepareOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
            this.i.postEvent(e);
            outputStream = null;
        }
        synchronized (this) {
            if (outputStream == null) {
                this.c = 0;
                return;
            }
            this.c = 2;
            this.b = Thread.currentThread();
            int i = 0;
            while (!this.h) {
                try {
                    if (onWriteAll(outputStream, this.g)) {
                        i = 0;
                    }
                    if (i > this.f) {
                        break;
                    }
                    Thread.sleep(this.e);
                    i += this.e;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    this.i.postEvent(e2);
                    this.d.clear();
                }
            }
            synchronized (this) {
                this.c = 3;
            }
            try {
                onCloseOutputStream(outputStream);
            } catch (Exception e3) {
                e3.printStackTrace();
                this.i.postEvent(e3);
            }
            try {
                outputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
                this.i.postEvent(e4);
            }
            if (this.d.isEmpty()) {
                return;
            }
            write(null);
        }
    }

    @Override // com.goodix.ble.libcomx.file.IStreamWriter
    public void close() {
        this.h = true;
        Thread thread = this.b;
        if (thread == null || !thread.isAlive()) {
            return;
        }
        thread.interrupt();
    }

    public Event<Throwable> evtError() {
        return this.i;
    }

    public abstract void onCloseOutputStream(OutputStream outputStream) throws Exception;

    public abstract OutputStream onPrepareOutputStream() throws Exception;

    public boolean onWriteAll(OutputStream outputStream, boolean z) throws IOException {
        boolean z2 = false;
        if (outputStream != null) {
            ArrayBlockingQueue<byte[]> arrayBlockingQueue = this.d;
            while (true) {
                byte[] poll = arrayBlockingQueue.poll();
                if (poll == null) {
                    break;
                }
                z2 = true;
                outputStream.write(poll);
            }
            if (z) {
                outputStream.flush();
            }
        }
        return z2;
    }

    public void setImmediatelyFlush(boolean z) {
        this.g = z;
    }

    public void setTTL(int i) {
        this.f = i;
    }

    @Override // com.goodix.ble.libcomx.file.IStreamWriter
    public boolean write(byte[] bArr) {
        synchronized (this) {
            if (this.h) {
                return false;
            }
            int i = this.c;
            if (i == 3 || i == 0) {
                this.c = 1;
                new Thread(new Runnable() { // from class: com.goodix.ble.libcomx.file.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        StreamAsyncWriter.this.a();
                    }
                }, this.f8038a).start();
            }
            if (bArr != null && bArr.length != 0) {
                try {
                    this.d.offer(bArr, this.f | (this.e << 1), TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.i.postEvent(e);
                }
            }
            return true;
        }
    }
}
