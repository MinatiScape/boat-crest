package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.RecordingApi;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;
/* loaded from: classes8.dex */
public final class zzdo implements RecordingApi {
    public final PendingResult<Status> a(GoogleApiClient googleApiClient, Subscription subscription) {
        return googleApiClient.enqueue(new v0(this, googleApiClient, subscription));
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public final PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new t0(this, googleApiClient));
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public final PendingResult<Status> subscribe(GoogleApiClient googleApiClient, DataType dataType) {
        return a(googleApiClient, new Subscription.zza().zza(dataType).zzr());
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public final PendingResult<Status> unsubscribe(GoogleApiClient googleApiClient, DataType dataType) {
        return googleApiClient.execute(new u0(this, googleApiClient, dataType));
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public final PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient googleApiClient, DataType dataType) {
        return googleApiClient.enqueue(new s0(this, googleApiClient, dataType));
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public final PendingResult<Status> subscribe(GoogleApiClient googleApiClient, DataSource dataSource) {
        return a(googleApiClient, new Subscription.zza().zza(dataSource).zzr());
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public final PendingResult<Status> unsubscribe(GoogleApiClient googleApiClient, DataSource dataSource) {
        return googleApiClient.execute(new x0(this, googleApiClient, dataSource));
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public final PendingResult<Status> unsubscribe(GoogleApiClient googleApiClient, Subscription subscription) {
        if (subscription.getDataType() == null) {
            return unsubscribe(googleApiClient, (DataSource) Preconditions.checkNotNull(subscription.getDataSource()));
        }
        return unsubscribe(googleApiClient, (DataType) Preconditions.checkNotNull(subscription.getDataType()));
    }
}
