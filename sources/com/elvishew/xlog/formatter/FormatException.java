package com.elvishew.xlog.formatter;
/* loaded from: classes9.dex */
public class FormatException extends RuntimeException {
    private static final long serialVersionUID = -5365630128856068164L;

    public FormatException() {
    }

    public FormatException(String str) {
        super(str);
    }

    public FormatException(String str, Throwable th) {
        super(str, th);
    }

    public FormatException(Throwable th) {
        super(th);
    }
}
