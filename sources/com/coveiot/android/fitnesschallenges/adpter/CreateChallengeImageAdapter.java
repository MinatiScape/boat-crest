package com.coveiot.android.fitnesschallenges.adpter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.adpter.CreateChallengeImageAdapter;
import com.coveiot.android.fitnesschallenges.databinding.CoustomizedChallengeImageItemBinding;
import com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CreateChallengeImageAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<BannerImagesRes.Item> f4493a;
    @NotNull
    public OnBackGroundSelectedListener b;
    @Nullable
    public BuddiesChallengeDetail c;
    public int d;
    public int e;

    /* loaded from: classes2.dex */
    public interface OnBackGroundSelectedListener {
        void onBackGroundSelected(@NotNull BannerImagesRes.Item item);
    }

    /* loaded from: classes2.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final CoustomizedChallengeImageItemBinding f4494a;
        public final /* synthetic */ CreateChallengeImageAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull CreateChallengeImageAdapter createChallengeImageAdapter, CoustomizedChallengeImageItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = createChallengeImageAdapter;
            this.f4494a = binding;
        }

        public static final void b(CreateChallengeImageAdapter this$0, ViewHolder this$1, BannerImagesRes.Item imageItem, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(imageItem, "$imageItem");
            this$0.e = this$0.d;
            this$0.d = this$1.getAbsoluteAdapterPosition();
            this$0.notifyItemChanged(this$0.e);
            this$0.notifyItemChanged(this$0.d);
            this$0.getOnBackGroundSelectedListener().onBackGroundSelected(imageItem);
        }

        public final void bind(@NotNull final BannerImagesRes.Item imageItem) {
            Intrinsics.checkNotNullParameter(imageItem, "imageItem");
            CoustomizedChallengeImageItemBinding coustomizedChallengeImageItemBinding = this.f4494a;
            final CreateChallengeImageAdapter createChallengeImageAdapter = this.b;
            ImageView imageView = coustomizedChallengeImageItemBinding.imageView;
            Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
            createChallengeImageAdapter.setImage(imageView, imageItem.getBannerImageUrl());
            if (createChallengeImageAdapter.d == getAbsoluteAdapterPosition()) {
                coustomizedChallengeImageItemBinding.clIVBorder.setBackgroundResource(R.drawable.create_challenge_selection_bg);
            } else {
                coustomizedChallengeImageItemBinding.clIVBorder.setBackgroundResource(0);
            }
            coustomizedChallengeImageItemBinding.imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.adpter.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CreateChallengeImageAdapter.ViewHolder.b(CreateChallengeImageAdapter.this, this, imageItem, view);
                }
            });
        }

        @NotNull
        public final CoustomizedChallengeImageItemBinding getBinding() {
            return this.f4494a;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CreateChallengeImageAdapter(@NotNull Context context, @NotNull List<? extends BannerImagesRes.Item> imageList, @NotNull OnBackGroundSelectedListener onBackGroundSelectedListener, @Nullable BuddiesChallengeDetail buddiesChallengeDetail) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageList, "imageList");
        Intrinsics.checkNotNullParameter(onBackGroundSelectedListener, "onBackGroundSelectedListener");
        this.f4493a = imageList;
        this.b = onBackGroundSelectedListener;
        this.c = buddiesChallengeDetail;
    }

    @Nullable
    public final BuddiesChallengeDetail getBuddiesChallengeDetail() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f4493a.size();
    }

    @NotNull
    public final OnBackGroundSelectedListener getOnBackGroundSelectedListener() {
        return this.b;
    }

    public final void setBuddiesChallengeDetail(@Nullable BuddiesChallengeDetail buddiesChallengeDetail) {
        this.c = buddiesChallengeDetail;
    }

    public final void setImage(@NotNull ImageView imageView, @Nullable String str) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        RequestBuilder<Drawable> m30load = Glide.with(imageView).m30load(str);
        int i = R.drawable.system_fittness_challenge_banner_bg1;
        m30load.error(i).placeholder(i).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public final void setImageSelectedPosition(int i) {
        this.d = i;
        this.e = i;
        notifyDataSetChanged();
    }

    public final void setOnBackGroundSelectedListener(@NotNull OnBackGroundSelectedListener onBackGroundSelectedListener) {
        Intrinsics.checkNotNullParameter(onBackGroundSelectedListener, "<set-?>");
        this.b = onBackGroundSelectedListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.f4493a.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        CoustomizedChallengeImageItemBinding inflate = CoustomizedChallengeImageItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
