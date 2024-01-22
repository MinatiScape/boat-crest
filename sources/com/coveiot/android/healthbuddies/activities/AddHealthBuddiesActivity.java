package com.coveiot.android.healthbuddies.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.android.healthbuddies.constants.Constants;
import com.coveiot.android.healthbuddies.fragments.AddHealthBuddiesFragment;
import com.coveiot.android.healthbuddies.fragments.HealthBuddiesConsentFragment;
import com.coveiot.android.healthbuddies.utils.HealthBuddiesViewModelFactory;
import com.coveiot.android.healthbuddies.viewmodels.AddHealthBuddiesViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogWellnessCrew;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.LanguageHelper;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AddHealthBuddiesActivity extends BaseActivity implements HealthBuddiesConsentFragment.ConsentListener, AddHealthBuddiesFragment.AddHealthBuddyListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AddHealthBuddiesViewModel mViewModel;
    @Nullable
    public String p;

    public static final void A(AddHealthBuddiesActivity this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.finish();
        }
    }

    public static final void w(AddHealthBuddiesActivity this$0, String relationType, BottomSheetDialogWellnessCrew dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(relationType, "$relationType");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.onAcceptClicked(relationType);
        dialog.dismiss();
    }

    public static final void x(AddHealthBuddiesActivity this$0, BottomSheetDialogWellnessCrew dialog, View view) {
        MutableLiveData<Boolean> selectedValueUpdated;
        ArrayList<CoveContact> selectedBuddiesContacts;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        AddHealthBuddiesViewModel mViewModel = this$0.getMViewModel();
        if (mViewModel != null && (selectedBuddiesContacts = mViewModel.getSelectedBuddiesContacts()) != null) {
            selectedBuddiesContacts.clear();
        }
        AddHealthBuddiesViewModel mViewModel2 = this$0.getMViewModel();
        if (mViewModel2 != null && (selectedValueUpdated = mViewModel2.getSelectedValueUpdated()) != null) {
            selectedValueUpdated.postValue(Boolean.TRUE);
        }
        this$0.onRejectClicked();
        dialog.dismiss();
    }

    public static final void z(AddHealthBuddiesActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(@Nullable Context context) {
        super.attachBaseContext(LanguageHelper.onAttach(context));
    }

    @NotNull
    public final AddHealthBuddiesViewModel getMViewModel() {
        AddHealthBuddiesViewModel addHealthBuddiesViewModel = this.mViewModel;
        if (addHealthBuddiesViewModel != null) {
            return addHealthBuddiesViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        return null;
    }

    @Nullable
    public final String getRelType() {
        return this.p;
    }

    @Override // com.coveiot.android.healthbuddies.fragments.HealthBuddiesConsentFragment.ConsentListener
    public void onAcceptClicked(@NotNull String relationType) {
        Intrinsics.checkNotNullParameter(relationType, "relationType");
        getMViewModel().onSubmitClick(relationType);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_health_buddies);
        String stringExtra = getIntent().getStringExtra(Constants.TOOLBAR_TITLE.getValue());
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.toolbar);
        ((TextView) constraintLayout.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.activities.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddHealthBuddiesActivity.z(AddHealthBuddiesActivity.this, view);
            }
        });
        ((TextView) constraintLayout.findViewById(R.id.toolbar_title)).setText(stringExtra);
        ViewModel viewModel = ViewModelProviders.of(this, new HealthBuddiesViewModelFactory(this)).get(AddHealthBuddiesViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            this,\n  …iesViewModel::class.java)");
        setMViewModel((AddHealthBuddiesViewModel) viewModel);
        String stringExtra2 = getIntent().getStringExtra(Constants.RELATION_TYPE.getValue());
        this.p = stringExtra2;
        if (stringExtra2 == null) {
            this.p = getResources().getString(R.string.guardian_dependent);
        }
        AddHealthBuddiesViewModel mViewModel = getMViewModel();
        Intrinsics.checkNotNull(mViewModel);
        mViewModel.getFinishActivity().observe(this, new Observer() { // from class: com.coveiot.android.healthbuddies.activities.h
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddHealthBuddiesActivity.A(AddHealthBuddiesActivity.this, ((Boolean) obj).booleanValue());
            }
        });
        String str = this.p;
        Intrinsics.checkNotNull(str);
        u(str);
    }

    @Override // com.coveiot.android.healthbuddies.fragments.HealthBuddiesConsentFragment.ConsentListener
    public void onRejectClicked() {
        String str = this.p;
        Intrinsics.checkNotNull(str);
        u(str);
    }

    @Override // com.coveiot.android.healthbuddies.fragments.AddHealthBuddiesFragment.AddHealthBuddyListener
    public void onSendClicked(@NotNull String relationType) {
        Intrinsics.checkNotNullParameter(relationType, "relationType");
        v(relationType);
    }

    public final void setMViewModel(@NotNull AddHealthBuddiesViewModel addHealthBuddiesViewModel) {
        Intrinsics.checkNotNullParameter(addHealthBuddiesViewModel, "<set-?>");
        this.mViewModel = addHealthBuddiesViewModel;
    }

    public final void setRelType(@Nullable String str) {
        this.p = str;
    }

    public final void u(String str) {
        AddHealthBuddiesFragment newInstance = AddHealthBuddiesFragment.Companion.newInstance(str);
        newInstance.setAddHealthBuddyListener(this);
        newInstance.setViewModel(getMViewModel());
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        supportFragmentManager.beginTransaction().replace(R.id.container, newInstance).commit();
    }

    public final void v(final String str) {
        final BottomSheetDialogWellnessCrew bottomSheetDialogWellnessCrew = new BottomSheetDialogWellnessCrew(this);
        bottomSheetDialogWellnessCrew.getYesButton().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.activities.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddHealthBuddiesActivity.w(AddHealthBuddiesActivity.this, str, bottomSheetDialogWellnessCrew, view);
            }
        });
        bottomSheetDialogWellnessCrew.getNoButton().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.activities.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddHealthBuddiesActivity.x(AddHealthBuddiesActivity.this, bottomSheetDialogWellnessCrew, view);
            }
        });
        bottomSheetDialogWellnessCrew.setHyperLinkAction(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.activities.AddHealthBuddiesActivity$loadConsentDialog$3
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                if (!AppUtils.isNetConnected(AddHealthBuddiesActivity.this)) {
                    Toast.makeText(AddHealthBuddiesActivity.this, R.string.noconnection, 0).show();
                    return;
                }
                SessionManager sessionManager = SessionManager.getInstance(AddHealthBuddiesActivity.this);
                String url = sessionManager.getLegalDocUrl() != null ? sessionManager.getLegalDocUrl() : Constants.GUARDIAN_CONSENT_EULA_URL.getValue();
                AddHealthBuddiesActivity addHealthBuddiesActivity = AddHealthBuddiesActivity.this;
                String string = addHealthBuddiesActivity.getString(R.string.terms_and_conditions);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.terms_and_conditions)");
                Intrinsics.checkNotNullExpressionValue(url, "url");
                addHealthBuddiesActivity.y(string, url);
            }
        });
        bottomSheetDialogWellnessCrew.setHyperLinkActionPolicy(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.activities.AddHealthBuddiesActivity$loadConsentDialog$4
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                if (!AppUtils.isNetConnected(AddHealthBuddiesActivity.this)) {
                    Toast.makeText(AddHealthBuddiesActivity.this, R.string.noconnection, 0).show();
                    return;
                }
                SessionManager sessionManager = SessionManager.getInstance(AddHealthBuddiesActivity.this);
                String url = sessionManager.getLegalDocUrl() != null ? sessionManager.getLegalDocUrl() : Constants.GUARDIAN_CONSENT_EULA_URL.getValue();
                AddHealthBuddiesActivity addHealthBuddiesActivity = AddHealthBuddiesActivity.this;
                String string = addHealthBuddiesActivity.getString(R.string.privacy_policy);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.privacy_policy)");
                Intrinsics.checkNotNullExpressionValue(url, "url");
                addHealthBuddiesActivity.y(string, url);
            }
        });
        bottomSheetDialogWellnessCrew.show();
    }

    public final void y(String str, String str2) {
        Intent intent = new Intent(this, ActivityInAppWebViewer.class);
        intent.putExtra(Constants.INTENT_EXTRA_TITLE.getValue(), str);
        intent.putExtra(Constants.INTENT_EXTRA_URL.getValue(), str2);
        startActivity(intent);
    }
}
