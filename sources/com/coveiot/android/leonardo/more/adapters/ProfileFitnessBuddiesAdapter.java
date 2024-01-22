package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.ProfileFitnessItemBinding;
import com.coveiot.android.leonardo.more.adapters.ProfileFitnessBuddiesAdapter;
import com.coveiot.android.leonardo.more.models.FitnessBuddiesModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ProfileFitnessBuddiesAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5066a;
    @NotNull
    public final OnFitnessItemClickListener b;
    @NotNull
    public List<FitnessBuddiesModel> c;

    /* loaded from: classes5.dex */
    public interface OnFitnessItemClickListener {
        void onFitnessItemClicked(@NotNull FitnessBuddiesModel fitnessBuddiesModel);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ProfileFitnessItemBinding f5067a;
        public final /* synthetic */ ProfileFitnessBuddiesAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ProfileFitnessBuddiesAdapter profileFitnessBuddiesAdapter, ProfileFitnessItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = profileFitnessBuddiesAdapter;
            this.f5067a = binding;
        }

        public static final void b(ProfileFitnessBuddiesAdapter this$0, FitnessBuddiesModel fitnessBuddiesItem, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(fitnessBuddiesItem, "$fitnessBuddiesItem");
            this$0.getListener().onFitnessItemClicked(fitnessBuddiesItem);
        }

        public final void bind(@NotNull final FitnessBuddiesModel fitnessBuddiesItem, boolean z) {
            Intrinsics.checkNotNullParameter(fitnessBuddiesItem, "fitnessBuddiesItem");
            ProfileFitnessItemBinding profileFitnessItemBinding = this.f5067a;
            final ProfileFitnessBuddiesAdapter profileFitnessBuddiesAdapter = this.b;
            profileFitnessItemBinding.setFitnessData(fitnessBuddiesItem);
            profileFitnessItemBinding.rvFitnessSubheaders.setLayoutManager(new LinearLayoutManager(profileFitnessBuddiesAdapter.getContext(), 0, false));
            profileFitnessItemBinding.rvFitnessSubheaders.setAdapter(new ProfileFitnessSubHeaderAdapter(fitnessBuddiesItem.getFitnessBuddiesSubList()));
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.u
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ProfileFitnessBuddiesAdapter.ViewHolder.b(ProfileFitnessBuddiesAdapter.this, fitnessBuddiesItem, view);
                }
            });
            if (z) {
                profileFitnessItemBinding.listItemDivider.setVisibility(8);
            } else {
                profileFitnessItemBinding.listItemDivider.setVisibility(0);
            }
        }
    }

    public ProfileFitnessBuddiesAdapter(@NotNull Context context, @NotNull OnFitnessItemClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5066a = context;
        this.b = listener;
        this.c = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f5066a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final OnFitnessItemClickListener getListener() {
        return this.b;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setProfileFitnessBuddiesList(@NotNull List<FitnessBuddiesModel> fitnessBuddiesList) {
        Intrinsics.checkNotNullParameter(fitnessBuddiesList, "fitnessBuddiesList");
        this.c = TypeIntrinsics.asMutableList(fitnessBuddiesList);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        FitnessBuddiesModel fitnessBuddiesModel = this.c.get(i);
        List<FitnessBuddiesModel> list = this.c;
        Intrinsics.checkNotNull(list);
        holder.bind(fitnessBuddiesModel, CollectionsKt__CollectionsKt.getLastIndex(list) == i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ProfileFitnessItemBinding inflate = ProfileFitnessItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
