package com.google.firebase.crashlytics.internal.network;
/* loaded from: classes10.dex */
public class HttpResponse {

    /* renamed from: a  reason: collision with root package name */
    public final int f11245a;
    public final String b;

    public HttpResponse(int i, String str) {
        this.f11245a = i;
        this.b = str;
    }

    public String body() {
        return this.b;
    }

    public int code() {
        return this.f11245a;
    }
}
