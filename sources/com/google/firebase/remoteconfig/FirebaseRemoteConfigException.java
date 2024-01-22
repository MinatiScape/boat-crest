package com.google.firebase.remoteconfig;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.FirebaseException;
/* loaded from: classes10.dex */
public class FirebaseRemoteConfigException extends FirebaseException {
    public FirebaseRemoteConfigException(@NonNull String str) {
        super(str);
    }

    public FirebaseRemoteConfigException(@NonNull String str, @Nullable Throwable th) {
        super(str, th);
    }
}
