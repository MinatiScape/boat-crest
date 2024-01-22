package com.google.firebase.crashlytics.internal;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;
/* loaded from: classes10.dex */
public interface CrashlyticsNativeComponent {
    void finalizeSession(@NonNull String str);

    @NonNull
    NativeSessionFileProvider getSessionFileProvider(@NonNull String str);

    boolean hasCrashDataForSession(@NonNull String str);

    void openSession(@NonNull String str, @NonNull String str2, long j, @NonNull StaticSessionData staticSessionData);
}
