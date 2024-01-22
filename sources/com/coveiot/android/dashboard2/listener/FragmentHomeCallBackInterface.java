package com.coveiot.android.dashboard2.listener;

import com.coveiot.android.dashboard2.model.ServerDataPullConfigModel;
import com.coveiot.android.theme.model.BoatCoinsScreenCaller;
import com.coveiot.android.theme.model.WatchfaceScreenCaller;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public interface FragmentHomeCallBackInterface {

    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void navigateToBoatCoinsWebViewer$default(FragmentHomeCallBackInterface fragmentHomeCallBackInterface, BoatCoinsScreenCaller boatCoinsScreenCaller, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: navigateToBoatCoinsWebViewer");
            }
            if ((i & 2) != 0) {
                str = null;
            }
            fragmentHomeCallBackInterface.navigateToBoatCoinsWebViewer(boatCoinsScreenCaller, str);
        }
    }

    void callRemoteConfigAPI(@Nullable WeatherResultListener weatherResultListener, @Nullable OnDynamicTabDataChangedListener onDynamicTabDataChangedListener);

    void deRegisterForConnectionStatusUpdate(@Nullable ConnectionStatusUpdateListener connectionStatusUpdateListener);

    void fetchRemoteConfig();

    @Nullable
    ServerDataPullConfigModel getServerSyncConfig();

    void navigateToAlexaConnect();

    void navigateToBluetoothCallingActivity();

    void navigateToBoatCoinsWebViewer(@NotNull BoatCoinsScreenCaller boatCoinsScreenCaller, @Nullable String str);

    void navigateToBuildFitnessPlan();

    void navigateToFitnessBuddies();

    void navigateToFitnessOverview();

    void navigateToFitnessVital(@NotNull String str);

    void navigateToLogin();

    void navigateToNavigationScreen();

    void navigateToNotificationActivity();

    void navigateToOnGoingFitnessPlan();

    void navigateToOneKActivity();

    void navigateToPairDevice();

    void navigateToProfileActivity();

    void navigateToSOSSettings(@NotNull String str);

    void navigateToSportsActivity();

    void navigateToTapAndPay();

    void navigateToWatchSettingsActivity(boolean z);

    void navigateToWatchfaceScreen(@NotNull WatchfaceScreenCaller watchfaceScreenCaller);

    void navigateToWatchfaceStudioActivity();

    void navigateToWellnessCrew();

    void registerForConnectionStatusUpdate(@Nullable ConnectionStatusUpdateListener connectionStatusUpdateListener);

    void registerForSyncInterruptionEvent(@Nullable SyncInterruptionListener syncInterruptionListener);

    void shouldShowIndusIndLogo(boolean z);

    void syncFailed(@Nullable String str);

    void syncSuccess();
}
