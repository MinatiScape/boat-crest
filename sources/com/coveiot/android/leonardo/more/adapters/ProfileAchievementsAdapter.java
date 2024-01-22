package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.ProfileAchievementsItemBinding;
import com.coveiot.android.leonardo.more.adapters.ProfileAchievementsAdapter;
import com.coveiot.android.leonardo.more.models.AchievementsModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ProfileAchievementsAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5064a;
    @NotNull
    public final OnAchievementsItemClickListener b;
    @NotNull
    public List<AchievementsModel> c;

    /* loaded from: classes5.dex */
    public interface OnAchievementsItemClickListener {
        void onAchievementsItemClicked(@NotNull AchievementsModel achievementsModel);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ProfileAchievementsItemBinding f5065a;
        public final /* synthetic */ ProfileAchievementsAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ProfileAchievementsAdapter profileAchievementsAdapter, ProfileAchievementsItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = profileAchievementsAdapter;
            this.f5065a = binding;
        }

        public static final void b(ProfileAchievementsAdapter this$0, AchievementsModel achievementsItem, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(achievementsItem, "$achievementsItem");
            this$0.getListener().onAchievementsItemClicked(achievementsItem);
        }

        public final void bind(@NotNull final AchievementsModel achievementsItem) {
            Intrinsics.checkNotNullParameter(achievementsItem, "achievementsItem");
            ProfileAchievementsItemBinding profileAchievementsItemBinding = this.f5065a;
            final ProfileAchievementsAdapter profileAchievementsAdapter = this.b;
            profileAchievementsItemBinding.setAchievementsData(achievementsItem);
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.t
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ProfileAchievementsAdapter.ViewHolder.b(ProfileAchievementsAdapter.this, achievementsItem, view);
                }
            });
        }
    }

    public ProfileAchievementsAdapter(@NotNull Context context, @NotNull OnAchievementsItemClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5064a = context;
        this.b = listener;
        this.c = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f5064a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final OnAchievementsItemClickListener getListener() {
        return this.b;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setProfileAchievementsList(@NotNull List<AchievementsModel> achievementsList) {
        Intrinsics.checkNotNullParameter(achievementsList, "achievementsList");
        this.c = TypeIntrinsics.asMutableList(achievementsList);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.c.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ProfileAchievementsItemBinding inflate = ProfileAchievementsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
