package com.coveiot.android.leonardo.onboarding.goal.goalPicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class PickerAdapter extends RecyclerView.Adapter<PickerItemViewHolder> {
    @Nullable
    public Callback b;
    public boolean e;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<String> f5211a = new ArrayList<>();
    @NotNull
    public final View.OnClickListener c = new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.goal.goalPicker.a
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            PickerAdapter.b(PickerAdapter.this, view);
        }
    };
    public int d = -1;

    /* loaded from: classes5.dex */
    public interface Callback {
        void onItemClicked(@NotNull View view);
    }

    /* loaded from: classes5.dex */
    public static final class PickerItemViewHolder extends RecyclerView.ViewHolder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5212a;
        @Nullable
        public final ImageView b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PickerItemViewHolder(@Nullable View view) {
            super(view);
            Intrinsics.checkNotNull(view);
            this.f5212a = (TextView) view.findViewById(R.id.tv_item);
            this.b = (ImageView) view.findViewById(R.id.ivSelectedPointer);
        }

        @Nullable
        public final ImageView getIvSelectedPointer() {
            return this.b;
        }

        @Nullable
        public final TextView getTvItem() {
            return this.f5212a;
        }
    }

    public static final void b(PickerAdapter this$0, View view) {
        Callback callback;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (view == null || (callback = this$0.b) == null) {
            return;
        }
        callback.onItemClicked(view);
    }

    @Nullable
    public final Callback getCallback() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5211a.size();
    }

    public final void setCallback(@Nullable Callback callback) {
        this.b = callback;
    }

    public final void setData(@NotNull ArrayList<String> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.f5211a.clear();
        this.f5211a.addAll(data);
        notifyDataSetChanged();
    }

    public final void setIfSleepData(boolean z) {
        this.e = z;
        notifyDataSetChanged();
    }

    public final void setSelectedItem(int i, boolean z) {
        this.d = i;
        if (z) {
            this.d = i - 1;
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull PickerItemViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.e) {
            TextView tvItem = holder.getTvItem();
            if (tvItem != null) {
                tvItem.setText(this.f5211a.get(i));
            }
            String str = this.f5211a.get(i);
            Intrinsics.checkNotNullExpressionValue(str, "data[position]");
            if (Integer.parseInt(str) < 10) {
                TextView tvItem2 = holder.getTvItem();
                if (tvItem2 != null) {
                    tvItem2.setText('0' + this.f5211a.get(i) + ":00");
                }
            } else {
                TextView tvItem3 = holder.getTvItem();
                if (tvItem3 != null) {
                    tvItem3.setText(this.f5211a.get(i) + ":00");
                }
            }
        } else {
            TextView tvItem4 = holder.getTvItem();
            if (tvItem4 != null) {
                tvItem4.setText(this.f5211a.get(i));
            }
        }
        if (this.d == i) {
            TextView tvItem5 = holder.getTvItem();
            if (tvItem5 != null) {
                Context context = tvItem5.getContext();
                Intrinsics.checkNotNull(context);
                tvItem5.setTextColor(ContextCompat.getColor(context, R.color.white));
                tvItem5.setTypeface(holder.getTvItem().getTypeface(), 1);
                tvItem5.setTextSize(2, 32.0f);
            }
            ImageView ivSelectedPointer = holder.getIvSelectedPointer();
            if (ivSelectedPointer != null) {
                ivSelectedPointer.setBackgroundResource(R.drawable.goal_selected_pointer);
            }
            this.d = -1;
            return;
        }
        TextView tvItem6 = holder.getTvItem();
        if (tvItem6 != null) {
            Context context2 = tvItem6.getContext();
            Intrinsics.checkNotNull(context2);
            tvItem6.setTextColor(ContextCompat.getColor(context2, R.color.color_666666));
            tvItem6.setTypeface(holder.getTvItem().getTypeface(), 0);
            tvItem6.setTextSize(2, 18.0f);
        }
        ImageView ivSelectedPointer2 = holder.getIvSelectedPointer();
        if (ivSelectedPointer2 != null) {
            ivSelectedPointer2.setBackgroundResource(R.drawable.goal_unselected_pointer);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public PickerItemViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_goal_picker_layout, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …er_layout, parent, false)");
        inflate.setOnClickListener(this.c);
        return new PickerItemViewHolder(inflate);
    }
}
