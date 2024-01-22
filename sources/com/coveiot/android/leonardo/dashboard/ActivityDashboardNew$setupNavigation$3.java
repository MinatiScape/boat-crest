package com.coveiot.android.leonardo.dashboard;

import android.os.Handler;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.utils.ThemesUtils;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes2.dex */
public final class ActivityDashboardNew$setupNavigation$3 extends OnBackPressedCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityDashboardNew f4668a;
    public final /* synthetic */ NavController b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityDashboardNew$setupNavigation$3(ActivityDashboardNew activityDashboardNew, NavController navController) {
        super(true);
        this.f4668a = activityDashboardNew;
        this.b = navController;
    }

    public static final void b(ActivityDashboardNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setBackPressFlag$app_prodRelease(0);
    }

    @Override // androidx.activity.OnBackPressedCallback
    public void handleOnBackPressed() {
        Fragment findFragmentById = this.f4668a.getSupportFragmentManager().findFragmentById(R.id.mainNavigationFragment);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        NavHostFragment navHostFragment = (NavHostFragment) findFragmentById;
        NavDestination currentDestination = navHostFragment.getNavController().getCurrentDestination();
        Intrinsics.checkNotNull(currentDestination);
        if (currentDestination.getId() == R.id.fragmentHomeGuest) {
            this.f4668a.finish();
        }
        if (navHostFragment.getNavController() != null && navHostFragment.getNavController().getCurrentDestination() != null) {
            NavDestination currentDestination2 = navHostFragment.getNavController().getCurrentDestination();
            Intrinsics.checkNotNull(currentDestination2);
            if (currentDestination2.getId() == R.id.fragmentHome) {
                ActivityDashboardNew activityDashboardNew = this.f4668a;
                activityDashboardNew.setBackPressFlag$app_prodRelease(activityDashboardNew.getBackPressFlag$app_prodRelease() + 1);
                this.f4668a.getBackPressHqandler$app_prodRelease().removeCallbacksAndMessages(null);
                Handler backPressHqandler$app_prodRelease = this.f4668a.getBackPressHqandler$app_prodRelease();
                final ActivityDashboardNew activityDashboardNew2 = this.f4668a;
                backPressHqandler$app_prodRelease.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.v0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityDashboardNew$setupNavigation$3.b(ActivityDashboardNew.this);
                    }
                }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                if (this.f4668a.getBackPressFlag$app_prodRelease() >= 2) {
                    this.f4668a.finish();
                    return;
                }
                ActivityDashboardNew activityDashboardNew3 = this.f4668a;
                String string = activityDashboardNew3.getString(R.string.press_again_to_exit);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.press_again_to_exit)");
                activityDashboardNew3.makeText(activityDashboardNew3, string, 1).show();
                return;
            }
        }
        if (this.f4668a.getFromMyWatchScreen()) {
            navHostFragment.getNavController().navigate(R.id.fragmentMyWatch);
            this.f4668a.setFromMyWatchScreen(false);
            return;
        }
        navHostFragment.getNavController().popBackStack();
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this.f4668a) && !themesUtils.isPairDeviceLater(this.f4668a)) {
            this.b.navigate(R.id.fragmentHome);
        } else {
            this.b.navigate(R.id.fragmentHomeGuest);
        }
    }
}
