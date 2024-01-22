package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResult;
import java.util.concurrent.TimeUnit;
@Deprecated
/* loaded from: classes6.dex */
public interface HistoryApi {

    /* loaded from: classes6.dex */
    public static class ViewIntentBuilder {

        /* renamed from: a  reason: collision with root package name */
        public final Context f8423a;
        public final DataType b;
        public DataSource c;
        public long d;
        public long e;
        public String f;

        public ViewIntentBuilder(@NonNull Context context, @NonNull DataType dataType) {
            this.f8423a = context;
            this.b = dataType;
        }

        @NonNull
        public Intent build() {
            Intent intent;
            ResolveInfo resolveActivity;
            Preconditions.checkState(this.d > 0, "Start time must be set");
            Preconditions.checkState(this.e > this.d, "End time must be set and after start time");
            Intent intent2 = new Intent(Fitness.ACTION_VIEW);
            intent2.setType(DataType.getMimeType(this.c.getDataType()));
            intent2.putExtra(Fitness.EXTRA_START_TIME, this.d);
            intent2.putExtra(Fitness.EXTRA_END_TIME, this.e);
            SafeParcelableSerializer.serializeToIntentExtra(this.c, intent2, DataSource.EXTRA_DATA_SOURCE);
            if (this.f == null || (resolveActivity = this.f8423a.getPackageManager().resolveActivity((intent = new Intent(intent2).setPackage(this.f)), 0)) == null) {
                return intent2;
            }
            intent.setComponent(new ComponentName(this.f, resolveActivity.activityInfo.name));
            return intent;
        }

        @NonNull
        public ViewIntentBuilder setDataSource(@NonNull DataSource dataSource) {
            Preconditions.checkArgument(dataSource.getDataType().equals(this.b), "Data source %s is not for the data type %s", dataSource, this.b);
            this.c = dataSource;
            return this;
        }

        @NonNull
        public ViewIntentBuilder setPreferredApplication(@NonNull String str) {
            this.f = str;
            return this;
        }

        @NonNull
        public ViewIntentBuilder setTimeInterval(long j, long j2, @NonNull TimeUnit timeUnit) {
            this.d = timeUnit.toMillis(j);
            this.e = timeUnit.toMillis(j2);
            return this;
        }
    }

    @NonNull
    PendingResult<Status> deleteData(@NonNull GoogleApiClient googleApiClient, @NonNull DataDeleteRequest dataDeleteRequest);

    @NonNull
    PendingResult<Status> insertData(@NonNull GoogleApiClient googleApiClient, @NonNull DataSet dataSet);

    @NonNull
    PendingResult<DailyTotalResult> readDailyTotal(@NonNull GoogleApiClient googleApiClient, @NonNull DataType dataType);

    @NonNull
    PendingResult<DailyTotalResult> readDailyTotalFromLocalDevice(@NonNull GoogleApiClient googleApiClient, @NonNull DataType dataType);

    @NonNull
    PendingResult<DataReadResult> readData(@NonNull GoogleApiClient googleApiClient, @NonNull DataReadRequest dataReadRequest);

    @NonNull
    PendingResult<Status> registerDataUpdateListener(@NonNull GoogleApiClient googleApiClient, @NonNull DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest);

    @NonNull
    PendingResult<Status> unregisterDataUpdateListener(@NonNull GoogleApiClient googleApiClient, @NonNull PendingIntent pendingIntent);

    @NonNull
    PendingResult<Status> updateData(@NonNull GoogleApiClient googleApiClient, @NonNull DataUpdateRequest dataUpdateRequest);
}
