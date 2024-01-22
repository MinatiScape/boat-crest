package com.coveiot.android.customreminders.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.listeners.ReminderListener;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.MedicineReminder;
import com.coveiot.android.customreminders.model.MeetingReminder;
import com.coveiot.android.customreminders.model.OtherReminder;
import com.coveiot.android.customreminders.model.RepeatModel;
import com.coveiot.sdk.ble.api.model.TimeInfo;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MedicineReminderViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4190a;
    @Nullable
    public Calendar b;
    @NotNull
    public MutableLiveData<Boolean> c;
    @NotNull
    public MutableLiveData<String> d;
    @NotNull
    public MutableLiveData<String> e;
    @NotNull
    public ArrayList<TimeInfo> f;
    @NotNull
    public MutableLiveData<Pair<Integer, String>> g;
    @NotNull
    public MutableLiveData<RepeatModel> h;
    @NotNull
    public RepeatModel i;
    public int j;
    @NotNull
    public MutableLiveData<String> k;
    @Nullable
    public String l;
    @Nullable
    public CustomReminder m;
    public ReminderType mReminderType;
    public Calendar mStartDate;

    public MedicineReminderViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4190a = context;
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.f = new ArrayList<>(3);
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>(new RepeatModel());
        this.i = new RepeatModel();
        this.j = 5;
        this.k = new MutableLiveData<>();
        this.f = new ArrayList<>(3);
    }

    public static /* synthetic */ void removeTimeAt$default(MedicineReminderViewModel medicineReminderViewModel, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        medicineReminderViewModel.removeTimeAt(i, z);
    }

    public static /* synthetic */ void setEndDate$default(MedicineReminderViewModel medicineReminderViewModel, Calendar calendar, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        medicineReminderViewModel.setEndDate(calendar, z);
    }

    public static /* synthetic */ void setReminderInAdvance$default(MedicineReminderViewModel medicineReminderViewModel, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        medicineReminderViewModel.setReminderInAdvance(i, z);
    }

    public static /* synthetic */ void setReminderName$default(MedicineReminderViewModel medicineReminderViewModel, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        medicineReminderViewModel.setReminderName(str, z);
    }

    public static /* synthetic */ void setRepeat$default(MedicineReminderViewModel medicineReminderViewModel, RepeatModel repeatModel, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        medicineReminderViewModel.setRepeat(repeatModel, z);
    }

    public static /* synthetic */ void setStartDate$default(MedicineReminderViewModel medicineReminderViewModel, Calendar calendar, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        medicineReminderViewModel.setStartDate(calendar, z);
    }

    public static /* synthetic */ void setTime$default(MedicineReminderViewModel medicineReminderViewModel, int i, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            z = true;
        }
        medicineReminderViewModel.setTime(i, i2, i3, z);
    }

    @NotNull
    public final Context getContext() {
        return this.f4190a;
    }

    @Nullable
    public final CustomReminder getCustomReminder() {
        return this.m;
    }

    @NotNull
    public final MutableLiveData<Boolean> getDoneBtnVisablityLiveData() {
        return this.c;
    }

    @NotNull
    public final MutableLiveData<String> getEndDateLiveData() {
        return this.e;
    }

    @Nullable
    public final Calendar getMEndDate() {
        return this.b;
    }

    @Nullable
    public final String getMReminderName() {
        return this.l;
    }

    @NotNull
    public final ReminderType getMReminderType() {
        ReminderType reminderType = this.mReminderType;
        if (reminderType != null) {
            return reminderType;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mReminderType");
        return null;
    }

    @NotNull
    public final Calendar getMStartDate() {
        Calendar calendar = this.mStartDate;
        if (calendar != null) {
            return calendar;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mStartDate");
        return null;
    }

    public final int getRemindInAdvance() {
        return this.j;
    }

    @NotNull
    public final MutableLiveData<String> getRemindInAdvanceLiveData() {
        return this.k;
    }

    @NotNull
    public final MutableLiveData<RepeatModel> getRepeatLiveData() {
        return this.h;
    }

    @NotNull
    public final RepeatModel getRepeatModel() {
        return this.i;
    }

    @NotNull
    public final MutableLiveData<String> getStartDateLiveData() {
        return this.d;
    }

    @NotNull
    public final ArrayList<TimeInfo> getTimeInfoArrayList() {
        return this.f;
    }

    @NotNull
    public final MutableLiveData<Pair<Integer, String>> getTimeInfoLiveData() {
        return this.g;
    }

    public final boolean h() {
        boolean z = false;
        if (!AppUtils.isEmpty(this.f)) {
            Iterator<TimeInfo> it = this.f.iterator();
            while (it.hasNext()) {
                if (it.next() != null) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final void isDoneButtonVisible() {
        this.c.setValue(Boolean.valueOf(!AppUtils.isEmpty(this.l) && this.mStartDate != null && h() && this.j >= 0 && this.i.isRepeatEnabled()));
    }

    public final void removeTimeAt(int i, boolean z) {
        if (i < this.f.size()) {
            this.f.remove(i);
            this.g.setValue(new Pair<>(Integer.valueOf(i), ""));
        } else {
            this.g.setValue(new Pair<>(Integer.valueOf(i), ""));
        }
        if (z) {
            isDoneButtonVisible();
        }
    }

    public final void save(@NotNull ReminderListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (getMReminderType() == ReminderType.MEDICINE) {
            CustomReminder customReminder = this.m;
            Intrinsics.checkNotNull(customReminder, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.MedicineReminder");
            listener.onSuccess((MedicineReminder) customReminder);
        } else if (getMReminderType() == ReminderType.MEETING) {
            CustomReminder customReminder2 = this.m;
            Intrinsics.checkNotNull(customReminder2, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.MeetingReminder");
            listener.onSuccess((MeetingReminder) customReminder2);
        } else if (getMReminderType() == ReminderType.OTHERS) {
            CustomReminder customReminder3 = this.m;
            Intrinsics.checkNotNull(customReminder3, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.OtherReminder");
            listener.onSuccess((OtherReminder) customReminder3);
        }
    }

    public final void setCustomReminder(@Nullable CustomReminder customReminder) {
        this.m = customReminder;
    }

    public final void setDoneBtnVisablityLiveData(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setEndDate(@NotNull Calendar endDate, boolean z) {
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        this.b = endDate;
        this.e.setValue(AppUtils.formatDate(endDate != null ? endDate.getTime() : null, "dd/MM/yyyy"));
        if (z) {
            isDoneButtonVisible();
        }
    }

    public final void setEndDateLiveData(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setMEndDate(@Nullable Calendar calendar) {
        this.b = calendar;
    }

    public final void setMReminderName(@Nullable String str) {
        this.l = str;
    }

    public final void setMReminderType(@NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "<set-?>");
        this.mReminderType = reminderType;
    }

    public final void setMStartDate(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "<set-?>");
        this.mStartDate = calendar;
    }

    public final void setRemindInAdvance(int i) {
        this.j = i;
    }

    public final void setRemindInAdvanceLiveData(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.k = mutableLiveData;
    }

    public final void setReminderInAdvance(int i, boolean z) {
        this.j = i;
        if (i == 0) {
            this.k.setValue("On time");
        } else {
            MutableLiveData<String> mutableLiveData = this.k;
            mutableLiveData.setValue(i + " min Before");
        }
        if (z) {
            isDoneButtonVisible();
        }
    }

    public final void setReminderName(@Nullable String str, boolean z) {
        this.l = str;
        if (z) {
            isDoneButtonVisible();
        }
    }

    public final void setReminderType(@NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        setMReminderType(reminderType);
    }

    public final void setRepeat(@NotNull RepeatModel repeatModel, boolean z) {
        Intrinsics.checkNotNullParameter(repeatModel, "repeatModel");
        this.i = repeatModel;
        this.h.setValue(repeatModel);
        if (z) {
            isDoneButtonVisible();
        }
    }

    public final void setRepeatLiveData(@NotNull MutableLiveData<RepeatModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }

    public final void setRepeatModel(@NotNull RepeatModel repeatModel) {
        Intrinsics.checkNotNullParameter(repeatModel, "<set-?>");
        this.i = repeatModel;
    }

    public final void setStartDate(@NotNull Calendar startDate, boolean z) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        setMStartDate(startDate);
        this.d.setValue(AppUtils.formatDate(getMStartDate().getTime(), "dd/MM/yyyy"));
        if (z) {
            isDoneButtonVisible();
        }
    }

    public final void setStartDateLiveData(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setTime(int i, int i2, int i3, boolean z) {
        String format;
        if (i3 <= this.f.size() - 1) {
            this.f.set(i3, new TimeInfo(i, i2));
        } else {
            this.f.add(new TimeInfo(i, i2));
        }
        if (i < 12) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            format = String.format("%02d:%02d AM", Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        } else if (i == 12) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            format = String.format("%02d:%02d PM", Arrays.copyOf(new Object[]{12, Integer.valueOf(i2)}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        } else {
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            format = String.format("%02d:%02d PM", Arrays.copyOf(new Object[]{Integer.valueOf(i - 12), Integer.valueOf(i2)}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        }
        this.g.setValue(new Pair<>(Integer.valueOf(i3), format));
        if (z) {
            isDoneButtonVisible();
        }
    }

    public final void setTimeInfoArrayList(@NotNull ArrayList<TimeInfo> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f = arrayList;
    }

    public final void setTimeInfoLiveData(@NotNull MutableLiveData<Pair<Integer, String>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }
}
