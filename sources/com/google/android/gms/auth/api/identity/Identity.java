package com.google.android.gms.auth.api.identity;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002authapi.zbao;
import com.google.android.gms.internal.p002authapi.zbay;
/* loaded from: classes6.dex */
public final class Identity {
    @NonNull
    public static CredentialSavingClient getCredentialSavingClient(@NonNull Activity activity) {
        return new zbao((Activity) Preconditions.checkNotNull(activity), new zbc());
    }

    @NonNull
    public static SignInClient getSignInClient(@NonNull Activity activity) {
        return new zbay((Activity) Preconditions.checkNotNull(activity), new zbp());
    }

    @NonNull
    public static CredentialSavingClient getCredentialSavingClient(@NonNull Context context) {
        return new zbao((Context) Preconditions.checkNotNull(context), new zbc());
    }

    @NonNull
    public static SignInClient getSignInClient(@NonNull Context context) {
        return new zbay((Context) Preconditions.checkNotNull(context), new zbp());
    }
}
