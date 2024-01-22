package com.mappls.sdk.direction.ui.event;

import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public final class c implements d {

    /* renamed from: a  reason: collision with root package name */
    public final String f12585a;
    public final String b;
    public final int c;
    public final boolean d;
    public final ArrayList<ReportDetails> e;

    public c(String str, ArrayList<ReportDetails> arrayList, String str2, int i, boolean z) {
        this.f12585a = str;
        this.b = str2;
        this.d = z;
        this.c = i;
        this.e = arrayList;
    }

    @Override // com.mappls.sdk.direction.ui.event.d
    public final int a() {
        return 1;
    }

    public final String b() {
        return this.f12585a;
    }

    public final String c() {
        return this.b;
    }

    public final ArrayList<ReportDetails> d() {
        return this.e;
    }

    public final int e() {
        return this.c;
    }

    public final boolean f() {
        return this.d;
    }
}
