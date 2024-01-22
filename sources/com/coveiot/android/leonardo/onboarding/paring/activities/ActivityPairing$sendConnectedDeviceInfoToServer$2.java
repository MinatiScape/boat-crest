package com.coveiot.android.leonardo.onboarding.paring.activities;

import android.os.Handler;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProviders;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.activitymodes.activity1k.viewmodel.OneKActivityViewModel;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel;
import com.coveiot.android.sleepenergyscore.FitnessComputedDataApiCall;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.device.model.IOTUserDeviceReq;
import com.coveiot.coveaccess.device.model.IOTUserDeviceRes;
import com.coveiot.coveaccess.fitness.model.BPFitnessRecordData;
import com.coveiot.coveaccess.fitness.model.BpFitnessRecords;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordApiManager;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.covedb.manualdata.entities.Source;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.repository.manualdata.datasources.db.write.ManualDataDBWrite;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.szabh.smable3.component.BleMessenger;
import java.util.Calendar;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityPairing$sendConnectedDeviceInfoToServer$2 implements CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ IOTUserDeviceReq f5220a;
    public final /* synthetic */ ActivityPairing b;
    public final /* synthetic */ BleDeviceInfo c;

    public ActivityPairing$sendConnectedDeviceInfoToServer$2(IOTUserDeviceReq iOTUserDeviceReq, ActivityPairing activityPairing, BleDeviceInfo bleDeviceInfo) {
        this.f5220a = iOTUserDeviceReq;
        this.b = activityPairing;
        this.c = bleDeviceInfo;
    }

    public static final void c(ActivityPairing this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar fromData = Calendar.getInstance();
        fromData.add(6, -90);
        Calendar toData = Calendar.getInstance();
        toData.add(6, 0);
        Intrinsics.checkNotNullExpressionValue(fromData, "fromData");
        Intrinsics.checkNotNullExpressionValue(toData, "toData");
        WorkoutSessionRepository.getSessionsListFromServer$default(WorkoutSessionRepository.Companion.getInstance(this$0), fromData, toData, null, 4, null);
    }

    public static final void d(Calendar calendar, Calendar calendar2, final ActivityPairing this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FitnessRecordApiManager.getBpFitnessRecord(AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), AppUtils.formatDate(calendar2.getTime(), "yyyy-MM-dd"), new CoveApiListener<BpFitnessRecords, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectedDeviceInfoToServer$2$onSuccess$3$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable BpFitnessRecords bpFitnessRecords) {
                if (bpFitnessRecords == null || bpFitnessRecords.getItems() == null || bpFitnessRecords.getItems() == null || bpFitnessRecords.getItems().getBpItems().size() <= 0) {
                    return;
                }
                for (BPFitnessRecordData bPFitnessRecordData : bpFitnessRecords.getItems().getBpItems()) {
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(AppUtils.parseDate(bPFitnessRecordData.getDate(), "yyyy-MM-dd"));
                    String time = bPFitnessRecordData.getTime();
                    Intrinsics.checkNotNullExpressionValue(time, "fitnessRecord.time");
                    List split$default = StringsKt__StringsKt.split$default((CharSequence) time, new char[]{':'}, false, 0, 6, (Object) null);
                    calendar3.set(11, Integer.parseInt((String) split$default.get(0)));
                    calendar3.set(12, Integer.parseInt((String) split$default.get(1)));
                    calendar3.set(13, Integer.parseInt((String) split$default.get(2)));
                    EntityManualData entityManualData = new EntityManualData(calendar3.getTimeInMillis(), Source.MANUAL.name());
                    Integer num = bPFitnessRecordData.getValue().get(0);
                    Intrinsics.checkNotNullExpressionValue(num, "fitnessRecord.value.get(0)");
                    entityManualData.setSystolicBp(num.intValue());
                    Integer num2 = bPFitnessRecordData.getValue().get(1);
                    Intrinsics.checkNotNullExpressionValue(num2, "fitnessRecord.value.get(1)");
                    entityManualData.setDiastolicBp(num2.intValue());
                    entityManualData.setSerialNo(BleApiManager.getInstance(ActivityPairing.this).getBleApi().getMacAddress());
                    entityManualData.setUserDeviceId(PreferenceManager.getInstance().getUserDeviceID());
                    entityManualData.setLevelInterpretation(false);
                    ManualDataDBWrite.getInstance(ActivityPairing.this).insert(entityManualData);
                }
            }
        });
    }

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
        Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
        this.b.dismissProgress();
        LogHelper.d("Iot API", "-- " + coveApiErrorModel.getMsg());
        Toast.makeText(this.b, "Unable to proceed. try again", 1).show();
    }

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onSuccess(@Nullable IOTUserDeviceRes iOTUserDeviceRes) {
        if (this.f5220a.getAppTrackerId() == null && !AppUtils.isEmpty(SessionManager.getInstance(this.b).getUserDetails().getAppTrackerId())) {
            SessionManager.getInstance(this.b).getUserDetails().getAppTrackerId();
        }
        if (iOTUserDeviceRes != null) {
            BleApiManager.getInstance(this.b).getBleApi().getDeviceSupportedFeatures().isAppSocialDistanceFeatureSupported();
            if (BleApiManager.getInstance(this.b).getBleApi().getDeviceSupportedFeatures().isSmartAlertsSupported()) {
                this.b.getActivityPairingViewModel().pullSmartAlertAppConfigFromServer();
            }
            if (BleApiManager.getInstance(this.b).getBleApi().getDeviceSupportedFeatures().isTurnByTurnNavigationSupported()) {
                this.b.getActivityPairingViewModel().getFavouritePlacesFromServer();
            }
            this.b.getActivityPairingViewModel().getFeatureMappingRemoteConfig(this.b);
            this.b.getActivityPairingViewModel().getUserSettings(this.c);
            this.b.getActivityPairingViewModel().getUserDeviceSettings();
            OneKActivityViewModel.saveCategoryListFromServer$default((OneKActivityViewModel) ViewModelProviders.of(this.b).get(OneKActivityViewModel.class), null, 1, null);
            this.b.getActivityPairingViewModel().getSportsSettingFromServer(this.b);
            if (UserDataManager.getInstance(this.b).isEnableSleepEnergyScoreFeature(this.b)) {
                Object clone = Calendar.getInstance().clone();
                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                Calendar calendar = (Calendar) clone;
                calendar.add(2, -3);
                FitnessComputedDataApiCall fitnessComputedDataApiCall = FitnessComputedDataApiCall.INSTANCE;
                String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
                fitnessComputedDataApiCall.getFitnessComputedDataFromServer(formatDate, this.b);
            }
            Handler handler = new Handler();
            final ActivityPairing activityPairing = this.b;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.l
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityPairing$sendConnectedDeviceInfoToServer$2.c(ActivityPairing.this);
                }
            }, BleMessenger.TIMEOUT);
            try {
                final ActivityPairing activityPairing2 = this.b;
                PreparationPlanRepository.Companion.getInstance(this.b).getCurrentPlanFromServer(new PreparationPlanRepository.PlanDetailsListner() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectedDeviceInfoToServer$2$onSuccess$2

                    @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectedDeviceInfoToServer$2$onSuccess$2$onPlanFetchedSuccessfully$1", f = "ActivityPairing.kt", i = {}, l = {2312, 2317}, m = "invokeSuspend", n = {}, s = {})
                    /* loaded from: classes5.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public int label;
                        public final /* synthetic */ ActivityPairing this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(ActivityPairing activityPairing, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.this$0 = activityPairing;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new a(this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        @Nullable
                        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        /* JADX WARN: Removed duplicated region for block: B:23:0x007d  */
                        /* JADX WARN: Removed duplicated region for block: B:24:0x007f  */
                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @org.jetbrains.annotations.Nullable
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r17) {
                            /*
                                Method dump skipped, instructions count: 243
                                To view this dump add '--comments-level debug' option
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectedDeviceInfoToServer$2$onSuccess$2.a.invokeSuspend(java.lang.Object):java.lang.Object");
                        }
                    }

                    @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
                    public void onFailure(@NotNull String mesaage) {
                        Intrinsics.checkNotNullParameter(mesaage, "mesaage");
                        Calendar fromData = Calendar.getInstance();
                        fromData.add(6, -30);
                        Calendar toData = Calendar.getInstance();
                        toData.add(6, 0);
                        Intrinsics.checkNotNullExpressionValue(fromData, "fromData");
                        Intrinsics.checkNotNullExpressionValue(toData, "toData");
                        WorkoutSessionRepository.getSessionsListFromServer$default(WorkoutSessionRepository.Companion.getInstance(ActivityPairing.this), fromData, toData, null, 4, null);
                        ActivityPairing.this.dismissProgress();
                        ActivityPairing activityPairing3 = ActivityPairing.this;
                        Toast.makeText(activityPairing3, activityPairing3.getResources().getString(R.string.unable_to_proceed), 1).show();
                    }

                    @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
                    public void onPlanFetchedSuccessfully() {
                        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new a(ActivityPairing.this, null), 3, null);
                    }
                });
                final Calendar fromData = Calendar.getInstance();
                fromData.add(2, -3);
                final Calendar toData = Calendar.getInstance();
                ManualDataRepository.Companion companion = ManualDataRepository.Companion;
                Intrinsics.checkNotNullExpressionValue(fromData, "fromData");
                Intrinsics.checkNotNullExpressionValue(toData, "toData");
                companion.getInstance(this.b).getSpo2DataFromServer(fromData, toData);
                companion.getInstance(this.b).getHRVStressDataFromServer(fromData, toData);
                Handler handler2 = new Handler();
                final ActivityPairing activityPairing3 = this.b;
                handler2.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.m
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityPairing$sendConnectedDeviceInfoToServer$2.d(fromData, toData, activityPairing3);
                    }
                }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                Dashboard2Utils.Companion.getSubscribeStatus(this.b);
                ActivityPairingViewModel activityPairingViewModel = this.b.getActivityPairingViewModel();
                String formatDate2 = AppUtils.formatDate(fromData.getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(\n            …                        )");
                String formatDate3 = AppUtils.formatDate(toData.getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate3, "formatDate(toData.time, \"yyyy-MM-dd\")");
                activityPairingViewModel.getAllFitnessRecord(formatDate2, formatDate3);
            } catch (Exception e) {
                e.printStackTrace();
                LogHelper.e(this.b.getTAG(), e.getMessage(), e);
            }
            if (BleApiManager.getInstance(this.b).getBleApi().getDeviceSupportedFeatures().isSensAISupported()) {
                Calendar fromData2 = Calendar.getInstance();
                fromData2.add(6, -180);
                Calendar toData2 = Calendar.getInstance();
                toData2.add(6, 0);
                ActivityPairingViewModel activityPairingViewModel2 = this.b.getActivityPairingViewModel();
                Intrinsics.checkNotNullExpressionValue(fromData2, "fromData");
                Intrinsics.checkNotNullExpressionValue(toData2, "toData");
                String connectedDeviceMacAddress = new com.coveiot.android.activitymodes.preference.PreferenceManager(this.b).getConnectedDeviceMacAddress();
                Intrinsics.checkNotNull(connectedDeviceMacAddress);
                activityPairingViewModel2.getSensAISessionsListFromServer(fromData2, toData2, connectedDeviceMacAddress);
            }
        }
    }
}
