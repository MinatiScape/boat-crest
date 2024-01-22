package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGErrorCode;
/* loaded from: classes12.dex */
public final class mb implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ nb f13797a;

    public mb(nb nbVar) {
        this.f13797a = nbVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        ob.a(this.f13797a.e, TGErrorCode.ERROR_WORKOUT_SYNC_COMPLETED);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        f8 f8Var = (f8) obj;
        this.f13797a.a();
    }
}
