package com.elvishew.xlog.formatter.message.throwable;

import com.elvishew.xlog.internal.util.StackTraceUtil;
/* loaded from: classes9.dex */
public class DefaultThrowableFormatter implements ThrowableFormatter {
    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(Throwable th) {
        return StackTraceUtil.getStackTraceString(th);
    }
}
