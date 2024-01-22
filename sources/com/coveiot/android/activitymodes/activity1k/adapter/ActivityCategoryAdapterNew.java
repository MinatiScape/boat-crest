package com.coveiot.android.activitymodes.activity1k.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.bleabstract.models.ConfiguredActivities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.s;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityCategoryAdapterNew extends BaseExpandableListAdapter {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2738a;
    public ConfiguredActivities.ActivityInfo[] activityInfoList;
    @NotNull
    public List<ActivityCategoriesModel> b;
    @NotNull
    public HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> c;
    @NotNull
    public final SearchAction d;
    @NotNull
    public final ItemClickListener e;
    public int f;
    public int g;
    public int h;
    @NotNull
    public List<ActivityCategoriesModel> i;
    @NotNull
    public HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> j;

    /* loaded from: classes2.dex */
    public interface ItemClickListener {
        void onChildClicked(@NotNull ActivityCategoriesModel activityCategoriesModel, @Nullable ActivitiesListModel activitiesListModel);

        void onHeaderClicked(@Nullable ActivityCategoriesModel activityCategoriesModel, int i);

        void onLongClicked(int i);
    }

    /* loaded from: classes2.dex */
    public interface SearchAction {
        void collapseGroup(int i);

        void expandGroup(int i);
    }

    public ActivityCategoryAdapterNew(@NotNull Context context, @NotNull List<ActivityCategoriesModel> originalList, @NotNull HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> expandableListDetailOriginal, @NotNull SearchAction searchListener, @NotNull ItemClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(originalList, "originalList");
        Intrinsics.checkNotNullParameter(expandableListDetailOriginal, "expandableListDetailOriginal");
        Intrinsics.checkNotNullParameter(searchListener, "searchListener");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f2738a = context;
        this.b = originalList;
        this.c = expandableListDetailOriginal;
        this.d = searchListener;
        this.e = listener;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        Intrinsics.checkNotNull(originalList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel>");
        this.i = (ArrayList) originalList;
        Object clone = this.c.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>>{ kotlin.collections.TypeAliasesKt.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>> }");
        this.j = (HashMap) clone;
    }

    public static final void c(ActivityCategoryAdapterNew this$0, Ref.ObjectRef activityListModel, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activityListModel, "$activityListModel");
        Integer activityId = ((ActivitiesListModel) activityListModel.element).getActivityId();
        Intrinsics.checkNotNull(activityId);
        this$0.f = activityId.intValue();
        Integer categoryId = ((ActivitiesListModel) activityListModel.element).getCategoryId();
        Intrinsics.checkNotNull(categoryId);
        this$0.h = categoryId.intValue();
        this$0.g = -1;
        this$0.e.onHeaderClicked(null, -1);
        this$0.e.onChildClicked(this$0.i.get(i), (ActivitiesListModel) activityListModel.element);
        this$0.notifyDataSetChanged();
    }

    public static final void d(ActivityCategoryAdapterNew this$0, int i, Ref.ObjectRef entityActivityCategory, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(entityActivityCategory, "$entityActivityCategory");
        this$0.g = i;
        this$0.f = -1;
        this$0.e.onChildClicked((ActivityCategoriesModel) entityActivityCategory.element, null);
        this$0.e.onHeaderClicked((ActivityCategoriesModel) entityActivityCategory.element, i);
        this$0.notifyDataSetChanged();
    }

    public final boolean e(ActivitiesListModel activitiesListModel) {
        if (this.activityInfoList != null) {
            for (ConfiguredActivities.ActivityInfo activityInfo : getActivityInfoList()) {
                if (activitiesListModel.getFwActId() == activityInfo.getActivityId()) {
                    return true;
                }
            }
        }
        return false;
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

    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public Object getChild(int i, int i2) {
        HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap = this.j;
        List<ActivityCategoriesModel> list = this.i;
        Intrinsics.checkNotNull(list);
        List<ActivitiesListModel> list2 = hashMap.get(list.get(i));
        ActivitiesListModel activitiesListModel = list2 != null ? list2.get(i2) : null;
        Intrinsics.checkNotNull(activitiesListModel);
        return activitiesListModel;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    /* JADX WARN: Type inference failed for: r6v2, types: [T, com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel] */
    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public View getChildView(final int i, int i2, boolean z, @Nullable View view, @NotNull ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Object child = getChild(i, i2);
        Intrinsics.checkNotNull(child, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel");
        objectRef.element = (ActivitiesListModel) child;
        if (view == null) {
            Object systemService = this.f2738a.getSystemService("layout_inflater");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
            view = ((LayoutInflater) systemService).inflate(R.layout.activity_selection_item, (ViewGroup) null);
        }
        ImageView imageView = view != null ? (ImageView) view.findViewById(R.id.activity_icon) : null;
        Intrinsics.checkNotNull(imageView);
        TextView textView = view != null ? (TextView) view.findViewById(R.id.activity_name) : null;
        Intrinsics.checkNotNull(textView);
        ConstraintLayout constraintLayout = view != null ? (ConstraintLayout) view.findViewById(R.id.activity_list_item_root) : null;
        Intrinsics.checkNotNull(constraintLayout);
        RequestBuilder<Drawable> m30load = Glide.with(view.getContext()).m30load(((ActivitiesListModel) objectRef.element).getIconUrl());
        Intrinsics.checkNotNull(imageView);
        m30load.into(imageView);
        String titleInMetric = ((ActivitiesListModel) objectRef.element).getTitleInMetric();
        textView.setText(titleInMetric != null ? kotlin.text.m.capitalize(titleInMetric) : null);
        if (e((ActivitiesListModel) objectRef.element)) {
            textView.setAlpha(0.2f);
            constraintLayout.setOnClickListener(null);
        } else {
            textView.setAlpha(1.0f);
            constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.j
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityCategoryAdapterNew.c(ActivityCategoryAdapterNew.this, objectRef, i, view2);
                }
            });
        }
        int i3 = this.f;
        Integer activityId = ((ActivitiesListModel) objectRef.element).getActivityId();
        if (activityId != null && i3 == activityId.intValue()) {
            int i4 = this.h;
            Integer categoryId = ((ActivitiesListModel) objectRef.element).getCategoryId();
            if (categoryId != null && i4 == categoryId.intValue()) {
                constraintLayout.setBackgroundResource(R.drawable.activity_icon_selected);
                Intrinsics.checkNotNull(view);
                return view;
            }
        }
        constraintLayout.setBackgroundResource(R.drawable.activity_icon_unselected);
        Intrinsics.checkNotNull(view);
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        List<ActivitiesListModel> list = this.j.get(this.i.get(i));
        Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
        Intrinsics.checkNotNull(valueOf);
        return valueOf.intValue();
    }

    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public Object getGroup(int i) {
        return this.i.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.i.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel] */
    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public View getGroupView(final int i, boolean z, @Nullable View view, @NotNull ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Object group = getGroup(i);
        Intrinsics.checkNotNull(group, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel");
        objectRef.element = (ActivityCategoriesModel) group;
        if (view == null) {
            Object systemService = this.f2738a.getSystemService("layout_inflater");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
            view = ((LayoutInflater) systemService).inflate(R.layout.item_category, parent, false);
        }
        TextView textView = view != null ? (TextView) view.findViewById(R.id.tv_name) : null;
        ImageView imageView = view != null ? (ImageView) view.findViewById(R.id.img_icon) : null;
        LinearLayout linearLayout = view != null ? (LinearLayout) view.findViewById(R.id.card_view) : null;
        if (this.g == i) {
            if (linearLayout != null) {
                linearLayout.setBackgroundResource(R.drawable.activity_icon_selected);
            }
        } else if (linearLayout != null) {
            linearLayout.setBackgroundResource(R.drawable.activity_icon_unselected);
        }
        if (textView != null) {
            textView.setText(((ActivityCategoriesModel) objectRef.element).getTitle());
        }
        Context context = view != null ? view.getContext() : null;
        Intrinsics.checkNotNull(context);
        RequestBuilder<Drawable> m30load = Glide.with(context).m30load(((ActivityCategoriesModel) objectRef.element).getIconUrl());
        Intrinsics.checkNotNull(imageView);
        m30load.into(imageView);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityCategoryAdapterNew.d(ActivityCategoryAdapterNew.this, i, objectRef, view2);
                }
            });
        }
        Intrinsics.checkNotNull(view);
        return view;
    }

    @NotNull
    public final ItemClickListener getListener() {
        return this.e;
    }

    @NotNull
    public final SearchAction getSearchListener() {
        return this.d;
    }

    public final int getSelectedPosition() {
        return this.g;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public final void notifyAdapter(@NotNull List<ActivityCategoriesModel> expandableListTitle, @NotNull HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> expandableListDetail) {
        Intrinsics.checkNotNullParameter(expandableListTitle, "expandableListTitle");
        Intrinsics.checkNotNullParameter(expandableListDetail, "expandableListDetail");
        this.i = TypeIntrinsics.asMutableList(expandableListTitle);
        this.b = TypeIntrinsics.asMutableList(expandableListTitle);
        Object clone = expandableListDetail.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>>{ kotlin.collections.TypeAliasesKt.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>> }");
        this.j = (HashMap) clone;
        Object clone2 = expandableListDetail.clone();
        Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>>{ kotlin.collections.TypeAliasesKt.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>> }");
        this.c = (HashMap) clone2;
        notifyDataSetChanged();
    }

    public final void search(@NotNull String searchStr) {
        int i;
        Intrinsics.checkNotNullParameter(searchStr, "searchStr");
        this.i = new ArrayList();
        this.j.clear();
        Iterator<ActivityCategoriesModel> it = this.b.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            ActivityCategoriesModel next = it.next();
            if (searchStr != null && !Intrinsics.areEqual(searchStr, "")) {
                String title = next.getTitle();
                Intrinsics.checkNotNull(title);
                String lowerCase = title.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                String lowerCase2 = searchStr.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null)) {
                    List<ActivityCategoriesModel> list = this.i;
                    Intrinsics.checkNotNull(list);
                    list.add(next);
                    this.j.put(next, (List) s.getValue(this.c, next));
                }
            } else {
                this.j.put(next, (List) s.getValue(this.c, next));
                List<ActivityCategoriesModel> list2 = this.i;
                Intrinsics.checkNotNull(list2);
                list2.add(next);
            }
        }
        if (this.i.size() == 0) {
            for (ActivityCategoriesModel activityCategoriesModel : this.b) {
                ArrayList arrayList = new ArrayList();
                HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap = this.c;
                List<ActivitiesListModel> list3 = hashMap != null ? (List) s.getValue(hashMap, activityCategoriesModel) : null;
                Intrinsics.checkNotNull(list3);
                boolean z = false;
                for (ActivitiesListModel activitiesListModel : list3) {
                    if (searchStr != null && !Intrinsics.areEqual(searchStr, "")) {
                        String titleInMetric = activitiesListModel.getTitleInMetric();
                        Intrinsics.checkNotNull(titleInMetric);
                        String lowerCase3 = titleInMetric.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                        String lowerCase4 = searchStr.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) lowerCase4, false, 2, (Object) null)) {
                            arrayList.add(activitiesListModel);
                            z = true;
                        }
                    }
                }
                if (z) {
                    this.i.add(activityCategoriesModel);
                    this.j.put(activityCategoriesModel, arrayList);
                }
            }
        }
        notifyDataSetChanged();
        if (searchStr != null && !Intrinsics.areEqual(searchStr, "")) {
            int size = this.i.size();
            while (i < size) {
                this.d.expandGroup(i);
                i++;
            }
            return;
        }
        int size2 = this.i.size();
        while (i < size2) {
            this.d.collapseGroup(i);
            i++;
        }
    }

    public final void setActivityInfoList(@NotNull ConfiguredActivities.ActivityInfo[] activityInfoArr) {
        Intrinsics.checkNotNullParameter(activityInfoArr, "<set-?>");
        this.activityInfoList = activityInfoArr;
    }

    public final void setSelectedPosition(int i) {
        this.g = i;
    }
}
