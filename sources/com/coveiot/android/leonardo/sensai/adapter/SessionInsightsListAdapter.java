package com.coveiot.android.leonardo.sensai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.SensAiListItemBinding;
import com.coveiot.android.leonardo.sensai.adapter.SessionInsightsListAdapter;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SessionInsightsListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5381a;
    @NotNull
    public final OnItemClickListener b;
    @Nullable
    public List<SensAIActivitySummary> c;

    /* loaded from: classes5.dex */
    public interface OnItemClickListener {
        void onBattingAddToCompareClicked(@Nullable SensAIActivitySummary sensAIActivitySummary, boolean z);

        void onBowlingAddToCompareClicked(@Nullable SensAIActivitySummary sensAIActivitySummary, boolean z);

        void onItemClicked(@Nullable String str, int i);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5382a;
        @NotNull
        public final ImageView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final TextView e;
        @NotNull
        public final ImageView f;
        @NotNull
        public final TextView g;
        @NotNull
        public final ImageView h;
        @NotNull
        public final CheckBox i;
        @NotNull
        public final TextView j;
        @NotNull
        public final ImageView k;
        @NotNull
        public final TextView l;
        @NotNull
        public final ConstraintLayout m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SessionInsightsListAdapter sessionInsightsListAdapter, SensAiListItemBinding itemView) {
            super(itemView.getRoot());
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = itemView.tvTitle;
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.tvTitle");
            this.f5382a = textView;
            ImageView imageView = itemView.ivImage;
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.ivImage");
            this.b = imageView;
            TextView textView2 = itemView.tvTime;
            Intrinsics.checkNotNullExpressionValue(textView2, "itemView.tvTime");
            this.c = textView2;
            TextView textView3 = itemView.tvDuration;
            Intrinsics.checkNotNullExpressionValue(textView3, "itemView.tvDuration");
            this.d = textView3;
            TextView textView4 = itemView.tvTotalShots;
            Intrinsics.checkNotNullExpressionValue(textView4, "itemView.tvTotalShots");
            this.e = textView4;
            ImageView imageView2 = itemView.ivSpeed;
            Intrinsics.checkNotNullExpressionValue(imageView2, "itemView.ivSpeed");
            this.f = imageView2;
            TextView textView5 = itemView.tvAvgHandSpeed;
            Intrinsics.checkNotNullExpressionValue(textView5, "itemView.tvAvgHandSpeed");
            this.g = textView5;
            ImageView imageView3 = itemView.ivAward;
            Intrinsics.checkNotNullExpressionValue(imageView3, "itemView.ivAward");
            this.h = imageView3;
            CheckBox checkBox = itemView.cbAddToCompare;
            Intrinsics.checkNotNullExpressionValue(checkBox, "itemView.cbAddToCompare");
            this.i = checkBox;
            TextView textView6 = itemView.tvTotalShotsTxt;
            Intrinsics.checkNotNullExpressionValue(textView6, "itemView.tvTotalShotsTxt");
            this.j = textView6;
            ImageView imageView4 = itemView.ivShots;
            Intrinsics.checkNotNullExpressionValue(imageView4, "itemView.ivShots");
            this.k = imageView4;
            TextView textView7 = itemView.tvAvgHandSpeedTxt;
            Intrinsics.checkNotNullExpressionValue(textView7, "itemView.tvAvgHandSpeedTxt");
            this.l = textView7;
            ConstraintLayout constraintLayout = itemView.clData1;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "itemView.clData1");
            this.m = constraintLayout;
        }

        @NotNull
        public final CheckBox getCbAddToCompare() {
            return this.i;
        }

        @NotNull
        public final ConstraintLayout getClData1() {
            return this.m;
        }

        @NotNull
        public final ImageView getIvAward() {
            return this.h;
        }

        @NotNull
        public final ImageView getIvImage() {
            return this.b;
        }

        @NotNull
        public final ImageView getIvShots() {
            return this.k;
        }

        @NotNull
        public final ImageView getIvSpeed() {
            return this.f;
        }

        @NotNull
        public final TextView getTvAvgHandSpeed() {
            return this.g;
        }

        @NotNull
        public final TextView getTvAvgHandSpeedTxt() {
            return this.l;
        }

        @NotNull
        public final TextView getTvDuration() {
            return this.d;
        }

        @NotNull
        public final TextView getTvTime() {
            return this.c;
        }

        @NotNull
        public final TextView getTvTitle() {
            return this.f5382a;
        }

        @NotNull
        public final TextView getTvTotalShots() {
            return this.e;
        }

        @NotNull
        public final TextView getTvTotalShotsTxt() {
            return this.j;
        }
    }

    public SessionInsightsListAdapter(@NotNull Context mContext, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f5381a = mContext;
        this.b = onItemClickListener;
    }

    public static final void c(SessionInsightsListAdapter this$0, int i, View view) {
        SensAIActivitySummary sensAIActivitySummary;
        SensAIActivitySummary sensAIActivitySummary2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnItemClickListener onItemClickListener = this$0.b;
        List<SensAIActivitySummary> list = this$0.c;
        Integer num = null;
        String sessionId = (list == null || (sensAIActivitySummary2 = list.get(i)) == null) ? null : sensAIActivitySummary2.getSessionId();
        List<SensAIActivitySummary> list2 = this$0.c;
        if (list2 != null && (sensAIActivitySummary = list2.get(i)) != null) {
            num = Integer.valueOf(sensAIActivitySummary.getActivityType());
        }
        Intrinsics.checkNotNull(num);
        onItemClickListener.onItemClicked(sessionId, num.intValue());
    }

    public static final void d(ViewHolder holder, SessionInsightsListAdapter this$0, int i, CompoundButton compoundButton, boolean z) {
        SensAIActivitySummary sensAIActivitySummary;
        SensAIActivitySummary sensAIActivitySummary2;
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (compoundButton.isShown()) {
            if (z) {
                holder.getCbAddToCompare().setTextColor(this$0.f5381a.getColor(R.color.main_text_color));
            } else {
                holder.getCbAddToCompare().setTextColor(this$0.f5381a.getColor(R.color.color_666666));
            }
            List<SensAIActivitySummary> list = this$0.c;
            SensAIActivitySummary sensAIActivitySummary3 = list != null ? list.get(i) : null;
            if (sensAIActivitySummary3 != null) {
                sensAIActivitySummary3.setAddToCompare(z);
            }
            List<SensAIActivitySummary> list2 = this$0.c;
            boolean z2 = true;
            if (list2 == null || (sensAIActivitySummary2 = list2.get(i)) == null || sensAIActivitySummary2.getActivityType() != 1) {
                z2 = false;
            }
            if (z2) {
                OnItemClickListener onItemClickListener = this$0.b;
                List<SensAIActivitySummary> list3 = this$0.c;
                sensAIActivitySummary = list3 != null ? list3.get(i) : null;
                Intrinsics.checkNotNull(sensAIActivitySummary);
                onItemClickListener.onBattingAddToCompareClicked(sensAIActivitySummary, z);
                return;
            }
            OnItemClickListener onItemClickListener2 = this$0.b;
            List<SensAIActivitySummary> list4 = this$0.c;
            sensAIActivitySummary = list4 != null ? list4.get(i) : null;
            Intrinsics.checkNotNull(sensAIActivitySummary);
            onItemClickListener2.onBowlingAddToCompareClicked(sensAIActivitySummary, z);
        }
    }

    @Nullable
    public final List<SensAIActivitySummary> getEntitySummaryData() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SensAIActivitySummary> list = this.c;
        if (list != null) {
            Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
            Intrinsics.checkNotNull(valueOf);
            return valueOf.intValue();
        }
        return 0;
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.b;
    }

    public final void setData(@NotNull List<SensAIActivitySummary> entitySummaryData) {
        Intrinsics.checkNotNullParameter(entitySummaryData, "entitySummaryData");
        this.c = entitySummaryData;
        notifyDataSetChanged();
    }

    public final void setEntitySummaryData(@Nullable List<SensAIActivitySummary> list) {
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final ViewHolder holder, final int i) {
        String string;
        String str;
        SensAIActivitySummary sensAIActivitySummary;
        SensAIActivitySummary sensAIActivitySummary2;
        SensAIActivitySummary sensAIActivitySummary3;
        SensAIActivitySummary sensAIActivitySummary4;
        SensAIActivitySummary sensAIActivitySummary5;
        SensAIActivitySummary sensAIActivitySummary6;
        SensAIActivitySummary sensAIActivitySummary7;
        SensAIActivitySummary sensAIActivitySummary8;
        SensAIActivitySummary sensAIActivitySummary9;
        Long timestamp;
        SensAIActivitySummary sensAIActivitySummary10;
        SensAIActivitySummary sensAIActivitySummary11;
        SensAIActivitySummary sensAIActivitySummary12;
        SensAIActivitySummary sensAIActivitySummary13;
        SensAIActivitySummary sensAIActivitySummary14;
        SensAIActivitySummary sensAIActivitySummary15;
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getClData1().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.adapter.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SessionInsightsListAdapter.c(SessionInsightsListAdapter.this, i, view);
            }
        });
        holder.getCbAddToCompare().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.sensai.adapter.i
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SessionInsightsListAdapter.d(SessionInsightsListAdapter.ViewHolder.this, this, i, compoundButton, z);
            }
        });
        CheckBox cbAddToCompare = holder.getCbAddToCompare();
        List<SensAIActivitySummary> list = this.c;
        boolean z = true;
        cbAddToCompare.setChecked((list == null || (sensAIActivitySummary15 = list.get(i)) == null || !sensAIActivitySummary15.isAddToCompare()) ? false : true);
        List<SensAIActivitySummary> list2 = this.c;
        Integer num = null;
        if ((list2 == null || (sensAIActivitySummary14 = list2.get(i)) == null || sensAIActivitySummary14.getActivityType() != 1) ? false : true) {
            List<SensAIActivitySummary> list3 = this.c;
            if (list3 == null || (sensAIActivitySummary13 = list3.get(i)) == null || sensAIActivitySummary13.getHand() != 0) {
                z = false;
            }
            if (z) {
                holder.getTvTitle().setText(this.f5381a.getString(R.string.right_hand_batting));
                holder.getIvImage().setImageDrawable(this.f5381a.getDrawable(R.drawable.sens_ai_right_hand_batting));
                holder.getIvSpeed().setImageDrawable(this.f5381a.getDrawable(R.drawable.ic_right_batting));
            } else {
                holder.getTvTitle().setText(this.f5381a.getString(R.string.left_hand_batting));
                holder.getIvImage().setImageDrawable(this.f5381a.getDrawable(R.drawable.sens_ai_left_hand_batting));
                holder.getIvSpeed().setImageDrawable(this.f5381a.getDrawable(R.drawable.ic_left_batting));
            }
            holder.getTvAvgHandSpeedTxt().setText(this.f5381a.getString(R.string.avg_hand_speed));
            holder.getTvTotalShotsTxt().setText(this.f5381a.getString(R.string.shots));
            holder.getIvShots().setImageDrawable(this.f5381a.getDrawable(R.drawable.sens_ai_bat));
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(this.f5381a).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(mContext).isDistanceUnitInMile");
            if (isDistanceUnitInMile.booleanValue()) {
                TextView tvAvgHandSpeed = holder.getTvAvgHandSpeed();
                StringBuilder sb = new StringBuilder();
                PayUtils payUtils = PayUtils.INSTANCE;
                Context context = this.f5381a;
                List<SensAIActivitySummary> list4 = this.c;
                Integer valueOf = (list4 == null || (sensAIActivitySummary12 = list4.get(i)) == null) ? null : Integer.valueOf(sensAIActivitySummary12.getAvgHandSpeed());
                Intrinsics.checkNotNull(valueOf);
                sb.append(payUtils.getSpeedValue(context, valueOf.intValue()));
                sb.append(' ');
                sb.append(this.f5381a.getResources().getString(R.string.mil_per_hours));
                sb.append(' ');
                tvAvgHandSpeed.setText(sb.toString());
            } else {
                TextView tvAvgHandSpeed2 = holder.getTvAvgHandSpeed();
                StringBuilder sb2 = new StringBuilder();
                List<SensAIActivitySummary> list5 = this.c;
                sb2.append((list5 == null || (sensAIActivitySummary10 = list5.get(i)) == null) ? null : Integer.valueOf(sensAIActivitySummary10.getAvgHandSpeed()));
                sb2.append(' ');
                sb2.append(this.f5381a.getResources().getString(R.string.km_per_hours));
                sb2.append(' ');
                tvAvgHandSpeed2.setText(sb2.toString());
            }
            TextView tvTotalShots = holder.getTvTotalShots();
            List<SensAIActivitySummary> list6 = this.c;
            tvTotalShots.setText(String.valueOf((list6 == null || (sensAIActivitySummary11 = list6.get(i)) == null) ? null : Integer.valueOf(sensAIActivitySummary11.getTotalSwings())));
        } else {
            List<SensAIActivitySummary> list7 = this.c;
            if ((list7 == null || (sensAIActivitySummary6 = list7.get(i)) == null || sensAIActivitySummary6.getHand() != 0) ? false : true) {
                holder.getIvImage().setImageDrawable(this.f5381a.getDrawable(R.drawable.sens_ai_right_hand_bowling));
                holder.getIvSpeed().setImageDrawable(this.f5381a.getDrawable(R.drawable.ic_right_bowling));
            } else {
                holder.getIvImage().setImageDrawable(this.f5381a.getDrawable(R.drawable.sens_ai_left_hand_bowling));
                holder.getIvSpeed().setImageDrawable(this.f5381a.getDrawable(R.drawable.ic_left_bowling));
            }
            List<SensAIActivitySummary> list8 = this.c;
            if ((list8 == null || (sensAIActivitySummary5 = list8.get(i)) == null || sensAIActivitySummary5.getHand() != 0) ? false : true) {
                string = this.f5381a.getString(R.string.right_hand);
                Intrinsics.checkNotNullExpressionValue(string, "{\n                mConte…right_hand)\n            }");
            } else {
                string = this.f5381a.getString(R.string.left_hand);
                Intrinsics.checkNotNullExpressionValue(string, "{\n                mConte….left_hand)\n            }");
            }
            List<SensAIActivitySummary> list9 = this.c;
            Integer valueOf2 = (list9 == null || (sensAIActivitySummary4 = list9.get(i)) == null) ? null : Integer.valueOf(sensAIActivitySummary4.getBowlingType());
            if (valueOf2 != null && valueOf2.intValue() == 0) {
                str = string + ' ' + this.f5381a.getString(R.string.fast);
            } else if (valueOf2 != null && valueOf2.intValue() == 1) {
                str = string + ' ' + this.f5381a.getString(R.string.medium_pace);
            } else {
                str = string + ' ' + this.f5381a.getString(R.string.spin);
            }
            holder.getTvTitle().setText(str);
            holder.getTvAvgHandSpeedTxt().setText(this.f5381a.getString(R.string.avg_arm_speed));
            Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(this.f5381a).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(mContext).isDistanceUnitInMile");
            if (isDistanceUnitInMile2.booleanValue()) {
                TextView tvAvgHandSpeed3 = holder.getTvAvgHandSpeed();
                StringBuilder sb3 = new StringBuilder();
                PayUtils payUtils2 = PayUtils.INSTANCE;
                Context context2 = this.f5381a;
                List<SensAIActivitySummary> list10 = this.c;
                Integer valueOf3 = (list10 == null || (sensAIActivitySummary3 = list10.get(i)) == null) ? null : Integer.valueOf(sensAIActivitySummary3.getAvgArmSpeed());
                Intrinsics.checkNotNull(valueOf3);
                sb3.append(payUtils2.getSpeedValue(context2, valueOf3.intValue()));
                sb3.append(' ');
                sb3.append(this.f5381a.getResources().getString(R.string.mil_per_hours));
                sb3.append(' ');
                tvAvgHandSpeed3.setText(sb3.toString());
            } else {
                TextView tvAvgHandSpeed4 = holder.getTvAvgHandSpeed();
                StringBuilder sb4 = new StringBuilder();
                List<SensAIActivitySummary> list11 = this.c;
                sb4.append((list11 == null || (sensAIActivitySummary = list11.get(i)) == null) ? null : Integer.valueOf(sensAIActivitySummary.getAvgArmSpeed()));
                sb4.append(' ');
                sb4.append(this.f5381a.getResources().getString(R.string.km_per_hours));
                sb4.append(' ');
                tvAvgHandSpeed4.setText(sb4.toString());
            }
            holder.getTvTotalShotsTxt().setText(this.f5381a.getString(R.string.total_balls));
            holder.getIvShots().setImageDrawable(this.f5381a.getDrawable(R.drawable.ic_compare_ball));
            TextView tvTotalShots2 = holder.getTvTotalShots();
            List<SensAIActivitySummary> list12 = this.c;
            tvTotalShots2.setText(String.valueOf((list12 == null || (sensAIActivitySummary2 = list12.get(i)) == null) ? null : Integer.valueOf(sensAIActivitySummary2.getTotalBallsBowled())));
        }
        AppUtils.getSimpleDateFormat("dd MMM YYYY, hh:mm aa");
        TextView tvTime = holder.getTvTime();
        List<SensAIActivitySummary> list13 = this.c;
        tvTime.setText(String.valueOf((list13 == null || (sensAIActivitySummary9 = list13.get(i)) == null || (timestamp = sensAIActivitySummary9.getTimestamp()) == null) ? null : PayUtils.getTodayYesterdayStringWithTimeStampAtddMMMYYYY(timestamp.longValue(), this.f5381a)));
        TextView tvDuration = holder.getTvDuration();
        List<SensAIActivitySummary> list14 = this.c;
        tvDuration.setText((list14 == null || (sensAIActivitySummary8 = list14.get(i)) == null) ? null : WorkoutUtils.INSTANCE.getFormattedDuration((int) sensAIActivitySummary8.getDurationSec()));
        List<SensAIActivitySummary> list15 = this.c;
        if (list15 != null && (sensAIActivitySummary7 = list15.get(i)) != null) {
            num = Integer.valueOf(sensAIActivitySummary7.getGoalCompletionPct());
        }
        Intrinsics.checkNotNull(num);
        if (num.intValue() >= 100) {
            holder.getIvAward().setVisibility(0);
        } else {
            holder.getIvAward().setVisibility(4);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SensAiListItemBinding inflate = SensAiListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
