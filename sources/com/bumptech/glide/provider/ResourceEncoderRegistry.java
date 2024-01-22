package com.bumptech.glide.provider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.ResourceEncoder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class ResourceEncoderRegistry {

    /* renamed from: a  reason: collision with root package name */
    public final List<a<?>> f2523a = new ArrayList();

    /* loaded from: classes2.dex */
    public static final class a<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Class<T> f2524a;
        public final ResourceEncoder<T> b;

        public a(@NonNull Class<T> cls, @NonNull ResourceEncoder<T> resourceEncoder) {
            this.f2524a = cls;
            this.b = resourceEncoder;
        }

        public boolean a(@NonNull Class<?> cls) {
            return this.f2524a.isAssignableFrom(cls);
        }
    }

    public synchronized <Z> void append(@NonNull Class<Z> cls, @NonNull ResourceEncoder<Z> resourceEncoder) {
        this.f2523a.add(new a<>(cls, resourceEncoder));
    }

    @Nullable
    public synchronized <Z> ResourceEncoder<Z> get(@NonNull Class<Z> cls) {
        int size = this.f2523a.size();
        for (int i = 0; i < size; i++) {
            a<?> aVar = this.f2523a.get(i);
            if (aVar.a(cls)) {
                return (ResourceEncoder<Z>) aVar.b;
            }
        }
        return null;
    }

    public synchronized <Z> void prepend(@NonNull Class<Z> cls, @NonNull ResourceEncoder<Z> resourceEncoder) {
        this.f2523a.add(0, new a<>(cls, resourceEncoder));
    }
}
