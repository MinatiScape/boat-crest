package com.google.android.gms.internal.auth;

import android.accounts.Account;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public final class d extends h {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ e f8520a;

    public d(e eVar) {
        this.f8520a = eVar;
    }

    @Override // com.google.android.gms.internal.auth.h, com.google.android.gms.auth.account.zzb
    public final void zzb(@Nullable Account account) {
        this.f8520a.setResult((e) new i(account != null ? Status.RESULT_SUCCESS : zzal.f8550a, account));
    }
}
