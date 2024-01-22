package com.coveiot.android.leonardo.dashboard.health.spo2.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import java.text.SimpleDateFormat;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SPO2ManualValuesAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4737a;
    @Nullable
    public final List<EntityManualData> b;

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4738a;
        @NotNull
        public final TextView b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SPO2ManualValuesAdapter sPO2ManualValuesAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.spo2_time);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.spo2_time)");
            this.f4738a = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.spo2_values);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.spo2_values)");
            this.b = (TextView) findViewById2;
        }

        @NotNull
        public final TextView getSpo2Time() {
            return this.f4738a;
        }

        @NotNull
        public final TextView getSpo2Values() {
            return this.b;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SPO2ManualValuesAdapter(@NotNull Context context, @Nullable List<? extends EntityManualData> list) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4737a = context;
        this.b = list;
    }

    @NotNull
    public final String formatDateToTime(@Nullable Long l, @NotNull String format) {
        Intrinsics.checkNotNullParameter(format, "format");
        String formattedDate = new SimpleDateFormat(format).format(l);
        Intrinsics.checkNotNullExpressionValue(formattedDate, "formattedDate");
        return formattedDate;
    }

    @NotNull
    public final Context getContext() {
        return this.f4737a;
    }

    @Nullable
    public final List<EntityManualData> getHourlySpo2List() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        StringBuilder sb = new StringBuilder();
        sb.append("check");
        List<EntityManualData> list = this.b;
        Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
        Intrinsics.checkNotNull(valueOf);
        sb.append(valueOf.intValue());
        Log.i("DEBUG", sb.toString());
        List<EntityManualData> list2 = this.b;
        Integer valueOf2 = list2 != null ? Integer.valueOf(list2.size()) : null;
        Intrinsics.checkNotNull(valueOf2);
        return valueOf2.intValue();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        EntityManualData entityManualData;
        EntityManualData entityManualData2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        TextView spo2Time = holder.getSpo2Time();
        List<EntityManualData> list = this.b;
        Integer num = null;
        spo2Time.setText(formatDateToTime((list == null || (entityManualData2 = list.get(i)) == null) ? null : Long.valueOf(entityManualData2.getTimeStamp()), "hh:mm a").toString());
        TextView spo2Values = holder.getSpo2Values();
        StringBuilder sb = new StringBuilder();
        List<EntityManualData> list2 = this.b;
        if (list2 != null && (entityManualData = list2.get(i)) != null) {
            num = Integer.valueOf((int) entityManualData.getSpo2());
        }
        sb.append(num);
        sb.append('%');
        spo2Values.setText(sb.toString());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Log.i("DEBUG", "check open");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spo2_list, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }
}
