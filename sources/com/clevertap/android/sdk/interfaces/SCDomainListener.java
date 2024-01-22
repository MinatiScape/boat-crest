package com.clevertap.android.sdk.interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public interface SCDomainListener {
    void onSCDomainAvailable(@NonNull String str);

    void onSCDomainUnavailable();
}
