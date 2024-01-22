package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import androidx.annotation.RestrictTo;
@SuppressLint({"AppCompatCustomView"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public class VisibilityAwareImageButton extends ImageButton {
    public int h;

    public VisibilityAwareImageButton(Context context) {
        this(context, null);
    }

    public final int getUserSetVisibility() {
        return this.h;
    }

    public final void internalSetVisibility(int i, boolean z) {
        super.setVisibility(i);
        if (z) {
            this.h = i;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        internalSetVisibility(i, true);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = getVisibility();
    }
}