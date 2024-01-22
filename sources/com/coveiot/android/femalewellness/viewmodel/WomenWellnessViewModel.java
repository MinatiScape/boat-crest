package com.coveiot.android.femalewellness.viewmodel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.SetWomenWellnessSettingsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.femalewellness.DialogListener;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.Utils;
import com.coveiot.android.femalewellness.db.FemaleWellnessRepository;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.fitness.model.MensSettings;
import com.coveiot.coveaccess.fitness.model.MensurationSymptomsRecordBeans;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordApiManager;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordType;
import com.coveiot.coveaccess.fitnessrecord.SaveFitnessRecordsResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SSaveMensurationFitnessRecordReq;
import com.coveiot.coveaccess.userappsetting.SaveWomenWellnessReq;
import com.coveiot.coveaccess.userappsetting.SaveWomenWellnessRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.m;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class WomenWellnessViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4399a;
    public String am_pm_var;
    @NotNull
    public final String b;
    @NotNull
    public String c;
    @NotNull
    public String d;
    public DialogListener dialogListener;
    @NotNull
    public String e;
    @NotNull
    public ArrayList<String> f;
    @NotNull
    public ArrayList<String> g;
    @NotNull
    public ArrayList<String> h;
    public String hour_var;
    @NotNull
    public ArrayList<String> i;
    @NotNull
    public ArrayList<String> j;
    @NotNull
    public ArrayList<String> k;
    public int l;
    public int m;
    public String min_var;
    public int n;
    public int o;
    public int p;
    public int q;
    @NotNull
    public String r;
    @NotNull
    public final String[] s;

    @DebugMetadata(c = "com.coveiot.android.femalewellness.viewmodel.WomenWellnessViewModel$saveWomenWellnessDataToDb$1", f = "WomenWellnessViewModel.kt", i = {}, l = {663}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ MensurationSymptomsRecordBeans $item;
        public final /* synthetic */ MensSettings $mensSettings;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.femalewellness.viewmodel.WomenWellnessViewModel$saveWomenWellnessDataToDb$1$1", f = "WomenWellnessViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.femalewellness.viewmodel.WomenWellnessViewModel$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public static final class C0270a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ MensurationSymptomsRecordBeans $item;
            public final /* synthetic */ MensSettings $mensSettings;
            public int label;
            public final /* synthetic */ WomenWellnessViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0270a(WomenWellnessViewModel womenWellnessViewModel, MensurationSymptomsRecordBeans mensurationSymptomsRecordBeans, MensSettings mensSettings, Continuation<? super C0270a> continuation) {
                super(2, continuation);
                this.this$0 = womenWellnessViewModel;
                this.$item = mensurationSymptomsRecordBeans;
                this.$mensSettings = mensSettings;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0270a(this.this$0, this.$item, this.$mensSettings, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0270a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    FemaleWellnessRepository singletonHolder = FemaleWellnessRepository.Companion.getInstance(this.this$0.getContext());
                    String date = this.$item.getDate();
                    Intrinsics.checkNotNullExpressionValue(date, "item.date");
                    String cycleStartDate = this.$mensSettings.getCycleStartDate();
                    Intrinsics.checkNotNullExpressionValue(cycleStartDate, "mensSettings.cycleStartDate");
                    Integer cycleLength = this.$mensSettings.getCycleLength();
                    Intrinsics.checkNotNullExpressionValue(cycleLength, "mensSettings.cycleLength");
                    int intValue = cycleLength.intValue();
                    Integer periodLength = this.$mensSettings.getPeriodLength();
                    Intrinsics.checkNotNullExpressionValue(periodLength, "mensSettings.periodLength");
                    int intValue2 = periodLength.intValue();
                    String phase = this.$item.getPhase();
                    Boolean active = this.$mensSettings.getActive();
                    Intrinsics.checkNotNullExpressionValue(active, "mensSettings.active");
                    singletonHolder.insertOrUpdateWellnessData(date, cycleStartDate, intValue, intValue2, phase, active.booleanValue());
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(MensurationSymptomsRecordBeans mensurationSymptomsRecordBeans, MensSettings mensSettings, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$item = mensurationSymptomsRecordBeans;
            this.$mensSettings = mensSettings;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$item, this.$mensSettings, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C0270a c0270a = new C0270a(WomenWellnessViewModel.this, this.$item, this.$mensSettings, null);
                this.label = 1;
                if (BuildersKt.withContext(io2, c0270a, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public WomenWellnessViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4399a = context;
        this.b = "WomenWellnessViewModel";
        this.c = "-1";
        this.d = "-1";
        this.e = "-1";
        this.f = new ArrayList<>();
        this.g = new ArrayList<>();
        this.h = new ArrayList<>();
        this.i = new ArrayList<>();
        this.j = new ArrayList<>();
        this.k = new ArrayList<>();
        this.r = "1";
        this.s = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    }

    public static final void h(WomenWellnessViewModel this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setAm_pm_var(obj.toString());
    }

    public static final void i(WomenWellnessViewModel this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c = obj.toString();
    }

    public static final void j(WomenWellnessViewModel this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setHour_var(obj.toString());
    }

    public static final void l(WomenWellnessViewModel this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setMin_var(obj.toString());
    }

    public static final void m(WomenWellnessViewModel this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.d = obj.toString();
        this$0.populateDaysDataInView(dialog);
    }

    public static final void n(WomenWellnessViewModel this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.e = obj.toString();
        this$0.populateMonthsDataInView(dialog);
        this$0.populateDaysDataInView(dialog);
    }

    public static final void p(BottomSheetDialogImageTitleMessage bottomSheetDialogOneButtonTitleMessage, WomenWellnessViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        if (this$0.getDialogListener() instanceof Activity) {
            DialogListener dialogListener = this$0.getDialogListener();
            Intrinsics.checkNotNull(dialogListener, "null cannot be cast to non-null type android.app.Activity");
            ((Activity) dialogListener).finish();
        }
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
        return this.k;
    }

    @NotNull
    public final Context getContext() {
        return this.f4399a;
    }

    @NotNull
    public final String getDate_var() {
        return this.c;
    }

    @NotNull
    public final ArrayList<String> getDays() {
        return this.f;
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
        return this.i;
    }

    public final int getMAMPMItemPosition() {
        return this.q;
    }

    public final int getMDayItemPosition() {
        return this.l;
    }

    public final int getMHourItemPosition() {
        return this.o;
    }

    public final int getMMinItemPosition() {
        return this.p;
    }

    public final int getMMonthSelectedPosition() {
        return this.m;
    }

    public final int getMYearSelectedPosition() {
        return this.n;
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
        return this.j;
    }

    @NotNull
    public final String getMonth_var() {
        return this.d;
    }

    @NotNull
    public final ArrayList<String> getMonths() {
        return this.g;
    }

    @NotNull
    public final String getOne() {
        return this.r;
    }

    @Nullable
    public final List<MensurationSymptomsRecordBeans> getPeriodAndOvulationDates(@Nullable WomenWellnessData womenWellnessData, @NotNull Calendar currentCalendarMonth) {
        int i;
        int i2;
        int i3;
        WomenWellnessViewModel womenWellnessViewModel = this;
        Intrinsics.checkNotNullParameter(currentCalendarMonth, "currentCalendarMonth");
        new ArrayList();
        if (UserDataManager.getInstance(womenWellnessViewModel.f4399a).getWomenWellnessData() == null || UserDataManager.getInstance(womenWellnessViewModel.f4399a).getWomenWellnessData().getLastPeriodYear() == 0) {
            return null;
        }
        int menstruationCycleLength = UserDataManager.getInstance(womenWellnessViewModel.f4399a).getWomenWellnessData().getMenstruationCycleLength();
        Object clone = Calendar.getInstance().clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        calendar.set(2, UserDataManager.getInstance(womenWellnessViewModel.f4399a).getWomenWellnessData().getLastPeriodMonth() - 1);
        calendar.set(1, UserDataManager.getInstance(womenWellnessViewModel.f4399a).getWomenWellnessData().getLastPeriodYear());
        int i4 = 5;
        calendar.set(5, UserDataManager.getInstance(womenWellnessViewModel.f4399a).getWomenWellnessData().getLastPeriodDay());
        int i5 = 0;
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        int daysDifference = ((int) Utils.Companion.getDaysDifference(calendar, currentCalendarMonth)) / menstruationCycleLength;
        Object clone2 = calendar.clone();
        Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar2 = (Calendar) clone2;
        int menstruationPeriodLength = UserDataManager.getInstance(womenWellnessViewModel.f4399a).getWomenWellnessData().getMenstruationPeriodLength();
        ArrayList arrayList = new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        if (daysDifference >= 0) {
            int i6 = 0;
            while (true) {
                int i7 = menstruationPeriodLength - 1;
                if (i7 >= 0) {
                    while (true) {
                        Object clone3 = calendar2.clone();
                        Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
                        Calendar calendar3 = (Calendar) clone3;
                        calendar3.add(i4, i5);
                        MensurationSymptomsRecordBeans mensurationSymptomsRecordBeans = new MensurationSymptomsRecordBeans();
                        i2 = menstruationPeriodLength;
                        String format = simpleDateFormat.format(calendar.getTime());
                        Intrinsics.checkNotNullExpressionValue(format, "simpleDateformat.format(startCal.time)");
                        i = daysDifference;
                        mensurationSymptomsRecordBeans.setDate(simpleDateFormat.format(calendar3.getTime()));
                        MensSettings mensSettings = new MensSettings();
                        i3 = i6;
                        mensSettings.setActive(Boolean.FALSE);
                        Intrinsics.checkNotNull(womenWellnessData);
                        mensSettings.setCycleLength(Integer.valueOf(womenWellnessData.getMenstruationCycleLength()));
                        mensSettings.setPeriodLength(Integer.valueOf(womenWellnessData.getMenstruationPeriodLength()));
                        mensSettings.setCycleStartDate(format);
                        mensurationSymptomsRecordBeans.setMensSettings(mensSettings);
                        mensurationSymptomsRecordBeans.setType(FitnessRecordType.MENSES.name());
                        mensurationSymptomsRecordBeans.setBaseUnit("UNITLESS");
                        mensurationSymptomsRecordBeans.setPhase("PERIOD");
                        if (calendar3.compareTo(currentCalendarMonth) > 0) {
                            break;
                        }
                        arrayList.add(mensurationSymptomsRecordBeans);
                        if (i5 == i7) {
                            break;
                        }
                        i5++;
                        menstruationPeriodLength = i2;
                        daysDifference = i;
                        i6 = i3;
                        i4 = 5;
                    }
                } else {
                    i = daysDifference;
                    i2 = menstruationPeriodLength;
                    i3 = i6;
                }
                int menstruationCycleLength2 = UserDataManager.getInstance(womenWellnessViewModel.f4399a).getWomenWellnessData().getMenstruationCycleLength();
                Utils.Companion companion = Utils.Companion;
                int ovulationDaysConst = (menstruationCycleLength2 - companion.getOvulationDaysConst()) - companion.getDaysBeforeForOvulation();
                Object clone4 = calendar2.clone();
                Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
                Calendar calendar4 = (Calendar) clone4;
                int i8 = 6;
                calendar4.add(6, ovulationDaysConst);
                int ovluationDaysLength = companion.getOvluationDaysLength() - 1;
                if (ovluationDaysLength >= 0) {
                    int i9 = 0;
                    while (true) {
                        Object clone5 = calendar4.clone();
                        Intrinsics.checkNotNull(clone5, "null cannot be cast to non-null type java.util.Calendar");
                        Calendar calendar5 = (Calendar) clone5;
                        calendar5.add(i8, i9);
                        MensurationSymptomsRecordBeans mensurationSymptomsRecordBeans2 = new MensurationSymptomsRecordBeans();
                        String format2 = simpleDateFormat.format(calendar.getTime());
                        Intrinsics.checkNotNullExpressionValue(format2, "simpleDateformat.format(startCal.time)");
                        mensurationSymptomsRecordBeans2.setDate(simpleDateFormat.format(calendar5.getTime()));
                        MensSettings mensSettings2 = new MensSettings();
                        mensSettings2.setActive(Boolean.FALSE);
                        Intrinsics.checkNotNull(womenWellnessData);
                        mensSettings2.setCycleLength(Integer.valueOf(womenWellnessData.getMenstruationCycleLength()));
                        mensSettings2.setPeriodLength(Integer.valueOf(womenWellnessData.getMenstruationPeriodLength()));
                        mensSettings2.setCycleStartDate(format2);
                        mensurationSymptomsRecordBeans2.setMensSettings(mensSettings2);
                        mensurationSymptomsRecordBeans2.setType(FitnessRecordType.MENSES.name());
                        mensurationSymptomsRecordBeans2.setBaseUnit("UNITLESS");
                        mensurationSymptomsRecordBeans2.setPhase("OVULATION");
                        if (calendar5.compareTo(currentCalendarMonth) > 0) {
                            break;
                        }
                        arrayList.add(mensurationSymptomsRecordBeans2);
                        if (i9 == ovluationDaysLength) {
                            break;
                        }
                        i9++;
                        i8 = 6;
                    }
                }
                daysDifference = i;
                int i10 = i3;
                if (i10 == daysDifference) {
                    break;
                }
                i6 = i10 + 1;
                i4 = 5;
                womenWellnessViewModel = this;
                menstruationPeriodLength = i2;
                i5 = 0;
            }
        }
        return arrayList;
    }

    @NotNull
    public final String[] getStrings() {
        return this.s;
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    @NotNull
    public final WomenWellnessData getWomenWellnessDataFromPref() {
        try {
            WomenWellnessData prefWomenWellnessData = UserDataManager.getInstance(this.f4399a).getWomenWellnessData();
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
        return this.e;
    }

    @NotNull
    public final ArrayList<String> getYears() {
        return this.h;
    }

    public final boolean isLeapYear(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        return calendar.getActualMaximum(6) > 365;
    }

    public final void k() {
        this.j.clear();
        for (int i = 0; i < 60; i++) {
            this.j.add(String.valueOf(i));
        }
        this.p = this.j.indexOf(getMin_var());
    }

    public final void o(WomenWellnessData womenWellnessData) {
        UserDataManager.getInstance(this.f4399a).saveWomenWellnessSettings(womenWellnessData);
    }

    public final void populateAMPMDataInView() {
        this.k.clear();
        this.k.add("AM");
        this.k.add("PM");
        this.q = this.k.indexOf(getAm_pm_var());
    }

    public final void populateAmPmDataInView(@NotNull Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        populateAMPMDataInView();
        int i = R.id.amPmPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.k);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(2);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.q);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(false);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.femalewellness.viewmodel.b
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.h(WomenWellnessViewModel.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void populateDaysDataInView() {
        this.f.clear();
        Calendar calendar = Calendar.getInstance();
        int i = 1;
        int i2 = calendar.get(1);
        String format = new SimpleDateFormat("MMMM").format(calendar.getTime());
        if (i2 == Integer.parseInt(this.e) && Intrinsics.areEqual(format, this.d)) {
            int parseInt = Integer.parseInt(String.valueOf(calendar.get(5)));
            if (1 <= parseInt) {
                while (true) {
                    this.f.add(String.valueOf(i));
                    if (i == parseInt) {
                        break;
                    }
                    i++;
                }
            }
        } else if (m.equals(this.d, "February", true) && !isLeapYear(Integer.parseInt(this.e))) {
            while (true) {
                int i3 = i + 1;
                this.f.add(String.valueOf(i));
                if (i3 > 28) {
                    break;
                }
                i = i3;
            }
            if (Integer.parseInt(this.c) > 28) {
                this.c = this.r;
            }
        } else if (m.equals(this.d, "February", true) && isLeapYear(Integer.parseInt(this.e))) {
            while (true) {
                int i4 = i + 1;
                this.f.add(String.valueOf(i));
                if (i4 > 29) {
                    break;
                }
                i = i4;
            }
            if (Integer.parseInt(this.c) > 29) {
                this.c = this.r;
            }
        } else if (m.equals(this.d, "April", true) || m.equals(this.d, "June", true) || m.equals(this.d, "September", true) || m.equals(this.d, "November", true)) {
            while (true) {
                int i5 = i + 1;
                this.f.add(String.valueOf(i));
                if (i5 > 30) {
                    break;
                }
                i = i5;
            }
            if (Integer.parseInt(this.c) > 30) {
                this.c = this.r;
            }
        } else {
            while (true) {
                int i6 = i + 1;
                this.f.add(String.valueOf(i));
                if (i6 > 31) {
                    break;
                }
                i = i6;
            }
        }
        this.l = this.f.indexOf(this.c);
    }

    public final void populateHourDataInView() {
        this.i.clear();
        for (int i = 1; i < 13; i++) {
            this.i.add(String.valueOf(i));
        }
        this.o = this.i.indexOf(getHour_var());
    }

    public final void populateHoursDataInView(@NotNull Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        populateHourDataInView();
        int i = R.id.hourPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.i);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.o);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.femalewellness.viewmodel.c
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.j(WomenWellnessViewModel.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void populateMinsDataInView(@NotNull Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        k();
        int i = R.id.minPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.j);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.p);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.femalewellness.viewmodel.e
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.l(WomenWellnessViewModel.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void populateMonthsDataInView() {
        this.g.clear();
        Object clone = Calendar.getInstance().clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        Object clone2 = Calendar.getInstance().clone();
        Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar2 = (Calendar) clone2;
        calendar2.add(2, -1);
        this.g.add(this.s[calendar.get(2)]);
        if (calendar.get(1) > Integer.parseInt(this.e)) {
            this.g.add(this.s[calendar2.get(2)]);
        } else if (calendar.get(1) == Integer.parseInt(this.e) && calendar.get(2) > calendar2.get(2)) {
            this.g.add(this.s[calendar2.get(2)]);
        }
        new SimpleDateFormat("MMMM").format(calendar.getTime());
        this.m = this.g.indexOf(this.d);
    }

    public final void populateYearsDataInView() {
        Object clone = Calendar.getInstance().clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        Object clone2 = Calendar.getInstance().clone();
        Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar2 = (Calendar) clone2;
        calendar.add(2, -1);
        this.h.clear();
        this.h.add(String.valueOf(calendar2.get(1)));
        if (calendar.get(1) < calendar2.get(1)) {
            this.h.add(String.valueOf(calendar.get(1)));
        }
        this.n = this.h.indexOf(this.e);
    }

    public final void saveMensurationRecords(@Nullable final WomenWellnessData womenWellnessData, @NotNull Calendar cal) {
        Intrinsics.checkNotNullParameter(cal, "cal");
        List<MensurationSymptomsRecordBeans> periodAndOvulationDates = getPeriodAndOvulationDates(womenWellnessData, cal);
        final SSaveMensurationFitnessRecordReq sSaveMensurationFitnessRecordReq = new SSaveMensurationFitnessRecordReq();
        if (periodAndOvulationDates != null && periodAndOvulationDates.size() > 0) {
            sSaveMensurationFitnessRecordReq.setItems(periodAndOvulationDates);
            LogHelper.d("MensurationSymptomsRecord", new Gson().toJson(sSaveMensurationFitnessRecordReq));
            FitnessRecordApiManager.saveMensurationFitnessRecords(sSaveMensurationFitnessRecordReq, new CoveApiListener<SaveFitnessRecordsResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.femalewellness.viewmodel.WomenWellnessViewModel$saveMensurationRecords$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@NotNull CoveApiErrorModel object) {
                    Intrinsics.checkNotNullParameter(object, "object");
                    WomenWellnessViewModel.this.getDialogListener().showErrorDialog();
                    LogHelper.d("MensurationSymptomsRecord", object.getMsg());
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull SaveFitnessRecordsResponse object) {
                    Intrinsics.checkNotNullParameter(object, "object");
                    WomenWellnessViewModel.this.showBottomSheet();
                    WomenWellnessViewModel womenWellnessViewModel = WomenWellnessViewModel.this;
                    WomenWellnessData womenWellnessData2 = womenWellnessData;
                    Intrinsics.checkNotNull(womenWellnessData2);
                    womenWellnessViewModel.o(womenWellnessData2);
                    WomenWellnessViewModel womenWellnessViewModel2 = WomenWellnessViewModel.this;
                    List<MensurationSymptomsRecordBeans> items = sSaveMensurationFitnessRecordReq.getItems();
                    Intrinsics.checkNotNullExpressionValue(items, "mensurationFitnessRecordReq.items");
                    womenWellnessViewModel2.saveWomenWellnessDataToDb(items);
                    LogHelper.d("MensurationSymptomsRecord", object.message);
                }
            });
            return;
        }
        showBottomSheet();
        Intrinsics.checkNotNull(womenWellnessData);
        o(womenWellnessData);
    }

    public final void saveWomenWellnessDataToDb(@NotNull List<MensurationSymptomsRecordBeans> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        for (MensurationSymptomsRecordBeans mensurationSymptomsRecordBeans : items) {
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), null, null, new a(mensurationSymptomsRecordBeans, mensurationSymptomsRecordBeans.getMensSettings(), null), 3, null);
        }
    }

    public final void saveWomenWellnessSettings(@NotNull final WomenWellnessData womenWellnessData, @NotNull SetWomenWellnessSettingsRequest setWomenWellnessSettingsRequest) {
        Intrinsics.checkNotNullParameter(womenWellnessData, "womenWellnessData");
        Intrinsics.checkNotNullParameter(setWomenWellnessSettingsRequest, "setWomenWellnessSettingsRequest");
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
        reminderListBean.setType(this.f4399a.getResources().getString(R.string.period));
        reminderListBean.setRemindBefore(Integer.valueOf(womenWellnessData.getPeriodReminderAdvance()));
        arrayList.add(reminderListBean);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isMatrixDevice(this.f4399a)) {
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
            reminderListBean2.setType(this.f4399a.getResources().getString(R.string.ovulation));
            reminderListBean2.setRemindBefore(Integer.valueOf(womenWellnessData.getOvulationReminderAdvance()));
            arrayList.add(reminderListBean2);
        }
        saveWomenWellnessReq.setActive(Boolean.valueOf(womenWellnessData.isEnabled()));
        saveWomenWellnessReq.setCycleLength(Integer.valueOf(womenWellnessData.getMenstruationCycleLength()));
        saveWomenWellnessReq.setPeriodLength(Integer.valueOf(womenWellnessData.getMenstruationPeriodLength()));
        final Calendar calendar = Calendar.getInstance();
        calendar.set(5, womenWellnessData.getLastPeriodDay());
        calendar.set(1, womenWellnessData.getLastPeriodYear());
        calendar.set(2, womenWellnessData.getLastPeriodMonth() - 1);
        saveWomenWellnessReq.setCycleStartDate(new SimpleDateFormat("yyyy-MM-dd", locale).format(Long.valueOf(calendar.getTimeInMillis())));
        if (!companion.isEastApexDevice(this.f4399a)) {
            saveWomenWellnessReq.setReminderListBeans(arrayList);
        }
        CoveUserAppSettings.saveMenstruationSettings(saveWomenWellnessReq, new CoveApiListener<SaveWomenWellnessRes, CoveApiErrorModel>() { // from class: com.coveiot.android.femalewellness.viewmodel.WomenWellnessViewModel$saveWomenWellnessSettings$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                WomenWellnessViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveWomenWellnessRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
                DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                if (!companion2.isCADevice(WomenWellnessViewModel.this.getContext()) && !companion2.isCYDevice(WomenWellnessViewModel.this.getContext()) && !companion2.isPS1Device(WomenWellnessViewModel.this.getContext()) && !companion2.isBESDevice(WomenWellnessViewModel.this.getContext())) {
                    WomenWellnessViewModel.this.showBottomSheet();
                    WomenWellnessViewModel womenWellnessViewModel = WomenWellnessViewModel.this;
                    WomenWellnessData womenWellnessData2 = womenWellnessData;
                    Intrinsics.checkNotNull(womenWellnessData2);
                    womenWellnessViewModel.o(womenWellnessData2);
                    return;
                }
                WomenWellnessViewModel womenWellnessViewModel2 = WomenWellnessViewModel.this;
                WomenWellnessData womenWellnessData3 = womenWellnessData;
                Calendar cal = calendar;
                Intrinsics.checkNotNullExpressionValue(cal, "cal");
                womenWellnessViewModel2.saveMensurationRecords(womenWellnessData3, cal);
            }
        });
    }

    public final void sendWomenWellnessDataToBand(@NotNull final WomenWellnessData womenWellnessData) {
        Intrinsics.checkNotNullParameter(womenWellnessData, "womenWellnessData");
        if (this.dialogListener != null) {
            getDialogListener().onShowProgressDialog();
        }
        final SetWomenWellnessSettingsRequest build = new SetWomenWellnessSettingsRequest.Builder().setEnabled(womenWellnessData.isEnabled()).setLastPeriodDay(womenWellnessData.getLastPeriodDay()).setLastPeriodMonth(womenWellnessData.getLastPeriodMonth()).setLastPeriodYear(womenWellnessData.getLastPeriodYear()).setMenstruationCycleLength(womenWellnessData.getMenstruationCycleLength()).setMenstruationPeriodLength(womenWellnessData.getMenstruationPeriodLength()).setOvulationReminderAdvance(womenWellnessData.getOvulationReminderAdvance()).setPeriodReminderAdvance(womenWellnessData.getPeriodReminderAdvance()).setReminderHour(womenWellnessData.getReminderHour()).setReminderMinute(womenWellnessData.getReminderMinute()).build();
        BleApiManager.getInstance(this.f4399a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.femalewellness.viewmodel.WomenWellnessViewModel$sendWomenWellnessDataToBand$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                WomenWellnessViewModel womenWellnessViewModel = WomenWellnessViewModel.this;
                if (womenWellnessViewModel.dialogListener != null) {
                    womenWellnessViewModel.getDialogListener().showErrorDialog();
                    LogHelper.d(WomenWellnessViewModel.this.getTAG(), error.toString());
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(WomenWellnessViewModel.this.getTAG(), response.toString());
                WomenWellnessViewModel.this.saveWomenWellnessSettings(womenWellnessData, build);
            }
        });
    }

    public final void setAm_pm_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.am_pm_var = str;
    }

    public final void setAmpm(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.k = arrayList;
    }

    public final void setDate_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.c = str;
    }

    public final void setDays(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f = arrayList;
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
        this.i = arrayList;
    }

    public final void setMAMPMItemPosition(int i) {
        this.q = i;
    }

    public final void setMDayItemPosition(int i) {
        this.l = i;
    }

    public final void setMHourItemPosition(int i) {
        this.o = i;
    }

    public final void setMMinItemPosition(int i) {
        this.p = i;
    }

    public final void setMMonthSelectedPosition(int i) {
        this.m = i;
    }

    public final void setMYearSelectedPosition(int i) {
        this.n = i;
    }

    public final void setMin_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.min_var = str;
    }

    public final void setMins(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.j = arrayList;
    }

    public final void setMonth_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }

    public final void setMonths(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.g = arrayList;
    }

    public final void setOne(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.r = str;
    }

    public final void setYear_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }

    public final void setYears(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.h = arrayList;
    }

    public final void showBottomSheet() {
        DialogListener dialogListener = getDialogListener();
        Intrinsics.checkNotNull(dialogListener, "null cannot be cast to non-null type android.app.Activity");
        if (UserDataManager.getInstance((Activity) dialogListener).getDoNotDisturbData() != null) {
            Utils.Companion companion = Utils.Companion;
            DialogListener dialogListener2 = getDialogListener();
            Intrinsics.checkNotNull(dialogListener2, "null cannot be cast to non-null type android.app.Activity");
            if (companion.isDNDEnabled(UserDataManager.getInstance((Activity) dialogListener2).getDoNotDisturbData())) {
                DialogListener dialogListener3 = getDialogListener();
                Intrinsics.checkNotNull(dialogListener3, "null cannot be cast to non-null type android.app.Activity");
                Drawable drawable = this.f4399a.getResources().getDrawable(R.drawable.info_icon_new);
                Intrinsics.checkNotNullExpressionValue(drawable, "context.resources.getDra…R.drawable.info_icon_new)");
                String string = this.f4399a.getString(R.string.success_message);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.success_message)");
                String string2 = this.f4399a.getString(R.string.turn_off_dnd_enable_sedentary);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…off_dnd_enable_sedentary)");
                final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage((Activity) dialogListener3, drawable, string, string2, false);
                bottomSheetDialogImageTitleMessage.setCancelable(false);
                bottomSheetDialogImageTitleMessage.setCancelableOutside(false);
                String string3 = this.f4399a.getResources().getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…ring.ok\n                )");
                bottomSheetDialogImageTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.viewmodel.a
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WomenWellnessViewModel.p(BottomSheetDialogImageTitleMessage.this, this, view);
                    }
                });
                bottomSheetDialogImageTitleMessage.show();
            } else if (this.dialogListener != null) {
                getDialogListener().showSuccessDialog();
            }
        } else if (this.dialogListener != null) {
            getDialogListener().showSuccessDialog();
        }
    }

    public final void populateYearsDataInView(@NotNull final Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        populateYearsDataInView();
        int i = R.id.number_picker_year;
        ((WheelPicker) dialog.findViewById(i)).setData(this.h);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(3);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.n);
        if (this.h.size() > 5) {
            ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(3);
            ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        } else {
            ((WheelPicker) dialog.findViewById(i)).setCyclic(false);
        }
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.femalewellness.viewmodel.g
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.n(WomenWellnessViewModel.this, dialog, wheelPicker, obj, i2);
            }
        });
    }

    public final void populateMonthsDataInView(@NotNull final Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        populateMonthsDataInView();
        int i = R.id.number_picker_month;
        ((WheelPicker) dialog.findViewById(i)).setData(this.g);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(3);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.m);
        if (this.g.size() > 5) {
            ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
            ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        } else {
            ((WheelPicker) dialog.findViewById(i)).setCyclic(false);
        }
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.femalewellness.viewmodel.f
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.m(WomenWellnessViewModel.this, dialog, wheelPicker, obj, i2);
            }
        });
    }

    public final void populateDaysDataInView(@NotNull Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        populateDaysDataInView();
        int i = R.id.number_picker_date;
        ((WheelPicker) dialog.findViewById(i)).setData(this.f);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(3);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.l);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.femalewellness.viewmodel.d
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                WomenWellnessViewModel.i(WomenWellnessViewModel.this, wheelPicker, obj, i2);
            }
        });
    }
}
