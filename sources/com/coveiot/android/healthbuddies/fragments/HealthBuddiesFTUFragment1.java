package com.coveiot.android.healthbuddies.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.android.healthbuddies.activities.AddHealthBuddiesActivity;
import com.coveiot.android.healthbuddies.activities.ManageHealthBuddiesActivity;
import com.coveiot.android.healthbuddies.constants.Constants;
import com.coveiot.android.healthbuddies.fragments.AddHealthBuddiesFragment;
import com.coveiot.android.healthbuddies.fragments.HealthBuddiesConsentFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class HealthBuddiesFTUFragment1 extends Fragment implements HealthBuddiesConsentFragment.ConsentListener, AddHealthBuddiesFragment.AddHealthBuddyListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public String h;

    public static final void b(HealthBuddiesFTUFragment1 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getResources().getString(R.string.familydoc_dependent);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.familydoc_dependent)");
        String string2 = this$0.getString(R.string.add_family_doctor);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.add_family_doctor)");
        Intent intent = new Intent(this$0.getActivity(), AddHealthBuddiesActivity.class);
        intent.putExtra(Constants.RELATION_TYPE.getValue(), string);
        intent.putExtra(Constants.TOOLBAR_TITLE.getValue(), string2);
        this$0.requireActivity().startActivity(intent);
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

    @Nullable
    public final String getRelType() {
        return this.h;
    }

    @Override // com.coveiot.android.healthbuddies.fragments.HealthBuddiesConsentFragment.ConsentListener
    public void onAcceptClicked(@NotNull String relationType) {
        Intrinsics.checkNotNullParameter(relationType, "relationType");
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.healthbuddies.activities.ManageHealthBuddiesActivity");
        ((ManageHealthBuddiesActivity) activity).onAcceptClicked(relationType);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_health_buddies_ftu1, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.healthbuddies.fragments.HealthBuddiesConsentFragment.ConsentListener
    public void onRejectClicked() {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.healthbuddies.activities.ManageHealthBuddiesActivity");
        ((ManageHealthBuddiesActivity) activity).onRejectClicked();
    }

    @Override // com.coveiot.android.healthbuddies.fragments.AddHealthBuddiesFragment.AddHealthBuddyListener
    public void onSendClicked(@NotNull String relationType) {
        Intrinsics.checkNotNullParameter(relationType, "relationType");
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.healthbuddies.activities.ManageHealthBuddiesActivity");
        ((ManageHealthBuddiesActivity) activity).onSendClicked(relationType);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ((TextView) view.findViewById(R.id.tvAddDoctor)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.fragments.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthBuddiesFTUFragment1.b(HealthBuddiesFTUFragment1.this, view2);
            }
        });
    }

    public final void setRelType(@Nullable String str) {
        this.h = str;
    }
}
