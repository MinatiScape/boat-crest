package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.work.PeriodicWorkRequest;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.GoalType;
import com.coveiot.android.bleabstract.models.HealthVitalsType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.AgpsUpdateRequest;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.GetCalorieDistanceGoalRequest;
import com.coveiot.android.bleabstract.request.GetLatestHealthDataRequest;
import com.coveiot.android.bleabstract.request.ReadStepTargetRequest;
import com.coveiot.android.bleabstract.request.TodaysFitnessDataRequest;
import com.coveiot.android.bleabstract.request.TodaysStepsDataRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetCalorieDistanceGoalResponse;
import com.coveiot.android.bleabstract.response.GetLatestHealthDataResponse;
import com.coveiot.android.bleabstract.response.GetStepGoalResponse;
import com.coveiot.android.bleabstract.response.HealthData;
import com.coveiot.android.bleabstract.response.TodaysStepsData;
import com.coveiot.android.bleabstract.response.TodaysStepsResponse;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.dashboard2.LastDataHelper;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.dashboard2.StatusApiDataHelper;
import com.coveiot.android.dashboard2.listener.SyncTroubleShoutListener;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.dashboard2.model.DeviceReconnected;
import com.coveiot.android.dashboard2.model.ServerDataPullConfigModel;
import com.coveiot.android.dashboard2.model.state.SyncState;
import com.coveiot.android.dashboard2.util.ActivityDataHelper;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.dashboard2.util.HeartRateDataHelper;
import com.coveiot.android.dashboard2.util.SPO2DataHelper;
import com.coveiot.android.dashboard2.util.StepsDataHelper;
import com.coveiot.android.dashboard2.util.TemperatureDataHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.navigation.utils.NavigationConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.fitness.ActivityBaseUnit;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.BatteryLevelData;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.covepreferences.data.SedentaryReminderData;
import com.coveiot.covepreferences.data.StepsDataModel;
import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.repository.sedentary.datasource.SedentarySettings;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libble.v2.profile.BatteryService;
import com.goodix.ble.libcomx.task.ITaskSet;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class DataSyncViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4260a;
    @NotNull
    public final MutableLiveData<SyncState> b;
    public boolean c;
    @NotNull
    public MutableLiveData<StepsDataModel> d;
    @NotNull
    public MutableLiveData<LatestHealthDataModel> e;
    @NotNull
    public MutableLiveData<LatestHealthDataModel> f;
    @NotNull
    public MutableLiveData<LatestHealthDataModel> g;
    @NotNull
    public MutableLiveData<Integer> h;
    @NotNull
    public SyncState i;
    public long j;
    public int k;
    @NotNull
    public MutableLiveData<Boolean> l;
    @NotNull
    public Context m;
    @Nullable
    public ViewModelListener n;
    public boolean o;
    @NotNull
    public final DataSyncViewModel$latestStepSuccessListener$1 p;
    @NotNull
    public final DataSyncViewModel$latestSPO2SuccessListener$1 q;
    @NotNull
    public final DataSyncViewModel$latestHeartRateSuccessListener$1 r;
    @NotNull
    public final DataSyncViewModel$latestTemperatureSuccessListener$1 s;
    public SyncTroubleShoutListener syncTroubleShoutListener;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$postDataSyncComplete$2", f = "DataSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
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
                CoveEventBusManager.getInstance().getEventBus().post(new DeviceReconnected());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$syncData$1", f = "DataSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isManualSync;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(boolean z, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$isManualSync = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$isManualSync, continuation);
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
                BleApi bleApi = BleApiManager.getInstance(DataSyncViewModel.this.getContext()).getBleApi();
                if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                    if (DataSyncViewModel.this.checkIsSyncing()) {
                        LogHelper.i(DataSyncViewModel.this.getTAG(), "Sync attempt ignored, sync is already in progress.");
                        return Unit.INSTANCE;
                    }
                    DataSyncViewModel.this.setManualSyncAttempt(this.$isManualSync);
                    Dashboard2PreferenceManager.Companion.getInstance(DataSyncViewModel.this.getContext()).saveLastSyncTriggeredTimestamp(System.currentTimeMillis());
                    DataSyncViewModel.this.getSyncState().setSyncing(true);
                    DataSyncViewModel.this.getSyncState().setProgress(0);
                    DataSyncViewModel.this.getSyncState().setProgressByStage(0);
                    SyncState syncState = DataSyncViewModel.this.getSyncState();
                    String string = DataSyncViewModel.this.getContext().getString(R.string.syncing_data);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.syncing_data)");
                    syncState.setMessage(string);
                    DataSyncViewModel.this.getSyncStateLiveData().postValue(DataSyncViewModel.this.getSyncState());
                    if (BleApiManager.getInstance(DataSyncViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isChangeStepGoalFromWatchSupported()) {
                        DataSyncViewModel.this.getStepGoalFromWatch();
                    } else {
                        DataSyncViewModel.this.getCurrentStepsFromWatchL();
                    }
                } else {
                    LogHelper.d(DataSyncViewModel.this.getTAG(), "watch is not connected.");
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestStepSuccessListener$1] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestSPO2SuccessListener$1] */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestHeartRateSuccessListener$1] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestTemperatureSuccessListener$1] */
    public DataSyncViewModel(@NotNull Context cntx) {
        Intrinsics.checkNotNullParameter(cntx, "cntx");
        this.f4260a = "DataSyncViewModel";
        this.b = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.i = new SyncState();
        this.l = new MutableLiveData<>();
        Context applicationContext = cntx.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "cntx.applicationContext");
        this.m = applicationContext;
        this.p = new SuccessResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestStepSuccessListener$1

            @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestStepSuccessListener$1$onSuccess$1", f = "DataSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes4.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ DataSyncViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(DataSyncViewModel dataSyncViewModel, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = dataSyncViewModel;
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

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableLiveData<StepsDataModel> stepsLiveDataOnPreference = this.this$0.getStepsLiveDataOnPreference();
                        if (stepsLiveDataOnPreference != null) {
                            UserDataManager userDataManager = UserDataManager.getInstance(this.this$0.getContext());
                            Calendar calendar = Calendar.getInstance();
                            BleApi bleApi = BleApiManager.getInstance(this.this$0.getContext()).getBleApi();
                            stepsLiveDataOnPreference.postValue(userDataManager.getLiveStepsData(calendar, bleApi != null ? bleApi.getMacAddress() : null));
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onError(@Nullable String str) {
                String tag = DataSyncViewModel.this.getTAG();
                LogHelper.d(tag, "latestStepSuccessListener->" + str);
            }

            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onSuccess() {
                CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(DataSyncViewModel.this);
                if (viewModelScope != null) {
                    kotlinx.coroutines.e.e(viewModelScope, Dispatchers.getIO(), null, new a(DataSyncViewModel.this, null), 2, null);
                }
            }
        };
        this.q = new SuccessResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestSPO2SuccessListener$1

            @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestSPO2SuccessListener$1$onSuccess$1", f = "DataSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes4.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ DataSyncViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(DataSyncViewModel dataSyncViewModel, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = dataSyncViewModel;
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

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableLiveData<LatestHealthDataModel> spo2LiveDataOnPreference = this.this$0.getSpo2LiveDataOnPreference();
                        if (spo2LiveDataOnPreference != null) {
                            spo2LiveDataOnPreference.postValue(UserDataManager.getInstance(this.this$0.getContext()).getLatestSpo2FromPref());
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onError(@Nullable String str) {
                String tag = DataSyncViewModel.this.getTAG();
                LogHelper.d(tag, "latestSPO2SuccessListener->" + str);
            }

            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onSuccess() {
                CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(DataSyncViewModel.this);
                if (viewModelScope != null) {
                    kotlinx.coroutines.e.e(viewModelScope, Dispatchers.getIO(), null, new a(DataSyncViewModel.this, null), 2, null);
                }
            }
        };
        this.r = new SuccessResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestHeartRateSuccessListener$1

            @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestHeartRateSuccessListener$1$onSuccess$1", f = "DataSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes4.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ DataSyncViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(DataSyncViewModel dataSyncViewModel, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = dataSyncViewModel;
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

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableLiveData<LatestHealthDataModel> heartRateLiveDataOnPreference = this.this$0.getHeartRateLiveDataOnPreference();
                        if (heartRateLiveDataOnPreference != null) {
                            heartRateLiveDataOnPreference.postValue(UserDataManager.getInstance(this.this$0.getContext()).getLatestHRValueFromPref());
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onError(@Nullable String str) {
                String tag = DataSyncViewModel.this.getTAG();
                LogHelper.d(tag, "latestHeartRateSuccessListener->" + str);
            }

            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onSuccess() {
                CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(DataSyncViewModel.this);
                if (viewModelScope != null) {
                    kotlinx.coroutines.e.e(viewModelScope, Dispatchers.getIO(), null, new a(DataSyncViewModel.this, null), 2, null);
                }
            }
        };
        this.s = new SuccessResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestTemperatureSuccessListener$1

            @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$latestTemperatureSuccessListener$1$onSuccess$1", f = "DataSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes4.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ DataSyncViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(DataSyncViewModel dataSyncViewModel, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = dataSyncViewModel;
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

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableLiveData<LatestHealthDataModel> temperatureLiveDataOnPreference = this.this$0.getTemperatureLiveDataOnPreference();
                        if (temperatureLiveDataOnPreference != null) {
                            temperatureLiveDataOnPreference.postValue(UserDataManager.getInstance(this.this$0.getContext()).getLatestTemperatureValueFromPref());
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onError(@Nullable String str) {
                String tag = DataSyncViewModel.this.getTAG();
                LogHelper.d(tag, "latestTemperatureSuccessListener->" + str);
            }

            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onSuccess() {
                CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(DataSyncViewModel.this);
                if (viewModelScope != null) {
                    kotlinx.coroutines.e.e(viewModelScope, Dispatchers.getIO(), null, new a(DataSyncViewModel.this, null), 2, null);
                }
            }
        };
    }

    public static /* synthetic */ void o(DataSyncViewModel dataSyncViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        dataSyncViewModel.syncData(z);
    }

    public static /* synthetic */ void syncData$default(DataSyncViewModel dataSyncViewModel, boolean z, ServerDataPullConfigModel serverDataPullConfigModel, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            serverDataPullConfigModel = null;
        }
        dataSyncViewModel.syncData(z, serverDataPullConfigModel);
    }

    public final void checkForAgpsFileUpdate() {
        if (BleApiManager.getInstance(this.m).getBleApi().getDeviceSupportedFeatures().isAgpsFileUploadSupported()) {
            if (Dashboard2Utils.Companion.boolUpdateAgpsFile(this.m)) {
                updateAgpsFile();
                return;
            } else {
                postDataSyncComplete();
                return;
            }
        }
        postDataSyncComplete();
    }

    public final boolean checkIsSyncing() {
        SyncState value = this.b.getValue();
        if ((value != null && value.isSyncing()) || SyncManager.getInstance().isSyncInProgress()) {
            long lastSyncTriggeredTimestamp = Dashboard2PreferenceManager.Companion.getInstance(this.m).getLastSyncTriggeredTimestamp();
            if (lastSyncTriggeredTimestamp != 0 && System.currentTimeMillis() - lastSyncTriggeredTimestamp <= PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
                LogHelper.d(this.f4260a, "Sync state is syncing");
                return true;
            }
            resetSyncState();
            return false;
        }
        return false;
    }

    public final void checkNavigationDisclaimerAcceptance(@NotNull final String version, @NotNull final Function3<? super String, ? super Boolean, ? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(result, "result");
        CoveOnboarding.getAcceptedLegalDoc(new CoveApiListener<LegalDocsAcceptedListRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$checkNavigationDisclaimerAcceptance$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Function3<String, Boolean, Boolean, Unit> function3 = result;
                String msg = coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null;
                Boolean bool = Boolean.FALSE;
                function3.invoke(msg, bool, bool);
            }

            /* JADX WARN: Removed duplicated region for block: B:35:0x0069 A[EDGE_INSN: B:35:0x0069->B:30:0x0069 ?: BREAK  , SYNTHETIC] */
            @Override // com.coveiot.coveaccess.CoveApiListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onSuccess(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes r8) {
                /*
                    r7 = this;
                    if (r8 == 0) goto L86
                    com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes$Data r0 = r8.getData()
                    if (r0 == 0) goto L86
                    com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes$Data r8 = r8.getData()
                    java.util.List r8 = r8.getItems()
                    java.lang.String r0 = "legalHistory"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)
                    boolean r0 = r8.isEmpty()
                    r1 = 1
                    r0 = r0 ^ r1
                    java.lang.String r2 = ""
                    if (r0 == 0) goto L7d
                    java.lang.String r0 = r2
                    boolean r3 = r8 instanceof java.util.Collection
                    r4 = 0
                    if (r3 == 0) goto L2e
                    boolean r3 = r8.isEmpty()
                    if (r3 == 0) goto L2e
                L2c:
                    r1 = r4
                    goto L69
                L2e:
                    java.util.Iterator r8 = r8.iterator()
                L32:
                    boolean r3 = r8.hasNext()
                    if (r3 == 0) goto L2c
                    java.lang.Object r3 = r8.next()
                    com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes$Items r3 = (com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes.Items) r3
                    java.lang.String r5 = r3.getType()
                    java.lang.String r6 = "MAP_NAV_DISCLAIMER"
                    boolean r5 = r5.equals(r6)
                    if (r5 == 0) goto L66
                    java.lang.String r5 = r3.medium
                    if (r5 == 0) goto L57
                    int r5 = r5.length()
                    if (r5 != 0) goto L55
                    goto L57
                L55:
                    r5 = r4
                    goto L58
                L57:
                    r5 = r1
                L58:
                    if (r5 == 0) goto L66
                    java.lang.String r3 = r3.getVersion()
                    boolean r3 = r3.equals(r0)
                    if (r3 == 0) goto L66
                    r3 = r1
                    goto L67
                L66:
                    r3 = r4
                L67:
                    if (r3 == 0) goto L32
                L69:
                    if (r1 == 0) goto L73
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r8 = r1
                    java.lang.Boolean r0 = java.lang.Boolean.TRUE
                    r8.invoke(r2, r0, r0)
                    goto L86
                L73:
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r8 = r1
                    java.lang.Boolean r0 = java.lang.Boolean.FALSE
                    java.lang.Boolean r1 = java.lang.Boolean.TRUE
                    r8.invoke(r2, r0, r1)
                    goto L86
                L7d:
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r8 = r1
                    java.lang.Boolean r0 = java.lang.Boolean.FALSE
                    java.lang.Boolean r1 = java.lang.Boolean.TRUE
                    r8.invoke(r2, r0, r1)
                L86:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$checkNavigationDisclaimerAcceptance$1.onSuccess(com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes):void");
            }
        }, NavigationConstants.MAP_NAV_DISCLAIMER);
    }

    @NotNull
    public final MutableLiveData<Integer> getBatteryLiveData() {
        return this.h;
    }

    @NotNull
    public final MutableLiveData<Boolean> getBatteryRequestFailure() {
        return this.l;
    }

    public final void getCalorieGoalFromWatch() {
        BleApiManager.getInstance(this.m).getBleApi().getData(new GetCalorieDistanceGoalRequest(GoalType.CALORIE), new DataResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$getCalorieGoalFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                DataSyncViewModel.this.getCurrentStepsFromWatchL();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() != null && (response.getResponseData() instanceof GetCalorieDistanceGoalResponse)) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetCalorieDistanceGoalResponse");
                    int calorieGoal = ((GetCalorieDistanceGoalResponse) responseData).getCalorieGoal();
                    Context context = DataSyncViewModel.this.getContext();
                    Intrinsics.checkNotNull(context);
                    FitnessGoal fitnessGoalCaloriesData = UserDataManager.getInstance(context).getFitnessGoalCaloriesData();
                    if (fitnessGoalCaloriesData == null) {
                        fitnessGoalCaloriesData = new FitnessGoal();
                        fitnessGoalCaloriesData.activityType = ActivityType.ANY.getActivityType();
                        fitnessGoalCaloriesData.activityBaseUnit = ActivityBaseUnit.CALORIES.getActivityBaseUnit();
                        fitnessGoalCaloriesData.targetedDays = 1;
                    }
                    fitnessGoalCaloriesData.target = Integer.valueOf(calorieGoal);
                    Context context2 = DataSyncViewModel.this.getContext();
                    Intrinsics.checkNotNull(context2);
                    UserDataManager.getInstance(context2).saveFitnessGoalCalories(fitnessGoalCaloriesData);
                }
                DataSyncViewModel.this.getCurrentStepsFromWatchL();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.m;
    }

    public final void getCurrentStepsFromWatch(final DataResultListener dataResultListener) {
        if (BleApiManager.getInstance(this.m).getDeviceType() != DeviceType.v2) {
            BleApiManager bleApiManager = BleApiManager.getInstance(this.m);
            if ((bleApiManager != null ? bleApiManager.getDeviceType() : null) != DeviceType.v7) {
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                if (!companion.isCZDevice(this.m) && !companion.isCADevice(this.m) && !companion.isCYDevice(this.m) && !companion.isPS1Device(this.m) && !companion.isBESDevice(this.m) && BleApiManager.getInstance(this.m).getDeviceType() != DeviceType.smartT) {
                    TodaysStepsDataRequest build = new TodaysStepsDataRequest.Builder().build();
                    Intrinsics.checkNotNullExpressionValue(build, "Builder().build()");
                    dataResultListener.onDataResponse(new BleBaseResponse(build));
                    return;
                }
            }
        }
        BleBaseRequest build2 = new TodaysStepsDataRequest.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder().build()");
        if (BleApiManager.getInstance(this.m).getBleApi().getDeviceSupportedFeatures().isFitnessValueCommandSupported()) {
            build2 = new TodaysFitnessDataRequest.Builder().build();
            Intrinsics.checkNotNullExpressionValue(build2, "Builder().build()");
        }
        BleApiManager.getInstance(this.m).getBleApi().getData(build2, new DataResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$getCurrentStepsFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                DataResultListener.this.onDataError(error);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                DataSyncViewModel$latestStepSuccessListener$1 dataSyncViewModel$latestStepSuccessListener$1;
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() != null && (response.getResponseData() instanceof TodaysStepsResponse)) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.TodaysStepsResponse");
                    TodaysStepsData todaysStepsData = ((TodaysStepsResponse) responseData).getTodaysStepsData();
                    if (todaysStepsData != null) {
                        StepsDataHelper stepsDataHelper = StepsDataHelper.INSTANCE;
                        Context context = this.getContext();
                        dataSyncViewModel$latestStepSuccessListener$1 = this.p;
                        stepsDataHelper.saveLatestStepsDataToPreferenceFromWatch(context, todaysStepsData, dataSyncViewModel$latestStepSuccessListener$1);
                    }
                }
                DataResultListener.this.onDataResponse(response);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void getCurrentStepsFromWatchL() {
        getCurrentStepsFromWatch(new DataResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$getCurrentStepsFromWatchL$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                DataSyncViewModel.this.p();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                DataSyncViewModel.this.p();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final int getDataSyncFailureCount() {
        return this.k;
    }

    public final void getDistanceGoalFromWatch() {
        BleApiManager.getInstance(this.m).getBleApi().getData(new GetCalorieDistanceGoalRequest(GoalType.DISTANCE), new DataResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$getDistanceGoalFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                DataSyncViewModel.this.getCurrentStepsFromWatchL();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() != null && (response.getResponseData() instanceof GetCalorieDistanceGoalResponse)) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetCalorieDistanceGoalResponse");
                    int distanceGoal = ((GetCalorieDistanceGoalResponse) responseData).getDistanceGoal();
                    Context context = DataSyncViewModel.this.getContext();
                    Intrinsics.checkNotNull(context);
                    FitnessGoal fitnessGoalDistanceData = UserDataManager.getInstance(context).getFitnessGoalDistanceData();
                    if (fitnessGoalDistanceData == null) {
                        fitnessGoalDistanceData = new FitnessGoal();
                        fitnessGoalDistanceData.activityType = ActivityType.WALK.getActivityType();
                        fitnessGoalDistanceData.activityBaseUnit = ActivityBaseUnit.METERS.getActivityBaseUnit();
                        fitnessGoalDistanceData.targetedDays = 1;
                    }
                    fitnessGoalDistanceData.target = Integer.valueOf(distanceGoal);
                    Context context2 = DataSyncViewModel.this.getContext();
                    Intrinsics.checkNotNull(context2);
                    UserDataManager.getInstance(context2).saveFitnessGoalDistance(fitnessGoalDistanceData);
                }
                if (BleApiManager.getInstance(DataSyncViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isChangeCalorieGoalFromWatchSupported()) {
                    DataSyncViewModel.this.getCalorieGoalFromWatch();
                } else {
                    DataSyncViewModel.this.getCurrentStepsFromWatchL();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @NotNull
    public final MutableLiveData<LatestHealthDataModel> getHeartRateLiveDataOnPreference() {
        return this.f;
    }

    public final long getLastActivitySyncTimeStamp() {
        return this.j;
    }

    @NotNull
    public final MutableLiveData<LatestHealthDataModel> getSpo2LiveDataOnPreference() {
        return this.e;
    }

    public final void getStepGoalFromWatch() {
        BleApiManager.getInstance(this.m).getBleApi().getData(new ReadStepTargetRequest(), new DataResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$getStepGoalFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                DataSyncViewModel.this.getCurrentStepsFromWatchL();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() != null && (response.getResponseData() instanceof GetStepGoalResponse)) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetStepGoalResponse");
                    int goal = ((GetStepGoalResponse) responseData).getGoal();
                    ProfileRepository.getInstance().updateStepsTarget(DataSyncViewModel.this.getContext(), goal);
                    Context context = DataSyncViewModel.this.getContext();
                    Intrinsics.checkNotNull(context);
                    FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(context).getFitnessGoalStepsData();
                    Intrinsics.checkNotNullExpressionValue(fitnessGoalStepsData, "getInstance(context!!).fitnessGoalStepsData");
                    fitnessGoalStepsData.target = Integer.valueOf(goal);
                    Context context2 = DataSyncViewModel.this.getContext();
                    Intrinsics.checkNotNull(context2);
                    UserDataManager.getInstance(context2).saveFitnessGoalSteps(fitnessGoalStepsData);
                }
                if (BleApiManager.getInstance(DataSyncViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isChangeDistanceGoalFromWatchSupported()) {
                    DataSyncViewModel.this.getDistanceGoalFromWatch();
                } else {
                    DataSyncViewModel.this.getCurrentStepsFromWatchL();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @NotNull
    public final MutableLiveData<StepsDataModel> getStepsLiveDataOnPreference() {
        return this.d;
    }

    @NotNull
    public final SyncState getSyncState() {
        return this.i;
    }

    @NotNull
    public final MutableLiveData<SyncState> getSyncStateLiveData() {
        return this.b;
    }

    @NotNull
    public final SyncTroubleShoutListener getSyncTroubleShoutListener() {
        SyncTroubleShoutListener syncTroubleShoutListener = this.syncTroubleShoutListener;
        if (syncTroubleShoutListener != null) {
            return syncTroubleShoutListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("syncTroubleShoutListener");
        return null;
    }

    @NotNull
    public final String getTAG() {
        return this.f4260a;
    }

    @NotNull
    public final MutableLiveData<LatestHealthDataModel> getTemperatureLiveDataOnPreference() {
        return this.g;
    }

    @Nullable
    public final ViewModelListener getViewModelListener() {
        return this.n;
    }

    public final void h() {
        if (BleApiManager.getInstance(this.m).getBleApi().getDeviceSupportedFeatures().isSportsModeHistorySupported()) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isTouchELXDevice(this.m) && !companion.isMatrixDevice(this.m) && !companion.isEastApexDevice(this.m)) {
                if (this.j == 0) {
                    this.j = System.currentTimeMillis();
                } else if (System.currentTimeMillis() - this.j < 15000) {
                    LogHelper.d(this.f4260a, "System.currentTimeMillis() - lastActivitySyncTimeStamp < 15000");
                    checkForAgpsFileUpdate();
                    return;
                }
            }
            ActivityModeWithSamplesRequest activityModeWithSamplesRequest = new ActivityModeWithSamplesRequest();
            Calendar.getInstance().add(6, -4);
            activityModeWithSamplesRequest.setStartDate(UserDataManager.getInstance(this.m).getLastActivitySyncDate());
            activityModeWithSamplesRequest.setEndDate(new Date());
            ActivityModeSummaryRequest sportsDataRequest = activityModeWithSamplesRequest;
            if (!BleApiManager.getInstance(this.m).getBleApi().getDeviceSupportedFeatures().isSampleDataSupportedInSportMode()) {
                sportsDataRequest = new ActivityModeSummaryRequest.Builder().build();
            }
            SyncState syncState = this.i;
            String string = this.m.getResources().getString(R.string.syncing_activity);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr….string.syncing_activity)");
            syncState.setMessage(string);
            this.b.postValue(this.i);
            this.j = System.currentTimeMillis();
            BleApi bleApi = BleApiManager.getInstance(this.m).getBleApi();
            Intrinsics.checkNotNullExpressionValue(sportsDataRequest, "sportsDataRequest");
            bleApi.getData(sportsDataRequest, new DataResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$getActivityModeDataFromDevice$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    DataSyncViewModel.this.l(error.getErrorMsg(), new Error(error.getErrorMsg(), error.getErrorCode(), "activity"));
                    String tag = DataSyncViewModel.this.getTAG();
                    LogHelper.d(tag, "getActivityModeDataFromDevice onDataError " + error.getErrorMsg());
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull final BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    String tag = DataSyncViewModel.this.getTAG();
                    LogHelper.d(tag, "getActivityModeDataFromDevice onDataResponse " + response);
                    DataSyncViewModel.this.getSyncState().setProgress(DataSyncViewModel.this.getSyncState().getProgress() + DataSyncViewModel.this.getSyncState().getProgressByStage());
                    DataSyncViewModel.this.getSyncStateLiveData().postValue(DataSyncViewModel.this.getSyncState());
                    ActivityDataHelper activityDataHelper = ActivityDataHelper.INSTANCE;
                    Context context = DataSyncViewModel.this.getContext();
                    final DataSyncViewModel dataSyncViewModel = DataSyncViewModel.this;
                    activityDataHelper.processBleResponse(response, context, new SuccessResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$getActivityModeDataFromDevice$1$onDataResponse$1
                        @Override // com.coveiot.android.theme.SuccessResultListener
                        public void onError(@Nullable String str) {
                        }

                        @Override // com.coveiot.android.theme.SuccessResultListener
                        public void onSuccess() {
                            String tag2 = DataSyncViewModel.this.getTAG();
                            LogHelper.d(tag2, "getActivityModeDataFromDevice ActivityDataHelper.processBleResponse " + response);
                            DataSyncViewModel.this.checkForAgpsFileUpdate();
                        }
                    });
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return;
        }
        checkForAgpsFileUpdate();
    }

    public final void i() {
        BleApiManager.getInstance(this.m).getBleApi().getData(new GetLatestHealthDataRequest(HealthVitalsType.HEART_RATE), new DataResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$getLatestHeartRateData$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                String tag = DataSyncViewModel.this.getTAG();
                LogHelper.d(tag, "error getLatestHeartRateData: " + error.getErrorMsg());
                DataSyncViewModel.this.k();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                DataSyncViewModel$latestHeartRateSuccessListener$1 dataSyncViewModel$latestHeartRateSuccessListener$1;
                Intrinsics.checkNotNullParameter(response, "response");
                Object responseData = response.getResponseData();
                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetLatestHealthDataResponse");
                GetLatestHealthDataResponse getLatestHealthDataResponse = (GetLatestHealthDataResponse) responseData;
                String tag = DataSyncViewModel.this.getTAG();
                LogHelper.d(tag, "getLatestHeartRateData: " + getLatestHealthDataResponse);
                if (getLatestHealthDataResponse.getHealthData() != null) {
                    HeartRateDataHelper heartRateDataHelper = HeartRateDataHelper.INSTANCE;
                    Context context = DataSyncViewModel.this.getContext();
                    HealthData healthData = getLatestHealthDataResponse.getHealthData();
                    Intrinsics.checkNotNullExpressionValue(healthData, "getLatestHealthDataResponse.healthData");
                    dataSyncViewModel$latestHeartRateSuccessListener$1 = DataSyncViewModel.this.r;
                    heartRateDataHelper.saveLatestHeartRateDataToPreferenceFromWatch(context, healthData, dataSyncViewModel$latestHeartRateSuccessListener$1);
                }
                DataSyncViewModel.this.k();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final boolean isDataPullInProgress() {
        return this.o;
    }

    public final boolean isManualSyncAttempt() {
        return this.c;
    }

    public final void j() {
        BleApiManager.getInstance(this.m).getBleApi().getData(new GetLatestHealthDataRequest(HealthVitalsType.SPO2), new DataResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$getLatestSPO2Data$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                String tag = DataSyncViewModel.this.getTAG();
                LogHelper.d(tag, "error getLatestSPO2Data: " + error.getErrorMsg());
                DataSyncViewModel.this.i();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                DataSyncViewModel$latestSPO2SuccessListener$1 dataSyncViewModel$latestSPO2SuccessListener$1;
                Intrinsics.checkNotNullParameter(response, "response");
                Object responseData = response.getResponseData();
                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetLatestHealthDataResponse");
                GetLatestHealthDataResponse getLatestHealthDataResponse = (GetLatestHealthDataResponse) responseData;
                String tag = DataSyncViewModel.this.getTAG();
                LogHelper.d(tag, "getLatestSPO2Data: " + getLatestHealthDataResponse);
                if (getLatestHealthDataResponse.getHealthData() != null) {
                    SPO2DataHelper sPO2DataHelper = SPO2DataHelper.INSTANCE;
                    Context context = DataSyncViewModel.this.getContext();
                    HealthData healthData = getLatestHealthDataResponse.getHealthData();
                    Intrinsics.checkNotNullExpressionValue(healthData, "getLatestHealthDataResponse.healthData");
                    dataSyncViewModel$latestSPO2SuccessListener$1 = DataSyncViewModel.this.q;
                    sPO2DataHelper.saveLatestSPO2DataToPreferenceFromWatch(context, healthData, dataSyncViewModel$latestSPO2SuccessListener$1);
                }
                DataSyncViewModel.this.i();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void k() {
        if (BleApiManager.getInstance(this.m).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
            BleApiManager.getInstance(this.m).getBleApi().getData(new GetLatestHealthDataRequest(HealthVitalsType.TEMPERATURE), new DataResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$getLatestTemperatureData$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    String tag = DataSyncViewModel.this.getTAG();
                    LogHelper.d(tag, "error getLatestTemperatureData: " + error.getErrorMsg());
                    DataSyncViewModel.this.r();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    DataSyncViewModel$latestTemperatureSuccessListener$1 dataSyncViewModel$latestTemperatureSuccessListener$1;
                    Intrinsics.checkNotNullParameter(response, "response");
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetLatestHealthDataResponse");
                    GetLatestHealthDataResponse getLatestHealthDataResponse = (GetLatestHealthDataResponse) responseData;
                    String tag = DataSyncViewModel.this.getTAG();
                    LogHelper.d(tag, "getLatestTemperatureData: " + getLatestHealthDataResponse);
                    if (getLatestHealthDataResponse.getHealthData() != null) {
                        TemperatureDataHelper temperatureDataHelper = TemperatureDataHelper.INSTANCE;
                        Context context = DataSyncViewModel.this.getContext();
                        HealthData healthData = getLatestHealthDataResponse.getHealthData();
                        Intrinsics.checkNotNullExpressionValue(healthData, "getLatestHealthDataResponse.healthData");
                        dataSyncViewModel$latestTemperatureSuccessListener$1 = DataSyncViewModel.this.s;
                        temperatureDataHelper.saveLatestTemperatureDataToPreferenceFromWatch(context, healthData, dataSyncViewModel$latestTemperatureSuccessListener$1);
                    }
                    DataSyncViewModel.this.r();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        } else {
            r();
        }
    }

    public final void l(String str, Error error) {
        this.i.setProgress(0);
        this.i.setProgressByStage(0);
        this.i.setSyncing(false);
        SyncState syncState = this.i;
        Resources resources = this.m.getResources();
        int i = R.string.sync_falied;
        String string = resources.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.sync_falied)");
        syncState.setMessage(string);
        this.b.postValue(this.i);
        ViewModelListener viewModelListener = this.n;
        if (viewModelListener != null) {
            String string2 = this.m.getResources().getString(i);
            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.sync_falied)");
            viewModelListener.onDataFailure(string2);
        }
        try {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.CV_BT_DATA_SYNC.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(FirebaseEventParams.MetaData.CV_BT_STATE.getValue(), AppUtils.isBluetoothEnabled(this.m) ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
            hashMap.put(FirebaseEventParams.MetaData.CV_TIME_SPENT_MILLS.getValue(), String.valueOf(System.currentTimeMillis() - Dashboard2PreferenceManager.Companion.getInstance(this.m).getLastSyncTriggeredTimestamp()));
            String value = FirebaseEventParams.MetaData.CV_PHONE_BATTERY_LEVEL.getValue();
            Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
            hashMap.put(value, companion.getPhoneBatteryLevel(this.m));
            BatteryLevelData batteryLevelData = UserDataManager.getInstance(this.m).getBatteryLevelData();
            if (batteryLevelData != null) {
                int i2 = batteryLevelData.batteryLevel;
                String value2 = FirebaseEventParams.MetaData.CV_DVC_BATTERY_LEVEL.getValue();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%.02f", Arrays.copyOf(new Object[]{Float.valueOf(i2 / 100.0f)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                hashMap.put(value2, format);
            }
            hashMap.put(FirebaseEventParams.MetaData.CV_EVT_TRIGGER_TYPE.getValue(), this.c ? "manual" : "auto");
            hashMap.put(FirebaseEventParams.MetaData.CV_DVC_BATTERY_LEVEL.getValue(), String.valueOf(UserDataManager.getInstance(this.m).getBatteryLevelData().batteryLevel));
            hashMap.put(FirebaseEventParams.MetaData.CV_APP_PROCESS_STATUS.getValue(), "foreground");
            hashMap.put(FirebaseEventParams.MetaData.CV_DVC_CONN_STATUS.getValue(), BleApiManager.getInstance(this.m).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED ? "connected" : "disconnected");
            hashMap.put(FirebaseEventParams.MetaData.CV_STATUS.getValue(), "error");
            if (error != null) {
                if (error.getDataType() != null) {
                    String value3 = FirebaseEventParams.MetaData.CV_DATA_TYPE.getValue();
                    String dataType = error.getDataType();
                    Intrinsics.checkNotNull(dataType);
                    hashMap.put(value3, dataType);
                }
                if (companion.getSyncErrorType(error.getCode()) != null) {
                    String value4 = FirebaseEventParams.MetaData.CV_ERROR.getValue();
                    String syncErrorType = companion.getSyncErrorType(error.getCode());
                    Intrinsics.checkNotNull(syncErrorType);
                    hashMap.put(value4, syncErrorType);
                }
            }
            analyticsLog.setMapData(hashMap);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StatusApiDataHelper.Companion.getInstance(this.m).saveStatusDataToServer(true);
        if (!kotlin.text.m.equals(str, "Interrupted request", true)) {
            LogHelper.d(this.f4260a, "Sync failed  Interrupted request");
        }
        LogHelper.e(this.f4260a, String.valueOf(str));
        if (kotlin.text.m.equals(str, "PPG operation in progress", true)) {
            return;
        }
        this.k++;
        Dashboard2Utils.Companion.resetBluetoothServicesAndConfigurations(this.m);
        if (this.k < 3 || this.syncTroubleShoutListener == null) {
            return;
        }
        getSyncTroubleShoutListener().showSyncTroubleShoot();
        this.k = 0;
    }

    public final void m(ServerDataPullConfigModel serverDataPullConfigModel) {
        LogHelper.i(this.f4260a, "pullDataFromServerAndSyncFromWatch start");
        this.o = false;
        if (AppUtils.isNetConnected(this.m)) {
            BleApi bleApi = BleApiManager.getInstance(this.m).getBleApi();
            if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                if (checkIsSyncing()) {
                    LogHelper.i(this.f4260a, "Sync attempt ignored, sync is already in progress.");
                    return;
                }
                Calendar calendar = Calendar.getInstance();
                Object clone = calendar.clone();
                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                Calendar calendar2 = (Calendar) clone;
                Intrinsics.checkNotNull(serverDataPullConfigModel);
                calendar2.setTime(AppUtils.parseDate(serverDataPullConfigModel.getFromDate(), "yyyy-MM-dd"));
                this.o = true;
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new DataSyncViewModel$pullDataFromServerAndSyncFromWatch$1(this, calendar2, calendar, serverDataPullConfigModel, null), 2, null);
                return;
            }
            LogHelper.i(this.f4260a, "Watch not connected.");
            return;
        }
        LogHelper.i(this.f4260a, "Network not available.");
    }

    public final void n() {
        if (!SyncManager.getInstance().isSyncInProgress()) {
            LogHelper.d(this.f4260a, "startSyncManager->Sync started");
            SyncManager.getInstance().syncData(new SyncCompleteListner() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$startRepositorySyncManager$1

                @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$startRepositorySyncManager$1$onDataSyncComplete$1", f = "DataSyncViewModel.kt", i = {}, l = {BatteryService.EVT_BATTERY_UPDATE}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes4.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ DataSyncViewModel this$0;

                    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$startRepositorySyncManager$1$onDataSyncComplete$1$1", f = "DataSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$startRepositorySyncManager$1$a$a  reason: collision with other inner class name */
                    /* loaded from: classes4.dex */
                    public static final class C0265a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public int label;
                        public final /* synthetic */ DataSyncViewModel this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C0265a(DataSyncViewModel dataSyncViewModel, Continuation<? super C0265a> continuation) {
                            super(2, continuation);
                            this.this$0 = dataSyncViewModel;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new C0265a(this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        @Nullable
                        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                            return ((C0265a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                            if (this.label == 0) {
                                ResultKt.throwOnFailure(obj);
                                Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
                                companion.getSubscribeStatus(this.this$0.getContext());
                                companion.getUserProfile(this.this$0.getContext());
                                companion.sleepAndEnergyMeterSync(this.this$0.getContext());
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(DataSyncViewModel dataSyncViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = dataSyncViewModel;
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

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            LogHelper.d(this.this$0.getTAG(), "onDataSyncComplete");
                            if (BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getDeviceSupportedFeatures().isSedentaryAlertHistorySupported()) {
                                this.this$0.q();
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C0265a(this.this$0, null), 2, null);
                                return Unit.INSTANCE;
                            }
                            this.label = 1;
                            if (DelayKt.delay(Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        this.this$0.h();
                        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C0265a(this.this$0, null), 2, null);
                        return Unit.INSTANCE;
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$startRepositorySyncManager$1$onProgressUpdate$1", f = "DataSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes4.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ProgressDataBean $progressDataBean;
                    public int label;
                    public final /* synthetic */ DataSyncViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(DataSyncViewModel dataSyncViewModel, ProgressDataBean progressDataBean, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = dataSyncViewModel;
                        this.$progressDataBean = progressDataBean;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, this.$progressDataBean, continuation);
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
                            String tag = this.this$0.getTAG();
                            StringBuilder sb = new StringBuilder();
                            sb.append("sync_progress_precent");
                            ProgressDataBean progressDataBean = this.$progressDataBean;
                            Intrinsics.checkNotNull(progressDataBean);
                            sb.append(progressDataBean.getProgress());
                            sb.append(" stage type *** ");
                            sb.append(this.$progressDataBean.getActivityType());
                            sb.append(" total stage *** ");
                            sb.append(this.$progressDataBean.getTotalStage());
                            sb.append("  stage progress *** ");
                            sb.append(this.$progressDataBean.getPercentByStage());
                            LogHelper.d(tag, sb.toString());
                            this.this$0.getSyncState().setProgress(this.$progressDataBean.getProgress());
                            this.this$0.getSyncState().setProgressByStage(this.$progressDataBean.getPercentByStage());
                            String lowerCase = this.$progressDataBean.getActivityType().name().toLowerCase();
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                            if (lowerCase != null && StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "_", false, 2, (Object) null)) {
                                lowerCase = kotlin.text.m.replace(lowerCase, "_", HexStringBuilder.DEFAULT_SEPARATOR, true);
                            }
                            if (Intrinsics.areEqual(lowerCase, this.this$0.getContext().getResources().getString(R.string.sync_heartrate))) {
                                lowerCase = this.this$0.getContext().getResources().getString(R.string.sync_heart_rate);
                                Intrinsics.checkNotNullExpressionValue(lowerCase, "context.resources.getStr…R.string.sync_heart_rate)");
                            }
                            SyncState syncState = this.this$0.getSyncState();
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(this.this$0.getContext().getResources().getString(R.string.syncing));
                            sb2.append(' ');
                            sb2.append(lowerCase != null ? kotlin.text.m.capitalize(lowerCase) : null);
                            sb2.append("..");
                            syncState.setMessage(sb2.toString());
                            this.this$0.getSyncStateLiveData().postValue(this.this$0.getSyncState());
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                public void onDataSyncComplete() {
                    kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(DataSyncViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                public void onFailure(@Nullable String str, @Nullable Error error) {
                    String tag = DataSyncViewModel.this.getTAG();
                    LogHelper.d(tag, "startRepositorySyncManager onFailure " + str);
                    DataSyncViewModel.this.l(str, error);
                }

                @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
                    kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(DataSyncViewModel.this), Dispatchers.getIO(), null, new b(DataSyncViewModel.this, progressDataBean, null), 2, null);
                }
            }, true);
            SessionManager.getInstance(this.m).setIsFromOnboarding(false);
            return;
        }
        LogHelper.d(this.f4260a, "startSyncManager->Sync is already in progress.");
    }

    public final void p() {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isCADevice(this.m) && !companion.isCYDevice(this.m) && !companion.isCZDevice(this.m) && !companion.isPS1Device(this.m) && !companion.isBESDevice(this.m)) {
            r();
        } else {
            j();
        }
    }

    public final void postDataSyncComplete() {
        this.i.setProgress(0);
        this.i.setProgressByStage(0);
        this.i.setSyncing(false);
        SyncState syncState = this.i;
        String string = this.m.getResources().getString(R.string.synced_successfully);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.synced_successfully)");
        syncState.setMessage(string);
        Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
        companion.getInstance(this.m).saveLastCompleteDataSyncTimestamp(System.currentTimeMillis());
        this.i.setLastDataSyncTimestamp(companion.getInstance(this.m).getLastCompleteDataSyncTimestamp());
        this.b.postValue(this.i);
        ViewModelListener viewModelListener = this.n;
        if (viewModelListener != null) {
            viewModelListener.onSuccess();
        }
        try {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.CV_BT_DATA_SYNC.getValue());
            HashMap<String, String> hashMap = new HashMap<>();
            analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
            hashMap.put(FirebaseEventParams.MetaData.CV_BT_STATE.getValue(), AppUtils.isBluetoothEnabled(this.m) ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
            hashMap.put(FirebaseEventParams.MetaData.CV_TIME_SPENT_MILLS.getValue(), String.valueOf(System.currentTimeMillis() - companion.getInstance(this.m).getLastSyncTriggeredTimestamp()));
            hashMap.put(FirebaseEventParams.MetaData.CV_PHONE_BATTERY_LEVEL.getValue(), Dashboard2Utils.Companion.getPhoneBatteryLevel(this.m));
            hashMap.put(FirebaseEventParams.MetaData.CV_EVT_TRIGGER_TYPE.getValue(), this.c ? "manual" : "auto");
            hashMap.put(FirebaseEventParams.MetaData.CV_DVC_BATTERY_LEVEL.getValue(), String.valueOf(UserDataManager.getInstance(this.m).getBatteryLevelData().batteryLevel));
            hashMap.put(FirebaseEventParams.MetaData.CV_APP_PROCESS_STATUS.getValue(), "foreground");
            hashMap.put(FirebaseEventParams.MetaData.CV_DVC_CONN_STATUS.getValue(), BleApiManager.getInstance(this.m).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED ? "connected" : "disconnected");
            hashMap.put(FirebaseEventParams.MetaData.CV_STATUS.getValue(), "ok");
            analyticsLog.setMapData(hashMap);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogHelper.d(this.f4260a, "Sync completed twice");
        if (this.syncTroubleShoutListener != null) {
            getSyncTroubleShoutListener().dismissSyncTroubleShoot();
        }
        this.k = 0;
        Boolean isWatchReconnected = SessionManager.getInstance(this.m).getIsWatchReconnected();
        Intrinsics.checkNotNullExpressionValue(isWatchReconnected, "getInstance(context).isWatchReconnected");
        if (isWatchReconnected.booleanValue()) {
            SessionManager.getInstance(this.m).saveIsWatchReconnected(Boolean.FALSE);
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(null), 2, null);
        }
        if (DeviceUtils.Companion.isSmaDevice(this.m)) {
            LastDataHelper.Companion.getInstance(this.m).sendSleepDataToBand(new LastDataHelper.UploadCompletionListner() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$postDataSyncComplete$3
                @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
                public void onDataUploadeComplete() {
                }

                @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
                public void onUploadFailed() {
                }
            });
        }
        LastDataHelper.Companion.getInstance(this.m).saveLastDataInfoToServer(new LastDataHelper.UploadCompletionListner() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$postDataSyncComplete$4
            @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
            public void onDataUploadeComplete() {
                LogHelper.d(DataSyncViewModel.this.getTAG(), "postDataSyncComplete saveLastDataInfoToServer onDataUploadeComplete");
            }

            @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
            public void onUploadFailed() {
                LogHelper.d(DataSyncViewModel.this.getTAG(), "postDataSyncComplete saveLastDataInfoToServer onUploadFailed");
            }
        });
    }

    public final void q() {
        SedentarySettings sedentarySettings;
        SyncState syncState = this.i;
        String string = this.m.getResources().getString(R.string.syncing_sedentary);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…string.syncing_sedentary)");
        syncState.setMessage(string);
        this.b.postValue(this.i);
        SedentaryReminderData sedentaryReminderData = UserDataManager.getInstance(this.m).getSedentaryReminderData();
        if (sedentaryReminderData != null) {
            sedentarySettings = new SedentarySettings();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%02d:%02d:00", Arrays.copyOf(new Object[]{Integer.valueOf(sedentaryReminderData.getBeggining_time_hour()), Integer.valueOf(sedentaryReminderData.getBeggining_time_minutes())}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            sedentarySettings.setStartTime(format);
            String format2 = String.format("%02d:%02d:00", Arrays.copyOf(new Object[]{Integer.valueOf(sedentaryReminderData.getEnd_time_hour()), Integer.valueOf(sedentaryReminderData.getEnd_time_minutes())}, 2));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            sedentarySettings.setEndTime(format2);
            sedentarySettings.setInterval(SettingsSyncHelper.Companion.getInstance(this.m).getConvertedInterval(sedentaryReminderData.getRemind_in()));
            sedentarySettings.setActive(sedentaryReminderData.getAlarm_on_off());
        } else {
            sedentarySettings = null;
        }
        SyncManager.getInstance().syncSedentaryData(new SyncCompleteListner() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$syncSedentaryReminder$1

            @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$syncSedentaryReminder$1$onDataSyncComplete$1", f = "DataSyncViewModel.kt", i = {}, l = {ITaskSet.EVT_SUBTASK_START}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes4.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ DataSyncViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(DataSyncViewModel dataSyncViewModel, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = dataSyncViewModel;
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

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        LogHelper.d(this.this$0.getTAG(), "syncSedentaryData->onDataSyncComplete");
                        this.this$0.getSyncState().setProgress(this.this$0.getSyncState().getProgress() + this.this$0.getSyncState().getProgressByStage());
                        this.this$0.getSyncStateLiveData().postValue(this.this$0.getSyncState());
                        this.label = 1;
                        if (DelayKt.delay(Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    LogHelper.d(this.this$0.getTAG(), "syncSedentaryData->activity sync triggered");
                    this.this$0.h();
                    return Unit.INSTANCE;
                }
            }

            @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$syncSedentaryReminder$1$onFailure$1", f = "DataSyncViewModel.kt", i = {}, l = {835}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes4.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ DataSyncViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(DataSyncViewModel dataSyncViewModel, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = dataSyncViewModel;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new b(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.getSyncState().setProgress(this.this$0.getSyncState().getProgress() + this.this$0.getSyncState().getProgressByStage());
                        this.this$0.getSyncStateLiveData().postValue(this.this$0.getSyncState());
                        this.label = 1;
                        if (DelayKt.delay(5000L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    this.this$0.h();
                    return Unit.INSTANCE;
                }
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
            public void onDataSyncComplete() {
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(DataSyncViewModel.this, null), 2, null);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
            public void onFailure(@Nullable String str, @Nullable Error error) {
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new b(DataSyncViewModel.this, null), 2, null);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
            public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
            }
        }, sedentarySettings);
    }

    public final void r() {
        BleApi bleApi = BleApiManager.getInstance(this.m).getBleApi();
        if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
            if (DeviceUtils.Companion.isTouchELXDevice(this.m)) {
                Thread.sleep(1000L);
            }
            if (BleApiManager.getInstance(this.m).getBleApi().getDeviceSupportedFeatures().isBatteryLevelRequestSupported()) {
                BleApiManager.getInstance(this.m).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$triggerSync$1
                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onDataError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        if (DeviceUtils.Companion.isMatrixDevice(DataSyncViewModel.this.getContext())) {
                            LogHelper.d(DataSyncViewModel.this.getTAG(), "BatteryLevelRequest onDataError Matrix");
                            DataSyncViewModel.this.n();
                            return;
                        }
                        DataSyncViewModel.this.getSyncState().setSyncing(false);
                        DataSyncViewModel.this.getSyncState().setProgress(0);
                        DataSyncViewModel.this.getSyncState().setProgressByStage(0);
                        SyncState syncState = DataSyncViewModel.this.getSyncState();
                        Resources resources = DataSyncViewModel.this.getContext().getResources();
                        int i = R.string.sync_falied;
                        String string = resources.getString(i);
                        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.sync_falied)");
                        syncState.setMessage(string);
                        DataSyncViewModel.this.getSyncStateLiveData().postValue(DataSyncViewModel.this.getSyncState());
                        ViewModelListener viewModelListener = DataSyncViewModel.this.getViewModelListener();
                        if (viewModelListener != null) {
                            String string2 = DataSyncViewModel.this.getContext().getResources().getString(i);
                            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.sync_falied)");
                            viewModelListener.onDataFailure(string2);
                        }
                        String tag = DataSyncViewModel.this.getTAG();
                        LogHelper.d(tag, "Sync Failed syncDataFromRepo error " + error.getErrorMsg());
                        DataSyncViewModel.this.l("Battery command failed", new Error("Battery command", 1, "Battery"));
                        DataSyncViewModel.this.getBatteryRequestFailure().postValue(Boolean.TRUE);
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onDataResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new DataSyncViewModel$triggerSync$1$onDataResponse$1(response, DataSyncViewModel.this, null), 2, null);
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onProgressUpdate(@NotNull ProgressData progress) {
                        Intrinsics.checkNotNullParameter(progress, "progress");
                    }
                });
                return;
            }
            LogHelper.d(this.f4260a, "isBatteryLevelRequestSupported -> false");
            if (BleApiManager.getInstance(this.m).getBleApi().getDeviceSupportedFeatures().isSyncBandSettingsSupported()) {
                SettingsSyncHelper.Companion.getInstance(this.m).syncBandSettings(new SettingsSyncHelper.SettingsSyncListner() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$triggerSync$2
                    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                    public void onProgressUpdate(int i) {
                    }

                    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                    public void onSettingSyncError() {
                        DataSyncViewModel.this.n();
                    }

                    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                    public void onSettingsSyncCompleted() {
                        DataSyncViewModel.this.n();
                    }
                });
                return;
            }
            LogHelper.d(this.f4260a, "isSyncBandSettingsSupported -> false");
            n();
        }
    }

    public final void resetSyncState() {
        SyncManager.getInstance().resetSyncProgress();
        SyncState value = this.b.getValue();
        if (value != null) {
            value.setSyncing(false);
        }
        this.i.setProgress(0);
        this.i.setProgressByStage(0);
        this.i.setMessage("");
        MutableLiveData<SyncState> mutableLiveData = this.b;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(this.i);
        }
    }

    public final void setBatteryLiveData(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }

    public final void setBatteryRequestFailure(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.l = mutableLiveData;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.m = context;
    }

    public final void setDataPullInProgress(boolean z) {
        this.o = z;
    }

    public final void setDataSyncFailureCount(int i) {
        this.k = i;
    }

    public final void setHeartRateLiveDataOnPreference(@NotNull MutableLiveData<LatestHealthDataModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void setLastActivitySyncTimeStamp(long j) {
        this.j = j;
    }

    public final void setManualSyncAttempt(boolean z) {
        this.c = z;
    }

    public final void setSpo2LiveDataOnPreference(@NotNull MutableLiveData<LatestHealthDataModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setStepsLiveDataOnPreference(@NotNull MutableLiveData<StepsDataModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setSyncState(@NotNull SyncState syncState) {
        Intrinsics.checkNotNullParameter(syncState, "<set-?>");
        this.i = syncState;
    }

    public final void setSyncTroubleShoutListener(@NotNull SyncTroubleShoutListener syncTroubleShoutListener) {
        Intrinsics.checkNotNullParameter(syncTroubleShoutListener, "<set-?>");
        this.syncTroubleShoutListener = syncTroubleShoutListener;
    }

    public final void setTemperatureLiveDataOnPreference(@NotNull MutableLiveData<LatestHealthDataModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setViewModelListener(@Nullable ViewModelListener viewModelListener) {
        this.n = viewModelListener;
    }

    public final void syncData(boolean z, @Nullable ServerDataPullConfigModel serverDataPullConfigModel) {
        LogHelper.i(this.f4260a, "checkAndStartSync start");
        if (serverDataPullConfigModel != null) {
            String versionTag = serverDataPullConfigModel.getVersionTag();
            boolean z2 = false;
            if (!(versionTag == null || versionTag.length() == 0)) {
                String fromDate = serverDataPullConfigModel.getFromDate();
                if (!(fromDate == null || fromDate.length() == 0)) {
                    ServerDataPullConfigModel serverSyncConfigModel = Dashboard2PreferenceManager.Companion.getInstance(this.m).getServerSyncConfigModel();
                    if (serverSyncConfigModel != null) {
                        String versionTag2 = serverSyncConfigModel.getVersionTag();
                        if (versionTag2 == null || versionTag2.length() == 0) {
                            z2 = true;
                        }
                        if (!z2) {
                            if (!kotlin.text.m.equals(serverDataPullConfigModel.getVersionTag(), serverSyncConfigModel.getVersionTag(), true)) {
                                if (!this.o) {
                                    m(serverDataPullConfigModel);
                                    return;
                                } else {
                                    LogHelper.i(this.f4260a, "Data pull from server is in progress.");
                                    return;
                                }
                            }
                            syncData(z);
                            return;
                        }
                    }
                    if (!this.o) {
                        m(serverDataPullConfigModel);
                        return;
                    } else {
                        LogHelper.i(this.f4260a, "Data pull from server is in progress.");
                        return;
                    }
                }
            }
        }
        syncData(z);
    }

    public final void updateAgpsFile() {
        File file = new File(this.m.getFilesDir(), "Jstyle_AGPS.bin");
        AgpsUpdateRequest agpsUpdateRequest = new AgpsUpdateRequest();
        agpsUpdateRequest.setAgpsFilePath(file.getAbsolutePath());
        BleApiManager.getInstance(this.m).getBleApi().getData(agpsUpdateRequest, new DataResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$updateAgpsFile$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(DataSyncViewModel.this.getTAG(), "agps_upload_failed");
                if (Intrinsics.areEqual(error.getErrorMsg(), DataSyncViewModel.this.getContext().getResources().getString(R.string.error_device_connected_msg))) {
                    UserDataManager.getInstance(DataSyncViewModel.this.getContext()).saveDisconnectionAtAgpsFileUpdate(true);
                    DataSyncViewModel.this.getSyncState().setSyncing(false);
                    DataSyncViewModel.this.getSyncStateLiveData().postValue(DataSyncViewModel.this.getSyncState());
                    return;
                }
                DataSyncViewModel.this.getSyncState().setProgress(DataSyncViewModel.this.getSyncState().getProgress() + DataSyncViewModel.this.getSyncState().getProgressByStage());
                DataSyncViewModel.this.getSyncStateLiveData().postValue(DataSyncViewModel.this.getSyncState());
                DataSyncViewModel.this.postDataSyncComplete();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                DataSyncViewModel.this.getSyncState().setProgress(DataSyncViewModel.this.getSyncState().getProgress() + DataSyncViewModel.this.getSyncState().getProgressByStage());
                DataSyncViewModel.this.getSyncStateLiveData().postValue(DataSyncViewModel.this.getSyncState());
                LogHelper.d(DataSyncViewModel.this.getTAG(), "agps_uploaded_success");
                DataSyncViewModel.this.postDataSyncComplete();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
        Thread.sleep(5000L);
        SyncState syncState = this.i;
        String string = this.m.getResources().getString(R.string.updating_agps_file);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…tring.updating_agps_file)");
        syncState.setMessage(string);
        this.b.postValue(this.i);
    }

    public final void syncData(boolean z) {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new b(z, null), 2, null);
    }
}
