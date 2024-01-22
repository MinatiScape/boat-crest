package com.coveiot.android.leonardo.dashboard.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FitnessGoalsItemBinding;
import com.coveiot.android.leonardo.more.models.GoalsModel;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FitnessGoalAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4773a;
    @NotNull
    public final List<GoalsModel> b;

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4774a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final ImageView d;
        @NotNull
        public final View e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull FitnessGoalAdapter fitnessGoalAdapter, FitnessGoalsItemBinding itemView) {
            super(itemView.getRoot());
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = itemView.tvGoalValue;
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.tvGoalValue");
            this.f4774a = textView;
            TextView textView2 = itemView.tvGoalTarget;
            Intrinsics.checkNotNullExpressionValue(textView2, "itemView.tvGoalTarget");
            this.b = textView2;
            TextView textView3 = itemView.tvGoalName;
            Intrinsics.checkNotNullExpressionValue(textView3, "itemView.tvGoalName");
            this.c = textView3;
            ImageView imageView = itemView.ivGoal;
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.ivGoal");
            this.d = imageView;
            View view = itemView.view;
            Intrinsics.checkNotNullExpressionValue(view, "itemView.view");
            this.e = view;
        }

        @NotNull
        public final ImageView getIvGoalImage() {
            return this.d;
        }

        @NotNull
        public final TextView getTvGoalName() {
            return this.c;
        }

        @NotNull
        public final TextView getTvGoalTarget() {
            return this.b;
        }

        @NotNull
        public final TextView getTvGoalValue() {
            return this.f4774a;
        }

        @NotNull
        public final View getView() {
            return this.e;
        }
    }

    public FitnessGoalAdapter(@NotNull Context mContext, @NotNull List<GoalsModel> goalsList) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(goalsList, "goalsList");
        this.f4773a = mContext;
        this.b = goalsList;
    }

    @NotNull
    public final List<GoalsModel> getGoalsList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<GoalsModel> list = this.b;
        Intrinsics.checkNotNull(list);
        if (CollectionsKt__CollectionsKt.getLastIndex(list) == i) {
            holder.getView().setVisibility(8);
        } else {
            holder.getView().setVisibility(8);
        }
        if (this.b.get(i).getGoalsName().equals(this.f4773a.getString(R.string.sleep))) {
            TextView tvGoalValue = holder.getTvGoalValue();
            StringBuilder sb = new StringBuilder();
            PayUtils payUtils = PayUtils.INSTANCE;
            sb.append(payUtils.getHourFormat(this.b.get(i).getGoalsValue()));
            sb.append(':');
            sb.append(payUtils.getSecondFormat(this.b.get(i).getGoalsValue() % 60));
            tvGoalValue.setText(sb.toString());
            TextView tvGoalTarget = holder.getTvGoalTarget();
            tvGoalTarget.setText("/ " + payUtils.getHourFormat(this.b.get(i).getGoalsTarget()) + ':' + payUtils.getSecondFormat(this.b.get(i).getGoalsTarget() % 60));
        } else {
            holder.getTvGoalValue().setText(String.valueOf(this.b.get(i).getGoalsValue()));
            TextView tvGoalTarget2 = holder.getTvGoalTarget();
            tvGoalTarget2.setText("/ " + this.b.get(i).getGoalsTarget());
        }
        holder.getTvGoalName().setText(this.b.get(i).getGoalsName());
        holder.getIvGoalImage().setImageDrawable(this.b.get(i).getImage());
        if (this.b.get(i).getGoalsValue() > this.b.get(i).getGoalsTarget()) {
            holder.getTvGoalValue().setTextColor(this.f4773a.getColor(R.color.color_03c28a));
            ViewUtilsKt.drawableEnd(holder.getTvGoalValue(), R.drawable.ic_small_green_arrow_up_12_by_12);
            return;
        }
        holder.getTvGoalValue().setTextColor(this.f4773a.getColor(R.color.main_text_color));
        ViewUtilsKt.emptyDrawable(holder.getTvGoalValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        FitnessGoalsItemBinding inflate = FitnessGoalsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
