package com.google.android.material.textfield;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public class b extends e {
    public b(@NonNull TextInputLayout textInputLayout, @DrawableRes int i) {
        super(textInputLayout, i);
    }

    @Override // com.google.android.material.textfield.e
    public void a() {
        this.f10389a.setEndIconDrawable(this.d);
        this.f10389a.setEndIconOnClickListener(null);
        this.f10389a.setEndIconOnLongClickListener(null);
    }
}
