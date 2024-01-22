package com.google.android.gms.analytics;

import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
/* loaded from: classes6.dex */
public interface ExceptionParser {
    @RecentlyNonNull
    String getDescription(@Nullable String str, @RecentlyNonNull Throwable th);
}
