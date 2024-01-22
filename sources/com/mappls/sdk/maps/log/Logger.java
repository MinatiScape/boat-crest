package com.mappls.sdk.maps.log;

import android.util.Log;
import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes11.dex */
public final class Logger {
    public static final int DEBUG = 3;
    private static final LoggerDefinition DEFAULT;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int NONE = 99;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static int logLevel;
    private static volatile LoggerDefinition logger;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface LogLevel {
    }

    /* loaded from: classes11.dex */
    public class a implements LoggerDefinition {
        @Override // com.mappls.sdk.maps.log.LoggerDefinition
        public void d(String str, String str2) {
            Log.d(str, str2);
        }

        @Override // com.mappls.sdk.maps.log.LoggerDefinition
        public void e(String str, String str2) {
            Log.e(str, str2);
        }

        @Override // com.mappls.sdk.maps.log.LoggerDefinition
        public void i(String str, String str2) {
            Log.i(str, str2);
        }

        @Override // com.mappls.sdk.maps.log.LoggerDefinition
        public void v(String str, String str2) {
            Log.v(str, str2);
        }

        @Override // com.mappls.sdk.maps.log.LoggerDefinition
        public void w(String str, String str2) {
            Log.w(str, str2);
        }

        @Override // com.mappls.sdk.maps.log.LoggerDefinition
        public void d(String str, String str2, Throwable th) {
            Log.d(str, str2, th);
        }

        @Override // com.mappls.sdk.maps.log.LoggerDefinition
        public void e(String str, String str2, Throwable th) {
            Log.e(str, str2, th);
        }

        @Override // com.mappls.sdk.maps.log.LoggerDefinition
        public void i(String str, String str2, Throwable th) {
            Log.i(str, str2, th);
        }

        @Override // com.mappls.sdk.maps.log.LoggerDefinition
        public void v(String str, String str2, Throwable th) {
            Log.v(str, str2, th);
        }

        @Override // com.mappls.sdk.maps.log.LoggerDefinition
        public void w(String str, String str2, Throwable th) {
            Log.w(str, str2, th);
        }
    }

    static {
        a aVar = new a();
        DEFAULT = aVar;
        logger = aVar;
    }

    public static void d(String str, String str2) {
        if (logLevel <= 3) {
            logger.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (logLevel <= 6) {
            logger.e(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (logLevel <= 4) {
            logger.i(str, str2);
        }
    }

    public static void log(int i, String str, String str2) {
        if (i == 2) {
            v(str, str2);
        } else if (i == 3) {
            d(str, str2);
        } else if (i == 4) {
            i(str, str2);
        } else if (i == 5) {
            w(str, str2);
        } else if (i == 6) {
            e(str, str2);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public static void setLoggerDefinition(LoggerDefinition loggerDefinition) {
        logger = loggerDefinition;
    }

    public static void setVerbosity(int i) {
        logLevel = i;
    }

    public static void v(String str, String str2) {
        if (logLevel <= 2) {
            logger.v(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (logLevel <= 5) {
            logger.w(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (logLevel <= 3) {
            logger.d(str, str2, th);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (logLevel <= 6) {
            logger.e(str, str2, th);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (logLevel <= 4) {
            logger.i(str, str2, th);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (logLevel <= 2) {
            logger.v(str, str2, th);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (logLevel <= 5) {
            logger.w(str, str2, th);
        }
    }
}