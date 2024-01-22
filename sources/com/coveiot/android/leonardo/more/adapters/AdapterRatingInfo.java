package com.coveiot.android.leonardo.more.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.models.RatingInfo;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AdapterRatingInfo extends RecyclerView.Adapter<RatingViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<RatingInfo> f5036a;
    @NotNull
    public final RatingSelectionListener b;
    public int c;

    /* loaded from: classes5.dex */
    public interface RatingSelectionListener {
        void onRatingSelected(@NotNull RatingInfo ratingInfo);
    }

    /* loaded from: classes5.dex */
    public final class RatingViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f5037a;
        public final TextView b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RatingViewHolder(@NotNull AdapterRatingInfo adapterRatingInfo, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f5037a = (ImageView) itemView.findViewById(R.id.rating_img);
            this.b = (TextView) itemView.findViewById(R.id.rating_name);
        }

        public final ImageView getRatingImg() {
            return this.f5037a;
        }

        public final TextView getRatingName() {
            return this.b;
        }
    }

    public AdapterRatingInfo(@NotNull ArrayList<RatingInfo> ratingInfoList, @NotNull RatingSelectionListener listener) {
        Intrinsics.checkNotNullParameter(ratingInfoList, "ratingInfoList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5036a = ratingInfoList;
        this.b = listener;
        this.c = -1;
    }

    public static final void b(AdapterRatingInfo this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c = i;
        RatingSelectionListener ratingSelectionListener = this$0.b;
        RatingInfo ratingInfo = this$0.f5036a.get(i);
        Intrinsics.checkNotNullExpressionValue(ratingInfo, "ratingInfoList.get(position)");
        ratingSelectionListener.onRatingSelected(ratingInfo);
        this$0.notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5036a.size();
    }

    @NotNull
    public final RatingSelectionListener getListener() {
        return this.b;
    }

    @NotNull
    public final ArrayList<RatingInfo> getRatingInfoList() {
        return this.f5036a;
    }

    public final int getSelectedItemPosition() {
        return this.c;
    }

    @Nullable
    public final RatingInfo getSelectedRatingInfo() {
        return this.f5036a.get(this.c);
    }

    public final void resetRating() {
        this.c = -1;
        notifyDataSetChanged();
    }

    public final void setSelectedItemPosition(int i) {
        this.c = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull RatingViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getRatingName().setText(this.f5036a.get(i).getName());
        holder.getRatingName().setVisibility(8);
        int i2 = this.c;
        if (i2 == -1 && i == 2) {
            holder.getRatingImg().setImageResource(R.drawable.rating_unselected_big);
        } else if (i2 < i) {
            holder.getRatingImg().setImageResource(R.drawable.rating_unselected);
        } else if (i2 == i) {
            holder.getRatingImg().setImageResource(R.drawable.rating_selected_big);
            holder.getRatingName().setVisibility(0);
        } else {
            holder.getRatingImg().setImageResource(R.drawable.rating_selected);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdapterRatingInfo.b(AdapterRatingInfo.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RatingViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_list_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…list_item, parent, false)");
        return new RatingViewHolder(this, inflate);
    }
}
