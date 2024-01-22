package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public final class c extends Drawable.ConstantState {

    /* renamed from: a  reason: collision with root package name */
    public int f8565a;
    public int b;

    public c(@Nullable c cVar) {
        if (cVar != null) {
            this.f8565a = cVar.f8565a;
            this.b = cVar.b;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return this.f8565a;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new zak(this);
    }
}
