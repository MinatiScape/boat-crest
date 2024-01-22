package com.coveiot.android.healthbuddies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.android.healthbuddies.adapters.AddHealthBuddiesAdapter;
import com.coveiot.android.healthbuddies.utils.HealthBuddiesViewModelFactory;
import com.coveiot.android.healthbuddies.viewmodels.AddHealthBuddiesViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AddHealthBuddiesFragment extends BaseFragment implements AddHealthBuddiesAdapter.OnUserSelectedListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int m = 1;
    @Nullable
    public AddHealthBuddiesAdapter n;
    @Nullable
    public LoadingDialog o;
    public AddHealthBuddyListener p;
    public AddHealthBuddiesViewModel q;
    @Nullable
    public String r;

    /* loaded from: classes3.dex */
    public interface AddHealthBuddyListener {
        void onSendClicked(@NotNull String str);
    }

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final AddHealthBuddiesFragment newInstance(@NotNull String relationType) {
            Intrinsics.checkNotNullParameter(relationType, "relationType");
            AddHealthBuddiesFragment addHealthBuddiesFragment = new AddHealthBuddiesFragment();
            addHealthBuddiesFragment.r = relationType;
            return addHealthBuddiesFragment;
        }
    }

    public static final void o(AddHealthBuddiesFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            AddHealthBuddiesViewModel addHealthBuddiesViewModel = this$0.q;
            if (addHealthBuddiesViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                addHealthBuddiesViewModel = null;
            }
            this$0.n = new AddHealthBuddiesAdapter(requireActivity, addHealthBuddiesViewModel.getContacts(), this$0);
            ((RecyclerView) this$0._$_findCachedViewById(R.id.contactsList)).setAdapter(this$0.n);
            this$0.u();
        }
    }

    public static final void p(AddHealthBuddiesFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            AddHealthBuddiesAdapter addHealthBuddiesAdapter = this$0.n;
            Intrinsics.checkNotNull(addHealthBuddiesAdapter);
            AddHealthBuddiesViewModel addHealthBuddiesViewModel = this$0.q;
            AddHealthBuddiesViewModel addHealthBuddiesViewModel2 = null;
            if (addHealthBuddiesViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                addHealthBuddiesViewModel = null;
            }
            AddHealthBuddiesViewModel addHealthBuddiesViewModel3 = this$0.q;
            if (addHealthBuddiesViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                addHealthBuddiesViewModel3 = null;
            }
            Map<Long, CoveContact> transformedUser = addHealthBuddiesViewModel.getTransformedUser(addHealthBuddiesViewModel3.getSelUsers());
            AddHealthBuddiesViewModel addHealthBuddiesViewModel4 = this$0.q;
            if (addHealthBuddiesViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                addHealthBuddiesViewModel4 = null;
            }
            AddHealthBuddiesViewModel addHealthBuddiesViewModel5 = this$0.q;
            if (addHealthBuddiesViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                addHealthBuddiesViewModel2 = addHealthBuddiesViewModel5;
            }
            Map<Long, CoveContact> transformedUser2 = addHealthBuddiesViewModel4.getTransformedUser(addHealthBuddiesViewModel2.getSelectedBuddiesContacts());
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            addHealthBuddiesAdapter.setSelectedContactsForUsers(transformedUser, transformedUser2, requireActivity);
        }
    }

    public static final void q(AddHealthBuddiesFragment this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            if (z) {
                LoadingDialog loadingDialog = this$0.o;
                if (loadingDialog != null) {
                    loadingDialog.show();
                    return;
                }
                return;
            }
            LoadingDialog loadingDialog2 = this$0.o;
            if (loadingDialog2 != null) {
                loadingDialog2.dismiss();
            }
        }
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
        if (ContextCompat.checkSelfPermission(requireActivity(), "android.permission.READ_CONTACTS") != 0) {
            if (shouldShowRequestPermissionRationale("android.permission.READ_CONTACTS")) {
                s();
                return;
            } else {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{"android.permission.READ_CONTACTS"}, this.m);
                return;
            }
        }
        AddHealthBuddiesViewModel addHealthBuddiesViewModel = this.q;
        if (addHealthBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            addHealthBuddiesViewModel = null;
        }
        String str = this.r;
        Intrinsics.checkNotNull(str);
        addHealthBuddiesViewModel.loadContacts(str);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_add_health_buddies, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.m && permissions.length > 0 && Intrinsics.areEqual(permissions[0], "android.permission.READ_CONTACTS") && grantResults.length > 0 && grantResults[0] == 0) {
            AddHealthBuddiesViewModel addHealthBuddiesViewModel = this.q;
            if (addHealthBuddiesViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                addHealthBuddiesViewModel = null;
            }
            String str = this.r;
            Intrinsics.checkNotNull(str);
            addHealthBuddiesViewModel.loadContacts(str);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        n();
    }

    @Override // com.coveiot.android.healthbuddies.adapters.AddHealthBuddiesAdapter.OnUserSelectedListener
    public void onUserContactSelected(@NotNull CoveContact contact) {
        Intrinsics.checkNotNullParameter(contact, "contact");
        AddHealthBuddiesViewModel addHealthBuddiesViewModel = this.q;
        if (addHealthBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            addHealthBuddiesViewModel = null;
        }
        addHealthBuddiesViewModel.onUserSelection(contact);
        r();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.contactsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.o = new LoadingDialog(requireActivity);
        if (isAdded() && this.q == null) {
            FragmentActivity requireActivity2 = requireActivity();
            FragmentActivity requireActivity3 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
            ViewModel viewModel = ViewModelProviders.of(requireActivity2, new HealthBuddiesViewModelFactory(requireActivity3)).get(AddHealthBuddiesViewModel.class);
            Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n                requ…iesViewModel::class.java)");
            this.q = (AddHealthBuddiesViewModel) viewModel;
        }
        AddHealthBuddiesViewModel addHealthBuddiesViewModel = this.q;
        AddHealthBuddiesViewModel addHealthBuddiesViewModel2 = null;
        if (addHealthBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            addHealthBuddiesViewModel = null;
        }
        addHealthBuddiesViewModel.getValueUpdated().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.healthbuddies.fragments.c
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddHealthBuddiesFragment.o(AddHealthBuddiesFragment.this, (Boolean) obj);
            }
        });
        AddHealthBuddiesViewModel addHealthBuddiesViewModel3 = this.q;
        if (addHealthBuddiesViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            addHealthBuddiesViewModel3 = null;
        }
        addHealthBuddiesViewModel3.getSelectedValueUpdated().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.healthbuddies.fragments.b
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddHealthBuddiesFragment.p(AddHealthBuddiesFragment.this, (Boolean) obj);
            }
        });
        AddHealthBuddiesViewModel addHealthBuddiesViewModel4 = this.q;
        if (addHealthBuddiesViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            addHealthBuddiesViewModel2 = addHealthBuddiesViewModel4;
        }
        addHealthBuddiesViewModel2.getLoadingStatus().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.healthbuddies.fragments.a
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddHealthBuddiesFragment.q(AddHealthBuddiesFragment.this, ((Boolean) obj).booleanValue());
            }
        });
    }

    public final void r() {
        AddHealthBuddiesViewModel addHealthBuddiesViewModel = this.q;
        AddHealthBuddyListener addHealthBuddyListener = null;
        if (addHealthBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            addHealthBuddiesViewModel = null;
        }
        if (addHealthBuddiesViewModel.getSelectedBuddiesContacts() != null) {
            AddHealthBuddiesViewModel addHealthBuddiesViewModel2 = this.q;
            if (addHealthBuddiesViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                addHealthBuddiesViewModel2 = null;
            }
            if (addHealthBuddiesViewModel2.getSelectedBuddiesContacts().size() > 0) {
                AddHealthBuddyListener addHealthBuddyListener2 = this.p;
                if (addHealthBuddyListener2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                } else {
                    addHealthBuddyListener = addHealthBuddyListener2;
                }
                String str = this.r;
                Intrinsics.checkNotNull(str);
                addHealthBuddyListener.onSendClicked(str);
                return;
            }
        }
        String string = getString(R.string.please_select_health_clovers);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_select_health_clovers)");
        t(string);
    }

    public final void s() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.contact_permission_content);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.contact_permission_content)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.fragments.AddHealthBuddiesFragment$showReadContactPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                int i;
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                FragmentActivity requireActivity = this.requireActivity();
                i = this.m;
                AppUtils.openAppSettings(requireActivity, i);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void setAddHealthBuddyListener(@NotNull AddHealthBuddyListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.p = listener;
    }

    public final void setViewModel(@NotNull AddHealthBuddiesViewModel viewModel) {
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        this.q = viewModel;
    }

    public final void t(String str) {
        Toast.makeText(requireActivity(), str, 0).show();
    }

    public final void u() {
        ((EditText) _$_findCachedViewById(R.id.search)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.healthbuddies.fragments.AddHealthBuddiesFragment$watchSearchTextBox$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                AddHealthBuddiesAdapter addHealthBuddiesAdapter;
                Intrinsics.checkNotNull(editable);
                if (StringsKt__StringsKt.trim(editable).toString().length() == 0) {
                    addHealthBuddiesAdapter = AddHealthBuddiesFragment.this.n;
                    Intrinsics.checkNotNull(addHealthBuddiesAdapter);
                    addHealthBuddiesAdapter.filter(null);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                AddHealthBuddiesAdapter addHealthBuddiesAdapter;
                AddHealthBuddiesAdapter addHealthBuddiesAdapter2;
                addHealthBuddiesAdapter = AddHealthBuddiesFragment.this.n;
                if (addHealthBuddiesAdapter != null) {
                    addHealthBuddiesAdapter2 = AddHealthBuddiesFragment.this.n;
                    Intrinsics.checkNotNull(addHealthBuddiesAdapter2);
                    String valueOf = String.valueOf(charSequence);
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                    String lowerCase = valueOf.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    addHealthBuddiesAdapter2.filter(lowerCase);
                }
            }
        });
    }
}
