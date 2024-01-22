package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.HButtonType;
import com.coveiot.android.bleabstract.models.WatchDiagnosticFeatureType;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentRunDiagnosticTestingBinding;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.model.DiagnosticTestModel;
import com.coveiot.android.leonardo.more.adapters.DiagnosticTestingAdapter;
import com.coveiot.android.leonardo.more.models.TestingStatus;
import com.coveiot.android.leonardo.more.viewmodel.DiagnosticsTestViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleIconMessage;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class RunDiagnosticTestingFragment extends BaseFragment implements ViewModelListener {
    @Nullable
    public FragmentRunDiagnosticTestingBinding m;
    @Nullable
    public DiagnosticTestModel p;
    @Nullable
    public BottomSheetDialogOneButtonTitleIconMessage q;
    public DiagnosticsTestViewModel r;
    @Nullable
    public CountDownTimer s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final DiagnosticTestingAdapter n = new DiagnosticTestingAdapter();
    @NotNull
    public List<DiagnosticTestModel> o = new ArrayList();

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TestingStatus.values().length];
            try {
                iArr[TestingStatus.NOT_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TestingStatus.IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TestingStatus.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TestingStatus.PASSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.RunDiagnosticTestingFragment$onViewCreated$1$2$1", f = "RunDiagnosticTestingFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ DiagnosticTestModel $diagnosticTest;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(DiagnosticTestModel diagnosticTestModel, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$diagnosticTest = diagnosticTestModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$diagnosticTest, continuation);
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
                RunDiagnosticTestingFragment runDiagnosticTestingFragment = RunDiagnosticTestingFragment.this;
                DiagnosticTestModel diagnosticTest = this.$diagnosticTest;
                Intrinsics.checkNotNullExpressionValue(diagnosticTest, "diagnosticTest");
                runDiagnosticTestingFragment.T(diagnosticTest);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.RunDiagnosticTestingFragment$processNextTest$1", f = "RunDiagnosticTestingFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ TestingStatus $fromTestingStatus;
        public final /* synthetic */ boolean $isRepeat;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(boolean z, TestingStatus testingStatus, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$isRepeat = z;
            this.$fromTestingStatus = testingStatus;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$isRepeat, this.$fromTestingStatus, continuation);
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
                DiagnosticTestModel x = RunDiagnosticTestingFragment.this.x(this.$isRepeat);
                DiagnosticsTestViewModel diagnosticsTestViewModel = null;
                if (x == null) {
                    RunDiagnosticTestingFragment.this.S();
                    DiagnosticsTestViewModel diagnosticsTestViewModel2 = RunDiagnosticTestingFragment.this.r;
                    if (diagnosticsTestViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        diagnosticsTestViewModel = diagnosticsTestViewModel2;
                    }
                    Context requireContext = RunDiagnosticTestingFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    diagnosticsTestViewModel.diagnosticsEnterOrExit(requireContext, false);
                    RunDiagnosticTestingFragment.this.L();
                } else {
                    x.setShow(true);
                    RunDiagnosticTestingFragment.this.I(x.getTestName());
                    TestingStatus testingStatus = TestingStatus.IN_PROGRESS;
                    x.setTestStatus(testingStatus);
                    RunDiagnosticTestingFragment.this.p = x;
                    if (this.$fromTestingStatus != testingStatus) {
                        RunDiagnosticTestingFragment.this.S();
                        RunDiagnosticTestingFragment.this.T(x);
                    }
                    DiagnosticsTestViewModel diagnosticsTestViewModel3 = RunDiagnosticTestingFragment.this.r;
                    if (diagnosticsTestViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        diagnosticsTestViewModel = diagnosticsTestViewModel3;
                    }
                    Context requireContext2 = RunDiagnosticTestingFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    diagnosticsTestViewModel.diagnosticFeatureTest(requireContext2, x);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.RunDiagnosticTestingFragment$showCompletionDialog$1$1", f = "RunDiagnosticTestingFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
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
                DiagnosticsTestViewModel diagnosticsTestViewModel = RunDiagnosticTestingFragment.this.r;
                if (diagnosticsTestViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    diagnosticsTestViewModel = null;
                }
                Context requireContext = RunDiagnosticTestingFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                diagnosticsTestViewModel.sendDiagnosticReport(requireContext, RunDiagnosticTestingFragment.this.o);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(RunDiagnosticTestingFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            CountDownTimer countDownTimer = this$0.s;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage = this$0.q;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage);
            if (bottomSheetDialogOneButtonTitleIconMessage.isShowing()) {
                BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage2 = this$0.q;
                Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage2);
                bottomSheetDialogOneButtonTitleIconMessage2.setDownload(true);
                this$0.dismissProgress();
                BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage3 = this$0.q;
                if (bottomSheetDialogOneButtonTitleIconMessage3 != null) {
                    bottomSheetDialogOneButtonTitleIconMessage3.dismiss();
                }
                this$0.requireActivity().finish();
            }
        }
    }

    public static final void B(RunDiagnosticTestingFragment this$0, DiagnosticTestModel diagnosticTestModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getMain(), null, new a(diagnosticTestModel, null), 2, null);
        }
    }

    public static final void C(RunDiagnosticTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DiagnosticTestModel diagnosticTestModel = this$0.p;
        H(this$0, false, diagnosticTestModel != null ? diagnosticTestModel.getTestStatus() : null, 1, null);
    }

    public static final void D(FragmentRunDiagnosticTestingBinding this_apply, RunDiagnosticTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_apply.btnNo.setClickable(false);
        DiagnosticTestModel diagnosticTestModel = this$0.p;
        if (diagnosticTestModel != null) {
            this_apply.setShowClBottomButton(Boolean.FALSE);
            diagnosticTestModel.setTestStatus(TestingStatus.FAILED);
            this$0.T(diagnosticTestModel);
        }
    }

    public static final void E(FragmentRunDiagnosticTestingBinding this_apply, RunDiagnosticTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_apply.btnYes.setClickable(false);
        DiagnosticTestModel diagnosticTestModel = this$0.p;
        if (diagnosticTestModel != null) {
            this_apply.setShowClBottomButton(Boolean.FALSE);
            diagnosticTestModel.setTestStatus(TestingStatus.PASSED);
            this$0.T(diagnosticTestModel);
        }
    }

    public static final void F(FragmentRunDiagnosticTestingBinding this_apply, RunDiagnosticTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_apply.setShowButtonRepeat(Boolean.FALSE);
        this_apply.btnRepeat.setTag("btnRepeatClicked");
        DiagnosticTestModel diagnosticTestModel = this$0.p;
        if (diagnosticTestModel != null) {
            this$0.G(true, diagnosticTestModel.getTestStatus());
        }
    }

    public static /* synthetic */ void H(RunDiagnosticTestingFragment runDiagnosticTestingFragment, boolean z, TestingStatus testingStatus, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            testingStatus = null;
        }
        runDiagnosticTestingFragment.G(z, testingStatus);
    }

    public static final void M(RunDiagnosticTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showProgress(true);
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new c(null), 2, null);
    }

    public static final void N(RunDiagnosticTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage = this$0.q;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage);
        if (bottomSheetDialogOneButtonTitleIconMessage.isDownload()) {
            BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage2 = this$0.q;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage2);
            bottomSheetDialogOneButtonTitleIconMessage2.dismiss();
            this$0.requireActivity().finish();
            return;
        }
        BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage3 = this$0.q;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage3);
        bottomSheetDialogOneButtonTitleIconMessage3.turnOnCloseCheck();
    }

    public static final void O(RunDiagnosticTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage = this$0.q;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage);
        bottomSheetDialogOneButtonTitleIconMessage.setNoClick();
    }

    public static final void P(RunDiagnosticTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage = this$0.q;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage);
        bottomSheetDialogOneButtonTitleIconMessage.dismiss();
        this$0.requireActivity().finish();
    }

    public static final void R(BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage, RunDiagnosticTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.requireActivity().finish();
    }

    public static final void z(final RunDiagnosticTestingFragment this$0, Boolean isDiagnosticDownloaded) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            Intrinsics.checkNotNullExpressionValue(isDiagnosticDownloaded, "isDiagnosticDownloaded");
            if (isDiagnosticDownloaded.booleanValue()) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.fragments.h1
                    @Override // java.lang.Runnable
                    public final void run() {
                        RunDiagnosticTestingFragment.A(RunDiagnosticTestingFragment.this);
                    }
                }, 1000L);
            }
        }
    }

    public final void G(boolean z, TestingStatus testingStatus) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new b(z, testingStatus, null), 2, null);
    }

    public final void I(String str) {
        w().setHeaderData(str);
    }

    public final void J(String str) {
        w().tvInfo.setText(str);
    }

    public final void K(final FragmentRunDiagnosticTestingBinding fragmentRunDiagnosticTestingBinding) {
        CountDownTimer countDownTimer = this.s;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        fragmentRunDiagnosticTestingBinding.setShowTimer(Boolean.TRUE);
        this.s = new CountDownTimer() { // from class: com.coveiot.android.leonardo.more.fragments.RunDiagnosticTestingFragment$setTimerOf60Sec$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.RunDiagnosticTestingFragment$setTimerOf60Sec$1$onFinish$1", f = "RunDiagnosticTestingFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ FragmentRunDiagnosticTestingBinding $this_setTimerOf60Sec;
                public int label;
                public final /* synthetic */ RunDiagnosticTestingFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(FragmentRunDiagnosticTestingBinding fragmentRunDiagnosticTestingBinding, RunDiagnosticTestingFragment runDiagnosticTestingFragment, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$this_setTimerOf60Sec = fragmentRunDiagnosticTestingBinding;
                    this.this$0 = runDiagnosticTestingFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$this_setTimerOf60Sec, this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    DiagnosticTestModel diagnosticTestModel;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.$this_setTimerOf60Sec.setShowTimer(Boxing.boxBoolean(false));
                        diagnosticTestModel = this.this$0.p;
                        if (diagnosticTestModel != null) {
                            RunDiagnosticTestingFragment runDiagnosticTestingFragment = this.this$0;
                            diagnosticTestModel.setTestStatus(TestingStatus.FAILED);
                            runDiagnosticTestingFragment.T(diagnosticTestModel);
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.RunDiagnosticTestingFragment$setTimerOf60Sec$1$onTick$1", f = "RunDiagnosticTestingFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ long $millisUntilFinished;
                public final /* synthetic */ FragmentRunDiagnosticTestingBinding $this_setTimerOf60Sec;
                public int label;
                public final /* synthetic */ RunDiagnosticTestingFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(RunDiagnosticTestingFragment runDiagnosticTestingFragment, FragmentRunDiagnosticTestingBinding fragmentRunDiagnosticTestingBinding, long j, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = runDiagnosticTestingFragment;
                    this.$this_setTimerOf60Sec = fragmentRunDiagnosticTestingBinding;
                    this.$millisUntilFinished = j;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new b(this.this$0, this.$this_setTimerOf60Sec, this.$millisUntilFinished, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    String y;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        if (this.this$0.isAdded()) {
                            FragmentRunDiagnosticTestingBinding fragmentRunDiagnosticTestingBinding = this.$this_setTimerOf60Sec;
                            StringBuilder sb = new StringBuilder();
                            sb.append(this.this$0.getString(R.string.zero_zero));
                            y = this.this$0.y(this.$millisUntilFinished / 1000);
                            sb.append(y);
                            sb.append(' ');
                            sb.append(this.this$0.getString(R.string.sec));
                            fragmentRunDiagnosticTestingBinding.setTimerData(sb.toString());
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(Constants.ONE_MIN_IN_MILLIS, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (RunDiagnosticTestingFragment.this.isAdded()) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(RunDiagnosticTestingFragment.this), Dispatchers.getMain(), null, new a(fragmentRunDiagnosticTestingBinding, RunDiagnosticTestingFragment.this, null), 2, null);
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (RunDiagnosticTestingFragment.this.isAdded() && RunDiagnosticTestingFragment.this.isVisible()) {
                    LifecycleOwner viewLifecycleOwner = RunDiagnosticTestingFragment.this.getViewLifecycleOwner();
                    Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new b(RunDiagnosticTestingFragment.this, fragmentRunDiagnosticTestingBinding, j, null), 3, null);
                }
            }
        }.start();
    }

    public final void L() {
        if (this.q == null && getActivity() != null && isAdded()) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage = new BottomSheetDialogOneButtonTitleIconMessage(requireActivity);
            this.q = bottomSheetDialogOneButtonTitleIconMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage);
            bottomSheetDialogOneButtonTitleIconMessage.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.n1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RunDiagnosticTestingFragment.M(RunDiagnosticTestingFragment.this, view);
                }
            });
            BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage2 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage2);
            bottomSheetDialogOneButtonTitleIconMessage2.setCloseFunction(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.o1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RunDiagnosticTestingFragment.N(RunDiagnosticTestingFragment.this, view);
                }
            });
            BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage3 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage3);
            bottomSheetDialogOneButtonTitleIconMessage3.setNOFunction(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.m1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RunDiagnosticTestingFragment.O(RunDiagnosticTestingFragment.this, view);
                }
            });
            BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage4 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage4);
            bottomSheetDialogOneButtonTitleIconMessage4.setYESFunction(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.k1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RunDiagnosticTestingFragment.P(RunDiagnosticTestingFragment.this, view);
                }
            });
        }
        BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage5 = this.q;
        if (bottomSheetDialogOneButtonTitleIconMessage5 != null) {
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage5);
            if (bottomSheetDialogOneButtonTitleIconMessage5.isShowing()) {
                return;
            }
            BottomSheetDialogOneButtonTitleIconMessage bottomSheetDialogOneButtonTitleIconMessage6 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleIconMessage6);
            bottomSheetDialogOneButtonTitleIconMessage6.show();
        }
    }

    public final void Q() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Drawable drawable = getResources().getDrawable(2131231919);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…ble.ic_band_disconnected)");
        String string = getString(R.string.information);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.information)");
        String string2 = getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.band_not_connected)");
        final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(requireContext, drawable, string, string2, false);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.leaderboard.R.string.ok)");
        bottomSheetDialogImageTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.p1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RunDiagnosticTestingFragment.R(BottomSheetDialogImageTitleMessage.this, this, view);
            }
        });
        bottomSheetDialogImageTitleMessage.show();
    }

    public final void S() {
        this.n.setDiagnosticTestList(this.o);
        w().rvDiagnosticTesting.setAdapter(this.n);
    }

    public final void T(DiagnosticTestModel diagnosticTestModel) {
        List<Integer> childTestCodes;
        int testCode = diagnosticTestModel.getTestCode();
        if (testCode == WatchDiagnosticFeatureType.CHARGING_TEST.getId()) {
            int i = WhenMappings.$EnumSwitchMapping$0[diagnosticTestModel.getTestStatus().ordinal()];
            if (i == 2) {
                FragmentRunDiagnosticTestingBinding w = w();
                Boolean bool = Boolean.TRUE;
                w.setShowTimer(bool);
                w().setShowCharging(bool);
                return;
            } else if (i == 3 || i == 4) {
                S();
                CountDownTimer countDownTimer = this.s;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                FragmentRunDiagnosticTestingBinding w2 = w();
                Boolean bool2 = Boolean.FALSE;
                w2.setShowTimer(bool2);
                w().setShowCharging(bool2);
                String string = getString(R.string.after_removing_charger);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.after_removing_charger)");
                J(string);
                String string2 = getString(R.string.wear_watch);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.wear_watch)");
                I(string2);
                w().setShowButtonProceed(Boolean.TRUE);
                return;
            } else {
                return;
            }
        }
        boolean z = false;
        if (testCode == WatchDiagnosticFeatureType.DISPLAY_TEST.getId()) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[diagnosticTestModel.getTestStatus().ordinal()];
            if (i2 != 2) {
                if (i2 == 3 || i2 == 4) {
                    S();
                    w().setShowButtonProceed(Boolean.TRUE);
                    w().btnProceed.setText(getString(R.string.click_for_vibration_test));
                    w().tvInfo.setText("");
                    return;
                }
                return;
            }
            String string3 = getString(R.string.are_you_able_to_see_color);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.are_you_able_to_see_color)");
            J(string3);
            CountDownTimer countDownTimer2 = this.s;
            if (countDownTimer2 != null) {
                countDownTimer2.cancel();
            }
            FragmentRunDiagnosticTestingBinding w3 = w();
            Boolean bool3 = Boolean.FALSE;
            w3.setShowButtonRepeat(bool3);
            w().setShowButtonProceed(bool3);
            w().btnYes.setClickable(true);
            w().btnNo.setClickable(true);
            w().setShowClBottomButton(Boolean.TRUE);
            DiagnosticTestModel diagnosticTestModel2 = this.p;
            if ((diagnosticTestModel2 == null || (childTestCodes = diagnosticTestModel2.getChildTestCodes()) == null || !(childTestCodes.isEmpty() ^ true)) ? false : true) {
                H(this, false, diagnosticTestModel.getTestStatus(), 1, null);
            }
        } else if (testCode == WatchDiagnosticFeatureType.VIBRATION_TEST.getId()) {
            int i3 = WhenMappings.$EnumSwitchMapping$0[diagnosticTestModel.getTestStatus().ordinal()];
            if (i3 != 2) {
                if (i3 == 3 || i3 == 4) {
                    H(this, false, diagnosticTestModel.getTestStatus(), 1, null);
                    return;
                }
                return;
            }
            String string4 = getString(R.string.did_the_watch_vibrate);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.did_the_watch_vibrate)");
            J(string4);
            CountDownTimer countDownTimer3 = this.s;
            if (countDownTimer3 != null) {
                countDownTimer3.cancel();
            }
            I(diagnosticTestModel.getTestName());
            FragmentRunDiagnosticTestingBinding w4 = w();
            if (w().btnRepeat.getTag() == null || !Intrinsics.areEqual(w().btnRepeat.getTag(), "btnRepeatClicked")) {
                z = true;
            }
            w4.setShowButtonRepeat(Boolean.valueOf(z));
            w().btnYes.setClickable(true);
            w().btnNo.setClickable(true);
            w().setShowClBottomButton(Boolean.TRUE);
            w().setShowButtonProceed(Boolean.FALSE);
        } else if (testCode != WatchDiagnosticFeatureType.BUTTON_TEST.getId()) {
            if (testCode != WatchDiagnosticFeatureType.TOUCHSCREEN_TEST.getId()) {
                if (testCode == WatchDiagnosticFeatureType.SENSOR_TEST.getId()) {
                    int i4 = WhenMappings.$EnumSwitchMapping$0[diagnosticTestModel.getTestStatus().ordinal()];
                    if (i4 == 2) {
                        J(HexStringBuilder.DEFAULT_SEPARATOR);
                        I(diagnosticTestModel.getTestName());
                        K(w());
                        return;
                    } else if (i4 == 3 || i4 == 4) {
                        CountDownTimer countDownTimer4 = this.s;
                        if (countDownTimer4 != null) {
                            countDownTimer4.cancel();
                        }
                        w().setShowTimer(Boolean.FALSE);
                        H(this, false, diagnosticTestModel.getTestStatus(), 1, null);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            }
            int i5 = WhenMappings.$EnumSwitchMapping$0[diagnosticTestModel.getTestStatus().ordinal()];
            if (i5 != 2) {
                if (i5 == 3 || i5 == 4) {
                    CountDownTimer countDownTimer5 = this.s;
                    if (countDownTimer5 != null) {
                        countDownTimer5.cancel();
                    }
                    w().setShowTimer(Boolean.FALSE);
                    H(this, false, diagnosticTestModel.getTestStatus(), 1, null);
                    return;
                }
                return;
            }
            if (BleApiManager.getInstance(getContext()).getDeviceType() != DeviceType.ULC5_ULTIMA_CALL && BleApiManager.getInstance(getContext()).getDeviceType() != DeviceType.ULC5_ULTIMA_CONNECT) {
                String string5 = getString(R.string.press_nine_square);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.press_nine_square)");
                J(string5);
            } else {
                String string6 = getString(R.string.press_watch_screen);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.press_watch_screen)");
                J(string6);
            }
            K(w());
        } else {
            int i6 = WhenMappings.$EnumSwitchMapping$0[diagnosticTestModel.getTestStatus().ordinal()];
            if (i6 != 2) {
                if (i6 == 3 || i6 == 4) {
                    CountDownTimer countDownTimer6 = this.s;
                    if (countDownTimer6 != null) {
                        countDownTimer6.cancel();
                    }
                    w().setShowTimer(Boolean.FALSE);
                    H(this, false, diagnosticTestModel.getTestStatus(), 1, null);
                    return;
                }
                return;
            }
            K(w());
            int extensionTestCode = diagnosticTestModel.getExtensionTestCode();
            if (extensionTestCode == HButtonType.H2.getPosition()) {
                String string7 = getString(R.string.press_side_button_of_watch);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.press_side_button_of_watch)");
                String string8 = getString(R.string.h2);
                Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.h2)");
                J(kotlin.text.m.replace$default(string7, "$", string8, false, 4, (Object) null));
            } else if (extensionTestCode == HButtonType.H3.getPosition()) {
                String string9 = getString(R.string.press_side_button_of_watch);
                Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.press_side_button_of_watch)");
                String string10 = getString(R.string.side_button);
                Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.side_button)");
                J(kotlin.text.m.replace$default(string9, "$", string10, false, 4, (Object) null));
            } else if (extensionTestCode == HButtonType.H4.getPosition()) {
                String string11 = getString(R.string.press_side_button_of_watch);
                Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.press_side_button_of_watch)");
                String string12 = getString(R.string.h4);
                Intrinsics.checkNotNullExpressionValue(string12, "getString(R.string.h4)");
                J(kotlin.text.m.replace$default(string11, "$", string12, false, 4, (Object) null));
            } else if (extensionTestCode == HButtonType.H5.getPosition()) {
                String string13 = getString(R.string.press_side_button_of_watch);
                Intrinsics.checkNotNullExpressionValue(string13, "getString(R.string.press_side_button_of_watch)");
                String string14 = getString(R.string.h5);
                Intrinsics.checkNotNullExpressionValue(string14, "getString(R.string.h5)");
                J(kotlin.text.m.replace$default(string13, "$", string14, false, 4, (Object) null));
            } else {
                String string15 = getString(R.string.press_side_button_of_watch);
                Intrinsics.checkNotNullExpressionValue(string15, "getString(R.string.press_side_button_of_watch)");
                String string16 = getString(R.string.side_button);
                Intrinsics.checkNotNullExpressionValue(string16, "getString(R.string.side_button)");
                J(kotlin.text.m.replace$default(string15, "$", string16, false, 4, (Object) null));
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentRunDiagnosticTestingBinding.inflate(inflater, viewGroup, false);
        View root = w().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Resources resources;
        Intrinsics.checkNotNullParameter(message, "message");
        if (isAdded()) {
            Context context = getContext();
            if (message.equals((context == null || (resources = context.getResources()) == null) ? null : resources.getString(R.string.band_disconnected))) {
                Q();
            }
            Toast.makeText(requireContext(), message, 0).show();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        DiagnosticsTestViewModel diagnosticsTestViewModel = this.r;
        if (diagnosticsTestViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            diagnosticsTestViewModel = null;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        diagnosticsTestViewModel.diagnosticsEnterOrExit(requireContext, false);
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        if (!isAdded() && !isVisible()) {
            DiagnosticsTestViewModel diagnosticsTestViewModel = this.r;
            if (diagnosticsTestViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                diagnosticsTestViewModel = null;
            }
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            diagnosticsTestViewModel.diagnosticsEnterOrExit(requireContext, false);
        }
        super.onPause();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        DiagnosticsTestViewModel diagnosticsTestViewModel = (DiagnosticsTestViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(DiagnosticsTestViewModel.class);
        this.r = diagnosticsTestViewModel;
        if (diagnosticsTestViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            diagnosticsTestViewModel = null;
        }
        diagnosticsTestViewModel.setMListener(this);
        final FragmentRunDiagnosticTestingBinding w = w();
        DiagnosticsTestViewModel diagnosticsTestViewModel2 = this.r;
        if (diagnosticsTestViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            diagnosticsTestViewModel2 = null;
        }
        this.o = diagnosticsTestViewModel2.getAllDiagnosticTest();
        w.rvDiagnosticTesting.setLayoutManager(new LinearLayoutManager(requireActivity()));
        Boolean bool = Boolean.FALSE;
        w.setShowClBottomButton(bool);
        w.setShowButtonProceed(bool);
        w.setShowButtonRepeat(bool);
        w.setShowCharging(bool);
        w.setShowTimer(bool);
        S();
        DiagnosticsTestViewModel diagnosticsTestViewModel3 = this.r;
        if (diagnosticsTestViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            diagnosticsTestViewModel3 = null;
        }
        diagnosticsTestViewModel3.isDiagnosticDownloaded().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.leonardo.more.fragments.g1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                RunDiagnosticTestingFragment.z(RunDiagnosticTestingFragment.this, (Boolean) obj);
            }
        });
        DiagnosticsTestViewModel diagnosticsTestViewModel4 = this.r;
        if (diagnosticsTestViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            diagnosticsTestViewModel4 = null;
        }
        diagnosticsTestViewModel4.getDiagnosticTestResult().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.leonardo.more.fragments.q1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                RunDiagnosticTestingFragment.B(RunDiagnosticTestingFragment.this, (DiagnosticTestModel) obj);
            }
        });
        K(w);
        H(this, false, null, 3, null);
        w.btnProceed.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.l1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RunDiagnosticTestingFragment.C(RunDiagnosticTestingFragment.this, view2);
            }
        });
        w.btnNo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.j1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RunDiagnosticTestingFragment.D(FragmentRunDiagnosticTestingBinding.this, this, view2);
            }
        });
        w.btnYes.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.i1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RunDiagnosticTestingFragment.E(FragmentRunDiagnosticTestingBinding.this, this, view2);
            }
        });
        w.btnRepeat.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.f1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RunDiagnosticTestingFragment.F(FragmentRunDiagnosticTestingBinding.this, this, view2);
            }
        });
    }

    public final FragmentRunDiagnosticTestingBinding w() {
        FragmentRunDiagnosticTestingBinding fragmentRunDiagnosticTestingBinding = this.m;
        Intrinsics.checkNotNull(fragmentRunDiagnosticTestingBinding);
        return fragmentRunDiagnosticTestingBinding;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.coveiot.android.leonardo.model.DiagnosticTestModel x(boolean r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L5
            com.coveiot.android.leonardo.model.DiagnosticTestModel r4 = r3.p
            goto L36
        L5:
            java.util.List<com.coveiot.android.leonardo.model.DiagnosticTestModel> r4 = r3.o
            java.util.Iterator r4 = r4.iterator()
        Lb:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L35
            java.lang.Object r0 = r4.next()
            com.coveiot.android.leonardo.model.DiagnosticTestModel r0 = (com.coveiot.android.leonardo.model.DiagnosticTestModel) r0
            com.coveiot.android.leonardo.more.models.TestingStatus r1 = r0.getTestStatus()
            com.coveiot.android.leonardo.more.models.TestingStatus r2 = com.coveiot.android.leonardo.more.models.TestingStatus.IN_PROGRESS
            if (r1 != r2) goto L2b
            java.util.List r1 = r0.getChildTestCodes()
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ 1
            if (r1 != 0) goto L33
        L2b:
            com.coveiot.android.leonardo.more.models.TestingStatus r1 = r0.getTestStatus()
            com.coveiot.android.leonardo.more.models.TestingStatus r2 = com.coveiot.android.leonardo.more.models.TestingStatus.NOT_STARTED
            if (r1 != r2) goto Lb
        L33:
            r4 = r0
            goto L36
        L35:
            r4 = 0
        L36:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.RunDiagnosticTestingFragment.x(boolean):com.coveiot.android.leonardo.model.DiagnosticTestModel");
    }

    public final String y(long j) {
        if (j < 10) {
            StringBuilder sb = new StringBuilder();
            sb.append('0');
            sb.append(j);
            return sb.toString();
        }
        return String.valueOf(j);
    }
}
