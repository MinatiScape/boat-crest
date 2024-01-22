package com.goodix.ble.libcomx.logger;

import com.goodix.ble.libcomx.ILogger;
import java.util.HashMap;
/* loaded from: classes5.dex */
public class PrefixedLogger implements ILogger {
    public ILogger h;
    public String i;
    public String j;
    public HashMap<String, String> k;

    public PrefixedLogger(ILogger iLogger) {
        this.i = null;
        this.j = "-";
        this.k = new HashMap<>(32);
        this.h = iLogger;
    }

    public final synchronized String a(String str) {
        String str2 = this.i;
        if (str == null) {
            if (str2 == null) {
                str2 = "null";
            }
            return str2;
        } else if (str2 == null) {
            return str;
        } else {
            String str3 = this.k.get(str);
            if (str3 == null) {
                str3 = str2 + this.j + str;
                this.k.put(str, str3);
            }
            return str3;
        }
    }

    public synchronized void clearTagCache() {
        this.k.clear();
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void d(String str, String str2) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.d(a(str), str2);
        }
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void e(String str, String str2) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.e(a(str), str2);
        }
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void i(String str, String str2) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.i(a(str), str2);
        }
    }

    public void setTagDelimiter(String str) {
        this.j = str;
    }

    public void setTagPrefix(String str) {
        this.i = str;
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void v(String str, String str2) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.v(a(str), str2);
        }
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void w(String str, String str2) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.w(a(str), str2);
        }
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void e(String str, String str2, Throwable th) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.e(a(str), str2, th);
        }
    }

    public PrefixedLogger(ILogger iLogger, String str) {
        this.i = null;
        this.j = "-";
        this.k = new HashMap<>(32);
        this.h = iLogger;
        this.i = str;
    }
}
