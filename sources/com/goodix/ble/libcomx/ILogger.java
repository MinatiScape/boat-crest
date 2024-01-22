package com.goodix.ble.libcomx;
/* loaded from: classes5.dex */
public interface ILogger {
    void d(String str, String str2);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    void i(String str, String str2);

    default ILogger subLogger() {
        return this;
    }

    void v(String str, String str2);

    void w(String str, String str2);
}
