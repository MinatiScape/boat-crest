package com.bumptech.glide.util;
/* loaded from: classes2.dex */
public final class GlideSuppliers {

    /* loaded from: classes2.dex */
    public interface GlideSupplier<T> {
        T get();
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes2.dex */
    public class a<T> implements GlideSupplier<T> {

        /* renamed from: a  reason: collision with root package name */
        public volatile T f2553a;
        public final /* synthetic */ GlideSupplier b;

        public a(GlideSupplier glideSupplier) {
            this.b = glideSupplier;
        }

        @Override // com.bumptech.glide.util.GlideSuppliers.GlideSupplier
        public T get() {
            if (this.f2553a == null) {
                synchronized (this) {
                    if (this.f2553a == null) {
                        this.f2553a = (T) Preconditions.checkNotNull(this.b.get());
                    }
                }
            }
            return this.f2553a;
        }
    }

    public static <T> GlideSupplier<T> memorize(GlideSupplier<T> glideSupplier) {
        return new a(glideSupplier);
    }
}
