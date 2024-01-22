package com.google.android.gms.common.api.internal;

import android.os.Handler;
import com.google.android.gms.common.api.internal.BackgroundDetector;
/* loaded from: classes6.dex */
public final class z implements BackgroundDetector.BackgroundStateChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleApiManager f8295a;

    public z(GoogleApiManager googleApiManager) {
        this.f8295a = googleApiManager;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z) {
        Handler handler;
        Handler handler2;
        GoogleApiManager googleApiManager = this.f8295a;
        handler = googleApiManager.w;
        handler2 = googleApiManager.w;
        handler.sendMessage(handler2.obtainMessage(1, Boolean.valueOf(z)));
    }
}
