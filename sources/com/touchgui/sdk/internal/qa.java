package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGErrorCode;
import com.touchgui.sdk.bean.TGSyncData;
/* loaded from: classes12.dex */
public final class qa implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f13819a;
    public final /* synthetic */ gb b;

    public qa(gb gbVar, int i) {
        this.b = gbVar;
        this.f13819a = i;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.b.a(TGErrorCode.ERROR_WORKOUT_SYNC_DETAIL, "Failed to synchronize sport data");
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        int i;
        TGSyncData tGSyncData = (TGSyncData) obj;
        if (tGSyncData == null) {
            gb.a(this.b, this.f13819a);
            return;
        }
        int i2 = this.f13819a;
        if ((i2 == 0 || i2 == 2) && tGSyncData.getGpsCount() > 0) {
            gb gbVar = this.b;
            gbVar.b.add(new za(gbVar, false));
            gb gbVar2 = this.b;
            gbVar2.b.add(new za(gbVar2, true));
            if (this.b.f13767a.d() == 1) {
                gb gbVar3 = this.b;
                gbVar3.b.add(new ta(gbVar3));
            }
        }
        int i3 = this.f13819a;
        if ((i3 == 0 || i3 == 3) && tGSyncData.getSwimCount() > 0) {
            gb gbVar4 = this.b;
            gbVar4.b.add(new fb(gbVar4, false));
            gb gbVar5 = this.b;
            gbVar5.b.add(new fb(gbVar5, true));
        }
        if (this.b.f13767a.d() == 1 && ((i = this.f13819a) == 0 || i == 6)) {
            gb gbVar6 = this.b;
            gbVar6.b.add(new db(gbVar6, false));
            gb gbVar7 = this.b;
            gbVar7.b.add(new db(gbVar7, true));
        }
        this.b.f13767a.getClass();
        gb.a(this.b, this.f13819a);
    }
}
