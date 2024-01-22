package com.coveiot.android.leonardo.more.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.listeners.ScheduleListener;
import com.coveiot.android.leonardo.utils.EventType;
import com.coveiot.sdk.ble.api.model.ScheduleInfo;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ScheduleListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5077a;
    @Nullable
    public List<ScheduleInfo> b;
    @NotNull
    public final ScheduleListener c;
    @NotNull
    public final EditScheduleReminderListener d;
    @NotNull
    public final SimpleDateFormat e;

    /* loaded from: classes5.dex */
    public interface EditScheduleReminderListener {
        void editListener(@NotNull ScheduleInfo scheduleInfo, @NotNull EventType eventType, @NotNull List<ScheduleInfo> list);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5078a;
        @NotNull
        public final ImageView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final TextView e;
        @NotNull
        public final ConstraintLayout f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ScheduleListAdapter scheduleListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = (TextView) itemView.findViewById(R.id.titleTv);
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.titleTv");
            this.f5078a = textView;
            ImageView imageView = (ImageView) itemView.findViewById(R.id.deleteImgV);
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.deleteImgV");
            this.b = imageView;
            TextView textView2 = (TextView) itemView.findViewById(R.id.contentTv);
            Intrinsics.checkNotNullExpressionValue(textView2, "itemView.contentTv");
            this.c = textView2;
            TextView textView3 = (TextView) itemView.findViewById(R.id.dateTv);
            Intrinsics.checkNotNullExpressionValue(textView3, "itemView.dateTv");
            this.d = textView3;
            TextView textView4 = (TextView) itemView.findViewById(R.id.reminderBeforeTv);
            Intrinsics.checkNotNullExpressionValue(textView4, "itemView.reminderBeforeTv");
            this.e = textView4;
            ConstraintLayout constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.cvScheduleItem);
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "itemView.cvScheduleItem");
            this.f = constraintLayout;
        }

        @NotNull
        public final TextView getContentTv() {
            return this.c;
        }

        @NotNull
        public final ConstraintLayout getCvScheduleItem() {
            return this.f;
        }

        @NotNull
        public final TextView getDateTv() {
            return this.d;
        }

        @NotNull
        public final ImageView getDeleteImgV() {
            return this.b;
        }

        @NotNull
        public final TextView getReminderBeforeTv() {
            return this.e;
        }

        @NotNull
        public final TextView getTitleTv() {
            return this.f5078a;
        }
    }

    public ScheduleListAdapter(@NotNull Context mContext, @Nullable List<ScheduleInfo> list, @NotNull ScheduleListener listener, @NotNull EditScheduleReminderListener editScheduleReminderListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(editScheduleReminderListener, "editScheduleReminderListener");
        this.f5077a = mContext;
        this.b = list;
        this.c = listener;
        this.d = editScheduleReminderListener;
        this.e = new SimpleDateFormat("hh:mma, dd/MM/yy", Locale.ENGLISH);
    }

    public static final void c(ScheduleListAdapter this$0, ScheduleInfo scheduleInfo, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scheduleInfo, "$scheduleInfo");
        EditScheduleReminderListener editScheduleReminderListener = this$0.d;
        EventType eventType = EventType.EDIT;
        List<ScheduleInfo> list = this$0.b;
        Intrinsics.checkNotNull(list);
        editScheduleReminderListener.editListener(scheduleInfo, eventType, list);
    }

    public static final void d(ScheduleListAdapter this$0, ScheduleInfo scheduleInfo, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scheduleInfo, "$scheduleInfo");
        this$0.c.onDeleteSchedule(scheduleInfo);
        this$0.c.visibleTextview();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ScheduleInfo> list = this.b;
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @Nullable
    public final List<ScheduleInfo> getMScheduleInfos() {
        return this.b;
    }

    @NotNull
    public final SimpleDateFormat getSdf() {
        return this.e;
    }

    public final void setMScheduleInfos(@Nullable List<ScheduleInfo> list) {
        this.b = list;
    }

    public final void setScheduleInfo(@NotNull List<ScheduleInfo> scheduleInfos) {
        Intrinsics.checkNotNullParameter(scheduleInfos, "scheduleInfos");
        this.b = scheduleInfos;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<ScheduleInfo> list = this.b;
        Intrinsics.checkNotNull(list);
        final ScheduleInfo scheduleInfo = list.get(i);
        holder.getTitleTv().setText(scheduleInfo.getTitle());
        holder.getContentTv().setText(scheduleInfo.getContent());
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, scheduleInfo.getDay());
        calendar.set(1, scheduleInfo.getYear());
        calendar.set(2, scheduleInfo.getMonth());
        calendar.set(11, scheduleInfo.getHour());
        calendar.set(12, scheduleInfo.getMinute());
        holder.getDateTv().setText(this.e.format(Long.valueOf(calendar.getTimeInMillis())));
        scheduleInfo.getAdvance();
        if (scheduleInfo.getAdvance() == 0) {
            holder.getReminderBeforeTv().setText(this.f5077a.getResources().getString(R.string.at_the_time_of_event));
        } else if (scheduleInfo.getAdvance() < 60) {
            TextView reminderBeforeTv = holder.getReminderBeforeTv();
            reminderBeforeTv.setText(scheduleInfo.getAdvance() + ' ' + this.f5077a.getResources().getString(R.string.min_before));
        } else if (scheduleInfo.getAdvance() == 60) {
            TextView reminderBeforeTv2 = holder.getReminderBeforeTv();
            reminderBeforeTv2.setText((scheduleInfo.getAdvance() / 60) + ' ' + this.f5077a.getResources().getString(R.string.hour_before));
        } else if (scheduleInfo.getAdvance() > 60 && scheduleInfo.getAdvance() < 1440) {
            TextView reminderBeforeTv3 = holder.getReminderBeforeTv();
            reminderBeforeTv3.setText((scheduleInfo.getAdvance() / 60) + ' ' + this.f5077a.getResources().getString(R.string.hours_before));
        } else if (scheduleInfo.getAdvance() == 1440) {
            TextView reminderBeforeTv4 = holder.getReminderBeforeTv();
            reminderBeforeTv4.setText((scheduleInfo.getAdvance() / 1440) + ' ' + this.f5077a.getResources().getString(R.string.day_before));
        } else if (scheduleInfo.getAdvance() > 1440 && scheduleInfo.getAdvance() < 10080) {
            TextView reminderBeforeTv5 = holder.getReminderBeforeTv();
            reminderBeforeTv5.setText((scheduleInfo.getAdvance() / 1440) + ' ' + this.f5077a.getResources().getString(R.string.days_before));
        } else if (scheduleInfo.getAdvance() >= 10080) {
            TextView reminderBeforeTv6 = holder.getReminderBeforeTv();
            reminderBeforeTv6.setText((scheduleInfo.getAdvance() / 10080) + ' ' + this.f5077a.getResources().getString(R.string.week_before));
        }
        holder.getCvScheduleItem().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScheduleListAdapter.c(ScheduleListAdapter.this, scheduleInfo, view);
            }
        });
        holder.getDeleteImgV().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScheduleListAdapter.d(ScheduleListAdapter.this, scheduleInfo, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.f5077a).inflate(R.layout.schedule_list_row, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(mContext).inflate(R…_list_row, parent, false)");
        return new ViewHolder(this, inflate);
    }
}
