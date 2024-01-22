package com.coveiot.android.activitymodes.activity1k.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivitiesSearchAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<ActivitiesListModel> f2729a;
    @NotNull
    public final ItemClickListener b;
    public List<ActivitiesListModel> c;

    /* loaded from: classes2.dex */
    public static final class ActivityViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f2730a;
        @NotNull
        public final TextView b;
        @NotNull
        public final ConstraintLayout c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityViewHolder(@NotNull View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.activity_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.activity_icon)");
            this.f2730a = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.activity_name);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.activity_name)");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.activity_list_item_root);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.….activity_list_item_root)");
            this.c = (ConstraintLayout) findViewById3;
        }

        public final void bindView(@NotNull ActivitiesListModel activityListModel) {
            Intrinsics.checkNotNullParameter(activityListModel, "activityListModel");
            RequestBuilder<Drawable> m30load = Glide.with(this.itemView.getContext()).m30load(activityListModel.getIconUrl());
            ImageView imageView = this.f2730a;
            Intrinsics.checkNotNull(imageView);
            m30load.into(imageView);
            TextView textView = this.b;
            String titleInMetric = activityListModel.getTitleInMetric();
            textView.setText(titleInMetric != null ? kotlin.text.m.capitalize(titleInMetric) : null);
        }

        @NotNull
        public final ImageView getActivityIcon() {
            return this.f2730a;
        }

        @NotNull
        public final TextView getActivityName() {
            return this.b;
        }

        @NotNull
        public final ConstraintLayout getRootItem() {
            return this.c;
        }
    }

    /* loaded from: classes2.dex */
    public interface ItemClickListener {
        void onClicked(@NotNull ActivitiesListModel activitiesListModel);

        void onLongClicked(int i);
    }

    public ActivitiesSearchAdapter(@NotNull Context context, @NotNull List<ActivitiesListModel> activitiesList, @NotNull ItemClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activitiesList, "activitiesList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f2729a = activitiesList;
        this.b = listener;
    }

    public static final void b(ActivitiesSearchAdapter this$0, ActivitiesListModel activityListModel, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activityListModel, "$activityListModel");
        this$0.b.onClicked(activityListModel);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ActivitiesListModel> list = this.c;
        if (list != null) {
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("expandableListTitle");
                list = null;
            }
            return list.size();
        }
        return 0;
    }

    public final void notifyAdapter(@NotNull List<ActivitiesListModel> expandableListDetail) {
        Intrinsics.checkNotNullParameter(expandableListDetail, "expandableListDetail");
        this.f2729a = expandableListDetail;
        Intrinsics.checkNotNull(expandableListDetail, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>");
        this.c = (ArrayList) expandableListDetail;
    }

    public final void search(@NotNull String searchStr) {
        Intrinsics.checkNotNullParameter(searchStr, "searchStr");
        this.c = new ArrayList();
        for (ActivitiesListModel activitiesListModel : this.f2729a) {
            List<ActivitiesListModel> list = null;
            if (searchStr != null && !Intrinsics.areEqual(searchStr, "")) {
                String titleInMetric = activitiesListModel.getTitleInMetric();
                Intrinsics.checkNotNull(titleInMetric);
                String lowerCase = titleInMetric.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                String lowerCase2 = searchStr.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null)) {
                    List<ActivitiesListModel> list2 = this.c;
                    if (list2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("expandableListTitle");
                    } else {
                        list = list2;
                    }
                    list.add(activitiesListModel);
                }
            } else {
                List<ActivitiesListModel> list3 = this.c;
                if (list3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("expandableListTitle");
                } else {
                    list = list3;
                }
                list.add(activitiesListModel);
            }
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<ActivitiesListModel> list = this.c;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("expandableListTitle");
            list = null;
        }
        final ActivitiesListModel activitiesListModel = list.get(i);
        holder.bindView(activitiesListModel);
        holder.getRootItem().setBackgroundResource(R.drawable.activity_icon_unselected);
        holder.getRootItem().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitiesSearchAdapter.b(ActivitiesSearchAdapter.this, activitiesListModel, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ActivityViewHolder(view);
    }
}
