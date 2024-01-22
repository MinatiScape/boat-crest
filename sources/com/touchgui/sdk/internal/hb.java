package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGErrorCode;
/* loaded from: classes12.dex */
public final class hb implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ob f13773a;

    public hb(ob obVar) {
        this.f13773a = obVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        ob.a(this.f13773a, TGErrorCode.ERROR_WORKOUT_QUERY_RECORD_COUNT);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        ob obVar;
        int i;
        f8 f8Var = (f8) obj;
        int i2 = f8Var.f13762a;
        if (i2 == 0) {
            new nb(this.f13773a, ((Integer) f8Var.b).intValue()).a();
            return;
        }
        if (i2 == 6) {
            obVar = this.f13773a;
            i = TGErrorCode.ERROR_WORKOUT_DEVICE_BUSY;
        } else {
            obVar = this.f13773a;
            i = TGErrorCode.ERROR_WORKOUT_QUERY_RECORD_COUNT;
        }
        ob.a(obVar, i);
    }
}
