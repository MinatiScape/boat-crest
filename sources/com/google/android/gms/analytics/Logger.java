package com.google.android.gms.analytics;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
@Deprecated
/* loaded from: classes6.dex */
public interface Logger {

    @Deprecated
    /* loaded from: classes6.dex */
    public static class LogLevel {
        public static final int ERROR = 3;
        public static final int INFO = 1;
        public static final int VERBOSE = 0;
        public static final int WARNING = 2;
    }

    @Deprecated
    void error(@RecentlyNonNull Exception exc);

    @Deprecated
    void error(@RecentlyNonNull String str);

    @Deprecated
    int getLogLevel();

    @Deprecated
    void info(@RecentlyNonNull String str);

    @Deprecated
    void setLogLevel(int i);

    @Deprecated
    void verbose(@RecentlyNonNull String str);

    @Deprecated
    void warn(@RecentlyNonNull String str);
}
