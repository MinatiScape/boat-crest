package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
/* loaded from: classes10.dex */
public final class m1 implements a4 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzhv f10125a;

    public m1(zzhv zzhvVar) {
        this.f10125a = zzhvVar;
    }

    @Override // com.google.android.gms.measurement.internal.a4
    public final void a(String str, String str2, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.f10125a.zzE("auto", "_err", bundle, str);
        } else {
            this.f10125a.zzC("auto", "_err", bundle);
        }
    }
}
