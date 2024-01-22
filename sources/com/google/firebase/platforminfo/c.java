package com.google.firebase.platforminfo;

import com.google.auto.value.AutoValue;
import javax.annotation.Nonnull;
@AutoValue
/* loaded from: classes10.dex */
public abstract class c {
    public static c a(String str, String str2) {
        return new a(str, str2);
    }

    @Nonnull
    public abstract String b();

    @Nonnull
    public abstract String c();
}
