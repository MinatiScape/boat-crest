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
import com.coveiot.android.devicemodels.DeviceUtils;
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
public class SMAWorkoutUtils implements WorkoutParameterInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f2876a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SMAWorkoutUtils, Context> {

        /* loaded from: classes2.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, SMAWorkoutUtils> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, SMAWorkoutUtils.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final SMAWorkoutUtils invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SMAWorkoutUtils(p0);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SMAWorkoutUtils(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2876a = context;
    }

    public static final void b(FirebaseRemoteConfig mFirebaseRemoteConfig, final Ref.ObjectRef smaActivitiesList, final Context context, Task task) {
        Intrinsics.checkNotNullParameter(mFirebaseRemoteConfig, "$mFirebaseRemoteConfig");
        Intrinsics.checkNotNullParameter(smaActivitiesList, "$smaActivitiesList");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                String string = mFirebaseRemoteConfig.getString(WorkoutConstants.SMA_ACTIVITIES_FIREBASE_CONFIG);
                Intrinsics.checkNotNullExpressionValue(string, "mFirebaseRemoteConfig.ge…TIVITIES_FIREBASE_CONFIG)");
                okHttpClient.newCall(builder.url(string).build()).enqueue(new Callback() { // from class: com.coveiot.android.activitymodes.utils.SMAWorkoutUtils$getActivitiesListFromRemoteConfig$1$1
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
                        Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef = smaActivitiesList;
                        List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                        Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem> }");
                        objectRef.element = (ArrayList) activities;
                        LogHelper.d("smaActivitiesList", smaActivitiesList.element.toString());
                        new PreferenceManager(context).saveSMAActivityIcons(smaActivitiesList.element);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.activitymodes.utils.b
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                SMAWorkoutUtils.b(FirebaseRemoteConfig.this, objectRef, context, task);
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f2876a;
    }

    @NotNull
    public final String getSPMName(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (BleApiManager.getInstance(context).getDeviceType() != DeviceType.SMA_WAVE_GENESIS_PRO && BleApiManager.getInstance(context).getDeviceType() != DeviceType.SMA_WAVE_GLORY_PRO && BleApiManager.getInstance(context).getDeviceType() != DeviceType.SMA_WAVE_ELEVATE_PRO && BleApiManager.getInstance(context).getDeviceType() != DeviceType.SMA_ULTIMA_VOGUE) {
            String string = context.getString(R.string.spm);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spm)");
            return string;
        }
        String string2 = context.getString(R.string.avg_spm);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.avg_spm)");
        return string2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4 */
    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    @NotNull
    public WorkoutImageBean getWorkoutImageBean(@NotNull ActivityMode activityMode, @Nullable String str, boolean z) {
        ActivitiesItem activitiesItem;
        String shareIconUrl;
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        ArrayList<ActivitiesItem> sMAActivityIcons = new PreferenceManager(this.f2876a).getSMAActivityIcons();
        Integer valueOf = Integer.valueOf(R.drawable.activity_walking_icon);
        if (!(sMAActivityIcons == null || sMAActivityIcons.isEmpty()) && (activitiesItem = WorkoutUtils.INSTANCE.getActivitiesItem(activityMode, str, sMAActivityIcons)) != null) {
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
        return Intrinsics.areEqual(activityMode, "TREKKING") || (Intrinsics.areEqual(activityMode, CoveApiConstants.CYCLE) && str != null && Intrinsics.areEqual(str, "OUTDOOR")) || Intrinsics.areEqual(activityMode, "HORSE_RIDING") || Intrinsics.areEqual(activityMode, "PARACHUTE") || Intrinsics.areEqual(activityMode, "RACING") || Intrinsics.areEqual(activityMode, "OFF_ROAD_BIKE") || Intrinsics.areEqual(activityMode, "MOTOCROSS") || Intrinsics.areEqual(activityMode, "KAYAKING") || Intrinsics.areEqual(activityMode, "ROWING");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isAvgStrideLengthApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
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
            if (!DeviceUtils.Companion.isSmaJieieDevice(context)) {
                switch (activityMode.hashCode()) {
                    case -1586487546:
                        if (activityMode.equals("TREADMILL")) {
                            return true;
                        }
                        break;
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
                    case 1698056461:
                        if (activityMode.equals("CLIMBING")) {
                            return true;
                        }
                        break;
                    case 2131022872:
                        if (activityMode.equals(CoveApiConstants.HIKING)) {
                            return true;
                        }
                        break;
                }
            } else if (Intrinsics.areEqual(activityMode, CoveApiConstants.RUN) || Intrinsics.areEqual(activityMode, "WALK") || Intrinsics.areEqual(activityMode, CoveApiConstants.HIKING) || Intrinsics.areEqual(activityMode, "CLIMBING") || Intrinsics.areEqual(activityMode, "TREADMILL")) {
                return true;
            } else {
                if ((Intrinsics.areEqual(activityMode, CoveApiConstants.CYCLE) && str != null && Intrinsics.areEqual(str, "OUTDOOR")) || Intrinsics.areEqual(activityMode, "FOOTBALL") || Intrinsics.areEqual(activityMode, "AEROBICS_GYMS") || Intrinsics.areEqual(activityMode, "TREKKING") || Intrinsics.areEqual(activityMode, "FRISBEE") || Intrinsics.areEqual(activityMode, "HORSE_RIDING") || Intrinsics.areEqual(activityMode, "PARACHUTE") || Intrinsics.areEqual(activityMode, "MARATHON") || Intrinsics.areEqual(activityMode, "RACING") || Intrinsics.areEqual(activityMode, "OFF_ROAD_BIKE") || Intrinsics.areEqual(activityMode, "MOTOCROSS") || Intrinsics.areEqual(activityMode, "KAYAKING") || Intrinsics.areEqual(activityMode, "ROWING") || Intrinsics.areEqual(activityMode, "CROQUET") || Intrinsics.areEqual(activityMode, "RUGBY")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isFastestPaceApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_GENESIS_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_GLORY_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_ELEVATE_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_ULTIMA_VOGUE) {
            return isPaceApplicable(context, activityMode, screenType, num, num2, str);
        }
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
        if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_GENESIS_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_GLORY_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_ELEVATE_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_ULTIMA_VOGUE) {
            return isPaceApplicable(context, activityMode, screenType, num, num2, str);
        }
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isMaxSPMApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_GENESIS_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_GLORY_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_ELEVATE_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_ULTIMA_VOGUE) {
            return isSPMApplicable(context, activityMode);
        }
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isPaceApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return Intrinsics.areEqual(activityMode, CoveApiConstants.RUN) || Intrinsics.areEqual(activityMode, "WALK") || Intrinsics.areEqual(activityMode, "CLIMBING") || Intrinsics.areEqual(activityMode, "TREADMILL") || (Intrinsics.areEqual(activityMode, CoveApiConstants.CYCLE) && str == null && Intrinsics.areEqual(str, "OUTDOOR")) || Intrinsics.areEqual(activityMode, "AEROBICS_GYMS") || Intrinsics.areEqual(activityMode, "TREKKING") || Intrinsics.areEqual(activityMode, "FRISBEE") || Intrinsics.areEqual(activityMode, "HORSE_RIDING") || Intrinsics.areEqual(activityMode, "PARACHUTE") || Intrinsics.areEqual(activityMode, "MARATHON") || Intrinsics.areEqual(activityMode, "RACING") || Intrinsics.areEqual(activityMode, "HORSE_RACING") || Intrinsics.areEqual(activityMode, "OFF_ROAD_BIKE") || Intrinsics.areEqual(activityMode, "MOTOCROSS") || Intrinsics.areEqual(activityMode, "KAYAKING") || Intrinsics.areEqual(activityMode, "ROWING") || Intrinsics.areEqual(activityMode, "CROQUET") || Intrinsics.areEqual(activityMode, "RUGBY") || Intrinsics.areEqual(activityMode, "FOOTBALL");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isSPMApplicable(@NotNull Context context, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isPrimiaDevice(context)) {
            if (!companion.isSmaJieieDevice(context)) {
                switch (activityMode.hashCode()) {
                    case -1586487546:
                        if (activityMode.equals("TREADMILL")) {
                            return true;
                        }
                        break;
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
                    case 1698056461:
                        if (activityMode.equals("CLIMBING")) {
                            return true;
                        }
                        break;
                }
            } else {
                switch (activityMode.hashCode()) {
                    case -2051813763:
                        if (activityMode.equals("WORKOUT")) {
                            return true;
                        }
                        break;
                    case -1965056527:
                        if (activityMode.equals("SQUARE_DANCE")) {
                            return true;
                        }
                        break;
                    case -1865330601:
                        if (activityMode.equals("STREET_DANCE")) {
                            return true;
                        }
                        break;
                    case -1841345217:
                        if (activityMode.equals("SQUASH")) {
                            return true;
                        }
                        break;
                    case -1827940748:
                        if (activityMode.equals("TAE_BO")) {
                            return true;
                        }
                        break;
                    case -1823994661:
                        if (activityMode.equals("TENNIS")) {
                            return true;
                        }
                        break;
                    case -1586487546:
                        if (activityMode.equals("TREADMILL")) {
                            return true;
                        }
                        break;
                    case -1366065295:
                        if (activityMode.equals("SKIPPING")) {
                            return true;
                        }
                        break;
                    case -1350986735:
                        if (activityMode.equals("LONG_JUMP")) {
                            return true;
                        }
                        break;
                    case -1175571791:
                        if (activityMode.equals("STEPPER")) {
                            return true;
                        }
                        break;
                    case -938464176:
                        if (activityMode.equals("BASEBALL")) {
                            return true;
                        }
                        break;
                    case -845589618:
                        if (activityMode.equals("TAEKWONDO")) {
                            return true;
                        }
                        break;
                    case -675235860:
                        if (activityMode.equals("VOLLEYBALL")) {
                            return true;
                        }
                        break;
                    case -143182912:
                        if (activityMode.equals("JAI_ALAI")) {
                            return true;
                        }
                        break;
                    case -61197506:
                        if (activityMode.equals("TENNIS_DOUBLES")) {
                            return true;
                        }
                        break;
                    case 81515:
                        if (activityMode.equals(CoveApiConstants.RUN)) {
                            return true;
                        }
                        break;
                    case 2193506:
                        if (activityMode.equals("GOLF")) {
                            return true;
                        }
                        break;
                    case 2656713:
                        if (activityMode.equals("WALK")) {
                            return true;
                        }
                        break;
                    case 64812947:
                        if (activityMode.equals("DANCE")) {
                            return true;
                        }
                        break;
                    case 64817332:
                        if (activityMode.equals("DARTS")) {
                            return true;
                        }
                        break;
                    case 65056090:
                        if (activityMode.equals("DISCO")) {
                            return true;
                        }
                        break;
                    case 85725233:
                        if (activityMode.equals("ZUMBA")) {
                            return true;
                        }
                        break;
                    case 112121867:
                        if (activityMode.equals("JAZZ_DANCE")) {
                            return true;
                        }
                        break;
                    case 147985428:
                        if (activityMode.equals("PILATES")) {
                            return true;
                        }
                        break;
                    case 481127594:
                        if (activityMode.equals("GYMNASTICS")) {
                            return true;
                        }
                        break;
                    case 657827443:
                        if (activityMode.equals("MODERN_DANCE")) {
                            return true;
                        }
                        break;
                    case 780621286:
                        if (activityMode.equals("BADMINTON")) {
                            return true;
                        }
                        break;
                    case 784393966:
                        if (activityMode.equals("HANDBALL")) {
                            return true;
                        }
                        break;
                    case 790086816:
                        if (activityMode.equals("BOWLING")) {
                            return true;
                        }
                        break;
                    case 881635287:
                        if (activityMode.equals("TAP_DANCE")) {
                            return true;
                        }
                        break;
                    case 1020800150:
                        if (activityMode.equals("MARATHON")) {
                            return true;
                        }
                        break;
                    case 1177295725:
                        if (activityMode.equals("FOOTBALL")) {
                            return true;
                        }
                        break;
                    case 1212242117:
                        if (activityMode.equals("BASKETBALL")) {
                            return true;
                        }
                        break;
                    case 1440891949:
                        if (activityMode.equals("ELLIPTICAL")) {
                            return true;
                        }
                        break;
                    case 1698056461:
                        if (activityMode.equals("CLIMBING")) {
                            return true;
                        }
                        break;
                    case 1750282193:
                        if (activityMode.equals("CRICKET")) {
                            return true;
                        }
                        break;
                    case 1959274292:
                        if (activityMode.equals("CLIMB_STAIRS")) {
                            return true;
                        }
                        break;
                    case 2101331241:
                        if (activityMode.equals("SOFTBALL")) {
                            return true;
                        }
                        break;
                    case 2122974379:
                        if (activityMode.equals("HIGH_JUMP")) {
                            return true;
                        }
                        break;
                    case 2136327331:
                        if (activityMode.equals("HOCKEY")) {
                            return true;
                        }
                        break;
                }
            }
        } else {
            switch (activityMode.hashCode()) {
                case -1586487546:
                    if (activityMode.equals("TREADMILL")) {
                        return true;
                    }
                    break;
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
                case 780621286:
                    if (activityMode.equals("BADMINTON")) {
                        return true;
                    }
                    break;
                case 1177295725:
                    if (activityMode.equals("FOOTBALL")) {
                        return true;
                    }
                    break;
                case 1212242117:
                    if (activityMode.equals("BASKETBALL")) {
                        return true;
                    }
                    break;
                case 1698056461:
                    if (activityMode.equals("CLIMBING")) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isStepsApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable Integer num, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num2, @Nullable Integer num3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (num != null) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isPrimiaDevice(context)) {
                if (!companion.isSmaJieieDevice(context)) {
                    switch (activityMode.hashCode()) {
                        case -1586487546:
                            if (activityMode.equals("TREADMILL")) {
                                return true;
                            }
                            break;
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
                        case 1698056461:
                            if (activityMode.equals("CLIMBING")) {
                                return true;
                            }
                            break;
                    }
                } else {
                    switch (activityMode.hashCode()) {
                        case -2119946949:
                            if (activityMode.equals("TREKKING")) {
                                return true;
                            }
                            break;
                        case -1965056527:
                            if (activityMode.equals("SQUARE_DANCE")) {
                                return true;
                            }
                            break;
                        case -1865330601:
                            if (activityMode.equals("STREET_DANCE")) {
                                return true;
                            }
                            break;
                        case -1841345217:
                            if (activityMode.equals("SQUASH")) {
                                return true;
                            }
                            break;
                        case -1827940748:
                            if (activityMode.equals("TAE_BO")) {
                                return true;
                            }
                            break;
                        case -1823994661:
                            if (activityMode.equals("TENNIS")) {
                                return true;
                            }
                            break;
                        case -1586487546:
                            if (activityMode.equals("TREADMILL")) {
                                return true;
                            }
                            break;
                        case -1366065295:
                            if (activityMode.equals("SKIPPING")) {
                                return true;
                            }
                            break;
                        case -1350986735:
                            if (activityMode.equals("LONG_JUMP")) {
                                return true;
                            }
                            break;
                        case -1175571791:
                            if (activityMode.equals("STEPPER")) {
                                return true;
                            }
                            break;
                        case -938464176:
                            if (activityMode.equals("BASEBALL")) {
                                return true;
                            }
                            break;
                        case -845589618:
                            if (activityMode.equals("TAEKWONDO")) {
                                return true;
                            }
                            break;
                        case -675235860:
                            if (activityMode.equals("VOLLEYBALL")) {
                                return true;
                            }
                            break;
                        case -143182912:
                            if (activityMode.equals("JAI_ALAI")) {
                                return true;
                            }
                            break;
                        case -97787361:
                            if (activityMode.equals("AEROBICS_GYMS")) {
                                return true;
                            }
                            break;
                        case -61197506:
                            if (activityMode.equals("TENNIS_DOUBLES")) {
                                return true;
                            }
                            break;
                        case 81515:
                            if (activityMode.equals(CoveApiConstants.RUN)) {
                                return true;
                            }
                            break;
                        case 2193506:
                            if (activityMode.equals("GOLF")) {
                                return true;
                            }
                            break;
                        case 2656713:
                            if (activityMode.equals("WALK")) {
                                return true;
                            }
                            break;
                        case 64812947:
                            if (activityMode.equals("DANCE")) {
                                return true;
                            }
                            break;
                        case 64817332:
                            if (activityMode.equals("DARTS")) {
                                return true;
                            }
                            break;
                        case 65056090:
                            if (activityMode.equals("DISCO")) {
                                return true;
                            }
                            break;
                        case 78331323:
                            if (activityMode.equals("RUGBY")) {
                                return true;
                            }
                            break;
                        case 85725233:
                            if (activityMode.equals("ZUMBA")) {
                                return true;
                            }
                            break;
                        case 112121867:
                            if (activityMode.equals("JAZZ_DANCE")) {
                                return true;
                            }
                            break;
                        case 147985428:
                            if (activityMode.equals("PILATES")) {
                                return true;
                            }
                            break;
                        case 481127594:
                            if (activityMode.equals("GYMNASTICS")) {
                                return true;
                            }
                            break;
                        case 657827443:
                            if (activityMode.equals("MODERN_DANCE")) {
                                return true;
                            }
                            break;
                        case 780621286:
                            if (activityMode.equals("BADMINTON")) {
                                return true;
                            }
                            break;
                        case 784393966:
                            if (activityMode.equals("HANDBALL")) {
                                return true;
                            }
                            break;
                        case 790086816:
                            if (activityMode.equals("BOWLING")) {
                                return true;
                            }
                            break;
                        case 881635287:
                            if (activityMode.equals("TAP_DANCE")) {
                                return true;
                            }
                            break;
                        case 1020800150:
                            if (activityMode.equals("MARATHON")) {
                                return true;
                            }
                            break;
                        case 1177295725:
                            if (activityMode.equals("FOOTBALL")) {
                                return true;
                            }
                            break;
                        case 1212242117:
                            if (activityMode.equals("BASKETBALL")) {
                                return true;
                            }
                            break;
                        case 1440891949:
                            if (activityMode.equals("ELLIPTICAL")) {
                                return true;
                            }
                            break;
                        case 1698056461:
                            if (activityMode.equals("CLIMBING")) {
                                return true;
                            }
                            break;
                        case 1750282193:
                            if (activityMode.equals("CRICKET")) {
                                return true;
                            }
                            break;
                        case 1756250003:
                            if (activityMode.equals("CROQUET")) {
                                return true;
                            }
                            break;
                        case 1959274292:
                            if (activityMode.equals("CLIMB_STAIRS")) {
                                return true;
                            }
                            break;
                        case 2101331241:
                            if (activityMode.equals("SOFTBALL")) {
                                return true;
                            }
                            break;
                        case 2122974379:
                            if (activityMode.equals("HIGH_JUMP")) {
                                return true;
                            }
                            break;
                        case 2136327331:
                            if (activityMode.equals("HOCKEY")) {
                                return true;
                            }
                            break;
                    }
                }
            } else {
                switch (activityMode.hashCode()) {
                    case -1586487546:
                        if (activityMode.equals("TREADMILL")) {
                            return true;
                        }
                        break;
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
                    case 2729584:
                        if (activityMode.equals("YOGA")) {
                            return true;
                        }
                        break;
                    case 780621286:
                        if (activityMode.equals("BADMINTON")) {
                            return true;
                        }
                        break;
                    case 1177295725:
                        if (activityMode.equals("FOOTBALL")) {
                            return true;
                        }
                        break;
                    case 1212242117:
                        if (activityMode.equals("BASKETBALL")) {
                            return true;
                        }
                        break;
                    case 1698056461:
                        if (activityMode.equals("CLIMBING")) {
                            return true;
                        }
                        break;
                }
            }
        }
        return false;
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
        this.f2876a = context;
    }
}
