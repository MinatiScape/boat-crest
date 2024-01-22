package com.google.firebase.installations.time;
/* loaded from: classes10.dex */
public class SystemClock implements Clock {

    /* renamed from: a  reason: collision with root package name */
    public static SystemClock f11325a;

    public static SystemClock getInstance() {
        if (f11325a == null) {
            f11325a = new SystemClock();
        }
        return f11325a;
    }

    @Override // com.google.firebase.installations.time.Clock
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
