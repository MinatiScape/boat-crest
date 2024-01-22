package com.google.android.odml.image;

import java.nio.ByteBuffer;
/* loaded from: classes10.dex */
public final class f implements g {

    /* renamed from: a  reason: collision with root package name */
    public final ByteBuffer f10447a;
    public final ImageProperties b;

    public f(ByteBuffer byteBuffer, int i) {
        this.f10447a = byteBuffer;
        b bVar = new b();
        bVar.b(2);
        bVar.a(i);
        this.b = bVar.c();
    }

    public final ByteBuffer a() {
        return this.f10447a;
    }

    @Override // com.google.android.odml.image.g
    public final ImageProperties zzb() {
        return this.b;
    }

    @Override // com.google.android.odml.image.g
    public final void zzc() {
    }
}
