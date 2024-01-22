package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGErrorCode;
/* loaded from: classes12.dex */
public final class pa implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f13813a;
    public final /* synthetic */ gb b;

    public pa(gb gbVar, int i) {
        this.b = gbVar;
        this.f13813a = i;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.b.a(TGErrorCode.ERROR_WORKOUT_QUERY_RECORD_COUNT, "Failed to synchronize sport data");
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Integer num = (Integer) obj;
        if (num == null) {
            return;
        }
        if (num.intValue() > 0) {
            gb gbVar = this.b;
            gbVar.b.add(new eb(gbVar, num.intValue()));
        }
        int i = this.f13813a;
        if (i == 0) {
            gb gbVar2 = this.b;
            new v8(gbVar2.f13767a, new x6()).execute(new qa(gbVar2, i));
            return;
        }
        gb.a(this.b, i);
    }
}
