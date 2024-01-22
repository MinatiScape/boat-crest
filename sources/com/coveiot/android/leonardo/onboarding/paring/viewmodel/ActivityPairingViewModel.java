package com.coveiot.android.leonardo.onboarding.paring.viewmodel;

import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ActivityTypes;
import com.coveiot.android.bleabstract.models.AutoActivityDetectionModel;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.PPGTypes;
import com.coveiot.android.bleabstract.request.AutoActivityRecognitionListRequest;
import com.coveiot.android.bleabstract.request.AutoActivityRecognitionSettingRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.ReminderType;
import com.coveiot.android.bleabstract.request.SetAmbientSoundLevelControlRequest;
import com.coveiot.android.bleabstract.request.SetAutoActivityDetectionSettingsRequest;
import com.coveiot.android.bleabstract.request.SetAutomaticPPGConfigRequest;
import com.coveiot.android.bleabstract.request.SetDNDModeRequest;
import com.coveiot.android.bleabstract.request.SetDeviceParametersRequest;
import com.coveiot.android.bleabstract.request.SetDistanceUnitRequest;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetNavigationFavouriteLocationRequest;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.request.SetScheduleRequest;
import com.coveiot.android.bleabstract.request.SetShortcutMenuListRequest;
import com.coveiot.android.bleabstract.request.SetTemperatureUnitRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmListRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.bleabstract.request.SetWearingHandRequest;
import com.coveiot.android.bleabstract.request.SetWomenWellnessSettingsRequest;
import com.coveiot.android.bleabstract.request.SetWorldClockListRequest;
import com.coveiot.android.bleabstract.request.SetWorldClockRequest;
import com.coveiot.android.bleabstract.request.Spo2TimeIntervalRequest;
import com.coveiot.android.bleabstract.request.SportNotificationControlRequest;
import com.coveiot.android.bleabstract.request.StressTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.TemperatureTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.customreminders.listeners.ResultListener;
import com.coveiot.android.customreminders.utils.ReminderHelper;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.femalewellness.db.FemaleWellnessRepository;
import com.coveiot.android.femalewellness.db.entities.EntityFemaleWellnessSymptoms;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.leonardo.more.EventReminderHelper;
import com.coveiot.android.leonardo.more.models.AutoRecognitonModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.smartalert.util.SmartAlertUtils;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsType;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel;
import com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel;
import com.coveiot.android.sportsnotificationsdk.network.SportType;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiClient;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSports;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.activitysession.GetActivitySessionHeaderResponse;
import com.coveiot.coveaccess.activitysession.PostActivitySessionDataRequest;
import com.coveiot.coveaccess.activitysession.PostActivitySessionHeaderResponse;
import com.coveiot.coveaccess.activitysession.Target;
import com.coveiot.coveaccess.activitysession.TraqConfigApi;
import com.coveiot.coveaccess.activitysession.fitnessActivitySessions;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.model.MensurationSymptomRecordResponse;
import com.coveiot.coveaccess.fitness.model.MensurationSymptomsRecordBeans;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordApiManager;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.AutoRecognize;
import com.coveiot.coveaccess.model.server.AutoRecognizeActivity;
import com.coveiot.coveaccess.model.server.AutoRecognizeSlot;
import com.coveiot.coveaccess.model.server.AutoStress;
import com.coveiot.coveaccess.model.server.DualTimeBean;
import com.coveiot.coveaccess.model.server.MensturationBean;
import com.coveiot.coveaccess.navigation.GetFavouritePlacesRes;
import com.coveiot.coveaccess.navigation.NavigationApi;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.sports.Cricket;
import com.coveiot.coveaccess.sports.MatchUpdateRequest;
import com.coveiot.coveaccess.sports.Soccer;
import com.coveiot.coveaccess.sports.SportsPrefResData;
import com.coveiot.coveaccess.sports.SportsUserPrefResponse;
import com.coveiot.coveaccess.sports.UpdateConfigsBean;
import com.coveiot.coveaccess.userappsetting.AlarmType;
import com.coveiot.coveaccess.userappsetting.EventsBean;
import com.coveiot.coveaccess.userappsetting.GetAllUserAppSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.GetAllUserDeviceSettingRes;
import com.coveiot.coveaccess.userdevicesetting.model.RespiratoryRateSettings;
import com.coveiot.coveaccess.userdevicesetting.model.SedentaryAlertUserDeviceSettingsBean;
import com.coveiot.coveaccess.userdevicesetting.model.SmartAlertSettings;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AutoActivityDetectionData;
import com.coveiot.covepreferences.data.AutoRecognitionData;
import com.coveiot.covepreferences.data.AutoSPO2SettingsData;
import com.coveiot.covepreferences.data.AutoStressSettingsData;
import com.coveiot.covepreferences.data.DoNotDisturbData;
import com.coveiot.covepreferences.data.DrinkWaterReminderData;
import com.coveiot.covepreferences.data.FeatureMapping;
import com.coveiot.covepreferences.data.RespiratoryRateRemoteConfiguration;
import com.coveiot.covepreferences.data.ScheduleData;
import com.coveiot.covepreferences.data.SedentaryReminderData;
import com.coveiot.covepreferences.data.StressConfiguration;
import com.coveiot.covepreferences.data.VibrationAlarmData;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.coveiot.covepreferences.data.WorldClockPrefData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.sensai.datasource.db.read.SensAIBeamDBRead;
import com.coveiot.repository.sensai.datasource.db.write.SensAIBeamDBWrite;
import com.coveiot.sdk.ble.api.model.AlarmInfo;
import com.coveiot.sdk.ble.api.model.FavouriteLocationData;
import com.coveiot.sdk.ble.api.model.ScheduleInfo;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.UtilConstants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityPairingViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5246a;
    public int b;
    public final String c;
    public int d;
    public final int e;
    public int f;
    public int g;
    public int h;
    public int i;
    @Nullable
    public String j;
    public ViewModelListener viewmodelListener;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<ArrayList<WorldClockPrefData>, Unit> {
        public final /* synthetic */ Function1<ArrayList<WorldClockPrefData>, Unit> $callback;
        public final /* synthetic */ Ref.ObjectRef<ArrayList<WorldClockPrefData>> $worldClockCountryList;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public a(Ref.ObjectRef<ArrayList<WorldClockPrefData>> objectRef, Function1<? super ArrayList<WorldClockPrefData>, Unit> function1) {
            super(1);
            this.$worldClockCountryList = objectRef;
            this.$callback = function1;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ArrayList<WorldClockPrefData> arrayList) {
            invoke2(arrayList);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ArrayList<WorldClockPrefData> countryList) {
            Intrinsics.checkNotNullParameter(countryList, "countryList");
            this.$worldClockCountryList.element.addAll(countryList);
            this.$callback.invoke(this.$worldClockCountryList.element);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$pullSmartAlertAppConfigFromServer$1", f = "ActivityPairingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                if (bleDeviceInfo != null && bleDeviceInfo.getFirmwareRevision() != null) {
                    SmartAlertUtils.Companion companion = SmartAlertUtils.Companion;
                    Context applicationContext = ActivityPairingViewModel.this.getContext().getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
                    SmartAlertUtils.Companion.getSmartAlertAppsFromServer$default(companion, applicationContext, null, false, 4, null);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public ActivityPairingViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5246a = context;
        this.c = ActivityPairingViewModel.class.getSimpleName();
        this.e = 900000;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.util.ArrayList] */
    public static final void n(Ref.ObjectRef worldClockCountryList, ActivityPairingViewModel this$0, Ref.ObjectRef mFirebaseRemoteConfig, Task task) {
        Intrinsics.checkNotNullParameter(worldClockCountryList, "$worldClockCountryList");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mFirebaseRemoteConfig, "$mFirebaseRemoteConfig");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            String string = ((FirebaseRemoteConfig) mFirebaseRemoteConfig.element).getString("worldclock_json_url");
            Intrinsics.checkNotNullExpressionValue(string, "mFirebaseRemoteConfig.ge…ng(\"worldclock_json_url\")");
            worldClockCountryList.element = this$0.j(string);
            return;
        }
        LogHelper.i(this$0.c, "CountryList Fetch Failed");
    }

    public static final void o(ActivityPairingViewModel this$0, FirebaseRemoteConfig mFirebaseRemoteConfig, Function1 callback, Ref.ObjectRef worldClockCountryList, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mFirebaseRemoteConfig, "$mFirebaseRemoteConfig");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(worldClockCountryList, "$worldClockCountryList");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            String string = mFirebaseRemoteConfig.getString("worldclock_json_url");
            Intrinsics.checkNotNullExpressionValue(string, "mFirebaseRemoteConfig.ge…ng(\"worldclock_json_url\")");
            this$0.k(string, new a(worldClockCountryList, callback));
            return;
        }
        callback.invoke(worldClockCountryList.element);
    }

    public static final void p(Exception it) {
        Intrinsics.checkNotNullParameter(it, "it");
    }

    public static final void q(final FirebaseRemoteConfig remoteConfig, final ActivityPairingViewModel this$0, final Context context, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Void r4 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.b
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    ActivityPairingViewModel.r(FirebaseRemoteConfig.this, this$0, context, task2);
                }
            });
            return;
        }
        LogHelper.e(this$0.c, "Remote Config Failed");
    }

    public static final void r(FirebaseRemoteConfig remoteConfig, ActivityPairingViewModel this$0, Context context, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(ThemeConstants.FEATURE_MAPPING.getValue());
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(T…ts.FEATURE_MAPPING.value)");
        String str = this$0.c;
        LogHelper.d(str, "feature mapping updated: " + string);
        if (string.length() == 0) {
            return;
        }
        FeatureMapping featureMapping = (FeatureMapping) new Gson().fromJson(string, (Class<Object>) FeatureMapping.class);
        String str2 = this$0.c;
        LogHelper.d(str2, "feature mapping updated: " + featureMapping);
        String str3 = ((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getmModelNumber();
        if (featureMapping != null && featureMapping.getIndusindBankBranding() != null && featureMapping.getIndusindBankBranding().getWellnessCrew() != null) {
            if (featureMapping.getIndusindBankBranding().getWellnessCrew().contains(str3)) {
                SessionManager.getInstance(context).showIndusInd(true);
                return;
            } else {
                SessionManager.getInstance(context).showIndusInd(false);
                return;
            }
        }
        SessionManager.getInstance(context).showIndusInd(false);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.util.ArrayList] */
    public final void A(AutoRecognize autoRecognize) {
        String name;
        String name2;
        boolean z;
        String name3;
        String name4;
        List<AutoRecognizeActivity> activities = autoRecognize.getActivities();
        ArrayList arrayList = new ArrayList();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        ArrayList<AutoRecognitonModel> defaultAutoRecogActivities = PayUtils.INSTANCE.getDefaultAutoRecogActivities();
        int size = defaultAutoRecogActivities.size();
        for (int i = 0; i < size; i++) {
            AutoActivityRecognitionSettingRequest autoActivityRecognitionSettingRequest = new AutoActivityRecognitionSettingRequest();
            String str = null;
            if (!(activities == null || activities.isEmpty())) {
                Iterator<AutoRecognizeActivity> it = activities.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    AutoRecognizeActivity next = it.next();
                    String type = next.getType();
                    if (!(type == null || type.length() == 0)) {
                        String type2 = next.getType();
                        ActivityTypes activityTypes = defaultAutoRecogActivities.get(i).getActivityTypes();
                        if (Intrinsics.areEqual(type2, activityTypes != null ? activityTypes.name() : null)) {
                            z = true;
                            break;
                        }
                    }
                }
                if (!z) {
                    PayUtils payUtils = PayUtils.INSTANCE;
                    ActivityTypes activityTypes2 = defaultAutoRecogActivities.get(i).getActivityTypes();
                    ActivityTypes activityTypesIDO = payUtils.getActivityTypesIDO((activityTypes2 == null || (name4 = activityTypes2.name()) == null) ? null : m.capitalize(name4));
                    Intrinsics.checkNotNull(activityTypesIDO);
                    autoActivityRecognitionSettingRequest.setActivityTpe(activityTypesIDO);
                    autoActivityRecognitionSettingRequest.setEnabled(false);
                } else {
                    PayUtils payUtils2 = PayUtils.INSTANCE;
                    ActivityTypes activityTypes3 = defaultAutoRecogActivities.get(i).getActivityTypes();
                    ActivityTypes activityTypesIDO2 = payUtils2.getActivityTypesIDO((activityTypes3 == null || (name3 = activityTypes3.name()) == null) ? null : m.capitalize(name3));
                    Intrinsics.checkNotNull(activityTypesIDO2);
                    autoActivityRecognitionSettingRequest.setActivityTpe(activityTypesIDO2);
                    autoActivityRecognitionSettingRequest.setEnabled(true);
                }
            } else {
                PayUtils payUtils3 = PayUtils.INSTANCE;
                ActivityTypes activityTypes4 = defaultAutoRecogActivities.get(i).getActivityTypes();
                ActivityTypes activityTypesIDO3 = payUtils3.getActivityTypesIDO((activityTypes4 == null || (name = activityTypes4.name()) == null) ? null : m.capitalize(name));
                Intrinsics.checkNotNull(activityTypesIDO3);
                autoActivityRecognitionSettingRequest.setActivityTpe(activityTypesIDO3);
                autoActivityRecognitionSettingRequest.setEnabled(false);
            }
            arrayList.add(autoActivityRecognitionSettingRequest);
            ArrayList arrayList2 = (ArrayList) objectRef.element;
            ActivityTypes activityTypes5 = defaultAutoRecogActivities.get(i).getActivityTypes();
            if (activityTypes5 != null && (name2 = activityTypes5.name()) != null) {
                str = m.capitalize(name2);
            }
            Intrinsics.checkNotNull(str);
            arrayList2.add(new AutoRecognitionData(str, autoActivityRecognitionSettingRequest.isEnabled()));
        }
        BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(new AutoActivityRecognitionListRequest.Builder().setAutoActivityRecognitionList(arrayList).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setAutoRecognitionToIDOWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveAutoRecognitionList(objectRef.element);
            }
        });
    }

    public final void B(final AutoRecognize autoRecognize) {
        if (BleApiManager.getInstance(this.f5246a).getBleApi().getDeviceSupportedFeatures().isActivityAutoRecognitionSupported()) {
            final AutoActivityDetectionData s = s(autoRecognize);
            SetAutoActivityDetectionSettingsRequest setAutoActivityDetectionSettingsRequest = new SetAutoActivityDetectionSettingsRequest();
            ArrayList arrayList = new ArrayList();
            Boolean smartModeEnabled = s.getSmartModeEnabled();
            Intrinsics.checkNotNullExpressionValue(smartModeEnabled, "activityDetectionData.smartModeEnabled");
            if (smartModeEnabled.booleanValue()) {
                List<AutoActivityDetectionData.Period> periods = s.getPeriods();
                if (!(periods == null || periods.isEmpty())) {
                    for (AutoActivityDetectionData.Period period : s.getPeriods()) {
                        Integer startTime = period.getStartTime();
                        Intrinsics.checkNotNullExpressionValue(startTime, "p.startTime");
                        int intValue = startTime.intValue();
                        Integer endTime = period.getEndTime();
                        Intrinsics.checkNotNullExpressionValue(endTime, "p.endTime");
                        arrayList.add(new AutoActivityDetectionModel.Period(intValue, endTime.intValue()));
                    }
                }
            }
            byte[] activities = s.getActivities();
            Intrinsics.checkNotNullExpressionValue(activities, "activityDetectionData.activities");
            Boolean sundayEnabled = s.getSundayEnabled();
            Intrinsics.checkNotNullExpressionValue(sundayEnabled, "activityDetectionData.sundayEnabled");
            boolean booleanValue = sundayEnabled.booleanValue();
            Boolean mondayEnabled = s.getMondayEnabled();
            Intrinsics.checkNotNullExpressionValue(mondayEnabled, "activityDetectionData.mondayEnabled");
            boolean booleanValue2 = mondayEnabled.booleanValue();
            Boolean tuesdayEnabled = s.getTuesdayEnabled();
            Intrinsics.checkNotNullExpressionValue(tuesdayEnabled, "activityDetectionData.tuesdayEnabled");
            boolean booleanValue3 = tuesdayEnabled.booleanValue();
            Boolean wednesdayEnabled = s.getWednesdayEnabled();
            Intrinsics.checkNotNullExpressionValue(wednesdayEnabled, "activityDetectionData.wednesdayEnabled");
            boolean booleanValue4 = wednesdayEnabled.booleanValue();
            Boolean thursdayEnabled = s.getThursdayEnabled();
            Intrinsics.checkNotNullExpressionValue(thursdayEnabled, "activityDetectionData.thursdayEnabled");
            boolean booleanValue5 = thursdayEnabled.booleanValue();
            Boolean fridayEnabled = s.getFridayEnabled();
            Intrinsics.checkNotNullExpressionValue(fridayEnabled, "activityDetectionData.fridayEnabled");
            boolean booleanValue6 = fridayEnabled.booleanValue();
            Boolean saturdayEnabled = s.getSaturdayEnabled();
            Intrinsics.checkNotNullExpressionValue(saturdayEnabled, "activityDetectionData.saturdayEnabled");
            boolean booleanValue7 = saturdayEnabled.booleanValue();
            Integer countDownValue = s.getCountDownValue();
            Intrinsics.checkNotNullExpressionValue(countDownValue, "activityDetectionData.countDownValue");
            int intValue2 = countDownValue.intValue();
            Boolean vibrationEnabled = s.getVibrationEnabled();
            Intrinsics.checkNotNullExpressionValue(vibrationEnabled, "activityDetectionData.vibrationEnabled");
            setAutoActivityDetectionSettingsRequest.setAutoActivityDetectionModel(new AutoActivityDetectionModel(activities, booleanValue, booleanValue2, booleanValue3, booleanValue4, booleanValue5, booleanValue6, booleanValue7, intValue2, vibrationEnabled.booleanValue(), arrayList));
            BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(setAutoActivityDetectionSettingsRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setAutoRecognitionToWatchToKaHaDevice$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveAutoActivityDetectionData(s);
                    ActivityPairingViewModel.this.v(autoRecognize);
                }
            });
        }
    }

    public final void C(BleDeviceInfo bleDeviceInfo) {
        BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(new TemperatureTimeIntervalRequest.Builder(UserDataManager.getInstance(this.f5246a).getAutoTemperatureInterval()).setOpen(UserDataManager.getInstance(this.f5246a).getAutoTemperatureEnabled() ? 1 : 0).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setAutoTemperature$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    @NotNull
    public final List<AlarmInfo> ConvertFromVibrateToAlarminfo() {
        List<VibrationAlarmData> vibrationAlarmData = UserDataManager.getInstance(this.f5246a).getVibrationAlarmData();
        Intrinsics.checkNotNull(vibrationAlarmData, "null cannot be cast to non-null type kotlin.collections.MutableList<@[FlexibleNullability] com.coveiot.covepreferences.data.VibrationAlarmData?>");
        List<VibrationAlarmData> asMutableList = TypeIntrinsics.asMutableList(vibrationAlarmData);
        ArrayList arrayList = new ArrayList();
        for (VibrationAlarmData vibrationAlarmData2 : asMutableList) {
            AlarmInfo alarmInfo = new AlarmInfo();
            alarmInfo.setEventName(vibrationAlarmData2.getEvent_name());
            String alarmId = vibrationAlarmData2.getAlarmId();
            Intrinsics.checkNotNullExpressionValue(alarmId, "mutlistarr.alarmId");
            alarmInfo.setAlarmId(Integer.parseInt(alarmId));
            alarmInfo.setHour(vibrationAlarmData2.getEvent_time_hour());
            alarmInfo.setMinute(vibrationAlarmData2.getEvent_time_minutes());
            alarmInfo.setDaysSelected(new AlarmInfo.Days(vibrationAlarmData2.isSunday(), vibrationAlarmData2.isMonday(), vibrationAlarmData2.isTuesday(), vibrationAlarmData2.isWednesday(), vibrationAlarmData2.isThrusday(), vibrationAlarmData2.isFriday(), vibrationAlarmData2.isSaturday()));
            alarmInfo.setAlarmOn(vibrationAlarmData2.getSwitch_on_off());
            alarmInfo.setAlarmType(vibrationAlarmData2.getAlarmType());
            alarmInfo.setSnoozeDuration(vibrationAlarmData2.getSnoozeDuration());
            alarmInfo.setSnoozeRepeatCount(vibrationAlarmData2.getSnoozeRepeatCount());
            arrayList.add(alarmInfo);
        }
        return arrayList;
    }

    public final void D() {
        if (BleApiManager.getInstance(this.f5246a).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported() && BleApiManager.getInstance(this.f5246a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            RespiratoryRateRemoteConfiguration respiratoryRateRemoteConfig = SessionManager.getInstance(this.f5246a).getRespiratoryRateRemoteConfig();
            PPGTypes pPGTypes = PPGTypes.PPG;
            String startTime = respiratoryRateRemoteConfig.getStartTime();
            Intrinsics.checkNotNullExpressionValue(startTime, "respiratoryRateConfPref.startTime");
            List split$default = StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null);
            int parseInt = Integer.parseInt((String) split$default.get(1)) + (Integer.parseInt((String) split$default.get(0)) * 60);
            String endTime = respiratoryRateRemoteConfig.getEndTime();
            Intrinsics.checkNotNullExpressionValue(endTime, "respiratoryRateConfPref.endTime");
            List split$default2 = StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null);
            int parseInt2 = Integer.parseInt((String) split$default2.get(1)) + (Integer.parseInt((String) split$default2.get(0)) * 60);
            String interval = respiratoryRateRemoteConfig.getInterval();
            Intrinsics.checkNotNullExpressionValue(interval, "respiratoryRateConfPref.interval");
            List split$default3 = StringsKt__StringsKt.split$default((CharSequence) interval, new String[]{":"}, false, 0, 6, (Object) null);
            SetAutomaticPPGConfigRequest setAutomaticPPGConfigRequest = new SetAutomaticPPGConfigRequest.Builder(pPGTypes, parseInt, parseInt2, (Integer.parseInt((String) split$default3.get(0)) * 60) + Integer.parseInt((String) split$default3.get(1)), respiratoryRateRemoteConfig.getDuration()).build();
            BleApi bleApi = BleApiManager.getInstance(this.f5246a).getBleApi();
            Intrinsics.checkNotNullExpressionValue(setAutomaticPPGConfigRequest, "setAutomaticPPGConfigRequest");
            bleApi.setUserSettings(setAutomaticPPGConfigRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setDefaultRespiratoryRateSettingsToWatch$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveRespiratoryRateFeatureEnabled(true);
                }
            });
        }
    }

    public final void E(BleDeviceInfo bleDeviceInfo) {
        SetLiftWristRequest.Builder builder;
        boolean z = true;
        if (BleApiManager.getInstance(this.f5246a).getBleApi().getDeviceSupportedFeatures().isDeviceSettingsSupportedInOneCommand()) {
            final Boolean isUnitInMile = UserDataManager.getInstance(this.f5246a).isDistanceUnitInMile();
            final Boolean isRightHandSelected = UserDataManager.getInstance(this.f5246a).isRightHandSelected();
            final Boolean hourPref = UserDataManager.getInstance(this.f5246a).isTimeIn12HourFormat();
            final Boolean isLiftWristEnabled = UserDataManager.getInstance(this.f5246a).isLiftWristToViewEnable();
            final Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(this.f5246a).isTemperatureUnitInFahrenheit();
            SetDeviceParametersRequest.Builder handState = new SetDeviceParametersRequest.Builder().setHandState(!isRightHandSelected.booleanValue());
            Intrinsics.checkNotNullExpressionValue(hourPref, "hourPref");
            SetDeviceParametersRequest.Builder hourFormat = handState.setHourFormat(hourPref.booleanValue());
            Intrinsics.checkNotNullExpressionValue(isUnitInMile, "isUnitInMile");
            SetDeviceParametersRequest.Builder temperatureUnitInCelsius = hourFormat.setDistanceUnit(isUnitInMile.booleanValue()).setTemperatureUnitInCelsius(!isTemperatureUnitInFahrenheit.booleanValue());
            Intrinsics.checkNotNullExpressionValue(isLiftWristEnabled, "isLiftWristEnabled");
            SetDeviceParametersRequest build = temperatureUnitInCelsius.setLiftWristEnable(isLiftWristEnabled.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder().setHandState(!…LiftWristEnabled).build()");
            BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setDeviceParameters$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    ActivityPairingViewModel.this.getViewmodelListener().onSuccess();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveDistanceUnitPref(isUnitInMile);
                    UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveHourFormatPref(hourPref);
                    UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveHandPref(isRightHandSelected);
                    UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveLiftWristPref(isLiftWristEnabled);
                    UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveTemperatureUnitPref(isTemperatureUnitInFahrenheit);
                    ActivityPairingViewModel.this.getViewmodelListener().onSuccess();
                }
            });
            return;
        }
        if (BleApiManager.getInstance(this.f5246a).getBleApi().getDeviceSupportedFeatures().isHandPreferenceSettingsSupported()) {
            Boolean handPref = UserDataManager.getInstance(this.f5246a).isRightHandSelected();
            Intrinsics.checkNotNullExpressionValue(handPref, "handPref");
            SetWearingHandRequest build2 = new SetWearingHandRequest.Builder(handPref.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build2, "Builder(handPref).build()");
            BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(build2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setDeviceParameters$2
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), response.toString());
                }
            });
        }
        if (BleApiManager.getInstance(this.f5246a).getBleApi().getDeviceSupportedFeatures().isDistanceUnitSettingsSupported()) {
            Boolean isUnitInMile2 = UserDataManager.getInstance(this.f5246a).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isUnitInMile2, "isUnitInMile");
            SetDistanceUnitRequest build3 = new SetDistanceUnitRequest.Builder(isUnitInMile2.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build3, "Builder(isUnitInMile).build()");
            BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(build3, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setDeviceParameters$3
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), response.toString());
                }
            });
        }
        if (BleApiManager.getInstance(this.f5246a).getBleApi().getDeviceSupportedFeatures().isTemperatureUnitSettingsSupported()) {
            SetTemperatureUnitRequest build4 = new SetTemperatureUnitRequest.Builder(!UserDataManager.getInstance(this.f5246a).isTemperatureUnitInFahrenheit().booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build4, "Builder(!isTemperatureinFahrenheit).build()");
            BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(build4, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setDeviceParameters$4
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), response.toString());
                }
            });
        }
        if (BleApiManager.getInstance(this.f5246a).getBleApi().getDeviceSupportedFeatures().isLiftWristToViewSettingsSupported()) {
            Boolean isLiftWristToViewOn = UserDataManager.getInstance(this.f5246a).isLiftWristToViewEnable();
            if (BleApiManager.getInstance(this.f5246a).getBleApi() != null && BleApiManager.getInstance(this.f5246a).getBleApi().getDeviceSupportedFeatures().isScheduledLiftWristViewSettingsSupported()) {
                Boolean isScheduleLiftWristToViewEnable = UserDataManager.getInstance(this.f5246a).isScheduleLiftWristToViewEnable();
                Intrinsics.checkNotNullExpressionValue(isScheduleLiftWristToViewEnable, "getInstance(context).isS…duleLiftWristToViewEnable");
                if (!isScheduleLiftWristToViewEnable.booleanValue()) {
                    Boolean isLiftWristToViewEnable = UserDataManager.getInstance(this.f5246a).isLiftWristToViewEnable();
                    Intrinsics.checkNotNullExpressionValue(isLiftWristToViewEnable, "getInstance(\n           …).isLiftWristToViewEnable");
                    if (!isLiftWristToViewEnable.booleanValue()) {
                        z = false;
                    }
                }
                builder = new SetLiftWristRequest.Builder(z);
                builder.setStartHour(UserDataManager.getInstance(this.f5246a).getLiftWristToViewStartHour());
                builder.setStartMinute(UserDataManager.getInstance(this.f5246a).getLiftWristToViewStartMin());
                builder.setEndHour(UserDataManager.getInstance(this.f5246a).getLiftWristToViewEndHour());
                builder.setEndMinute(UserDataManager.getInstance(this.f5246a).getLiftWristToViewEndMin());
            } else {
                Intrinsics.checkNotNullExpressionValue(isLiftWristToViewOn, "isLiftWristToViewOn");
                builder = new SetLiftWristRequest.Builder(isLiftWristToViewOn.booleanValue());
            }
            SetLiftWristRequest build5 = builder.build();
            Intrinsics.checkNotNullExpressionValue(build5, "builder.build()");
            BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(build5, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setDeviceParameters$5
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), response.toString());
                }
            });
        }
        if (BleApiManager.getInstance(this.f5246a).getBleApi().getDeviceSupportedFeatures().isTimeFormatSettingsSupported()) {
            Boolean isTimeIn12HourFormat = UserDataManager.getInstance(this.f5246a).isTimeIn12HourFormat();
            Intrinsics.checkNotNullExpressionValue(isTimeIn12HourFormat, "isTimeIn12HourFormat");
            SetHourFormatRequest build6 = new SetHourFormatRequest.Builder(isTimeIn12HourFormat.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build6, "Builder(isTimeIn12HourFormat).build()");
            BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(build6, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setDeviceParameters$6
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), response.toString());
                }
            });
        }
        getViewmodelListener().onSuccess();
    }

    public final void convertBeanToWorldClockData(@NotNull final Context context, @NotNull final GetAllUserAppSettingsRes response) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(response, "response");
        m(new Function1<ArrayList<WorldClockPrefData>, Unit>() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$convertBeanToWorldClockData$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ArrayList<WorldClockPrefData> arrayList) {
                invoke2(arrayList);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull ArrayList<WorldClockPrefData> worldClockCountryList) {
                WorldClockPrefData i;
                Intrinsics.checkNotNullParameter(worldClockCountryList, "worldClockCountryList");
                if (GetAllUserAppSettingsRes.this.getDualTimeBean() == null || GetAllUserAppSettingsRes.this.getDualTimeBean().getTimeZones() == null) {
                    return;
                }
                List<DualTimeBean.TimeZone> timeZones = GetAllUserAppSettingsRes.this.getDualTimeBean().getTimeZones();
                final ArrayList arrayList = new ArrayList();
                for (DualTimeBean.TimeZone timeZonesList : timeZones) {
                    Intrinsics.checkNotNullExpressionValue(timeZonesList, "timeZonesList");
                    DualTimeBean.TimeZone timeZone = timeZonesList;
                    if (timeZone.getCity() != null) {
                        ActivityPairingViewModel activityPairingViewModel = this;
                        String city = timeZone.getCity();
                        Intrinsics.checkNotNullExpressionValue(city, "timeZones.city");
                        i = activityPairingViewModel.i(worldClockCountryList, city);
                        if (i != null) {
                            arrayList.add(i);
                        }
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    SetWorldClockRequest setWorldClockRequest = new SetWorldClockRequest();
                    Integer id = ((WorldClockPrefData) arrayList.get(i2)).getId();
                    Intrinsics.checkNotNull(id);
                    setWorldClockRequest.setId(id.intValue());
                    String name = ((WorldClockPrefData) arrayList.get(i2)).getName();
                    Intrinsics.checkNotNull(name);
                    setWorldClockRequest.setCityName(name);
                    Double latitude = ((WorldClockPrefData) arrayList.get(i2)).getLatitude();
                    Intrinsics.checkNotNull(latitude);
                    setWorldClockRequest.setLatitude(latitude.doubleValue());
                    Double longitude = ((WorldClockPrefData) arrayList.get(i2)).getLongitude();
                    Intrinsics.checkNotNull(longitude);
                    setWorldClockRequest.setLongitude(longitude.doubleValue());
                    setWorldClockRequest.setSunSet(0);
                    setWorldClockRequest.setSunRise(0);
                    setWorldClockRequest.setTimeZoneOffsetMinutes((int) TimeUnit.MILLISECONDS.toMinutes(TimeZone.getTimeZone(((WorldClockPrefData) arrayList.get(i2)).getTimeZoneName()).getOffset(Calendar.getInstance().getTimeInMillis())));
                    arrayList2.add(setWorldClockRequest);
                }
                SetWorldClockListRequest build = new SetWorldClockListRequest.Builder().setSedentaryReminderList(arrayList2).build();
                BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
                final Context context2 = context;
                bleApi.setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$convertBeanToWorldClockData$1.1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                        Intrinsics.checkNotNullParameter(response2, "response");
                        UserDataManager.getInstance(context2).saveWorldClockList(arrayList);
                    }
                });
            }
        });
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.lang.Object, com.coveiot.covepreferences.data.DoNotDisturbData] */
    public final void convertDndData(@NotNull final Context context, @NotNull GetAllUserAppSettingsRes response) {
        SetDNDModeRequest build;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(response, "response");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? doNotDisturbData = DoNotDisturbData.getInstance();
        Intrinsics.checkNotNullExpressionValue(doNotDisturbData, "getInstance()");
        objectRef.element = doNotDisturbData;
        if (response.getDndBean().getSchedules() != null && response.getDndBean().getSchedules().size() > 0 && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isScheduledDndSupported()) {
            String startTime = response.getDndBean().getSchedules().get(0).getStartTime();
            Intrinsics.checkNotNullExpressionValue(startTime, "response.dndBean.schedules[0].startTime");
            String endTime = response.getDndBean().getSchedules().get(0).getEndTime();
            Intrinsics.checkNotNullExpressionValue(endTime, "response.dndBean.schedules[0].endTime");
            ((DoNotDisturbData) objectRef.element).setBeggining_time_hour(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0)));
            ((DoNotDisturbData) objectRef.element).setBeggining_time_minutes(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(1)));
            ((DoNotDisturbData) objectRef.element).setEnd_time_hour(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(0)));
            ((DoNotDisturbData) objectRef.element).setEnd_time_minutes(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(1)));
            if (startTime.equals("00:00:00") && endTime.equals("00:00:00")) {
                ((DoNotDisturbData) objectRef.element).setBeggining_time_hour(22);
                ((DoNotDisturbData) objectRef.element).setBeggining_time_minutes(0);
                ((DoNotDisturbData) objectRef.element).setEnd_time_hour(6);
                ((DoNotDisturbData) objectRef.element).setEnd_time_minutes(0);
            }
            Boolean active = response.getDndBean().getActive();
            Intrinsics.checkNotNullExpressionValue(active, "response.dndBean.active");
            ((DoNotDisturbData) objectRef.element).setSchedule_on_off(active.booleanValue());
            ((DoNotDisturbData) objectRef.element).setDnd_on_off(false);
            UserDataManager userDataManager = UserDataManager.getInstance(context);
            Boolean active2 = response.getDndBean().getActive();
            Intrinsics.checkNotNullExpressionValue(active2, "response.dndBean.active");
            userDataManager.saveScheuleDnd(active2.booleanValue());
            int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            int parseInt3 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            int parseInt4 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            Boolean active3 = response.getDndBean().getActive();
            Intrinsics.checkNotNullExpressionValue(active3, "response.dndBean.active");
            build = new SetDNDModeRequest.Builder(active3.booleanValue(), parseInt, parseInt2, parseInt3, parseInt4).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(\n               …                ).build()");
        } else {
            Boolean active4 = response.getDndBean().getActive();
            Intrinsics.checkNotNullExpressionValue(active4, "response.dndBean.active");
            ((DoNotDisturbData) objectRef.element).setDnd_on_off(active4.booleanValue());
            ((DoNotDisturbData) objectRef.element).setSchedule_on_off(false);
            UserDataManager.getInstance(context).saveScheuleDnd(false);
            Boolean active5 = response.getDndBean().getActive();
            Intrinsics.checkNotNullExpressionValue(active5, "response.dndBean.active");
            build = new SetDNDModeRequest.Builder(active5.booleanValue(), 0, 0, 23, 59).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(response.dndBean…ve, 0, 0, 23, 59).build()");
        }
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isDndSupported() || BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isScheduledDndSupported()) {
            BleApiManager.getInstance(context).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$convertDndData$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                    Intrinsics.checkNotNullParameter(response2, "response");
                    UserDataManager.getInstance(context).saveDoNotDisturbSettings(objectRef.element);
                }
            });
        }
    }

    public final void convertDrinkReminderBeanToReminderData(@NotNull Context context, @NotNull GetAllUserAppSettingsRes response) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.getDrinkReminderAlert() != null) {
            GetAllUserAppSettingsRes.DrinkReminderAlertBean drinkReminderAlert = response.getDrinkReminderAlert();
            Intrinsics.checkNotNullExpressionValue(drinkReminderAlert, "response.drinkReminderAlert");
            DrinkWaterReminderData companion = DrinkWaterReminderData.Companion.getInstance();
            Intrinsics.checkNotNull(companion);
            companion.setReminderEnable(drinkReminderAlert.isActive());
            String startTime = drinkReminderAlert.getStartTime();
            Intrinsics.checkNotNullExpressionValue(startTime, "drinkReminderAlert.startTime");
            String endTime = drinkReminderAlert.getEndTime();
            Intrinsics.checkNotNullExpressionValue(endTime, "drinkReminderAlert.endTime");
            companion.setStartHour(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0)));
            companion.setStartMinute(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(1)));
            companion.setEndHour(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(0)));
            companion.setEndMinute(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(1)));
            int i = 60;
            if (drinkReminderAlert.getInterval().length() == 8) {
                String interval = drinkReminderAlert.getInterval();
                Intrinsics.checkNotNullExpressionValue(interval, "drinkReminderAlert.interval");
                int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval, new String[]{":"}, false, 0, 6, (Object) null).get(0));
                String interval2 = drinkReminderAlert.getInterval();
                Intrinsics.checkNotNullExpressionValue(interval2, "drinkReminderAlert.interval");
                int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval2, new String[]{":"}, false, 0, 6, (Object) null).get(1));
                String interval3 = drinkReminderAlert.getInterval();
                Intrinsics.checkNotNullExpressionValue(interval3, "drinkReminderAlert.interval");
                i = (parseInt * 60) + parseInt2 + Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval3, new String[]{":"}, false, 0, 6, (Object) null).get(2));
            }
            companion.setRemindInterval(i);
            UserDataManager.getInstance(context).saveDrinkWaterReminderSettings(companion);
            SetReminderRequest drinkReminderReq = new SetReminderRequest.Builder().setStartHour1(companion.getStartHour()).setStartMin1(companion.getStartMinute()).setEndHour1(companion.getEndHour()).setEndMin1(companion.getEndMinute()).setReminderInterval(companion.getRemindInterval()).setEnabled(companion.isReminderEnable()).setReminderType(ReminderType.DRINK_WATER_REMINDER).build();
            BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
            Intrinsics.checkNotNullExpressionValue(drinkReminderReq, "drinkReminderReq");
            bleApi.setUserSettings(drinkReminderReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$convertDrinkReminderBeanToReminderData$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
                    Intrinsics.checkNotNullParameter(bleBaseResponse, "bleBaseResponse");
                }
            });
        }
    }

    public final void convertFromAlarminfoToVibrate(@NotNull Context context, @NotNull GetAllUserAppSettingsRes response) {
        int alarmId;
        boolean z;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(response, "response");
        List<GetAllUserAppSettingsRes.AlarmBean.ListBean> list = response.getAlarm().getList();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        if (list.get(0).getAlarmId() == 0) {
            alarmId = 0;
            z = false;
        } else {
            alarmId = list.get(0).getAlarmId();
            z = true;
        }
        for (GetAllUserAppSettingsRes.AlarmBean.ListBean alarmInfos : list) {
            Intrinsics.checkNotNullExpressionValue(alarmInfos, "alarmInfos");
            GetAllUserAppSettingsRes.AlarmBean.ListBean listBean = alarmInfos;
            VibrationAlarmData vibrationAlarmData = VibrationAlarmData.getInstance();
            Intrinsics.checkNotNullExpressionValue(vibrationAlarmData, "getInstance()");
            vibrationAlarmData.setEvent_name(AppUtils.isEmpty(listBean.getLabel()) ? "" : listBean.getLabel());
            if (Intrinsics.areEqual(listBean.getType(), AlarmType.FOOD.getAlarmType())) {
                vibrationAlarmData.setAlarmType(4);
            } else if (Intrinsics.areEqual(listBean.getType(), AlarmType.DRINK.getAlarmType())) {
                vibrationAlarmData.setAlarmType(3);
            } else if (Intrinsics.areEqual(listBean.getType(), AlarmType.MEDICINE.getAlarmType())) {
                vibrationAlarmData.setAlarmType(2);
            } else {
                vibrationAlarmData.setAlarmType(1);
            }
            String time = listBean.getTime();
            Intrinsics.checkNotNullExpressionValue(time, "mutalblealarm.time");
            vibrationAlarmData.setEvent_time_hour(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).get(i)));
            vibrationAlarmData.setEvent_time_minutes(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).get(1)));
            vibrationAlarmData.setSwitch_on_off(listBean.isActive());
            vibrationAlarmData.setWeeks(listBean.getWeek());
            vibrationAlarmData.setSunday(!Character.valueOf(listBean.getWeek().charAt(i)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            vibrationAlarmData.setMonday(!Character.valueOf(listBean.getWeek().charAt(1)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            vibrationAlarmData.setTuesday(!Character.valueOf(listBean.getWeek().charAt(2)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            vibrationAlarmData.setWednesday(!Character.valueOf(listBean.getWeek().charAt(3)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            vibrationAlarmData.setThrusday(!Character.valueOf(listBean.getWeek().charAt(4)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            vibrationAlarmData.setFriday(!Character.valueOf(listBean.getWeek().charAt(5)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            vibrationAlarmData.setSaturday(!Character.valueOf(listBean.getWeek().charAt(6)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            if (z) {
                vibrationAlarmData.setAlarmId(String.valueOf(listBean.getAlarmId() - alarmId));
            } else {
                vibrationAlarmData.setAlarmId(String.valueOf(listBean.getAlarmId()));
            }
            if (response.getAlarm().getSnooze() != null && response.getAlarm().getSnooze().getInterval() != null && response.getAlarm().getSnooze().getMaxAllowed() != null) {
                String interval = response.getAlarm().getSnooze().getInterval();
                Intrinsics.checkNotNullExpressionValue(interval, "response.alarm.snooze.interval");
                vibrationAlarmData.setSnoozeDuration(Integer.parseInt(m.replace$default(m.replace$default(interval, "00", "", false, 4, (Object) null), ":", "", false, 4, (Object) null)));
                Integer maxAllowed = response.getAlarm().getSnooze().getMaxAllowed();
                Intrinsics.checkNotNullExpressionValue(maxAllowed, "response.alarm.snooze.maxAllowed");
                vibrationAlarmData.setSnoozeRepeatCount(maxAllowed.intValue());
            }
            arrayList.add(vibrationAlarmData);
            i = 0;
        }
        UserDataManager.getInstance(context).saveVibrationAlarmSettings(arrayList);
        this.d = 0;
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isMultipleAlarmsSupportedAtATime()) {
            sendMultipleAlarmsToBle(ConvertFromVibrateToAlarminfo(), response);
            return;
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isEastApexDevice(context) && !companion.isSmaDevice(context)) {
            sendToBle(ConvertFromVibrateToAlarminfo(), response);
        } else if (response.getAutoHr() != null) {
            GetAllUserAppSettingsRes.AutoHr autoHr = response.getAutoHr();
            Intrinsics.checkNotNullExpressionValue(autoHr, "response.autoHr");
            setAutoHrFromResponse(context, autoHr);
        } else {
            getViewmodelListener().onSuccess();
        }
    }

    @NotNull
    public final List<ScheduleInfo> convertFromScheduleDataToScheduleInfo() {
        List<ScheduleData> scheduleData = UserDataManager.getInstance(this.f5246a).getScheduleData();
        Intrinsics.checkNotNull(scheduleData, "null cannot be cast to non-null type kotlin.collections.MutableList<@[FlexibleNullability] com.coveiot.covepreferences.data.ScheduleData?>");
        List<ScheduleData> asMutableList = TypeIntrinsics.asMutableList(scheduleData);
        ArrayList arrayList = new ArrayList();
        for (ScheduleData scheduleData2 : asMutableList) {
            ScheduleInfo scheduleInfo = new ScheduleInfo();
            scheduleInfo.setScheduleId(scheduleData2.getScheduleId());
            scheduleInfo.setTitle(scheduleData2.getTitle());
            scheduleInfo.setContent(scheduleData2.getContent());
            scheduleInfo.setAdvance(scheduleData2.getAdvance());
            scheduleInfo.setDay(scheduleData2.getDay());
            scheduleInfo.setMonth(scheduleData2.getMonth());
            scheduleInfo.setYear(scheduleData2.getYear());
            scheduleInfo.setHour(scheduleData2.getHour());
            scheduleInfo.setMinute(scheduleData2.getMinute());
            arrayList.add(scheduleInfo);
        }
        if ((!arrayList.isEmpty()) && arrayList.size() > 1) {
            kotlin.collections.h.sortWith(arrayList, new Comparator() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$convertFromScheduleDataToScheduleInfo$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return kotlin.comparisons.f.compareValues(Integer.valueOf(((ScheduleInfo) t).getScheduleId()), Integer.valueOf(((ScheduleInfo) t2).getScheduleId()));
                }
            });
        }
        return arrayList;
    }

    public final void convertSedentaryBeanToReminderData(@NotNull Context context, @NotNull GetAllUserAppSettingsRes response) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.getSedentaryAlert() != null) {
            GetAllUserAppSettingsRes.SedentaryAlertBean sedentaryAlert = response.getSedentaryAlert();
            Intrinsics.checkNotNullExpressionValue(sedentaryAlert, "response.sedentaryAlert");
            SedentaryReminderData sedentaryReminderData = SedentaryReminderData.getInstance();
            Intrinsics.checkNotNullExpressionValue(sedentaryReminderData, "getInstance()");
            sedentaryReminderData.setAlarm_on_off(sedentaryAlert.isActive());
            String startTime = sedentaryAlert.getStartTime();
            Intrinsics.checkNotNullExpressionValue(startTime, "sedentaryAlert.startTime");
            String endTime = sedentaryAlert.getEndTime();
            Intrinsics.checkNotNullExpressionValue(endTime, "sedentaryAlert.endTime");
            sedentaryReminderData.setBeggining_time_hour(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0)));
            sedentaryReminderData.setBeggining_time_minutes(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(1)));
            sedentaryReminderData.setEnd_time_hour(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(0)));
            sedentaryReminderData.setEnd_time_minutes(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(1)));
            int i = 15;
            if (sedentaryAlert.getInterval().length() == 8) {
                String interval = sedentaryAlert.getInterval();
                Intrinsics.checkNotNullExpressionValue(interval, "sedentaryAlert.interval");
                int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval, new String[]{":"}, false, 0, 6, (Object) null).get(0));
                String interval2 = sedentaryAlert.getInterval();
                Intrinsics.checkNotNullExpressionValue(interval2, "sedentaryAlert.interval");
                int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval2, new String[]{":"}, false, 0, 6, (Object) null).get(1));
                String interval3 = sedentaryAlert.getInterval();
                Intrinsics.checkNotNullExpressionValue(interval3, "sedentaryAlert.interval");
                i = (parseInt * 60) + parseInt2 + Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval3, new String[]{":"}, false, 0, 6, (Object) null).get(2));
            }
            sedentaryReminderData.setRemind_in(i);
            this.f = sedentaryReminderData.getBeggining_time_hour();
            this.g = sedentaryReminderData.getBeggining_time_minutes();
            int i2 = this.f;
            if (i2 > 12) {
                this.j = ' ' + context.getResources().getString(R.string.pm);
                this.f = this.f - 12;
            } else if (i2 == 12) {
                this.j = ' ' + context.getResources().getString(R.string.pm);
                this.f = this.f;
            } else {
                this.j = ' ' + context.getResources().getString(R.string.am);
            }
            if (this.f == 0) {
                this.f = 12;
            }
            StringBuilder sb = new StringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.f)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(':');
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.g)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb.append(format2);
            sb.append(this.j);
            String sb2 = sb.toString();
            this.h = sedentaryReminderData.getEnd_time_hour();
            this.i = sedentaryReminderData.getEnd_time_minutes();
            int i3 = this.h;
            if (i3 > 12) {
                this.j = ' ' + context.getResources().getString(R.string.pm);
                this.h = this.h - 12;
            } else if (i3 == 12) {
                this.j = ' ' + context.getResources().getString(R.string.pm);
                this.h = this.h;
            } else {
                this.j = ' ' + context.getResources().getString(R.string.am);
            }
            if (this.h == 0) {
                this.h = 12;
            }
            StringBuilder sb3 = new StringBuilder();
            String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.h)}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
            sb3.append(format3);
            sb3.append(':');
            String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.i)}, 1));
            Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
            sb3.append(format4);
            sb3.append(this.j);
            String sb4 = sb3.toString();
            UserDataManager.getInstance(context).saveSedentaryReminderSettings(sedentaryReminderData);
            sendSedentarytoBleApi(i, sedentaryAlert.isActive(), sb2, sb4, response);
        } else if (response.getAutoHr() == null || response.getAutoHr() == null) {
        } else {
            GetAllUserAppSettingsRes.AutoHr autoHr = response.getAutoHr();
            Intrinsics.checkNotNullExpressionValue(autoHr, "response.autoHr");
            setAutoHrFromResponse(context, autoHr);
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, com.coveiot.covepreferences.data.WomenWellnessData] */
    public final void convertWomenWellnessData(@NotNull final Context context, @NotNull GetAllUserAppSettingsRes response) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(response, "response");
        List<MensturationBean.Reminder> reminders = response.getMensturationBean().getReminders();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? womenWellnessData = WomenWellnessData.getInstance();
        objectRef.element = womenWellnessData;
        Integer cycleLength = response.getMensturationBean().getCycleLength();
        Intrinsics.checkNotNullExpressionValue(cycleLength, "response.mensturationBean.cycleLength");
        ((WomenWellnessData) womenWellnessData).setMenstruationCycleLength(cycleLength.intValue());
        Integer periodLength = response.getMensturationBean().getPeriodLength();
        Intrinsics.checkNotNullExpressionValue(periodLength, "response.mensturationBean.periodLength");
        ((WomenWellnessData) objectRef.element).setMenstruationPeriodLength(periodLength.intValue());
        String cycleStartDate = response.getMensturationBean().getCycleStartDate();
        Intrinsics.checkNotNullExpressionValue(cycleStartDate, "response.mensturationBean.cycleStartDate");
        List split$default = StringsKt__StringsKt.split$default((CharSequence) cycleStartDate, new String[]{"-"}, false, 0, 6, (Object) null);
        ((WomenWellnessData) objectRef.element).setLastPeriodYear(Integer.parseInt((String) split$default.get(0)));
        ((WomenWellnessData) objectRef.element).setLastPeriodMonth(Integer.parseInt((String) split$default.get(1)));
        ((WomenWellnessData) objectRef.element).setLastPeriodDay(Integer.parseInt((String) split$default.get(2)));
        if (!AppUtils.isEmpty(reminders)) {
            String remindAt = response.getMensturationBean().getReminders().get(0).getRemindAt();
            Intrinsics.checkNotNullExpressionValue(remindAt, "response.mensturationBean.reminders[0].remindAt");
            List split$default2 = StringsKt__StringsKt.split$default((CharSequence) remindAt, new String[]{":"}, false, 0, 6, (Object) null);
            ((WomenWellnessData) objectRef.element).setReminderHour(Integer.parseInt((String) split$default2.get(0)));
            ((WomenWellnessData) objectRef.element).setReminderMinute(Integer.parseInt((String) split$default2.get(1)));
            Boolean active = response.getMensturationBean().getActive();
            Intrinsics.checkNotNullExpressionValue(active, "response.mensturationBean.active");
            ((WomenWellnessData) objectRef.element).setEnabled(active.booleanValue());
            for (MensturationBean.Reminder reminderList : reminders) {
                Intrinsics.checkNotNullExpressionValue(reminderList, "reminderList");
                MensturationBean.Reminder reminder = reminderList;
                if (reminder.getType().equals(context.getResources().getString(R.string.period))) {
                    Integer remindBefore = reminder.getRemindBefore();
                    Intrinsics.checkNotNullExpressionValue(remindBefore, "reminderData.remindBefore");
                    ((WomenWellnessData) objectRef.element).setPeriodReminderAdvance(remindBefore.intValue());
                }
                if (reminder.getType().equals(context.getResources().getString(R.string.ovulation))) {
                    Integer remindBefore2 = reminder.getRemindBefore();
                    Intrinsics.checkNotNullExpressionValue(remindBefore2, "reminderData.remindBefore");
                    ((WomenWellnessData) objectRef.element).setOvulationReminderAdvance(remindBefore2.intValue());
                }
            }
        } else {
            Boolean active2 = response.getMensturationBean().getActive();
            Intrinsics.checkNotNullExpressionValue(active2, "response.mensturationBean.active");
            ((WomenWellnessData) objectRef.element).setEnabled(active2.booleanValue());
        }
        BleApiManager.getInstance(context).getBleApi().setUserSettings(new SetWomenWellnessSettingsRequest.Builder().setEnabled(((WomenWellnessData) objectRef.element).isEnabled()).setLastPeriodDay(((WomenWellnessData) objectRef.element).getLastPeriodDay()).setLastPeriodMonth(((WomenWellnessData) objectRef.element).getLastPeriodMonth()).setLastPeriodYear(((WomenWellnessData) objectRef.element).getLastPeriodYear()).setMenstruationCycleLength(((WomenWellnessData) objectRef.element).getMenstruationCycleLength()).setMenstruationPeriodLength(((WomenWellnessData) objectRef.element).getMenstruationPeriodLength()).setOvulationReminderAdvance(((WomenWellnessData) objectRef.element).getOvulationReminderAdvance()).setPeriodReminderAdvance(((WomenWellnessData) objectRef.element).getPeriodReminderAdvance()).setReminderHour(((WomenWellnessData) objectRef.element).getReminderHour()).setReminderMinute(((WomenWellnessData) objectRef.element).getReminderMinute()).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$convertWomenWellnessData$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityPairingViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                Intrinsics.checkNotNullParameter(response2, "response");
                LogHelper.d(ActivityPairingViewModel.this.getTAG(), response2.toString());
                UserDataManager.getInstance(context).saveWomenWellnessSettings(objectRef.element);
            }
        });
    }

    public final void convertWorldClockData(@NotNull final Context context, @NotNull GetAllUserAppSettingsRes response) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(response, "response");
        ArrayList<WorldClockPrefData> l = l();
        if (response.getDualTimeBean() == null || response.getDualTimeBean().getTimeZones() == null) {
            return;
        }
        List<DualTimeBean.TimeZone> timeZones = response.getDualTimeBean().getTimeZones();
        final ArrayList arrayList = new ArrayList();
        for (DualTimeBean.TimeZone timeZonesList : timeZones) {
            Intrinsics.checkNotNullExpressionValue(timeZonesList, "timeZonesList");
            DualTimeBean.TimeZone timeZone = timeZonesList;
            timeZone.getPreference();
            WorldClockPrefData h = h(l, timeZone.getPreference());
            if (h != null) {
                arrayList.add(h);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            SetWorldClockRequest setWorldClockRequest = new SetWorldClockRequest();
            Integer id = ((WorldClockPrefData) arrayList.get(i)).getId();
            Intrinsics.checkNotNull(id);
            setWorldClockRequest.setId(id.intValue());
            String name = ((WorldClockPrefData) arrayList.get(i)).getName();
            Intrinsics.checkNotNull(name);
            setWorldClockRequest.setCityName(name);
            Double latitude = ((WorldClockPrefData) arrayList.get(i)).getLatitude();
            Intrinsics.checkNotNull(latitude);
            setWorldClockRequest.setLatitude(latitude.doubleValue());
            Double longitude = ((WorldClockPrefData) arrayList.get(i)).getLongitude();
            Intrinsics.checkNotNull(longitude);
            setWorldClockRequest.setLongitude(longitude.doubleValue());
            setWorldClockRequest.setSunSet(0);
            setWorldClockRequest.setSunRise(0);
            setWorldClockRequest.setTimeZoneOffsetMinutes((int) TimeUnit.MILLISECONDS.toMinutes(TimeZone.getTimeZone(((WorldClockPrefData) arrayList.get(i)).getTimeZoneName()).getOffset(Calendar.getInstance().getTimeInMillis())));
            arrayList2.add(setWorldClockRequest);
        }
        BleApiManager.getInstance(context).getBleApi().setUserSettings(new SetWorldClockListRequest.Builder().setSedentaryReminderList(arrayList2).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$convertWorldClockData$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                Intrinsics.checkNotNullParameter(response2, "response");
                LogHelper.i(ActivityPairingViewModel.this.getTAG(), "setWorldClockRequest onSettingsResponse");
                UserDataManager.getInstance(context).saveWorldClockList(arrayList);
            }
        });
    }

    public final void f(final BleDeviceInfo bleDeviceInfo) {
        int autoHrInterval = UserDataManager.getInstance(this.f5246a).getAutoHrInterval();
        if (UserDataManager.getInstance(this.f5246a).isAutoHrEnabled()) {
            BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(new HeartRateTimeIntervalRequest.Builder(autoHrInterval).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$checkAndEnableAutoHRSettings$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getDeviceType() == DeviceType.smaF2) {
                        ActivityPairingViewModel.this.C(bleDeviceInfo);
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getDeviceType() == DeviceType.smaF2) {
                        ActivityPairingViewModel.this.C(bleDeviceInfo);
                    }
                }
            });
        }
    }

    public final void g(Context context, GetAllUserAppSettingsRes getAllUserAppSettingsRes) {
        ArrayList arrayList = new ArrayList();
        int size = getAllUserAppSettingsRes.getCalendarBean().getEvents().size();
        for (int i = 0; i < size; i++) {
            EventsBean eventsBean = getAllUserAppSettingsRes.getCalendarBean().getEvents().get(i);
            ScheduleData scheduleData = ScheduleData.getInstance();
            scheduleData.setScheduleId(i);
            Integer remindBefore = eventsBean.getRemindBefore();
            Intrinsics.checkNotNullExpressionValue(remindBefore, "event.remindBefore");
            scheduleData.setAdvance(remindBefore.intValue());
            scheduleData.setContent(eventsBean.getDescription());
            scheduleData.setTitle(eventsBean.getSummary());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(AppUtils.parseDate(eventsBean.getStart(), UtilConstants.SERVER_TIME_FORMAT));
            scheduleData.setYear(calendar.get(1));
            scheduleData.setMonth(calendar.get(2));
            scheduleData.setDay(calendar.get(5));
            scheduleData.setHour(calendar.get(11));
            scheduleData.setMinute(calendar.get(12));
            arrayList.add(scheduleData);
        }
        UserDataManager.getInstance(context).saveScheduleSettings(arrayList);
        sendToBle(convertFromScheduleDataToScheduleInfo());
    }

    public final int getAlarmCount() {
        return this.d;
    }

    public final void getAllFitnessRecord(@NotNull String fromDate, @NotNull String todate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(todate, "todate");
        FitnessRecordApiManager.getMensurationSymptomRecord(fromDate, todate, new CoveApiListener<MensurationSymptomRecordResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getAllFitnessRecord$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable MensurationSymptomRecordResponse mensurationSymptomRecordResponse) {
                if (mensurationSymptomRecordResponse == null || mensurationSymptomRecordResponse.getItems() == null) {
                    return;
                }
                EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms = new EntityFemaleWellnessSymptoms();
                for (MensurationSymptomsRecordBeans mensurationSymptomsRecordBeans : mensurationSymptomRecordResponse.getItems().getMensesData()) {
                    entityFemaleWellnessSymptoms.setDate(mensurationSymptomsRecordBeans.getDate());
                    entityFemaleWellnessSymptoms.setFlow(mensurationSymptomsRecordBeans.getFlow());
                    entityFemaleWellnessSymptoms.setPain(mensurationSymptomsRecordBeans.getPain());
                    entityFemaleWellnessSymptoms.setMood(mensurationSymptomsRecordBeans.getMood());
                    entityFemaleWellnessSymptoms.setPhase(mensurationSymptomsRecordBeans.getPhase());
                    ArrayList arrayList = new ArrayList();
                    if (mensurationSymptomsRecordBeans.getSymptoms() != null) {
                        List<String> symptoms = mensurationSymptomsRecordBeans.getSymptoms();
                        Intrinsics.checkNotNull(symptoms, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>");
                        arrayList = (ArrayList) symptoms;
                    }
                    entityFemaleWellnessSymptoms.setSymptoms(arrayList);
                    if (mensurationSymptomsRecordBeans.getMensSettings() != null) {
                        entityFemaleWellnessSymptoms.setCycleLength(mensurationSymptomsRecordBeans.getMensSettings().getCycleLength());
                        if (mensurationSymptomsRecordBeans.getMensSettings().getActive() != null) {
                            Boolean active = mensurationSymptomsRecordBeans.getMensSettings().getActive();
                            Intrinsics.checkNotNullExpressionValue(active, "fitnessRecordBean.mensSettings.active");
                            entityFemaleWellnessSymptoms.setActive(active.booleanValue());
                        }
                        entityFemaleWellnessSymptoms.setCycleStartDate(mensurationSymptomsRecordBeans.getMensSettings().getCycleStartDate());
                        entityFemaleWellnessSymptoms.setPeriodLength(mensurationSymptomsRecordBeans.getMensSettings().getPeriodLength());
                        entityFemaleWellnessSymptoms.setPmsLength(mensurationSymptomsRecordBeans.getMensSettings().getPmsLength());
                    }
                    FemaleWellnessRepository.Companion.getInstance(ActivityPairingViewModel.this.getContext()).insert(entityFemaleWellnessSymptoms);
                }
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5246a;
    }

    public final int getFIFTEEN_MIN() {
        return this.e;
    }

    public final void getFavouritePlacesFromServer() {
        NavigationApi.getFavouritePlaces(new CoveApiListener<GetFavouritePlacesRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getFavouritePlacesFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String tag = ActivityPairingViewModel.this.getTAG();
                LogHelper.d(tag, "getFavouritePlaces favPlacesData onError " + new Gson().toJson(coveApiErrorModel));
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetFavouritePlacesRes getFavouritePlacesRes) {
                String tag = ActivityPairingViewModel.this.getTAG();
                LogHelper.d(tag, "getFavouritePlaces favPlacesData onSuccess " + new Gson().toJson(getFavouritePlacesRes));
                ActivityPairingViewModel.this.writeFavouritePlacesOnBand(getFavouritePlacesRes);
            }
        });
    }

    public final void getFeatureMappingRemoteConfig(@NotNull final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.e
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                ActivityPairingViewModel.p(exc);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.c
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ActivityPairingViewModel.q(FirebaseRemoteConfig.this, this, context, task);
            }
        });
    }

    public final void getSensAISessionsListFromServer(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final String macAddress) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        TraqConfigApi.getSessionHeaderListFromServer(0, AppUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), AppUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), PreferenceManager.getInstance().getUserDeviceID(), new CoveApiListener<GetActivitySessionHeaderResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getSensAISessionsListFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetActivitySessionHeaderResponse getActivitySessionHeaderResponse) {
                if (getActivitySessionHeaderResponse != null) {
                    fitnessActivitySessions fitnessactivitysessions = getActivitySessionHeaderResponse.requestData;
                    List<PostActivitySessionDataRequest> fitnessActivitySessions = fitnessactivitysessions != null ? fitnessactivitysessions.getFitnessActivitySessions() : null;
                    if (fitnessActivitySessions != null) {
                        for (PostActivitySessionDataRequest postActivitySessionDataRequest : fitnessActivitySessions) {
                            try {
                                if (m.equals(postActivitySessionDataRequest.getSessionType(), ActivityPairingViewModel.this.getContext().getString(R.string.cricket__batting), true)) {
                                    if (SensAIBeamDBRead.getInstance(ActivityPairingViewModel.this.getContext()).isSessionIdExists(postActivitySessionDataRequest.getFwSessionId(), macAddress) != 1) {
                                        final ArrayList arrayList = new ArrayList();
                                        String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
                                        Intrinsics.checkNotNullExpressionValue(userDeviceID, "getInstance().userDeviceID");
                                        int parseInt = Integer.parseInt(userDeviceID);
                                        String clientRefId = postActivitySessionDataRequest.getClientRefId();
                                        ActivityType activityType = ActivityType.CRICKET_BATTING;
                                        final String str = macAddress;
                                        final ActivityPairingViewModel activityPairingViewModel = ActivityPairingViewModel.this;
                                        TraqConfigApi.getSessionOverallDataFromServer(0, parseInt, clientRefId, activityType, new CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getSensAISessionsListFromServer$1$onSuccess$1

                                            @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getSensAISessionsListFromServer$1$onSuccess$1$onSuccess$1", f = "ActivityPairingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                                            /* loaded from: classes5.dex */
                                            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                public final /* synthetic */ List<SensAIActivitySummary> $battingList;
                                                public final /* synthetic */ String $macAddress;
                                                public final /* synthetic */ PostActivitySessionHeaderResponse $p0;
                                                public int label;
                                                public final /* synthetic */ ActivityPairingViewModel this$0;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                public a(PostActivitySessionHeaderResponse postActivitySessionHeaderResponse, String str, ActivityPairingViewModel activityPairingViewModel, List<SensAIActivitySummary> list, Continuation<? super a> continuation) {
                                                    super(2, continuation);
                                                    this.$p0 = postActivitySessionHeaderResponse;
                                                    this.$macAddress = str;
                                                    this.this$0 = activityPairingViewModel;
                                                    this.$battingList = list;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                @NotNull
                                                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                                    return new a(this.$p0, this.$macAddress, this.this$0, this.$battingList, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                @Nullable
                                                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                                                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                @Nullable
                                                public final Object invokeSuspend(@NotNull Object obj) {
                                                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                                                    if (this.label == 0) {
                                                        ResultKt.throwOnFailure(obj);
                                                        SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                                                        SensAIActivitySummaryDetails sensAIActivitySummaryDetails = new SensAIActivitySummaryDetails();
                                                        sensAIActivitySummary.setSessionId(this.$p0.requestData.getFwSessionId());
                                                        sensAIActivitySummary.setMacAddress(this.$macAddress);
                                                        sensAIActivitySummary.setClientRefID(this.$p0.requestData.getClientRefId());
                                                        PayUtils payUtils = PayUtils.INSTANCE;
                                                        String sessionStartDate = this.$p0.requestData.getSessionStartDate();
                                                        Intrinsics.checkNotNullExpressionValue(sessionStartDate, "p0.requestData.sessionStartDate");
                                                        Long timeStampFromDate = payUtils.getTimeStampFromDate(sessionStartDate);
                                                        Intrinsics.checkNotNull(timeStampFromDate);
                                                        sensAIActivitySummary.setTimestamp(timeStampFromDate);
                                                        sensAIActivitySummary.setActivityType(1);
                                                        sensAIActivitySummary.setDurationSec(this.$p0.requestData.getTotalActivityDuration());
                                                        Integer totalSteps = this.$p0.requestData.getActivityData().getTotalSteps();
                                                        Intrinsics.checkNotNullExpressionValue(totalSteps, "p0.requestData.activityData.totalSteps");
                                                        sensAIActivitySummary.setTotalSteps(totalSteps.intValue());
                                                        sensAIActivitySummary.setTotalCalories(this.$p0.requestData.getActivityData().getTotalCalories().floatValue());
                                                        if (m.equals(this.$p0.requestData.getActivityData().getPlayingHand(), this.this$0.getContext().getString(R.string.right), true)) {
                                                            sensAIActivitySummary.setHand(0);
                                                        } else {
                                                            sensAIActivitySummary.setHand(1);
                                                        }
                                                        if (this.$p0.requestData.getActivityData().getTargets() != null) {
                                                            for (Target target : this.$p0.requestData.getActivityData().getTargets()) {
                                                                if (target.getBaseUnit().equals("MINUTES")) {
                                                                    sensAIActivitySummary.setGoalType(1);
                                                                    Integer value = target.getValue();
                                                                    Intrinsics.checkNotNullExpressionValue(value, "targetItem.value");
                                                                    sensAIActivitySummary.setTargetGoalValue(value.intValue());
                                                                } else {
                                                                    sensAIActivitySummary.setGoalType(2);
                                                                    Integer value2 = target.getValue();
                                                                    Intrinsics.checkNotNullExpressionValue(value2, "targetItem.value");
                                                                    sensAIActivitySummary.setTargetGoalValue(value2.intValue());
                                                                }
                                                            }
                                                        }
                                                        Integer targetAchievedPct = this.$p0.requestData.getActivityData().getTargetAchievedPct();
                                                        Intrinsics.checkNotNullExpressionValue(targetAchievedPct, "p0.requestData.activityData.targetAchievedPct");
                                                        sensAIActivitySummary.setGoalCompletionPct(targetAchievedPct.intValue());
                                                        Integer maxHr = this.$p0.requestData.getActivityData().getMaxHr();
                                                        Intrinsics.checkNotNullExpressionValue(maxHr, "p0.requestData.activityData.maxHr");
                                                        sensAIActivitySummary.setMaxHR(maxHr.intValue());
                                                        Integer avgHr = this.$p0.requestData.getActivityData().getAvgHr();
                                                        Intrinsics.checkNotNullExpressionValue(avgHr, "p0.requestData.activityData.avgHr");
                                                        sensAIActivitySummary.setAvgHR(avgHr.intValue());
                                                        Integer totalSwings = this.$p0.requestData.getActivityData().getTotalSwings();
                                                        Intrinsics.checkNotNullExpressionValue(totalSwings, "p0.requestData.activityData.totalSwings");
                                                        sensAIActivitySummary.setTotalSwings(totalSwings.intValue());
                                                        Integer totalHits = this.$p0.requestData.getActivityData().getTotalHits();
                                                        Intrinsics.checkNotNullExpressionValue(totalHits, "p0.requestData.activityData.totalHits");
                                                        sensAIActivitySummary.setPlayed(totalHits.intValue());
                                                        Integer hitPercentage = this.$p0.requestData.getActivityData().getHitPercentage();
                                                        Intrinsics.checkNotNullExpressionValue(hitPercentage, "p0.requestData.activityData.hitPercentage");
                                                        sensAIActivitySummary.setHitPct(hitPercentage.intValue());
                                                        Double maxHandSpeed = this.$p0.requestData.getActivityData().getMaxHandSpeed();
                                                        Intrinsics.checkNotNullExpressionValue(maxHandSpeed, "p0.requestData.activityData.maxHandSpeed");
                                                        sensAIActivitySummary.setMaxHandSpeed(kotlin.math.c.roundToInt(maxHandSpeed.doubleValue()));
                                                        Double avgHandSpeed = this.$p0.requestData.getActivityData().getAvgHandSpeed();
                                                        Intrinsics.checkNotNullExpressionValue(avgHandSpeed, "p0.requestData.activityData.avgHandSpeed");
                                                        sensAIActivitySummary.setAvgHandSpeed(kotlin.math.c.roundToInt(avgHandSpeed.doubleValue()));
                                                        sensAIActivitySummary.setSavedServer(true);
                                                        sensAIActivitySummary.setDataAggregateSaved(!RepositoryUtils.isDateToday(WorkoutUtils.INSTANCE.getDateFromTimeStamp(sensAIActivitySummary.getTimestamp(), "yyyy-MM-dd")));
                                                        sensAIActivitySummary.setAddToCompare(false);
                                                        sensAIActivitySummaryDetails.setSessionId(this.$p0.requestData.getFwSessionId());
                                                        sensAIActivitySummaryDetails.setMacAddress(this.$macAddress);
                                                        sensAIActivitySummaryDetails.setActivityType(1);
                                                        PayUtils payUtils2 = PayUtils.INSTANCE;
                                                        String sessionStartDate2 = this.$p0.requestData.getSessionStartDate();
                                                        Intrinsics.checkNotNullExpressionValue(sessionStartDate2, "p0.requestData.sessionStartDate");
                                                        Long timeStampFromDate2 = payUtils2.getTimeStampFromDate(sessionStartDate2);
                                                        Intrinsics.checkNotNull(timeStampFromDate2);
                                                        sensAIActivitySummaryDetails.setUnixTimeStamp(timeStampFromDate2);
                                                        if (this.$p0.requestData.getFeedback() != null) {
                                                            sensAIActivitySummaryDetails.setFeedbackSaved(true);
                                                        }
                                                        if (this.$p0.requestData.getActivityData().getTraqActivityLogs() != null) {
                                                            sensAIActivitySummaryDetails.setDistance((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getDistanceValues());
                                                            sensAIActivitySummaryDetails.setHr((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHrValues());
                                                            sensAIActivitySummaryDetails.setHandSpeed((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHandSpeedValues());
                                                            sensAIActivitySummaryDetails.setSteps((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getStepValues());
                                                            ArrayList<Integer> arrayList = new ArrayList<>();
                                                            for (Boolean hitItem : this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHitValues()) {
                                                                Intrinsics.checkNotNullExpressionValue(hitItem, "hitItem");
                                                                if (hitItem.booleanValue()) {
                                                                    arrayList.add(Boxing.boxInt(1));
                                                                } else {
                                                                    arrayList.add(Boxing.boxInt(0));
                                                                }
                                                            }
                                                            sensAIActivitySummaryDetails.setHitOrMiss(arrayList);
                                                            ArrayList<Integer> arrayList2 = new ArrayList<>();
                                                            for (Float calorieItem : this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getCalorieValues()) {
                                                                Intrinsics.checkNotNullExpressionValue(calorieItem, "calorieItem");
                                                                arrayList2.add(Boxing.boxInt(kotlin.math.c.roundToInt(calorieItem.floatValue())));
                                                            }
                                                            sensAIActivitySummaryDetails.setCalories(arrayList2);
                                                        }
                                                        sensAIActivitySummaryDetails.setDetailsDataNum(sensAIActivitySummaryDetails.getCalories().size());
                                                        this.$battingList.add(sensAIActivitySummary);
                                                        SensAIBeamDBWrite.getInstance(this.this$0.getContext()).insertActivitySummaryList(this.$battingList);
                                                        SensAIBeamDBWrite.getInstance(this.this$0.getContext()).insertActivitySummaryDetails(sensAIActivitySummaryDetails);
                                                        return Unit.INSTANCE;
                                                    }
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                            }

                                            /* loaded from: classes5.dex */
                                            public static final class b extends Lambda implements Function1<Throwable, Unit> {
                                                public final /* synthetic */ ActivityPairingViewModel this$0;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                public b(ActivityPairingViewModel activityPairingViewModel) {
                                                    super(1);
                                                    this.this$0 = activityPairingViewModel;
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                                    invoke2(th);
                                                    return Unit.INSTANCE;
                                                }

                                                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                                                public final void invoke2(@Nullable Throwable th) {
                                                    LogHelper.d(this.this$0.getTAG(), "Inserted Successfully");
                                                }
                                            }

                                            @Override // com.coveiot.coveaccess.CoveApiListener
                                            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                                            }

                                            @Override // com.coveiot.coveaccess.CoveApiListener
                                            public void onSuccess(@Nullable PostActivitySessionHeaderResponse postActivitySessionHeaderResponse) {
                                                Job e;
                                                if ((postActivitySessionHeaderResponse != null ? postActivitySessionHeaderResponse.requestData : null) != null) {
                                                    e = kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(postActivitySessionHeaderResponse, str, activityPairingViewModel, arrayList, null), 2, null);
                                                    e.invokeOnCompletion(new b(activityPairingViewModel));
                                                }
                                            }
                                        });
                                    }
                                } else if (m.equals(postActivitySessionDataRequest.getSessionType(), ActivityPairingViewModel.this.getContext().getString(R.string.cricket__bowling), true) && SensAIBeamDBRead.getInstance(ActivityPairingViewModel.this.getContext()).isSessionIdExists(postActivitySessionDataRequest.getFwSessionId(), macAddress) != 1) {
                                    final ArrayList arrayList2 = new ArrayList();
                                    String userDeviceID2 = PreferenceManager.getInstance().getUserDeviceID();
                                    Intrinsics.checkNotNullExpressionValue(userDeviceID2, "getInstance().userDeviceID");
                                    int parseInt2 = Integer.parseInt(userDeviceID2);
                                    String clientRefId2 = postActivitySessionDataRequest.getClientRefId();
                                    ActivityType activityType2 = ActivityType.CRICKET_BOWLING;
                                    final String str2 = macAddress;
                                    final ActivityPairingViewModel activityPairingViewModel2 = ActivityPairingViewModel.this;
                                    TraqConfigApi.getSessionOverallDataFromServer(0, parseInt2, clientRefId2, activityType2, new CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getSensAISessionsListFromServer$1$onSuccess$2

                                        @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getSensAISessionsListFromServer$1$onSuccess$2$onSuccess$1", f = "ActivityPairingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                                        /* loaded from: classes5.dex */
                                        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            public final /* synthetic */ List<SensAIActivitySummary> $bowlingList;
                                            public final /* synthetic */ String $macAddress;
                                            public final /* synthetic */ PostActivitySessionHeaderResponse $p0;
                                            public int label;
                                            public final /* synthetic */ ActivityPairingViewModel this$0;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            public a(PostActivitySessionHeaderResponse postActivitySessionHeaderResponse, String str, ActivityPairingViewModel activityPairingViewModel, List<SensAIActivitySummary> list, Continuation<? super a> continuation) {
                                                super(2, continuation);
                                                this.$p0 = postActivitySessionHeaderResponse;
                                                this.$macAddress = str;
                                                this.this$0 = activityPairingViewModel;
                                                this.$bowlingList = list;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            @NotNull
                                            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                                return new a(this.$p0, this.$macAddress, this.this$0, this.$bowlingList, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            @Nullable
                                            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                                                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            @Nullable
                                            public final Object invokeSuspend(@NotNull Object obj) {
                                                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                                                if (this.label == 0) {
                                                    ResultKt.throwOnFailure(obj);
                                                    SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                                                    SensAIActivitySummaryDetails sensAIActivitySummaryDetails = new SensAIActivitySummaryDetails();
                                                    sensAIActivitySummary.setSessionId(this.$p0.requestData.getFwSessionId());
                                                    sensAIActivitySummary.setMacAddress(this.$macAddress);
                                                    sensAIActivitySummary.setClientRefID(this.$p0.requestData.getClientRefId());
                                                    PayUtils payUtils = PayUtils.INSTANCE;
                                                    String sessionStartDate = this.$p0.requestData.getSessionStartDate();
                                                    Intrinsics.checkNotNullExpressionValue(sessionStartDate, "p0.requestData.sessionStartDate");
                                                    Long timeStampFromDate = payUtils.getTimeStampFromDate(sessionStartDate);
                                                    Intrinsics.checkNotNull(timeStampFromDate);
                                                    sensAIActivitySummary.setTimestamp(timeStampFromDate);
                                                    sensAIActivitySummary.setActivityType(2);
                                                    sensAIActivitySummary.setDurationSec(this.$p0.requestData.getTotalActivityDuration());
                                                    Integer totalSteps = this.$p0.requestData.getActivityData().getTotalSteps();
                                                    Intrinsics.checkNotNullExpressionValue(totalSteps, "p0.requestData.activityData.totalSteps");
                                                    sensAIActivitySummary.setTotalSteps(totalSteps.intValue());
                                                    sensAIActivitySummary.setTotalCalories(this.$p0.requestData.getActivityData().getTotalCalories().floatValue());
                                                    if (m.equals(this.$p0.requestData.getActivityData().getPlayingHand(), this.this$0.getContext().getString(R.string.right), true)) {
                                                        sensAIActivitySummary.setHand(0);
                                                    } else {
                                                        sensAIActivitySummary.setHand(1);
                                                    }
                                                    if (this.$p0.requestData.getActivityData().getTargets() != null) {
                                                        for (Target target : this.$p0.requestData.getActivityData().getTargets()) {
                                                            if (target.getBaseUnit().equals("MINUTES")) {
                                                                sensAIActivitySummary.setGoalType(1);
                                                                Integer value = target.getValue();
                                                                Intrinsics.checkNotNullExpressionValue(value, "targetItem.value");
                                                                sensAIActivitySummary.setTargetGoalValue(value.intValue());
                                                            } else {
                                                                sensAIActivitySummary.setGoalType(2);
                                                                Integer value2 = target.getValue();
                                                                Intrinsics.checkNotNullExpressionValue(value2, "targetItem.value");
                                                                sensAIActivitySummary.setTargetGoalValue(value2.intValue());
                                                            }
                                                        }
                                                    }
                                                    Integer targetAchievedPct = this.$p0.requestData.getActivityData().getTargetAchievedPct();
                                                    Intrinsics.checkNotNullExpressionValue(targetAchievedPct, "p0.requestData.activityData.targetAchievedPct");
                                                    sensAIActivitySummary.setGoalCompletionPct(targetAchievedPct.intValue());
                                                    Integer maxHr = this.$p0.requestData.getActivityData().getMaxHr();
                                                    Intrinsics.checkNotNullExpressionValue(maxHr, "p0.requestData.activityData.maxHr");
                                                    sensAIActivitySummary.setMaxHR(maxHr.intValue());
                                                    Integer avgHr = this.$p0.requestData.getActivityData().getAvgHr();
                                                    Intrinsics.checkNotNullExpressionValue(avgHr, "p0.requestData.activityData.avgHr");
                                                    sensAIActivitySummary.setAvgHR(avgHr.intValue());
                                                    Integer totalBallsBowled = this.$p0.requestData.getActivityData().getTotalBallsBowled();
                                                    Intrinsics.checkNotNullExpressionValue(totalBallsBowled, "p0.requestData.activityData.totalBallsBowled");
                                                    sensAIActivitySummary.setTotalBallsBowled(totalBallsBowled.intValue());
                                                    if (m.equals(this.$p0.requestData.getActivityData().getBowlingType(), AppConstants.BOWLING_FAST.getValue(), true)) {
                                                        sensAIActivitySummary.setBowlingType(0);
                                                    } else if (!m.equals(this.$p0.requestData.getActivityData().getBowlingType(), AppConstants.BOWLING_MEDIUM.getValue(), true) && !m.equals(this.$p0.requestData.getActivityData().getBowlingType(), AppConstants.BOWLING_MEDIUM_PACE.getValue(), true)) {
                                                        if (m.equals(this.$p0.requestData.getActivityData().getBowlingType(), AppConstants.BOWLING_SPIN.getValue(), true)) {
                                                            sensAIActivitySummary.setBowlingType(2);
                                                        }
                                                    } else {
                                                        sensAIActivitySummary.setBowlingType(1);
                                                    }
                                                    Double maxHandSpeed = this.$p0.requestData.getActivityData().getMaxHandSpeed();
                                                    Intrinsics.checkNotNullExpressionValue(maxHandSpeed, "p0.requestData.activityData.maxHandSpeed");
                                                    sensAIActivitySummary.setMaxArmSpeed(kotlin.math.c.roundToInt(maxHandSpeed.doubleValue()));
                                                    Double avgHandSpeed = this.$p0.requestData.getActivityData().getAvgHandSpeed();
                                                    Intrinsics.checkNotNullExpressionValue(avgHandSpeed, "p0.requestData.activityData.avgHandSpeed");
                                                    sensAIActivitySummary.setAvgArmSpeed(kotlin.math.c.roundToInt(avgHandSpeed.doubleValue()));
                                                    Double minHandSpeed = this.$p0.requestData.getActivityData().getMinHandSpeed();
                                                    Intrinsics.checkNotNullExpressionValue(minHandSpeed, "p0.requestData.activityData.minHandSpeed");
                                                    sensAIActivitySummary.setMinArmSpeed(kotlin.math.c.roundToInt(minHandSpeed.doubleValue()));
                                                    sensAIActivitySummary.setSavedServer(true);
                                                    sensAIActivitySummary.setDataAggregateSaved(!RepositoryUtils.isDateToday(WorkoutUtils.INSTANCE.getDateFromTimeStamp(sensAIActivitySummary.getTimestamp(), "yyyy-MM-dd")));
                                                    sensAIActivitySummary.setAddToCompare(false);
                                                    sensAIActivitySummaryDetails.setSessionId(this.$p0.requestData.getFwSessionId());
                                                    sensAIActivitySummaryDetails.setMacAddress(this.$macAddress);
                                                    sensAIActivitySummaryDetails.setActivityType(2);
                                                    PayUtils payUtils2 = PayUtils.INSTANCE;
                                                    String sessionStartDate2 = this.$p0.requestData.getSessionStartDate();
                                                    Intrinsics.checkNotNullExpressionValue(sessionStartDate2, "p0.requestData.sessionStartDate");
                                                    Long timeStampFromDate2 = payUtils2.getTimeStampFromDate(sessionStartDate2);
                                                    Intrinsics.checkNotNull(timeStampFromDate2);
                                                    sensAIActivitySummaryDetails.setUnixTimeStamp(timeStampFromDate2);
                                                    if (this.$p0.requestData.getFeedback() != null) {
                                                        sensAIActivitySummaryDetails.setFeedbackSaved(true);
                                                    }
                                                    if (this.$p0.requestData.getActivityData().getTraqActivityLogs() != null) {
                                                        sensAIActivitySummaryDetails.setDistance((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getDistanceValues());
                                                        sensAIActivitySummaryDetails.setHr((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHrValues());
                                                        sensAIActivitySummaryDetails.setHandSpeed((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHandSpeedValues());
                                                        sensAIActivitySummaryDetails.setSteps((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getStepValues());
                                                        ArrayList<Integer> arrayList = new ArrayList<>();
                                                        for (Float calorieItem : this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getCalorieValues()) {
                                                            Intrinsics.checkNotNullExpressionValue(calorieItem, "calorieItem");
                                                            arrayList.add(Boxing.boxInt(kotlin.math.c.roundToInt(calorieItem.floatValue())));
                                                        }
                                                        sensAIActivitySummaryDetails.setCalories(arrayList);
                                                        sensAIActivitySummaryDetails.setDetailsDataNum(sensAIActivitySummaryDetails.getCalories().size());
                                                    }
                                                    this.$bowlingList.add(sensAIActivitySummary);
                                                    SensAIBeamDBWrite.getInstance(this.this$0.getContext()).insertActivitySummaryList(this.$bowlingList);
                                                    SensAIBeamDBWrite.getInstance(this.this$0.getContext()).insertActivitySummaryDetails(sensAIActivitySummaryDetails);
                                                    return Unit.INSTANCE;
                                                }
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                        }

                                        /* loaded from: classes5.dex */
                                        public static final class b extends Lambda implements Function1<Throwable, Unit> {
                                            public final /* synthetic */ ActivityPairingViewModel this$0;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            public b(ActivityPairingViewModel activityPairingViewModel) {
                                                super(1);
                                                this.this$0 = activityPairingViewModel;
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                                invoke2(th);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                                            public final void invoke2(@Nullable Throwable th) {
                                                LogHelper.d(this.this$0.getTAG(), "Inserted Successfully");
                                            }
                                        }

                                        @Override // com.coveiot.coveaccess.CoveApiListener
                                        public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                                        }

                                        @Override // com.coveiot.coveaccess.CoveApiListener
                                        public void onSuccess(@Nullable PostActivitySessionHeaderResponse postActivitySessionHeaderResponse) {
                                            Job e;
                                            if ((postActivitySessionHeaderResponse != null ? postActivitySessionHeaderResponse.requestData : null) != null) {
                                                e = kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(postActivitySessionHeaderResponse, str2, activityPairingViewModel2, arrayList2, null), 2, null);
                                                e.invokeOnCompletion(new b(activityPairingViewModel2));
                                            }
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                LogHelper.d(ActivityPairingViewModel.this.getTAG(), e.toString());
                            }
                        }
                    }
                }
            }
        });
    }

    public final void getSportsSettingFromServer(@NotNull final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
        Intrinsics.checkNotNullExpressionValue(userDeviceID, "getInstance().userDeviceID");
        CoveSports.getSportsUserPref(Integer.valueOf(Integer.parseInt(userDeviceID)), new CoveApiListener<SportsUserPrefResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getSportsSettingFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                if (coveApiErrorModel == null || coveApiErrorModel.getCode() != 404) {
                    return;
                }
                SportsPreference.Companion.clearPreferences(context);
                SportNotificationControlRequest sportsNotificationRequest = new SportNotificationControlRequest.Builder(false).build();
                BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
                Intrinsics.checkNotNullExpressionValue(sportsNotificationRequest, "sportsNotificationRequest");
                bleApi.setUserSettings(sportsNotificationRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getSportsSettingFromServer$1$onError$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                    }
                });
            }

            /* JADX WARN: Type inference failed for: r2v18, types: [T, java.text.SimpleDateFormat] */
            /* JADX WARN: Type inference failed for: r2v8, types: [com.coveiot.coveaccess.sports.Cricket, T] */
            /* JADX WARN: Type inference failed for: r7v29, types: [T, java.lang.Integer] */
            /* JADX WARN: Type inference failed for: r8v34, types: [T, java.lang.Integer] */
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SportsUserPrefResponse sportsUserPrefResponse) {
                if (sportsUserPrefResponse != null && sportsUserPrefResponse.getData() != null) {
                    SportsPrefResData data = sportsUserPrefResponse.getData();
                    Intrinsics.checkNotNull(data);
                    if (data.getCricket() != null) {
                        Ref.ObjectRef objectRef = new Ref.ObjectRef();
                        objectRef.element = sportsUserPrefResponse.getData().getCricket();
                        Soccer soccer = sportsUserPrefResponse.getData().getSoccer();
                        SportsPreferenceModel sportsPreferenceModel = new SportsPreferenceModel();
                        sportsPreferenceModel.setInterval(2);
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                        T t = objectRef.element;
                        if (t != 0) {
                            Boolean enable = ((Cricket) t).getEnable();
                            Intrinsics.checkNotNullExpressionValue(enable, "cricketRes.enable");
                            if (enable.booleanValue()) {
                                sportsPreferenceModel.setSport(SportsType.Cricket.getSport());
                            }
                            if (!AppUtils.isEmpty(((Cricket) objectRef.element).getMatchUpdates())) {
                                for (MatchUpdateRequest matchUpdateRequest : ((Cricket) objectRef.element).getMatchUpdates()) {
                                    objectRef2.element = matchUpdateRequest.getMatchId();
                                }
                            }
                        }
                        if (soccer != null) {
                            Boolean enable2 = soccer.getEnable();
                            Intrinsics.checkNotNullExpressionValue(enable2, "soccerRes.enable");
                            if (enable2.booleanValue()) {
                                sportsPreferenceModel.setSport(SportsType.Football.getSport());
                            }
                            if (!AppUtils.isEmpty(soccer.getMatchUpdates())) {
                                for (MatchUpdateRequest matchUpdateRequest2 : soccer.getMatchUpdates()) {
                                    objectRef3.element = matchUpdateRequest2.getMatchId();
                                }
                            }
                        }
                        sportsPreferenceModel.setEnable(sportsUserPrefResponse.getData().getPushToDevice());
                        SportsPreference.Companion.saveSportsNotification(context, sportsPreferenceModel);
                        if (sportsUserPrefResponse.getData().getCricket() != null) {
                            SportsPrefResData data2 = sportsUserPrefResponse.getData();
                            Intrinsics.checkNotNull(data2);
                            Cricket cricket = data2.getCricket();
                            Intrinsics.checkNotNull(cricket);
                            if (cricket.getUpdateConfigs() != null) {
                                for (UpdateConfigsBean updateConfigsBean : ((Cricket) objectRef.element).getUpdateConfigs()) {
                                    Intrinsics.checkNotNull(updateConfigsBean);
                                    String matchFormat = updateConfigsBean.getMatchFormat();
                                    Intrinsics.checkNotNull(matchFormat);
                                    if (matchFormat.equals("T20")) {
                                        SportsPreference.Companion.saveT20Interval(context, updateConfigsBean.getInterval().intValue() / 60);
                                    } else {
                                        String matchFormat2 = updateConfigsBean.getMatchFormat();
                                        Intrinsics.checkNotNull(matchFormat2);
                                        if (matchFormat2.equals("ODI")) {
                                            SportsPreference.Companion.saveODIInterval(context, updateConfigsBean.getInterval().intValue() / 60);
                                        }
                                    }
                                }
                            }
                        }
                        if (sportsUserPrefResponse.getData().getVibrationConfig() != null) {
                            SportsPreference.Companion companion = SportsPreference.Companion;
                            Context context2 = context;
                            Boolean active = sportsUserPrefResponse.getData().getVibrationConfig().getActive();
                            Intrinsics.checkNotNullExpressionValue(active, "res.data.vibrationConfig.active");
                            companion.saveVibrationEnabled(context2, active.booleanValue());
                        }
                        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                        objectRef4.element = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date date = new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(5, 1);
                        ((SimpleDateFormat) objectRef4.element).format(date);
                        ((SimpleDateFormat) objectRef4.element).format(calendar.getTime());
                        SportsPreference.Companion companion2 = SportsPreference.Companion;
                        companion2.saveFTUOpened(context, true);
                        Context context3 = context;
                        Boolean pushToDevice = sportsUserPrefResponse.getData().getPushToDevice();
                        Intrinsics.checkNotNullExpressionValue(pushToDevice, "res.data.pushToDevice");
                        companion2.saveNotificationEnabled(context3, pushToDevice.booleanValue());
                        Context context4 = context;
                        Intrinsics.checkNotNull(context4, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                        TodaysMatchFragmentViewModel todaysMatchFragmentViewModel = (TodaysMatchFragmentViewModel) ViewModelProviders.of((FragmentActivity) context4).get(TodaysMatchFragmentViewModel.class);
                        Context context5 = context;
                        Intrinsics.checkNotNull(context5, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                        TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel = (TodaysMatchFragmentSoccerViewModel) ViewModelProviders.of((FragmentActivity) context5).get(TodaysMatchFragmentSoccerViewModel.class);
                        if (sportsPreferenceModel.getSport() != null) {
                            String sport = sportsPreferenceModel.getSport();
                            Boolean valueOf = sport != null ? Boolean.valueOf(sport.equals(SportsType.Cricket)) : null;
                            Intrinsics.checkNotNull(valueOf);
                            if (!valueOf.booleanValue()) {
                                if (m.equals$default(sportsPreferenceModel.getSport(), SportsType.Football.name(), false, 2, null)) {
                                    SportsApiClient.Companion.resetSportsApi(((FragmentActivity) context).getApplicationContext(), SportType.SOCCER);
                                    todaysMatchFragmentSoccerViewModel.setMatchListListener(new ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$2(objectRef3, context, objectRef4, todaysMatchFragmentSoccerViewModel, this));
                                    if (objectRef3.element != 0) {
                                        todaysMatchFragmentSoccerViewModel.getAccessToken();
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                        }
                        SportsApiClient.Companion.resetSportsApi(((FragmentActivity) context).getApplicationContext(), SportType.CRICKET);
                        todaysMatchFragmentViewModel.setMatchListListener(new ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$1(objectRef2, context, objectRef, objectRef4, todaysMatchFragmentViewModel, this));
                        if (objectRef2.element != 0) {
                            todaysMatchFragmentViewModel.getAccessToken();
                            return;
                        }
                        return;
                    }
                }
                SportNotificationControlRequest sportsNotificationRequest = new SportNotificationControlRequest.Builder(false).build();
                BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
                Intrinsics.checkNotNullExpressionValue(sportsNotificationRequest, "sportsNotificationRequest");
                bleApi.setUserSettings(sportsNotificationRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$3
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                    }
                });
            }
        });
    }

    @NotNull
    public final Triple<String, String, Integer> getStartEndtimeInterval(@NotNull AutoStress autoStress) {
        int i;
        Intrinsics.checkNotNullParameter(autoStress, "autoStress");
        String startTime = autoStress.getAlert().getStartTime();
        Intrinsics.checkNotNullExpressionValue(startTime, "autoStress.alert.startTime");
        String endTime = autoStress.getAlert().getEndTime();
        Intrinsics.checkNotNullExpressionValue(endTime, "autoStress.alert.endTime");
        int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
        int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(1));
        int parseInt3 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
        int parseInt4 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(1));
        if (autoStress.getAlert().getInterval().length() == 8) {
            String interval = autoStress.getAlert().getInterval();
            Intrinsics.checkNotNullExpressionValue(interval, "autoStress.alert.interval");
            int parseInt5 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            String interval2 = autoStress.getAlert().getInterval();
            Intrinsics.checkNotNullExpressionValue(interval2, "autoStress.alert.interval");
            int parseInt6 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval2, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            String interval3 = autoStress.getAlert().getInterval();
            Intrinsics.checkNotNullExpressionValue(interval3, "autoStress.alert.interval");
            i = (parseInt5 * 60) + parseInt6 + Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval3, new String[]{":"}, false, 0, 6, (Object) null).get(2));
        } else {
            i = 15;
        }
        this.f = parseInt;
        this.g = parseInt2;
        if (parseInt > 12) {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.pm);
            this.f = this.f - 12;
        } else if (parseInt == 12) {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.pm);
            this.f = this.f;
        } else {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.am);
        }
        if (this.f == 0) {
            this.f = 12;
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.g)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(this.j);
        String sb2 = sb.toString();
        this.h = parseInt3;
        this.i = parseInt4;
        if (parseInt3 > 12) {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.pm);
            this.h = this.h - 12;
        } else if (parseInt3 == 12) {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.pm);
            this.h = this.h;
        } else {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.am);
        }
        if (this.h == 0) {
            this.h = 12;
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.h)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.i)}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(this.j);
        return new Triple<>(sb2, sb3.toString(), Integer.valueOf(i));
    }

    public final String getTAG() {
        return this.c;
    }

    public final void getUserDeviceSettings() {
        CoveUserDeviceSettings.getAllUserDeviceSettings(new CoveApiListener<GetAllUserDeviceSettingRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getUserDeviceSettings$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                boolean z = false;
                if (coveApiErrorModel != null && coveApiErrorModel.getCode() == 404) {
                    z = true;
                }
                if (z) {
                    if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isAutoStressSettingsSupported()) {
                        ActivityPairingViewModel.this.writeAutoStressDefaultSettingsToWatch();
                    }
                    if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported()) {
                        ActivityPairingViewModel.this.D();
                    }
                }
                LogHelper.i(ActivityPairingViewModel.this.getTAG(), "getAllUserDeviceSettings onError error");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetAllUserDeviceSettingRes getAllUserDeviceSettingRes) {
                if (getAllUserDeviceSettingRes != null) {
                    if (getAllUserDeviceSettingRes.getStatus() != null) {
                        String tag = ActivityPairingViewModel.this.getTAG();
                        LogHelper.i(tag, "getAllUserDeviceSettings onSuccess " + getAllUserDeviceSettingRes.getStatus());
                    }
                    if (getAllUserDeviceSettingRes.getDataBean() != null) {
                        if (getAllUserDeviceSettingRes.getDataBean().getCustomMessages() != null) {
                            if (getAllUserDeviceSettingRes.getDataBean().getCustomMessages().getNudgeMessages() != null) {
                                boolean isActive = getAllUserDeviceSettingRes.getDataBean().getCustomMessages().getNudgeMessages().isActive();
                                KHPerformancePreferenceManager.getInstance(ActivityPairingViewModel.this.getContext()).setIsPerformanceBasedNotificationEnabled(Boolean.valueOf(isActive));
                                KHPerformancePreferenceManager.getInstance(ActivityPairingViewModel.this.getContext()).setIsNudge1Enabled(Boolean.valueOf(isActive));
                                KHPerformancePreferenceManager.getInstance(ActivityPairingViewModel.this.getContext()).setIsNudge2Enabled(Boolean.valueOf(isActive));
                                KHPerformancePreferenceManager.getInstance(ActivityPairingViewModel.this.getContext()).setIsNudge3Enabled(Boolean.valueOf(isActive));
                                KHPerformancePreferenceManager.getInstance(ActivityPairingViewModel.this.getContext()).setSleepScoreNudgeEnabled(Boolean.valueOf(isActive));
                                KHPerformancePreferenceManager.getInstance(ActivityPairingViewModel.this.getContext()).setEnergyScoreNudgeEnabled(Boolean.valueOf(isActive));
                                KHPerformancePreferenceManager.getInstance(ActivityPairingViewModel.this.getContext()).setStressNudgeEnabled(Boolean.valueOf(isActive));
                            }
                            if (getAllUserDeviceSettingRes.getDataBean().getCustomMessages().getNudgeMessageVibration() != null) {
                                KHPerformancePreferenceManager.getInstance(ActivityPairingViewModel.this.getContext()).setIsPerformanceBasedNotificationVibrationEnabled(Boolean.valueOf(getAllUserDeviceSettingRes.getDataBean().getCustomMessages().getNudgeMessageVibration().isActive()));
                            }
                        }
                        if (getAllUserDeviceSettingRes.getDataBean().getSedentaryAlertUserDeviceSettingsBean() != null) {
                            ActivityPairingViewModel activityPairingViewModel = ActivityPairingViewModel.this;
                            SedentaryAlertUserDeviceSettingsBean sedentaryAlertUserDeviceSettingsBean = getAllUserDeviceSettingRes.getDataBean().getSedentaryAlertUserDeviceSettingsBean();
                            Intrinsics.checkNotNullExpressionValue(sedentaryAlertUserDeviceSettingsBean, "`object`.dataBean.sedent…ertUserDeviceSettingsBean");
                            activityPairingViewModel.saveSedentaryRemindersToPref(sedentaryAlertUserDeviceSettingsBean);
                        }
                        if (getAllUserDeviceSettingRes.getDataBean().getAmbientSoundSettings() != null) {
                            getAllUserDeviceSettingRes.getDataBean().getAmbientSoundSettings().isActive();
                            if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                ActivityPairingViewModel.this.setAmbientSoundSettingsToWatch(getAllUserDeviceSettingRes.getDataBean().getAmbientSoundSettings().isActive());
                            }
                        }
                        if (getAllUserDeviceSettingRes.getDataBean().getCustomReminders() != null) {
                            ActivityPairingViewModel activityPairingViewModel2 = ActivityPairingViewModel.this;
                            GetAllUserDeviceSettingRes.DataBean.CustomReminders customReminders = getAllUserDeviceSettingRes.getDataBean().getCustomReminders();
                            Intrinsics.checkNotNullExpressionValue(customReminders, "`object`.dataBean.customReminders");
                            activityPairingViewModel2.saveCustomRemindersToPref(customReminders);
                        }
                        if (getAllUserDeviceSettingRes.getDataBean().getAutoStress() != null) {
                            if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isAutoStressSettingsSupported()) {
                                ActivityPairingViewModel activityPairingViewModel3 = ActivityPairingViewModel.this;
                                AutoStress autoStress = getAllUserDeviceSettingRes.getDataBean().getAutoStress();
                                Intrinsics.checkNotNullExpressionValue(autoStress, "`object`.dataBean.autoStress");
                                activityPairingViewModel3.sentAutoStressIntervalToBle(autoStress);
                            }
                        } else if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isAutoStressSettingsSupported()) {
                            ActivityPairingViewModel.this.writeAutoStressDefaultSettingsToWatch();
                        }
                        ActivityPairingViewModel.this.setAutoRecognitionToWatch(getAllUserDeviceSettingRes.getDataBean().getAutoRecognize());
                        if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported()) {
                            ActivityPairingViewModel.this.w(getAllUserDeviceSettingRes.getDataBean().getRespiratoryRateSettings());
                        }
                        if (getAllUserDeviceSettingRes.getDataBean().getAutoSPO2Settings() != null) {
                            getAllUserDeviceSettingRes.getDataBean().getAutoSPO2Settings().isActive();
                            if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isAutoSPO2SettingsSupported()) {
                                ActivityPairingViewModel.this.setAutoSpo2SettingsToWatch(getAllUserDeviceSettingRes.getDataBean().getAutoSPO2Settings().isActive());
                            }
                        }
                        if (getAllUserDeviceSettingRes.getDataBean().getSmartAlertSettings() == null || !BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isSmartAlertsSupported()) {
                            return;
                        }
                        SmartAlertSettings smartAlertSettings = getAllUserDeviceSettingRes.getDataBean().getSmartAlertSettings();
                        Intrinsics.checkNotNullExpressionValue(smartAlertSettings, "`object`.dataBean.smartAlertSettings");
                        SettingsSyncHelper.Companion.getInstance(ActivityPairingViewModel.this.getContext()).sendSmartAlertSettingsToWatch(smartAlertSettings, false);
                        return;
                    }
                    if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isAutoStressSettingsSupported()) {
                        ActivityPairingViewModel.this.writeAutoStressDefaultSettingsToWatch();
                    }
                    if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported()) {
                        ActivityPairingViewModel.this.D();
                        return;
                    }
                    return;
                }
                if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isAutoStressSettingsSupported()) {
                    ActivityPairingViewModel.this.writeAutoStressDefaultSettingsToWatch();
                }
                if (BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported()) {
                    ActivityPairingViewModel.this.D();
                }
            }
        });
    }

    public final void getUserSettings(@NotNull final BleDeviceInfo bleDeviceInfo) {
        Intrinsics.checkNotNullParameter(bleDeviceInfo, "bleDeviceInfo");
        CoveUserAppSettings.getAllUserAppSettings(new CoveApiListener<GetAllUserAppSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getUserSettings$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNull(coveApiErrorModel);
                if (coveApiErrorModel.getCode() == 404) {
                    ActivityPairingViewModel.this.y();
                    ActivityPairingViewModel.this.E(bleDeviceInfo);
                    ActivityPairingViewModel.this.x();
                    return;
                }
                ActivityPairingViewModel.this.getViewmodelListener().onDataFailure(AppConstants.EMPTY_STRING.getValue());
            }

            /* JADX WARN: Code restructure failed: missing block: B:99:0x04e1, code lost:
                if (r0.isBESDevice(r23.f5256a.getContext()) == false) goto L96;
             */
            @Override // com.coveiot.coveaccess.CoveApiListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onSuccess(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.userappsetting.GetAllUserAppSettingsRes r24) {
                /*
                    Method dump skipped, instructions count: 2169
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getUserSettings$1.onSuccess(com.coveiot.coveaccess.userappsetting.GetAllUserAppSettingsRes):void");
            }
        });
    }

    @NotNull
    public final ViewModelListener getViewmodelListener() {
        ViewModelListener viewModelListener = this.viewmodelListener;
        if (viewModelListener != null) {
            return viewModelListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewmodelListener");
        return null;
    }

    public final WorldClockPrefData h(ArrayList<WorldClockPrefData> arrayList, int i) {
        Iterator<WorldClockPrefData> it = arrayList.iterator();
        while (it.hasNext()) {
            WorldClockPrefData next = it.next();
            Integer id = next.getId();
            if (id != null && id.intValue() == i) {
                return next;
            }
        }
        return null;
    }

    public final WorldClockPrefData i(ArrayList<WorldClockPrefData> arrayList, String str) {
        Iterator<WorldClockPrefData> it = arrayList.iterator();
        while (it.hasNext()) {
            WorldClockPrefData next = it.next();
            if (Intrinsics.areEqual(next.getName(), str)) {
                return next;
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final ArrayList<WorldClockPrefData> j(String str) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        new OkHttpClient().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getCountryListFromApi$2
            @Override // okhttp3.Callback
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
            }

            /* JADX WARN: Type inference failed for: r3v6, types: [T, java.util.ArrayList] */
            @Override // okhttp3.Callback
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Gson gson = new Gson();
                ResponseBody body = response.body();
                Intrinsics.checkNotNull(body);
                String string = body.string();
                Ref.ObjectRef<ArrayList<WorldClockPrefData>> objectRef2 = objectRef;
                Object fromJson = gson.fromJson(string, (Class<Object>) WorldClockPrefData[].class);
                Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(jsonString…ockPrefData>::class.java)");
                List list = ArraysKt___ArraysKt.toList((Object[]) fromJson);
                Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covepreferences.data.WorldClockPrefData>");
                objectRef2.element = (ArrayList) list;
            }
        });
        return (ArrayList) objectRef.element;
    }

    public final void k(String str, final Function1<? super ArrayList<WorldClockPrefData>, Unit> function1) {
        new OkHttpClient().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getCountryListFromApi$1
            @Override // okhttp3.Callback
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // okhttp3.Callback
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Gson gson = new Gson();
                ResponseBody body = response.body();
                Intrinsics.checkNotNull(body);
                Object fromJson = gson.fromJson(body.string(), (Class<Object>) WorldClockPrefData[].class);
                Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(jsonString…ockPrefData>::class.java)");
                List list = ArraysKt___ArraysKt.toList((Object[]) fromJson);
                Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covepreferences.data.WorldClockPrefData>");
                function1.invoke((ArrayList) list);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, com.google.firebase.remoteconfig.FirebaseRemoteConfig, java.lang.Object] */
    public final ArrayList<WorldClockPrefData> l() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        ?? firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseRemoteConfig, "getInstance()");
        objectRef2.element = firebaseRemoteConfig;
        FirebaseRemoteConfigSettings build = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0L).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…s(0)\n            .build()");
        ((FirebaseRemoteConfig) objectRef2.element).setConfigSettingsAsync(build);
        ((FirebaseRemoteConfig) objectRef2.element).fetchAndActivate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.d
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ActivityPairingViewModel.n(Ref.ObjectRef.this, this, objectRef2, task);
            }
        });
        return (ArrayList) objectRef.element;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final void m(final Function1<? super ArrayList<WorldClockPrefData>, Unit> function1) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        final FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseRemoteConfig, "getInstance()");
        FirebaseRemoteConfigSettings build = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0L).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…s(0)\n            .build()");
        firebaseRemoteConfig.setConfigSettingsAsync(build);
        firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.a
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ActivityPairingViewModel.o(ActivityPairingViewModel.this, firebaseRemoteConfig, function1, objectRef, task);
            }
        });
    }

    public final void pullSmartAlertAppConfigFromServer() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new b(null), 2, null);
    }

    public final AutoActivityDetectionData s(AutoRecognize autoRecognize) {
        AutoActivityDetectionData autoActivityDetectionData = new AutoActivityDetectionData();
        if (autoRecognize != null) {
            List<AutoRecognizeActivity> activities = autoRecognize.getActivities();
            if (!(activities == null || activities.isEmpty())) {
                int size = autoRecognize.getActivities().size();
                for (int i = 0; i < size; i++) {
                    String type = autoRecognize.getActivities().get(i).getType();
                    if (!(type == null || type.length() == 0)) {
                        String code = autoRecognize.getActivities().get(i).getCode();
                        if (!(code == null || code.length() == 0)) {
                            byte[] activities2 = autoActivityDetectionData.getActivities();
                            WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
                            String code2 = autoRecognize.getActivities().get(i).getCode();
                            Intrinsics.checkNotNullExpressionValue(code2, "autoRecognize.activities[i].code");
                            activities2[workoutUtils.getAutoActivityDetectionFWOrderForAnActivity(code2)] = 1;
                        }
                    }
                }
            }
            String weekDays = autoRecognize.getWeekDays();
            if (!(weekDays == null || weekDays.length() == 0)) {
                autoActivityDetectionData.setSundayEnabled(Boolean.valueOf(autoRecognize.getWeekDays().charAt(0) != '-'));
                autoActivityDetectionData.setMondayEnabled(Boolean.valueOf(autoRecognize.getWeekDays().charAt(1) != '-'));
                autoActivityDetectionData.setTuesdayEnabled(Boolean.valueOf(autoRecognize.getWeekDays().charAt(2) != '-'));
                autoActivityDetectionData.setWednesdayEnabled(Boolean.valueOf(autoRecognize.getWeekDays().charAt(3) != '-'));
                autoActivityDetectionData.setThursdayEnabled(Boolean.valueOf(autoRecognize.getWeekDays().charAt(4) != '-'));
                autoActivityDetectionData.setFridayEnabled(Boolean.valueOf(autoRecognize.getWeekDays().charAt(5) != '-'));
                autoActivityDetectionData.setSaturdayEnabled(Boolean.valueOf(autoRecognize.getWeekDays().charAt(6) != '-'));
            }
            if (autoRecognize.getSchedule() != null) {
                autoRecognize.getSchedule().isActive();
                autoActivityDetectionData.setSmartModeEnabled(Boolean.valueOf(autoRecognize.getSchedule().isActive()));
                List<AutoRecognizeSlot> slots = autoRecognize.getSchedule().getSlots();
                if (!(slots == null || slots.isEmpty())) {
                    ArrayList arrayList = new ArrayList();
                    for (AutoRecognizeSlot autoRecognizeSlot : autoRecognize.getSchedule().getSlots()) {
                        String startTime = autoRecognizeSlot.getStartTime();
                        if (!(startTime == null || startTime.length() == 0)) {
                            String endTime = autoRecognizeSlot.getEndTime();
                            if (!(endTime == null || endTime.length() == 0)) {
                                String startTime2 = autoRecognizeSlot.getStartTime();
                                Intrinsics.checkNotNullExpressionValue(startTime2, "s.startTime");
                                String startTime3 = autoRecognizeSlot.getStartTime();
                                Intrinsics.checkNotNullExpressionValue(startTime3, "s.startTime");
                                String endTime2 = autoRecognizeSlot.getEndTime();
                                Intrinsics.checkNotNullExpressionValue(endTime2, "s.endTime");
                                String endTime3 = autoRecognizeSlot.getEndTime();
                                Intrinsics.checkNotNullExpressionValue(endTime3, "s.endTime");
                                arrayList.add(new AutoActivityDetectionData.Period(Integer.valueOf((Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime2, new String[]{":"}, false, 0, 6, (Object) null).get(0)) * 60) + Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime3, new String[]{":"}, false, 0, 6, (Object) null).get(1))), Integer.valueOf((Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime2, new String[]{":"}, false, 0, 6, (Object) null).get(0)) * 60) + Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime3, new String[]{":"}, false, 0, 6, (Object) null).get(1)))));
                            }
                        }
                    }
                    autoActivityDetectionData.setPeriods(arrayList);
                }
            }
        }
        return autoActivityDetectionData;
    }

    public final void saveCustomRemindersToPref(@NotNull GetAllUserDeviceSettingRes.DataBean.CustomReminders customReminders) {
        Intrinsics.checkNotNullParameter(customReminders, "customReminders");
        if (BleApiManager.getInstance(this.f5246a) != null && BleApiManager.getInstance(this.f5246a).getBleApi() != null && BleApiManager.getInstance(this.f5246a).getBleApi().getDeviceSupportedFeatures().isCustomRemindersSupported()) {
            ReminderHelper.Companion.getInstance(this.f5246a).saveCustomReminderFromServer(customReminders, new ResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$saveCustomRemindersToPref$1
                @Override // com.coveiot.android.customreminders.listeners.ResultListener
                public void onError(@Nullable String str) {
                }

                @Override // com.coveiot.android.customreminders.listeners.ResultListener
                public void onSuccess() {
                }
            });
        } else if (DeviceUtils.Companion.isTouchELXDevice(this.f5246a)) {
            sendEventRemindersToBand(customReminders);
        }
    }

    public final void saveSedentaryRemindersToPref(@NotNull SedentaryAlertUserDeviceSettingsBean sedentaryAlertUserDeviceSettingsBean) {
        int i;
        Intrinsics.checkNotNullParameter(sedentaryAlertUserDeviceSettingsBean, "sedentaryAlertUserDeviceSettingsBean");
        SedentaryReminderData sedentaryReminderData = SedentaryReminderData.getInstance();
        Intrinsics.checkNotNullExpressionValue(sedentaryReminderData, "getInstance()");
        sedentaryReminderData.setAlarm_on_off(sedentaryAlertUserDeviceSettingsBean.isActive());
        String startTime = sedentaryAlertUserDeviceSettingsBean.getStartTime();
        Intrinsics.checkNotNullExpressionValue(startTime, "sedentaryAlertUserDeviceSettingsBean.startTime");
        String endTime = sedentaryAlertUserDeviceSettingsBean.getEndTime();
        Intrinsics.checkNotNullExpressionValue(endTime, "sedentaryAlertUserDeviceSettingsBean.endTime");
        sedentaryReminderData.setBeggining_time_hour(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0)));
        sedentaryReminderData.setBeggining_time_minutes(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(1)));
        sedentaryReminderData.setEnd_time_hour(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(0)));
        sedentaryReminderData.setEnd_time_minutes(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(1)));
        if (sedentaryAlertUserDeviceSettingsBean.getInterval().length() == 8) {
            String interval = sedentaryAlertUserDeviceSettingsBean.getInterval();
            Intrinsics.checkNotNullExpressionValue(interval, "sedentaryAlertUserDeviceSettingsBean.interval");
            int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            String interval2 = sedentaryAlertUserDeviceSettingsBean.getInterval();
            Intrinsics.checkNotNullExpressionValue(interval2, "sedentaryAlertUserDeviceSettingsBean.interval");
            int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval2, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            String interval3 = sedentaryAlertUserDeviceSettingsBean.getInterval();
            Intrinsics.checkNotNullExpressionValue(interval3, "sedentaryAlertUserDeviceSettingsBean.interval");
            i = (parseInt * 60) + parseInt2 + Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval3, new String[]{":"}, false, 0, 6, (Object) null).get(2));
        } else {
            i = 15;
        }
        sedentaryReminderData.setRemind_in(i);
        this.f = sedentaryReminderData.getBeggining_time_hour();
        this.g = sedentaryReminderData.getBeggining_time_minutes();
        int i2 = this.f;
        if (i2 > 12) {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.pm);
            this.f = this.f - 12;
        } else if (i2 == 12) {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.pm);
            this.f = this.f;
        } else {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.am);
        }
        if (this.f == 0) {
            this.f = 12;
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.g)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(this.j);
        String sb2 = sb.toString();
        this.h = sedentaryReminderData.getEnd_time_hour();
        this.i = sedentaryReminderData.getEnd_time_minutes();
        int i3 = this.h;
        if (i3 > 12) {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.pm);
            this.h = this.h - 12;
        } else if (i3 == 12) {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.pm);
            this.h = this.h;
        } else {
            this.j = ' ' + this.f5246a.getResources().getString(R.string.am);
        }
        if (this.h == 0) {
            this.h = 12;
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.h)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.i)}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(this.j);
        String sb4 = sb3.toString();
        sedentaryReminderData.setSunday(!Character.valueOf(sedentaryAlertUserDeviceSettingsBean.getWeekDays().charAt(0)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
        sedentaryReminderData.setMonday(!Character.valueOf(sedentaryAlertUserDeviceSettingsBean.getWeekDays().charAt(1)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
        sedentaryReminderData.setTuesday(!Character.valueOf(sedentaryAlertUserDeviceSettingsBean.getWeekDays().charAt(2)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
        sedentaryReminderData.setWednesday(!Character.valueOf(sedentaryAlertUserDeviceSettingsBean.getWeekDays().charAt(3)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
        sedentaryReminderData.setThrusday(!Character.valueOf(sedentaryAlertUserDeviceSettingsBean.getWeekDays().charAt(4)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
        sedentaryReminderData.setFriday(!Character.valueOf(sedentaryAlertUserDeviceSettingsBean.getWeekDays().charAt(5)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
        sedentaryReminderData.setSaturday(!Character.valueOf(sedentaryAlertUserDeviceSettingsBean.getWeekDays().charAt(6)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
        UserDataManager.getInstance(this.f5246a).saveSedentaryReminderSettings(sedentaryReminderData);
        sendSedentaryReminderToBle(i, sedentaryReminderData.getAlarm_on_off(), sb2, sb4, new boolean[]{sedentaryReminderData.isSunday(), sedentaryReminderData.isMonday(), sedentaryReminderData.isTuesday(), sedentaryReminderData.isWednesday(), sedentaryReminderData.isThrusday(), sedentaryReminderData.isFriday(), sedentaryReminderData.isSaturday()});
    }

    public final void sendEventRemindersToBand(@NotNull GetAllUserDeviceSettingRes.DataBean.CustomReminders customReminders) {
        Intrinsics.checkNotNullParameter(customReminders, "customReminders");
        ArrayList arrayList = new ArrayList();
        for (GetAllUserDeviceSettingRes.DataBean.CustomReminders.CustomReminderListItem customReminderListItem : customReminders.getCustomReminderListItems()) {
            if (customReminderListItem.getType().equals(com.coveiot.android.customreminders.ReminderType.OTHERS.name())) {
                arrayList.add(customReminderListItem);
            }
        }
        if (!arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Object obj = arrayList.get(i);
                Intrinsics.checkNotNullExpressionValue(obj, "eventRemindersList[i]");
                arrayList2.add(EventReminderHelper.Companion.getInstance(this.f5246a).convertServerDataToEventReminder((GetAllUserDeviceSettingRes.DataBean.CustomReminders.CustomReminderListItem) obj, i));
            }
            if (!arrayList2.isEmpty()) {
                EventReminderHelper.Companion.getInstance(this.f5246a).sendEventRemindersToBand(arrayList2, false);
            }
        }
    }

    public final void sendMultipleAlarmsToBle(@NotNull List<AlarmInfo> alarmInfos, @NotNull final GetAllUserAppSettingsRes settingsResponse) {
        Intrinsics.checkNotNullParameter(alarmInfos, "alarmInfos");
        Intrinsics.checkNotNullParameter(settingsResponse, "settingsResponse");
        if (alarmInfos.size() > 0) {
            try {
                ArrayList arrayList = new ArrayList();
                int size = alarmInfos.size();
                for (int i = 0; i < size; i++) {
                    AlarmInfo alarmInfo = alarmInfos.get(i);
                    SetVibrationAlarmRequest setAlarmReq = new SetVibrationAlarmRequest.Builder().setAlarmId(alarmInfo.getAlarmId()).setHour(alarmInfo.getHour()).setMinute(alarmInfo.getMinute()).setEnabled(alarmInfo.isAlarmOn()).setEventName(alarmInfo.getEventName()).setRepeatEnabled(t(alarmInfo)).setSnoozeDuration(alarmInfo.getSnoozeDuration()).setSnoozeRepeatTimes(alarmInfo.getSnoozeRepeatCount()).setSundayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[0]).setMondayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[1]).setTuesdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[2]).setWednesdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[3]).setThursdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[4]).setFridayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[5]).setSaturdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[6]).build();
                    Intrinsics.checkNotNullExpressionValue(setAlarmReq, "setAlarmReq");
                    arrayList.add(setAlarmReq);
                }
                SetVibrationAlarmListRequest setAlarmReqList = new SetVibrationAlarmListRequest.Builder().setSedentaryReminderList(arrayList).build();
                BleApi bleApi = BleApiManager.getInstance(this.f5246a).getBleApi();
                Intrinsics.checkNotNullExpressionValue(setAlarmReqList, "setAlarmReqList");
                bleApi.setUserSettings(setAlarmReqList, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$sendMultipleAlarmsToBle$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        this.getViewmodelListener().onDataFailure(AppConstants.EMPTY_STRING.getValue());
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        if (GetAllUserAppSettingsRes.this.getAutoHr() != null) {
                            ActivityPairingViewModel activityPairingViewModel = this;
                            Context context = activityPairingViewModel.getContext();
                            GetAllUserAppSettingsRes.AutoHr autoHr = GetAllUserAppSettingsRes.this.getAutoHr();
                            Intrinsics.checkNotNullExpressionValue(autoHr, "settingsResponse.autoHr");
                            activityPairingViewModel.setAutoHrFromResponse(context, autoHr);
                            return;
                        }
                        this.getViewmodelListener().onSuccess();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void sendSedentaryReminderToBle(int i, boolean z, @NotNull String startTime, @NotNull String endTime1, @NotNull boolean[] isDaySelected) {
        List emptyList;
        List emptyList2;
        List emptyList3;
        List emptyList4;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(endTime1, "endTime1");
        Intrinsics.checkNotNullParameter(isDaySelected, "isDaySelected");
        String str = (String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        String str2 = (String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(1);
        List<String> split = new Regex(":").split(str, 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() == 0) {
                    z5 = true;
                    continue;
                } else {
                    z5 = false;
                    continue;
                }
                if (!z5) {
                    emptyList = CollectionsKt___CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        String str3 = ((String[]) emptyList.toArray(new String[0]))[0];
        List<String> split2 = new Regex(":").split(str, 0);
        if (!split2.isEmpty()) {
            ListIterator<String> listIterator2 = split2.listIterator(split2.size());
            while (listIterator2.hasPrevious()) {
                if (listIterator2.previous().length() == 0) {
                    z4 = true;
                    continue;
                } else {
                    z4 = false;
                    continue;
                }
                if (!z4) {
                    emptyList2 = CollectionsKt___CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList2 = CollectionsKt__CollectionsKt.emptyList();
        String str4 = ((String[]) emptyList2.toArray(new String[0]))[1];
        String str5 = (String) StringsKt__StringsKt.split$default((CharSequence) endTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        String str6 = (String) StringsKt__StringsKt.split$default((CharSequence) endTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(1);
        List<String> split3 = new Regex(":").split(str5, 0);
        if (!split3.isEmpty()) {
            ListIterator<String> listIterator3 = split3.listIterator(split3.size());
            while (listIterator3.hasPrevious()) {
                if (listIterator3.previous().length() == 0) {
                    z3 = true;
                    continue;
                } else {
                    z3 = false;
                    continue;
                }
                if (!z3) {
                    emptyList3 = CollectionsKt___CollectionsKt.take(split3, listIterator3.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList3 = CollectionsKt__CollectionsKt.emptyList();
        String str7 = ((String[]) emptyList3.toArray(new String[0]))[0];
        List<String> split4 = new Regex(":").split(str5, 0);
        if (!split4.isEmpty()) {
            ListIterator<String> listIterator4 = split4.listIterator(split4.size());
            while (listIterator4.hasPrevious()) {
                if (listIterator4.previous().length() == 0) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (!z2) {
                    emptyList4 = CollectionsKt___CollectionsKt.take(split4, listIterator4.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList4 = CollectionsKt__CollectionsKt.emptyList();
        String str8 = ((String[]) emptyList4.toArray(new String[0]))[1];
        int parseInt = Integer.parseInt(str3);
        int parseInt2 = Integer.parseInt(str7);
        if (m.equals(str2, "am", true)) {
            if (parseInt == 12) {
                parseInt = 0;
            }
        } else if (m.equals(str2, "pm", true) && parseInt != 12) {
            parseInt += 12;
        }
        if (m.equals(str6, "am", true)) {
            if (parseInt2 == 12) {
                parseInt2 = 0;
            }
        } else if (m.equals(str6, "pm", true) && parseInt2 != 12) {
            parseInt2 += 12;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, parseInt);
        calendar.set(12, Integer.parseInt(str4));
        long timeInMillis = calendar.getTimeInMillis();
        calendar.set(11, parseInt2);
        calendar.set(12, Integer.parseInt(str8));
        if (Math.abs(calendar.getTimeInMillis() - timeInMillis) < this.e) {
            Context context = this.f5246a;
            Toast.makeText(context, context.getString(R.string.minimum_value_sedentary_reminder), 0).show();
            return;
        }
        SetReminderRequest sedentaryReminderReq = new SetReminderRequest.Builder().setStartHour1(parseInt).setEndHour1(parseInt2).setStartMin1(Integer.parseInt(str4)).setEndMin1(Integer.parseInt(str8)).setReminderInterval(i).setEnabled(z).setRepeatEnabled(u(isDaySelected)).setSundayEnabled(isDaySelected[0]).setMondayEnabled(isDaySelected[1]).setTuesdayEnabled(isDaySelected[2]).setWednesdayEnabled(isDaySelected[3]).setThursdayEnabled(isDaySelected[4]).setFridayEnabled(isDaySelected[5]).setSaturdayEnabled(isDaySelected[6]).setLeastStep(PayUtils.INSTANCE.getLeastStepForSedentary(this.f5246a)).setReminderType(ReminderType.SEDENTARY_REMINDER).build();
        BleApi bleApi = BleApiManager.getInstance(this.f5246a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(sedentaryReminderReq, "sedentaryReminderReq");
        bleApi.setUserSettings(sedentaryReminderReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$sendSedentaryReminderToBle$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
                Intrinsics.checkNotNullParameter(bleBaseResponse, "bleBaseResponse");
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x01ae, code lost:
        if (r4 == 12) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void sendSedentarytoBleApi(int r18, boolean r19, @org.jetbrains.annotations.NotNull java.lang.String r20, @org.jetbrains.annotations.NotNull java.lang.String r21, @org.jetbrains.annotations.NotNull final com.coveiot.coveaccess.userappsetting.GetAllUserAppSettingsRes r22) {
        /*
            Method dump skipped, instructions count: 558
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel.sendSedentarytoBleApi(int, boolean, java.lang.String, java.lang.String, com.coveiot.coveaccess.userappsetting.GetAllUserAppSettingsRes):void");
    }

    public final void sendShortcutListToWatch(@NotNull List<String> activeDisplays) {
        Intrinsics.checkNotNullParameter(activeDisplays, "activeDisplays");
        ArrayList arrayList = new ArrayList();
        for (String str : activeDisplays) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isIDODevice(this.f5246a)) {
                arrayList.add(Integer.valueOf(PayUtils.INSTANCE.getShortcutsMenuValueByEnumIDO(str, this.f5246a)));
            } else if (companion.isTouchELXDevice(this.f5246a)) {
                arrayList.add(Integer.valueOf(PayUtils.INSTANCE.getShortcutsMenuValueByEnumTouch(str, this.f5246a)));
            }
        }
        BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(new SetShortcutMenuListRequest(arrayList), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$sendShortcutListToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    public final void sendToBle(@NotNull final List<ScheduleInfo> scheduleInfos) {
        Intrinsics.checkNotNullParameter(scheduleInfos, "scheduleInfos");
        if (this.b < scheduleInfos.size()) {
            ScheduleInfo scheduleInfo = scheduleInfos.get(this.b);
            SetScheduleRequest setScheduleReq = new SetScheduleRequest.Builder().setScheduleId(scheduleInfo.getScheduleId()).setTitle(scheduleInfo.getTitle()).setContent(scheduleInfo.getContent()).setAdvance(scheduleInfo.getAdvance()).setDay(scheduleInfo.getDay()).setMonth(scheduleInfo.getMonth()).setYear(scheduleInfo.getYear()).setHour(scheduleInfo.getHour()).setMinute(scheduleInfo.getMinute()).build();
            BleApi bleApi = BleApiManager.getInstance(this.f5246a).getBleApi();
            Intrinsics.checkNotNullExpressionValue(setScheduleReq, "setScheduleReq");
            bleApi.setUserSettings(setScheduleReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$sendToBle$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    Toast.makeText(ActivityPairingViewModel.this.getContext(), ActivityPairingViewModel.this.getContext().getString(R.string.setting_couldnot_save), 0).show();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    int i;
                    int i2;
                    int unused;
                    Intrinsics.checkNotNullParameter(response, "response");
                    ActivityPairingViewModel activityPairingViewModel = ActivityPairingViewModel.this;
                    i = activityPairingViewModel.b;
                    activityPairingViewModel.b = i + 1;
                    i2 = ActivityPairingViewModel.this.b;
                    if (i2 >= scheduleInfos.size()) {
                        unused = ActivityPairingViewModel.this.b;
                        scheduleInfos.size();
                        return;
                    }
                    ActivityPairingViewModel.this.sendToBle(scheduleInfos);
                }
            });
        }
    }

    public final void sentAutoStressIntervalToBle(@NotNull AutoStress autoStress) {
        List emptyList;
        List emptyList2;
        List emptyList3;
        List emptyList4;
        StressTimeIntervalRequest build;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        Intrinsics.checkNotNullParameter(autoStress, "autoStress");
        String first = getStartEndtimeInterval(autoStress).getFirst();
        String second = getStartEndtimeInterval(autoStress).getSecond();
        int intValue = getStartEndtimeInterval(autoStress).getThird().intValue();
        Intrinsics.checkNotNull(first);
        String str = (String) StringsKt__StringsKt.split$default((CharSequence) first, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        String str2 = (String) StringsKt__StringsKt.split$default((CharSequence) first, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(1);
        List<String> split = new Regex(":").split(str, 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() == 0) {
                    z4 = true;
                    continue;
                } else {
                    z4 = false;
                    continue;
                }
                if (!z4) {
                    emptyList = CollectionsKt___CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        String str3 = ((String[]) emptyList.toArray(new String[0]))[0];
        List<String> split2 = new Regex(":").split(str, 0);
        if (!split2.isEmpty()) {
            ListIterator<String> listIterator2 = split2.listIterator(split2.size());
            while (listIterator2.hasPrevious()) {
                if (listIterator2.previous().length() == 0) {
                    z3 = true;
                    continue;
                } else {
                    z3 = false;
                    continue;
                }
                if (!z3) {
                    emptyList2 = CollectionsKt___CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList2 = CollectionsKt__CollectionsKt.emptyList();
        String str4 = ((String[]) emptyList2.toArray(new String[0]))[1];
        Intrinsics.checkNotNull(second);
        String str5 = (String) StringsKt__StringsKt.split$default((CharSequence) second, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        String str6 = (String) StringsKt__StringsKt.split$default((CharSequence) second, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(1);
        List<String> split3 = new Regex(":").split(str5, 0);
        if (!split3.isEmpty()) {
            ListIterator<String> listIterator3 = split3.listIterator(split3.size());
            while (listIterator3.hasPrevious()) {
                if (listIterator3.previous().length() == 0) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (!z2) {
                    emptyList3 = CollectionsKt___CollectionsKt.take(split3, listIterator3.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList3 = CollectionsKt__CollectionsKt.emptyList();
        String str7 = ((String[]) emptyList3.toArray(new String[0]))[0];
        List<String> split4 = new Regex(":").split(str5, 0);
        if (!split4.isEmpty()) {
            ListIterator<String> listIterator4 = split4.listIterator(split4.size());
            while (listIterator4.hasPrevious()) {
                if (listIterator4.previous().length() == 0) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (!z) {
                    emptyList4 = CollectionsKt___CollectionsKt.take(split4, listIterator4.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList4 = CollectionsKt__CollectionsKt.emptyList();
        String str8 = ((String[]) emptyList4.toArray(new String[0]))[1];
        int parseInt = Integer.parseInt(str3);
        int parseInt2 = Integer.parseInt(str7);
        if (m.equals(str2, "am", true)) {
            if (parseInt == 12) {
                parseInt = 0;
            }
        } else if (m.equals(str2, "pm", true) && parseInt != 12) {
            parseInt += 12;
        }
        if (m.equals(str6, "am", true)) {
            if (parseInt2 == 12) {
                parseInt2 = 0;
            }
        } else if (m.equals(str6, "pm", true) && parseInt2 != 12) {
            parseInt2 += 12;
        }
        final AutoStressSettingsData autoStressSettingsData = AutoStressSettingsData.getInstance();
        Intrinsics.checkNotNullExpressionValue(autoStressSettingsData, "getInstance()");
        autoStressSettingsData.setBeggining_time_hour(parseInt);
        autoStressSettingsData.setBeggining_time_minutes(Integer.parseInt(str4));
        autoStressSettingsData.setEnd_time_hour(parseInt2);
        autoStressSettingsData.setEnd_time_minutes(Integer.parseInt(str8));
        autoStressSettingsData.setRemind_in(intValue);
        if (autoStress.getInterval() != null) {
            PayUtils payUtils = PayUtils.INSTANCE;
            String interval = autoStress.getInterval();
            Intrinsics.checkNotNull(interval);
            autoStressSettingsData.setMeasuringInterval((int) TimeUnit.MILLISECONDS.toMinutes(payUtils.convertIntervalToMilliSeconds(interval)));
        }
        Boolean active = autoStress.getActive();
        Intrinsics.checkNotNullExpressionValue(active, "autoStress.active");
        autoStressSettingsData.setAutoStress(active.booleanValue());
        if (!AppUtils.isEmpty(autoStress.getAlert().getWeekDays())) {
            autoStressSettingsData.setSunday(!Character.valueOf(autoStress.getAlert().getWeekDays().charAt(0)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            autoStressSettingsData.setMonday(!Character.valueOf(autoStress.getAlert().getWeekDays().charAt(1)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            autoStressSettingsData.setTuesday(!Character.valueOf(autoStress.getAlert().getWeekDays().charAt(2)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            autoStressSettingsData.setWednesday(!Character.valueOf(autoStress.getAlert().getWeekDays().charAt(3)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            autoStressSettingsData.setThrusday(!Character.valueOf(autoStress.getAlert().getWeekDays().charAt(4)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            autoStressSettingsData.setFriday(!Character.valueOf(autoStress.getAlert().getWeekDays().charAt(5)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
            autoStressSettingsData.setSaturday(!Character.valueOf(autoStress.getAlert().getWeekDays().charAt(6)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
        }
        Boolean active2 = autoStress.getAlert().getActive();
        Intrinsics.checkNotNullExpressionValue(active2, "autoStress.alert.active");
        autoStressSettingsData.setHighStressReminder(active2.booleanValue());
        autoStressSettingsData.setWeeks(autoStress.getAlert().getWeekDays());
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isCZDevice(this.f5246a) && !companion.isCADevice(this.f5246a) && !companion.isCYDevice(this.f5246a) && !companion.isPS1Device(this.f5246a) && !companion.isBESDevice(this.f5246a)) {
            build = new StressTimeIntervalRequest.Builder(autoStressSettingsData.getRemind_in()).setStartHour(autoStressSettingsData.getBeggining_time_hour()).setEndHour(autoStressSettingsData.getEnd_time_hour()).setStartMin(autoStressSettingsData.getBeggining_time_minutes()).setEndMin(autoStressSettingsData.getEnd_time_minutes()).setEnabled(autoStressSettingsData.getAutoStress()).setHighThresholdRemindEnabled(autoStressSettingsData.isHighStressReminder()).setSundayEnabled(autoStressSettingsData.isSunday()).setMondayEnabled(autoStressSettingsData.isMonday()).setTuesdayEnabled(autoStressSettingsData.isTuesday()).setWednesdayEnabled(autoStressSettingsData.isWednesday()).setThursdayEnabled(autoStressSettingsData.isThrusday()).setFridayEnabled(autoStressSettingsData.isFriday()).setSaturdayEnabled(autoStressSettingsData.isSaturday()).build();
        } else {
            StressConfiguration autoStressConfig = SessionManager.getInstance(this.f5246a).getAutoStressConfig();
            PayUtils payUtils2 = PayUtils.INSTANCE;
            String baselineTime = autoStressConfig.getStress().getBaselineTime();
            Intrinsics.checkNotNullExpressionValue(baselineTime, "stressConfiguration.stress.baselineTime");
            long convertIntervalToMilliSeconds = payUtils2.convertIntervalToMilliSeconds(baselineTime);
            String readinessTime = autoStressConfig.getStress().getReadinessTime();
            Intrinsics.checkNotNullExpressionValue(readinessTime, "stressConfiguration.stress.readinessTime");
            long convertIntervalToMilliSeconds2 = payUtils2.convertIntervalToMilliSeconds(readinessTime);
            StressTimeIntervalRequest.Builder enabled = new StressTimeIntervalRequest.Builder(autoStressSettingsData.getMeasuringInterval()).setEnabled(true);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            StressTimeIntervalRequest.Builder highStressThreshold = enabled.setBaseLineTime((int) timeUnit.toMinutes(convertIntervalToMilliSeconds)).setReadinessTime((int) timeUnit.toMinutes(convertIntervalToMilliSeconds2)).setHighStressThreshold(autoStressConfig.getStress().getAlert().getThreshold().intValue());
            Integer maxAllowed = autoStressConfig.getStress().getAlert().getMaxAllowed();
            Intrinsics.checkNotNullExpressionValue(maxAllowed, "stressConfiguration.stress.alert.maxAllowed");
            build = highStressThreshold.setLimit(maxAllowed.intValue()).build();
        }
        BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$sentAutoStressIntervalToBle$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityPairingViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ActivityPairingViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveAutoStressSettings(autoStressSettingsData);
                BleApiManager.getInstance(ActivityPairingViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isStressNudgeSupported();
            }
        });
    }

    public final void setAlarmCount(int i) {
        this.d = i;
    }

    public final void setAmbientSoundSettingsToWatch(final boolean z) {
        BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(new SetAmbientSoundLevelControlRequest.Builder(z).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setAmbientSoundSettingsToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveAmbientSoundSettingsEnabled(Boolean.valueOf(z));
            }
        });
    }

    public final void setAutoHrFromResponse(@NotNull Context context, @NotNull GetAllUserAppSettingsRes.AutoHr autoHr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(autoHr, "autoHr");
        if (autoHr.getActive() == null || autoHr.getInterval() == null) {
            return;
        }
        int i = 30;
        if (autoHr.getInterval().length() == 8) {
            String interval = autoHr.getInterval();
            Intrinsics.checkNotNullExpressionValue(interval, "autoHr.interval");
            int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            String interval2 = autoHr.getInterval();
            Intrinsics.checkNotNullExpressionValue(interval2, "autoHr.interval");
            int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval2, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            String interval3 = autoHr.getInterval();
            Intrinsics.checkNotNullExpressionValue(interval3, "autoHr.interval");
            i = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval3, new String[]{":"}, false, 0, 6, (Object) null).get(2)) + (parseInt * 60) + parseInt2;
        }
        Boolean active = autoHr.getActive();
        Intrinsics.checkNotNullExpressionValue(active, "autoHr.active");
        z(i, active.booleanValue());
    }

    public final void setAutoRecognitionToWatch(@Nullable AutoRecognize autoRecognize) {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isIDODevice(this.f5246a) && autoRecognize != null) {
            A(autoRecognize);
        } else if (companion.isCADevice(this.f5246a) || companion.isCYDevice(this.f5246a) || companion.isPS1Device(this.f5246a) || companion.isBESDevice(this.f5246a)) {
            B(autoRecognize);
        }
    }

    public final void setAutoSpo2SettingsToWatch(final boolean z) {
        BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(new Spo2TimeIntervalRequest.Builder(z ? 10 : 0).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setAutoSpo2SettingsToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                AutoSPO2SettingsData companion = AutoSPO2SettingsData.Companion.getInstance();
                Intrinsics.checkNotNull(companion);
                companion.setAutoSpO2(z);
                UserDataManager.getInstance(this.getContext()).saveAutoSPO2Settings(companion);
            }
        });
    }

    public final void setViewmodelListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.viewmodelListener = viewModelListener;
    }

    public final boolean t(AlarmInfo alarmInfo) {
        boolean[] isDaySelected = alarmInfo.getDaysSelected().getIsDaySelected();
        Intrinsics.checkNotNullExpressionValue(isDaySelected, "alarmInfo.daysSelected.isDaySelected");
        for (boolean z : isDaySelected) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final boolean u(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final void v(AutoRecognize autoRecognize) {
        ArrayList arrayList = new ArrayList();
        if (autoRecognize != null) {
            List<AutoRecognizeActivity> activities = autoRecognize.getActivities();
            if (!(activities == null || activities.isEmpty())) {
                int size = autoRecognize.getActivities().size();
                for (int i = 0; i < size; i++) {
                    String type = autoRecognize.getActivities().get(i).getType();
                    if (!(type == null || type.length() == 0)) {
                        String code = autoRecognize.getActivities().get(i).getCode();
                        if (!(code == null || code.length() == 0)) {
                            String code2 = autoRecognize.getActivities().get(i).getCode();
                            Intrinsics.checkNotNullExpressionValue(code2, "autoRecognize.activities[i].code");
                            EntityPhysicalActivities physicalActivityN = PhysicalActivityRepository.Companion.getInstance(this.f5246a).getPhysicalActivityN(code2);
                            if (physicalActivityN != null) {
                                String type2 = autoRecognize.getActivities().get(i).getType();
                                String code3 = autoRecognize.getActivities().get(i).getCode();
                                WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
                                String code4 = autoRecognize.getActivities().get(i).getCode();
                                Intrinsics.checkNotNullExpressionValue(code4, "autoRecognize.activities[i].code");
                                arrayList.add(new AutoRecognitionData(type2, code3, true, workoutUtils.getAutoActivityDetectionFWOrderForAnActivity(code4), physicalActivityN.getFwActId()));
                            }
                        }
                    }
                }
            }
        }
        UserDataManager.getInstance(this.f5246a).saveAutoRecognitionList(arrayList);
    }

    public final void w(RespiratoryRateSettings respiratoryRateSettings) {
        if (respiratoryRateSettings != null) {
            respiratoryRateSettings.isActive();
            UserDataManager.getInstance(this.f5246a).saveRespiratoryRateFeatureEnabled(respiratoryRateSettings.isActive());
            return;
        }
        D();
    }

    public final void writeAutoStressDefaultSettingsToWatch() {
        StressTimeIntervalRequest stressTimeIntervalRequest;
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isCZDevice(this.f5246a) && !companion.isCADevice(this.f5246a) && !companion.isCYDevice(this.f5246a) && !companion.isPS1Device(this.f5246a) && !companion.isBESDevice(this.f5246a)) {
            stressTimeIntervalRequest = new StressTimeIntervalRequest.Builder(60).setStartHour(0).setEndHour(23).setStartMin(0).setEndMin(59).setEnabled(false).setHighThresholdRemindEnabled(false).setSundayEnabled(false).setMondayEnabled(false).setTuesdayEnabled(false).setWednesdayEnabled(false).setThursdayEnabled(false).setFridayEnabled(false).setSaturdayEnabled(false).build();
        } else if (BleApiManager.getInstance(this.f5246a).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
            StressConfiguration autoStressConfig = SessionManager.getInstance(this.f5246a).getAutoStressConfig();
            PayUtils payUtils = PayUtils.INSTANCE;
            String baselineTime = autoStressConfig.getStress().getBaselineTime();
            Intrinsics.checkNotNullExpressionValue(baselineTime, "stressConfiguration.stress.baselineTime");
            long convertIntervalToMilliSeconds = payUtils.convertIntervalToMilliSeconds(baselineTime);
            String readinessTime = autoStressConfig.getStress().getReadinessTime();
            Intrinsics.checkNotNullExpressionValue(readinessTime, "stressConfiguration.stress.readinessTime");
            long convertIntervalToMilliSeconds2 = payUtils.convertIntervalToMilliSeconds(readinessTime);
            StressTimeIntervalRequest.Builder enabled = new StressTimeIntervalRequest.Builder(60).setEnabled(false);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            StressTimeIntervalRequest.Builder highStressThreshold = enabled.setBaseLineTime((int) timeUnit.toMinutes(convertIntervalToMilliSeconds)).setReadinessTime((int) timeUnit.toMinutes(convertIntervalToMilliSeconds2)).setHighStressThreshold(autoStressConfig.getStress().getAlert().getThreshold().intValue());
            Integer maxAllowed = autoStressConfig.getStress().getAlert().getMaxAllowed();
            Intrinsics.checkNotNullExpressionValue(maxAllowed, "stressConfiguration.stress.alert.maxAllowed");
            stressTimeIntervalRequest = highStressThreshold.setLimit(maxAllowed.intValue()).build();
        } else {
            stressTimeIntervalRequest = null;
        }
        if (stressTimeIntervalRequest != null) {
            BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(stressTimeIntervalRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$writeAutoStressDefaultSettingsToWatch$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityPairingViewModel.this.getTAG(), response.toString());
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.util.ArrayList] */
    public final void writeFavouritePlacesOnBand(@Nullable GetFavouritePlacesRes getFavouritePlacesRes) {
        if (BleApiManager.getInstance(this.f5246a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new ArrayList();
            List<FavouritePlace> favouritePlaceList = getFavouritePlacesRes != null ? getFavouritePlacesRes.getFavouritePlaceList() : null;
            int i = 0;
            if (!(favouritePlaceList == null || favouritePlaceList.isEmpty())) {
                List<FavouritePlace> favouritePlaceList2 = getFavouritePlacesRes.getFavouritePlaceList();
                Intrinsics.checkNotNullExpressionValue(favouritePlaceList2, "getFavouritePlacesRes.favouritePlaceList");
                for (Object obj : favouritePlaceList2) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    FavouritePlace favouritePlace = (FavouritePlace) obj;
                    Integer sortIndex = favouritePlace.getSortIndex();
                    Intrinsics.checkNotNullExpressionValue(sortIndex, "favouritePlace.sortIndex");
                    int intValue = sortIndex.intValue();
                    Integer sortIndex2 = favouritePlace.getSortIndex();
                    Intrinsics.checkNotNullExpressionValue(sortIndex2, "favouritePlace.sortIndex");
                    FavouriteLocationData favouriteLocationData = new FavouriteLocationData(intValue, sortIndex2.intValue(), favouritePlace.getLabel(), favouritePlace.getName());
                    String str = this.c;
                    LogHelper.d(str, "writeFavouritePlacesOnBand data " + new Gson().toJson(favouriteLocationData));
                    ((ArrayList) objectRef.element).add(favouriteLocationData);
                    i = i2;
                }
            }
            BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(new SetNavigationFavouriteLocationRequest((ArrayList) objectRef.element), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$writeFavouritePlacesOnBand$2
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    String tag = ActivityPairingViewModel.this.getTAG();
                    LogHelper.d(tag, "writeFavouritePlacesOnBand onSettingsError " + error.getErrorMsg());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    String tag = ActivityPairingViewModel.this.getTAG();
                    LogHelper.d(tag, "writeFavouritePlacesOnBand onSettingsResponse favouriteLocationData size " + objectRef.element.size());
                }
            });
        }
    }

    public final void x() {
        B(null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0071, code lost:
        if (((com.coveiot.covepreferences.data.DoNotDisturbData) r3).isDnd_on_off() != false) goto L15;
     */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.coveiot.covepreferences.data.DoNotDisturbData] */
    /* JADX WARN: Type inference failed for: r1v5, types: [T, com.coveiot.covepreferences.data.DoNotDisturbData] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void y() {
        /*
            r10 = this;
            android.content.Context r0 = r10.f5246a
            com.coveiot.android.bleabstract.api.BleApiManager r0 = com.coveiot.android.bleabstract.api.BleApiManager.getInstance(r0)
            com.coveiot.android.bleabstract.api.BleApi r0 = r0.getBleApi()
            com.coveiot.android.bleabstract.models.DeviceSupportedFeatures r0 = r0.getDeviceSupportedFeatures()
            boolean r0 = r0.isDndSupported()
            if (r0 == 0) goto Lbe
            android.content.Context r0 = r10.f5246a
            com.coveiot.android.bleabstract.api.BleApiManager r0 = com.coveiot.android.bleabstract.api.BleApiManager.getInstance(r0)
            com.coveiot.android.bleabstract.api.BleApi r0 = r0.getBleApi()
            com.coveiot.android.bleabstract.models.DeviceSupportedFeatures r0 = r0.getDeviceSupportedFeatures()
            boolean r0 = r0.isScheduledDndSupported()
            if (r0 == 0) goto Lbe
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            android.content.Context r1 = r10.f5246a
            com.coveiot.covepreferences.UserDataManager r1 = com.coveiot.covepreferences.UserDataManager.getInstance(r1)
            com.coveiot.covepreferences.data.DoNotDisturbData r1 = r1.getDoNotDisturbData()
            r0.element = r1
            r2 = 0
            if (r1 != 0) goto L57
            com.coveiot.covepreferences.data.DoNotDisturbData r1 = com.coveiot.covepreferences.data.DoNotDisturbData.getInstance()
            r3 = 22
            r1.setBeggining_time_hour(r3)
            r1.setBeggining_time_minutes(r2)
            r3 = 6
            r1.setEnd_time_hour(r3)
            r1.setEnd_time_minutes(r2)
            r1.setDnd_on_off(r2)
            r1.setSchedule_on_off(r2)
            r0.element = r1
        L57:
            com.coveiot.android.bleabstract.request.SetDNDModeRequest$Builder r1 = new com.coveiot.android.bleabstract.request.SetDNDModeRequest$Builder
            T r3 = r0.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            com.coveiot.covepreferences.data.DoNotDisturbData r3 = (com.coveiot.covepreferences.data.DoNotDisturbData) r3
            boolean r3 = r3.isSchedule_on_off()
            if (r3 != 0) goto L73
            T r3 = r0.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            com.coveiot.covepreferences.data.DoNotDisturbData r3 = (com.coveiot.covepreferences.data.DoNotDisturbData) r3
            boolean r3 = r3.isDnd_on_off()
            if (r3 == 0) goto L74
        L73:
            r2 = 1
        L74:
            r5 = r2
            T r2 = r0.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            com.coveiot.covepreferences.data.DoNotDisturbData r2 = (com.coveiot.covepreferences.data.DoNotDisturbData) r2
            int r6 = r2.getBeggining_time_hour()
            T r2 = r0.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            com.coveiot.covepreferences.data.DoNotDisturbData r2 = (com.coveiot.covepreferences.data.DoNotDisturbData) r2
            int r7 = r2.getBeggining_time_minutes()
            T r2 = r0.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            com.coveiot.covepreferences.data.DoNotDisturbData r2 = (com.coveiot.covepreferences.data.DoNotDisturbData) r2
            int r8 = r2.getEnd_time_hour()
            T r2 = r0.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            com.coveiot.covepreferences.data.DoNotDisturbData r2 = (com.coveiot.covepreferences.data.DoNotDisturbData) r2
            int r9 = r2.getEnd_time_minutes()
            r4 = r1
            r4.<init>(r5, r6, r7, r8, r9)
            com.coveiot.android.bleabstract.request.SetDNDModeRequest r1 = r1.build()
            android.content.Context r2 = r10.f5246a
            com.coveiot.android.bleabstract.api.BleApiManager r2 = com.coveiot.android.bleabstract.api.BleApiManager.getInstance(r2)
            com.coveiot.android.bleabstract.api.BleApi r2 = r2.getBleApi()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$sendDefaultDndSettings$2 r3 = new com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$sendDefaultDndSettings$2
            r3.<init>()
            r2.setUserSettings(r1, r3)
        Lbe:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel.y():void");
    }

    public final void z(final int i, final boolean z) {
        BleApiManager.getInstance(this.f5246a).getBleApi().setUserSettings(new HeartRateTimeIntervalRequest.Builder(z ? i : 0).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$setAutoHrToBleApi$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                ActivityPairingViewModel.this.getViewmodelListener().onDataFailure(AppConstants.EMPTY_STRING.getValue());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveAutoHrEnabled(z);
                UserDataManager.getInstance(ActivityPairingViewModel.this.getContext()).saveAutoHrInterval(i);
                ActivityPairingViewModel.this.getViewmodelListener().onSuccess();
            }
        });
    }

    public final void sendToBle(@NotNull final List<AlarmInfo> alarmInfos, @NotNull final GetAllUserAppSettingsRes settingsResponse) {
        Intrinsics.checkNotNullParameter(alarmInfos, "alarmInfos");
        Intrinsics.checkNotNullParameter(settingsResponse, "settingsResponse");
        if (alarmInfos.size() > 0) {
            try {
                AlarmInfo alarmInfo = alarmInfos.get(this.d);
                SetVibrationAlarmRequest setAlarmReq = new SetVibrationAlarmRequest.Builder().setAlarmId(alarmInfo.getAlarmId()).setHour(alarmInfo.getHour()).setMinute(alarmInfo.getMinute()).setEnabled(alarmInfo.isAlarmOn()).setEventName(alarmInfo.getEventName()).setRepeatEnabled(t(alarmInfo)).setSnoozeDuration(alarmInfo.getSnoozeDuration()).setSnoozeRepeatTimes(alarmInfo.getSnoozeRepeatCount()).setSundayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[0]).setMondayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[1]).setTuesdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[2]).setWednesdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[3]).setThursdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[4]).setFridayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[5]).setSaturdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[6]).build();
                BleApi bleApi = BleApiManager.getInstance(this.f5246a).getBleApi();
                Intrinsics.checkNotNullExpressionValue(setAlarmReq, "setAlarmReq");
                bleApi.setUserSettings(setAlarmReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$sendToBle$2
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ActivityPairingViewModel.this.getViewmodelListener().onDataFailure(AppConstants.EMPTY_STRING.getValue());
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        ActivityPairingViewModel activityPairingViewModel = ActivityPairingViewModel.this;
                        activityPairingViewModel.setAlarmCount(activityPairingViewModel.getAlarmCount() + 1);
                        if (ActivityPairingViewModel.this.getAlarmCount() < alarmInfos.size()) {
                            ActivityPairingViewModel.this.sendToBle(alarmInfos, settingsResponse);
                        } else if (settingsResponse.getAutoHr() != null) {
                            ActivityPairingViewModel activityPairingViewModel2 = ActivityPairingViewModel.this;
                            Context context = activityPairingViewModel2.getContext();
                            GetAllUserAppSettingsRes.AutoHr autoHr = settingsResponse.getAutoHr();
                            Intrinsics.checkNotNullExpressionValue(autoHr, "settingsResponse.autoHr");
                            activityPairingViewModel2.setAutoHrFromResponse(context, autoHr);
                        } else {
                            ActivityPairingViewModel.this.getViewmodelListener().onSuccess();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
