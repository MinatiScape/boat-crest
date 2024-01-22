package com.google.firebase.crashlytics.internal.stacktrace;
/* loaded from: classes10.dex */
public class MiddleOutFallbackStrategy implements StackTraceTrimmingStrategy {

    /* renamed from: a  reason: collision with root package name */
    public final int f11260a;
    public final StackTraceTrimmingStrategy[] b;
    public final MiddleOutStrategy c;

    public MiddleOutFallbackStrategy(int i, StackTraceTrimmingStrategy... stackTraceTrimmingStrategyArr) {
        this.f11260a = i;
        this.b = stackTraceTrimmingStrategyArr;
        this.c = new MiddleOutStrategy(i);
    }

    @Override // com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy
    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stackTraceElementArr) {
        StackTraceTrimmingStrategy[] stackTraceTrimmingStrategyArr;
        if (stackTraceElementArr.length <= this.f11260a) {
            return stackTraceElementArr;
        }
        StackTraceElement[] stackTraceElementArr2 = stackTraceElementArr;
        for (StackTraceTrimmingStrategy stackTraceTrimmingStrategy : this.b) {
            if (stackTraceElementArr2.length <= this.f11260a) {
                break;
            }
            stackTraceElementArr2 = stackTraceTrimmingStrategy.getTrimmedStackTrace(stackTraceElementArr);
        }
        return stackTraceElementArr2.length > this.f11260a ? this.c.getTrimmedStackTrace(stackTraceElementArr2) : stackTraceElementArr2;
    }
}
