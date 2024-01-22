package com.coveiot.android.healthbuddies.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.android.healthbuddies.activities.ActivityInAppWebViewer;
import com.coveiot.android.healthbuddies.constants.Constants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class HealthBuddiesConsentFragment extends BaseFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ConsentListener m;

    /* loaded from: classes3.dex */
    public interface ConsentListener {
        void onAcceptClicked(@NotNull String str);

        void onRejectClicked();
    }

    public static final void p(HealthBuddiesConsentFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    public static final void q(HealthBuddiesConsentFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ConsentListener consentListener = this$0.m;
        if (consentListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            consentListener = null;
        }
        consentListener.onRejectClicked();
    }

    public static final void r(HealthBuddiesConsentFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.getActivity())) {
            Toast.makeText(this$0.getActivity(), R.string.noconnection, 0).show();
            return;
        }
        SessionManager sessionManager = SessionManager.getInstance(this$0.getContext());
        String url = sessionManager.getLegalDocUrl() != null ? sessionManager.getLegalDocUrl() : Constants.GUARDIAN_CONSENT_EULA_URL.getValue();
        String string = this$0.getString(R.string.terms_and_conditions);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.terms_and_conditions)");
        Intrinsics.checkNotNullExpressionValue(url, "url");
        this$0.o(string, url);
    }

    public static final void s(HealthBuddiesConsentFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.getActivity())) {
            Toast.makeText(this$0.getActivity(), R.string.noconnection, 0).show();
            return;
        }
        SessionManager sessionManager = SessionManager.getInstance(this$0.getContext());
        String url = sessionManager.getPrivacyPolicyDocUrl() != null ? sessionManager.getPrivacyPolicyDocUrl() : Constants.GUARDIAN_CONSENT_PRIVACY_POLICY_URL.getValue();
        String string = this$0.getString(R.string.privacy_policy);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.privacy_policy)");
        Intrinsics.checkNotNullExpressionValue(url, "url");
        this$0.o(string, url);
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

    public final void o(String str, String str2) {
        Intent intent = new Intent(getContext(), ActivityInAppWebViewer.class);
        intent.putExtra(Constants.INTENT_EXTRA_TITLE.getValue(), str);
        intent.putExtra(Constants.INTENT_EXTRA_URL.getValue(), str2);
        startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_health_buddies_consent_screen, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.toolbar);
        ((TextView) constraintLayout.findViewById(R.id.toolbar_title)).setText(getString(R.string.terms_and_conditions));
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.fragments.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthBuddiesConsentFragment.p(HealthBuddiesConsentFragment.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.textViewMessageToAccept)).setText(requireContext().getString(R.string.acceptance_msg_health_buddies_data_sharing_indus_ind));
        ((Button) _$_findCachedViewById(R.id.buttonReject)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.fragments.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthBuddiesConsentFragment.q(HealthBuddiesConsentFragment.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.textViewTermsConditionView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.fragments.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthBuddiesConsentFragment.r(HealthBuddiesConsentFragment.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.textViewPolicyView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.fragments.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthBuddiesConsentFragment.s(HealthBuddiesConsentFragment.this, view2);
            }
        });
    }

    public final void setConsentListener(@NotNull ConsentListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.m = listener;
    }
}
