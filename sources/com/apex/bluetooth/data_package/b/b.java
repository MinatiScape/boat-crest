package com.apex.bluetooth.data_package.b;
/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    public volatile boolean f2207a;
    public a b;

    /* loaded from: classes.dex */
    public enum a {
        east_apex_01,
        east_apex_02,
        east_apex_03,
        east_apex_04,
        east_apex_05
    }

    public abstract com.apex.bluetooth.data_package.b.a<byte[]> a();

    public void a(boolean z) {
        this.f2207a = z;
    }

    public boolean b() {
        return this.f2207a;
    }
}
