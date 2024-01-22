package com.google.android.gms.auth.api;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
@ShowFirstParty
@KeepForSdk
/* loaded from: classes6.dex */
public final class AuthProxyOptions implements Api.ApiOptions.Optional {
    @NonNull
    public static final AuthProxyOptions zza = new AuthProxyOptions(new Bundle(), null);
    public final Bundle h;

    public /* synthetic */ AuthProxyOptions(Bundle bundle, zzb zzbVar) {
        this.h = bundle;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AuthProxyOptions) {
            return Objects.checkBundlesEquality(this.h, ((AuthProxyOptions) obj).h);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h);
    }

    @NonNull
    public final Bundle zza() {
        return new Bundle(this.h);
    }
}
