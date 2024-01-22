package com.coveiot.android.fitnesschallenges.adpter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnesschallenges.databinding.ListItemLoadingLayoutBinding;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class LoaderAdapter extends LoadStateAdapter<ViewHolder> {

    /* loaded from: classes2.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemLoadingLayoutBinding f4501a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull LoaderAdapter loaderAdapter, ListItemLoadingLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f4501a = binding;
        }

        public final void bind(@NotNull LoadState loadState) {
            Intrinsics.checkNotNullParameter(loadState, "loadState");
            ProgressBar progressBar = this.f4501a.progressBar;
            Intrinsics.checkNotNullExpressionValue(progressBar, "binding.progressBar");
            progressBar.setVisibility(loadState instanceof LoadState.Loading ? 0 : 8);
        }

        @NotNull
        public final ListItemLoadingLayoutBinding getBinding() {
            return this.f4501a;
        }
    }

    @Override // androidx.paging.LoadStateAdapter
    public void onBindViewHolder(@NotNull ViewHolder holder, @NotNull LoadState loadState) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        holder.bind(loadState);
    }

    @Override // androidx.paging.LoadStateAdapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, @NotNull LoadState loadState) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        ListItemLoadingLayoutBinding inflate = ListItemLoadingLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
