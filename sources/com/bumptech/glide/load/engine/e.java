package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;
/* loaded from: classes2.dex */
public class e<DataType> implements DiskCache.Writer {

    /* renamed from: a  reason: collision with root package name */
    public final Encoder<DataType> f2379a;
    public final DataType b;
    public final Options c;

    public e(Encoder<DataType> encoder, DataType datatype, Options options) {
        this.f2379a = encoder;
        this.b = datatype;
        this.c = options;
    }

    @Override // com.bumptech.glide.load.engine.cache.DiskCache.Writer
    public boolean write(@NonNull File file) {
        return this.f2379a.encode(this.b, file, this.c);
    }
}
