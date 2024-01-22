package com.coveiot.android.tappy.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.customview.ErrorDialog;
import com.coveiot.android.tappy.databinding.ActivityActivateCardBinding;
import com.coveiot.android.tappy.fragment.FragmentActivateCard;
import com.coveiot.android.tappy.fragment.FragmentEnterOtp;
import com.coveiot.android.tappy.fragment.FragmentStepUpOptions;
import com.coveiot.android.tappy.model.GetStepUpOptionsResponse;
import com.coveiot.android.tappy.model.StepUpRequest;
import com.coveiot.android.tappy.model.ValidateOTPResponse;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.tappy.utils.StepUpOptions;
import com.coveiot.android.tappy.viewmodel.NfcStrapViewModel;
import com.coveiot.android.tappy.viewmodel.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.CleverTapConstants;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CardActivationActivity extends BaseActivity {
    @Nullable
    public String A;
    @Nullable
    public String B;
    @Nullable
    public ErrorDialog C;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityActivateCardBinding p;
    public NfcStrapViewModel q;
    @Nullable
    public Long r;
    @Nullable
    public Long s;
    @Nullable
    public String t;
    @Nullable
    public Long u;
    @Nullable
    public Long v;
    @Nullable
    public GetStepUpOptionsResponse w;
    @Nullable
    public StepUpOptions x;
    public boolean y;
    @Nullable
    public String z;

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.CardActivationActivity$activateCard$1", f = "CardActivationActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
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
                NfcStrapViewModel nfcStrapViewModel = CardActivationActivity.this.q;
                if (nfcStrapViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    nfcStrapViewModel = null;
                }
                NfcStrapViewModel nfcStrapViewModel2 = nfcStrapViewModel;
                Long endUserGlobalId = CardActivationActivity.this.getEndUserGlobalId();
                Intrinsics.checkNotNull(endUserGlobalId);
                long longValue = endUserGlobalId.longValue();
                Long paymentInstrumentTokenId = CardActivationActivity.this.getPaymentInstrumentTokenId();
                Intrinsics.checkNotNull(paymentInstrumentTokenId);
                long longValue2 = paymentInstrumentTokenId.longValue();
                Long deviceId = CardActivationActivity.this.getDeviceId();
                Intrinsics.checkNotNull(deviceId);
                nfcStrapViewModel2.getStepUpOptions(longValue, longValue2, deviceId.longValue());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes7.dex */
    public static final class b extends Lambda implements Function1<Intent, Unit> {
        public static final b INSTANCE = new b();

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.addFlags(67108864);
        }
    }

    /* loaded from: classes7.dex */
    public static final class c extends Lambda implements Function1<Intent, Unit> {
        public static final c INSTANCE = new c();

        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.addFlags(67108864);
        }
    }

    /* loaded from: classes7.dex */
    public static final class d extends Lambda implements Function1<GetStepUpOptionsResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.CardActivationActivity$onCreate$2$1", f = "CardActivationActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ GetStepUpOptionsResponse $it;
            public int label;
            public final /* synthetic */ CardActivationActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(CardActivationActivity cardActivationActivity, GetStepUpOptionsResponse getStepUpOptionsResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = cardActivationActivity;
                this.$it = getStepUpOptionsResponse;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$it, continuation);
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
                    this.this$0.dismissProgress();
                    this.this$0.setGetStepUpOptionsResponse(this.$it);
                    this.this$0.y();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.CardActivationActivity$onCreate$2$2", f = "CardActivationActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ CardActivationActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(CardActivationActivity cardActivationActivity, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = cardActivationActivity;
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
                    CardActivationActivity cardActivationActivity = this.this$0;
                    String string = cardActivationActivity.getString(R.string.some_error_occurred_try_again);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                    CardActivationActivity.G(cardActivationActivity, string, false, null, 4, null);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetStepUpOptionsResponse getStepUpOptionsResponse) {
            invoke2(getStepUpOptionsResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable GetStepUpOptionsResponse getStepUpOptionsResponse) {
            if (getStepUpOptionsResponse != null) {
                List<StepUpRequest> stepUpOptions = getStepUpOptionsResponse.getStepUpOptions();
                if (!(stepUpOptions == null || stepUpOptions.isEmpty())) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(CardActivationActivity.this), Dispatchers.getMain(), null, new a(CardActivationActivity.this, getStepUpOptionsResponse, null), 2, null);
                    return;
                }
            }
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(CardActivationActivity.this), Dispatchers.getMain(), null, new b(CardActivationActivity.this, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class e extends Lambda implements Function1<Boolean, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.CardActivationActivity$onCreate$3$1", f = "CardActivationActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ CardActivationActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(CardActivationActivity cardActivationActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = cardActivationActivity;
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
                    this.this$0.dismissProgress();
                    if (!this.this$0.isFromResendOtp()) {
                        this.this$0.loadEnterOtpFragment();
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.CardActivationActivity$onCreate$3$2", f = "CardActivationActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ CardActivationActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(CardActivationActivity cardActivationActivity, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = cardActivationActivity;
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
                    CardActivationActivity cardActivationActivity = this.this$0;
                    String string = cardActivationActivity.getString(R.string.some_error_occurred_try_again);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                    CardActivationActivity.G(cardActivationActivity, string, false, null, 4, null);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean it) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (it.booleanValue()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(CardActivationActivity.this), Dispatchers.getMain(), null, new a(CardActivationActivity.this, null), 2, null);
            } else {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(CardActivationActivity.this), Dispatchers.getMain(), null, new b(CardActivationActivity.this, null), 2, null);
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class f extends Lambda implements Function1<ValidateOTPResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.CardActivationActivity$onCreate$4$1", f = "CardActivationActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ CardActivationActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(CardActivationActivity cardActivationActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = cardActivationActivity;
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
                    this.this$0.dismissProgress();
                    this.this$0.I();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.CardActivationActivity$onCreate$4$2", f = "CardActivationActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ValidateOTPResponse $it;
            public int label;
            public final /* synthetic */ CardActivationActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(CardActivationActivity cardActivationActivity, ValidateOTPResponse validateOTPResponse, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = cardActivationActivity;
                this.$it = validateOTPResponse;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, this.$it, continuation);
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
                    if (kotlin.text.m.equals$default(this.$it.getErrorMessage(), this.this$0.getString(R.string.max_otp_verification_exceeded), false, 2, null)) {
                        CardActivationActivity cardActivationActivity = this.this$0;
                        String errorMessage = this.$it.getErrorMessage();
                        Intrinsics.checkNotNull(errorMessage);
                        cardActivationActivity.F(errorMessage, true, this.this$0.getString(R.string.ok));
                    } else {
                        CardActivationActivity cardActivationActivity2 = this.this$0;
                        String errorMessage2 = this.$it.getErrorMessage();
                        Intrinsics.checkNotNull(errorMessage2);
                        CardActivationActivity.G(cardActivationActivity2, errorMessage2, false, null, 4, null);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.CardActivationActivity$onCreate$4$3", f = "CardActivationActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ CardActivationActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(CardActivationActivity cardActivationActivity, Continuation<? super c> continuation) {
                super(2, continuation);
                this.this$0 = cardActivationActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new c(this.this$0, continuation);
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
                    this.this$0.dismissProgress();
                    CardActivationActivity cardActivationActivity = this.this$0;
                    String string = cardActivationActivity.getString(R.string.some_error_occurred_try_again);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                    cardActivationActivity.F(string, true, this.this$0.getString(R.string.ok));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public f() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ValidateOTPResponse validateOTPResponse) {
            invoke2(validateOTPResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable ValidateOTPResponse validateOTPResponse) {
            if (validateOTPResponse == null) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(CardActivationActivity.this), Dispatchers.getMain(), null, new c(CardActivationActivity.this, null), 2, null);
                DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_OTP_VERIF_FAILED.getValue(), null);
            } else if (validateOTPResponse.getErrorMessage() == null) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(CardActivationActivity.this), Dispatchers.getMain(), null, new a(CardActivationActivity.this, null), 2, null);
            } else {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(CardActivationActivity.this), Dispatchers.getMain(), null, new b(CardActivationActivity.this, validateOTPResponse, null), 2, null);
                DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_OTP_VERIF_FAILED.getValue(), null);
            }
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.CardActivationActivity$putStepUpOption$1", f = "CardActivationActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public g(Continuation<? super g> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                GetStepUpOptionsResponse getStepUpOptionsResponse = CardActivationActivity.this.getGetStepUpOptionsResponse();
                Intrinsics.checkNotNull(getStepUpOptionsResponse);
                List<StepUpRequest> stepUpOptions = getStepUpOptionsResponse.getStepUpOptions();
                Intrinsics.checkNotNull(stepUpOptions);
                Iterator<StepUpRequest> it = stepUpOptions.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    StepUpRequest next = it.next();
                    String method = next.getMethod();
                    StepUpOptions selectedOption = CardActivationActivity.this.getSelectedOption();
                    Intrinsics.checkNotNull(selectedOption);
                    if (Intrinsics.areEqual(method, selectedOption.name())) {
                        CardActivationActivity.this.setStepUpId(next.getId());
                        break;
                    }
                }
                NfcStrapViewModel nfcStrapViewModel = CardActivationActivity.this.q;
                if (nfcStrapViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    nfcStrapViewModel = null;
                }
                NfcStrapViewModel nfcStrapViewModel2 = nfcStrapViewModel;
                Long endUserGlobalId = CardActivationActivity.this.getEndUserGlobalId();
                Intrinsics.checkNotNull(endUserGlobalId);
                long longValue = endUserGlobalId.longValue();
                Long paymentInstrumentTokenId = CardActivationActivity.this.getPaymentInstrumentTokenId();
                Intrinsics.checkNotNull(paymentInstrumentTokenId);
                long longValue2 = paymentInstrumentTokenId.longValue();
                String stepUpId = CardActivationActivity.this.getStepUpId();
                Intrinsics.checkNotNull(stepUpId);
                Long deviceId = CardActivationActivity.this.getDeviceId();
                Intrinsics.checkNotNull(deviceId);
                nfcStrapViewModel2.putStepUpOptions(longValue, longValue2, stepUpId, deviceId.longValue());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes7.dex */
    public static final class h extends Lambda implements Function1<Intent, Unit> {
        public static final h INSTANCE = new h();

        public h() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.addFlags(67108864);
        }
    }

    /* loaded from: classes7.dex */
    public static final class i extends Lambda implements Function1<Intent, Unit> {
        public static final i INSTANCE = new i();

        public i() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.addFlags(67108864);
        }
    }

    /* loaded from: classes7.dex */
    public static final class j extends Lambda implements Function1<Intent, Unit> {
        public static final j INSTANCE = new j();

        public j() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.addFlags(67108864);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.CardActivationActivity$validateOtp$1", f = "CardActivationActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class k extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $otp;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public k(String str, Continuation<? super k> continuation) {
            super(2, continuation);
            this.$otp = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new k(this.$otp, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((k) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                NfcStrapViewModel nfcStrapViewModel = CardActivationActivity.this.q;
                if (nfcStrapViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    nfcStrapViewModel = null;
                }
                NfcStrapViewModel nfcStrapViewModel2 = nfcStrapViewModel;
                Long endUserGlobalId = CardActivationActivity.this.getEndUserGlobalId();
                Intrinsics.checkNotNull(endUserGlobalId);
                long longValue = endUserGlobalId.longValue();
                Long paymentInstrumentTokenId = CardActivationActivity.this.getPaymentInstrumentTokenId();
                Intrinsics.checkNotNull(paymentInstrumentTokenId);
                long longValue2 = paymentInstrumentTokenId.longValue();
                String str = this.$otp;
                Long deviceId = CardActivationActivity.this.getDeviceId();
                Intrinsics.checkNotNull(deviceId);
                nfcStrapViewModel2.validateOTP(longValue, longValue2, str, deviceId.longValue());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void B(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void C(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void E(CardActivationActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentManager supportFragmentManager = this$0.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        Fragment findFragmentById = supportFragmentManager.findFragmentById(R.id.fragment_container);
        if (findFragmentById instanceof FragmentEnterOtp) {
            this$0.y();
        } else if (findFragmentById instanceof FragmentStepUpOptions) {
            this$0.x();
        } else {
            h hVar = h.INSTANCE;
            Intent intent = new Intent(this$0, ActivityRegisteredProductSummary.class);
            hVar.invoke((h) intent);
            this$0.startActivityForResult(intent, -1, null);
            this$0.finish();
        }
    }

    public static /* synthetic */ void G(CardActivationActivity cardActivationActivity, String str, boolean z, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        if ((i2 & 4) != 0) {
            str2 = null;
        }
        cardActivationActivity.F(str, z, str2);
    }

    public static final void H(CardActivationActivity this$0, boolean z, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ErrorDialog errorDialog = this$0.C;
        Intrinsics.checkNotNull(errorDialog);
        errorDialog.dismiss();
        if (z) {
            i iVar = i.INSTANCE;
            Intent intent = new Intent(this$0, ActivityRegisteredProductSummary.class);
            iVar.invoke((i) intent);
            this$0.startActivityForResult(intent, -1, null);
            this$0.finish();
        }
    }

    public static final void J(CardActivationActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_CONFIG_SUCCESS.getValue(), null);
        j jVar = j.INSTANCE;
        Intent intent = new Intent(this$0, ActivityRegisteredProductSummary.class);
        jVar.invoke((j) intent);
        this$0.startActivityForResult(intent, -1, null);
        this$0.finish();
    }

    public static final void z(CardActivationActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        c cVar = c.INSTANCE;
        Intent intent = new Intent(this$0, ActivityRegisteredProductSummary.class);
        cVar.invoke((c) intent);
        this$0.startActivityForResult(intent, -1, null);
        this$0.finish();
    }

    public final void D(String str) {
        ((TextView) findViewById(R.id.toolbar_title)).setText(str);
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.b1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardActivationActivity.E(CardActivationActivity.this, view);
            }
        });
    }

    public final void F(String str, final boolean z, String str2) {
        ErrorDialog errorDialog;
        ErrorDialog errorDialog2 = this.C;
        if (errorDialog2 != null) {
            Boolean valueOf = errorDialog2 != null ? Boolean.valueOf(errorDialog2.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (errorDialog = this.C) != null) {
                errorDialog.dismiss();
            }
            this.C = null;
        }
        ErrorDialog errorDialog3 = this.C;
        if (errorDialog3 == null) {
            ErrorDialog errorDialog4 = new ErrorDialog(this);
            this.C = errorDialog4;
            Intrinsics.checkNotNull(errorDialog4);
            errorDialog4.setCancelable(false);
            ErrorDialog errorDialog5 = this.C;
            Intrinsics.checkNotNull(errorDialog5);
            TextView textView = (TextView) errorDialog5.findViewById(R.id.tvErrorMsg);
            textView.setVisibility(0);
            textView.setText(str);
            ErrorDialog errorDialog6 = this.C;
            Intrinsics.checkNotNull(errorDialog6);
            ((TextView) errorDialog6.findViewById(R.id.tvErrorTitle)).setVisibility(8);
            ErrorDialog errorDialog7 = this.C;
            Intrinsics.checkNotNull(errorDialog7);
            Button button = (Button) errorDialog7.findViewById(R.id.bt_ok);
            if (str2 != null) {
                button.setText(str2);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.c1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CardActivationActivity.H(CardActivationActivity.this, z, view);
                }
            });
            ErrorDialog errorDialog8 = this.C;
            Intrinsics.checkNotNull(errorDialog8);
            Window window = errorDialog8.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        } else {
            Intrinsics.checkNotNull(errorDialog3);
            ((TextView) errorDialog3.findViewById(R.id.tvErrorMsg)).setText(str);
        }
        ErrorDialog errorDialog9 = this.C;
        Boolean valueOf2 = errorDialog9 != null ? Boolean.valueOf(errorDialog9.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue()) {
            return;
        }
        ErrorDialog errorDialog10 = this.C;
        Intrinsics.checkNotNull(errorDialog10);
        errorDialog10.show();
    }

    public final void I() {
        ActivityActivateCardBinding activityActivateCardBinding = this.p;
        ActivityActivateCardBinding activityActivateCardBinding2 = null;
        if (activityActivateCardBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityActivateCardBinding = null;
        }
        activityActivateCardBinding.clOTPSuccess.setVisibility(0);
        ActivityActivateCardBinding activityActivateCardBinding3 = this.p;
        if (activityActivateCardBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityActivateCardBinding3 = null;
        }
        activityActivateCardBinding3.toolbar.setVisibility(8);
        ActivityActivateCardBinding activityActivateCardBinding4 = this.p;
        if (activityActivateCardBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityActivateCardBinding4 = null;
        }
        activityActivateCardBinding4.fragmentContainer.setVisibility(8);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_OTP_VERIF_SUCCESS.getValue(), null);
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_ACTIVATECARD_SUCCESS.getValue(), null);
        ActivityActivateCardBinding activityActivateCardBinding5 = this.p;
        if (activityActivateCardBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityActivateCardBinding2 = activityActivateCardBinding5;
        }
        activityActivateCardBinding2.btnBackToDashBoard.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.z0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardActivationActivity.J(CardActivationActivity.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final void activateCard() {
        showProgress();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new a(null), 2, null);
    }

    @Nullable
    public final String getCardArtUrl() {
        return this.z;
    }

    @Nullable
    public final Long getDeviceId() {
        return this.v;
    }

    @Nullable
    public final Long getEndUserGlobalId() {
        return this.r;
    }

    @Nullable
    public final Long getEndUserProductRegistrationId() {
        return this.s;
    }

    @Nullable
    public final ErrorDialog getErrorDialog() {
        return this.C;
    }

    @Nullable
    public final GetStepUpOptionsResponse getGetStepUpOptionsResponse() {
        return this.w;
    }

    @Nullable
    public final String getLast4() {
        return this.A;
    }

    @Nullable
    public final Long getPaymentInstrumentTokenId() {
        return this.u;
    }

    @Nullable
    public final StepUpOptions getSelectedOption() {
        return this.x;
    }

    @Nullable
    public final String getSerialNumber() {
        return this.t;
    }

    @Nullable
    public final String getStepUpId() {
        return this.B;
    }

    public final boolean isFromResendOtp() {
        return this.y;
    }

    public final void loadEnterOtpFragment() {
        String string = getString(R.string.card_activation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.card_activation)");
        D(string);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentEnterOtp.Companion.newInstance(this.w, this.B)).addToBackStack(null).commit();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        dismissProgress();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        Fragment findFragmentById = supportFragmentManager.findFragmentById(R.id.fragment_container);
        if (findFragmentById instanceof FragmentEnterOtp) {
            y();
        } else if (findFragmentById instanceof FragmentStepUpOptions) {
            x();
        } else {
            b bVar = b.INSTANCE;
            Intent intent = new Intent(this, ActivityRegisteredProductSummary.class);
            bVar.invoke((b) intent);
            startActivityForResult(intent, -1, null);
            finish();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityActivateCardBinding inflate = ActivityActivateCardBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        NfcStrapViewModel nfcStrapViewModel = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        this.q = (NfcStrapViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(NfcStrapViewModel.class);
        this.r = Long.valueOf(getIntent().getLongExtra(Constants.END_USER_GLOBAL_ID, 0L));
        this.s = Long.valueOf(getIntent().getLongExtra(Constants.END_USER_PRODUCT_REGISTRATION_ID, 0L));
        this.t = getIntent().getStringExtra(Constants.SERIAL_NUMBER);
        this.u = Long.valueOf(getIntent().getLongExtra(Constants.PAYMENT_INSTRUMENT_TOKEN_ID, 0L));
        this.v = Long.valueOf(getIntent().getLongExtra("deviceId", 0L));
        this.z = getIntent().getStringExtra(Constants.CARD_ART_URL);
        this.A = getIntent().getStringExtra(Constants.LAST4);
        x();
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.a1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardActivationActivity.z(CardActivationActivity.this, view);
            }
        });
        NfcStrapViewModel nfcStrapViewModel2 = this.q;
        if (nfcStrapViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel2 = null;
        }
        MutableLiveData<GetStepUpOptionsResponse> getStepUpOptionsLiveData = nfcStrapViewModel2.getGetStepUpOptionsLiveData();
        final d dVar = new d();
        getStepUpOptionsLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.e1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CardActivationActivity.A(Function1.this, obj);
            }
        });
        NfcStrapViewModel nfcStrapViewModel3 = this.q;
        if (nfcStrapViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel3 = null;
        }
        MutableLiveData<Boolean> putStepUpOptionsLiveData = nfcStrapViewModel3.getPutStepUpOptionsLiveData();
        final e eVar = new e();
        putStepUpOptionsLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.d1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CardActivationActivity.B(Function1.this, obj);
            }
        });
        NfcStrapViewModel nfcStrapViewModel4 = this.q;
        if (nfcStrapViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            nfcStrapViewModel = nfcStrapViewModel4;
        }
        MutableLiveData<ValidateOTPResponse> validateOtpLiveData = nfcStrapViewModel.getValidateOtpLiveData();
        final f fVar = new f();
        validateOtpLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.f1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CardActivationActivity.C(Function1.this, obj);
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        dismissProgress();
        super.onDestroy();
    }

    public final void putStepUpOption(boolean z) {
        showProgress();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new g(null), 2, null);
    }

    public final void setCardArtUrl(@Nullable String str) {
        this.z = str;
    }

    public final void setDeviceId(@Nullable Long l) {
        this.v = l;
    }

    public final void setEndUserGlobalId(@Nullable Long l) {
        this.r = l;
    }

    public final void setEndUserProductRegistrationId(@Nullable Long l) {
        this.s = l;
    }

    public final void setErrorDialog(@Nullable ErrorDialog errorDialog) {
        this.C = errorDialog;
    }

    public final void setFromResendOtp(boolean z) {
        this.y = z;
    }

    public final void setGetStepUpOptionsResponse(@Nullable GetStepUpOptionsResponse getStepUpOptionsResponse) {
        this.w = getStepUpOptionsResponse;
    }

    public final void setLast4(@Nullable String str) {
        this.A = str;
    }

    public final void setPaymentInstrumentTokenId(@Nullable Long l) {
        this.u = l;
    }

    public final void setSelectedOption(@Nullable StepUpOptions stepUpOptions) {
        this.x = stepUpOptions;
    }

    public final void setSerialNumber(@Nullable String str) {
        this.t = str;
    }

    public final void setStepUpId(@Nullable String str) {
        this.B = str;
    }

    public final void validateOtp(@NotNull String otp) {
        Intrinsics.checkNotNullParameter(otp, "otp");
        showProgress();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new k(otp, null), 2, null);
    }

    public final void x() {
        String string = getString(R.string.my_card);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.my_card)");
        D(string);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentActivateCard.Companion.newInstance(this.z, this.A)).addToBackStack(null).commit();
    }

    public final void y() {
        String string = getString(R.string.card_activation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.card_activation)");
        D(string);
        FragmentStepUpOptions.Companion companion = FragmentStepUpOptions.Companion;
        GetStepUpOptionsResponse getStepUpOptionsResponse = this.w;
        Intrinsics.checkNotNull(getStepUpOptionsResponse);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, companion.newInstance(getStepUpOptionsResponse)).addToBackStack(null).commit();
    }
}
