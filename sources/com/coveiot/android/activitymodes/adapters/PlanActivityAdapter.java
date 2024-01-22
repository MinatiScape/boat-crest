package com.coveiot.android.activitymodes.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.BASEUNIT;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationActivity;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.coveaccess.fitness.ActivityCode;
import com.coveiot.covepreferences.UserDataManager;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class PlanActivityAdapter extends RecyclerView.Adapter<ActivityHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<EntityPreparationActivity> f2779a = new ArrayList<>();
    public boolean b;
    @Nullable
    public Context c;

    /* loaded from: classes2.dex */
    public final class ActivityHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f2780a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final ConstraintLayout d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityHolder(@NotNull PlanActivityAdapter planActivityAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.activity_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.activity_icon)");
            this.f2780a = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.activity_name);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.activity_name)");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.activity_target);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.activity_target)");
            this.c = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.activity_constraint);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.activity_constraint)");
            this.d = (ConstraintLayout) findViewById4;
        }

        @NotNull
        public final ImageView getActivityIcon() {
            return this.f2780a;
        }

        @NotNull
        public final TextView getActivityName() {
            return this.b;
        }

        @NotNull
        public final TextView getActivityTarget() {
            return this.c;
        }

        @NotNull
        public final ConstraintLayout getActivity_constraint() {
            return this.d;
        }
    }

    public PlanActivityAdapter(boolean z) {
        this.b = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2779a.size();
    }

    @Nullable
    public final Context getMContext() {
        return this.c;
    }

    @NotNull
    public final ArrayList<EntityPreparationActivity> getPlanActivities() {
        return this.f2779a;
    }

    public final boolean isCurrentDay() {
        return this.b;
    }

    public final void setCurrentDay(boolean z) {
        this.b = z;
    }

    public final void setMContext(@Nullable Context context) {
        this.c = context;
    }

    public final void setPlanActivities(@NotNull ArrayList<EntityPreparationActivity> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f2779a = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getActivityName().setText(this.f2779a.get(i).getTitle());
        if (this.b) {
            if (kotlin.text.m.equals(this.f2779a.get(i).getActivityCode(), ActivityCode.WALK.toString(), true)) {
                holder.getActivityIcon().setImageResource(R.drawable.ic_walk_activity_icon);
            } else if (kotlin.text.m.equals(this.f2779a.get(i).getActivityCode(), ActivityCode.LIGHT_RUN.toString(), true)) {
                holder.getActivityIcon().setImageResource(R.drawable.ic_light_run);
            } else {
                holder.getActivityIcon().setImageResource(R.drawable.ic_run_activity_icon);
            }
        } else if (kotlin.text.m.equals(this.f2779a.get(i).getActivityCode(), ActivityCode.WALK.toString(), true)) {
            holder.getActivityIcon().setImageResource(R.drawable.ic_walk_activity_icon_red);
        } else if (kotlin.text.m.equals(this.f2779a.get(i).getActivityCode(), ActivityCode.LIGHT_RUN.toString(), true)) {
            holder.getActivityIcon().setImageResource(R.drawable.ic_light_run_red);
        } else {
            holder.getActivityIcon().setImageResource(R.drawable.ic_run_activity_icon_red);
        }
        String target = this.f2779a.get(i).getTarget();
        float convertMetersToKmFloat = kotlin.text.m.equals$default(this.f2779a.get(i).getActivityBaseUnit(), BASEUNIT.METERS.toString(), false, 2, null) ? WorkoutUtils.INSTANCE.convertMetersToKmFloat(target != null ? Integer.parseInt(target) : 0) : 0.0f;
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(this.c).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(mContext).isDistanceUnitInMile");
        String str = "- -";
        if (isDistanceUnitInMile.booleanValue()) {
            TextView activityTarget = holder.getActivityTarget();
            if (!(convertMetersToKmFloat == 0.0f)) {
                str = WorkoutUtils.INSTANCE.convertKMToMiles(convertMetersToKmFloat) + " mi";
            }
            activityTarget.setText(str);
        } else {
            TextView activityTarget2 = holder.getActivityTarget();
            if (!(convertMetersToKmFloat == 0.0f)) {
                str = convertMetersToKmFloat + " Km";
            }
            activityTarget2.setText(str);
        }
        if (this.b) {
            TextView activityName = holder.getActivityName();
            Resources resources = holder.itemView.getContext().getResources();
            int i2 = R.color.main_text_color;
            activityName.setTextColor(resources.getColor(i2));
            holder.getActivityTarget().setTextColor(holder.itemView.getContext().getResources().getColor(i2));
            holder.getActivity_constraint().setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.colorPrimary));
            return;
        }
        TextView activityName2 = holder.getActivityName();
        Resources resources2 = holder.itemView.getContext().getResources();
        int i3 = R.color.main_text_color;
        activityName2.setTextColor(resources2.getColor(i3));
        holder.getActivityTarget().setTextColor(holder.itemView.getContext().getResources().getColor(i3));
        holder.getActivityTarget().setTextColor(holder.itemView.getContext().getResources().getColor(R.color.color_1f1f20));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.c = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_activity_detaills, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…      false\n            )");
        return new ActivityHolder(this, inflate);
    }
}
