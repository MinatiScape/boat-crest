package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public class BaseVector {

    /* renamed from: a  reason: collision with root package name */
    public int f1267a;
    public int b;
    public ByteBuffer bb;
    public int c;

    public int __element(int i) {
        return this.f1267a + (i * this.c);
    }

    public void __reset(int i, int i2, ByteBuffer byteBuffer) {
        this.bb = byteBuffer;
        if (byteBuffer != null) {
            this.f1267a = i;
            this.b = byteBuffer.getInt(i - 4);
            this.c = i2;
            return;
        }
        this.f1267a = 0;
        this.b = 0;
        this.c = 0;
    }

    public int __vector() {
        return this.f1267a;
    }

    public int length() {
        return this.b;
    }

    public void reset() {
        __reset(0, 0, null);
    }
}
