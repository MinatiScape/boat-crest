package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.internal.fitness.zzdd;
import com.google.android.gms.internal.fitness.zzw;
import com.google.android.gms.tasks.Task;
import java.util.List;
/* loaded from: classes6.dex */
public class GoalsClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    public static final GoalsApi j = new zzdd();

    @ShowFirstParty
    public GoalsClient(@NonNull Context context, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzw.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    public Task<List<Goal>> readCurrentGoals(@NonNull GoalsReadRequest goalsReadRequest) {
        return PendingResultUtil.toTask(j.readCurrentGoals(asGoogleApiClient(), goalsReadRequest), g.f8438a);
    }

    public GoalsClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzw.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
