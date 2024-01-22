package com.coveiot.android.tappy.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.activity.CardActivationActivity;
import com.coveiot.android.tappy.databinding.FragmentEnterOtpBinding;
import com.coveiot.android.tappy.model.GetStepUpOptionsResponse;
import com.coveiot.android.tappy.model.StepUpRequest;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.compundview.OTPView;
import com.coveiot.utils.utility.AppUtils;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentEnterOtp extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentEnterOtpBinding m;
    @Nullable
    public GetStepUpOptionsResponse n;
    @Nullable
    public String o;
    @Nullable
    public TextView p;
    @Nullable
    public String q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final FragmentEnterOtp$resendCountdownTimer$1 r = new CountDownTimer() { // from class: com.coveiot.android.tappy.fragment.FragmentEnterOtp$resendCountdownTimer$1

        @DebugMetadata(c = "com.coveiot.android.tappy.fragment.FragmentEnterOtp$resendCountdownTimer$1$onFinish$1", f = "FragmentEnterOtp.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentEnterOtp this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentEnterOtp fragmentEnterOtp, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentEnterOtp;
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
                    this.this$0.m().tvResendTimerInSeconds1.setVisibility(8);
                    this.this$0.m().timerMessage.setVisibility(8);
                    this.this$0.m().tvResend.setTextColor(this.this$0.requireContext().getColor(R.color.white));
                    this.this$0.m().tvResend.setEnabled(true);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.fragment.FragmentEnterOtp$resendCountdownTimer$1$onTick$1", f = "FragmentEnterOtp.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ long $millisUntilFinished;
            public int label;
            public final /* synthetic */ FragmentEnterOtp this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(FragmentEnterOtp fragmentEnterOtp, long j, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = fragmentEnterOtp;
                this.$millisUntilFinished = j;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, this.$millisUntilFinished, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                String str;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.m().tvResendTimerInSeconds1.setVisibility(0);
                    this.this$0.m().timerMessage.setVisibility(0);
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(this.$millisUntilFinished);
                    if (seconds > 9) {
                        str = "00:" + seconds;
                    } else {
                        str = "00:0" + seconds;
                    }
                    this.this$0.m().tvResendTimerInSeconds1.setText(str);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        {
            super(Constants.ONE_MIN_IN_MILLIS, 1000L);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (FragmentEnterOtp.this.isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentEnterOtp.this), Dispatchers.getMain(), null, new a(FragmentEnterOtp.this, null), 2, null);
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (FragmentEnterOtp.this.isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentEnterOtp.this), Dispatchers.getMain(), null, new b(FragmentEnterOtp.this, j, null), 2, null);
            }
        }
    };

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FragmentEnterOtp newInstance$default(Companion companion, GetStepUpOptionsResponse getStepUpOptionsResponse, String str, int i, Object obj) {
            if ((i & 2) != 0) {
                str = null;
            }
            return companion.newInstance(getStepUpOptionsResponse, str);
        }

        @JvmStatic
        @NotNull
        public final FragmentEnterOtp newInstance(@Nullable GetStepUpOptionsResponse getStepUpOptionsResponse, @Nullable String str) {
            FragmentEnterOtp fragmentEnterOtp = new FragmentEnterOtp();
            fragmentEnterOtp.n = getStepUpOptionsResponse;
            fragmentEnterOtp.setStepUpId(str);
            return fragmentEnterOtp;
        }
    }

    /* loaded from: classes7.dex */
    public static final class a extends Lambda implements Function1<Boolean, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            FragmentEnterOtp.this.m().btnVerifyOtp.setEnabled(((OTPView) FragmentEnterOtp.this._$_findCachedViewById(R.id.otpEditText)).isFilled());
        }
    }

    public static final void n(FragmentEnterOtp this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.otpEditText;
        String stringFromFields = ((OTPView) this$0._$_findCachedViewById(i)).getStringFromFields();
        if (stringFromFields == null || stringFromFields.length() == 0) {
            Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.please_enter_otp), 0).show();
        } else if (AppUtils.isNetConnected(this$0.requireActivity())) {
            if (this$0.getActivity() instanceof CardActivationActivity) {
                FragmentActivity activity = this$0.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.CardActivationActivity");
                ((CardActivationActivity) activity).validateOtp(((OTPView) this$0._$_findCachedViewById(i)).getStringFromFields());
            }
        } else {
            Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.no_internet_connection), 1).show();
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentEnterOtp newInstance(@Nullable GetStepUpOptionsResponse getStepUpOptionsResponse, @Nullable String str) {
        return Companion.newInstance(getStepUpOptionsResponse, str);
    }

    public static final void o(FragmentEnterOtp this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.otpEditText;
        ((OTPView) this$0._$_findCachedViewById(i)).clearText(true);
        ((OTPView) this$0._$_findCachedViewById(i)).callStyleDefault();
        this$0.hideKeyboard();
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_OTP_RESENT.getValue(), null);
        if (AppUtils.isNetConnected(this$0.requireActivity())) {
            if (this$0.getActivity() instanceof CardActivationActivity) {
                FragmentActivity activity = this$0.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.CardActivationActivity");
                ((CardActivationActivity) activity).putStepUpOption(true);
                return;
            }
            return;
        }
        Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.no_internet_connection), 1).show();
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

    @Nullable
    public final String getStepUpId() {
        return this.o;
    }

    public final FragmentEnterOtpBinding m() {
        FragmentEnterOtpBinding fragmentEnterOtpBinding = this.m;
        Intrinsics.checkNotNull(fragmentEnterOtpBinding);
        return fragmentEnterOtpBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentEnterOtpBinding.inflate(inflater, viewGroup, false);
        return m().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        try {
            cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        boolean z;
        TextView textView;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.p = (TextView) view.findViewById(R.id.tv_dynamicAccountNumber);
        GetStepUpOptionsResponse getStepUpOptionsResponse = this.n;
        Intrinsics.checkNotNull(getStepUpOptionsResponse);
        List<StepUpRequest> stepUpOptions = getStepUpOptionsResponse.getStepUpOptions();
        Intrinsics.checkNotNull(stepUpOptions);
        Iterator<StepUpRequest> it = stepUpOptions.iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            StepUpRequest next = it.next();
            if (Intrinsics.areEqual(this.o, next.getId())) {
                m().enterOtpDescriptionTxt.setText(next.getMethodDescription() + ' ' + next.getMethodValue());
                m().verificationMessage.setText(getString(R.string.otp_to_sent_to, next.getMethodValue()));
                break;
            }
        }
        String str = this.q;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (!z && (textView = this.p) != null) {
            textView.setText(this.q);
        }
        ((OTPView) _$_findCachedViewById(R.id.otpEditText)).setOnCharacterUpdatedListener(new a());
        m().btnVerifyOtp.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentEnterOtp.n(FragmentEnterOtp.this, view2);
            }
        });
        m().tvResend.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentEnterOtp.o(FragmentEnterOtp.this, view2);
            }
        });
        m().tvResendTimerInSeconds1.setVisibility(0);
        m().timerMessage.setVisibility(0);
        m().tvResend.setEnabled(false);
        start();
    }

    public final void setStepUpId(@Nullable String str) {
        this.o = str;
    }
}
