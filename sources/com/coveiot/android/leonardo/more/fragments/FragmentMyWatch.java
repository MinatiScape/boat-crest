package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentMyWatchBinding;
import com.coveiot.android.customreminders.activities.ActivityCustomReminders;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.preference.PreferenceManagerCustomReminders;
import com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface;
import com.coveiot.android.dashboard2.util.Dashboard2Constants;
import com.coveiot.android.dashboard2.util.DoMoreWithYourWatchHelper;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.ActivityDashboardNew;
import com.coveiot.android.leonardo.model.MyWatchFeatureModel;
import com.coveiot.android.leonardo.model.MyWatchFeatureType;
import com.coveiot.android.leonardo.more.activities.ActivityAlarmNew;
import com.coveiot.android.leonardo.more.activities.ActivityFindMyWatch;
import com.coveiot.android.leonardo.more.adapters.MyWatchFeaturesAdapter;
import com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDataViewModel;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.sos.utils.SOSCleverTapConstants;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsType;
import com.coveiot.android.sportsnotification.SportsUtils;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.model.WatchfaceScreenCaller;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceCloudViewModel;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.VibrationAlarmData;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.coveiot.utils.utility.AppUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentMyWatch extends BaseFragment implements MyWatchFeaturesAdapter.OnFeatureClickListener {
    public boolean m;
    @Nullable
    public FragmentMyWatchBinding n;
    @Nullable
    public MyWatchFeaturesAdapter o;
    public SensAISummaryDataViewModel q;
    public WatchFaceCloudViewModel r;
    public DataSyncViewModel s;
    public boolean u;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<List<MyWatchFeatureModel>> p = new ArrayList();
    @NotNull
    public SimpleDateFormat t = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MyWatchFeatureType.values().length];
            try {
                iArr[MyWatchFeatureType.CUSTOM_REMINDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MyWatchFeatureType.SPORTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[MyWatchFeatureType.ALEXA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[MyWatchFeatureType.ALARMS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[MyWatchFeatureType.SENSAI.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[MyWatchFeatureType.TAP_AND_PAY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[MyWatchFeatureType.FEMALE_WELLNESS_TRACKER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void E(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.A()) {
            return;
        }
        this$0.Q();
    }

    public static final void F(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.A()) {
            return;
        }
        this$0.Q();
    }

    public static final void I(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.requireActivity() instanceof ActivityDashboardNew) {
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.ActivityDashboardNew");
            ((ActivityDashboardNew) requireActivity).navigateToWatchfaceStudioActivity();
        }
    }

    public static final void M(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.navigateToSensAIHomeScreen(requireContext);
    }

    public static final void N(FragmentMyWatch this$0, List list) {
        String string;
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            if (!(list == null || list.isEmpty())) {
                ConstraintLayout constraintLayout = this$0.B().clSensAIDetails;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clSensAIDetails");
                this$0.visible(constraintLayout);
                if (((SensAIActivitySummary) list.get(0)).getActivityType() == 1) {
                    if (((SensAIActivitySummary) list.get(0)).getHand() == 0) {
                        this$0.B().tvTitle.setText(this$0.requireContext().getString(R.string.right_hand_batting));
                        this$0.B().ivImage.setImageDrawable(this$0.requireContext().getDrawable(R.drawable.sens_ai_right_hand_batting));
                    } else {
                        this$0.B().tvTitle.setText(this$0.requireContext().getString(R.string.left_hand_batting));
                        this$0.B().ivImage.setImageDrawable(this$0.requireContext().getDrawable(R.drawable.sens_ai_left_hand_batting));
                    }
                    this$0.B().tvAvgHandSpeedTxt.setText(this$0.requireContext().getString(R.string.avg_hand_speed));
                    this$0.B().tvTotalShotsTxt.setText(this$0.requireContext().getString(R.string.shots));
                    this$0.B().ivShots.setImageDrawable(this$0.requireContext().getDrawable(R.drawable.sens_ai_bat));
                    Boolean isDistanceUnitInMile = UserDataManager.getInstance(this$0.requireContext()).isDistanceUnitInMile();
                    Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(requireContext()).isDistanceUnitInMile");
                    if (isDistanceUnitInMile.booleanValue()) {
                        TextView textView = this$0.B().tvAvgHandSpeed;
                        StringBuilder sb = new StringBuilder();
                        PayUtils payUtils = PayUtils.INSTANCE;
                        Context requireContext = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        sb.append(payUtils.getSpeedValue(requireContext, ((SensAIActivitySummary) list.get(0)).getAvgHandSpeed()));
                        sb.append(' ');
                        sb.append(this$0.requireContext().getResources().getString(R.string.mil_per_hours));
                        sb.append(' ');
                        textView.setText(sb.toString());
                    } else {
                        this$0.B().tvAvgHandSpeed.setText(((SensAIActivitySummary) list.get(0)).getAvgHandSpeed() + ' ' + this$0.requireContext().getResources().getString(R.string.km_per_hours) + ' ');
                    }
                    this$0.B().ivSpeed.setImageDrawable(this$0.requireContext().getDrawable(R.drawable.ic_batting_avg_armspeed));
                    this$0.B().tvTotalShots.setText(String.valueOf(((SensAIActivitySummary) list.get(0)).getTotalSwings()));
                } else {
                    if (((SensAIActivitySummary) list.get(0)).getHand() == 0) {
                        this$0.B().ivImage.setImageDrawable(this$0.requireContext().getDrawable(R.drawable.sens_ai_right_hand_bowling));
                    } else {
                        this$0.B().ivImage.setImageDrawable(this$0.requireContext().getDrawable(R.drawable.sens_ai_left_hand_bowling));
                    }
                    if (((SensAIActivitySummary) list.get(0)).getHand() == 0) {
                        string = this$0.requireContext().getString(R.string.right_hand);
                        Intrinsics.checkNotNullExpressionValue(string, "{\n                      …                        }");
                    } else {
                        string = this$0.requireContext().getString(R.string.left_hand);
                        Intrinsics.checkNotNullExpressionValue(string, "{\n                      …                        }");
                    }
                    int bowlingType = ((SensAIActivitySummary) list.get(0)).getBowlingType();
                    if (bowlingType == 0) {
                        str = string + ' ' + this$0.requireContext().getString(R.string.fast);
                    } else if (bowlingType != 1) {
                        str = string + ' ' + this$0.requireContext().getString(R.string.spin);
                    } else {
                        str = string + ' ' + this$0.requireContext().getString(R.string.medium_pace);
                    }
                    this$0.B().tvTitle.setText(str);
                    this$0.B().tvAvgHandSpeedTxt.setText(this$0.requireContext().getString(R.string.avg_arm_speed));
                    Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(this$0.requireContext()).isDistanceUnitInMile();
                    Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(requireContext()).isDistanceUnitInMile");
                    if (isDistanceUnitInMile2.booleanValue()) {
                        TextView textView2 = this$0.B().tvAvgHandSpeed;
                        StringBuilder sb2 = new StringBuilder();
                        PayUtils payUtils2 = PayUtils.INSTANCE;
                        Context requireContext2 = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        sb2.append(payUtils2.getSpeedValue(requireContext2, ((SensAIActivitySummary) list.get(0)).getAvgArmSpeed()));
                        sb2.append(' ');
                        sb2.append(this$0.requireContext().getResources().getString(R.string.mil_per_hours));
                        sb2.append(' ');
                        textView2.setText(sb2.toString());
                    } else {
                        this$0.B().tvAvgHandSpeed.setText(((SensAIActivitySummary) list.get(0)).getAvgArmSpeed() + ' ' + this$0.requireContext().getResources().getString(R.string.km_per_hours) + ' ');
                    }
                    this$0.B().ivSpeed.setImageDrawable(this$0.requireContext().getDrawable(R.drawable.ic_avg_bowling));
                    this$0.B().tvTotalShotsTxt.setText(this$0.requireContext().getString(R.string.total_balls));
                    this$0.B().ivShots.setImageDrawable(this$0.requireContext().getDrawable(R.drawable.ic_compare_ball));
                    this$0.B().tvTotalShots.setText(String.valueOf(((SensAIActivitySummary) list.get(0)).getTotalBallsBowled()));
                }
                SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM YYYY, hh:mm aa");
                TextView textView3 = this$0.B().tvTime;
                Long timestamp = ((SensAIActivitySummary) list.get(0)).getTimestamp();
                textView3.setText(simpleDateFormat.format(timestamp != null ? new Date(timestamp.longValue()) : null));
                this$0.B().tvDuration.setText(WorkoutUtils.INSTANCE.getFormattedDuration((int) ((SensAIActivitySummary) list.get(0)).getDurationSec()));
                if (((SensAIActivitySummary) list.get(0)).getGoalCompletionPct() >= 100) {
                    this$0.B().ivAward.setVisibility(0);
                } else {
                    this$0.B().ivAward.setVisibility(4);
                }
                this$0.u = true;
                this$0.G();
                return;
            }
            ConstraintLayout constraintLayout2 = this$0.B().clSensAIDetails;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clSensAIDetails");
            this$0.gone(constraintLayout2);
            this$0.G();
        }
    }

    public static final void P(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.A() || !(this$0.requireActivity() instanceof ActivityDashboardNew)) {
            return;
        }
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.ActivityDashboardNew");
        ((ActivityDashboardNew) requireActivity).navigateToSportsActivity();
    }

    public static final void R(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.B().clViewAll.setVisibility(8);
        this$0.B().clViewLess.setVisibility(0);
        for (List<MyWatchFeatureModel> list : this$0.p) {
            for (MyWatchFeatureModel myWatchFeatureModel : list) {
                myWatchFeatureModel.setShow(true);
            }
        }
        MyWatchFeaturesAdapter myWatchFeaturesAdapter = this$0.o;
        if (myWatchFeaturesAdapter != null) {
            myWatchFeaturesAdapter.setMyWatchFeatureList(this$0.p);
        }
        this$0.B().rvMyWatchFeatures.setLayoutManager(new LinearLayoutManager(this$0.requireActivity()));
        this$0.B().rvMyWatchFeatures.setAdapter(this$0.o);
    }

    public static final void S(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.B().clViewAll.setVisibility(0);
        this$0.B().clViewLess.setVisibility(8);
        int i = 0;
        for (List<MyWatchFeatureModel> list : this$0.p) {
            for (MyWatchFeatureModel myWatchFeatureModel : list) {
                myWatchFeatureModel.setShow(i <= 3);
                i++;
            }
        }
        MyWatchFeaturesAdapter myWatchFeaturesAdapter = this$0.o;
        if (myWatchFeaturesAdapter != null) {
            myWatchFeaturesAdapter.setMyWatchFeatureList(this$0.p);
        }
        this$0.B().rvMyWatchFeatures.setLayoutManager(new LinearLayoutManager(this$0.requireActivity()));
        this$0.B().rvMyWatchFeatures.setAdapter(this$0.o);
    }

    public static final void T(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                if (this$0.requireActivity() instanceof ActivityDashboardNew) {
                    FragmentActivity requireActivity = this$0.requireActivity();
                    Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.ActivityDashboardNew");
                    ((ActivityDashboardNew) requireActivity).navigateToBluetoothCallingActivity();
                    return;
                }
                return;
            }
        }
        this$0.Z();
    }

    public static final void U(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                if (BleApiManager.getInstance(this$0.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    this$0.startActivity(new Intent(this$0.requireContext(), ActivityFindMyWatch.class));
                    return;
                } else {
                    Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.band_not_connected), 1).show();
                    return;
                }
            }
        }
        this$0.Z();
    }

    public static final void V(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.requireActivity() instanceof ActivityDashboardNew) {
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.ActivityDashboardNew");
            ((ActivityDashboardNew) requireActivity).navigateToWatchSettingsActivity(true);
        }
    }

    public static final void W(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                if (this$0.requireActivity() instanceof ActivityDashboardNew) {
                    FragmentActivity requireActivity = this$0.requireActivity();
                    Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.ActivityDashboardNew");
                    ((ActivityDashboardNew) requireActivity).navigateToOneKActivity();
                    return;
                }
                return;
            }
        }
        this$0.Z();
    }

    public static final void X(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                if (this$0.requireActivity() instanceof ActivityDashboardNew) {
                    FragmentActivity requireActivity = this$0.requireActivity();
                    Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.ActivityDashboardNew");
                    ((ActivityDashboardNew) requireActivity).navigateToWatchfaceScreen(WatchfaceScreenCaller.MY_WATCH);
                    return;
                }
                return;
            }
        }
        this$0.Z();
    }

    public static final void Y(FragmentMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.requireActivity() instanceof ActivityDashboardNew) {
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.ActivityDashboardNew");
            ((ActivityDashboardNew) requireActivity).navigateToSOSSettings(SOSCleverTapConstants.SOS_MY_WATCH.getValue());
        }
    }

    public static final void a0(BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        guestOrPairDevicePopup.dismiss();
    }

    public static final void b0(FragmentMyWatch this$0, BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (themesUtils.isGuestUser(requireContext)) {
            if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
                FragmentActivity activity = this$0.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                ((FragmentHomeCallBackInterface) activity).navigateToLogin();
            }
        } else if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity2 = this$0.getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity2).navigateToPairDevice();
        }
        guestOrPairDevicePopup.dismiss();
    }

    public final boolean A() {
        DataSyncViewModel dataSyncViewModel = this.s;
        if (dataSyncViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataSyncViewModel");
            dataSyncViewModel = null;
        }
        if (dataSyncViewModel.checkIsSyncing()) {
            Toast.makeText(requireActivity(), getString(R.string.syncing_please_wait), 1).show();
            return true;
        }
        return false;
    }

    public final FragmentMyWatchBinding B() {
        FragmentMyWatchBinding fragmentMyWatchBinding = this.n;
        Intrinsics.checkNotNull(fragmentMyWatchBinding);
        return fragmentMyWatchBinding;
    }

    /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.Object] */
    public final void C() {
        B().watchFeatureHeaderTv.setText(getString(R.string.watch_features));
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.o = new MyWatchFeaturesAdapter(requireActivity);
        B().rvMyWatchFeatures.setLayoutManager(new LinearLayoutManager(requireActivity()));
        B().rvMyWatchFeatures.setAdapter(this.o);
        this.p.clear();
        MyWatchFeaturesAdapter myWatchFeaturesAdapter = this.o;
        if (myWatchFeaturesAdapter != null) {
            if (myWatchFeaturesAdapter != null) {
                myWatchFeaturesAdapter.setMyFeatureClickedListener(this);
            }
            ArrayList arrayList = new ArrayList();
            MyWatchFeatureModel myWatchFeatureModel = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel.setMyWatchFeatureType(MyWatchFeatureType.SENSAI);
            arrayList.add(myWatchFeatureModel);
            MyWatchFeatureModel myWatchFeatureModel2 = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel2.setMyWatchFeatureType(MyWatchFeatureType.ALEXA);
            arrayList.add(myWatchFeatureModel2);
            MyWatchFeatureModel myWatchFeatureModel3 = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel3.setMyWatchFeatureType(MyWatchFeatureType.SPORTS);
            arrayList.add(myWatchFeatureModel3);
            MyWatchFeatureModel myWatchFeatureModel4 = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel4.setMyWatchFeatureType(MyWatchFeatureType.CUSTOM_REMINDER);
            arrayList.add(myWatchFeatureModel4);
            MyWatchFeatureModel myWatchFeatureModel5 = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel5.setMyWatchFeatureType(MyWatchFeatureType.TAP_AND_PAY);
            arrayList.add(myWatchFeatureModel5);
            MyWatchFeatureModel myWatchFeatureModel6 = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel6.setMyWatchFeatureType(MyWatchFeatureType.FEMALE_WELLNESS_TRACKER);
            arrayList.add(myWatchFeatureModel6);
            MyWatchFeatureModel myWatchFeatureModel7 = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel7.setMyWatchFeatureType(MyWatchFeatureType.ALARMS);
            arrayList.add(myWatchFeatureModel7);
            B().clViewLess.setVisibility(8);
            if (arrayList.size() > 4) {
                B().clViewAll.setVisibility(0);
            } else {
                B().clViewAll.setVisibility(8);
            }
            if (arrayList.isEmpty()) {
                B().watchFeatureHeaderTv.setVisibility(8);
            }
            int size = arrayList.size();
            int i = 1;
            int i2 = 0;
            while (i2 < size) {
                ((MyWatchFeatureModel) arrayList.get(i2)).setShow(i2 <= 3);
                if (i2 % 3 == 0) {
                    i = 1;
                }
                if (i == 1) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(arrayList.get(i2));
                    this.p.add(arrayList2);
                } else if (i == 2) {
                    List<List<MyWatchFeatureModel>> list = this.p;
                    List<MyWatchFeatureModel> list2 = list.get(list.size() - 1);
                    list2.add(arrayList.get(i2));
                    List<List<MyWatchFeatureModel>> list3 = this.p;
                    list3.set(list3.size() - 1, list2);
                } else if (i == 3) {
                    List<List<MyWatchFeatureModel>> list4 = this.p;
                    List<MyWatchFeatureModel> list5 = list4.get(list4.size() - 1);
                    List<List<MyWatchFeatureModel>> list6 = this.p;
                    list6.remove(list6.size() - 1);
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(list5.get(0));
                    this.p.add(arrayList3);
                    ArrayList arrayList4 = new ArrayList();
                    arrayList4.add(list5.get(1));
                    arrayList4.add((MyWatchFeatureModel) arrayList.get(i2));
                    this.p.add(arrayList4);
                }
                i++;
                i2++;
            }
            MyWatchFeaturesAdapter myWatchFeaturesAdapter2 = this.o;
            if (myWatchFeaturesAdapter2 != null) {
                myWatchFeaturesAdapter2.setMyWatchFeatureList(this.p);
            }
            B().rvMyWatchFeatures.setLayoutManager(new LinearLayoutManager(requireActivity()));
        }
    }

    public final void D() {
        ProfileData userDetails;
        WomenWellnessData womenWellnessData;
        B().clFemaleWellnessTracker.setVisibility(8);
        if (!BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isFemaleWellnessSupported() || (userDetails = SessionManager.getInstance(requireContext()).getUserDetails()) == null || AppUtils.isEmpty(userDetails.getGender()) || !kotlin.text.m.equals(userDetails.getGender(), Dashboard2Constants.FEMALE, true) || (womenWellnessData = UserDataManager.getInstance(requireContext()).getWomenWellnessData()) == null || womenWellnessData.getLastPeriodDay() == 0) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, womenWellnessData.getLastPeriodDay());
        calendar.set(1, womenWellnessData.getLastPeriodYear());
        calendar.set(2, womenWellnessData.getLastPeriodMonth() - 1);
        String format = this.t.format(Long.valueOf(calendar.getTimeInMillis()));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateformat.format(statCal.timeInMillis)");
        Object clone = calendar.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar2 = (Calendar) clone;
        calendar2.add(6, womenWellnessData.getMenstruationCycleLength());
        String format2 = this.t.format(Long.valueOf(calendar2.getTimeInMillis()));
        Intrinsics.checkNotNullExpressionValue(format2, "simpleDateformat.format(endCal.timeInMillis)");
        TextView textView = B().femaleDetailTv;
        textView.setText(format + " - " + format2);
        B().clFemaleWellnessTracker.setVisibility(0);
        B().clFemaleWellnessTrackerHeader.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentMyWatch.E(FragmentMyWatch.this, view);
            }
        });
        B().clFemaleWellnessTrackerData.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentMyWatch.F(FragmentMyWatch.this, view);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.Object] */
    public final void G() {
        ProfileData userDetails;
        TextView textView = B().watchFeatureHeaderTv;
        StringBuilder sb = new StringBuilder();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        sb.append(companion.getWatchName(requireContext));
        sb.append(' ');
        String string = getString(R.string.features);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.features)");
        String lowerCase = string.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        sb.append(lowerCase);
        textView.setText(sb.toString());
        this.p.clear();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        MyWatchFeaturesAdapter myWatchFeaturesAdapter = new MyWatchFeaturesAdapter(requireActivity);
        this.o = myWatchFeaturesAdapter;
        myWatchFeaturesAdapter.setMyFeatureClickedListener(this);
        ArrayList arrayList = new ArrayList();
        if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isSensAISupported() && !this.u) {
            MyWatchFeatureModel myWatchFeatureModel = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel.setMyWatchFeatureType(MyWatchFeatureType.SENSAI);
            arrayList.add(myWatchFeatureModel);
        }
        DoMoreWithYourWatchHelper doMoreWithYourWatchHelper = DoMoreWithYourWatchHelper.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        if (doMoreWithYourWatchHelper.isSetupAlexaConnectSupported(requireContext2) && !SessionManager.getInstance(requireContext()).getAlexaAccountLinkingStatus()) {
            MyWatchFeatureModel myWatchFeatureModel2 = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel2.setMyWatchFeatureType(MyWatchFeatureType.ALEXA);
            arrayList.add(myWatchFeatureModel2);
        }
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        if (doMoreWithYourWatchHelper.isSportScoreSupported(requireContext3)) {
            SportsPreference.Companion companion2 = SportsPreference.Companion;
            Context requireContext4 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
            if (companion2.getSportsNotification(requireContext4) == null) {
                MyWatchFeatureModel myWatchFeatureModel3 = new MyWatchFeatureModel(null, false, 3, null);
                myWatchFeatureModel3.setMyWatchFeatureType(MyWatchFeatureType.SPORTS);
                arrayList.add(myWatchFeatureModel3);
            }
        }
        if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isCustomRemindersSupported()) {
            PreferenceManagerCustomReminders.Companion companion3 = PreferenceManagerCustomReminders.Companion;
            Context requireContext5 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
            List<CustomReminder> reminders = companion3.getReminders(requireContext5);
            if (reminders == null || reminders.isEmpty()) {
                MyWatchFeatureModel myWatchFeatureModel4 = new MyWatchFeatureModel(null, false, 3, null);
                myWatchFeatureModel4.setMyWatchFeatureType(MyWatchFeatureType.CUSTOM_REMINDER);
                arrayList.add(myWatchFeatureModel4);
            }
        }
        Context requireContext6 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
        if (doMoreWithYourWatchHelper.isTapAndPaySupported(requireContext6)) {
            MyWatchFeatureModel myWatchFeatureModel5 = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel5.setMyWatchFeatureType(MyWatchFeatureType.TAP_AND_PAY);
            arrayList.add(myWatchFeatureModel5);
        }
        if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isFemaleWellnessSupported() && (userDetails = SessionManager.getInstance(requireContext()).getUserDetails()) != null && !AppUtils.isEmpty(userDetails.getGender()) && kotlin.text.m.equals(userDetails.getGender(), Dashboard2Constants.FEMALE, true) && UserDataManager.getInstance(requireContext()).getWomenWellnessData() != null && UserDataManager.getInstance(requireContext()).getWomenWellnessData().getLastPeriodDay() == 0) {
            MyWatchFeatureModel myWatchFeatureModel6 = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel6.setMyWatchFeatureType(MyWatchFeatureType.FEMALE_WELLNESS_TRACKER);
            arrayList.add(myWatchFeatureModel6);
        }
        List<VibrationAlarmData> vibrationAlarmData = UserDataManager.getInstance(requireContext()).getVibrationAlarmData();
        if (vibrationAlarmData == null || vibrationAlarmData.isEmpty()) {
            MyWatchFeatureModel myWatchFeatureModel7 = new MyWatchFeatureModel(null, false, 3, null);
            myWatchFeatureModel7.setMyWatchFeatureType(MyWatchFeatureType.ALARMS);
            arrayList.add(myWatchFeatureModel7);
        }
        B().clViewLess.setVisibility(8);
        if (arrayList.size() > 4) {
            B().clViewAll.setVisibility(0);
        } else {
            B().clViewAll.setVisibility(8);
        }
        if (arrayList.isEmpty()) {
            B().watchFeatureHeaderTv.setVisibility(8);
        }
        int size = arrayList.size();
        int i = 0;
        int i2 = 1;
        while (i < size) {
            ((MyWatchFeatureModel) arrayList.get(i)).setShow(i <= 3);
            if (i % 3 == 0) {
                i2 = 1;
            }
            if (i2 == 1) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(arrayList.get(i));
                this.p.add(arrayList2);
            } else if (i2 == 2) {
                List<List<MyWatchFeatureModel>> list = this.p;
                List<MyWatchFeatureModel> list2 = list.get(list.size() - 1);
                list2.add(arrayList.get(i));
                List<List<MyWatchFeatureModel>> list3 = this.p;
                list3.set(list3.size() - 1, list2);
            } else if (i2 == 3) {
                List<List<MyWatchFeatureModel>> list4 = this.p;
                List<MyWatchFeatureModel> list5 = list4.get(list4.size() - 1);
                List<List<MyWatchFeatureModel>> list6 = this.p;
                list6.remove(list6.size() - 1);
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(list5.get(0));
                this.p.add(arrayList3);
                ArrayList arrayList4 = new ArrayList();
                arrayList4.add(list5.get(1));
                arrayList4.add((MyWatchFeatureModel) arrayList.get(i));
                this.p.add(arrayList4);
            }
            i2++;
            i++;
        }
        MyWatchFeaturesAdapter myWatchFeaturesAdapter2 = this.o;
        if (myWatchFeaturesAdapter2 != null) {
            myWatchFeaturesAdapter2.setMyWatchFeatureList(this.p);
        }
        B().rvMyWatchFeatures.setLayoutManager(new LinearLayoutManager(requireActivity()));
        B().rvMyWatchFeatures.setAdapter(this.o);
    }

    public final void H() {
        B().personalizedWatchfaceContainer.getRoot().setVisibility(0);
        B().watchfaceStudioBigTop.getRoot().setVisibility(8);
        B().watchfaceStudioBigTop.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentMyWatch.I(FragmentMyWatch.this, view);
            }
        });
    }

    public final void J() {
        if (UserDataManager.getInstance(requireActivity()).isOneKSupported()) {
            B().activities700plus.getRoot().setVisibility(0);
        } else {
            B().activities700plus.getRoot().setVisibility(8);
        }
    }

    public final void K() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        B().clWatchSettings.setVisibility(0);
        DeviceSupportedFeatures deviceSupportedFeatures3 = BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures();
        boolean z = true;
        if (deviceSupportedFeatures3 != null && deviceSupportedFeatures3.isFindMyBandSupported()) {
            B().clFindMyWatch.setVisibility(0);
        } else {
            B().clFindMyWatch.setVisibility(8);
        }
        BleApi bleApi = BleApiManager.getInstance(requireActivity()).getBleApi();
        if (!((bleApi == null || (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures2.isKahaBTCallSupported()) ? false : true)) {
            BleApi bleApi2 = BleApiManager.getInstance(getContext()).getBleApi();
            if (!((bleApi2 == null || (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isBTCallingSupported()) ? false : true)) {
                z = false;
            }
        }
        this.m = z;
        if (z) {
            B().clBluetoothCalling.setVisibility(0);
        } else {
            B().clBluetoothCalling.setVisibility(8);
        }
    }

    public final void L() {
        ArrayList<String> arrayList = new ArrayList<>();
        B().clSensAIHeader.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentMyWatch.M(FragmentMyWatch.this, view);
            }
        });
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String connectedDeviceMacAddress = new PreferenceManager(requireActivity).getConnectedDeviceMacAddress();
        if (connectedDeviceMacAddress != null) {
            SensAISummaryDataViewModel sensAISummaryDataViewModel = this.q;
            if (sensAISummaryDataViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelSensAIData");
                sensAISummaryDataViewModel = null;
            }
            sensAISummaryDataViewModel.getSensAIActivitySummaryDataFromDB(connectedDeviceMacAddress, arrayList, 1).observe(requireActivity(), new Observer() { // from class: com.coveiot.android.leonardo.more.fragments.z
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentMyWatch.N(FragmentMyWatch.this, (List) obj);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    public final void O() {
        String sport;
        String lowerCase;
        DoMoreWithYourWatchHelper doMoreWithYourWatchHelper = DoMoreWithYourWatchHelper.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (doMoreWithYourWatchHelper.isSportScoreSupported(requireContext)) {
            SportsPreference.Companion companion = SportsPreference.Companion;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            SportsPreferenceModel sportsNotification = companion.getSportsNotification(requireContext2);
            if (sportsNotification != null) {
                B().clSportsScoreDetail.setVisibility(0);
                String sport2 = sportsNotification.getSport();
                if ((sport2 != null && kotlin.text.m.equals(sport2, SportsType.Football.getSport(), true)) != false) {
                    sport = SportsType.Football.getSport();
                } else {
                    sport = SportsType.Cricket.getSport();
                }
                if (Intrinsics.areEqual(sportsNotification.isEnable(), Boolean.TRUE)) {
                    String string = getString(R.string.enabled);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enabled)");
                    lowerCase = string.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                } else {
                    String string2 = getString(R.string.disabled);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …led\n                    )");
                    lowerCase = string2.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                }
                TextView textView = B().sportsScoreContentTv;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string3 = getString(R.string.sports_score_status);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.sports_score_status)");
                String format = String.format(locale, string3, Arrays.copyOf(new Object[]{sport, lowerCase}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                textView.setText(format);
                ImageView imageView = B().sportsScoreTypeImgV;
                String sport3 = sportsNotification.getSport();
                imageView.setImageResource((sport3 != null && kotlin.text.m.equals(sport3, SportsType.Football.getSport(), true)) != false ? R.drawable.ic_football_24dp : R.drawable.ic_bat_swing_24dp);
                String sport4 = sportsNotification.getSport();
                int i = 15;
                if ((sport4 != null && kotlin.text.m.equals(sport4, SportsType.Football.getSport(), true)) != false) {
                    B().sportsScoreTypeTv.setVisibility(8);
                    Context requireContext3 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(requireContext3);
                    String title = selectedSoccerMatch != null ? selectedSoccerMatch.getTitle() : null;
                    if ((title == null || title.length() == 0) == false) {
                        TextView textView2 = B().sportsScoreTypeTv;
                        Intrinsics.checkNotNull(selectedSoccerMatch);
                        String title2 = selectedSoccerMatch.getTitle();
                        Intrinsics.checkNotNull(title2);
                        textView2.setText(title2);
                        B().sportsScoreTypeTv.setVisibility(0);
                    }
                } else {
                    B().sportsScoreTypeTv.setVisibility(8);
                    if (sportsNotification.getInterval() != null) {
                        Integer interval = sportsNotification.getInterval();
                        Intrinsics.checkNotNull(interval);
                        if (interval.intValue() > 0) {
                            Integer interval2 = sportsNotification.getInterval();
                            Intrinsics.checkNotNull(interval2);
                            i = interval2.intValue();
                        }
                    }
                    Context requireContext4 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                    MatchListModel selectedMatch = companion.getSelectedMatch(requireContext4);
                    if ((selectedMatch != null ? selectedMatch.getMatchFormat() : null) != null) {
                        Intrinsics.checkNotNull(selectedMatch);
                        Integer matchFormat = selectedMatch.getMatchFormat();
                        Intrinsics.checkNotNull(matchFormat);
                        if (matchFormat.intValue() > 0) {
                            TextView textView3 = B().sportsScoreTypeTv;
                            String string4 = getString(R.string.s_match);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.s_match)");
                            SportsUtils sportsUtils = SportsUtils.INSTANCE;
                            Integer matchFormat2 = selectedMatch.getMatchFormat();
                            Intrinsics.checkNotNull(matchFormat2);
                            String format2 = String.format(locale, string4, Arrays.copyOf(new Object[]{sportsUtils.getMatchFormat(matchFormat2.intValue())}, 1));
                            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                            textView3.setText(format2);
                            B().sportsScoreTypeTv.setVisibility(0);
                        }
                    }
                }
                TextView textView4 = B().intervalTv;
                String string5 = getString(R.string.mins_interval);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.mins_interval)");
                String format3 = String.format(locale, string5, Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                textView4.setText(format3);
                B().clSportsScoreHeader.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.a0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentMyWatch.P(FragmentMyWatch.this, view);
                    }
                });
                return;
            }
            B().clSportsScoreDetail.setVisibility(8);
            return;
        }
        B().clSportsScoreDetail.setVisibility(8);
    }

    public final void Q() {
        if (A()) {
            return;
        }
        if (UserDataManager.getInstance(requireActivity()).getWomenWellnessData() != null) {
            if (UserDataManager.getInstance(requireActivity()).getWomenWellnessData().getLastPeriodDay() == 0) {
                AppNavigator.Companion companion = AppNavigator.Companion;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                companion.navigateToWomenWellnessFTUActivity(requireActivity);
                return;
            }
            AppNavigator.Companion companion2 = AppNavigator.Companion;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            companion2.navigateToFemaleWellnessCalendarViewOrSettings(requireActivity2);
            return;
        }
        AppNavigator.Companion companion3 = AppNavigator.Companion;
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
        companion3.navigateToWomenWellnessFTUActivity(requireActivity3);
    }

    public final void Z() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String string = getString(themesUtils.isGuestUser(requireContext2) ? R.string.login : R.string.pair_device);
        Intrinsics.checkNotNullExpressionValue(string, "if(requireContext().isGu…ing(R.string.pair_device)");
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        String string2 = getString(themesUtils.isGuestUser(requireContext3) ? R.string.please_login_to_use_this_feature : R.string.please_pair_with_your_bluetooth_device_to_use_this_feature);
        Intrinsics.checkNotNullExpressionValue(string2, "if(requireContext().isGu…vice_to_use_this_feature)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentMyWatch.a0(BottomSheetDialogTwoButtons.this, view);
            }
        });
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        String string4 = getString(themesUtils.isGuestUser(requireContext4) ? R.string.login_now : R.string.pair_now);
        Intrinsics.checkNotNullExpressionValue(string4, "if(requireContext().isGu…String(R.string.pair_now)");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentMyWatch.b0(FragmentMyWatch.this, bottomSheetDialogTwoButtons, view);
            }
        });
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
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

    @NotNull
    public final List<List<MyWatchFeatureModel>> getListOfList() {
        return this.p;
    }

    @Nullable
    public final MyWatchFeaturesAdapter getMyWatchFeatureAdapter() {
        return this.o;
    }

    @NotNull
    public final SimpleDateFormat getSimpleDateformat() {
        return this.t;
    }

    public final boolean isSensAIDataPresent() {
        return this.u;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.n = FragmentMyWatchBinding.inflate(inflater, viewGroup, false);
        return B().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.MyWatchFeaturesAdapter.OnFeatureClickListener
    public void onFeatureClicked(@NotNull MyWatchFeatureType featureType) {
        BleApi bleApi;
        BleApi bleApi2;
        Intrinsics.checkNotNullParameter(featureType, "featureType");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                ConnectionStatus connectionStatus = null;
                switch (WhenMappings.$EnumSwitchMapping$0[featureType.ordinal()]) {
                    case 1:
                        if (A()) {
                            return;
                        }
                        startActivity(new Intent(requireActivity(), ActivityCustomReminders.class));
                        return;
                    case 2:
                        if (A() || !(requireActivity() instanceof ActivityDashboardNew)) {
                            return;
                        }
                        FragmentActivity requireActivity = requireActivity();
                        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.ActivityDashboardNew");
                        ((ActivityDashboardNew) requireActivity).navigateToSportsActivity();
                        return;
                    case 3:
                        if (A() || !(requireActivity() instanceof ActivityDashboardNew)) {
                            return;
                        }
                        FragmentActivity requireActivity2 = requireActivity();
                        Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.ActivityDashboardNew");
                        ((ActivityDashboardNew) requireActivity2).navigateToAlexaConnect();
                        return;
                    case 4:
                        if (A()) {
                            return;
                        }
                        BleApiManager bleApiManager = BleApiManager.getInstance(requireContext());
                        if (bleApiManager != null && (bleApi = bleApiManager.getBleApi()) != null) {
                            connectionStatus = bleApi.getConnectionStatus();
                        }
                        if (connectionStatus == ConnectionStatus.CONNECTED) {
                            startActivity(new Intent(requireActivity(), ActivityAlarmNew.class));
                            return;
                        } else {
                            Toast.makeText(requireActivity(), getString(R.string.band_not_connected), 0).show();
                            return;
                        }
                    case 5:
                        if (A()) {
                            return;
                        }
                        BleApiManager bleApiManager2 = BleApiManager.getInstance(requireContext());
                        if (bleApiManager2 != null && (bleApi2 = bleApiManager2.getBleApi()) != null) {
                            connectionStatus = bleApi2.getConnectionStatus();
                        }
                        if (connectionStatus == ConnectionStatus.CONNECTED) {
                            AppNavigator.Companion companion = AppNavigator.Companion;
                            Context requireContext3 = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                            companion.navigateToSensAIHomeScreen(requireContext3);
                            return;
                        }
                        Toast.makeText(requireActivity(), getString(R.string.band_not_connected), 0).show();
                        return;
                    case 6:
                        if (requireActivity() instanceof ActivityDashboardNew) {
                            FragmentActivity requireActivity3 = requireActivity();
                            Intrinsics.checkNotNull(requireActivity3, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.ActivityDashboardNew");
                            ((ActivityDashboardNew) requireActivity3).navigateToTapAndPay();
                            return;
                        }
                        return;
                    case 7:
                        if (A()) {
                            return;
                        }
                        Q();
                        return;
                    default:
                        return;
                }
            }
        }
        Z();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (isAdded()) {
            ProfileData userDetails = SessionManager.getInstance(requireContext()).getUserDetails();
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (!themesUtils.isGuestUser(requireContext)) {
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                if (!themesUtils.isPairDeviceLater(requireContext2)) {
                    if (userDetails != null) {
                        String name = userDetails.getName();
                        if (!(name == null || name.length() == 0)) {
                            TextView textView = B().topHeaderTv;
                            StringBuilder sb = new StringBuilder();
                            sb.append(userDetails.getName());
                            sb.append("'s ");
                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                            Context requireContext3 = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                            sb.append(companion.getWatchName(requireContext3));
                            textView.setText(sb.toString());
                        }
                    }
                    H();
                    K();
                    J();
                    O();
                    if (this.q != null) {
                        L();
                    }
                    D();
                    return;
                }
            }
            B().topHeaderTv.setText(getString(R.string.boathead_s_watch));
            B().personalizedWatchfaceContainer.tvHeader.setText(getString(R.string.pair_your_device_explore_wide_range_of_watch_faces));
            B().personalizedWatchfaceContainer.tvChangeYourWatchFace.setText(getString(R.string.pair_your_device));
            View root = B().activities700plus.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.activities700plus.root");
            visible(root);
            B().activities700plus.tvActionButton.setText(getString(R.string.get_started));
            ConstraintLayout constraintLayout = B().clFemaleWellnessTracker;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clFemaleWellnessTracker");
            gone(constraintLayout);
            ConstraintLayout constraintLayout2 = B().clSensAIDetails;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clSensAIDetails");
            gone(constraintLayout2);
            ConstraintLayout constraintLayout3 = B().clSportsScoreDetail;
            Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clSportsScoreDetail");
            gone(constraintLayout3);
            ConstraintLayout constraintLayout4 = B().clFeatureInfo;
            Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.clFeatureInfo");
            visible(constraintLayout4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x017e  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r6, @org.jetbrains.annotations.Nullable android.os.Bundle r7) {
        /*
            Method dump skipped, instructions count: 395
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.FragmentMyWatch.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    public final void setListOfList(@NotNull List<List<MyWatchFeatureModel>> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.p = list;
    }

    public final void setMyWatchFeatureAdapter(@Nullable MyWatchFeaturesAdapter myWatchFeaturesAdapter) {
        this.o = myWatchFeaturesAdapter;
    }

    public final void setSensAIDataPresent(boolean z) {
        this.u = z;
    }

    public final void setSimpleDateformat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.t = simpleDateFormat;
    }
}
