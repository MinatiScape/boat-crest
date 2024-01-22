package com.clevertap.android.sdk;

import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTLockManager {

    /* renamed from: a  reason: collision with root package name */
    public final Boolean f2566a = Boolean.TRUE;
    public final Object b = new Object();

    public Boolean getEventLock() {
        return this.f2566a;
    }

    public Object getInboxControllerLock() {
        return this.b;
    }
}
