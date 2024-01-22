package com.coveiot.android.leonardo.dashboard.health.spo2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.SPO2ListItem;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SPO2MethodInfoAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4739a;
    @NotNull
    public final List<SPO2ListItem> b;
    @Nullable
    public ItemClickListener c;

    /* loaded from: classes3.dex */
    public interface ItemClickListener {
        void onItemClick(@NotNull String str);
    }

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4740a;
        @NotNull
        public final TextView b;
        @NotNull
        public final ImageView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SPO2MethodInfoAdapter sPO2MethodInfoAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.spo2_method_info_tv);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.spo2_method_info_tv)");
            this.f4740a = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.spo2_method_info_desc_tv);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.spo2_method_info_desc_tv)");
            this.b = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.spo2_method_info_img_v);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.spo2_method_info_img_v)");
            this.c = (ImageView) findViewById3;
        }

        @NotNull
        public final TextView getSpo2MethodInfoDescTv() {
            return this.b;
        }

        @NotNull
        public final ImageView getSpo2MethodInfoImgV() {
            return this.c;
        }

        @NotNull
        public final TextView getSpo2MethodInfoTitleTv() {
            return this.f4740a;
        }
    }

    public SPO2MethodInfoAdapter(@NotNull Context context, @NotNull List<SPO2ListItem> items) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(items, "items");
        this.f4739a = context;
        this.b = items;
    }

    @NotNull
    public final Context getContext() {
        return this.f4739a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public final void setItemClickListener(@Nullable ItemClickListener itemClickListener) {
        this.c = itemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        viewHolder.getSpo2MethodInfoTitleTv().setText(this.b.get(i).getName());
        viewHolder.getSpo2MethodInfoDescTv().setText(this.b.get(i).getDescription());
        viewHolder.getSpo2MethodInfoImgV().setImageResource(this.b.get(i).getDrawableResId());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_sp02_method_info, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }
}
