package com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.os.CountDownTimer;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.activitymodes.utils.SingleLiveEvent;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractEnterOTP;
import com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.OTPChecksListener;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.model.server.GetOTPServicesRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.OTPResendTimerConfigBean;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes5.dex */
public final class FragmentEnterOtpViewModel extends AndroidViewModel {
    @Nullable
    public ContractEnterOTP d;
    @Nullable
    public OTPChecksListener e;
    public final long f;
    @Nullable
    public CountDownTimer g;
    @NotNull
    public SingleLiveEvent<List<GetOTPServicesRes.ServiceType>> h;
    @Nullable
    public Context i;

    @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.FragmentEnterOtpViewModel", f = "FragmentEnterOtpViewModel.kt", i = {0, 0}, l = {105}, m = "generateAuthOtp", n = {"this", "otpAuthReq"}, s = {"L$0", "L$1"})
    /* loaded from: classes5.dex */
    public static final class a extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FragmentEnterOtpViewModel.this.generateAuthOtp(null, null, null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentEnterOtpViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.f = 1000L;
        this.h = new SingleLiveEvent<>();
        this.i = getApplication();
    }

    public static final void d(Exception it) {
        Intrinsics.checkNotNullParameter(it, "it");
    }

    public static final void e(final FirebaseRemoteConfig remoteConfig, final String key, final Context context, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Void r4 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.d
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    FragmentEnterOtpViewModel.f(FirebaseRemoteConfig.this, key, context, task2);
                }
            });
            return;
        }
        LogHelper.e("otpServiceCheck", "Remote Config Failed");
    }

    public static final void f(FirebaseRemoteConfig remoteConfig, String key, Context context, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(key);
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(key)");
        JSONObject jSONObject = new JSONObject(string).getJSONObject("otp_resend_timer");
        SessionManager.getInstance(context).saveResendOTPTimerConfigBean((OTPResendTimerConfigBean) new Gson().fromJson(jSONObject.toString(), (Class<Object>) OTPResendTimerConfigBean.class));
    }

    public final void fetchOTPResendTimer(@NotNull final String key, @NotNull final Context context) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(context, "context");
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.setDefaultsAsync(R.xml.remote_config_details);
        remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.f
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                FragmentEnterOtpViewModel.d(exc);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.e
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FragmentEnterOtpViewModel.e(FirebaseRemoteConfig.this, key, context, task);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ac  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object generateAuthOtp(@org.jetbrains.annotations.NotNull androidx.fragment.app.FragmentActivity r6, @org.jetbrains.annotations.NotNull java.lang.String r7, @org.jetbrains.annotations.Nullable java.lang.String r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            Method dump skipped, instructions count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.FragmentEnterOtpViewModel.generateAuthOtp(androidx.fragment.app.FragmentActivity, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final CountDownTimer getCountDownTimer$app_prodRelease() {
        return this.g;
    }

    @Nullable
    public final OTPChecksListener getListener() {
        return this.e;
    }

    @Nullable
    public final ContractEnterOTP getMContractEnterOTP() {
        return this.d;
    }

    public final void getOtpServices() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mobileNumber", AppConstants.COUNTRY_CODE.getValue() + AppConstants.PHONE_NO.getValue());
        CoveApi.getService().getAvailableOtpServices(CoveApi.getCustomHeaders(), jsonObject).enqueue(new Callback<CommonResponseGeneric<GetOTPServicesRes>>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.FragmentEnterOtpViewModel$getOtpServices$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<CommonResponseGeneric<GetOTPServicesRes>> call, @NotNull Throwable t) {
                OTPChecksListener listener;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t, "t");
                String message = t.getMessage();
                if (message == null || (listener = FragmentEnterOtpViewModel.this.getListener()) == null) {
                    return;
                }
                listener.onFailure(message);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<CommonResponseGeneric<GetOTPServicesRes>> call, @NotNull Response<CommonResponseGeneric<GetOTPServicesRes>> response) {
                GetOTPServicesRes data;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    OTPChecksListener listener = FragmentEnterOtpViewModel.this.getListener();
                    if (listener != null) {
                        listener.onSuccess();
                    }
                    SingleLiveEvent<List<GetOTPServicesRes.ServiceType>> otpServicesList = FragmentEnterOtpViewModel.this.getOtpServicesList();
                    CommonResponseGeneric<GetOTPServicesRes> body = response.body();
                    otpServicesList.postValue((body == null || (data = body.getData()) == null) ? null : data.getItems());
                    return;
                }
                OTPChecksListener listener2 = FragmentEnterOtpViewModel.this.getListener();
                if (listener2 != null) {
                    String message = response.message();
                    Intrinsics.checkNotNullExpressionValue(message, "response.message()");
                    listener2.onFailure(message);
                }
            }
        });
    }

    @NotNull
    public final SingleLiveEvent<List<GetOTPServicesRes.ServiceType>> getOtpServicesList() {
        return this.h;
    }

    public final void setCountDownTimer$app_prodRelease(@Nullable CountDownTimer countDownTimer) {
        this.g = countDownTimer;
    }

    public final void setListener(@Nullable OTPChecksListener oTPChecksListener) {
        this.e = oTPChecksListener;
    }

    public final void setMContractEnterOTP(@Nullable ContractEnterOTP contractEnterOTP) {
        this.d = contractEnterOTP;
    }

    public final void setOtpServicesList(@NotNull SingleLiveEvent<List<GetOTPServicesRes.ServiceType>> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.h = singleLiveEvent;
    }

    public final void startTimer(int i) {
        CountDownTimer countDownTimer = this.g;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        final long j = i;
        final long j2 = this.f;
        CountDownTimer countDownTimer2 = new CountDownTimer(j, j2) { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.FragmentEnterOtpViewModel$startTimer$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                ContractEnterOTP mContractEnterOTP = FragmentEnterOtpViewModel.this.getMContractEnterOTP();
                Intrinsics.checkNotNull(mContractEnterOTP);
                mContractEnterOTP.onTimerFinished();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j3) {
                ContractEnterOTP mContractEnterOTP = FragmentEnterOtpViewModel.this.getMContractEnterOTP();
                Intrinsics.checkNotNull(mContractEnterOTP);
                mContractEnterOTP.onTimerValueChanged((int) (j3 / 1000));
            }
        };
        this.g = countDownTimer2;
        Intrinsics.checkNotNull(countDownTimer2);
        countDownTimer2.start();
    }

    public final void stopTimer() {
        CountDownTimer countDownTimer = this.g;
        if (countDownTimer != null) {
            Intrinsics.checkNotNull(countDownTimer);
            countDownTimer.cancel();
        }
    }
}
