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
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.bleabstract.models.ConfiguredActivities;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivitiesSelectionAdapterNew extends RecyclerView.Adapter<ActivityViewHolderH> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<ActivityCategoriesModel> f2732a;
    public ConfiguredActivities.ActivityInfo[] activityInfoList;
    @NotNull
    public List<ActivitiesListModel> b;
    @NotNull
    public final ItemClickListener c;
    public int d;
    public final boolean e;
    public List<ActivitiesListModel> f;

    /* loaded from: classes2.dex */
    public static final class ActivityViewHolderH extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f2733a;
        @NotNull
        public final TextView b;
        @NotNull
        public final ImageView c;
        @NotNull
        public final ConstraintLayout d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityViewHolderH(@NotNull View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.activity_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.activity_icon)");
            this.f2733a = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.info);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.info)");
            this.c = (ImageView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.activity_name);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.activity_name)");
            this.b = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.activity_list_item_root);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.….activity_list_item_root)");
            this.d = (ConstraintLayout) findViewById4;
        }

        public final void bindView(@NotNull ActivitiesListModel activityListModel) {
            Intrinsics.checkNotNullParameter(activityListModel, "activityListModel");
            RequestBuilder<Drawable> m30load = Glide.with(this.itemView.getContext()).m30load(activityListModel.getIconUrl());
            ImageView imageView = this.f2733a;
            Intrinsics.checkNotNull(imageView);
            m30load.into(imageView);
            TextView textView = this.b;
            String titleInMetric = activityListModel.getTitleInMetric();
            textView.setText(titleInMetric != null ? kotlin.text.m.capitalize(titleInMetric) : null);
        }

        @NotNull
        public final ImageView getActivityIcon() {
            return this.f2733a;
        }

        @NotNull
        public final ImageView getActivityInfo() {
            return this.c;
        }

        @NotNull
        public final TextView getActivityName() {
            return this.b;
        }

        @NotNull
        public final ConstraintLayout getRootItem() {
            return this.d;
        }
    }

    /* loaded from: classes2.dex */
    public interface ItemClickListener {
        void onClicked(@NotNull ActivitiesListModel activitiesListModel);

        void onInfoClicked(@NotNull ActivitiesListModel activitiesListModel);

        void onLongClicked(int i);
    }

    public /* synthetic */ ActivitiesSelectionAdapterNew(Context context, List list, List list2, ItemClickListener itemClickListener, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, list, list2, itemClickListener, i, (i2 & 32) != 0 ? true : z);
    }

    public static final void c(ActivitiesSelectionAdapterNew this$0, ActivitiesListModel activityListModel, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activityListModel, "$activityListModel");
        if (this$0.e) {
            Integer activityId = activityListModel.getActivityId();
            Intrinsics.checkNotNull(activityId);
            this$0.d = activityId.intValue();
            this$0.c.onClicked(activityListModel);
            this$0.notifyDataSetChanged();
        }
    }

    public static final void d(ActivitiesSelectionAdapterNew this$0, ActivitiesListModel activityListModel, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activityListModel, "$activityListModel");
        this$0.c.onInfoClicked(activityListModel);
    }

    @NotNull
    public final ConfiguredActivities.ActivityInfo[] getActivityInfoList() {
        ConfiguredActivities.ActivityInfo[] activityInfoArr = this.activityInfoList;
        if (activityInfoArr != null) {
            return activityInfoArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("activityInfoList");
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ActivitiesListModel> list = this.f;
        if (list != null) {
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("expandableListTitle");
                list = null;
            }
            return list.size();
        }
        return 0;
    }

    public final boolean getShouldAllowSelection() {
        return this.e;
    }

    public final void notifyAdapter(@NotNull List<ActivitiesListModel> expandableListDetail) {
        Intrinsics.checkNotNullParameter(expandableListDetail, "expandableListDetail");
        this.b = expandableListDetail;
        Intrinsics.checkNotNull(expandableListDetail, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>");
        this.f = (ArrayList) expandableListDetail;
        notifyDataSetChanged();
    }

    public final void search(@NotNull String searchStr) {
        Intrinsics.checkNotNullParameter(searchStr, "searchStr");
        this.f = new ArrayList();
        List<ActivitiesListModel> list = this.b;
        if (list != null) {
            for (ActivitiesListModel activitiesListModel : list) {
                List<ActivitiesListModel> list2 = null;
                if (searchStr != null && !Intrinsics.areEqual(searchStr, "")) {
                    String titleInMetric = activitiesListModel.getTitleInMetric();
                    if (!(titleInMetric == null || titleInMetric.length() == 0)) {
                        String titleInMetric2 = activitiesListModel.getTitleInMetric();
                        Intrinsics.checkNotNull(titleInMetric2);
                        String lowerCase = titleInMetric2.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                        String lowerCase2 = searchStr.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null)) {
                            List<ActivitiesListModel> list3 = this.f;
                            if (list3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("expandableListTitle");
                            } else {
                                list2 = list3;
                            }
                            Intrinsics.checkNotNull(list2);
                            list2.add(activitiesListModel);
                        }
                    }
                } else {
                    List<ActivitiesListModel> list4 = this.f;
                    if (list4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("expandableListTitle");
                    } else {
                        list2 = list4;
                    }
                    Intrinsics.checkNotNull(list2);
                    list2.add(activitiesListModel);
                }
            }
        }
        notifyDataSetChanged();
    }

    public final void setActivityInfoList(@NotNull ConfiguredActivities.ActivityInfo[] activityInfoArr) {
        Intrinsics.checkNotNullParameter(activityInfoArr, "<set-?>");
        this.activityInfoList = activityInfoArr;
    }

    public ActivitiesSelectionAdapterNew(@NotNull Context context, @NotNull List<ActivityCategoriesModel> originalList, @NotNull List<ActivitiesListModel> activitiesList, @NotNull ItemClickListener listener, int i, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(originalList, "originalList");
        Intrinsics.checkNotNullParameter(activitiesList, "activitiesList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f2732a = originalList;
        this.b = activitiesList;
        this.c = listener;
        this.d = i;
        this.e = z;
        if (activitiesList.size() > 0) {
            List<ActivitiesListModel> list = this.b;
            Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>");
            this.f = (ArrayList) list;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityViewHolderH holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<ActivitiesListModel> list = this.f;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("expandableListTitle");
            list = null;
        }
        final ActivitiesListModel activitiesListModel = list.get(i);
        holder.bindView(activitiesListModel);
        int i2 = this.d;
        Integer activityId = activitiesListModel.getActivityId();
        if (activityId != null && i2 == activityId.intValue()) {
            holder.getRootItem().setBackgroundResource(R.drawable.activity_icon_selected);
        } else {
            holder.getRootItem().setBackgroundResource(R.drawable.activity_icon_unselected);
        }
        holder.getActivityName().setAlpha(1.0f);
        holder.getRootItem().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitiesSelectionAdapterNew.c(ActivitiesSelectionAdapterNew.this, activitiesListModel, view);
            }
        });
        holder.getActivityInfo().setVisibility(0);
        holder.getActivityInfo().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitiesSelectionAdapterNew.d(ActivitiesSelectionAdapterNew.this, activitiesListModel, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityViewHolderH onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_selection_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ActivityViewHolderH(view);
    }
}
