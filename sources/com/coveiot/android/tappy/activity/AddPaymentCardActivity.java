package com.coveiot.android.tappy.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.nfc.TagLostException;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.activity.AddPaymentCardActivity;
import com.coveiot.android.tappy.customview.ErrorDialog;
import com.coveiot.android.tappy.fragment.FragmentCardDetails;
import com.coveiot.android.tappy.fragment.FragmentCardScan;
import com.coveiot.android.tappy.fragment.FragmentContactPhoneNfcStrap;
import com.coveiot.android.tappy.fragment.FragmentSupportedCardType;
import com.coveiot.android.tappy.model.DeviceInfo;
import com.coveiot.android.tappy.model.EncryptionKey;
import com.coveiot.android.tappy.model.ErrorLogInfo;
import com.coveiot.android.tappy.model.InstallFoundationData;
import com.coveiot.android.tappy.model.PaymentInstrumentData;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenData;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenResponseData;
import com.coveiot.android.tappy.model.ProductDetails;
import com.coveiot.android.tappy.model.RegisterProductResponse;
import com.coveiot.android.tappy.model.RegisteredProductInfo;
import com.coveiot.android.tappy.nfc.NfcUtility;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.tappy.utils.LocalAPDUCommands;
import com.coveiot.android.tappy.utils.Utils;
import com.coveiot.android.tappy.viewmodel.NfcStrapViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.jieli.jl_rcsp.constant.Command;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class AddPaymentCardActivity extends BaseActivity implements FragmentContactPhoneNfcStrap.OnTryAgainClickListener {
    @Nullable
    public String A;
    @Nullable
    public String B;
    public boolean C;
    @Nullable
    public String D;
    @Nullable
    public String E;
    @Nullable
    public String F;
    @Nullable
    public String G;
    @Nullable
    public String H;
    @Nullable
    public String I;
    @Nullable
    public String J;
    @Nullable
    public Long K;
    @Nullable
    public Long L;
    @Nullable
    public EncryptionKey M;
    @Nullable
    public InstallFoundationData N;
    @Nullable
    public String O;
    @Nullable
    public String P;
    public long R;
    public boolean T;
    public boolean U;
    @Nullable
    public FragmentContactPhoneNfcStrap V;
    @Nullable
    public ErrorDialog W;
    public NfcStrapViewModel p;
    public boolean q;
    @Nullable
    public Long s;
    @Nullable
    public String t;
    @Nullable
    public Long v;
    @Nullable
    public Long w;
    @Nullable
    public String x;
    @Nullable
    public String z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String r = AddPaymentCardActivity.class.getSimpleName();
    public int u = LocalAPDUCommands.PAYMENTNETWORK_MASTERCARD;
    @NotNull
    public ArrayList<String> y = new ArrayList<>();
    public int Q = 1;
    @NotNull
    public Handler S = new Handler(Looper.getMainLooper());

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$loadContactPhoneNfcStrapFragment$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(AddPaymentCardActivity addPaymentCardActivity) {
            FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap;
            FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap2 = addPaymentCardActivity.getMFragmentContactPhoneNfcStrap();
            boolean z = true;
            if (mFragmentContactPhoneNfcStrap2 == null || !mFragmentContactPhoneNfcStrap2.isAdded()) {
                z = false;
            }
            if (!z || (mFragmentContactPhoneNfcStrap = addPaymentCardActivity.getMFragmentContactPhoneNfcStrap()) == null) {
                return;
            }
            mFragmentContactPhoneNfcStrap.setCurrentState(3);
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
                AddPaymentCardActivity.this.Q = 1;
                AddPaymentCardActivity.this.q = true;
                NfcUtility.Companion.startListeningNFC(AddPaymentCardActivity.this);
                AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                addPaymentCardActivity.setMFragmentContactPhoneNfcStrap(FragmentContactPhoneNfcStrap.Companion.newInstance(1, addPaymentCardActivity));
                FragmentTransaction beginTransaction = AddPaymentCardActivity.this.getSupportFragmentManager().beginTransaction();
                int i = R.id.fragment_container;
                FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap = AddPaymentCardActivity.this.getMFragmentContactPhoneNfcStrap();
                Intrinsics.checkNotNull(mFragmentContactPhoneNfcStrap);
                beginTransaction.replace(i, mFragmentContactPhoneNfcStrap).commitAllowingStateLoss();
                Handler handler = AddPaymentCardActivity.this.S;
                final AddPaymentCardActivity addPaymentCardActivity2 = AddPaymentCardActivity.this;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.tappy.activity.y0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AddPaymentCardActivity.a.invokeSuspend$lambda$0(AddPaymentCardActivity.this);
                    }
                }, 6000L);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes7.dex */
    public static final class b extends Lambda implements Function1<List<? extends DeviceInfo>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$1$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ List<DeviceInfo> $it;
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$1$1$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.AddPaymentCardActivity$b$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0317a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ DeviceInfo $deviceInfo;
                public int label;
                public final /* synthetic */ AddPaymentCardActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0317a(AddPaymentCardActivity addPaymentCardActivity, DeviceInfo deviceInfo, Continuation<? super C0317a> continuation) {
                    super(2, continuation);
                    this.this$0 = addPaymentCardActivity;
                    this.$deviceInfo = deviceInfo;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0317a(this.this$0, this.$deviceInfo, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0317a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                        Long endUserGlobalId = this.this$0.getEndUserGlobalId();
                        Intrinsics.checkNotNull(endUserGlobalId);
                        nfcStrapViewModel.registerNewDevice(endUserGlobalId.longValue(), this.$deviceInfo);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddPaymentCardActivity addPaymentCardActivity, List<DeviceInfo> list, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
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
                                AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                                Long deviceId2 = deviceInfo.getDeviceId();
                                Intrinsics.checkNotNull(deviceId2);
                                addPaymentCardActivity.setDeviceId(deviceId2.longValue());
                            }
                        }
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new C0317a(this.this$0, deviceInfo, null), 2, null);
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
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends DeviceInfo> list) {
            invoke2((List<DeviceInfo>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<DeviceInfo> list) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new a(AddPaymentCardActivity.this, list, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class c extends Lambda implements Function1<DeviceInfo, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$2$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ DeviceInfo $it;
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddPaymentCardActivity addPaymentCardActivity, DeviceInfo deviceInfo, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
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
                            LogHelper.d(this.this$0.r, "New device registration failed.");
                            AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                            String string = addPaymentCardActivity.getString(R.string.some_error_occurred_try_again);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                            addPaymentCardActivity.showErrorDialog(string);
                        } else {
                            AddPaymentCardActivity addPaymentCardActivity2 = this.this$0;
                            Long deviceId = deviceInfo.getDeviceId();
                            Intrinsics.checkNotNull(deviceId);
                            addPaymentCardActivity2.setDeviceId(deviceId.longValue());
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DeviceInfo deviceInfo) {
            invoke2(deviceInfo);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable DeviceInfo deviceInfo) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new a(AddPaymentCardActivity.this, deviceInfo, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class d extends Lambda implements Function1<InstallFoundationData, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$3$1$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ InstallFoundationData $installFoundationData;
            private /* synthetic */ Object L$0;
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$3$1$1$1$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.AddPaymentCardActivity$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0318a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ AddPaymentCardActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0318a(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super C0318a> continuation) {
                    super(2, continuation);
                    this.this$0 = addPaymentCardActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0318a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0318a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                        InstallFoundationData mInstallFoundationData = addPaymentCardActivity.getMInstallFoundationData();
                        Intrinsics.checkNotNull(mInstallFoundationData);
                        addPaymentCardActivity.U(mInstallFoundationData);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$3$1$1$2$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ AddPaymentCardActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = addPaymentCardActivity;
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
                        AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                        String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                        addPaymentCardActivity.showErrorDialog(string);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(InstallFoundationData installFoundationData, AddPaymentCardActivity addPaymentCardActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$installFoundationData = installFoundationData;
                this.this$0 = addPaymentCardActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                a aVar = new a(this.$installFoundationData, this.this$0, continuation);
                aVar.L$0 = obj;
                return aVar;
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:7:0x002d, code lost:
                if (r11 == null) goto L10;
             */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
                /*
                    r10 = this;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                    int r0 = r10.label
                    if (r0 != 0) goto L50
                    kotlin.ResultKt.throwOnFailure(r11)
                    java.lang.Object r11 = r10.L$0
                    kotlinx.coroutines.CoroutineScope r11 = (kotlinx.coroutines.CoroutineScope) r11
                    com.coveiot.android.tappy.model.InstallFoundationData r11 = r10.$installFoundationData
                    java.util.List r11 = r11.getApduCommands()
                    r0 = 0
                    if (r11 == 0) goto L2f
                    com.coveiot.android.tappy.activity.AddPaymentCardActivity r11 = r10.this$0
                    androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r11)
                    kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
                    r3 = 0
                    com.coveiot.android.tappy.activity.AddPaymentCardActivity$d$a$a r4 = new com.coveiot.android.tappy.activity.AddPaymentCardActivity$d$a$a
                    r4.<init>(r11, r0)
                    r5 = 2
                    r6 = 0
                    kotlinx.coroutines.Job r11 = kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
                    if (r11 != 0) goto L4d
                L2f:
                    com.coveiot.android.tappy.activity.AddPaymentCardActivity r11 = r10.this$0
                    java.lang.String r1 = "InstallFoundation"
                    java.lang.String r2 = "APDU commands not found."
                    java.lang.String r3 = "Critical"
                    r11.logError(r1, r2, r3)
                    androidx.lifecycle.LifecycleCoroutineScope r4 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r11)
                    kotlinx.coroutines.MainCoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getMain()
                    r6 = 0
                    com.coveiot.android.tappy.activity.AddPaymentCardActivity$d$a$b r7 = new com.coveiot.android.tappy.activity.AddPaymentCardActivity$d$a$b
                    r7.<init>(r11, r0)
                    r8 = 2
                    r9 = 0
                    kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)
                L4d:
                    kotlin.Unit r11 = kotlin.Unit.INSTANCE
                    return r11
                L50:
                    java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r11.<init>(r0)
                    throw r11
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.AddPaymentCardActivity.d.a.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$3$2$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ AddPaymentCardActivity $this_run;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super b> continuation) {
                super(2, continuation);
                this.$this_run = addPaymentCardActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.$this_run, continuation);
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
                    AddPaymentCardActivity addPaymentCardActivity = this.$this_run;
                    String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                    addPaymentCardActivity.showErrorDialog(string);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(InstallFoundationData installFoundationData) {
            invoke2(installFoundationData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable InstallFoundationData installFoundationData) {
            Job e;
            AddPaymentCardActivity.this.Q = 3;
            if (installFoundationData != null) {
                AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                LogHelper.d(addPaymentCardActivity.r, "Install foundation received.");
                addPaymentCardActivity.setMInstallFoundationData(installFoundationData);
                e = kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(addPaymentCardActivity), Dispatchers.getIO(), null, new a(installFoundationData, addPaymentCardActivity, null), 2, null);
                if (e != null) {
                    return;
                }
            }
            AddPaymentCardActivity addPaymentCardActivity2 = AddPaymentCardActivity.this;
            LogHelper.d(addPaymentCardActivity2.r, "No install foundation data received.");
            addPaymentCardActivity2.logError("InstallFoundation", "Install found API request fails.", "Critical");
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(addPaymentCardActivity2), Dispatchers.getMain(), null, new b(addPaymentCardActivity2, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class e extends Lambda implements Function1<PaymentInstrumentTokenResponseData, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$4$1", f = "AddPaymentCardActivity.kt", i = {}, l = {Command.CMD_SET_DEVICE_STORAGE}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ PaymentInstrumentTokenResponseData $paymentInstrumentTokenResponseData;
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$4$1$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.AddPaymentCardActivity$e$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0319a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ AddPaymentCardActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0319a(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super C0319a> continuation) {
                    super(2, continuation);
                    this.this$0 = addPaymentCardActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0319a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0319a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                        String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                        addPaymentCardActivity.showErrorDialog(string);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$4$1$2", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ AddPaymentCardActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = addPaymentCardActivity;
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
                        AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                        String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                        addPaymentCardActivity.showErrorDialog(string);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$4$1$3", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ AddPaymentCardActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public c(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super c> continuation) {
                    super(2, continuation);
                    this.this$0 = addPaymentCardActivity;
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
                        AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                        String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                        addPaymentCardActivity.showErrorDialog(string);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(PaymentInstrumentTokenResponseData paymentInstrumentTokenResponseData, AddPaymentCardActivity addPaymentCardActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$paymentInstrumentTokenResponseData = paymentInstrumentTokenResponseData;
                this.this$0 = addPaymentCardActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$paymentInstrumentTokenResponseData, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                PaymentInstrumentTokenData paymentInstrumentTokenData;
                PaymentInstrumentTokenData paymentInstrumentTokenData2;
                PaymentInstrumentData paymentInstrument;
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        PaymentInstrumentTokenResponseData paymentInstrumentTokenResponseData = this.$paymentInstrumentTokenResponseData;
                        if ((paymentInstrumentTokenResponseData != null ? paymentInstrumentTokenResponseData.getPaymentInstrumentTokenData() : null) == null) {
                            LogHelper.e(this.this$0.r, "No Payment Instrument Token response data received.");
                            this.this$0.logError("InstallFoundation", "No Payment Instrument Token response data received.", "Critical");
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new c(this.this$0, null), 2, null);
                        } else {
                            AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                            PaymentInstrumentTokenData paymentInstrumentTokenData3 = this.$paymentInstrumentTokenResponseData.getPaymentInstrumentTokenData();
                            Intrinsics.checkNotNull(paymentInstrumentTokenData3);
                            addPaymentCardActivity.setPaymentInstrumentTokenId(paymentInstrumentTokenData3.getPaymentInstrumentTokenID());
                            AddPaymentCardActivity addPaymentCardActivity2 = this.this$0;
                            PaymentInstrumentTokenResponseData paymentInstrumentTokenResponseData2 = this.$paymentInstrumentTokenResponseData;
                            addPaymentCardActivity2.setCardArtImageUrl((paymentInstrumentTokenResponseData2 == null || (paymentInstrumentTokenData2 = paymentInstrumentTokenResponseData2.getPaymentInstrumentTokenData()) == null || (paymentInstrument = paymentInstrumentTokenData2.getPaymentInstrument()) == null) ? null : paymentInstrument.getCardArtImageUrl());
                            AddPaymentCardActivity addPaymentCardActivity3 = this.this$0;
                            PaymentInstrumentTokenResponseData paymentInstrumentTokenResponseData3 = this.$paymentInstrumentTokenResponseData;
                            addPaymentCardActivity3.setLast4((paymentInstrumentTokenResponseData3 == null || (paymentInstrumentTokenData = paymentInstrumentTokenResponseData3.getPaymentInstrumentTokenData()) == null) ? null : paymentInstrumentTokenData.getLast4());
                            if (this.$paymentInstrumentTokenResponseData.getTermsAndConditionsFileUrl() == null) {
                                LogHelper.e(this.this$0.r, "No terms and conditions url found.");
                                this.this$0.logError("TermsAndConditions", "Terms and condition file url not found.", "Critical");
                                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new b(this.this$0, null), 2, null);
                            } else {
                                this.this$0.setTermsAndConditionsID(this.$paymentInstrumentTokenResponseData.getTermsAndConditionsID());
                                NfcStrapViewModel nfcStrapViewModel = this.this$0.p;
                                if (nfcStrapViewModel == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                    nfcStrapViewModel = null;
                                }
                                String termsAndConditionsFileUrl = this.$paymentInstrumentTokenResponseData.getTermsAndConditionsFileUrl();
                                Intrinsics.checkNotNull(termsAndConditionsFileUrl);
                                URL url = new URL(termsAndConditionsFileUrl);
                                this.label = 1;
                                if (nfcStrapViewModel.getTAndCTextFromFileUrl(url, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    this.this$0.logError("TermsAndConditions", "Terms and condition file url parsing failed.", ErrorConstants.GENERIC_ERROR);
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new C0319a(this.this$0, null), 2, null);
                }
                return Unit.INSTANCE;
            }
        }

        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PaymentInstrumentTokenResponseData paymentInstrumentTokenResponseData) {
            invoke2(paymentInstrumentTokenResponseData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable PaymentInstrumentTokenResponseData paymentInstrumentTokenResponseData) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getIO(), null, new a(paymentInstrumentTokenResponseData, AddPaymentCardActivity.this, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class f extends Lambda implements Function1<String, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$5$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
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
                    AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                    String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                    addPaymentCardActivity.showErrorDialog(string);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public f() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(String str) {
            invoke2(str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable String str) {
            if (str == null || str.length() == 0) {
                LogHelper.e(AddPaymentCardActivity.this.r, "terms and conditions text not found.");
                AddPaymentCardActivity.this.logError("TermsAndConditions", "terms and conditions text not found.", "Critical");
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new a(AddPaymentCardActivity.this, null), 2, null);
                return;
            }
            AddPaymentCardActivity.this.C(str);
        }
    }

    /* loaded from: classes7.dex */
    public static final class g extends Lambda implements Function1<EncryptionKey, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$6$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
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
                    AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                    String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                    addPaymentCardActivity.showErrorDialog(string);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$6$2", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ EncryptionKey $it;
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(AddPaymentCardActivity addPaymentCardActivity, EncryptionKey encryptionKey, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
                this.$it = encryptionKey;
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

            /* JADX WARN: Removed duplicated region for block: B:21:0x011d  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x0166  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x0180  */
            /* JADX WARN: Removed duplicated region for block: B:34:0x01c6  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r20) {
                /*
                    Method dump skipped, instructions count: 502
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.AddPaymentCardActivity.g.b.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        public g() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(EncryptionKey encryptionKey) {
            invoke2(encryptionKey);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable EncryptionKey encryptionKey) {
            FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap;
            if (encryptionKey == null) {
                LogHelper.e(AddPaymentCardActivity.this.r, "Encryption key not found.");
                AddPaymentCardActivity.this.logError("InstallFoundation", "Encryption key not found.", "Critical");
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new a(AddPaymentCardActivity.this, null), 2, null);
                return;
            }
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getIO(), null, new b(AddPaymentCardActivity.this, encryptionKey, null), 2, null);
            FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap2 = AddPaymentCardActivity.this.getMFragmentContactPhoneNfcStrap();
            boolean z = true;
            if (mFragmentContactPhoneNfcStrap2 == null || !mFragmentContactPhoneNfcStrap2.isAdded()) {
                z = false;
            }
            if (!z || (mFragmentContactPhoneNfcStrap = AddPaymentCardActivity.this.getMFragmentContactPhoneNfcStrap()) == null) {
                return;
            }
            mFragmentContactPhoneNfcStrap.setCurrentState(5);
        }
    }

    /* loaded from: classes7.dex */
    public static final class h extends Lambda implements Function1<ProductDetails, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$7$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ProductDetails $t;
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$7$1$1$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.AddPaymentCardActivity$h$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0320a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ AddPaymentCardActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0320a(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super C0320a> continuation) {
                    super(2, continuation);
                    this.this$0 = addPaymentCardActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0320a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0320a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                        Long endUserGlobalId = this.this$0.getEndUserGlobalId();
                        Intrinsics.checkNotNull(endUserGlobalId);
                        nfcStrapViewModel.getAllRegisteredProductByUserId(endUserGlobalId.longValue());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$7$1$1$2", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ AddPaymentCardActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = addPaymentCardActivity;
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
                        AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                        String string = addPaymentCardActivity.getString(R.string.user_details_not_found);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.user_details_not_found)");
                        addPaymentCardActivity.showErrorDialog(string);
                        LogHelper.d(this.this$0.r, "User details not found.");
                        this.this$0.logError("RegisterProduct", "User detail not found.", ErrorConstants.GENERIC_ERROR);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$7$1$2", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ AddPaymentCardActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public c(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super c> continuation) {
                    super(2, continuation);
                    this.this$0 = addPaymentCardActivity;
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
                        AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                        String string = addPaymentCardActivity.getString(R.string.product_details_not_found);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.product_details_not_found)");
                        addPaymentCardActivity.showErrorDialog(string);
                        LogHelper.d(this.this$0.r, "Product details not found.");
                        this.this$0.logError("RegisterProduct", "Product detail not found.", "Critical");
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddPaymentCardActivity addPaymentCardActivity, ProductDetails productDetails, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
                this.$t = productDetails;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$t, continuation);
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
                        LogHelper.i(this.this$0.r, "t value is null");
                        if (this.$t != null) {
                            String str = this.this$0.r;
                            LogHelper.i(str, "t value " + this.$t);
                            long productId = this.$t.getProductId();
                            AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                            ProductDetails productDetails = this.$t;
                            addPaymentCardActivity.s = Boxing.boxLong(productId);
                            addPaymentCardActivity.t = productDetails.getName();
                            if (addPaymentCardActivity.getEndUserGlobalId() != null) {
                                Long endUserGlobalId = addPaymentCardActivity.getEndUserGlobalId();
                                Intrinsics.checkNotNull(endUserGlobalId);
                                if (endUserGlobalId.longValue() > 0) {
                                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(addPaymentCardActivity), Dispatchers.getIO(), null, new C0320a(addPaymentCardActivity, null), 2, null);
                                }
                            }
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(addPaymentCardActivity), Dispatchers.getMain(), null, new b(addPaymentCardActivity, null), 2, null);
                        } else {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new c(this.this$0, null), 2, null);
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
        public /* bridge */ /* synthetic */ Unit invoke(ProductDetails productDetails) {
            invoke2(productDetails);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable ProductDetails productDetails) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new a(AddPaymentCardActivity.this, productDetails, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class i extends Lambda implements Function1<List<? extends RegisteredProductInfo>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$8$1$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Ref.ObjectRef<RegisteredProductInfo> $registeredProductInfo;
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddPaymentCardActivity addPaymentCardActivity, Ref.ObjectRef<RegisteredProductInfo> objectRef, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
                this.$registeredProductInfo = objectRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$registeredProductInfo, continuation);
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
                    AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                    RegisteredProductInfo registeredProductInfo = this.$registeredProductInfo.element;
                    Intrinsics.checkNotNull(registeredProductInfo);
                    addPaymentCardActivity.setEndUserProductRegistrationId(registeredProductInfo.getEndUserProductRegistrationID());
                    this.this$0.Q = 2;
                    FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap2 = this.this$0.getMFragmentContactPhoneNfcStrap();
                    boolean z = true;
                    if (mFragmentContactPhoneNfcStrap2 == null || !mFragmentContactPhoneNfcStrap2.isAdded()) {
                        z = false;
                    }
                    if (z && (mFragmentContactPhoneNfcStrap = this.this$0.getMFragmentContactPhoneNfcStrap()) != null) {
                        mFragmentContactPhoneNfcStrap.setCurrentState(2);
                    }
                    LogHelper.d(this.this$0.r, "NFC TAG CONNECTED: " + this.this$0.Q);
                    this.this$0.T();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$8$2$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ AddPaymentCardActivity $this_run;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super b> continuation) {
                super(2, continuation);
                this.$this_run = addPaymentCardActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.$this_run, continuation);
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
                    AddPaymentCardActivity addPaymentCardActivity = this.$this_run;
                    String string = addPaymentCardActivity.getString(R.string.something_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.something_went_wrong)");
                    addPaymentCardActivity.showErrorDialog(string);
                    LogHelper.d(this.$this_run.r, "Serial number found null.");
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public i() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends RegisteredProductInfo> list) {
            invoke2((List<RegisteredProductInfo>) list);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference failed for: r11v11, types: [com.coveiot.android.tappy.model.RegisteredProductInfo, T] */
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<RegisteredProductInfo> list) {
            Object e;
            if (AddPaymentCardActivity.this.isFinishing()) {
                return;
            }
            String serialNumber = AddPaymentCardActivity.this.getSerialNumber();
            if (serialNumber != null) {
                AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (!(list == null || list.isEmpty())) {
                    objectRef.element = Utils.Companion.getRegisteredProductDetail(list, serialNumber);
                }
                if (objectRef.element == 0) {
                    NfcStrapViewModel nfcStrapViewModel = addPaymentCardActivity.p;
                    if (nfcStrapViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        nfcStrapViewModel = null;
                    }
                    Long endUserGlobalId = addPaymentCardActivity.getEndUserGlobalId();
                    Intrinsics.checkNotNull(endUserGlobalId);
                    long longValue = endUserGlobalId.longValue();
                    Long l = addPaymentCardActivity.s;
                    Intrinsics.checkNotNull(l);
                    nfcStrapViewModel.registerNewProduct(longValue, l.longValue(), serialNumber, null);
                    e = Unit.INSTANCE;
                } else {
                    LogHelper.d(addPaymentCardActivity.r, "Product is already registered. Moving for install foundation.");
                    e = kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(addPaymentCardActivity), Dispatchers.getMain(), null, new a(addPaymentCardActivity, objectRef, null), 2, null);
                }
                if (e != null) {
                    return;
                }
            }
            AddPaymentCardActivity addPaymentCardActivity2 = AddPaymentCardActivity.this;
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(addPaymentCardActivity2), Dispatchers.getMain(), null, new b(addPaymentCardActivity2, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class j extends Lambda implements Function1<RegisterProductResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onCreate$9$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ RegisterProductResponse $t;
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddPaymentCardActivity addPaymentCardActivity, RegisterProductResponse registerProductResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
                this.$t = registerProductResponse;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$t, continuation);
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
                FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap2;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (!this.this$0.isFinishing()) {
                        boolean z = false;
                        if (this.$t.getRegisteredProductInfo() == null) {
                            LogHelper.e(this.this$0.r, "Product registration failed.");
                            String string = this.this$0.getString(R.string.product_registration_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.product_registration_failed)");
                            String errorMessage = this.$t.getErrorMessage();
                            if (errorMessage == null || errorMessage.length() == 0) {
                                z = true;
                            }
                            if (!z) {
                                string = this.$t.getErrorMessage();
                                Intrinsics.checkNotNull(string);
                            }
                            this.this$0.showErrorDialog(string);
                            this.this$0.logError("RegisterProduct", "Product registration failed.", "Critical");
                        } else {
                            LogHelper.d(this.this$0.r, "Product registered successfully.");
                            AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                            RegisteredProductInfo registeredProductInfo = this.$t.getRegisteredProductInfo();
                            addPaymentCardActivity.setEndUserProductRegistrationId(registeredProductInfo != null ? registeredProductInfo.getEndUserProductRegistrationID() : null);
                            this.this$0.Q = 2;
                            FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap3 = this.this$0.getMFragmentContactPhoneNfcStrap();
                            if ((mFragmentContactPhoneNfcStrap3 != null && mFragmentContactPhoneNfcStrap3.isAdded()) && (mFragmentContactPhoneNfcStrap2 = this.this$0.getMFragmentContactPhoneNfcStrap()) != null) {
                                mFragmentContactPhoneNfcStrap2.setCurrentState(2);
                            }
                            FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap4 = this.this$0.getMFragmentContactPhoneNfcStrap();
                            if (mFragmentContactPhoneNfcStrap4 != null && mFragmentContactPhoneNfcStrap4.isAdded()) {
                                z = true;
                            }
                            if (z && (mFragmentContactPhoneNfcStrap = this.this$0.getMFragmentContactPhoneNfcStrap()) != null) {
                                Long l = this.this$0.s;
                                Intrinsics.checkNotNull(l);
                                long longValue = l.longValue();
                                String str = this.this$0.t;
                                Intrinsics.checkNotNull(str);
                                mFragmentContactPhoneNfcStrap.setStrapDetails(longValue, str);
                            }
                            LogHelper.d(this.this$0.r, "NFC TAG CONNECTED: " + this.this$0.Q);
                            this.this$0.T();
                        }
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
        public /* bridge */ /* synthetic */ Unit invoke(RegisterProductResponse registerProductResponse) {
            invoke2(registerProductResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(RegisterProductResponse registerProductResponse) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new a(AddPaymentCardActivity.this, registerProductResponse, null), 2, null);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onNewIntent$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class k extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Intent $intent;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onNewIntent$1$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
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
                    this.this$0.S.removeCallbacksAndMessages(null);
                    FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap2 = this.this$0.getMFragmentContactPhoneNfcStrap();
                    boolean z = true;
                    if (mFragmentContactPhoneNfcStrap2 == null || !mFragmentContactPhoneNfcStrap2.isAdded()) {
                        z = false;
                    }
                    if (z && (mFragmentContactPhoneNfcStrap = this.this$0.getMFragmentContactPhoneNfcStrap()) != null) {
                        mFragmentContactPhoneNfcStrap.setCurrentState(4);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onNewIntent$1$3", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
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
                    this.this$0.B();
                    this.this$0.finish();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onNewIntent$1$4", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super c> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
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
                    this.this$0.B();
                    this.this$0.finish();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onNewIntent$1$5", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public d(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super d> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
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
                    AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                    String string = addPaymentCardActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    addPaymentCardActivity.showErrorDialog(string);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onNewIntent$1$6", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public e(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super e> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
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
                    AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                    String string = addPaymentCardActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    addPaymentCardActivity.showErrorDialog(string);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$onNewIntent$1$7", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public f(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super f> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
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
                    AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                    String string = addPaymentCardActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    addPaymentCardActivity.showErrorDialog(string);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public k(Intent intent, Continuation<? super k> continuation) {
            super(2, continuation);
            this.$intent = intent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new k(this.$intent, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((k) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                } catch (TagLostException e2) {
                    e2.printStackTrace();
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new d(AddPaymentCardActivity.this, null), 2, null);
                } catch (IOException e3) {
                    e3.printStackTrace();
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new e(AddPaymentCardActivity.this, null), 2, null);
                } catch (Exception e4) {
                    e4.printStackTrace();
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new f(AddPaymentCardActivity.this, null), 2, null);
                }
                if (AddPaymentCardActivity.this.getEndUserGlobalId() != null) {
                    Long endUserGlobalId = AddPaymentCardActivity.this.getEndUserGlobalId();
                    Intrinsics.checkNotNull(endUserGlobalId);
                    if (endUserGlobalId.longValue() > 0) {
                        NfcUtility.Companion companion = NfcUtility.Companion;
                        boolean connect = companion.connect(this.$intent);
                        String str = AddPaymentCardActivity.this.r;
                        StringBuilder sb = new StringBuilder();
                        sb.append("NFC TAG CONNECTED 1: ");
                        boolean z = false;
                        sb.append(connect);
                        LogHelper.d(str, sb.toString());
                        if (connect) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new a(AddPaymentCardActivity.this, null), 2, null);
                            if (AddPaymentCardActivity.this.getEndUserProductRegistrationId() != null) {
                                Long endUserProductRegistrationId = AddPaymentCardActivity.this.getEndUserProductRegistrationId();
                                Intrinsics.checkNotNull(endUserProductRegistrationId);
                                if (endUserProductRegistrationId.longValue() > 0) {
                                    AddPaymentCardActivity.this.Q = 2;
                                    LogHelper.d(AddPaymentCardActivity.this.r, "NFC TAG CONNECTED 1: " + AddPaymentCardActivity.this.Q);
                                    FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap2 = AddPaymentCardActivity.this.getMFragmentContactPhoneNfcStrap();
                                    if (mFragmentContactPhoneNfcStrap2 != null && mFragmentContactPhoneNfcStrap2.isAdded()) {
                                        z = true;
                                    }
                                    if (z && (mFragmentContactPhoneNfcStrap = AddPaymentCardActivity.this.getMFragmentContactPhoneNfcStrap()) != null) {
                                        mFragmentContactPhoneNfcStrap.setCurrentState(2);
                                    }
                                    AddPaymentCardActivity.this.T();
                                }
                            }
                            AddPaymentCardActivity.this.Q = 1;
                            AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                            String selectIsdApdu = LocalAPDUCommands.selectIsdApdu();
                            Intrinsics.checkNotNullExpressionValue(selectIsdApdu, "selectIsdApdu()");
                            addPaymentCardActivity.O(selectIsdApdu);
                            AddPaymentCardActivity addPaymentCardActivity2 = AddPaymentCardActivity.this;
                            String cPLCDataApdu = LocalAPDUCommands.getCPLCDataApdu();
                            Intrinsics.checkNotNullExpressionValue(cPLCDataApdu, "getCPLCDataApdu()");
                            String P = addPaymentCardActivity2.P(cPLCDataApdu);
                            LogHelper.i(AddPaymentCardActivity.this.r, "cplcData " + P);
                            if (P == null || P.length() == 0) {
                                z = true;
                            }
                            if (z) {
                                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new b(AddPaymentCardActivity.this, null), 2, null);
                            } else {
                                AddPaymentCardActivity.this.setSerialNumber(companion.parseSerialNumber(P));
                                LogHelper.d(AddPaymentCardActivity.this.r, "SERIAL NUMBER: " + AddPaymentCardActivity.this.getSerialNumber());
                                String serialNumber = AddPaymentCardActivity.this.getSerialNumber();
                                if (serialNumber != null) {
                                    NfcStrapViewModel nfcStrapViewModel = AddPaymentCardActivity.this.p;
                                    if (nfcStrapViewModel == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                        nfcStrapViewModel = null;
                                    }
                                    nfcStrapViewModel.getProductDetailBySerialNumber(serialNumber);
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new c(AddPaymentCardActivity.this, null), 2, null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$sendApdu$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class l extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public l(Continuation<? super l> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new l(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((l) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!AddPaymentCardActivity.this.isFinishing()) {
                    AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                    String string = addPaymentCardActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    addPaymentCardActivity.showErrorDialog(string);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$sendApdu$2", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                if (!AddPaymentCardActivity.this.isFinishing()) {
                    AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                    String string = addPaymentCardActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    addPaymentCardActivity.showErrorDialog(string);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$sendApdu$3", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class n extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public n(Continuation<? super n> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new n(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((n) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!AddPaymentCardActivity.this.isFinishing()) {
                    AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                    String string = addPaymentCardActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    addPaymentCardActivity.showErrorDialog(string);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$sendApduCopiedFromAddNfcStrap$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class o extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$sendApduCopiedFromAddNfcStrap$1$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddPaymentCardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddPaymentCardActivity addPaymentCardActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addPaymentCardActivity;
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
                        AddPaymentCardActivity addPaymentCardActivity = this.this$0;
                        String string = addPaymentCardActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                        addPaymentCardActivity.showErrorDialog(string);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public o(Continuation<? super o> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new o(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((o) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!AddPaymentCardActivity.this.isFinishing()) {
                    AddPaymentCardActivity.this.logError("RegisterProduct", "Tag lost.", "Informational");
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddPaymentCardActivity.this), Dispatchers.getMain(), null, new a(AddPaymentCardActivity.this, null), 2, null);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$sendApduCopiedFromAddNfcStrap$2", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class p extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public p(Continuation<? super p> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new p(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((p) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!AddPaymentCardActivity.this.isFinishing()) {
                    AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                    String string = addPaymentCardActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    addPaymentCardActivity.showErrorDialog(string);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$sendApduCopiedFromAddNfcStrap$3", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class q extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public q(Continuation<? super q> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new q(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((q) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!AddPaymentCardActivity.this.isFinishing()) {
                    AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                    String string = addPaymentCardActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…_nfc_strap_and_the_phone)");
                    addPaymentCardActivity.showErrorDialog(string);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes7.dex */
    public static final class r extends Lambda implements Function1<Intent, Unit> {
        public static final r INSTANCE = new r();

        public r() {
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

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$wearableDataExchangeInStep2$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class s extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public s(Continuation<? super s> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new s(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((s) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                addPaymentCardActivity.showErrorDialog(string);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$wearableDataExchangeInStep2$2", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class t extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public t(Continuation<? super t> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new t(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((t) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                addPaymentCardActivity.showErrorDialog(string);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$wearableDataExchangeInStep3$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class u extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public u(Continuation<? super u> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new u(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((u) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                addPaymentCardActivity.showErrorDialog(string);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$wearableDataExchangeInStep3$2", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class v extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public v(Continuation<? super v> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new v(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((v) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                addPaymentCardActivity.showErrorDialog(string);
                AddPaymentCardActivity.this.B();
                AddPaymentCardActivity.this.finish();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$wearableDataExchangeInStep4$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class w extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public w(Continuation<? super w> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new w(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((w) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                addPaymentCardActivity.showErrorDialog(string);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$wearableDataExchangeInStep4$2", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class x extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public x(Continuation<? super x> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new x(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((x) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                addPaymentCardActivity.showErrorDialog(string);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddPaymentCardActivity$wearableDataExchangeInStep5$1", f = "AddPaymentCardActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class y extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public y(Continuation<? super y> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new y(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((y) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AddPaymentCardActivity addPaymentCardActivity = AddPaymentCardActivity.this;
                String string = addPaymentCardActivity.getString(R.string.an_error_occured_while_adding_the_card_please_try_again);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.an_er…he_card_please_try_again)");
                addPaymentCardActivity.showErrorDialog(string);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void F(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
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

    public static final void M(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void N(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void R(AddPaymentCardActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.B();
        this$0.finish();
    }

    public static final void S(AddPaymentCardActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ErrorDialog errorDialog = this$0.W;
        Intrinsics.checkNotNull(errorDialog);
        errorDialog.dismiss();
        this$0.B();
        r rVar = r.INSTANCE;
        Intent intent = new Intent(this$0, ActivityRegisteredProductSummary.class);
        rVar.invoke((r) intent);
        this$0.startActivityForResult(intent, -1, null);
        this$0.finish();
    }

    public static /* synthetic */ void loadCardDetailsFragment$default(AddPaymentCardActivity addPaymentCardActivity, String str, String str2, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        if ((i2 & 2) != 0) {
            str2 = null;
        }
        if ((i2 & 4) != 0) {
            str3 = null;
        }
        if ((i2 & 8) != 0) {
            str4 = null;
        }
        if ((i2 & 16) != 0) {
            str5 = null;
        }
        addPaymentCardActivity.loadCardDetailsFragment(str, str2, str3, str4, str5);
    }

    public final void B() {
        LogHelper.w(this.r, "checkAndStopListening called");
        if (this.q) {
            this.q = false;
            NfcUtility.Companion.stopListeningNFC();
        }
    }

    public final void C(String str) {
        Intent intent = new Intent(this, TermsAndConditionsActivity.class);
        intent.putExtra(Constants.END_USER_GLOBAL_ID, this.v);
        intent.putExtra(Constants.END_USER_PRODUCT_REGISTRATION_ID, this.w);
        intent.putExtra(Constants.TERMS_AND_CONDITIONS_TEXT, str);
        intent.putExtra(Constants.TERMS_AND_CONDITIONS_ID, this.B);
        intent.putExtra("deviceId", this.R);
        intent.putExtra(Constants.PAYMENT_INSTRUMENT_TOKEN_ID, this.K);
        intent.putExtra(Constants.SERIAL_NUMBER, this.D);
        intent.putExtra(Constants.PAYMENT_NETWORK_ID, this.u);
        intent.putExtra(Constants.CVV, this.I);
        intent.putExtra(Constants.CARD_ART_URL, this.O);
        intent.putExtra(Constants.LAST4, this.P);
        startActivity(intent);
        B();
        finish();
    }

    public final void D() {
        String string = getString(R.string.add_card);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.add_card)");
        Q(string);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentCardScan.Companion.newInstance("", "")).commitAllowingStateLoss();
    }

    public final void E() {
        Q("");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentSupportedCardType.Companion.newInstance("", "")).commitAllowingStateLoss();
    }

    public final String O(String str) {
        String str2 = "";
        try {
            NfcUtility.Companion companion = NfcUtility.Companion;
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            if (!companion.isConnected(intent)) {
                LogHelper.e(this.r, "Connection Break!!");
            }
            if (str != null) {
                String str3 = this.r;
                LogHelper.d(str3, "\n\nSEND: " + str);
                str2 = String.valueOf(companion.transmit(str));
                String str4 = this.r;
                LogHelper.d(str4, "RECEIVED: " + str2);
                return str2;
            }
            return "";
        } catch (TagLostException e2) {
            e2.printStackTrace();
            B();
            logError("InstallFoundation", "Tag lost.", "Informational");
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new l(null), 2, null);
            return str2;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            B();
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new m(null), 2, null);
            return str2;
        } catch (Exception e4) {
            e4.printStackTrace();
            B();
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new n(null), 2, null);
            return str2;
        }
    }

    public final String P(String str) {
        String str2 = "";
        try {
            String str3 = this.r;
            LogHelper.d(str3, "\n\nSEND: " + str);
            if (str != null) {
                LogHelper.d(this.r, "Sending...");
                str2 = String.valueOf(NfcUtility.Companion.transmit(str));
                LogHelper.d(this.r, "Done...");
                String str4 = this.r;
                LogHelper.d(str4, "RECEIVED: " + str2);
                return str2;
            }
            return "";
        } catch (TagLostException e2) {
            e2.printStackTrace();
            B();
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new o(null), 2, null);
            return str2;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            B();
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new p(null), 2, null);
            return str2;
        } catch (Exception e4) {
            e4.printStackTrace();
            B();
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new q(null), 2, null);
            return str2;
        }
    }

    public final void Q(String str) {
        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        if (this.T) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(str);
        }
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.n0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddPaymentCardActivity.R(AddPaymentCardActivity.this, view);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a A[Catch: Exception -> 0x0071, TryCatch #0 {Exception -> 0x0071, blocks: (B:3:0x0001, B:5:0x001e, B:11:0x002a, B:13:0x002e, B:15:0x0036, B:16:0x0054), top: B:21:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0054 A[Catch: Exception -> 0x0071, TRY_LEAVE, TryCatch #0 {Exception -> 0x0071, blocks: (B:3:0x0001, B:5:0x001e, B:11:0x002a, B:13:0x002e, B:15:0x0036, B:16:0x0054), top: B:21:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void T() {
        /*
            r10 = this;
            r0 = 0
            java.lang.String r1 = com.coveiot.android.tappy.utils.LocalAPDUCommands.selectIsdApdu()     // Catch: java.lang.Exception -> L71
            java.lang.String r2 = "selectIsdApdu()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch: java.lang.Exception -> L71
            r10.O(r1)     // Catch: java.lang.Exception -> L71
            java.lang.String r1 = com.coveiot.android.tappy.utils.LocalAPDUCommands.initUpdateApdu()     // Catch: java.lang.Exception -> L71
            java.lang.String r2 = "initUpdateApdu()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch: java.lang.Exception -> L71
            java.lang.String r1 = r10.O(r1)     // Catch: java.lang.Exception -> L71
            r10.x = r1     // Catch: java.lang.Exception -> L71
            if (r1 == 0) goto L27
            int r1 = r1.length()     // Catch: java.lang.Exception -> L71
            if (r1 != 0) goto L25
            goto L27
        L25:
            r1 = 0
            goto L28
        L27:
            r1 = 1
        L28:
            if (r1 != 0) goto L54
            com.coveiot.android.tappy.viewmodel.NfcStrapViewModel r1 = r10.p     // Catch: java.lang.Exception -> L71
            if (r1 != 0) goto L35
            java.lang.String r1 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)     // Catch: java.lang.Exception -> L71
            r2 = r0
            goto L36
        L35:
            r2 = r1
        L36:
            java.lang.Long r1 = r10.v     // Catch: java.lang.Exception -> L71
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> L71
            long r3 = r1.longValue()     // Catch: java.lang.Exception -> L71
            java.lang.Long r1 = r10.w     // Catch: java.lang.Exception -> L71
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> L71
            long r5 = r1.longValue()     // Catch: java.lang.Exception -> L71
            int r7 = r10.u     // Catch: java.lang.Exception -> L71
            java.lang.String r8 = r10.x     // Catch: java.lang.Exception -> L71
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)     // Catch: java.lang.Exception -> L71
            r9 = 0
            r2.installFoundationToSecureElement(r3, r5, r7, r8, r9)     // Catch: java.lang.Exception -> L71
            goto L88
        L54:
            java.lang.String r1 = "InstallFoundation"
            java.lang.String r2 = "init update response is null or empty."
            java.lang.String r3 = "Critical"
            r10.logError(r1, r2, r3)     // Catch: java.lang.Exception -> L71
            androidx.lifecycle.LifecycleCoroutineScope r4 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r10)     // Catch: java.lang.Exception -> L71
            kotlinx.coroutines.MainCoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.lang.Exception -> L71
            r6 = 0
            com.coveiot.android.tappy.activity.AddPaymentCardActivity$s r7 = new com.coveiot.android.tappy.activity.AddPaymentCardActivity$s     // Catch: java.lang.Exception -> L71
            r7.<init>(r0)     // Catch: java.lang.Exception -> L71
            r8 = 2
            r9 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> L71
            goto L88
        L71:
            r1 = move-exception
            r1.printStackTrace()
            androidx.lifecycle.LifecycleCoroutineScope r2 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r10)
            kotlinx.coroutines.MainCoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getMain()
            r4 = 0
            com.coveiot.android.tappy.activity.AddPaymentCardActivity$t r5 = new com.coveiot.android.tappy.activity.AddPaymentCardActivity$t
            r5.<init>(r0)
            r6 = 2
            r7 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r2, r3, r4, r5, r6, r7)
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.AddPaymentCardActivity.T():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x002e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x000c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void U(com.coveiot.android.tappy.model.InstallFoundationData r10) {
        /*
            r9 = this;
            r0 = 0
            java.util.List r1 = r10.getApduCommands()     // Catch: java.lang.Exception -> Lcd
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> Lcd
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Exception -> Lcd
        Lc:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Exception -> Lcd
            if (r2 == 0) goto Lc3
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Exception -> Lcd
            com.coveiot.android.tappy.model.ApduCommand r2 = (com.coveiot.android.tappy.model.ApduCommand) r2     // Catch: java.lang.Exception -> Lcd
            if (r2 == 0) goto Lc
            java.lang.String r3 = r2.getApdu()     // Catch: java.lang.Exception -> Lcd
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L2b
            int r3 = r3.length()     // Catch: java.lang.Exception -> Lcd
            if (r3 != 0) goto L29
            goto L2b
        L29:
            r3 = r4
            goto L2c
        L2b:
            r3 = r5
        L2c:
            if (r3 != 0) goto Lc
            java.lang.String r3 = r9.r     // Catch: java.lang.Exception -> Lcd
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lcd
            r6.<init>()     // Catch: java.lang.Exception -> Lcd
            java.lang.String r7 = "Sending.."
            r6.append(r7)     // Catch: java.lang.Exception -> Lcd
            java.lang.String r7 = r2.getName()     // Catch: java.lang.Exception -> Lcd
            r6.append(r7)     // Catch: java.lang.Exception -> Lcd
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> Lcd
            com.coveiot.utils.utility.LogHelper.d(r3, r6)     // Catch: java.lang.Exception -> Lcd
            java.lang.String r3 = r2.getApdu()     // Catch: java.lang.Exception -> Lcd
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch: java.lang.Exception -> Lcd
            java.lang.String r3 = r9.O(r3)     // Catch: java.lang.Exception -> Lcd
            if (r3 == 0) goto L5b
            int r6 = r3.length()     // Catch: java.lang.Exception -> Lcd
            if (r6 != 0) goto L5c
        L5b:
            r4 = r5
        L5c:
            if (r4 != 0) goto Lc
            java.lang.Boolean r4 = r2.getIgnoreFailureResponse()     // Catch: java.lang.Exception -> Lcd
            java.lang.Boolean r5 = java.lang.Boolean.FALSE     // Catch: java.lang.Exception -> Lcd
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)     // Catch: java.lang.Exception -> Lcd
            if (r4 == 0) goto Lb0
            com.coveiot.android.tappy.nfc.NfcUtility$Companion r4 = com.coveiot.android.tappy.nfc.NfcUtility.Companion     // Catch: java.lang.Exception -> Lcd
            byte[] r5 = com.coveiot.android.tappy.utils.HexUtils.hex2bin(r3)     // Catch: java.lang.Exception -> Lcd
            byte[] r5 = r4.getResponseCode(r5)     // Catch: java.lang.Exception -> Lcd
            java.lang.String r5 = com.coveiot.android.tappy.utils.HexUtils.bin2hex(r5)     // Catch: java.lang.Exception -> Lcd
            byte[] r4 = r4.getSUCCESS_RESPONSE()     // Catch: java.lang.Exception -> Lcd
            java.lang.String r4 = com.coveiot.android.tappy.utils.HexUtils.bin2hex(r4)     // Catch: java.lang.Exception -> Lcd
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)     // Catch: java.lang.Exception -> Lcd
            if (r4 != 0) goto Lb0
            java.lang.String r4 = r2.getName()     // Catch: java.lang.Exception -> Lcd
            java.lang.String r5 = "Install Container"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)     // Catch: java.lang.Exception -> Lcd
            if (r4 == 0) goto L93
            goto Lb0
        L93:
            java.lang.String r10 = "InstallFoundation"
            java.lang.String r1 = "Response code is not 9000."
            java.lang.String r2 = "Error"
            r9.logError(r10, r1, r2)     // Catch: java.lang.Exception -> Lcd
            androidx.lifecycle.LifecycleCoroutineScope r3 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r9)     // Catch: java.lang.Exception -> Lcd
            kotlinx.coroutines.MainCoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.lang.Exception -> Lcd
            r5 = 0
            com.coveiot.android.tappy.activity.AddPaymentCardActivity$u r6 = new com.coveiot.android.tappy.activity.AddPaymentCardActivity$u     // Catch: java.lang.Exception -> Lcd
            r6.<init>(r0)     // Catch: java.lang.Exception -> Lcd
            r7 = 2
            r8 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> Lcd
            return
        Lb0:
            java.lang.Boolean r2 = r2.getSaveResponseData()     // Catch: java.lang.Exception -> Lcd
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch: java.lang.Exception -> Lcd
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)     // Catch: java.lang.Exception -> Lcd
            if (r2 == 0) goto Lc
            java.util.ArrayList<java.lang.String> r2 = r9.y     // Catch: java.lang.Exception -> Lcd
            r2.add(r3)     // Catch: java.lang.Exception -> Lcd
            goto Lc
        Lc3:
            r1 = 4
            r9.Q = r1     // Catch: java.lang.Exception -> Lcd
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)     // Catch: java.lang.Exception -> Lcd
            r9.V(r10)     // Catch: java.lang.Exception -> Lcd
            goto Le4
        Lcd:
            r10 = move-exception
            r10.printStackTrace()
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r9)
            kotlinx.coroutines.MainCoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getMain()
            r3 = 0
            com.coveiot.android.tappy.activity.AddPaymentCardActivity$v r4 = new com.coveiot.android.tappy.activity.AddPaymentCardActivity$v
            r4.<init>(r0)
            r5 = 2
            r6 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
        Le4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.AddPaymentCardActivity.U(com.coveiot.android.tappy.model.InstallFoundationData):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0015 A[Catch: Exception -> 0x00a1, TryCatch #0 {Exception -> 0x00a1, blocks: (B:3:0x0001, B:5:0x0009, B:11:0x0015, B:13:0x0022, B:19:0x002e, B:21:0x003f, B:25:0x0048, B:27:0x004c, B:28:0x0052, B:29:0x007a, B:31:0x0097), top: B:36:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e A[Catch: Exception -> 0x00a1, TryCatch #0 {Exception -> 0x00a1, blocks: (B:3:0x0001, B:5:0x0009, B:11:0x0015, B:13:0x0022, B:19:0x002e, B:21:0x003f, B:25:0x0048, B:27:0x004c, B:28:0x0052, B:29:0x007a, B:31:0x0097), top: B:36:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0097 A[Catch: Exception -> 0x00a1, TRY_LEAVE, TryCatch #0 {Exception -> 0x00a1, blocks: (B:3:0x0001, B:5:0x0009, B:11:0x0015, B:13:0x0022, B:19:0x002e, B:21:0x003f, B:25:0x0048, B:27:0x004c, B:28:0x0052, B:29:0x007a, B:31:0x0097), top: B:36:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void V(com.coveiot.android.tappy.model.InstallFoundationData r10) {
        /*
            r9 = this;
            r0 = 0
            java.lang.String r1 = r10.getNextSecureChannelSelectAIDCommand()     // Catch: java.lang.Exception -> La1
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L12
            int r1 = r1.length()     // Catch: java.lang.Exception -> La1
            if (r1 != 0) goto L10
            goto L12
        L10:
            r1 = r2
            goto L13
        L12:
            r1 = r3
        L13:
            if (r1 != 0) goto L97
            java.lang.String r1 = r10.getNextSecureChannelSelectAIDCommand()     // Catch: java.lang.Exception -> La1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> La1
            java.lang.String r1 = r9.O(r1)     // Catch: java.lang.Exception -> La1
            if (r1 == 0) goto L2b
            int r1 = r1.length()     // Catch: java.lang.Exception -> La1
            if (r1 != 0) goto L29
            goto L2b
        L29:
            r1 = r2
            goto L2c
        L2b:
            r1 = r3
        L2c:
            if (r1 != 0) goto Lb8
            java.lang.String r1 = com.coveiot.android.tappy.utils.LocalAPDUCommands.initUpdateApdu()     // Catch: java.lang.Exception -> La1
            java.lang.String r4 = "initUpdateApdu()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)     // Catch: java.lang.Exception -> La1
            java.lang.String r1 = r9.O(r1)     // Catch: java.lang.Exception -> La1
            r9.x = r1     // Catch: java.lang.Exception -> La1
            if (r1 == 0) goto L45
            int r1 = r1.length()     // Catch: java.lang.Exception -> La1
            if (r1 != 0) goto L46
        L45:
            r2 = r3
        L46:
            if (r2 != 0) goto L7a
            com.coveiot.android.tappy.viewmodel.NfcStrapViewModel r1 = r9.p     // Catch: java.lang.Exception -> La1
            if (r1 != 0) goto L52
            java.lang.String r1 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)     // Catch: java.lang.Exception -> La1
            r1 = r0
        L52:
            java.lang.Long r2 = r9.v     // Catch: java.lang.Exception -> La1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.Exception -> La1
            long r2 = r2.longValue()     // Catch: java.lang.Exception -> La1
            java.lang.Long r4 = r9.w     // Catch: java.lang.Exception -> La1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch: java.lang.Exception -> La1
            long r4 = r4.longValue()     // Catch: java.lang.Exception -> La1
            int r6 = r9.u     // Catch: java.lang.Exception -> La1
            java.lang.String r7 = r9.x     // Catch: java.lang.Exception -> La1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch: java.lang.Exception -> La1
            java.lang.Integer r10 = r10.getNextCommandSetIndex()     // Catch: java.lang.Exception -> La1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)     // Catch: java.lang.Exception -> La1
            int r8 = r10.intValue()     // Catch: java.lang.Exception -> La1
            r1.installFoundationToSecureElement(r2, r4, r6, r7, r8)     // Catch: java.lang.Exception -> La1
            goto Lb8
        L7a:
            java.lang.String r10 = "InstallFoundation"
            java.lang.String r1 = "Init update response is null or empty."
            java.lang.String r2 = "Error"
            r9.logError(r10, r1, r2)     // Catch: java.lang.Exception -> La1
            androidx.lifecycle.LifecycleCoroutineScope r3 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r9)     // Catch: java.lang.Exception -> La1
            kotlinx.coroutines.MainCoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.lang.Exception -> La1
            r5 = 0
            com.coveiot.android.tappy.activity.AddPaymentCardActivity$w r6 = new com.coveiot.android.tappy.activity.AddPaymentCardActivity$w     // Catch: java.lang.Exception -> La1
            r6.<init>(r0)     // Catch: java.lang.Exception -> La1
            r7 = 2
            r8 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> La1
            return
        L97:
            r1 = 5
            r9.Q = r1     // Catch: java.lang.Exception -> La1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)     // Catch: java.lang.Exception -> La1
            r9.W(r10)     // Catch: java.lang.Exception -> La1
            goto Lb8
        La1:
            r10 = move-exception
            r10.printStackTrace()
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r9)
            kotlinx.coroutines.MainCoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getMain()
            r3 = 0
            com.coveiot.android.tappy.activity.AddPaymentCardActivity$x r4 = new com.coveiot.android.tappy.activity.AddPaymentCardActivity$x
            r4.<init>(r0)
            r5 = 2
            r6 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.AddPaymentCardActivity.V(com.coveiot.android.tappy.model.InstallFoundationData):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e A[Catch: Exception -> 0x00c7, TryCatch #0 {Exception -> 0x00c7, blocks: (B:3:0x0001, B:5:0x0012, B:11:0x001e, B:13:0x002f, B:19:0x003b, B:21:0x0071, B:27:0x007d, B:29:0x008e, B:33:0x0097, B:35:0x009b, B:36:0x00a1, B:37:0x00a7, B:38:0x00af, B:39:0x00b7, B:40:0x00bf), top: B:45:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003b A[Catch: Exception -> 0x00c7, TryCatch #0 {Exception -> 0x00c7, blocks: (B:3:0x0001, B:5:0x0012, B:11:0x001e, B:13:0x002f, B:19:0x003b, B:21:0x0071, B:27:0x007d, B:29:0x008e, B:33:0x0097, B:35:0x009b, B:36:0x00a1, B:37:0x00a7, B:38:0x00af, B:39:0x00b7, B:40:0x00bf), top: B:45:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007d A[Catch: Exception -> 0x00c7, TryCatch #0 {Exception -> 0x00c7, blocks: (B:3:0x0001, B:5:0x0012, B:11:0x001e, B:13:0x002f, B:19:0x003b, B:21:0x0071, B:27:0x007d, B:29:0x008e, B:33:0x0097, B:35:0x009b, B:36:0x00a1, B:37:0x00a7, B:38:0x00af, B:39:0x00b7, B:40:0x00bf), top: B:45:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00af A[Catch: Exception -> 0x00c7, TryCatch #0 {Exception -> 0x00c7, blocks: (B:3:0x0001, B:5:0x0012, B:11:0x001e, B:13:0x002f, B:19:0x003b, B:21:0x0071, B:27:0x007d, B:29:0x008e, B:33:0x0097, B:35:0x009b, B:36:0x00a1, B:37:0x00a7, B:38:0x00af, B:39:0x00b7, B:40:0x00bf), top: B:45:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b7 A[Catch: Exception -> 0x00c7, TryCatch #0 {Exception -> 0x00c7, blocks: (B:3:0x0001, B:5:0x0012, B:11:0x001e, B:13:0x002f, B:19:0x003b, B:21:0x0071, B:27:0x007d, B:29:0x008e, B:33:0x0097, B:35:0x009b, B:36:0x00a1, B:37:0x00a7, B:38:0x00af, B:39:0x00b7, B:40:0x00bf), top: B:45:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bf A[Catch: Exception -> 0x00c7, TRY_LEAVE, TryCatch #0 {Exception -> 0x00c7, blocks: (B:3:0x0001, B:5:0x0012, B:11:0x001e, B:13:0x002f, B:19:0x003b, B:21:0x0071, B:27:0x007d, B:29:0x008e, B:33:0x0097, B:35:0x009b, B:36:0x00a1, B:37:0x00a7, B:38:0x00af, B:39:0x00b7, B:40:0x00bf), top: B:45:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void W(com.coveiot.android.tappy.model.InstallFoundationData r8) {
        /*
            r7 = this;
            r0 = 0
            java.lang.String r1 = com.coveiot.android.tappy.utils.LocalAPDUCommands.selectCASDApdu()     // Catch: java.lang.Exception -> Lc7
            java.lang.String r2 = "selectCASDApdu()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r1 = r7.O(r1)     // Catch: java.lang.Exception -> Lc7
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L1b
            int r1 = r1.length()     // Catch: java.lang.Exception -> Lc7
            if (r1 != 0) goto L19
            goto L1b
        L19:
            r1 = r2
            goto L1c
        L1b:
            r1 = r3
        L1c:
            if (r1 != 0) goto Lbf
            java.lang.String r1 = com.coveiot.android.tappy.utils.LocalAPDUCommands.getDataApdu()     // Catch: java.lang.Exception -> Lc7
            java.lang.String r4 = "getDataApdu()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r1 = r7.O(r1)     // Catch: java.lang.Exception -> Lc7
            r7.z = r1     // Catch: java.lang.Exception -> Lc7
            if (r1 == 0) goto L38
            int r1 = r1.length()     // Catch: java.lang.Exception -> Lc7
            if (r1 != 0) goto L36
            goto L38
        L36:
            r1 = r2
            goto L39
        L38:
            r1 = r3
        L39:
            if (r1 != 0) goto Lb7
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lc7
            r1.<init>()     // Catch: java.lang.Exception -> Lc7
            java.lang.String r4 = "00A40400"
            r1.append(r4)     // Catch: java.lang.Exception -> Lc7
            com.coveiot.android.tappy.utils.Utils$Companion r4 = com.coveiot.android.tappy.utils.Utils.Companion     // Catch: java.lang.Exception -> Lc7
            java.lang.String r5 = r8.getPaymentApplicationAID()     // Catch: java.lang.Exception -> Lc7
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch: java.lang.Exception -> Lc7
            int r5 = r5.length()     // Catch: java.lang.Exception -> Lc7
            int r5 = r5 / 2
            java.lang.String r4 = r4.convertToAtleastTwoDigits(r5)     // Catch: java.lang.Exception -> Lc7
            r1.append(r4)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r8 = r8.getPaymentApplicationAID()     // Catch: java.lang.Exception -> Lc7
            r1.append(r8)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r8 = r1.toString()     // Catch: java.lang.Exception -> Lc7
            java.lang.String r1 = r7.r     // Catch: java.lang.Exception -> Lc7
            com.coveiot.utils.utility.LogHelper.d(r1, r8)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r8 = r7.O(r8)     // Catch: java.lang.Exception -> Lc7
            if (r8 == 0) goto L7a
            int r8 = r8.length()     // Catch: java.lang.Exception -> Lc7
            if (r8 != 0) goto L78
            goto L7a
        L78:
            r8 = r2
            goto L7b
        L7a:
            r8 = r3
        L7b:
            if (r8 != 0) goto Laf
            java.lang.String r8 = com.coveiot.android.tappy.utils.LocalAPDUCommands.initUpdateApdu()     // Catch: java.lang.Exception -> Lc7
            java.lang.String r1 = "initUpdateApdu()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r1)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r8 = r7.O(r8)     // Catch: java.lang.Exception -> Lc7
            r7.A = r8     // Catch: java.lang.Exception -> Lc7
            if (r8 == 0) goto L94
            int r8 = r8.length()     // Catch: java.lang.Exception -> Lc7
            if (r8 != 0) goto L95
        L94:
            r2 = r3
        L95:
            if (r2 != 0) goto La7
            com.coveiot.android.tappy.viewmodel.NfcStrapViewModel r8 = r7.p     // Catch: java.lang.Exception -> Lc7
            if (r8 != 0) goto La1
            java.lang.String r8 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)     // Catch: java.lang.Exception -> Lc7
            r8 = r0
        La1:
            int r1 = r7.u     // Catch: java.lang.Exception -> Lc7
            r8.getEncryptionKey(r1)     // Catch: java.lang.Exception -> Lc7
            goto Lde
        La7:
            java.lang.String r8 = r7.r     // Catch: java.lang.Exception -> Lc7
            java.lang.String r1 = "apduResponseFromStep62 is null or empty"
            com.coveiot.utils.utility.LogHelper.d(r8, r1)     // Catch: java.lang.Exception -> Lc7
            goto Lde
        Laf:
            java.lang.String r8 = r7.r     // Catch: java.lang.Exception -> Lc7
            java.lang.String r1 = "selectPAAIDRes is null or empty"
            com.coveiot.utils.utility.LogHelper.d(r8, r1)     // Catch: java.lang.Exception -> Lc7
            goto Lde
        Lb7:
            java.lang.String r8 = r7.r     // Catch: java.lang.Exception -> Lc7
            java.lang.String r1 = "apduResponseFromStep52 is null or empty"
            com.coveiot.utils.utility.LogHelper.d(r8, r1)     // Catch: java.lang.Exception -> Lc7
            goto Lde
        Lbf:
            java.lang.String r8 = r7.r     // Catch: java.lang.Exception -> Lc7
            java.lang.String r1 = "selectCASDApduRes is null or empty"
            com.coveiot.utils.utility.LogHelper.d(r8, r1)     // Catch: java.lang.Exception -> Lc7
            goto Lde
        Lc7:
            r8 = move-exception
            r8.printStackTrace()
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r7)
            kotlinx.coroutines.MainCoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getMain()
            r3 = 0
            com.coveiot.android.tappy.activity.AddPaymentCardActivity$y r4 = new com.coveiot.android.tappy.activity.AddPaymentCardActivity$y
            r4.<init>(r0)
            r5 = 2
            r6 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
        Lde:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.AddPaymentCardActivity.W(com.coveiot.android.tappy.model.InstallFoundationData):void");
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

    @Nullable
    public final String getApduResponseFromStep52() {
        return this.z;
    }

    @Nullable
    public final String getApduResponseFromStep62() {
        return this.A;
    }

    @Nullable
    public final String getCardArtImageUrl() {
        return this.O;
    }

    @Nullable
    public final String getCardHolderName() {
        return this.F;
    }

    @Nullable
    public final String getCardNumber() {
        return this.E;
    }

    @Nullable
    public final String getCvv() {
        return this.I;
    }

    public final long getDeviceId() {
        return this.R;
    }

    @Nullable
    public final EncryptionKey getEncryptionKey() {
        return this.M;
    }

    @Nullable
    public final Long getEndUserGlobalId() {
        return this.v;
    }

    @Nullable
    public final Long getEndUserProductRegistrationId() {
        return this.w;
    }

    @Nullable
    public final ErrorDialog getErrorDialog() {
        return this.W;
    }

    @Nullable
    public final String getExpiryMonth() {
        return this.G;
    }

    @Nullable
    public final String getExpiryYear() {
        return this.H;
    }

    @Nullable
    public final String getInitUpdateResponse() {
        return this.x;
    }

    @Nullable
    public final String getLast4() {
        return this.P;
    }

    @Nullable
    public final FragmentContactPhoneNfcStrap getMFragmentContactPhoneNfcStrap() {
        return this.V;
    }

    @Nullable
    public final InstallFoundationData getMInstallFoundationData() {
        return this.N;
    }

    @Nullable
    public final Long getPaymentInstrumentId() {
        return this.L;
    }

    @Nullable
    public final Long getPaymentInstrumentTokenId() {
        return this.K;
    }

    public final int getPaymentNetworkId() {
        return this.u;
    }

    @NotNull
    public final ArrayList<String> getSaveApdusForStep7() {
        return this.y;
    }

    @Nullable
    public final String getSecurityCode() {
        return this.J;
    }

    @Nullable
    public final String getSerialNumber() {
        return this.D;
    }

    @Nullable
    public final String getTermsAndConditionsID() {
        return this.B;
    }

    public final boolean isCameraCaptured() {
        return this.C;
    }

    public final boolean isDeviceScannning() {
        return this.U;
    }

    public final void loadCardDetailsFragment(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        String string = getString(R.string.add_card);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.add_card)");
        Q(string);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentCardDetails.Companion.newInstance(str, str2, str3, str4, str5)).commitAllowingStateLoss();
    }

    public final void loadContactPhoneNfcStrapFragment() {
        String string = getString(R.string.pair_nfc_strap);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pair_nfc_strap)");
        Q(string);
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
    }

    public final void logError(@NotNull String module, @NotNull String details, @NotNull String severity) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(details, "details");
        Intrinsics.checkNotNullParameter(severity, "severity");
        ErrorLogInfo errorLogInfo = new ErrorLogInfo();
        errorLogInfo.setErrorGUID(UUID.randomUUID().toString());
        errorLogInfo.setDateUTC(AppUtils.formatDateUTC(new Date(), Constants.DATE_FORMAT));
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
        B();
        finish();
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0202  */
    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @androidx.annotation.RequiresApi(26)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate(@org.jetbrains.annotations.Nullable android.os.Bundle r8) {
        /*
            Method dump skipped, instructions count: 533
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.AddPaymentCardActivity.onCreate(android.os.Bundle):void");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        B();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        if (Intrinsics.areEqual("android.nfc.action.TECH_DISCOVERED", intent.getAction())) {
            kotlinx.coroutines.e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new k(intent, null), 3, null);
        }
    }

    @Override // com.coveiot.android.tappy.fragment.FragmentContactPhoneNfcStrap.OnTryAgainClickListener
    public void onTryAgainClicked() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentContactPhoneNfcStrap.Companion.newInstance(2, this)).commitAllowingStateLoss();
    }

    public final void setApduResponseFromStep52(@Nullable String str) {
        this.z = str;
    }

    public final void setApduResponseFromStep62(@Nullable String str) {
        this.A = str;
    }

    public final void setCameraCaptured(boolean z) {
        this.C = z;
    }

    public final void setCardArtImageUrl(@Nullable String str) {
        this.O = str;
    }

    public final void setCardHolderName(@Nullable String str) {
        this.F = str;
    }

    public final void setCardNumber(@Nullable String str) {
        this.E = str;
    }

    public final void setCvv(@Nullable String str) {
        this.I = str;
    }

    public final void setDeviceId(long j2) {
        this.R = j2;
    }

    public final void setDeviceScannning(boolean z) {
        this.U = z;
    }

    public final void setEncryptionKey(@Nullable EncryptionKey encryptionKey) {
        this.M = encryptionKey;
    }

    public final void setEndUserGlobalId(@Nullable Long l2) {
        this.v = l2;
    }

    public final void setEndUserProductRegistrationId(@Nullable Long l2) {
        this.w = l2;
    }

    public final void setErrorDialog(@Nullable ErrorDialog errorDialog) {
        this.W = errorDialog;
    }

    public final void setExpiryMonth(@Nullable String str) {
        this.G = str;
    }

    public final void setExpiryYear(@Nullable String str) {
        this.H = str;
    }

    public final void setInitUpdateResponse(@Nullable String str) {
        this.x = str;
    }

    public final void setLast4(@Nullable String str) {
        this.P = str;
    }

    public final void setMFragmentContactPhoneNfcStrap(@Nullable FragmentContactPhoneNfcStrap fragmentContactPhoneNfcStrap) {
        this.V = fragmentContactPhoneNfcStrap;
    }

    public final void setMInstallFoundationData(@Nullable InstallFoundationData installFoundationData) {
        this.N = installFoundationData;
    }

    public final void setPaymentInstrumentId(@Nullable Long l2) {
        this.L = l2;
    }

    public final void setPaymentInstrumentTokenId(@Nullable Long l2) {
        this.K = l2;
    }

    public final void setPaymentNetworkId(int i2) {
        this.u = i2;
    }

    public final void setSaveApdusForStep7(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.y = arrayList;
    }

    public final void setSecurityCode(@Nullable String str) {
        this.J = str;
    }

    public final void setSerialNumber(@Nullable String str) {
        this.D = str;
    }

    public final void setTermsAndConditionsID(@Nullable String str) {
        this.B = str;
    }

    public final void showErrorDialog(String str) {
        ErrorDialog errorDialog;
        ErrorDialog errorDialog2 = this.W;
        if (errorDialog2 != null) {
            Boolean valueOf = errorDialog2 != null ? Boolean.valueOf(errorDialog2.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (errorDialog = this.W) != null) {
                errorDialog.dismiss();
            }
            this.W = null;
        }
        ErrorDialog errorDialog3 = this.W;
        if (errorDialog3 == null) {
            ErrorDialog errorDialog4 = new ErrorDialog(this);
            this.W = errorDialog4;
            Intrinsics.checkNotNull(errorDialog4);
            errorDialog4.setCancelable(false);
            ErrorDialog errorDialog5 = this.W;
            Intrinsics.checkNotNull(errorDialog5);
            ((TextView) errorDialog5.findViewById(R.id.tvErrorTitle)).setText(getString(R.string.card_activation_failed));
            ErrorDialog errorDialog6 = this.W;
            Intrinsics.checkNotNull(errorDialog6);
            TextView textView = (TextView) errorDialog6.findViewById(R.id.tvErrorMsg);
            textView.setVisibility(0);
            textView.setText(str);
            ErrorDialog errorDialog7 = this.W;
            Intrinsics.checkNotNull(errorDialog7);
            ((Button) errorDialog7.findViewById(R.id.bt_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.p0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddPaymentCardActivity.S(AddPaymentCardActivity.this, view);
                }
            });
            ErrorDialog errorDialog8 = this.W;
            Intrinsics.checkNotNull(errorDialog8);
            Window window = errorDialog8.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        } else {
            Intrinsics.checkNotNull(errorDialog3);
            ((TextView) errorDialog3.findViewById(R.id.tvErrorTitle)).setText(str);
        }
        ErrorDialog errorDialog9 = this.W;
        Boolean valueOf2 = errorDialog9 != null ? Boolean.valueOf(errorDialog9.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue()) {
            return;
        }
        ErrorDialog errorDialog10 = this.W;
        Intrinsics.checkNotNull(errorDialog10);
        errorDialog10.show();
    }
}
