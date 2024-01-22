package org.bouncycastle.util.test;

import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class SimpleTestResult implements TestResult {
    public static final String d = Strings.lineSeparator();

    /* renamed from: a  reason: collision with root package name */
    public boolean f15408a;
    public String b;
    public Throwable c;

    public SimpleTestResult(boolean z, String str) {
        this.f15408a = z;
        this.b = str;
    }

    public SimpleTestResult(boolean z, String str, Throwable th) {
        this.f15408a = z;
        this.b = str;
        this.c = th;
    }

    public static TestResult failed(Test test, String str) {
        return new SimpleTestResult(false, test.getName() + ": " + str);
    }

    public static TestResult failed(Test test, String str, Object obj, Object obj2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String str2 = d;
        sb.append(str2);
        sb.append("Expected: ");
        sb.append(obj);
        sb.append(str2);
        sb.append("Found   : ");
        sb.append(obj2);
        return failed(test, sb.toString());
    }

    public static TestResult failed(Test test, String str, Throwable th) {
        return new SimpleTestResult(false, test.getName() + ": " + str, th);
    }

    public static String failedMessage(String str, String str2, String str3, String str4) {
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.append(" failing ");
        stringBuffer.append(str2);
        String str5 = d;
        stringBuffer.append(str5);
        stringBuffer.append("    expected: ");
        stringBuffer.append(str3);
        stringBuffer.append(str5);
        stringBuffer.append("    got     : ");
        stringBuffer.append(str4);
        return stringBuffer.toString();
    }

    public static TestResult successful(Test test, String str) {
        return new SimpleTestResult(true, test.getName() + ": " + str);
    }

    @Override // org.bouncycastle.util.test.TestResult
    public Throwable getException() {
        return this.c;
    }

    @Override // org.bouncycastle.util.test.TestResult
    public boolean isSuccessful() {
        return this.f15408a;
    }

    @Override // org.bouncycastle.util.test.TestResult
    public String toString() {
        return this.b;
    }
}
