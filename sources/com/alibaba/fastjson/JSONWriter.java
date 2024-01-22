package com.alibaba.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
/* loaded from: classes.dex */
public class JSONWriter implements Closeable, Flushable {
    public SerializeWriter h;
    public JSONSerializer i;
    public a j;

    public JSONWriter(Writer writer) {
        SerializeWriter serializeWriter = new SerializeWriter(writer);
        this.h = serializeWriter;
        this.i = new JSONSerializer(serializeWriter);
    }

    public final void a() {
        int i;
        a aVar = this.j;
        if (aVar == null) {
            return;
        }
        switch (aVar.b) {
            case 1001:
            case 1003:
                i = 1002;
                break;
            case 1002:
                i = 1003;
                break;
            case 1004:
                i = 1005;
                break;
            default:
                i = -1;
                break;
        }
        if (i != -1) {
            aVar.b = i;
        }
    }

    public final void b() {
        a aVar = this.j;
        if (aVar == null) {
            return;
        }
        int i = aVar.b;
        if (i == 1002) {
            this.h.write(58);
        } else if (i == 1003) {
            this.h.write(44);
        } else if (i != 1005) {
        } else {
            this.h.write(44);
        }
    }

    public final void c() {
        int i = this.j.b;
        switch (i) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.h.write(58);
                return;
            case 1003:
            default:
                throw new JSONException("illegal state : " + i);
            case 1005:
                this.h.write(44);
                return;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h.close();
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        this.h.config(serializerFeature, z);
    }

    public final void d() {
        a aVar = this.j.f2114a;
        this.j = aVar;
        if (aVar == null) {
            return;
        }
        int i = aVar.b;
        int i2 = i != 1001 ? i != 1002 ? i != 1004 ? -1 : 1005 : 1003 : 1002;
        if (i2 != -1) {
            aVar.b = i2;
        }
    }

    public void endArray() {
        this.h.write(93);
        d();
    }

    public void endObject() {
        this.h.write(125);
        d();
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.h.flush();
    }

    public void startArray() {
        if (this.j != null) {
            c();
        }
        this.j = new a(this.j, 1004);
        this.h.write(91);
    }

    public void startObject() {
        if (this.j != null) {
            c();
        }
        this.j = new a(this.j, 1001);
        this.h.write(123);
    }

    public void writeKey(String str) {
        writeObject(str);
    }

    public void writeObject(String str) {
        b();
        this.i.write(str);
        a();
    }

    public void writeValue(Object obj) {
        writeObject(obj);
    }

    public void writeObject(Object obj) {
        b();
        this.i.write(obj);
        a();
    }
}
