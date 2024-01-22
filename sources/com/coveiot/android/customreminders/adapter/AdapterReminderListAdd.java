package com.coveiot.android.customreminders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.adapter.AdapterReminderListAdd;
import com.coveiot.android.customreminders.listeners.EditReminderListListener;
import com.coveiot.android.customreminders.model.CustomReminder;
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
public final class AdapterReminderListAdd extends RecyclerView.Adapter<ReminderViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4133a;
    @NotNull
    public final EditReminderListListener b;
    public List<? extends CustomReminder> reminderList;

    /* loaded from: classes3.dex */
    public final class ReminderViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public ImageView f4134a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final ImageView e;
        @NotNull
        public final ImageView f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReminderViewHolder(@NotNull AdapterReminderListAdd adapterReminderListAdd, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.reminder_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.reminder_icon)");
            this.f4134a = (ImageView) findViewById;
            View findViewById2 = view.findViewById(R.id.reminder_name);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.reminder_name)");
            this.b = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.tv_remind_at);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.tv_remind_at)");
            this.c = (TextView) findViewById3;
            View findViewById4 = view.findViewById(R.id.reminder_repeat_value);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "view.findViewById(R.id.reminder_repeat_value)");
            this.d = (TextView) findViewById4;
            View findViewById5 = view.findViewById(R.id.iv_reminder_delete);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "view.findViewById(R.id.iv_reminder_delete)");
            ImageView imageView = (ImageView) findViewById5;
            this.e = imageView;
            View findViewById6 = view.findViewById(R.id.iv_reminder_edit);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "view.findViewById(R.id.iv_reminder_edit)");
            ImageView imageView2 = (ImageView) findViewById6;
            this.f = imageView2;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.l
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AdapterReminderListAdd.ReminderViewHolder.c(view2);
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.m
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AdapterReminderListAdd.ReminderViewHolder.d(view2);
                }
            });
        }

        public static final void c(View view) {
        }

        public static final void d(View view) {
        }

        @NotNull
        public final TextView getRemindAt() {
            return this.c;
        }

        @NotNull
        public final ImageView getReminderDeleteIcon() {
            return this.e;
        }

        @NotNull
        public final ImageView getReminderEditIcon() {
            return this.f;
        }

        @NotNull
        public final ImageView getReminderIcon() {
            return this.f4134a;
        }

        @NotNull
        public final TextView getReminderName() {
            return this.b;
        }

        @NotNull
        public final TextView getReminderRepeat() {
            return this.d;
        }

        public final void setReminderIcon(@NotNull ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.f4134a = imageView;
        }
    }

    public AdapterReminderListAdd(@NotNull Context context, @NotNull EditReminderListListener editReminderListListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(editReminderListListener, "editReminderListListener");
        this.f4133a = context;
        this.b = editReminderListListener;
    }

    public static final void c(AdapterReminderListAdd this$0, int i, Ref.ObjectRef reminderType, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(reminderType, "$reminderType");
        this$0.b.onDeleteReminder(i, (ReminderType) reminderType.element);
    }

    public static final void d(AdapterReminderListAdd this$0, int i, Ref.ObjectRef reminderType, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(reminderType, "$reminderType");
        this$0.b.onEditReminder(i, (ReminderType) reminderType.element);
    }

    @NotNull
    public final Context getContext() {
        return this.f4133a;
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
    public final List<CustomReminder> getReminderList() {
        List list = this.reminderList;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("reminderList");
        return null;
    }

    public final void setReminderList(@NotNull List<? extends CustomReminder> list) {
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

    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.coveiot.android.customreminders.ReminderType] */
    /* JADX WARN: Type inference failed for: r2v4, types: [T, com.coveiot.android.customreminders.ReminderType] */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, com.coveiot.android.customreminders.ReminderType] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ReminderViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        CustomReminder customReminder = getReminderList().get(i);
        holder.getReminderName().setText(customReminder.getDescription());
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? r2 = ReminderType.MEDICINE;
        objectRef.element = r2;
        if (customReminder instanceof MedicineReminder) {
            objectRef.element = r2;
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
        }
        if (customReminder.getRepeatModel().isAllSelected()) {
            holder.getReminderRepeat().setText(this.f4133a.getString(R.string.everyday));
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
                holder.getReminderRepeat().setText(this.f4133a.getString(R.string.once));
            } else {
                holder.getReminderRepeat().setText(str4);
            }
        }
        holder.getReminderDeleteIcon().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdapterReminderListAdd.c(AdapterReminderListAdd.this, i, objectRef, view);
            }
        });
        holder.getReminderEditIcon().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdapterReminderListAdd.d(AdapterReminderListAdd.this, i, objectRef, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ReminderViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item_layout_sub, parent, false);
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        return new ReminderViewHolder(this, itemView);
    }
}
