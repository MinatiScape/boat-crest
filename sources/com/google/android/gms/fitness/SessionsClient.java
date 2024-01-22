package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResponse;
import com.google.android.gms.internal.fitness.zzav;
import com.google.android.gms.internal.fitness.zzeb;
import com.google.android.gms.tasks.Task;
import java.util.List;
/* loaded from: classes6.dex */
public class SessionsClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    public static final SessionsApi j = new zzeb();

    @ShowFirstParty
    public SessionsClient(@NonNull Context context, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzav.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    public Task<Void> insertSession(@NonNull SessionInsertRequest sessionInsertRequest) {
        return PendingResultUtil.toVoidTask(j.insertSession(asGoogleApiClient(), sessionInsertRequest));
    }

    @NonNull
    public Task<SessionReadResponse> readSession(@NonNull SessionReadRequest sessionReadRequest) {
        return PendingResultUtil.toResponseTask(j.readSession(asGoogleApiClient(), sessionReadRequest), new SessionReadResponse());
    }

    @NonNull
    public Task<Void> registerForSessions(@NonNull PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(j.registerForSessions(asGoogleApiClient(), pendingIntent));
    }

    @NonNull
    public Task<Void> startSession(@NonNull Session session) {
        return PendingResultUtil.toVoidTask(j.startSession(asGoogleApiClient(), session));
    }

    @NonNull
    public Task<List<Session>> stopSession(@Nullable String str) {
        return PendingResultUtil.toTask(j.stopSession(asGoogleApiClient(), str), o.f8446a);
    }

    @NonNull
    public Task<Void> unregisterForSessions(@NonNull PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(j.unregisterForSessions(asGoogleApiClient(), pendingIntent));
    }

    public SessionsClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzav.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
