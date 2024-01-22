package org.slf4j.helpers;
/* loaded from: classes13.dex */
public class FormattingTuple {
    public static FormattingTuple NULL = new FormattingTuple(null);

    /* renamed from: a  reason: collision with root package name */
    public String f15575a;
    public Throwable b;
    public Object[] c;

    public FormattingTuple(String str) {
        this(str, null, null);
    }

    public Object[] getArgArray() {
        return this.c;
    }

    public String getMessage() {
        return this.f15575a;
    }

    public Throwable getThrowable() {
        return this.b;
    }

    public FormattingTuple(String str, Object[] objArr, Throwable th) {
        this.f15575a = str;
        this.b = th;
        this.c = objArr;
    }
}
