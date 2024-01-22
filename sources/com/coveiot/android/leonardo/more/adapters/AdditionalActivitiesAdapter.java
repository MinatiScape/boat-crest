package com.coveiot.android.leonardo.more.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.models.ActivitiesItem;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AdditionalActivitiesAdapter extends RecyclerView.Adapter<AdditionalActivitiesViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<ActivitiesItem> f5038a;
    @NotNull
    public Context b;
    @Nullable
    public ActivitiesItem c;
    public ActivitySelectionListener listner;

    /* loaded from: classes5.dex */
    public interface ActivitySelectionListener {
        void onActivitySelected(@NotNull ActivitiesItem activitiesItem);
    }

    /* loaded from: classes5.dex */
    public final class AdditionalActivitiesViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ConstraintLayout f5039a;
        @NotNull
        public final TextView b;
        @NotNull
        public final ImageView c;
        public final /* synthetic */ AdditionalActivitiesAdapter d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AdditionalActivitiesViewHolder(@NotNull AdditionalActivitiesAdapter additionalActivitiesAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.d = additionalActivitiesAdapter;
            View findViewById = itemView.findViewById(R.id.clSelect);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById<Co…intLayout>(R.id.clSelect)");
            this.f5039a = (ConstraintLayout) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvActivityName);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById<Te…iew>(R.id.tvActivityName)");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.ivActivityIcon);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById<Im…iew>(R.id.ivActivityIcon)");
            this.c = (ImageView) findViewById3;
        }

        public final void bindView(@NotNull ActivitiesItem activitiesItem) {
            Intrinsics.checkNotNullParameter(activitiesItem, "activitiesItem");
            this.b.setText(activitiesItem.getActivityName());
            ViewUtilsKt.loadImagesWithGlideExt(this.c, activitiesItem.getIconUrl());
        }

        @NotNull
        public final ConstraintLayout getClSelect() {
            return this.f5039a;
        }

        @NotNull
        public final ImageView getIvActivityIcon() {
            return this.c;
        }

        @NotNull
        public final TextView getTvActivityName() {
            return this.b;
        }

        public final void selected(boolean z) {
            if (z) {
                this.f5039a.setBackgroundResource(R.drawable.additional_activity_selection_bg);
                this.b.setTextAppearance(R.style.semi_bold);
                this.b.setTextColor(this.d.getContext().getResources().getColor(R.color.main_text_color));
                return;
            }
            this.f5039a.setBackgroundResource(R.drawable.additional_activities_background);
            this.b.setTextAppearance(R.style.regular);
            this.b.setTextColor(this.d.getContext().getResources().getColor(R.color.secondary_text_color));
        }
    }

    public AdditionalActivitiesAdapter(@NotNull ArrayList<ActivitiesItem> additionalActivitiesList, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(additionalActivitiesList, "additionalActivitiesList");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5038a = additionalActivitiesList;
        this.b = context;
    }

    public static final void b(AdditionalActivitiesAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c = this$0.f5038a.get(i);
        this$0.notifyDataSetChanged();
        ActivitySelectionListener listner = this$0.getListner();
        ActivitiesItem activitiesItem = this$0.c;
        Intrinsics.checkNotNull(activitiesItem);
        listner.onActivitySelected(activitiesItem);
    }

    @NotNull
    public final ArrayList<ActivitiesItem> getAdditionalActivitiesList() {
        return this.f5038a;
    }

    @NotNull
    public final Context getContext() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5038a.size();
    }

    @NotNull
    public final ActivitySelectionListener getListner() {
        ActivitySelectionListener activitySelectionListener = this.listner;
        if (activitySelectionListener != null) {
            return activitySelectionListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("listner");
        return null;
    }

    @Nullable
    public final ActivitiesItem getSelectedActivityItem() {
        return this.c;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.b = context;
    }

    public final void setListner(@NotNull ActivitySelectionListener activitySelectionListener) {
        Intrinsics.checkNotNullParameter(activitySelectionListener, "<set-?>");
        this.listner = activitySelectionListener;
    }

    public final void setSelectedActivityItem(@Nullable ActivitiesItem activitiesItem) {
        this.c = activitiesItem;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull AdditionalActivitiesViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ActivitiesItem activitiesItem = this.f5038a.get(i);
        Intrinsics.checkNotNullExpressionValue(activitiesItem, "additionalActivitiesList.get(position)");
        holder.bindView(activitiesItem);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdditionalActivitiesAdapter.b(AdditionalActivitiesAdapter.this, i, view);
            }
        });
        holder.selected(Intrinsics.areEqual(this.c, this.f5038a.get(i)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public AdditionalActivitiesViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_additional_activities, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …ctivities, parent, false)");
        return new AdditionalActivitiesViewHolder(this, inflate);
    }
}
