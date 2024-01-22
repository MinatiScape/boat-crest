package com.google.android.odml.image;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
/* loaded from: classes10.dex */
public class ByteBufferMlImageBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final ByteBuffer f10440a;
    public final int b;
    public final int c;
    public final int d;
    public int e = 0;
    public Rect f;

    public ByteBufferMlImageBuilder(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3) {
        this.f10440a = byteBuffer;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.f = new Rect(0, 0, i, i2);
    }

    @NonNull
    public MlImage build() {
        return new MlImage(new f(this.f10440a, this.d), this.e, this.f, 0L, this.b, this.c);
    }

    @NonNull
    public ByteBufferMlImageBuilder setRotation(int i) {
        MlImage.c(i);
        this.e = i;
        return this;
    }
}
