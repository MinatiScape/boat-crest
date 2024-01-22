package com.google.android.gms.auth.api.accounttransfer;
/* loaded from: classes6.dex */
public final class f extends j {
    public final /* synthetic */ g b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(g gVar, k kVar) {
        super(kVar);
        this.b = gVar;
    }

    @Override // com.google.android.gms.internal.auth.zzan, com.google.android.gms.internal.auth.zzat
    public final void zzc(DeviceMetaData deviceMetaData) {
        this.b.d.setResult(deviceMetaData);
    }
}
