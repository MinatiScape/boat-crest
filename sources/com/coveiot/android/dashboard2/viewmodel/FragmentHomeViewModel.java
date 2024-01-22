package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchDataModel;
import com.coveiot.android.dashboard2.model.FitnessVitalsDataModel;
import com.coveiot.android.dashboard2.model.SelectedFitnessVitalsDataModel;
import com.coveiot.android.dashboard2.model.SetupYourWatchDataModel;
import com.coveiot.android.dashboard2.util.FitnessVitalsHelper;
import com.coveiot.android.dashboard2.util.SetupYourWatchDataHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.idoSdk.IDOConstants;
import com.coveiot.android.theme.utils.BT3Utils;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.boatcoins.BoatCoinsApiManager;
import com.coveiot.coveaccess.boatcoins.model.BoatCoinsDetailsResponse;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FeatureMapping;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
import java.util.Collection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FragmentHomeViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4275a;
    @NotNull
    public final String b;
    @NotNull
    public final MutableLiveData<Integer> c;
    @NotNull
    public final MutableLiveData<List<SetupYourWatchDataModel>> d;
    @NotNull
    public final MutableLiveData<SelectedFitnessVitalsDataModel> e;
    @NotNull
    public final MutableLiveData<List<DoMoreWithYourWatchDataModel>> f;
    @NotNull
    public MutableLiveData<BoatCoinsDetailsResponse> g;
    @NotNull
    public MutableLiveData<Boolean> h;
    @NotNull
    public MutableLiveData<Boolean> i;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel$getBT3ConnectionStatus$1", f = "FragmentHomeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            DeviceSupportedFeatures deviceSupportedFeatures;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                BleApi bleApi = BleApiManager.getInstance(FragmentHomeViewModel.this.getContext()).getBleApi();
                String str = null;
                Boolean boxBoolean = (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boxing.boxBoolean(deviceSupportedFeatures.isKahaBTCallSupported());
                Intrinsics.checkNotNull(boxBoolean);
                if (boxBoolean.booleanValue()) {
                    str = UserDataManager.getInstance(FragmentHomeViewModel.this.getContext()).getConnectedBTCallDeviceMac();
                } else {
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    if (companion.isTouchELXDevice(FragmentHomeViewModel.this.getContext())) {
                        str = PreferenceManagerAbstract.getInstance(FragmentHomeViewModel.this.getContext()).getConnectedDeviceBT3MacAddress();
                    } else if (companion.isEastApexDevice(FragmentHomeViewModel.this.getContext())) {
                        str = BleApiManager.getInstance(FragmentHomeViewModel.this.getContext()).getBleApi().getMacAddress();
                    }
                }
                if (str == null) {
                    str = BleApiManager.getInstance(FragmentHomeViewModel.this.getContext()).getBleApi().getMacAddress();
                }
                if (!(str == null || str.length() == 0)) {
                    FragmentHomeViewModel.this.c.postValue(BT3Utils.getBTDeviceBondedState(UserDataManager.getInstance(FragmentHomeViewModel.this.getContext()).getConnectedBTCallDeviceMac(), FragmentHomeViewModel.this.getContext()));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel$getDoMoreWithYourWatchData$1", f = "FragmentHomeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
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
                List<DoMoreWithYourWatchDataModel> doMoreWithYourWatchData = Dashboard2PreferenceManager.Companion.getInstance(FragmentHomeViewModel.this.getContext()).getDoMoreWithYourWatchData();
                FragmentHomeViewModel.this.f.postValue(doMoreWithYourWatchData != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) doMoreWithYourWatchData) : null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel$getSetupYourWatchData$1", f = "FragmentHomeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentHomeViewModel.this.d.postValue(SetupYourWatchDataHelper.INSTANCE.getSetupYourWatchData(FragmentHomeViewModel.this.getContext()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public FragmentHomeViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4275a = context;
        this.b = "FragmentHomeViewModel";
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        new MutableLiveData();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.i = new MutableLiveData<>();
    }

    public static final void g(Exception it) {
        Intrinsics.checkNotNullParameter(it, "it");
    }

    public static final void h(final FirebaseRemoteConfig remoteConfig, final FragmentHomeViewModel this$0, final Context context, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Void r4 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.dashboard2.viewmodel.c
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    FragmentHomeViewModel.i(FirebaseRemoteConfig.this, this$0, context, task2);
                }
            });
            return;
        }
        LogHelper.e(this$0.b, "Remote Config Failed");
    }

    public static final void i(FirebaseRemoteConfig remoteConfig, FragmentHomeViewModel this$0, Context context, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(ThemeConstants.FEATURE_MAPPING.getValue());
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(T…ts.FEATURE_MAPPING.value)");
        String str = this$0.b;
        LogHelper.d(str, "feature mapping updated: " + string);
        if (string.length() == 0) {
            return;
        }
        FeatureMapping featureMapping = (FeatureMapping) new Gson().fromJson(string, (Class<Object>) FeatureMapping.class);
        String str2 = this$0.b;
        LogHelper.d(str2, "feature mapping updated: " + featureMapping);
        String str3 = ((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getmModelNumber();
        if (featureMapping != null && featureMapping.getIndusindBankBranding() != null && featureMapping.getIndusindBankBranding().getWellnessCrew() != null) {
            if (featureMapping.getIndusindBankBranding().getWellnessCrew().contains(str3)) {
                SessionManager.getInstance(context).showIndusInd(true);
                this$0.i.postValue(Boolean.TRUE);
                return;
            }
            SessionManager.getInstance(context).showIndusInd(false);
            this$0.i.postValue(Boolean.FALSE);
            return;
        }
        SessionManager.getInstance(context).showIndusInd(false);
        this$0.i.postValue(Boolean.FALSE);
    }

    public static final void j(Exception it) {
        Intrinsics.checkNotNullParameter(it, "it");
    }

    public static final void k(final FirebaseRemoteConfig remoteConfig, final FragmentHomeViewModel this$0, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r4 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.dashboard2.viewmodel.e
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    FragmentHomeViewModel.l(FirebaseRemoteConfig.this, this$0, r4, task2);
                }
            });
            return;
        }
        LogHelper.e(this$0.b, "Remote Config Failed");
    }

    public static final void l(FirebaseRemoteConfig remoteConfig, FragmentHomeViewModel this$0, Void r4, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(ThemeConstants.FITNESS_CHALLENGES.getValue());
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(T…FITNESS_CHALLENGES.value)");
        FitnessChallengeRemoteConfiguration fitnessChallengeRemoteConfiguration = (FitnessChallengeRemoteConfiguration) new Gson().fromJson(string, (Class<Object>) FitnessChallengeRemoteConfiguration.class);
        if (fitnessChallengeRemoteConfiguration != null) {
            this$0.h.postValue(fitnessChallengeRemoteConfiguration.getFitness_challenges().getVisibility());
            FitnessChallengeSessionManager.getInstance(this$0.f4275a).saveFitnessChallengeRemoteConfig(fitnessChallengeRemoteConfiguration);
        }
        String str = this$0.b;
        LogHelper.d(str, "Config params updated: " + r4 + ' ' + string);
    }

    public final void getBT3ConnectionStatus() {
        if (BleApiManager.getInstance(this.f4275a).getBleApi().getDeviceSupportedFeatures().isKahaBTCallSupported()) {
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new a(null), 2, null);
        }
    }

    @NotNull
    public final LiveData<Integer> getBT3ConnectionStatusLiveData() {
        return this.c;
    }

    public final void getBoatCoinsDetails() {
        BoatCoinsApiManager.getBoatCoinsDetails(new CoveApiListener<BoatCoinsDetailsResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel$getBoatCoinsDetails$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull BoatCoinsDetailsResponse boatCoinsDetailsResponse) {
                Intrinsics.checkNotNullParameter(boatCoinsDetailsResponse, "boatCoinsDetailsResponse");
                FragmentHomeViewModel.this.getGetBoatCoinsDetails().postValue(boatCoinsDetailsResponse);
            }
        });
    }

    @Nullable
    public final LiveData<ConnectionStatus> getConnectionChangeLiveData() {
        BleApi bleApi = BleApiManager.getInstance(this.f4275a).getBleApi();
        if (bleApi != null) {
            return bleApi.registerConnectionStatus();
        }
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f4275a;
    }

    public final void getDoMoreWithYourWatchData() {
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new b(null), 2, null);
    }

    @NotNull
    public final MutableLiveData<List<DoMoreWithYourWatchDataModel>> getDoMoreWithYourWatchLiveData() {
        return this.f;
    }

    public final void getFeatureMappingRemoteConfig(@NotNull final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.dashboard2.viewmodel.g
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                FragmentHomeViewModel.g(exc);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.dashboard2.viewmodel.d
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FragmentHomeViewModel.h(FirebaseRemoteConfig.this, this, context, task);
            }
        });
    }

    public final void getFitnessChallengeFirebaseConfig() {
        if (this.f4275a != null) {
            final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
            remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.dashboard2.viewmodel.f
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    FragmentHomeViewModel.j(exc);
                }
            }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.dashboard2.viewmodel.b
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    FragmentHomeViewModel.k(FirebaseRemoteConfig.this, this, task);
                }
            });
        }
    }

    @NotNull
    public final MutableLiveData<Boolean> getFitnessChallengeVisibility() {
        return this.h;
    }

    @NotNull
    public final MutableLiveData<BoatCoinsDetailsResponse> getGetBoatCoinsDetails() {
        return this.g;
    }

    public final void getSelectedFitnessVitals() {
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new FragmentHomeViewModel$getSelectedFitnessVitals$1(this, null), 2, null);
    }

    @NotNull
    public final MutableLiveData<SelectedFitnessVitalsDataModel> getSelectedFitnessVitalsLiveData() {
        return this.e;
    }

    public final void getSetupYourWatchData() {
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new c(null), 2, null);
    }

    @NotNull
    public final LiveData<List<SetupYourWatchDataModel>> getSetupYourWatchLiveData() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Boolean> getShouldShowIndusIndLogo() {
        return this.i;
    }

    public final void m() {
        SelectedFitnessVitalsDataModel selectedFitnessVitals = Dashboard2PreferenceManager.Companion.getInstance(this.f4275a).getSelectedFitnessVitals();
        for (FitnessVitalsDataModel fitnessVitalsDataModel : selectedFitnessVitals.getFitnessDataModels()) {
            String fitnessVitalType = fitnessVitalsDataModel.getFitnessVitalType();
            switch (fitnessVitalType.hashCode()) {
                case -1962477464:
                    if (fitnessVitalType.equals("AMBIENT_SOUND")) {
                        fitnessVitalsDataModel.setSelected(FitnessVitalsHelper.INSTANCE.isAmbientSoundSupported(this.f4275a));
                        break;
                    } else {
                        break;
                    }
                case -1849873063:
                    if (fitnessVitalType.equals(IDOConstants.DATA_TYPE_HEART_RATE)) {
                        fitnessVitalsDataModel.setSelected(FitnessVitalsHelper.INSTANCE.isHeartRateSupported(this.f4275a));
                        break;
                    } else {
                        break;
                    }
                case -1838660172:
                    if (fitnessVitalType.equals("STRESS")) {
                        fitnessVitalsDataModel.setSelected(FitnessVitalsHelper.INSTANCE.isStressSupported(this.f4275a));
                        break;
                    } else {
                        break;
                    }
                case -1820305068:
                    if (fitnessVitalType.equals("TEMPERATURE")) {
                        fitnessVitalsDataModel.setSelected(FitnessVitalsHelper.INSTANCE.isTemperatureSupported(this.f4275a));
                        break;
                    } else {
                        break;
                    }
                case -1606469902:
                    if (fitnessVitalType.equals("ENERGY_METER")) {
                        fitnessVitalsDataModel.setSelected(FitnessVitalsHelper.INSTANCE.isEnergyMeterSupported(this.f4275a));
                        break;
                    } else {
                        break;
                    }
                case 2126:
                    if (fitnessVitalType.equals(EcgStyleReportUtil.UserInfoKey.BP)) {
                        fitnessVitalsDataModel.setSelected(FitnessVitalsHelper.INSTANCE.isBpSupported(this.f4275a));
                        break;
                    } else {
                        break;
                    }
                case 71820:
                    if (fitnessVitalType.equals("HRV")) {
                        fitnessVitalsDataModel.setSelected(FitnessVitalsHelper.INSTANCE.isHRVSupported(this.f4275a));
                        break;
                    } else {
                        break;
                    }
                case 77086:
                    if (fitnessVitalType.equals("NBR")) {
                        fitnessVitalsDataModel.setSelected(FitnessVitalsHelper.INSTANCE.isRespiratoryRateSupported(this.f4275a));
                        break;
                    } else {
                        break;
                    }
                case 2552032:
                    if (fitnessVitalType.equals("SPO2")) {
                        fitnessVitalsDataModel.setSelected(FitnessVitalsHelper.INSTANCE.isSPO2Supported(this.f4275a));
                        break;
                    } else {
                        break;
                    }
            }
        }
        this.e.postValue(selectedFitnessVitals);
    }

    public final void setFitnessChallengeVisibility(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }

    public final void setGetBoatCoinsDetails(@NotNull MutableLiveData<BoatCoinsDetailsResponse> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setShouldShowIndusIndLogo(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.i = mutableLiveData;
    }
}
