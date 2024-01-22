package com.coveiot.android.leonardo.more.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.models.EventReminder;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.listeners.EventReminderListener;
import com.coveiot.android.leonardo.utils.EventType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class EventReminderListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5058a;
    @Nullable
    public List<EventReminder> b;
    @NotNull
    public final EventReminderListener c;
    @NotNull
    public final EditEventReminderListener d;
    @NotNull
    public final SimpleDateFormat e;

    /* loaded from: classes5.dex */
    public interface EditEventReminderListener {
        void editListener(@NotNull EventReminder eventReminder, @NotNull EventType eventType, @NotNull List<EventReminder> list);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5059a;
        @NotNull
        public final ImageView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final ConstraintLayout e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull EventReminderListAdapter eventReminderListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = (TextView) itemView.findViewById(R.id.titleTv);
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.titleTv");
            this.f5059a = textView;
            ImageView imageView = (ImageView) itemView.findViewById(R.id.deleteImgV);
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.deleteImgV");
            this.b = imageView;
            TextView textView2 = (TextView) itemView.findViewById(R.id.dateTv);
            Intrinsics.checkNotNullExpressionValue(textView2, "itemView.dateTv");
            this.c = textView2;
            TextView textView3 = (TextView) itemView.findViewById(R.id.repeatTv);
            Intrinsics.checkNotNullExpressionValue(textView3, "itemView.repeatTv");
            this.d = textView3;
            ConstraintLayout constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.cvScheduleItem);
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "itemView.cvScheduleItem");
            this.e = constraintLayout;
        }

        @NotNull
        public final ConstraintLayout getCvScheduleItem() {
            return this.e;
        }

        @NotNull
        public final TextView getDateTv() {
            return this.c;
        }

        @NotNull
        public final ImageView getDeleteImgV() {
            return this.b;
        }

        @NotNull
        public final TextView getRepeatTv() {
            return this.d;
        }

        @NotNull
        public final TextView getTitleTv() {
            return this.f5059a;
        }
    }

    public EventReminderListAdapter(@NotNull Context mContext, @Nullable List<EventReminder> list, @NotNull EventReminderListener listener, @NotNull EditEventReminderListener editEventReminderListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(editEventReminderListener, "editEventReminderListener");
        this.f5058a = mContext;
        this.b = list;
        this.c = listener;
        this.d = editEventReminderListener;
        this.e = new SimpleDateFormat("hh:mma, dd/MM/yy", Locale.ENGLISH);
    }

    public static final void c(EventReminderListAdapter this$0, EventReminder eventReminder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(eventReminder, "$eventReminder");
        EditEventReminderListener editEventReminderListener = this$0.d;
        EventType eventType = EventType.EDIT;
        List<EventReminder> list = this$0.b;
        Intrinsics.checkNotNull(list);
        editEventReminderListener.editListener(eventReminder, eventType, list);
    }

    public static final void d(EventReminderListAdapter this$0, EventReminder eventReminder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(eventReminder, "$eventReminder");
        this$0.c.onDeleteEventReminder(eventReminder);
        this$0.c.visibleTextview();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<EventReminder> list = this.b;
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @Nullable
    public final List<EventReminder> getMEventRemindersList() {
        return this.b;
    }

    @NotNull
    public final SimpleDateFormat getSdf() {
        return this.e;
    }

    public final void setEventReminder(@NotNull List<EventReminder> scheduleInfos) {
        Intrinsics.checkNotNullParameter(scheduleInfos, "scheduleInfos");
        this.b = scheduleInfos;
        Log.d("setEventReminder: ", String.valueOf(scheduleInfos));
        notifyDataSetChanged();
    }

    public final void setMEventRemindersList(@Nullable List<EventReminder> list) {
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<EventReminder> list = this.b;
        Intrinsics.checkNotNull(list);
        final EventReminder eventReminder = list.get(i);
        holder.getTitleTv().setText(eventReminder.getEventName());
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, eventReminder.getDay());
        calendar.set(1, eventReminder.getYear());
        calendar.set(2, eventReminder.getMonth());
        calendar.set(11, eventReminder.getHour());
        calendar.set(12, eventReminder.getMinute());
        holder.getDateTv().setText(this.e.format(Long.valueOf(calendar.getTimeInMillis())));
        eventReminder.getRepeatType();
        int repeatType = eventReminder.getRepeatType();
        if (repeatType == 0) {
            holder.getRepeatTv().setText(this.f5058a.getResources().getString(R.string.once));
        } else if (repeatType == 1) {
            holder.getRepeatTv().setText(this.f5058a.getResources().getString(R.string.daily));
        } else if (repeatType == 2) {
            holder.getRepeatTv().setText(this.f5058a.getResources().getString(R.string.every_week));
        } else if (repeatType == 3) {
            holder.getRepeatTv().setText(this.f5058a.getResources().getString(R.string.monthly));
        } else if (repeatType == 4) {
            holder.getRepeatTv().setText(this.f5058a.getResources().getString(R.string.yearly));
        }
        holder.getCvScheduleItem().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EventReminderListAdapter.c(EventReminderListAdapter.this, eventReminder, view);
            }
        });
        holder.getDeleteImgV().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EventReminderListAdapter.d(EventReminderListAdapter.this, eventReminder, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.f5058a).inflate(R.layout.event_list_row, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(mContext).inflate(R…_list_row, parent, false)");
        return new ViewHolder(this, inflate);
    }
}
