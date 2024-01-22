package com.google.android.gms.internal.firebase_messaging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;
/* loaded from: classes7.dex */
public final class e implements ValueEncoderContext {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8645a = false;
    public boolean b = false;
    public FieldDescriptor c;
    public final c d;

    public e(c cVar) {
        this.d = cVar;
    }

    public final void a(FieldDescriptor fieldDescriptor, boolean z) {
        this.f8645a = false;
        this.c = fieldDescriptor;
        this.b = z;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    public final ValueEncoderContext add(double d) throws IOException {
        b();
        this.d.b(this.c, d, this.b);
        return this;
    }

    public final void b() {
        if (this.f8645a) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.f8645a = true;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    public final ValueEncoderContext add(float f) throws IOException {
        b();
        this.d.c(this.c, f, this.b);
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
        this.d.a(this.c, str, this.b);
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
        this.d.a(this.c, bArr, this.b);
        return this;
    }
}
