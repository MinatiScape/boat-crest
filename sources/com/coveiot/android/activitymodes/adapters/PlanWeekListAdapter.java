package com.coveiot.android.activitymodes.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationActivity;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class PlanWeekListAdapter extends RecyclerView.Adapter<PlanWeekHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<EntityPreparationWeek> f2783a = new ArrayList();
    @Nullable
    public WeekSelectionListener b;
    @Nullable
    public Context c;

    /* loaded from: classes2.dex */
    public final class PlanWeekHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f2784a;
        public final TextView b;
        @NotNull
        public final CardView c;
        @NotNull
        public final TextView d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlanWeekHolder(@NotNull PlanWeekListAdapter planWeekListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f2784a = (TextView) itemView.findViewById(R.id.week_target);
            this.b = (TextView) itemView.findViewById(R.id.week_name);
            View findViewById = itemView.findViewById(R.id.cardview_week);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.cardview_week)");
            this.c = (CardView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.activity_count);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.activity_count)");
            this.d = (TextView) findViewById2;
        }

        @NotNull
        public final TextView getActivityCount() {
            return this.d;
        }

        @NotNull
        public final CardView getCardviewWeek() {
            return this.c;
        }

        public final TextView getWeekName() {
            return this.b;
        }

        public final TextView getWeekTarget() {
            return this.f2784a;
        }
    }

    /* loaded from: classes2.dex */
    public interface WeekSelectionListener {
        void onWeekSelected(@NotNull EntityPreparationWeek entityPreparationWeek);
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.adapters.PlanWeekListAdapter$onBindViewHolder$1", f = "PlanWeekListAdapter.kt", i = {}, l = {56}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ PlanWeekHolder $holder;
        public final /* synthetic */ int $position;
        public int label;
        public final /* synthetic */ PlanWeekListAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(PlanWeekHolder planWeekHolder, PlanWeekListAdapter planWeekListAdapter, int i, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$holder = planWeekHolder;
            this.this$0 = planWeekListAdapter;
            this.$position = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$holder, this.this$0, this.$position, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
                Context context = this.$holder.itemView.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "holder.itemView.context");
                int week_number = this.this$0.getPlanWeeksList().get(this.$position).getWeek_number();
                this.label = 1;
                obj = companion.getInstance(context).getDayLevelInfoWithOutLiveData(week_number, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            Ref.IntRef intRef = new Ref.IntRef();
            for (EntityPreparationDay entityPreparationDay : (List) obj) {
                if (entityPreparationDay.getActivities() != null) {
                    for (EntityPreparationActivity entityPreparationActivity : entityPreparationDay.getActivities()) {
                        if (entityPreparationActivity.getTarget() != null) {
                            intRef.element++;
                        }
                    }
                }
            }
            this.$holder.getActivityCount().setText(String.valueOf(intRef.element));
            return Unit.INSTANCE;
        }
    }

    public static final void c(PlanWeekListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WeekSelectionListener weekSelectionListener = this$0.b;
        if (weekSelectionListener != null) {
            Intrinsics.checkNotNull(weekSelectionListener);
            weekSelectionListener.onWeekSelected(this$0.f2783a.get(i));
            return;
        }
        throw new RuntimeException("Set the WeekSelectionListener first");
    }

    public final boolean b(EntityPreparationWeek entityPreparationWeek) {
        return entityPreparationWeek.getDaysRange().contains(AppUtils.formatDate(new Date(), "yyyy-MM-dd"));
    }

    public final void d(View view, int i) {
        if (view instanceof ViewGroup) {
            int i2 = 0;
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount() - 1;
            if (childCount < 0) {
                return;
            }
            while (true) {
                View childAt = viewGroup.getChildAt(i2);
                Intrinsics.checkNotNullExpressionValue(childAt, "itemView.getChildAt(i)");
                d(childAt, i);
                if (i2 == childCount) {
                    return;
                }
                i2++;
            }
        } else if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2783a.size();
    }

    @Nullable
    public final Context getMContext() {
        return this.c;
    }

    @Nullable
    public final WeekSelectionListener getMWeekSelectionListener() {
        return this.b;
    }

    @NotNull
    public final List<EntityPreparationWeek> getPlanWeeksList() {
        return this.f2783a;
    }

    public final void setMContext(@Nullable Context context) {
        this.c = context;
    }

    public final void setMWeekSelectionListener(@Nullable WeekSelectionListener weekSelectionListener) {
        this.b = weekSelectionListener;
    }

    public final void setPlanWeekList(@NotNull List<EntityPreparationWeek> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.f2783a = list;
    }

    public final void setPlanWeeksList(@NotNull List<EntityPreparationWeek> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f2783a = list;
    }

    public final void setWeekSelectionListener(@NotNull WeekSelectionListener weekSelectionListener) {
        Intrinsics.checkNotNullParameter(weekSelectionListener, "weekSelectionListener");
        this.b = weekSelectionListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull PlanWeekHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getWeekName().setText(this.f2783a.get(i).getName());
        if (!UserDataManager.getInstance(this.c).isDistanceUnitInMile().booleanValue()) {
            Context context = this.c;
            Intrinsics.checkNotNull(context);
            ((TextView) holder.itemView.findViewById(R.id.textView9)).setText(context.getResources().getString(R.string.km));
            holder.getWeekTarget().setText(String.valueOf(this.f2783a.get(i).getWeeklyTarget() / 1000.0f));
        } else {
            Context context2 = this.c;
            Intrinsics.checkNotNull(context2);
            ((TextView) holder.itemView.findViewById(R.id.textView9)).setText(context2.getResources().getString(R.string.mil));
            holder.getWeekTarget().setText(String.valueOf(WorkoutUtils.INSTANCE.convertKMToMiles(this.f2783a.get(i).getWeeklyTarget() / 1000.0f)));
        }
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(holder, this, i, null), 2, null);
        if (b(this.f2783a.get(i))) {
            View view = holder.itemView;
            int i2 = R.id.cardview_week;
            ((CardView) view.findViewById(i2)).setCardBackgroundColor((ColorStateList) null);
            ((CardView) holder.itemView.findViewById(i2)).setBackgroundResource(R.drawable.button_gradiant_bg);
            Resources resources = holder.itemView.getContext().getResources();
            int i3 = R.color.main_text_color;
            ((TextView) holder.itemView.findViewById(R.id.textView11)).setTextColor(resources.getColor(i3));
            ((TextView) holder.itemView.findViewById(R.id.week_target)).setTextColor(holder.itemView.getContext().getResources().getColor(i3));
            ((TextView) holder.itemView.findViewById(R.id.week_name)).setTextColor(holder.itemView.getContext().getResources().getColor(i3));
            ((TextView) holder.itemView.findViewById(R.id.textView9)).setTextColor(holder.itemView.getContext().getResources().getColor(i3));
            ((TextView) holder.itemView.findViewById(R.id.activity_count)).setTextColor(holder.itemView.getContext().getResources().getColor(i3));
            View view2 = holder.itemView;
            Intrinsics.checkNotNullExpressionValue(view2, "holder.itemView");
            d(view2, R.color.white);
        } else {
            ((CardView) holder.itemView.findViewById(R.id.cardview_week)).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.color_333131));
            Resources resources2 = holder.itemView.getContext().getResources();
            int i4 = R.color.main_text_color;
            ((TextView) holder.itemView.findViewById(R.id.textView11)).setTextColor(resources2.getColor(i4));
            ((TextView) holder.itemView.findViewById(R.id.week_target)).setTextColor(holder.itemView.getContext().getResources().getColor(i4));
            ((TextView) holder.itemView.findViewById(R.id.week_name)).setTextColor(holder.itemView.getContext().getResources().getColor(R.color.colorPrimary));
            ((TextView) holder.itemView.findViewById(R.id.textView9)).setTextColor(holder.itemView.getContext().getResources().getColor(i4));
            ((TextView) holder.itemView.findViewById(R.id.activity_count)).setTextColor(holder.itemView.getContext().getResources().getColor(i4));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                PlanWeekListAdapter.c(PlanWeekListAdapter.this, i, view3);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public PlanWeekHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.c = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_week_details, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…      false\n            )");
        return new PlanWeekHolder(this, inflate);
    }
}
