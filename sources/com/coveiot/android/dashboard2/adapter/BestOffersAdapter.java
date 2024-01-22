package com.coveiot.android.dashboard2.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.adapter.BestOffersAdapter;
import com.coveiot.android.dashboard2.model.BestOffers;
import com.coveiot.android.devicemodels.DeviceUtils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class BestOffersAdapter extends RecyclerView.Adapter<BestOffersViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<BestOffers> f4211a;

    /* loaded from: classes4.dex */
    public final class BestOffersViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public ImageView f4212a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BestOffersViewHolder(@NotNull BestOffersAdapter bestOffersAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f4212a = (ImageView) itemView.findViewById(R.id.ivBestOffers);
        }

        public static final void b(int i, BestOffers bestOffers, BestOffersViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(bestOffers, "$bestOffers");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            companion.logAnalyticsEvent("BC_HP_offerbanner" + (i + 1) + "_Tapped", null);
            if (bestOffers.getRedirectionType().equals("IN-APP")) {
                this$0.itemView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(bestOffers.getRedirectionUrl())));
                return;
            }
            this$0.itemView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(bestOffers.getRedirectionUrl())));
        }

        public final void bindView(@NotNull final BestOffers bestOffers, final int i) {
            Intrinsics.checkNotNullParameter(bestOffers, "bestOffers");
            Glide.with(this.itemView.getContext()).asBitmap().m21load(bestOffers.getImageUrl()).into(this.f4212a);
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.adapter.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BestOffersAdapter.BestOffersViewHolder.b(i, bestOffers, this, view);
                }
            });
        }

        public final ImageView getImage() {
            return this.f4212a;
        }

        public final void setImage(ImageView imageView) {
            this.f4212a = imageView;
        }
    }

    public BestOffersAdapter(@NotNull List<BestOffers> bestOffers) {
        Intrinsics.checkNotNullParameter(bestOffers, "bestOffers");
        this.f4211a = bestOffers;
    }

    @NotNull
    public final List<BestOffers> getBestOffers() {
        return this.f4211a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BestOffers> list = this.f4211a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull BestOffersViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bindView(this.f4211a.get(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public BestOffersViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_offers_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…fers_item, parent, false)");
        return new BestOffersViewHolder(this, inflate);
    }
}
