package com.coveiot.android.customreminders.adapter;

import android.app.TimePickerDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.adapter.AdapterRemindMeAt;
import com.coveiot.sdk.ble.api.model.TimeInfo;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class AdapterRemindMeAt extends RecyclerView.Adapter<RemindMeAtHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<TimeInfo> f4131a = new ArrayList<>();
    public RemindAtListener listener;

    /* loaded from: classes3.dex */
    public interface RemindAtListener {
        void onTimeInfoUpdate(@NotNull ArrayList<TimeInfo> arrayList);
    }

    /* loaded from: classes3.dex */
    public final class RemindMeAtHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4132a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RemindMeAtHolder(@NotNull AdapterRemindMeAt adapterRemindMeAt, TextView itemView2) {
            super(itemView2);
            Intrinsics.checkNotNullParameter(itemView2, "itemView2");
            this.f4132a = itemView2;
        }

        @NotNull
        public final TextView getItemView2() {
            return this.f4132a;
        }
    }

    public static final void c(RemindMeAtHolder holder, final AdapterRemindMeAt this$0, final int i, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new TimePickerDialog(holder.itemView.getContext(), R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.customreminders.adapter.h
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i2, int i3) {
                AdapterRemindMeAt.d(AdapterRemindMeAt.this, i, timePicker, i2, i3);
            }
        }, Calendar.getInstance().get(11), Calendar.getInstance().get(12), DateFormat.is24HourFormat(holder.itemView.getContext())).show();
    }

    public static final void d(AdapterRemindMeAt this$0, int i, TimePicker timePicker, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.f4131a.size() > i) {
            this$0.f4131a.set(i, new TimeInfo(i2, i3));
        } else {
            this$0.f4131a.add(i, new TimeInfo(i2, i3));
        }
        if (this$0.listener != null) {
            this$0.getListener().onTimeInfoUpdate(this$0.f4131a);
        }
        this$0.notifyDataSetChanged();
    }

    public final void addTimeInfo(@NotNull TimeInfo timeInfo) {
        Intrinsics.checkNotNullParameter(timeInfo, "timeInfo");
        this.f4131a.add(timeInfo);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.f4131a.size() == 0) {
            return 1;
        }
        return this.f4131a.size();
    }

    @NotNull
    public final RemindAtListener getListener() {
        RemindAtListener remindAtListener = this.listener;
        if (remindAtListener != null) {
            return remindAtListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return null;
    }

    @NotNull
    public final ArrayList<TimeInfo> getTimeInfosList() {
        return this.f4131a;
    }

    public final void setListener(@NotNull RemindAtListener remindAtListener) {
        Intrinsics.checkNotNullParameter(remindAtListener, "<set-?>");
        this.listener = remindAtListener;
    }

    public final void setTimeInfosList(@NotNull ArrayList<TimeInfo> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f4131a = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final RemindMeAtHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.f4131a.size() > 0) {
            TimeInfo timeInfo = this.f4131a.get(i);
            Intrinsics.checkNotNullExpressionValue(timeInfo, "timeInfosList.get(position)");
            TimeInfo timeInfo2 = timeInfo;
            TextView itemView2 = holder.getItemView2();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(timeInfo2.getHour()), Integer.valueOf(timeInfo2.getMinute())}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            itemView2.setText(format);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdapterRemindMeAt.c(AdapterRemindMeAt.RemindMeAtHolder.this, this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RemindMeAtHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_info_list_item, parent, false);
        Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.TextView");
        return new RemindMeAtHolder(this, (TextView) inflate);
    }
}
