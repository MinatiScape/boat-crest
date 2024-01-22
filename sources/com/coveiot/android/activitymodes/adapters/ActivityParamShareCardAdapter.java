package com.coveiot.android.activitymodes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.models.WorkoutUiBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityParamShareCardAdapter extends RecyclerView.Adapter<ActivityHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<WorkoutUiBean> f2767a = new ArrayList();
    @Nullable
    public Context b;

    /* loaded from: classes2.dex */
    public final class ActivityHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f2768a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityHolder(@NotNull ActivityParamShareCardAdapter activityParamShareCardAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.itemTitleImg);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.itemTitleImg)");
            this.f2768a = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.itemTitleText);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.itemTitleText)");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.itemValText);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.itemValText)");
            this.c = (TextView) findViewById3;
        }

        @NotNull
        public final ImageView getItemTitleImg() {
            return this.f2768a;
        }

        @NotNull
        public final TextView getItemTitleText() {
            return this.b;
        }

        @NotNull
        public final TextView getItemValText() {
            return this.c;
        }
    }

    @NotNull
    public final List<WorkoutUiBean> getActivityUiBeans() {
        return this.f2767a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2767a.size();
    }

    @Nullable
    public final Context getMContext() {
        return this.b;
    }

    public final void setActivityUiBeans(@NotNull List<WorkoutUiBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f2767a = list;
    }

    public final void setMContext(@Nullable Context context) {
        this.b = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityHolder holder, int i) {
        Integer image;
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.f2767a.get(i).getImage() != null && ((image = this.f2767a.get(i).getImage()) == null || image.intValue() != 0)) {
            ImageView itemTitleImg = holder.getItemTitleImg();
            Integer image2 = this.f2767a.get(i).getImage();
            Intrinsics.checkNotNull(image2);
            itemTitleImg.setImageResource(image2.intValue());
        } else {
            holder.getItemTitleImg().setVisibility(8);
        }
        holder.getItemTitleText().setText(this.f2767a.get(i).getTitle());
        holder.getItemValText().setText(this.f2767a.get(i).getValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.b = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_activity_share_card, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…      false\n            )");
        return new ActivityHolder(this, inflate);
    }
}
