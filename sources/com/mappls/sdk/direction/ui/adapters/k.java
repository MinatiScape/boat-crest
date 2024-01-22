package com.mappls.sdk.direction.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionItemTollRowLayoutBinding;
import com.mappls.sdk.services.api.costestimation.model.CostEstimationTollDetail;
import java.util.List;
/* loaded from: classes11.dex */
public final class k extends RecyclerView.Adapter<a> {

    /* renamed from: a  reason: collision with root package name */
    public final List<CostEstimationTollDetail> f12578a;

    /* loaded from: classes11.dex */
    public static class a extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final MapplsDirectionItemTollRowLayoutBinding f12579a;

        public a(@NonNull MapplsDirectionItemTollRowLayoutBinding mapplsDirectionItemTollRowLayoutBinding) {
            super(mapplsDirectionItemTollRowLayoutBinding.getRoot());
            this.f12579a = mapplsDirectionItemTollRowLayoutBinding;
        }
    }

    public k(List<CostEstimationTollDetail> list) {
        this.f12578a = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<CostEstimationTollDetail> list = this.f12578a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull a aVar, int i) {
        StringBuilder sb;
        a aVar2 = aVar;
        CostEstimationTollDetail costEstimationTollDetail = this.f12578a.get(i);
        aVar2.f12579a.textViewTollName.setText(costEstimationTollDetail.getTollName());
        Integer cost = costEstimationTollDetail.getCost();
        TextView textView = aVar2.f12579a.textViewTollInfo;
        if (cost != null) {
            sb = new StringBuilder("₹ ");
            sb.append(costEstimationTollDetail.getCost());
            sb.append(" • ");
        } else {
            sb = new StringBuilder("₹ NA • ");
        }
        sb.append(com.mappls.sdk.direction.ui.common.d.b(costEstimationTollDetail.getDistance().doubleValue()));
        sb.append(" • ETA:");
        sb.append(com.mappls.sdk.direction.ui.common.d.a(costEstimationTollDetail.getDuration().doubleValue()));
        textView.setText(sb.toString());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new a((MapplsDirectionItemTollRowLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.mappls_direction_item_toll_row_layout, viewGroup, false));
    }
}
