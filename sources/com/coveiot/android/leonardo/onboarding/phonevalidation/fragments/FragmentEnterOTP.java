package com.coveiot.android.leonardo.onboarding.phonevalidation.fragments;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.constant.PermissionConstants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.utils.SingleLiveEvent;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityReportIssue;
import com.coveiot.android.leonardo.onboarding.phonevalidation.adapters.OtpServiceAdapter;
import com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractEnterOTP;
import com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.OTPChecksListener;
import com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ValidationCallback;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.FragmentEnterOtpViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogForOTPWithTwoButtons;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.compundview.OTPView;
import com.coveiot.coveaccess.model.server.GetOTPServicesRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.OTPResendTimerConfigBean;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentEnterOTP extends BaseFragment implements OTPChecksListener, ContractEnterOTP, OtpServiceAdapter.ServiceTypeClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public String n;
    @Nullable
    public ValidationCallback o;
    @Nullable
    public String p;
    public int q;
    public FragmentEnterOtpViewModel r;
    public Dialog reportIssueDialog;
    public OtpServiceAdapter s;
    public int t;
    @Nullable
    public String w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int m = 60000;
    @NotNull
    public final Handler u = new Handler();
    public final long v = 10000;
    @NotNull
    public final String x = "FragmentEnterOTP";
    @NotNull
    public final FragmentEnterOTP$smsBroadcastReceiver$1 y = new FragmentEnterOTP$smsBroadcastReceiver$1();

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentEnterOTP newInstance() {
            return new FragmentEnterOTP();
        }
    }

    /* loaded from: classes5.dex */
    public interface SmsBroadcastReceiverListener {
        void onFailure();

        void onSuccess(@Nullable String str);
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterOTP$callAuthOtp$1", f = "FragmentEnterOTP.kt", i = {}, l = {com.veryfit.multi.nativeprotocol.b.P2}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $serviceType;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$serviceType = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$serviceType, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentEnterOtpViewModel fragmentEnterOtpViewModel = FragmentEnterOTP.this.r;
                if (fragmentEnterOtpViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
                    fragmentEnterOtpViewModel = null;
                }
                FragmentActivity requireActivity = FragmentEnterOTP.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                String str = this.$serviceType;
                String countryCodePhoneNo = FragmentEnterOTP.this.getCountryCodePhoneNo();
                Intrinsics.checkNotNull(countryCodePhoneNo);
                String replace = new Regex("\\s").replace(countryCodePhoneNo, "");
                this.label = 1;
                if (fragmentEnterOtpViewModel.generateAuthOtp(requireActivity, str, replace, this) == coroutine_suspended) {
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

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function0<Unit> {
        public final /* synthetic */ String $message;
        public final /* synthetic */ FragmentEnterOTP this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, FragmentEnterOTP fragmentEnterOTP) {
            super(0);
            this.$message = str;
            this.this$0 = fragmentEnterOTP;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            String stringFromFields;
            Pattern compile = Pattern.compile("(\\d{4})");
            Intrinsics.checkNotNullExpressionValue(compile, "compile(\"(\\\\d{4})\")");
            Matcher matcher = compile.matcher(this.$message);
            Intrinsics.checkNotNullExpressionValue(matcher, "otpPattern.matcher(message)");
            if (matcher.find()) {
                FragmentEnterOTP fragmentEnterOTP = this.this$0;
                int i = R.id.otpEditText;
                if (((OTPView) fragmentEnterOTP._$_findCachedViewById(i)) != null) {
                    OTPView oTPView = (OTPView) this.this$0._$_findCachedViewById(i);
                    if (oTPView != null) {
                        String group = matcher.group(0);
                        Intrinsics.checkNotNullExpressionValue(group, "matcher.group(0)");
                        oTPView.setText(group);
                    }
                    this.this$0.hideKeyboard();
                    OTPView oTPView2 = (OTPView) this.this$0._$_findCachedViewById(i);
                    if (oTPView2 != null && (stringFromFields = oTPView2.getStringFromFields()) != null) {
                        ValidationCallback validationCallback = this.this$0.getValidationCallback();
                        Intrinsics.checkNotNull(validationCallback);
                        validationCallback.onOtpReceived(stringFromFields);
                    }
                    FragmentEnterOtpViewModel fragmentEnterOtpViewModel = this.this$0.r;
                    if (fragmentEnterOtpViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
                        fragmentEnterOtpViewModel = null;
                    }
                    fragmentEnterOtpViewModel.stopTimer();
                    ((Button) this.this$0._$_findCachedViewById(R.id.btn_submit)).setEnabled(true);
                    ((TextView) this.this$0._$_findCachedViewById(R.id.report_issue_text)).setVisibility(8);
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    public static final class c extends Lambda implements Function1<String, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(String str) {
            invoke2(str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            FragmentEnterOTP.this.hideKeyboard();
            FragmentEnterOtpViewModel fragmentEnterOtpViewModel = FragmentEnterOTP.this.r;
            if (fragmentEnterOtpViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
                fragmentEnterOtpViewModel = null;
            }
            fragmentEnterOtpViewModel.stopTimer();
            ((Button) FragmentEnterOTP.this._$_findCachedViewById(R.id.btn_submit)).setEnabled(true);
            ((TextView) FragmentEnterOTP.this._$_findCachedViewById(R.id.report_issue_text)).setVisibility(8);
        }
    }

    /* loaded from: classes5.dex */
    public static final class d extends Lambda implements Function1<Boolean, Unit> {
        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            Button button = (Button) FragmentEnterOTP.this._$_findCachedViewById(R.id.btn_submit);
            OTPView oTPView = (OTPView) FragmentEnterOTP.this._$_findCachedViewById(R.id.otpEditText);
            boolean z2 = true;
            if (oTPView == null || !oTPView.isFilled()) {
                z2 = false;
            }
            button.setEnabled(z2);
        }
    }

    /* loaded from: classes5.dex */
    public static final class e extends Lambda implements Function1<Boolean, Unit> {
        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            if (z) {
                ((Button) FragmentEnterOTP.this._$_findCachedViewById(R.id.btn_submit)).setEnabled(z);
                FragmentEnterOTP.this.hideKeyboard();
                FragmentEnterOtpViewModel fragmentEnterOtpViewModel = FragmentEnterOTP.this.r;
                if (fragmentEnterOtpViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
                    fragmentEnterOtpViewModel = null;
                }
                fragmentEnterOtpViewModel.stopTimer();
            }
        }
    }

    /* loaded from: classes5.dex */
    public static final class f extends Lambda implements Function1<List<? extends GetOTPServicesRes.ServiceType>, Unit> {
        public f() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends GetOTPServicesRes.ServiceType> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<? extends GetOTPServicesRes.ServiceType> serviceTypes) {
            FragmentEnterOTP fragmentEnterOTP = FragmentEnterOTP.this;
            Context requireContext = fragmentEnterOTP.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            fragmentEnterOTP.s = new OtpServiceAdapter(requireContext, FragmentEnterOTP.this);
            FragmentEnterOTP fragmentEnterOTP2 = FragmentEnterOTP.this;
            int i = R.id.rvServiceTypes;
            RecyclerView recyclerView = (RecyclerView) fragmentEnterOTP2._$_findCachedViewById(i);
            OtpServiceAdapter otpServiceAdapter = FragmentEnterOTP.this.s;
            if (otpServiceAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("otpServiceAdapter");
                otpServiceAdapter = null;
            }
            recyclerView.setAdapter(otpServiceAdapter);
            ((RecyclerView) FragmentEnterOTP.this._$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(FragmentEnterOTP.this.requireActivity(), 0, false));
            OtpServiceAdapter otpServiceAdapter2 = FragmentEnterOTP.this.s;
            if (otpServiceAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("otpServiceAdapter");
                otpServiceAdapter2 = null;
            }
            Intrinsics.checkNotNullExpressionValue(serviceTypes, "serviceTypes");
            otpServiceAdapter2.setServiceList(serviceTypes);
            FragmentEnterOTP.this.R();
            FragmentEnterOTP.this.t = 0;
            FragmentEnterOTP fragmentEnterOTP3 = FragmentEnterOTP.this;
            for (GetOTPServicesRes.ServiceType serviceType : serviceTypes) {
                Integer attemptsRemaining = serviceType.getAttemptsRemaining();
                if (attemptsRemaining == null || attemptsRemaining.intValue() != 0) {
                    int i2 = fragmentEnterOTP3.t;
                    Integer attemptsRemaining2 = serviceType.getAttemptsRemaining();
                    Intrinsics.checkNotNullExpressionValue(attemptsRemaining2, "it.attemptsRemaining");
                    fragmentEnterOTP3.t = i2 + attemptsRemaining2.intValue();
                }
                if (!Intrinsics.areEqual(serviceType.getType(), PermissionConstants.SMS)) {
                    OtpServiceAdapter otpServiceAdapter3 = fragmentEnterOTP3.s;
                    if (otpServiceAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("otpServiceAdapter");
                        otpServiceAdapter3 = null;
                    }
                    otpServiceAdapter3.setDefaultSelection(true);
                } else if (!Intrinsics.areEqual(serviceType.getAttemptsAllowed(), serviceType.getAttemptsRemaining())) {
                    OtpServiceAdapter otpServiceAdapter4 = fragmentEnterOTP3.s;
                    if (otpServiceAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("otpServiceAdapter");
                        otpServiceAdapter4 = null;
                    }
                    otpServiceAdapter4.showRemainingServices();
                }
            }
        }
    }

    public static final void A(FragmentEnterOTP this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.onBackPressed();
    }

    public static final void B(FragmentEnterOTP this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_OTP_RESEND.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.OTP_SCREEN.getValue());
        analyticsLog.setPreviousScreenName(FirebaseEventParams.ScreenName.PHONE_NUMBER_SCREEN.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        int i = R.id.otpEditText;
        OTPView oTPView = (OTPView) this$0._$_findCachedViewById(i);
        if (oTPView != null) {
            oTPView.clearText(true);
        }
        OTPView oTPView2 = (OTPView) this$0._$_findCachedViewById(i);
        if (oTPView2 != null) {
            oTPView2.callStyleDefault();
        }
        this$0.hideKeyboard();
        ValidationCallback validationCallback = this$0.o;
        Intrinsics.checkNotNull(validationCallback);
        String str = this$0.n;
        Intrinsics.checkNotNull(str);
        validationCallback.onResendOtpRequested(str);
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel = this$0.r;
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel2 = null;
        if (fragmentEnterOtpViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
            fragmentEnterOtpViewModel = null;
        }
        fragmentEnterOtpViewModel.stopTimer();
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel3 = this$0.r;
        if (fragmentEnterOtpViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
            fragmentEnterOtpViewModel3 = null;
        }
        fragmentEnterOtpViewModel3.startTimer(this$0.m);
        ((TextView) this$0._$_findCachedViewById(R.id.verification_message)).setVisibility(0);
        int i2 = R.id.resend_otp;
        ((TextView) this$0._$_findCachedViewById(i2)).setVisibility(8);
        this$0.q++;
        this$0.v();
        if (this$0.q >= 4) {
            FragmentEnterOtpViewModel fragmentEnterOtpViewModel4 = this$0.r;
            if (fragmentEnterOtpViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
            } else {
                fragmentEnterOtpViewModel2 = fragmentEnterOtpViewModel4;
            }
            fragmentEnterOtpViewModel2.stopTimer();
            ((TextView) this$0._$_findCachedViewById(R.id.report_issue_text)).setVisibility(0);
            ((TextView) this$0._$_findCachedViewById(R.id.timer_message)).setVisibility(8);
            ((TextView) this$0._$_findCachedViewById(R.id.timer_value)).setVisibility(8);
            ((TextView) this$0._$_findCachedViewById(i2)).setVisibility(8);
            return;
        }
        ((TextView) this$0._$_findCachedViewById(R.id.report_issue_text)).setVisibility(8);
        ((TextView) this$0._$_findCachedViewById(R.id.timer_message)).setVisibility(0);
        ((TextView) this$0._$_findCachedViewById(R.id.timer_value)).setVisibility(0);
        ((TextView) this$0._$_findCachedViewById(i2)).setVisibility(8);
    }

    public static final void C(FragmentEnterOTP this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_OTP_RESEND.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.OTP_SCREEN.getValue());
        analyticsLog.setPreviousScreenName(FirebaseEventParams.ScreenName.PHONE_NUMBER_SCREEN.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        int i = R.id.otpEditText;
        OTPView oTPView = (OTPView) this$0._$_findCachedViewById(i);
        if (oTPView != null) {
            oTPView.clearText(true);
        }
        OTPView oTPView2 = (OTPView) this$0._$_findCachedViewById(i);
        if (oTPView2 != null) {
            oTPView2.callStyleDefault();
        }
        this$0.hideKeyboard();
        ValidationCallback validationCallback = this$0.o;
        Intrinsics.checkNotNull(validationCallback);
        String str = this$0.n;
        Intrinsics.checkNotNull(str);
        validationCallback.onResendOtpRequested(str);
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel = this$0.r;
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel2 = null;
        if (fragmentEnterOtpViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
            fragmentEnterOtpViewModel = null;
        }
        fragmentEnterOtpViewModel.stopTimer();
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel3 = this$0.r;
        if (fragmentEnterOtpViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
            fragmentEnterOtpViewModel3 = null;
        }
        fragmentEnterOtpViewModel3.startTimer(this$0.m);
        int i2 = R.id.invalid_otp_layout;
        ((ConstraintLayout) this$0._$_findCachedViewById(i2)).setVisibility(8);
        ((TextView) this$0._$_findCachedViewById(R.id.verification_message)).setVisibility(0);
        int i3 = this$0.q + 1;
        this$0.q = i3;
        if (i3 >= 4) {
            FragmentEnterOtpViewModel fragmentEnterOtpViewModel4 = this$0.r;
            if (fragmentEnterOtpViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
            } else {
                fragmentEnterOtpViewModel2 = fragmentEnterOtpViewModel4;
            }
            fragmentEnterOtpViewModel2.stopTimer();
            ((TextView) this$0._$_findCachedViewById(R.id.report_issue_text)).setVisibility(0);
            ((TextView) this$0._$_findCachedViewById(R.id.timer_message)).setVisibility(8);
            ((TextView) this$0._$_findCachedViewById(R.id.timer_value)).setVisibility(8);
            ((ConstraintLayout) this$0._$_findCachedViewById(i2)).setVisibility(8);
            return;
        }
        ((TextView) this$0._$_findCachedViewById(R.id.report_issue_text)).setVisibility(8);
        ((TextView) this$0._$_findCachedViewById(R.id.timer_message)).setVisibility(0);
        ((TextView) this$0._$_findCachedViewById(R.id.timer_value)).setVisibility(0);
        ((ConstraintLayout) this$0._$_findCachedViewById(i2)).setVisibility(8);
    }

    public static final void D(FragmentEnterOTP this$0, View view) {
        String stringFromFields;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.OTP_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_EXISTING_PROFILE_POPUP.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.NEXT_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.hideKeyboard();
        OTPView oTPView = (OTPView) this$0._$_findCachedViewById(R.id.otpEditText);
        if (oTPView == null || (stringFromFields = oTPView.getStringFromFields()) == null) {
            return;
        }
        ValidationCallback validationCallback = this$0.o;
        Intrinsics.checkNotNull(validationCallback);
        validationCallback.onOtpReceived(stringFromFields);
    }

    public static final void E(FragmentEnterOTP this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_OTP_REPORT_ISSUE.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.OTP_SCREEN.getValue());
        analyticsLog.setPreviousScreenName(FirebaseEventParams.ScreenName.PHONE_NUMBER_SCREEN.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.q = 0;
        this$0.O();
    }

    public static final void I(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void M(BottomSheetDialogForOTPWithTwoButtons bottomSheetDialogForOTPWithTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogForOTPWithTwoButtons, "$bottomSheetDialogForOTPWithTwoButtons");
        bottomSheetDialogForOTPWithTwoButtons.dismiss();
    }

    public static final void N(Function0 onConsent, BottomSheetDialogForOTPWithTwoButtons bottomSheetDialogForOTPWithTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(onConsent, "$onConsent");
        Intrinsics.checkNotNullParameter(bottomSheetDialogForOTPWithTwoButtons, "$bottomSheetDialogForOTPWithTwoButtons");
        onConsent.invoke();
        bottomSheetDialogForOTPWithTwoButtons.dismiss();
    }

    public static final void P(FragmentEnterOTP this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.APOLOGISE_POPUP.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_OTP_REPORT_ISSUE_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SHARE_FEEDBACK_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        Intent intent = new Intent(activity, ActivityReportIssue.class);
        intent.putExtra(AppConstants.PHONE_NUMBER.getValue(), this$0.n);
        intent.putExtra(AppConstants.INTENT_EXTRA_TITLE.getValue(), this$0.getString(R.string.otp_report_issue));
        this$0.startActivityForResult(intent, 105);
    }

    public static final void Q(FragmentEnterOTP this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.APOLOGISE_POPUP.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_OTP_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.OK_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.getReportIssueDialog().dismiss();
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    public static final void S(FragmentEnterOTP this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            TextView tv_resend_attempt_error_msg = (TextView) this$0._$_findCachedViewById(R.id.tv_resend_attempt_error_msg);
            Intrinsics.checkNotNullExpressionValue(tv_resend_attempt_error_msg, "tv_resend_attempt_error_msg");
            this$0.gone(tv_resend_attempt_error_msg);
        }
    }

    public final void F(int i, String str, GetOTPServicesRes.ServiceType serviceType) {
        w(str);
        R();
        int i2 = R.id.otpEditText;
        ((OTPView) _$_findCachedViewById(i2)).clearText(true);
        OTPView oTPView = (OTPView) _$_findCachedViewById(i2);
        if (oTPView != null) {
            oTPView.callStyleDefault();
        }
        hideKeyboard();
        this.t--;
        OtpServiceAdapter otpServiceAdapter = this.s;
        if (otpServiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("otpServiceAdapter");
            otpServiceAdapter = null;
        }
        otpServiceAdapter.setUpdateServiceData(serviceType.getAttemptsRemaining().intValue() - 1);
    }

    public final void G() {
        this.y.setSmsBroadcastReceiverListener(new SmsBroadcastReceiverListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterOTP$registerBroadcastReceiver$1
            @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterOTP.SmsBroadcastReceiverListener
            public void onFailure() {
                LogHelper.d(FragmentEnterOTP.this.getTAG(), "smsBroadcastReceiver on failure");
            }

            @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterOTP.SmsBroadcastReceiverListener
            public void onSuccess(@Nullable String str) {
                try {
                    LogHelper.d(FragmentEnterOTP.this.getTAG(), "smsBroadcastReceiver on success");
                    FragmentEnterOTP.this.y(str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        requireActivity().registerReceiver(this.y, new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION));
    }

    public final void H() {
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel = this.r;
        if (fragmentEnterOtpViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
            fragmentEnterOtpViewModel = null;
        }
        SingleLiveEvent<List<GetOTPServicesRes.ServiceType>> otpServicesList = fragmentEnterOtpViewModel.getOtpServicesList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        final f fVar = new f();
        otpServicesList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.k
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentEnterOTP.I(Function1.this, obj);
            }
        });
    }

    public final void J(String str) {
        OTPResendTimerConfigBean resendOTPTimerConfigBean = SessionManager.getInstance(requireActivity()).getResendOTPTimerConfigBean();
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel = null;
        if (resendOTPTimerConfigBean != null) {
            int hashCode = str.hashCode();
            if (hashCode != -1577559662) {
                if (hashCode != -1309800785) {
                    if (hashCode == 82233 && str.equals(PermissionConstants.SMS)) {
                        this.m = resendOTPTimerConfigBean.getSms() * 1000;
                    }
                } else if (str.equals("PHONE_CALL")) {
                    this.m = resendOTPTimerConfigBean.getPhone_call() * 1000;
                }
            } else if (str.equals("WHATSAPP")) {
                this.m = resendOTPTimerConfigBean.getWhatsapp() * 1000;
            }
            FragmentEnterOtpViewModel fragmentEnterOtpViewModel2 = this.r;
            if (fragmentEnterOtpViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
            } else {
                fragmentEnterOtpViewModel = fragmentEnterOtpViewModel2;
            }
            fragmentEnterOtpViewModel.startTimer(this.m);
            return;
        }
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel3 = this.r;
        if (fragmentEnterOtpViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
        } else {
            fragmentEnterOtpViewModel = fragmentEnterOtpViewModel3;
        }
        fragmentEnterOtpViewModel.startTimer(this.m);
    }

    public final void K(String str) {
        String str2 = str;
        int hashCode = str.hashCode();
        if (hashCode != -1577559662) {
            if (hashCode != -1309800785) {
                if (hashCode == 82233 && str2.equals(PermissionConstants.SMS)) {
                    str2 = getString(R.string.sms);
                }
            } else if (str2.equals("PHONE_CALL")) {
                str2 = getString(R.string.call);
            }
        } else if (str2.equals("WHATSAPP")) {
            str2 = getString(R.string.whatsApp);
        }
        Intrinsics.checkNotNullExpressionValue(str2, "when (serviceType) {\n   … -> serviceType\n        }");
        String value = AppConstants.PHONE_NO.getValue();
        String str3 = HexStringBuilder.COMMENT_BEGIN_CHAR + AppConstants.COUNTRY_CODE.getValue() + HexStringBuilder.COMMENT_END_CHAR + AppConstants.EMPTY_SPACE.getValue() + ' ' + value;
        String str4 = getString(R.string.a_verification_otp_has_been_sent_to) + ' ' + str3 + ' ' + getString(R.string.via) + ' ' + str2;
        SpannableString spannableString = new SpannableString(str4);
        spannableString.setSpan(new StyleSpan(1), StringsKt__StringsKt.indexOf$default((CharSequence) str4, str3, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) str4, str3, 0, false, 6, (Object) null) + str3.length(), 33);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.main_text_color)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), StringsKt__StringsKt.indexOf$default((CharSequence) str4, str3, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) str4, str3, 0, false, 6, (Object) null) + str3.length(), 33);
        String str5 = str2;
        spannableString.setSpan(new StyleSpan(1), StringsKt__StringsKt.indexOf$default((CharSequence) str4, str5, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) str4, str5, 0, false, 6, (Object) null) + str2.length(), 33);
        Context context2 = getContext();
        Integer valueOf2 = context2 != null ? Integer.valueOf(context2.getColor(R.color.main_text_color)) : null;
        Intrinsics.checkNotNull(valueOf2);
        String str6 = str2;
        spannableString.setSpan(new ForegroundColorSpan(valueOf2.intValue()), StringsKt__StringsKt.indexOf$default((CharSequence) str4, str6, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) str4, str6, 0, false, 6, (Object) null) + str2.length(), 33);
        int i = R.id.verification_message;
        ((TextView) _$_findCachedViewById(i)).setText(spannableString);
        ((TextView) _$_findCachedViewById(i)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    public final void L(String str, final Function0<Unit> function0) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.read_otp_title);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(com.….R.string.read_otp_title)");
        final BottomSheetDialogForOTPWithTwoButtons bottomSheetDialogForOTPWithTwoButtons = new BottomSheetDialogForOTPWithTwoButtons(requireContext, string, str);
        String string2 = getString(R.string.allow);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.allow)");
        bottomSheetDialogForOTPWithTwoButtons.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentEnterOTP.N(Function0.this, bottomSheetDialogForOTPWithTwoButtons, view);
            }
        });
        String string3 = getString(R.string.deny);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.android.theme.R.string.deny)");
        bottomSheetDialogForOTPWithTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentEnterOTP.M(BottomSheetDialogForOTPWithTwoButtons.this, view);
            }
        });
        bottomSheetDialogForOTPWithTwoButtons.setCancelable(false);
        bottomSheetDialogForOTPWithTwoButtons.show();
    }

    public final void O() {
        setReportIssueDialog(new Dialog(requireActivity(), R.style.DialogTheme));
        getReportIssueDialog().setCanceledOnTouchOutside(false);
        getReportIssueDialog().setCancelable(false);
        getReportIssueDialog().setContentView(R.layout.report_issue_dialog);
        TextView textView = (TextView) getReportIssueDialog().findViewById(R.id.title);
        if (textView != null) {
            textView.setText(getString(R.string.otp_report_issue));
        }
        TextView textView2 = (TextView) getReportIssueDialog().findViewById(R.id.share_your_feedback);
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentEnterOTP.P(FragmentEnterOTP.this, view);
                }
            });
        }
        Button button = (Button) getReportIssueDialog().findViewById(R.id.positive_btn);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentEnterOTP.Q(FragmentEnterOTP.this, view);
                }
            });
        }
        getReportIssueDialog().show();
    }

    public final void R() {
        int i = R.id.rvServiceTypes;
        if (((RecyclerView) _$_findCachedViewById(i)).getVisibility() == 0) {
            ConstraintLayout clTimer = (ConstraintLayout) _$_findCachedViewById(R.id.clTimer);
            Intrinsics.checkNotNullExpressionValue(clTimer, "clTimer");
            visible(clTimer);
            RecyclerView rvServiceTypes = (RecyclerView) _$_findCachedViewById(i);
            Intrinsics.checkNotNullExpressionValue(rvServiceTypes, "rvServiceTypes");
            gone(rvServiceTypes);
            FragmentEnterOtpViewModel fragmentEnterOtpViewModel = this.r;
            FragmentEnterOtpViewModel fragmentEnterOtpViewModel2 = null;
            if (fragmentEnterOtpViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
                fragmentEnterOtpViewModel = null;
            }
            fragmentEnterOtpViewModel.stopTimer();
            FragmentEnterOtpViewModel fragmentEnterOtpViewModel3 = this.r;
            if (fragmentEnterOtpViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
            } else {
                fragmentEnterOtpViewModel2 = fragmentEnterOtpViewModel3;
            }
            fragmentEnterOtpViewModel2.startTimer(this.m);
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.timer_message)).setText(getResources().getText(R.string.resend_via));
        TextView timer_value = (TextView) _$_findCachedViewById(R.id.timer_value);
        Intrinsics.checkNotNullExpressionValue(timer_value, "timer_value");
        gone(timer_value);
        RecyclerView rvServiceTypes2 = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(rvServiceTypes2, "rvServiceTypes");
        visible(rvServiceTypes2);
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

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractEnterOTP
    public void enableVerifyButton(boolean z) {
    }

    public final int getCountdown_time() {
        return this.m;
    }

    @Nullable
    public final String getCountryCodePhoneNo() {
        return this.p;
    }

    @NotNull
    public final Dialog getReportIssueDialog() {
        Dialog dialog = this.reportIssueDialog;
        if (dialog != null) {
            return dialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("reportIssueDialog");
        return null;
    }

    public final int getResendCount() {
        return this.q;
    }

    @NotNull
    public final String getTAG() {
        return this.x;
    }

    @Nullable
    public final ValidationCallback getValidationCallback() {
        return this.o;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getActivity() instanceof ValidationCallback) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ValidationCallback");
            this.o = (ValidationCallback) activity;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 105) {
            if (getReportIssueDialog().isShowing()) {
                getReportIssueDialog().dismiss();
            }
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.onBackPressed();
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            String value = AppConstants.PHONE_VALID_PHONENO.getValue();
            AppConstants appConstants = AppConstants.EMPTY_STRING;
            this.n = arguments.getString(value, appConstants.getValue());
            Bundle arguments2 = getArguments();
            Intrinsics.checkNotNull(arguments2);
            this.p = arguments2.getString(AppConstants.COUNTRYCODE_PHONENO.getValue(), appConstants.getValue());
            Bundle arguments3 = getArguments();
            Intrinsics.checkNotNull(arguments3);
            this.w = arguments3.getString(AppConstants.DEFAULT_SERVICE_TYPE.getValue(), appConstants.getValue());
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_enter_ot_fragment, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.y != null) {
            requireActivity().unregisterReceiver(this.y);
        }
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.OTPChecksListener
    public void onFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        toast(requireContext, message);
    }

    public final void onInvalidOTP() {
        int i = R.id.otpEditText;
        OTPView oTPView = (OTPView) _$_findCachedViewById(i);
        if (oTPView != null) {
            oTPView.setErrorBackgroundImage(getResources().getDrawable(R.drawable.otp_text_view_error_bg));
        }
        OTPView oTPView2 = (OTPView) _$_findCachedViewById(i);
        if (oTPView2 != null) {
            oTPView2.callStyleError();
        }
        ((Button) _$_findCachedViewById(R.id.btn_submit)).setEnabled(false);
        int i2 = R.id.verification_message;
        ((TextView) _$_findCachedViewById(i2)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i2)).setText(getString(R.string.incorrect_otp_msg));
        int i3 = R.id.timer_message;
        TextView timer_message = (TextView) _$_findCachedViewById(i3);
        Intrinsics.checkNotNullExpressionValue(timer_message, "timer_message");
        visible(timer_message);
        ((TextView) _$_findCachedViewById(i3)).setText(getResources().getText(R.string.resend_via));
        TextView timer_value = (TextView) _$_findCachedViewById(R.id.timer_value);
        Intrinsics.checkNotNullExpressionValue(timer_value, "timer_value");
        gone(timer_value);
        RecyclerView rvServiceTypes = (RecyclerView) _$_findCachedViewById(R.id.rvServiceTypes);
        Intrinsics.checkNotNullExpressionValue(rvServiceTypes, "rvServiceTypes");
        visible(rvServiceTypes);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        G();
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.OTPChecksListener
    public void onSuccess() {
    }

    public final void onTextHide() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.timer_message);
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.timer_value);
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.resend_otp);
        if (textView3 == null) {
            return;
        }
        textView3.setVisibility(8);
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractEnterOTP
    public void onTimerFinished() {
        int i = R.id.resend_otp;
        if (((TextView) _$_findCachedViewById(i)) != null) {
            ((TextView) _$_findCachedViewById(R.id.timer_value)).setVisibility(8);
            R();
            OtpServiceAdapter otpServiceAdapter = this.s;
            if (otpServiceAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("otpServiceAdapter");
                otpServiceAdapter = null;
            }
            otpServiceAdapter.showRemainingServices();
            ((TextView) _$_findCachedViewById(i)).setVisibility(8);
        }
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractEnterOTP
    public void onTimerValueChanged(int i) {
        int i2 = R.id.timer_value;
        if (((TextView) _$_findCachedViewById(i2)) != null) {
            ((TextView) _$_findCachedViewById(i2)).setText(x(i));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.OTP_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        v();
        firebaseConstants.setValue(screenName.getValue());
        AppConstants.PHONE_NO.getValue();
        AppConstants.COUNTRY_CODE.getValue();
        AppConstants.EMPTY_SPACE.getValue();
        ((TextView) ((ConstraintLayout) view.findViewById(R.id.toolbar)).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentEnterOTP.A(FragmentEnterOTP.this, view2);
            }
        });
        Application application = requireActivity().getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "requireActivity().application");
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel = new FragmentEnterOtpViewModel(application);
        this.r = fragmentEnterOtpViewModel;
        fragmentEnterOtpViewModel.setMContractEnterOTP(this);
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel2 = this.r;
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel3 = null;
        if (fragmentEnterOtpViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
            fragmentEnterOtpViewModel2 = null;
        }
        fragmentEnterOtpViewModel2.setListener(this);
        String str = this.w;
        if (!(str == null || str.length() == 0)) {
            String str2 = this.w;
            Intrinsics.checkNotNull(str2);
            J(str2);
            String str3 = this.w;
            Intrinsics.checkNotNull(str3);
            K(str3);
        }
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel4 = this.r;
        if (fragmentEnterOtpViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
        } else {
            fragmentEnterOtpViewModel3 = fragmentEnterOtpViewModel4;
        }
        fragmentEnterOtpViewModel3.getOtpServices();
        H();
        ((TextView) _$_findCachedViewById(R.id.resend_otp)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentEnterOTP.B(FragmentEnterOTP.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.resend_otp_2)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentEnterOTP.C(FragmentEnterOTP.this, view2);
            }
        });
        int i = R.id.otpEditText;
        OTPView oTPView = (OTPView) _$_findCachedViewById(i);
        if (oTPView != null) {
            oTPView.setOnFinishListener(new c());
        }
        OTPView oTPView2 = (OTPView) _$_findCachedViewById(i);
        if (oTPView2 != null) {
            oTPView2.setOnCharacterUpdatedListener(new d());
        }
        OTPView oTPView3 = (OTPView) _$_findCachedViewById(i);
        if (oTPView3 != null) {
            oTPView3.setOnFilledListener(new e());
        }
        ((Button) _$_findCachedViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentEnterOTP.D(FragmentEnterOTP.this, view2);
            }
        });
        OTPView oTPView4 = (OTPView) _$_findCachedViewById(i);
        if (oTPView4 != null) {
            oTPView4.requestFocus();
        }
        ((TextView) _$_findCachedViewById(R.id.report_issue_text)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentEnterOTP.E(FragmentEnterOTP.this, view2);
            }
        });
    }

    public final void setCountdown_time(int i) {
        this.m = i;
    }

    public final void setCountryCodePhoneNo(@Nullable String str) {
        this.p = str;
    }

    public final void setReportIssueDialog(@NotNull Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "<set-?>");
        this.reportIssueDialog = dialog;
    }

    public final void setResendCount(int i) {
        this.q = i;
    }

    public final void setValidationCallback(@Nullable ValidationCallback validationCallback) {
        this.o = validationCallback;
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.adapters.OtpServiceAdapter.ServiceTypeClickListener
    public void typeClick(@NotNull GetOTPServicesRes.ServiceType serviceData, int i) {
        Intrinsics.checkNotNullParameter(serviceData, "serviceData");
        if (AppUtils.isNetConnected(requireContext())) {
            Integer attemptsRemaining = serviceData.getAttemptsRemaining();
            OtpServiceAdapter otpServiceAdapter = null;
            if (attemptsRemaining == null || attemptsRemaining.intValue() != 0) {
                try {
                    TextView tv_resend_attempt_error_msg = (TextView) _$_findCachedViewById(R.id.tv_resend_attempt_error_msg);
                    Intrinsics.checkNotNullExpressionValue(tv_resend_attempt_error_msg, "tv_resend_attempt_error_msg");
                    gone(tv_resend_attempt_error_msg);
                    String type = serviceData.getType();
                    Intrinsics.checkNotNullExpressionValue(type, "serviceData.type");
                    z(type);
                    String type2 = serviceData.getType();
                    Intrinsics.checkNotNullExpressionValue(type2, "serviceData.type");
                    F(i, type2, serviceData);
                    OtpServiceAdapter otpServiceAdapter2 = this.s;
                    if (otpServiceAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("otpServiceAdapter");
                        otpServiceAdapter2 = null;
                    }
                    otpServiceAdapter2.setUpdateServiceData(serviceData.getAttemptsRemaining().intValue() - 1);
                    OtpServiceAdapter otpServiceAdapter3 = this.s;
                    if (otpServiceAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("otpServiceAdapter");
                        otpServiceAdapter3 = null;
                    }
                    otpServiceAdapter3.notifyItemChanged(i);
                    OtpServiceAdapter otpServiceAdapter4 = this.s;
                    if (otpServiceAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("otpServiceAdapter");
                        otpServiceAdapter4 = null;
                    }
                    otpServiceAdapter4.showRemainingServices();
                    OtpServiceAdapter otpServiceAdapter5 = this.s;
                    if (otpServiceAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("otpServiceAdapter");
                    } else {
                        otpServiceAdapter = otpServiceAdapter5;
                    }
                    otpServiceAdapter.notifyDataSetChanged();
                    String type3 = serviceData.getType();
                    Intrinsics.checkNotNullExpressionValue(type3, "serviceData.type");
                    K(type3);
                    String type4 = serviceData.getType();
                    Intrinsics.checkNotNullExpressionValue(type4, "serviceData.type");
                    J(type4);
                    return;
                } catch (Exception e2) {
                    Log.d("otpServiceCheck", "typeClick:Error " + e2.getMessage());
                    return;
                }
            }
            int i2 = R.id.tv_resend_attempt_error_msg;
            TextView tv_resend_attempt_error_msg2 = (TextView) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(tv_resend_attempt_error_msg2, "tv_resend_attempt_error_msg");
            visible(tv_resend_attempt_error_msg2);
            if (this.t < 1) {
                ((TextView) _$_findCachedViewById(i2)).setText(getString(R.string.retry_limit_exceeded_plaese_try_again));
            } else {
                ((TextView) _$_findCachedViewById(i2)).setText(getString(R.string.you_ve_reached_the_maximum_attempts_for_this_option_please_select_from_the_other_options));
            }
            this.u.removeCallbacksAndMessages(null);
            this.u.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.b
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentEnterOTP.S(FragmentEnterOTP.this);
                }
            }, this.v);
            return;
        }
        showNoInternetMessage();
    }

    public final void v() {
        if (isAdded()) {
            SmsRetrieverClient client = SmsRetriever.getClient((Activity) requireActivity());
            Intrinsics.checkNotNullExpressionValue(client, "getClient(requireActivity())");
            client.startSmsRetriever();
        }
    }

    public final void w(String str) {
        ((TextView) _$_findCachedViewById(R.id.timer_value)).setVisibility(0);
        ((TextView) _$_findCachedViewById(R.id.timer_message)).setText(getResources().getText(R.string.resend_in));
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new a(str, null), 3, null);
    }

    public final String x(int i) {
        String valueOf;
        String valueOf2;
        int i2 = i / 60;
        int i3 = i % 60;
        if (i2 < 10) {
            StringBuilder sb = new StringBuilder();
            sb.append('0');
            sb.append(i2);
            valueOf = sb.toString();
        } else {
            valueOf = String.valueOf(i2);
        }
        if (i3 < 10) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('0');
            sb2.append(i3);
            valueOf2 = sb2.toString();
        } else {
            valueOf2 = String.valueOf(i3);
        }
        return valueOf + ':' + valueOf2;
    }

    public final void y(String str) {
        if (!isAdded() || str == null) {
            return;
        }
        L(str, new b(str, this));
    }

    public final void z(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        int hashCode = str.hashCode();
        if (hashCode != -1577559662) {
            if (hashCode != -1309800785) {
                if (hashCode == 82233 && str.equals(PermissionConstants.SMS)) {
                    hashMap.put(CleverTapConstants.CustomEventProperties.RESEND_BY.getValue(), CleverTapConstants.CustomEventValues.SMS.getValue());
                }
            } else if (str.equals("PHONE_CALL")) {
                hashMap.put(CleverTapConstants.CustomEventProperties.RESEND_BY.getValue(), CleverTapConstants.CustomEventValues.ONCALL.getValue());
            }
        } else if (str.equals("WHATSAPP")) {
            hashMap.put(CleverTapConstants.CustomEventProperties.RESEND_BY.getValue(), CleverTapConstants.CustomEventValues.WHATSAPP.getValue());
        }
        companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_OTP_RESENT.getValue(), hashMap);
    }
}
