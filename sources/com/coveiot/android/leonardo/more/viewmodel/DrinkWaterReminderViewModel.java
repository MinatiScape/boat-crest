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
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.userappsetting.SaveDrinkReminderSettingsReq;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DrinkWaterReminderData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class DrinkWaterReminderViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5186a;
    @NotNull
    public final String b;
    public DialogListener dialogListener;

    public DrinkWaterReminderViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5186a = context;
        this.b = "DrinkWaterReminderViewModel";
    }

    public static final void d(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    public final String b(int i) {
        return i != 60 ? i != 90 ? i != 120 ? i != 150 ? "01:00:00" : "02:30:00" : "02:00:00" : "01:30:00" : "01:00:00";
    }

    public final void c(int i, String str, int i2, String str2, int i3, boolean z) {
        if (AppUtils.isNetConnected(this.f5186a)) {
            SaveDrinkReminderSettingsReq saveDrinkReminderSettingsReq = new SaveDrinkReminderSettingsReq();
            StringBuilder sb = new StringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i2)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(':');
            sb.append(str2);
            sb.append(":00");
            saveDrinkReminderSettingsReq.setStartTime(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb2.append(format2);
            sb2.append(':');
            sb2.append(str);
            sb2.append(":00");
            saveDrinkReminderSettingsReq.setEndTime(sb2.toString());
            saveDrinkReminderSettingsReq.setInterval(b(i3));
            saveDrinkReminderSettingsReq.setActive(z);
            CoveUserAppSettings.saveDrinkReminderAlertSettings(saveDrinkReminderSettingsReq, new DrinkWaterReminderViewModel$uploadDrinkReminderToServer$1(this, z));
            return;
        }
        getDialogListener().onDismiss();
        Context context = this.f5186a;
        String string = context.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ease_check_your_internet)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(context, string);
        String string2 = this.f5186a.getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DrinkWaterReminderViewModel.d(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    /* JADX WARN: Type inference failed for: r0v17, types: [T, com.coveiot.covepreferences.data.DrinkWaterReminderData, java.lang.Object] */
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
            Context context = this.f5186a;
            Toast.makeText(context, context.getString(R.string.sedentary_reminder_set_value), 0).show();
            return;
        }
        getDialogListener().onShowProgressDialog();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? companion = DrinkWaterReminderData.Companion.getInstance();
        Intrinsics.checkNotNull(companion);
        objectRef.element = companion;
        ((DrinkWaterReminderData) companion).setStartHour(intRef.element);
        ((DrinkWaterReminderData) objectRef.element).setStartMinute(Integer.parseInt(str4));
        ((DrinkWaterReminderData) objectRef.element).setEndHour(intRef2.element);
        ((DrinkWaterReminderData) objectRef.element).setEndMinute(Integer.parseInt(str8));
        ((DrinkWaterReminderData) objectRef.element).setRemindInterval(i);
        ((DrinkWaterReminderData) objectRef.element).setReminderEnable(z);
        SetReminderRequest drinkReminderReq = new SetReminderRequest.Builder().setStartHour1(intRef.element).setEndHour1(intRef2.element).setStartMin1(Integer.parseInt(str4)).setEndMin1(Integer.parseInt(str8)).setReminderInterval(i).setEnabled(((DrinkWaterReminderData) objectRef.element).isReminderEnable()).setReminderType(ReminderType.DRINK_WATER_REMINDER).build();
        BleApi bleApi = BleApiManager.getInstance(this.f5186a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(drinkReminderReq, "drinkReminderReq");
        bleApi.setUserSettings(drinkReminderReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel$callSaveAndBleApi$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                DrinkWaterReminderViewModel.this.getDialogListener().onDismiss();
                DrinkWaterReminderViewModel.this.getDialogListener().showErrorDialog();
                LogHelper.d(DrinkWaterReminderViewModel.this.getTAG(), error.toString());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(DrinkWaterReminderViewModel.this.getTAG(), response.toString());
                UserDataManager.getInstance(DrinkWaterReminderViewModel.this.getContext()).saveDrinkWaterReminderSettings(objectRef.element);
                DrinkWaterReminderViewModel.this.c(intRef2.element, str8, intRef.element, str4, i, z);
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5186a;
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

    @NotNull
    public final DrinkWaterReminderData getDrinkWaterReminderDataFromPref() {
        try {
            DrinkWaterReminderData prefDrinkWaterReminderData = UserDataManager.getInstance(this.f5186a).getDrinkWaterReminderData();
            if (prefDrinkWaterReminderData == null) {
                DrinkWaterReminderData companion = DrinkWaterReminderData.Companion.getInstance();
                Intrinsics.checkNotNull(companion);
                companion.setRemindInterval(60);
                companion.setStartHour(9);
                companion.setStartMinute(0);
                companion.setEndHour(18);
                companion.setEndMinute(0);
            }
            Intrinsics.checkNotNullExpressionValue(prefDrinkWaterReminderData, "prefDrinkWaterReminderData");
            return prefDrinkWaterReminderData;
        } catch (Exception unused) {
            DrinkWaterReminderData companion2 = DrinkWaterReminderData.Companion.getInstance();
            Intrinsics.checkNotNull(companion2);
            companion2.setRemindInterval(60);
            companion2.setStartHour(9);
            companion2.setStartMinute(0);
            companion2.setEndHour(18);
            companion2.setEndMinute(0);
            return companion2;
        }
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }
}
