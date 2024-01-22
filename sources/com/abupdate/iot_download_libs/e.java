package com.abupdate.iot_download_libs;
/* loaded from: classes.dex */
public class e extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private int f1883a;
    private Throwable b;

    public e(int i) {
        this.f1883a = i;
    }

    public int a() {
        return this.f1883a;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.b;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String str = getMessage() + " (" + this.f1883a + ")";
        if (this.b != null) {
            return str + " - " + this.b.toString();
        }
        return str;
    }
}
