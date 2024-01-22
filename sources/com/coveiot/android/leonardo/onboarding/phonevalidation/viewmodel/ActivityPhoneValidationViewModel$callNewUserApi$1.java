package com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.provider.Settings;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.fitness.config.FitnessConfigApi;
import com.coveiot.coveaccess.fitness.config.models.requestmodel.FitnessConfigRequest;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessConfigResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.onboarding.model.RegisterNewUserReq;
import com.coveiot.coveaccess.onboarding.model.RegisterNewUserRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.common.Scopes;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$callNewUserApi$1", f = "ActivityPhoneValidationViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class ActivityPhoneValidationViewModel$callNewUserApi$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $phoneNumber;
    public int label;
    public final /* synthetic */ ActivityPhoneValidationViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityPhoneValidationViewModel$callNewUserApi$1(String str, ActivityPhoneValidationViewModel activityPhoneValidationViewModel, Continuation<? super ActivityPhoneValidationViewModel$callNewUserApi$1> continuation) {
        super(2, continuation);
        this.$phoneNumber = str;
        this.this$0 = activityPhoneValidationViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityPhoneValidationViewModel$callNewUserApi$1(this.$phoneNumber, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityPhoneValidationViewModel$callNewUserApi$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [com.coveiot.covepreferences.data.ProfileData, T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            RegisterNewUserReq registerNewUserReq = new RegisterNewUserReq();
            registerNewUserReq.setName(AppConstants.DEFAULT_USER_PROFILE_NAME.getValue());
            registerNewUserReq.setMobileNumber(this.$phoneNumber);
            registerNewUserReq.setGender(AppConstants.DEFAULT_USER_PROFILE_GENDER.getValue());
            registerNewUserReq.setHeight(AppConstants.DEFAULT_USER_PROFILE_HEIGHT.getValue());
            registerNewUserReq.setWeight(AppConstants.DEFAULT_USER_PROFILE_WEIGHT.getValue());
            if (SessionManager.getInstance(this.this$0.getContext()).getGuestSessionId() != null) {
                registerNewUserReq.setGuestUserSessionId(SessionManager.getInstance(this.this$0.getContext()).getGuestSessionId());
            }
            registerNewUserReq.setDeviceID(Settings.Secure.getString(this.this$0.getContext().getContentResolver(), "android_id"));
            Bitmap bitmapFromVectorDrawable = AppUtils.getBitmapFromVectorDrawable(this.this$0.getContext(), 2131231665);
            File dir = new ContextWrapper(this.this$0.getContext().getApplicationContext()).getDir(Scopes.PROFILE, 0);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, "profile_pic.png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmapFromVectorDrawable.compress(Bitmap.CompressFormat.PNG, 70, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            String absolutePath = file.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
            registerNewUserReq.setDpFile(new File(absolutePath));
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ?? profileData = ProfileData.getInstance();
            objectRef.element = profileData;
            ((ProfileData) profileData).setName(registerNewUserReq.getName());
            ((ProfileData) objectRef.element).setMobileNumber(registerNewUserReq.getMobileNumber());
            ((ProfileData) objectRef.element).setGender(registerNewUserReq.getGender());
            ((ProfileData) objectRef.element).setHeight(registerNewUserReq.getHeight());
            ((ProfileData) objectRef.element).setWeight(registerNewUserReq.getWeight());
            ((ProfileData) objectRef.element).setProfile_pic(absolutePath);
            final ActivityPhoneValidationViewModel activityPhoneValidationViewModel = this.this$0;
            CoveOnboarding.registerNewUser(registerNewUserReq, new CoveApiListener<RegisterNewUserRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$callNewUserApi$1.1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    MutableLiveData<Integer> fragmentState = activityPhoneValidationViewModel.getFragmentState();
                    if (fragmentState != null) {
                        fragmentState.postValue(-1);
                    }
                    MutableLiveData<Integer> fragmentState2 = activityPhoneValidationViewModel.getFragmentState();
                    Intrinsics.checkNotNull(fragmentState2);
                    fragmentState2.setValue(-1);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable RegisterNewUserRes registerNewUserRes) {
                    if (registerNewUserRes != null) {
                        if (registerNewUserRes.getCode() == 200) {
                            if (registerNewUserRes.getUserId() != 0) {
                                objectRef.element.setUserId(registerNewUserRes.getUserId());
                                objectRef.element.setExistingUser(false);
                                objectRef.element.setDpUrl(registerNewUserRes.getDpUrl());
                                objectRef.element.setAppTrackerId(registerNewUserRes.getAppTrackerId());
                                if (registerNewUserRes.getAxTrackerId() != null) {
                                    SessionManager.getInstance(activityPhoneValidationViewModel.getContext()).setAxTrackerId(registerNewUserRes.getAxTrackerId());
                                }
                                AppConstants appConstants = AppConstants.DEFAULT_USER_PROFILE_HEIGHT;
                                FitnessConfigRequest fitnessConfigRequest = new FitnessConfigRequest(appConstants.getValue(), AppConstants.DEFAULT_USER_PROFILE_WEIGHT.getValue(), activityPhoneValidationViewModel.getStrideeLength(Integer.parseInt(appConstants.getValue())));
                                final Ref.ObjectRef<ProfileData> objectRef2 = objectRef;
                                final ActivityPhoneValidationViewModel activityPhoneValidationViewModel2 = activityPhoneValidationViewModel;
                                FitnessConfigApi.saveFitnessConfigData(fitnessConfigRequest, new CoveApiListener<FitnessConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel$callNewUserApi$1$1$onSuccess$1
                                    @Override // com.coveiot.coveaccess.CoveApiListener
                                    public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                                        Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                                        MutableLiveData<Integer> fragmentState = activityPhoneValidationViewModel2.getFragmentState();
                                        if (fragmentState != null) {
                                            fragmentState.postValue(-1);
                                        }
                                        MutableLiveData<Integer> fragmentState2 = activityPhoneValidationViewModel2.getFragmentState();
                                        Intrinsics.checkNotNull(fragmentState2);
                                        fragmentState2.setValue(-1);
                                    }

                                    @Override // com.coveiot.coveaccess.CoveApiListener
                                    public void onSuccess(@NotNull FitnessConfigResponse fitnessConfigResponse) {
                                        Intrinsics.checkNotNullParameter(fitnessConfigResponse, "fitnessConfigResponse");
                                        AppConstants appConstants2 = AppConstants.DEFAULT_USER_PROFILE_HEIGHT;
                                        objectRef2.element.setHeight(appConstants2.getValue());
                                        objectRef2.element.setWeight(AppConstants.DEFAULT_USER_PROFILE_WEIGHT.getValue());
                                        if (objectRef2.element.getHeight() != null && objectRef2.element.getWeight() != null) {
                                            ProfileRepository profileRepository = ProfileRepository.getInstance();
                                            Context context = activityPhoneValidationViewModel2.getContext();
                                            String height = objectRef2.element.getHeight();
                                            Intrinsics.checkNotNull(height);
                                            int parseInt = Integer.parseInt(height);
                                            String weight = objectRef2.element.getWeight();
                                            Intrinsics.checkNotNull(weight);
                                            profileRepository.updateHeightWeight(context, parseInt, Double.parseDouble(weight));
                                            objectRef2.element.setStride_length(activityPhoneValidationViewModel2.getStrideeLength(Integer.parseInt(appConstants2.getValue())));
                                            SessionManager.getInstance(activityPhoneValidationViewModel2.getContext()).createLoginSession(objectRef2.element);
                                        } else {
                                            MutableLiveData<Integer> fragmentState = activityPhoneValidationViewModel2.getFragmentState();
                                            if (fragmentState != null) {
                                                fragmentState.postValue(-1);
                                            }
                                            MutableLiveData<Integer> fragmentState2 = activityPhoneValidationViewModel2.getFragmentState();
                                            Intrinsics.checkNotNull(fragmentState2);
                                            fragmentState2.setValue(-1);
                                        }
                                        MutableLiveData<Integer> fragmentState3 = activityPhoneValidationViewModel2.getFragmentState();
                                        Intrinsics.checkNotNull(fragmentState3);
                                        fragmentState3.setValue(3);
                                    }
                                });
                                return;
                            }
                            MutableLiveData<Integer> fragmentState = activityPhoneValidationViewModel.getFragmentState();
                            if (fragmentState != null) {
                                fragmentState.postValue(-1);
                            }
                            MutableLiveData<Integer> fragmentState2 = activityPhoneValidationViewModel.getFragmentState();
                            Intrinsics.checkNotNull(fragmentState2);
                            fragmentState2.setValue(-1);
                            return;
                        }
                        MutableLiveData<Integer> fragmentState3 = activityPhoneValidationViewModel.getFragmentState();
                        if (fragmentState3 != null) {
                            fragmentState3.postValue(-1);
                        }
                        MutableLiveData<Integer> fragmentState4 = activityPhoneValidationViewModel.getFragmentState();
                        Intrinsics.checkNotNull(fragmentState4);
                        fragmentState4.setValue(-1);
                        return;
                    }
                    MutableLiveData<Integer> fragmentState5 = activityPhoneValidationViewModel.getFragmentState();
                    if (fragmentState5 != null) {
                        fragmentState5.postValue(-1);
                    }
                    MutableLiveData<Integer> fragmentState6 = activityPhoneValidationViewModel.getFragmentState();
                    Intrinsics.checkNotNull(fragmentState6);
                    fragmentState6.setValue(-1);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
