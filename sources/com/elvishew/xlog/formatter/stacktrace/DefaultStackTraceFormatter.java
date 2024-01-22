package com.elvishew.xlog.formatter.stacktrace;

import com.elvishew.xlog.internal.SystemCompat;
/* loaded from: classes9.dex */
public class DefaultStackTraceFormatter implements StackTraceFormatter {
    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(StackTraceElement[] stackTraceElementArr) {
        StringBuilder sb = new StringBuilder(256);
        if (stackTraceElementArr == null || stackTraceElementArr.length == 0) {
            return null;
        }
        if (stackTraceElementArr.length == 1) {
            return "\t─ " + stackTraceElementArr[0].toString();
        }
        int length = stackTraceElementArr.length;
        for (int i = 0; i < length; i++) {
            if (i != length - 1) {
                sb.append("\t├ ");
                sb.append(stackTraceElementArr[i].toString());
                sb.append(SystemCompat.lineSeparator);
            } else {
                sb.append("\t└ ");
                sb.append(stackTraceElementArr[i].toString());
            }
        }
        return sb.toString();
    }
}
