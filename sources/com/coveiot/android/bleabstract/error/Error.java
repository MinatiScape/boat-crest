package com.coveiot.android.bleabstract.error;
/* loaded from: classes2.dex */
public class Error {

    /* renamed from: a  reason: collision with root package name */
    public Type f3298a;
    public String b;

    public Error(Type type, String str) {
        this.f3298a = type;
        this.b = str;
    }

    public String getMessage() {
        return this.b;
    }

    public Type getType() {
        return this.f3298a;
    }

    public String toString() {
        return "Error{type=" + this.f3298a.toString() + ", message='" + this.b + "'}";
    }
}
