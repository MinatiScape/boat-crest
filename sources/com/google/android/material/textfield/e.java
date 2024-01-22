package com.google.android.material.textfield;

import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.google.android.material.internal.CheckableImageButton;
/* loaded from: classes10.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    public TextInputLayout f10389a;
    public Context b;
    public CheckableImageButton c;
    @DrawableRes
    public final int d;

    public e(@NonNull TextInputLayout textInputLayout, @DrawableRes int i) {
        this.f10389a = textInputLayout;
        this.b = textInputLayout.getContext();
        this.c = textInputLayout.getEndIconView();
        this.d = i;
    }

    public abstract void a();

    public boolean b(int i) {
        return true;
    }

    public void c(boolean z) {
    }

    public boolean d() {
        return false;
    }
}
