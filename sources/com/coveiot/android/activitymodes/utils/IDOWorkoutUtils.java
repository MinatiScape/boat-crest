package com.coveiot.android.activitymodes.utils;

import android.content.Context;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.SingletonHolder;
import com.coveiot.android.activitymodes.models.ActivitiesItem;
import com.coveiot.android.activitymodes.models.ActivityIcons;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import com.coveiot.coveaccess.constants.CoveApiConstants;
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
public class IDOWorkoutUtils implements WorkoutParameterInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f2870a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<IDOWorkoutUtils, Context> {

        /* loaded from: classes2.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, IDOWorkoutUtils> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, IDOWorkoutUtils.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final IDOWorkoutUtils invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new IDOWorkoutUtils(p0);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IDOWorkoutUtils(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2870a = context;
    }

    public static final void b(FirebaseRemoteConfig mFirebaseRemoteConfig, final Ref.ObjectRef idoActivitiesList, final Context context, Task task) {
        Intrinsics.checkNotNullParameter(mFirebaseRemoteConfig, "$mFirebaseRemoteConfig");
        Intrinsics.checkNotNullParameter(idoActivitiesList, "$idoActivitiesList");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            String string = mFirebaseRemoteConfig.getString(WorkoutConstants.IDO_ACTIVITIES_FIREBASE_CONFIG);
            Intrinsics.checkNotNullExpressionValue(string, "mFirebaseRemoteConfig.ge…TIVITIES_FIREBASE_CONFIG)");
            okHttpClient.newCall(builder.url(string).build()).enqueue(new Callback() { // from class: com.coveiot.android.activitymodes.utils.IDOWorkoutUtils$getActivitiesListFromRemoteConfig$1$1
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
                    Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef = idoActivitiesList;
                    List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                    Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem> }");
                    objectRef.element = (ArrayList) activities;
                    LogHelper.d("idoActivitiesList", idoActivitiesList.element.toString());
                    new PreferenceManager(context).saveIDOActivityIcons(idoActivitiesList.element);
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
        firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.activitymodes.utils.a
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                IDOWorkoutUtils.b(FirebaseRemoteConfig.this, objectRef, context, task);
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f2870a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4 */
    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    @NotNull
    public WorkoutImageBean getWorkoutImageBean(@NotNull ActivityMode activityMode, @Nullable String str, boolean z) {
        ActivitiesItem activitiesItem;
        String shareIconUrl;
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        ArrayList<ActivitiesItem> iDOActivityIcons = new PreferenceManager(this.f2870a).getIDOActivityIcons();
        Integer valueOf = Integer.valueOf(R.drawable.activity_walking_icon);
        if (!(iDOActivityIcons == null || iDOActivityIcons.isEmpty()) && (activitiesItem = WorkoutUtils.INSTANCE.getActivitiesItem(activityMode, str, iDOActivityIcons)) != null) {
            if (z) {
                shareIconUrl = activitiesItem.getIconUrl();
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
        return Intrinsics.areEqual(activityMode, CoveApiConstants.RUN) || Intrinsics.areEqual(activityMode, "WALK") || Intrinsics.areEqual(activityMode, CoveApiConstants.HIKING) || (Intrinsics.areEqual(activityMode, CoveApiConstants.CYCLE) && str != null && Intrinsics.areEqual(str, "OUTDOOR"));
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isAvgStrideLengthApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return (Intrinsics.areEqual(activityMode, CoveApiConstants.RUN) && str != null && Intrinsics.areEqual(str, "OUTDOOR")) || (Intrinsics.areEqual(activityMode, "WALK") && str != null && Intrinsics.areEqual(str, "OUTDOOR")) || Intrinsics.areEqual(activityMode, CoveApiConstants.HIKING);
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isAvgSwolfApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return Intrinsics.areEqual(activityMode, CoveApiConstants.SWIM) && str != null && Intrinsics.areEqual(str, "INDOOR");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isDistanceApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable Integer num, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return num != null && (Intrinsics.areEqual(activityMode, CoveApiConstants.RUN) || Intrinsics.areEqual(activityMode, "WALK") || Intrinsics.areEqual(activityMode, CoveApiConstants.HIKING) || ((Intrinsics.areEqual(activityMode, CoveApiConstants.SWIM) && str != null && Intrinsics.areEqual(str, "INDOOR")) || (Intrinsics.areEqual(activityMode, CoveApiConstants.CYCLE) && str != null && Intrinsics.areEqual(str, "OUTDOOR"))));
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isFastestPaceApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        int hashCode = activityMode.hashCode();
        return hashCode == 81515 ? activityMode.equals(CoveApiConstants.RUN) : hashCode == 2656713 ? activityMode.equals("WALK") : hashCode == 2131022872 && activityMode.equals(CoveApiConstants.HIKING);
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isLapsApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return Intrinsics.areEqual(activityMode, CoveApiConstants.SWIM) && str != null && Intrinsics.areEqual(str, "INDOOR");
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
        return (Intrinsics.areEqual(activityMode, CoveApiConstants.RUN) && str != null && Intrinsics.areEqual(str, "INDOOR")) || (Intrinsics.areEqual(activityMode, "WALK") && str != null && Intrinsics.areEqual(str, "INDOOR")) || Intrinsics.areEqual(activityMode, "ELLIPTICAL");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isPaceApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        int hashCode = activityMode.hashCode();
        return hashCode == 81515 ? activityMode.equals(CoveApiConstants.RUN) : hashCode == 2656713 ? activityMode.equals("WALK") : hashCode == 2131022872 && activityMode.equals(CoveApiConstants.HIKING);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0038 A[ORIG_RETURN, RETURN] */
    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isSPMApplicable(@org.jetbrains.annotations.NotNull android.content.Context r2, @org.jetbrains.annotations.NotNull java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r2 = "activityMode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            int r2 = r3.hashCode()
            switch(r2) {
                case 81515: goto L2d;
                case 2656713: goto L24;
                case 1440891949: goto L1b;
                case 2131022872: goto L12;
                default: goto L11;
            }
        L11:
            goto L38
        L12:
            java.lang.String r2 = "HIKING"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L36
            goto L38
        L1b:
            java.lang.String r2 = "ELLIPTICAL"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L38
            goto L36
        L24:
            java.lang.String r2 = "WALK"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L36
            goto L38
        L2d:
            java.lang.String r2 = "RUN"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L36
            goto L38
        L36:
            r2 = 1
            goto L39
        L38:
            r2 = 0
        L39:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.utils.IDOWorkoutUtils.isSPMApplicable(android.content.Context, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0041 A[RETURN, SYNTHETIC] */
    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isStepsApplicable(@org.jetbrains.annotations.NotNull android.content.Context r1, @org.jetbrains.annotations.NotNull java.lang.String r2, @org.jetbrains.annotations.Nullable java.lang.Integer r3, @org.jetbrains.annotations.Nullable com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider.ScreenType r4, @org.jetbrains.annotations.Nullable java.lang.Integer r5, @org.jetbrains.annotations.Nullable java.lang.Integer r6) {
        /*
            r0 = this;
            java.lang.String r4 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r1 = "activityMode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            if (r3 == 0) goto L43
            int r1 = r2.hashCode()
            switch(r1) {
                case 81515: goto L38;
                case 2656713: goto L2f;
                case 1440891949: goto L26;
                case 1750282193: goto L1d;
                case 2131022872: goto L14;
                default: goto L13;
            }
        L13:
            goto L43
        L14:
            java.lang.String r1 = "HIKING"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L41
            goto L43
        L1d:
            java.lang.String r1 = "CRICKET"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L41
            goto L43
        L26:
            java.lang.String r1 = "ELLIPTICAL"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L43
            goto L41
        L2f:
            java.lang.String r1 = "WALK"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L41
            goto L43
        L38:
            java.lang.String r1 = "RUN"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L41
            goto L43
        L41:
            r1 = 1
            goto L44
        L43:
            r1 = 0
        L44:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.utils.IDOWorkoutUtils.isStepsApplicable(android.content.Context, java.lang.String, java.lang.Integer, com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider$ScreenType, java.lang.Integer, java.lang.Integer):boolean");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isStrokeFrequencyApplicable(@NotNull Context context, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isStrokeOrCountApplicable(@NotNull Context context, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isSwimStrokeApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return Intrinsics.areEqual(activityMode, CoveApiConstants.SWIM) && str != null && Intrinsics.areEqual(str, "INDOOR");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isTotalStrokeApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return Intrinsics.areEqual(activityMode, CoveApiConstants.SWIM) && str != null && Intrinsics.areEqual(str, "INDOOR");
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f2870a = context;
    }
}
