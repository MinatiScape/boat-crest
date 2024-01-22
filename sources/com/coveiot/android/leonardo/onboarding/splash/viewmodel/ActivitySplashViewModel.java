package com.coveiot.android.leonardo.onboarding.splash.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.BuildConfig;
import com.coveiot.android.boat.R;
import com.coveiot.android.customreminders.preference.PreferenceManagerCustomReminders;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.femalewellness.wellnesscalendar.AppPreferenceManager;
import com.coveiot.android.healthbuddies.utils.HealthBuddiesPreferenceManager;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.leonardo.googlefit.GoogleFitPreferenceManager;
import com.coveiot.android.leonardo.onboarding.splash.activities.ActivitySplash;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.AppSessionManager;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.weather.weather.WeatherAppPreferenceManager;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covepreferences.HelpManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.repository.datasync.domainlogic.SyncSessionManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySplashViewModel extends ViewModel {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int f = 567;
    public static final int g = 568;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5335a;
    @NotNull
    public final String b;
    @Nullable
    public Handler c;
    @Nullable
    public SessionManager d;
    @Nullable
    public AppSessionManager e;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getCONFIGURATION_FTU_REQUESTCODE() {
            return ActivitySplashViewModel.g;
        }

        public final int getCONFIGURATION_REQUESTCODE() {
            return ActivitySplashViewModel.f;
        }
    }

    public ActivitySplashViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5335a = context;
        this.b = "ActivitySplashViewModel";
        this.c = new Handler(Looper.getMainLooper());
        this.d = SessionManager.getInstance(context);
        this.e = AppSessionManager.getInstance(context);
    }

    public static final void i(ActivitySplashViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoveAppDatabase.getAppDatabase(this$0.f5335a).clearAllTables();
    }

    public static final void m(ActivitySplashViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.l();
    }

    public final void c(AppNotificationData appNotificationData) {
        List<AppNotificationData> appNotificationsData = UserDataManager.getInstance(this.f5335a).getAppNotificationsData();
        Intrinsics.checkNotNullExpressionValue(appNotificationsData, "getInstance(context).appNotificationsData");
        appNotificationsData.add(appNotificationData);
        UserDataManager.getInstance(this.f5335a).saveAppNotificationsSettings(appNotificationsData);
        PreferenceManager.saveAppNotificationData(this.f5335a, (ArrayList) appNotificationsData);
    }

    public final void deleteUser() {
        logOffUser();
        Thread thread = new Thread(new Runnable() { // from class: com.coveiot.android.leonardo.onboarding.splash.viewmodel.a
            @Override // java.lang.Runnable
            public final void run() {
                ActivitySplashViewModel.i(ActivitySplashViewModel.this);
            }
        });
        thread.setName("Database clean up task");
        thread.start();
    }

    @Nullable
    public final AppSessionManager getAppSessionManager() {
        return this.e;
    }

    @NotNull
    public final Context getContext() {
        return this.f5335a;
    }

    @Nullable
    public final Handler getHandler() {
        return this.c;
    }

    @Nullable
    public final SessionManager getSessionManager() {
        return this.d;
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final void h() {
        String appVersionName = SessionManager.getInstance(this.f5335a).getAppVersionName();
        if (appVersionName == null) {
            SessionManager.getInstance(this.f5335a).setAppVersionName(BuildConfig.VERSION_NAME);
        } else if (!Intrinsics.areEqual(appVersionName, BuildConfig.VERSION_NAME)) {
            if (!SessionManager.getInstance(this.f5335a).getIsUpdatedWalktarget().booleanValue()) {
                SessionManager.getInstance(this.f5335a).saveIsUpdatedWalkTarget(Boolean.TRUE);
                SyncSessionManager.getInstance(this.f5335a).setExistingUserFirstTime(true);
            }
            k();
            SessionManager.getInstance(this.f5335a).setAppVersionName(BuildConfig.VERSION_NAME);
        } else {
            SessionManager.getInstance(this.f5335a).setAppVersionName(BuildConfig.VERSION_NAME);
        }
    }

    public final AppNotificationData j(String str) {
        List<AppNotificationData> appNotificationsData = UserDataManager.getInstance(this.f5335a).getAppNotificationsData();
        Intrinsics.checkNotNullExpressionValue(appNotificationsData, "getInstance(context).appNotificationsData");
        if (!appNotificationsData.isEmpty()) {
            for (AppNotificationData appNotificationData : appNotificationsData) {
                if (Intrinsics.areEqual(appNotificationData.getPackageName(), str)) {
                    return appNotificationData;
                }
            }
        }
        return null;
    }

    public final void k() {
        List<AppNotificationData> appNotificationsData;
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3;
        if (UserDataManager.getInstance(this.f5335a).getAppNotificationsData() == null || !(!appNotificationsData.isEmpty()) || SessionManager.getInstance(this.f5335a).getDeviceType() == null) {
            return;
        }
        if (!m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.j1790_device), false) && !m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.j1810g_device), false)) {
            if (!m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.j1963d_device), false) && !m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.j1963yh_device), false) && !m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.j1860_device), false)) {
                if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.j1860_device), false)) {
                    stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_1860);
                    Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ay.app_package_list_1860)");
                    stringArray2 = this.f5335a.getResources().getStringArray(R.array.app_name_list_1860);
                    Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…array.app_name_list_1860)");
                } else {
                    if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.smaf2_device), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray3 = this.f5335a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.smas12_device), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray3 = this.f5335a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.sma_wave_genesis_pro), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray3 = this.f5335a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.sma_wave_elevate_pro), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray3 = this.f5335a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.sma_wave_glory_pro), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray3 = this.f5335a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.sma_ultima_vogue), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray3 = this.f5335a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.sma_lunar_seek), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray3 = this.f5335a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.sma_lunar_comet), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray3 = this.f5335a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.sma_lunar_velocity), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray3 = this.f5335a.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.moyangy20_device), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_moyang);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_moyang)");
                        stringArray2 = this.f5335a.getResources().getStringArray(R.array.app_name_list_moyang);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_moyang)");
                    } else if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.matrix_device), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_matrix);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_matrix)");
                        stringArray2 = this.f5335a.getResources().getStringArray(R.array.app_name_list_matrix);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_matrix)");
                    } else if (m.equals(SessionManager.getInstance(this.f5335a).getDeviceType(), this.f5335a.getResources().getString(R.string.moyangygpf5_device), false)) {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_moyang_gpf5);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…package_list_moyang_gpf5)");
                        stringArray2 = this.f5335a.getResources().getStringArray(R.array.app_name_list_moyang_gpf5);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…pp_name_list_moyang_gpf5)");
                    } else {
                        stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…R.array.app_package_list)");
                        stringArray2 = this.f5335a.getResources().getStringArray(R.array.app_name_list);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ay(R.array.app_name_list)");
                    }
                    stringArray2 = stringArray3;
                }
            } else {
                stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_1963);
                Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ay.app_package_list_1963)");
                stringArray2 = this.f5335a.getResources().getStringArray(R.array.app_name_list_1963);
                Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…array.app_name_list_1963)");
            }
        } else {
            stringArray = this.f5335a.getResources().getStringArray(R.array.app_package_list_1790);
            Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ay.app_package_list_1790)");
            stringArray2 = this.f5335a.getResources().getStringArray(R.array.app_name_list_1790);
            Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…array.app_name_list_1790)");
        }
        if (!(stringArray.length == 0)) {
            if (true ^ (stringArray2.length == 0)) {
                int length = stringArray.length;
                for (int i = 0; i < length; i++) {
                    if (j(stringArray[i]) == null) {
                        AppNotificationData appNotificationData = new AppNotificationData();
                        appNotificationData.setAppName(stringArray2[i]);
                        appNotificationData.setChecked(false);
                        appNotificationData.setPackageName(stringArray[i]);
                        c(appNotificationData);
                    }
                }
            }
        }
    }

    public final void l() {
        AppSessionManager appSessionManager = this.e;
        Intrinsics.checkNotNull(appSessionManager);
        if (appSessionManager.isFirstTimeUserComplete()) {
            SessionManager sessionManager = this.d;
            Intrinsics.checkNotNull(sessionManager);
            if (!sessionManager.isOnboardingComplete()) {
                SessionManager sessionManager2 = this.d;
                Intrinsics.checkNotNull(sessionManager2);
                if (!sessionManager2.isLoggedIn()) {
                    AppNavigator.Companion.navigateToPhoneValidationScreen(this.f5335a, false, "");
                    SessionManager.getInstance(this.f5335a).setAnalyticsUserPropertiesUpdated(false);
                } else {
                    SessionManager sessionManager3 = this.d;
                    Intrinsics.checkNotNull(sessionManager3);
                    if (!sessionManager3.isDevicePaired()) {
                        AppNavigator.Companion.navigateToBluetoothScanActivity(this.f5335a);
                    } else if (!SessionManager.getInstance(this.f5335a).getIsBatteryOptimisationDone().booleanValue()) {
                        AppNavigator.Companion.navigateToConfigurationSettings(this.f5335a, f);
                    } else {
                        AppNavigator.Companion.navigateToDashBoard(this.f5335a, false);
                    }
                }
            } else {
                AppNavigator.Companion.navigateToDashBoard(this.f5335a, false);
            }
            Context context = this.f5335a;
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            ((Activity) context).finish();
            return;
        }
        Context context2 = this.f5335a;
        if (context2 instanceof ActivitySplash) {
            Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type android.app.Activity");
            ((Activity) context2).finish();
            AppNavigator.Companion.navigateToFirstTimeUser(this.f5335a);
        }
    }

    public final void logOffUser() {
        SessionManager.getInstance(this.f5335a).setIsDevicePaired(false);
        if (BleApiManager.getInstance(this.f5335a).getBleApi() != null) {
            BleApiManager.getInstance(this.f5335a).getBleApi().stopService();
        }
        SessionManager.getInstance(this.f5335a).clearPreferences(this.f5335a);
        UserDataManager.getInstance(this.f5335a).clearPreferences(this.f5335a);
        HelpManager.getInstance(this.f5335a).clearPreferences(this.f5335a);
        AppPreferenceManager.getInstance(this.f5335a).clearPreferences(this.f5335a);
        com.coveiot.android.fitnessbuddies.utils.PreferenceManager.Companion.clearPreferences(this.f5335a);
        HealthBuddiesPreferenceManager.Companion.clearPreferences(this.f5335a);
        KHPerformancePreferenceManager.getInstance(this.f5335a).clearPreferences(this.f5335a);
        SportsPreference.Companion.clearPreferences(this.f5335a);
        PreferenceManager.clearPreferences(this.f5335a);
        WeatherAppPreferenceManager companion = WeatherAppPreferenceManager.Companion.getInstance(this.f5335a);
        if (companion != null) {
            companion.clearPreferences(this.f5335a);
        }
        new com.coveiot.android.activitymodes.preference.PreferenceManager(this.f5335a).clearPreferences(this.f5335a);
        GoogleFitPreferenceManager.getInstance(this.f5335a).clearPreferences(this.f5335a);
        com.coveiot.leaderboard.preference.PreferenceManager.clearData(this.f5335a);
        PreferenceManagerCustomReminders.Companion.clearPreferences(this.f5335a);
        Dashboard2PreferenceManager.Companion.getInstance(this.f5335a).clearPreferences();
    }

    public final void navigateAfter(long j) {
        h();
        Handler handler = this.c;
        Intrinsics.checkNotNull(handler);
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.onboarding.splash.viewmodel.b
            @Override // java.lang.Runnable
            public final void run() {
                ActivitySplashViewModel.m(ActivitySplashViewModel.this);
            }
        }, j);
    }

    public final void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (i == f) {
            AppSessionManager.getInstance(this.f5335a).setPermissionSettings(Boolean.TRUE);
            l();
        } else if (i == g) {
            if (i2 == -1) {
                AppSessionManager.getInstance(this.f5335a).setPermissionFTUSettings(Boolean.TRUE);
                l();
                return;
            }
            Context context = this.f5335a;
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            ((Activity) context).finish();
        }
    }

    public final void setAppSessionManager(@Nullable AppSessionManager appSessionManager) {
        this.e = appSessionManager;
    }

    public final void setHandler(@Nullable Handler handler) {
        this.c = handler;
    }

    public final void setSessionManager(@Nullable SessionManager sessionManager) {
        this.d = sessionManager;
    }
}
