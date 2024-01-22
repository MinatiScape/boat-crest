package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmListRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.listeners.AlarmListener;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.Snooze;
import com.coveiot.coveaccess.userappsetting.AlarmType;
import com.coveiot.coveaccess.userappsetting.SaveAlarmSettingReq;
import com.coveiot.coveaccess.userappsetting.SaveAlarmSettingRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.VibrationAlarmData;
import com.coveiot.sdk.ble.api.model.AlarmInfo;
import com.coveiot.utils.utility.AppUtils;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AlarmViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5171a;
    public AlarmListener alarmListner;
    @NotNull
    public final String b;
    @NotNull
    public String c;
    @NotNull
    public String d;
    public DialogListener dialogListener;
    public boolean e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;

    public AlarmViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5171a = context;
        this.b = "AlarmViewModel";
        this.c = "00:10:00";
        this.d = "1";
        this.g = 1;
        this.h = 2;
        this.i = 3;
        this.j = 4;
        this.k = 5;
        this.l = 6;
    }

    public static final void b(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    @NotNull
    public final List<AlarmInfo> ConvertFromVibrateToAlarminfo() {
        List<VibrationAlarmData> vibrationAlarmData = UserDataManager.getInstance(this.f5171a).getVibrationAlarmData();
        Intrinsics.checkNotNull(vibrationAlarmData, "null cannot be cast to non-null type kotlin.collections.MutableList<@[FlexibleNullability] com.coveiot.covepreferences.data.VibrationAlarmData?>");
        List<VibrationAlarmData> asMutableList = TypeIntrinsics.asMutableList(vibrationAlarmData);
        ArrayList arrayList = new ArrayList();
        String str = BleConst.GetDeviceMacAddress;
        String str2 = "1";
        boolean z = true;
        for (VibrationAlarmData vibrationAlarmData2 : asMutableList) {
            if (vibrationAlarmData2.getSnoozeDuration() > 0 && vibrationAlarmData2.getSnoozeRepeatCount() > 0 && z) {
                str = String.valueOf(vibrationAlarmData2.getSnoozeDuration());
                str2 = String.valueOf(vibrationAlarmData2.getSnoozeRepeatCount());
            } else if (vibrationAlarmData2.getSnoozeDuration() > 0 && vibrationAlarmData2.getSnoozeRepeatCount() == 0 && z) {
                str = String.valueOf(vibrationAlarmData2.getSnoozeDuration());
                str2 = String.valueOf(vibrationAlarmData2.getSnoozeRepeatCount());
            }
            z = false;
        }
        for (VibrationAlarmData vibrationAlarmData3 : asMutableList) {
            AlarmInfo alarmInfo = new AlarmInfo();
            alarmInfo.setEventName(vibrationAlarmData3.getEvent_name());
            alarmInfo.setAlarmType(vibrationAlarmData3.getAlarmType());
            String alarmId = vibrationAlarmData3.getAlarmId();
            Intrinsics.checkNotNullExpressionValue(alarmId, "mutlistarr.alarmId");
            alarmInfo.setAlarmId(Integer.parseInt(alarmId));
            alarmInfo.setHour(vibrationAlarmData3.getEvent_time_hour());
            alarmInfo.setMinute(vibrationAlarmData3.getEvent_time_minutes());
            alarmInfo.setDaysSelected(new AlarmInfo.Days(vibrationAlarmData3.isSunday(), vibrationAlarmData3.isMonday(), vibrationAlarmData3.isTuesday(), vibrationAlarmData3.isWednesday(), vibrationAlarmData3.isThrusday(), vibrationAlarmData3.isFriday(), vibrationAlarmData3.isSaturday()));
            alarmInfo.setAlarmOn(vibrationAlarmData3.getSwitch_on_off());
            alarmInfo.setSnoozeDuration(Integer.parseInt(str));
            alarmInfo.setSnoozeRepeatCount(Integer.parseInt(str2));
            arrayList.add(alarmInfo);
        }
        return arrayList;
    }

    public final boolean c(AlarmInfo alarmInfo) {
        boolean[] isDaySelected = alarmInfo.getDaysSelected().getIsDaySelected();
        Intrinsics.checkNotNullExpressionValue(isDaySelected, "alarmInfo.daysSelected.isDaySelected");
        for (boolean z : isDaySelected) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final void callSaveApi(@NotNull List<AlarmInfo> alarmInfos) {
        boolean z;
        int i;
        Intrinsics.checkNotNullParameter(alarmInfos, "alarmInfos");
        if (AppUtils.isNetConnected(this.f5171a)) {
            new SaveAlarmSettingReq.AlarmListBean();
            ArrayList arrayList = new ArrayList();
            Snooze snooze = new Snooze();
            int i2 = 1;
            if (alarmInfos.size() > 0) {
                int size = alarmInfos.size();
                z = false;
                i = 1;
                for (int i3 = 0; i3 < size; i3++) {
                    if (alarmInfos.get(i3).getAlarmId() <= 0) {
                        i = alarmInfos.get(i3).getAlarmId() == 0 ? 1 : Math.abs(alarmInfos.get(0).getAlarmId() - 1);
                        z = true;
                    }
                }
            } else {
                z = false;
                i = 1;
            }
            for (AlarmInfo alarmInfo : alarmInfos) {
                SaveAlarmSettingReq.AlarmListBean alarmListBean = new SaveAlarmSettingReq.AlarmListBean();
                if (z) {
                    alarmListBean.setAlarmId(String.valueOf(alarmInfo.getAlarmId() + i));
                } else {
                    alarmListBean.setAlarmId(String.valueOf(alarmInfo.getAlarmId()));
                }
                alarmListBean.setActive(alarmInfo.isAlarmOn());
                StringBuilder sb = new StringBuilder();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                Object[] objArr = new Object[i2];
                objArr[0] = Integer.valueOf(alarmInfo.getHour());
                String format = String.format(locale, "%02d", Arrays.copyOf(objArr, i2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                sb.append(format);
                sb.append(':');
                Object[] objArr2 = new Object[i2];
                objArr2[0] = Integer.valueOf(alarmInfo.getMinute());
                String format2 = String.format(locale, "%02d", Arrays.copyOf(objArr2, i2));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb.append(format2);
                sb.append(":00");
                alarmListBean.setTime(sb.toString());
                if (!AppUtils.isEmpty(alarmInfo.getEventName())) {
                    alarmListBean.setLabel(alarmInfo.getEventName());
                }
                AlarmInfo.Days daysSelected = alarmInfo.getDaysSelected();
                String value = AppConstants.EMPTY_STRING.getValue();
                if (daysSelected != null && daysSelected.getIsDaySelected() != null) {
                    int length = daysSelected.getIsDaySelected().length;
                    for (int i4 = 0; i4 < length; i4++) {
                        if (i4 == this.f) {
                            value = daysSelected.getIsDaySelected()[i4] ? value + this.f5171a.getResources().getString(R.string.S) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i4 == this.g) {
                            value = daysSelected.getIsDaySelected()[i4] ? value + this.f5171a.getResources().getString(R.string.M) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i4 == this.h) {
                            value = daysSelected.getIsDaySelected()[i4] ? value + this.f5171a.getResources().getString(R.string.T) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i4 == this.i) {
                            value = daysSelected.getIsDaySelected()[i4] ? value + this.f5171a.getResources().getString(R.string.W) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i4 == this.j) {
                            value = daysSelected.getIsDaySelected()[i4] ? value + this.f5171a.getResources().getString(R.string.T) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i4 == this.k) {
                            value = daysSelected.getIsDaySelected()[i4] ? value + this.f5171a.getResources().getString(R.string.F) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i4 == this.l) {
                            value = daysSelected.getIsDaySelected()[i4] ? value + this.f5171a.getResources().getString(R.string.S) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        }
                    }
                }
                if (TextUtils.isEmpty(value)) {
                    value = this.f5171a.getResources().getString(R.string.week_empty);
                    Intrinsics.checkNotNullExpressionValue(value, "context.resources.getString(R.string.week_empty)");
                    alarmListBean.setRepeat(false);
                }
                if (kotlin.text.m.equals(value, this.f5171a.getResources().getString(R.string.week_empty), true)) {
                    alarmListBean.setRepeat(false);
                } else {
                    alarmListBean.setRepeat(true);
                }
                alarmListBean.setWeek(value);
                if (alarmInfo.getAlarmType() == 4) {
                    alarmListBean.setType(AlarmType.FOOD.getAlarmType());
                } else if (alarmInfo.getAlarmType() == 3) {
                    alarmListBean.setType(AlarmType.DRINK.getAlarmType());
                } else if (alarmInfo.getAlarmType() == 2) {
                    alarmListBean.setType(AlarmType.MEDICINE.getAlarmType());
                }
                arrayList.add(alarmListBean);
                i2 = 1;
            }
            SaveAlarmSettingReq saveAlarmSettingReq = new SaveAlarmSettingReq();
            if (!kotlin.text.m.equals(this.d, "DonotRepeat", true)) {
                snooze.setMaxAllowed(Integer.valueOf(Integer.parseInt(this.d)));
            } else {
                snooze.setMaxAllowed(0);
            }
            snooze.setInterval(this.c);
            saveAlarmSettingReq.setSnooze(snooze);
            saveAlarmSettingReq.setAlarmListBeans(arrayList);
            CoveUserAppSettings.saveAlarmSettings(saveAlarmSettingReq, new CoveApiListener<SaveAlarmSettingRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.AlarmViewModel$callSaveApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    AlarmViewModel.this.getDialogListener().onDismiss();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveAlarmSettingRes saveAlarmSettingRes) {
                    AlarmViewModel.this.getDialogListener().onDismiss();
                    AlarmViewModel.this.getDialogListener().showSuccessDialog();
                }
            });
            return;
        }
        getDialogListener().onDismiss();
        Context context = this.f5171a;
        String string = context.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ease_check_your_internet)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(context, string);
        String string2 = this.f5171a.getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlarmViewModel.b(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final int getAlarmCount() {
        return this.m;
    }

    @NotNull
    public final AlarmListener getAlarmListner() {
        AlarmListener alarmListener = this.alarmListner;
        if (alarmListener != null) {
            return alarmListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("alarmListner");
        return null;
    }

    public final boolean getAnyChangesMade() {
        return this.e;
    }

    @NotNull
    public final Context getContext() {
        return this.f5171a;
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

    public final int getPosition0() {
        return this.f;
    }

    public final int getPosition1() {
        return this.g;
    }

    public final int getPosition2() {
        return this.h;
    }

    public final int getPosition3() {
        return this.i;
    }

    public final int getPosition4() {
        return this.j;
    }

    public final int getPosition5() {
        return this.k;
    }

    public final int getPosition6() {
        return this.l;
    }

    @NotNull
    public final String getSnoozeDuration() {
        return this.c;
    }

    @NotNull
    public final String getSnoozeRepeatCount() {
        return this.d;
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final void saveToPref(@NotNull List<AlarmInfo> alarmInfos) {
        int alarmId;
        boolean z;
        int parseInt;
        Intrinsics.checkNotNullParameter(alarmInfos, "alarmInfos");
        ArrayList arrayList = new ArrayList();
        if (alarmInfos.size() > 0) {
            if (alarmInfos.get(0).getAlarmId() == 0) {
                alarmId = 0;
                z = false;
            } else {
                alarmId = alarmInfos.get(0).getAlarmId();
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                z = (companion.isIDODevice(this.f5171a) || companion.isTouchELXDevice(this.f5171a) || companion.isEastApexDevice(this.f5171a)) ? false : true;
            }
            for (AlarmInfo alarmInfo : alarmInfos) {
                VibrationAlarmData vibrationAlarmData = VibrationAlarmData.getInstance();
                Intrinsics.checkNotNullExpressionValue(vibrationAlarmData, "getInstance()");
                vibrationAlarmData.setAlarmType(alarmInfo.getAlarmType());
                vibrationAlarmData.setEvent_name(alarmInfo.getEventName());
                vibrationAlarmData.setEvent_time_hour(alarmInfo.getHour());
                vibrationAlarmData.setEvent_time_minutes(alarmInfo.getMinute());
                vibrationAlarmData.setSunday(alarmInfo.getDaysSelected().getIsDaySelected()[0]);
                vibrationAlarmData.setMonday(alarmInfo.getDaysSelected().getIsDaySelected()[1]);
                vibrationAlarmData.setTuesday(alarmInfo.getDaysSelected().getIsDaySelected()[2]);
                vibrationAlarmData.setWednesday(alarmInfo.getDaysSelected().getIsDaySelected()[3]);
                vibrationAlarmData.setThrusday(alarmInfo.getDaysSelected().getIsDaySelected()[4]);
                vibrationAlarmData.setFriday(alarmInfo.getDaysSelected().getIsDaySelected()[5]);
                vibrationAlarmData.setSaturday(alarmInfo.getDaysSelected().getIsDaySelected()[6]);
                String replace$default = kotlin.text.m.replace$default(kotlin.text.m.replace$default(this.c, "00", "", false, 4, (Object) null), ":", "", false, 4, (Object) null);
                int parseInt2 = Integer.parseInt(replace$default);
                if (1 <= parseInt2 && parseInt2 < 10) {
                    parseInt = Integer.parseInt(kotlin.text.m.replace$default(replace$default, BleConst.GetDeviceTime, "", false, 4, (Object) null));
                } else {
                    parseInt = Integer.parseInt(replace$default);
                }
                vibrationAlarmData.setSnoozeDuration(parseInt);
                vibrationAlarmData.setSnoozeRepeatCount(Integer.parseInt(this.d));
                vibrationAlarmData.setSwitch_on_off(alarmInfo.isAlarmOn());
                AlarmInfo.Days daysSelected = alarmInfo.getDaysSelected();
                String value = AppConstants.EMPTY_STRING.getValue();
                if (daysSelected != null && daysSelected.getIsDaySelected() != null) {
                    int length = daysSelected.getIsDaySelected().length;
                    for (int i = 0; i < length; i++) {
                        if (i == this.f) {
                            value = daysSelected.getIsDaySelected()[i] ? value + this.f5171a.getResources().getString(R.string.S) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i == this.g) {
                            value = daysSelected.getIsDaySelected()[i] ? value + this.f5171a.getResources().getString(R.string.M) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i == this.h) {
                            value = daysSelected.getIsDaySelected()[i] ? value + this.f5171a.getResources().getString(R.string.T) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i == this.i) {
                            value = daysSelected.getIsDaySelected()[i] ? value + this.f5171a.getResources().getString(R.string.W) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i == this.j) {
                            value = daysSelected.getIsDaySelected()[i] ? value + this.f5171a.getResources().getString(R.string.T) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i == this.k) {
                            value = daysSelected.getIsDaySelected()[i] ? value + this.f5171a.getResources().getString(R.string.F) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        } else if (i == this.l) {
                            value = daysSelected.getIsDaySelected()[i] ? value + this.f5171a.getResources().getString(R.string.S) : value + AppConstants.EMPTY_HYPHEN.getValue();
                        }
                    }
                }
                vibrationAlarmData.setWeeks(value);
                if (z) {
                    vibrationAlarmData.setAlarmId(String.valueOf(alarmInfo.getAlarmId() - alarmId));
                } else {
                    vibrationAlarmData.setAlarmId(String.valueOf(alarmInfo.getAlarmId()));
                }
                arrayList.add(vibrationAlarmData);
            }
            UserDataManager.getInstance(this.f5171a).saveVibrationAlarmSettings(arrayList);
        }
    }

    public final void sendMultipleAlarmToBle(@NotNull List<AlarmInfo> alarmInfos) {
        int parseInt;
        Intrinsics.checkNotNullParameter(alarmInfos, "alarmInfos");
        ArrayList arrayList = new ArrayList();
        int size = alarmInfos.size();
        for (int i = 0; i < size; i++) {
            AlarmInfo alarmInfo = alarmInfos.get(i);
            String replace$default = kotlin.text.m.replace$default(kotlin.text.m.replace$default(this.c, "00", "", false, 4, (Object) null), ":", "", false, 4, (Object) null);
            int parseInt2 = Integer.parseInt(replace$default);
            if (1 <= parseInt2 && parseInt2 < 10) {
                parseInt = Integer.parseInt(kotlin.text.m.replace$default(replace$default, BleConst.GetDeviceTime, "", false, 4, (Object) null));
            } else {
                parseInt = Integer.parseInt(replace$default);
            }
            SetVibrationAlarmRequest setAlarmReq = new SetVibrationAlarmRequest.Builder().setAlarmId(alarmInfo.getAlarmId()).setHour(alarmInfo.getHour()).setMinute(alarmInfo.getMinute()).setEnabled(alarmInfo.isAlarmOn()).setAlarmType(alarmInfo.getAlarmType()).setEventName(alarmInfo.getEventName()).setRepeatEnabled(c(alarmInfo)).setSnoozeDuration(parseInt).setSnoozeRepeatTimes(Integer.parseInt(this.d)).setSundayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[0]).setMondayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[1]).setTuesdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[2]).setWednesdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[3]).setThursdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[4]).setFridayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[5]).setSaturdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[6]).build();
            getDialogListener().onShowProgressDialog();
            Intrinsics.checkNotNullExpressionValue(setAlarmReq, "setAlarmReq");
            arrayList.add(setAlarmReq);
        }
        SetVibrationAlarmListRequest setAlarmReqList = new SetVibrationAlarmListRequest.Builder().setSedentaryReminderList(arrayList).build();
        BleApi bleApi = BleApiManager.getInstance(this.f5171a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(setAlarmReqList, "setAlarmReqList");
        bleApi.setUserSettings(setAlarmReqList, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.AlarmViewModel$sendMultipleAlarmToBle$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                Toast.makeText(AlarmViewModel.this.getContext(), AlarmViewModel.this.getContext().getString(R.string.setting_couldnot_save), 0).show();
                AlarmViewModel.this.getDialogListener().onDismiss();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                AlarmViewModel alarmViewModel = AlarmViewModel.this;
                alarmViewModel.callSaveApi(alarmViewModel.getAlarmListner().getLatestAlarmList());
                AlarmViewModel alarmViewModel2 = AlarmViewModel.this;
                alarmViewModel2.saveToPref(alarmViewModel2.getAlarmListner().getLatestAlarmList());
            }
        });
    }

    public final void sendToBle(@NotNull final List<AlarmInfo> alarmInfos) {
        boolean z;
        Intrinsics.checkNotNullParameter(alarmInfos, "alarmInfos");
        if (this.m < alarmInfos.size()) {
            AlarmInfo alarmInfo = alarmInfos.get(this.m);
            List<AlarmInfo> ConvertFromVibrateToAlarminfo = ConvertFromVibrateToAlarminfo();
            int size = ConvertFromVibrateToAlarminfo.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    z = false;
                    break;
                } else if (ConvertFromVibrateToAlarminfo.get(i).getAlarmId() == alarmInfo.getAlarmId()) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            SetVibrationAlarmRequest setAlarmReq = new SetVibrationAlarmRequest.Builder().setAlarmId(alarmInfo.getAlarmId()).setHour(alarmInfo.getHour()).setMinute(alarmInfo.getMinute()).setEnabled(alarmInfo.isAlarmOn()).setEventName(alarmInfo.getEventName()).setAlarmType(alarmInfo.getAlarmType()).setRepeatEnabled(c(alarmInfo)).setSnoozeDuration(alarmInfo.getSnoozeDuration()).setSnoozeRepeatTimes(alarmInfo.getSnoozeRepeatCount()).setSundayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[0]).setMondayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[1]).setTuesdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[2]).setWednesdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[3]).setThursdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[4]).setFridayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[5]).setSaturdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[6]).setEditingAlarm(z).build();
            getDialogListener().onShowProgressDialog();
            BleApi bleApi = BleApiManager.getInstance(this.f5171a).getBleApi();
            Intrinsics.checkNotNullExpressionValue(setAlarmReq, "setAlarmReq");
            bleApi.setUserSettings(setAlarmReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.AlarmViewModel$sendToBle$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    AlarmViewModel.this.setAlarmCount(0);
                    AlarmViewModel.this.getDialogListener().showErrorDialog();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    AlarmViewModel alarmViewModel = AlarmViewModel.this;
                    alarmViewModel.setAlarmCount(alarmViewModel.getAlarmCount() + 1);
                    AlarmViewModel.this.setAnyChangesMade(true);
                    if (AlarmViewModel.this.getAlarmCount() < alarmInfos.size()) {
                        AlarmViewModel.this.sendToBle(alarmInfos);
                    } else if (AlarmViewModel.this.getAlarmCount() == alarmInfos.size()) {
                        AlarmViewModel.this.setAlarmCount(0);
                        AlarmViewModel alarmViewModel2 = AlarmViewModel.this;
                        alarmViewModel2.callSaveApi(alarmViewModel2.getAlarmListner().getLatestAlarmList());
                        AlarmViewModel alarmViewModel3 = AlarmViewModel.this;
                        alarmViewModel3.saveToPref(alarmViewModel3.getAlarmListner().getLatestAlarmList());
                    }
                }
            });
        } else if (this.e) {
            this.e = false;
            getDialogListener().onShowProgressDialog();
            callSaveApi(getAlarmListner().getLatestAlarmList());
            saveToPref(getAlarmListner().getLatestAlarmList());
        } else {
            Context context = this.f5171a;
            Toast.makeText(context, context.getString(R.string.no_changes_to_save), 0).show();
        }
    }

    public final void setAlarmCount(int i) {
        this.m = i;
    }

    public final void setAlarmListner(@NotNull AlarmListener alarmListener) {
        Intrinsics.checkNotNullParameter(alarmListener, "<set-?>");
        this.alarmListner = alarmListener;
    }

    public final void setAnyChangesMade(boolean z) {
        this.e = z;
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setPosition0(int i) {
        this.f = i;
    }

    public final void setPosition1(int i) {
        this.g = i;
    }

    public final void setPosition2(int i) {
        this.h = i;
    }

    public final void setPosition3(int i) {
        this.i = i;
    }

    public final void setPosition4(int i) {
        this.j = i;
    }

    public final void setPosition5(int i) {
        this.k = i;
    }

    public final void setPosition6(int i) {
        this.l = i;
    }

    public final void setSnoozeDuration(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.c = str;
    }

    public final void setSnoozeRepeatCount(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }
}
