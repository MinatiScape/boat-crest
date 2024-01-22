package com.coveiot.android.customreminders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.listeners.EditReminderListListener;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.CustomReminderDataHolder;
import com.coveiot.android.customreminders.model.DrinkWaterReminder;
import com.coveiot.android.customreminders.model.HandWashReminder;
import com.coveiot.android.customreminders.model.MedicineReminder;
import com.coveiot.android.customreminders.model.MeetingReminder;
import com.coveiot.android.customreminders.model.OtherReminder;
import com.coveiot.sdk.ble.api.model.TimeInfo;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class AdapterReminderSubListMain extends RecyclerView.Adapter<ReminderViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4139a;
    @NotNull
    public final EditReminderListListener b;
    public List<CustomReminderDataHolder> reminderList;

    /* loaded from: classes3.dex */
    public final class ReminderViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public View f4140a;
        @NotNull
        public ImageView b;
        @NotNull
        public Switch c;
        @NotNull
        public final TextView d;
        @NotNull
        public final TextView e;
        @NotNull
        public final TextView f;
        @NotNull
        public final ImageView g;
        @NotNull
        public final ImageView h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReminderViewHolder(@NotNull AdapterReminderSubListMain adapterReminderSubListMain, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.reminder_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.reminder_icon)");
            this.b = (ImageView) findViewById;
            View findViewById2 = view.findViewById(R.id.cl_root_layout);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.cl_root_layout)");
            this.f4140a = findViewById2;
            View findViewById3 = view.findViewById(R.id.reminder_switch);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.reminder_switch)");
            this.c = (Switch) findViewById3;
            View findViewById4 = view.findViewById(R.id.reminder_name);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "view.findViewById(R.id.reminder_name)");
            this.d = (TextView) findViewById4;
            View findViewById5 = view.findViewById(R.id.tv_remind_at);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "view.findViewById(R.id.tv_remind_at)");
            this.e = (TextView) findViewById5;
            View findViewById6 = view.findViewById(R.id.reminder_repeat_value);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "view.findViewById(R.id.reminder_repeat_value)");
            this.f = (TextView) findViewById6;
            View findViewById7 = view.findViewById(R.id.iv_reminder_delete);
            Intrinsics.checkNotNullExpressionValue(findViewById7, "view.findViewById(R.id.iv_reminder_delete)");
            this.g = (ImageView) findViewById7;
            View findViewById8 = view.findViewById(R.id.iv_reminder_edit);
            Intrinsics.checkNotNullExpressionValue(findViewById8, "view.findViewById(R.id.iv_reminder_edit)");
            this.h = (ImageView) findViewById8;
        }

        @NotNull
        public final TextView getRemindAt() {
            return this.e;
        }

        @NotNull
        public final ImageView getReminderDeleteIcon() {
            return this.g;
        }

        @NotNull
        public final ImageView getReminderEditIcon() {
            return this.h;
        }

        @NotNull
        public final ImageView getReminderIcon() {
            return this.b;
        }

        @NotNull
        public final TextView getReminderName() {
            return this.d;
        }

        @NotNull
        public final TextView getReminderRepeat() {
            return this.f;
        }

        @NotNull
        public final Switch getReminderSwitch() {
            return this.c;
        }

        @NotNull
        public final View getRootView() {
            return this.f4140a;
        }

        public final void setReminderIcon(@NotNull ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.b = imageView;
        }

        public final void setReminderSwitch(@NotNull Switch r2) {
            Intrinsics.checkNotNullParameter(r2, "<set-?>");
            this.c = r2;
        }

        public final void setRootView(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.f4140a = view;
        }
    }

    public AdapterReminderSubListMain(@NotNull Context context, @NotNull EditReminderListListener editReminderListListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(editReminderListListener, "editReminderListListener");
        this.f4139a = context;
        this.b = editReminderListListener;
    }

    public static final void e(AdapterReminderSubListMain this$0, int i, Ref.ObjectRef reminderType, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(reminderType, "$reminderType");
        this$0.b.onDeleteReminder(i, (ReminderType) reminderType.element);
    }

    public static final void f(AdapterReminderSubListMain this$0, int i, Ref.ObjectRef reminderType, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(reminderType, "$reminderType");
        this$0.b.onEditReminder(i, (ReminderType) reminderType.element);
    }

    public static final void g(AdapterReminderSubListMain this$0, int i, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b.onReminderStatusChange(i, z);
    }

    public final CharSequence d(CustomReminder customReminder) {
        if (customReminder instanceof HandWashReminder) {
            HandWashReminder handWashReminder = (HandWashReminder) customReminder;
            int frequency = handWashReminder.getFrequency();
            if (frequency != 30) {
                if (frequency != 60) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("Every %d hours", Arrays.copyOf(new Object[]{Integer.valueOf(handWashReminder.getFrequency() / 60)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    return format;
                }
                return "Every hour";
            }
            return "Every 30 minutes";
        } else if (customReminder instanceof DrinkWaterReminder) {
            DrinkWaterReminder drinkWaterReminder = (DrinkWaterReminder) customReminder;
            int frequency2 = drinkWaterReminder.getFrequency();
            if (frequency2 != 30) {
                if (frequency2 != 60) {
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String format2 = String.format("Every %d hours", Arrays.copyOf(new Object[]{Integer.valueOf(drinkWaterReminder.getFrequency() / 60)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                    return format2;
                }
                return "Every hour";
            }
            return "Every 30 minutes";
        } else {
            return null;
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f4139a;
    }

    @NotNull
    public final EditReminderListListener getEditReminderListListener() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getReminderList().size();
    }

    @NotNull
    public final List<CustomReminderDataHolder> getReminderList() {
        List<CustomReminderDataHolder> list = this.reminderList;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("reminderList");
        return null;
    }

    public final void setReminderList(@NotNull List<CustomReminderDataHolder> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.reminderList = list;
    }

    @NotNull
    public final String toFormattedString(@NotNull TimeInfo timeInfo) {
        String str;
        Intrinsics.checkNotNullParameter(timeInfo, "<this>");
        int hour = timeInfo.getHour();
        if (timeInfo.getHour() > 12) {
            hour = timeInfo.getHour() - 12;
            str = "PM";
        } else {
            str = "AM";
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%02d:%02d %s", Arrays.copyOf(new Object[]{Integer.valueOf(hour), Integer.valueOf(timeInfo.getMinute()), str}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    /* JADX WARN: Type inference failed for: r1v11, types: [T, com.coveiot.android.customreminders.ReminderType] */
    /* JADX WARN: Type inference failed for: r1v15, types: [T, com.coveiot.android.customreminders.ReminderType] */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.coveiot.android.customreminders.ReminderType] */
    /* JADX WARN: Type inference failed for: r1v7, types: [T, com.coveiot.android.customreminders.ReminderType] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, com.coveiot.android.customreminders.ReminderType] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ReminderViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        CustomReminder customReminder = getReminderList().get(i).getCustomReminder();
        final int position = getReminderList().get(i).getPosition();
        boolean z = customReminder instanceof DrinkWaterReminder;
        if (z) {
            holder.getReminderName().setText(this.f4139a.getString(R.string.please_drink_water));
        } else if (customReminder instanceof HandWashReminder) {
            holder.getReminderName().setText(this.f4139a.getString(R.string.please_wash_hands));
        } else {
            holder.getReminderName().setText(customReminder.getDescription());
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? r3 = ReminderType.MEDICINE;
        objectRef.element = r3;
        if (customReminder instanceof MedicineReminder) {
            objectRef.element = r3;
            MedicineReminder medicineReminder = (MedicineReminder) customReminder;
            int size = medicineReminder.getTimeInfos().size();
            String str = "";
            for (int i2 = 0; i2 < size; i2++) {
                TimeInfo timeInfo = medicineReminder.getTimeInfos().get(i2);
                if (i2 < medicineReminder.getTimeInfos().size() - 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    Intrinsics.checkNotNullExpressionValue(timeInfo, "timeInfo");
                    sb.append(toFormattedString(timeInfo));
                    sb.append(',');
                    str = sb.toString();
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    Intrinsics.checkNotNullExpressionValue(timeInfo, "timeInfo");
                    sb2.append(toFormattedString(timeInfo));
                    str = sb2.toString();
                }
            }
            holder.getRemindAt().setText(str);
        } else if (customReminder instanceof MeetingReminder) {
            objectRef.element = ReminderType.MEETING;
            MeetingReminder meetingReminder = (MeetingReminder) customReminder;
            int size2 = meetingReminder.getTimeInfos().size();
            String str2 = "";
            for (int i3 = 0; i3 < size2; i3++) {
                TimeInfo timeInfo2 = meetingReminder.getTimeInfos().get(i3);
                if (i3 < meetingReminder.getTimeInfos().size() - 1) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str2);
                    Intrinsics.checkNotNullExpressionValue(timeInfo2, "timeInfo");
                    sb3.append(toFormattedString(timeInfo2));
                    sb3.append(',');
                    str2 = sb3.toString();
                } else {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(str2);
                    Intrinsics.checkNotNullExpressionValue(timeInfo2, "timeInfo");
                    sb4.append(toFormattedString(timeInfo2));
                    str2 = sb4.toString();
                }
            }
            holder.getRemindAt().setText(str2);
        } else if (customReminder instanceof OtherReminder) {
            objectRef.element = ReminderType.OTHERS;
            OtherReminder otherReminder = (OtherReminder) customReminder;
            int size3 = otherReminder.getTimeInfos().size();
            String str3 = "";
            for (int i4 = 0; i4 < size3; i4++) {
                TimeInfo timeInfo3 = otherReminder.getTimeInfos().get(i4);
                if (i4 < otherReminder.getTimeInfos().size() - 1) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(str3);
                    Intrinsics.checkNotNullExpressionValue(timeInfo3, "timeInfo");
                    sb5.append(toFormattedString(timeInfo3));
                    sb5.append(',');
                    str3 = sb5.toString();
                } else {
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(str3);
                    Intrinsics.checkNotNullExpressionValue(timeInfo3, "timeInfo");
                    sb6.append(toFormattedString(timeInfo3));
                    str3 = sb6.toString();
                }
            }
            holder.getRemindAt().setText(str3);
        } else if (customReminder instanceof HandWashReminder) {
            objectRef.element = ReminderType.HAND_WASH;
            CharSequence d = d(customReminder);
            if (d != null) {
                holder.getRemindAt().setText(d);
                holder.getRemindAt().setVisibility(0);
            } else {
                holder.getRemindAt().setVisibility(4);
            }
        } else if (z) {
            objectRef.element = ReminderType.DRINK;
            holder.getRemindAt().setVisibility(4);
            CharSequence d2 = d(customReminder);
            if (d2 != null) {
                holder.getRemindAt().setText(d2);
                holder.getRemindAt().setVisibility(0);
            } else {
                holder.getRemindAt().setVisibility(4);
            }
        }
        if (customReminder.getRepeatModel().isAllSelected()) {
            holder.getReminderRepeat().setText(this.f4139a.getString(R.string.everyday));
        } else {
            String str4 = customReminder.getRepeatModel().monday ? "Mon, " : "";
            if (customReminder.getRepeatModel().tuesday) {
                str4 = str4 + "Tue, ";
            }
            if (customReminder.getRepeatModel().wednesday) {
                str4 = str4 + "Wed, ";
            }
            if (customReminder.getRepeatModel().thursday) {
                str4 = str4 + "Thu, ";
            }
            if (customReminder.getRepeatModel().friday) {
                str4 = str4 + "Fri, ";
            }
            if (customReminder.getRepeatModel().saturday) {
                str4 = str4 + "Sat, ";
            }
            if (customReminder.getRepeatModel().sunday) {
                str4 = str4 + "Sun";
            }
            if (str4.length() == 0) {
                holder.getReminderRepeat().setText(this.f4139a.getString(R.string.once));
            } else {
                holder.getReminderRepeat().setText(str4);
            }
        }
        holder.getReminderDeleteIcon().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdapterReminderSubListMain.e(AdapterReminderSubListMain.this, position, objectRef, view);
            }
        });
        holder.getReminderEditIcon().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdapterReminderSubListMain.f(AdapterReminderSubListMain.this, position, objectRef, view);
            }
        });
        holder.getReminderSwitch().setChecked(customReminder.isReminderOn());
        holder.getReminderSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.customreminders.adapter.q
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                AdapterReminderSubListMain.g(AdapterReminderSubListMain.this, position, compoundButton, z2);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ReminderViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item_layout_main_new, parent, false);
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        return new ReminderViewHolder(this, itemView);
    }
}
