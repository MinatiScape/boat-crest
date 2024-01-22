package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.result.SessionStopResult;
@Deprecated
/* loaded from: classes6.dex */
public interface SessionsApi {

    /* loaded from: classes6.dex */
    public static class ViewIntentBuilder {

        /* renamed from: a  reason: collision with root package name */
        public final Context f8424a;
        public Session b;
        @Nullable
        public String c;
        public boolean d = false;

        public ViewIntentBuilder(@NonNull Context context) {
            this.f8424a = context;
        }

        @NonNull
        public Intent build() {
            Intent intent;
            ResolveInfo resolveActivity;
            Preconditions.checkState(this.b != null, "Session must be set");
            Intent intent2 = new Intent(Fitness.ACTION_VIEW);
            intent2.setType(Session.getMimeType(this.b.getActivity()));
            SafeParcelableSerializer.serializeToIntentExtra(this.b, intent2, Session.EXTRA_SESSION);
            if (!this.d) {
                this.c = this.b.getAppPackageName();
            }
            String str = this.c;
            if (str == null || (resolveActivity = this.f8424a.getPackageManager().resolveActivity((intent = new Intent(intent2).setPackage(str)), 0)) == null) {
                return intent2;
            }
            intent.setComponent(new ComponentName(str, resolveActivity.activityInfo.name));
            return intent;
        }

        @NonNull
        public ViewIntentBuilder setPreferredApplication(@Nullable String str) {
            this.c = str;
            this.d = true;
            return this;
        }

        @NonNull
        public ViewIntentBuilder setSession(@NonNull Session session) {
            this.b = session;
            return this;
        }
    }

    @NonNull
    PendingResult<Status> insertSession(@NonNull GoogleApiClient googleApiClient, @NonNull SessionInsertRequest sessionInsertRequest);

    @NonNull
    PendingResult<SessionReadResult> readSession(@NonNull GoogleApiClient googleApiClient, @NonNull SessionReadRequest sessionReadRequest);

    @NonNull
    PendingResult<Status> registerForSessions(@NonNull GoogleApiClient googleApiClient, @NonNull PendingIntent pendingIntent);

    @NonNull
    PendingResult<Status> startSession(@NonNull GoogleApiClient googleApiClient, @NonNull Session session);

    @NonNull
    PendingResult<SessionStopResult> stopSession(@NonNull GoogleApiClient googleApiClient, @Nullable String str);

    @NonNull
    PendingResult<Status> unregisterForSessions(@NonNull GoogleApiClient googleApiClient, @NonNull PendingIntent pendingIntent);
}
