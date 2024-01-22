package com.coveiot.android.leonardo.sensai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.SensaiCompareItemBinding;
import com.coveiot.android.leonardo.sensai.model.AddToCompareData;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class SensAICompareAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5361a;
    @NotNull
    public final List<AddToCompareData> b;

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5362a;
        @NotNull
        public final TextView b;
        @NotNull
        public final ImageView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final TextView e;
        @NotNull
        public final ImageView f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SensAICompareAdapter sensAICompareAdapter, SensaiCompareItemBinding itemView) {
            super(itemView.getRoot());
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = itemView.tvCompareTitle1;
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.tvCompareTitle1");
            this.f5362a = textView;
            TextView textView2 = itemView.tvCompareDetails1;
            Intrinsics.checkNotNullExpressionValue(textView2, "itemView.tvCompareDetails1");
            this.b = textView2;
            ImageView imageView = itemView.ivCompare1;
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.ivCompare1");
            this.c = imageView;
            TextView textView3 = itemView.tvCompareTitle2;
            Intrinsics.checkNotNullExpressionValue(textView3, "itemView.tvCompareTitle2");
            this.d = textView3;
            TextView textView4 = itemView.tvCompareDetails2;
            Intrinsics.checkNotNullExpressionValue(textView4, "itemView.tvCompareDetails2");
            this.e = textView4;
            ImageView imageView2 = itemView.ivCompare2;
            Intrinsics.checkNotNullExpressionValue(imageView2, "itemView.ivCompare2");
            this.f = imageView2;
        }

        @NotNull
        public final ImageView getIvCompare1() {
            return this.c;
        }

        @NotNull
        public final ImageView getIvCompare2() {
            return this.f;
        }

        @NotNull
        public final TextView getTvCompareDetails1() {
            return this.b;
        }

        @NotNull
        public final TextView getTvCompareDetails2() {
            return this.e;
        }

        @NotNull
        public final TextView getTvCompareTitle1() {
            return this.f5362a;
        }

        @NotNull
        public final TextView getTvCompareTitle2() {
            return this.d;
        }
    }

    public SensAICompareAdapter(@NotNull Context mContext, @NotNull List<AddToCompareData> addToCompareDataList) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(addToCompareDataList, "addToCompareDataList");
        this.f5361a = mContext;
        this.b = addToCompareDataList;
    }

    @NotNull
    public final List<AddToCompareData> getAddToCompareDataList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvCompareTitle1().setText(this.b.get(i).getName1());
        holder.getTvCompareDetails1().setText(this.b.get(i).getValue1());
        Glide.with(this.f5361a).m28load(this.b.get(i).getImage1()).into(holder.getIvCompare1());
        holder.getTvCompareTitle2().setText(this.b.get(i).getName2());
        holder.getTvCompareDetails2().setText(this.b.get(i).getValue2());
        Glide.with(this.f5361a).m28load(this.b.get(i).getImage2()).into(holder.getIvCompare2());
        if (holder.getTvCompareTitle1().getText().equals(this.f5361a.getString(R.string.goals_achieved))) {
            if (holder.getTvCompareDetails1().getText().equals("0 %")) {
                holder.getTvCompareDetails1().setText("NA");
            }
            if (holder.getTvCompareDetails2().getText().equals("0 %")) {
                holder.getTvCompareDetails2().setText("NA");
            }
        }
        if (Intrinsics.areEqual(this.b.get(i).isHighlight1(), Boolean.TRUE)) {
            holder.getTvCompareDetails1().setTextColor(this.f5361a.getColor(R.color.color_7dd221));
            holder.getTvCompareDetails2().setTextColor(this.f5361a.getColor(R.color.main_text_color));
        } else {
            holder.getTvCompareDetails1().setTextColor(this.f5361a.getColor(R.color.main_text_color));
            holder.getTvCompareDetails2().setTextColor(this.f5361a.getColor(R.color.color_7dd221));
        }
        if (holder.getTvCompareDetails1().getText().equals(holder.getTvCompareDetails2().getText())) {
            holder.getTvCompareDetails1().setTextColor(this.f5361a.getColor(R.color.main_text_color));
            holder.getTvCompareDetails2().setTextColor(this.f5361a.getColor(R.color.main_text_color));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SensaiCompareItemBinding inflate = SensaiCompareItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
