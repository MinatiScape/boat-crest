package com.coveiot.android.leonardo.onboarding.paring.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ViewPagerOnboardingFTUAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5232a;
    @NotNull
    public final int[] b;

    /* loaded from: classes5.dex */
    public final class ImageViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f5233a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImageViewHolder(@NotNull ViewPagerOnboardingFTUAdapter viewPagerOnboardingFTUAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.ivImages);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.ivImages)");
            this.f5233a = (ImageView) findViewById;
        }

        public final void bind(int i) {
            this.f5233a.setImageResource(i);
        }
    }

    public ViewPagerOnboardingFTUAdapter(@NotNull Context context, @NotNull int[] images) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(images, "images");
        this.f5232a = context;
        this.b = images;
        Intrinsics.checkNotNullExpressionValue(LayoutInflater.from(context), "from(context)");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.length;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ImageViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.b[i]);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ImageViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qr_scan_on_boarding_f_t_u, parent, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ImageViewHolder(this, view);
    }
}
