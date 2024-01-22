package com.coveiot.android.customreminders.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.listeners.ResultListener;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.DrinkWaterReminder;
import com.coveiot.android.customreminders.model.HandWashReminder;
import com.coveiot.android.customreminders.model.MedicineReminder;
import com.coveiot.android.customreminders.model.MeetingReminder;
import com.coveiot.android.customreminders.model.OtherReminder;
import com.coveiot.android.customreminders.preference.PreferenceManagerCustomReminders;
import com.coveiot.android.customreminders.utils.CustomReminderConstants;
import com.coveiot.android.customreminders.utils.ReminderHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AddCustomReminderViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4188a;
    @NotNull
    public final MutableLiveData<ArrayList<MedicineReminder>> b;
    @NotNull
    public final MutableLiveData<ArrayList<MeetingReminder>> c;
    public ReminderType currentReminderType;
    @NotNull
    public final MutableLiveData<ArrayList<OtherReminder>> d;
    @Nullable
    public DrinkWaterReminder e;
    @Nullable
    public HandWashReminder f;

    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReminderType.values().length];
            try {
                iArr[ReminderType.MEDICINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReminderType.MEETING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReminderType.OTHERS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReminderType.DRINK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ReminderType.HAND_WASH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AddCustomReminderViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4188a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
    }

    public static /* synthetic */ boolean canAddMoreReminders$default(AddCustomReminderViewModel addCustomReminderViewModel, ReminderType reminderType, int i, Object obj) {
        if ((i & 1) != 0) {
            reminderType = addCustomReminderViewModel.getCurrentReminderType();
        }
        return addCustomReminderViewModel.canAddMoreReminders(reminderType);
    }

    public final int a(ReminderType reminderType) {
        ArrayList<MeetingReminder> value;
        if (reminderType == ReminderType.MEDICINE) {
            ArrayList<MedicineReminder> value2 = this.b.getValue();
            if (value2 != null) {
                return value2.size();
            }
            return 0;
        } else if (reminderType == ReminderType.OTHERS) {
            ArrayList<OtherReminder> value3 = this.d.getValue();
            if (value3 != null) {
                return value3.size();
            }
            return 0;
        } else if (reminderType != ReminderType.MEETING || (value = this.c.getValue()) == null) {
            return 0;
        } else {
            return value.size();
        }
    }

    public final int b(ReminderType reminderType) {
        int i = WhenMappings.$EnumSwitchMapping$0[reminderType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            return 1;
                        }
                        return CustomReminderConstants.Companion.getHAND_WASH_REMINDER_MAX_LIMIT();
                    }
                    return CustomReminderConstants.Companion.getDRINK_REMINDER_MAX_LIMIT();
                }
                return CustomReminderConstants.Companion.getOTHER_REMINDER_MAX_LIMIT();
            }
            return CustomReminderConstants.Companion.getMEETING_REMINDER_MAX_LIMIT();
        }
        return CustomReminderConstants.Companion.getMEDICINE_REMINDER_MAX_LIMIT();
    }

    public final boolean canAddMoreReminders(@NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        return ReminderHelper.Companion.getInstance(this.f4188a).getReminderCountInPreference(reminderType) + a(reminderType) < b(reminderType);
    }

    @NotNull
    public final Context getContext() {
        return this.f4188a;
    }

    @NotNull
    public final ReminderType getCurrentReminderType() {
        ReminderType reminderType = this.currentReminderType;
        if (reminderType != null) {
            return reminderType;
        }
        Intrinsics.throwUninitializedPropertyAccessException("currentReminderType");
        return null;
    }

    @Nullable
    public final DrinkWaterReminder getDrinkReminder() {
        return this.e;
    }

    @Nullable
    public final HandWashReminder getHandWashReminder() {
        return this.f;
    }

    @NotNull
    public final MutableLiveData<ArrayList<MedicineReminder>> getLiveDataMedicineRemindersList() {
        return this.b;
    }

    @NotNull
    public final MutableLiveData<ArrayList<MeetingReminder>> getLiveDataMeetingRemindersList() {
        return this.c;
    }

    @NotNull
    public final MutableLiveData<ArrayList<OtherReminder>> getLiveDataOtherRemindersList() {
        return this.d;
    }

    public final boolean isCurrentReminderInitialized() {
        return this.currentReminderType != null;
    }

    public final void removeReminderAt(int i, @NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        if (reminderType == ReminderType.MEDICINE) {
            ArrayList<MedicineReminder> value = this.b.getValue();
            if (value != null) {
                value.remove(i);
            }
            MutableLiveData<ArrayList<MedicineReminder>> mutableLiveData = this.b;
            Intrinsics.checkNotNull(value);
            mutableLiveData.setValue(value);
        } else if (reminderType == ReminderType.MEETING) {
            ArrayList<MeetingReminder> value2 = this.c.getValue();
            if (value2 != null) {
                value2.remove(i);
            }
            MutableLiveData<ArrayList<MeetingReminder>> mutableLiveData2 = this.c;
            Intrinsics.checkNotNull(value2);
            mutableLiveData2.setValue(value2);
        } else if (reminderType == ReminderType.OTHERS) {
            ArrayList<OtherReminder> value3 = this.d.getValue();
            if (value3 != null) {
                value3.remove(i);
            }
            MutableLiveData<ArrayList<OtherReminder>> mutableLiveData3 = this.d;
            Intrinsics.checkNotNull(value3);
            mutableLiveData3.setValue(value3);
        }
    }

    public final void saveReminders(@NotNull ResultListener resultListener) {
        Intrinsics.checkNotNullParameter(resultListener, "resultListener");
        List<CustomReminder> reminders = PreferenceManagerCustomReminders.Companion.getReminders(this.f4188a);
        if (reminders == null) {
            reminders = new ArrayList<>();
        }
        ArrayList<CustomReminder> arrayList = (ArrayList) reminders;
        ArrayList<MedicineReminder> value = this.b.getValue();
        if (value != null) {
            arrayList.addAll(value);
        }
        ArrayList<MeetingReminder> value2 = this.c.getValue();
        if (value2 != null) {
            arrayList.addAll(value2);
        }
        ArrayList<OtherReminder> value3 = this.d.getValue();
        if (value3 != null) {
            arrayList.addAll(value3);
        }
        HandWashReminder handWashReminder = this.f;
        if (handWashReminder != null) {
            Intrinsics.checkNotNull(handWashReminder);
            arrayList.add(handWashReminder);
        }
        DrinkWaterReminder drinkWaterReminder = this.e;
        if (drinkWaterReminder != null) {
            Intrinsics.checkNotNull(drinkWaterReminder);
            arrayList.add(drinkWaterReminder);
        }
        ReminderHelper.Companion.getInstance(this.f4188a).sendRemindersToWatch(arrayList, resultListener);
    }

    public final void setCurrentReminderType(@NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "<set-?>");
        this.currentReminderType = reminderType;
    }

    public final void setDrinkReminder(@Nullable DrinkWaterReminder drinkWaterReminder) {
        this.e = drinkWaterReminder;
    }

    public final void setHandWashReminder(@Nullable HandWashReminder handWashReminder) {
        this.f = handWashReminder;
    }
}
