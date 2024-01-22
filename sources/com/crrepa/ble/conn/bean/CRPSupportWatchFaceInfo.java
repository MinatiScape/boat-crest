package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPSupportWatchFaceInfo {
    public static final int DEFAULT_WATCH_FACE_ID = 65535;

    /* renamed from: a  reason: collision with root package name */
    public int f7669a;
    public List<Integer> b;

    public CRPSupportWatchFaceInfo(int i, List<Integer> list) {
        this.f7669a = i;
        this.b = list;
    }

    public int getDisplayWatchFace() {
        return this.f7669a;
    }

    public List<Integer> getSupportWatchFaceList() {
        return this.b;
    }
}
