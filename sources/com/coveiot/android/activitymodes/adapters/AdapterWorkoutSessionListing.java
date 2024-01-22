package com.coveiot.android.activitymodes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.covepreferences.UserDataManager;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AdapterWorkoutSessionListing extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2769a;
    @NotNull
    public final OnItemClickListener b;
    @Nullable
    public List<EntityWorkoutSession> c;

    /* loaded from: classes2.dex */
    public interface OnItemClickListener {
        void onItemClicked(@Nullable String str);
    }

    /* loaded from: classes2.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ConstraintLayout f2770a;
        @NotNull
        public final TextView b;
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull AdapterWorkoutSessionListing adapterWorkoutSessionListing, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            ConstraintLayout constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.parent_layout);
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "itemView.parent_layout");
            this.f2770a = constraintLayout;
            TextView textView = (TextView) itemView.findViewById(R.id.run_title);
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.run_title");
            this.b = textView;
            ImageView imageView = (ImageView) itemView.findViewById(R.id.map_image);
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.map_image");
            this.c = imageView;
            TextView textView2 = (TextView) itemView.findViewById(R.id.distance_tv);
            Intrinsics.checkNotNullExpressionValue(textView2, "itemView.distance_tv");
            this.d = textView2;
            TextView textView3 = (TextView) itemView.findViewById(R.id.duration_tv);
            Intrinsics.checkNotNullExpressionValue(textView3, "itemView.duration_tv");
            this.e = textView3;
            TextView textView4 = (TextView) itemView.findViewById(R.id.pace_tv);
            Intrinsics.checkNotNullExpressionValue(textView4, "itemView.pace_tv");
            this.f = textView4;
            TextView textView5 = (TextView) itemView.findViewById(R.id.date_tv);
            Intrinsics.checkNotNullExpressionValue(textView5, "itemView.date_tv");
            this.g = textView5;
        }

        @NotNull
        public final TextView getDateTv() {
            return this.g;
        }

        @NotNull
        public final TextView getDistanceTv() {
            return this.d;
        }

        @NotNull
        public final TextView getDurationTv() {
            return this.e;
        }

        @NotNull
        public final ImageView getMapImage() {
            return this.c;
        }

        @NotNull
        public final TextView getPaceTv() {
            return this.f;
        }

        @NotNull
        public final ConstraintLayout getParentLayout() {
            return this.f2770a;
        }

        @NotNull
        public final TextView getRunTitle() {
            return this.b;
        }
    }

    public AdapterWorkoutSessionListing(@NotNull Context mContext, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f2769a = mContext;
        this.b = onItemClickListener;
    }

    public static final void b(AdapterWorkoutSessionListing this$0, int i, View view) {
        EntityWorkoutSession entityWorkoutSession;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnItemClickListener onItemClickListener = this$0.b;
        List<EntityWorkoutSession> list = this$0.c;
        onItemClickListener.onItemClicked((list == null || (entityWorkoutSession = list.get(i)) == null) ? null : entityWorkoutSession.getSession_id());
    }

    @Nullable
    public final List<EntityWorkoutSession> getEntityWorkoutSessions() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<EntityWorkoutSession> list = this.c;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @NotNull
    public final Context getMContext() {
        return this.f2769a;
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.b;
    }

    public final void setData(@NotNull List<EntityWorkoutSession> entityWorkoutSessions) {
        Intrinsics.checkNotNullParameter(entityWorkoutSessions, "entityWorkoutSessions");
        this.c = entityWorkoutSessions;
        notifyDataSetChanged();
    }

    public final void setEntityWorkoutSessions(@Nullable List<EntityWorkoutSession> list) {
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<EntityWorkoutSession> list = this.c;
        EntityWorkoutSession entityWorkoutSession = list != null ? list.get(i) : null;
        if (entityWorkoutSession != null) {
            holder.getRunTitle().setText(entityWorkoutSession.getActivity_type());
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(this.f2769a).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(mContext).isDistanceUnitInMile");
            if (isDistanceUnitInMile.booleanValue()) {
                TextView distanceTv = holder.getDistanceTv();
                StringBuilder sb = new StringBuilder();
                WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
                sb.append((float) workoutUtils.convertKMToMiles(workoutUtils.convertCmToKm(entityWorkoutSession.getTotal_distance())));
                sb.append(this.f2769a.getString(R.string.space));
                sb.append(' ');
                sb.append(this.f2769a.getString(R.string.mil));
                distanceTv.setText(sb.toString());
            } else {
                TextView distanceTv2 = holder.getDistanceTv();
                distanceTv2.setText(WorkoutUtils.INSTANCE.convertCmToKm(entityWorkoutSession.getTotal_distance()) + ' ' + this.f2769a.getString(R.string.space) + ' ' + this.f2769a.getString(R.string.km));
            }
            TextView durationTv = holder.getDurationTv();
            WorkoutUtils workoutUtils2 = WorkoutUtils.INSTANCE;
            durationTv.setText(workoutUtils2.getFormattedDuration(entityWorkoutSession.getSession_duration()));
            holder.getPaceTv().setText(workoutUtils2.getFormattedPace(entityWorkoutSession.getPace()));
            holder.getDateTv().setText(workoutUtils2.getFormattedDate(entityWorkoutSession.getSession_date(), "yyyy-MM-dd", "dd MMM yy"));
        }
        holder.getParentLayout().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdapterWorkoutSessionListing.b(AdapterWorkoutSessionListing.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup p0, int i) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        View inflate = LayoutInflater.from(this.f2769a).inflate(R.layout.workout_session_list_item, p0, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(\n                mC…ion_list_item, p0, false)");
        return new ViewHolder(this, inflate);
    }
}
