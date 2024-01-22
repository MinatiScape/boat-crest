package com.coveiot.android.leonardo.sensai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.ItemSensAiCompareHeaderBinding;
import com.coveiot.android.leonardo.sensai.model.SensAICompareData;
import com.coveiot.android.leonardo.sensai.model.SensAICompareListItem;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class SensAICompareHeaderAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5363a;
    @NotNull
    public final List<SensAICompareData> b;

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ItemSensAiCompareHeaderBinding f5364a;
        public final /* synthetic */ SensAICompareHeaderAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SensAICompareHeaderAdapter sensAICompareHeaderAdapter, ItemSensAiCompareHeaderBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = sensAICompareHeaderAdapter;
            this.f5364a = binding;
        }

        public final void bind(@NotNull SensAICompareData compareDataItem, boolean z) {
            Intrinsics.checkNotNullParameter(compareDataItem, "compareDataItem");
            ItemSensAiCompareHeaderBinding itemSensAiCompareHeaderBinding = this.f5364a;
            SensAICompareHeaderAdapter sensAICompareHeaderAdapter = this.b;
            itemSensAiCompareHeaderBinding.setCompareData(compareDataItem);
            itemSensAiCompareHeaderBinding.rvCompare.setLayoutManager(new LinearLayoutManager(sensAICompareHeaderAdapter.f5363a));
            ArrayList<SensAICompareListItem> compareList = compareDataItem.getCompareList();
            itemSensAiCompareHeaderBinding.rvCompare.setAdapter(compareList != null ? new SensAICompareListAdapter(sensAICompareHeaderAdapter.f5363a, compareList) : null);
            if (z) {
                itemSensAiCompareHeaderBinding.listItemDivider.setVisibility(8);
            } else {
                itemSensAiCompareHeaderBinding.listItemDivider.setVisibility(0);
            }
        }
    }

    public SensAICompareHeaderAdapter(@NotNull Context mContext, @NotNull List<SensAICompareData> compareList) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(compareList, "compareList");
        this.f5363a = mContext;
        this.b = compareList;
    }

    @NotNull
    public final List<SensAICompareData> getCompareList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        SensAICompareData sensAICompareData = this.b.get(i);
        List<SensAICompareData> list = this.b;
        Intrinsics.checkNotNull(list);
        holder.bind(sensAICompareData, CollectionsKt__CollectionsKt.getLastIndex(list) == i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSensAiCompareHeaderBinding inflate = ItemSensAiCompareHeaderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
