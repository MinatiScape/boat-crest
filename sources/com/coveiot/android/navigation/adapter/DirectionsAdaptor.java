package com.coveiot.android.navigation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.databinding.ItemLayoutDirectionsBinding;
import com.coveiot.android.navigation.models.DirectionsData;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class DirectionsAdaptor extends RecyclerView.Adapter<DirectionViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<DirectionsData> f5497a;

    /* loaded from: classes5.dex */
    public final class DirectionViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ItemLayoutDirectionsBinding f5498a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DirectionViewHolder(@NotNull DirectionsAdaptor directionsAdaptor, ItemLayoutDirectionsBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5498a = binding;
        }

        @NotNull
        public final ItemLayoutDirectionsBinding getBinding() {
            return this.f5498a;
        }
    }

    public DirectionsAdaptor(@NotNull ArrayList<DirectionsData> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.f5497a = data;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5497a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull DirectionViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getBinding().tvDirectionAdvice.setText(this.f5497a.get(i).getInstruction());
        holder.getBinding().tvTurnDistance.setText(this.f5497a.get(i).getDistance());
        holder.getBinding().ivDirectionImage.setImageDrawable(this.f5497a.get(i).getManevurImage());
        if (i == this.f5497a.size() - 1) {
            holder.getBinding().vLine.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public DirectionViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemLayoutDirectionsBinding inflate = ItemLayoutDirectionsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new DirectionViewHolder(this, inflate);
    }
}
