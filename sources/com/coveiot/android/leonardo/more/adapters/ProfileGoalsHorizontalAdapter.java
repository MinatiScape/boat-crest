package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ProfileGoalsItemHorizontalBinding;
import com.coveiot.android.leonardo.more.adapters.ProfileGoalsHorizontalAdapter;
import com.coveiot.android.leonardo.more.models.GoalsModel;
import com.coveiot.android.leonardo.utils.PayUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ProfileGoalsHorizontalAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5072a;
    @NotNull
    public final OnGoalsItemClickListener b;
    @NotNull
    public List<GoalsModel> c;

    /* loaded from: classes5.dex */
    public interface OnGoalsItemClickListener {
        void onGoalsItemClicked(@NotNull GoalsModel goalsModel);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ProfileGoalsItemHorizontalBinding f5073a;
        public final /* synthetic */ ProfileGoalsHorizontalAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ProfileGoalsHorizontalAdapter profileGoalsHorizontalAdapter, ProfileGoalsItemHorizontalBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = profileGoalsHorizontalAdapter;
            this.f5073a = binding;
        }

        public static final void b(ProfileGoalsHorizontalAdapter this$0, GoalsModel goalsItem, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(goalsItem, "$goalsItem");
            this$0.getListener().onGoalsItemClicked(goalsItem);
        }

        public final void bind(@NotNull final GoalsModel goalsItem) {
            Intrinsics.checkNotNullParameter(goalsItem, "goalsItem");
            ProfileGoalsItemHorizontalBinding profileGoalsItemHorizontalBinding = this.f5073a;
            final ProfileGoalsHorizontalAdapter profileGoalsHorizontalAdapter = this.b;
            profileGoalsItemHorizontalBinding.setGoalsData(goalsItem);
            if (goalsItem.getGoalsName().equals(profileGoalsHorizontalAdapter.getContext().getString(R.string.sleep_goal))) {
                TextView textView = profileGoalsItemHorizontalBinding.tvGoalValue;
                StringBuilder sb = new StringBuilder();
                sb.append(goalsItem.getGoalsTarget());
                sb.append(' ');
                String string = profileGoalsHorizontalAdapter.getContext().getString(R.string.hrs);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.hrs)");
                String lowerCase = string.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                sb.append(lowerCase);
                textView.setText(sb.toString());
            } else if (goalsItem.getGoalsName().equals(profileGoalsHorizontalAdapter.getContext().getString(R.string.calories))) {
                TextView textView2 = profileGoalsItemHorizontalBinding.tvGoalValue;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(goalsItem.getGoalsTarget());
                sb2.append(' ');
                String string2 = profileGoalsHorizontalAdapter.getContext().getString(R.string.cal);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.cal)");
                String lowerCase2 = string2.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                sb2.append(lowerCase2);
                textView2.setText(sb2.toString());
            } else if (goalsItem.getGoalsName().equals(profileGoalsHorizontalAdapter.getContext().getString(R.string.exercise_min_goal))) {
                TextView textView3 = profileGoalsItemHorizontalBinding.tvGoalValue;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(goalsItem.getGoalsTarget());
                sb3.append(' ');
                String string3 = profileGoalsHorizontalAdapter.getContext().getString(R.string.min);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.min)");
                String lowerCase3 = string3.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                sb3.append(lowerCase3);
                textView3.setText(sb3.toString());
            } else if (goalsItem.getGoalsName().equals(profileGoalsHorizontalAdapter.getContext().getString(R.string.distance))) {
                TextView textView4 = profileGoalsItemHorizontalBinding.tvGoalValue;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(PayUtils.INSTANCE.roundOffDecimalTillOnePlace(goalsItem.getGoalsTarget() / 1000));
                sb4.append(' ');
                String string4 = profileGoalsHorizontalAdapter.getContext().getString(R.string.km);
                Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.km)");
                String lowerCase4 = string4.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                sb4.append(lowerCase4);
                textView4.setText(sb4.toString());
            } else if (goalsItem.getGoalsName().equals(profileGoalsHorizontalAdapter.getContext().getString(R.string.walk_hour_goal))) {
                TextView textView5 = profileGoalsItemHorizontalBinding.tvGoalValue;
                StringBuilder sb5 = new StringBuilder();
                sb5.append(goalsItem.getGoalsTarget());
                sb5.append(' ');
                String string5 = profileGoalsHorizontalAdapter.getContext().getString(R.string.hrs);
                Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.hrs)");
                String lowerCase5 = string5.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase()");
                sb5.append(lowerCase5);
                textView5.setText(sb5.toString());
            } else {
                profileGoalsItemHorizontalBinding.tvGoalValue.setText(String.valueOf(goalsItem.getGoalsTarget()));
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.w
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ProfileGoalsHorizontalAdapter.ViewHolder.b(ProfileGoalsHorizontalAdapter.this, goalsItem, view);
                }
            });
        }
    }

    public ProfileGoalsHorizontalAdapter(@NotNull Context context, @NotNull OnGoalsItemClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5072a = context;
        this.b = listener;
        this.c = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f5072a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final OnGoalsItemClickListener getListener() {
        return this.b;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5072a = context;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setProfileGoalsList(@NotNull List<GoalsModel> goalsList) {
        Intrinsics.checkNotNullParameter(goalsList, "goalsList");
        this.c = TypeIntrinsics.asMutableList(goalsList);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.c.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ProfileGoalsItemHorizontalBinding inflate = ProfileGoalsItemHorizontalBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
