package com.goodix.ble.libcomx.file;

import com.goodix.ble.libcomx.event.Event;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;
/* loaded from: classes5.dex */
public class DelayedStreamWriter implements IStreamWriter {
    public static final int EVT_ERROR = 206;

    /* renamed from: a  reason: collision with root package name */
    public Thread f8036a = new Thread(new Runnable() { // from class: com.goodix.ble.libcomx.file.a
        @Override // java.lang.Runnable
        public final void run() {
            DelayedStreamWriter.this.a();
        }
    });
    public ArrayBlockingQueue<byte[]> b = new ArrayBlockingQueue<>(1024);
    public int c = 100;
    public boolean d = false;
    public boolean e = false;
    public boolean f = false;
    public Event<Throwable> g = new Event<>(this, 206);
    public OutputStream outputStream;

    public DelayedStreamWriter() {
    }

    public void a() {
        OutputStream outputStream;
        try {
            onStartThread();
        } catch (Exception e) {
            e.printStackTrace();
            this.g.postEvent(e);
        }
        while (true) {
            try {
            } catch (IOException e2) {
                e2.printStackTrace();
                this.g.postEvent(e2);
            } catch (InterruptedException unused) {
            }
            if (this.f) {
                this.outputStream.close();
                try {
                    break;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    this.g.postEvent(e3);
                    return;
                }
            }
            onWrite(this.d);
            Thread.sleep(this.c);
        }
        onStopThread();
        if (!this.f || (outputStream = this.outputStream) == null) {
            return;
        }
        outputStream.close();
    }

    @Override // com.goodix.ble.libcomx.file.IStreamWriter
    public void close() {
        this.f = true;
        this.f8036a.interrupt();
    }

    public Event<Throwable> evtError() {
        return this.g;
    }

    public void onStartThread() throws Exception {
    }

    public void onStopThread() throws Exception {
    }

    public void onWrite(boolean z) throws InterruptedException, IOException {
        byte[] take = this.b.take();
        OutputStream outputStream = this.outputStream;
        if (outputStream != null) {
            do {
                outputStream.write(take);
                take = this.b.poll();
            } while (take != null);
            if (z) {
                outputStream.flush();
            }
        }
    }

    public void setImmediatelyFlush(boolean z) {
        this.d = z;
    }

    @Override // com.goodix.ble.libcomx.file.IStreamWriter
    public boolean write(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        if (!this.e) {
            synchronized (this) {
                if (!this.e) {
                    this.e = true;
                    this.f8036a.start();
                }
            }
        }
        if (bArr.length == 0) {
            return true;
        }
        try {
            this.b.put(bArr);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public DelayedStreamWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
