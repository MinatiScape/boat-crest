package com.bumptech.glide.util.pool;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public abstract class StateVerifier {

    /* loaded from: classes2.dex */
    public static class b extends StateVerifier {

        /* renamed from: a  reason: collision with root package name */
        public volatile boolean f2563a;

        public b() {
            super();
        }

        @Override // com.bumptech.glide.util.pool.StateVerifier
        public void a(boolean z) {
            this.f2563a = z;
        }

        @Override // com.bumptech.glide.util.pool.StateVerifier
        public void throwIfRecycled() {
            if (this.f2563a) {
                throw new IllegalStateException("Already released");
            }
        }
    }

    @NonNull
    public static StateVerifier newInstance() {
        return new b();
    }

    public abstract void a(boolean z);

    public abstract void throwIfRecycled();

    public StateVerifier() {
    }
}
