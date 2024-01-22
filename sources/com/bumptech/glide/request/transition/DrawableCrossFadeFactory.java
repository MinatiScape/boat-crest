package com.bumptech.glide.request.transition;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
/* loaded from: classes2.dex */
public class DrawableCrossFadeFactory implements TransitionFactory<Drawable> {

    /* renamed from: a  reason: collision with root package name */
    public final int f2534a;
    public final boolean b;
    public DrawableCrossFadeTransition c;

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final int f2535a;
        public boolean b;

        public Builder() {
            this(300);
        }

        public DrawableCrossFadeFactory build() {
            return new DrawableCrossFadeFactory(this.f2535a, this.b);
        }

        public Builder setCrossFadeEnabled(boolean z) {
            this.b = z;
            return this;
        }

        public Builder(int i) {
            this.f2535a = i;
        }
    }

    public DrawableCrossFadeFactory(int i, boolean z) {
        this.f2534a = i;
        this.b = z;
    }

    public final Transition<Drawable> a() {
        if (this.c == null) {
            this.c = new DrawableCrossFadeTransition(this.f2534a, this.b);
        }
        return this.c;
    }

    @Override // com.bumptech.glide.request.transition.TransitionFactory
    public Transition<Drawable> build(DataSource dataSource, boolean z) {
        if (dataSource == DataSource.MEMORY_CACHE) {
            return NoTransition.get();
        }
        return a();
    }
}
