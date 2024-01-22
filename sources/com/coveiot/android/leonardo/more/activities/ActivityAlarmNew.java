package com.coveiot.android.leonardo.more.activities;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.model.AlarmDataHolder;
import com.coveiot.android.leonardo.more.activities.ActivityAlarmNew;
import com.coveiot.android.leonardo.more.adapters.AlarmListAdapterNew;
import com.coveiot.android.leonardo.more.listeners.AlarmListener;
import com.coveiot.android.leonardo.more.models.AlarmUpdated;
import com.coveiot.android.leonardo.more.viewmodel.AlarmViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.sdk.ble.api.model.AlarmInfo;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.jstyle.blesdk1860.constant.BleConst;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAlarmNew extends BaseActivity implements AlarmListener, DialogListener {
    public AlarmViewModel alarmViewModel;
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitleError;
    public DialogListener dialogListener;
    public AlarmListAdapterNew mAlarmListAdapter;
    public Dialog malarmDialog;
    public boolean q;
    public int r;
    public int s;
    public ImageView t;
    public List<AlarmInfo> u;
    public List<AlarmInfo> v;
    public boolean w;
    @Nullable
    public BottomSheetDialogTwoButtons x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int p = 110;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityAlarmNew this$0, View view) {
            Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            bottomSheetDialogOneButtonTitleMessage.dismiss();
            this$0.finish();
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            if (!AppUtils.isNetConnected(ActivityAlarmNew.this)) {
                ActivityAlarmNew.this.showNoInternetMessage();
            } else if (BleApiManager.getInstance(ActivityAlarmNew.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                ArrayList arrayList = new ArrayList();
                List list = ActivityAlarmNew.this.u;
                if (list == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                    list = null;
                }
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    List list2 = ActivityAlarmNew.this.v;
                    if (list2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("alarmInfoPrefrence");
                        list2 = null;
                    }
                    if (i >= list2.size()) {
                        List list3 = ActivityAlarmNew.this.u;
                        if (list3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                            list3 = null;
                        }
                        arrayList.add(list3.get(i));
                    } else if (BleApiManager.getInstance(ActivityAlarmNew.this).getBleApi().getDeviceSupportedFeatures().isMultipleAlarmsSupportedAtATime()) {
                        List list4 = ActivityAlarmNew.this.u;
                        if (list4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                            list4 = null;
                        }
                        arrayList.add(list4.get(i));
                    } else {
                        List list5 = ActivityAlarmNew.this.u;
                        if (list5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                            list5 = null;
                        }
                        String eventName = ((AlarmInfo) list5.get(i)).getEventName();
                        List list6 = ActivityAlarmNew.this.v;
                        if (list6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("alarmInfoPrefrence");
                            list6 = null;
                        }
                        if (eventName.equals(((AlarmInfo) list6.get(i)).getEventName())) {
                            List list7 = ActivityAlarmNew.this.u;
                            if (list7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                                list7 = null;
                            }
                            int hour = ((AlarmInfo) list7.get(i)).getHour();
                            List list8 = ActivityAlarmNew.this.v;
                            if (list8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("alarmInfoPrefrence");
                                list8 = null;
                            }
                            if (hour == ((AlarmInfo) list8.get(i)).getHour()) {
                                List list9 = ActivityAlarmNew.this.u;
                                if (list9 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                                    list9 = null;
                                }
                                int minute = ((AlarmInfo) list9.get(i)).getMinute();
                                List list10 = ActivityAlarmNew.this.v;
                                if (list10 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("alarmInfoPrefrence");
                                    list10 = null;
                                }
                                if (minute == ((AlarmInfo) list10.get(i)).getMinute()) {
                                    List list11 = ActivityAlarmNew.this.u;
                                    if (list11 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                                        list11 = null;
                                    }
                                    boolean isAlarmOn = ((AlarmInfo) list11.get(i)).isAlarmOn();
                                    List list12 = ActivityAlarmNew.this.v;
                                    if (list12 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("alarmInfoPrefrence");
                                        list12 = null;
                                    }
                                    if (isAlarmOn == ((AlarmInfo) list12.get(i)).isAlarmOn()) {
                                        List list13 = ActivityAlarmNew.this.u;
                                        if (list13 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                                            list13 = null;
                                        }
                                        boolean[] isDaySelected = ((AlarmInfo) list13.get(i)).getDaysSelected().getIsDaySelected();
                                        List list14 = ActivityAlarmNew.this.v;
                                        if (list14 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("alarmInfoPrefrence");
                                            list14 = null;
                                        }
                                        if (Arrays.equals(isDaySelected, ((AlarmInfo) list14.get(i)).getDaysSelected().getIsDaySelected())) {
                                        }
                                    }
                                }
                            }
                        }
                        List list15 = ActivityAlarmNew.this.u;
                        if (list15 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                            list15 = null;
                        }
                        arrayList.add(list15.get(i));
                    }
                }
                if (BleApiManager.getInstance(ActivityAlarmNew.this).getBleApi().getDeviceSupportedFeatures().isMultipleAlarmsSupportedAtATime()) {
                    ActivityAlarmNew.this.getAlarmViewModel().sendMultipleAlarmToBle(arrayList);
                } else {
                    ActivityAlarmNew.this.getAlarmViewModel().sendToBle(arrayList);
                }
            } else {
                ActivityAlarmNew activityAlarmNew = ActivityAlarmNew.this;
                String string = activityAlarmNew.getString(R.string.band_not_connected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                String string2 = ActivityAlarmNew.this.getString(R.string.please_connect_the_device);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activityAlarmNew, string, string2);
                String string3 = ActivityAlarmNew.this.getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                final ActivityAlarmNew activityAlarmNew2 = ActivityAlarmNew.this;
                bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.t
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAlarmNew.a.invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage.this, activityAlarmNew2, view);
                    }
                });
                bottomSheetDialogOneButtonTitleMessage.show();
            }
        }
    }

    public static final void A(ActivityAlarmNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.x;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
    }

    public static final void C(ActivityAlarmNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitle().dismiss();
    }

    public static final void D(ActivityAlarmNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitleError().dismiss();
        this$0.finish();
    }

    public static final void E(ActivityAlarmNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.backPress();
    }

    public static final void G(final ActivityAlarmNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int maxAlarmSupportedOnBand = BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().getMaxAlarmSupportedOnBand();
        if (this$0.getMAlarmListAdapter().getMAlarmInfo().size() >= maxAlarmSupportedOnBand) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = this$0.getString(R.string.maximum_no_of_alarm_vibration);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.maximum_no_of_alarm_vibration)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{String.valueOf(maxAlarmSupportedOnBand)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            Toast.makeText(this$0, format, 0).show();
            return;
        }
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this$0, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.activities.k
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                ActivityAlarmNew.H(ActivityAlarmNew.this, timePicker, i, i2);
            }
        }, calendar.get(11), calendar.get(12), DateFormat.is24HourFormat(this$0)).show();
    }

    public static final void H(ActivityAlarmNew this$0, TimePicker timePicker, int i, int i2) {
        int parseInt;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AlarmInfo alarmInfo = new AlarmInfo();
        alarmInfo.setAlarmOn(true);
        alarmInfo.setMinute(i2);
        alarmInfo.setHour(i);
        alarmInfo.setDaysSelected(new AlarmInfo.Days(false, false, false, false, false, false, false));
        if (this$0.getMAlarmListAdapter().getMAlarmInfo().size() == 0) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isIDODevice(this$0) && !companion.isTouchELXDevice(this$0) && !companion.isEastApexDevice(this$0)) {
                alarmInfo.setAlarmId(0);
            } else {
                alarmInfo.setAlarmId(1);
            }
        } else {
            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
            if (!companion2.isIDODevice(this$0) && !companion2.isTouchELXDevice(this$0) && !companion2.isEastApexDevice(this$0)) {
                alarmInfo.setAlarmId(this$0.getMAlarmListAdapter().getMAlarmInfo().size());
            } else {
                alarmInfo.setAlarmId(this$0.getMAlarmListAdapter().getMAlarmInfo().size() + 1);
            }
        }
        String replace$default = kotlin.text.m.replace$default(kotlin.text.m.replace$default(this$0.getAlarmViewModel().getSnoozeDuration(), "00", "", false, 4, (Object) null), ":", "", false, 4, (Object) null);
        int parseInt2 = Integer.parseInt(replace$default);
        if (1 <= parseInt2 && parseInt2 < 10) {
            parseInt = Integer.parseInt(kotlin.text.m.replace$default(replace$default, BleConst.GetDeviceTime, "", false, 4, (Object) null));
        } else {
            parseInt = Integer.parseInt(replace$default);
        }
        alarmInfo.setSnoozeDuration(parseInt);
        alarmInfo.setEventName("");
        alarmInfo.setSnoozeRepeatCount(Integer.parseInt(this$0.getAlarmViewModel().getSnoozeRepeatCount()));
        this$0.getMAlarmListAdapter().addAlarmInfo(new AlarmDataHolder(alarmInfo, false));
        ((ConstraintLayout) this$0._$_findCachedViewById(R.id.empty_layout)).setVisibility(8);
        int i3 = R.id.alarmList;
        ((RecyclerView) this$0._$_findCachedViewById(i3)).setVisibility(0);
        ((RecyclerView) this$0._$_findCachedViewById(i3)).scrollToPosition(this$0.getMAlarmListAdapter().getMAlarmInfo().size() - 1);
    }

    public static final void I(ActivityAlarmNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageView imageView = this$0.t;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("plusBtn");
            imageView = null;
        }
        imageView.performClick();
    }

    public static final void J(ActivityAlarmNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.q = true;
        AppNavigator.Companion.navigateToActivtyAlarmSettings(this$0, this$0.p, this$0.getAlarmViewModel().getSnoozeDuration(), this$0.getAlarmViewModel().getSnoozeRepeatCount());
    }

    public static final void z(ActivityAlarmNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.x;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
    }

    public final boolean B() {
        List<AlarmInfo> ConvertFromVibrateToAlarminfo = getAlarmViewModel().ConvertFromVibrateToAlarminfo();
        String[] alarmTypes = BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().getAlarmTypes();
        List<AlarmInfo> list = this.u;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
            list = null;
        }
        if (list.size() > 0) {
            List<AlarmInfo> list2 = this.u;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                list2 = null;
            }
            this.r = list2.get(0).getSnoozeDuration();
            List<AlarmInfo> list3 = this.u;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                list3 = null;
            }
            this.s = list3.get(0).getSnoozeRepeatCount();
        }
        int size = ConvertFromVibrateToAlarminfo.size();
        List<AlarmInfo> list4 = this.u;
        if (list4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
            list4 = null;
        }
        if (size < list4.size()) {
            return true;
        }
        for (AlarmInfo alarmInfo : ConvertFromVibrateToAlarminfo) {
            List<AlarmInfo> list5 = this.u;
            if (list5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                list5 = null;
            }
            for (AlarmInfo alarmInfo2 : list5) {
                if (alarmInfo2.getAlarmId() == alarmInfo.getAlarmId()) {
                    if (alarmInfo2.isAlarmOn() == alarmInfo.isAlarmOn() && alarmInfo2.getHour() == alarmInfo.getHour() && this.r == Integer.parseInt(kotlin.text.m.replace$default(kotlin.text.m.replace$default(getAlarmViewModel().getSnoozeDuration(), "00", "", false, 4, (Object) null), ":", "", false, 4, (Object) null)) && this.s == Integer.parseInt(getAlarmViewModel().getSnoozeRepeatCount()) && alarmInfo2.getMinute() == alarmInfo.getMinute() && kotlin.text.m.equals(alarmInfo2.getEventName(), alarmInfo.getEventName(), true)) {
                        if (alarmTypes == null || alarmTypes.length <= 0 || alarmInfo2.getAlarmType() == alarmInfo.getAlarmType()) {
                            int length = alarmInfo.getDaysSelected().getIsDaySelected().length;
                            for (int i = 0; i < length; i++) {
                                if (alarmInfo.getDaysSelected().getIsDaySelected()[i] != alarmInfo2.getDaysSelected().getIsDaySelected()[i]) {
                                    return true;
                                }
                            }
                            continue;
                        } else {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f4, code lost:
        if (r1.get(r7.size() - 1).getSnoozeRepeatCount() <= 0) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0172, code lost:
        if (r1.get(r7.size() - 1).getSnoozeRepeatCount() == 0) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0174, code lost:
        r1 = getAlarmViewModel();
        r7 = r9.u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x017a, code lost:
        if (r7 != null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x017c, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0180, code lost:
        r8 = r9.u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0182, code lost:
        if (r8 != null) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0184, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0188, code lost:
        r1.setSnoozeDuration(java.lang.String.valueOf(r7.get(r8.size() - 1).getSnoozeDuration()));
        r1 = getAlarmViewModel();
        r7 = r9.u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01a4, code lost:
        if (r7 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01a6, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01aa, code lost:
        r8 = r9.u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01ac, code lost:
        if (r8 != null) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01ae, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01b2, code lost:
        r1.setSnoozeRepeatCount(java.lang.String.valueOf(r7.get(r8.size() - 1).getSnoozeRepeatCount()));
        r1 = r9.u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01ca, code lost:
        if (r1 != null) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01cc, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01d0, code lost:
        r7 = r9.u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01d2, code lost:
        if (r7 != null) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01d4, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01d8, code lost:
        r9.r = r1.get(r7.size() - 1).getSnoozeDuration();
        r1 = r9.u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01eb, code lost:
        if (r1 != null) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01ed, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01f1, code lost:
        r7 = r9.u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01f3, code lost:
        if (r7 != null) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01f5, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01f9, code lost:
        r9.s = r1.get(r7.size() - 1).getSnoozeRepeatCount();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void F() {
        /*
            Method dump skipped, instructions count: 709
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.activities.ActivityAlarmNew.F():void");
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

    public final void backPress() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2;
        if (B()) {
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.x;
            if (bottomSheetDialogTwoButtons3 != null) {
                Boolean valueOf = bottomSheetDialogTwoButtons3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons3.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue() && (bottomSheetDialogTwoButtons2 = this.x) != null) {
                    bottomSheetDialogTwoButtons2.dismiss();
                }
                this.x = null;
            }
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.x = bottomSheetDialogTwoButtons4;
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogTwoButtons4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.s
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAlarmNew.z(ActivityAlarmNew.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.x;
            if (bottomSheetDialogTwoButtons5 != null) {
                String string4 = getString(R.string.cancel);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
                bottomSheetDialogTwoButtons5.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.r
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAlarmNew.A(ActivityAlarmNew.this, view);
                    }
                });
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons6 = this.x;
            Boolean valueOf2 = bottomSheetDialogTwoButtons6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons6.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf2);
            if (valueOf2.booleanValue() || (bottomSheetDialogTwoButtons = this.x) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    public final int getALARM_REQUESTCODE() {
        return this.p;
    }

    @NotNull
    public final AlarmViewModel getAlarmViewModel() {
        AlarmViewModel alarmViewModel = this.alarmViewModel;
        if (alarmViewModel != null) {
            return alarmViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("alarmViewModel");
        return null;
    }

    public final boolean getBoolSaveVisible() {
        return this.w;
    }

    @NotNull
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitle() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.bottomSheetDialogOneButtonOneTitle;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            return bottomSheetDialogOneButtonOneTitle;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogOneButtonOneTitle");
        return null;
    }

    @NotNull
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitleError() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.bottomSheetDialogOneButtonOneTitleError;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            return bottomSheetDialogOneButtonOneTitle;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogOneButtonOneTitleError");
        return null;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationDailog() {
        return this.x;
    }

    @NotNull
    public final DialogListener getDialogListener() {
        DialogListener dialogListener = this.dialogListener;
        if (dialogListener != null) {
            return dialogListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dialogListener");
        return null;
    }

    public final int getLastSavedRepaet() {
        return this.s;
    }

    public final int getLastSavedSnoozeDuration() {
        return this.r;
    }

    @Override // com.coveiot.android.leonardo.more.listeners.AlarmListener
    @NotNull
    public List<AlarmInfo> getLatestAlarmList() {
        List<AlarmInfo> list = this.u;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
            return null;
        }
        return list;
    }

    @NotNull
    public final AlarmListAdapterNew getMAlarmListAdapter() {
        AlarmListAdapterNew alarmListAdapterNew = this.mAlarmListAdapter;
        if (alarmListAdapterNew != null) {
            return alarmListAdapterNew;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAlarmListAdapter");
        return null;
    }

    @NotNull
    public final Dialog getMalarmDialog() {
        Dialog dialog = this.malarmDialog;
        if (dialog != null) {
            return dialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("malarmDialog");
        return null;
    }

    public final void initDialogs() {
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        setBottomSheetDialogOneButtonOneTitle(new BottomSheetDialogOneButtonOneTitle(this, string));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = getBottomSheetDialogOneButtonOneTitle();
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAlarmNew.C(ActivityAlarmNew.this, view);
            }
        });
        String string3 = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.setting_couldnot_save)");
        setBottomSheetDialogOneButtonOneTitleError(new BottomSheetDialogOneButtonOneTitle(this, string3));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitleError = getBottomSheetDialogOneButtonOneTitleError();
        String string4 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitleError.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAlarmNew.D(ActivityAlarmNew.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.vibration_alarm));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAlarmNew.E(ActivityAlarmNew.this, view);
            }
        });
        View findViewById = findViewById(R.id.share_iv);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<ImageView>(R.id.share_iv)");
        this.t = (ImageView) findViewById;
    }

    public final boolean isIntervalSettings() {
        return this.q;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.p && i2 == -1 && intent != null) {
            String snoozeDuration = getAlarmViewModel().getSnoozeDuration();
            AppConstants appConstants = AppConstants.SNOOZE_TIME_INTERVAL_KEY;
            if (!Intrinsics.areEqual(snoozeDuration, intent.getStringExtra(appConstants.getValue()))) {
                ((Button) _$_findCachedViewById(R.id.btn_save)).setEnabled(true);
                this.w = true;
                AlarmViewModel alarmViewModel = getAlarmViewModel();
                String stringExtra = intent.getStringExtra(appConstants.getValue());
                Intrinsics.checkNotNull(stringExtra);
                alarmViewModel.setSnoozeDuration(stringExtra);
            }
            String snoozeRepeatCount = getAlarmViewModel().getSnoozeRepeatCount();
            AppConstants appConstants2 = AppConstants.SNOOZE_REPEAT_COUNT_KEY;
            String stringExtra2 = intent.getStringExtra(appConstants2.getValue());
            Intrinsics.checkNotNull(stringExtra2);
            if (Intrinsics.areEqual(snoozeRepeatCount, stringExtra2)) {
                return;
            }
            ((Button) _$_findCachedViewById(R.id.btn_save)).setEnabled(true);
            this.w = true;
            if (kotlin.text.m.equals$default(intent.getStringExtra(appConstants2.getValue()), "DonotRepeat", false, 2, null)) {
                getAlarmViewModel().setSnoozeRepeatCount(AppConstants.ZERO.getValue());
                return;
            }
            AlarmViewModel alarmViewModel2 = getAlarmViewModel();
            String stringExtra3 = intent.getStringExtra(appConstants2.getValue());
            Intrinsics.checkNotNull(stringExtra3);
            alarmViewModel2.setSnoozeRepeatCount(stringExtra3);
        }
    }

    @Override // com.coveiot.android.leonardo.more.listeners.AlarmListener
    public void onAlarmAdded(@NotNull AlarmInfo info) {
        BleApi bleApi;
        DeviceSupportedFeatures deviceSupportedFeatures;
        Intrinsics.checkNotNullParameter(info, "info");
        ((ConstraintLayout) _$_findCachedViewById(R.id.empty_layout)).setVisibility(8);
        ((RecyclerView) _$_findCachedViewById(R.id.alarmList)).setVisibility(0);
        ((Button) _$_findCachedViewById(R.id.add_alarm_btn)).setText(getString(R.string.add_more_alarm));
        List<AlarmInfo> list = this.u;
        ImageView imageView = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
            list = null;
        }
        list.clear();
        for (AlarmDataHolder alarmDataHolder : getMAlarmListAdapter().getMAlarmInfo()) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isTouchELXDevice(this) && !companion.isEastApexDevice(this) && !companion.isBESDevice(this)) {
                if (AppUtils.isEmpty(alarmDataHolder.getAlarmInfo().getEventName())) {
                    String string = getString(R.string.please_enter_alarm_label);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_enter_alarm_label)");
                    ViewUtilsKt.toast(this, string);
                    ((RecyclerView) _$_findCachedViewById(R.id.alarmList)).scrollToPosition(getMAlarmListAdapter().getMAlarmInfo().indexOf(alarmDataHolder));
                    return;
                }
                List<AlarmInfo> list2 = this.u;
                if (list2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                    list2 = null;
                }
                list2.add(alarmDataHolder.getAlarmInfo());
            } else {
                List<AlarmInfo> list3 = this.u;
                if (list3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                    list3 = null;
                }
                list3.add(alarmDataHolder.getAlarmInfo());
            }
        }
        List<AlarmInfo> list4 = this.u;
        if (list4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
            list4 = null;
        }
        int size = list4.size();
        BleApiManager bleApiManager = BleApiManager.getInstance(this);
        Integer valueOf = (bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : Integer.valueOf(deviceSupportedFeatures.getMaxAlarmSupportedOnBand());
        Intrinsics.checkNotNull(valueOf);
        if (size >= valueOf.intValue()) {
            ImageView imageView2 = this.t;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("plusBtn");
            } else {
                imageView = imageView2;
            }
            imageView.setVisibility(8);
        } else {
            ImageView imageView3 = this.t;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("plusBtn");
            } else {
                imageView = imageView3;
            }
            imageView.setVisibility(0);
        }
        ((Button) _$_findCachedViewById(R.id.btn_save)).performClick();
    }

    @Subscribe
    public final void onAlarmDataUpdate(@NotNull AlarmUpdated alarmUpdated) {
        Intrinsics.checkNotNullParameter(alarmUpdated, "alarmUpdated");
        this.u = getAlarmViewModel().ConvertFromVibrateToAlarminfo();
        this.v = getAlarmViewModel().ConvertFromVibrateToAlarminfo();
        ArrayList arrayList = new ArrayList();
        List<AlarmInfo> list = this.u;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
            list = null;
        }
        for (AlarmInfo alarmInfo : list) {
            arrayList.add(new AlarmDataHolder(alarmInfo, true));
        }
        setMAlarmListAdapter(new AlarmListAdapterNew(this, arrayList, this));
        ((RecyclerView) _$_findCachedViewById(R.id.alarmList)).setAdapter(getMAlarmListAdapter());
    }

    @Override // com.coveiot.android.leonardo.more.listeners.AlarmListener
    public void onAlarmUpdated(@NotNull AlarmInfo alarmInfo) {
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(alarmInfo, "alarmInfo");
        List<AlarmInfo> list = this.u;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
            list = null;
        }
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (AlarmInfo alarmInfo2 : list) {
                if (alarmInfo2.getAlarmId() != alarmInfo.getAlarmId() && alarmInfo2.getHour() == alarmInfo.getHour() && alarmInfo2.getMinute() == alarmInfo.getMinute() && Arrays.equals(alarmInfo2.getDaysSelected().getIsDaySelected(), alarmInfo.getDaysSelected().getIsDaySelected())) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    z2 = true;
                    break;
                }
            }
        }
        z2 = false;
        if (z2) {
            Toast.makeText(this, getString(R.string.an_alarm_with_same_time_exists), 0).show();
            return;
        }
        List<AlarmInfo> list2 = this.u;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
            list2 = null;
        }
        list2.clear();
        int size = getMAlarmListAdapter().getMAlarmInfo().size();
        for (int i = 0; i < size; i++) {
            AlarmDataHolder alarmDataHolder = getMAlarmListAdapter().getMAlarmInfo().get(i);
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isTouchELXDevice(this) && !companion.isEastApexDevice(this) && !companion.isBESDevice(this)) {
                if (AppUtils.isEmpty(alarmDataHolder.getAlarmInfo().getEventName())) {
                    return;
                }
                getMAlarmListAdapter().updateShouldExpandAlarm(true, i);
                List<AlarmInfo> list3 = this.u;
                if (list3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                    list3 = null;
                }
                list3.add(alarmDataHolder.getAlarmInfo());
                alarmDataHolder.isAlreadySaved();
            } else {
                List<AlarmInfo> list4 = this.u;
                if (list4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
                    list4 = null;
                }
                list4.add(alarmDataHolder.getAlarmInfo());
                getMAlarmListAdapter().updateShouldExpandAlarm(true, i);
            }
        }
        ((Button) _$_findCachedViewById(R.id.btn_save)).performClick();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        backPress();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_vibration_alarm_new);
        initToolbar();
        initDialogs();
        setAlarmViewModel((AlarmViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(AlarmViewModel.class));
        getAlarmViewModel().setDialogListener(this);
        getAlarmViewModel().setAlarmListner(this);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isIDODevice(this) && !companion.isTouchELXDevice(this) && !companion.isMatrixDevice(this) && !companion.isEastApexDevice(this) && !companion.isSmaDevice(this) && !companion.isBESDevice(this)) {
            F();
        } else {
            showProgress();
            SettingsSyncHelper.Companion.getInstance(this).readAlarmInfoFromBand(new SettingsSyncHelper.SettingsSyncListner() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAlarmNew$onCreate$1
                @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                public void onProgressUpdate(int i) {
                }

                @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                public void onSettingSyncError() {
                    ActivityAlarmNew.this.dismissProgress();
                    ActivityAlarmNew.this.F();
                }

                @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                public void onSettingsSyncCompleted() {
                    ActivityAlarmNew.this.dismissProgress();
                    ActivityAlarmNew.this.F();
                }
            });
        }
        Button btn_save = (Button) _$_findCachedViewById(R.id.btn_save);
        Intrinsics.checkNotNullExpressionValue(btn_save, "btn_save");
        ViewUtilsKt.setSafeOnClickListener(btn_save, new a());
        ImageView imageView = this.t;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("plusBtn");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAlarmNew.G(ActivityAlarmNew.this, view);
            }
        });
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isSnoozeSettingsSupportedInAlarm()) {
            ((TextView) _$_findCachedViewById(R.id.settings_tv)).setVisibility(0);
        } else {
            ((TextView) _$_findCachedViewById(R.id.settings_tv)).setVisibility(8);
        }
        ((Button) _$_findCachedViewById(R.id.add_alarm_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAlarmNew.I(ActivityAlarmNew.this, view);
            }
        });
        int i = R.id.settings_tv;
        ((TextView) _$_findCachedViewById(i)).setPaintFlags(8);
        ((TextView) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAlarmNew.J(ActivityAlarmNew.this, view);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        try {
            CoveEventBusManager.getInstance().getEventBus().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            CoveEventBusManager.getInstance().getEventBus().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.q) {
            this.q = false;
            return;
        }
        this.u = getAlarmViewModel().ConvertFromVibrateToAlarminfo();
        ArrayList arrayList = new ArrayList();
        List<AlarmInfo> list = this.u;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alarmInfos");
            list = null;
        }
        for (AlarmInfo alarmInfo : list) {
            arrayList.add(new AlarmDataHolder(alarmInfo, true));
        }
        this.v = getAlarmViewModel().ConvertFromVibrateToAlarminfo();
        setMAlarmListAdapter(new AlarmListAdapterNew(this, arrayList, this));
        ((RecyclerView) _$_findCachedViewById(R.id.alarmList)).setAdapter(getMAlarmListAdapter());
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        BaseActivity.showProgressWithTitle$default(this, null, 1, null);
    }

    public final void setAlarmViewModel(@NotNull AlarmViewModel alarmViewModel) {
        Intrinsics.checkNotNullParameter(alarmViewModel, "<set-?>");
        this.alarmViewModel = alarmViewModel;
    }

    public final void setBoolSaveVisible(boolean z) {
        this.w = z;
    }

    public final void setBottomSheetDialogOneButtonOneTitle(@NotNull BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "<set-?>");
        this.bottomSheetDialogOneButtonOneTitle = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setBottomSheetDialogOneButtonOneTitleError(@NotNull BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "<set-?>");
        this.bottomSheetDialogOneButtonOneTitleError = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setConfirmationDailog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.x = bottomSheetDialogTwoButtons;
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setIntervalSettings(boolean z) {
        this.q = z;
    }

    public final void setLastSavedRepaet(int i) {
        this.s = i;
    }

    public final void setLastSavedSnoozeDuration(int i) {
        this.r = i;
    }

    public final void setMAlarmListAdapter(@NotNull AlarmListAdapterNew alarmListAdapterNew) {
        Intrinsics.checkNotNullParameter(alarmListAdapterNew, "<set-?>");
        this.mAlarmListAdapter = alarmListAdapterNew;
    }

    public final void setMalarmDialog(@NotNull Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "<set-?>");
        this.malarmDialog = dialog;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        onDismiss();
        if (getBottomSheetDialogOneButtonOneTitleError().isShowing()) {
            return;
        }
        getBottomSheetDialogOneButtonOneTitleError().show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        onDismiss();
        if (getBottomSheetDialogOneButtonOneTitle().isShowing()) {
            return;
        }
        getBottomSheetDialogOneButtonOneTitle().show();
    }

    @Override // com.coveiot.android.leonardo.more.listeners.AlarmListener
    public void visibleTextview() {
        if (B()) {
            ((Button) _$_findCachedViewById(R.id.btn_save)).setEnabled(true);
            this.w = true;
            return;
        }
        ((Button) _$_findCachedViewById(R.id.btn_save)).setEnabled(false);
        this.w = false;
    }
}
