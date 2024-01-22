package com.coveiot.android.activitymodes.activity1k.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.s;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityInformationAdapter extends BaseExpandableListAdapter {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2739a;
    @NotNull
    public List<ActivityCategoriesModel> b;
    @NotNull
    public HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> c;
    @NotNull
    public final SearchAction d;
    @NotNull
    public List<ActivityCategoriesModel> e;
    @NotNull
    public HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> f;

    /* loaded from: classes2.dex */
    public interface SearchAction {
        void collapseGroup(int i);

        void expandGroup(int i);
    }

    public ActivityInformationAdapter(@NotNull Context context, @NotNull List<ActivityCategoriesModel> originalList, @NotNull HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> expandableListDetailOriginal, @NotNull SearchAction searchListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(originalList, "originalList");
        Intrinsics.checkNotNullParameter(expandableListDetailOriginal, "expandableListDetailOriginal");
        Intrinsics.checkNotNullParameter(searchListener, "searchListener");
        this.f2739a = context;
        this.b = originalList;
        this.c = expandableListDetailOriginal;
        this.d = searchListener;
        Intrinsics.checkNotNull(originalList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel>");
        this.e = (ArrayList) originalList;
        Object clone = this.c.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>>{ kotlin.collections.TypeAliasesKt.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>> }");
        this.f = (HashMap) clone;
    }

    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public Object getChild(int i, int i2) {
        HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap = this.f;
        List<ActivityCategoriesModel> list = this.e;
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

    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public View getChildView(int i, int i2, boolean z, @Nullable View view, @NotNull ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Object child = getChild(i, i2);
        Intrinsics.checkNotNull(child, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel");
        ActivitiesListModel activitiesListModel = (ActivitiesListModel) child;
        if (view == null) {
            Object systemService = this.f2739a.getSystemService("layout_inflater");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
            view = ((LayoutInflater) systemService).inflate(R.layout.item_expandable_view, (ViewGroup) null);
        }
        TextView textView = view != null ? (TextView) view.findViewById(R.id.txt_name) : null;
        Intrinsics.checkNotNull(textView);
        String titleInMetric = activitiesListModel.getTitleInMetric();
        textView.setText(titleInMetric != null ? kotlin.text.m.capitalize(titleInMetric) : null);
        Intrinsics.checkNotNull(view);
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        List<ActivitiesListModel> list = this.f.get(this.e.get(i));
        Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
        Intrinsics.checkNotNull(valueOf);
        return valueOf.intValue();
    }

    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public Object getGroup(int i) {
        return this.e.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.e.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public View getGroupView(int i, boolean z, @Nullable View view, @NotNull ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Object group = getGroup(i);
        Intrinsics.checkNotNull(group, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel");
        ActivityCategoriesModel activityCategoriesModel = (ActivityCategoriesModel) group;
        if (view == null) {
            Object systemService = this.f2739a.getSystemService("layout_inflater");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
            view = ((LayoutInflater) systemService).inflate(R.layout.item_activity_information, parent, false);
        }
        TextView textView = view != null ? (TextView) view.findViewById(R.id.txt_name) : null;
        ImageView imageView = view != null ? (ImageView) view.findViewById(R.id.img_icon) : null;
        if (z) {
            if (imageView != null) {
                imageView.setImageResource(R.drawable.ic_expanded_arrow);
            }
        } else if (imageView != null) {
            imageView.setImageResource(R.drawable.ic_pluse_icon);
        }
        Intrinsics.checkNotNull(textView);
        textView.setTypeface(null, 1);
        textView.setText(activityCategoriesModel.getTitle());
        Intrinsics.checkNotNull(view);
        return view;
    }

    @NotNull
    public final SearchAction getSearchListener() {
        return this.d;
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
        this.e = TypeIntrinsics.asMutableList(expandableListTitle);
        this.b = TypeIntrinsics.asMutableList(expandableListTitle);
        Object clone = expandableListDetail.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>>{ kotlin.collections.TypeAliasesKt.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>> }");
        this.f = (HashMap) clone;
        Object clone2 = expandableListDetail.clone();
        Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>>{ kotlin.collections.TypeAliasesKt.HashMap<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel, kotlin.collections.List<com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel>> }");
        this.c = (HashMap) clone2;
        notifyDataSetChanged();
    }

    public final void search(@NotNull String searchStr) {
        int i;
        Intrinsics.checkNotNullParameter(searchStr, "searchStr");
        this.e = new ArrayList();
        this.f.clear();
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
                    List<ActivityCategoriesModel> list = this.e;
                    Intrinsics.checkNotNull(list);
                    list.add(next);
                    this.f.put(next, (List) s.getValue(this.c, next));
                }
            } else {
                this.f.put(next, (List) s.getValue(this.c, next));
                List<ActivityCategoriesModel> list2 = this.e;
                Intrinsics.checkNotNull(list2);
                list2.add(next);
            }
        }
        if (this.e.size() == 0) {
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
                    this.e.add(activityCategoriesModel);
                    this.f.put(activityCategoriesModel, arrayList);
                }
            }
        }
        notifyDataSetChanged();
        if (searchStr != null && !Intrinsics.areEqual(searchStr, "")) {
            int size = this.e.size();
            while (i < size) {
                this.d.expandGroup(i);
                i++;
            }
            return;
        }
        int size2 = this.e.size();
        while (i < size2) {
            this.d.collapseGroup(i);
            i++;
        }
    }
}
