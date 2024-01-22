package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes13.dex */
public class CountingInputStream extends InputStream {
    public InputStream h;
    public int i = 0;

    public CountingInputStream(InputStream inputStream) {
        this.h = inputStream;
    }

    public int getCounter() {
        return this.i;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.h.read();
        if (read != -1) {
            this.i++;
        }
        return read;
    }

    public void resetCounter() {
        this.i = 0;
    }
}
