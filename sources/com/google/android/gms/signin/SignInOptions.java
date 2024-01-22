package com.google.android.gms.signin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes10.dex */
public final class SignInOptions implements Api.ApiOptions.Optional {
    @NonNull
    public static final SignInOptions zaa = new SignInOptions(false, false, null, false, null, null, false, null, null, null);
    public final boolean h = false;
    public final boolean i = false;
    @Nullable
    public final String j = null;
    public final boolean k = false;
    public final boolean n = false;
    @Nullable
    public final String l = null;
    @Nullable
    public final String m = null;
    @Nullable
    public final Long o = null;
    @Nullable
    public final Long p = null;

    public /* synthetic */ SignInOptions(boolean z, boolean z2, String str, boolean z3, String str2, String str3, boolean z4, Long l, Long l2, zaf zafVar) {
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SignInOptions) {
            boolean z = ((SignInOptions) obj).h;
            return Objects.equal(null, null) && Objects.equal(null, null) && Objects.equal(null, null) && Objects.equal(null, null) && Objects.equal(null, null);
        }
        return false;
    }

    public final int hashCode() {
        Boolean bool = Boolean.FALSE;
        return Objects.hashCode(bool, bool, null, bool, bool, null, null, null, null);
    }
}
