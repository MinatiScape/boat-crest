package com.google.android.gms.fitness;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.PlatformVersion;
@ShowFirstParty
/* loaded from: classes6.dex */
public final class zzg implements Api.ApiOptions.HasGoogleSignInAccountOptions {
    @Nullable
    public final GoogleSignInAccount h;

    public zzg(Context context, GoogleSignInAccount googleSignInAccount) {
        if ("<<default account>>".equals(googleSignInAccount.getEmail())) {
            if (PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google")) {
                this.h = null;
                return;
            }
        }
        this.h = googleSignInAccount;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != this) {
            return (obj instanceof zzg) && Objects.equal(((zzg) obj).h, this.h);
        }
        return true;
    }

    @Override // com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions
    @Nullable
    public final GoogleSignInAccount getGoogleSignInAccount() {
        return this.h;
    }

    public final int hashCode() {
        GoogleSignInAccount googleSignInAccount = this.h;
        if (googleSignInAccount != null) {
            return googleSignInAccount.hashCode();
        }
        return 0;
    }
}
