package com.coveiot.android.leonardo.onboarding.profile.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentSocialLoginViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentSocialLogin extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public GoogleSignInClient m;
    public GoogleSignInOptions n;
    public FragmentSocialLoginViewModel o;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = Companion.toString();
    public final int q = 10001;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentSocialLogin newInstance() {
            return new FragmentSocialLogin();
        }
    }

    public static final void o(FragmentSocialLogin this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getActivity() instanceof ActivityProfile) {
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile");
            ((ActivityProfile) activity).loadProfilePictureFragment();
        }
    }

    public static final void p(View view) {
    }

    public static final void q(FragmentSocialLogin this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.n();
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

    public final void n() {
        GoogleSignInClient googleSignInClient = this.m;
        if (googleSignInClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleSignInClient");
            googleSignInClient = null;
        }
        Intent signInIntent = googleSignInClient.getSignInIntent();
        Intrinsics.checkNotNullExpressionValue(signInIntent, "googleSignInClient.signInIntent");
        startActivityForResult(signInIntent, this.q);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.q) {
            Task<GoogleSignInAccount> signedInAccountFromIntent = GoogleSignIn.getSignedInAccountFromIntent(intent);
            Intrinsics.checkNotNullExpressionValue(signedInAccountFromIntent, "getSignedInAccountFromIntent(data)");
            if (signedInAccountFromIntent.isSuccessful()) {
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile");
                ActivityProfile activityProfile = (ActivityProfile) activity;
                FragmentSocialLoginViewModel fragmentSocialLoginViewModel = this.o;
                if (fragmentSocialLoginViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentSocialLoginViewModel = null;
                }
                Bundle handleSignInResult = fragmentSocialLoginViewModel.handleSignInResult(signedInAccountFromIntent);
                Intrinsics.checkNotNull(handleSignInResult);
                activityProfile.loadProfilePictureFragmentWith(handleSignInResult);
                return;
            }
            LogHelper.e(this.p, String.valueOf(signedInAccountFromIntent.getException()));
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_social_login_fragment, viewGroup, false);
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
        GoogleSignInOptions build = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestProfile().build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(GoogleSignInOpti…le()\n            .build()");
        this.n = build;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        GoogleSignInOptions googleSignInOptions = this.n;
        if (googleSignInOptions == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleSignInOptions");
            googleSignInOptions = null;
        }
        GoogleSignInClient client = GoogleSignIn.getClient(context, googleSignInOptions);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(context!!, googleSignInOptions)");
        this.m = client;
        ((TextView) _$_findCachedViewById(R.id.btn_email_signin)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSocialLogin.o(FragmentSocialLogin.this, view2);
            }
        });
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        this.o = (FragmentSocialLoginViewModel) ViewModelProviders.of(activity).get(FragmentSocialLoginViewModel.class);
        ((TextView) _$_findCachedViewById(R.id.btn_facebook_sign)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSocialLogin.p(view2);
            }
        });
        int i = R.id.btn_google_signin;
        ((SignInButton) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSocialLogin.q(FragmentSocialLogin.this, view2);
            }
        });
        SignInButton btn_google_signin = (SignInButton) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(btn_google_signin, "btn_google_signin");
        String string = getString(R.string.signup_with_google);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.signup_with_google)");
        r(btn_google_signin, string);
    }

    public final void r(SignInButton signInButton, String str) {
        int childCount = signInButton.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = signInButton.getChildAt(i);
            Intrinsics.checkNotNullExpressionValue(childAt, "signInButton.getChildAt(i)");
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setText(str);
                textView.setGravity(19);
                return;
            }
        }
    }
}
