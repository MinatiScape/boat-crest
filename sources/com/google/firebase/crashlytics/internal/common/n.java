package com.google.firebase.crashlytics.internal.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.InputStream;
/* loaded from: classes10.dex */
public interface n {
    @Nullable
    CrashlyticsReport.FilesPayload.File a();

    @NonNull
    String b();

    @Nullable
    InputStream c();
}
