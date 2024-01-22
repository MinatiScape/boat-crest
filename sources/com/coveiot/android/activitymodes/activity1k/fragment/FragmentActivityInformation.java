package com.coveiot.android.activitymodes.activity1k.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.OneKActivity;
import com.coveiot.android.activitymodes.activity1k.adapter.ActivityInformationAdapter;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentActivityInformationViewModel;
import com.coveiot.android.theme.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentActivityInformation extends BaseFragment implements ActivityInformationAdapter.SearchAction {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public FragmentActivityInformationViewModel m;
    @Nullable
    public ActivityInformationAdapter n;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public ArrayList<ActivityCategoriesModel> o = new ArrayList<>();
    @Nullable
    public HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> p = new HashMap<>();
    @NotNull
    public List<ActivitiesListModel> q = new ArrayList();
    @NotNull
    public final Observer<List<ActivityCategoriesModel>> r = new Observer() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.c
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            FragmentActivityInformation.o(FragmentActivityInformation.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<ActivitiesListModel>> s = new Observer() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.b
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            FragmentActivityInformation.n(FragmentActivityInformation.this, (List) obj);
        }
    };

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentActivityInformation newInstance() {
            return new FragmentActivityInformation();
        }
    }

    public static final void n(FragmentActivityInformation this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            this$0.dismissProgress();
            this$0.q.clear();
            this$0.q.addAll(list);
            if (this$0.q.size() > 0) {
                ArrayList<ActivityCategoriesModel> arrayList = this$0.o;
                Intrinsics.checkNotNull(arrayList);
                if (arrayList.size() > 0) {
                    FragmentActivityInformationViewModel fragmentActivityInformationViewModel = this$0.m;
                    if (fragmentActivityInformationViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentActivityInformationViewModel = null;
                    }
                    List<ActivitiesListModel> list2 = this$0.q;
                    ArrayList<ActivityCategoriesModel> arrayList2 = this$0.o;
                    Intrinsics.checkNotNull(arrayList2);
                    this$0.p = fragmentActivityInformationViewModel.getExpandableHashMap(list2, arrayList2);
                    ActivityInformationAdapter activityInformationAdapter = this$0.n;
                    Intrinsics.checkNotNull(activityInformationAdapter);
                    ArrayList<ActivityCategoriesModel> arrayList3 = this$0.o;
                    Intrinsics.checkNotNull(arrayList3);
                    HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap = this$0.p;
                    Intrinsics.checkNotNull(hashMap);
                    activityInformationAdapter.notifyAdapter(arrayList3, hashMap);
                }
            }
        }
    }

    public static final void o(FragmentActivityInformation this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            ArrayList<ActivityCategoriesModel> arrayList = this$0.o;
            Intrinsics.checkNotNull(arrayList);
            arrayList.clear();
            ArrayList<ActivityCategoriesModel> arrayList2 = this$0.o;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.addAll(list);
            FragmentActivityInformationViewModel fragmentActivityInformationViewModel = this$0.m;
            FragmentActivityInformationViewModel fragmentActivityInformationViewModel2 = null;
            if (fragmentActivityInformationViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentActivityInformationViewModel = null;
            }
            fragmentActivityInformationViewModel.getActivityListFromDb();
            FragmentActivityInformationViewModel fragmentActivityInformationViewModel3 = this$0.m;
            if (fragmentActivityInformationViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentActivityInformationViewModel2 = fragmentActivityInformationViewModel3;
            }
            LiveData<List<ActivitiesListModel>> activityLiveData = fragmentActivityInformationViewModel2.getActivityLiveData();
            if (activityLiveData != null) {
                activityLiveData.observe(this$0, this$0.s);
            }
        }
    }

    public static final boolean p(ExpandableListView expandableListView, View view, int i, long j) {
        View findViewById = view.findViewById(R.id.img_icon);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView = (ImageView) findViewById;
        if (expandableListView.isGroupExpanded(i)) {
            expandableListView.collapseGroup(i);
            imageView.setImageResource(R.drawable.ic_dot);
            return true;
        }
        expandableListView.expandGroup(i);
        imageView.setImageResource(R.drawable.up_arrow);
        return true;
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivityInformationAdapter.SearchAction
    public void collapseGroup(int i) {
        int i2 = R.id.expandableListView;
        if (((ExpandableListView) _$_findCachedViewById(i2)) == null || ((ExpandableListView) _$_findCachedViewById(i2)).getExpandableListAdapter() == null) {
            return;
        }
        ((ExpandableListView) _$_findCachedViewById(i2)).collapseGroup(i);
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivityInformationAdapter.SearchAction
    public void expandGroup(int i) {
        int i2 = R.id.expandableListView;
        if (((ExpandableListView) _$_findCachedViewById(i2)) == null || ((ExpandableListView) _$_findCachedViewById(i2)).getExpandableListAdapter() == null) {
            return;
        }
        ((ExpandableListView) _$_findCachedViewById(i2)).expandGroup(i);
    }

    @Nullable
    public final ActivityInformationAdapter getExpandableListAdapter() {
        return this.n;
    }

    @Nullable
    public final HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> getExpandableListDetail() {
        return this.p;
    }

    @Nullable
    public final ArrayList<ActivityCategoriesModel> getExpandableListTitle() {
        return this.o;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
        ((OneKActivity) activity).setupToolbar(Companion.newInstance());
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        ViewModel viewModel = ViewModelProviders.of(activity2).get(FragmentActivityInformationViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(activity!!).get(Fragm…ionViewModel::class.java)");
        this.m = (FragmentActivityInformationViewModel) viewModel;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        ArrayList<ActivityCategoriesModel> arrayList = this.o;
        Intrinsics.checkNotNull(arrayList);
        HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap = this.p;
        Intrinsics.checkNotNull(hashMap);
        this.n = new ActivityInformationAdapter(context, arrayList, hashMap, this);
        int i = R.id.expandableListView;
        ExpandableListView expandableListView = (ExpandableListView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(expandableListView);
        expandableListView.setAdapter(this.n);
        FragmentActivityInformationViewModel fragmentActivityInformationViewModel = this.m;
        if (fragmentActivityInformationViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentActivityInformationViewModel = null;
        }
        fragmentActivityInformationViewModel.getCategoryListFromDb();
        FragmentActivityInformationViewModel fragmentActivityInformationViewModel2 = this.m;
        if (fragmentActivityInformationViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentActivityInformationViewModel2 = null;
        }
        LiveData<List<ActivityCategoriesModel>> categoryLiveData = fragmentActivityInformationViewModel2.getCategoryLiveData();
        if (categoryLiveData != null) {
            categoryLiveData.observe(this, this.r);
        }
        BaseFragment.showProgress$default(this, false, 1, null);
        EditText editText = (EditText) _$_findCachedViewById(R.id.edt_search);
        Intrinsics.checkNotNull(editText);
        editText.addTextChangedListener(new FragmentActivityInformation$onActivityCreated$1(this));
        ((ExpandableListView) _$_findCachedViewById(i)).setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.a
            @Override // android.widget.ExpandableListView.OnGroupClickListener
            public final boolean onGroupClick(ExpandableListView expandableListView2, View view, int i2, long j) {
                boolean p;
                p = FragmentActivityInformation.p(expandableListView2, view, i2, j);
                return p;
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_activity_information, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        dismissProgress();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setExpandableListAdapter(@Nullable ActivityInformationAdapter activityInformationAdapter) {
        this.n = activityInformationAdapter;
    }

    public final void setExpandableListDetail(@Nullable HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap) {
        this.p = hashMap;
    }

    public final void setExpandableListTitle(@Nullable ArrayList<ActivityCategoriesModel> arrayList) {
        this.o = arrayList;
    }
}
