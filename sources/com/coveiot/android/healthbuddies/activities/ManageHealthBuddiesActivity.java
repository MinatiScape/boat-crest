package com.coveiot.android.healthbuddies.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.android.healthbuddies.constants.Constants;
import com.coveiot.android.healthbuddies.fragments.AddHealthBuddiesFragment;
import com.coveiot.android.healthbuddies.fragments.HealthBuddiesConsentFragment;
import com.coveiot.android.healthbuddies.fragments.HealthBuddiesFragment;
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
public final class ManageHealthBuddiesActivity extends BaseActivity implements HealthBuddiesConsentFragment.ConsentListener, AddHealthBuddiesFragment.AddHealthBuddyListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AddHealthBuddiesViewModel mViewModel;
    public int p;

    public static final void t(ManageHealthBuddiesActivity this$0, String relationType, BottomSheetDialogWellnessCrew dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(relationType, "$relationType");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.onAcceptClicked(relationType);
        dialog.dismiss();
    }

    public static final void u(ManageHealthBuddiesActivity this$0, BottomSheetDialogWellnessCrew dialog, View view) {
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

    public final int getTab_pos() {
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
        setContentView(R.layout.activity_manage_health_buddies);
        ViewModel viewModel = ViewModelProviders.of(this, new HealthBuddiesViewModelFactory(this)).get(AddHealthBuddiesViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            this,\n  …iesViewModel::class.java)");
        setMViewModel((AddHealthBuddiesViewModel) viewModel);
        int intExtra = getIntent().getIntExtra(Constants.EXTRA_GUARDIAN_TAB_POSITION.getValue(), 0);
        this.p = intExtra;
        v(intExtra);
    }

    @Override // com.coveiot.android.healthbuddies.fragments.HealthBuddiesConsentFragment.ConsentListener
    public void onRejectClicked() {
    }

    @Override // com.coveiot.android.healthbuddies.fragments.AddHealthBuddiesFragment.AddHealthBuddyListener
    public void onSendClicked(@NotNull String relationType) {
        Intrinsics.checkNotNullParameter(relationType, "relationType");
        s(relationType);
    }

    public final void s(final String str) {
        final BottomSheetDialogWellnessCrew bottomSheetDialogWellnessCrew = new BottomSheetDialogWellnessCrew(this);
        bottomSheetDialogWellnessCrew.getYesButton().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.activities.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageHealthBuddiesActivity.t(ManageHealthBuddiesActivity.this, str, bottomSheetDialogWellnessCrew, view);
            }
        });
        bottomSheetDialogWellnessCrew.setHyperLinkAction(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.activities.ManageHealthBuddiesActivity$loadConsentDialog$2
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                if (!AppUtils.isNetConnected(ManageHealthBuddiesActivity.this)) {
                    Toast.makeText(ManageHealthBuddiesActivity.this, R.string.noconnection, 0).show();
                    return;
                }
                SessionManager sessionManager = SessionManager.getInstance(ManageHealthBuddiesActivity.this);
                String url = sessionManager.getLegalDocUrl() != null ? sessionManager.getLegalDocUrl() : Constants.GUARDIAN_CONSENT_EULA_URL.getValue();
                ManageHealthBuddiesActivity manageHealthBuddiesActivity = ManageHealthBuddiesActivity.this;
                String string = manageHealthBuddiesActivity.getString(R.string.terms_and_conditions);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.terms_and_conditions)");
                Intrinsics.checkNotNullExpressionValue(url, "url");
                manageHealthBuddiesActivity.w(string, url);
            }
        });
        bottomSheetDialogWellnessCrew.setHyperLinkActionPolicy(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.activities.ManageHealthBuddiesActivity$loadConsentDialog$3
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                ManageHealthBuddiesActivity manageHealthBuddiesActivity = ManageHealthBuddiesActivity.this;
                if (manageHealthBuddiesActivity != null) {
                    if (!AppUtils.isNetConnected(manageHealthBuddiesActivity)) {
                        Toast.makeText(ManageHealthBuddiesActivity.this, R.string.noconnection, 0).show();
                        return;
                    }
                    SessionManager sessionManager = SessionManager.getInstance(ManageHealthBuddiesActivity.this);
                    String url = sessionManager.getLegalDocUrl() != null ? sessionManager.getLegalDocUrl() : Constants.GUARDIAN_CONSENT_EULA_URL.getValue();
                    ManageHealthBuddiesActivity manageHealthBuddiesActivity2 = ManageHealthBuddiesActivity.this;
                    String string = manageHealthBuddiesActivity2.getString(R.string.privacy_policy);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.privacy_policy)");
                    Intrinsics.checkNotNullExpressionValue(url, "url");
                    manageHealthBuddiesActivity2.w(string, url);
                }
            }
        });
        bottomSheetDialogWellnessCrew.getNoButton().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.activities.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageHealthBuddiesActivity.u(ManageHealthBuddiesActivity.this, bottomSheetDialogWellnessCrew, view);
            }
        });
        bottomSheetDialogWellnessCrew.show();
    }

    public final void setMViewModel(@NotNull AddHealthBuddiesViewModel addHealthBuddiesViewModel) {
        Intrinsics.checkNotNullParameter(addHealthBuddiesViewModel, "<set-?>");
        this.mViewModel = addHealthBuddiesViewModel;
    }

    public final void setTab_pos(int i) {
        this.p = i;
    }

    public final void v(int i) {
        HealthBuddiesFragment newInstance = HealthBuddiesFragment.Companion.newInstance(i);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        supportFragmentManager.beginTransaction().replace(R.id.container, newInstance).commit();
    }

    public final void w(String str, String str2) {
        Intent intent = new Intent(this, ActivityInAppWebViewer.class);
        intent.putExtra(Constants.INTENT_EXTRA_TITLE.getValue(), str);
        intent.putExtra(Constants.INTENT_EXTRA_URL.getValue(), str2);
        startActivity(intent);
    }
}
