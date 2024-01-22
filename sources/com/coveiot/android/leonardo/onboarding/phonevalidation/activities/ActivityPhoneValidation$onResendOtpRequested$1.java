package com.coveiot.android.leonardo.onboarding.phonevalidation.activities;

import android.app.Application;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.boat.GenericBandApplication;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaClient;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onResendOtpRequested$1", f = "ActivityPhoneValidation.kt", i = {}, l = {421}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class ActivityPhoneValidation$onResendOtpRequested$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $phoneNumber;
    public int label;
    public final /* synthetic */ ActivityPhoneValidation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityPhoneValidation$onResendOtpRequested$1(ActivityPhoneValidation activityPhoneValidation, String str, Continuation<? super ActivityPhoneValidation$onResendOtpRequested$1> continuation) {
        super(2, continuation);
        this.this$0 = activityPhoneValidation;
        this.$phoneNumber = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityPhoneValidation$onResendOtpRequested$1(this.this$0, this.$phoneNumber, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityPhoneValidation$onResendOtpRequested$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object mo113executegIAlus;
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            RecaptchaAction copy = RecaptchaAction.SIGNUP.copy("signin_otp");
            String tag = this.this$0.getTAG();
            LogHelper.d(tag, "recaptcha Action : " + copy);
            Application application = this.this$0.getApplication();
            Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.coveiot.android.boat.GenericBandApplication");
            RecaptchaClient recaptchaClient = ((GenericBandApplication) application).getRecaptchaClient();
            if (recaptchaClient != null) {
                this.label = 1;
                mo113executegIAlus = recaptchaClient.mo113executegIAlus(copy, this);
                if (mo113executegIAlus == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
            mo113executegIAlus = ((Result) obj).m132unboximpl();
        }
        final ActivityPhoneValidation activityPhoneValidation = this.this$0;
        String str = this.$phoneNumber;
        if (Result.m130isSuccessimpl(mo113executegIAlus)) {
            String str2 = (String) mo113executegIAlus;
            ActivityPhoneValidationViewModel viewModelActivityPhoneValidation = activityPhoneValidation.getViewModelActivityPhoneValidation();
            if (viewModelActivityPhoneValidation != null) {
                viewModelActivityPhoneValidation.sendOtp(true, str2, str, new ActivityPhoneValidationViewModel.SendOTPCallback() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onResendOtpRequested$1$1$1

                    @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onResendOtpRequested$1$1$1$onFailure$1", f = "ActivityPhoneValidation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* loaded from: classes5.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public final /* synthetic */ String $message;
                        public int label;
                        public final /* synthetic */ ActivityPhoneValidation this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(ActivityPhoneValidation activityPhoneValidation, String str, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.this$0 = activityPhoneValidation;
                            this.$message = str;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new a(this.this$0, this.$message, continuation);
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
                                Toast.makeText(this.this$0, this.$message, 1).show();
                                this.this$0.dismissProgress();
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onResendOtpRequested$1$1$1$onSuccess$1", f = "ActivityPhoneValidation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* loaded from: classes5.dex */
                    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public int label;
                        public final /* synthetic */ ActivityPhoneValidation this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public b(ActivityPhoneValidation activityPhoneValidation, Continuation<? super b> continuation) {
                            super(2, continuation);
                            this.this$0 = activityPhoneValidation;
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
                                this.this$0.dismissProgress();
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel.SendOTPCallback
                    public void onFailure(@NotNull String message) {
                        Intrinsics.checkNotNullParameter(message, "message");
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityPhoneValidation.this), Dispatchers.getMain(), null, new a(ActivityPhoneValidation.this, message, null), 2, null);
                    }

                    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel.SendOTPCallback
                    public void onSuccess() {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityPhoneValidation.this), Dispatchers.getMain(), null, new b(ActivityPhoneValidation.this, null), 2, null);
                    }
                });
            }
        }
        ActivityPhoneValidation activityPhoneValidation2 = this.this$0;
        String str3 = this.$phoneNumber;
        Throwable m126exceptionOrNullimpl = Result.m126exceptionOrNullimpl(mo113executegIAlus);
        if (m126exceptionOrNullimpl != null) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(activityPhoneValidation2), Dispatchers.getMain(), null, new ActivityPhoneValidation$onResendOtpRequested$1$2$1(m126exceptionOrNullimpl, activityPhoneValidation2, str3, null), 2, null);
            LogHelper.e(activityPhoneValidation2.getTAG(), m126exceptionOrNullimpl.getLocalizedMessage());
        }
        Result.m122boximpl(mo113executegIAlus);
        return Unit.INSTANCE;
    }
}
