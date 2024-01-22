package com.coveiot.android.leonardo.onboarding.paring.activities;

import android.widget.Toast;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityPairing$callUserConsentApi$1 implements CoveApiListener<ActivityRes, CoveApiErrorModel> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityPairing f5216a;

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
        ActivityPairing activityPairing = this.f5216a;
        Toast.makeText(activityPairing, activityPairing.getResources().getString(R.string.failure_message), 0).show();
    }

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onSuccess(@Nullable ActivityRes activityRes) {
        ActivityPairing activityPairing = this.f5216a;
        Toast.makeText(activityPairing, activityPairing.getResources().getString(R.string.success_message), 0).show();
        UserDataManager.getInstance(this.f5216a).saveRemoteMonitoringConsent(true);
        if (!SessionManager.getInstance(this.f5216a).getIsBatteryOptimisationDone().booleanValue()) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            ActivityPairing activityPairing2 = this.f5216a;
            companion.navigateToConfigurationSettings(activityPairing2, activityPairing2.getCONFIGURATION_REQUESTCODE());
        } else {
            AppNavigator.Companion.navigateToDashBoard(this.f5216a, true);
            SessionManager.getInstance(this.f5216a).setIsFromOnboarding(true);
        }
        this.f5216a.finish();
    }
}
