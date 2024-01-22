package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;
import java.util.Collections;
/* loaded from: classes8.dex */
public final class y0 extends n<DataSourcesResult> {
    public final /* synthetic */ DataSourcesRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y0(zzdx zzdxVar, GoogleApiClient googleApiClient, DataSourcesRequest dataSourcesRequest) {
        super(googleApiClient);
        this.c = dataSourcesRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new DataSourcesResult(Collections.emptyList(), status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzap zzapVar) throws RemoteException {
        ((zzby) zzapVar.getService()).zza(new DataSourcesRequest(this.c, new zzl(this)));
    }
}
