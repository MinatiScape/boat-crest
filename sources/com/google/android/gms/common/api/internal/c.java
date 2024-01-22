package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.Map;
/* loaded from: classes6.dex */
public final class c implements PendingResult.StatusListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BasePendingResult f8271a;
    public final /* synthetic */ zaad b;

    public c(zaad zaadVar, BasePendingResult basePendingResult) {
        this.b = zaadVar;
        this.f8271a = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        Map map;
        map = this.b.f8296a;
        map.remove(this.f8271a);
    }
}
