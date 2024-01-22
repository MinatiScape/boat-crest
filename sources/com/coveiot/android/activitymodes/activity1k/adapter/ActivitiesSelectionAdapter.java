package com.coveiot.android.activitymodes.activity1k.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivitiesSelectionAdapter extends BaseExpandableListAdapter {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2731a;
    @NotNull
    public List<ActivityCategoriesModel> b;
    @NotNull
    public HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> c;
    @NotNull
    public final ItemClickListener d;
    public int e;
    @NotNull
    public List<ActivityCategoriesModel> f;

    /* loaded from: classes2.dex */
    public interface ItemClickListener {
        void onClicked(@NotNull ActivitiesListModel activitiesListModel, @NotNull ActivityCategoriesModel activityCategoriesModel);

        void onLongClicked(int i);
    }

    public ActivitiesSelectionAdapter(@NotNull Context context, @NotNull List<ActivityCategoriesModel> originalList, @NotNull HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> expandableListDetail, @NotNull ItemClickListener listener, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(originalList, "originalList");
        Intrinsics.checkNotNullParameter(expandableListDetail, "expandableListDetail");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f2731a = context;
        this.b = originalList;
        this.c = expandableListDetail;
        this.d = listener;
        this.e = i;
        Intrinsics.checkNotNull(originalList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel>");
        this.f = (ArrayList) originalList;
    }

    public static final void b(ActivitiesSelectionAdapter this$0, Ref.ObjectRef expandedListModel, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(expandedListModel, "$expandedListModel");
        Integer activityId = ((ActivitiesListModel) expandedListModel.element).getActivityId();
        Intrinsics.checkNotNull(activityId);
        this$0.e = activityId.intValue();
        this$0.d.onClicked((ActivitiesListModel) expandedListModel.element, this$0.f.get(i));
        this$0.notifyDataSetChanged();
    }

    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public Object getChild(int i, int i2) {
        HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap = this.c;
        List<ActivityCategoriesModel> list = this.f;
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

    /* JADX WARN: Type inference failed for: r4v2, types: [T, com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel] */
    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public View getChildView(final int i, int i2, boolean z, @Nullable View view, @NotNull ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Object child = getChild(i, i2);
        Intrinsics.checkNotNull(child, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel");
        objectRef.element = (ActivitiesListModel) child;
        if (view == null) {
            Object systemService = this.f2731a.getSystemService("layout_inflater");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
            view = ((LayoutInflater) systemService).inflate(R.layout.item_expandable_view, (ViewGroup) null);
        }
        TextView textView = view != null ? (TextView) view.findViewById(R.id.txt_name) : null;
        Intrinsics.checkNotNull(view);
        View findViewById = view.findViewById(R.id.text_input_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view!!.findViewById(R.id.text_input_layout)");
        TextInputLayout textInputLayout = (TextInputLayout) findViewById;
        String titleInMetric = ((ActivitiesListModel) objectRef.element).getTitleInMetric();
        textInputLayout.setHint(titleInMetric != null ? kotlin.text.m.capitalize(titleInMetric) : null);
        Intrinsics.checkNotNull(textView);
        textView.setText(((ActivitiesListModel) objectRef.element).getDescInMetric());
        int i3 = this.e;
        Integer activityId = ((ActivitiesListModel) objectRef.element).getActivityId();
        if (activityId != null && i3 == activityId.intValue()) {
            textInputLayout.setBoxBackgroundColorResource(R.color.transparent);
            Context context = this.f2731a;
            int i4 = R.color.colorPrimary;
            textInputLayout.setBoxStrokeColor(context.getColor(i4));
            textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(this.f2731a.getColor(i4)));
        } else {
            textInputLayout.setBoxBackgroundColorResource(R.color.transparent);
            Context context2 = this.f2731a;
            int i5 = R.color.color_93a6b9;
            textInputLayout.setBoxStrokeColor(context2.getColor(i5));
            textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(this.f2731a.getColor(i5)));
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ActivitiesSelectionAdapter.b(ActivitiesSelectionAdapter.this, objectRef, i, view2);
            }
        });
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        List<ActivitiesListModel> list = this.c.get(this.f.get(i));
        Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
        Intrinsics.checkNotNull(valueOf);
        return valueOf.intValue();
    }

    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public Object getGroup(int i) {
        return this.f.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.f.size();
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
            Object systemService = this.f2731a.getSystemService("layout_inflater");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
            view = ((LayoutInflater) systemService).inflate(R.layout.item_activity_information, (ViewGroup) null);
        }
        TextView textView = view != null ? (TextView) view.findViewById(R.id.txt_name) : null;
        Intrinsics.checkNotNull(textView);
        textView.setTypeface(null, 1);
        textView.setText(activityCategoriesModel.getTitle());
        Intrinsics.checkNotNull(view);
        return view;
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
        this.f = TypeIntrinsics.asMutableList(expandableListTitle);
        this.c = expandableListDetail;
        notifyDataSetChanged();
    }

    public final void search(@NotNull String searchStr) {
        Intrinsics.checkNotNullParameter(searchStr, "searchStr");
        this.f = new ArrayList();
        for (ActivityCategoriesModel activityCategoriesModel : this.b) {
            if (searchStr != null && !Intrinsics.areEqual(searchStr, "")) {
                String title = activityCategoriesModel.getTitle();
                Intrinsics.checkNotNull(title);
                String lowerCase = title.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                String lowerCase2 = searchStr.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null)) {
                    List<ActivityCategoriesModel> list = this.f;
                    Intrinsics.checkNotNull(list);
                    list.add(activityCategoriesModel);
                }
            } else {
                List<ActivityCategoriesModel> list2 = this.f;
                Intrinsics.checkNotNull(list2);
                list2.add(activityCategoriesModel);
            }
        }
        notifyDataSetChanged();
    }
}
