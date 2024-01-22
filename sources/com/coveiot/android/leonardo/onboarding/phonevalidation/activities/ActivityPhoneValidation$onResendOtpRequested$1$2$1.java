package com.coveiot.android.leonardo.onboarding.phonevalidation.activities;

import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel;
import com.google.android.recaptcha.RecaptchaErrorCode;
import com.google.android.recaptcha.RecaptchaException;
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
@DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onResendOtpRequested$1$2$1", f = "ActivityPhoneValidation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class ActivityPhoneValidation$onResendOtpRequested$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Throwable $exception;
    public final /* synthetic */ String $phoneNumber;
    public int label;
    public final /* synthetic */ ActivityPhoneValidation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityPhoneValidation$onResendOtpRequested$1$2$1(Throwable th, ActivityPhoneValidation activityPhoneValidation, String str, Continuation<? super ActivityPhoneValidation$onResendOtpRequested$1$2$1> continuation) {
        super(2, continuation);
        this.$exception = th;
        this.this$0 = activityPhoneValidation;
        this.$phoneNumber = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityPhoneValidation$onResendOtpRequested$1$2$1(this.$exception, this.this$0, this.$phoneNumber, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityPhoneValidation$onResendOtpRequested$1$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Throwable th = this.$exception;
            if (th instanceof RecaptchaException) {
                RecaptchaErrorCode errorCode = ((RecaptchaException) th).getErrorCode();
                if (errorCode != RecaptchaErrorCode.INTERNAL_ERROR && errorCode != RecaptchaErrorCode.NETWORK_ERROR && errorCode != RecaptchaErrorCode.UNKNOWN_ERROR) {
                    this.this$0.dismissProgress();
                    Toast.makeText(this.this$0, (int) R.string.snap_we_didnt_expect, 1).show();
                } else {
                    ActivityPhoneValidationViewModel viewModelActivityPhoneValidation = this.this$0.getViewModelActivityPhoneValidation();
                    if (viewModelActivityPhoneValidation != null) {
                        String str = this.$phoneNumber;
                        final ActivityPhoneValidation activityPhoneValidation = this.this$0;
                        viewModelActivityPhoneValidation.sendOtp(true, null, str, new ActivityPhoneValidationViewModel.SendOTPCallback() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onResendOtpRequested$1$2$1.1

                            @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onResendOtpRequested$1$2$1$1$onFailure$1", f = "ActivityPhoneValidation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onResendOtpRequested$1$2$1$1$a */
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

                            @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onResendOtpRequested$1$2$1$1$onSuccess$1", f = "ActivityPhoneValidation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onResendOtpRequested$1$2$1$1$b */
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
            } else {
                this.this$0.dismissProgress();
                Toast.makeText(this.this$0, (int) R.string.snap_we_didnt_expect, 1).show();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
