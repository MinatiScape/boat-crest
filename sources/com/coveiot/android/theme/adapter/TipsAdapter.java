package com.coveiot.android.theme.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.databinding.ListItemVitalTipsLayoutBinding;
import com.coveiot.android.theme.model.TipsModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TipsAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<TipsModel> f6087a = new ArrayList();

    /* loaded from: classes7.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemVitalTipsLayoutBinding f6088a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull TipsAdapter tipsAdapter, ListItemVitalTipsLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f6088a = binding;
        }

        public final void bind(@NotNull TipsModel reduceStressData) {
            Intrinsics.checkNotNullParameter(reduceStressData, "reduceStressData");
            ListItemVitalTipsLayoutBinding listItemVitalTipsLayoutBinding = this.f6088a;
            listItemVitalTipsLayoutBinding.setTipsData(reduceStressData);
            ImageView imageView = listItemVitalTipsLayoutBinding.ivStress;
            TipsModel tipsData = listItemVitalTipsLayoutBinding.getTipsData();
            Intrinsics.checkNotNull(tipsData);
            imageView.setImageResource(tipsData.getTipsImage());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f6087a.size();
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setTipsList(@NotNull List<TipsModel> tipsData) {
        Intrinsics.checkNotNullParameter(tipsData, "tipsData");
        this.f6087a = TypeIntrinsics.asMutableList(tipsData);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.f6087a.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemVitalTipsLayoutBinding inflate = ListItemVitalTipsLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
