package com.google.firebase.remoteconfig;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public interface FirebaseRemoteConfigInfo {
    @NonNull
    FirebaseRemoteConfigSettings getConfigSettings();

    long getFetchTimeMillis();

    int getLastFetchStatus();
}
