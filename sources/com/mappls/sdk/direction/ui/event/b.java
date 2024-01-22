package com.mappls.sdk.direction.ui.event;

import com.mappls.sdk.services.api.event.route.model.ReportDetails;
/* loaded from: classes11.dex */
public final class b implements d {

    /* renamed from: a  reason: collision with root package name */
    public final ReportDetails f12584a;

    public b(ReportDetails reportDetails) {
        this.f12584a = reportDetails;
    }

    @Override // com.mappls.sdk.direction.ui.event.d
    public final int a() {
        return 2;
    }

    public final ReportDetails b() {
        return this.f12584a;
    }
}
