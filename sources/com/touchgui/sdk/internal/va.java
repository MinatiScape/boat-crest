package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.bean.TGSportRecord;
/* loaded from: classes12.dex */
public final class va implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ eb f13835a;

    public va(eb ebVar) {
        this.f13835a = ebVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13835a.f13824a.a(-1);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        if (obj != null) {
            eb ebVar = this.f13835a;
            ebVar.getClass();
            TGSportRecord tGSportRecord = (TGSportRecord) obj;
            ua uaVar = ebVar.f.d;
            if (uaVar != null) {
                uaVar.onSportRecord(tGSportRecord);
            }
        }
        this.f13835a.getClass();
        this.f13835a.b();
    }
}
