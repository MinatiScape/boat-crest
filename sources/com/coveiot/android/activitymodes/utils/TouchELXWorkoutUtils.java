package com.coveiot.android.activitymodes.utils;

import android.content.Context;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.SingletonHolder;
import com.coveiot.android.activitymodes.models.ActivitiesItem;
import com.coveiot.android.activitymodes.models.ActivityIcons;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class TouchELXWorkoutUtils implements WorkoutParameterInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f2878a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<TouchELXWorkoutUtils, Context> {

        /* loaded from: classes2.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, TouchELXWorkoutUtils> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, TouchELXWorkoutUtils.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final TouchELXWorkoutUtils invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new TouchELXWorkoutUtils(p0);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TouchELXWorkoutUtils(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2878a = context;
    }

    public static final void b(FirebaseRemoteConfig mFirebaseRemoteConfig, final Ref.ObjectRef touchActivitiesList, final Context context, Task task) {
        Intrinsics.checkNotNullParameter(mFirebaseRemoteConfig, "$mFirebaseRemoteConfig");
        Intrinsics.checkNotNullParameter(touchActivitiesList, "$touchActivitiesList");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            String string = mFirebaseRemoteConfig.getString(WorkoutConstants.TOUCH_ACTIVITIES_FIREBASE_CONFIG);
            Intrinsics.checkNotNullExpressionValue(string, "mFirebaseRemoteConfig.ge…TIVITIES_FIREBASE_CONFIG)");
            okHttpClient.newCall(builder.url(string).build()).enqueue(new Callback() { // from class: com.coveiot.android.activitymodes.utils.TouchELXWorkoutUtils$getActivitiesListFromRemoteConfig$1$1
                @Override // okhttp3.Callback
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(e, "e");
                }

                /* JADX WARN: Type inference failed for: r2v7, types: [T, java.util.ArrayList] */
                @Override // okhttp3.Callback
                public void onResponse(@NotNull Call call, @NotNull Response response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    ResponseBody body = response.body();
                    Intrinsics.checkNotNull(body);
                    Object fromJson = new Gson().fromJson(body.string(), (Class<Object>) ActivityIcons.class);
                    Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(jsonStri…ctivityIcons::class.java)");
                    Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef = touchActivitiesList;
                    List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                    Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem> }");
                    objectRef.element = (ArrayList) activities;
                    LogHelper.d("touchActivitiesList", touchActivitiesList.element.toString());
                    new PreferenceManager(context).saveTouchActivityIcons(touchActivitiesList.element);
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public void getActivitiesListFromRemoteConfig(@NotNull final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        final FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseRemoteConfig, "getInstance()");
        FirebaseRemoteConfigSettings build = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0L).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…s(0)\n            .build()");
        firebaseRemoteConfig.setConfigSettingsAsync(build);
        firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.activitymodes.utils.d
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                TouchELXWorkoutUtils.b(FirebaseRemoteConfig.this, objectRef, context, task);
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f2878a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4 */
    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    @NotNull
    public WorkoutImageBean getWorkoutImageBean(@NotNull ActivityMode activityMode, @Nullable String str, boolean z) {
        ActivitiesItem activitiesItem;
        String shareIconUrl;
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        ArrayList<ActivitiesItem> touchActivityIcons = new PreferenceManager(this.f2878a).getTouchActivityIcons();
        Integer valueOf = Integer.valueOf(R.drawable.activity_walking_icon);
        if (!(touchActivityIcons == null || touchActivityIcons.isEmpty()) && (activitiesItem = WorkoutUtils.INSTANCE.getActivitiesItem(activityMode, str, touchActivityIcons)) != null) {
            if (z) {
                shareIconUrl = activitiesItem.getShareIconUrl();
                Intrinsics.checkNotNull(shareIconUrl);
            } else {
                shareIconUrl = activitiesItem.getShareIconUrl();
                Intrinsics.checkNotNull(shareIconUrl);
            }
            valueOf = shareIconUrl;
        }
        return new WorkoutImageBean(valueOf);
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isAvgSPeedApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return Intrinsics.areEqual(activityMode, CoveApiConstants.RUN) || Intrinsics.areEqual(activityMode, "WALK") || (Intrinsics.areEqual(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.wave_neo)) && Intrinsics.areEqual(activityMode, CoveApiConstants.HIKING));
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isAvgStrideLengthApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return !Intrinsics.areEqual(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.wave_neo)) && (Intrinsics.areEqual(activityMode, CoveApiConstants.RUN) || Intrinsics.areEqual(activityMode, "WALK"));
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isAvgSwolfApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isDistanceApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable Integer num, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (num != null) {
            if (!Intrinsics.areEqual(activityMode, CoveApiConstants.RUN) && !Intrinsics.areEqual(activityMode, "WALK")) {
                String deviceType = SessionManager.getInstance(context).getDeviceType();
                int i = R.string.wave_neo;
                if ((Intrinsics.areEqual(deviceType, context.getString(i)) || !Intrinsics.areEqual(activityMode, CoveApiConstants.CYCLE) || str == null || !Intrinsics.areEqual(str, "OUTDOOR")) && !Intrinsics.areEqual(activityMode, "MOUNTAINEERING") && (!Intrinsics.areEqual(SessionManager.getInstance(context).getDeviceType(), context.getString(i)) || !Intrinsics.areEqual(activityMode, CoveApiConstants.HIKING))) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isFastestPaceApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isLapsApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isLowestPaceApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isMaxSPMApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isPaceApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
            return false;
        }
        return Intrinsics.areEqual(activityMode, CoveApiConstants.RUN) || Intrinsics.areEqual(activityMode, "WALK");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isSPMApplicable(@NotNull Context context, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
            int hashCode = activityMode.hashCode();
            if (hashCode != 81515) {
                if (hashCode != 2656713) {
                    if (hashCode == 2131022872 && activityMode.equals(CoveApiConstants.HIKING)) {
                        return true;
                    }
                } else if (activityMode.equals("WALK")) {
                    return true;
                }
            } else if (activityMode.equals(CoveApiConstants.RUN)) {
                return true;
            }
        } else {
            int hashCode2 = activityMode.hashCode();
            if (hashCode2 != 81515) {
                if (hashCode2 != 2656713) {
                    if (hashCode2 == 1440891949 && activityMode.equals("ELLIPTICAL")) {
                        return true;
                    }
                } else if (activityMode.equals("WALK")) {
                    return true;
                }
            } else if (activityMode.equals(CoveApiConstants.RUN)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isStepsApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable Integer num, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num2, @Nullable Integer num3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (num != null) {
            if (BleApiManager.getInstance(context).getDeviceType() != DeviceType.TOUCH_WAVE_NEO) {
                switch (activityMode.hashCode()) {
                    case 81515:
                        if (activityMode.equals(CoveApiConstants.RUN)) {
                            return true;
                        }
                        break;
                    case 2656713:
                        if (activityMode.equals("WALK")) {
                            return true;
                        }
                        break;
                    case 1440891949:
                        if (activityMode.equals("ELLIPTICAL")) {
                            return true;
                        }
                        break;
                    case 2052409245:
                        if (activityMode.equals("MOUNTAINEERING")) {
                            return true;
                        }
                        break;
                }
            } else {
                int hashCode = activityMode.hashCode();
                if (hashCode != 81515) {
                    if (hashCode != 2656713) {
                        if (hashCode == 2131022872 && activityMode.equals(CoveApiConstants.HIKING)) {
                            return true;
                        }
                    } else if (activityMode.equals("WALK")) {
                        return true;
                    }
                } else if (activityMode.equals(CoveApiConstants.RUN)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isStrokeFrequencyApplicable(@NotNull Context context, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
            return false;
        }
        return Intrinsics.areEqual(activityMode, "ROWING") || Intrinsics.areEqual(activityMode, "SKIPPING");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isStrokeOrCountApplicable(@NotNull Context context, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
            return false;
        }
        return Intrinsics.areEqual(activityMode, "ROWING") || Intrinsics.areEqual(activityMode, "SKIPPING");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isSwimStrokeApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isTotalStrokeApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f2878a = context;
    }
}
