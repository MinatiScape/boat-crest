package com.google.android.gms.auth.api.credentials;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Result;
@Deprecated
/* loaded from: classes6.dex */
public interface CredentialRequestResult extends Result {
    @Nullable
    Credential getCredential();
}
