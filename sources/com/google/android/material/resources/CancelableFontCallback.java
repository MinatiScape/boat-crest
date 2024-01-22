package com.google.android.material.resources;

import android.graphics.Typeface;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public final class CancelableFontCallback extends TextAppearanceFontCallback {

    /* renamed from: a  reason: collision with root package name */
    public final Typeface f10336a;
    public final ApplyFont b;
    public boolean c;

    /* loaded from: classes10.dex */
    public interface ApplyFont {
        void apply(Typeface typeface);
    }

    public CancelableFontCallback(ApplyFont applyFont, Typeface typeface) {
        this.f10336a = typeface;
        this.b = applyFont;
    }

    public final void a(Typeface typeface) {
        if (this.c) {
            return;
        }
        this.b.apply(typeface);
    }

    public void cancel() {
        this.c = true;
    }

    @Override // com.google.android.material.resources.TextAppearanceFontCallback
    public void onFontRetrievalFailed(int i) {
        a(this.f10336a);
    }

    @Override // com.google.android.material.resources.TextAppearanceFontCallback
    public void onFontRetrieved(Typeface typeface, boolean z) {
        a(typeface);
    }
}
