package com.coveiot.android.leonardo.onboarding.phonevalidation.activities;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.utils.SingleLiveEvent;
import com.coveiot.android.activitymodes.utils.ViewUtilsKt;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation;
import com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterOTP;
import com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterPhoneNumber;
import com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentVerificationSuccess;
import com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation;
import com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.OTPChecksListener;
import com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ValidationCallback;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.FragmentEnterOtpViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.coveaccess.model.server.GetOTPServicesRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.NameDetails;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.WeeklyReportPrefData;
import com.coveiot.repository.datasync.domainlogic.SyncSessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import kotlin.text.Regex;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityPhoneValidation extends BaseActivity implements ValidationCallback, ContractPhoneValidation, Observer<Integer>, OTPChecksListener {
    @Nullable
    public String A;
    @Nullable
    public List<? extends GetOTPServicesRes.ServiceType> B;
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
    @Nullable
    public String p;
    public long r;
    @Nullable
    public SessionManager s;
    @Nullable
    public FragmentEnterOTP t;
    @Nullable
    public ActivityPhoneValidationViewModel u;
    @Nullable
    public String v;
    public boolean w;
    public FragmentEnterOtpViewModel y;
    public boolean z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final long q = 3000;
    public final String x = ActivityPhoneValidation.class.getSimpleName();
    @NotNull
    public final Map<String, Integer> C = new LinkedHashMap();
    @NotNull
    public final Handler D = new Handler();

    @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onPhoneNumberEntered$1", f = "ActivityPhoneValidation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
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
                ActivityPhoneValidation.this.y();
                FragmentEnterOtpViewModel fragmentEnterOtpViewModel = ActivityPhoneValidation.this.y;
                if (fragmentEnterOtpViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
                    fragmentEnterOtpViewModel = null;
                }
                fragmentEnterOtpViewModel.getOtpServices();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<List<? extends GetOTPServicesRes.ServiceType>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$setOtpServicesObserver$1$1", f = "ActivityPhoneValidation.kt", i = {}, l = {346}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ GetOTPServicesRes.ServiceType $service;
            public int label;
            public final /* synthetic */ ActivityPhoneValidation this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityPhoneValidation activityPhoneValidation, GetOTPServicesRes.ServiceType serviceType, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityPhoneValidation;
                this.$service = serviceType;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$service, continuation);
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
                    this.this$0.z = true;
                    FragmentEnterOtpViewModel fragmentEnterOtpViewModel = this.this$0.y;
                    if (fragmentEnterOtpViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
                        fragmentEnterOtpViewModel = null;
                    }
                    ActivityPhoneValidation activityPhoneValidation = this.this$0;
                    String type = this.$service.getType();
                    Intrinsics.checkNotNullExpressionValue(type, "service.type");
                    String countryCodePhoneNo = this.this$0.getCountryCodePhoneNo();
                    Intrinsics.checkNotNull(countryCodePhoneNo);
                    String replace = new Regex("\\s").replace(countryCodePhoneNo, "");
                    this.label = 1;
                    if (fragmentEnterOtpViewModel.generateAuthOtp(activityPhoneValidation, type, replace, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                this.this$0.A = this.$service.getType();
                GetOTPServicesRes.ServiceType serviceType = this.$service;
                serviceType.setAttemptsRemaining(Boxing.boxInt(serviceType.getAttemptsRemaining().intValue() - 1));
                Fragment findFragmentById = this.this$0.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (!this.this$0.isFinishing() && (findFragmentById instanceof FragmentEnterPhoneNumber)) {
                    ((FragmentEnterPhoneNumber) findFragmentById).showOTPLimitExceedError(false);
                }
                return Unit.INSTANCE;
            }
        }

        public b() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(ActivityPhoneValidation this$0, Fragment fragment) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (this$0.isFinishing() || !(fragment instanceof FragmentEnterPhoneNumber)) {
                return;
            }
            ((FragmentEnterPhoneNumber) fragment).showOTPLimitExceedError(false);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends GetOTPServicesRes.ServiceType> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<? extends GetOTPServicesRes.ServiceType> list) {
            if (ActivityPhoneValidation.this.isFinishing()) {
                return;
            }
            ActivityPhoneValidation.this.B = list;
            if (!(list == null || list.isEmpty())) {
                for (GetOTPServicesRes.ServiceType serviceType : list) {
                    Integer attemptsRemaining = serviceType.getAttemptsRemaining();
                    Intrinsics.checkNotNullExpressionValue(attemptsRemaining, "service.attemptsRemaining");
                    if (attemptsRemaining.intValue() > 0) {
                        Map map = ActivityPhoneValidation.this.C;
                        String type = serviceType.getType();
                        Intrinsics.checkNotNullExpressionValue(type, "service.type");
                        Integer attemptsRemaining2 = serviceType.getAttemptsRemaining();
                        Intrinsics.checkNotNullExpressionValue(attemptsRemaining2, "service.attemptsRemaining");
                        map.put(type, attemptsRemaining2);
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityPhoneValidation.this), null, null, new a(ActivityPhoneValidation.this, serviceType, null), 3, null);
                        return;
                    }
                }
                final Fragment findFragmentById = ActivityPhoneValidation.this.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (findFragmentById instanceof FragmentEnterPhoneNumber) {
                    ((FragmentEnterPhoneNumber) findFragmentById).showOTPLimitExceedError(true);
                    ActivityPhoneValidation.this.dismissProgress();
                }
                ActivityPhoneValidation.this.D.removeCallbacksAndMessages(null);
                Handler handler = ActivityPhoneValidation.this.D;
                final ActivityPhoneValidation activityPhoneValidation = ActivityPhoneValidation.this;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityPhoneValidation.b.invoke$lambda$0(ActivityPhoneValidation.this, findFragmentById);
                    }
                }, 10000L);
                return;
            }
            ActivityPhoneValidation.this.dismissProgress();
            ActivityPhoneValidation activityPhoneValidation2 = ActivityPhoneValidation.this;
            String string = activityPhoneValidation2.getString(R.string.something_went_wrong);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.something_went_wrong)");
            activityPhoneValidation2.showCommonMessageDialog(string, true);
        }
    }

    public static final void B(ActivityPhoneValidation this$0, BottomSheetDialog confirmExistingUserDataDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(confirmExistingUserDataDialog, "$confirmExistingUserDataDialog");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.EXISTING_PROFILE_POPUP.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_PAIRING_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CONTINUE_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        if (!AppUtils.isNetConnected(this$0)) {
            this$0.showNoInternetMessage();
            return;
        }
        confirmExistingUserDataDialog.dismiss();
        this$0.showProgress();
        ActivityPhoneValidationViewModel activityPhoneValidationViewModel = this$0.u;
        Intrinsics.checkNotNull(activityPhoneValidationViewModel);
        ProfileData profileData = ProfileData.getInstance();
        Intrinsics.checkNotNullExpressionValue(profileData, "getInstance()");
        activityPhoneValidationViewModel.callReturningUserAPI(profileData);
    }

    public static final void C(ActivityPhoneValidation this$0, BottomSheetDialog confirmExistingUserDataDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(confirmExistingUserDataDialog, "$confirmExistingUserDataDialog");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.OTP_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_EXISTING_PROFILE_POPUP.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.NEXT_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        if (!AppUtils.isNetConnected(this$0)) {
            this$0.showNoInternetMessage();
            return;
        }
        this$0.v();
        confirmExistingUserDataDialog.dismiss();
    }

    public static final void w(ActivityPhoneValidation this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitle().dismiss();
        this$0.finish();
    }

    public static final void z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void A() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.requestWindowFeature(1);
        View inflate = getLayoutInflater().inflate(R.layout.existing_user_yes_no_dialog, (ViewGroup) null);
        bottomSheetDialog.setContentView(inflate);
        ViewParent parent = inflate.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
        BottomSheetBehavior from = BottomSheetBehavior.from((View) parent);
        Intrinsics.checkNotNullExpressionValue(from, "from(view.getParent() as View)");
        from.setState(3);
        bottomSheetDialog.setCancelable(false);
        View findViewById = bottomSheetDialog.findViewById(R.id.dialog_yes_button);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.dialog_no_button);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView2 = (TextView) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.dialog_message_tv);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView3 = (TextView) findViewById3;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.existing_user_dialog_user_pic_iv);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.ImageView");
        final ImageView imageView = (ImageView) findViewById4;
        View findViewById5 = bottomSheetDialog.findViewById(R.id.user_name);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
        View findViewById6 = bottomSheetDialog.findViewById(R.id.user_mobile);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById5).setText(ProfileData.getInstance().getName());
        ((TextView) findViewById6).setText(ProfileData.getInstance().getMobileNumber());
        GlideUtils.loadImage(this, ProfileData.getInstance().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$showExistingUserDialog$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                imageView.setImageBitmap(AppUtils.getCircleBitmap(resource));
            }
        });
        textView.setText(R.string.continu);
        textView2.setText(R.string.create_new);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityPhoneValidation.B(ActivityPhoneValidation.this, bottomSheetDialog, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityPhoneValidation.C(ActivityPhoneValidation.this, bottomSheetDialog, view);
            }
        });
        bottomSheetDialog.show();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @NotNull
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitle() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.bottomSheetDialogOneButtonOneTitle;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            return bottomSheetDialogOneButtonOneTitle;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogOneButtonOneTitle");
        return null;
    }

    @Nullable
    public final String getCountryCodePhoneNo() {
        return this.v;
    }

    @Nullable
    public final FragmentEnterOTP getFragmentEnterOTP() {
        return this.t;
    }

    public final String getTAG() {
        return this.x;
    }

    @Nullable
    public final ActivityPhoneValidationViewModel getViewModelActivityPhoneValidation() {
        return this.u;
    }

    public final boolean isModifyPhoneNo() {
        return this.w;
    }

    public final void modifyPhoneNoDialog() {
        if (!getBottomSheetDialogOneButtonOneTitle().isShowing()) {
            getBottomSheetDialogOneButtonOneTitle().show();
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = getBottomSheetDialogOneButtonOneTitle();
        String string = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityPhoneValidation.w(ActivityPhoneValidation.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ActivityPhoneValidationViewModel activityPhoneValidationViewModel = this.u;
        Intrinsics.checkNotNull(activityPhoneValidationViewModel);
        activityPhoneValidationViewModel.onBackPressed();
        if (this.r + this.q > System.currentTimeMillis()) {
            finish();
        }
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (findFragmentById instanceof FragmentEnterPhoneNumber) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.PHONE_NUMBER_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_LANGUAGE_SELECTION_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        }
        if (findFragmentById instanceof FragmentEnterOTP) {
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.OTP_SCREEN.getValue());
            analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_PHONE_NUMBER_SCREEN.getValue());
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
        }
        Toast.makeText(this, getString(R.string.quit_app), 0).show();
        this.r = System.currentTimeMillis();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        MutableLiveData<Integer> fragmentState;
        super.onCreate(bundle);
        setContentView(R.layout.activity_phone_validation);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.PREMISSION_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
        SessionManager.getInstance(this).setLanguageSettings(Boolean.FALSE);
        this.s = SessionManager.getInstance(this);
        UserDataManager.getInstance(this);
        setProgressDialog(new LoadingDialog(this));
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "application");
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel = new FragmentEnterOtpViewModel(application);
        this.y = fragmentEnterOtpViewModel;
        fragmentEnterOtpViewModel.setListener(this);
        ActivityPhoneValidationViewModel activityPhoneValidationViewModel = (ActivityPhoneValidationViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityPhoneValidationViewModel.class);
        this.u = activityPhoneValidationViewModel;
        if (activityPhoneValidationViewModel != null) {
            activityPhoneValidationViewModel.getLegalDetailsFromServer();
        }
        ActivityPhoneValidationViewModel activityPhoneValidationViewModel2 = this.u;
        if (activityPhoneValidationViewModel2 != null && (fragmentState = activityPhoneValidationViewModel2.getFragmentState()) != null) {
            fragmentState.observe(this, this);
        }
        ActivityPhoneValidationViewModel activityPhoneValidationViewModel3 = this.u;
        if (activityPhoneValidationViewModel3 != null) {
            activityPhoneValidationViewModel3.loadPhoneNumberEnterFragment();
        }
        if (getIntent() != null) {
            this.w = getIntent().getBooleanExtra(AppConstants.IS_MODIFY_PHONE_NO.getValue(), false);
            this.p = getIntent().getStringExtra(AppConstants.PHONE_NUMBER.getValue());
        }
        String string = getString(R.string.modify_phone_no_success_msg);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.modify_phone_no_success_msg)");
        setBottomSheetDialogOneButtonOneTitle(new BottomSheetDialogOneButtonOneTitle(this, string));
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.OTPChecksListener
    public void onFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (isFinishing()) {
            return;
        }
        ViewUtilsKt.toast(this, message);
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation
    public void onFetchingFitnessSummary(boolean z) {
        MutableLiveData<Integer> fragmentState;
        if (z) {
            SyncSessionManager.getInstance(this).setExistingUserFirstTime(true);
            SessionManager.getInstance(this).setLanguageSettings(Boolean.TRUE);
            ActivityPhoneValidationViewModel activityPhoneValidationViewModel = this.u;
            if (activityPhoneValidationViewModel != null && (fragmentState = activityPhoneValidationViewModel.getFragmentState()) != null) {
                fragmentState.observe(this, this);
            }
            ActivityPhoneValidationViewModel activityPhoneValidationViewModel2 = this.u;
            if (activityPhoneValidationViewModel2 != null) {
                activityPhoneValidationViewModel2.loadVerificationSuccessFragment();
            }
            SessionManager sessionManager = this.s;
            Intrinsics.checkNotNull(sessionManager);
            sessionManager.createLoginSession(ProfileData.getInstance());
            SessionManager sessionManager2 = this.s;
            Intrinsics.checkNotNull(sessionManager2);
            sessionManager2.setExistingUserFirstTime(true);
            ActivityPhoneValidationViewModel activityPhoneValidationViewModel3 = this.u;
            if (activityPhoneValidationViewModel3 != null) {
                activityPhoneValidationViewModel3.fetchFitnessGoals();
            }
            if (isFinishing()) {
                return;
            }
            dismissProgress();
            return;
        }
        if (!isFinishing()) {
            dismissProgress();
        }
        Toast.makeText(this, getString(R.string.something_went_wrong), 0).show();
    }

    public final void onNewuserClick(@NotNull String phoneNumber) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        ActivityPhoneValidationViewModel activityPhoneValidationViewModel = this.u;
        if (activityPhoneValidationViewModel != null) {
            activityPhoneValidationViewModel.callNewUserApi(phoneNumber);
        }
        SessionManager.getInstance(this).setIsNewUser(true);
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ValidationCallback
    public void onOtpReceived(@NotNull String otp) {
        Intrinsics.checkNotNullParameter(otp, "otp");
        if (!AppUtils.isNetConnected(this)) {
            showNoInternetMessage();
        } else if (this.w && !isFinishing()) {
            showProgress();
            ActivityPhoneValidationViewModel activityPhoneValidationViewModel = this.u;
            if (activityPhoneValidationViewModel != null) {
                activityPhoneValidationViewModel.onCallUpdateNumberApi(otp, new ActivityPhoneValidationViewModel.OTPVerification() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onOtpReceived$1
                    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel.OTPVerification
                    public void onError(@NotNull String message, int i) {
                        Intrinsics.checkNotNullParameter(message, "message");
                        ActivityPhoneValidation.this.dismissProgress();
                        if (i == 401) {
                            if (ActivityPhoneValidation.this.getFragmentEnterOTP() != null) {
                                FragmentEnterOTP fragmentEnterOTP = ActivityPhoneValidation.this.getFragmentEnterOTP();
                                Intrinsics.checkNotNull(fragmentEnterOTP);
                                if (fragmentEnterOTP.isVisible()) {
                                    FragmentEnterOTP fragmentEnterOTP2 = ActivityPhoneValidation.this.getFragmentEnterOTP();
                                    Intrinsics.checkNotNull(fragmentEnterOTP2);
                                    fragmentEnterOTP2.onInvalidOTP();
                                    HashMap<String, Object> hashMap = new HashMap<>();
                                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                                    hashMap.putAll(companion.getDeviceId(ActivityPhoneValidation.this));
                                    companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_OTP_VERIF_FAILED.getValue(), hashMap);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        ActivityPhoneValidation activityPhoneValidation = ActivityPhoneValidation.this;
                        Toast.makeText(activityPhoneValidation, "Error " + message + " Code " + i, 0).show();
                    }

                    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel.OTPVerification
                    public void onExistingUser(@NotNull ProfileData profileData, @NotNull NameDetails nameDetails) {
                        Intrinsics.checkNotNullParameter(profileData, "profileData");
                        Intrinsics.checkNotNullParameter(nameDetails, "nameDetails");
                        ActivityPhoneValidation.this.dismissProgress();
                    }

                    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel.OTPVerification
                    public void onNewUser() {
                        SessionManager.getInstance(ActivityPhoneValidation.this).setNewUserFirstTime(true);
                        ActivityPhoneValidation.this.dismissProgress();
                        ActivityPhoneValidation.this.modifyPhoneNoDialog();
                    }
                });
            }
        } else {
            String str = this.v;
            final String replace = str != null ? new Regex("\\s").replace(str, "") : null;
            if (this.u != null) {
                if (!(replace == null || replace.length() == 0) && !isFinishing()) {
                    showProgress();
                    ActivityPhoneValidationViewModel activityPhoneValidationViewModel2 = this.u;
                    if (activityPhoneValidationViewModel2 != null) {
                        activityPhoneValidationViewModel2.verifyOtp(otp, replace, new ActivityPhoneValidationViewModel.OTPVerification() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$onOtpReceived$2
                            @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel.OTPVerification
                            public void onError(@NotNull String message, int i) {
                                Intrinsics.checkNotNullParameter(message, "message");
                                if (!ActivityPhoneValidation.this.isFinishing()) {
                                    ActivityPhoneValidation.this.dismissProgress();
                                }
                                if (i == 401) {
                                    if (ActivityPhoneValidation.this.getFragmentEnterOTP() != null) {
                                        FragmentEnterOTP fragmentEnterOTP = ActivityPhoneValidation.this.getFragmentEnterOTP();
                                        Intrinsics.checkNotNull(fragmentEnterOTP);
                                        if (fragmentEnterOTP.isVisible()) {
                                            FragmentEnterOTP fragmentEnterOTP2 = ActivityPhoneValidation.this.getFragmentEnterOTP();
                                            Intrinsics.checkNotNull(fragmentEnterOTP2);
                                            fragmentEnterOTP2.onInvalidOTP();
                                            HashMap<String, Object> hashMap = new HashMap<>();
                                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                                            hashMap.putAll(companion.getDeviceId(ActivityPhoneValidation.this));
                                            companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_OTP_VERIF_FAILED.getValue(), hashMap);
                                            return;
                                        }
                                        return;
                                    }
                                    return;
                                }
                                ActivityPhoneValidation activityPhoneValidation = ActivityPhoneValidation.this;
                                Toast.makeText(activityPhoneValidation, "Error " + message + " Code " + i, 0).show();
                            }

                            @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel.OTPVerification
                            public void onExistingUser(@NotNull ProfileData profileData, @NotNull NameDetails nameDetails) {
                                Intrinsics.checkNotNullParameter(profileData, "profileData");
                                Intrinsics.checkNotNullParameter(nameDetails, "nameDetails");
                                if (ActivityPhoneValidation.this.getFragmentEnterOTP() != null) {
                                    FragmentEnterOTP fragmentEnterOTP = ActivityPhoneValidation.this.getFragmentEnterOTP();
                                    Intrinsics.checkNotNull(fragmentEnterOTP);
                                    if (fragmentEnterOTP.isVisible()) {
                                        FragmentEnterOTP fragmentEnterOTP2 = ActivityPhoneValidation.this.getFragmentEnterOTP();
                                        Intrinsics.checkNotNull(fragmentEnterOTP2);
                                        fragmentEnterOTP2.onTextHide();
                                    }
                                }
                                ActivityPhoneValidation.this.dismissProgress();
                                AnalyticsLog analyticsLog = new AnalyticsLog();
                                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.EXISTING_PROFILE_POPUP.getValue());
                                analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_PAIRING_SCREEN.getValue());
                                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CONTINUE_BUTTON.getValue());
                                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                                if (!AppUtils.isNetConnected(ActivityPhoneValidation.this)) {
                                    ActivityPhoneValidation.this.showNoInternetMessage();
                                }
                                SessionManager.getInstance(ActivityPhoneValidation.this).setIsNewUser(false);
                                ActivityPhoneValidation.this.showProgress();
                                ActivityPhoneValidationViewModel viewModelActivityPhoneValidation = ActivityPhoneValidation.this.getViewModelActivityPhoneValidation();
                                Intrinsics.checkNotNull(viewModelActivityPhoneValidation);
                                ProfileData profileData2 = ProfileData.getInstance();
                                Intrinsics.checkNotNullExpressionValue(profileData2, "getInstance()");
                                viewModelActivityPhoneValidation.callReturningUserAPI(profileData2);
                                HashMap<String, Object> hashMap = new HashMap<>();
                                DeviceUtils.Companion companion = DeviceUtils.Companion;
                                hashMap.putAll(companion.getDeviceId(ActivityPhoneValidation.this));
                                companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_OTP_VERIF_SUCCESS.getValue(), hashMap);
                            }

                            @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel.OTPVerification
                            public void onNewUser() {
                                ActivityPhoneValidation.this.onNewuserClick(replace);
                                HashMap<String, Object> hashMap = new HashMap<>();
                                DeviceUtils.Companion companion = DeviceUtils.Companion;
                                hashMap.putAll(companion.getDeviceId(ActivityPhoneValidation.this));
                                companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_OTP_VERIF_SUCCESS.getValue(), hashMap);
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            if (isFinishing()) {
                return;
            }
            Toast.makeText(this, getString(R.string.phone_number_not_found), 0).show();
        }
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ValidationCallback
    public void onPhoneNumberEntered(@NotNull String phoneNumber, @NotNull String countryCode_PhoneNo, boolean z) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        Intrinsics.checkNotNullParameter(countryCode_PhoneNo, "countryCode_PhoneNo");
        this.v = countryCode_PhoneNo;
        String string = getString(R.string.loading);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.loading)");
        showProgresswithMsg(string);
        if (z) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new a(null), 3, null);
        } else {
            u(phoneNumber);
        }
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation
    public void onRemoveExistingUser(boolean z) {
        if (z) {
            SessionManager.getInstance(this).saveNameDetails(null);
            x();
            WeeklyReportPrefData weeklyReportData = UserDataManager.getInstance(this).getWeeklyReportData();
            if (weeklyReportData == null) {
                weeklyReportData = WeeklyReportPrefData.getInstance();
            }
            weeklyReportData.setEmailVerified(false);
            UserDataManager.getInstance(this).saveWeeklyReport(weeklyReportData);
        } else if (isFinishing()) {
        } else {
            dismissProgress();
            Toast.makeText(this, getString(R.string.something_went_wrong), 0).show();
            A();
        }
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ValidationCallback
    public void onResendOtpRequested(@NotNull String phoneNumber) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        Intrinsics.checkNotNull(this);
        if (AppUtils.isNetConnected(this)) {
            showProgress();
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new ActivityPhoneValidation$onResendOtpRequested$1(this, phoneNumber, null), 3, null);
            return;
        }
        showNoInternetMessage();
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation
    public void onReturningUser(boolean z) {
        if (z) {
            ActivityPhoneValidationViewModel activityPhoneValidationViewModel = this.u;
            if (activityPhoneValidationViewModel != null) {
                activityPhoneValidationViewModel.getFitnessConfigDataForExistingUser();
                return;
            }
            return;
        }
        dismissProgress();
        Toast.makeText(this, getString(R.string.something_went_wrong), 0).show();
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.OTPChecksListener
    public void onSuccess() {
        if (!this.z || isFinishing()) {
            return;
        }
        this.z = false;
        dismissProgress();
        Bundle bundle = new Bundle();
        String value = AppConstants.PHONE_VALID_PHONENO.getValue();
        ActivityPhoneValidationViewModel activityPhoneValidationViewModel = this.u;
        Intrinsics.checkNotNull(activityPhoneValidationViewModel);
        bundle.putString(value, activityPhoneValidationViewModel.getPhoneNumber());
        bundle.putString(AppConstants.COUNTRYCODE_PHONENO.getValue(), this.v);
        bundle.putSerializable(AppConstants.SERVICE_TYPE_LIST.getValue(), new ArrayList(this.B));
        bundle.putString(AppConstants.DEFAULT_SERVICE_TYPE.getValue(), this.A);
        FragmentEnterOTP newInstance = FragmentEnterOTP.Companion.newInstance();
        this.t = newInstance;
        Intrinsics.checkNotNull(newInstance);
        newInstance.setArguments(bundle);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentEnterOTP fragmentEnterOTP = this.t;
        Intrinsics.checkNotNull(fragmentEnterOTP);
        beginTransaction.replace(R.id.fragment_container, fragmentEnterOTP).addToBackStack(null).commit();
    }

    public final void setBottomSheetDialogOneButtonOneTitle(@NotNull BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "<set-?>");
        this.bottomSheetDialogOneButtonOneTitle = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setCountryCodePhoneNo(@Nullable String str) {
        this.v = str;
    }

    public final void setFragmentEnterOTP(@Nullable FragmentEnterOTP fragmentEnterOTP) {
        this.t = fragmentEnterOTP;
    }

    public final void setModifyPhoneNo(boolean z) {
        this.w = z;
    }

    public final void setViewModelActivityPhoneValidation(@Nullable ActivityPhoneValidationViewModel activityPhoneValidationViewModel) {
        this.u = activityPhoneValidationViewModel;
    }

    public final void u(String str) {
        ActivityPhoneValidationViewModel activityPhoneValidationViewModel = this.u;
        if (activityPhoneValidationViewModel != null) {
            activityPhoneValidationViewModel.sendOtp(false, null, str, new ActivityPhoneValidationViewModel.SendOTPCallback() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$callOTPApiWithOutCaptcha$1

                @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$callOTPApiWithOutCaptcha$1$onFailure$1", f = "ActivityPhoneValidation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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

                @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$callOTPApiWithOutCaptcha$1$onSuccess$1", f = "ActivityPhoneValidation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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

    public final void v() {
        String string = getString(R.string.confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
        String string2 = getString(R.string.remover_user_confirm_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.remover_user_confirm_msg)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$confirmUserRemoveDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DELETE_EXISTING_PROFILE_POPUP.getValue());
                analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_SIGNUP_SCREEN.getValue());
                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.YES_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                if (!AppUtils.isNetConnected(ActivityPhoneValidation.this)) {
                    ActivityPhoneValidation.this.showNoInternetMessage();
                    return;
                }
                ActivityPhoneValidation.this.showProgress();
                ActivityPhoneValidationViewModel viewModelActivityPhoneValidation = ActivityPhoneValidation.this.getViewModelActivityPhoneValidation();
                Intrinsics.checkNotNull(viewModelActivityPhoneValidation);
                ProfileData profileData = ProfileData.getInstance();
                Intrinsics.checkNotNullExpressionValue(profileData, "getInstance()");
                viewModelActivityPhoneValidation.callRemoveUserAPI(profileData);
                bottomSheetDialogTwoButtons.dismiss();
            }
        });
        String string4 = getString(R.string.f4085no);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation$confirmUserRemoveDialog$2
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DELETE_EXISTING_PROFILE_POPUP.getValue());
                analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_EXISTING_PROFILE_POPUP.getValue());
                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.NO_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                BottomSheetDialogTwoButtons.this.dismiss();
                this.A();
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    public final void x() {
        ProfileData.clearInstance();
        ProfileData profileData = ProfileData.getInstance();
        ActivityPhoneValidationViewModel activityPhoneValidationViewModel = this.u;
        profileData.setMobileNumber(activityPhoneValidationViewModel != null ? activityPhoneValidationViewModel.getPhoneNumber() : null);
        AppNavigator.Companion.navigateToProfileScreen(this);
        dismissProgress();
        finish();
    }

    public final void y() {
        FragmentEnterOtpViewModel fragmentEnterOtpViewModel = this.y;
        if (fragmentEnterOtpViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFragmentEnterOtp");
            fragmentEnterOtpViewModel = null;
        }
        SingleLiveEvent<List<GetOTPServicesRes.ServiceType>> otpServicesList = fragmentEnterOtpViewModel.getOtpServicesList();
        final b bVar = new b();
        otpServicesList.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityPhoneValidation.z(Function1.this, obj);
            }
        });
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable Integer num) {
        if (num != null && num.intValue() == 1) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(AppConstants.IS_MODIFY_PHONE_NO.getValue(), this.w);
            bundle.putString(AppConstants.PHONE_NUMBER.getValue(), this.p);
            FragmentEnterPhoneNumber.Companion companion = FragmentEnterPhoneNumber.Companion;
            FragmentEnterPhoneNumber newInstance = companion.newInstance();
            Intrinsics.checkNotNull(newInstance);
            newInstance.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newInstance).addToBackStack(companion.toString()).commit();
        } else if (num != null && num.intValue() == 2) {
            dismissProgress();
            Bundle bundle2 = new Bundle();
            String value = AppConstants.PHONE_VALID_PHONENO.getValue();
            ActivityPhoneValidationViewModel activityPhoneValidationViewModel = this.u;
            Intrinsics.checkNotNull(activityPhoneValidationViewModel);
            bundle2.putString(value, activityPhoneValidationViewModel.getPhoneNumber());
            bundle2.putString(AppConstants.COUNTRYCODE_PHONENO.getValue(), this.v);
            FragmentEnterOTP newInstance2 = FragmentEnterOTP.Companion.newInstance();
            this.t = newInstance2;
            Intrinsics.checkNotNull(newInstance2);
            newInstance2.setArguments(bundle2);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            FragmentEnterOTP fragmentEnterOTP = this.t;
            Intrinsics.checkNotNull(fragmentEnterOTP);
            beginTransaction.replace(R.id.fragment_container, fragmentEnterOTP).addToBackStack(null).commit();
        } else if (num != null && num.intValue() == 3) {
            dismissProgress();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentVerificationSuccess.Companion.newInstance()).commit();
        } else if (num != null && num.intValue() == 11) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {
                getSupportFragmentManager().popBackStack();
            }
        } else if (num != null && num.intValue() == -1) {
            if (isFinishing()) {
                return;
            }
            dismissProgress();
            Toast.makeText(this, getString(R.string.something_went_wrong), 0).show();
        } else if (num != null && num.intValue() == 0) {
            finish();
        }
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation
    public void onRemoveExistingUser(boolean z, @Nullable String str) {
        if (isFinishing()) {
            return;
        }
        if (z) {
            dismissProgress();
            x();
            return;
        }
        Toast.makeText(this, str, 0).show();
        dismissProgress();
        A();
    }
}
