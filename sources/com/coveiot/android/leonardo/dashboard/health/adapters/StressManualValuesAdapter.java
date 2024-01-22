package com.coveiot.android.leonardo.dashboard.health.adapters;

import android.content.Context;
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
public final class StressManualValuesAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4721a;
    @Nullable
    public final List<EntityManualData> b;

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4722a;
        @NotNull
        public final TextView b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull StressManualValuesAdapter stressManualValuesAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.stress_time);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.stress_time)");
            this.f4722a = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.stress_values);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.stress_values)");
            this.b = (TextView) findViewById2;
        }

        @NotNull
        public final TextView getStressTime() {
            return this.f4722a;
        }

        @NotNull
        public final TextView getStressValues() {
            return this.b;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public StressManualValuesAdapter(@NotNull Context context, @Nullable List<? extends EntityManualData> list) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4721a = context;
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
        return this.f4721a;
    }

    @Nullable
    public final List<EntityManualData> getHourlyStressList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<EntityManualData> list = this.b;
        Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
        Intrinsics.checkNotNull(valueOf);
        return valueOf.intValue();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        EntityManualData entityManualData;
        EntityManualData entityManualData2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        TextView stressTime = holder.getStressTime();
        List<EntityManualData> list = this.b;
        Integer num = null;
        stressTime.setText(formatDateToTime((list == null || (entityManualData2 = list.get(i)) == null) ? null : Long.valueOf(entityManualData2.getTimeStamp()), "hh:mm a").toString());
        TextView stressValues = holder.getStressValues();
        List<EntityManualData> list2 = this.b;
        if (list2 != null && (entityManualData = list2.get(i)) != null) {
            num = Integer.valueOf(entityManualData.getStress());
        }
        stressValues.setText(String.valueOf(num));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stress_list, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }
}
