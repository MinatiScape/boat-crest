package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ProfileGoalsItemBinding;
import com.coveiot.android.leonardo.more.adapters.ProfileGoalsAdapter;
import com.coveiot.android.leonardo.more.models.GoalsModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ProfileGoalsAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5070a;
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
        public final ProfileGoalsItemBinding f5071a;
        public final /* synthetic */ ProfileGoalsAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ProfileGoalsAdapter profileGoalsAdapter, ProfileGoalsItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = profileGoalsAdapter;
            this.f5071a = binding;
        }

        public static final void b(ProfileGoalsAdapter this$0, GoalsModel goalsItem, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(goalsItem, "$goalsItem");
            this$0.getListener().onGoalsItemClicked(goalsItem);
        }

        public final void bind(@NotNull final GoalsModel goalsItem) {
            Intrinsics.checkNotNullParameter(goalsItem, "goalsItem");
            ProfileGoalsItemBinding profileGoalsItemBinding = this.f5071a;
            final ProfileGoalsAdapter profileGoalsAdapter = this.b;
            profileGoalsItemBinding.setGoalsData(goalsItem);
            if (goalsItem.getGoalsName().equals(profileGoalsAdapter.getContext().getString(R.string.sleep))) {
                TextView textView = profileGoalsItemBinding.tvGoalValue;
                StringBuilder sb = new StringBuilder();
                sb.append(goalsItem.getGoalsTarget());
                sb.append(' ');
                String string = profileGoalsAdapter.getContext().getString(R.string.hrs);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.hrs)");
                String lowerCase = string.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                sb.append(lowerCase);
                textView.setText(sb.toString());
            } else if (goalsItem.getGoalsName().equals(profileGoalsAdapter.getContext().getString(R.string.calories))) {
                TextView textView2 = profileGoalsItemBinding.tvGoalValue;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(goalsItem.getGoalsTarget());
                sb2.append(' ');
                String string2 = profileGoalsAdapter.getContext().getString(R.string.cal);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.cal)");
                String lowerCase2 = string2.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                sb2.append(lowerCase2);
                textView2.setText(sb2.toString());
            } else if (goalsItem.getGoalsName().equals(profileGoalsAdapter.getContext().getString(R.string.exercise_min_goal))) {
                TextView textView3 = profileGoalsItemBinding.tvGoalValue;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(goalsItem.getGoalsTarget());
                sb3.append(' ');
                String string3 = profileGoalsAdapter.getContext().getString(R.string.min);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.min)");
                String lowerCase3 = string3.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                sb3.append(lowerCase3);
                textView3.setText(sb3.toString());
            } else if (goalsItem.getGoalsName().equals(profileGoalsAdapter.getContext().getString(R.string.distance))) {
                TextView textView4 = profileGoalsItemBinding.tvGoalValue;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(goalsItem.getGoalsTarget() / 1000);
                sb4.append(' ');
                String string4 = profileGoalsAdapter.getContext().getString(R.string.km);
                Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.km)");
                String lowerCase4 = string4.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                sb4.append(lowerCase4);
                textView4.setText(sb4.toString());
            } else {
                profileGoalsItemBinding.tvGoalValue.setText(String.valueOf(goalsItem.getGoalsTarget()));
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.v
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ProfileGoalsAdapter.ViewHolder.b(ProfileGoalsAdapter.this, goalsItem, view);
                }
            });
        }
    }

    public ProfileGoalsAdapter(@NotNull Context context, @NotNull OnGoalsItemClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5070a = context;
        this.b = listener;
        this.c = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f5070a;
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
        this.f5070a = context;
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
        ProfileGoalsItemBinding inflate = ProfileGoalsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
