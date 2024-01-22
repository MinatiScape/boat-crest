package com.coveiot.android.weeklyreport.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.compundview.OtpEditText;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.WeeklyReportData;
import com.coveiot.android.weeklyreport.activities.ActivityReportIssue;
import com.coveiot.android.weeklyreport.listeners.OnSuccessListener;
import com.coveiot.android.weeklyreport.listeners.TimerListener;
import com.coveiot.android.weeklyreport.utils.ViewModelFactory;
import com.coveiot.android.weeklyreport.utils.WeeklyReportConstant;
import com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentVerifyEmail extends BaseFragment implements TimerListener, OnSuccessListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public NextScreenListener listener;
    public WeeklyReportViewModel m;
    @Nullable
    public String o;
    public int q;
    public BottomSheetDialog reportIssueDialog;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String n = "EMAILID";
    public final int p = 60000;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentVerifyEmail newInstance(@NotNull String emailId) {
            Intrinsics.checkNotNullParameter(emailId, "emailId");
            FragmentVerifyEmail fragmentVerifyEmail = new FragmentVerifyEmail();
            Bundle bundle = new Bundle();
            bundle.putString(fragmentVerifyEmail.n, emailId);
            fragmentVerifyEmail.setArguments(bundle);
            return fragmentVerifyEmail;
        }
    }

    /* loaded from: classes8.dex */
    public interface NextScreenListener {
        void nextScreen(@NotNull WeeklyReportData weeklyReportData);
    }

    public static final void r(FragmentVerifyEmail this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (str.equals(this$0.getResources().getString(R.string.valid_otp))) {
            this$0.onTextHide();
        } else {
            this$0.onInvalidOTP();
        }
        WeeklyReportViewModel weeklyReportViewModel = this$0.m;
        if (weeklyReportViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
            weeklyReportViewModel = null;
        }
        weeklyReportViewModel.getInvalidOtpUiLivedata().removeObservers(this$0);
    }

    public static final void t(FragmentVerifyEmail this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WeeklyReportConstant weeklyReportConstant = WeeklyReportConstant.UNSUBSCRIBE;
        if (str.equals(weeklyReportConstant.getValue())) {
            this$0.getListener().nextScreen(new WeeklyReportData(weeklyReportConstant.getValue(), ""));
        } else {
            WeeklyReportConstant weeklyReportConstant2 = WeeklyReportConstant.FINISH;
            if (str.equals(weeklyReportConstant2.getValue())) {
                this$0.getListener().nextScreen(new WeeklyReportData(weeklyReportConstant2.getValue(), ""));
            } else {
                this$0.getListener().nextScreen(new WeeklyReportData(WeeklyReportConstant.SUBSCRIBE_SUCCESS.getValue(), ""));
            }
        }
        WeeklyReportViewModel weeklyReportViewModel = this$0.m;
        if (weeklyReportViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
            weeklyReportViewModel = null;
        }
        weeklyReportViewModel.getSuccessLiveData().removeObservers(this$0);
    }

    public static final void u(FragmentVerifyEmail this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((OtpEditText) this$0._$_findCachedViewById(R.id.otpEditText)).getText().clear();
        WeeklyReportViewModel weeklyReportViewModel = this$0.m;
        WeeklyReportViewModel weeklyReportViewModel2 = null;
        if (weeklyReportViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
            weeklyReportViewModel = null;
        }
        String str = this$0.o;
        Intrinsics.checkNotNull(str);
        weeklyReportViewModel.generateOtp(str);
        WeeklyReportViewModel weeklyReportViewModel3 = this$0.m;
        if (weeklyReportViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
            weeklyReportViewModel3 = null;
        }
        weeklyReportViewModel3.stopTimer();
        WeeklyReportViewModel weeklyReportViewModel4 = this$0.m;
        if (weeklyReportViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
            weeklyReportViewModel4 = null;
        }
        weeklyReportViewModel4.startTimer(this$0.p);
        int i = R.id.resend_otp;
        ((TextView) this$0._$_findCachedViewById(i)).setVisibility(8);
        int i2 = this$0.q + 1;
        this$0.q = i2;
        if (i2 >= 4) {
            WeeklyReportViewModel weeklyReportViewModel5 = this$0.m;
            if (weeklyReportViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
            } else {
                weeklyReportViewModel2 = weeklyReportViewModel5;
            }
            weeklyReportViewModel2.stopTimer();
            int i3 = R.id.report_issue_text;
            ((TextView) this$0._$_findCachedViewById(i3)).setPaintFlags(((TextView) this$0._$_findCachedViewById(i3)).getPaintFlags() | 8);
            ((TextView) this$0._$_findCachedViewById(i3)).setVisibility(0);
            ((TextView) this$0._$_findCachedViewById(R.id.invalid_otp)).setVisibility(8);
            ((TextView) this$0._$_findCachedViewById(R.id.timer_message)).setVisibility(8);
            ((TextView) this$0._$_findCachedViewById(i)).setVisibility(8);
            return;
        }
        ((TextView) this$0._$_findCachedViewById(R.id.report_issue_text)).setVisibility(8);
        ((TextView) this$0._$_findCachedViewById(R.id.invalid_otp)).setVisibility(8);
        ((TextView) this$0._$_findCachedViewById(R.id.timer_message)).setVisibility(0);
        ((TextView) this$0._$_findCachedViewById(i)).setVisibility(8);
    }

    public static final void v(FragmentVerifyEmail this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.q = 0;
        this$0.w();
    }

    public static final void x(FragmentVerifyEmail this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivityForResult(new Intent(this$0.requireActivity(), ActivityReportIssue.class), 105);
    }

    public static final void y(FragmentVerifyEmail this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getReportIssueDialog().dismiss();
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.onBackPressed();
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

    @Override // com.coveiot.android.weeklyreport.listeners.TimerListener
    public void enableVerifyButton(boolean z) {
    }

    public final int getCountdown_time() {
        return this.p;
    }

    @Nullable
    public final String getEmailId() {
        return this.o;
    }

    @NotNull
    public final NextScreenListener getListener() {
        NextScreenListener nextScreenListener = this.listener;
        if (nextScreenListener != null) {
            return nextScreenListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return null;
    }

    @NotNull
    public final BottomSheetDialog getReportIssueDialog() {
        BottomSheetDialog bottomSheetDialog = this.reportIssueDialog;
        if (bottomSheetDialog != null) {
            return bottomSheetDialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("reportIssueDialog");
        return null;
    }

    public final int getResendCount() {
        return this.q;
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

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onAttach(activity);
        if (activity instanceof NextScreenListener) {
            setListener((NextScreenListener) activity);
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.o = requireArguments().getString(this.n);
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(WeeklyReportViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…del::class.java\n        )");
        WeeklyReportViewModel weeklyReportViewModel = (WeeklyReportViewModel) viewModel;
        this.m = weeklyReportViewModel;
        if (weeklyReportViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
            weeklyReportViewModel = null;
        }
        String str = this.o;
        Intrinsics.checkNotNull(str);
        weeklyReportViewModel.generateOtp(str);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_verify_otp, viewGroup, false);
    }

    @Override // com.coveiot.android.weeklyreport.listeners.OnSuccessListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        dismissProgress();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void onInvalidOTP() {
        ((OtpEditText) _$_findCachedViewById(R.id.otpEditText)).updateUnderLineColor(false);
        int i = R.id.timer_message;
        if (((TextView) _$_findCachedViewById(i)) != null) {
            ((TextView) _$_findCachedViewById(i)).setText(getString(R.string.didnt_recieve_otp));
        }
        ((TextView) _$_findCachedViewById(R.id.invalid_otp)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i)).setVisibility(0);
        ((TextView) _$_findCachedViewById(R.id.resend_otp)).setVisibility(0);
    }

    @Override // com.coveiot.android.weeklyreport.listeners.OnSuccessListener
    public void onSuccess() {
        dismissProgress();
    }

    public final void onTextHide() {
        int i = R.id.invalid_otp;
        if (((TextView) _$_findCachedViewById(i)) != null) {
            ((TextView) _$_findCachedViewById(i)).setVisibility(8);
            ((TextView) _$_findCachedViewById(R.id.timer_message)).setVisibility(8);
            ((TextView) _$_findCachedViewById(R.id.resend_otp)).setVisibility(8);
        }
    }

    @Override // com.coveiot.android.weeklyreport.listeners.TimerListener
    public void onTimerFinished() {
        int i = R.id.timer_message;
        if (((TextView) _$_findCachedViewById(i)) != null) {
            ((TextView) _$_findCachedViewById(i)).setText(getString(R.string.didnt_recieve_otp));
        }
        int i2 = R.id.resend_otp;
        if (((TextView) _$_findCachedViewById(i2)) != null) {
            ((TextView) _$_findCachedViewById(i2)).setVisibility(0);
        }
    }

    @Override // com.coveiot.android.weeklyreport.listeners.TimerListener
    public void onTimerValueChanged(int i) {
        int i2 = R.id.timer_message;
        if (((TextView) _$_findCachedViewById(i2)) != null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = getString(R.string.you_will_receive_otp_in_30_seconds);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.you_w…eceive_otp_in_30_seconds)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            ((TextView) _$_findCachedViewById(i2)).setText(format);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        String str = getString(R.string.a_verification_otp_has_been_sent_to) + '\n' + this.o;
        SpannableString spannableString = new SpannableString(str);
        StyleSpan styleSpan = new StyleSpan(1);
        String str2 = this.o;
        Intrinsics.checkNotNull(str2);
        spannableString.setSpan(styleSpan, StringsKt__StringsKt.indexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null), str.length(), 33);
        int i = R.id.verification_message;
        ((TextView) _$_findCachedViewById(i)).setText(spannableString);
        ((TextView) _$_findCachedViewById(i)).setMovementMethod(LinkMovementMethod.getInstance());
        WeeklyReportViewModel weeklyReportViewModel = this.m;
        WeeklyReportViewModel weeklyReportViewModel2 = null;
        if (weeklyReportViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
            weeklyReportViewModel = null;
        }
        weeklyReportViewModel.setTimerListener(this);
        WeeklyReportViewModel weeklyReportViewModel3 = this.m;
        if (weeklyReportViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
            weeklyReportViewModel3 = null;
        }
        weeklyReportViewModel3.setOnSuccessListener(this);
        WeeklyReportViewModel weeklyReportViewModel4 = this.m;
        if (weeklyReportViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
        } else {
            weeklyReportViewModel2 = weeklyReportViewModel4;
        }
        weeklyReportViewModel2.startTimer(this.p);
        ((TextView) _$_findCachedViewById(R.id.resend_otp)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentVerifyEmail.u(FragmentVerifyEmail.this, view2);
            }
        });
        int i2 = R.id.otpEditText;
        ((OtpEditText) _$_findCachedViewById(i2)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.weeklyreport.fragments.FragmentVerifyEmail$onViewCreated$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
                WeeklyReportViewModel weeklyReportViewModel5;
                WeeklyReportViewModel weeklyReportViewModel6;
                FragmentVerifyEmail fragmentVerifyEmail = FragmentVerifyEmail.this;
                int i6 = R.id.otpEditText;
                if (((OtpEditText) fragmentVerifyEmail._$_findCachedViewById(i6)).getText().toString().length() == 4) {
                    WeeklyReportViewModel weeklyReportViewModel7 = null;
                    BaseFragment.showProgress$default(FragmentVerifyEmail.this, false, 1, null);
                    FragmentVerifyEmail.this.hideKeyboard();
                    FragmentVerifyEmail.this.q();
                    FragmentVerifyEmail.this.s();
                    weeklyReportViewModel5 = FragmentVerifyEmail.this.m;
                    if (weeklyReportViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
                        weeklyReportViewModel5 = null;
                    }
                    weeklyReportViewModel5.verifylOtp(Integer.parseInt(((OtpEditText) FragmentVerifyEmail.this._$_findCachedViewById(i6)).getText().toString()));
                    weeklyReportViewModel6 = FragmentVerifyEmail.this.m;
                    if (weeklyReportViewModel6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
                    } else {
                        weeklyReportViewModel7 = weeklyReportViewModel6;
                    }
                    weeklyReportViewModel7.stopTimer();
                    ((Button) FragmentVerifyEmail.this._$_findCachedViewById(R.id.btn_submit)).setEnabled(true);
                    ((TextView) FragmentVerifyEmail.this._$_findCachedViewById(R.id.report_issue_text)).setVisibility(8);
                    return;
                }
                ((Button) FragmentVerifyEmail.this._$_findCachedViewById(R.id.btn_submit)).setEnabled(false);
            }
        });
        ((OtpEditText) _$_findCachedViewById(i2)).requestFocus();
        ((TextView) _$_findCachedViewById(R.id.report_issue_text)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentVerifyEmail.v(FragmentVerifyEmail.this, view2);
            }
        });
    }

    public final void q() {
        WeeklyReportViewModel weeklyReportViewModel = this.m;
        if (weeklyReportViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
            weeklyReportViewModel = null;
        }
        MutableLiveData<String> invalidOtpUiLivedata = weeklyReportViewModel.getInvalidOtpUiLivedata();
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type androidx.lifecycle.LifecycleOwner");
        invalidOtpUiLivedata.observe((LifecycleOwner) context, new Observer() { // from class: com.coveiot.android.weeklyreport.fragments.w
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentVerifyEmail.r(FragmentVerifyEmail.this, (String) obj);
            }
        });
    }

    public final void s() {
        WeeklyReportViewModel weeklyReportViewModel = this.m;
        if (weeklyReportViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelVerifyOtp");
            weeklyReportViewModel = null;
        }
        MutableLiveData<String> successLiveData = weeklyReportViewModel.getSuccessLiveData();
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type androidx.lifecycle.LifecycleOwner");
        successLiveData.observe((LifecycleOwner) context, new Observer() { // from class: com.coveiot.android.weeklyreport.fragments.v
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentVerifyEmail.t(FragmentVerifyEmail.this, (String) obj);
            }
        });
    }

    public final void setEmailId(@Nullable String str) {
        this.o = str;
    }

    public final void setListener(@NotNull NextScreenListener nextScreenListener) {
        Intrinsics.checkNotNullParameter(nextScreenListener, "<set-?>");
        this.listener = nextScreenListener;
    }

    public final void setReportIssueDialog(@NotNull BottomSheetDialog bottomSheetDialog) {
        Intrinsics.checkNotNullParameter(bottomSheetDialog, "<set-?>");
        this.reportIssueDialog = bottomSheetDialog;
    }

    public final void setResendCount(int i) {
        this.q = i;
    }

    public final void w() {
        setReportIssueDialog(new BottomSheetDialog(requireActivity(), R.style.DialogThemeBottomSheet));
        getReportIssueDialog().setCanceledOnTouchOutside(false);
        getReportIssueDialog().setCancelable(false);
        getReportIssueDialog().setContentView(R.layout.report_issue_dialog);
        TextView textView = (TextView) getReportIssueDialog().findViewById(R.id.title);
        if (textView != null) {
            textView.setText(getString(R.string.otp_report_issue));
        }
        TextView textView2 = (TextView) getReportIssueDialog().findViewById(R.id.share_your_feedback);
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.t
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentVerifyEmail.x(FragmentVerifyEmail.this, view);
                }
            });
        }
        Button button = (Button) getReportIssueDialog().findViewById(R.id.positive_btn);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.u
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentVerifyEmail.y(FragmentVerifyEmail.this, view);
                }
            });
        }
        getReportIssueDialog().show();
    }
}
