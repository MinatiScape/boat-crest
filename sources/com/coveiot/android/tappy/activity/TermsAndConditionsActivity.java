package com.coveiot.android.tappy.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.customview.CardActionConfirmationDialog;
import com.coveiot.android.tappy.customview.ErrorDialog;
import com.coveiot.android.tappy.customview.SuccessDialog;
import com.coveiot.android.tappy.fragment.FragmentContactPhoneNfcStrap;
import com.coveiot.android.tappy.fragment.FragmentTermsAndConditions;
import com.coveiot.android.tappy.model.APDULine;
import com.coveiot.android.tappy.model.APDUResponse;
import com.coveiot.android.tappy.model.AcceptTermsAndGenerateTokenResponseData;
import com.coveiot.android.tappy.model.ApduCommand;
import com.coveiot.android.tappy.model.ConfirmProvisioningResponseData;
import com.coveiot.android.tappy.model.DeletePaymentInstrumentTokenResponse;
import com.coveiot.android.tappy.model.EncryptionKey;
import com.coveiot.android.tappy.model.ErrorLogInfo;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenData;
import com.coveiot.android.tappy.model.SECardPersoScript;
import com.coveiot.android.tappy.nfc.NfcUtility;
import com.coveiot.android.tappy.utils.EncryptionUtil;
import com.coveiot.android.tappy.utils.LocalAPDUCommands;
import com.coveiot.android.tappy.viewmodel.ManageViewModel;
import com.coveiot.android.tappy.viewmodel.NfcStrapViewModel;
import com.coveiot.android.tappy.viewmodel.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class TermsAndConditionsActivity extends BaseActivity implements FragmentContactPhoneNfcStrap.OnTryAgainClickListener {
    @Nullable
    public EncryptionKey A;
    @Nullable
    public String B;
    @Nullable
    public Integer C;
    public boolean H;
    @Nullable
    public String I;
    @NotNull
    public Handler J;
    @Nullable
    public FragmentContactPhoneNfcStrap K;
    @Nullable
    public ErrorDialog L;
    @Nullable
    public SuccessDialog M;
    @Nullable
    public CardActionConfirmationDialog N;
    public NfcStrapViewModel p;
    public ManageViewModel q;
    public boolean s;
    @Nullable
    public Long t;
    @Nullable
    public Long u;
    @Nullable
    public String v;
    @Nullable
    public String w;
    @Nullable
    public String x;
    @Nullable
    public Long y;
    @Nullable
    public Long z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String r = TermsAndConditionsActivity.class.getSimpleName();
    public final long D = Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS;
    @NotNull
    public List<APDULine> E = new ArrayList();
    @NotNull
    public List<ApduCommand> F = new ArrayList();
    @NotNull
    public List<APDUResponse> G = new ArrayList();

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$acceptTermsAndConditions$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                NfcStrapViewModel nfcStrapViewModel = TermsAndConditionsActivity.this.p;
                if (nfcStrapViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    nfcStrapViewModel = null;
                }
                nfcStrapViewModel.getLastKnownLocation();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes7.dex */
    public static final class b extends Lambda implements Function1<ConfirmProvisioningResponseData, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$3$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ConfirmProvisioningResponseData $confirmProvisioningData;
            public int label;
            public final /* synthetic */ TermsAndConditionsActivity this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$3$1$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.TermsAndConditionsActivity$b$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0321a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0321a(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super C0321a> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0321a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0321a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.dismissProgress();
                        TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                        String string = termsAndConditionsActivity.getString(R.string.some_error_occurred_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                        TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$3$1$2", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.TermsAndConditionsActivity$b$a$b  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0322b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0322b(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super C0322b> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0322b(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0322b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.dismissProgress();
                        TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                        boolean z = true;
                        Toast.makeText(termsAndConditionsActivity, termsAndConditionsActivity.getString(R.string.provisioning_is_completed_successfully), 1).show();
                        FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap2 = this.this$0.getMFragmentContactPhoneNfcStrap();
                        if (mFragmentContactPhoneNfcStrap2 == null || !mFragmentContactPhoneNfcStrap2.isAdded()) {
                            z = false;
                        }
                        if (z && (mFragmentContactPhoneNfcStrap = this.this$0.getMFragmentContactPhoneNfcStrap()) != null) {
                            mFragmentContactPhoneNfcStrap.setCurrentState(5);
                        }
                        TermsAndConditionsActivity termsAndConditionsActivity2 = this.this$0;
                        String string = termsAndConditionsActivity2.getString(R.string.your_card_has_been_added_succesfully_please_active_the_card);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.your_…y_please_active_the_card)");
                        TermsAndConditionsActivity.X(termsAndConditionsActivity2, string, false, 2, null);
                        this.this$0.D();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$3$1$3", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public c(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super c> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
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
                    FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap2 = this.this$0.getMFragmentContactPhoneNfcStrap();
                        boolean z = true;
                        if (mFragmentContactPhoneNfcStrap2 == null || !mFragmentContactPhoneNfcStrap2.isAdded()) {
                            z = false;
                        }
                        if (z && (mFragmentContactPhoneNfcStrap = this.this$0.getMFragmentContactPhoneNfcStrap()) != null) {
                            mFragmentContactPhoneNfcStrap.setCurrentState(5);
                        }
                        TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                        String string = termsAndConditionsActivity.getString(R.string.your_card_has_been_added_succesfully_please_active_the_card);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.your_…y_please_active_the_card)");
                        TermsAndConditionsActivity.X(termsAndConditionsActivity, string, false, 2, null);
                        this.this$0.D();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$3$1$4", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public d(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super d> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new d(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.dismissProgress();
                        TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                        String string = termsAndConditionsActivity.getString(R.string.some_error_occurred_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                        TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$3$1$5", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public e(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super e> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new e(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.dismissProgress();
                        TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                        String string = termsAndConditionsActivity.getString(R.string.some_error_occurred_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                        TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$3$1$6", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public f(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super f> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new f(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.dismissProgress();
                        TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                        String string = termsAndConditionsActivity.getString(R.string.some_error_occurred_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                        TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$3$1$7", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public g(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super g> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new g(this.this$0, continuation);
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
                        this.this$0.dismissProgress();
                        TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                        String string = termsAndConditionsActivity.getString(R.string.some_error_occurred_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                        TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ConfirmProvisioningResponseData confirmProvisioningResponseData, TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$confirmProvisioningData = confirmProvisioningResponseData;
                this.this$0 = termsAndConditionsActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$confirmProvisioningData, this.this$0, continuation);
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
                    ConfirmProvisioningResponseData confirmProvisioningResponseData = this.$confirmProvisioningData;
                    if (confirmProvisioningResponseData == null) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new g(this.this$0, null), 2, null);
                    } else if (confirmProvisioningResponseData.getPaymentInstrumentToken() == null) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new f(this.this$0, null), 2, null);
                    } else {
                        PaymentInstrumentTokenData paymentInstrumentToken = this.$confirmProvisioningData.getPaymentInstrumentToken();
                        Intrinsics.checkNotNull(paymentInstrumentToken);
                        if (paymentInstrumentToken.getProvisioningStatus() == null) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new e(this.this$0, null), 2, null);
                        } else {
                            PaymentInstrumentTokenData paymentInstrumentToken2 = this.$confirmProvisioningData.getPaymentInstrumentToken();
                            Intrinsics.checkNotNull(paymentInstrumentToken2);
                            if (Intrinsics.areEqual(paymentInstrumentToken2.getProvisioningStatus(), "Provisioned_PersoScriptExecuted")) {
                                List<ApduCommand> apduCommands = this.$confirmProvisioningData.getApduCommands();
                                if (apduCommands == null || apduCommands.isEmpty()) {
                                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new C0321a(this.this$0, null), 2, null);
                                } else {
                                    TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                                    List<ApduCommand> apduCommands2 = this.$confirmProvisioningData.getApduCommands();
                                    Intrinsics.checkNotNull(apduCommands2);
                                    termsAndConditionsActivity.F = apduCommands2;
                                    this.this$0.M();
                                }
                            } else {
                                PaymentInstrumentTokenData paymentInstrumentToken3 = this.$confirmProvisioningData.getPaymentInstrumentToken();
                                Intrinsics.checkNotNull(paymentInstrumentToken3);
                                if (!Intrinsics.areEqual(paymentInstrumentToken3.getProvisioningStatus(), "Provisioned")) {
                                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new d(this.this$0, null), 2, null);
                                } else {
                                    PaymentInstrumentTokenData paymentInstrumentToken4 = this.$confirmProvisioningData.getPaymentInstrumentToken();
                                    Intrinsics.checkNotNull(paymentInstrumentToken4);
                                    if (Intrinsics.areEqual(paymentInstrumentToken4.getStatus(), "Active")) {
                                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new C0322b(this.this$0, null), 2, null);
                                    } else {
                                        PaymentInstrumentTokenData paymentInstrumentToken5 = this.$confirmProvisioningData.getPaymentInstrumentToken();
                                        Intrinsics.checkNotNull(paymentInstrumentToken5);
                                        if (Intrinsics.areEqual(paymentInstrumentToken5.getStatus(), "Inactive")) {
                                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new c(this.this$0, null), 2, null);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ConfirmProvisioningResponseData confirmProvisioningResponseData) {
            invoke2(confirmProvisioningResponseData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable ConfirmProvisioningResponseData confirmProvisioningResponseData) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(TermsAndConditionsActivity.this), Dispatchers.getIO(), null, new a(confirmProvisioningResponseData, TermsAndConditionsActivity.this, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class c extends Lambda implements Function1<DeletePaymentInstrumentTokenResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$4$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ TermsAndConditionsActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = termsAndConditionsActivity;
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
                    TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                    String string = termsAndConditionsActivity.getString(R.string.some_error_occurred_try_again);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                    TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DeletePaymentInstrumentTokenResponse deletePaymentInstrumentTokenResponse) {
            invoke2(deletePaymentInstrumentTokenResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable DeletePaymentInstrumentTokenResponse deletePaymentInstrumentTokenResponse) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(TermsAndConditionsActivity.this), Dispatchers.getMain(), null, new a(TermsAndConditionsActivity.this, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class d extends Lambda implements Function1<EncryptionKey, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$5$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ EncryptionKey $it;
            public int label;
            public final /* synthetic */ TermsAndConditionsActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(TermsAndConditionsActivity termsAndConditionsActivity, EncryptionKey encryptionKey, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = termsAndConditionsActivity;
                this.$it = encryptionKey;
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
                    EncryptionKey encryptionKey = this.$it;
                    if (encryptionKey != null) {
                        this.this$0.A = encryptionKey;
                        this.this$0.loadTermsAndConditionsFragment();
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(EncryptionKey encryptionKey) {
            invoke2(encryptionKey);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable EncryptionKey encryptionKey) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(TermsAndConditionsActivity.this), Dispatchers.getMain(), null, new a(TermsAndConditionsActivity.this, encryptionKey, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class e extends Lambda implements Function1<Location, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$6$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Location $it;
            public int label;
            public final /* synthetic */ TermsAndConditionsActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(TermsAndConditionsActivity termsAndConditionsActivity, Location location, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = termsAndConditionsActivity;
                this.$it = location;
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
                String encryptPanMasterCard;
                double d;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    Integer num = this.this$0.C;
                    int i = LocalAPDUCommands.PAYMENTNETWORK_VISA;
                    if (num != null && num.intValue() == i) {
                        String str = this.this$0.B;
                        Intrinsics.checkNotNull(str);
                        Long l = this.this$0.u;
                        Intrinsics.checkNotNull(l);
                        String valueOf = String.valueOf(l.longValue());
                        String str2 = this.this$0.x;
                        Intrinsics.checkNotNull(str2);
                        encryptPanMasterCard = EncryptionUtil.encryptPanVisaCard(str, valueOf, str2, new Gson().toJson(this.this$0.A));
                    } else {
                        encryptPanMasterCard = EncryptionUtil.encryptPanMasterCard(this.this$0.x, new Gson().toJson(this.this$0.A));
                    }
                    String str3 = encryptPanMasterCard;
                    NfcStrapViewModel nfcStrapViewModel = this.this$0.p;
                    if (nfcStrapViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        nfcStrapViewModel = null;
                    }
                    NfcStrapViewModel nfcStrapViewModel2 = nfcStrapViewModel;
                    Long l2 = this.this$0.t;
                    Intrinsics.checkNotNull(l2);
                    long longValue = l2.longValue();
                    Long l3 = this.this$0.z;
                    Intrinsics.checkNotNull(l3);
                    long longValue2 = l3.longValue();
                    Long l4 = this.this$0.y;
                    Intrinsics.checkNotNull(l4);
                    long longValue3 = l4.longValue();
                    String str4 = this.this$0.w;
                    Intrinsics.checkNotNull(str4);
                    Location location = this.$it;
                    double d2 = 0.0d;
                    if (location != null) {
                        location.getLatitude();
                        d = this.$it.getLatitude();
                    } else {
                        d = 0.0d;
                    }
                    Location location2 = this.$it;
                    if (location2 != null) {
                        location2.getLongitude();
                        d2 = this.$it.getLongitude();
                    }
                    nfcStrapViewModel2.acceptTermsAndGenerateToken(longValue, longValue2, longValue3, str4, d, d2, "GPS", str3);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Location location) {
            invoke2(location);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Location location) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(TermsAndConditionsActivity.this), Dispatchers.getIO(), null, new a(TermsAndConditionsActivity.this, location, null), 2, null);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onNewIntent$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Intent $intent;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onNewIntent$1$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ TermsAndConditionsActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = termsAndConditionsActivity;
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
                FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.J.removeCallbacksAndMessages(null);
                    FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap2 = this.this$0.getMFragmentContactPhoneNfcStrap();
                    boolean z = true;
                    if (mFragmentContactPhoneNfcStrap2 == null || !mFragmentContactPhoneNfcStrap2.isAdded()) {
                        z = false;
                    }
                    if (z && (mFragmentContactPhoneNfcStrap = this.this$0.getMFragmentContactPhoneNfcStrap()) != null) {
                        mFragmentContactPhoneNfcStrap.setCurrentState(2);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onNewIntent$1$2", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ TermsAndConditionsActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = termsAndConditionsActivity;
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
                    TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                    String string = termsAndConditionsActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onNewIntent$1$3", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ TermsAndConditionsActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super c> continuation) {
                super(2, continuation);
                this.this$0 = termsAndConditionsActivity;
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
                    TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                    String string = termsAndConditionsActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onNewIntent$1$4", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ TermsAndConditionsActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public d(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super d> continuation) {
                super(2, continuation);
                this.this$0 = termsAndConditionsActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new d(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                    String string = termsAndConditionsActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onNewIntent$1$5", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ TermsAndConditionsActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public e(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super e> continuation) {
                super(2, continuation);
                this.this$0 = termsAndConditionsActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new e(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                    String string = termsAndConditionsActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(Intent intent, Continuation<? super f> continuation) {
            super(2, continuation);
            this.$intent = intent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f(this.$intent, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0021 A[Catch: Exception -> 0x018c, IOException -> 0x01a8, TagLostException -> 0x01c4, TryCatch #2 {TagLostException -> 0x01c4, IOException -> 0x01a8, Exception -> 0x018c, blocks: (B:5:0x000b, B:7:0x0015, B:13:0x0021, B:17:0x003d, B:19:0x0049, B:20:0x006b, B:22:0x0071, B:24:0x00dd, B:25:0x00e7, B:27:0x00f8, B:29:0x0143, B:31:0x014b, B:32:0x0169), top: B:44:0x000b }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
            /*
                Method dump skipped, instructions count: 490
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.TermsAndConditionsActivity.f.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$sendAPDUCommands$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$sendAPDUCommands$1$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ TermsAndConditionsActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = termsAndConditionsActivity;
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
                    if (!this.this$0.isFinishing()) {
                        TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                        String string = termsAndConditionsActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                        TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

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

        /* JADX WARN: Removed duplicated region for block: B:13:0x001f A[Catch: Exception -> 0x00a3, TryCatch #0 {Exception -> 0x00a3, blocks: (B:5:0x000b, B:7:0x0013, B:13:0x001f, B:14:0x0029, B:16:0x002f, B:18:0x005e, B:20:0x0066, B:21:0x006c, B:22:0x0099), top: B:30:0x000b }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
            /*
                r8 = this;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r0 = r8.label
                if (r0 != 0) goto Lc1
                kotlin.ResultKt.throwOnFailure(r9)
                r9 = 0
                com.coveiot.android.tappy.activity.TermsAndConditionsActivity r0 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.this     // Catch: java.lang.Exception -> La3
                java.util.List r0 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.access$getApduCommands$p(r0)     // Catch: java.lang.Exception -> La3
                if (r0 == 0) goto L1c
                boolean r0 = r0.isEmpty()     // Catch: java.lang.Exception -> La3
                if (r0 == 0) goto L1a
                goto L1c
            L1a:
                r0 = 0
                goto L1d
            L1c:
                r0 = 1
            L1d:
                if (r0 != 0) goto Lbe
                com.coveiot.android.tappy.activity.TermsAndConditionsActivity r0 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.this     // Catch: java.lang.Exception -> La3
                java.util.List r0 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.access$getApduCommands$p(r0)     // Catch: java.lang.Exception -> La3
                java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> La3
            L29:
                boolean r1 = r0.hasNext()     // Catch: java.lang.Exception -> La3
                if (r1 == 0) goto L99
                java.lang.Object r1 = r0.next()     // Catch: java.lang.Exception -> La3
                com.coveiot.android.tappy.model.ApduCommand r1 = (com.coveiot.android.tappy.model.ApduCommand) r1     // Catch: java.lang.Exception -> La3
                com.coveiot.android.tappy.activity.TermsAndConditionsActivity r2 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.this     // Catch: java.lang.Exception -> La3
                java.lang.String r1 = r1.getApdu()     // Catch: java.lang.Exception -> La3
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> La3
                java.lang.String r1 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.access$sendApdu(r2, r1)     // Catch: java.lang.Exception -> La3
                com.coveiot.android.tappy.nfc.NfcUtility$Companion r2 = com.coveiot.android.tappy.nfc.NfcUtility.Companion     // Catch: java.lang.Exception -> La3
                byte[] r3 = r2.getSUCCESS_RESPONSE()     // Catch: java.lang.Exception -> La3
                java.lang.String r3 = com.coveiot.android.tappy.utils.HexUtils.bin2hex(r3)     // Catch: java.lang.Exception -> La3
                byte[] r4 = com.coveiot.android.tappy.utils.HexUtils.hex2bin(r1)     // Catch: java.lang.Exception -> La3
                byte[] r4 = r2.getResponseCode(r4)     // Catch: java.lang.Exception -> La3
                java.lang.String r4 = com.coveiot.android.tappy.utils.HexUtils.bin2hex(r4)     // Catch: java.lang.Exception -> La3
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)     // Catch: java.lang.Exception -> La3
                if (r3 != 0) goto L29
                com.coveiot.android.tappy.activity.TermsAndConditionsActivity r0 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.this     // Catch: java.lang.Exception -> La3
                com.coveiot.android.tappy.viewmodel.ManageViewModel r0 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.access$getManageViewModel$p(r0)     // Catch: java.lang.Exception -> La3
                if (r0 != 0) goto L6c
                java.lang.String r0 = "manageViewModel"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)     // Catch: java.lang.Exception -> La3
                r0 = r9
            L6c:
                com.coveiot.android.tappy.activity.TermsAndConditionsActivity r3 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.this     // Catch: java.lang.Exception -> La3
                java.lang.Long r3 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.access$getEndUserGlobalId$p(r3)     // Catch: java.lang.Exception -> La3
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch: java.lang.Exception -> La3
                long r3 = r3.longValue()     // Catch: java.lang.Exception -> La3
                com.coveiot.android.tappy.activity.TermsAndConditionsActivity r5 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.this     // Catch: java.lang.Exception -> La3
                java.lang.Long r5 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.access$getPaymentInstrumentTokeId$p(r5)     // Catch: java.lang.Exception -> La3
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch: java.lang.Exception -> La3
                long r5 = r5.longValue()     // Catch: java.lang.Exception -> La3
                byte[] r1 = com.coveiot.android.tappy.utils.HexUtils.hex2bin(r1)     // Catch: java.lang.Exception -> La3
                byte[] r1 = r2.getResponseCode(r1)     // Catch: java.lang.Exception -> La3
                java.lang.String r7 = com.coveiot.android.tappy.utils.HexUtils.bin2hex(r1)     // Catch: java.lang.Exception -> La3
                r1 = r0
                r2 = r3
                r4 = r5
                r6 = r7
                r1.deletePaymentInstrumentTokens(r2, r4, r6)     // Catch: java.lang.Exception -> La3
            L99:
                com.coveiot.android.tappy.activity.TermsAndConditionsActivity r0 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.this     // Catch: java.lang.Exception -> La3
                java.util.List r0 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.access$getApduCommands$p(r0)     // Catch: java.lang.Exception -> La3
                r0.clear()     // Catch: java.lang.Exception -> La3
                goto Lbe
            La3:
                r0 = move-exception
                r0.printStackTrace()
                com.coveiot.android.tappy.activity.TermsAndConditionsActivity r0 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.this
                androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r0)
                kotlinx.coroutines.MainCoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getMain()
                r3 = 0
                com.coveiot.android.tappy.activity.TermsAndConditionsActivity$g$a r4 = new com.coveiot.android.tappy.activity.TermsAndConditionsActivity$g$a
                com.coveiot.android.tappy.activity.TermsAndConditionsActivity r0 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.this
                r4.<init>(r0, r9)
                r5 = 2
                r6 = 0
                kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
            Lbe:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            Lc1:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.TermsAndConditionsActivity.g.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$sendApdu$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public h(Continuation<? super h> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new h(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((h) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!TermsAndConditionsActivity.this.isFinishing()) {
                    TermsAndConditionsActivity.this.dismissProgress();
                    TermsAndConditionsActivity termsAndConditionsActivity = TermsAndConditionsActivity.this;
                    String string = termsAndConditionsActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$sendApdu$2", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class i extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public i(Continuation<? super i> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new i(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((i) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!TermsAndConditionsActivity.this.isFinishing()) {
                    TermsAndConditionsActivity termsAndConditionsActivity = TermsAndConditionsActivity.this;
                    String string = termsAndConditionsActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$sendApdu$3", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class j extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public j(Continuation<? super j> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new j(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((j) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!TermsAndConditionsActivity.this.isFinishing()) {
                    TermsAndConditionsActivity termsAndConditionsActivity = TermsAndConditionsActivity.this;
                    String string = termsAndConditionsActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes7.dex */
    public static final class k extends Lambda implements Function1<Intent, Unit> {
        public static final k INSTANCE = new k();

        public k() {
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
    public static final class l extends Lambda implements Function1<Intent, Unit> {
        public static final l INSTANCE = new l();

        public l() {
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
    public static final class m extends Lambda implements Function1<Intent, Unit> {
        public static final m INSTANCE = new m();

        public m() {
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

    public TermsAndConditionsActivity() {
        new ArrayList();
        this.H = true;
        this.J = new Handler(Looper.getMainLooper());
    }

    public static final void E(TermsAndConditionsActivity this$0) {
        FragmentContactPhoneNfcStrap fragmentContactPhoneNfcStrap;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentContactPhoneNfcStrap fragmentContactPhoneNfcStrap2 = this$0.K;
        boolean z = false;
        if (fragmentContactPhoneNfcStrap2 != null && fragmentContactPhoneNfcStrap2.isAdded()) {
            z = true;
        }
        if (!z || (fragmentContactPhoneNfcStrap = this$0.K) == null) {
            return;
        }
        fragmentContactPhoneNfcStrap.setCurrentState(1);
    }

    public static final void F(TermsAndConditionsActivity this$0) {
        FragmentContactPhoneNfcStrap fragmentContactPhoneNfcStrap;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentContactPhoneNfcStrap fragmentContactPhoneNfcStrap2 = this$0.K;
        boolean z = true;
        if (fragmentContactPhoneNfcStrap2 == null || !fragmentContactPhoneNfcStrap2.isAdded()) {
            z = false;
        }
        if (!z || (fragmentContactPhoneNfcStrap = this$0.K) == null) {
            return;
        }
        fragmentContactPhoneNfcStrap.setCurrentState(3);
    }

    public static final void G(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void H(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void I(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void J(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void K(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void L(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void P(TermsAndConditionsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.D();
        this$0.Q();
    }

    public static final void R(TermsAndConditionsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setResult(-1);
        k kVar = k.INSTANCE;
        Intent intent = new Intent(this$0, ActivityRegisteredProductSummary.class);
        kVar.invoke((k) intent);
        this$0.startActivityForResult(intent, -1, null);
        this$0.finish();
    }

    public static final void S(TermsAndConditionsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CardActionConfirmationDialog cardActionConfirmationDialog = this$0.N;
        Intrinsics.checkNotNull(cardActionConfirmationDialog);
        cardActionConfirmationDialog.dismiss();
        this$0.N = null;
    }

    public static /* synthetic */ void U(TermsAndConditionsActivity termsAndConditionsActivity, String str, boolean z, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        if ((i2 & 4) != 0) {
            str2 = null;
        }
        termsAndConditionsActivity.T(str, z, str2);
    }

    public static final void V(TermsAndConditionsActivity this$0, boolean z, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ErrorDialog errorDialog = this$0.L;
        Intrinsics.checkNotNull(errorDialog);
        errorDialog.dismiss();
        this$0.D();
        if (z) {
            l lVar = l.INSTANCE;
            Intent intent = new Intent(this$0, ActivityRegisteredProductSummary.class);
            lVar.invoke((l) intent);
            this$0.startActivityForResult(intent, -1, null);
            this$0.finish();
        }
    }

    public static /* synthetic */ void X(TermsAndConditionsActivity termsAndConditionsActivity, String str, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        termsAndConditionsActivity.W(str, z);
    }

    public static final void Y(TermsAndConditionsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SuccessDialog successDialog = this$0.M;
        Intrinsics.checkNotNull(successDialog);
        successDialog.dismiss();
        m mVar = m.INSTANCE;
        Intent intent = new Intent(this$0, ActivityRegisteredProductSummary.class);
        mVar.invoke((m) intent);
        this$0.startActivityForResult(intent, -1, null);
        this$0.finish();
    }

    public final void D() {
        try {
            if (this.s) {
                this.s = false;
                boolean stopListeningNFC = NfcUtility.Companion.stopListeningNFC();
                String str = this.r;
                LogHelper.d(str, "NFC STOPPED LISTENING " + stopListeningNFC);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void M() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new g(null), 2, null);
    }

    public final String N(String str) {
        String str2 = "";
        if (str != null) {
            try {
                String str3 = this.r;
                LogHelper.d(str3, "\n\nSEND: " + str);
                str2 = String.valueOf(NfcUtility.Companion.transmit(str));
                String str4 = this.r;
                LogHelper.d(str4, "RECEIVED: " + str2);
                return str2;
            } catch (TagLostException e2) {
                e2.printStackTrace();
                D();
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new h(null), 2, null);
                return str2;
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
                D();
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new i(null), 2, null);
                return str2;
            } catch (Exception e4) {
                e4.printStackTrace();
                D();
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new j(null), 2, null);
                return str2;
            }
        }
        return "";
    }

    public final void O() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.terms_and_conditions_tappy));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.l1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TermsAndConditionsActivity.P(TermsAndConditionsActivity.this, view);
            }
        });
    }

    public final void Q() {
        if (this.N == null) {
            CardActionConfirmationDialog cardActionConfirmationDialog = new CardActionConfirmationDialog(this);
            this.N = cardActionConfirmationDialog;
            Intrinsics.checkNotNull(cardActionConfirmationDialog);
            cardActionConfirmationDialog.setCancelable(false);
            CardActionConfirmationDialog cardActionConfirmationDialog2 = this.N;
            Intrinsics.checkNotNull(cardActionConfirmationDialog2);
            CardActionConfirmationDialog cardActionConfirmationDialog3 = this.N;
            Intrinsics.checkNotNull(cardActionConfirmationDialog3);
            CardActionConfirmationDialog cardActionConfirmationDialog4 = this.N;
            Intrinsics.checkNotNull(cardActionConfirmationDialog4);
            ((TextView) cardActionConfirmationDialog2.findViewById(R.id.tv_DeregisterStrap)).setVisibility(8);
            ((TextView) cardActionConfirmationDialog4.findViewById(R.id.tvAreYouSure)).setVisibility(8);
            ((TextView) cardActionConfirmationDialog3.findViewById(R.id.tvByProceeding)).setText(getString(R.string.back_confirm_msg));
        }
        CardActionConfirmationDialog cardActionConfirmationDialog5 = this.N;
        Intrinsics.checkNotNull(cardActionConfirmationDialog5);
        CardActionConfirmationDialog cardActionConfirmationDialog6 = this.N;
        Intrinsics.checkNotNull(cardActionConfirmationDialog6);
        ((TextView) cardActionConfirmationDialog5.findViewById(R.id.btn_yes)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.m1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TermsAndConditionsActivity.R(TermsAndConditionsActivity.this, view);
            }
        });
        ((TextView) cardActionConfirmationDialog6.findViewById(R.id.btn_No)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.g1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TermsAndConditionsActivity.S(TermsAndConditionsActivity.this, view);
            }
        });
        CardActionConfirmationDialog cardActionConfirmationDialog7 = this.N;
        Intrinsics.checkNotNull(cardActionConfirmationDialog7);
        Window window = cardActionConfirmationDialog7.getWindow();
        Intrinsics.checkNotNull(window);
        window.setBackgroundDrawable(new ColorDrawable(0));
        CardActionConfirmationDialog cardActionConfirmationDialog8 = this.N;
        Boolean valueOf = cardActionConfirmationDialog8 != null ? Boolean.valueOf(cardActionConfirmationDialog8.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        CardActionConfirmationDialog cardActionConfirmationDialog9 = this.N;
        Intrinsics.checkNotNull(cardActionConfirmationDialog9);
        cardActionConfirmationDialog9.show();
        CardActionConfirmationDialog cardActionConfirmationDialog10 = this.N;
        Intrinsics.checkNotNull(cardActionConfirmationDialog10);
        Window window2 = cardActionConfirmationDialog10.getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -2);
        }
        if (window2 != null) {
            window2.setGravity(17);
        }
    }

    public final void T(String str, final boolean z, String str2) {
        ErrorDialog errorDialog;
        ErrorDialog errorDialog2 = this.L;
        if (errorDialog2 != null) {
            Boolean valueOf = errorDialog2 != null ? Boolean.valueOf(errorDialog2.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (errorDialog = this.L) != null) {
                errorDialog.dismiss();
            }
            this.L = null;
        }
        ErrorDialog errorDialog3 = this.L;
        if (errorDialog3 == null) {
            ErrorDialog errorDialog4 = new ErrorDialog(this);
            this.L = errorDialog4;
            Intrinsics.checkNotNull(errorDialog4);
            errorDialog4.setCancelable(false);
            ErrorDialog errorDialog5 = this.L;
            Intrinsics.checkNotNull(errorDialog5);
            ErrorDialog errorDialog6 = this.L;
            Intrinsics.checkNotNull(errorDialog6);
            ((TextView) errorDialog6.findViewById(R.id.tvErrorMsg)).setVisibility(8);
            ((TextView) errorDialog5.findViewById(R.id.tvErrorTitle)).setText(str);
            ErrorDialog errorDialog7 = this.L;
            Intrinsics.checkNotNull(errorDialog7);
            ((Button) errorDialog7.findViewById(R.id.bt_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.n1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TermsAndConditionsActivity.V(TermsAndConditionsActivity.this, z, view);
                }
            });
            ErrorDialog errorDialog8 = this.L;
            Intrinsics.checkNotNull(errorDialog8);
            Window window = errorDialog8.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        } else {
            Intrinsics.checkNotNull(errorDialog3);
            ((TextView) errorDialog3.findViewById(R.id.tvErrorTitle)).setText(str);
        }
        ErrorDialog errorDialog9 = this.L;
        Boolean valueOf2 = errorDialog9 != null ? Boolean.valueOf(errorDialog9.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue()) {
            return;
        }
        ErrorDialog errorDialog10 = this.L;
        Intrinsics.checkNotNull(errorDialog10);
        errorDialog10.show();
    }

    public final void W(String str, boolean z) {
        SuccessDialog successDialog = this.M;
        if (successDialog == null) {
            SuccessDialog successDialog2 = new SuccessDialog(this);
            this.M = successDialog2;
            Intrinsics.checkNotNull(successDialog2);
            successDialog2.setCancelable(false);
            SuccessDialog successDialog3 = this.M;
            Intrinsics.checkNotNull(successDialog3);
            ((TextView) successDialog3.findViewById(R.id.tvSuccessDesc)).setText(str);
            SuccessDialog successDialog4 = this.M;
            Intrinsics.checkNotNull(successDialog4);
            ((Button) successDialog4.findViewById(R.id.bt_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.k1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TermsAndConditionsActivity.Y(TermsAndConditionsActivity.this, view);
                }
            });
            SuccessDialog successDialog5 = this.M;
            Intrinsics.checkNotNull(successDialog5);
            Window window = successDialog5.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        } else {
            Intrinsics.checkNotNull(successDialog);
            ((TextView) successDialog.findViewById(R.id.tvErrorMsg)).setText(str);
        }
        SuccessDialog successDialog6 = this.M;
        Boolean valueOf = successDialog6 != null ? Boolean.valueOf(successDialog6.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        SuccessDialog successDialog7 = this.M;
        Intrinsics.checkNotNull(successDialog7);
        successDialog7.show();
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

    @RequiresApi(26)
    public final void acceptTermsAndConditions() {
        showProgress();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new a(null), 2, null);
    }

    @Nullable
    public final CardActionConfirmationDialog getCardActionConfirmationDialog() {
        return this.N;
    }

    @Nullable
    public final ErrorDialog getErrorDialog() {
        return this.L;
    }

    @Nullable
    public final FragmentContactPhoneNfcStrap getMFragmentContactPhoneNfcStrap() {
        return this.K;
    }

    @Nullable
    public final SuccessDialog getSuccessDialog() {
        return this.M;
    }

    public final String getTAG() {
        return this.r;
    }

    public final void loadContactPhoneNfcStrapFragment() {
        this.K = FragmentContactPhoneNfcStrap.Companion.newInstance(0, this);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i2 = R.id.fragment_container;
        FragmentContactPhoneNfcStrap fragmentContactPhoneNfcStrap = this.K;
        Intrinsics.checkNotNull(fragmentContactPhoneNfcStrap);
        beginTransaction.replace(i2, fragmentContactPhoneNfcStrap).commitAllowingStateLoss();
        this.J.postDelayed(new Runnable() { // from class: com.coveiot.android.tappy.activity.i1
            @Override // java.lang.Runnable
            public final void run() {
                TermsAndConditionsActivity.E(TermsAndConditionsActivity.this);
            }
        }, 5000L);
        this.J.postDelayed(new Runnable() { // from class: com.coveiot.android.tappy.activity.j1
            @Override // java.lang.Runnable
            public final void run() {
                TermsAndConditionsActivity.F(TermsAndConditionsActivity.this);
            }
        }, 10000L);
    }

    public final void loadTermsAndConditionsFragment() {
        FragmentTermsAndConditions.Companion companion = FragmentTermsAndConditions.Companion;
        String str = this.v;
        Intrinsics.checkNotNull(str);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, companion.newInstance(str, "")).commitAllowingStateLoss();
    }

    public final void logError(@NotNull String module, @NotNull String details, @NotNull String severity) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(details, "details");
        Intrinsics.checkNotNullParameter(severity, "severity");
        ErrorLogInfo errorLogInfo = new ErrorLogInfo();
        errorLogInfo.setErrorGUID(UUID.randomUUID().toString());
        errorLogInfo.setDateUTC(AppUtils.formatDateUTC(new Date(), com.coveiot.android.tappy.utils.Constants.DATE_FORMAT));
        errorLogInfo.setLogSeverity(severity);
        errorLogInfo.setDetails(details);
        errorLogInfo.setModule(module);
        NfcStrapViewModel nfcStrapViewModel = this.p;
        if (nfcStrapViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel = null;
        }
        nfcStrapViewModel.logError(errorLogInfo);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Q();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @RequiresApi(26)
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_tappy_terms_and_condtions);
        O();
        this.p = (NfcStrapViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(NfcStrapViewModel.class);
        this.q = (ManageViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(ManageViewModel.class);
        this.t = Long.valueOf(getIntent().getLongExtra(com.coveiot.android.tappy.utils.Constants.END_USER_GLOBAL_ID, 0L));
        this.u = Long.valueOf(getIntent().getLongExtra(com.coveiot.android.tappy.utils.Constants.END_USER_PRODUCT_REGISTRATION_ID, 0L));
        this.v = getIntent().getStringExtra(com.coveiot.android.tappy.utils.Constants.TERMS_AND_CONDITIONS_TEXT);
        this.w = getIntent().getStringExtra(com.coveiot.android.tappy.utils.Constants.TERMS_AND_CONDITIONS_ID);
        this.y = Long.valueOf(getIntent().getLongExtra("deviceId", 0L));
        this.z = Long.valueOf(getIntent().getLongExtra(com.coveiot.android.tappy.utils.Constants.PAYMENT_INSTRUMENT_TOKEN_ID, 0L));
        this.B = getIntent().getStringExtra(com.coveiot.android.tappy.utils.Constants.SERIAL_NUMBER);
        this.C = Integer.valueOf(getIntent().getIntExtra(com.coveiot.android.tappy.utils.Constants.PAYMENT_NETWORK_ID, 0));
        this.x = getIntent().getStringExtra(com.coveiot.android.tappy.utils.Constants.CVV);
        getIntent().getStringExtra(com.coveiot.android.tappy.utils.Constants.CARD_ART_URL);
        getIntent().getStringExtra(com.coveiot.android.tappy.utils.Constants.LAST4);
        NfcStrapViewModel nfcStrapViewModel = this.p;
        NfcStrapViewModel nfcStrapViewModel2 = null;
        if (nfcStrapViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel = null;
        }
        MutableLiveData<AcceptTermsAndGenerateTokenResponseData> acceptTermsAndGenerateTokenResponseLiveData = nfcStrapViewModel.getAcceptTermsAndGenerateTokenResponseLiveData();
        final Function1<AcceptTermsAndGenerateTokenResponseData, Unit> function1 = new Function1<AcceptTermsAndGenerateTokenResponseData, Unit>() { // from class: com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$1

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$1$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
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
                        TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                        String string = termsAndConditionsActivity.getString(R.string.some_error_occurred_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                        TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$1$2$2", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
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

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$1$2$3", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public c(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super c> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
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
                        this.this$0.loadContactPhoneNfcStrapFragment();
                        this.this$0.s = true;
                        NfcUtility.Companion.startListeningNFC(this.this$0);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$1$2$4", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public d(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super d> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new d(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.dismissProgress();
                        TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                        String string = termsAndConditionsActivity.getString(R.string.some_error_occurred_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                        TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$1$3$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ TermsAndConditionsActivity $this_run;
                public int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public e(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super e> continuation) {
                    super(2, continuation);
                    this.$this_run = termsAndConditionsActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new e(this.$this_run, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.$this_run.dismissProgress();
                        TermsAndConditionsActivity termsAndConditionsActivity = this.$this_run;
                        String string = termsAndConditionsActivity.getString(R.string.some_error_occurred_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                        TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AcceptTermsAndGenerateTokenResponseData acceptTermsAndGenerateTokenResponseData) {
                invoke2(acceptTermsAndGenerateTokenResponseData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable AcceptTermsAndGenerateTokenResponseData acceptTermsAndGenerateTokenResponseData) {
                Job e2;
                if (acceptTermsAndGenerateTokenResponseData == null) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(TermsAndConditionsActivity.this), Dispatchers.getMain(), null, new a(TermsAndConditionsActivity.this, null), 2, null);
                    return;
                }
                SECardPersoScript seCardPersoScript = acceptTermsAndGenerateTokenResponseData.getSeCardPersoScript();
                if (seCardPersoScript != null) {
                    TermsAndConditionsActivity termsAndConditionsActivity = TermsAndConditionsActivity.this;
                    if (Intrinsics.areEqual(seCardPersoScript.isTokenPersoScriptPending(), Boolean.TRUE)) {
                        e2 = kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(termsAndConditionsActivity), Dispatchers.getMain(), null, new TermsAndConditionsActivity$onCreate$1$2$1(termsAndConditionsActivity, null), 2, null);
                    } else {
                        ArrayList<APDULine> aPDULines = seCardPersoScript.getAPDULines();
                        if (!(aPDULines == null || aPDULines.isEmpty())) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(termsAndConditionsActivity), Dispatchers.getMain(), null, new b(termsAndConditionsActivity, null), 2, null);
                            ArrayList<APDULine> aPDULines2 = seCardPersoScript.getAPDULines();
                            Intrinsics.checkNotNull(aPDULines2);
                            Iterator<APDULine> it = aPDULines2.iterator();
                            while (it.hasNext()) {
                                APDULine next = it.next();
                                APDULine aPDULine = new APDULine();
                                aPDULine.setAPDU(next.getAPDU());
                                aPDULine.setAPDUID(next.getAPDUID());
                                termsAndConditionsActivity.E.add(aPDULine);
                            }
                            e2 = kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(termsAndConditionsActivity), Dispatchers.getMain(), null, new c(termsAndConditionsActivity, null), 2, null);
                        } else {
                            e2 = kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(termsAndConditionsActivity), Dispatchers.getMain(), null, new d(termsAndConditionsActivity, null), 2, null);
                        }
                    }
                    if (e2 != null) {
                        return;
                    }
                }
                TermsAndConditionsActivity termsAndConditionsActivity2 = TermsAndConditionsActivity.this;
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(termsAndConditionsActivity2), Dispatchers.getMain(), null, new e(termsAndConditionsActivity2, null), 2, null);
            }
        };
        acceptTermsAndGenerateTokenResponseLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.r1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TermsAndConditionsActivity.G(Function1.this, obj);
            }
        });
        NfcStrapViewModel nfcStrapViewModel3 = this.p;
        if (nfcStrapViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel3 = null;
        }
        MutableLiveData<SECardPersoScript> tokenPersoScriptLiveData = nfcStrapViewModel3.getTokenPersoScriptLiveData();
        final Function1<SECardPersoScript, Unit> function12 = new Function1<SECardPersoScript, Unit>() { // from class: com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$1  reason: invalid class name */
            /* loaded from: classes7.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ TermsAndConditionsActivity this$0;

                @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$1$1$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$1$a */
                /* loaded from: classes7.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ TermsAndConditionsActivity this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = termsAndConditionsActivity;
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
                            NfcStrapViewModel nfcStrapViewModel = this.this$0.p;
                            if (nfcStrapViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                nfcStrapViewModel = null;
                            }
                            Long l = this.this$0.t;
                            Intrinsics.checkNotNull(l);
                            long longValue = l.longValue();
                            Long l2 = this.this$0.z;
                            Intrinsics.checkNotNull(l2);
                            nfcStrapViewModel.getTokenPersoScript(longValue, l2.longValue());
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = termsAndConditionsActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    long j;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        Handler handler = new Handler(Looper.getMainLooper());
                        final TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                        Runnable runnable = 
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: CONSTRUCTOR  (r1v0 'runnable' java.lang.Runnable) = (r0v3 'termsAndConditionsActivity' com.coveiot.android.tappy.activity.TermsAndConditionsActivity A[DONT_INLINE]) call: com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$1$invokeSuspend$$inlined$Runnable$1.<init>(com.coveiot.android.tappy.activity.TermsAndConditionsActivity):void type: CONSTRUCTOR in method: com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2.1.invokeSuspend(java.lang.Object):java.lang.Object, file: classes7.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$1$invokeSuspend$$inlined$Runnable$1, state: NOT_LOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:302)
                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:769)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                            	... 23 more
                            */
                        /*
                            this = this;
                            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                            int r0 = r4.label
                            if (r0 != 0) goto L26
                            kotlin.ResultKt.throwOnFailure(r5)
                            android.os.Handler r5 = new android.os.Handler
                            android.os.Looper r0 = android.os.Looper.getMainLooper()
                            r5.<init>(r0)
                            com.coveiot.android.tappy.activity.TermsAndConditionsActivity r0 = r4.this$0
                            com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$1$invokeSuspend$$inlined$Runnable$1 r1 = new com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$1$invokeSuspend$$inlined$Runnable$1
                            r1.<init>(r0)
                            com.coveiot.android.tappy.activity.TermsAndConditionsActivity r0 = r4.this$0
                            long r2 = com.coveiot.android.tappy.activity.TermsAndConditionsActivity.access$getINTERVAL_TO_CHECK_PERSO_SCRIPT_GENERATION$p(r0)
                            r5.postDelayed(r1, r2)
                            kotlin.Unit r5 = kotlin.Unit.INSTANCE
                            return r5
                        L26:
                            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r5.<init>(r0)
                            throw r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$2", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes7.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ TermsAndConditionsActivity this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = termsAndConditionsActivity;
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
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$3", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ TermsAndConditionsActivity this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = termsAndConditionsActivity;
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
                            this.this$0.loadContactPhoneNfcStrapFragment();
                            this.this$0.s = true;
                            NfcUtility.Companion.startListeningNFC(this.this$0);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$4", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes7.dex */
                public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ TermsAndConditionsActivity this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public c(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super c> continuation) {
                        super(2, continuation);
                        this.this$0 = termsAndConditionsActivity;
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
                            TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                            String string = termsAndConditionsActivity.getString(R.string.some_error_occurred_try_again);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                            TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$2$5", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes7.dex */
                public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ TermsAndConditionsActivity this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public d(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super d> continuation) {
                        super(2, continuation);
                        this.this$0 = termsAndConditionsActivity;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new d(this.this$0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.this$0.dismissProgress();
                            TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
                            String string = termsAndConditionsActivity.getString(R.string.some_error_occurred_try_again);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                            TermsAndConditionsActivity.U(termsAndConditionsActivity, string, false, null, 6, null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SECardPersoScript sECardPersoScript) {
                    invoke2(sECardPersoScript);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(@Nullable SECardPersoScript sECardPersoScript) {
                    if (sECardPersoScript == null) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(TermsAndConditionsActivity.this), Dispatchers.getMain(), null, new d(TermsAndConditionsActivity.this, null), 2, null);
                    } else if (Intrinsics.areEqual(sECardPersoScript.isTokenPersoScriptPending(), Boolean.TRUE)) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(TermsAndConditionsActivity.this), Dispatchers.getMain(), null, new AnonymousClass1(TermsAndConditionsActivity.this, null), 2, null);
                    } else {
                        ArrayList<APDULine> aPDULines = sECardPersoScript.getAPDULines();
                        if (!(aPDULines == null || aPDULines.isEmpty())) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(TermsAndConditionsActivity.this), Dispatchers.getMain(), null, new a(TermsAndConditionsActivity.this, null), 2, null);
                            ArrayList<APDULine> aPDULines2 = sECardPersoScript.getAPDULines();
                            Intrinsics.checkNotNull(aPDULines2);
                            Iterator<APDULine> it = aPDULines2.iterator();
                            while (it.hasNext()) {
                                APDULine next = it.next();
                                APDULine aPDULine = new APDULine();
                                aPDULine.setAPDU(next.getAPDU());
                                aPDULine.setAPDUID(next.getAPDUID());
                                TermsAndConditionsActivity.this.E.add(aPDULine);
                            }
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(TermsAndConditionsActivity.this), Dispatchers.getMain(), null, new b(TermsAndConditionsActivity.this, null), 2, null);
                            return;
                        }
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(TermsAndConditionsActivity.this), Dispatchers.getMain(), null, new c(TermsAndConditionsActivity.this, null), 2, null);
                    }
                }
            };
            tokenPersoScriptLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.h1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    TermsAndConditionsActivity.H(Function1.this, obj);
                }
            });
            NfcStrapViewModel nfcStrapViewModel4 = this.p;
            if (nfcStrapViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                nfcStrapViewModel4 = null;
            }
            MutableLiveData<ConfirmProvisioningResponseData> confirmProvisioningLiveData = nfcStrapViewModel4.getConfirmProvisioningLiveData();
            final b bVar = new b();
            confirmProvisioningLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.q1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    TermsAndConditionsActivity.I(Function1.this, obj);
                }
            });
            ManageViewModel manageViewModel = this.q;
            if (manageViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                manageViewModel = null;
            }
            MutableLiveData<DeletePaymentInstrumentTokenResponse> deletePaymentInstrumentTokenLiveData = manageViewModel.getDeletePaymentInstrumentTokenLiveData();
            final c cVar = new c();
            deletePaymentInstrumentTokenLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.o1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    TermsAndConditionsActivity.J(Function1.this, obj);
                }
            });
            showProgress();
            NfcStrapViewModel nfcStrapViewModel5 = this.p;
            if (nfcStrapViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                nfcStrapViewModel5 = null;
            }
            Integer num = this.C;
            Intrinsics.checkNotNull(num);
            nfcStrapViewModel5.getEncryptionKey(num.intValue());
            NfcStrapViewModel nfcStrapViewModel6 = this.p;
            if (nfcStrapViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                nfcStrapViewModel6 = null;
            }
            MutableLiveData<EncryptionKey> encryptionKeyLiveData = nfcStrapViewModel6.getEncryptionKeyLiveData();
            final d dVar = new d();
            encryptionKeyLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.p1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    TermsAndConditionsActivity.K(Function1.this, obj);
                }
            });
            NfcStrapViewModel nfcStrapViewModel7 = this.p;
            if (nfcStrapViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                nfcStrapViewModel2 = nfcStrapViewModel7;
            }
            MutableLiveData<Location> lastKnownLocationLiveData = nfcStrapViewModel2.getLastKnownLocationLiveData();
            final e eVar = new e();
            lastKnownLocationLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.s1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    TermsAndConditionsActivity.L(Function1.this, obj);
                }
            });
        }

        @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
        public void onDestroy() {
            super.onDestroy();
            D();
        }

        @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
        public void onNewIntent(@NotNull Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            super.onNewIntent(intent);
            if (Intrinsics.areEqual("android.nfc.action.TECH_DISCOVERED", intent.getAction())) {
                kotlinx.coroutines.e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new f(intent, null), 3, null);
            }
        }

        @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
        public void onResume() {
            super.onResume();
        }

        @Override // com.coveiot.android.tappy.fragment.FragmentContactPhoneNfcStrap.OnTryAgainClickListener
        public void onTryAgainClicked() {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentContactPhoneNfcStrap.Companion.newInstance(2, this)).commitAllowingStateLoss();
        }

        public final void rejectTermsAndConditions() {
            D();
            finish();
        }

        public final void setCardActionConfirmationDialog(@Nullable CardActionConfirmationDialog cardActionConfirmationDialog) {
            this.N = cardActionConfirmationDialog;
        }

        public final void setErrorDialog(@Nullable ErrorDialog errorDialog) {
            this.L = errorDialog;
        }

        public final void setMFragmentContactPhoneNfcStrap(@Nullable FragmentContactPhoneNfcStrap fragmentContactPhoneNfcStrap) {
            this.K = fragmentContactPhoneNfcStrap;
        }

        public final void setSuccessDialog(@Nullable SuccessDialog successDialog) {
            this.M = successDialog;
        }
    }
