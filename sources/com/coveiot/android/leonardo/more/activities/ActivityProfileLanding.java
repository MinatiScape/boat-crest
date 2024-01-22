package com.coveiot.android.leonardo.more.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebStorage;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.activities.ActivityFitnessPlan;
import com.coveiot.android.activitymodes.activities.ActivityHistory;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityProfileLandingPageBinding;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnessbuddies.activities.FitnessBuddiesDashBoardActivity;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.leonardo.boatcoin.ftu.activities.ActivityBoatCoinsFTU;
import com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener;
import com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel;
import com.coveiot.android.leonardo.more.adapters.ProfileAchievementsAdapter;
import com.coveiot.android.leonardo.more.adapters.ProfileFitnessBuddiesAdapter;
import com.coveiot.android.leonardo.more.adapters.ProfileGoalsAdapter;
import com.coveiot.android.leonardo.more.adapters.ProfileMoreAdapter;
import com.coveiot.android.leonardo.more.models.AchievementsModel;
import com.coveiot.android.leonardo.more.models.FitnessBuddiesModel;
import com.coveiot.android.leonardo.more.models.FitnessBuddiesSubModel;
import com.coveiot.android.leonardo.more.models.GoalsModel;
import com.coveiot.android.leonardo.more.models.ProfileMoreModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityProfileLandingViewModel;
import com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel;
import com.coveiot.android.leonardo.rateus.ActivityRateUs;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.model.BoatCoinsScreenCaller;
import com.coveiot.android.theme.utils.GridSpacingItemDecoration;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.android.weeklyreport.activities.ActivityWeeklyReport;
import com.coveiot.android.weeklyreport.utils.WeeklyReportConstant;
import com.coveiot.coveaccess.boatcoins.model.BoatCoinsDetailsResponse;
import com.coveiot.coveaccess.leaderboard.model.MyRankModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.WeeklyReportPrefData;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
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
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityProfileLanding extends BaseActivity implements ProfileAchievementsAdapter.OnAchievementsItemClickListener, ProfileGoalsAdapter.OnGoalsItemClickListener, ProfileFitnessBuddiesAdapter.OnFitnessItemClickListener, IBT3ConnectionChangeListener, ContractPhoneValidation, ProfileMoreAdapter.OnProfileMoreItemClickListener {
    @Nullable
    public BottomSheetDialogTwoButtons B;
    public int C;
    public int D;
    @NotNull
    public BoatCoinsDetailsResponse.DataBean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public int I;
    @NotNull
    public ActivityResultLauncher<Intent> J;
    public ActivityProfileLandingPageBinding p;
    public ActivityProfileLandingViewModel q;
    public BT3CallViewModel r;
    @Nullable
    public ActivityPhoneValidationViewModel s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final ProfileAchievementsAdapter t = new ProfileAchievementsAdapter(this, this);
    @NotNull
    public ArrayList<AchievementsModel> u = new ArrayList<>();
    @NotNull
    public final ProfileGoalsAdapter v = new ProfileGoalsAdapter(this, this);
    @NotNull
    public ArrayList<GoalsModel> w = new ArrayList<>();
    @NotNull
    public final ProfileFitnessBuddiesAdapter x = new ProfileFitnessBuddiesAdapter(this, this);
    @NotNull
    public ArrayList<FitnessBuddiesModel> y = new ArrayList<>();
    @NotNull
    public final ProfileMoreAdapter z = new ProfileMoreAdapter(this, this);
    @NotNull
    public ArrayList<ProfileMoreModel> A = new ArrayList<>();

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityProfileLanding$onFitnessItemClicked$1", f = "ActivityProfileLanding.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        /* renamed from: com.coveiot.android.leonardo.more.activities.ActivityProfileLanding$a$a  reason: collision with other inner class name */
        /* loaded from: classes5.dex */
        public static final class C0279a extends Lambda implements Function1<Intent, Unit> {
            public static final C0279a INSTANCE = new C0279a();

            public C0279a() {
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
                launchActivity.putExtra("isPlanHistory", true);
            }
        }

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
                ActivityProfileLanding activityProfileLanding = ActivityProfileLanding.this;
                C0279a c0279a = C0279a.INSTANCE;
                Intent intent = new Intent(activityProfileLanding, ActivityFitnessPlan.class);
                c0279a.invoke((C0279a) intent);
                activityProfileLanding.startActivityForResult(intent, -1, null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public ActivityProfileLanding() {
        new MyRankModel.DataBean.RankBean();
        this.E = new BoatCoinsDetailsResponse.DataBean();
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.coveiot.android.leonardo.more.activities.rg
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ActivityProfileLanding.f0(ActivityProfileLanding.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…etGoals()\n        }\n    }");
        this.J = registerForActivityResult;
    }

    public static final void O(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DELETE_EXISTING_PROFILE_POPUP.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_EXISTING_PROFILE_POPUP.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.NO_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void R(ActivityProfileLanding this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void S(ActivityProfileLanding this$0, String url, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (AppUtils.isNetConnected(this$0)) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            Intrinsics.checkNotNullExpressionValue(url, "url");
            companion.navigateToBoatCoinsWebViewer(this$0, url, BoatCoinsScreenCaller.PROFILE);
            ((Dialog) dialog.element).dismiss();
            return;
        }
        String string = this$0.getString(R.string.noconnection);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.noconnection)");
        ViewUtilsKt.toast(this$0, string);
    }

    public static final void T(Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((Dialog) dialog.element).dismiss();
    }

    public static final void U(ActivityProfileLanding this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SessionManager.getInstance(this$0).setIsGuestUser(false);
        SessionManager.getInstance(this$0).setOnBoardingComplete(false);
        this$0.logOutUser();
    }

    public static final void V(ActivityProfileLanding this$0, Integer it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityProfileLandingViewModel activityProfileLandingViewModel = this$0.q;
        if (activityProfileLandingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
            activityProfileLandingViewModel = null;
        }
        activityProfileLandingViewModel.getGetMyBadgesCount().removeObservers(this$0);
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.C = it.intValue();
        this$0.u0();
    }

    public static final void W(ActivityProfileLanding this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LeaderBoardNavigator.navigateToAchievementsHomeScreen(this$0, 0);
    }

    public static final void X(ActivityProfileLanding this$0, MyRankModel.DataBean.RankBean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityProfileLandingViewModel activityProfileLandingViewModel = this$0.q;
        if (activityProfileLandingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
            activityProfileLandingViewModel = null;
        }
        activityProfileLandingViewModel.getGetBestRankBean().removeObservers(this$0);
        this$0.D = it.getRank();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.u0();
    }

    public static final void Y(ActivityProfileLanding this$0, BoatCoinsDetailsResponse boatCoinsDetailsResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BoatCoinsDetailsResponse.DataBean data = boatCoinsDetailsResponse.getData();
        Intrinsics.checkNotNullExpressionValue(data, "it.data");
        this$0.E = data;
        this$0.u0();
    }

    public static final void Z(ActivityProfileLanding this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, ActivityEditProfile.class);
        intent.putExtra("fromFitnessPlan", false);
        this$0.J.launch(intent);
    }

    public static final void a0(ActivityProfileLanding this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, ActivityEditProfile.class);
        intent.putExtra("fromFitnessPlan", false);
        this$0.J.launch(intent);
    }

    public static final void b0(ActivityProfileLanding this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.l0();
    }

    public static final void c0(ActivityProfileLanding this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.N();
    }

    public static final void d0(ActivityProfileLanding this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LeaderBoardNavigator.navigateToAchievementsHomeScreen(this$0, 0);
    }

    public static final void e0(ActivityProfileLanding this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, ActivityProfileGoals.class));
    }

    public static final void f0(ActivityProfileLanding this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            this$0.j0();
            this$0.M();
            this$0.i0();
        }
    }

    public static final void m0(ActivityProfileLanding this$0, View view) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = false;
        if (ThemesUtils.INSTANCE.isPairDeviceLater(this$0)) {
            SessionManager.getInstance(this$0).setIsPairDeviceLater(false);
            this$0.P();
            this$0.logOutUser();
            return;
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isIDODevice(this$0)) {
            this$0.r0();
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.B;
            if (bottomSheetDialogTwoButtons != null) {
                bottomSheetDialogTwoButtons.dismiss();
            }
        } else {
            BleApi bleApi = BleApiManager.getInstance(this$0).getBleApi();
            if (bleApi != null && (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures.isKahaBTCallSupported()) {
                z = true;
            }
            if (!z && !companion.isTouchELXDevice(this$0)) {
                this$0.Q();
            } else {
                BT3CallViewModel bT3CallViewModel = this$0.r;
                if (bT3CallViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
                    bT3CallViewModel = null;
                }
                bT3CallViewModel.disconnectToBT3Watch();
            }
        }
        WebStorage.getInstance().deleteAllData();
        FitnessChallengeSessionManager.getInstance(this$0).saveFitnessChallengeLastSyncDate(null);
    }

    public static final void n0(ActivityProfileLanding this$0, View view) {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this$0.B;
        if (bottomSheetDialogTwoButtons2 != null) {
            boolean z = true;
            if (bottomSheetDialogTwoButtons2 == null || !bottomSheetDialogTwoButtons2.isShowing()) {
                z = false;
            }
            if (!z || (bottomSheetDialogTwoButtons = this$0.B) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.dismiss();
        }
    }

    public static final void p0(BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        guestOrPairDevicePopup.dismiss();
    }

    public static final void q0(ActivityProfileLanding this$0, BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        AppNavigator.Companion.navigateToPairYourDevice(this$0);
        guestOrPairDevicePopup.dismiss();
    }

    public static final void s0(ActivityProfileLanding this$0, Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.Q();
        dialog.dismiss();
    }

    public static final void t0(Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public final void M() {
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding = null;
        if (kotlin.text.m.equals(SessionManager.getInstance(this).getUserDetails().getName(), getString(R.string.boathead), true)) {
            ActivityProfileLandingPageBinding activityProfileLandingPageBinding2 = this.p;
            if (activityProfileLandingPageBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityProfileLandingPageBinding2 = null;
            }
            activityProfileLandingPageBinding2.profileComplete.setVisibility(0);
            this.I = 90;
        } else {
            ActivityProfileLandingPageBinding activityProfileLandingPageBinding3 = this.p;
            if (activityProfileLandingPageBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityProfileLandingPageBinding3 = null;
            }
            activityProfileLandingPageBinding3.profileComplete.setVisibility(8);
            this.I = 100;
        }
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding4 = this.p;
        if (activityProfileLandingPageBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileLandingPageBinding4 = null;
        }
        ((ProgressBar) activityProfileLandingPageBinding4.profileComplete.findViewById(R.id.progressBar)).setProgress(this.I);
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding5 = this.p;
        if (activityProfileLandingPageBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileLandingPageBinding = activityProfileLandingPageBinding5;
        }
        ((TextView) activityProfileLandingPageBinding.profileComplete.findViewById(R.id.tvProgressValue)).setText(this.I + " %");
    }

    public final void N() {
        String string = getString(R.string.delete_account);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.delete_account)");
        String string2 = getString(R.string.delete_account_user_confirm_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.delet…account_user_confirm_msg)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        bottomSheetDialogTwoButtons.setPositiveButtonDarkColor();
        bottomSheetDialogTwoButtons.setCrossButtonVisible();
        String string3 = getString(R.string.delete);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.delete)");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityProfileLanding$confirmUserRemoveDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                if (!AppUtils.isNetConnected(ActivityProfileLanding.this)) {
                    ActivityProfileLanding.this.showNoInternetMessage();
                    return;
                }
                ActivityProfileLanding.this.showProgress();
                WebStorage.getInstance().deleteAllData();
                FitnessChallengeSessionManager.getInstance(ActivityProfileLanding.this).clearPreferences(ActivityProfileLanding.this);
                ActivityPhoneValidationViewModel viewModelActivityPhoneValidation = ActivityProfileLanding.this.getViewModelActivityPhoneValidation();
                Intrinsics.checkNotNull(viewModelActivityPhoneValidation);
                ProfileData userDetails = SessionManager.getInstance(ActivityProfileLanding.this).getUserDetails();
                Intrinsics.checkNotNullExpressionValue(userDetails, "getInstance(this@Activit…ofileLanding).userDetails");
                viewModelActivityPhoneValidation.callRemoveUserAPI(userDetails);
                bottomSheetDialogTwoButtons.dismiss();
            }
        });
        String string4 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityProfileLanding$confirmUserRemoveDialog$2
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DELETE_EXISTING_PROFILE_POPUP.getValue());
                analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_EXISTING_PROFILE_POPUP.getValue());
                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.NO_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                BottomSheetDialogTwoButtons.this.dismiss();
            }
        });
        bottomSheetDialogTwoButtons.setCrossButton(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.pg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.O(BottomSheetDialogTwoButtons.this, view);
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    public final void P() {
        if (!this.H) {
            ActivityProfileLandingViewModel activityProfileLandingViewModel = this.q;
            if (activityProfileLandingViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
                activityProfileLandingViewModel = null;
            }
            activityProfileLandingViewModel.deleteUserSession();
        }
        this.H = true;
    }

    public final void Q() {
        dismissProgress();
        SyncManager.getInstance().clearBleSyncUtilsInstance();
        UserDataManager.getInstance(this).saveSyncedContacts(null);
        try {
            stopService(new Intent(this, AlexaRemoteCommandFrameworkService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    DeviceScanManager.getInstance(this).deassociateWithBle(this, BleApiManager.getInstance(this).getBleApi().getMacAddress());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            BleApiManager.getInstance(this).getBleApi().disconnect(new ConnectionResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityProfileLanding$disconnect$1
                /* JADX WARN: Code restructure failed: missing block: B:13:0x0066, code lost:
                    r6 = r5.h.B;
                 */
                @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onConnectionResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.models.ConnectionStatus r6) {
                    /*
                        r5 = this;
                        java.lang.String r0 = "status"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                        com.coveiot.analytics.AnalyticsLog r0 = new com.coveiot.analytics.AnalyticsLog
                        r0.<init>()
                        com.coveiot.android.theme.FirebaseEventParams$EventName r1 = com.coveiot.android.theme.FirebaseEventParams.EventName.KH_TAP
                        java.lang.String r1 = r1.getValue()
                        r0.setEventName(r1)
                        com.coveiot.android.theme.FirebaseEventParams$Description r1 = com.coveiot.android.theme.FirebaseEventParams.Description.DISCONNECT
                        java.lang.String r1 = r1.getValue()
                        r0.setCommandName(r1)
                        com.coveiot.android.theme.FirebaseEventParams$Description r1 = com.coveiot.android.theme.FirebaseEventParams.Description.OK
                        java.lang.String r1 = r1.getValue()
                        r0.setCommandStatus(r1)
                        com.coveiot.android.theme.FirebaseEventParams$Description r1 = com.coveiot.android.theme.FirebaseEventParams.Description.DISCONNECTED
                        java.lang.String r1 = r1.getValue()
                        r0.setDeviceConnectionStatus(r1)
                        long r1 = java.lang.System.currentTimeMillis()
                        r3 = 1000(0x3e8, float:1.401E-42)
                        long r3 = (long) r3
                        long r1 = r1 / r3
                        java.lang.String r1 = java.lang.String.valueOf(r1)
                        r0.setTimeStamp(r1)
                        com.coveiot.analytics.CoveAnalyticsManager$Companion r1 = com.coveiot.analytics.CoveAnalyticsManager.Companion
                        com.coveiot.analytics.CoveAnalyticsManager r1 = r1.getInstance()
                        r1.logEvent(r0)
                        com.coveiot.android.bleabstract.models.ConnectionStatus r0 = com.coveiot.android.bleabstract.models.ConnectionStatus.DISCONNECTED
                        if (r6 != r0) goto L8b
                        com.coveiot.android.leonardo.more.activities.ActivityProfileLanding r6 = com.coveiot.android.leonardo.more.activities.ActivityProfileLanding.this
                        com.coveiot.android.theme.BottomSheetDialogTwoButtons r6 = com.coveiot.android.leonardo.more.activities.ActivityProfileLanding.access$getBottomSheetDialogTwoButtons$p(r6)
                        r0 = 0
                        if (r6 == 0) goto L71
                        com.coveiot.android.leonardo.more.activities.ActivityProfileLanding r6 = com.coveiot.android.leonardo.more.activities.ActivityProfileLanding.this
                        com.coveiot.android.theme.BottomSheetDialogTwoButtons r6 = com.coveiot.android.leonardo.more.activities.ActivityProfileLanding.access$getBottomSheetDialogTwoButtons$p(r6)
                        r1 = 1
                        if (r6 == 0) goto L63
                        boolean r6 = r6.isShowing()
                        if (r6 != r1) goto L63
                        goto L64
                    L63:
                        r1 = r0
                    L64:
                        if (r1 == 0) goto L71
                        com.coveiot.android.leonardo.more.activities.ActivityProfileLanding r6 = com.coveiot.android.leonardo.more.activities.ActivityProfileLanding.this
                        com.coveiot.android.theme.BottomSheetDialogTwoButtons r6 = com.coveiot.android.leonardo.more.activities.ActivityProfileLanding.access$getBottomSheetDialogTwoButtons$p(r6)
                        if (r6 == 0) goto L71
                        r6.dismiss()
                    L71:
                        com.coveiot.android.leonardo.more.activities.ActivityProfileLanding r6 = com.coveiot.android.leonardo.more.activities.ActivityProfileLanding.this
                        com.coveiot.android.leonardo.more.activities.ActivityProfileLanding.access$deleteUserSession(r6)
                        com.coveiot.android.leonardo.more.activities.ActivityProfileLanding r6 = com.coveiot.android.leonardo.more.activities.ActivityProfileLanding.this
                        r1 = 2131953418(0x7f13070a, float:1.9543306E38)
                        java.lang.String r1 = r6.getString(r1)
                        android.widget.Toast r6 = android.widget.Toast.makeText(r6, r1, r0)
                        r6.show()
                        com.coveiot.android.leonardo.more.activities.ActivityProfileLanding r6 = com.coveiot.android.leonardo.more.activities.ActivityProfileLanding.this
                        r6.logOutUser()
                    L8b:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.activities.ActivityProfileLanding$disconnect$1.onConnectionResponse(com.coveiot.android.bleabstract.models.ConnectionStatus):void");
                }

                @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
                public void onError(@NotNull Error error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    AnalyticsLog analyticsLog = new AnalyticsLog();
                    analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                    analyticsLog.setCommandName(FirebaseEventParams.Description.CONNECT.getValue());
                    analyticsLog.setCommandStatus(FirebaseEventParams.Description.ERROR.getValue());
                    analyticsLog.setDeviceConnectionStatus(FirebaseEventParams.Description.CONNECTED.getValue());
                    analyticsLog.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                }
            });
            return;
        }
        BleApiManager.getInstance(this).getBleApi().stopService();
        Toast.makeText(this, getString(R.string.logout_successful), 0).show();
        P();
        logOutUser();
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

    public final void g0() {
        int i;
        Drawable drawable = getDrawable(2131231443);
        Intrinsics.checkNotNull(drawable);
        String valueOf = String.valueOf(this.C);
        String string = getString(R.string.badges);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.badges)");
        AchievementsModel achievementsModel = new AchievementsModel(drawable, valueOf, string);
        Drawable drawable2 = getDrawable(2131233269);
        Intrinsics.checkNotNull(drawable2);
        String valueOf2 = String.valueOf(this.D);
        String string2 = getString(R.string.rank);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.rank)");
        AchievementsModel achievementsModel2 = new AchievementsModel(drawable2, valueOf2, string2);
        ArrayList<AchievementsModel> arrayList = new ArrayList<>();
        this.u = arrayList;
        arrayList.add(achievementsModel);
        this.u.add(achievementsModel2);
        if (PreferenceManager.isBoatCoinsEnabled(this)) {
            Drawable drawable3 = getDrawable(2131231590);
            Intrinsics.checkNotNull(drawable3);
            String string3 = getString(R.string.boatcoins);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.boatcoins)");
            this.u.add(new AchievementsModel(drawable3, "--", string3));
            i = 3;
        } else {
            i = 2;
        }
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding = this.p;
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding2 = null;
        if (activityProfileLandingPageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileLandingPageBinding = null;
        }
        activityProfileLandingPageBinding.rvAchievements.setLayoutManager(new GridLayoutManager(this, i));
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding3 = this.p;
        if (activityProfileLandingPageBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileLandingPageBinding3 = null;
        }
        activityProfileLandingPageBinding3.rvAchievements.addItemDecoration(new GridSpacingItemDecoration(i, 10, false));
        this.t.setProfileAchievementsList(this.u);
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding4 = this.p;
        if (activityProfileLandingPageBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileLandingPageBinding2 = activityProfileLandingPageBinding4;
        }
        activityProfileLandingPageBinding2.rvAchievements.setAdapter(this.t);
    }

    public final int getProgressResult() {
        return this.I;
    }

    public final void getRecentActivity() {
        String connectedDeviceMacAddress = new com.coveiot.android.activitymodes.preference.PreferenceManager(this).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNull(connectedDeviceMacAddress);
        WorkoutSessionRepository.Companion.getInstance(this).getLastSessionLiveData(connectedDeviceMacAddress).observe(this, new Observer<EntityWorkoutSession>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityProfileLanding$getRecentActivity$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable EntityWorkoutSession entityWorkoutSession) {
                if (entityWorkoutSession != null) {
                    ActivityProfileLanding.this.F = true;
                }
            }
        });
    }

    @Nullable
    public final ActivityPhoneValidationViewModel getViewModelActivityPhoneValidation() {
        return this.s;
    }

    public final void h0() {
        ArrayList arrayList = new ArrayList();
        String string = getString(R.string.overview);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.overview)");
        arrayList.add(new FitnessBuddiesSubModel(string));
        String string2 = getString(R.string.vital_analysis);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.vital_analysis)");
        arrayList.add(new FitnessBuddiesSubModel(string2));
        ArrayList arrayList2 = new ArrayList();
        String string3 = getString(R.string.view);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.view)");
        arrayList2.add(new FitnessBuddiesSubModel(string3));
        String string4 = getString(R.string.update);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.update)");
        arrayList2.add(new FitnessBuddiesSubModel(string4));
        String string5 = getString(R.string.end_plan);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.end_plan)");
        arrayList2.add(new FitnessBuddiesSubModel(string5));
        ArrayList arrayList3 = new ArrayList();
        String string6 = getString(R.string.view);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.view)");
        arrayList3.add(new FitnessBuddiesSubModel(string6));
        String string7 = getString(R.string.edit);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.edit)");
        arrayList3.add(new FitnessBuddiesSubModel(string7));
        String string8 = getString(R.string.share_);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.share_)");
        arrayList3.add(new FitnessBuddiesSubModel(string8));
        Drawable drawable = getDrawable(R.drawable.profile_activity_report);
        Intrinsics.checkNotNull(drawable);
        String string9 = getString(R.string.activity_report);
        Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.activity_report)");
        FitnessBuddiesModel fitnessBuddiesModel = new FitnessBuddiesModel(drawable, string9, arrayList);
        Drawable drawable2 = getDrawable(R.drawable.profile_fitness_plan);
        Intrinsics.checkNotNull(drawable2);
        String string10 = getString(R.string.fitness_plan);
        Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.fitness_plan)");
        FitnessBuddiesModel fitnessBuddiesModel2 = new FitnessBuddiesModel(drawable2, string10, arrayList2);
        Drawable drawable3 = getDrawable(R.drawable.profile_my_buddies);
        Intrinsics.checkNotNull(drawable3);
        String string11 = getString(R.string.my_buddies);
        Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.my_buddies)");
        FitnessBuddiesModel fitnessBuddiesModel3 = new FitnessBuddiesModel(drawable3, string11, arrayList3);
        this.y.add(fitnessBuddiesModel);
        this.y.add(fitnessBuddiesModel2);
        this.y.add(fitnessBuddiesModel3);
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding = this.p;
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding2 = null;
        if (activityProfileLandingPageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileLandingPageBinding = null;
        }
        activityProfileLandingPageBinding.rvFitnessBuddiess.setLayoutManager(new LinearLayoutManager(this));
        this.x.setProfileFitnessBuddiesList(this.y);
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding3 = this.p;
        if (activityProfileLandingPageBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileLandingPageBinding2 = activityProfileLandingPageBinding3;
        }
        activityProfileLandingPageBinding2.rvFitnessBuddiess.setAdapter(this.x);
    }

    public final void i0() {
        Integer num;
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding = null;
        if (!ThemesUtils.INSTANCE.isPairDeviceLater(this) && (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isCalorieGoalSupported() || BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isDistanceGoalSupported())) {
            ActivityProfileLandingPageBinding activityProfileLandingPageBinding2 = this.p;
            if (activityProfileLandingPageBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityProfileLandingPageBinding2 = null;
            }
            activityProfileLandingPageBinding2.cvGoalsMore.setVisibility(0);
        }
        Drawable drawable = getDrawable(R.drawable.ic_steps_profile);
        Intrinsics.checkNotNull(drawable);
        String string = getString(R.string.steps);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.steps)");
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this).getFitnessGoalStepsData();
        Integer num2 = fitnessGoalStepsData != null ? fitnessGoalStepsData.target : null;
        GoalsModel goalsModel = new GoalsModel(drawable, 0, string, num2 == null ? 0 : num2.intValue());
        Drawable drawable2 = getDrawable(R.drawable.ic_sleep);
        Intrinsics.checkNotNull(drawable2);
        String string2 = getString(R.string.sleep);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.sleep)");
        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(this).getFitnessGoalSleepData();
        GoalsModel goalsModel2 = new GoalsModel(drawable2, 0, string2, ((fitnessGoalSleepData == null || (num = fitnessGoalSleepData.target) == null) ? 480 : num.intValue()) / 60);
        ArrayList<GoalsModel> arrayList = new ArrayList<>();
        this.w = arrayList;
        arrayList.add(goalsModel);
        this.w.add(goalsModel2);
        this.v.setProfileGoalsList(this.w);
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding3 = this.p;
        if (activityProfileLandingPageBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileLandingPageBinding = activityProfileLandingPageBinding3;
        }
        activityProfileLandingPageBinding.rvGoals.setAdapter(this.v);
    }

    public final void initToolbar() {
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding = this.p;
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding2 = null;
        if (activityProfileLandingPageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileLandingPageBinding = null;
        }
        ((TextView) activityProfileLandingPageBinding.toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.profile));
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding3 = this.p;
        if (activityProfileLandingPageBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileLandingPageBinding2 = activityProfileLandingPageBinding3;
        }
        ((TextView) activityProfileLandingPageBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.bh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.R(ActivityProfileLanding.this, view);
            }
        });
    }

    public final boolean isBoatCoinsFTU() {
        return this.G;
    }

    public final void j0() {
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding = this.p;
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding2 = null;
        if (activityProfileLandingPageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileLandingPageBinding = null;
        }
        activityProfileLandingPageBinding.tvName.setText(SessionManager.getInstance(this).getUserDetails().getName());
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding3 = this.p;
        if (activityProfileLandingPageBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileLandingPageBinding2 = activityProfileLandingPageBinding3;
        }
        activityProfileLandingPageBinding2.tvUserContact.setText(SessionManager.getInstance(this).getUserDetails().getMobileNumber());
        GlideUtils.loadScaledImage(this, SessionManager.getInstance(this).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityProfileLanding$setProfileDetails$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                ActivityProfileLandingPageBinding activityProfileLandingPageBinding4;
                Bitmap circleBitmap;
                ActivityProfileLandingPageBinding activityProfileLandingPageBinding5;
                Intrinsics.checkNotNullParameter(resource, "resource");
                activityProfileLandingPageBinding4 = ActivityProfileLanding.this.p;
                ActivityProfileLandingPageBinding activityProfileLandingPageBinding6 = null;
                if (activityProfileLandingPageBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityProfileLandingPageBinding4 = null;
                }
                if (activityProfileLandingPageBinding4.ivProfile == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                activityProfileLandingPageBinding5 = ActivityProfileLanding.this.p;
                if (activityProfileLandingPageBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityProfileLandingPageBinding6 = activityProfileLandingPageBinding5;
                }
                activityProfileLandingPageBinding6.ivProfile.setImageBitmap(circleBitmap);
            }
        });
    }

    public final void k0() {
        ArrayList<ProfileMoreModel> arrayList = this.A;
        Drawable drawable = getDrawable(R.drawable.fitness_report);
        Intrinsics.checkNotNull(drawable);
        String string = getString(R.string.fitness_report);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fitness_report)");
        arrayList.add(new ProfileMoreModel(drawable, string));
        ArrayList<ProfileMoreModel> arrayList2 = this.A;
        Drawable drawable2 = getDrawable(R.drawable.ic_rate_us_new);
        Intrinsics.checkNotNull(drawable2);
        String string2 = getString(R.string.rate_us);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.rate_us)");
        arrayList2.add(new ProfileMoreModel(drawable2, string2));
        ArrayList<ProfileMoreModel> arrayList3 = this.A;
        Drawable drawable3 = getDrawable(R.drawable.ic_share_app);
        Intrinsics.checkNotNull(drawable3);
        String string3 = getString(R.string.share_this_app);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.share_this_app)");
        arrayList3.add(new ProfileMoreModel(drawable3, string3));
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding = this.p;
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding2 = null;
        if (activityProfileLandingPageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileLandingPageBinding = null;
        }
        activityProfileLandingPageBinding.rvMore.setLayoutManager(new LinearLayoutManager(this));
        this.z.setProfileMoreList(this.A);
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding3 = this.p;
        if (activityProfileLandingPageBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileLandingPageBinding2 = activityProfileLandingPageBinding3;
        }
        activityProfileLandingPageBinding2.rvMore.setAdapter(this.z);
    }

    public final void l0() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.B;
        if (bottomSheetDialogTwoButtons2 != null) {
            boolean z = true;
            if (bottomSheetDialogTwoButtons2 == null || !bottomSheetDialogTwoButtons2.isShowing()) {
                z = false;
            }
            if (z && (bottomSheetDialogTwoButtons = this.B) != null) {
                bottomSheetDialogTwoButtons.dismiss();
            }
            this.B = null;
        }
        String string = getString(R.string.logout);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.logout)");
        String string2 = getString(R.string.logout_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.logout_msg)");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        this.B = bottomSheetDialogTwoButtons3;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        String string3 = getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
        bottomSheetDialogTwoButtons3.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.tg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.m0(ActivityProfileLanding.this, view);
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.B;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        String string4 = getString(R.string.f4085no);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
        bottomSheetDialogTwoButtons4.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ch
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.n0(ActivityProfileLanding.this, view);
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.B;
        if (bottomSheetDialogTwoButtons5 != null) {
            bottomSheetDialogTwoButtons5.show();
        }
    }

    public final void o0() {
        String string = getString(R.string.pair_device);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …rd2.R.string.pair_device)");
        String string2 = getString(R.string.please_pair_with_your_bluetooth_device_to_use_this_feature);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …vice_to_use_this_feature)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.an…shboard2.R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.og
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.p0(BottomSheetDialogTwoButtons.this, view);
            }
        });
        String string4 = getString(ThemesUtils.INSTANCE.isGuestUser(this) ? R.string.login_now : R.string.pair_now);
        Intrinsics.checkNotNullExpressionValue(string4, "if(isGuestUser())getStri…board2.R.string.pair_now)");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.mg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.q0(ActivityProfileLanding.this, bottomSheetDialogTwoButtons, view);
            }
        });
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [T, android.app.Dialog] */
    @Override // com.coveiot.android.leonardo.more.adapters.ProfileAchievementsAdapter.OnAchievementsItemClickListener
    public void onAchievementsItemClicked(@NotNull AchievementsModel achievementsModel) {
        Intrinsics.checkNotNullParameter(achievementsModel, "achievementsModel");
        if (kotlin.text.m.equals(achievementsModel.getAchievementsName(), getString(R.string.badges), true)) {
            LeaderBoardNavigator.navigateToAchievementsHomeScreen(this, 0);
        } else if (kotlin.text.m.equals(achievementsModel.getAchievementsName(), getString(R.string.rank), true)) {
            LeaderBoardNavigator.navigateToAchievementsHomeScreen(this, 1);
        } else {
            SessionManager sessionManager = SessionManager.getInstance(this);
            final String url = sessionManager.getCoinsHomeUrl();
            if (sessionManager.isBoatCoinsFTUShown()) {
                if (AppUtils.isNetConnected(this)) {
                    AppNavigator.Companion companion = AppNavigator.Companion;
                    Intrinsics.checkNotNullExpressionValue(url, "url");
                    companion.navigateToBoatCoinsWebViewer(this, url, BoatCoinsScreenCaller.PROFILE);
                    return;
                }
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                ?? dialog = new Dialog(this, 16973829);
                objectRef.element = dialog;
                ((Dialog) dialog).requestWindowFeature(1);
                ((Dialog) objectRef.element).setContentView(R.layout.no_internet_message);
                Button button = (Button) ((Dialog) objectRef.element).findViewById(R.id.btn_retry);
                if (button != null) {
                    button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ng
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityProfileLanding.S(ActivityProfileLanding.this, url, objectRef, view);
                        }
                    });
                }
                ((TextView) ((Dialog) objectRef.element).findViewById(R.id.btn_home)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.qg
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityProfileLanding.T(Ref.ObjectRef.this, view);
                    }
                });
                ((Dialog) objectRef.element).show();
                return;
            }
            Intent intent = new Intent(this, ActivityBoatCoinsFTU.class);
            intent.putExtra(AppConstants.SCREEN_NAME.getValue(), BoatCoinsScreenCaller.PROFILE.name());
            startActivity(intent);
        }
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3Connecting() {
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3ConnectionFailure(@Nullable String str) {
        Q();
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3Disconnected() {
        Q();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityProfileLandingPageBinding inflate = ActivityProfileLandingPageBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityProfileLandingViewModel activityProfileLandingViewModel = null;
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        PayUtils.fetchBadgesInBackground(this);
        this.q = (ActivityProfileLandingViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityProfileLandingViewModel.class);
        BT3CallViewModel bT3CallViewModel = (BT3CallViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(BT3CallViewModel.class);
        this.r = bT3CallViewModel;
        if (bT3CallViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
            bT3CallViewModel = null;
        }
        bT3CallViewModel.setBT3ConnectionChangeListener(this);
        this.s = (ActivityPhoneValidationViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityPhoneValidationViewModel.class);
        initToolbar();
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding2 = this.p;
        if (activityProfileLandingPageBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileLandingPageBinding2 = null;
        }
        TextView textView = activityProfileLandingPageBinding2.tvAppVersion1;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = getString(R.string.app_version_value);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.app_version_value)");
        String format = String.format(locale, string, Arrays.copyOf(new Object[]{SessionManager.getInstance(this).getAppVersionName()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        textView.setText(format);
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding3 = this.p;
        if (activityProfileLandingPageBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileLandingPageBinding3 = null;
        }
        TextView textView2 = activityProfileLandingPageBinding3.tvAppVersion;
        String string2 = getString(R.string.app_version_value);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.app_version_value)");
        String format2 = String.format(locale, string2, Arrays.copyOf(new Object[]{SessionManager.getInstance(this).getAppVersionName()}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        textView2.setText(format2);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (themesUtils.isGuestUser(this)) {
            ActivityProfileLandingPageBinding activityProfileLandingPageBinding4 = this.p;
            if (activityProfileLandingPageBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityProfileLandingPageBinding4 = null;
            }
            ConstraintLayout constraintLayout = activityProfileLandingPageBinding4.clGuestUser;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clGuestUser");
            visible(constraintLayout);
            ActivityProfileLandingPageBinding activityProfileLandingPageBinding5 = this.p;
            if (activityProfileLandingPageBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityProfileLandingPageBinding5 = null;
            }
            ConstraintLayout constraintLayout2 = activityProfileLandingPageBinding5.clLoggedInUser;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clLoggedInUser");
            gone(constraintLayout2);
            ActivityProfileLandingPageBinding activityProfileLandingPageBinding6 = this.p;
            if (activityProfileLandingPageBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityProfileLandingPageBinding = activityProfileLandingPageBinding6;
            }
            activityProfileLandingPageBinding.btnLogin.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.kg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityProfileLanding.U(ActivityProfileLanding.this, view);
                }
            });
            return;
        }
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding7 = this.p;
        if (activityProfileLandingPageBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileLandingPageBinding7 = null;
        }
        if (themesUtils.isPairDeviceLater(this)) {
            i0();
        } else {
            if (AppUtils.isNetConnected(this)) {
                showProgress();
            }
            getRecentActivity();
            g0();
            i0();
            ActivityProfileLandingViewModel activityProfileLandingViewModel2 = this.q;
            if (activityProfileLandingViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
                activityProfileLandingViewModel2 = null;
            }
            activityProfileLandingViewModel2.getMyBadges();
            ActivityProfileLandingViewModel activityProfileLandingViewModel3 = this.q;
            if (activityProfileLandingViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
                activityProfileLandingViewModel3 = null;
            }
            activityProfileLandingViewModel3.getMyRankDetails();
            ActivityProfileLandingViewModel activityProfileLandingViewModel4 = this.q;
            if (activityProfileLandingViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
                activityProfileLandingViewModel4 = null;
            }
            activityProfileLandingViewModel4.getBoatCoinsDetails();
            ActivityProfileLandingViewModel activityProfileLandingViewModel5 = this.q;
            if (activityProfileLandingViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
                activityProfileLandingViewModel5 = null;
            }
            activityProfileLandingViewModel5.getGetMyBadgesCount().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.vg
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ActivityProfileLanding.V(ActivityProfileLanding.this, (Integer) obj);
                }
            });
            ActivityProfileLandingViewModel activityProfileLandingViewModel6 = this.q;
            if (activityProfileLandingViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
                activityProfileLandingViewModel6 = null;
            }
            activityProfileLandingViewModel6.getGetBestRankBean().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.ug
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ActivityProfileLanding.X(ActivityProfileLanding.this, (MyRankModel.DataBean.RankBean) obj);
                }
            });
            ActivityProfileLandingViewModel activityProfileLandingViewModel7 = this.q;
            if (activityProfileLandingViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
            } else {
                activityProfileLandingViewModel = activityProfileLandingViewModel7;
            }
            activityProfileLandingViewModel.getGetBoatCoinsDetails().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.sg
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ActivityProfileLanding.Y(ActivityProfileLanding.this, (BoatCoinsDetailsResponse) obj);
                }
            });
        }
        j0();
        ((TextView) activityProfileLandingPageBinding7.profileComplete.findViewById(R.id.tvSyncingStatus)).setText(getString(R.string.profile_incomplete_desc));
        M();
        activityProfileLandingPageBinding7.cvViewProfile.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.wg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.Z(ActivityProfileLanding.this, view);
            }
        });
        ((ConstraintLayout) activityProfileLandingPageBinding7.profileComplete.findViewById(R.id.clCompleteProfile)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.yg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.a0(ActivityProfileLanding.this, view);
            }
        });
        activityProfileLandingPageBinding7.rvGoals.setLayoutManager(new GridLayoutManager(this, 2));
        activityProfileLandingPageBinding7.rvGoals.addItemDecoration(new GridSpacingItemDecoration(2, 30, false));
        h0();
        k0();
        if (!AppUtils.isNetConnected(this)) {
            dismissProgress();
        }
        activityProfileLandingPageBinding7.tvLogout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ah
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.b0(ActivityProfileLanding.this, view);
            }
        });
        activityProfileLandingPageBinding7.tvDeleteMyaccount.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.zg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.c0(ActivityProfileLanding.this, view);
            }
        });
        activityProfileLandingPageBinding7.tvAchievementsMore.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.dh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.d0(ActivityProfileLanding.this, view);
            }
        });
        activityProfileLandingPageBinding7.cvGoalsMore.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.jg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.e0(ActivityProfileLanding.this, view);
            }
        });
        activityProfileLandingPageBinding7.cvAchievementsFirst.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.xg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.W(ActivityProfileLanding.this, view);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation
    public void onFetchingFitnessSummary(boolean z) {
    }

    @Override // com.coveiot.android.leonardo.more.adapters.ProfileFitnessBuddiesAdapter.OnFitnessItemClickListener
    public void onFitnessItemClicked(@NotNull FitnessBuddiesModel fitnessBuddiesItem) {
        Intrinsics.checkNotNullParameter(fitnessBuddiesItem, "fitnessBuddiesItem");
        if (ThemesUtils.INSTANCE.isPairDeviceLater(this)) {
            o0();
        } else if (fitnessBuddiesItem.getFitnessBuddiesName().equals(getString(R.string.activity_report))) {
            if (this.F) {
                startActivity(new Intent(this, ActivityHistory.class));
            } else {
                Toast.makeText(this, getString(R.string.no_recent_activities), 1).show();
            }
        } else if (fitnessBuddiesItem.getFitnessBuddiesName().equals(getString(R.string.fitness_plan))) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new a(null), 2, null);
        } else {
            startActivity(new Intent(this, FitnessBuddiesDashBoardActivity.class));
        }
    }

    @Override // com.coveiot.android.leonardo.more.adapters.ProfileGoalsAdapter.OnGoalsItemClickListener
    public void onGoalsItemClicked(@NotNull GoalsModel goalsItem) {
        Intrinsics.checkNotNullParameter(goalsItem, "goalsItem");
        if (ThemesUtils.INSTANCE.isPairDeviceLater(this)) {
            o0();
        } else if (goalsItem.getGoalsName().equals(getString(R.string.steps))) {
            this.J.launch(new Intent(this, ActivityStepsGoalSettings.class));
        } else {
            this.J.launch(new Intent(this, ActivitySleepGoalSettings.class));
        }
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onInitialCheckFailed(@Nullable String str) {
    }

    @Override // com.coveiot.android.leonardo.more.adapters.ProfileMoreAdapter.OnProfileMoreItemClickListener
    public void onMoreClickListener(@NotNull ProfileMoreModel profileMoreItem) {
        Intrinsics.checkNotNullParameter(profileMoreItem, "profileMoreItem");
        if (profileMoreItem.getName().equals(getString(R.string.fitness_report))) {
            if (!ThemesUtils.INSTANCE.isPairDeviceLater(this)) {
                WeeklyReportPrefData weeklyReportData = UserDataManager.getInstance(this).getWeeklyReportData();
                Intent intent = new Intent(this, ActivityWeeklyReport.class);
                if (weeklyReportData != null && weeklyReportData.isSubscribed()) {
                    WeeklyReportConstant weeklyReportConstant = WeeklyReportConstant.SUBSCRIBE_SUCCESS;
                    intent.putExtra(weeklyReportConstant.getValue(), weeklyReportConstant.getValue());
                } else {
                    WeeklyReportConstant weeklyReportConstant2 = WeeklyReportConstant.EDIT_EMAIL;
                    intent.putExtra(weeklyReportConstant2.getValue(), weeklyReportConstant2.getValue());
                }
                startActivity(intent);
                return;
            }
            o0();
        } else if (profileMoreItem.getName().equals(getString(R.string.rate_us))) {
            if (!ThemesUtils.INSTANCE.isPairDeviceLater(this)) {
                startActivity(new Intent(this, ActivityRateUs.class));
            } else {
                o0();
            }
        } else if (profileMoreItem.getName().equals(getString(R.string.share_this_app))) {
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.SEND");
            intent2.putExtra("android.intent.extra.TEXT", getString(R.string.hey_check_out_app) + ' ' + getString(R.string.app_link_to_play_store));
            intent2.setType("text/plain");
            startActivity(intent2);
        }
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation
    public void onRemoveExistingUser(boolean z) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        boolean z2 = true;
        this.H = true;
        if (DeviceUtils.Companion.isIDODevice(this)) {
            r0();
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this.B;
            if (bottomSheetDialogTwoButtons != null) {
                bottomSheetDialogTwoButtons.dismiss();
                return;
            }
            return;
        }
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        if (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) {
            z2 = false;
        }
        if (z2) {
            BT3CallViewModel bT3CallViewModel = this.r;
            if (bT3CallViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
                bT3CallViewModel = null;
            }
            bT3CallViewModel.disconnectToBT3Watch();
            return;
        }
        Q();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (ThemesUtils.INSTANCE.isGuestUser(this)) {
            return;
        }
        i0();
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation
    public void onReturningUser(boolean z) {
    }

    public final void r0() {
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.setContentView(R.layout.pair_popup);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((Button) dialog.findViewById(R.id.btn_okay)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.lg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.s0(ActivityProfileLanding.this, dialog, view);
            }
        });
        ((ImageView) dialog.findViewById(R.id.close_icon)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ig
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileLanding.t0(dialog, view);
            }
        });
        dialog.show();
    }

    public final void setBoatCoinsFTU(boolean z) {
        this.G = z;
    }

    public final void setProgressResult(int i) {
        this.I = i;
    }

    public final void setViewModelActivityPhoneValidation(@Nullable ActivityPhoneValidationViewModel activityPhoneValidationViewModel) {
        this.s = activityPhoneValidationViewModel;
    }

    public final void u0() {
        BoatCoinsDetailsResponse.DataBean.CoinsProfile coinsProfile;
        BoatCoinsDetailsResponse.DataBean dataBean = this.E;
        ActivityProfileLandingPageBinding activityProfileLandingPageBinding = null;
        Integer coinsBalance = (dataBean == null || (coinsProfile = dataBean.getCoinsProfile()) == null) ? null : coinsProfile.getCoinsBalance();
        int intValue = coinsBalance == null ? 0 : coinsBalance.intValue();
        if (this.C == 0 && this.D == 0 && intValue == 0) {
            ActivityProfileLandingPageBinding activityProfileLandingPageBinding2 = this.p;
            if (activityProfileLandingPageBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityProfileLandingPageBinding2 = null;
            }
            ConstraintLayout constraintLayout = activityProfileLandingPageBinding2.cvAchievements;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.cvAchievements");
            gone(constraintLayout);
            ActivityProfileLandingPageBinding activityProfileLandingPageBinding3 = this.p;
            if (activityProfileLandingPageBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityProfileLandingPageBinding = activityProfileLandingPageBinding3;
            }
            ConstraintLayout constraintLayout2 = activityProfileLandingPageBinding.cvAchievementsFirst;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.cvAchievementsFirst");
            visible(constraintLayout2);
        } else {
            ActivityProfileLandingPageBinding activityProfileLandingPageBinding4 = this.p;
            if (activityProfileLandingPageBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityProfileLandingPageBinding4 = null;
            }
            ConstraintLayout constraintLayout3 = activityProfileLandingPageBinding4.cvAchievements;
            Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.cvAchievements");
            visible(constraintLayout3);
            ActivityProfileLandingPageBinding activityProfileLandingPageBinding5 = this.p;
            if (activityProfileLandingPageBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityProfileLandingPageBinding = activityProfileLandingPageBinding5;
            }
            ConstraintLayout constraintLayout4 = activityProfileLandingPageBinding.cvAchievementsFirst;
            Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.cvAchievementsFirst");
            gone(constraintLayout4);
        }
        Iterator<AchievementsModel> it = this.u.iterator();
        while (it.hasNext()) {
            AchievementsModel next = it.next();
            if (next.getAchievementsName().equals(getString(R.string.badges))) {
                ArrayList<AchievementsModel> arrayList = this.u;
                Drawable drawable = getDrawable(2131231443);
                Intrinsics.checkNotNull(drawable);
                String valueOf = String.valueOf(this.C);
                String string = getString(R.string.badges);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.badges)");
                arrayList.set(0, new AchievementsModel(drawable, valueOf, string));
            } else if (next.getAchievementsName().equals(getString(R.string.rank))) {
                ArrayList<AchievementsModel> arrayList2 = this.u;
                Drawable drawable2 = getDrawable(2131233269);
                Intrinsics.checkNotNull(drawable2);
                String valueOf2 = String.valueOf(this.D);
                String string2 = getString(R.string.rank);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.rank)");
                arrayList2.set(1, new AchievementsModel(drawable2, valueOf2, string2));
            } else {
                ArrayList<AchievementsModel> arrayList3 = this.u;
                Drawable drawable3 = getDrawable(2131231590);
                Intrinsics.checkNotNull(drawable3);
                String valueOf3 = String.valueOf(intValue);
                String string3 = getString(R.string.boatcoins);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.boatcoins)");
                arrayList3.set(2, new AchievementsModel(drawable3, valueOf3, string3));
            }
        }
        this.t.notifyDataSetChanged();
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.listeners.ContractPhoneValidation
    public void onRemoveExistingUser(boolean z, @Nullable String str) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        boolean z2 = true;
        this.H = true;
        if (DeviceUtils.Companion.isIDODevice(this)) {
            r0();
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this.B;
            if (bottomSheetDialogTwoButtons != null) {
                bottomSheetDialogTwoButtons.dismiss();
                return;
            }
            return;
        }
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        if (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) {
            z2 = false;
        }
        if (z2) {
            BT3CallViewModel bT3CallViewModel = this.r;
            if (bT3CallViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
                bT3CallViewModel = null;
            }
            bT3CallViewModel.disconnectToBT3Watch();
            return;
        }
        Q();
    }
}
