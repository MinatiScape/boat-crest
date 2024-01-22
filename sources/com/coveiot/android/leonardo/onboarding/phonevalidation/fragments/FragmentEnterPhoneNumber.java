package com.coveiot.android.leonardo.onboarding.phonevalidation.fragments;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityChooseCountry;
import com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ValidationCallback;
import com.coveiot.android.leonardo.onboarding.phonevalidation.model.CountryCodeModel;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.FragmentEnterOtpViewModel;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.FragmentEnterPhoneNumberViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.FCMTokenDataReq;
import com.coveiot.coveaccess.model.server.GuestUserSessionDataRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.phonevaildation.model.PhoneNoValidationState;
import com.coveiot.utils.utility.phonevaildation.model.PhoneValidationResponse;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentEnterPhoneNumber extends BaseFragment implements Observer<CountryCodeModel> {
    public static final int COUNTRY_CODE_RESULT = 200;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EXTRA_COUNTRY_NAME_SELECTED = "extra_country_name_selected";
    @NotNull
    public static final String EXTRA_COUNTRY_SELECTED = "extra_country_selected";
    @Nullable
    public String m;
    @Nullable
    public ValidationCallback n;
    public FragmentEnterOtpViewModel o;
    public boolean p;
    public FragmentEnterPhoneNumberViewModel s;
    @Nullable
    public View t;
    public boolean u;
    @NotNull
    public ActivityResultLauncher<IntentSenderRequest> w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String q = "FragmentEnterPhoneNumber";
    public final int r = 201;
    @NotNull
    public String v = "";

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentEnterPhoneNumber newInstance() {
            return new FragmentEnterPhoneNumber();
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<PendingIntent, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PendingIntent pendingIntent) {
            invoke2(pendingIntent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull PendingIntent result) {
            Intrinsics.checkNotNullParameter(result, "result");
            try {
                FragmentEnterPhoneNumber.this.w.launch(new IntentSenderRequest.Builder(result).build());
            } catch (Exception unused) {
                LogHelper.i(FragmentEnterPhoneNumber.this.getTAG(), "Launching the PendingIntent failed.");
                FragmentEnterPhoneNumber fragmentEnterPhoneNumber = FragmentEnterPhoneNumber.this;
                fragmentEnterPhoneNumber.openKeyBoard((EditText) fragmentEnterPhoneNumber._$_findCachedViewById(R.id.mobileNoEditText));
            }
        }
    }

    public FragmentEnterPhoneNumber() {
        ActivityResultLauncher<IntentSenderRequest> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.p
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                FragmentEnterPhoneNumber.A(FragmentEnterPhoneNumber.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…\n\n            }\n        }");
        this.w = registerForActivityResult;
    }

    public static final void A(FragmentEnterPhoneNumber this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            String phoneNumberFromIntent = Identity.getSignInClient((Activity) this$0.requireActivity()).getPhoneNumberFromIntent(activityResult.getData());
            Intrinsics.checkNotNullExpressionValue(phoneNumberFromIntent, "getSignInClient(requireA…erFromIntent(result.data)");
            if (kotlin.text.m.startsWith$default(phoneNumberFromIntent, "+", false, 2, null)) {
                EditText editText = (EditText) this$0._$_findCachedViewById(R.id.mobileNoEditText);
                if (editText != null) {
                    editText.setText(this$0.B(phoneNumberFromIntent));
                }
            } else {
                EditText editText2 = (EditText) this$0._$_findCachedViewById(R.id.mobileNoEditText);
                if (editText2 != null) {
                    editText2.setText(phoneNumberFromIntent);
                }
            }
        } catch (Exception unused) {
            LogHelper.i(this$0.q, "Phone Number Hint failed");
            this$0.openKeyBoard((EditText) this$0._$_findCachedViewById(R.id.mobileNoEditText));
        }
    }

    public static final void D(Ref.ObjectRef mFirebaseRemoteConfig, FragmentEnterPhoneNumber this$0, String phoneNo, String country_phoneno, Ref.BooleanRef useCaptcha, Task task) {
        Intrinsics.checkNotNullParameter(mFirebaseRemoteConfig, "$mFirebaseRemoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(phoneNo, "$phoneNo");
        Intrinsics.checkNotNullParameter(country_phoneno, "$country_phoneno");
        Intrinsics.checkNotNullParameter(useCaptcha, "$useCaptcha");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Boolean bool = (Boolean) task.getResult();
            boolean z = ((FirebaseRemoteConfig) mFirebaseRemoteConfig.element).getBoolean("shouldUseRecaptcha");
            ValidationCallback validationCallback = this$0.n;
            Intrinsics.checkNotNull(validationCallback);
            validationCallback.onPhoneNumberEntered(phoneNo, country_phoneno, useCaptcha.element && z);
            return;
        }
        ValidationCallback validationCallback2 = this$0.n;
        Intrinsics.checkNotNull(validationCallback2);
        validationCallback2.onPhoneNumberEntered(phoneNo, country_phoneno, useCaptcha.element);
    }

    public static /* synthetic */ void showOTPLimitExceedError$default(FragmentEnterPhoneNumber fragmentEnterPhoneNumber, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        fragmentEnterPhoneNumber.showOTPLimitExceedError(z);
    }

    public static final void t(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void u(FragmentEnterPhoneNumber this$0, Exception e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(e, "e");
        String str = this$0.q;
        StringBuilder sb = new StringBuilder();
        sb.append("addOnFailureListener ");
        e.printStackTrace();
        sb.append(Unit.INSTANCE);
        LogHelper.i(str, sb.toString());
        this$0.openKeyBoard((EditText) this$0._$_findCachedViewById(R.id.mobileNoEditText));
    }

    public static final void w(FragmentEnterPhoneNumber this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.PHONE_NUMBER_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_COUNTRY_SELECTION_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.COUNTRY_TEXT_FIELD.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.startActivityForResult(new Intent(this$0.getContext(), ActivityChooseCountry.class), this$0.r);
    }

    public static final void x(FragmentEnterPhoneNumber this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0.requireContext())) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.PHONE_NUMBER_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_OTP_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SUBMIT_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            FragmentEnterOtpViewModel fragmentEnterOtpViewModel = this$0.o;
            if (fragmentEnterOtpViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
                fragmentEnterOtpViewModel = null;
            }
            String value = ThemeConstants.OTP_RESEND_TIMER_CONFIG.getValue();
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            fragmentEnterOtpViewModel.fetchOTPResendTimer(value, requireContext);
            if (this$0.u) {
                StringBuilder sb = new StringBuilder();
                sb.append('+');
                int i = R.id.phone_valid_isdcode;
                sb.append((Object) ((TextView) this$0._$_findCachedViewById(i)).getText());
                int i2 = R.id.mobileNoEditText;
                sb.append((Object) ((EditText) this$0._$_findCachedViewById(i2)).getText());
                if (sb.toString().equals(this$0.m)) {
                    Toast.makeText(this$0.getContext(), this$0.getString(R.string.phone_number_same), 1).show();
                    return;
                }
                PhoneValidationResponse isValidPhoneNumber = AppUtils.isValidPhoneNumber(this$0.getContext(), ((EditText) this$0._$_findCachedViewById(i2)).getText().toString(), ((TextView) this$0._$_findCachedViewById(i)).getText().toString());
                AppConstants.PHONE_NO.setValue(((EditText) this$0._$_findCachedViewById(i2)).getText().toString());
                AppConstants appConstants = AppConstants.COUNTRY_CODE;
                StringBuilder sb2 = new StringBuilder();
                sb2.append('+');
                sb2.append((Object) ((TextView) this$0._$_findCachedViewById(i)).getText());
                appConstants.setValue(sb2.toString());
                String parsedMobileNumber = isValidPhoneNumber.getParsedMobileNumber();
                Intrinsics.checkNotNullExpressionValue(parsedMobileNumber, "response.parsedMobileNumber");
                this$0.C(parsedMobileNumber, '+' + ((Object) ((TextView) this$0._$_findCachedViewById(i)).getText()) + AppConstants.EMPTY_SPACE.getValue() + ((Object) ((EditText) this$0._$_findCachedViewById(i2)).getText()));
                return;
            }
            Context context = this$0.getContext();
            int i3 = R.id.mobileNoEditText;
            String obj = ((EditText) this$0._$_findCachedViewById(i3)).getText().toString();
            int i4 = R.id.phone_valid_isdcode;
            PhoneValidationResponse isValidPhoneNumber2 = AppUtils.isValidPhoneNumber(context, obj, ((TextView) this$0._$_findCachedViewById(i4)).getText().toString());
            String str = '+' + ((Object) ((TextView) this$0._$_findCachedViewById(i4)).getText()) + AppConstants.EMPTY_SPACE.getValue() + ((Object) ((EditText) this$0._$_findCachedViewById(i3)).getText());
            if (isValidPhoneNumber2.getParsedMobileNumber() != null) {
                AppConstants.PHONE_NO.setValue(((EditText) this$0._$_findCachedViewById(i3)).getText().toString());
                AppConstants appConstants2 = AppConstants.COUNTRY_CODE;
                StringBuilder sb3 = new StringBuilder();
                sb3.append('+');
                sb3.append((Object) ((TextView) this$0._$_findCachedViewById(i4)).getText());
                appConstants2.setValue(sb3.toString());
                String parsedMobileNumber2 = isValidPhoneNumber2.getParsedMobileNumber();
                Intrinsics.checkNotNullExpressionValue(parsedMobileNumber2, "response.parsedMobileNumber");
                this$0.C(parsedMobileNumber2, str);
                DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_MOBILE_OTP_SENT.getValue(), null);
                return;
            }
            Toast.makeText(this$0.getContext(), this$0.getString(R.string.please_enter_valid_phone_no), 1).show();
            return;
        }
        this$0.showNoInternetMessage();
    }

    public static final void y(FragmentEnterPhoneNumber this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtils.isValidPhoneNumber(this$0.getContext(), ((EditText) this$0._$_findCachedViewById(R.id.mobileNoEditText)).getText().toString(), ((TextView) this$0._$_findCachedViewById(R.id.phone_valid_isdcode)).getText().toString());
    }

    public static final void z(final FragmentEnterPhoneNumber this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.v.length() == 0) {
            if (AppUtils.isNetConnected(this$0.requireContext())) {
                FCMTokenDataReq fCMTokenDataReq = new FCMTokenDataReq();
                fCMTokenDataReq.setFcmRegistrationToken(SessionManager.getInstance(this$0.requireContext()).getRegistrationToken());
                CoveOnboarding.getGuestUserSessionId(fCMTokenDataReq, new CoveApiListener<GuestUserSessionDataRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterPhoneNumber$onViewCreated$7$1
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                        Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                        if (FragmentEnterPhoneNumber.this.isAdded()) {
                            Toast.makeText(FragmentEnterPhoneNumber.this.requireContext(), coveApiErrorModel.getMsg(), 0).show();
                        }
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@NotNull GuestUserSessionDataRes guestUserSessionDataRes) {
                        Intrinsics.checkNotNullParameter(guestUserSessionDataRes, "guestUserSessionDataRes");
                        if (FragmentEnterPhoneNumber.this.isAdded()) {
                            SessionManager.getInstance(FragmentEnterPhoneNumber.this.requireContext()).setGuestSessionId(guestUserSessionDataRes.getData().getGuestUserSessionId());
                            if (guestUserSessionDataRes.getData() != null && guestUserSessionDataRes.getData().getAxTrackerId() != null && SessionManager.getInstance(FragmentEnterPhoneNumber.this.getContext()).getAxTrackerId() == null) {
                                SessionManager.getInstance(FragmentEnterPhoneNumber.this.getContext()).setAxTrackerId(guestUserSessionDataRes.getData().getAxTrackerId());
                                HashMap hashMap = new HashMap();
                                hashMap.put("Identity", guestUserSessionDataRes.getData().getAxTrackerId());
                                CoveAnalyticsManager.Companion.getInstance().setOnUserLogin(hashMap);
                            }
                            FragmentEnterPhoneNumber.this.v();
                        }
                    }
                });
                DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_LOGIN_SKIPPED.getValue(), null);
                return;
            }
            this$0.showNoInternetMessage();
            return;
        }
        this$0.v();
    }

    public final String B(String str) {
        try {
            Phonenumber.PhoneNumber parse = PhoneNumberUtil.getInstance().parse(str, null);
            Intrinsics.checkNotNullExpressionValue(parse, "{\n            phoneNumbe…neNumber, null)\n        }");
            return String.valueOf(parse.getNationalNumber());
        } catch (Exception unused) {
            return str;
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [T, com.google.firebase.remoteconfig.FirebaseRemoteConfig, java.lang.Object] */
    public final void C(final String str, final String str2) {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        if (AppUtils.isNetConnected(context)) {
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = true;
            if (kotlin.text.m.equals("prod", "qa", true)) {
                booleanRef.element = ((CheckBox) _$_findCachedViewById(R.id.use_captcha)).isChecked();
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ?? firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
            Intrinsics.checkNotNullExpressionValue(firebaseRemoteConfig, "getInstance()");
            objectRef.element = firebaseRemoteConfig;
            FirebaseRemoteConfigSettings build = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0L).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder()\n              …\n                .build()");
            ((FirebaseRemoteConfig) objectRef.element).setConfigSettingsAsync(build);
            ((FirebaseRemoteConfig) objectRef.element).fetchAndActivate().addOnCompleteListener(requireActivity(), new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.q
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    FragmentEnterPhoneNumber.D(Ref.ObjectRef.this, this, str, str2, booleanRef, task);
                }
            });
            return;
        }
        showNoInternetMessage();
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
    public final String getPhoneNo() {
        return this.m;
    }

    public final boolean getSmsServiceTypeOtpLimitExceed() {
        return this.p;
    }

    @NotNull
    public final String getTAG() {
        return this.q;
    }

    @Nullable
    public final ValidationCallback getValidationCallback() {
        return this.n;
    }

    @Nullable
    public final View getView$app_prodRelease() {
        return this.t;
    }

    public final boolean isModifyPhoneNumber() {
        return this.u;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getActivity() instanceof ValidationCallback) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ValidationCallback");
            this.n = (ValidationCallback) activity;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.r && i2 == 200 && intent != null) {
            String stringExtra = intent.getStringExtra(EXTRA_COUNTRY_SELECTED);
            String stringExtra2 = intent.getStringExtra(EXTRA_COUNTRY_NAME_SELECTED);
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.COUNTRY_SELECTION_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.SELECT_COUNTRY_CODE.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.COUNTRY_SELECTION.getValue());
            Intrinsics.checkNotNull(stringExtra);
            analyticsLog.setNewCountryCode(stringExtra);
            int i3 = R.id.phone_valid_isdcode;
            analyticsLog.setOldCountryCode(((TextView) _$_findCachedViewById(i3)).getText().toString());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            ((TextView) _$_findCachedViewById(i3)).setText(stringExtra);
            FragmentEnterPhoneNumberViewModel fragmentEnterPhoneNumberViewModel = this.s;
            if (fragmentEnterPhoneNumberViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterPhoneNumber");
                fragmentEnterPhoneNumberViewModel = null;
            }
            Intrinsics.checkNotNull(stringExtra2);
            fragmentEnterPhoneNumberViewModel.onCountryCodeChanged(stringExtra, stringExtra2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = this.t;
        if (view != null) {
            Intrinsics.checkNotNull(view);
            if (view.getParent() != null) {
                View view2 = this.t;
                Intrinsics.checkNotNull(view2);
                ViewParent parent = view2.getParent();
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                ((ViewGroup) parent).removeView(this.t);
            }
            return this.t;
        }
        View inflate = inflater.inflate(R.layout.fragment_enter_phone_number_fragment, viewGroup, false);
        this.t = inflate;
        return inflate;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentEnterPhoneNumberViewModel fragmentEnterPhoneNumberViewModel = this.s;
        if (fragmentEnterPhoneNumberViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterPhoneNumber");
            fragmentEnterPhoneNumberViewModel = null;
        }
        MutableLiveData<CountryCodeModel> mSelectedCountryCode = fragmentEnterPhoneNumberViewModel.getMSelectedCountryCode();
        Intrinsics.checkNotNull(mSelectedCountryCode);
        mSelectedCountryCode.removeObservers(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentEnterPhoneNumberViewModel fragmentEnterPhoneNumberViewModel = this.s;
        if (fragmentEnterPhoneNumberViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterPhoneNumber");
            fragmentEnterPhoneNumberViewModel = null;
        }
        MutableLiveData<CountryCodeModel> mSelectedCountryCode = fragmentEnterPhoneNumberViewModel.getMSelectedCountryCode();
        Intrinsics.checkNotNull(mSelectedCountryCode);
        mSelectedCountryCode.observe(this, this);
        if (kotlin.text.m.equals("prod", "qa", true)) {
            ((CheckBox) _$_findCachedViewById(R.id.use_captcha)).setVisibility(0);
        } else {
            ((CheckBox) _$_findCachedViewById(R.id.use_captcha)).setVisibility(8);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.u = arguments.getBoolean(AppConstants.IS_MODIFY_PHONE_NO.getValue());
            Bundle arguments2 = getArguments();
            Intrinsics.checkNotNull(arguments2);
            this.m = arguments2.getString(AppConstants.PHONE_NUMBER.getValue());
        }
        if (SessionManager.getInstance(requireContext()).getGuestSessionId() != null) {
            String guestSessionId = SessionManager.getInstance(requireContext()).getGuestSessionId();
            Intrinsics.checkNotNullExpressionValue(guestSessionId, "getInstance(requireContext()).guestSessionId");
            this.v = guestSessionId;
        }
        Application application = requireActivity().getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "requireActivity().application");
        this.o = new FragmentEnterOtpViewModel(application);
        s();
        if (this.u) {
            ((Button) _$_findCachedViewById(R.id.btn_submit)).setText(getResources().getString(R.string.update));
            ((LinearLayout) _$_findCachedViewById(R.id.terms_cond_lay)).setVisibility(8);
            ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.clGuest);
            if (constraintLayout != null) {
                gone(constraintLayout);
            }
        } else {
            ((LinearLayout) _$_findCachedViewById(R.id.terms_cond_lay)).setVisibility(0);
            ((Button) _$_findCachedViewById(R.id.btn_submit)).setText(getResources().getString(R.string.send_otp));
        }
        ((LinearLayout) _$_findCachedViewById(R.id.country_flag_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentEnterPhoneNumber.w(FragmentEnterPhoneNumber.this, view2);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentEnterPhoneNumber.x(FragmentEnterPhoneNumber.this, view2);
            }
        });
        String string = getString(R.string.terms_conditions);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.terms_conditions)");
        SpannableString spannableString = new SpannableString(string);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterPhoneNumber$onViewCreated$3
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View view2) {
                FragmentEnterPhoneNumberViewModel fragmentEnterPhoneNumberViewModel;
                Intrinsics.checkNotNullParameter(view2, "view");
                FragmentEnterPhoneNumber.this.hideKeyBoard();
                Context context = FragmentEnterPhoneNumber.this.getContext();
                Intrinsics.checkNotNull(context);
                if (AppUtils.isNetConnected(context)) {
                    FragmentEnterPhoneNumberViewModel fragmentEnterPhoneNumberViewModel2 = null;
                    DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_TNC_VIEWED.getValue(), null);
                    fragmentEnterPhoneNumberViewModel = FragmentEnterPhoneNumber.this.s;
                    if (fragmentEnterPhoneNumberViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterPhoneNumber");
                    } else {
                        fragmentEnterPhoneNumberViewModel2 = fragmentEnterPhoneNumberViewModel;
                    }
                    fragmentEnterPhoneNumberViewModel2.showTermsAndConditions();
                    return;
                }
                FragmentEnterPhoneNumber.this.showNoInternetMessage();
            }
        };
        String string2 = getResources().getString(R.string.terms);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.terms)");
        int indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) string, string2, 0, false, 6, (Object) null);
        String string3 = getResources().getString(R.string.terms);
        Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.terms)");
        spannableString.setSpan(clickableSpan, indexOf$default, StringsKt__StringsKt.indexOf$default((CharSequence) string, string3, 0, false, 6, (Object) null) + getResources().getString(R.string.terms).length(), 33);
        ClickableSpan clickableSpan2 = new ClickableSpan() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterPhoneNumber$onViewCreated$4
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View view2) {
                FragmentEnterPhoneNumberViewModel fragmentEnterPhoneNumberViewModel;
                Intrinsics.checkNotNullParameter(view2, "view");
                Context context = FragmentEnterPhoneNumber.this.getContext();
                Intrinsics.checkNotNull(context);
                if (AppUtils.isNetConnected(context)) {
                    FragmentEnterPhoneNumberViewModel fragmentEnterPhoneNumberViewModel2 = null;
                    DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_PRIVACYPOLICY_VIEWED.getValue(), null);
                    fragmentEnterPhoneNumberViewModel = FragmentEnterPhoneNumber.this.s;
                    if (fragmentEnterPhoneNumberViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterPhoneNumber");
                    } else {
                        fragmentEnterPhoneNumberViewModel2 = fragmentEnterPhoneNumberViewModel;
                    }
                    fragmentEnterPhoneNumberViewModel2.showPrivacyPolicy();
                    return;
                }
                FragmentEnterPhoneNumber.this.showNoInternetMessage();
            }
        };
        String string4 = getResources().getString(R.string.privacy);
        Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.privacy)");
        int indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) string, string4, 0, false, 6, (Object) null);
        String string5 = getResources().getString(R.string.privacy);
        Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.string.privacy)");
        spannableString.setSpan(clickableSpan2, indexOf$default2, StringsKt__StringsKt.indexOf$default((CharSequence) string, string5, 0, false, 6, (Object) null) + getResources().getString(R.string.privacy_policy).length(), 33);
        int i = R.id.tv_terms;
        ((TextView) _$_findCachedViewById(i)).setText(spannableString);
        ((TextView) _$_findCachedViewById(i)).setMovementMethod(LinkMovementMethod.getInstance());
        ((CheckBox) _$_findCachedViewById(R.id.rbtn_terms)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.o
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FragmentEnterPhoneNumber.y(FragmentEnterPhoneNumber.this, compoundButton, z);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.mobileNoEditText)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterPhoneNumber$onViewCreated$6
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                PhoneValidationResponse isValidPhoneNumber = AppUtils.isValidPhoneNumber(FragmentEnterPhoneNumber.this.getContext(), ((EditText) FragmentEnterPhoneNumber.this._$_findCachedViewById(R.id.mobileNoEditText)).getText().toString(), ((TextView) FragmentEnterPhoneNumber.this._$_findCachedViewById(R.id.phone_valid_isdcode)).getText().toString());
                if (FragmentEnterPhoneNumber.this.isModifyPhoneNumber()) {
                    ((Button) FragmentEnterPhoneNumber.this._$_findCachedViewById(R.id.btn_submit)).setEnabled(isValidPhoneNumber.getPhoneNoValidationState() == PhoneNoValidationState.VALID_PHONE_NO);
                }
            }
        });
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        this.s = (FragmentEnterPhoneNumberViewModel) ViewModelProviders.of(this, new ViewModelFactory(context)).get(FragmentEnterPhoneNumberViewModel.class);
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        AppConstants appConstants = AppConstants.DEFAULT_COUNTRY_CODE;
        ((TextView) _$_findCachedViewById(R.id.phone_valid_isdcode)).setText(AppUtils.getTargetCountryCodeModel(context2, appConstants.getValue()).getIsoCode());
        FragmentEnterPhoneNumberViewModel fragmentEnterPhoneNumberViewModel = this.s;
        if (fragmentEnterPhoneNumberViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterPhoneNumber");
            fragmentEnterPhoneNumberViewModel = null;
        }
        Context context3 = getContext();
        Intrinsics.checkNotNull(context3);
        String isoCode = AppUtils.getTargetCountryCodeModel(context3, appConstants.getValue()).getIsoCode();
        Intrinsics.checkNotNullExpressionValue(isoCode, "getTargetCountryCodeMode…lue\n            ).isoCode");
        fragmentEnterPhoneNumberViewModel.onCountryCodeChanged(isoCode, "");
        ((TextView) _$_findCachedViewById(R.id.tvContinueAsGuest)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentEnterPhoneNumber.z(FragmentEnterPhoneNumber.this, view2);
            }
        });
    }

    public final void s() {
        GetPhoneNumberHintIntentRequest build = GetPhoneNumberHintIntentRequest.builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
        Task<PendingIntent> phoneNumberHintIntent = Identity.getSignInClient((Activity) requireActivity()).getPhoneNumberHintIntent(build);
        final a aVar = new a();
        phoneNumberHintIntent.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.s
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FragmentEnterPhoneNumber.t(Function1.this, obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.r
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                FragmentEnterPhoneNumber.u(FragmentEnterPhoneNumber.this, exc);
            }
        });
    }

    public final void setModifyPhoneNumber(boolean z) {
        this.u = z;
    }

    public final void setPhoneNo(@Nullable String str) {
        this.m = str;
    }

    public final void setSmsServiceTypeOtpLimitExceed(boolean z) {
        this.p = z;
    }

    public final void setTAG(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.q = str;
    }

    public final void setValidationCallback(@Nullable ValidationCallback validationCallback) {
        this.n = validationCallback;
    }

    public final void setView$app_prodRelease(@Nullable View view) {
        this.t = view;
    }

    public final void showOTPLimitExceedError(boolean z) {
        if (z) {
            TextView tvOTPAttemptErrorMsg = (TextView) _$_findCachedViewById(R.id.tvOTPAttemptErrorMsg);
            Intrinsics.checkNotNullExpressionValue(tvOTPAttemptErrorMsg, "tvOTPAttemptErrorMsg");
            visible(tvOTPAttemptErrorMsg);
            return;
        }
        TextView tvOTPAttemptErrorMsg2 = (TextView) _$_findCachedViewById(R.id.tvOTPAttemptErrorMsg);
        Intrinsics.checkNotNullExpressionValue(tvOTPAttemptErrorMsg2, "tvOTPAttemptErrorMsg");
        gone(tvOTPAttemptErrorMsg2);
    }

    public final void v() {
        if (isAdded() && isVisible()) {
            SessionManager.getInstance(requireContext()).setIsGuestUser(true);
            AppNavigator.Companion companion = AppNavigator.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.navigateToDashBoard(requireContext, true);
        }
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@NotNull CountryCodeModel countryCodeModel) {
        Intrinsics.checkNotNullParameter(countryCodeModel, "countryCodeModel");
        ((TextView) _$_findCachedViewById(R.id.countryCodeTextView)).setText(countryCodeModel.getFlagInfo());
        if (countryCodeModel.getFlagDrawable() != null) {
            ((ImageView) _$_findCachedViewById(R.id.flag)).setImageDrawable(countryCodeModel.getFlagDrawable());
        }
    }
}
