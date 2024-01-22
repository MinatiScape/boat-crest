package com.mappls.sdk.services.log;

import android.util.Log;
import androidx.annotation.Keep;
@Keep
/* loaded from: classes8.dex */
public class LoggerUtils {
    private static final ApiLoggerDefinition DEFAULT;
    private static boolean enableLogging = false;
    private static volatile ApiLoggerDefinition loggerDefinition;

    /* loaded from: classes8.dex */
    public static class a implements ApiLoggerDefinition {
        @Override // com.mappls.sdk.services.log.ApiLoggerDefinition
        public void d(String str, String str2) {
            Log.d(str, str2);
        }

        @Override // com.mappls.sdk.services.log.ApiLoggerDefinition
        public void e(String str, String str2) {
            Log.e(str, str2);
        }

        @Override // com.mappls.sdk.services.log.ApiLoggerDefinition
        public void i(String str, String str2) {
            Log.i(str, str2);
        }

        @Override // com.mappls.sdk.services.log.ApiLoggerDefinition
        public void v(String str, String str2) {
            Log.v(str, str2);
        }

        @Override // com.mappls.sdk.services.log.ApiLoggerDefinition
        public void w(String str, String str2) {
            Log.w(str, str2);
        }

        @Override // com.mappls.sdk.services.log.ApiLoggerDefinition
        public void d(String str, String str2, Throwable th) {
            Log.d(str, str2, th);
        }

        @Override // com.mappls.sdk.services.log.ApiLoggerDefinition
        public void e(String str, String str2, Throwable th) {
            Log.e(str, str2, th);
        }

        @Override // com.mappls.sdk.services.log.ApiLoggerDefinition
        public void i(String str, String str2, Throwable th) {
            Log.i(str, str2, th);
        }

        @Override // com.mappls.sdk.services.log.ApiLoggerDefinition
        public void v(String str, String str2, Throwable th) {
            Log.v(str, str2, th);
        }

        @Override // com.mappls.sdk.services.log.ApiLoggerDefinition
        public void w(String str, String str2, Throwable th) {
            Log.w(str, str2, th);
        }
    }

    static {
        a aVar = new a();
        DEFAULT = aVar;
        loggerDefinition = aVar;
    }

    public static void d(String str, String str2) {
        if (enableLogging) {
            loggerDefinition.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (enableLogging) {
            loggerDefinition.w(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (enableLogging) {
            loggerDefinition.i(str, str2);
        }
    }

    public static void setEnableLogging(boolean z) {
        enableLogging = z;
    }

    public static void setLoggerDefinition(ApiLoggerDefinition apiLoggerDefinition) {
        loggerDefinition = apiLoggerDefinition;
    }

    public static void v(String str, String str2) {
        if (enableLogging) {
            loggerDefinition.v(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (enableLogging) {
            loggerDefinition.w(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (enableLogging) {
            loggerDefinition.d(str, str2, th);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (enableLogging) {
            loggerDefinition.w(str, str2, th);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (enableLogging) {
            loggerDefinition.i(str, str2, th);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (enableLogging) {
            loggerDefinition.v(str, str2, th);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (enableLogging) {
            loggerDefinition.w(str, str2, th);
        }
    }
}
