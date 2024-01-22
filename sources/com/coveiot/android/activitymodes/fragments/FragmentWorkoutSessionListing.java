package com.coveiot.android.activitymodes.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.ActivityWorkoutDetails;
import com.coveiot.android.activitymodes.adapters.AdapterWorkoutSessionListing;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutHistory;
import com.coveiot.coveaccess.fitness.ActivityType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentWorkoutSessionListing extends Fragment implements AdapterWorkoutSessionListing.OnItemClickListener, Observer<List<? extends EntityWorkoutSession>> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public ViewModelWorkoutHistory i;
    @Nullable
    public RecyclerView k;
    @Nullable
    public AdapterWorkoutSessionListing l;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String h = "FragmentWorkoutListing";
    @NotNull
    public ActivityMode j = ActivityMode.RUN;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentWorkoutSessionListing newInstance(@NotNull ActivityMode activityMode) {
            Intrinsics.checkNotNullParameter(activityMode, "activityMode");
            FragmentWorkoutSessionListing fragmentWorkoutSessionListing = new FragmentWorkoutSessionListing();
            Bundle bundle = new Bundle();
            bundle.putSerializable(WorkoutConstants.ACTIVITY_MODE, activityMode);
            fragmentWorkoutSessionListing.setArguments(bundle);
            return fragmentWorkoutSessionListing;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ActivityMode.values().length];
            try {
                iArr[ActivityMode.RUN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ActivityMode.WALK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void e(FragmentWorkoutSessionListing this$0, List entityWorkoutSessions) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.d(this$0.h, String.valueOf(entityWorkoutSessions.size()));
        Intrinsics.checkNotNullExpressionValue(entityWorkoutSessions, "entityWorkoutSessions");
        if (!entityWorkoutSessions.isEmpty()) {
            ((TextView) this$0._$_findCachedViewById(R.id.tv_empty_session)).setVisibility(8);
            RecyclerView recyclerView = this$0.k;
            Intrinsics.checkNotNull(recyclerView);
            recyclerView.setVisibility(0);
            AdapterWorkoutSessionListing adapterWorkoutSessionListing = this$0.l;
            Intrinsics.checkNotNull(adapterWorkoutSessionListing);
            adapterWorkoutSessionListing.setData(entityWorkoutSessions);
        }
    }

    public static final void f(FragmentWorkoutSessionListing this$0, List entityWorkoutSessions) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.d(this$0.h, String.valueOf(entityWorkoutSessions.size()));
        Intrinsics.checkNotNullExpressionValue(entityWorkoutSessions, "entityWorkoutSessions");
        if (!entityWorkoutSessions.isEmpty()) {
            ((TextView) this$0._$_findCachedViewById(R.id.tv_empty_session)).setVisibility(8);
            RecyclerView recyclerView = this$0.k;
            Intrinsics.checkNotNull(recyclerView);
            recyclerView.setVisibility(0);
            AdapterWorkoutSessionListing adapterWorkoutSessionListing = this$0.l;
            Intrinsics.checkNotNull(adapterWorkoutSessionListing);
            adapterWorkoutSessionListing.setData(entityWorkoutSessions);
        }
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    public final Drawable c() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.j.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return getResources().getDrawable(R.drawable.walk_summary_image);
            }
            return getResources().getDrawable(R.drawable.walk_summary_image);
        }
        return getResources().getDrawable(R.drawable.run_summary_image);
    }

    public final String d() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.j.ordinal()];
        if (i == 1) {
            String string = getResources().getString(R.string.run_summary);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.run_summary)");
            return string;
        } else if (i != 2) {
            String string2 = getResources().getString(R.string.walk_summary);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.walk_summary)");
            return string2;
        } else {
            String string3 = getResources().getString(R.string.walk_summary);
            Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.walk_summary)");
            return string3;
        }
    }

    @Nullable
    public final AdapterWorkoutSessionListing getMAdapter() {
        return this.l;
    }

    @Nullable
    public final RecyclerView getMRecyclerView() {
        return this.k;
    }

    @Override // androidx.lifecycle.Observer
    public /* bridge */ /* synthetic */ void onChanged(List<? extends EntityWorkoutSession> list) {
        onChanged2((List<EntityWorkoutSession>) list);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        ViewModel viewModel = ViewModelProviders.of(activity).get(ViewModelWorkoutHistory.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(activity!!).get(ViewM…rkoutHistory::class.java)");
        this.i = (ViewModelWorkoutHistory) viewModel;
        Bundle arguments = getArguments();
        Object obj = arguments != null ? arguments.get(WorkoutConstants.ACTIVITY_MODE) : null;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.coveiot.android.activitymodes.utils.ActivityMode");
        this.j = (ActivityMode) obj;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_workout_session_listing, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.activitymodes.adapters.AdapterWorkoutSessionListing.OnItemClickListener
    public void onItemClicked(@Nullable String str) {
        if (str != null) {
            Intent intent = new Intent(getActivity(), ActivityWorkoutDetails.class);
            intent.putExtra(WorkoutConstants.ACTIVITY_MODE, this.j);
            intent.putExtra(WorkoutConstants.SESSION_ID, str);
            startActivity(intent);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ((ImageView) _$_findCachedViewById(R.id.summary_image)).setImageDrawable(c());
        ((TextView) _$_findCachedViewById(R.id.summary_text)).setText(d());
        View findViewById = view.findViewById(R.id.recyclerView);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        RecyclerView recyclerView = (RecyclerView) findViewById;
        this.k = recyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        AdapterWorkoutSessionListing adapterWorkoutSessionListing = new AdapterWorkoutSessionListing(context, this);
        this.l = adapterWorkoutSessionListing;
        RecyclerView recyclerView2 = this.k;
        if (recyclerView2 != null) {
            recyclerView2.setAdapter(adapterWorkoutSessionListing);
        }
        ViewModelWorkoutHistory viewModelWorkoutHistory = null;
        if (this.j.equals(ActivityType.WALK.name())) {
            ViewModelWorkoutHistory viewModelWorkoutHistory2 = this.i;
            if (viewModelWorkoutHistory2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutHistory");
            } else {
                viewModelWorkoutHistory = viewModelWorkoutHistory2;
            }
            String name = this.j.name();
            Context context2 = getContext();
            Intrinsics.checkNotNull(context2);
            String connectedDeviceMacAddress = new PreferenceManager(context2).getConnectedDeviceMacAddress();
            Intrinsics.checkNotNull(connectedDeviceMacAddress);
            viewModelWorkoutHistory.getWorkoutSessionsFromDB(name, connectedDeviceMacAddress).observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.fragments.c1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentWorkoutSessionListing.e(FragmentWorkoutSessionListing.this, (List) obj);
                }
            });
            return;
        }
        ViewModelWorkoutHistory viewModelWorkoutHistory3 = this.i;
        if (viewModelWorkoutHistory3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutHistory");
        } else {
            viewModelWorkoutHistory = viewModelWorkoutHistory3;
        }
        String name2 = this.j.name();
        Context context3 = getContext();
        Intrinsics.checkNotNull(context3);
        String connectedDeviceMacAddress2 = new PreferenceManager(context3).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNull(connectedDeviceMacAddress2);
        viewModelWorkoutHistory.getWorkoutSessionsFromDB(name2, connectedDeviceMacAddress2).observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.fragments.d1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentWorkoutSessionListing.f(FragmentWorkoutSessionListing.this, (List) obj);
            }
        });
    }

    public final void setMAdapter(@Nullable AdapterWorkoutSessionListing adapterWorkoutSessionListing) {
        this.l = adapterWorkoutSessionListing;
    }

    public final void setMRecyclerView(@Nullable RecyclerView recyclerView) {
        this.k = recyclerView;
    }

    /* renamed from: onChanged  reason: avoid collision after fix types in other method */
    public void onChanged2(@Nullable List<EntityWorkoutSession> list) {
        Intrinsics.checkNotNull(list);
        if (!list.isEmpty()) {
            ((TextView) _$_findCachedViewById(R.id.tv_empty_session)).setVisibility(8);
            RecyclerView recyclerView = this.k;
            if (recyclerView != null) {
                recyclerView.setVisibility(0);
            }
            AdapterWorkoutSessionListing adapterWorkoutSessionListing = this.l;
            if (adapterWorkoutSessionListing != null) {
                adapterWorkoutSessionListing.setData(list);
            }
        }
    }
}
