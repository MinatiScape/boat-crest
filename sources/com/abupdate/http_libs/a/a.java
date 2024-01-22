package com.abupdate.http_libs.a;
/* loaded from: classes.dex */
public class a extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private int f1863a;
    private Throwable b;

    public a(int i) {
        this.f1863a = i;
    }

    public a(int i, Throwable th) {
        this.f1863a = i;
        this.b = th;
    }

    public a(Throwable th) {
        this.b = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.b;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String str = getMessage() + " (" + this.f1863a + ")";
        if (this.b != null) {
            return str + " - " + this.b.toString();
        }
        return str;
    }
}
