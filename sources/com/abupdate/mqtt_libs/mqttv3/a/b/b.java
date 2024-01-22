package com.abupdate.mqtt_libs.mqttv3.a.b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public class b extends ByteArrayOutputStream {
    public final f h;
    public final h i;

    public b(f fVar) {
        this.h = fVar;
        this.i = null;
    }

    public OutputStream a() throws IOException {
        f fVar = this.h;
        if (fVar != null) {
            return fVar.f();
        }
        h hVar = this.i;
        if (hVar != null) {
            return hVar.f();
        }
        return null;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        ByteBuffer wrap;
        synchronized (this) {
            wrap = ByteBuffer.wrap(toByteArray());
            reset();
        }
        a().write(new d((byte) 2, true, wrap.array()).c());
        a().flush();
    }

    public b(h hVar) {
        this.h = null;
        this.i = hVar;
    }
}
