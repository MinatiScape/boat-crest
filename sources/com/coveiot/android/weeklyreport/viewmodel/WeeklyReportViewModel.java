package com.coveiot.android.weeklyreport.viewmodel;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.listeners.OnSuccessListener;
import com.coveiot.android.weeklyreport.listeners.TimerListener;
import com.coveiot.android.weeklyreport.utils.WeeklyReportConstant;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.weeklyreport.WeeklyReportApiManager;
import com.coveiot.coveaccess.weeklyreport.request.GenerateOtpReq;
import com.coveiot.coveaccess.weeklyreport.request.VerifyOtpReq;
import com.coveiot.coveaccess.weeklyreport.request.WeeklyReportSubscriptionReq;
import com.coveiot.coveaccess.weeklyreport.request.WeeklyReportUnsubscribeReq;
import com.coveiot.coveaccess.weeklyreport.response.FitnessReportRes;
import com.coveiot.coveaccess.weeklyreport.response.GenerateVerifyOtpRes;
import com.coveiot.coveaccess.weeklyreport.response.WeekReportSubscriptionResponse;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.WeeklyReportPrefData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwx.HeaderParameterNames;
/* loaded from: classes8.dex */
public final class WeeklyReportViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6246a;
    @Nullable
    public TimerListener b;
    @Nullable
    public OnSuccessListener c;
    @NotNull
    public MutableLiveData<String> d;
    @NotNull
    public MutableLiveData<String> e;
    @NotNull
    public MutableLiveData<String> f;
    @Nullable
    public final MutableLiveData<List<FitnessReportRes.FitnessReportItem>> g;
    public final long h;
    @Nullable
    public String i;
    @Nullable
    public CountDownTimer j;

    public WeeklyReportViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6246a = context;
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = 1000L;
    }

    public final void generateOtp(@NotNull String emailId) {
        Intrinsics.checkNotNullParameter(emailId, "emailId");
        GenerateOtpReq generateOtpReq = new GenerateOtpReq();
        generateOtpReq.setEmailId(emailId);
        generateOtpReq.setOtpLength(4);
        WeeklyReportApiManager.sendEmailToGenerateOtp(generateOtpReq, new CoveApiListener<GenerateVerifyOtpRes, CoveApiErrorModel>() { // from class: com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel$generateOtp$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LogHelper.d(HeaderParameterNames.AUTHENTICATION_TAG, "Save  auto recognize error");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull GenerateVerifyOtpRes generateVerifyOtpRes) {
                Intrinsics.checkNotNullParameter(generateVerifyOtpRes, "generateVerifyOtpRes");
                WeeklyReportViewModel.this.i = generateVerifyOtpRes.getData().getVerificationId();
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f6246a;
    }

    @Nullable
    public final CountDownTimer getCountDownTimer$weeklyreport_prodRelease() {
        return this.j;
    }

    @Nullable
    public final MutableLiveData<List<FitnessReportRes.FitnessReportItem>> getGetWeeklyReportLiveData() {
        return this.g;
    }

    @NotNull
    public final MutableLiveData<String> getInvalidOtpUiLivedata() {
        return this.d;
    }

    @Nullable
    public final OnSuccessListener getOnSuccessListener() {
        return this.c;
    }

    @NotNull
    public final MutableLiveData<String> getSuccessLiveData() {
        return this.e;
    }

    @Nullable
    public final TimerListener getTimerListener() {
        return this.b;
    }

    @NotNull
    public final MutableLiveData<String> getUnSubscribeLiveData() {
        return this.f;
    }

    @NotNull
    public final ArrayList<String> getUnSubscribeReason() {
        String string = this.f6246a.getResources().getString(R.string.please_select_reason);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ing.please_select_reason)");
        String string2 = this.f6246a.getResources().getString(R.string.email_is_not_relevant);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ng.email_is_not_relevant)");
        String string3 = this.f6246a.getResources().getString(R.string.email_are_too_frequent);
        Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…g.email_are_too_frequent)");
        String string4 = this.f6246a.getResources().getString(R.string.no_longer_want_this_email);
        Intrinsics.checkNotNullExpressionValue(string4, "context.resources.getStr…o_longer_want_this_email)");
        String string5 = this.f6246a.getResources().getString(R.string.more_detailed_insights);
        Intrinsics.checkNotNullExpressionValue(string5, "context.resources.getStr…g.more_detailed_insights)");
        String string6 = this.f6246a.getResources().getString(R.string.no_longer_using_product);
        Intrinsics.checkNotNullExpressionValue(string6, "context.resources.getStr….no_longer_using_product)");
        return CollectionsKt__CollectionsKt.arrayListOf(string, string2, string3, string4, string5, string6);
    }

    public final void getWeeklyReportHistory() {
        LogHelper.d("FitnessReport", "Method get called");
        WeeklyReportApiManager.getWeeklyFitnessReports(new CoveApiListener<FitnessReportRes, CoveApiErrorModel>() { // from class: com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel$getWeeklyReportHistory$1

            @DebugMetadata(c = "com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel$getWeeklyReportHistory$1$onError$1", f = "WeeklyReportViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes8.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ WeeklyReportViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(WeeklyReportViewModel weeklyReportViewModel, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = weeklyReportViewModel;
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
                        MutableLiveData<List<FitnessReportRes.FitnessReportItem>> getWeeklyReportLiveData = this.this$0.getGetWeeklyReportLiveData();
                        if (getWeeklyReportLiveData != null) {
                            getWeeklyReportLiveData.postValue(null);
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel$getWeeklyReportHistory$1$onSuccess$1", f = "WeeklyReportViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes8.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ FitnessReportRes $fitnessReportRes;
                public int label;
                public final /* synthetic */ WeeklyReportViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(FitnessReportRes fitnessReportRes, WeeklyReportViewModel weeklyReportViewModel, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.$fitnessReportRes = fitnessReportRes;
                    this.this$0 = weeklyReportViewModel;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new b(this.$fitnessReportRes, this.this$0, continuation);
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
                        FitnessReportRes fitnessReportRes = this.$fitnessReportRes;
                        WeeklyReportViewModel weeklyReportViewModel = this.this$0;
                        Intrinsics.checkNotNull(fitnessReportRes);
                        List<FitnessReportRes.FitnessReportItem> items = fitnessReportRes.getItems();
                        if (!(items == null || items.isEmpty())) {
                            MutableLiveData<List<FitnessReportRes.FitnessReportItem>> getWeeklyReportLiveData = weeklyReportViewModel.getGetWeeklyReportLiveData();
                            if (getWeeklyReportLiveData != null) {
                                getWeeklyReportLiveData.postValue(fitnessReportRes.getItems());
                            }
                        } else {
                            MutableLiveData<List<FitnessReportRes.FitnessReportItem>> getWeeklyReportLiveData2 = weeklyReportViewModel.getGetWeeklyReportLiveData();
                            if (getWeeklyReportLiveData2 != null) {
                                getWeeklyReportLiveData2.postValue(null);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LogHelper.d("FitnessReport", "ReportFail");
                e.e(ViewModelKt.getViewModelScope(WeeklyReportViewModel.this), Dispatchers.getMain(), null, new a(WeeklyReportViewModel.this, null), 2, null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable FitnessReportRes fitnessReportRes) {
                LogHelper.d("FitnessReport", "ReportSuccess");
                e.e(ViewModelKt.getViewModelScope(WeeklyReportViewModel.this), Dispatchers.getMain(), null, new b(fitnessReportRes, WeeklyReportViewModel.this, null), 2, null);
            }
        });
    }

    public final void setCountDownTimer$weeklyreport_prodRelease(@Nullable CountDownTimer countDownTimer) {
        this.j = countDownTimer;
    }

    public final void setInvalidOtpUiLivedata(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setOnSuccessListener(@Nullable OnSuccessListener onSuccessListener) {
        this.c = onSuccessListener;
    }

    public final void setSuccessLiveData(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setTimerListener(@Nullable TimerListener timerListener) {
        this.b = timerListener;
    }

    public final void setUnSubscribeLiveData(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void startTimer(int i) {
        if (this.j == null) {
            final long j = i;
            final long j2 = this.h;
            this.j = new CountDownTimer(j, j2) { // from class: com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel$startTimer$1
                @Override // android.os.CountDownTimer
                public void onFinish() {
                    TimerListener timerListener = WeeklyReportViewModel.this.getTimerListener();
                    Intrinsics.checkNotNull(timerListener);
                    timerListener.onTimerFinished();
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j3) {
                    TimerListener timerListener = WeeklyReportViewModel.this.getTimerListener();
                    Intrinsics.checkNotNull(timerListener);
                    timerListener.onTimerValueChanged((int) (j3 / 1000));
                }
            };
        }
        CountDownTimer countDownTimer = this.j;
        Intrinsics.checkNotNull(countDownTimer);
        countDownTimer.start();
    }

    public final void stopTimer() {
        CountDownTimer countDownTimer = this.j;
        if (countDownTimer != null) {
            Intrinsics.checkNotNull(countDownTimer);
            countDownTimer.cancel();
        }
    }

    public final void subscribeEmail() {
        WeeklyReportSubscriptionReq weeklyReportSubscriptionReq = new WeeklyReportSubscriptionReq();
        WeeklyReportSubscriptionReq.Subscription subscription = new WeeklyReportSubscriptionReq.Subscription();
        ArrayList arrayList = new ArrayList();
        subscription.setMedium(WeeklyReportConstant.EMAIL.getValue());
        subscription.setTopic(WeeklyReportConstant.WEEKLY_FITNESS_REPORT.getValue());
        arrayList.add(subscription);
        weeklyReportSubscriptionReq.setSubscriptions(arrayList);
        WeeklyReportApiManager.subscribeForWeeklyReport(weeklyReportSubscriptionReq, new CoveApiListener<WeekReportSubscriptionResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel$subscribeEmail$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                OnSuccessListener onSuccessListener;
                boolean z = false;
                if (coveApiErrorModel != null && coveApiErrorModel.getCode() == 400) {
                    z = true;
                }
                if (z) {
                    WeeklyReportViewModel.this.getSuccessLiveData().postValue(WeeklyReportConstant.SUBSCRIBE_SUCCESS.getValue());
                }
                if (coveApiErrorModel == null || (onSuccessListener = WeeklyReportViewModel.this.getOnSuccessListener()) == null) {
                    return;
                }
                String msg = coveApiErrorModel.getMsg();
                Intrinsics.checkNotNull(msg);
                onSuccessListener.onDataFailure(msg);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull WeekReportSubscriptionResponse weekReportSubscriptionResponse) {
                Intrinsics.checkNotNullParameter(weekReportSubscriptionResponse, "weekReportSubscriptionResponse");
                WeeklyReportPrefData weeklyReportData = UserDataManager.getInstance(WeeklyReportViewModel.this.getContext()).getWeeklyReportData();
                weeklyReportData.setSubscribed(true);
                UserDataManager.getInstance(WeeklyReportViewModel.this.getContext()).saveWeeklyReport(weeklyReportData);
                WeeklyReportViewModel.this.getSuccessLiveData().postValue(WeeklyReportConstant.SUBSCRIBE_SUCCESS.getValue());
                OnSuccessListener onSuccessListener = WeeklyReportViewModel.this.getOnSuccessListener();
                if (onSuccessListener != null) {
                    onSuccessListener.onSuccess();
                }
            }
        });
    }

    public final void unSubscribeEmail(@NotNull String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (AppUtils.isNetConnected(this.f6246a)) {
            WeeklyReportUnsubscribeReq weeklyReportUnsubscribeReq = new WeeklyReportUnsubscribeReq();
            WeeklyReportUnsubscribeReq.Subscription subscription = new WeeklyReportUnsubscribeReq.Subscription();
            ArrayList arrayList = new ArrayList();
            subscription.setMedium(WeeklyReportConstant.EMAIL.getValue());
            subscription.setTopic(WeeklyReportConstant.WEEKLY_FITNESS_REPORT.getValue());
            subscription.setUnsubscribeReason(reason);
            arrayList.add(subscription);
            weeklyReportUnsubscribeReq.setSubscriptions(arrayList);
            WeeklyReportApiManager.unsubscribeFromWeeklyReport(weeklyReportUnsubscribeReq, new CoveApiListener<JsonObject, CoveApiErrorModel>() { // from class: com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel$unSubscribeEmail$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    OnSuccessListener onSuccessListener;
                    if (coveApiErrorModel == null || (onSuccessListener = WeeklyReportViewModel.this.getOnSuccessListener()) == null) {
                        return;
                    }
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNull(msg);
                    onSuccessListener.onDataFailure(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull JsonObject jsonObject) {
                    Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                    WeeklyReportPrefData weeklyReportData = UserDataManager.getInstance(WeeklyReportViewModel.this.getContext()).getWeeklyReportData();
                    weeklyReportData.setSubscribed(false);
                    UserDataManager.getInstance(WeeklyReportViewModel.this.getContext()).saveWeeklyReport(weeklyReportData);
                    WeeklyReportViewModel.this.getUnSubscribeLiveData().postValue(jsonObject.get(Constants.KEY_MESSAGE).toString());
                    LogHelper.d(HeaderParameterNames.AUTHENTICATION_TAG, "unsubscribe  *** " + jsonObject.get(Constants.KEY_MESSAGE));
                    OnSuccessListener onSuccessListener = WeeklyReportViewModel.this.getOnSuccessListener();
                    if (onSuccessListener != null) {
                        onSuccessListener.onSuccess();
                    }
                }
            });
            return;
        }
        OnSuccessListener onSuccessListener = this.c;
        if (onSuccessListener != null) {
            onSuccessListener.onSuccess();
        }
        Context context = this.f6246a;
        Toast.makeText(context, context.getResources().getString(R.string.please_check_your_internet), 1).show();
    }

    public final void verifylOtp(int i) {
        if (AppUtils.isNetConnected(this.f6246a)) {
            VerifyOtpReq verifyOtpReq = new VerifyOtpReq();
            verifyOtpReq.setOtp(Integer.valueOf(i));
            verifyOtpReq.setVerificationId(this.i);
            WeeklyReportApiManager.verifyEmailOtp(verifyOtpReq, new CoveApiListener<GenerateVerifyOtpRes, CoveApiErrorModel>() { // from class: com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel$verifylOtp$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    String msg;
                    OnSuccessListener onSuccessListener;
                    if (coveApiErrorModel != null && coveApiErrorModel.getCode() == 401) {
                        WeeklyReportViewModel.this.getInvalidOtpUiLivedata().postValue(WeeklyReportViewModel.this.getContext().getResources().getString(R.string.invalid_otp));
                    } else {
                        Context context = WeeklyReportViewModel.this.getContext();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Error ");
                        sb.append(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                        sb.append(" Code ");
                        sb.append(coveApiErrorModel != null ? Integer.valueOf(coveApiErrorModel.getCode()) : null);
                        Toast.makeText(context, sb.toString(), 0).show();
                    }
                    if (coveApiErrorModel != null && (msg = coveApiErrorModel.getMsg()) != null && (onSuccessListener = WeeklyReportViewModel.this.getOnSuccessListener()) != null) {
                        onSuccessListener.onDataFailure(msg);
                    }
                    LogHelper.d(HeaderParameterNames.AUTHENTICATION_TAG, "Save  auto recognize error");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull GenerateVerifyOtpRes generateVerifyOtpRes) {
                    Intrinsics.checkNotNullParameter(generateVerifyOtpRes, "generateVerifyOtpRes");
                    ProfileData userDetails = SessionManager.getInstance(WeeklyReportViewModel.this.getContext()).getUserDetails();
                    userDetails.setEmail(generateVerifyOtpRes.getData().getEmailId());
                    SessionManager.getInstance(WeeklyReportViewModel.this.getContext()).createLoginSession(userDetails);
                    WeeklyReportPrefData weeklyReportData = UserDataManager.getInstance(WeeklyReportViewModel.this.getContext()).getWeeklyReportData();
                    if (weeklyReportData == null) {
                        weeklyReportData = WeeklyReportPrefData.getInstance();
                    }
                    weeklyReportData.setEmailVerified(true);
                    UserDataManager.getInstance(WeeklyReportViewModel.this.getContext()).saveWeeklyReport(weeklyReportData);
                    if (!weeklyReportData.isSubscribed()) {
                        WeeklyReportViewModel.this.subscribeEmail();
                    } else {
                        OnSuccessListener onSuccessListener = WeeklyReportViewModel.this.getOnSuccessListener();
                        if (onSuccessListener != null) {
                            onSuccessListener.onSuccess();
                        }
                        WeeklyReportViewModel.this.getSuccessLiveData().postValue(WeeklyReportConstant.SUBSCRIBE_SUCCESS.getValue());
                        Toast.makeText(WeeklyReportViewModel.this.getContext(), WeeklyReportViewModel.this.getContext().getResources().getString(R.string.email_verified_successfully), 0).show();
                    }
                    WeeklyReportViewModel.this.getInvalidOtpUiLivedata().postValue(WeeklyReportViewModel.this.getContext().getResources().getString(R.string.valid_otp));
                }
            });
            return;
        }
        OnSuccessListener onSuccessListener = this.c;
        if (onSuccessListener != null) {
            onSuccessListener.onSuccess();
        }
        Context context = this.f6246a;
        Toast.makeText(context, context.getResources().getString(R.string.please_check_your_internet), 1).show();
    }
}
