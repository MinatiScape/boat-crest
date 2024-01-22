package com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.activitymodes.models.ActivitiesItem;
import com.coveiot.android.activitymodes.models.ActivityIcons;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveFitness;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.fitness.ActivityBaseUnit;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.config.FitnessConfigApi;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessSummaryResponse;
import com.coveiot.coveaccess.fitness.model.GetFitnessGoalResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SRemoteConfigResponse;
import com.coveiot.coveaccess.onboarding.model.ModifyPhoneNumberReq;
import com.coveiot.coveaccess.onboarding.model.ModifyPhoneNumberRes;
import com.coveiot.coveaccess.onboarding.model.PhoneNumberVerificationReq;
import com.coveiot.coveaccess.onboarding.model.PhoneNumberVerificationRes;
import com.coveiot.coveaccess.onboarding.model.RegisterExistingUserReq;
import com.coveiot.coveaccess.onboarding.model.RegisterExistingUserRes;
import com.coveiot.coveaccess.onboarding.model.RemoveUserReq;
import com.coveiot.coveaccess.onboarding.model.RemoveUserRes;
import com.coveiot.coveaccess.onboarding.model.UserByPhoneNumberReq;
import com.coveiot.coveaccess.onboarding.model.UserByPhoneNumberRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.GoalSettingsData;
import com.coveiot.covepreferences.data.NameDetails;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.WeeklyReportPrefData;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityPhoneValidationViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5293a;
    @Nullable
    public UserDataManager b;
    @Nullable
    public SessionManager c;
    @Nullable
    public String d;
    @Nullable
    public ContractPhoneValidation e;
    @Nullable
    public MutableLiveData<Integer> f;
    public final String g;

    /* loaded from: classes5.dex */
    public interface OTPVerification {
        void onError(@NotNull String str, int i);

        void onExistingUser(@NotNull ProfileData profileData, @NotNull NameDetails nameDetails);

        void onNewUser();
    }

    /* loaded from: classes5.dex */
    public interface SendOTPCallback {
        void onFailure(@NotNull String str);

        void onSuccess();
    }

    public ActivityPhoneValidationViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5293a = context;
        this.f = new MutableLiveData<>();
        this.g = ActivityPhoneValidationViewModel.class.getSimpleName();
        this.f = new MutableLiveData<>();
        if (context instanceof ContractPhoneValidation) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation");
            this.e = (ContractPhoneValidation) context;
        }
        this.c = SessionManager.getInstance(context);
        this.b = UserDataManager.getInstance(context);
    }

    public final void callNewUserApi(@NotNull String phoneNumber) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ActivityPhoneValidationViewModel$callNewUserApi$1(phoneNumber, this, null), 2, null);
    }

    public final void callRemoveUserAPI(@NotNull ProfileData profileData) {
        Intrinsics.checkNotNullParameter(profileData, "profileData");
        String mobileNumber = profileData.getMobileNumber();
        CoveOnboarding.removeUser(new RemoveUserReq(mobileNumber, profileData.getUserId() + ""), new CoveApiListener<RemoveUserRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$callRemoveUserAPI$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                ContractPhoneValidation contractPhoneValidation = ActivityPhoneValidationViewModel.this.getContractPhoneValidation();
                Intrinsics.checkNotNull(contractPhoneValidation);
                contractPhoneValidation.onRemoveExistingUser(false);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull RemoveUserRes removeUserRes) {
                Intrinsics.checkNotNullParameter(removeUserRes, "removeUserRes");
                if (removeUserRes.getCode() == 200) {
                    if (m.equals(removeUserRes.getStatus(), CoveApiConstants.RESPONSE_STATUS_VALUE_OK, true)) {
                        ContractPhoneValidation contractPhoneValidation = ActivityPhoneValidationViewModel.this.getContractPhoneValidation();
                        Intrinsics.checkNotNull(contractPhoneValidation);
                        contractPhoneValidation.onRemoveExistingUser(true);
                        return;
                    }
                    ContractPhoneValidation contractPhoneValidation2 = ActivityPhoneValidationViewModel.this.getContractPhoneValidation();
                    Intrinsics.checkNotNull(contractPhoneValidation2);
                    contractPhoneValidation2.onRemoveExistingUser(false, removeUserRes.getMessage());
                    return;
                }
                ContractPhoneValidation contractPhoneValidation3 = ActivityPhoneValidationViewModel.this.getContractPhoneValidation();
                Intrinsics.checkNotNull(contractPhoneValidation3);
                contractPhoneValidation3.onRemoveExistingUser(false);
            }
        });
    }

    public final void callReturningUserAPI(@NotNull final ProfileData profileData) {
        Intrinsics.checkNotNullParameter(profileData, "profileData");
        RegisterExistingUserReq registerExistingUserReq = new RegisterExistingUserReq();
        registerExistingUserReq.setName(profileData.getName());
        registerExistingUserReq.setEmailId(profileData.getEmail());
        registerExistingUserReq.setMobileNumber(profileData.getMobileNumber());
        registerExistingUserReq.setUserId(profileData.getUserId() + "");
        registerExistingUserReq.setGender(profileData.getGender());
        registerExistingUserReq.setBirthDate(profileData.getDob());
        if (SessionManager.getInstance(this.f5293a).getGuestSessionId() != null) {
            registerExistingUserReq.setGuestUserSessionId(SessionManager.getInstance(this.f5293a).getGuestSessionId());
        }
        registerExistingUserReq.setDeviceID(Settings.Secure.getString(this.f5293a.getContentResolver(), "android_id"));
        Object systemService = this.f5293a.getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothManager bluetoothManager = (BluetoothManager) systemService;
        if (Build.VERSION.SDK_INT < 31) {
            registerExistingUserReq.setBleId(bluetoothManager.getAdapter().getAddress());
        }
        CoveOnboarding.registerExistingUser(registerExistingUserReq, new CoveApiListener<RegisterExistingUserRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$callReturningUserAPI$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                Intrinsics.checkNotNullParameter(object, "object");
                ContractPhoneValidation contractPhoneValidation = this.getContractPhoneValidation();
                Intrinsics.checkNotNull(contractPhoneValidation);
                contractPhoneValidation.onReturningUser(false);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull RegisterExistingUserRes object) {
                SessionManager sessionManager;
                Intrinsics.checkNotNullParameter(object, "object");
                if (object.getCode() == 200) {
                    ProfileData.getInstance().setAppTrackerId(object.getAppTrackerId());
                    ProfileRepository.getInstance().updateGender(this.getContext(), !m.equals(ProfileData.this.getGender(), AppConstants.MALE.getValue(), true) ? 1 : 0);
                    if (!AppUtils.isEmpty(ProfileData.this.getDob())) {
                        ProfileRepository.getInstance().updateAge(this.getContext(), AppUtils.getAge(ProfileData.this.getDob()));
                    }
                    ContractPhoneValidation contractPhoneValidation = this.getContractPhoneValidation();
                    Intrinsics.checkNotNull(contractPhoneValidation);
                    contractPhoneValidation.onReturningUser(true);
                    sessionManager = this.c;
                    Intrinsics.checkNotNull(sessionManager);
                    sessionManager.createLoginSession(ProfileData.getInstance());
                    return;
                }
                ContractPhoneValidation contractPhoneValidation2 = this.getContractPhoneValidation();
                Intrinsics.checkNotNull(contractPhoneValidation2);
                contractPhoneValidation2.onReturningUser(false);
            }
        });
    }

    public final void fetchFitnessGoals() {
        CoveFitness.getFitnessGoal(new CoveApiListener<GetFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$fetchFitnessGoals$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull GetFitnessGoalResponse getFitnessGoalResponse) {
                UserDataManager userDataManager;
                UserDataManager userDataManager2;
                UserDataManager userDataManager3;
                UserDataManager userDataManager4;
                UserDataManager userDataManager5;
                UserDataManager userDataManager6;
                UserDataManager userDataManager7;
                UserDataManager userDataManager8;
                UserDataManager userDataManager9;
                UserDataManager userDataManager10;
                Intrinsics.checkNotNullParameter(getFitnessGoalResponse, "getFitnessGoalResponse");
                int size = getFitnessGoalResponse.data.size();
                for (int i = 0; i < size; i++) {
                    FitnessGoal fitnessGoal = new FitnessGoal();
                    if (m.equals(getFitnessGoalResponse.data.get(i).activityType, "SLEEP", true)) {
                        fitnessGoal.setActivityBaseUnit(getFitnessGoalResponse.data.get(i).activityBaseUnit);
                        fitnessGoal.setActivityType(getFitnessGoalResponse.data.get(i).activityType);
                        fitnessGoal.setCreatedDate(getFitnessGoalResponse.data.get(i).createdDate);
                        fitnessGoal.setGoalId(getFitnessGoalResponse.data.get(i).goalId);
                        fitnessGoal.setModifiedDate(getFitnessGoalResponse.data.get(i).modifiedDate);
                        fitnessGoal.setStartDate(getFitnessGoalResponse.data.get(i).startDate);
                        fitnessGoal.setTarget(getFitnessGoalResponse.data.get(i).target);
                        fitnessGoal.setTargetAchieved(String.valueOf(getFitnessGoalResponse.data.get(i).targetAchieved));
                        fitnessGoal.setTargetedDays(getFitnessGoalResponse.data.get(i).targetedDays);
                        userDataManager8 = ActivityPhoneValidationViewModel.this.b;
                        Intrinsics.checkNotNull(userDataManager8);
                        userDataManager8.saveFitnessGoalSleep(fitnessGoal);
                        ProfileRepository profileRepository = ProfileRepository.getInstance();
                        Context context = ActivityPhoneValidationViewModel.this.getContext();
                        Integer num = getFitnessGoalResponse.data.get(i).target;
                        Intrinsics.checkNotNullExpressionValue(num, "getFitnessGoalResponse.data[i].target");
                        profileRepository.updateSleepTarget(context, num.intValue());
                        userDataManager9 = ActivityPhoneValidationViewModel.this.b;
                        Intrinsics.checkNotNull(userDataManager9);
                        userDataManager10 = ActivityPhoneValidationViewModel.this.b;
                        Intrinsics.checkNotNull(userDataManager10);
                        userDataManager9.saveGoalSettings(userDataManager10.getGoalSttingsData().setSleep_hours(getFitnessGoalResponse.data.get(i).target.intValue() / 60));
                    }
                    String str = getFitnessGoalResponse.data.get(i).activityType;
                    ActivityType activityType = ActivityType.WALK;
                    if (m.equals(str, activityType.name(), true) && m.equals(getFitnessGoalResponse.data.get(i).activityBaseUnit, ActivityBaseUnit.STEPS.name(), true)) {
                        fitnessGoal.setActivityBaseUnit(getFitnessGoalResponse.data.get(i).activityBaseUnit);
                        fitnessGoal.setActivityType(getFitnessGoalResponse.data.get(i).activityType);
                        fitnessGoal.setCreatedDate(getFitnessGoalResponse.data.get(i).createdDate);
                        fitnessGoal.setGoalId(getFitnessGoalResponse.data.get(i).goalId);
                        fitnessGoal.setModifiedDate(getFitnessGoalResponse.data.get(i).modifiedDate);
                        fitnessGoal.setStartDate(getFitnessGoalResponse.data.get(i).startDate);
                        fitnessGoal.setTarget(getFitnessGoalResponse.data.get(i).target);
                        fitnessGoal.setTargetAchieved(String.valueOf(getFitnessGoalResponse.data.get(i).targetAchieved));
                        fitnessGoal.setTargetedDays(getFitnessGoalResponse.data.get(i).targetedDays);
                        userDataManager5 = ActivityPhoneValidationViewModel.this.b;
                        Intrinsics.checkNotNull(userDataManager5);
                        userDataManager5.saveFitnessGoalSteps(fitnessGoal);
                        ProfileRepository profileRepository2 = ProfileRepository.getInstance();
                        Context context2 = ActivityPhoneValidationViewModel.this.getContext();
                        Integer num2 = getFitnessGoalResponse.data.get(i).target;
                        Intrinsics.checkNotNullExpressionValue(num2, "getFitnessGoalResponse.data[i].target");
                        profileRepository2.updateStepsTarget(context2, num2.intValue());
                        userDataManager6 = ActivityPhoneValidationViewModel.this.b;
                        Intrinsics.checkNotNull(userDataManager6);
                        userDataManager7 = ActivityPhoneValidationViewModel.this.b;
                        Intrinsics.checkNotNull(userDataManager7);
                        GoalSettingsData goalSttingsData = userDataManager7.getGoalSttingsData();
                        Integer num3 = getFitnessGoalResponse.data.get(i).target;
                        Intrinsics.checkNotNullExpressionValue(num3, "getFitnessGoalResponse.data[i].target");
                        userDataManager6.saveGoalSettings(goalSttingsData.setSteps(num3.intValue()));
                    }
                    String str2 = getFitnessGoalResponse.data.get(i).activityType;
                    ActivityType activityType2 = ActivityType.ANY;
                    if (m.equals(str2, activityType2.name(), true) && m.equals(getFitnessGoalResponse.data.get(i).activityBaseUnit, ActivityBaseUnit.MINUTES.name(), true)) {
                        fitnessGoal.setActivityBaseUnit(getFitnessGoalResponse.data.get(i).activityBaseUnit);
                        fitnessGoal.setActivityType(getFitnessGoalResponse.data.get(i).activityType);
                        fitnessGoal.setCreatedDate(getFitnessGoalResponse.data.get(i).createdDate);
                        fitnessGoal.setGoalId(getFitnessGoalResponse.data.get(i).goalId);
                        fitnessGoal.setModifiedDate(getFitnessGoalResponse.data.get(i).modifiedDate);
                        fitnessGoal.setStartDate(getFitnessGoalResponse.data.get(i).startDate);
                        fitnessGoal.setTarget(getFitnessGoalResponse.data.get(i).target);
                        fitnessGoal.setTargetAchieved(String.valueOf(getFitnessGoalResponse.data.get(i).targetAchieved));
                        fitnessGoal.setTargetedDays(getFitnessGoalResponse.data.get(i).targetedDays);
                        userDataManager4 = ActivityPhoneValidationViewModel.this.b;
                        Intrinsics.checkNotNull(userDataManager4);
                        userDataManager4.saveFitnessGoalExerciseMinutes(fitnessGoal);
                    }
                    if (m.equals(getFitnessGoalResponse.data.get(i).activityType, activityType.name(), true) && m.equals(getFitnessGoalResponse.data.get(i).activityBaseUnit, ActivityBaseUnit.MINUTES.name(), true)) {
                        fitnessGoal.setActivityBaseUnit(getFitnessGoalResponse.data.get(i).activityBaseUnit);
                        fitnessGoal.setActivityType(getFitnessGoalResponse.data.get(i).activityType);
                        fitnessGoal.setCreatedDate(getFitnessGoalResponse.data.get(i).createdDate);
                        fitnessGoal.setGoalId(getFitnessGoalResponse.data.get(i).goalId);
                        fitnessGoal.setModifiedDate(getFitnessGoalResponse.data.get(i).modifiedDate);
                        fitnessGoal.setStartDate(getFitnessGoalResponse.data.get(i).startDate);
                        fitnessGoal.setTarget(Integer.valueOf(getFitnessGoalResponse.data.get(i).target.intValue() / 60));
                        fitnessGoal.setTargetAchieved(String.valueOf(getFitnessGoalResponse.data.get(i).targetAchieved));
                        fitnessGoal.setTargetedDays(getFitnessGoalResponse.data.get(i).targetedDays);
                        userDataManager3 = ActivityPhoneValidationViewModel.this.b;
                        Intrinsics.checkNotNull(userDataManager3);
                        userDataManager3.saveFitnessGoalWalkHour(fitnessGoal);
                    }
                    if (m.equals(getFitnessGoalResponse.data.get(i).activityType, activityType2.name(), true) && m.equals(getFitnessGoalResponse.data.get(i).activityBaseUnit, ActivityBaseUnit.CALORIES.name(), true)) {
                        fitnessGoal.setActivityBaseUnit(getFitnessGoalResponse.data.get(i).activityBaseUnit);
                        fitnessGoal.setActivityType(getFitnessGoalResponse.data.get(i).activityType);
                        fitnessGoal.setCreatedDate(getFitnessGoalResponse.data.get(i).createdDate);
                        fitnessGoal.setGoalId(getFitnessGoalResponse.data.get(i).goalId);
                        fitnessGoal.setModifiedDate(getFitnessGoalResponse.data.get(i).modifiedDate);
                        fitnessGoal.setStartDate(getFitnessGoalResponse.data.get(i).startDate);
                        fitnessGoal.setTarget(getFitnessGoalResponse.data.get(i).target);
                        fitnessGoal.setTargetAchieved(String.valueOf(getFitnessGoalResponse.data.get(i).targetAchieved));
                        fitnessGoal.setTargetedDays(getFitnessGoalResponse.data.get(i).targetedDays);
                        userDataManager2 = ActivityPhoneValidationViewModel.this.b;
                        Intrinsics.checkNotNull(userDataManager2);
                        userDataManager2.saveFitnessGoalCalories(fitnessGoal);
                    }
                    if (m.equals(getFitnessGoalResponse.data.get(i).activityType, activityType.name(), true) && m.equals(getFitnessGoalResponse.data.get(i).activityBaseUnit, ActivityBaseUnit.METERS.name(), true)) {
                        fitnessGoal.setActivityBaseUnit(getFitnessGoalResponse.data.get(i).activityBaseUnit);
                        fitnessGoal.setActivityType(getFitnessGoalResponse.data.get(i).activityType);
                        fitnessGoal.setCreatedDate(getFitnessGoalResponse.data.get(i).createdDate);
                        fitnessGoal.setGoalId(getFitnessGoalResponse.data.get(i).goalId);
                        fitnessGoal.setModifiedDate(getFitnessGoalResponse.data.get(i).modifiedDate);
                        fitnessGoal.setStartDate(getFitnessGoalResponse.data.get(i).startDate);
                        fitnessGoal.setTarget(getFitnessGoalResponse.data.get(i).target);
                        fitnessGoal.setTargetAchieved(String.valueOf(getFitnessGoalResponse.data.get(i).targetAchieved));
                        fitnessGoal.setTargetedDays(getFitnessGoalResponse.data.get(i).targetedDays);
                        userDataManager = ActivityPhoneValidationViewModel.this.b;
                        Intrinsics.checkNotNull(userDataManager);
                        userDataManager.saveFitnessGoalDistance(fitnessGoal);
                    }
                }
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5293a;
    }

    @Nullable
    public final ContractPhoneValidation getContractPhoneValidation() {
        return this.e;
    }

    public final void getFitnessConfigDataForExistingUser() {
        FitnessConfigApi.getFitnessConfigData(new CoveApiListener<FitnessSummaryResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$getFitnessConfigDataForExistingUser$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                ContractPhoneValidation contractPhoneValidation = ActivityPhoneValidationViewModel.this.getContractPhoneValidation();
                Intrinsics.checkNotNull(contractPhoneValidation);
                contractPhoneValidation.onFetchingFitnessSummary(false);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable FitnessSummaryResponse fitnessSummaryResponse) {
                int i;
                if (fitnessSummaryResponse != null) {
                    if (fitnessSummaryResponse.getData() != null) {
                        ProfileData.getInstance().setHeight(fitnessSummaryResponse.getData().getHeight());
                        ProfileData.getInstance().setWeight(fitnessSummaryResponse.getData().getWeight());
                        ProfileData profileData = ProfileData.getInstance();
                        ProfileRepository profileRepository = ProfileRepository.getInstance();
                        Context context = ActivityPhoneValidationViewModel.this.getContext();
                        String height = profileData.getHeight();
                        Intrinsics.checkNotNull(height);
                        int parseInt = Integer.parseInt(height);
                        String weight = profileData.getWeight();
                        Intrinsics.checkNotNull(weight);
                        profileRepository.updateHeightWeight(context, parseInt, Double.parseDouble(weight));
                        ProfileRepository profileRepository2 = ProfileRepository.getInstance();
                        Context context2 = ActivityPhoneValidationViewModel.this.getContext();
                        int i2 = -1;
                        if (AppUtils.isEmpty(fitnessSummaryResponse.getData().getWalkingStrideLength())) {
                            i = -1;
                        } else {
                            String walkingStrideLength = fitnessSummaryResponse.getData().getWalkingStrideLength();
                            Intrinsics.checkNotNullExpressionValue(walkingStrideLength, "fitnessSummaryResponse.data.walkingStrideLength");
                            i = Integer.parseInt(walkingStrideLength);
                        }
                        if (!AppUtils.isEmpty(fitnessSummaryResponse.getData().getRunningStrideLength())) {
                            String runningStrideLength = fitnessSummaryResponse.getData().getRunningStrideLength();
                            Intrinsics.checkNotNullExpressionValue(runningStrideLength, "fitnessSummaryResponse.data.runningStrideLength");
                            i2 = Integer.parseInt(runningStrideLength);
                        }
                        profileRepository2.updateStrideLength(context2, i, i2);
                        ContractPhoneValidation contractPhoneValidation = ActivityPhoneValidationViewModel.this.getContractPhoneValidation();
                        Intrinsics.checkNotNull(contractPhoneValidation);
                        contractPhoneValidation.onFetchingFitnessSummary(true);
                        return;
                    }
                    ContractPhoneValidation contractPhoneValidation2 = ActivityPhoneValidationViewModel.this.getContractPhoneValidation();
                    Intrinsics.checkNotNull(contractPhoneValidation2);
                    contractPhoneValidation2.onFetchingFitnessSummary(false);
                }
            }
        });
    }

    @Nullable
    public final MutableLiveData<Integer> getFragmentState() {
        return this.f;
    }

    public final void getLegalDetailsFromServer() {
        CoveOnboarding.getV2RemoteConfiguration("1", new CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$getLegalDetailsFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LogHelper.e(ActivityPhoneValidationViewModel.this.getTAG(), "Remote config api onError");
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [T, java.util.ArrayList] */
            /* JADX WARN: Type inference failed for: r1v10, types: [T, java.util.ArrayList] */
            /* JADX WARN: Type inference failed for: r1v13, types: [T, java.util.ArrayList] */
            /* JADX WARN: Type inference failed for: r1v4, types: [T, java.util.ArrayList] */
            /* JADX WARN: Type inference failed for: r1v7, types: [T, java.util.ArrayList] */
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SRemoteConfigResponse sRemoteConfigResponse) {
                if (sRemoteConfigResponse != null) {
                    if (sRemoteConfigResponse.getData().getLegalBean() != null) {
                        SessionManager sessionManager = SessionManager.getInstance(ActivityPhoneValidationViewModel.this.getContext());
                        List<SRemoteConfigResponse.DataBean.LegalBean.Doc> doc = sRemoteConfigResponse.getData().getLegalBean().getDoc();
                        Intrinsics.checkNotNullExpressionValue(doc, "`object`.data.legalBean.doc");
                        for (SRemoteConfigResponse.DataBean.LegalBean.Doc doc2 : doc) {
                            if (m.equals(doc2.getType(), ActivityPhoneValidationViewModel.this.getContext().getResources().getString(R.string.eula), true)) {
                                sessionManager.setLegalDocType(doc2.getType());
                                sessionManager.setLegalDocUrl(doc2.getHtmlUrl());
                                sessionManager.setLegalDocVersion(doc2.getVersion());
                            }
                            if (m.equals(doc2.getType(), "PRIVACY_POLICY", true)) {
                                sessionManager.setPrivacyPolicyDocUrl(doc2.getHtmlUrl());
                            }
                            if (m.equals(doc2.getType(), "SPORTS_DISCLAIMER", true)) {
                                sessionManager.setSportsDisclaimerDocUrl(doc2.getHtmlUrl());
                            }
                        }
                    } else {
                        LogHelper.e(ActivityPhoneValidationViewModel.this.getTAG(), "Remote config api legal bean is null");
                    }
                    if (sRemoteConfigResponse.getData().getRefsBean() != null) {
                        if (sRemoteConfigResponse.getData().getRefsBean().getIdoActivities() != null) {
                            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                            objectRef.element = new ArrayList();
                            OkHttpClient okHttpClient = new OkHttpClient();
                            Request.Builder builder = new Request.Builder();
                            String idoActivities = sRemoteConfigResponse.getData().getRefsBean().getIdoActivities();
                            Intrinsics.checkNotNullExpressionValue(idoActivities, "`object`.data.refsBean.idoActivities");
                            Call newCall = okHttpClient.newCall(builder.url(idoActivities).build());
                            final ActivityPhoneValidationViewModel activityPhoneValidationViewModel = ActivityPhoneValidationViewModel.this;
                            newCall.enqueue(new Callback() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$getLegalDetailsFromServer$1$onSuccess$1
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
                                    Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef2 = objectRef;
                                    List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                                    Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem> }");
                                    objectRef2.element = (ArrayList) activities;
                                    LogHelper.d("idoActivitiesList", objectRef.element.toString());
                                    new PreferenceManager(activityPhoneValidationViewModel.getContext()).saveIDOActivityIcons(objectRef.element);
                                }
                            });
                        }
                        if (sRemoteConfigResponse.getData().getRefsBean().getTouchelxActivities() != null) {
                            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                            objectRef2.element = new ArrayList();
                            OkHttpClient okHttpClient2 = new OkHttpClient();
                            Request.Builder builder2 = new Request.Builder();
                            String touchelxActivities = sRemoteConfigResponse.getData().getRefsBean().getTouchelxActivities();
                            Intrinsics.checkNotNullExpressionValue(touchelxActivities, "`object`.data.refsBean.touchelxActivities");
                            Call newCall2 = okHttpClient2.newCall(builder2.url(touchelxActivities).build());
                            final ActivityPhoneValidationViewModel activityPhoneValidationViewModel2 = ActivityPhoneValidationViewModel.this;
                            newCall2.enqueue(new Callback() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$getLegalDetailsFromServer$1$onSuccess$2
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
                                    Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef3 = objectRef2;
                                    List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                                    Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem> }");
                                    objectRef3.element = (ArrayList) activities;
                                    LogHelper.d("touchActivitiesList", objectRef2.element.toString());
                                    new PreferenceManager(activityPhoneValidationViewModel2.getContext()).saveTouchActivityIcons(objectRef2.element);
                                }
                            });
                        }
                        if (sRemoteConfigResponse.getData().getRefsBean().getEastapexActivities() != null) {
                            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                            objectRef3.element = new ArrayList();
                            OkHttpClient okHttpClient3 = new OkHttpClient();
                            Request.Builder builder3 = new Request.Builder();
                            String eastapexActivities = sRemoteConfigResponse.getData().getRefsBean().getEastapexActivities();
                            Intrinsics.checkNotNullExpressionValue(eastapexActivities, "`object`.data.refsBean.eastapexActivities");
                            Call newCall3 = okHttpClient3.newCall(builder3.url(eastapexActivities).build());
                            final ActivityPhoneValidationViewModel activityPhoneValidationViewModel3 = ActivityPhoneValidationViewModel.this;
                            newCall3.enqueue(new Callback() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$getLegalDetailsFromServer$1$onSuccess$3
                                @Override // okhttp3.Callback
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(e, "e");
                                }

                                /* JADX WARN: Type inference failed for: r4v8, types: [T, java.util.ArrayList] */
                                @Override // okhttp3.Callback
                                public void onResponse(@NotNull Call call, @NotNull Response response) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    if (response.body() != null) {
                                        ResponseBody body = response.body();
                                        Intrinsics.checkNotNull(body);
                                        Object fromJson = new Gson().fromJson(body.string(), (Class<Object>) ActivityIcons.class);
                                        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(jsonStri…ctivityIcons::class.java)");
                                        Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef4 = objectRef3;
                                        List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                                        Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem> }");
                                        objectRef4.element = (ArrayList) activities;
                                        LogHelper.d("eastapexActivitiesList", objectRef3.element.toString());
                                        int i = 0;
                                        for (Object obj : objectRef3.element) {
                                            int i2 = i + 1;
                                            if (i < 0) {
                                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                                            }
                                            LogHelper.d("eastapexActivitiesList", ((ActivitiesItem) obj).toString());
                                            i = i2;
                                        }
                                        new PreferenceManager(activityPhoneValidationViewModel3.getContext()).saveEastApexActivityIcons(objectRef3.element);
                                    }
                                }
                            });
                        }
                        if (sRemoteConfigResponse.getData().getRefsBean().getRuggedActivities() != null) {
                            final Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                            objectRef4.element = new ArrayList();
                            OkHttpClient okHttpClient4 = new OkHttpClient();
                            Request.Builder builder4 = new Request.Builder();
                            String ruggedActivities = sRemoteConfigResponse.getData().getRefsBean().getRuggedActivities();
                            Intrinsics.checkNotNullExpressionValue(ruggedActivities, "`object`.data.refsBean.ruggedActivities");
                            Call newCall4 = okHttpClient4.newCall(builder4.url(ruggedActivities).build());
                            final ActivityPhoneValidationViewModel activityPhoneValidationViewModel4 = ActivityPhoneValidationViewModel.this;
                            newCall4.enqueue(new Callback() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$getLegalDetailsFromServer$1$onSuccess$4
                                @Override // okhttp3.Callback
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(e, "e");
                                }

                                /* JADX WARN: Type inference failed for: r4v8, types: [T, java.util.ArrayList] */
                                @Override // okhttp3.Callback
                                public void onResponse(@NotNull Call call, @NotNull Response response) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    if (response.body() != null) {
                                        ResponseBody body = response.body();
                                        Intrinsics.checkNotNull(body);
                                        Object fromJson = new Gson().fromJson(body.string(), (Class<Object>) ActivityIcons.class);
                                        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(jsonStri…ctivityIcons::class.java)");
                                        Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef5 = objectRef4;
                                        List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                                        Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem> }");
                                        objectRef5.element = (ArrayList) activities;
                                        LogHelper.d("ruggedActivitiesList", objectRef4.element.toString());
                                        int i = 0;
                                        for (Object obj : objectRef4.element) {
                                            int i2 = i + 1;
                                            if (i < 0) {
                                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                                            }
                                            LogHelper.d("ruggedActivitiesList", ((ActivitiesItem) obj).toString());
                                            i = i2;
                                        }
                                        new PreferenceManager(activityPhoneValidationViewModel4.getContext()).saveRuggedActivityIcons(objectRef4.element);
                                    }
                                }
                            });
                        }
                        if (sRemoteConfigResponse.getData().getRefsBean().getSmaActivities() != null) {
                            final Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                            objectRef5.element = new ArrayList();
                            OkHttpClient okHttpClient5 = new OkHttpClient();
                            Request.Builder builder5 = new Request.Builder();
                            String smaActivities = sRemoteConfigResponse.getData().getRefsBean().getSmaActivities();
                            Intrinsics.checkNotNullExpressionValue(smaActivities, "`object`.data.refsBean.smaActivities");
                            Call newCall5 = okHttpClient5.newCall(builder5.url(smaActivities).build());
                            final ActivityPhoneValidationViewModel activityPhoneValidationViewModel5 = ActivityPhoneValidationViewModel.this;
                            newCall5.enqueue(new Callback() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$getLegalDetailsFromServer$1$onSuccess$5
                                @Override // okhttp3.Callback
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(e, "e");
                                }

                                /* JADX WARN: Type inference failed for: r4v8, types: [T, java.util.ArrayList] */
                                @Override // okhttp3.Callback
                                public void onResponse(@NotNull Call call, @NotNull Response response) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    if (response.body() != null) {
                                        ResponseBody body = response.body();
                                        Intrinsics.checkNotNull(body);
                                        Object fromJson = new Gson().fromJson(body.string(), (Class<Object>) ActivityIcons.class);
                                        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(jsonStri…ctivityIcons::class.java)");
                                        Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef6 = objectRef5;
                                        List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                                        Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem> }");
                                        objectRef6.element = (ArrayList) activities;
                                        LogHelper.d("smaActivitiesList", objectRef5.element.toString());
                                        int i = 0;
                                        for (Object obj : objectRef5.element) {
                                            int i2 = i + 1;
                                            if (i < 0) {
                                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                                            }
                                            LogHelper.d("smaActivitiesList", ((ActivitiesItem) obj).toString());
                                            i = i2;
                                        }
                                        new PreferenceManager(activityPhoneValidationViewModel5.getContext()).saveSMAActivityIcons(objectRef5.element);
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    }
                    return;
                }
                LogHelper.e(ActivityPhoneValidationViewModel.this.getTAG(), "Remote config api object is null");
            }
        });
    }

    @Nullable
    public final String getPhoneNumber() {
        return this.d;
    }

    @NotNull
    public final String getStrideeLength(int i) {
        return String.valueOf(i * 0.413d);
    }

    public final String getTAG() {
        return this.g;
    }

    public final void loadPhoneNumberEnterFragment() {
        MutableLiveData<Integer> mutableLiveData = this.f;
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.setValue(1);
    }

    public final void loadVerificationSuccessFragment() {
        MutableLiveData<Integer> mutableLiveData = this.f;
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.setValue(3);
    }

    public final void onBackPressed() {
        MutableLiveData<Integer> mutableLiveData = this.f;
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.setValue(11);
    }

    public final void onCallUpdateNumberApi(@NotNull String otpnumber, @NotNull final OTPVerification otpVerification) {
        Intrinsics.checkNotNullParameter(otpnumber, "otpnumber");
        Intrinsics.checkNotNullParameter(otpVerification, "otpVerification");
        if (AppUtils.isNetConnected(this.f5293a)) {
            if (otpnumber.length() == 4) {
                CoveOnboarding.modifyPhoneNumber(new ModifyPhoneNumberReq(SessionManager.getInstance(this.f5293a).getUserDetails().getUserId(), this.d, otpnumber), new CoveApiListener<ModifyPhoneNumberRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$onCallUpdateNumberApi$1
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        ActivityPhoneValidationViewModel.OTPVerification oTPVerification = otpVerification;
                        Intrinsics.checkNotNull(coveApiErrorModel);
                        String msg = coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "coveApiErrorModel!!.msg");
                        oTPVerification.onError(msg, coveApiErrorModel.getCode());
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@NotNull ModifyPhoneNumberRes response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        ProfileData userDetails = SessionManager.getInstance(ActivityPhoneValidationViewModel.this.getContext()).getUserDetails();
                        userDetails.setMobileNumber(ActivityPhoneValidationViewModel.this.getPhoneNumber());
                        SessionManager.getInstance(ActivityPhoneValidationViewModel.this.getContext()).createLoginSession(userDetails);
                        otpVerification.onNewUser();
                    }
                });
                return;
            }
            String string = this.f5293a.getResources().getString(R.string.enter_valid_otp);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…R.string.enter_valid_otp)");
            otpVerification.onError(string, Integer.parseInt(AppConstants.ZERO.getValue()));
            return;
        }
        String string2 = this.f5293a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        otpVerification.onError(string2, Integer.parseInt(AppConstants.ZERO.getValue()));
    }

    public final void sendOtp(final boolean z, @Nullable String str, @NotNull String phoneNo, @NotNull final SendOTPCallback sendOTPCallback) {
        Intrinsics.checkNotNullParameter(phoneNo, "phoneNo");
        Intrinsics.checkNotNullParameter(sendOTPCallback, "sendOTPCallback");
        this.d = phoneNo;
        PhoneNumberVerificationReq phoneNumberVerificationReq = new PhoneNumberVerificationReq(phoneNo);
        phoneNumberVerificationReq.setRecaptchaAction("signin_otp");
        phoneNumberVerificationReq.setRecaptchaToken(str);
        CoveOnboarding.verifyPhoneNumberWithCustomeOtpLenth(phoneNumberVerificationReq, 4, new CoveApiListener<PhoneNumberVerificationRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$sendOtp$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$sendOtp$1$onError$1", f = "ActivityPhoneValidationViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ CoveApiErrorModel $coveApiErrorModel;
                public final /* synthetic */ ActivityPhoneValidationViewModel.SendOTPCallback $sendOTPCallback;
                public int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ActivityPhoneValidationViewModel.SendOTPCallback sendOTPCallback, CoveApiErrorModel coveApiErrorModel, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$sendOTPCallback = sendOTPCallback;
                    this.$coveApiErrorModel = coveApiErrorModel;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$sendOTPCallback, this.$coveApiErrorModel, continuation);
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
                        ActivityPhoneValidationViewModel.SendOTPCallback sendOTPCallback = this.$sendOTPCallback;
                        String msg = this.$coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "coveApiErrorModel.msg");
                        sendOTPCallback.onFailure(msg);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$sendOtp$1$onSuccess$1", f = "ActivityPhoneValidationViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityPhoneValidationViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(ActivityPhoneValidationViewModel activityPhoneValidationViewModel, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = activityPhoneValidationViewModel;
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
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableLiveData<Integer> fragmentState = this.this$0.getFragmentState();
                        Intrinsics.checkNotNull(fragmentState);
                        fragmentState.setValue(Boxing.boxInt(2));
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(ActivityPhoneValidationViewModel.SendOTPCallback.this, coveApiErrorModel, null), 2, null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull PhoneNumberVerificationRes phoneNumberVerificationRes) {
                Intrinsics.checkNotNullParameter(phoneNumberVerificationRes, "phoneNumberVerificationRes");
                ActivityPhoneValidationViewModel.SendOTPCallback.this.onSuccess();
                Dashboard2Utils.Companion.loadScanDeviceOnDisconnectConfiguration(this.getContext());
                if (phoneNumberVerificationRes.getCode() != 200 || z) {
                    return;
                }
                kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new b(this, null), 2, null);
            }
        });
    }

    public final void setContractPhoneValidation(@Nullable ContractPhoneValidation contractPhoneValidation) {
        this.e = contractPhoneValidation;
    }

    public final void setFragmentState(@Nullable MutableLiveData<Integer> mutableLiveData) {
        this.f = mutableLiveData;
    }

    public final void setPhoneNumber(@Nullable String str) {
        this.d = str;
    }

    public final void verifyOtp(@NotNull String otp, @NotNull final String phoneNumber, @NotNull final OTPVerification otpVerification) {
        Intrinsics.checkNotNullParameter(otp, "otp");
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        Intrinsics.checkNotNullParameter(otpVerification, "otpVerification");
        CoveOnboarding.getUserByPhoneNumber(new UserByPhoneNumberReq(phoneNumber, otp), new CoveApiListener<UserByPhoneNumberRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$verifyOtp$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                ActivityPhoneValidationViewModel.OTPVerification oTPVerification = ActivityPhoneValidationViewModel.OTPVerification.this;
                String msg = coveApiErrorModel.getMsg();
                Intrinsics.checkNotNullExpressionValue(msg, "coveApiErrorModel.msg");
                oTPVerification.onError(msg, coveApiErrorModel.getCode());
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable UserByPhoneNumberRes userByPhoneNumberRes) {
                if (userByPhoneNumberRes == null) {
                    ActivityPhoneValidationViewModel.OTPVerification.this.onNewUser();
                    return;
                }
                if (userByPhoneNumberRes.getCode() == 200) {
                    if (userByPhoneNumberRes.getId() != 0) {
                        ProfileData profileData = ProfileData.getInstance();
                        profileData.setExistingUser(true);
                        profileData.setUserId(userByPhoneNumberRes.getId());
                        profileData.setName(userByPhoneNumberRes.getName());
                        NameDetails nameDetails = new NameDetails();
                        nameDetails.setFname(userByPhoneNumberRes.getFirstName());
                        nameDetails.setLname(userByPhoneNumberRes.getLastName());
                        SessionManager.getInstance(this.getContext()).saveNameDetails(nameDetails);
                        profileData.setEmail(userByPhoneNumberRes.getEmailId());
                        profileData.setDob(userByPhoneNumberRes.getBirthDate());
                        profileData.setGender(userByPhoneNumberRes.getGender());
                        profileData.setDpUrl(userByPhoneNumberRes.getDpUrl());
                        profileData.setMobileNumber(phoneNumber);
                        if (userByPhoneNumberRes.getAxTrackerId() != null) {
                            SessionManager.getInstance(this.getContext()).setAxTrackerId(userByPhoneNumberRes.getAxTrackerId());
                        }
                        ActivityPhoneValidationViewModel.OTPVerification oTPVerification = ActivityPhoneValidationViewModel.OTPVerification.this;
                        Intrinsics.checkNotNullExpressionValue(profileData, "profileData");
                        oTPVerification.onExistingUser(profileData, nameDetails);
                    } else {
                        ProfileData profileData2 = ProfileData.getInstance();
                        if (userByPhoneNumberRes.getMobileNumber() != null) {
                            profileData2.setMobileNumber(userByPhoneNumberRes.getMobileNumber());
                            ActivityPhoneValidationViewModel.OTPVerification.this.onNewUser();
                        } else if (this.getContext() != null) {
                            Toast.makeText(this.getContext(), this.getContext().getString(R.string.somethings_went_wrong), 0).show();
                        }
                    }
                }
                WeeklyReportPrefData.getInstance().setEmailVerified(userByPhoneNumberRes.isEmailVerified());
            }
        });
    }
}
