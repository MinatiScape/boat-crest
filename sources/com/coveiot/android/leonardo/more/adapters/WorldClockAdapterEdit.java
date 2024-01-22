package com.coveiot.android.leonardo.more.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.models.WorldClockData;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class WorldClockAdapterEdit extends RecyclerView.Adapter<WorldClockViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5100a;
    @NotNull
    public final ArrayList<WorldClockData> b;
    @NotNull
    public final OnItemClickListener c;
    @NotNull
    public final OnItemSwapListener d;

    /* loaded from: classes5.dex */
    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    /* loaded from: classes5.dex */
    public interface OnItemSwapListener {
        void onSwap();
    }

    /* loaded from: classes5.dex */
    public final class WorldClockViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public View f5101a;
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
        public WorldClockViewHolder(@NotNull WorldClockAdapterEdit worldClockAdapterEdit, View rowView) {
            super(rowView);
            Intrinsics.checkNotNullParameter(rowView, "rowView");
            this.f5101a = rowView;
            View findViewById = this.itemView.findViewById(R.id.countryName);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.countryName)");
            this.b = (TextView) findViewById;
            View findViewById2 = this.itemView.findViewById(R.id.hour_text);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.hour_text)");
            this.c = (TextView) findViewById2;
            View findViewById3 = this.itemView.findViewById(R.id.time_text);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.time_text)");
            this.d = (TextView) findViewById3;
            View findViewById4 = this.itemView.findViewById(R.id.delete_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.delete_icon)");
            this.e = (ImageView) findViewById4;
            View findViewById5 = this.itemView.findViewById(R.id.swap_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.swap_icon)");
            this.f = (ImageView) findViewById5;
        }

        @NotNull
        public final TextView getCountryName() {
            return this.b;
        }

        @NotNull
        public final ImageView getDelete_icon() {
            return this.e;
        }

        @NotNull
        public final TextView getHours_tv() {
            return this.c;
        }

        @NotNull
        public final View getRowView() {
            return this.f5101a;
        }

        @NotNull
        public final ImageView getSwap_icon() {
            return this.f;
        }

        @NotNull
        public final TextView getTime_text() {
            return this.d;
        }

        public final void setRowView(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.f5101a = view;
        }
    }

    public WorldClockAdapterEdit(@NotNull Context context, @NotNull ArrayList<WorldClockData> data, @NotNull OnItemClickListener onItemClickListener, @NotNull OnItemSwapListener onItemSwapListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        Intrinsics.checkNotNullParameter(onItemSwapListener, "onItemSwapListener");
        this.f5100a = context;
        this.b = data;
        this.c = onItemClickListener;
        this.d = onItemSwapListener;
    }

    public static final void b(WorldClockAdapterEdit this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.onItemClick(i);
    }

    @NotNull
    public final Context getContext() {
        return this.f5100a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.c;
    }

    @NotNull
    public final OnItemSwapListener getOnItemSwapListener() {
        return this.d;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull WorldClockViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getDelete_icon().setVisibility(0);
        holder.getSwap_icon().setVisibility(0);
        TextView countryName = holder.getCountryName();
        if (countryName != null) {
            countryName.setText(this.b.get(i).getCountryName());
        }
        TextView hours_tv = holder.getHours_tv();
        if (hours_tv != null) {
            hours_tv.setText(this.b.get(i).getHoursDifference());
        }
        TextView time_text = holder.getTime_text();
        if (time_text != null) {
            time_text.setText(this.b.get(i).getTime());
        }
        ImageView delete_icon = holder.getDelete_icon();
        if (delete_icon != null) {
            delete_icon.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.p0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WorldClockAdapterEdit.b(WorldClockAdapterEdit.this, i, view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public WorldClockViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.world_clock_adapter_item_edit, parent, false);
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        return new WorldClockViewHolder(this, itemView);
    }
}
