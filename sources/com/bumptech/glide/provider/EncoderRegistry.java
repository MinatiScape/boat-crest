package com.bumptech.glide.provider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Encoder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class EncoderRegistry {

    /* renamed from: a  reason: collision with root package name */
    public final List<a<?>> f2516a = new ArrayList();

    /* loaded from: classes2.dex */
    public static final class a<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Class<T> f2517a;
        public final Encoder<T> b;

        public a(@NonNull Class<T> cls, @NonNull Encoder<T> encoder) {
            this.f2517a = cls;
            this.b = encoder;
        }

        public boolean a(@NonNull Class<?> cls) {
            return this.f2517a.isAssignableFrom(cls);
        }
    }

    public synchronized <T> void append(@NonNull Class<T> cls, @NonNull Encoder<T> encoder) {
        this.f2516a.add(new a<>(cls, encoder));
    }

    @Nullable
    public synchronized <T> Encoder<T> getEncoder(@NonNull Class<T> cls) {
        for (a<?> aVar : this.f2516a) {
            if (aVar.a(cls)) {
                return (Encoder<T>) aVar.b;
            }
        }
        return null;
    }

    public synchronized <T> void prepend(@NonNull Class<T> cls, @NonNull Encoder<T> encoder) {
        this.f2516a.add(0, new a<>(cls, encoder));
    }
}
