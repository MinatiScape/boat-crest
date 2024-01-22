package com.coveiot.android.leonardo.more.viewmodel;

import android.app.Dialog;
import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.request.SetWomenWellnessSettingsRequest;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveWomenWellnessReq;
import com.coveiot.coveaccess.userappsetting.SaveWomenWellnessRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WomenWellnessData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class WomenWellnessViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5206a;
    public String am_pm_var;
    @NotNull
    public final String b;
    @NotNull
    public ArrayList<String> c;
    @NotNull
    public ArrayList<String> d;
    public String date_var;
    public DialogListener dialogListener;
    @NotNull
    public ArrayList<String> e;
    @NotNull
    public ArrayList<String> f;
    @NotNull
    public ArrayList<String> g;
    @NotNull
    public ArrayList<String> h;
    public String hour_var;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public String min_var;
    public String month_var;
    public int n;
    @NotNull
    public String o;
    @NotNull
    public final String[] p;
    public String year_var;

    public WomenWellnessViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5206a = context;
        this.b = "WomenWellnessViewModel";
        this.c = new ArrayList<>();
        this.d = new ArrayList<>();
        this.e = new ArrayList<>();
        this.f = new ArrayList<>();
        this.g = new ArrayList<>();
        this.h = new ArrayList<>();
        this.o = "1";
        this.p = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    }

    public static final void g(WomenWellnessViewModel this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setAm_pm_var(obj.toString());
    }

    public static final void h(WomenWellnessViewModel this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setDate_var(obj.toString());
    }

    public static final void i(WomenWellnessViewModel this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setHour_var(obj.toString());
    }

    public static final void k(WomenWellnessViewModel this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setMin_var(obj.toString());
    }

    public static final void l(WomenWellnessViewModel this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.setMonth_var(obj.toString());
        this$0.populateDaysDataInView(dialog);
    }

    public static final void m(WomenWellnessViewModel this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.setYear_var(obj.toString());
        this$0.populateDaysDataInView(dialog);
        this$0.populateMonthsDataInView(dialog);
    }

    @NotNull
    public final String getAm_pm_var() {
        String str = this.am_pm_var;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("am_pm_var");
        return null;
    }

    @NotNull
    public final ArrayList<String> getAmpm() {
        return this.h;
    }

    @NotNull
    public final Context getContext() {
        return this.f5206a;
    }

    @NotNull
    public final String getDate_var() {
        String str = this.date_var;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("date_var");
        return null;
    }

    @NotNull
    public final ArrayList<String> getDays() {
        return this.c;
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
    public final String getHour_var() {
        String str = this.hour_var;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hour_var");
        return null;
    }

    @NotNull
    public final ArrayList<String> getHours() {
        return this.f;
    }

    public final int getMAMPMItemPosition() {
        return this.n;
    }

    public final int getMDayItemPosition() {
        return this.i;
    }

    public final int getMHourItemPosition() {
        return this.l;
    }

    public final int getMMinItemPosition() {
        return this.m;
    }

    public final int getMMonthSelectedPosition() {
        return this.j;
    }

    public final int getMYearSelectedPosition() {
        return this.k;
    }

    @NotNull
    public final String getMin_var() {
        String str = this.min_var;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("min_var");
        return null;
    }

    @NotNull
    public final ArrayList<String> getMins() {
        return this.g;
    }

    @NotNull
    public final String getMonth_var() {
        String str = this.month_var;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("month_var");
        return null;
    }

    @NotNull
    public final ArrayList<String> getMonths() {
        return this.d;
    }

    @NotNull
    public final String getOne() {
        return this.o;
    }

    @NotNull
    public final String[] getStrings() {
        return this.p;
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    @NotNull
    public final WomenWellnessData getWomenWellnessDataFromPref() {
        try {
            WomenWellnessData prefWomenWellnessData = UserDataManager.getInstance(this.f5206a).getWomenWellnessData();
            if (prefWomenWellnessData == null) {
                WomenWellnessData womenWellnessData = WomenWellnessData.getInstance();
                Intrinsics.checkNotNullExpressionValue(womenWellnessData, "getInstance()");
                womenWellnessData.setEnabled(false);
                womenWellnessData.setReminderHour(9);
                womenWellnessData.setReminderMinute(0);
                womenWellnessData.setPeriodReminderAdvance(2);
                womenWellnessData.setOvulationReminderAdvance(2);
                womenWellnessData.setLastPeriodYear(0);
                womenWellnessData.setLastPeriodMonth(0);
                womenWellnessData.setLastPeriodDay(0);
                womenWellnessData.setMenstruationPeriodLength(5);
                womenWellnessData.setMenstruationCycleLength(28);
            }
            Intrinsics.checkNotNullExpressionValue(prefWomenWellnessData, "prefWomenWellnessData");
            return prefWomenWellnessData;
        } catch (Exception unused) {
            WomenWellnessData womenWellnessData2 = WomenWellnessData.getInstance();
            Intrinsics.checkNotNullExpressionValue(womenWellnessData2, "getInstance()");
            womenWellnessData2.setEnabled(false);
            womenWellnessData2.setReminderHour(9);
            womenWellnessData2.setReminderMinute(0);
            womenWellnessData2.setPeriodReminderAdvance(2);
            womenWellnessData2.setOvulationReminderAdvance(2);
            womenWellnessData2.setLastPeriodYear(0);
            womenWellnessData2.setLastPeriodMonth(0);
            womenWellnessData2.setLastPeriodDay(0);
            womenWellnessData2.setMenstruationPeriodLength(5);
            womenWellnessData2.setMenstruationCycleLength(28);
            return womenWellnessData2;
        }
    }

    @NotNull
    public final String getYear_var() {
        String str = this.year_var;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("year_var");
        return null;
    }

    @NotNull
    public final ArrayList<String> getYears() {
        return this.e;
    }

    public final boolean isLeapYear(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        return calendar.getActualMaximum(6) > 365;
    }

    public final void j() {
        this.g.clear();
        for (int i = 0; i < 60; i++) {
            this.g.add(String.valueOf(i));
        }
        this.m = this.g.indexOf(getMin_var());
    }

    public final void n(WomenWellnessData womenWellnessData) {
        UserDataManager.getInstance(this.f5206a).saveWomenWellnessSettings(womenWellnessData);
    }

    public final void populateAMPMDataInView() {
        this.h.clear();
        this.h.add("AM");
        this.h.add("PM");
        this.n = this.h.indexOf(getAm_pm_var());
    }

    public final void populateAmPmDataInView(@NotNull Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        populateAMPMDataInView();
        int i = R.id.amPmPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.h);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(2);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.n);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(false);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.k
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.g(WomenWellnessViewModel.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void populateDaysDataInView() {
        this.c.clear();
        Calendar calendar = Calendar.getInstance();
        int i = 1;
        int i2 = calendar.get(1);
        String format = new SimpleDateFormat("MMMM").format(calendar.getTime());
        if (i2 == Integer.parseInt(getYear_var()) && Intrinsics.areEqual(format, getMonth_var())) {
            int parseInt = Integer.parseInt(String.valueOf(calendar.get(5)));
            if (1 <= parseInt) {
                while (true) {
                    this.c.add(String.valueOf(i));
                    if (i == parseInt) {
                        break;
                    }
                    i++;
                }
            }
        } else if (kotlin.text.m.equals(getMonth_var(), "February", true) && !isLeapYear(Integer.parseInt(getYear_var()))) {
            while (true) {
                int i3 = i + 1;
                this.c.add(String.valueOf(i));
                if (i3 > 28) {
                    break;
                }
                i = i3;
            }
            if (Integer.parseInt(getDate_var()) > 28) {
                setDate_var(this.o);
            }
        } else if (kotlin.text.m.equals(getMonth_var(), "February", true) && isLeapYear(Integer.parseInt(getYear_var()))) {
            while (true) {
                int i4 = i + 1;
                this.c.add(String.valueOf(i));
                if (i4 > 29) {
                    break;
                }
                i = i4;
            }
            if (Integer.parseInt(getDate_var()) > 29) {
                setDate_var(this.o);
            }
        } else if (kotlin.text.m.equals(getMonth_var(), "April", true) || kotlin.text.m.equals(getMonth_var(), "June", true) || kotlin.text.m.equals(getMonth_var(), "September", true) || kotlin.text.m.equals(getMonth_var(), "November", true)) {
            while (true) {
                int i5 = i + 1;
                this.c.add(String.valueOf(i));
                if (i5 > 30) {
                    break;
                }
                i = i5;
            }
            if (Integer.parseInt(getDate_var()) > 30) {
                setDate_var(this.o);
            }
        } else {
            while (true) {
                int i6 = i + 1;
                this.c.add(String.valueOf(i));
                if (i6 > 31) {
                    break;
                }
                i = i6;
            }
        }
        this.i = this.c.indexOf(getDate_var());
    }

    public final void populateHourDataInView() {
        this.f.clear();
        for (int i = 1; i < 13; i++) {
            this.f.add(String.valueOf(i));
        }
        this.l = this.f.indexOf(getHour_var());
    }

    public final void populateHoursDataInView(@NotNull Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        populateHourDataInView();
        int i = R.id.hourPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.f);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.l);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.i
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.i(WomenWellnessViewModel.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void populateMinsDataInView(@NotNull Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        j();
        int i = R.id.minPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.g);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.m);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.j
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.k(WomenWellnessViewModel.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void populateMonthsDataInView() {
        this.d.clear();
        Calendar calendar = Calendar.getInstance();
        int i = 0;
        if (calendar.get(1) == Integer.parseInt(getYear_var())) {
            int parseInt = Integer.parseInt(String.valueOf(calendar.get(2)));
            if (parseInt >= 0) {
                while (true) {
                    this.d.add(this.p[i]);
                    if (i == parseInt) {
                        break;
                    }
                    i++;
                }
            }
        } else {
            int length = this.p.length - 1;
            if (length >= 0) {
                while (true) {
                    this.d.add(this.p[i]);
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
        }
        this.j = this.d.indexOf(getMonth_var());
    }

    public final void populateYearsDataInView() {
        Calendar calendar = Calendar.getInstance();
        this.e.clear();
        int parseInt = Integer.parseInt(String.valueOf(calendar.get(1)));
        int i = 2000;
        if (2000 <= parseInt) {
            while (true) {
                this.e.add(String.valueOf(i));
                if (i == parseInt) {
                    break;
                }
                i++;
            }
        }
        this.k = this.e.indexOf(getYear_var());
    }

    public final void saveWomenWellnessSettings(@NotNull WomenWellnessData womenWellnessData) {
        Intrinsics.checkNotNullParameter(womenWellnessData, "womenWellnessData");
        SaveWomenWellnessReq saveWomenWellnessReq = new SaveWomenWellnessReq();
        ArrayList arrayList = new ArrayList();
        SaveWomenWellnessReq.ReminderListBean reminderListBean = new SaveWomenWellnessReq.ReminderListBean();
        reminderListBean.setActive(Boolean.valueOf(womenWellnessData.isEnabled()));
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(womenWellnessData.getReminderHour())}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(womenWellnessData.getReminderMinute())}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(":00");
        reminderListBean.setRemindAt(sb.toString());
        reminderListBean.setType(this.f5206a.getResources().getString(R.string.period));
        reminderListBean.setRemindBefore(Integer.valueOf(womenWellnessData.getPeriodReminderAdvance()));
        arrayList.add(reminderListBean);
        SaveWomenWellnessReq.ReminderListBean reminderListBean2 = new SaveWomenWellnessReq.ReminderListBean();
        reminderListBean2.setActive(Boolean.valueOf(womenWellnessData.isEnabled()));
        StringBuilder sb2 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(womenWellnessData.getReminderHour())}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb2.append(format3);
        sb2.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(womenWellnessData.getReminderMinute())}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb2.append(format4);
        sb2.append(":00");
        reminderListBean2.setRemindAt(sb2.toString());
        reminderListBean2.setType(this.f5206a.getResources().getString(R.string.ovulation));
        reminderListBean2.setRemindBefore(Integer.valueOf(womenWellnessData.getOvulationReminderAdvance()));
        arrayList.add(reminderListBean2);
        saveWomenWellnessReq.setActive(Boolean.valueOf(womenWellnessData.isEnabled()));
        saveWomenWellnessReq.setCycleLength(Integer.valueOf(womenWellnessData.getMenstruationCycleLength()));
        saveWomenWellnessReq.setPeriodLength(Integer.valueOf(womenWellnessData.getMenstruationPeriodLength()));
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, womenWellnessData.getLastPeriodDay());
        calendar.set(1, womenWellnessData.getLastPeriodYear());
        calendar.set(2, womenWellnessData.getLastPeriodMonth() - 1);
        saveWomenWellnessReq.setCycleStartDate(new SimpleDateFormat("yyyy-MM-dd", locale).format(Long.valueOf(calendar.getTimeInMillis())));
        if (!DeviceUtils.Companion.isEastApexDevice(this.f5206a)) {
            saveWomenWellnessReq.setReminderListBeans(arrayList);
        }
        CoveUserAppSettings.saveMenstruationSettings(saveWomenWellnessReq, new CoveApiListener<SaveWomenWellnessRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.WomenWellnessViewModel$saveWomenWellnessSettings$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveWomenWellnessRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
            }
        });
    }

    public final void sendWomenWellnessDataToBand(@NotNull WomenWellnessData womenWellnessData) {
        Intrinsics.checkNotNullParameter(womenWellnessData, "womenWellnessData");
        if (this.dialogListener != null) {
            getDialogListener().onShowProgressDialog();
        }
        BleApiManager.getInstance(this.f5206a).getBleApi().setUserSettings(new SetWomenWellnessSettingsRequest.Builder().setEnabled(womenWellnessData.isEnabled()).setLastPeriodDay(womenWellnessData.getLastPeriodDay()).setLastPeriodMonth(womenWellnessData.getLastPeriodMonth()).setLastPeriodYear(womenWellnessData.getLastPeriodYear()).setMenstruationCycleLength(womenWellnessData.getMenstruationCycleLength()).setMenstruationPeriodLength(womenWellnessData.getMenstruationPeriodLength()).setOvulationReminderAdvance(womenWellnessData.getOvulationReminderAdvance()).setPeriodReminderAdvance(womenWellnessData.getPeriodReminderAdvance()).setReminderHour(womenWellnessData.getReminderHour()).setReminderMinute(womenWellnessData.getReminderMinute()).build(), new WomenWellnessViewModel$sendWomenWellnessDataToBand$2(this, womenWellnessData));
    }

    public final void setAm_pm_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.am_pm_var = str;
    }

    public final void setAmpm(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.h = arrayList;
    }

    public final void setDate_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date_var = str;
    }

    public final void setDays(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.c = arrayList;
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setHour_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hour_var = str;
    }

    public final void setHours(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f = arrayList;
    }

    public final void setMAMPMItemPosition(int i) {
        this.n = i;
    }

    public final void setMDayItemPosition(int i) {
        this.i = i;
    }

    public final void setMHourItemPosition(int i) {
        this.l = i;
    }

    public final void setMMinItemPosition(int i) {
        this.m = i;
    }

    public final void setMMonthSelectedPosition(int i) {
        this.j = i;
    }

    public final void setMYearSelectedPosition(int i) {
        this.k = i;
    }

    public final void setMin_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.min_var = str;
    }

    public final void setMins(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.g = arrayList;
    }

    public final void setMonth_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.month_var = str;
    }

    public final void setMonths(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.d = arrayList;
    }

    public final void setOne(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.o = str;
    }

    public final void setYear_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.year_var = str;
    }

    public final void setYears(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.e = arrayList;
    }

    public final void populateYearsDataInView(@NotNull final Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        populateYearsDataInView();
        int i = R.id.number_picker_year;
        ((WheelPicker) dialog.findViewById(i)).setData(this.e);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.k);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.l
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.m(WomenWellnessViewModel.this, dialog, wheelPicker, obj, i2);
            }
        });
    }

    public final void populateMonthsDataInView(@NotNull final Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        populateMonthsDataInView();
        int i = R.id.number_picker_month;
        ((WheelPicker) dialog.findViewById(i)).setData(this.d);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        if (this.d.size() > 5) {
            ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
            ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        } else {
            ((WheelPicker) dialog.findViewById(i)).setCyclic(false);
        }
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.j);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.m
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.l(WomenWellnessViewModel.this, dialog, wheelPicker, obj, i2);
            }
        });
    }

    public final void populateDaysDataInView(@NotNull Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        populateDaysDataInView();
        int i = R.id.number_picker_date;
        ((WheelPicker) dialog.findViewById(i)).setData(this.c);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.i);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.h
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.h(WomenWellnessViewModel.this, wheelPicker, obj, i2);
            }
        });
    }
}
