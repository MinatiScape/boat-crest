package com.clevertap.android.sdk.login;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public interface IdentityRepo {
    IdentitySet getIdentitySet();

    boolean hasIdentity(@NonNull String str);
}
