package com.coveiot.android.leonardo.more.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.ItemMoveCallbackWorldClock;
import com.coveiot.android.leonardo.more.models.WorldClockData;
import java.util.ArrayList;
import java.util.Collections;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class WorldClockAdapter extends RecyclerView.Adapter<WorldClockViewHolder> implements ItemMoveCallbackWorldClock.ItemTouchHelperContract {
    @NotNull
    public final Context h;
    @NotNull
    public final ArrayList<WorldClockData> i;
    @NotNull
    public MenuClickListener j;
    @NotNull
    public final OnItemSwapListener k;

    /* loaded from: classes5.dex */
    public interface MenuClickListener {
        void onItemDeleteClick(int i);
    }

    /* loaded from: classes5.dex */
    public interface OnItemSwapListener {
        void onSwap();
    }

    /* loaded from: classes5.dex */
    public final class WorldClockViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public View f5099a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final ImageView d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WorldClockViewHolder(@NotNull WorldClockAdapter worldClockAdapter, View rowView) {
            super(rowView);
            Intrinsics.checkNotNullParameter(rowView, "rowView");
            this.f5099a = rowView;
            View findViewById = this.itemView.findViewById(R.id.countryName);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.countryName)");
            this.b = (TextView) findViewById;
            View findViewById2 = this.itemView.findViewById(R.id.hour_text);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.hour_text)");
            this.c = (TextView) findViewById2;
            View findViewById3 = this.itemView.findViewById(R.id.delete_worldClock_item_img);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.…lete_worldClock_item_img)");
            this.d = (ImageView) findViewById3;
        }

        @NotNull
        public final TextView getCountryName() {
            return this.b;
        }

        @NotNull
        public final ImageView getDelete_item() {
            return this.d;
        }

        @NotNull
        public final TextView getHours_tv() {
            return this.c;
        }

        @NotNull
        public final View getRowView() {
            return this.f5099a;
        }

        public final void setRowView(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.f5099a = view;
        }
    }

    public WorldClockAdapter(@NotNull Context context, @NotNull ArrayList<WorldClockData> data, @NotNull MenuClickListener listener, @NotNull OnItemSwapListener onItemSwapListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(onItemSwapListener, "onItemSwapListener");
        this.h = context;
        this.i = data;
        this.j = listener;
        this.k = onItemSwapListener;
    }

    public static final void b(WorldClockAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.j.onItemDeleteClick(i);
    }

    @NotNull
    public final Context getContext() {
        return this.h;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.i.size();
    }

    @NotNull
    public final MenuClickListener getListener() {
        return this.j;
    }

    @NotNull
    public final OnItemSwapListener getOnItemSwapListener() {
        return this.k;
    }

    @Override // com.coveiot.android.leonardo.more.ItemMoveCallbackWorldClock.ItemTouchHelperContract
    public void onRowClear(@Nullable WorldClockViewHolder worldClockViewHolder) {
        notifyDataSetChanged();
        this.k.onSwap();
    }

    @Override // com.coveiot.android.leonardo.more.ItemMoveCallbackWorldClock.ItemTouchHelperContract
    public void onRowMoved(int i, int i2) {
        if (i < i2) {
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                Collections.swap(this.i, i3, i4);
                i3 = i4;
            }
        } else {
            int i5 = i2 + 1;
            if (i5 <= i) {
                int i6 = i;
                while (true) {
                    Collections.swap(this.i, i6, i6 - 1);
                    if (i6 == i5) {
                        break;
                    }
                    i6--;
                }
            }
        }
        notifyItemMoved(i, i2);
    }

    @Override // com.coveiot.android.leonardo.more.ItemMoveCallbackWorldClock.ItemTouchHelperContract
    public void onRowSelected(@Nullable WorldClockViewHolder worldClockViewHolder) {
    }

    public final void setListener(@NotNull MenuClickListener menuClickListener) {
        Intrinsics.checkNotNullParameter(menuClickListener, "<set-?>");
        this.j = menuClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull WorldClockViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        TextView countryName = holder.getCountryName();
        if (countryName != null) {
            countryName.setText(this.i.get(i).getCountryName());
        }
        TextView hours_tv = holder.getHours_tv();
        if (hours_tv != null) {
            hours_tv.setText(this.i.get(i).getHoursDifference());
        }
        ImageView delete_item = holder.getDelete_item();
        if (delete_item != null) {
            delete_item.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.o0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WorldClockAdapter.b(WorldClockAdapter.this, i, view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public WorldClockViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.world_clock_adapter_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        return new WorldClockViewHolder(this, itemView);
    }
}
