package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.ReminderType;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SUserAppSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveSedentaryAlertSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveSedentaryAlertDeviceSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveSedentaryAlertDeviceSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.model.SedentaryAlertUserDeviceSettingsBean;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.SedentaryReminderData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SedentaryReminderViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5202a;
    @NotNull
    public final String b;
    public final int c;
    public int d;
    public DialogListener dialogListener;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;

    public SedentaryReminderViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5202a = context;
        this.b = "SedentaryReminderViewModel";
        this.c = 900000;
        this.e = 1;
        this.f = 2;
        this.g = 3;
        this.h = 4;
        this.i = 5;
        this.j = 6;
    }

    public static final void e(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    public final String b(int i) {
        return i != 60 ? i != 90 ? i != 120 ? i != 150 ? "01:00:00" : "02:30:00" : "02:00:00" : "01:30:00" : "01:00:00";
    }

    public final boolean c(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v16, types: [com.coveiot.covepreferences.data.SedentaryReminderData, T, java.lang.Object] */
    public final void callSaveAndBleApi(final int i, final boolean z, @NotNull String beginTime1, @NotNull String endTime1) {
        List emptyList;
        List emptyList2;
        List emptyList3;
        List emptyList4;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        Intrinsics.checkNotNullParameter(beginTime1, "beginTime1");
        Intrinsics.checkNotNullParameter(endTime1, "endTime1");
        String str = (String) StringsKt__StringsKt.split$default((CharSequence) beginTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        String str2 = (String) StringsKt__StringsKt.split$default((CharSequence) beginTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(1);
        List<String> split = new Regex(":").split(str, 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() == 0) {
                    z5 = true;
                    continue;
                } else {
                    z5 = false;
                    continue;
                }
                if (!z5) {
                    emptyList = CollectionsKt___CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        String str3 = ((String[]) emptyList.toArray(new String[0]))[0];
        List<String> split2 = new Regex(":").split(str, 0);
        if (!split2.isEmpty()) {
            ListIterator<String> listIterator2 = split2.listIterator(split2.size());
            while (listIterator2.hasPrevious()) {
                if (listIterator2.previous().length() == 0) {
                    z4 = true;
                    continue;
                } else {
                    z4 = false;
                    continue;
                }
                if (!z4) {
                    emptyList2 = CollectionsKt___CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList2 = CollectionsKt__CollectionsKt.emptyList();
        final String str4 = ((String[]) emptyList2.toArray(new String[0]))[1];
        String str5 = (String) StringsKt__StringsKt.split$default((CharSequence) endTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        String str6 = (String) StringsKt__StringsKt.split$default((CharSequence) endTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(1);
        List<String> split3 = new Regex(":").split(str5, 0);
        if (!split3.isEmpty()) {
            ListIterator<String> listIterator3 = split3.listIterator(split3.size());
            while (listIterator3.hasPrevious()) {
                if (listIterator3.previous().length() == 0) {
                    z3 = true;
                    continue;
                } else {
                    z3 = false;
                    continue;
                }
                if (!z3) {
                    emptyList3 = CollectionsKt___CollectionsKt.take(split3, listIterator3.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList3 = CollectionsKt__CollectionsKt.emptyList();
        String str7 = ((String[]) emptyList3.toArray(new String[0]))[0];
        List<String> split4 = new Regex(":").split(str5, 0);
        if (!split4.isEmpty()) {
            ListIterator<String> listIterator4 = split4.listIterator(split4.size());
            while (listIterator4.hasPrevious()) {
                if (listIterator4.previous().length() == 0) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (!z2) {
                    emptyList4 = CollectionsKt___CollectionsKt.take(split4, listIterator4.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList4 = CollectionsKt__CollectionsKt.emptyList();
        final String str8 = ((String[]) emptyList4.toArray(new String[0]))[1];
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = Integer.parseInt(str3);
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = Integer.parseInt(str7);
        if (kotlin.text.m.equals(str2, "am", true)) {
            int i2 = intRef.element;
            if (i2 == 12) {
                intRef.element = 0;
            } else {
                intRef.element = i2;
            }
        } else if (kotlin.text.m.equals(str2, "pm", true)) {
            int i3 = intRef.element;
            if (i3 == 12) {
                intRef.element = i3;
            } else {
                intRef.element = i3 + 12;
            }
        }
        if (kotlin.text.m.equals(str6, "am", true)) {
            int i4 = intRef2.element;
            if (i4 == 12) {
                intRef2.element = 0;
            } else {
                intRef2.element = i4;
            }
        } else if (kotlin.text.m.equals(str6, "pm", true)) {
            int i5 = intRef2.element;
            if (i5 == 12) {
                intRef2.element = i5;
            } else {
                intRef2.element = i5 + 12;
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, intRef.element);
        calendar.set(12, Integer.parseInt(str4));
        long timeInMillis = calendar.getTimeInMillis();
        calendar.set(11, intRef2.element);
        calendar.set(12, Integer.parseInt(str8));
        if (Math.abs(calendar.getTimeInMillis() - timeInMillis) < i * 60 * 1000) {
            Context context = this.f5202a;
            Toast.makeText(context, context.getString(R.string.sedentary_reminder_set_value), 0).show();
            return;
        }
        getDialogListener().onShowProgressDialog();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? sedentaryReminderData = SedentaryReminderData.getInstance();
        Intrinsics.checkNotNullExpressionValue(sedentaryReminderData, "getInstance()");
        objectRef.element = sedentaryReminderData;
        ((SedentaryReminderData) sedentaryReminderData).setBeggining_time_hour(intRef.element);
        ((SedentaryReminderData) objectRef.element).setBeggining_time_minutes(Integer.parseInt(str4));
        ((SedentaryReminderData) objectRef.element).setEnd_time_hour(intRef2.element);
        ((SedentaryReminderData) objectRef.element).setEnd_time_minutes(Integer.parseInt(str8));
        ((SedentaryReminderData) objectRef.element).setRemind_in(i);
        ((SedentaryReminderData) objectRef.element).setAlarm_on_off(z);
        ((SedentaryReminderData) objectRef.element).setAlarm_on_off(z);
        SetReminderRequest sedentaryReminderReq = new SetReminderRequest.Builder().setStartHour1(intRef.element).setEndHour1(intRef2.element).setStartMin1(Integer.parseInt(str4)).setEndMin1(Integer.parseInt(str8)).setReminderInterval(i).setEnabled(((SedentaryReminderData) objectRef.element).getAlarm_on_off()).setReminderType(ReminderType.SEDENTARY_REMINDER).build();
        BleApi bleApi = BleApiManager.getInstance(this.f5202a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(sedentaryReminderReq, "sedentaryReminderReq");
        bleApi.setUserSettings(sedentaryReminderReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.SedentaryReminderViewModel$callSaveAndBleApi$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SedentaryReminderViewModel.this.getDialogListener().onDismiss();
                SedentaryReminderViewModel.this.getDialogListener().showErrorDialog();
                LogHelper.d(SedentaryReminderViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(SedentaryReminderViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(SedentaryReminderViewModel.this.getContext()).saveSedentaryReminderSettings(objectRef.element);
                SedentaryReminderViewModel.this.d(intRef2.element, str8, intRef.element, str4, i, z);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v17, types: [com.coveiot.covepreferences.data.SedentaryReminderData, T, java.lang.Object] */
    public final void callSedentaryUserDeviceSettingsAndBleApi(final int i, boolean z, @NotNull String beginTime1, @NotNull String endTime1, @NotNull final boolean[] isDaySelected) {
        List emptyList;
        List emptyList2;
        List emptyList3;
        List emptyList4;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        Intrinsics.checkNotNullParameter(beginTime1, "beginTime1");
        Intrinsics.checkNotNullParameter(endTime1, "endTime1");
        Intrinsics.checkNotNullParameter(isDaySelected, "isDaySelected");
        String str = (String) StringsKt__StringsKt.split$default((CharSequence) beginTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        String str2 = (String) StringsKt__StringsKt.split$default((CharSequence) beginTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(1);
        List<String> split = new Regex(":").split(str, 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() == 0) {
                    z5 = true;
                    continue;
                } else {
                    z5 = false;
                    continue;
                }
                if (!z5) {
                    emptyList = CollectionsKt___CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        String str3 = ((String[]) emptyList.toArray(new String[0]))[0];
        List<String> split2 = new Regex(":").split(str, 0);
        if (!split2.isEmpty()) {
            ListIterator<String> listIterator2 = split2.listIterator(split2.size());
            while (listIterator2.hasPrevious()) {
                if (listIterator2.previous().length() == 0) {
                    z4 = true;
                    continue;
                } else {
                    z4 = false;
                    continue;
                }
                if (!z4) {
                    emptyList2 = CollectionsKt___CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList2 = CollectionsKt__CollectionsKt.emptyList();
        final String str4 = ((String[]) emptyList2.toArray(new String[0]))[1];
        String str5 = (String) StringsKt__StringsKt.split$default((CharSequence) endTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0);
        String str6 = (String) StringsKt__StringsKt.split$default((CharSequence) endTime1, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(1);
        List<String> split3 = new Regex(":").split(str5, 0);
        if (!split3.isEmpty()) {
            ListIterator<String> listIterator3 = split3.listIterator(split3.size());
            while (listIterator3.hasPrevious()) {
                if (listIterator3.previous().length() == 0) {
                    z3 = true;
                    continue;
                } else {
                    z3 = false;
                    continue;
                }
                if (!z3) {
                    emptyList3 = CollectionsKt___CollectionsKt.take(split3, listIterator3.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList3 = CollectionsKt__CollectionsKt.emptyList();
        String str7 = ((String[]) emptyList3.toArray(new String[0]))[0];
        List<String> split4 = new Regex(":").split(str5, 0);
        if (!split4.isEmpty()) {
            ListIterator<String> listIterator4 = split4.listIterator(split4.size());
            while (listIterator4.hasPrevious()) {
                if (listIterator4.previous().length() == 0) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (!z2) {
                    emptyList4 = CollectionsKt___CollectionsKt.take(split4, listIterator4.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList4 = CollectionsKt__CollectionsKt.emptyList();
        final String str8 = ((String[]) emptyList4.toArray(new String[0]))[1];
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = Integer.parseInt(str3);
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = Integer.parseInt(str7);
        if (kotlin.text.m.equals(str2, "am", true)) {
            int i2 = intRef.element;
            if (i2 == 12) {
                intRef.element = 0;
            } else {
                intRef.element = i2;
            }
        } else if (kotlin.text.m.equals(str2, "pm", true)) {
            int i3 = intRef.element;
            if (i3 == 12) {
                intRef.element = i3;
            } else {
                intRef.element = i3 + 12;
            }
        }
        if (kotlin.text.m.equals(str6, "am", true)) {
            int i4 = intRef2.element;
            if (i4 == 12) {
                intRef2.element = 0;
            } else {
                intRef2.element = i4;
            }
        } else if (kotlin.text.m.equals(str6, "pm", true)) {
            int i5 = intRef2.element;
            if (i5 == 12) {
                intRef2.element = i5;
            } else {
                intRef2.element = i5 + 12;
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, intRef.element);
        calendar.set(12, Integer.parseInt(str4));
        long timeInMillis = calendar.getTimeInMillis();
        calendar.set(11, intRef2.element);
        calendar.set(12, Integer.parseInt(str8));
        if (Math.abs(calendar.getTimeInMillis() - timeInMillis) < i) {
            Context context = this.f5202a;
            Toast.makeText(context, context.getString(R.string.sedentary_reminder_set_value), 0).show();
            return;
        }
        getDialogListener().onShowProgressDialog();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? sedentaryReminderData = SedentaryReminderData.getInstance();
        Intrinsics.checkNotNullExpressionValue(sedentaryReminderData, "getInstance()");
        objectRef.element = sedentaryReminderData;
        ((SedentaryReminderData) sedentaryReminderData).setBeggining_time_hour(intRef.element);
        ((SedentaryReminderData) objectRef.element).setBeggining_time_minutes(Integer.parseInt(str4));
        ((SedentaryReminderData) objectRef.element).setEnd_time_hour(intRef2.element);
        ((SedentaryReminderData) objectRef.element).setEnd_time_minutes(Integer.parseInt(str8));
        ((SedentaryReminderData) objectRef.element).setRemind_in(i);
        ((SedentaryReminderData) objectRef.element).setAlarm_on_off(z);
        ((SedentaryReminderData) objectRef.element).setSunday(isDaySelected[0]);
        ((SedentaryReminderData) objectRef.element).setMonday(isDaySelected[1]);
        ((SedentaryReminderData) objectRef.element).setTuesday(isDaySelected[2]);
        ((SedentaryReminderData) objectRef.element).setWednesday(isDaySelected[3]);
        ((SedentaryReminderData) objectRef.element).setThrusday(isDaySelected[4]);
        ((SedentaryReminderData) objectRef.element).setFriday(isDaySelected[5]);
        ((SedentaryReminderData) objectRef.element).setSaturday(isDaySelected[6]);
        String value = AppConstants.EMPTY_STRING.getValue();
        int length = isDaySelected.length;
        for (int i6 = 0; i6 < length; i6++) {
            if (i6 == this.d) {
                value = isDaySelected[i6] ? value + this.f5202a.getResources().getString(R.string.S) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i6 == this.e) {
                value = isDaySelected[i6] ? value + this.f5202a.getResources().getString(R.string.M) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i6 == this.f) {
                value = isDaySelected[i6] ? value + this.f5202a.getResources().getString(R.string.T) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i6 == this.g) {
                value = isDaySelected[i6] ? value + this.f5202a.getResources().getString(R.string.W) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i6 == this.h) {
                value = isDaySelected[i6] ? value + this.f5202a.getResources().getString(R.string.T) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i6 == this.i) {
                value = isDaySelected[i6] ? value + this.f5202a.getResources().getString(R.string.F) : value + AppConstants.EMPTY_HYPHEN.getValue();
            } else if (i6 == this.j) {
                value = isDaySelected[i6] ? value + this.f5202a.getResources().getString(R.string.S) : value + AppConstants.EMPTY_HYPHEN.getValue();
            }
        }
        ((SedentaryReminderData) objectRef.element).setWeeks(value);
        SetReminderRequest sedentaryReminderReq = new SetReminderRequest.Builder().setStartHour1(intRef.element).setEndHour1(intRef2.element).setStartMin1(Integer.parseInt(str4)).setEndMin1(Integer.parseInt(str8)).setReminderInterval(i).setEnabled(((SedentaryReminderData) objectRef.element).getAlarm_on_off()).setRepeatEnabled(c(isDaySelected)).setSundayEnabled(isDaySelected[0]).setMondayEnabled(isDaySelected[1]).setTuesdayEnabled(isDaySelected[2]).setWednesdayEnabled(isDaySelected[3]).setThursdayEnabled(isDaySelected[4]).setFridayEnabled(isDaySelected[5]).setSaturdayEnabled(isDaySelected[6]).setLeastStep(PayUtils.INSTANCE.getLeastStepForSedentary(this.f5202a)).setReminderType(ReminderType.SEDENTARY_REMINDER).build();
        BleApi bleApi = BleApiManager.getInstance(this.f5202a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(sedentaryReminderReq, "sedentaryReminderReq");
        bleApi.setUserSettings(sedentaryReminderReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.SedentaryReminderViewModel$callSedentaryUserDeviceSettingsAndBleApi$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SedentaryReminderViewModel.this.getDialogListener().onDismiss();
                SedentaryReminderViewModel.this.getDialogListener().showErrorDialog();
                LogHelper.d(SedentaryReminderViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                boolean c;
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(SedentaryReminderViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(SedentaryReminderViewModel.this.getContext()).saveSedentaryReminderSettings(objectRef.element);
                SedentaryReminderViewModel sedentaryReminderViewModel = SedentaryReminderViewModel.this;
                int i7 = intRef2.element;
                String str9 = str8;
                int i8 = intRef.element;
                String str10 = str4;
                int i9 = i;
                c = sedentaryReminderViewModel.c(isDaySelected);
                sedentaryReminderViewModel.saveSedentaryUserDeviceSettingsToServer(i7, str9, i8, str10, i9, c, objectRef.element);
            }
        });
    }

    public final void d(int i, String str, int i2, String str2, int i3, boolean z) {
        if (AppUtils.isNetConnected(this.f5202a)) {
            SUserAppSettingsReq.SedentaryAlertBean.SiestaBean siestaBean = new SUserAppSettingsReq.SedentaryAlertBean.SiestaBean();
            siestaBean.setActive(false);
            SaveSedentaryAlertSettingsReq saveSedentaryAlertSettingsReq = new SaveSedentaryAlertSettingsReq();
            if (i < 10) {
                saveSedentaryAlertSettingsReq.setEndTime('0' + i + ':' + str + ":00");
            } else {
                saveSedentaryAlertSettingsReq.setEndTime(i + ':' + str + ":00");
            }
            if (i2 < 10) {
                saveSedentaryAlertSettingsReq.setStartTime('0' + i2 + ':' + str2 + ":00");
            } else {
                saveSedentaryAlertSettingsReq.setStartTime(i2 + ':' + str2 + ":00");
            }
            saveSedentaryAlertSettingsReq.setInterval(b(i3));
            saveSedentaryAlertSettingsReq.setActive(z);
            saveSedentaryAlertSettingsReq.setSiesta(siestaBean);
            CoveUserAppSettings.saveSedentaryAlertSettings(saveSedentaryAlertSettingsReq, new SedentaryReminderViewModel$uploadSedentaryReminderToServer$1(this, z));
            return;
        }
        getDialogListener().onDismiss();
        Context context = this.f5202a;
        String string = context.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ease_check_your_internet)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(context, string);
        String string2 = this.f5202a.getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SedentaryReminderViewModel.e(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @NotNull
    public final Context getContext() {
        return this.f5202a;
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

    public final int getFIFTEEN_MIN() {
        return this.c;
    }

    public final int getPosition0() {
        return this.d;
    }

    public final int getPosition1() {
        return this.e;
    }

    public final int getPosition2() {
        return this.f;
    }

    public final int getPosition3() {
        return this.g;
    }

    public final int getPosition4() {
        return this.h;
    }

    public final int getPosition5() {
        return this.i;
    }

    public final int getPosition6() {
        return this.j;
    }

    @NotNull
    public final SedentaryReminderData getSedentaryReminderDataFromPref() {
        try {
            SedentaryReminderData prefSendentaryReminderData = UserDataManager.getInstance(this.f5202a).getSedentaryReminderData();
            if (prefSendentaryReminderData == null) {
                SedentaryReminderData sedentaryReminderData = SedentaryReminderData.getInstance();
                Intrinsics.checkNotNullExpressionValue(sedentaryReminderData, "getInstance()");
                sedentaryReminderData.setRemind_in(60);
                sedentaryReminderData.setBeggining_time_hour(9);
                sedentaryReminderData.setBeggining_time_minutes(0);
                sedentaryReminderData.setEnd_time_minutes(0);
                sedentaryReminderData.setEnd_time_hour(18);
            }
            Intrinsics.checkNotNullExpressionValue(prefSendentaryReminderData, "prefSendentaryReminderData");
            return prefSendentaryReminderData;
        } catch (Exception unused) {
            SedentaryReminderData sedentaryReminderData2 = SedentaryReminderData.getInstance();
            Intrinsics.checkNotNullExpressionValue(sedentaryReminderData2, "getInstance()");
            sedentaryReminderData2.setRemind_in(60);
            sedentaryReminderData2.setBeggining_time_hour(9);
            sedentaryReminderData2.setBeggining_time_minutes(0);
            sedentaryReminderData2.setEnd_time_minutes(0);
            sedentaryReminderData2.setEnd_time_hour(18);
            return sedentaryReminderData2;
        }
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final void saveSedentaryUserDeviceSettingsToServer(int i, @NotNull String endMin, int i2, @NotNull String beginMin, int i3, boolean z, @NotNull SedentaryReminderData sedentaryReminderData) {
        Intrinsics.checkNotNullParameter(endMin, "endMin");
        Intrinsics.checkNotNullParameter(beginMin, "beginMin");
        Intrinsics.checkNotNullParameter(sedentaryReminderData, "sedentaryReminderData");
        SaveSedentaryAlertDeviceSettingsReq saveSedentaryAlertDeviceSettingsReq = new SaveSedentaryAlertDeviceSettingsReq();
        SedentaryAlertUserDeviceSettingsBean sedentaryAlertUserDeviceSettingsBean = new SedentaryAlertUserDeviceSettingsBean();
        sedentaryAlertUserDeviceSettingsBean.setRepeat(z);
        sedentaryAlertUserDeviceSettingsBean.setActive(sedentaryReminderData.getAlarm_on_off());
        if (i < 10) {
            sedentaryAlertUserDeviceSettingsBean.setEndTime('0' + i + ':' + endMin + ":00");
        } else {
            sedentaryAlertUserDeviceSettingsBean.setEndTime(i + ':' + endMin + ":00");
        }
        if (i2 < 10) {
            sedentaryAlertUserDeviceSettingsBean.setStartTime('0' + i2 + ':' + beginMin + ":00");
        } else {
            sedentaryAlertUserDeviceSettingsBean.setStartTime(i2 + ':' + beginMin + ":00");
        }
        sedentaryAlertUserDeviceSettingsBean.setInterval(b(i3));
        sedentaryAlertUserDeviceSettingsBean.setWeekDays(sedentaryReminderData.getWeeks());
        saveSedentaryAlertDeviceSettingsReq.setSedentaryAlertBean(sedentaryAlertUserDeviceSettingsBean);
        CoveUserDeviceSettings.saveSedentarySettings(saveSedentaryAlertDeviceSettingsReq, new CoveApiListener<SaveSedentaryAlertDeviceSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.SedentaryReminderViewModel$saveSedentaryUserDeviceSettingsToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                SedentaryReminderViewModel.this.getDialogListener().onDismiss();
                SedentaryReminderViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveSedentaryAlertDeviceSettingsRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
                SedentaryReminderViewModel.this.getDialogListener().onDismiss();
                if (object.getCode() == 200) {
                    SedentaryReminderViewModel.this.getDialogListener().showSuccessDialog();
                } else {
                    SedentaryReminderViewModel.this.getDialogListener().showErrorDialog();
                }
            }
        });
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setPosition0(int i) {
        this.d = i;
    }

    public final void setPosition1(int i) {
        this.e = i;
    }

    public final void setPosition2(int i) {
        this.f = i;
    }

    public final void setPosition3(int i) {
        this.g = i;
    }

    public final void setPosition4(int i) {
        this.h = i;
    }

    public final void setPosition5(int i) {
        this.i = i;
    }

    public final void setPosition6(int i) {
        this.j = i;
    }
}
