package com.coveiot.android.activitymodes.workoutVideos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.databinding.CustomTabWorkoutVideosBinding;
import com.coveiot.android.activitymodes.workoutVideos.models.WorkoutCategoriesBean;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityWorkoutVideosCategoryAdapter extends RecyclerView.Adapter<ActivityHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2895a;
    @Nullable
    public ItemClickListener b;
    public int c;
    @Nullable
    public List<WorkoutCategoriesBean> d;

    /* loaded from: classes2.dex */
    public final class ActivityHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final CustomTabWorkoutVideosBinding f2896a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityHolder(@NotNull ActivityWorkoutVideosCategoryAdapter activityWorkoutVideosCategoryAdapter, CustomTabWorkoutVideosBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f2896a = binding;
        }

        @NotNull
        public final CustomTabWorkoutVideosBinding getBinding() {
            return this.f2896a;
        }
    }

    /* loaded from: classes2.dex */
    public interface ItemClickListener {
        void onItemClick(@Nullable String str);
    }

    /* loaded from: classes2.dex */
    public static final class a extends Lambda implements Function2<Integer, Integer, Unit> {
        public a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
            invoke(num.intValue(), num2.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i, int i2) {
            WorkoutCategoriesBean workoutCategoriesBean;
            ItemClickListener itemClickListener = ActivityWorkoutVideosCategoryAdapter.this.b;
            if (itemClickListener != null) {
                List<WorkoutCategoriesBean> categoriesList = ActivityWorkoutVideosCategoryAdapter.this.getCategoriesList();
                itemClickListener.onItemClick((categoriesList == null || (workoutCategoriesBean = categoriesList.get(i)) == null || (r2 = workoutCategoriesBean.getCategoryId()) == null) ? null : null);
            }
        }
    }

    public ActivityWorkoutVideosCategoryAdapter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2895a = context;
    }

    public static final void c(Function2 event, RecyclerView.ViewHolder this_listen, View view) {
        Intrinsics.checkNotNullParameter(event, "$event");
        Intrinsics.checkNotNullParameter(this_listen, "$this_listen");
        event.invoke(Integer.valueOf(this_listen.getAdapterPosition()), Integer.valueOf(this_listen.getItemViewType()));
    }

    public static final void d(ActivityWorkoutVideosCategoryAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c = i;
        this$0.notifyDataSetChanged();
        ItemClickListener itemClickListener = this$0.b;
        if (itemClickListener != null) {
            Intrinsics.checkNotNull(itemClickListener);
            List<WorkoutCategoriesBean> list = this$0.d;
            WorkoutCategoriesBean workoutCategoriesBean = list != null ? list.get(i) : null;
            Intrinsics.checkNotNull(workoutCategoriesBean);
            itemClickListener.onItemClick(workoutCategoriesBean.getCategoryId());
        }
    }

    @Nullable
    public final List<WorkoutCategoriesBean> getCategoriesList() {
        return this.d;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<WorkoutCategoriesBean> list = this.d;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public final int getRow_index() {
        return this.c;
    }

    @NotNull
    public final <T extends RecyclerView.ViewHolder> T listen(@NotNull final T t, @NotNull final Function2<? super Integer, ? super Integer, Unit> event) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(event, "event");
        t.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.adapters.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutVideosCategoryAdapter.c(Function2.this, t, view);
            }
        });
        return t;
    }

    public final void setCategoriesList(@Nullable List<WorkoutCategoriesBean> list) {
        this.d = list;
    }

    public final void setData(@NotNull List<WorkoutCategoriesBean> categoriesList) {
        Intrinsics.checkNotNullParameter(categoriesList, "categoriesList");
        this.d = categoriesList;
        notifyDataSetChanged();
    }

    public final void setItemClickListener(@Nullable ItemClickListener itemClickListener) {
        this.b = itemClickListener;
    }

    public final void setRow_index(int i) {
        this.c = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.adapters.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutVideosCategoryAdapter.d(ActivityWorkoutVideosCategoryAdapter.this, i, view);
            }
        });
        if (this.c == i) {
            holder.getBinding().tabTitle.setBackgroundResource(R.drawable.rounded_red_border_grey_background_symptoms);
            holder.getBinding().tabTitle.setTypeface(ResourcesCompat.getFont(this.f2895a, R.font.metropolis_bold));
            holder.getBinding().tabTitle.setTextColor(this.f2895a.getColor(R.color.white));
        } else {
            holder.getBinding().tabTitle.setBackgroundResource(R.drawable.rounded_grey_with_border_background_40dp);
            holder.getBinding().tabTitle.setTypeface(ResourcesCompat.getFont(this.f2895a, R.font.metropolis_regular));
            holder.getBinding().tabTitle.setTextColor(this.f2895a.getColor(R.color.info_text_color));
        }
        TextView textView = holder.getBinding().tabTitle;
        List<WorkoutCategoriesBean> list = this.d;
        WorkoutCategoriesBean workoutCategoriesBean = list != null ? list.get(i) : null;
        Intrinsics.checkNotNull(workoutCategoriesBean);
        textView.setText(workoutCategoriesBean.getCategoryName());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        CustomTabWorkoutVideosBinding binding = (CustomTabWorkoutVideosBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_tab_workout_videos, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return (ActivityHolder) listen(new ActivityHolder(this, binding), new a());
    }
}
