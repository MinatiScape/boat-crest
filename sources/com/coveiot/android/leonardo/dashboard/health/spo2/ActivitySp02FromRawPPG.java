package com.coveiot.android.leonardo.dashboard.health.spo2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.request.LiveHeartRateControlRequest;
import com.coveiot.android.bleabstract.request.LiveRawPPGControlRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2DeviceType;
import com.coveiot.android.leonardo.dashboard.home.adapters.RemotePagerAdapter;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.model.SPO2LowDataModel;
import com.coveiot.android.leonardo.probe.ReminderJobServiceJobService;
import com.coveiot.android.leonardo.sp02.preference.Spo2DataManager;
import com.coveiot.android.leonardo.utils.PagerContainer;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.compundview.AnimatedDotsView;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.spo2.SPO2EstimationReq;
import com.coveiot.coveaccess.spo2.SSPO2EstimationPostRes;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.coveiot.repository.profile.read.ProfileDBRead;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import com.viewpagerindicator.CirclePageIndicator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivitySp02FromRawPPG extends BaseActivity implements Observer<PPGData> {
    @Nullable
    public BottomSheetDialogImageTitleMessage A;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage B;
    @Nullable
    public MutableLiveData<PPGData> C;
    @Nullable
    public BottomSheetDialogTwoButtons q;
    public boolean u;
    public boolean v;
    public boolean w;
    public ViewModelActivityDashboardNew x;
    @NotNull
    public final CountDownTimer y;
    @Nullable
    public BottomSheetDialogImageTitleMessageTwoBtns z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final StringBuilder p = new StringBuilder();
    public final String r = ActivitySp02FromRawPPG.class.getSimpleName();
    @NotNull
    public final int[] s = {R.string.spo2_instruction_text1, R.string.spo2_instruction_text2, R.string.spo2_instruction_text3, R.string.spo2_instruction_text4};
    @NotNull
    public final int[] t = {R.drawable.spo2_instruction1, R.drawable.spo2_instruction2, R.drawable.spo2_instruction1, R.drawable.warning_icon};

    public ActivitySp02FromRawPPG() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        BleApiManager.getInstance(this).getDeviceType();
        DeviceType deviceType = DeviceType.jstyle1810G;
        final long millis = timeUnit.toMillis(80L);
        final long millis2 = timeUnit.toMillis(1L);
        this.y = new CountDownTimer(millis, millis2) { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.ActivitySp02FromRawPPG$countDownTimer$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                ((AnimatedDotsView) ActivitySp02FromRawPPG.this._$_findCachedViewById(R.id.spo2DotView)).stopAnimation();
                ActivitySp02FromRawPPG.this.stopReadingRawPPG();
                if (ActivitySp02FromRawPPG.this.getPpgData() != null) {
                    if (ActivitySp02FromRawPPG.this.getPpgData().length() > 0) {
                        StringBuilder deleteCharAt = ActivitySp02FromRawPPG.this.getPpgData().deleteCharAt(ActivitySp02FromRawPPG.this.getPpgData().length() - 1);
                        if (!AppUtils.isEmpty(deleteCharAt.toString())) {
                            ActivitySp02FromRawPPG activitySp02FromRawPPG = ActivitySp02FromRawPPG.this;
                            String sb = deleteCharAt.toString();
                            Intrinsics.checkNotNullExpressionValue(sb, "signal.toString()");
                            activitySp02FromRawPPG.L(sb);
                            return;
                        }
                        ActivitySp02FromRawPPG.this.setPPGSuccess(false);
                        ActivitySp02FromRawPPG.this.F();
                        return;
                    }
                }
                ActivitySp02FromRawPPG.this.setPPGSuccess(false);
                ActivitySp02FromRawPPG.this.F();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
                String format = String.format("%02d:%02d min", Arrays.copyOf(new Object[]{Long.valueOf(timeUnit2.toMinutes(j) % TimeUnit.HOURS.toMinutes(1L)), Long.valueOf(timeUnit2.toSeconds(j) % TimeUnit.MINUTES.toSeconds(1L))}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                ((TextView) ActivitySp02FromRawPPG.this._$_findCachedViewById(R.id.tv_countdown_timer)).setText(format);
            }
        };
    }

    public static final void D(ActivitySp02FromRawPPG this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.u) {
            this$0.P();
        } else {
            this$0.Y();
        }
    }

    public static final void E(ActivitySp02FromRawPPG this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((TextView) this$0._$_findCachedViewById(R.id.close)).performClick();
    }

    public static final void G(ActivitySp02FromRawPPG this$0, ConnectionStatus connectionStatus) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (connectionStatus == ConnectionStatus.DISCONNECTED) {
            this$0.N();
        }
    }

    public static final void I(ActivitySp02FromRawPPG this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.W();
    }

    public static final void J(ActivitySp02FromRawPPG this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((TextView) this$0._$_findCachedViewById(R.id.close)).performClick();
    }

    public static final void O(ActivitySp02FromRawPPG this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.B;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }
        this$0.Y();
    }

    public static final void Q(ActivitySp02FromRawPPG this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
        this$0.Y();
    }

    public static final void R(ActivitySp02FromRawPPG this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void T(ActivitySp02FromRawPPG this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this$0.z;
        if (bottomSheetDialogImageTitleMessageTwoBtns != null) {
            bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
        }
        this$0.X();
        Toast.makeText(this$0, this$0.getString(R.string.reminder_set_msg) + Spo2DataManager.getInstance(this$0).getReminderInterval() + " mins", 1).show();
        this$0.Y();
    }

    public static final void U(ActivitySp02FromRawPPG this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this$0.z;
        if (bottomSheetDialogImageTitleMessageTwoBtns != null) {
            bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
        }
        this$0.A();
        ((TextView) this$0._$_findCachedViewById(R.id.close)).performClick();
    }

    public final void A() {
        PayUtils.INSTANCE.cancelReminderJob(this);
        NotificationManager.getInstance().cancleNotification(this, ReminderJobServiceJobService.Companion.getSPO2_REMINDER_NOTIFICATION_ID());
    }

    public final CharSequence B(String str) {
        if (str != null) {
            switch (str.hashCode()) {
                case 48:
                    if (str.equals(BleConst.GetDeviceTime)) {
                        return SPO2Level.VERY_LOW.name();
                    }
                    break;
                case 49:
                    if (str.equals("1")) {
                        return SPO2Level.LOW.name();
                    }
                    break;
                case 50:
                    if (str.equals("2")) {
                        return SPO2Level.NORMAL.name();
                    }
                    break;
            }
        }
        Intrinsics.checkNotNull(str);
        if (Double.parseDouble(str) >= 95.0d) {
            return SPO2Level.NORMAL.name();
        }
        if (Double.parseDouble(str) < 95.0d && Double.parseDouble(str) >= 93.0d) {
            return SPO2Level.LOW.name();
        }
        return SPO2Level.VERY_LOW.name();
    }

    public final void C() {
        View findViewById = findViewById(R.id.viewpagger_spo2);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type com.coveiot.android.leonardo.utils.PagerContainer");
        PagerContainer pagerContainer = (PagerContainer) findViewById;
        pagerContainer.setAdapter(new RemotePagerAdapter(getSupportFragmentManager(), this, this.s, this.t));
        pagerContainer.setAnimationEnabled(true);
        pagerContainer.setFadeEnabled(true);
        pagerContainer.setFadeFactor(0.6f);
        ((CirclePageIndicator) _$_findCachedViewById(R.id.circlePageIndicator_spo2)).setViewPager(pagerContainer);
    }

    public final void F() {
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage;
        if (this.A == null) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_mesurement_failed);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…ble.ic_mesurement_failed)");
            String string = getString(R.string.measurement_failed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.measurement_failed)");
            String string2 = getString(R.string.please_try_again);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_try_again)");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = new BottomSheetDialogImageTitleMessage(this, drawable, string, string2, false);
            this.A = bottomSheetDialogImageTitleMessage2;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogImageTitleMessage2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.ActivitySp02FromRawPPG$onPPGMesurementFailed$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    ((TextView) ActivitySp02FromRawPPG.this._$_findCachedViewById(R.id.close)).performClick();
                }
            });
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.A;
            if (bottomSheetDialogImageTitleMessage3 != null) {
                bottomSheetDialogImageTitleMessage3.setCancelable(false);
            }
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.A;
        Boolean valueOf = bottomSheetDialogImageTitleMessage4 != null ? Boolean.valueOf(bottomSheetDialogImageTitleMessage4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() || (bottomSheetDialogImageTitleMessage = this.A) == null) {
            return;
        }
        bottomSheetDialogImageTitleMessage.show();
    }

    public final void H() {
        A();
        ((LinearLayout) _$_findCachedViewById(R.id.mesured_value_layout)).setVisibility(0);
        ((TextView) _$_findCachedViewById(R.id.blood_saturation_tv)).setVisibility(0);
        ((LinearLayout) _$_findCachedViewById(R.id.measuring_timer_layout)).setVisibility(8);
        ((ConstraintLayout) _$_findCachedViewById(R.id.meanusrement_reminder_layout)).setVisibility(8);
        ((ConstraintLayout) _$_findCachedViewById(R.id.measurment_completed_layout)).setVisibility(0);
        ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.result));
        ((TextView) _$_findCachedViewById(R.id.spo2_measurment_title)).setText(getResources().getString(R.string.spo2_measurement_in_complete));
        ((Button) _$_findCachedViewById(R.id.btn_tryagain)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySp02FromRawPPG.I(ActivitySp02FromRawPPG.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_done)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySp02FromRawPPG.J(ActivitySp02FromRawPPG.this, view);
            }
        });
    }

    public final void K(LiveRawPPGControlRequest liveRawPPGControlRequest) {
        BleApiManager.getInstance(this).getBleApi().setUserSettings(liveRawPPGControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.ActivitySp02FromRawPPG$sendPPGCommand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    public final void L(String str) {
        LogHelper.d("SIGNAL COUNT", String.valueOf(StringsKt__StringsKt.split$default((CharSequence) str, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).size()));
        String string = getString(R.string.calclating_spo2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.calclating_spo2)");
        showProgressWithTitle(string);
        EntityProfile latestProfileData = ProfileDBRead.getInstance(this).getLatestProfileData();
        boolean z = false;
        if (latestProfileData != null && latestProfileData.age == 0) {
            z = true;
        }
        SPO2EstimationReq sPO2EstimationReq = new SPO2EstimationReq(str, String.valueOf(z ? 30 : latestProfileData.age), String.valueOf(latestProfileData.gender), String.valueOf((int) latestProfileData.weight), String.valueOf(latestProfileData.height), "1", BleApiManager.getInstance(this).getDeviceType().name());
        sPO2EstimationReq.setRegression("1");
        CoveUserAppSettings.sendSPO2Estimation(sPO2EstimationReq, new CoveApiListener<SSPO2EstimationPostRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.ActivitySp02FromRawPPG$sendPPGDataToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ActivitySp02FromRawPPG.this.setPPGSuccess(false);
                ActivitySp02FromRawPPG.this.dismissProgress();
                ActivitySp02FromRawPPG.this.F();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SSPO2EstimationPostRes sSPO2EstimationPostRes) {
                CharSequence B;
                ViewModelActivityDashboardNew viewModelActivityDashboardNew;
                boolean z2;
                ActivitySp02FromRawPPG.this.dismissProgress();
                if (sSPO2EstimationPostRes != null) {
                    try {
                        Integer type = sSPO2EstimationPostRes.getType();
                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
                        String result = sSPO2EstimationPostRes.getResult();
                        ViewModelActivityDashboardNew viewModelActivityDashboardNew2 = null;
                        if (type != null && type.intValue() == 0) {
                            Intrinsics.checkNotNullExpressionValue(result, "result");
                            ((TextView) ActivitySp02FromRawPPG.this._$_findCachedViewById(R.id.tv_spo2value)).setText(String.valueOf(decimalFormat.format(Double.parseDouble(result))));
                            Intrinsics.checkNotNullExpressionValue(result, "result");
                            if (Float.parseFloat(result) < 95.0f) {
                                ActivitySp02FromRawPPG.this.V();
                            } else {
                                ActivitySp02FromRawPPG.this.setShouldShowReminder(false);
                                Spo2DataManager.getInstance(ActivitySp02FromRawPPG.this).setSpo2LowDataModel(null);
                            }
                        } else {
                            if (result.equals(BleConst.GetDeviceTime)) {
                                result = "1";
                            }
                            if (result.equals("1")) {
                                ActivitySp02FromRawPPG.this.V();
                            } else if (result.equals("2")) {
                                ActivitySp02FromRawPPG.this.setShouldShowReminder(false);
                                Spo2DataManager.getInstance(ActivitySp02FromRawPPG.this).setSpo2LowDataModel(null);
                            }
                            B = ActivitySp02FromRawPPG.this.B(result);
                            ((TextView) ActivitySp02FromRawPPG.this._$_findCachedViewById(R.id.tv_spo2value)).setText(m.replace$default(String.valueOf(B), "_", HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null));
                            ((TextView) ActivitySp02FromRawPPG.this._$_findCachedViewById(R.id.tv_percent)).setVisibility(8);
                        }
                        if (!ActivitySp02FromRawPPG.this.getShouldShowReminder()) {
                            viewModelActivityDashboardNew = ActivitySp02FromRawPPG.this.x;
                            if (viewModelActivityDashboardNew == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            } else {
                                viewModelActivityDashboardNew2 = viewModelActivityDashboardNew;
                            }
                            Intrinsics.checkNotNullExpressionValue(result, "result");
                            double parseDouble = Double.parseDouble(result);
                            Spo2DeviceType spo2DeviceType = Spo2DeviceType.PPG;
                            if (type != null && type.intValue() == 1) {
                                z2 = true;
                                viewModelActivityDashboardNew2.saveSPO2Value(parseDouble, spo2DeviceType, z2);
                            }
                            z2 = false;
                            viewModelActivityDashboardNew2.saveSPO2Value(parseDouble, spo2DeviceType, z2);
                        }
                        ActivitySp02FromRawPPG.this.setPPGSuccess(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ActivitySp02FromRawPPG.this.setPPGSuccess(false);
                    }
                }
                if (!ActivitySp02FromRawPPG.this.isPPGSuccess()) {
                    ActivitySp02FromRawPPG.this.F();
                } else if (ActivitySp02FromRawPPG.this.getShouldShowReminder()) {
                    ActivitySp02FromRawPPG.this.S();
                } else {
                    ActivitySp02FromRawPPG.this.H();
                }
            }
        });
    }

    public final void M() {
        LiveRawPPGControlRequest liveRawPPGControlRequest = new LiveRawPPGControlRequest.Builder(false).build();
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        Intrinsics.checkNotNullExpressionValue(liveRawPPGControlRequest, "liveRawPPGControlRequest");
        bleApi.setUserSettings(liveRawPPGControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.ActivitySp02FromRawPPG$sendStopPPGCommand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    public final void N() {
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage;
        Z();
        if (this.B == null) {
            String string = getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
            String string2 = getString(R.string.please_connect_the_device);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            this.B = bottomSheetDialogOneButtonTitleMessage2;
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySp02FromRawPPG.O(ActivitySp02FromRawPPG.this, view);
                }
            });
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.B;
            if (bottomSheetDialogOneButtonTitleMessage3 != null) {
                bottomSheetDialogOneButtonTitleMessage3.setCancelable(false);
            }
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage4 = this.B;
        Boolean valueOf = bottomSheetDialogOneButtonTitleMessage4 != null ? Boolean.valueOf(bottomSheetDialogOneButtonTitleMessage4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() || (bottomSheetDialogOneButtonTitleMessage = this.B) == null) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void P() {
        if (this.q == null) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.measurement_in_progress);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.measurement_in_progress)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.q = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.yes);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySp02FromRawPPG.Q(ActivitySp02FromRawPPG.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.f4085no);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySp02FromRawPPG.R(ActivitySp02FromRawPPG.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        if (bottomSheetDialogTwoButtons3.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        bottomSheetDialogTwoButtons4.show();
    }

    public final void S() {
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns;
        ((LinearLayout) _$_findCachedViewById(R.id.mesured_value_layout)).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.blood_saturation_tv)).setVisibility(8);
        ((LinearLayout) _$_findCachedViewById(R.id.measuring_timer_layout)).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.result));
        ((TextView) _$_findCachedViewById(R.id.spo2_measurment_title)).setText(getResources().getString(R.string.spo2_measurement_in_complete));
        boolean z = false;
        if (this.z == null) {
            Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
            DrawableCompat.setTint(drawable, getColor(R.color.colorPrimary));
            Intrinsics.checkNotNullExpressionValue(drawable, "drawable");
            String string = getString(R.string.information);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.information)");
            String string2 = getString(R.string.we_are_unable_to_evaluate_your_spo2);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.we_ar…le_to_evaluate_your_spo2)");
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns2 = new BottomSheetDialogImageTitleMessageTwoBtns(this, drawable, string, string2, false);
            this.z = bottomSheetDialogImageTitleMessageTwoBtns2;
            String string3 = getString(R.string.remind);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.remind)");
            bottomSheetDialogImageTitleMessageTwoBtns2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySp02FromRawPPG.T(ActivitySp02FromRawPPG.this, view);
                }
            });
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns3 = this.z;
            if (bottomSheetDialogImageTitleMessageTwoBtns3 != null) {
                String string4 = getString(R.string.cancel);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
                bottomSheetDialogImageTitleMessageTwoBtns3.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.a
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivitySp02FromRawPPG.U(ActivitySp02FromRawPPG.this, view);
                    }
                });
            }
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns4 = this.z;
            if (bottomSheetDialogImageTitleMessageTwoBtns4 != null) {
                bottomSheetDialogImageTitleMessageTwoBtns4.setCancelable(false);
            }
        }
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns5 = this.z;
        if (bottomSheetDialogImageTitleMessageTwoBtns5 != null && !bottomSheetDialogImageTitleMessageTwoBtns5.isShowing()) {
            z = true;
        }
        if (!z || (bottomSheetDialogImageTitleMessageTwoBtns = this.z) == null) {
            return;
        }
        bottomSheetDialogImageTitleMessageTwoBtns.show();
    }

    public final void V() {
        this.w = true;
        int consecutiveLowCountLimit = Spo2DataManager.getInstance(this).getConsecutiveLowCountLimit();
        if (Spo2DataManager.getInstance(this).getSpo2LowDataModel() == null) {
            if (consecutiveLowCountLimit > 1) {
                SPO2LowDataModel sPO2LowDataModel = new SPO2LowDataModel(1, System.currentTimeMillis());
                LogHelper.d(this.r, "Spo2 data Null");
                Spo2DataManager.getInstance(this).setSpo2LowDataModel(sPO2LowDataModel);
                return;
            }
            this.w = false;
            return;
        }
        SPO2LowDataModel spo2LowDataModel = Spo2DataManager.getInstance(this).getSpo2LowDataModel();
        LogHelper.d(this.r, spo2LowDataModel.toString());
        boolean z = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - spo2LowDataModel.getFirstLowTimeStamp()) >= ((long) Spo2DataManager.getInstance(this).getMinTimeOut());
        if (spo2LowDataModel.getLowCount() + 1 < consecutiveLowCountLimit && !z) {
            spo2LowDataModel.setLowCount(spo2LowDataModel.getLowCount() + 1);
            LogHelper.d(this.r, "shouldremind Spo2 false");
            Spo2DataManager.getInstance(this).setSpo2LowDataModel(spo2LowDataModel);
            return;
        }
        LogHelper.d(this.r, "shouldremind Spo2 true");
        this.w = false;
        Spo2DataManager.getInstance(this).setSpo2LowDataModel(null);
    }

    public final void W() {
        ((AnimatedDotsView) _$_findCachedViewById(R.id.spo2DotView)).startAnimation();
        this.y.start();
        this.u = true;
        ((LinearLayout) _$_findCachedViewById(R.id.mesured_value_layout)).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.blood_saturation_tv)).setVisibility(8);
        ((ConstraintLayout) _$_findCachedViewById(R.id.measurment_completed_layout)).setVisibility(8);
        ((LinearLayout) _$_findCachedViewById(R.id.measuring_timer_layout)).setVisibility(0);
        ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.measuring_spo2));
        ((TextView) _$_findCachedViewById(R.id.spo2_measurment_title)).setText(getResources().getString(R.string.spo2_measurement_in_progress));
        ((TextView) _$_findCachedViewById(R.id.tv_spo2value)).setText("");
        kotlin.text.i.clear(this.p);
        if (this.C == null) {
            this.C = BleApiManager.getInstance(this).getBleApi().registerLivePPGData();
        }
        MutableLiveData<PPGData> mutableLiveData = this.C;
        if (mutableLiveData != null) {
            mutableLiveData.observeForever(this);
        }
        final LiveRawPPGControlRequest liveRawPPGControlRequest = new LiveRawPPGControlRequest.Builder(true).build();
        LiveHeartRateControlRequest liveHeartRateControlRequest = new LiveHeartRateControlRequest.Builder(true).build();
        if (BleApiManager.getInstance(this).getDeviceType() == DeviceType.jstyle1810G) {
            BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
            Intrinsics.checkNotNullExpressionValue(liveHeartRateControlRequest, "liveHeartRateControlRequest");
            bleApi.setUserSettings(liveHeartRateControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.ActivitySp02FromRawPPG$startReadingRawPPG$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    ActivitySp02FromRawPPG activitySp02FromRawPPG = ActivitySp02FromRawPPG.this;
                    LiveRawPPGControlRequest liveRawPPGControlRequest2 = liveRawPPGControlRequest;
                    Intrinsics.checkNotNullExpressionValue(liveRawPPGControlRequest2, "liveRawPPGControlRequest");
                    activitySp02FromRawPPG.K(liveRawPPGControlRequest2);
                }
            });
            return;
        }
        if (BleApiManager.getInstance(this).getDeviceType() != DeviceType.v2 && BleApiManager.getInstance(this).getDeviceType() != DeviceType.v7) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
                return;
            }
        }
        Intrinsics.checkNotNullExpressionValue(liveRawPPGControlRequest, "liveRawPPGControlRequest");
        K(liveRawPPGControlRequest);
    }

    public final void X() {
        PayUtils.INSTANCE.scheduleReminderJob(this);
    }

    public final void Y() {
        stopReadingRawPPG();
        Z();
        finish();
    }

    public final void Z() {
        try {
            this.y.cancel();
        } catch (Exception unused) {
        }
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
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessage() {
        return this.A;
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessageTwoBtns getBottomSheetDialogImageTitleMessage2() {
        return this.z;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetDialogOneButtonTitleMessage() {
        return this.B;
    }

    @NotNull
    public final CountDownTimer getCountDownTimer() {
        return this.y;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getExitConfirmationDialog() {
        return this.q;
    }

    @NotNull
    public final int[] getMContents() {
        return this.s;
    }

    @NotNull
    public final int[] getMImages() {
        return this.t;
    }

    @NotNull
    public final StringBuilder getPpgData() {
        return this.p;
    }

    @Nullable
    public final MutableLiveData<PPGData> getRegisterLivePPGData() {
        return this.C;
    }

    public final boolean getShouldShowReminder() {
        return this.w;
    }

    public final String getTAG() {
        return this.r;
    }

    public final boolean isPPGReadingStarted() {
        return this.u;
    }

    public final boolean isPPGSuccess() {
        return this.v;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ((TextView) _$_findCachedViewById(R.id.close)).performClick();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_spo2_measuring);
        this.x = (ViewModelActivityDashboardNew) ViewModelProviders.of(this).get(ViewModelActivityDashboardNew.class);
        ((TextView) _$_findCachedViewById(R.id.close)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySp02FromRawPPG.D(ActivitySp02FromRawPPG.this, view);
            }
        });
        ((ImageButton) _$_findCachedViewById(R.id.back)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySp02FromRawPPG.E(ActivitySp02FromRawPPG.this, view);
            }
        });
        if (!this.u) {
            W();
        }
        C();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        stopReadingRawPPG();
        Z();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        BleApiManager.getInstance(this).getBleApi().registerConnectionStatus().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.j
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivitySp02FromRawPPG.G(ActivitySp02FromRawPPG.this, (ConnectionStatus) obj);
            }
        });
    }

    public final void setBottomSheetDialogImageTitleMessage(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.A = bottomSheetDialogImageTitleMessage;
    }

    public final void setBottomSheetDialogImageTitleMessage2(@Nullable BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns) {
        this.z = bottomSheetDialogImageTitleMessageTwoBtns;
    }

    public final void setBottomSheetDialogOneButtonTitleMessage(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.B = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setExitConfirmationDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.q = bottomSheetDialogTwoButtons;
    }

    public final void setPPGReadingStarted(boolean z) {
        this.u = z;
    }

    public final void setPPGSuccess(boolean z) {
        this.v = z;
    }

    public final void setRegisterLivePPGData(@Nullable MutableLiveData<PPGData> mutableLiveData) {
        this.C = mutableLiveData;
    }

    public final void setShouldShowReminder(boolean z) {
        this.w = z;
    }

    public final void stopReadingRawPPG() {
        this.u = false;
        MutableLiveData<PPGData> mutableLiveData = this.C;
        if (mutableLiveData != null) {
            mutableLiveData.removeObservers(this);
        }
        LiveHeartRateControlRequest liveHeartRateControlRequest = new LiveHeartRateControlRequest.Builder(false).build();
        if (BleApiManager.getInstance(this).getDeviceType() == DeviceType.jstyle1810G) {
            BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
            Intrinsics.checkNotNullExpressionValue(liveHeartRateControlRequest, "liveHeartRateControlRequest");
            bleApi.setUserSettings(liveHeartRateControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.ActivitySp02FromRawPPG$stopReadingRawPPG$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    ActivitySp02FromRawPPG.this.M();
                }
            });
            return;
        }
        if (BleApiManager.getInstance(this).getDeviceType() != DeviceType.v2 && BleApiManager.getInstance(this).getDeviceType() != DeviceType.v7) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
                return;
            }
        }
        M();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable PPGData pPGData) {
        if (pPGData != null) {
            StringBuilder sb = this.p;
            String arrayList = pPGData.getData().toString();
            Intrinsics.checkNotNullExpressionValue(arrayList, "t.data.toString()");
            sb.append(m.replace$default(m.replace$default(m.replace$default(arrayList, "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null));
            sb.append(Constants.SEPARATOR_COMMA);
        }
    }
}
