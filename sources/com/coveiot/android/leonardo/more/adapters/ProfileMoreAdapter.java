package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.ProfileMoreItemBinding;
import com.coveiot.android.leonardo.more.adapters.ProfileMoreAdapter;
import com.coveiot.android.leonardo.more.models.ProfileMoreModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ProfileMoreAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5074a;
    @NotNull
    public final OnProfileMoreItemClickListener b;
    @NotNull
    public List<ProfileMoreModel> c;

    /* loaded from: classes5.dex */
    public interface OnProfileMoreItemClickListener {
        void onMoreClickListener(@NotNull ProfileMoreModel profileMoreModel);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ProfileMoreItemBinding f5075a;
        public final /* synthetic */ ProfileMoreAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ProfileMoreAdapter profileMoreAdapter, ProfileMoreItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = profileMoreAdapter;
            this.f5075a = binding;
        }

        public static final void b(ProfileMoreAdapter this$0, ProfileMoreModel profileMoreItem, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(profileMoreItem, "$profileMoreItem");
            this$0.getListener().onMoreClickListener(profileMoreItem);
        }

        public final void bind(@NotNull final ProfileMoreModel profileMoreItem, boolean z) {
            Intrinsics.checkNotNullParameter(profileMoreItem, "profileMoreItem");
            ProfileMoreItemBinding profileMoreItemBinding = this.f5075a;
            final ProfileMoreAdapter profileMoreAdapter = this.b;
            profileMoreItemBinding.setMoreData(profileMoreItem);
            if (z) {
                profileMoreItemBinding.listItemDivider.setVisibility(4);
            } else {
                profileMoreItemBinding.listItemDivider.setVisibility(0);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.x
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ProfileMoreAdapter.ViewHolder.b(ProfileMoreAdapter.this, profileMoreItem, view);
                }
            });
        }
    }

    public ProfileMoreAdapter(@NotNull Context context, @NotNull OnProfileMoreItemClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5074a = context;
        this.b = listener;
        this.c = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f5074a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final OnProfileMoreItemClickListener getListener() {
        return this.b;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setProfileMoreList(@NotNull List<ProfileMoreModel> profileMoreList) {
        Intrinsics.checkNotNullParameter(profileMoreList, "profileMoreList");
        this.c = TypeIntrinsics.asMutableList(profileMoreList);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.c.get(i), CollectionsKt__CollectionsKt.getLastIndex(this.c) == i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ProfileMoreItemBinding inflate = ProfileMoreItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
