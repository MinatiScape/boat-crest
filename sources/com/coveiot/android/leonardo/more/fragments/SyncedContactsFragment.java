package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ContactData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.SyncContactsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnessbuddies.utils.PermissionUtils;
import com.coveiot.android.leonardo.more.activities.ActivitySyncContacts;
import com.coveiot.android.leonardo.more.adapters.SyncedContactsAdapter;
import com.coveiot.android.leonardo.more.viewmodel.BTContactsViewmodel;
import com.coveiot.android.sos.viewmodel.SOSSettingsViewmodel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SyncedContactsFragment extends BaseFragment implements SyncedContactsAdapter.ContactListener, SuccessResultListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public SOSSettingsViewmodel m;
    public BTContactsViewmodel n;
    public boolean o;
    public boolean p;
    @Nullable
    public CoveContact q;
    public boolean s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public List<CoveContact> r = new ArrayList();
    public final int t = 1;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SyncedContactsFragment newInstance() {
            SyncedContactsFragment syncedContactsFragment = new SyncedContactsFragment();
            syncedContactsFragment.setArguments(new Bundle());
            return syncedContactsFragment;
        }
    }

    @JvmStatic
    @NotNull
    public static final SyncedContactsFragment newInstance() {
        return Companion.newInstance();
    }

    public static final void r(SyncedContactsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.more.activities.ActivitySyncContacts");
        ((ActivitySyncContacts) activity).addContacts();
    }

    public static final void t(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    public static final void u(SyncedContactsFragment this$0, CoveContact deleteContact, BottomSheetDialogTwoButtons sheetDialogTwoButtons, List list, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(deleteContact, "$deleteContact");
        Intrinsics.checkNotNullParameter(sheetDialogTwoButtons, "$sheetDialogTwoButtons");
        if (AppUtils.isBluetoothEnabled(this$0.requireContext())) {
            CoveContact coveContact = this$0.q;
            if (coveContact != null) {
                Intrinsics.checkNotNull(coveContact);
                if (coveContact.getPhoneNumber().equals(deleteContact.getPhoneNumber())) {
                    SOSSettingsViewmodel sOSSettingsViewmodel = this$0.m;
                    if (sOSSettingsViewmodel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        sOSSettingsViewmodel = null;
                    }
                    sOSSettingsViewmodel.setSOSConfigSettings(false, "", "", true);
                }
            }
            sheetDialogTwoButtons.dismiss();
            if (list != null) {
                this$0.s(list);
                return;
            }
            return;
        }
        Toast.makeText(this$0.requireContext(), (int) R.string.bluetooth_off_message, 0).show();
    }

    public static final void v(BottomSheetDialogTwoButtons sheetDialogTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(sheetDialogTwoButtons, "$sheetDialogTwoButtons");
        sheetDialogTwoButtons.dismiss();
    }

    public static final void w(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
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

    @Override // com.coveiot.android.leonardo.more.adapters.SyncedContactsAdapter.ContactListener
    public void onContactDelete(@NotNull CoveContact coveContact) {
        boolean z;
        Intrinsics.checkNotNullParameter(coveContact, "coveContact");
        ArrayList<CoveContact> syncedContacts = UserDataManager.getInstance(getContext()).getSyncedContacts();
        if (syncedContacts != null && syncedContacts.size() == 1) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            if (!companion.isCADevice(context)) {
                Context context2 = getContext();
                Intrinsics.checkNotNull(context2);
                if (!companion.isCYDevice(context2)) {
                    Context context3 = getContext();
                    Intrinsics.checkNotNull(context3);
                    if (!companion.isPS1Device(context3)) {
                        Context requireContext = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        if (!companion.isTouchELXDevice(requireContext)) {
                            Context requireContext2 = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                            if (!companion.isMatrixDevice(requireContext2)) {
                                Context requireContext3 = requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                                if (!companion.isEastApexDevice(requireContext3)) {
                                    Context requireContext4 = requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                                    if (!companion.isBESDevice(requireContext4)) {
                                        Toast.makeText(getContext(), "Cannot delete this contact", 1).show();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        CoveContact coveContact2 = this.q;
        if (coveContact2 != null) {
            Intrinsics.checkNotNull(coveContact2);
            z = coveContact2.getPhoneNumber().equals(coveContact.getPhoneNumber());
        } else {
            z = false;
        }
        this.p = z;
        if (syncedContacts != null) {
            syncedContacts.remove(coveContact);
        }
        updateContacts(syncedContacts, coveContact);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_synced_cotacts, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        Toast.makeText(requireContext(), str, 1).show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.t && permissions.length > 0 && Intrinsics.areEqual(permissions[0], "android.permission.READ_CONTACTS") && grantResults.length > 0 && grantResults[0] == 0) {
            q();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        p();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.SyncedContactsAdapter.ContactListener
    public void onSOSCLicked() {
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        themesUtils.showCommonMessageDialog(requireActivity, "Chosen as the emergency contact number");
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        if (this.p) {
            SessionManager.getInstance(requireContext()).saveSOSContact(null);
        }
        q();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        SOSSettingsViewmodel sOSSettingsViewmodel = new SOSSettingsViewmodel(requireContext);
        this.m = sOSSettingsViewmodel;
        sOSSettingsViewmodel.setMListener(this);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        BTContactsViewmodel bTContactsViewmodel = new BTContactsViewmodel(requireContext2);
        this.n = bTContactsViewmodel;
        bTContactsViewmodel.setMListener(this);
        ((RecyclerView) _$_findCachedViewById(R.id.recycler_view_synced_contacts)).setLayoutManager(new LinearLayoutManager(getContext()));
        int maxContactsInOneRequest = BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().getMaxContactsInOneRequest();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.you_can_add_up_to_20_contacts_only);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.you_c…d_up_to_20_contacts_only)");
        String format = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(maxContactsInOneRequest)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((TextView) _$_findCachedViewById(R.id.you_can_add_up_to)).setText(format);
    }

    public final void p() {
        PermissionUtils permissionUtils = PermissionUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        permissionUtils.checkPermission(requireContext, "android.permission.READ_CONTACTS", new SyncedContactsFragment$getContactsPermission$1(this));
    }

    public final void q() {
        CoveContact coveContact;
        ArrayList arrayList;
        if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isSosSupported()) {
            this.q = SessionManager.getInstance(requireContext()).getSOSContact();
        }
        SyncedContactsAdapter syncedContactsAdapter = null;
        boolean z = false;
        if (!this.s) {
            ArrayList<CoveContact> syncedContacts = UserDataManager.getInstance(getContext()).getSyncedContacts();
            if (syncedContacts == null || syncedContacts.isEmpty()) {
                this.s = true;
                BTContactsViewmodel bTContactsViewmodel = this.n;
                if (bTContactsViewmodel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("btContactsViewModel");
                    bTContactsViewmodel = null;
                }
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                bTContactsViewmodel.getUserDeviceSettings(requireContext);
            }
        }
        if (AppUtils.isEmpty(UserDataManager.getInstance(getContext()).getSyncedContacts()) && this.q == null) {
            ((ConstraintLayout) _$_findCachedViewById(R.id.tv_no_contacts)).setVisibility(0);
            ((LinearLayout) _$_findCachedViewById(R.id.sync_contacts_layout)).setVisibility(8);
        } else {
            ((ConstraintLayout) _$_findCachedViewById(R.id.tv_no_contacts)).setVisibility(8);
            ((LinearLayout) _$_findCachedViewById(R.id.sync_contacts_layout)).setVisibility(0);
            int maxContactsInOneRequest = BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().getMaxContactsInOneRequest();
            StringBuilder sb = new StringBuilder();
            ArrayList<CoveContact> syncedContacts2 = UserDataManager.getInstance(getContext()).getSyncedContacts();
            sb.append(syncedContacts2 != null ? syncedContacts2.size() : 0);
            sb.append('/');
            sb.append(maxContactsInOneRequest);
            String sb2 = sb.toString();
            SpannableString spannableString = new SpannableString(sb2);
            Context context = getContext();
            Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.color_b3b3b3)) : null;
            Intrinsics.checkNotNull(valueOf);
            spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), StringsKt__StringsKt.indexOf$default((CharSequence) sb2, MqttTopic.TOPIC_LEVEL_SEPARATOR, 0, false, 6, (Object) null), sb2.length(), 33);
            int i = R.id.tv_no_of_contacts;
            ((TextView) _$_findCachedViewById(i)).setText(spannableString);
            ((TextView) _$_findCachedViewById(i)).setMovementMethod(new LinkMovementMethod());
        }
        ((ConstraintLayout) _$_findCachedViewById(R.id.tv_no_contacts)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.u1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SyncedContactsFragment.r(SyncedContactsFragment.this, view);
            }
        });
        this.r = new ArrayList();
        ArrayList<CoveContact> syncedContacts3 = UserDataManager.getInstance(getContext()).getSyncedContacts();
        if (syncedContacts3 == null) {
            syncedContacts3 = new ArrayList<>();
        }
        this.r = syncedContacts3;
        if (!(syncedContacts3.isEmpty())) {
            BTContactsViewmodel bTContactsViewmodel2 = this.n;
            if (bTContactsViewmodel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btContactsViewModel");
                bTContactsViewmodel2 = null;
            }
            List<CoveContact> list = this.r;
            Intrinsics.checkNotNull(list);
            bTContactsViewmodel2.saveBTContactsToServer(list);
        }
        if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isSosSupported() && this.q != null) {
            List<CoveContact> list2 = this.r;
            if (list2 == null || list2.isEmpty()) {
                z = true;
            }
            if (!z) {
                List<CoveContact> list3 = this.r;
                Intrinsics.checkNotNull(list3);
                Iterator<CoveContact> it = list3.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CoveContact coveContact2 = this.q;
                    Intrinsics.checkNotNull(coveContact2);
                    if (coveContact2.getPhoneNumber().equals(it.next().getPhoneNumber())) {
                        this.o = true;
                        break;
                    }
                }
            }
        }
        this.r = new ArrayList();
        if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isSosSupported() && !this.o && (coveContact = this.q) != null && (arrayList = (ArrayList) this.r) != null) {
            arrayList.add(coveContact);
        }
        List<CoveContact> list4 = this.r;
        Intrinsics.checkNotNull(list4, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.utils.model.CoveContact>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.utils.model.CoveContact> }");
        ArrayList arrayList2 = (ArrayList) list4;
        ArrayList<CoveContact> syncedContacts4 = UserDataManager.getInstance(getContext()).getSyncedContacts();
        if (syncedContacts4 == null) {
            syncedContacts4 = new ArrayList<>();
        }
        arrayList2.addAll(syncedContacts4);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recycler_view_synced_contacts);
        ArrayList arrayList3 = (ArrayList) this.r;
        if (arrayList3 != null) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            syncedContactsAdapter = new SyncedContactsAdapter(arrayList3, this, requireContext2);
        }
        recyclerView.setAdapter(syncedContactsAdapter);
    }

    public final void s(final List<CoveContact> list) {
        showProgress(true);
        ArrayList<ContactData> arrayList = new ArrayList<>();
        for (CoveContact coveContact : list) {
            String name = coveContact.getName();
            Intrinsics.checkNotNullExpressionValue(name, "contact.name");
            String phoneNumber = coveContact.getPhoneNumber();
            Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
            arrayList.add(new ContactData(name, phoneNumber));
        }
        SyncContactsRequest syncContactsRequest = new SyncContactsRequest.Builder().Builder(arrayList).build();
        BleApi bleApi = BleApiManager.getInstance(getContext()).getBleApi();
        Intrinsics.checkNotNullExpressionValue(syncContactsRequest, "syncContactsRequest");
        bleApi.getData(syncContactsRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.more.fragments.SyncedContactsFragment$sendContactsToDevice$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SyncedContactsFragment.this.dismissProgress();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                BTContactsViewmodel bTContactsViewmodel;
                Intrinsics.checkNotNullParameter(response, "response");
                UserDataManager.getInstance(SyncedContactsFragment.this.getContext()).saveSyncedContacts((ArrayList) list);
                bTContactsViewmodel = SyncedContactsFragment.this.n;
                if (bTContactsViewmodel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("btContactsViewModel");
                    bTContactsViewmodel = null;
                }
                bTContactsViewmodel.saveBTContactsToServer(list);
                SyncedContactsFragment.this.dismissProgress();
                SyncedContactsFragment.this.q();
                SyncedContactsFragment.this.showSuccessDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void showSuccessDialog() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String string = getResources().getString(R.string.contact_sync_success);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.contact_sync_success)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(context, string);
        String string2 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …    R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.w1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SyncedContactsFragment.t(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void updateContacts(@Nullable final List<CoveContact> list, @NotNull final CoveContact deleteContact) {
        Intrinsics.checkNotNullParameter(deleteContact, "deleteContact");
        if (BleApiManager.getInstance(getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            String string = getString(this.p ? R.string.delete_emergency_contact : R.string.delete_contact);
            Intrinsics.checkNotNullExpressionValue(string, "if(sosContactDelete) get…(R.string.delete_contact)");
            String string2 = getString(this.p ? R.string.this_action_will_disable_sos_feature : R.string.do_you_want_delete);
            Intrinsics.checkNotNullExpressionValue(string2, "if(sosContactDelete) get…tring.do_you_want_delete)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(context, string, string2, false, 8, null);
            String string3 = getString(R.string.delete);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.delete)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.v1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SyncedContactsFragment.u(SyncedContactsFragment.this, deleteContact, bottomSheetDialogTwoButtons, list, view);
                }
            });
            String string4 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.y1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SyncedContactsFragment.v(BottomSheetDialogTwoButtons.this, view);
                }
            });
            bottomSheetDialogTwoButtons.show();
            return;
        }
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        String string5 = getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.band_not_connected)");
        String string6 = getString(R.string.please_connect_the_device);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.please_connect_the_device)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(context2, string5, string6);
        String string7 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string7, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.x1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SyncedContactsFragment.w(BottomSheetDialogOneButtonTitleMessage.this, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }
}
