package com.mappls.sdk.maps.module.http;
/* loaded from: classes11.dex */
public class MapplsMapManager {
    public static final MapplsMapManager b = new MapplsMapManager();

    /* renamed from: a  reason: collision with root package name */
    public String f12789a;

    public static MapplsMapManager getInstance() {
        return b;
    }

    public String getRawPublicKey() {
        return this.f12789a;
    }

    public void setKeyExpirationTime(long j) {
    }

    public void setRawPublicKey(String str) {
        this.f12789a = str;
    }
}
