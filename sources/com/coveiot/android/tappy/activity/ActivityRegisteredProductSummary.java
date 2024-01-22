package com.coveiot.android.tappy.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.adapter.PagerAdapterRegisteredProductSummary;
import com.coveiot.android.tappy.adapter.TransactionHistoryAdapter;
import com.coveiot.android.tappy.customview.CVVInputDialog;
import com.coveiot.android.tappy.customview.CardActionConfirmationDialog;
import com.coveiot.android.tappy.customview.ErrorDialog;
import com.coveiot.android.tappy.customview.ReasonInputDialog;
import com.coveiot.android.tappy.customview.TransactionDetailDialog;
import com.coveiot.android.tappy.databinding.ActivityRegisteredProductSummaryBinding;
import com.coveiot.android.tappy.model.DeviceInfo;
import com.coveiot.android.tappy.model.GetTransactionDetailsByIdResponse;
import com.coveiot.android.tappy.model.GetTransactionDetailsResponse;
import com.coveiot.android.tappy.model.PaymentInstrumentData;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenData;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenStatus;
import com.coveiot.android.tappy.model.ProductDetails;
import com.coveiot.android.tappy.model.RegCardBeanInfo;
import com.coveiot.android.tappy.model.RegStrapBeanInfo;
import com.coveiot.android.tappy.model.RegisteredProductInfo;
import com.coveiot.android.tappy.model.ResumePaymentInstrumentTokenResponse;
import com.coveiot.android.tappy.model.SuspendPaymentInstrumentTokenResponse;
import com.coveiot.android.tappy.model.TransactionBeanInfo;
import com.coveiot.android.tappy.model.TransactionDetails;
import com.coveiot.android.tappy.model.UserDetails;
import com.coveiot.android.tappy.nfc.NfcUtility;
import com.coveiot.android.tappy.utils.Action;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.tappy.utils.TappyCleverTapConstants;
import com.coveiot.android.tappy.utils.Utils;
import com.coveiot.android.tappy.viewmodel.ManageViewModel;
import com.coveiot.android.tappy.viewmodel.NfcStrapViewModel;
import com.coveiot.android.tappy.viewmodel.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
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
public final class ActivityRegisteredProductSummary extends BaseActivity implements TransactionHistoryAdapter.ItemClickListener, SuccessResultListener {
    public boolean A;
    @Nullable
    public RecyclerView B;
    @Nullable
    public ConstraintLayout C;
    @Nullable
    public ErrorDialog E;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle F;
    @Nullable
    public CVVInputDialog G;
    @Nullable
    public TransactionDetailDialog H;
    @Nullable
    public ReasonInputDialog I;
    @Nullable
    public CardActionConfirmationDialog J;
    public ActivityRegisteredProductSummaryBinding q;
    public long r;
    public long s;
    public NfcStrapViewModel t;
    @Nullable
    public PagerAdapterRegisteredProductSummary v;
    public int w;
    public int x;
    public ManageViewModel y;
    @Nullable
    public TransactionHistoryAdapter z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityRegisteredProductSummary";
    @NotNull
    public final ArrayList<RegStrapBeanInfo> u = new ArrayList<>();
    @NotNull
    public final ArrayList<TransactionBeanInfo> D = new ArrayList<>();

    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Action.values().length];
            try {
                iArr[Action.suspend.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Action.resume.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$handlePIAddedCase$1$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ RegStrapBeanInfo $regStrapBeanInfo;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(RegStrapBeanInfo regStrapBeanInfo, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$regStrapBeanInfo = regStrapBeanInfo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$regStrapBeanInfo, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ManageViewModel manageViewModel;
            RegCardBeanInfo regCardBeanInfo;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ManageViewModel manageViewModel2 = ActivityRegisteredProductSummary.this.y;
                Long l = null;
                if (manageViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                    manageViewModel = null;
                } else {
                    manageViewModel = manageViewModel2;
                }
                RegStrapBeanInfo regStrapBeanInfo = this.$regStrapBeanInfo;
                Long endUserID = regStrapBeanInfo != null ? regStrapBeanInfo.getEndUserID() : null;
                Intrinsics.checkNotNull(endUserID);
                long longValue = endUserID.longValue();
                RegStrapBeanInfo regStrapBeanInfo2 = this.$regStrapBeanInfo;
                if (regStrapBeanInfo2 != null && (regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo()) != null) {
                    l = regCardBeanInfo.getPaymentInstrumentTokenId();
                }
                Intrinsics.checkNotNull(l);
                ManageViewModel.deletePaymentInstrumentTokens$default(manageViewModel, longValue, l.longValue(), null, 4, null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$handlePIAddedCase$1$2", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {604}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ RegStrapBeanInfo $regStrapBeanInfo;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(RegStrapBeanInfo regStrapBeanInfo, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$regStrapBeanInfo = regStrapBeanInfo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$regStrapBeanInfo, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            RegCardBeanInfo regCardBeanInfo;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                NfcStrapViewModel nfcStrapViewModel = ActivityRegisteredProductSummary.this.t;
                String str = null;
                if (nfcStrapViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    nfcStrapViewModel = null;
                }
                RegStrapBeanInfo regStrapBeanInfo = this.$regStrapBeanInfo;
                if (regStrapBeanInfo != null && (regCardBeanInfo = regStrapBeanInfo.getRegCardBeanInfo()) != null) {
                    str = regCardBeanInfo.getTermsAndConditionsFileUrl();
                }
                Intrinsics.checkNotNull(str);
                URL url = new URL(str);
                this.label = 1;
                if (nfcStrapViewModel.getTAndCTextFromFileUrl(url, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$handlePIAddedCase$1$3", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
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
                ActivityRegisteredProductSummary.this.dismissProgress();
                ActivityRegisteredProductSummary activityRegisteredProductSummary = ActivityRegisteredProductSummary.this;
                String string = activityRegisteredProductSummary.getString(R.string.some_error_occurred_try_again);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                activityRegisteredProductSummary.showErrorDialog(string);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes7.dex */
    public static final class d extends Lambda implements Function1<String, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$observeTermsAndConditions$1$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ String $it;
            public int label;
            public final /* synthetic */ ActivityRegisteredProductSummary this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$observeTermsAndConditions$1$1$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0309a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityRegisteredProductSummary this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0309a(ActivityRegisteredProductSummary activityRegisteredProductSummary, Continuation<? super C0309a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityRegisteredProductSummary;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0309a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0309a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        ActivityRegisteredProductSummary activityRegisteredProductSummary = this.this$0;
                        String string = activityRegisteredProductSummary.getString(R.string.some_error_occurred_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                        activityRegisteredProductSummary.showErrorDialog(string);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$observeTermsAndConditions$1$1$2", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ String $it;
                public int label;
                public final /* synthetic */ ActivityRegisteredProductSummary this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(ActivityRegisteredProductSummary activityRegisteredProductSummary, String str, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = activityRegisteredProductSummary;
                    this.$it = str;
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
                        ActivityRegisteredProductSummary.r0(this.this$0, this.$it, null, 2, null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityRegisteredProductSummary activityRegisteredProductSummary, String str, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityRegisteredProductSummary;
                this.$it = str;
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
                    String str = this.$it;
                    if (str == null || str.length() == 0) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new C0309a(this.this$0, null), 2, null);
                    } else {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new b(this.this$0, this.$it, null), 2, null);
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
        public /* bridge */ /* synthetic */ Unit invoke(String str) {
            invoke2(str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable String str) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityRegisteredProductSummary.this), Dispatchers.getMain(), null, new a(ActivityRegisteredProductSummary.this, str, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class e extends Lambda implements Function1<List<? extends DeviceInfo>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$2$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ List<DeviceInfo> $it;
            public int label;
            public final /* synthetic */ ActivityRegisteredProductSummary this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$2$1$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$e$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0310a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ DeviceInfo $deviceInfo;
                public int label;
                public final /* synthetic */ ActivityRegisteredProductSummary this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0310a(ActivityRegisteredProductSummary activityRegisteredProductSummary, DeviceInfo deviceInfo, Continuation<? super C0310a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityRegisteredProductSummary;
                    this.$deviceInfo = deviceInfo;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0310a(this.this$0, this.$deviceInfo, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0310a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        NfcStrapViewModel nfcStrapViewModel = this.this$0.t;
                        if (nfcStrapViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            nfcStrapViewModel = null;
                        }
                        nfcStrapViewModel.registerNewDevice(this.this$0.getGlobalUserId(), this.$deviceInfo);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$2$1$2", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityRegisteredProductSummary this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(ActivityRegisteredProductSummary activityRegisteredProductSummary, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = activityRegisteredProductSummary;
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
                        NfcStrapViewModel nfcStrapViewModel = this.this$0.t;
                        if (nfcStrapViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            nfcStrapViewModel = null;
                        }
                        nfcStrapViewModel.getAllRegisteredProductByUserId(this.this$0.getGlobalUserId());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityRegisteredProductSummary activityRegisteredProductSummary, List<DeviceInfo> list, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityRegisteredProductSummary;
                this.$it = list;
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
                    if (!this.this$0.isFinishing()) {
                        DeviceInfo deviceInfo = Utils.Companion.getDeviceInfo(this.$it, this.this$0);
                        if (deviceInfo.getDeviceId() != null) {
                            Long deviceId = deviceInfo.getDeviceId();
                            Intrinsics.checkNotNull(deviceId);
                            if (deviceId.longValue() > 0) {
                                ActivityRegisteredProductSummary activityRegisteredProductSummary = this.this$0;
                                Long deviceId2 = deviceInfo.getDeviceId();
                                Intrinsics.checkNotNull(deviceId2);
                                activityRegisteredProductSummary.setDeviceId(deviceId2.longValue());
                                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new b(this.this$0, null), 2, null);
                            }
                        }
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new C0310a(this.this$0, deviceInfo, null), 2, null);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends DeviceInfo> list) {
            invoke2((List<DeviceInfo>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<DeviceInfo> list) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityRegisteredProductSummary.this), Dispatchers.getMain(), null, new a(ActivityRegisteredProductSummary.this, list, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class f extends Lambda implements Function1<DeviceInfo, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$3$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ DeviceInfo $it;
            public int label;
            public final /* synthetic */ ActivityRegisteredProductSummary this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$3$1$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$f$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0311a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityRegisteredProductSummary this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0311a(ActivityRegisteredProductSummary activityRegisteredProductSummary, Continuation<? super C0311a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityRegisteredProductSummary;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0311a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0311a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        NfcStrapViewModel nfcStrapViewModel = this.this$0.t;
                        if (nfcStrapViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            nfcStrapViewModel = null;
                        }
                        nfcStrapViewModel.getAllRegisteredProductByUserId(this.this$0.getGlobalUserId());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityRegisteredProductSummary activityRegisteredProductSummary, DeviceInfo deviceInfo, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityRegisteredProductSummary;
                this.$it = deviceInfo;
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
                    if (!this.this$0.isFinishing()) {
                        DeviceInfo deviceInfo = this.$it;
                        if (deviceInfo == null) {
                            this.this$0.dismissProgress();
                            LogHelper.d(this.this$0.getTAG(), "New device registration failed.");
                            ActivityRegisteredProductSummary activityRegisteredProductSummary = this.this$0;
                            String string = activityRegisteredProductSummary.getString(R.string.some_error_occurred_try_again);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                            activityRegisteredProductSummary.showErrorDialog(string);
                        } else {
                            ActivityRegisteredProductSummary activityRegisteredProductSummary2 = this.this$0;
                            Long deviceId = deviceInfo.getDeviceId();
                            Intrinsics.checkNotNull(deviceId);
                            activityRegisteredProductSummary2.setDeviceId(deviceId.longValue());
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new C0311a(this.this$0, null), 2, null);
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public f() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DeviceInfo deviceInfo) {
            invoke2(deviceInfo);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable DeviceInfo deviceInfo) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityRegisteredProductSummary.this), Dispatchers.getMain(), null, new a(ActivityRegisteredProductSummary.this, deviceInfo, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class g extends Lambda implements Function1<List<? extends RegisteredProductInfo>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$4$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ List<RegisteredProductInfo> $it;
            public int label;
            public final /* synthetic */ ActivityRegisteredProductSummary this$0;

            /* renamed from: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$g$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0312a extends Lambda implements Function1<Intent, Unit> {
                public final /* synthetic */ ActivityRegisteredProductSummary this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0312a(ActivityRegisteredProductSummary activityRegisteredProductSummary) {
                    super(1);
                    this.this$0 = activityRegisteredProductSummary;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
                    invoke2(intent);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull Intent launchActivity) {
                    Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
                    launchActivity.putExtra(Constants.END_USER_GLOBAL_ID, this.this$0.getGlobalUserId());
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityRegisteredProductSummary activityRegisteredProductSummary, List<RegisteredProductInfo> list, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityRegisteredProductSummary;
                this.$it = list;
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
                ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.dismissProgress();
                    this.this$0.u.clear();
                    List<RegisteredProductInfo> list = this.$it;
                    boolean z = true;
                    if (!(list == null || list.isEmpty())) {
                        for (RegisteredProductInfo registeredProductInfo : this.$it) {
                            RegStrapBeanInfo regStrapBeanInfo = new RegStrapBeanInfo(null, null, null, null, null, null, null, null, false, false, null, 2047, null);
                            regStrapBeanInfo.setDeviceId(Boxing.boxLong(this.this$0.getDeviceId()));
                            ProductDetails product = registeredProductInfo.getProduct();
                            regStrapBeanInfo.setProductName(product != null ? product.getName() : null);
                            ProductDetails product2 = registeredProductInfo.getProduct();
                            regStrapBeanInfo.setSku(product2 != null ? product2.getSKU() : null);
                            regStrapBeanInfo.setEndUserID(registeredProductInfo.getEndUserID());
                            regStrapBeanInfo.setProductSerialNumber(registeredProductInfo.getProductSerialNumber());
                            regStrapBeanInfo.setEndUserProductRegistrationID(registeredProductInfo.getEndUserProductRegistrationID());
                            ProductDetails product3 = registeredProductInfo.getProduct();
                            regStrapBeanInfo.setImageUrl(product3 != null ? product3.getImageUrl() : null);
                            regStrapBeanInfo.setFriendlyName(registeredProductInfo.getFriendlyName());
                            regStrapBeanInfo.setSelected(false);
                            List<PaymentInstrumentTokenData> paymentInstrumentTokens = registeredProductInfo.getPaymentInstrumentTokens();
                            regStrapBeanInfo.setCardAdded(!(paymentInstrumentTokens == null || paymentInstrumentTokens.isEmpty()));
                            List<PaymentInstrumentTokenData> paymentInstrumentTokens2 = registeredProductInfo.getPaymentInstrumentTokens();
                            if (!(paymentInstrumentTokens2 == null || paymentInstrumentTokens2.isEmpty())) {
                                List<PaymentInstrumentTokenData> paymentInstrumentTokens3 = registeredProductInfo.getPaymentInstrumentTokens();
                                Intrinsics.checkNotNull(paymentInstrumentTokens3);
                                for (PaymentInstrumentTokenData paymentInstrumentTokenData : paymentInstrumentTokens3) {
                                    if (paymentInstrumentTokenData.getPaymentInstrument() != null) {
                                        RegCardBeanInfo regCardBeanInfo = new RegCardBeanInfo(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, null, false, null, null, 268435455, null);
                                        ProductDetails product4 = registeredProductInfo.getProduct();
                                        regCardBeanInfo.setProductName(product4 != null ? product4.getName() : null);
                                        regCardBeanInfo.setEndUserID(registeredProductInfo.getEndUserID());
                                        regCardBeanInfo.setProductSerialNumber(registeredProductInfo.getProductSerialNumber());
                                        regCardBeanInfo.setEndUserProductRegistrationID(registeredProductInfo.getEndUserProductRegistrationID());
                                        regCardBeanInfo.setFriendlyName(registeredProductInfo.getFriendlyName());
                                        regCardBeanInfo.setStatus(paymentInstrumentTokenData.getStatus());
                                        regCardBeanInfo.setProvisioningStatus(paymentInstrumentTokenData.getProvisioningStatus());
                                        regCardBeanInfo.setPaymentInstrumentTokenId(paymentInstrumentTokenData.getPaymentInstrumentTokenID());
                                        PaymentInstrumentData paymentInstrument = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument);
                                        regCardBeanInfo.setPaymentInstrumentId(paymentInstrument.getPaymentInstrumentID());
                                        PaymentInstrumentData paymentInstrument2 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument2);
                                        regCardBeanInfo.setLast4(paymentInstrument2.getLast4());
                                        PaymentInstrumentData paymentInstrument3 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument3);
                                        regCardBeanInfo.setPaymentNetworkId(paymentInstrument3.getPaymentNetworkID());
                                        PaymentInstrumentData paymentInstrument4 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument4);
                                        regCardBeanInfo.setPaymentNetworkName(paymentInstrument4.getPaymentNetworkName());
                                        PaymentInstrumentData paymentInstrument5 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument5);
                                        regCardBeanInfo.setCardArtImageUrl(paymentInstrument5.getCardArtImageUrl());
                                        PaymentInstrumentData paymentInstrument6 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument6);
                                        regCardBeanInfo.setCardSymbolImageUrl(paymentInstrument6.getCardSymbolImageUrl());
                                        PaymentInstrumentData paymentInstrument7 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument7);
                                        regCardBeanInfo.setBackgroundColor(paymentInstrument7.getBackgroundColor());
                                        PaymentInstrumentData paymentInstrument8 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument8);
                                        regCardBeanInfo.setLabelColor(paymentInstrument8.getLabelColor());
                                        PaymentInstrumentData paymentInstrument9 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument9);
                                        regCardBeanInfo.setContactName(paymentInstrument9.getContactName());
                                        PaymentInstrumentData paymentInstrument10 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument10);
                                        regCardBeanInfo.setContactNumber(paymentInstrument10.getContactNumber());
                                        PaymentInstrumentData paymentInstrument11 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument11);
                                        regCardBeanInfo.setContactEmail(paymentInstrument11.getContactEmail());
                                        PaymentInstrumentData paymentInstrument12 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument12);
                                        regCardBeanInfo.setContactWebsite(paymentInstrument12.getContactWebSite());
                                        PaymentInstrumentData paymentInstrument13 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument13);
                                        regCardBeanInfo.setTermsAndConditionId(paymentInstrument13.getTermsAndConditionsID());
                                        PaymentInstrumentData paymentInstrument14 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument14);
                                        regCardBeanInfo.setTermsAndConditionsFileUrl(paymentInstrument14.getTermsAndConditionsFileUrl());
                                        PaymentInstrumentData paymentInstrument15 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument15);
                                        regCardBeanInfo.setPIFinalized(Intrinsics.areEqual(paymentInstrument15.isPIFinalized(), Boxing.boxBoolean(true)));
                                        PaymentInstrumentData paymentInstrument16 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument16);
                                        regCardBeanInfo.setDeleted(Intrinsics.areEqual(paymentInstrument16.isDeleted(), Boxing.boxBoolean(true)));
                                        PaymentInstrumentData paymentInstrument17 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument17);
                                        regCardBeanInfo.setIssuerType(paymentInstrument17.getIssuerType());
                                        ProductDetails product5 = registeredProductInfo.getProduct();
                                        regCardBeanInfo.setProductImageUrl(product5 != null ? product5.getImageUrl() : null);
                                        regCardBeanInfo.setSelected(false);
                                        regCardBeanInfo.setExpiryDate(paymentInstrumentTokenData.getExpiryDate());
                                        regStrapBeanInfo.setRegCardBeanInfo(regCardBeanInfo);
                                    }
                                }
                            }
                            this.this$0.u.add(regStrapBeanInfo);
                        }
                        ArrayList arrayList = this.this$0.u;
                        if (arrayList != null && !arrayList.isEmpty()) {
                            z = false;
                        }
                        if (z) {
                            ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding2 = this.this$0.q;
                            if (activityRegisteredProductSummaryBinding2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRegisteredProductSummaryBinding2 = null;
                            }
                            activityRegisteredProductSummaryBinding2.viewPagerRegisteredProductSummary.setVisibility(8);
                            ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding3 = this.this$0.q;
                            if (activityRegisteredProductSummaryBinding3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRegisteredProductSummaryBinding = null;
                            } else {
                                activityRegisteredProductSummaryBinding = activityRegisteredProductSummaryBinding3;
                            }
                            activityRegisteredProductSummaryBinding.virtualCardTitleLayout.setVisibility(8);
                            this.this$0.O();
                            this.this$0.N();
                        } else {
                            ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding4 = this.this$0.q;
                            if (activityRegisteredProductSummaryBinding4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRegisteredProductSummaryBinding4 = null;
                            }
                            activityRegisteredProductSummaryBinding4.virtualCardTitleLayout.setVisibility(0);
                            ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding5 = this.this$0.q;
                            if (activityRegisteredProductSummaryBinding5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRegisteredProductSummaryBinding5 = null;
                            }
                            activityRegisteredProductSummaryBinding5.viewPagerRegisteredProductSummary.setVisibility(0);
                            ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding6 = this.this$0.q;
                            if (activityRegisteredProductSummaryBinding6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRegisteredProductSummaryBinding6 = null;
                            }
                            activityRegisteredProductSummaryBinding6.viewPagerRegisteredProductSummary.setClipToPadding(false);
                            ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding7 = this.this$0.q;
                            if (activityRegisteredProductSummaryBinding7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRegisteredProductSummaryBinding7 = null;
                            }
                            activityRegisteredProductSummaryBinding7.viewPagerRegisteredProductSummary.setPadding(40, 0, 40, 0);
                            ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding8 = this.this$0.q;
                            if (activityRegisteredProductSummaryBinding8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRegisteredProductSummaryBinding8 = null;
                            }
                            activityRegisteredProductSummaryBinding8.viewPagerRegisteredProductSummary.setPageMargin(20);
                            ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding9 = this.this$0.q;
                            if (activityRegisteredProductSummaryBinding9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRegisteredProductSummaryBinding9 = null;
                            }
                            activityRegisteredProductSummaryBinding9.noStrapAdded.setVisibility(8);
                            ActivityRegisteredProductSummary activityRegisteredProductSummary = this.this$0;
                            FragmentManager supportFragmentManager = activityRegisteredProductSummary.getSupportFragmentManager();
                            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                            activityRegisteredProductSummary.v = new PagerAdapterRegisteredProductSummary(activityRegisteredProductSummary, supportFragmentManager, this.this$0.u);
                            ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding10 = this.this$0.q;
                            if (activityRegisteredProductSummaryBinding10 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRegisteredProductSummaryBinding10 = null;
                            }
                            activityRegisteredProductSummaryBinding10.viewPagerRegisteredProductSummary.setAdapter(this.this$0.v);
                            ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding11 = this.this$0.q;
                            if (activityRegisteredProductSummaryBinding11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRegisteredProductSummaryBinding11 = null;
                            }
                            PagerAdapter adapter = activityRegisteredProductSummaryBinding11.viewPagerRegisteredProductSummary.getAdapter();
                            if (adapter != null) {
                                adapter.notifyDataSetChanged();
                            }
                            ActivityRegisteredProductSummary activityRegisteredProductSummary2 = this.this$0;
                            PagerAdapterRegisteredProductSummary pagerAdapterRegisteredProductSummary = activityRegisteredProductSummary2.v;
                            Integer boxInt = pagerAdapterRegisteredProductSummary != null ? Boxing.boxInt(pagerAdapterRegisteredProductSummary.getCount()) : null;
                            Intrinsics.checkNotNull(boxInt);
                            activityRegisteredProductSummary2.w = boxInt.intValue();
                            this.this$0.x = 0;
                            this.this$0.N();
                            this.this$0.O();
                        }
                    } else {
                        ActivityRegisteredProductSummary activityRegisteredProductSummary3 = this.this$0;
                        C0312a c0312a = new C0312a(activityRegisteredProductSummary3);
                        Intent intent = new Intent(activityRegisteredProductSummary3, AddNfcStrapFtuActivity.class);
                        c0312a.invoke((C0312a) intent);
                        activityRegisteredProductSummary3.startActivityForResult(intent, -1, null);
                        this.this$0.finish();
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public g() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends RegisteredProductInfo> list) {
            invoke2((List<RegisteredProductInfo>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<RegisteredProductInfo> list) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityRegisteredProductSummary.this), Dispatchers.getMain(), null, new a(ActivityRegisteredProductSummary.this, list, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class h extends Lambda implements Function1<UserDetails, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$5$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ UserDetails $it;
            public int label;
            public final /* synthetic */ ActivityRegisteredProductSummary this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$5$1$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$h$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0313a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityRegisteredProductSummary this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0313a(ActivityRegisteredProductSummary activityRegisteredProductSummary, Continuation<? super C0313a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityRegisteredProductSummary;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0313a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0313a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        NfcStrapViewModel nfcStrapViewModel = this.this$0.t;
                        if (nfcStrapViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            nfcStrapViewModel = null;
                        }
                        nfcStrapViewModel.getDeviceInfoByUserId(this.this$0.getGlobalUserId());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityRegisteredProductSummary activityRegisteredProductSummary, UserDetails userDetails, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityRegisteredProductSummary;
                this.$it = userDetails;
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
                    if (!this.this$0.isFinishing()) {
                        UserDetails userDetails = this.$it;
                        if (userDetails != null && userDetails.getGlobalUserID() > 0) {
                            this.this$0.setGlobalUserId(this.$it.getGlobalUserID());
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new C0313a(this.this$0, null), 2, null);
                        } else {
                            this.this$0.dismissProgress();
                            LogHelper.d(this.this$0.getTAG(), "User Id not found.");
                            ActivityRegisteredProductSummary activityRegisteredProductSummary = this.this$0;
                            String string = activityRegisteredProductSummary.getString(R.string.some_error_occurred_try_again);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                            activityRegisteredProductSummary.showErrorDialog(string);
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public h() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(UserDetails userDetails) {
            invoke2(userDetails);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable UserDetails userDetails) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityRegisteredProductSummary.this), Dispatchers.getMain(), null, new a(ActivityRegisteredProductSummary.this, userDetails, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class i extends Lambda implements Function1<GetTransactionDetailsByIdResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$7$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ GetTransactionDetailsByIdResponse $it;
            public int label;
            public final /* synthetic */ ActivityRegisteredProductSummary this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityRegisteredProductSummary activityRegisteredProductSummary, GetTransactionDetailsByIdResponse getTransactionDetailsByIdResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityRegisteredProductSummary;
                this.$it = getTransactionDetailsByIdResponse;
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
                    if (!this.this$0.isFinishing()) {
                        this.this$0.dismissProgress();
                        GetTransactionDetailsByIdResponse getTransactionDetailsByIdResponse = this.$it;
                        if (getTransactionDetailsByIdResponse != null) {
                            this.this$0.z0(getTransactionDetailsByIdResponse);
                        } else {
                            ActivityRegisteredProductSummary activityRegisteredProductSummary = this.this$0;
                            Toast.makeText(activityRegisteredProductSummary, activityRegisteredProductSummary.getString(R.string.some_error_occurred_try_again), 1).show();
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public i() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetTransactionDetailsByIdResponse getTransactionDetailsByIdResponse) {
            invoke2(getTransactionDetailsByIdResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable GetTransactionDetailsByIdResponse getTransactionDetailsByIdResponse) {
            LifecycleCoroutineScope lifecycleScope = LifecycleOwnerKt.getLifecycleScope(ActivityRegisteredProductSummary.this);
            if (lifecycleScope != null) {
                kotlinx.coroutines.e.e(lifecycleScope, Dispatchers.getMain(), null, new a(ActivityRegisteredProductSummary.this, getTransactionDetailsByIdResponse, null), 2, null);
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class j extends Lambda implements Function1<ResumePaymentInstrumentTokenResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$8$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ResumePaymentInstrumentTokenResponse $it;
            public int label;
            public final /* synthetic */ ActivityRegisteredProductSummary this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityRegisteredProductSummary activityRegisteredProductSummary, ResumePaymentInstrumentTokenResponse resumePaymentInstrumentTokenResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityRegisteredProductSummary;
                this.$it = resumePaymentInstrumentTokenResponse;
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
                    if (this.$it != null) {
                        ActivityRegisteredProductSummary activityRegisteredProductSummary = this.this$0;
                        Toast.makeText(activityRegisteredProductSummary, activityRegisteredProductSummary.getString(R.string.your_card_resumed_successfully), 1).show();
                        this.this$0.Z(CleverTapConstants.EventName.BC_PAY_RESUMEDCARD_SUCCESS);
                        this.this$0.refresh();
                    } else {
                        ActivityRegisteredProductSummary activityRegisteredProductSummary2 = this.this$0;
                        Toast.makeText(activityRegisteredProductSummary2, activityRegisteredProductSummary2.getString(R.string.something_went_wrong), 1).show();
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public j() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ResumePaymentInstrumentTokenResponse resumePaymentInstrumentTokenResponse) {
            invoke2(resumePaymentInstrumentTokenResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable ResumePaymentInstrumentTokenResponse resumePaymentInstrumentTokenResponse) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityRegisteredProductSummary.this), Dispatchers.getMain(), null, new a(ActivityRegisteredProductSummary.this, resumePaymentInstrumentTokenResponse, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class k extends Lambda implements Function1<SuspendPaymentInstrumentTokenResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$9$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ SuspendPaymentInstrumentTokenResponse $it;
            public int label;
            public final /* synthetic */ ActivityRegisteredProductSummary this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityRegisteredProductSummary activityRegisteredProductSummary, SuspendPaymentInstrumentTokenResponse suspendPaymentInstrumentTokenResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityRegisteredProductSummary;
                this.$it = suspendPaymentInstrumentTokenResponse;
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
                ReasonInputDialog reasonInputDialog;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.dismissProgress();
                    ReasonInputDialog reasonInputDialog2 = this.this$0.I;
                    boolean z = false;
                    if (reasonInputDialog2 != null && reasonInputDialog2.isShowing()) {
                        z = true;
                    }
                    if (z && (reasonInputDialog = this.this$0.I) != null) {
                        reasonInputDialog.dismiss();
                    }
                    this.this$0.I = null;
                    if (this.$it != null) {
                        ActivityRegisteredProductSummary activityRegisteredProductSummary = this.this$0;
                        Toast.makeText(activityRegisteredProductSummary, activityRegisteredProductSummary.getString(R.string.your_card_suspended_successfully), 1).show();
                        this.this$0.Z(CleverTapConstants.EventName.BC_PAY_SUSPENDCARD_SUCCESS);
                        this.this$0.refresh();
                    } else {
                        ActivityRegisteredProductSummary activityRegisteredProductSummary2 = this.this$0;
                        Toast.makeText(activityRegisteredProductSummary2, activityRegisteredProductSummary2.getString(R.string.something_went_wrong), 1).show();
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public k() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SuspendPaymentInstrumentTokenResponse suspendPaymentInstrumentTokenResponse) {
            invoke2(suspendPaymentInstrumentTokenResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable SuspendPaymentInstrumentTokenResponse suspendPaymentInstrumentTokenResponse) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityRegisteredProductSummary.this), Dispatchers.getMain(), null, new a(ActivityRegisteredProductSummary.this, suspendPaymentInstrumentTokenResponse, null), 2, null);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onViewDetail$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class l extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ RegStrapBeanInfo $regStrapBeanInfo;
        public final /* synthetic */ TransactionBeanInfo $transactionBeanInfo;
        public int label;
        public final /* synthetic */ ActivityRegisteredProductSummary this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public l(RegStrapBeanInfo regStrapBeanInfo, ActivityRegisteredProductSummary activityRegisteredProductSummary, TransactionBeanInfo transactionBeanInfo, Continuation<? super l> continuation) {
            super(2, continuation);
            this.$regStrapBeanInfo = regStrapBeanInfo;
            this.this$0 = activityRegisteredProductSummary;
            this.$transactionBeanInfo = transactionBeanInfo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new l(this.$regStrapBeanInfo, this.this$0, this.$transactionBeanInfo, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((l) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ManageViewModel manageViewModel;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                RegStrapBeanInfo regStrapBeanInfo = this.$regStrapBeanInfo;
                if ((regStrapBeanInfo != null ? regStrapBeanInfo.getRegCardBeanInfo() : null) != null) {
                    ManageViewModel manageViewModel2 = this.this$0.y;
                    if (manageViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                        manageViewModel = null;
                    } else {
                        manageViewModel = manageViewModel2;
                    }
                    RegStrapBeanInfo regStrapBeanInfo2 = this.$regStrapBeanInfo;
                    Intrinsics.checkNotNull(regStrapBeanInfo2);
                    Long endUserID = regStrapBeanInfo2.getEndUserID();
                    Intrinsics.checkNotNull(endUserID);
                    long longValue = endUserID.longValue();
                    RegStrapBeanInfo regStrapBeanInfo3 = this.$regStrapBeanInfo;
                    Intrinsics.checkNotNull(regStrapBeanInfo3);
                    RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo3.getRegCardBeanInfo();
                    Intrinsics.checkNotNull(regCardBeanInfo);
                    Long paymentInstrumentTokenId = regCardBeanInfo.getPaymentInstrumentTokenId();
                    Intrinsics.checkNotNull(paymentInstrumentTokenId);
                    long longValue2 = paymentInstrumentTokenId.longValue();
                    TransactionBeanInfo transactionBeanInfo = this.$transactionBeanInfo;
                    Intrinsics.checkNotNull(transactionBeanInfo);
                    Long transactionId = transactionBeanInfo.getTransactionId();
                    Intrinsics.checkNotNull(transactionId);
                    manageViewModel.getTransactionDetailById(longValue, longValue2, transactionId.longValue());
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$refresh$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class m extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public m(Continuation<? super m> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new m(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((m) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                NfcStrapViewModel nfcStrapViewModel = ActivityRegisteredProductSummary.this.t;
                if (nfcStrapViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    nfcStrapViewModel = null;
                }
                nfcStrapViewModel.getUserInfo();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public ActivityRegisteredProductSummary() {
        new ArrayList();
    }

    public static final void B0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void Q(ActivityRegisteredProductSummary this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.Z(CleverTapConstants.EventName.BC_PAY_RESUMECARD_REQUEST);
        if (AppUtils.isNetConnected(this$0)) {
            this$0.m0(Action.resume);
        } else {
            this$0.showNoInternetMessage();
        }
    }

    public static final void S(ActivityRegisteredProductSummary this$0, RegStrapBeanInfo regStrapBeanInfo, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(regStrapBeanInfo, "$regStrapBeanInfo");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_ACTIVATECARD_REQUEST.getValue(), null);
        Intent intent = new Intent(this$0, CardActivationActivity.class);
        intent.putExtra(Constants.END_USER_GLOBAL_ID, regStrapBeanInfo.getEndUserID());
        intent.putExtra(Constants.END_USER_PRODUCT_REGISTRATION_ID, regStrapBeanInfo.getEndUserProductRegistrationID());
        intent.putExtra(Constants.SERIAL_NUMBER, regStrapBeanInfo.getProductSerialNumber());
        RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo.getRegCardBeanInfo();
        intent.putExtra(Constants.PAYMENT_INSTRUMENT_TOKEN_ID, regCardBeanInfo != null ? regCardBeanInfo.getPaymentInstrumentTokenId() : null);
        intent.putExtra("deviceId", regStrapBeanInfo.getDeviceId());
        RegCardBeanInfo regCardBeanInfo2 = regStrapBeanInfo.getRegCardBeanInfo();
        intent.putExtra(Constants.CARD_ART_URL, regCardBeanInfo2 != null ? regCardBeanInfo2.getCardArtImageUrl() : null);
        RegCardBeanInfo regCardBeanInfo3 = regStrapBeanInfo.getRegCardBeanInfo();
        intent.putExtra(Constants.LAST4, regCardBeanInfo3 != null ? regCardBeanInfo3.getLast4() : null);
        this$0.startActivity(intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x0102, code lost:
        if (r9.intValue() != r3) goto L50;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void V(com.coveiot.android.tappy.model.RegStrapBeanInfo r9, com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary r10, android.view.View r11) {
        /*
            Method dump skipped, instructions count: 341
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary.V(com.coveiot.android.tappy.model.RegStrapBeanInfo, com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary, android.view.View):void");
    }

    public static final void X(ActivityRegisteredProductSummary this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.m0(Action.suspend);
        } else {
            this$0.showNoInternetMessage();
        }
        this$0.Z(CleverTapConstants.EventName.BC_PAY_SUSPENDCARD_REQUEST);
    }

    public static final void b0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void c0(ActivityRegisteredProductSummary this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0)) {
            this$0.showNoInternetMessage();
            return;
        }
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_SETTINGS_TAPPED.getValue(), null);
        Intent intent = new Intent(this$0, ActivityManageRegisteredProducts.class);
        intent.putExtra(Constants.END_USER_GLOBAL_ID, this$0.r);
        intent.putExtra(Constants.REGISTERED_STRAP_BEAN_LIST, this$0.u);
        this$0.startActivity(intent);
    }

    public static final void d0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void e0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void f0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void g0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void h0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void i0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void j0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void l0(ActivityRegisteredProductSummary this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void n0(ActivityRegisteredProductSummary this$0, Action action, View view) {
        ManageViewModel manageViewModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(action, "$action");
        if (AppUtils.isNetConnected(this$0)) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[action.ordinal()];
            if (i2 == 1) {
                CardActionConfirmationDialog cardActionConfirmationDialog = this$0.J;
                if (cardActionConfirmationDialog != null) {
                    cardActionConfirmationDialog.dismiss();
                }
                this$0.J = null;
                this$0.w0();
                return;
            } else if (i2 != 2) {
                return;
            } else {
                RegStrapBeanInfo regStrapBeanInfo = this$0.u.get(this$0.x);
                Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "registeredStrapInfoList[selectedItemPosition]");
                RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
                CardActionConfirmationDialog cardActionConfirmationDialog2 = this$0.J;
                if (cardActionConfirmationDialog2 != null) {
                    cardActionConfirmationDialog2.dismiss();
                }
                this$0.J = null;
                this$0.showProgress();
                ManageViewModel manageViewModel2 = this$0.y;
                if (manageViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                    manageViewModel = null;
                } else {
                    manageViewModel = manageViewModel2;
                }
                Long endUserID = regStrapBeanInfo2.getEndUserID();
                Intrinsics.checkNotNull(endUserID);
                long longValue = endUserID.longValue();
                RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo();
                Intrinsics.checkNotNull(regCardBeanInfo);
                Long paymentInstrumentTokenId = regCardBeanInfo.getPaymentInstrumentTokenId();
                Intrinsics.checkNotNull(paymentInstrumentTokenId);
                ManageViewModel.resumePaymentInstrumentTokens$default(manageViewModel, longValue, paymentInstrumentTokenId.longValue(), null, 4, null);
                return;
            }
        }
        this$0.showNoInternetMessage();
    }

    public static final void o0(ActivityRegisteredProductSummary this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CardActionConfirmationDialog cardActionConfirmationDialog = this$0.J;
        Intrinsics.checkNotNull(cardActionConfirmationDialog);
        cardActionConfirmationDialog.dismiss();
        this$0.J = null;
    }

    public static final void p0(ActivityRegisteredProductSummary this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ErrorDialog errorDialog = this$0.E;
        Intrinsics.checkNotNull(errorDialog);
        errorDialog.dismiss();
        this$0.finish();
    }

    public static /* synthetic */ void r0(ActivityRegisteredProductSummary activityRegisteredProductSummary, String str, Intent intent, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        if ((i2 & 2) != 0) {
            intent = null;
        }
        activityRegisteredProductSummary.q0(str, intent);
    }

    public static final void s0(EditText editText, ActivityRegisteredProductSummary this$0, TextView textView, Intent intent, String str, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Editable text = editText.getText();
        if (text == null || text.length() == 0) {
            Toast.makeText(this$0, textView.getText().toString(), 1).show();
            return;
        }
        if (intent == null) {
            this$0.Y(str, editText.getText().toString());
        } else {
            intent.putExtra(Constants.SECURITY_CODE, editText.getText().toString());
            this$0.startActivity(intent);
        }
        CVVInputDialog cVVInputDialog = this$0.G;
        Intrinsics.checkNotNull(cVVInputDialog);
        cVVInputDialog.dismiss();
    }

    public static final void t0(ActivityRegisteredProductSummary this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CVVInputDialog cVVInputDialog = this$0.G;
        Intrinsics.checkNotNull(cVVInputDialog);
        cVVInputDialog.dismiss();
    }

    public static final void v0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityRegisteredProductSummary this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.startActivity(new Intent("android.settings.NFC_SETTINGS"));
    }

    public static final void x0(EditText editText, ActivityRegisteredProductSummary this$0, TextView textView, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Editable text = editText.getText();
        if (text == null || text.length() == 0) {
            Toast.makeText(this$0, textView.getText().toString(), 1).show();
            return;
        }
        ReasonInputDialog reasonInputDialog = this$0.I;
        Intrinsics.checkNotNull(reasonInputDialog);
        reasonInputDialog.dismiss();
        this$0.showProgress();
        RegStrapBeanInfo regStrapBeanInfo = this$0.u.get(this$0.x);
        Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "registeredStrapInfoList[selectedItemPosition]");
        RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
        ManageViewModel manageViewModel = this$0.y;
        if (manageViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
            manageViewModel = null;
        }
        ManageViewModel manageViewModel2 = manageViewModel;
        Long endUserID = regStrapBeanInfo2.getEndUserID();
        Intrinsics.checkNotNull(endUserID);
        long longValue = endUserID.longValue();
        RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo();
        Intrinsics.checkNotNull(regCardBeanInfo);
        Long paymentInstrumentTokenId = regCardBeanInfo.getPaymentInstrumentTokenId();
        Intrinsics.checkNotNull(paymentInstrumentTokenId);
        manageViewModel2.suspendPaymentInstrumentTokens(longValue, paymentInstrumentTokenId.longValue(), editText.getText().toString());
    }

    public static final void y0(ActivityRegisteredProductSummary this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReasonInputDialog reasonInputDialog = this$0.I;
        Intrinsics.checkNotNull(reasonInputDialog);
        reasonInputDialog.dismiss();
        this$0.I = null;
    }

    public final void A0() {
        ConstraintLayout constraintLayout = this.C;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(0);
        }
        RegStrapBeanInfo regStrapBeanInfo = this.u.get(this.x);
        Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "registeredStrapInfoList[selectedItemPosition]");
        RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
        RecyclerView recyclerView = this.B;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        showProgress();
        ManageViewModel manageViewModel = this.y;
        ManageViewModel manageViewModel2 = null;
        if (manageViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
            manageViewModel = null;
        }
        Long endUserID = regStrapBeanInfo2.getEndUserID();
        Intrinsics.checkNotNull(endUserID);
        long longValue = endUserID.longValue();
        RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo();
        Long paymentInstrumentTokenId = regCardBeanInfo != null ? regCardBeanInfo.getPaymentInstrumentTokenId() : null;
        Intrinsics.checkNotNull(paymentInstrumentTokenId);
        manageViewModel.getTransactionHistory(longValue, paymentInstrumentTokenId.longValue());
        ManageViewModel manageViewModel3 = this.y;
        if (manageViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
        } else {
            manageViewModel2 = manageViewModel3;
        }
        MutableLiveData<GetTransactionDetailsResponse> getTransactionHistoryLiveData = manageViewModel2.getGetTransactionHistoryLiveData();
        final Function1<GetTransactionDetailsResponse, Unit> function1 = new Function1<GetTransactionDetailsResponse, Unit>() { // from class: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$showTransactionHistory$1

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$showTransactionHistory$1$1", f = "ActivityRegisteredProductSummary.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$showTransactionHistory$1$1  reason: invalid class name */
            /* loaded from: classes7.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ GetTransactionDetailsResponse $it;
                public int label;
                public final /* synthetic */ ActivityRegisteredProductSummary this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(ActivityRegisteredProductSummary activityRegisteredProductSummary, GetTransactionDetailsResponse getTransactionDetailsResponse, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = activityRegisteredProductSummary;
                    this.$it = getTransactionDetailsResponse;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, this.$it, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    ArrayList arrayList;
                    RecyclerView recyclerView;
                    RecyclerView recyclerView2;
                    ArrayList arrayList2;
                    RecyclerView recyclerView3;
                    ArrayList arrayList3;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.dismissProgress();
                        arrayList = this.this$0.D;
                        arrayList.clear();
                        GetTransactionDetailsResponse getTransactionDetailsResponse = this.$it;
                        if (getTransactionDetailsResponse != null) {
                            List<TransactionDetails> transactionDetailList = getTransactionDetailsResponse.getTransactionDetailList();
                            if (!(transactionDetailList == null || transactionDetailList.isEmpty())) {
                                List<TransactionDetails> transactionDetailList2 = this.$it.getTransactionDetailList();
                                Intrinsics.checkNotNull(transactionDetailList2);
                                for (TransactionDetails transactionDetails : transactionDetailList2) {
                                    TransactionBeanInfo transactionBeanInfo = new TransactionBeanInfo(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
                                    transactionBeanInfo.setTransactionId(Boxing.boxLong(transactionDetails.getTransactionID()));
                                    transactionBeanInfo.setAmount(Boxing.boxDouble(transactionDetails.getAmount()));
                                    transactionBeanInfo.setCurrency(transactionDetails.getCurrency());
                                    transactionBeanInfo.setTransactionType(transactionDetails.getTransactionType());
                                    transactionBeanInfo.setTransactionDate(Boxing.boxLong(transactionDetails.getTransactionDate()));
                                    transactionBeanInfo.setIndustryCategoryCode(transactionDetails.getIndustryCategoryCode());
                                    transactionBeanInfo.setIndustryCode(transactionDetails.getIndustryCode());
                                    transactionBeanInfo.setMerchantLatitude(transactionDetails.getMerchantLatitude());
                                    transactionBeanInfo.setMerchantLongitude(transactionDetails.getMerchantLongitude());
                                    transactionBeanInfo.setMerchantName(transactionDetails.getMerchantName());
                                    transactionBeanInfo.setTransactionStatus(transactionDetails.getTransactionStatus());
                                    arrayList3 = this.this$0.D;
                                    arrayList3.add(transactionBeanInfo);
                                }
                                recyclerView2 = this.this$0.B;
                                if (recyclerView2 != null) {
                                    recyclerView2.setVisibility(0);
                                }
                                ActivityRegisteredProductSummary activityRegisteredProductSummary = this.this$0;
                                arrayList2 = activityRegisteredProductSummary.D;
                                activityRegisteredProductSummary.setAdapter(new TransactionHistoryAdapter(activityRegisteredProductSummary, CollectionsKt___CollectionsKt.sortedWith(arrayList2, 
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00eb: INVOKE  
                                      (r1v17 'activityRegisteredProductSummary' com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary)
                                      (wrap: com.coveiot.android.tappy.adapter.TransactionHistoryAdapter : 0x00e8: CONSTRUCTOR  (r2v2 com.coveiot.android.tappy.adapter.TransactionHistoryAdapter A[REMOVE]) = 
                                      (r1v17 'activityRegisteredProductSummary' com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary)
                                      (wrap: java.util.List : 0x00e4: INVOKE  (r3v2 java.util.List A[REMOVE]) = 
                                      (r3v1 'arrayList2' java.util.ArrayList)
                                      (wrap: java.util.Comparator : 0x00e1: CONSTRUCTOR  (r4v0 java.util.Comparator A[REMOVE]) =  call: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$showTransactionHistory$1$1$invokeSuspend$$inlined$sortedByDescending$1.<init>():void type: CONSTRUCTOR)
                                     type: STATIC call: kotlin.collections.CollectionsKt___CollectionsKt.sortedWith(java.lang.Iterable, java.util.Comparator):java.util.List)
                                     call: com.coveiot.android.tappy.adapter.TransactionHistoryAdapter.<init>(android.content.Context, java.util.List):void type: CONSTRUCTOR)
                                     type: VIRTUAL call: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary.setAdapter(com.coveiot.android.tappy.adapter.TransactionHistoryAdapter):void in method: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$showTransactionHistory$1.1.invokeSuspend(java.lang.Object):java.lang.Object, file: classes7.dex
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
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$showTransactionHistory$1$1$invokeSuspend$$inlined$sortedByDescending$1, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:302)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:769)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:765)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                                    	... 35 more
                                    */
                                /*
                                    Method dump skipped, instructions count: 296
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$showTransactionHistory$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                            }
                        }

                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(GetTransactionDetailsResponse getTransactionDetailsResponse) {
                            invoke2(getTransactionDetailsResponse);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke  reason: avoid collision after fix types in other method */
                        public final void invoke2(@Nullable GetTransactionDetailsResponse getTransactionDetailsResponse) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityRegisteredProductSummary.this), Dispatchers.getMain(), null, new AnonymousClass1(ActivityRegisteredProductSummary.this, getTransactionDetailsResponse, null), 2, null);
                        }
                    };
                    getTransactionHistoryLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.l
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            ActivityRegisteredProductSummary.B0(Function1.this, obj);
                        }
                    });
                }

                public final void N() {
                    ArrayList<RegStrapBeanInfo> arrayList = this.u;
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding = null;
                    if (!(arrayList == null || arrayList.isEmpty())) {
                        ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding2 = this.q;
                        if (activityRegisteredProductSummaryBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityRegisteredProductSummaryBinding2 = null;
                        }
                        activityRegisteredProductSummaryBinding2.linearLayoutDots.setVisibility(0);
                        ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding3 = this.q;
                        if (activityRegisteredProductSummaryBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityRegisteredProductSummaryBinding = activityRegisteredProductSummaryBinding3;
                        }
                        LinearLayout linearLayout = activityRegisteredProductSummaryBinding.linearLayoutDots;
                        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.linearLayoutDots");
                        linearLayout.removeAllViews();
                        int i2 = this.w;
                        if (i2 > 1) {
                            for (int i3 = 0; i3 < i2; i3++) {
                                ImageView imageView = new ImageView(this);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                                layoutParams.setMargins(0, 0, getResources().getDimensionPixelSize(R.dimen.margin_8dp), 0);
                                imageView.setLayoutParams(layoutParams);
                                if (i3 == this.x) {
                                    imageView.setImageResource(R.drawable.viewpager_selected_indicator);
                                } else {
                                    imageView.setImageResource(R.drawable.viewpager_unselected_indicator);
                                }
                                linearLayout.addView(imageView);
                            }
                            return;
                        }
                        return;
                    }
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding4 = this.q;
                    if (activityRegisteredProductSummaryBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityRegisteredProductSummaryBinding = activityRegisteredProductSummaryBinding4;
                    }
                    activityRegisteredProductSummaryBinding.linearLayoutDots.setVisibility(8);
                }

                public final void O() {
                    ArrayList<RegStrapBeanInfo> arrayList = this.u;
                    if (!(arrayList == null || arrayList.isEmpty())) {
                        RegStrapBeanInfo regStrapBeanInfo = this.u.get(this.x);
                        Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "registeredStrapInfoList[selectedItemPosition]");
                        RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
                        if (regStrapBeanInfo2.getRegCardBeanInfo() == null) {
                            T();
                            return;
                        }
                        RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo();
                        if (Intrinsics.areEqual(regCardBeanInfo != null ? regCardBeanInfo.getStatus() : null, PaymentInstrumentTokenStatus.INACTIVE.getStatus())) {
                            R();
                            return;
                        }
                        RegCardBeanInfo regCardBeanInfo2 = regStrapBeanInfo2.getRegCardBeanInfo();
                        if (Intrinsics.areEqual(regCardBeanInfo2 != null ? regCardBeanInfo2.getStatus() : null, PaymentInstrumentTokenStatus.ACTIVE.getStatus())) {
                            W();
                            return;
                        }
                        RegCardBeanInfo regCardBeanInfo3 = regStrapBeanInfo2.getRegCardBeanInfo();
                        if (Intrinsics.areEqual(regCardBeanInfo3 != null ? regCardBeanInfo3.getStatus() : null, PaymentInstrumentTokenStatus.PI_ADDED.getStatus())) {
                            U();
                            return;
                        }
                        RegCardBeanInfo regCardBeanInfo4 = regStrapBeanInfo2.getRegCardBeanInfo();
                        if (Intrinsics.areEqual(regCardBeanInfo4 != null ? regCardBeanInfo4.getStatus() : null, PaymentInstrumentTokenStatus.TERMS_ACCEPTED.getStatus())) {
                            U();
                            return;
                        }
                        RegCardBeanInfo regCardBeanInfo5 = regStrapBeanInfo2.getRegCardBeanInfo();
                        if (Intrinsics.areEqual(regCardBeanInfo5 != null ? regCardBeanInfo5.getStatus() : null, PaymentInstrumentTokenStatus.SUSPENDED.getStatus())) {
                            P();
                            return;
                        }
                        return;
                    }
                    T();
                }

                public final void P() {
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding = this.q;
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding2 = null;
                    if (activityRegisteredProductSummaryBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding = null;
                    }
                    activityRegisteredProductSummaryBinding.btnActiveCard.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding3 = this.q;
                    if (activityRegisteredProductSummaryBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding3 = null;
                    }
                    activityRegisteredProductSummaryBinding3.clTransactionHistory.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding4 = this.q;
                    if (activityRegisteredProductSummaryBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding4 = null;
                    }
                    activityRegisteredProductSummaryBinding4.btnSuspendCard.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding5 = this.q;
                    if (activityRegisteredProductSummaryBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding5 = null;
                    }
                    activityRegisteredProductSummaryBinding5.btnResumeCard.setVisibility(0);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding6 = this.q;
                    if (activityRegisteredProductSummaryBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityRegisteredProductSummaryBinding2 = activityRegisteredProductSummaryBinding6;
                    }
                    activityRegisteredProductSummaryBinding2.btnResumeCard.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.b0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityRegisteredProductSummary.Q(ActivityRegisteredProductSummary.this, view);
                        }
                    });
                }

                public final void R() {
                    RegStrapBeanInfo regStrapBeanInfo = this.u.get(this.x);
                    Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "registeredStrapInfoList[selectedItemPosition]");
                    final RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding = this.q;
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding2 = null;
                    if (activityRegisteredProductSummaryBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding = null;
                    }
                    activityRegisteredProductSummaryBinding.btnActiveCard.setVisibility(0);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding3 = this.q;
                    if (activityRegisteredProductSummaryBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding3 = null;
                    }
                    activityRegisteredProductSummaryBinding3.btnResumeCard.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding4 = this.q;
                    if (activityRegisteredProductSummaryBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding4 = null;
                    }
                    activityRegisteredProductSummaryBinding4.btnSuspendCard.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding5 = this.q;
                    if (activityRegisteredProductSummaryBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding5 = null;
                    }
                    activityRegisteredProductSummaryBinding5.clTransactionHistory.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding6 = this.q;
                    if (activityRegisteredProductSummaryBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityRegisteredProductSummaryBinding2 = activityRegisteredProductSummaryBinding6;
                    }
                    Button button = activityRegisteredProductSummaryBinding2.btnActiveCard;
                    if (button != null) {
                        button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.g
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityRegisteredProductSummary.S(ActivityRegisteredProductSummary.this, regStrapBeanInfo2, view);
                            }
                        });
                    }
                }

                public final void T() {
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding = this.q;
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding2 = null;
                    if (activityRegisteredProductSummaryBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding = null;
                    }
                    activityRegisteredProductSummaryBinding.btnActiveCard.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding3 = this.q;
                    if (activityRegisteredProductSummaryBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding3 = null;
                    }
                    activityRegisteredProductSummaryBinding3.btnResumeCard.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding4 = this.q;
                    if (activityRegisteredProductSummaryBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding4 = null;
                    }
                    activityRegisteredProductSummaryBinding4.btnSuspendCard.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding5 = this.q;
                    if (activityRegisteredProductSummaryBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityRegisteredProductSummaryBinding2 = activityRegisteredProductSummaryBinding5;
                    }
                    activityRegisteredProductSummaryBinding2.clTransactionHistory.setVisibility(8);
                }

                public final void U() {
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding = this.q;
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding2 = null;
                    if (activityRegisteredProductSummaryBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding = null;
                    }
                    activityRegisteredProductSummaryBinding.btnActiveCard.setVisibility(0);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding3 = this.q;
                    if (activityRegisteredProductSummaryBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding3 = null;
                    }
                    activityRegisteredProductSummaryBinding3.btnResumeCard.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding4 = this.q;
                    if (activityRegisteredProductSummaryBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding4 = null;
                    }
                    activityRegisteredProductSummaryBinding4.btnSuspendCard.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding5 = this.q;
                    if (activityRegisteredProductSummaryBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding5 = null;
                    }
                    activityRegisteredProductSummaryBinding5.clTransactionHistory.setVisibility(8);
                    RegStrapBeanInfo regStrapBeanInfo = this.u.get(this.x);
                    Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "registeredStrapInfoList[selectedItemPosition]");
                    final RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding6 = this.q;
                    if (activityRegisteredProductSummaryBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityRegisteredProductSummaryBinding2 = activityRegisteredProductSummaryBinding6;
                    }
                    Button button = activityRegisteredProductSummaryBinding2.btnActiveCard;
                    if (button != null) {
                        button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.i
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityRegisteredProductSummary.V(RegStrapBeanInfo.this, this, view);
                            }
                        });
                    }
                }

                public final void W() {
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding = this.q;
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding2 = null;
                    if (activityRegisteredProductSummaryBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding = null;
                    }
                    activityRegisteredProductSummaryBinding.btnActiveCard.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding3 = this.q;
                    if (activityRegisteredProductSummaryBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding3 = null;
                    }
                    activityRegisteredProductSummaryBinding3.btnResumeCard.setVisibility(8);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding4 = this.q;
                    if (activityRegisteredProductSummaryBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding4 = null;
                    }
                    activityRegisteredProductSummaryBinding4.btnSuspendCard.setVisibility(0);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding5 = this.q;
                    if (activityRegisteredProductSummaryBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding5 = null;
                    }
                    activityRegisteredProductSummaryBinding5.clTransactionHistory.setVisibility(0);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding6 = this.q;
                    if (activityRegisteredProductSummaryBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding6 = null;
                    }
                    activityRegisteredProductSummaryBinding6.tvNoTransaction.setVisibility(0);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding7 = this.q;
                    if (activityRegisteredProductSummaryBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityRegisteredProductSummaryBinding2 = activityRegisteredProductSummaryBinding7;
                    }
                    activityRegisteredProductSummaryBinding2.btnSuspendCard.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.w
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityRegisteredProductSummary.X(ActivityRegisteredProductSummary.this, view);
                        }
                    });
                    A0();
                }

                public final void Y(String str, String str2) {
                    NfcUtility.Companion companion = NfcUtility.Companion;
                    if (companion.hasNFC(this)) {
                        if (companion.isNFCOn(this)) {
                            RegStrapBeanInfo regStrapBeanInfo = this.u.get(this.x);
                            Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "registeredStrapInfoList[selectedItemPosition]");
                            RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
                            Intent intent = new Intent(this, TermsAndConditionsActivity.class);
                            intent.putExtra(Constants.END_USER_GLOBAL_ID, regStrapBeanInfo2.getEndUserID());
                            intent.putExtra(Constants.END_USER_PRODUCT_REGISTRATION_ID, regStrapBeanInfo2.getEndUserProductRegistrationID());
                            intent.putExtra(Constants.TERMS_AND_CONDITIONS_TEXT, str);
                            RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo();
                            intent.putExtra(Constants.TERMS_AND_CONDITIONS_ID, regCardBeanInfo != null ? regCardBeanInfo.getTermsAndConditionId() : null);
                            intent.putExtra("deviceId", regStrapBeanInfo2.getDeviceId());
                            RegCardBeanInfo regCardBeanInfo2 = regStrapBeanInfo2.getRegCardBeanInfo();
                            intent.putExtra(Constants.PAYMENT_INSTRUMENT_TOKEN_ID, regCardBeanInfo2 != null ? regCardBeanInfo2.getPaymentInstrumentTokenId() : null);
                            intent.putExtra(Constants.CVV, str2);
                            intent.putExtra(Constants.SERIAL_NUMBER, regStrapBeanInfo2.getProductSerialNumber());
                            RegCardBeanInfo regCardBeanInfo3 = regStrapBeanInfo2.getRegCardBeanInfo();
                            intent.putExtra(Constants.PAYMENT_NETWORK_ID, regCardBeanInfo3 != null ? regCardBeanInfo3.getPaymentNetworkId() : null);
                            RegCardBeanInfo regCardBeanInfo4 = regStrapBeanInfo2.getRegCardBeanInfo();
                            intent.putExtra(Constants.LAST4, regCardBeanInfo4 != null ? regCardBeanInfo4.getLast4() : null);
                            startActivity(intent);
                            return;
                        }
                        u0();
                        return;
                    }
                    String string = getString(R.string.nfc_not_supported);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.nfc_not_supported)");
                    showErrorDialog(string);
                }

                public final void Z(CleverTapConstants.EventName eventName) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    RegStrapBeanInfo regStrapBeanInfo = this.u.get(this.x);
                    Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "registeredStrapInfoList[selectedItemPosition]");
                    RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
                    hashMap.put(TappyCleverTapConstants.REQUEST_LOCATION.getValue(), TappyCleverTapConstants.BOATPAY_HP.getValue());
                    RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo();
                    Intrinsics.checkNotNull(regCardBeanInfo);
                    if (regCardBeanInfo.getLast4() != null) {
                        RegCardBeanInfo regCardBeanInfo2 = regStrapBeanInfo2.getRegCardBeanInfo();
                        Intrinsics.checkNotNull(regCardBeanInfo2);
                        String last4 = regCardBeanInfo2.getLast4();
                        if (last4 != null) {
                            hashMap.put(TappyCleverTapConstants.CARD_NOS.getValue(), last4);
                        }
                        String value = TappyCleverTapConstants.CARD_NOS.getValue();
                        StringBuilder sb = new StringBuilder();
                        sb.append("XXX ");
                        RegCardBeanInfo regCardBeanInfo3 = regStrapBeanInfo2.getRegCardBeanInfo();
                        Intrinsics.checkNotNull(regCardBeanInfo3);
                        sb.append(regCardBeanInfo3.getLast4());
                        hashMap.put(value, sb.toString());
                    }
                    String friendlyName = regStrapBeanInfo2.getFriendlyName();
                    boolean z = false;
                    if (!(friendlyName == null || friendlyName.length() == 0)) {
                        String value2 = TappyCleverTapConstants.CARD_LINKED_WITH.getValue();
                        String friendlyName2 = regStrapBeanInfo2.getFriendlyName();
                        Intrinsics.checkNotNull(friendlyName2);
                        hashMap.put(value2, friendlyName2);
                    }
                    RegCardBeanInfo regCardBeanInfo4 = regStrapBeanInfo2.getRegCardBeanInfo();
                    Intrinsics.checkNotNull(regCardBeanInfo4);
                    String paymentNetworkName = regCardBeanInfo4.getPaymentNetworkName();
                    if (paymentNetworkName == null || paymentNetworkName.length() == 0) {
                        z = true;
                    }
                    if (!z) {
                        RegCardBeanInfo regCardBeanInfo5 = regStrapBeanInfo2.getRegCardBeanInfo();
                        Intrinsics.checkNotNull(regCardBeanInfo5);
                        String paymentNetworkName2 = regCardBeanInfo5.getPaymentNetworkName();
                        Intrinsics.checkNotNull(paymentNetworkName2);
                        if (Intrinsics.areEqual(paymentNetworkName2, Constants.MASTERCARD)) {
                            hashMap.put(TappyCleverTapConstants.CARD_TYPE.getValue(), TappyCleverTapConstants.MASTER.getValue());
                        } else {
                            hashMap.put(TappyCleverTapConstants.CARD_TYPE.getValue(), TappyCleverTapConstants.VISA.getValue());
                        }
                    }
                    hashMap.put(TappyCleverTapConstants.INITIATED_BY.getValue(), TappyCleverTapConstants.USER.getValue());
                    DeviceUtils.Companion.logAnalyticsEvent(eventName.getValue(), hashMap);
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

                public final void a0() {
                    NfcStrapViewModel nfcStrapViewModel = this.t;
                    if (nfcStrapViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        nfcStrapViewModel = null;
                    }
                    MutableLiveData<String> termsAndConditionsTextLiveData = nfcStrapViewModel.getTermsAndConditionsTextLiveData();
                    final d dVar = new d();
                    termsAndConditionsTextLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.n
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            ActivityRegisteredProductSummary.b0(Function1.this, obj);
                        }
                    });
                }

                @Nullable
                public final TransactionHistoryAdapter getAdapter() {
                    return this.z;
                }

                @Nullable
                public final CardActionConfirmationDialog getCardActionConfirmationDialog() {
                    return this.J;
                }

                public final long getDeviceId() {
                    return this.s;
                }

                @Nullable
                public final ErrorDialog getErrorDialog() {
                    return this.E;
                }

                public final long getGlobalUserId() {
                    return this.r;
                }

                @NotNull
                public final String getTAG() {
                    return this.p;
                }

                public final void k0() {
                    ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.boat_pay));
                    ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.u
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityRegisteredProductSummary.l0(ActivityRegisteredProductSummary.this, view);
                        }
                    });
                }

                public final void m0(final Action action) {
                    CardActionConfirmationDialog cardActionConfirmationDialog;
                    CardActionConfirmationDialog cardActionConfirmationDialog2 = this.J;
                    if (cardActionConfirmationDialog2 != null) {
                        Boolean valueOf = cardActionConfirmationDialog2 != null ? Boolean.valueOf(cardActionConfirmationDialog2.isShowing()) : null;
                        Intrinsics.checkNotNull(valueOf);
                        if (valueOf.booleanValue() && (cardActionConfirmationDialog = this.J) != null) {
                            cardActionConfirmationDialog.dismiss();
                        }
                        this.J = null;
                    }
                    if (this.J == null) {
                        CardActionConfirmationDialog cardActionConfirmationDialog3 = new CardActionConfirmationDialog(this);
                        this.J = cardActionConfirmationDialog3;
                        Intrinsics.checkNotNull(cardActionConfirmationDialog3);
                        cardActionConfirmationDialog3.setCancelable(false);
                        CardActionConfirmationDialog cardActionConfirmationDialog4 = this.J;
                        Intrinsics.checkNotNull(cardActionConfirmationDialog4);
                        TextView textView = (TextView) cardActionConfirmationDialog4.findViewById(R.id.tv_DeregisterStrap);
                        CardActionConfirmationDialog cardActionConfirmationDialog5 = this.J;
                        Intrinsics.checkNotNull(cardActionConfirmationDialog5);
                        TextView textView2 = (TextView) cardActionConfirmationDialog5.findViewById(R.id.tvByProceeding);
                        CardActionConfirmationDialog cardActionConfirmationDialog6 = this.J;
                        Intrinsics.checkNotNull(cardActionConfirmationDialog6);
                        TextView textView3 = (TextView) cardActionConfirmationDialog6.findViewById(R.id.tvAreYouSure);
                        int i2 = WhenMappings.$EnumSwitchMapping$0[action.ordinal()];
                        if (i2 == 1) {
                            textView.setText(getString(R.string.suspend_card_q));
                            textView2.setText(getString(R.string.proceeding_this_strap_will_be_suspended));
                            textView3.setText(getString(R.string.are_you_sure_you_want_to_suspend_card));
                        } else if (i2 == 2) {
                            textView.setText(getString(R.string.resume_card_q));
                            textView2.setText(getString(R.string.proceeding_this_strap_will_be_resumed));
                            textView3.setText(getString(R.string.are_you_sure_you_want_to_resume_card));
                        }
                        CardActionConfirmationDialog cardActionConfirmationDialog7 = this.J;
                        Intrinsics.checkNotNull(cardActionConfirmationDialog7);
                        CardActionConfirmationDialog cardActionConfirmationDialog8 = this.J;
                        Intrinsics.checkNotNull(cardActionConfirmationDialog8);
                        ((TextView) cardActionConfirmationDialog7.findViewById(R.id.btn_yes)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.h
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityRegisteredProductSummary.n0(ActivityRegisteredProductSummary.this, action, view);
                            }
                        });
                        ((TextView) cardActionConfirmationDialog8.findViewById(R.id.btn_No)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.v
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityRegisteredProductSummary.o0(ActivityRegisteredProductSummary.this, view);
                            }
                        });
                        CardActionConfirmationDialog cardActionConfirmationDialog9 = this.J;
                        Intrinsics.checkNotNull(cardActionConfirmationDialog9);
                        Window window = cardActionConfirmationDialog9.getWindow();
                        Intrinsics.checkNotNull(window);
                        window.setBackgroundDrawable(new ColorDrawable(0));
                    }
                    CardActionConfirmationDialog cardActionConfirmationDialog10 = this.J;
                    Boolean valueOf2 = cardActionConfirmationDialog10 != null ? Boolean.valueOf(cardActionConfirmationDialog10.isShowing()) : null;
                    Intrinsics.checkNotNull(valueOf2);
                    if (valueOf2.booleanValue()) {
                        return;
                    }
                    CardActionConfirmationDialog cardActionConfirmationDialog11 = this.J;
                    Intrinsics.checkNotNull(cardActionConfirmationDialog11);
                    cardActionConfirmationDialog11.show();
                    CardActionConfirmationDialog cardActionConfirmationDialog12 = this.J;
                    Intrinsics.checkNotNull(cardActionConfirmationDialog12);
                    Window window2 = cardActionConfirmationDialog12.getWindow();
                    if (window2 != null) {
                        window2.setLayout(-1, -2);
                    }
                    if (window2 != null) {
                        window2.setGravity(17);
                    }
                }

                @Override // androidx.activity.ComponentActivity, android.app.Activity
                public void onBackPressed() {
                    super.onBackPressed();
                }

                @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
                public void onCreate(@Nullable Bundle bundle) {
                    super.onCreate(bundle);
                    ActivityRegisteredProductSummaryBinding inflate = ActivityRegisteredProductSummaryBinding.inflate(getLayoutInflater());
                    Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
                    this.q = inflate;
                    ManageViewModel manageViewModel = null;
                    if (inflate == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        inflate = null;
                    }
                    setContentView(inflate.getRoot());
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding = this.q;
                    if (activityRegisteredProductSummaryBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding = null;
                    }
                    this.B = activityRegisteredProductSummaryBinding.rvTransactionDetails;
                    this.t = (NfcStrapViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(NfcStrapViewModel.class);
                    ManageViewModel manageViewModel2 = (ManageViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(ManageViewModel.class);
                    this.y = manageViewModel2;
                    if (manageViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                        manageViewModel2 = null;
                    }
                    manageViewModel2.setMListener(this);
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding2 = this.q;
                    if (activityRegisteredProductSummaryBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding2 = null;
                    }
                    activityRegisteredProductSummaryBinding2.settingsLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.a0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityRegisteredProductSummary.c0(ActivityRegisteredProductSummary.this, view);
                        }
                    });
                    k0();
                    NfcStrapViewModel nfcStrapViewModel = this.t;
                    if (nfcStrapViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        nfcStrapViewModel = null;
                    }
                    MutableLiveData<List<DeviceInfo>> getAllRegisteredDevicesLiveData = nfcStrapViewModel.getGetAllRegisteredDevicesLiveData();
                    final e eVar = new e();
                    getAllRegisteredDevicesLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.o
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            ActivityRegisteredProductSummary.d0(Function1.this, obj);
                        }
                    });
                    NfcStrapViewModel nfcStrapViewModel2 = this.t;
                    if (nfcStrapViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        nfcStrapViewModel2 = null;
                    }
                    MutableLiveData<DeviceInfo> registerNewDeviceLiveData = nfcStrapViewModel2.getRegisterNewDeviceLiveData();
                    final f fVar = new f();
                    registerNewDeviceLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.m
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            ActivityRegisteredProductSummary.e0(Function1.this, obj);
                        }
                    });
                    NfcStrapViewModel nfcStrapViewModel3 = this.t;
                    if (nfcStrapViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        nfcStrapViewModel3 = null;
                    }
                    MutableLiveData<List<RegisteredProductInfo>> registerProductsByUserIdLiveData = nfcStrapViewModel3.getRegisterProductsByUserIdLiveData();
                    final g gVar = new g();
                    registerProductsByUserIdLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.r
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            ActivityRegisteredProductSummary.f0(Function1.this, obj);
                        }
                    });
                    NfcStrapViewModel nfcStrapViewModel4 = this.t;
                    if (nfcStrapViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        nfcStrapViewModel4 = null;
                    }
                    MutableLiveData<UserDetails> userDetailLiveData = nfcStrapViewModel4.getUserDetailLiveData();
                    final h hVar = new h();
                    userDetailLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.k
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            ActivityRegisteredProductSummary.g0(Function1.this, obj);
                        }
                    });
                    ActivityRegisteredProductSummaryBinding activityRegisteredProductSummaryBinding3 = this.q;
                    if (activityRegisteredProductSummaryBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisteredProductSummaryBinding3 = null;
                    }
                    ViewPager viewPager = activityRegisteredProductSummaryBinding3.viewPagerRegisteredProductSummary;
                    if (viewPager != null) {
                        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary$onCreate$6
                            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                            public void onPageScrollStateChanged(int i2) {
                            }

                            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                            public void onPageScrolled(int i2, float f2, int i3) {
                            }

                            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                            public void onPageSelected(int i2) {
                                ActivityRegisteredProductSummary.this.x = i2;
                                ActivityRegisteredProductSummary.this.N();
                                ActivityRegisteredProductSummary.this.O();
                                ActivityRegisteredProductSummary.this.A = true;
                            }
                        });
                    }
                    N();
                    ManageViewModel manageViewModel3 = this.y;
                    if (manageViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                        manageViewModel3 = null;
                    }
                    MutableLiveData<GetTransactionDetailsByIdResponse> getTransactionDetailsByIdLiveData = manageViewModel3.getGetTransactionDetailsByIdLiveData();
                    final i iVar = new i();
                    getTransactionDetailsByIdLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.t
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            ActivityRegisteredProductSummary.h0(Function1.this, obj);
                        }
                    });
                    ManageViewModel manageViewModel4 = this.y;
                    if (manageViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                        manageViewModel4 = null;
                    }
                    MutableLiveData<ResumePaymentInstrumentTokenResponse> resumePaymentInstrumentTokenLiveData = manageViewModel4.getResumePaymentInstrumentTokenLiveData();
                    final j jVar = new j();
                    resumePaymentInstrumentTokenLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.s
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            ActivityRegisteredProductSummary.i0(Function1.this, obj);
                        }
                    });
                    ManageViewModel manageViewModel5 = this.y;
                    if (manageViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                    } else {
                        manageViewModel = manageViewModel5;
                    }
                    MutableLiveData<SuspendPaymentInstrumentTokenResponse> suspendPaymentInstrumentTokenLiveData = manageViewModel.getSuspendPaymentInstrumentTokenLiveData();
                    final k kVar = new k();
                    suspendPaymentInstrumentTokenLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.p
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            ActivityRegisteredProductSummary.j0(Function1.this, obj);
                        }
                    });
                }

                @Override // com.coveiot.android.theme.SuccessResultListener
                public void onError(@Nullable String str) {
                    if (str != null) {
                        showErrorDialog(str);
                    }
                }

                @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
                public void onResume() {
                    super.onResume();
                    if (AppUtils.isNetConnected(this)) {
                        refresh();
                    } else {
                        Toast.makeText(this, getString(R.string.no_internet_connection), 1).show();
                    }
                }

                @Override // com.coveiot.android.theme.SuccessResultListener
                public void onSuccess() {
                    dismissProgress();
                }

                @Override // com.coveiot.android.tappy.adapter.TransactionHistoryAdapter.ItemClickListener
                public void onViewDetail(@NotNull TransactionBeanInfo transactionBeanInfo, int i2) {
                    Intrinsics.checkNotNullParameter(transactionBeanInfo, "transactionBeanInfo");
                    if (!AppUtils.isNetConnected(this)) {
                        showNoInternetMessage();
                        return;
                    }
                    showProgress();
                    RegStrapBeanInfo regStrapBeanInfo = this.u.get(this.x);
                    Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "registeredStrapInfoList[selectedItemPosition]");
                    RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
                    LifecycleCoroutineScope lifecycleScope = LifecycleOwnerKt.getLifecycleScope(this);
                    if (lifecycleScope != null) {
                        kotlinx.coroutines.e.e(lifecycleScope, Dispatchers.getIO(), null, new l(regStrapBeanInfo2, this, transactionBeanInfo, null), 2, null);
                    }
                }

                public final void q0(final String str, final Intent intent) {
                    if (this.G == null) {
                        CVVInputDialog cVVInputDialog = new CVVInputDialog(this);
                        this.G = cVVInputDialog;
                        Intrinsics.checkNotNull(cVVInputDialog);
                        cVVInputDialog.setCancelable(false);
                        CVVInputDialog cVVInputDialog2 = this.G;
                        Intrinsics.checkNotNull(cVVInputDialog2);
                        CVVInputDialog cVVInputDialog3 = this.G;
                        Intrinsics.checkNotNull(cVVInputDialog3);
                        CVVInputDialog cVVInputDialog4 = this.G;
                        Intrinsics.checkNotNull(cVVInputDialog4);
                        final EditText editText = (EditText) cVVInputDialog4.findViewById(R.id.edittext_cvv);
                        CVVInputDialog cVVInputDialog5 = this.G;
                        Intrinsics.checkNotNull(cVVInputDialog5);
                        final TextView textView = (TextView) cVVInputDialog5.findViewById(R.id.tv_giveName);
                        ((Button) cVVInputDialog2.findViewById(R.id.btnSave)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.q
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityRegisteredProductSummary.s0(editText, this, textView, intent, str, view);
                            }
                        });
                        ((TextView) cVVInputDialog3.findViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.z
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityRegisteredProductSummary.t0(ActivityRegisteredProductSummary.this, view);
                            }
                        });
                        CVVInputDialog cVVInputDialog6 = this.G;
                        Intrinsics.checkNotNull(cVVInputDialog6);
                        Window window = cVVInputDialog6.getWindow();
                        Intrinsics.checkNotNull(window);
                        window.setBackgroundDrawable(new ColorDrawable(0));
                    }
                    CVVInputDialog cVVInputDialog7 = this.G;
                    Boolean valueOf = cVVInputDialog7 != null ? Boolean.valueOf(cVVInputDialog7.isShowing()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    if (valueOf.booleanValue()) {
                        return;
                    }
                    CVVInputDialog cVVInputDialog8 = this.G;
                    Intrinsics.checkNotNull(cVVInputDialog8);
                    cVVInputDialog8.show();
                    CVVInputDialog cVVInputDialog9 = this.G;
                    Intrinsics.checkNotNull(cVVInputDialog9);
                    Window window2 = cVVInputDialog9.getWindow();
                    if (window2 != null) {
                        window2.setLayout(-1, -2);
                    }
                    if (window2 != null) {
                        window2.setGravity(17);
                    }
                }

                public final void refresh() {
                    LoadingDialog progressDialog = getProgressDialog();
                    boolean z = false;
                    if (progressDialog != null && !progressDialog.isShowing()) {
                        z = true;
                    }
                    if (z) {
                        showProgress();
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new m(null), 2, null);
                    }
                }

                public final void setAdapter(@Nullable TransactionHistoryAdapter transactionHistoryAdapter) {
                    this.z = transactionHistoryAdapter;
                }

                public final void setCardActionConfirmationDialog(@Nullable CardActionConfirmationDialog cardActionConfirmationDialog) {
                    this.J = cardActionConfirmationDialog;
                }

                public final void setDeviceId(long j2) {
                    this.s = j2;
                }

                public final void setErrorDialog(@Nullable ErrorDialog errorDialog) {
                    this.E = errorDialog;
                }

                public final void setGlobalUserId(long j2) {
                    this.r = j2;
                }

                public final void showErrorDialog(String str) {
                    ErrorDialog errorDialog = this.E;
                    if (errorDialog == null) {
                        ErrorDialog errorDialog2 = new ErrorDialog(this);
                        this.E = errorDialog2;
                        Intrinsics.checkNotNull(errorDialog2);
                        errorDialog2.setCancelable(false);
                        ErrorDialog errorDialog3 = this.E;
                        Intrinsics.checkNotNull(errorDialog3);
                        ((TextView) errorDialog3.findViewById(R.id.tvErrorTitle)).setText(str);
                        ErrorDialog errorDialog4 = this.E;
                        Intrinsics.checkNotNull(errorDialog4);
                        ((TextView) errorDialog4.findViewById(R.id.tvErrorMsg)).setVisibility(8);
                        ErrorDialog errorDialog5 = this.E;
                        Intrinsics.checkNotNull(errorDialog5);
                        ((Button) errorDialog5.findViewById(R.id.bt_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.y
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityRegisteredProductSummary.p0(ActivityRegisteredProductSummary.this, view);
                            }
                        });
                        ErrorDialog errorDialog6 = this.E;
                        Intrinsics.checkNotNull(errorDialog6);
                        Window window = errorDialog6.getWindow();
                        Intrinsics.checkNotNull(window);
                        window.setBackgroundDrawable(new ColorDrawable(0));
                    } else {
                        Intrinsics.checkNotNull(errorDialog);
                        ((TextView) errorDialog.findViewById(R.id.tvErrorMsg)).setText(str);
                    }
                    ErrorDialog errorDialog7 = this.E;
                    Boolean valueOf = errorDialog7 != null ? Boolean.valueOf(errorDialog7.isShowing()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    if (valueOf.booleanValue()) {
                        return;
                    }
                    ErrorDialog errorDialog8 = this.E;
                    Intrinsics.checkNotNull(errorDialog8);
                    errorDialog8.show();
                }

                public final void u0() {
                    if (this.F == null) {
                        String string = getString(R.string.please_turn_on_nfc);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_turn_on_nfc)");
                        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
                        this.F = bottomSheetDialogOneButtonOneTitle;
                        String string2 = getString(R.string.ok);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
                        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.j
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityRegisteredProductSummary.v0(BottomSheetDialogOneButtonOneTitle.this, this, view);
                            }
                        });
                    }
                    BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.F;
                    Boolean valueOf = bottomSheetDialogOneButtonOneTitle2 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle2.isShowing()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    if (valueOf.booleanValue()) {
                        return;
                    }
                    BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.F;
                    Intrinsics.checkNotNull(bottomSheetDialogOneButtonOneTitle3);
                    bottomSheetDialogOneButtonOneTitle3.show();
                }

                public final void w0() {
                    if (this.I == null) {
                        ReasonInputDialog reasonInputDialog = new ReasonInputDialog(this);
                        this.I = reasonInputDialog;
                        Intrinsics.checkNotNull(reasonInputDialog);
                        reasonInputDialog.setCancelable(false);
                        ReasonInputDialog reasonInputDialog2 = this.I;
                        Intrinsics.checkNotNull(reasonInputDialog2);
                        Button button = (Button) reasonInputDialog2.findViewById(R.id.btnSave);
                        ReasonInputDialog reasonInputDialog3 = this.I;
                        Intrinsics.checkNotNull(reasonInputDialog3);
                        ReasonInputDialog reasonInputDialog4 = this.I;
                        Intrinsics.checkNotNull(reasonInputDialog4);
                        final EditText editText = (EditText) reasonInputDialog4.findViewById(R.id.edittext_reason);
                        ReasonInputDialog reasonInputDialog5 = this.I;
                        Intrinsics.checkNotNull(reasonInputDialog5);
                        final TextView textView = (TextView) reasonInputDialog5.findViewById(R.id.tv_giveName);
                        int i2 = R.string.suspend;
                        button.setText(getString(i2));
                        textView.setText(getString(R.string.give_a_reason_to) + ' ' + getString(i2));
                        button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.f
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityRegisteredProductSummary.x0(editText, this, textView, view);
                            }
                        });
                        ((TextView) reasonInputDialog3.findViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.x
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityRegisteredProductSummary.y0(ActivityRegisteredProductSummary.this, view);
                            }
                        });
                        ReasonInputDialog reasonInputDialog6 = this.I;
                        Intrinsics.checkNotNull(reasonInputDialog6);
                        Window window = reasonInputDialog6.getWindow();
                        Intrinsics.checkNotNull(window);
                        window.setBackgroundDrawable(new ColorDrawable(0));
                    }
                    ReasonInputDialog reasonInputDialog7 = this.I;
                    Boolean valueOf = reasonInputDialog7 != null ? Boolean.valueOf(reasonInputDialog7.isShowing()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    if (valueOf.booleanValue()) {
                        return;
                    }
                    ReasonInputDialog reasonInputDialog8 = this.I;
                    Intrinsics.checkNotNull(reasonInputDialog8);
                    reasonInputDialog8.show();
                    ReasonInputDialog reasonInputDialog9 = this.I;
                    Intrinsics.checkNotNull(reasonInputDialog9);
                    Window window2 = reasonInputDialog9.getWindow();
                    if (window2 != null) {
                        window2.setLayout(-1, -2);
                    }
                    if (window2 != null) {
                        window2.setGravity(17);
                    }
                }

                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                public final void z0(GetTransactionDetailsByIdResponse getTransactionDetailsByIdResponse) {
                    int i2;
                    TransactionDetailDialog transactionDetailDialog;
                    TransactionDetailDialog transactionDetailDialog2 = this.H;
                    if (transactionDetailDialog2 != null) {
                        Boolean valueOf = transactionDetailDialog2 != null ? Boolean.valueOf(transactionDetailDialog2.isShowing()) : null;
                        Intrinsics.checkNotNull(valueOf);
                        if (valueOf.booleanValue() && (transactionDetailDialog = this.H) != null) {
                            transactionDetailDialog.dismiss();
                        }
                        this.H = null;
                    }
                    if (this.H == null) {
                        TransactionDetailDialog transactionDetailDialog3 = new TransactionDetailDialog(this);
                        this.H = transactionDetailDialog3;
                        Intrinsics.checkNotNull(transactionDetailDialog3);
                        transactionDetailDialog3.setCancelable(false);
                        TransactionDetailDialog transactionDetailDialog4 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog4);
                        transactionDetailDialog4.setCanceledOnTouchOutside(true);
                        TransactionDetailDialog transactionDetailDialog5 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog5);
                        TextView textView = (TextView) transactionDetailDialog5.findViewById(R.id.tv_transactionAmount);
                        TransactionDetailDialog transactionDetailDialog6 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog6);
                        TextView textView2 = (TextView) transactionDetailDialog6.findViewById(R.id.tv_transactionAmountInWords);
                        TransactionDetailDialog transactionDetailDialog7 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog7);
                        ImageView imageView = (ImageView) transactionDetailDialog7.findViewById(R.id.iv_transactionStatus);
                        TransactionDetailDialog transactionDetailDialog8 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog8);
                        TextView textView3 = (TextView) transactionDetailDialog8.findViewById(R.id.tvPaidStatus);
                        TransactionDetailDialog transactionDetailDialog9 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog9);
                        TextView textView4 = (TextView) transactionDetailDialog9.findViewById(R.id.tv_merchantName);
                        TransactionDetailDialog transactionDetailDialog10 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog10);
                        TextView textView5 = (TextView) transactionDetailDialog10.findViewById(R.id.tvPaidFromStrap);
                        TransactionDetailDialog transactionDetailDialog11 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog11);
                        TextView textView6 = (TextView) transactionDetailDialog11.findViewById(R.id.tvLast4);
                        TransactionDetailDialog transactionDetailDialog12 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog12);
                        TextView textView7 = (TextView) transactionDetailDialog12.findViewById(R.id.tv_transactionDt);
                        TransactionDetailDialog transactionDetailDialog13 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog13);
                        TextView textView8 = (TextView) transactionDetailDialog13.findViewById(R.id.tv_merchantCity);
                        TransactionDetailDialog transactionDetailDialog14 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog14);
                        TextView textView9 = (TextView) transactionDetailDialog14.findViewById(R.id.tvPaidTime);
                        TransactionDetailDialog transactionDetailDialog15 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog15);
                        TextView textView10 = (TextView) transactionDetailDialog15.findViewById(R.id.tvTransactionId);
                        String merchantName = getTransactionDetailsByIdResponse.getMerchantName();
                        if (!(merchantName == null || merchantName.length() == 0)) {
                            textView4.setText(getTransactionDetailsByIdResponse.getMerchantName());
                        }
                        getTransactionDetailsByIdResponse.getAmount();
                        StringBuilder sb = new StringBuilder();
                        Intrinsics.checkNotNull(getTransactionDetailsByIdResponse);
                        String currency = getTransactionDetailsByIdResponse.getCurrency();
                        sb.append(currency != null ? Utils.Companion.getCurrencySymbol(currency) : null);
                        sb.append(getTransactionDetailsByIdResponse.getAmount());
                        textView.setText(sb.toString());
                        textView2.setText(EnglishNumberToWords.Companion.convert(getTransactionDetailsByIdResponse.getAmount(), getTransactionDetailsByIdResponse.getCurrency()));
                        String transactionStatus = getTransactionDetailsByIdResponse.getTransactionStatus();
                        if (!(transactionStatus == null || transactionStatus.length() == 0)) {
                            String transactionStatus2 = getTransactionDetailsByIdResponse.getTransactionStatus();
                            if (transactionStatus2 != null) {
                                switch (transactionStatus2.hashCode()) {
                                    case -644370343:
                                        if (transactionStatus2.equals("Settled")) {
                                            imageView.setImageResource(R.drawable.iv_transaction_settled);
                                            textView3.setText("Settled");
                                            break;
                                        }
                                        break;
                                    case -199856670:
                                        if (transactionStatus2.equals("Reversed")) {
                                            imageView.setImageResource(R.drawable.iv_transaction_reverse);
                                            textView3.setText("Reversed");
                                            break;
                                        }
                                        break;
                                    case 632840270:
                                        if (transactionStatus2.equals("Declined")) {
                                            imageView.setImageResource(R.drawable.iv_fail);
                                            textView3.setText(getString(R.string.failed_to_pay));
                                            break;
                                        }
                                        break;
                                    case 982065527:
                                        if (transactionStatus2.equals("Pending")) {
                                            imageView.setImageResource(R.drawable.iv_transaction_pending);
                                            textView3.setText(getString(R.string.in_progress));
                                            break;
                                        }
                                        break;
                                }
                            }
                            imageView.setImageResource(R.drawable.success_image_new_img);
                            textView3.setText(getString(R.string.paid));
                        }
                        String merchantCity = getTransactionDetailsByIdResponse.getMerchantCity();
                        if (!(merchantCity == null || merchantCity.length() == 0)) {
                            textView8.setText(getTransactionDetailsByIdResponse.getMerchantCity());
                        }
                        getTransactionDetailsByIdResponse.getTransactionDate();
                        if (getTransactionDetailsByIdResponse.getTransactionDate() > 0) {
                            textView7.setText(Utils.Companion.formatTimestamp(this, getTransactionDetailsByIdResponse.getTransactionDate(), false, true));
                            Date date = new Date();
                            date.setTime(getTransactionDetailsByIdResponse.getTransactionDate());
                            String sdf = AppUtils.formatDate(date, "hh:mm a");
                            Intrinsics.checkNotNullExpressionValue(sdf, "sdf");
                            textView9.setText(kotlin.text.m.replace$default(kotlin.text.m.replace$default(sdf, "AM", "am", false, 4, (Object) null), "PM", "pm", false, 4, (Object) null));
                        }
                        getTransactionDetailsByIdResponse.getTransactionID();
                        textView10.setText(getString(R.string.transaction_id, new Object[]{String.valueOf(getTransactionDetailsByIdResponse.getTransactionID())}));
                        if (this.u.get(this.x).getFriendlyName() != null) {
                            textView5.setText(this.u.get(this.x).getFriendlyName());
                        }
                        RegCardBeanInfo regCardBeanInfo = this.u.get(this.x).getRegCardBeanInfo();
                        Intrinsics.checkNotNull(regCardBeanInfo);
                        String last4 = regCardBeanInfo.getLast4();
                        if (last4 == null || last4.length() == 0) {
                            i2 = 0;
                        } else {
                            int i3 = R.string.last_star_4;
                            RegCardBeanInfo regCardBeanInfo2 = this.u.get(this.x).getRegCardBeanInfo();
                            Intrinsics.checkNotNull(regCardBeanInfo2);
                            i2 = 0;
                            textView6.setText(getString(i3, new Object[]{regCardBeanInfo2.getLast4()}));
                        }
                        TransactionDetailDialog transactionDetailDialog16 = this.H;
                        Intrinsics.checkNotNull(transactionDetailDialog16);
                        Window window = transactionDetailDialog16.getWindow();
                        Intrinsics.checkNotNull(window);
                        window.setBackgroundDrawable(new ColorDrawable(i2));
                    }
                    TransactionDetailDialog transactionDetailDialog17 = this.H;
                    Boolean valueOf2 = transactionDetailDialog17 != null ? Boolean.valueOf(transactionDetailDialog17.isShowing()) : null;
                    Intrinsics.checkNotNull(valueOf2);
                    if (valueOf2.booleanValue()) {
                        return;
                    }
                    TransactionDetailDialog transactionDetailDialog18 = this.H;
                    Intrinsics.checkNotNull(transactionDetailDialog18);
                    transactionDetailDialog18.show();
                    TransactionDetailDialog transactionDetailDialog19 = this.H;
                    Intrinsics.checkNotNull(transactionDetailDialog19);
                    Window window2 = transactionDetailDialog19.getWindow();
                    if (window2 != null) {
                        window2.setLayout(-1, -2);
                    }
                    if (window2 != null) {
                        window2.setGravity(17);
                    }
                }
            }
