package com.coveiot.android.sportsnotification.fragment;

import androidx.appcompat.widget.SwitchCompat;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.batterysaver.BatterySaverModeSportsNotificationHelper;
/* loaded from: classes7.dex */
public final class SportsSettingsFragmentNew$onCreateView$1 implements BatterySaverModeSportsNotificationHelper.BatterySaverModeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SportsSettingsFragmentNew f5855a;

    @Override // com.coveiot.android.sportsnotification.batterysaver.BatterySaverModeSportsNotificationHelper.BatterySaverModeListener
    public void onBatterySavingSettingsReceived(boolean z, int i) {
        if (z && i == 1) {
            ((SwitchCompat) this.f5855a._$_findCachedViewById(R.id.switch_button)).setChecked(false);
        }
    }
}
