package com.coveiot.android.leonardo.more.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.ProfileFitnessSubheaderItemBinding;
import com.coveiot.android.leonardo.more.models.FitnessBuddiesSubModel;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ProfileFitnessSubHeaderAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<FitnessBuddiesSubModel> f5068a;

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ProfileFitnessSubheaderItemBinding f5069a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ProfileFitnessSubHeaderAdapter profileFitnessSubHeaderAdapter, ProfileFitnessSubheaderItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5069a = binding;
        }

        public final void bind(@NotNull FitnessBuddiesSubModel fitnessSubModelItem, boolean z) {
            Intrinsics.checkNotNullParameter(fitnessSubModelItem, "fitnessSubModelItem");
            this.f5069a.setFitnessData(fitnessSubModelItem);
            if (z) {
                this.f5069a.listItemDivider.setVisibility(8);
            } else {
                this.f5069a.listItemDivider.setVisibility(0);
            }
        }
    }

    public ProfileFitnessSubHeaderAdapter(@NotNull List<FitnessBuddiesSubModel> fitnessBuddiesSubHeaderList) {
        Intrinsics.checkNotNullParameter(fitnessBuddiesSubHeaderList, "fitnessBuddiesSubHeaderList");
        this.f5068a = fitnessBuddiesSubHeaderList;
    }

    @NotNull
    public final List<FitnessBuddiesSubModel> getFitnessBuddiesSubHeaderList() {
        return this.f5068a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5068a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        FitnessBuddiesSubModel fitnessBuddiesSubModel = this.f5068a.get(i);
        List<FitnessBuddiesSubModel> list = this.f5068a;
        Intrinsics.checkNotNull(list);
        holder.bind(fitnessBuddiesSubModel, CollectionsKt__CollectionsKt.getLastIndex(list) == i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ProfileFitnessSubheaderItemBinding inflate = ProfileFitnessSubheaderItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
