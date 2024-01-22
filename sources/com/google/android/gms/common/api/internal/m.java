package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public final class m extends q {
    public final ArrayList i;
    public final /* synthetic */ zaaw j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(zaaw zaawVar, ArrayList arrayList) {
        super(zaawVar, null);
        this.j = zaawVar;
        this.i = arrayList;
    }

    @Override // com.google.android.gms.common.api.internal.q
    @WorkerThread
    public final void a() {
        zabi zabiVar;
        IAccountAccessor iAccountAccessor;
        zabi zabiVar2;
        zaaw zaawVar = this.j;
        zabiVar = zaawVar.f8298a;
        zabiVar.n.p = zaaw.q(zaawVar);
        ArrayList arrayList = this.i;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            zaaw zaawVar2 = this.j;
            iAccountAccessor = zaawVar2.o;
            zabiVar2 = zaawVar2.f8298a;
            ((Api.Client) arrayList.get(i)).getRemoteService(iAccountAccessor, zabiVar2.n.p);
        }
    }
}
