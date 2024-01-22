package com.goodix.ble.libcomx.logger;

import com.goodix.ble.libcomx.ILogger;
/* loaded from: classes5.dex */
public class IndentedLogger implements ILogger {
    public ILogger h;
    public String i;
    public String j;

    public IndentedLogger(ILogger iLogger) {
        this.i = null;
        this.j = "null";
        this.h = iLogger;
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void d(String str, String str2) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            if (str == null) {
                str = this.j;
            }
            if (this.i != null) {
                str2 = this.i + str2;
            }
            iLogger.d(str, str2);
        }
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void e(String str, String str2) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            if (str == null) {
                str = this.j;
            }
            if (this.i != null) {
                str2 = this.i + str2;
            }
            iLogger.e(str, str2);
        }
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void i(String str, String str2) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            if (str == null) {
                str = this.j;
            }
            if (this.i != null) {
                str2 = this.i + str2;
            }
            iLogger.i(str, str2);
        }
    }

    public void setDefaultTag(String str) {
        if (str == null) {
            return;
        }
        this.j = str;
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public ILogger subLogger() {
        IndentedLogger indentedLogger = new IndentedLogger(this.h);
        String str = this.i;
        if (str != null) {
            indentedLogger.i = str + str;
        } else {
            indentedLogger.i = "  ";
        }
        indentedLogger.j = this.j;
        return indentedLogger;
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void v(String str, String str2) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            if (str == null) {
                str = this.j;
            }
            if (this.i != null) {
                str2 = this.i + str2;
            }
            iLogger.v(str, str2);
        }
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void w(String str, String str2) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            if (str == null) {
                str = this.j;
            }
            if (this.i != null) {
                str2 = this.i + str2;
            }
            iLogger.w(str, str2);
        }
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void e(String str, String str2, Throwable th) {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            if (str == null) {
                str = this.j;
            }
            if (this.i != null) {
                str2 = this.i + str2;
            }
            iLogger.e(str, str2, th);
        }
    }

    public IndentedLogger(ILogger iLogger, String str) {
        this.i = null;
        this.j = "null";
        this.h = iLogger;
        this.i = str;
    }
}
