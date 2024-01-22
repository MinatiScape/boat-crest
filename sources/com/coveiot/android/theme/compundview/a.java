package com.coveiot.android.theme.compundview;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class a extends PasswordTransformationMethod {
    @Override // android.text.method.PasswordTransformationMethod, android.text.method.TransformationMethod
    @NotNull
    public CharSequence getTransformation(@Nullable CharSequence charSequence, @Nullable View view) {
        Intrinsics.checkNotNull(charSequence);
        return new h(charSequence);
    }
}
