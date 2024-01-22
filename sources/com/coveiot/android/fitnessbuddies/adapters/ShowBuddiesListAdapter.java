package com.coveiot.android.fitnessbuddies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.constants.FitnessBuddiesLabels;
import com.coveiot.android.fitnessbuddies.databinding.BuddiesListItemNewBinding;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class ShowBuddiesListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4434a;
    @NotNull
    public final List<GetBuddyItems> b;
    @NotNull
    public final OnItemClickListener c;

    /* loaded from: classes4.dex */
    public interface OnItemClickListener {
        void onItemClickListener(@NotNull GetBuddyItems getBuddyItems);

        void onLabelItemClicked(@NotNull GetBuddyItems getBuddyItems, @NotNull FitnessBuddiesLabels fitnessBuddiesLabels);

        void showMessage(@NotNull String str);
    }

    /* loaded from: classes4.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final BuddiesListItemNewBinding f4435a;
        @NotNull
        public final ConstraintLayout b;
        @NotNull
        public final ImageView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final TextView e;
        @NotNull
        public final TextView f;
        @NotNull
        public final TextView g;
        @NotNull
        public final TextView h;
        @NotNull
        public final TextView i;
        @NotNull
        public final ConstraintLayout j;
        @NotNull
        public final ProgressBar k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ShowBuddiesListAdapter showBuddiesListAdapter, BuddiesListItemNewBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f4435a = binding;
            ConstraintLayout constraintLayout = binding.clMain;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clMain");
            this.b = constraintLayout;
            ImageView imageView = binding.ivProfile;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivProfile");
            this.c = imageView;
            TextView textView = binding.tvName;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvName");
            this.d = textView;
            TextView textView2 = binding.tvGoalCompletion;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvGoalCompletion");
            this.e = textView2;
            TextView textView3 = binding.tvStepsGoalValue;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvStepsGoalValue");
            this.f = textView3;
            TextView textView4 = binding.tvStepsGoalTarget;
            Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvStepsGoalTarget");
            this.g = textView4;
            TextView textView5 = binding.tvCalorieValue;
            Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvCalorieValue");
            this.h = textView5;
            TextView textView6 = binding.tvLabel;
            Intrinsics.checkNotNullExpressionValue(textView6, "binding.tvLabel");
            this.i = textView6;
            ConstraintLayout constraintLayout2 = binding.clLabel;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clLabel");
            this.j = constraintLayout2;
            ProgressBar progressBar = binding.progressBarSteps;
            Intrinsics.checkNotNullExpressionValue(progressBar, "binding.progressBarSteps");
            this.k = progressBar;
        }

        @NotNull
        public final ConstraintLayout getClLabel() {
            return this.j;
        }

        @NotNull
        public final ConstraintLayout getClMain() {
            return this.b;
        }

        @NotNull
        public final ImageView getIvProfile() {
            return this.c;
        }

        @NotNull
        public final ProgressBar getStepsProgress() {
            return this.k;
        }

        @NotNull
        public final TextView getTvCalorieValue() {
            return this.h;
        }

        @NotNull
        public final TextView getTvGoalCompletion() {
            return this.e;
        }

        @NotNull
        public final TextView getTvLabel() {
            return this.i;
        }

        @NotNull
        public final TextView getTvName() {
            return this.d;
        }

        @NotNull
        public final TextView getTvStepsGoalTarget() {
            return this.g;
        }

        @NotNull
        public final TextView getTvStepsGoalValue() {
            return this.f;
        }
    }

    public ShowBuddiesListAdapter(@NotNull Context mContext, @NotNull List<GetBuddyItems> buddyItemsList, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(buddyItemsList, "buddyItemsList");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f4434a = mContext;
        this.b = buddyItemsList;
        this.c = onItemClickListener;
    }

    public static final void e() {
    }

    public static final void f(Ref.BooleanRef isReminded, Ref.ObjectRef fitnessBuddiesLabels, ShowBuddiesListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(isReminded, "$isReminded");
        Intrinsics.checkNotNullParameter(fitnessBuddiesLabels, "$fitnessBuddiesLabels");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (isReminded.element) {
            T t = fitnessBuddiesLabels.element;
            Intrinsics.checkNotNull(t);
            if (t == FitnessBuddiesLabels.APPLAUD) {
                OnItemClickListener onItemClickListener = this$0.c;
                String string = this$0.f4434a.getString(R.string.you_applauded_buddy);
                Intrinsics.checkNotNullExpressionValue(string, "mContext.getString(R.string.you_applauded_buddy)");
                onItemClickListener.showMessage(string);
                return;
            }
            OnItemClickListener onItemClickListener2 = this$0.c;
            String string2 = this$0.f4434a.getString(R.string.you_reminded_buddy);
            Intrinsics.checkNotNullExpressionValue(string2, "mContext.getString(R.string.you_reminded_buddy)");
            onItemClickListener2.showMessage(string2);
            return;
        }
        this$0.c.onLabelItemClicked(this$0.b.get(i), (FitnessBuddiesLabels) fitnessBuddiesLabels.element);
    }

    public static final void g(ShowBuddiesListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.onItemClickListener(this$0.b.get(i));
    }

    @NotNull
    public final List<GetBuddyItems> getBuddyItemsList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0297  */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.coveiot.android.fitnessbuddies.constants.FitnessBuddiesLabels, T] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.coveiot.android.fitnessbuddies.constants.FitnessBuddiesLabels, T] */
    /* JADX WARN: Type inference failed for: r3v38, types: [com.coveiot.android.fitnessbuddies.constants.FitnessBuddiesLabels, T] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesListAdapter.ViewHolder r13, final int r14) {
        /*
            Method dump skipped, instructions count: 880
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesListAdapter.onBindViewHolder(com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesListAdapter$ViewHolder, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        BuddiesListItemNewBinding inflate = BuddiesListItemNewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
