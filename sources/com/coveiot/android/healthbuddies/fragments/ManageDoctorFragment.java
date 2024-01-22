package com.coveiot.android.healthbuddies.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.android.healthbuddies.activities.ActivityPendingRequests;
import com.coveiot.android.healthbuddies.adapters.ManageDoctorAdapterNew;
import com.coveiot.android.healthbuddies.constants.Constants;
import com.coveiot.android.healthbuddies.utils.HealthBuddiesViewModelFactory;
import com.coveiot.android.healthbuddies.viewmodels.ManageDoctorViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.coveaccess.healthbuddies.HealthBuddy;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.model.CoveContact;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ManageDoctorFragment extends BaseFragment implements ManageDoctorAdapterNew.OnOptionSelectedListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public ManageDoctorAdapterNew m;
    public ManageDoctorViewModel n;

    public static final void l(ManageDoctorFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0.requireContext(), ActivityPendingRequests.class);
        intent.putExtra(Constants.FROM_DOCTOR.getValue(), true);
        this$0.startActivity(intent);
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

    @Override // com.coveiot.android.healthbuddies.adapters.ManageDoctorAdapterNew.OnOptionSelectedListener
    public void onCancel(int i) {
        ManageDoctorViewModel manageDoctorViewModel = this.n;
        if (manageDoctorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            manageDoctorViewModel = null;
        }
        manageDoctorViewModel.cancelSentHealthBuddyRequest(i);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity requireActivity = requireActivity();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(requireActivity, new HealthBuddiesViewModelFactory(requireContext)).get(ManageDoctorViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(requireActivity(), He…torViewModel::class.java)");
        this.n = (ManageDoctorViewModel) viewModel;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_manage_doctor_new, viewGroup, false);
    }

    @Override // com.coveiot.android.healthbuddies.adapters.ManageDoctorAdapterNew.OnOptionSelectedListener
    public void onDelete(int i) {
        ManageDoctorViewModel manageDoctorViewModel = this.n;
        if (manageDoctorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            manageDoctorViewModel = null;
        }
        manageDoctorViewModel.deleteHealthBuddy(i);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.healthbuddies.adapters.ManageDoctorAdapterNew.OnOptionSelectedListener
    public void onReInvite(int i, @Nullable String str, @Nullable String str2) {
        new ArrayList().add(new CoveContact("1", str2, str, 0L));
        ManageDoctorViewModel manageDoctorViewModel = this.n;
        if (manageDoctorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            manageDoctorViewModel = null;
        }
        manageDoctorViewModel.reInviteHealthBuddy(i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ManageDoctorViewModel manageDoctorViewModel = this.n;
        ManageDoctorViewModel manageDoctorViewModel2 = null;
        if (manageDoctorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            manageDoctorViewModel = null;
        }
        LinkedHashMap<String, List<HealthBuddy>> mListdata = manageDoctorViewModel.getMListdata();
        if (!(mListdata == null || mListdata.isEmpty())) {
            ManageDoctorViewModel manageDoctorViewModel3 = this.n;
            if (manageDoctorViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                manageDoctorViewModel2 = manageDoctorViewModel3;
            }
            showContent(manageDoctorViewModel2.getMListdata());
        }
        if (SessionManager.getInstance(requireContext()).isShowIndusInd()) {
            ImageView ivIndusIndLogo = (ImageView) _$_findCachedViewById(R.id.ivIndusIndLogo);
            Intrinsics.checkNotNullExpressionValue(ivIndusIndLogo, "ivIndusIndLogo");
            visible(ivIndusIndLogo);
            return;
        }
        ImageView ivIndusIndLogo2 = (ImageView) _$_findCachedViewById(R.id.ivIndusIndLogo);
        Intrinsics.checkNotNullExpressionValue(ivIndusIndLogo2, "ivIndusIndLogo");
        gone(ivIndusIndLogo2);
    }

    public final void showContent(LinkedHashMap<String, List<HealthBuddy>> linkedHashMap) {
        List<HealthBuddy> list;
        List<HealthBuddy> list2;
        if (isAdded()) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvManageRequests);
            StringBuilder sb = new StringBuilder();
            int i = R.string.pending_requests;
            sb.append(getString(i));
            sb.append(" (");
            Integer num = null;
            sb.append((linkedHashMap == null || (list2 = linkedHashMap.get(getString(i))) == null) ? null : Integer.valueOf(list2.size()));
            sb.append(HexStringBuilder.COMMENT_END_CHAR);
            textView.setText(sb.toString());
            if (linkedHashMap != null && (list = linkedHashMap.get(getString(i))) != null) {
                num = Integer.valueOf(list.size());
            }
            Intrinsics.checkNotNull(num);
            if (num.intValue() > 0) {
                ((ConstraintLayout) _$_findCachedViewById(R.id.clManageRequests)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.fragments.p
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ManageDoctorFragment.l(ManageDoctorFragment.this, view);
                    }
                });
            }
            if (linkedHashMap != null) {
                List<HealthBuddy> list3 = linkedHashMap.get(getString(R.string.my_doctor));
                Intrinsics.checkNotNull(list3);
                if (!list3.isEmpty()) {
                    TextView tvMyFriends = (TextView) _$_findCachedViewById(R.id.tvMyFriends);
                    Intrinsics.checkNotNullExpressionValue(tvMyFriends, "tvMyFriends");
                    visible(tvMyFriends);
                } else {
                    TextView tvMyFriends2 = (TextView) _$_findCachedViewById(R.id.tvMyFriends);
                    Intrinsics.checkNotNullExpressionValue(tvMyFriends2, "tvMyFriends");
                    gone(tvMyFriends2);
                }
            } else {
                TextView tvMyFriends3 = (TextView) _$_findCachedViewById(R.id.tvMyFriends);
                Intrinsics.checkNotNullExpressionValue(tvMyFriends3, "tvMyFriends");
                gone(tvMyFriends3);
            }
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            Intrinsics.checkNotNull(linkedHashMap);
            this.m = new ManageDoctorAdapterNew(requireActivity, linkedHashMap, new ArrayList(linkedHashMap.keySet()), this);
            int i2 = R.id.rvFriends;
            ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new LinearLayoutManager(requireContext()));
            ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(this.m);
        }
    }
}
