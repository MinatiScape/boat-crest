package com.coveiot.android.leonardo.sensai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.SensaiShareCardItemBinding;
import com.coveiot.android.leonardo.sensai.model.SensAIShareList;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class SensAIShareCardAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<SensAIShareList> f5377a;

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5378a;
        @NotNull
        public final TextView b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SensAIShareCardAdapter sensAIShareCardAdapter, SensaiShareCardItemBinding itemView) {
            super(itemView.getRoot());
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = itemView.itemValText;
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.itemValText");
            this.f5378a = textView;
            TextView textView2 = itemView.itemTitleText;
            Intrinsics.checkNotNullExpressionValue(textView2, "itemView.itemTitleText");
            this.b = textView2;
        }

        @NotNull
        public final TextView getTitle() {
            return this.b;
        }

        @NotNull
        public final TextView getValue() {
            return this.f5378a;
        }
    }

    public SensAIShareCardAdapter(@NotNull Context mContext, @NotNull ArrayList<SensAIShareList> sensAIShareList) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(sensAIShareList, "sensAIShareList");
        this.f5377a = sensAIShareList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5377a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTitle().setText(this.f5377a.get(i).getName());
        holder.getValue().setText(this.f5377a.get(i).getValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SensaiShareCardItemBinding inflate = SensaiShareCardItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
