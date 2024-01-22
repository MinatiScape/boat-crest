package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.fitness.zzad;
import com.google.android.gms.internal.fitness.zzaj;
import com.google.android.gms.internal.fitness.zzap;
import com.google.android.gms.internal.fitness.zzav;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcw;
import com.google.android.gms.internal.fitness.zzdd;
import com.google.android.gms.internal.fitness.zzde;
import com.google.android.gms.internal.fitness.zzdo;
import com.google.android.gms.internal.fitness.zzdx;
import com.google.android.gms.internal.fitness.zzeb;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.internal.fitness.zzk;
import com.google.android.gms.internal.fitness.zzq;
import com.google.android.gms.internal.fitness.zzw;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public class Fitness {
    @NonNull
    public static final String ACTION_TRACK = "vnd.google.fitness.TRACK";
    @NonNull
    public static final String ACTION_VIEW = "vnd.google.fitness.VIEW";
    @NonNull
    public static final String ACTION_VIEW_GOAL = "vnd.google.fitness.VIEW_GOAL";
    @NonNull
    @Deprecated
    public static final Void API = null;
    @NonNull
    @Deprecated
    public static final BleApi BleApi;
    @NonNull
    public static final String EXTRA_END_TIME = "vnd.google.fitness.end_time";
    @NonNull
    public static final String EXTRA_START_TIME = "vnd.google.fitness.start_time";
    @NonNull
    public static final Scope SCOPE_ACTIVITY_READ;
    @NonNull
    public static final Scope SCOPE_ACTIVITY_READ_WRITE;
    @NonNull
    public static final Scope SCOPE_BODY_READ;
    @NonNull
    public static final Scope SCOPE_BODY_READ_WRITE;
    @NonNull
    public static final Scope SCOPE_LOCATION_READ;
    @NonNull
    public static final Scope SCOPE_LOCATION_READ_WRITE;
    @NonNull
    public static final Scope SCOPE_NUTRITION_READ;
    @NonNull
    public static final Scope SCOPE_NUTRITION_READ_WRITE;
    @NonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> SENSORS_API = zzap.API;
    @NonNull
    @Deprecated
    public static final SensorsApi SensorsApi = new zzdx();
    @NonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> RECORDING_API = zzaj.API;
    @NonNull
    @Deprecated
    public static final RecordingApi RecordingApi = new zzdo();
    @NonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> SESSIONS_API = zzav.API;
    @NonNull
    @Deprecated
    public static final SessionsApi SessionsApi = new zzeb();
    @NonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> HISTORY_API = zzad.API;
    @NonNull
    @Deprecated
    public static final HistoryApi HistoryApi = new zzde();
    @NonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> GOALS_API = zzw.API;
    @NonNull
    @Deprecated
    public static final GoalsApi GoalsApi = new zzdd();
    @NonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> CONFIG_API = zzq.API;
    @NonNull
    @Deprecated
    public static final ConfigApi ConfigApi = new zzcw();
    @NonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> BLE_API = zzk.API;

    static {
        BleApi zzenVar;
        if (Build.VERSION.SDK_INT >= 18) {
            zzenVar = new zzco();
        } else {
            zzenVar = new zzen();
        }
        BleApi = zzenVar;
        SCOPE_ACTIVITY_READ = new Scope("https://www.googleapis.com/auth/fitness.activity.read");
        SCOPE_ACTIVITY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.activity.write");
        SCOPE_LOCATION_READ = new Scope("https://www.googleapis.com/auth/fitness.location.read");
        SCOPE_LOCATION_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.location.write");
        SCOPE_BODY_READ = new Scope("https://www.googleapis.com/auth/fitness.body.read");
        SCOPE_BODY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.body.write");
        SCOPE_NUTRITION_READ = new Scope("https://www.googleapis.com/auth/fitness.nutrition.read");
        SCOPE_NUTRITION_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.nutrition.write");
        new Scope("https://www.googleapis.com/auth/fitness.heart_rate.read");
        new Scope("https://www.googleapis.com/auth/fitness.heart_rate.write");
        new Scope("https://www.googleapis.com/auth/fitness.respiratory_rate.read");
        new Scope("https://www.googleapis.com/auth/fitness.respiratory_rate.write");
        new Scope("https://www.googleapis.com/auth/fitness.sleep.read");
        new Scope("https://www.googleapis.com/auth/fitness.sleep.write");
    }

    @NonNull
    @Deprecated
    public static BleClient getBleClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new BleClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @NonNull
    public static ConfigClient getConfigClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new ConfigClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    public static long getEndTime(@NonNull Intent intent, @NonNull TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_END_TIME, -1L);
        if (longExtra == -1) {
            return -1L;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }

    @NonNull
    public static GoalsClient getGoalsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new GoalsClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @NonNull
    public static HistoryClient getHistoryClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new HistoryClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @NonNull
    public static RecordingClient getRecordingClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new RecordingClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @NonNull
    public static SensorsClient getSensorsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SensorsClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @NonNull
    public static SessionsClient getSessionsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SessionsClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    public static long getStartTime(@NonNull Intent intent, @NonNull TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_START_TIME, -1L);
        if (longExtra == -1) {
            return -1L;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }

    @NonNull
    @Deprecated
    public static BleClient getBleClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new BleClient(context, new zzg(context, googleSignInAccount));
    }

    @NonNull
    public static ConfigClient getConfigClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new ConfigClient(context, new zzg(context, googleSignInAccount));
    }

    @NonNull
    public static GoalsClient getGoalsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new GoalsClient(context, new zzg(context, googleSignInAccount));
    }

    @NonNull
    public static HistoryClient getHistoryClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new HistoryClient(context, new zzg(context, googleSignInAccount));
    }

    @NonNull
    public static RecordingClient getRecordingClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new RecordingClient(context, new zzg(context, googleSignInAccount));
    }

    @NonNull
    public static SensorsClient getSensorsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SensorsClient(context, new zzg(context, googleSignInAccount));
    }

    @NonNull
    public static SessionsClient getSessionsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SessionsClient(context, new zzg(context, googleSignInAccount));
    }
}
