package com.coveiot.android.qrtray.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.qrtray.R;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ImagePagerAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<Integer> f5568a;

    /* loaded from: classes5.dex */
    public final class ImageViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f5569a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImageViewHolder(@NotNull ImagePagerAdapter imagePagerAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.ivIntro);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.ivIntro)");
            this.f5569a = (ImageView) findViewById;
        }

        public final void bind(int i) {
            this.f5569a.setImageResource(i);
        }
    }

    public ImagePagerAdapter(@NotNull Context context, @NotNull List<Integer> images) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(images, "images");
        this.f5568a = images;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5568a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ImageViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.f5568a.get(i).intValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ImageViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_qr_tray_intro_images_layout, parent, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ImageViewHolder(this, view);
    }
}
