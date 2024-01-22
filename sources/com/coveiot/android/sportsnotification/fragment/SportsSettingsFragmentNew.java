package com.coveiot.android.sportsnotification.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.SportsNotificationActivity;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsType;
import com.coveiot.android.sportsnotification.SportsUtils;
import com.coveiot.android.sportsnotification.adapter.SportIntervalAdapter;
import com.coveiot.android.sportsnotification.adapter.SportSettingAdapter;
import com.coveiot.android.sportsnotification.batterysaver.BatterySaverModeSportsNotificationHelper;
import com.coveiot.android.sportsnotification.model.CoinNotifications;
import com.coveiot.android.sportsnotification.model.Filters;
import com.coveiot.android.sportsnotification.model.SettingsModel;
import com.coveiot.android.sportsnotification.model.SoccerFilterConfig;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.sportsnotification.viewmodel.SportsSettingFragmentViewModelNew;
import com.coveiot.android.sportsnotificationsdk.network.SportType;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiClient;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SportsSettingsFragmentNew extends BaseFragment implements SportSettingAdapter.SportSelectSelectListener, BatterySaverModeDialogClickCallback, SportsSettingFragmentViewModelNew.SettingsUpdateListener, SportIntervalAdapter.IntervalSelectListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public BottomSheetDialogImageTitleMessageTwoBtns A;
    @Nullable
    public ArrayList<SettingsModel> m;
    @Nullable
    public ArrayList<SettingsModel> n;
    @Nullable
    public SportSettingAdapter o;
    @Nullable
    public SportIntervalAdapter p;
    @Nullable
    public Context q;
    @Nullable
    public SportsSettingFragmentViewModelNew r;
    public boolean s;
    public boolean t;
    @Nullable
    public String u;
    @Nullable
    public Integer v;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage y;
    @Nullable
    public SportsPreferenceModel z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int w = 10;
    public int x = 2;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SportsSettingsFragmentNew newInstance() {
            return new SportsSettingsFragmentNew();
        }
    }

    public static final void K(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<SportsType> selectedSportType = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedSportType() : null;
        if (selectedSportType == null) {
            return;
        }
        selectedSportType.setValue(SportsType.Football);
    }

    public static final void L(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<SportsType> selectedSportType = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedSportType() : null;
        if (selectedSportType == null) {
            return;
        }
        selectedSportType.setValue(SportsType.Football);
    }

    public static final void M(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<SportsType> selectedSportType = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedSportType() : null;
        if (selectedSportType == null) {
            return;
        }
        selectedSportType.setValue(SportsType.Football);
    }

    public static final void N(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<SportsType> selectedSportType = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedSportType() : null;
        if (selectedSportType == null) {
            return;
        }
        selectedSportType.setValue(SportsType.Football);
    }

    public static final void O(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<SportsType> selectedSportType = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedSportType() : null;
        if (selectedSportType == null) {
            return;
        }
        selectedSportType.setValue(SportsType.Football);
    }

    public static final void P(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<String> selectedCricketMatchFormat = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedCricketMatchFormat() : null;
        if (selectedCricketMatchFormat == null) {
            return;
        }
        selectedCricketMatchFormat.setValue("T20");
    }

    public static final void Q(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<String> selectedCricketMatchFormat = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedCricketMatchFormat() : null;
        if (selectedCricketMatchFormat == null) {
            return;
        }
        selectedCricketMatchFormat.setValue("ODI");
    }

    public static final void R(SportsSettingsFragmentNew this$0, View view) {
        Integer num;
        Integer[] odiIntervalList;
        Integer[] odiIntervalList2;
        MutableLiveData<Integer> selectedODIInterval;
        MutableLiveData<String> selectedCricketMatchFormat;
        Integer num2;
        Integer[] t20IntervalList;
        Integer[] t20IntervalList2;
        MutableLiveData<Integer> selectedT20Interval;
        MutableLiveData<String> selectedCricketMatchFormat2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        Integer num3 = null;
        String value = (sportsSettingFragmentViewModelNew == null || (selectedCricketMatchFormat2 = sportsSettingFragmentViewModelNew.getSelectedCricketMatchFormat()) == null) ? null : selectedCricketMatchFormat2.getValue();
        Intrinsics.checkNotNull(value);
        if (value.equals("T20")) {
            SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew2 = this$0.r;
            if (sportsSettingFragmentViewModelNew2 == null || (t20IntervalList2 = sportsSettingFragmentViewModelNew2.getT20IntervalList()) == null) {
                num2 = null;
            } else {
                SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew3 = this$0.r;
                num2 = Integer.valueOf(ArraysKt___ArraysKt.indexOf(t20IntervalList2, (sportsSettingFragmentViewModelNew3 == null || (selectedT20Interval = sportsSettingFragmentViewModelNew3.getSelectedT20Interval()) == null) ? null : selectedT20Interval.getValue()));
            }
            Intrinsics.checkNotNull(num2);
            int intValue = num2.intValue();
            if (intValue > 0) {
                SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew4 = this$0.r;
                MutableLiveData<Integer> selectedT20Interval2 = sportsSettingFragmentViewModelNew4 != null ? sportsSettingFragmentViewModelNew4.getSelectedT20Interval() : null;
                if (selectedT20Interval2 != null) {
                    SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew5 = this$0.r;
                    if (sportsSettingFragmentViewModelNew5 != null && (t20IntervalList = sportsSettingFragmentViewModelNew5.getT20IntervalList()) != null) {
                        num3 = t20IntervalList[intValue - 1];
                    }
                    selectedT20Interval2.setValue(num3);
                }
                ((Button) this$0._$_findCachedViewById(R.id.get_cricket_score_updates)).setEnabled(true);
                return;
            }
            return;
        }
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew6 = this$0.r;
        String value2 = (sportsSettingFragmentViewModelNew6 == null || (selectedCricketMatchFormat = sportsSettingFragmentViewModelNew6.getSelectedCricketMatchFormat()) == null) ? null : selectedCricketMatchFormat.getValue();
        Intrinsics.checkNotNull(value2);
        if (value2.equals("ODI")) {
            SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew7 = this$0.r;
            if (sportsSettingFragmentViewModelNew7 == null || (odiIntervalList2 = sportsSettingFragmentViewModelNew7.getOdiIntervalList()) == null) {
                num = null;
            } else {
                SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew8 = this$0.r;
                num = Integer.valueOf(ArraysKt___ArraysKt.indexOf(odiIntervalList2, (sportsSettingFragmentViewModelNew8 == null || (selectedODIInterval = sportsSettingFragmentViewModelNew8.getSelectedODIInterval()) == null) ? null : selectedODIInterval.getValue()));
            }
            Intrinsics.checkNotNull(num);
            int intValue2 = num.intValue();
            if (intValue2 > 0) {
                SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew9 = this$0.r;
                MutableLiveData<Integer> selectedODIInterval2 = sportsSettingFragmentViewModelNew9 != null ? sportsSettingFragmentViewModelNew9.getSelectedODIInterval() : null;
                if (selectedODIInterval2 != null) {
                    SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew10 = this$0.r;
                    if (sportsSettingFragmentViewModelNew10 != null && (odiIntervalList = sportsSettingFragmentViewModelNew10.getOdiIntervalList()) != null) {
                        num3 = odiIntervalList[intValue2 - 1];
                    }
                    selectedODIInterval2.setValue(num3);
                }
                ((Button) this$0._$_findCachedViewById(R.id.get_cricket_score_updates)).setEnabled(true);
            }
        }
    }

    public static final void S(SportsSettingsFragmentNew this$0, View view) {
        Integer num;
        Integer[] odiIntervalList;
        Integer[] odiIntervalList2;
        MutableLiveData<Integer> selectedODIInterval;
        MutableLiveData<String> selectedCricketMatchFormat;
        Integer num2;
        Integer[] t20IntervalList;
        Integer[] t20IntervalList2;
        MutableLiveData<Integer> selectedT20Interval;
        MutableLiveData<String> selectedCricketMatchFormat2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        Integer num3 = null;
        String value = (sportsSettingFragmentViewModelNew == null || (selectedCricketMatchFormat2 = sportsSettingFragmentViewModelNew.getSelectedCricketMatchFormat()) == null) ? null : selectedCricketMatchFormat2.getValue();
        Intrinsics.checkNotNull(value);
        if (value.equals("T20")) {
            SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew2 = this$0.r;
            if (sportsSettingFragmentViewModelNew2 == null || (t20IntervalList2 = sportsSettingFragmentViewModelNew2.getT20IntervalList()) == null) {
                num2 = null;
            } else {
                SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew3 = this$0.r;
                num2 = Integer.valueOf(ArraysKt___ArraysKt.indexOf(t20IntervalList2, (sportsSettingFragmentViewModelNew3 == null || (selectedT20Interval = sportsSettingFragmentViewModelNew3.getSelectedT20Interval()) == null) ? null : selectedT20Interval.getValue()));
            }
            Intrinsics.checkNotNull(num2);
            int intValue = num2.intValue() + 1;
            SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew4 = this$0.r;
            Integer[] t20IntervalList3 = sportsSettingFragmentViewModelNew4 != null ? sportsSettingFragmentViewModelNew4.getT20IntervalList() : null;
            Intrinsics.checkNotNull(t20IntervalList3);
            if (intValue < t20IntervalList3.length) {
                SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew5 = this$0.r;
                MutableLiveData<Integer> selectedT20Interval2 = sportsSettingFragmentViewModelNew5 != null ? sportsSettingFragmentViewModelNew5.getSelectedT20Interval() : null;
                if (selectedT20Interval2 != null) {
                    SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew6 = this$0.r;
                    if (sportsSettingFragmentViewModelNew6 != null && (t20IntervalList = sportsSettingFragmentViewModelNew6.getT20IntervalList()) != null) {
                        num3 = t20IntervalList[intValue];
                    }
                    selectedT20Interval2.setValue(num3);
                }
                ((Button) this$0._$_findCachedViewById(R.id.get_cricket_score_updates)).setEnabled(true);
                return;
            }
            return;
        }
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew7 = this$0.r;
        String value2 = (sportsSettingFragmentViewModelNew7 == null || (selectedCricketMatchFormat = sportsSettingFragmentViewModelNew7.getSelectedCricketMatchFormat()) == null) ? null : selectedCricketMatchFormat.getValue();
        Intrinsics.checkNotNull(value2);
        if (value2.equals("ODI")) {
            SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew8 = this$0.r;
            if (sportsSettingFragmentViewModelNew8 == null || (odiIntervalList2 = sportsSettingFragmentViewModelNew8.getOdiIntervalList()) == null) {
                num = null;
            } else {
                SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew9 = this$0.r;
                num = Integer.valueOf(ArraysKt___ArraysKt.indexOf(odiIntervalList2, (sportsSettingFragmentViewModelNew9 == null || (selectedODIInterval = sportsSettingFragmentViewModelNew9.getSelectedODIInterval()) == null) ? null : selectedODIInterval.getValue()));
            }
            Intrinsics.checkNotNull(num);
            int intValue2 = num.intValue() + 1;
            SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew10 = this$0.r;
            Integer[] odiIntervalList3 = sportsSettingFragmentViewModelNew10 != null ? sportsSettingFragmentViewModelNew10.getOdiIntervalList() : null;
            Intrinsics.checkNotNull(odiIntervalList3);
            if (intValue2 < odiIntervalList3.length) {
                SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew11 = this$0.r;
                MutableLiveData<Integer> selectedODIInterval2 = sportsSettingFragmentViewModelNew11 != null ? sportsSettingFragmentViewModelNew11.getSelectedODIInterval() : null;
                if (selectedODIInterval2 != null) {
                    SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew12 = this$0.r;
                    if (sportsSettingFragmentViewModelNew12 != null && (odiIntervalList = sportsSettingFragmentViewModelNew12.getOdiIntervalList()) != null) {
                        num3 = odiIntervalList[intValue2];
                    }
                    selectedODIInterval2.setValue(num3);
                }
                ((Button) this$0._$_findCachedViewById(R.id.get_cricket_score_updates)).setEnabled(true);
            }
        }
    }

    public static final void T(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((Button) this$0._$_findCachedViewById(R.id.btn_done)).performClick();
    }

    public static final void U(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((Button) this$0._$_findCachedViewById(R.id.btn_done)).performClick();
    }

    public static final void V(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<SportsType> selectedSportType = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedSportType() : null;
        if (selectedSportType == null) {
            return;
        }
        selectedSportType.setValue(SportsType.Cricket);
    }

    public static final void W(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<SportsType> selectedSportType = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedSportType() : null;
        if (selectedSportType == null) {
            return;
        }
        selectedSportType.setValue(SportsType.Cricket);
    }

    public static final void X(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<SportsType> selectedSportType = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedSportType() : null;
        if (selectedSportType == null) {
            return;
        }
        selectedSportType.setValue(SportsType.Cricket);
    }

    public static final void Y(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<SportsType> selectedSportType = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedSportType() : null;
        if (selectedSportType == null) {
            return;
        }
        selectedSportType.setValue(SportsType.Cricket);
    }

    public static final void Z(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        MutableLiveData<SportsType> selectedSportType = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedSportType() : null;
        if (selectedSportType == null) {
            return;
        }
        selectedSportType.setValue(SportsType.Cricket);
    }

    public static final void a0(SportsSettingsFragmentNew this$0, Integer it) {
        MutableLiveData<String> selectedCricketMatchFormat;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.x = it.intValue();
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        if (kotlin.text.m.equals$default((sportsSettingFragmentViewModelNew == null || (selectedCricketMatchFormat = sportsSettingFragmentViewModelNew.getSelectedCricketMatchFormat()) == null) ? null : selectedCricketMatchFormat.getValue(), "T20", false, 2, null)) {
            ((TextView) this$0._$_findCachedViewById(R.id.tv_interval_value)).setText(it + " Mins");
        }
    }

    public static final void b0(SportsSettingsFragmentNew this$0, Integer it) {
        MutableLiveData<String> selectedCricketMatchFormat;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.w = it.intValue();
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this$0.r;
        if (kotlin.text.m.equals$default((sportsSettingFragmentViewModelNew == null || (selectedCricketMatchFormat = sportsSettingFragmentViewModelNew.getSelectedCricketMatchFormat()) == null) ? null : selectedCricketMatchFormat.getValue(), "ODI", false, 2, null)) {
            ((TextView) this$0._$_findCachedViewById(R.id.tv_interval_value)).setText(it + " Mins");
        }
    }

    public static final void c0(SportsSettingsFragmentNew this$0, List list, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.y;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
                BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this$0.y;
                Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
                bottomSheetDialogOneButtonTitleMessage2.dismiss();
            }
        }
        Context context = this$0.q;
        Intrinsics.checkNotNull(context);
        SportsUtils.isBoatCoinsEnabled(context, new SportsSettingsFragmentNew$onSettingSaved$1$1(list, this$0));
    }

    public static final void d0(SportsSettingsFragmentNew this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.s = z;
        this$0.t = z;
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CRICKET_SETTINGS_SCORE_PUSH.getValue());
        analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.SELECT_MATCH_SCREEN.getValue());
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.SPORTS_SETTINGS.getValue());
        analyticsLog.setCVValue(String.valueOf(z));
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.updateListVisibilty(z);
    }

    public static final void e0(final SportsSettingsFragmentNew this$0, CompoundButton compoundButton, final boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0.getContext()) != null && BleApiManager.getInstance(this$0.getContext()).getBleApi() != null && BleApiManager.getInstance(this$0.getContext()).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            BatterySaverModeSportsNotificationHelper.Companion companion = BatterySaverModeSportsNotificationHelper.Companion;
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            companion.getInstance(requireActivity).getBatterySaverMode(new BatterySaverModeSportsNotificationHelper.BatterySaverModeListener() { // from class: com.coveiot.android.sportsnotification.fragment.SportsSettingsFragmentNew$onViewCreated$2$1
                @Override // com.coveiot.android.sportsnotification.batterysaver.BatterySaverModeSportsNotificationHelper.BatterySaverModeListener
                public void onBatterySavingSettingsReceived(boolean z2, int i) {
                    if (z2 && i == 1) {
                        ((SwitchCompat) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.switch_button)).setChecked(false);
                        SportsSettingsFragmentNew sportsSettingsFragmentNew = SportsSettingsFragmentNew.this;
                        sportsSettingsFragmentNew.showBatterySaverModeEnabledDialog(sportsSettingsFragmentNew);
                        return;
                    }
                    SportsSettingsFragmentNew.this.setSportsSettingSelcted(z);
                    SportsSettingsFragmentNew.this.setSettingSelcted(z);
                    AnalyticsLog analyticsLog = new AnalyticsLog();
                    analyticsLog.setEventName(FirebaseEventParams.EventName.CRICKET_SETTINGS_SCORE_PUSH.getValue());
                    analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.SELECT_MATCH_SCREEN.getValue());
                    analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.SPORTS_SETTINGS.getValue());
                    analyticsLog.setCVValue(String.valueOf(z));
                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                    SportsSettingsFragmentNew.this.updateListVisibilty(z);
                }
            });
        } else {
            this$0.s = z;
            this$0.t = z;
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.CRICKET_SETTINGS_SCORE_PUSH.getValue());
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.SELECT_MATCH_SCREEN.getValue());
            analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.SPORTS_SETTINGS.getValue());
            analyticsLog.setCVValue(String.valueOf(z));
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            this$0.updateListVisibilty(z);
        }
        int i = R.id.btn_done;
        ((Button) this$0._$_findCachedViewById(i)).setEnabled(this$0.k0());
        if (this$0.k0()) {
            if (AppUtils.isNetConnected(this$0.requireContext())) {
                if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    ((Button) this$0._$_findCachedViewById(i)).performClick();
                    return;
                }
                Toast.makeText(this$0.q, R.string.band_not_connected, 1).show();
                int i2 = R.id.switch_button;
                ((SwitchCompat) this$0._$_findCachedViewById(i2)).setChecked(!((SwitchCompat) this$0._$_findCachedViewById(i2)).isChecked());
                return;
            }
            Toast.makeText(this$0.q, R.string.please_check_network, 1).show();
            int i3 = R.id.switch_button;
            ((SwitchCompat) this$0._$_findCachedViewById(i3)).setChecked(!((SwitchCompat) this$0._$_findCachedViewById(i3)).isChecked());
        }
    }

    public static final void f0(SportsSettingsFragmentNew this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.btn_done;
        ((Button) this$0._$_findCachedViewById(i)).setEnabled(this$0.k0());
        if (this$0.k0()) {
            if (AppUtils.isNetConnected(this$0.requireContext())) {
                if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    ((Button) this$0._$_findCachedViewById(i)).performClick();
                    return;
                }
                Toast.makeText(this$0.q, R.string.band_not_connected, 1).show();
                int i2 = R.id.switch_vibrate;
                ((SwitchCompat) this$0._$_findCachedViewById(i2)).setChecked(!((SwitchCompat) this$0._$_findCachedViewById(i2)).isChecked());
                return;
            }
            Toast.makeText(this$0.q, R.string.please_check_network, 1).show();
            int i3 = R.id.switch_vibrate;
            ((SwitchCompat) this$0._$_findCachedViewById(i3)).setChecked(!((SwitchCompat) this$0._$_findCachedViewById(i3)).isChecked());
        }
    }

    public static final void g0(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.q)) {
            Toast.makeText(this$0.q, R.string.please_check_network, 1).show();
        } else if (BleApiManager.getInstance(this$0.q).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            String str = this$0.u;
            Intrinsics.checkNotNull(str);
            if (str.equals(SportsType.Cricket)) {
                SportsApiClient.Companion.resetSportsApi(this$0.requireContext(), SportType.CRICKET);
            } else {
                String str2 = this$0.u;
                Intrinsics.checkNotNull(str2);
                if (str2.equals(SportsType.Football)) {
                    SportsApiClient.Companion.resetSportsApi(this$0.requireContext(), SportType.SOCCER);
                }
            }
            SportsPreference.Companion companion = SportsPreference.Companion;
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (companion.getSportsNotification(requireContext) != null) {
                Context requireContext2 = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                SportsPreferenceModel sportsNotification = companion.getSportsNotification(requireContext2);
                if (!kotlin.text.m.equals$default(sportsNotification != null ? sportsNotification.getSport() : null, this$0.u, false, 2, null)) {
                    this$0.l0();
                    return;
                }
            }
            this$0.j0();
        } else {
            Toast.makeText(this$0.q, R.string.band_not_connected, 1).show();
        }
    }

    public static final void h0(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.sportsnotification.SportsNotificationActivity");
        ((SportsNotificationActivity) requireActivity).onBackPressed();
    }

    public static final void m0(SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this$0.A;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
        bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
    }

    public final void J(View view, boolean z) {
        if (!(view instanceof ViewGroup)) {
            if (view != null) {
                view.setClickable(z);
                return;
            }
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        viewGroup.setClickable(z);
        int i = 0;
        int childCount = viewGroup.getChildCount() - 1;
        if (childCount < 0) {
            return;
        }
        while (true) {
            J(viewGroup.getChildAt(i), z);
            if (i == childCount) {
                return;
            }
            i++;
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

    @Nullable
    public final BottomSheetDialogImageTitleMessageTwoBtns getBottomSheetDialogImageTitleMessageTwoBtns() {
        return this.A;
    }

    @Nullable
    public final Integer getInterval() {
        return this.v;
    }

    @Nullable
    public final ArrayList<SettingsModel> getIntervalSettingList() {
        return this.n;
    }

    @Nullable
    public final SportIntervalAdapter getIntervalSettingsAdapter() {
        return this.p;
    }

    @Nullable
    public final Context getMcontext() {
        return this.q;
    }

    public final int getOdiMatchInterval() {
        return this.w;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getSettingSavedDialog() {
        return this.y;
    }

    @Nullable
    public final String getSport() {
        return this.u;
    }

    @Nullable
    public final SportsPreferenceModel getSportsPreferenceModel() {
        return this.z;
    }

    @Nullable
    public final ArrayList<SettingsModel> getSportsSettingList() {
        return this.m;
    }

    @Nullable
    public final SportSettingAdapter getSportsSettingsAdapter() {
        return this.o;
    }

    public final int getT20MatchInterval() {
        return this.x;
    }

    @Nullable
    public final SportsSettingFragmentViewModelNew getViewmodel() {
        return this.r;
    }

    public final void i0() {
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this.r;
        MutableLiveData<SportsType> selectedSportType = sportsSettingFragmentViewModelNew != null ? sportsSettingFragmentViewModelNew.getSelectedSportType() : null;
        if (selectedSportType != null) {
            String str = this.u;
            Intrinsics.checkNotNull(str);
            selectedSportType.setValue(SportsType.valueOf(str));
        }
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew2 = this.r;
        MutableLiveData<Integer> selectedT20Interval = sportsSettingFragmentViewModelNew2 != null ? sportsSettingFragmentViewModelNew2.getSelectedT20Interval() : null;
        if (selectedT20Interval != null) {
            selectedT20Interval.setValue(Integer.valueOf(this.x));
        }
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew3 = this.r;
        MutableLiveData<Integer> selectedODIInterval = sportsSettingFragmentViewModelNew3 != null ? sportsSettingFragmentViewModelNew3.getSelectedODIInterval() : null;
        if (selectedODIInterval == null) {
            return;
        }
        selectedODIInterval.setValue(Integer.valueOf(this.w));
    }

    public final void initClickListeners() {
        int i;
        ((LinearLayout) _$_findCachedViewById(R.id.cricket_main_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.V(SportsSettingsFragmentNew.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cricket_header_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.W(SportsSettingsFragmentNew.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.cricket_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.X(SportsSettingsFragmentNew.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.cricket_title)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.Y(SportsSettingsFragmentNew.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.iv_cricket_icon)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.Z(SportsSettingsFragmentNew.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.football_main_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.K(SportsSettingsFragmentNew.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.football_header_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.L(SportsSettingsFragmentNew.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.football_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.M(SportsSettingsFragmentNew.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.football_title)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.N(SportsSettingsFragmentNew.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.iv_football_icon)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.O(SportsSettingsFragmentNew.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tab_t20)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.P(SportsSettingsFragmentNew.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tab_odi)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.Q(SportsSettingsFragmentNew.this, view);
            }
        });
        SportsPreference.Companion companion = SportsPreference.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.getSoccerConfig(requireContext) != null) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            SoccerFilterConfig soccerConfig = companion.getSoccerConfig(requireContext2);
            Intrinsics.checkNotNull(soccerConfig);
            Filters filters = soccerConfig.getFilters().get(0);
            Intrinsics.checkNotNull(filters);
            if (filters.getApiHitInterval() != null) {
                Context requireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                SoccerFilterConfig soccerConfig2 = companion.getSoccerConfig(requireContext3);
                Intrinsics.checkNotNull(soccerConfig2);
                Filters filters2 = soccerConfig2.getFilters().get(0);
                Intrinsics.checkNotNull(filters2);
                Integer apiHitInterval = filters2.getApiHitInterval();
                Intrinsics.checkNotNull(apiHitInterval);
                i = apiHitInterval.intValue();
                StringBuilder sb = new StringBuilder();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string = requireContext().getString(R.string.score_will_be_updated_every_15min);
                Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…l_be_updated_every_15min)");
                String format = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                sb.append(format);
                sb.append(" min");
                ((TextView) _$_findCachedViewById(R.id.interval_text)).setText(sb.toString());
                ((ImageView) _$_findCachedViewById(R.id.iv_cricket_interval_left_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.i
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SportsSettingsFragmentNew.R(SportsSettingsFragmentNew.this, view);
                    }
                });
                ((ImageView) _$_findCachedViewById(R.id.iv_cricket_interval_right_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.d
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SportsSettingsFragmentNew.S(SportsSettingsFragmentNew.this, view);
                    }
                });
                ((Button) _$_findCachedViewById(R.id.get_cricket_score_updates)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.j
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SportsSettingsFragmentNew.T(SportsSettingsFragmentNew.this, view);
                    }
                });
                ((Button) _$_findCachedViewById(R.id.get_football_score_updates)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.y
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SportsSettingsFragmentNew.U(SportsSettingsFragmentNew.this, view);
                    }
                });
            }
        }
        i = 15;
        StringBuilder sb2 = new StringBuilder();
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String string2 = requireContext().getString(R.string.score_will_be_updated_every_15min);
        Intrinsics.checkNotNullExpressionValue(string2, "requireContext().getStri…l_be_updated_every_15min)");
        String format2 = String.format(string2, Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        sb2.append(format2);
        sb2.append(" min");
        ((TextView) _$_findCachedViewById(R.id.interval_text)).setText(sb2.toString());
        ((ImageView) _$_findCachedViewById(R.id.iv_cricket_interval_left_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.R(SportsSettingsFragmentNew.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.iv_cricket_interval_right_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.S(SportsSettingsFragmentNew.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.get_cricket_score_updates)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.T(SportsSettingsFragmentNew.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.get_football_score_updates)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsSettingsFragmentNew.U(SportsSettingsFragmentNew.this, view);
            }
        });
    }

    public final void initObservers() {
        MutableLiveData<Integer> selectedODIInterval;
        MutableLiveData<Integer> selectedT20Interval;
        MutableLiveData<String> selectedCricketMatchFormat;
        MutableLiveData<SportsType> selectedSportType;
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this.r;
        if (sportsSettingFragmentViewModelNew != null && (selectedSportType = sportsSettingFragmentViewModelNew.getSelectedSportType()) != null) {
            selectedSportType.observe(getViewLifecycleOwner(), new Observer<SportsType>() { // from class: com.coveiot.android.sportsnotification.fragment.SportsSettingsFragmentNew$initObservers$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable SportsType sportsType) {
                    if (sportsType == SportsType.Cricket) {
                        LinearLayout linearLayout = (LinearLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.cricket_main_layout);
                        Context context = SportsSettingsFragmentNew.this.getContext();
                        linearLayout.setBackground(context != null ? context.getDrawable(R.drawable.sports_type_expanded_bg) : null);
                        SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.cricket_divider).setVisibility(0);
                        ((ConstraintLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.cricket_config_layout)).setVisibility(0);
                        ConstraintLayout constraintLayout = (ConstraintLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.cricket_header_layout);
                        Context context2 = SportsSettingsFragmentNew.this.getContext();
                        constraintLayout.setBackground(context2 != null ? context2.getDrawable(R.drawable.sports_type_expanded_header_bg) : null);
                        ((ImageView) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.cricket_arrow)).setImageResource(R.drawable.ic_up_arrow_white);
                        LinearLayout linearLayout2 = (LinearLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.football_main_layout);
                        Context context3 = SportsSettingsFragmentNew.this.getContext();
                        linearLayout2.setBackground(context3 != null ? context3.getDrawable(R.drawable.sports_type_collapsed_bg) : null);
                        SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.football_divider).setVisibility(8);
                        ((ConstraintLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.football_header_layout)).setBackground(null);
                        ((ConstraintLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.football_config_layout)).setVisibility(8);
                        ((ImageView) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.football_arrow)).setImageResource(R.drawable.ic_down_arrow_white);
                    } else if (sportsType == SportsType.Football) {
                        LinearLayout linearLayout3 = (LinearLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.cricket_main_layout);
                        Context context4 = SportsSettingsFragmentNew.this.getContext();
                        linearLayout3.setBackground(context4 != null ? context4.getDrawable(R.drawable.sports_type_collapsed_bg) : null);
                        SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.cricket_divider).setVisibility(8);
                        ((ConstraintLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.cricket_config_layout)).setVisibility(8);
                        ((ConstraintLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.cricket_header_layout)).setBackground(null);
                        ((ImageView) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.cricket_arrow)).setImageResource(R.drawable.ic_down_arrow_white);
                        LinearLayout linearLayout4 = (LinearLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.football_main_layout);
                        Context context5 = SportsSettingsFragmentNew.this.getContext();
                        linearLayout4.setBackground(context5 != null ? context5.getDrawable(R.drawable.sports_type_expanded_bg) : null);
                        SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.football_divider).setVisibility(0);
                        ((ConstraintLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.football_config_layout)).setVisibility(0);
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.football_header_layout);
                        Context context6 = SportsSettingsFragmentNew.this.getContext();
                        constraintLayout2.setBackground(context6 != null ? context6.getDrawable(R.drawable.sports_type_expanded_header_bg) : null);
                        ((ImageView) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.football_arrow)).setImageResource(R.drawable.ic_up_arrow_white);
                    }
                    SportsSettingsFragmentNew.this.setSport(sportsType != null ? sportsType.name() : null);
                }
            });
        }
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew2 = this.r;
        if (sportsSettingFragmentViewModelNew2 != null && (selectedCricketMatchFormat = sportsSettingFragmentViewModelNew2.getSelectedCricketMatchFormat()) != null) {
            selectedCricketMatchFormat.observe(getViewLifecycleOwner(), new Observer<String>() { // from class: com.coveiot.android.sportsnotification.fragment.SportsSettingsFragmentNew$initObservers$2
                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable String str) {
                    MutableLiveData<Integer> selectedODIInterval2;
                    MutableLiveData<Integer> selectedT20Interval2;
                    Integer num = null;
                    if (Intrinsics.areEqual(str, "T20")) {
                        SportsSettingsFragmentNew sportsSettingsFragmentNew = SportsSettingsFragmentNew.this;
                        int i = R.id.tab_t20;
                        ((TextView) sportsSettingsFragmentNew._$_findCachedViewById(i)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff_selected);
                        SportsSettingsFragmentNew sportsSettingsFragmentNew2 = SportsSettingsFragmentNew.this;
                        int i2 = R.id.tab_odi;
                        ((TextView) sportsSettingsFragmentNew2._$_findCachedViewById(i2)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_1f1f20);
                        Context context = SportsSettingsFragmentNew.this.getContext();
                        Intrinsics.checkNotNull(context);
                        ((TextView) SportsSettingsFragmentNew.this._$_findCachedViewById(i2)).setTextColor(context.getResources().getColor(R.color.secondary_text_color));
                        Context context2 = SportsSettingsFragmentNew.this.getContext();
                        Intrinsics.checkNotNull(context2);
                        ((TextView) SportsSettingsFragmentNew.this._$_findCachedViewById(i)).setTextColor(context2.getResources().getColor(R.color.main_text_color));
                        TextView textView = (TextView) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.tv_interval_value);
                        StringBuilder sb = new StringBuilder();
                        SportsSettingFragmentViewModelNew viewmodel = SportsSettingsFragmentNew.this.getViewmodel();
                        if (viewmodel != null && (selectedT20Interval2 = viewmodel.getSelectedT20Interval()) != null) {
                            num = selectedT20Interval2.getValue();
                        }
                        sb.append(num);
                        sb.append(" Mins");
                        textView.setText(sb.toString());
                    } else if (Intrinsics.areEqual(str, "ODI")) {
                        SportsSettingsFragmentNew sportsSettingsFragmentNew3 = SportsSettingsFragmentNew.this;
                        int i3 = R.id.tab_odi;
                        ((TextView) sportsSettingsFragmentNew3._$_findCachedViewById(i3)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff_selected);
                        SportsSettingsFragmentNew sportsSettingsFragmentNew4 = SportsSettingsFragmentNew.this;
                        int i4 = R.id.tab_t20;
                        ((TextView) sportsSettingsFragmentNew4._$_findCachedViewById(i4)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_1f1f20);
                        TextView textView2 = (TextView) SportsSettingsFragmentNew.this._$_findCachedViewById(R.id.tv_interval_value);
                        StringBuilder sb2 = new StringBuilder();
                        SportsSettingFragmentViewModelNew viewmodel2 = SportsSettingsFragmentNew.this.getViewmodel();
                        if (viewmodel2 != null && (selectedODIInterval2 = viewmodel2.getSelectedODIInterval()) != null) {
                            num = selectedODIInterval2.getValue();
                        }
                        sb2.append(num);
                        sb2.append(" Mins");
                        textView2.setText(sb2.toString());
                        Context context3 = SportsSettingsFragmentNew.this.getContext();
                        Intrinsics.checkNotNull(context3);
                        ((TextView) SportsSettingsFragmentNew.this._$_findCachedViewById(i3)).setTextColor(context3.getResources().getColor(R.color.main_text_color));
                        Context context4 = SportsSettingsFragmentNew.this.getContext();
                        Intrinsics.checkNotNull(context4);
                        ((TextView) SportsSettingsFragmentNew.this._$_findCachedViewById(i4)).setTextColor(context4.getResources().getColor(R.color.secondary_text_color));
                    }
                }
            });
        }
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew3 = this.r;
        if (sportsSettingFragmentViewModelNew3 != null && (selectedT20Interval = sportsSettingFragmentViewModelNew3.getSelectedT20Interval()) != null) {
            selectedT20Interval.observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.sportsnotification.fragment.p
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    SportsSettingsFragmentNew.a0(SportsSettingsFragmentNew.this, (Integer) obj);
                }
            });
        }
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew4 = this.r;
        if (sportsSettingFragmentViewModelNew4 == null || (selectedODIInterval = sportsSettingFragmentViewModelNew4.getSelectedODIInterval()) == null) {
            return;
        }
        selectedODIInterval.observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.sportsnotification.fragment.q
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SportsSettingsFragmentNew.b0(SportsSettingsFragmentNew.this, (Integer) obj);
            }
        });
    }

    public final boolean isSettingSelcted() {
        return this.t;
    }

    public final boolean isSportsSettingSelcted() {
        return this.s;
    }

    public final void j0() {
        String string = getString(R.string.configuring_your_watch);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.configuring_your_watch)");
        BaseFragment.ProgressListener showProgressDialogWithTitleAndProgress$default = BaseFragment.showProgressDialogWithTitleAndProgress$default(this, string, false, 0, 6, null);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CRICKET_SETTINGS_DONE.getValue());
        analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.SELECT_MATCH_SCREEN.getValue());
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.SPORTS_SETTINGS.getValue());
        HashMap<String, String> hashMap = new HashMap<>();
        int i = R.id.switch_vibrate;
        if (((SwitchCompat) _$_findCachedViewById(i)) != null) {
            int i2 = R.id.switch_button;
            if (((SwitchCompat) _$_findCachedViewById(i2)) != null) {
                hashMap.put("cv_match_type", "ODI");
                hashMap.put("cv_interval", String.valueOf(this.w));
                hashMap.put("cv_match_type2", "T20");
                hashMap.put("cv_vibration_alert", String.valueOf(((SwitchCompat) _$_findCachedViewById(i)).isChecked()));
                hashMap.put("cv_score_push", String.valueOf(((SwitchCompat) _$_findCachedViewById(i2)).isChecked()));
                hashMap.put("cv_interval2", String.valueOf(this.x));
                analyticsLog.setMapData(hashMap);
                HashMap<String, String> hashMap2 = new HashMap<>();
                String value = FirebaseEventParams.MetaData.CV_SPORT_TYPE.getValue();
                String str = this.u;
                Intrinsics.checkNotNull(str);
                String lowerCase = str.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                hashMap2.put(value, lowerCase);
                analyticsLog.setMapData(hashMap2);
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            }
        }
        SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this.r;
        Intrinsics.checkNotNull(sportsSettingFragmentViewModelNew);
        Context context = this.q;
        Intrinsics.checkNotNull(context);
        Integer valueOf = Integer.valueOf(this.w);
        Integer valueOf2 = Integer.valueOf(this.x);
        String str2 = this.u;
        Intrinsics.checkNotNull(str2);
        sportsSettingFragmentViewModelNew.saveSportsPreference(context, valueOf, valueOf2, str2, ((SwitchCompat) _$_findCachedViewById(R.id.switch_button)).isChecked(), ((SwitchCompat) _$_findCachedViewById(i)).isChecked(), showProgressDialogWithTitleAndProgress$default);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean k0() {
        /*
            r8 = this;
            com.coveiot.android.sportsnotification.SportsPreference$Companion r0 = com.coveiot.android.sportsnotification.SportsPreference.Companion
            android.content.Context r1 = r8.requireContext()
            java.lang.String r2 = "requireContext()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.coveiot.android.sportsnotification.model.SportsPreferenceModel r1 = r0.getSportsNotification(r1)
            android.content.Context r3 = r8.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            boolean r3 = r0.isVibrationEnabled(r3)
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L9a
            java.lang.String r6 = r1.getSport()
            if (r6 == 0) goto L2e
            java.lang.String r7 = r8.u
            boolean r6 = r6.equals(r7)
            if (r6 != r4) goto L2e
            r6 = r4
            goto L2f
        L2e:
            r6 = r5
        L2f:
            if (r6 == 0) goto L99
            java.lang.Integer r6 = r1.getInterval()
            if (r6 == 0) goto L47
            java.lang.Integer r7 = r8.v
            if (r7 != 0) goto L3f
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
        L3f:
            boolean r6 = r6.equals(r7)
            if (r6 != r4) goto L47
            r6 = r4
            goto L48
        L47:
            r6 = r5
        L48:
            if (r6 == 0) goto L99
            java.lang.Boolean r1 = r1.isEnable()
            if (r1 == 0) goto L68
            int r6 = com.coveiot.android.sportsnotification.R.id.switch_button
            android.view.View r6 = r8._$_findCachedViewById(r6)
            androidx.appcompat.widget.SwitchCompat r6 = (androidx.appcompat.widget.SwitchCompat) r6
            boolean r6 = r6.isChecked()
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
            boolean r1 = r1.equals(r6)
            if (r1 != r4) goto L68
            r1 = r4
            goto L69
        L68:
            r1 = r5
        L69:
            if (r1 == 0) goto L99
            int r1 = com.coveiot.android.sportsnotification.R.id.switch_vibrate
            android.view.View r1 = r8._$_findCachedViewById(r1)
            androidx.appcompat.widget.SwitchCompat r1 = (androidx.appcompat.widget.SwitchCompat) r1
            boolean r1 = r1.isChecked()
            if (r1 != r3) goto L99
            int r1 = r8.w
            android.content.Context r3 = r8.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            int r3 = r0.getODIInterval(r3)
            if (r1 != r3) goto L99
            int r1 = r8.x
            android.content.Context r3 = r8.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            int r0 = r0.getT20Interval(r3)
            if (r1 == r0) goto L98
            goto L99
        L98:
            r4 = r5
        L99:
            return r4
        L9a:
            int r0 = com.coveiot.android.sportsnotification.R.id.switch_button
            android.view.View r0 = r8._$_findCachedViewById(r0)
            androidx.appcompat.widget.SwitchCompat r0 = (androidx.appcompat.widget.SwitchCompat) r0
            boolean r0 = r0.isChecked()
            if (r0 != 0) goto Lb8
            int r0 = com.coveiot.android.sportsnotification.R.id.switch_vibrate
            android.view.View r0 = r8._$_findCachedViewById(r0)
            androidx.appcompat.widget.SwitchCompat r0 = (androidx.appcompat.widget.SwitchCompat) r0
            boolean r0 = r0.isChecked()
            if (r0 == 0) goto Lb7
            goto Lb8
        Lb7:
            r4 = r5
        Lb8:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.fragment.SportsSettingsFragmentNew.k0():boolean");
    }

    public final void l0() {
        if (this.A == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            Drawable drawable = requireContext().getDrawable(R.drawable.info_icon_new);
            Intrinsics.checkNotNull(drawable);
            String string = getString(R.string.are_you_sure_you_want_to_change_sport);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.are_y…you_want_to_change_sport)");
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = new BottomSheetDialogImageTitleMessageTwoBtns(requireContext, drawable, string, "", true);
            this.A = bottomSheetDialogImageTitleMessageTwoBtns;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
            String string2 = getString(R.string.yes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.yes)");
            bottomSheetDialogImageTitleMessageTwoBtns.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.SportsSettingsFragmentNew$showSportsChangeDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns2 = SportsSettingsFragmentNew.this.getBottomSheetDialogImageTitleMessageTwoBtns();
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns2);
                    bottomSheetDialogImageTitleMessageTwoBtns2.dismiss();
                    SportsSettingsFragmentNew.this.j0();
                }
            });
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns2 = this.A;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns2);
            String string3 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
            bottomSheetDialogImageTitleMessageTwoBtns2.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.x
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SportsSettingsFragmentNew.m0(SportsSettingsFragmentNew.this, view);
                }
            });
        }
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns3 = this.A;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns3);
        if (bottomSheetDialogImageTitleMessageTwoBtns3.isShowing()) {
            return;
        }
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns4 = this.A;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns4);
        bottomSheetDialogImageTitleMessageTwoBtns4.show();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_sports_settings_new_final, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this.y;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }
        dismissProgressDialogWithTitleAndProgress();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.sportsnotification.adapter.SportIntervalAdapter.IntervalSelectListener
    public void onIntervalSelect(int i) {
        ArrayList<SettingsModel> arrayList = this.n;
        Intrinsics.checkNotNull(arrayList);
        this.v = Integer.valueOf(arrayList.get(i).getValue());
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onNegativeButtonClicked() {
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onPositiveButtonClicked() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        logScreenViewEvent(FirebaseEventParams.ScreenName.SPORTS_SETTINGS.getValue());
    }

    public final void onSettingSaved(@NotNull String title, @NotNull String msg, @Nullable final List<CoinNotifications> list) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (this.y == null) {
            Context context = this.q;
            Intrinsics.checkNotNull(context);
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(context, title, msg);
            this.y = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SportsSettingsFragmentNew.c0(SportsSettingsFragmentNew.this, list, view);
                }
            });
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.y;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
            bottomSheetDialogOneButtonTitleMessage2.setCancelable(false);
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.y;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
            bottomSheetDialogOneButtonTitleMessage3.setCancelableOutside(false);
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage4 = this.y;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage4);
        if (bottomSheetDialogOneButtonTitleMessage4.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage5 = this.y;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage5);
        bottomSheetDialogOneButtonTitleMessage5.show();
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.SportsSettingFragmentViewModelNew.SettingsUpdateListener
    public void onSettingsSaved(boolean z, @Nullable List<CoinNotifications> list) {
        if (isAdded()) {
            dismissProgressDialogWithTitleAndProgress();
            if (z) {
                if (this.s) {
                    String string = requireActivity().getResources().getString(R.string.setting_saved_successfully);
                    Intrinsics.checkNotNullExpressionValue(string, "requireActivity().resour…tting_saved_successfully)");
                    String string2 = requireActivity().getResources().getString(R.string.selected_score_available_in_watch);
                    Intrinsics.checkNotNullExpressionValue(string2, "requireActivity().resour…score_available_in_watch)");
                    onSettingSaved(string, string2, list);
                    return;
                }
                String string3 = requireActivity().getResources().getString(R.string.setting_saved_successfully);
                Intrinsics.checkNotNullExpressionValue(string3, "requireActivity().resour…tting_saved_successfully)");
                onSettingSaved(string3, "", list);
                return;
            }
            String string4 = requireActivity().getResources().getString(R.string.setting_could_not_be_saved);
            Intrinsics.checkNotNullExpressionValue(string4, "requireActivity().resour…tting_could_not_be_saved)");
            onSettingSaved(string4, "", list);
        }
    }

    @Override // com.coveiot.android.sportsnotification.adapter.SportSettingAdapter.SportSelectSelectListener
    public void onSportSelect(int i) {
        ArrayList<SettingsModel> arrayList = this.m;
        Intrinsics.checkNotNull(arrayList);
        this.u = arrayList.get(i).getName();
        if (i == 0) {
            SportsApiClient.Companion.resetSportsApi(requireContext(), SportType.CRICKET);
        } else {
            SportsApiClient.Companion.resetSportsApi(requireContext(), SportType.SOCCER);
        }
        ((Button) _$_findCachedViewById(R.id.btn_done)).setEnabled(k0());
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0144  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r8, @org.jetbrains.annotations.Nullable android.os.Bundle r9) {
        /*
            Method dump skipped, instructions count: 464
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.fragment.SportsSettingsFragmentNew.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    public final void replaceFragment() {
        if (isAdded()) {
            if (kotlin.text.m.equals$default(this.u, "Cricket", false, 2, null)) {
                replaceFragment(TodaysMatchFragment.Companion.newInstance());
            } else {
                replaceFragment(TodaysMatchFragmentSoccer.Companion.newInstance());
            }
        }
    }

    public final void setBottomSheetDialogImageTitleMessageTwoBtns(@Nullable BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns) {
        this.A = bottomSheetDialogImageTitleMessageTwoBtns;
    }

    public final void setInterval(@Nullable Integer num) {
        this.v = num;
    }

    public final void setIntervalSettingList(@Nullable ArrayList<SettingsModel> arrayList) {
        this.n = arrayList;
    }

    public final void setIntervalSettingsAdapter(@Nullable SportIntervalAdapter sportIntervalAdapter) {
        this.p = sportIntervalAdapter;
    }

    public final void setMcontext(@Nullable Context context) {
        this.q = context;
    }

    public final void setOdiMatchInterval(int i) {
        this.w = i;
    }

    public final void setSettingSavedDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.y = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setSettingSelcted(boolean z) {
        this.t = z;
    }

    public final void setSport(@Nullable String str) {
        this.u = str;
    }

    public final void setSportsPreferenceModel(@Nullable SportsPreferenceModel sportsPreferenceModel) {
        this.z = sportsPreferenceModel;
    }

    public final void setSportsSettingList(@Nullable ArrayList<SettingsModel> arrayList) {
        this.m = arrayList;
    }

    public final void setSportsSettingSelcted(boolean z) {
        this.s = z;
    }

    public final void setSportsSettingsAdapter(@Nullable SportSettingAdapter sportSettingAdapter) {
        this.o = sportSettingAdapter;
    }

    public final void setT20MatchInterval(int i) {
        this.x = i;
    }

    public final void setViewmodel(@Nullable SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew) {
        this.r = sportsSettingFragmentViewModelNew;
    }

    public final void updateListVisibilty(boolean z) {
        int i = 0;
        if (!z) {
            int i2 = R.id.rl_sport_settings;
            ((LinearLayout) _$_findCachedViewById(i2)).setEnabled(false);
            int i3 = R.id.rl_vibrate;
            ((ConstraintLayout) _$_findCachedViewById(i3)).setEnabled(false);
            ((TextView) _$_findCachedViewById(R.id.tv_vibrate_desc)).setVisibility(0);
            ((SwitchCompat) _$_findCachedViewById(R.id.switch_vibrate)).setEnabled(false);
            ((LinearLayout) _$_findCachedViewById(i2)).setAlpha(0.5f);
            ((ConstraintLayout) _$_findCachedViewById(i3)).setAlpha(0.5f);
            int childCount = ((LinearLayout) _$_findCachedViewById(i2)).getChildCount() - 1;
            if (childCount >= 0) {
                int i4 = 0;
                while (true) {
                    int i5 = R.id.rl_sport_settings;
                    ((LinearLayout) _$_findCachedViewById(i5)).getChildAt(i4).setClickable(false);
                    J(((LinearLayout) _$_findCachedViewById(i5)).getChildAt(i4), false);
                    if (i4 == childCount) {
                        break;
                    }
                    i4++;
                }
            }
        } else {
            int i6 = R.id.rl_sport_settings;
            ((LinearLayout) _$_findCachedViewById(i6)).setEnabled(true);
            int i7 = R.id.rl_vibrate;
            ((ConstraintLayout) _$_findCachedViewById(i7)).setEnabled(true);
            ((SwitchCompat) _$_findCachedViewById(R.id.switch_vibrate)).setEnabled(true);
            ((TextView) _$_findCachedViewById(R.id.tv_vibrate_desc)).setVisibility(0);
            ((LinearLayout) _$_findCachedViewById(i6)).setAlpha(1.0f);
            SportSettingAdapter sportSettingAdapter = this.o;
            if (sportSettingAdapter != null) {
                sportSettingAdapter.setEnabled(true);
            }
            SportIntervalAdapter sportIntervalAdapter = this.p;
            if (sportIntervalAdapter != null) {
                sportIntervalAdapter.setEnabled(true);
            }
            ((ConstraintLayout) _$_findCachedViewById(i7)).setAlpha(1.0f);
            int childCount2 = ((LinearLayout) _$_findCachedViewById(i6)).getChildCount() - 1;
            if (childCount2 >= 0) {
                while (true) {
                    int i8 = R.id.rl_sport_settings;
                    ((LinearLayout) _$_findCachedViewById(i8)).getChildAt(i).setClickable(true);
                    J(((LinearLayout) _$_findCachedViewById(i8)).getChildAt(i), true);
                    if (i == childCount2) {
                        break;
                    }
                    i++;
                }
            }
        }
        ((Button) _$_findCachedViewById(R.id.btn_done)).setEnabled(k0());
    }

    public final void replaceFragment(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }
}
