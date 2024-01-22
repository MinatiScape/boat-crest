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
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivityParamHistoryAdapter extends RecyclerView.Adapter<ActivityHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<WorkoutUiBean> f2765a;
    @NotNull
    public final WorkoutUiBeanProvider.ScreenType b;

    /* loaded from: classes2.dex */
    public final class ActivityHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f2766a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityHolder(@NotNull ActivityParamHistoryAdapter activityParamHistoryAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.itemTitleImg);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.itemTitleImg)");
            this.f2766a = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.itemTitleText);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.itemTitleText)");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.itemValText);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.itemValText)");
            this.c = (TextView) findViewById3;
        }

        @NotNull
        public final ImageView getItemTitleImg() {
            return this.f2766a;
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

    public ActivityParamHistoryAdapter(@NotNull Context context, @NotNull List<WorkoutUiBean> workoutUiBeans, @NotNull WorkoutUiBeanProvider.ScreenType screenType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workoutUiBeans, "workoutUiBeans");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        this.f2765a = workoutUiBeans;
        this.b = screenType;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        if (workoutUtils.getMaximumWorkoutUiBeans(this.b) > 0 && this.f2765a.size() > 3) {
            return workoutUtils.getMaximumWorkoutUiBeans(this.b);
        }
        return this.f2765a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityHolder holder, int i) {
        Integer image;
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.f2765a.get(i).getImage() != null && ((image = this.f2765a.get(i).getImage()) == null || image.intValue() != 0)) {
            holder.getItemTitleImg().setVisibility(0);
            ImageView itemTitleImg = holder.getItemTitleImg();
            Integer image2 = this.f2765a.get(i).getImage();
            Intrinsics.checkNotNull(image2);
            itemTitleImg.setImageResource(image2.intValue());
        } else {
            holder.getItemTitleImg().setVisibility(8);
        }
        holder.getItemTitleText().setText(this.f2765a.get(i).getTitle());
        holder.getItemValText().setText(this.f2765a.get(i).getValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_activity_history_card, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…      false\n            )");
        return new ActivityHolder(this, inflate);
    }
}
