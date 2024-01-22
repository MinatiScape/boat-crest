package com.google.android.gms.internal.mlkit_vision_text_common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class c1 implements ValueEncoderContext {

    /* renamed from: a  reason: collision with root package name */
    public boolean f9807a = false;
    public boolean b = false;
    public FieldDescriptor c;
    public final b1 d;

    public c1(b1 b1Var) {
        this.d = b1Var;
    }

    public final void a(FieldDescriptor fieldDescriptor, boolean z) {
        this.f9807a = false;
        this.c = fieldDescriptor;
        this.b = z;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    public final ValueEncoderContext add(double d) throws IOException {
        b();
        this.d.a(this.c, d, this.b);
        return this;
    }

    public final void b() {
        if (this.f9807a) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.f9807a = true;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    public final ValueEncoderContext add(float f) throws IOException {
        b();
        this.d.b(this.c, f, this.b);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    public final ValueEncoderContext add(int i) throws IOException {
        b();
        this.d.d(this.c, i, this.b);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    public final ValueEncoderContext add(long j) throws IOException {
        b();
        this.d.e(this.c, j, this.b);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    public final ValueEncoderContext add(@Nullable String str) throws IOException {
        b();
        this.d.c(this.c, str, this.b);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    public final ValueEncoderContext add(boolean z) throws IOException {
        b();
        this.d.d(this.c, z ? 1 : 0, this.b);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    public final ValueEncoderContext add(@NonNull byte[] bArr) throws IOException {
        b();
        this.d.c(this.c, bArr, this.b);
        return this;
    }
}
