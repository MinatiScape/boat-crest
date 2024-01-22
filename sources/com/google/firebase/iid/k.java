package com.google.firebase.iid;
/* loaded from: classes10.dex */
public final class k implements InstanceIdResult {

    /* renamed from: a  reason: collision with root package name */
    public final String f11295a;
    public final String b;

    public k(String str, String str2) {
        this.f11295a = str;
        this.b = str2;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public String getId() {
        return this.f11295a;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public String getToken() {
        return this.b;
    }
}
