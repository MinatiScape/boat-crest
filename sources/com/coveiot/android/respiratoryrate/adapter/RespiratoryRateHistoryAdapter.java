package com.coveiot.android.respiratoryrate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.respiratoryrate.R;
import com.coveiot.android.respiratoryrate.databinding.RespiratoryRateHistoryListItemBinding;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateListBean;
import com.coveiot.android.respiratoryrate.utils.Constants;
import com.coveiot.android.respiratoryrate.utils.Utils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class RespiratoryRateHistoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5667a;
    @NotNull
    public List<RespiratoryRateListBean> b;

    /* loaded from: classes6.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5668a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull RespiratoryRateHistoryAdapter respiratoryRateHistoryAdapter, RespiratoryRateHistoryListItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            TextView textView = binding.tvHistoryItemDate;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvHistoryItemDate");
            this.f5668a = textView;
            TextView textView2 = binding.tvHistoryItemMin;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvHistoryItemMin");
            this.b = textView2;
            TextView textView3 = binding.tvHistoryItemMax;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvHistoryItemMax");
            this.c = textView3;
        }

        @NotNull
        public final TextView getTvDate() {
            return this.f5668a;
        }

        @NotNull
        public final TextView getTvMax() {
            return this.c;
        }

        @NotNull
        public final TextView getTvMin() {
            return this.b;
        }
    }

    public RespiratoryRateHistoryAdapter(@NotNull Context context, @NotNull List<RespiratoryRateListBean> listData) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listData, "listData");
        this.f5667a = context;
        this.b = listData;
    }

    @NotNull
    public final Context getContext() {
        return this.f5667a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    @NotNull
    public final List<RespiratoryRateListBean> getListData() {
        return this.b;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5667a = context;
    }

    public final void setListData(@NotNull List<RespiratoryRateListBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Integer max;
        Integer min;
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvDate().setText(Utils.getDayMonthYearFormatDate(this.b.get(i).getDate()));
        TextView tvMin = holder.getTvMin();
        StringBuilder sb = new StringBuilder();
        sb.append((this.b.get(i).getMin() == null || ((min = this.b.get(i).getMin()) != null && min.intValue() == 0)) ? Constants.EMPTY_RESPIRATORY_RATE.getValue() : this.b.get(i).getMin());
        sb.append(' ');
        Context context = this.f5667a;
        int i2 = R.string.brpm;
        sb.append(context.getString(i2));
        tvMin.setText(sb.toString());
        TextView tvMax = holder.getTvMax();
        StringBuilder sb2 = new StringBuilder();
        sb2.append((this.b.get(i).getMax() == null || ((max = this.b.get(i).getMax()) != null && max.intValue() == 0)) ? Constants.EMPTY_RESPIRATORY_RATE.getValue() : this.b.get(i).getMax());
        sb2.append(' ');
        sb2.append(this.f5667a.getString(i2));
        tvMax.setText(sb2.toString());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        RespiratoryRateHistoryListItemBinding inflate = RespiratoryRateHistoryListItemBinding.inflate(LayoutInflater.from(this.f5667a), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n            Lay…          false\n        )");
        return new ViewHolder(this, inflate);
    }
}
