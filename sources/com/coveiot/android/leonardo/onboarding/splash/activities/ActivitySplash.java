package com.coveiot.android.leonardo.onboarding.splash.activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.lifecycle.ViewModelProviders;
import com.airbnb.lottie.LottieAnimationView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.onboarding.splash.viewmodel.ActivitySplashViewModel;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySplash extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final long p = 3000;
    @Nullable
    public ActivitySplashViewModel q;
    @Nullable
    public SessionManager r;

    public static final void r(ActivitySplash this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppNavigator.Companion.navigateToPermissionCheck(this$0);
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

    @Nullable
    public final SessionManager getSessionManager() {
        return this.r;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        ActivitySplashViewModel activitySplashViewModel = this.q;
        Intrinsics.checkNotNull(activitySplashViewModel);
        activitySplashViewModel.onActivityResult(i, i2, intent);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        ActivitySplashViewModel activitySplashViewModel;
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        FirebaseConstants.PREVIOUS_SCREEN_NAME.setValue(FirebaseEventParams.ScreenName.MAIN_SPLASH.getValue());
        PayUtils payUtils = PayUtils.INSTANCE;
        payUtils.getBatteryOptimizationConfig(this);
        this.q = (ActivitySplashViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivitySplashViewModel.class);
        this.r = SessionManager.getInstance(this);
        if (getIntent().hasExtra("IS_FROM_SESSION_EXPIRY") && getIntent().getBooleanExtra("IS_FROM_SESSION_EXPIRY", false) && (activitySplashViewModel = this.q) != null) {
            activitySplashViewModel.deleteUser();
        }
        SessionManager sessionManager = this.r;
        Intrinsics.checkNotNull(sessionManager);
        if (sessionManager.isOnboardingComplete() && Build.VERSION.SDK_INT >= 31) {
            if (AppUtils.checkIfLocationPermissionExists(this) && payUtils.checkIfBluetoothPermissionExists(this) && payUtils.checkIfNotificationPermissionExists(this)) {
                s();
                return;
            }
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            if (!themesUtils.isGuestUser(this) && !themesUtils.isPairDeviceLater(this)) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.onboarding.splash.activities.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivitySplash.r(ActivitySplash.this);
                    }
                }, this.p);
                return;
            } else {
                s();
                return;
            }
        }
        s();
    }

    public final void s() {
        int i = R.id.splash_animation_2;
        ((LottieAnimationView) _$_findCachedViewById(i)).setAnimation(R.raw.splash_screen_without_logo_v09);
        ((LottieAnimationView) _$_findCachedViewById(i)).playAnimation();
        ((LottieAnimationView) _$_findCachedViewById(i)).addAnimatorListener(new Animator.AnimatorListener() { // from class: com.coveiot.android.leonardo.onboarding.splash.activities.ActivitySplash$startNavigation$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(@NotNull Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@NotNull Animator animation, boolean z) {
                ActivitySplashViewModel activitySplashViewModel;
                Intrinsics.checkNotNullParameter(animation, "animation");
                activitySplashViewModel = ActivitySplash.this.q;
                Intrinsics.checkNotNull(activitySplashViewModel);
                activitySplashViewModel.navigateAfter(0L);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(@NotNull Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(@NotNull Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(@NotNull Animator animation, boolean z) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@NotNull Animator animation) {
                ActivitySplashViewModel activitySplashViewModel;
                Intrinsics.checkNotNullParameter(animation, "animation");
                activitySplashViewModel = ActivitySplash.this.q;
                Intrinsics.checkNotNull(activitySplashViewModel);
                activitySplashViewModel.navigateAfter(0L);
            }
        });
    }

    public final void setSessionManager(@Nullable SessionManager sessionManager) {
        this.r = sessionManager;
    }
}
