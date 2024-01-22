package com.coveiot.android.customreminders.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.listeners.ReminderListener;
import com.coveiot.android.customreminders.model.DrinkWaterReminder;
import com.coveiot.android.customreminders.model.HandWashReminder;
import com.coveiot.android.customreminders.model.RepeatModel;
import com.coveiot.android.customreminders.utils.CustomReminderConstants;
import com.coveiot.sdk.ble.api.model.TimeInfo;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class HandWashReminderViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4189a;
    @NotNull
    public MutableLiveData<Boolean> b;
    @Nullable
    public TimeInfo c;
    @Nullable
    public TimeInfo d;
    public int e;
    @NotNull
    public MutableLiveData<String> f;
    @NotNull
    public MutableLiveData<String> g;
    @NotNull
    public MutableLiveData<String> h;
    @NotNull
    public MutableLiveData<RepeatModel> i;
    @NotNull
    public RepeatModel j;
    public ReminderType mReminderType;

    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReminderType.values().length];
            try {
                iArr[ReminderType.HAND_WASH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReminderType.DRINK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public HandWashReminderViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4189a = context;
        this.b = new MutableLiveData<>();
        this.e = 60;
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.i = new MutableLiveData<>(new RepeatModel());
        this.j = new RepeatModel();
    }

    public static /* synthetic */ void setEndTime$default(HandWashReminderViewModel handWashReminderViewModel, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            z = true;
        }
        handWashReminderViewModel.setEndTime(i, i2, z);
    }

    public static /* synthetic */ void setReminderFrequency$default(HandWashReminderViewModel handWashReminderViewModel, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        handWashReminderViewModel.setReminderFrequency(i, z);
    }

    public static /* synthetic */ void setRepeat$default(HandWashReminderViewModel handWashReminderViewModel, RepeatModel repeatModel, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        handWashReminderViewModel.setRepeat(repeatModel, z);
    }

    public static /* synthetic */ void setStartTime$default(HandWashReminderViewModel handWashReminderViewModel, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            z = true;
        }
        handWashReminderViewModel.setStartTime(i, i2, z);
    }

    @NotNull
    public final Context getContext() {
        return this.f4189a;
    }

    @NotNull
    public final MutableLiveData<Boolean> getDoneBtnVisablityLiveData() {
        return this.b;
    }

    @NotNull
    public final MutableLiveData<String> getEndTimeLiveData() {
        return this.h;
    }

    @Nullable
    public final TimeInfo getMEndTime() {
        return this.d;
    }

    public final int getMReminderFrequency() {
        return this.e;
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

    @Nullable
    public final TimeInfo getMStartTime() {
        return this.c;
    }

    @NotNull
    public final MutableLiveData<String> getReminderFrequencyLiveData() {
        return this.g;
    }

    @NotNull
    public final MutableLiveData<RepeatModel> getRepeatLiveData() {
        return this.i;
    }

    @NotNull
    public final RepeatModel getRepeatModel() {
        return this.j;
    }

    @NotNull
    public final MutableLiveData<String> getStartTimeLiveData() {
        return this.f;
    }

    public final void isDoneButtonVisible() {
        this.b.setValue(Boolean.valueOf(this.c != null));
    }

    public final void save(@NotNull ReminderListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.d != null) {
            TimeInfo timeInfo = this.c;
            Integer valueOf = timeInfo != null ? Integer.valueOf(timeInfo.getTimeInMinutes()) : null;
            Intrinsics.checkNotNull(valueOf);
            int intValue = valueOf.intValue();
            TimeInfo timeInfo2 = this.d;
            Intrinsics.checkNotNull(timeInfo2);
            if (intValue > timeInfo2.getTimeInMinutes()) {
                String string = this.f4189a.getString(R.string.start_time_end_time_validation);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…time_end_time_validation)");
                listener.onError(string);
                return;
            }
        }
        int i = WhenMappings.$EnumSwitchMapping$0[getMReminderType().ordinal()];
        if (i == 1) {
            listener.onSuccess(new HandWashReminder(this.j, CustomReminderConstants.Companion.getHAND_WASH(), true, this.c, this.d, this.e));
        } else if (i != 2) {
        } else {
            listener.onSuccess(new DrinkWaterReminder(this.j, CustomReminderConstants.Companion.getDRINK_WATER(), true, this.c, this.d, this.e));
        }
    }

    public final void setDoneBtnVisablityLiveData(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setEndTime(int i, int i2, boolean z) {
        String format;
        this.d = new TimeInfo(i, i2);
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
        this.h.setValue(format);
        if (z) {
            isDoneButtonVisible();
        }
    }

    public final void setEndTimeLiveData(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }

    public final void setMEndTime(@Nullable TimeInfo timeInfo) {
        this.d = timeInfo;
    }

    public final void setMReminderFrequency(int i) {
        this.e = i;
    }

    public final void setMReminderType(@NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "<set-?>");
        this.mReminderType = reminderType;
    }

    public final void setMStartTime(@Nullable TimeInfo timeInfo) {
        this.c = timeInfo;
    }

    public final void setReminderFrequency(int i, boolean z) {
        this.e = i;
        MutableLiveData<String> mutableLiveData = this.g;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i / 60)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        mutableLiveData.setValue(format);
        if (z) {
            isDoneButtonVisible();
        }
    }

    public final void setReminderFrequencyLiveData(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setReminderType(@NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        setMReminderType(reminderType);
    }

    public final void setRepeat(@NotNull RepeatModel repeatModel, boolean z) {
        Intrinsics.checkNotNullParameter(repeatModel, "repeatModel");
        this.j = repeatModel;
        this.i.setValue(repeatModel);
        if (z) {
            isDoneButtonVisible();
        }
    }

    public final void setRepeatLiveData(@NotNull MutableLiveData<RepeatModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.i = mutableLiveData;
    }

    public final void setRepeatModel(@NotNull RepeatModel repeatModel) {
        Intrinsics.checkNotNullParameter(repeatModel, "<set-?>");
        this.j = repeatModel;
    }

    public final void setStartTime(int i, int i2, boolean z) {
        String format;
        this.c = new TimeInfo(i, i2);
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
        this.f.setValue(format);
        if (z) {
            isDoneButtonVisible();
        }
    }

    public final void setStartTimeLiveData(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }
}
