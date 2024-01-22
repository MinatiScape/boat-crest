package com.elvishew.xlog.interceptor;

import com.elvishew.xlog.LogItem;
/* loaded from: classes9.dex */
public abstract class AbstractFilterInterceptor implements Interceptor {
    @Override // com.elvishew.xlog.interceptor.Interceptor
    public LogItem intercept(LogItem logItem) {
        if (reject(logItem)) {
            return null;
        }
        return logItem;
    }

    public abstract boolean reject(LogItem logItem);
}
