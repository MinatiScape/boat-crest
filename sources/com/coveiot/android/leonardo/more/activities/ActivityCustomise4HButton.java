package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.response.GetButtonFunctionResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityCustomise4hButtonBinding;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.more.adapters.Customise4hButtonLongPressAdapter;
import com.coveiot.android.leonardo.more.adapters.Customise4hButtonShortPressAdapter;
import com.coveiot.android.leonardo.more.listeners.Customise4hButtonLongPressListener;
import com.coveiot.android.leonardo.more.listeners.Customise4hButtonShortPressListener;
import com.coveiot.android.leonardo.more.listeners.GenericBandAppCoveApiListener;
import com.coveiot.android.leonardo.more.models.Customise4hButtonData;
import com.coveiot.android.leonardo.more.viewmodel.ActivityCustomise4hButtonViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.coveaccess.model.server.GetAppConfigRes;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityCustomise4HButton extends BaseActivity implements GenericBandAppCoveApiListener, Customise4hButtonLongPressListener, Customise4hButtonShortPressListener, ViewModelListener {
    public ActivityCustomise4hButtonBinding binding;
    public ActivityCustomise4hButtonViewModel q;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage r;
    @Nullable
    public Customise4hButtonLongPressAdapter s;
    @Nullable
    public Customise4hButtonShortPressAdapter t;
    public Customise4hButtonData v;
    public Customise4hButtonData w;
    @Nullable
    public GetButtonFunctionResponse x;
    public boolean y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public String p = ActivityCustomise4HButton.class.getSimpleName();
    @NotNull
    public ArrayList<Customise4hButtonData> u = new ArrayList<>();

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<GetButtonFunctionResponse, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetButtonFunctionResponse getButtonFunctionResponse) {
            invoke2(getButtonFunctionResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable GetButtonFunctionResponse getButtonFunctionResponse) {
            if (getButtonFunctionResponse != null) {
                ActivityCustomise4HButton.this.x = getButtonFunctionResponse;
                ActivityCustomise4hButtonViewModel activityCustomise4hButtonViewModel = ActivityCustomise4HButton.this.q;
                if (activityCustomise4hButtonViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    activityCustomise4hButtonViewModel = null;
                }
                activityCustomise4hButtonViewModel.getButtonActionSupportedItems(String.valueOf(getButtonFunctionResponse.getVersionNumber()));
                return;
            }
            ActivityCustomise4HButton.this.dismissProgress();
            ActivityCustomise4HButton activityCustomise4HButton = ActivityCustomise4HButton.this;
            String string = activityCustomise4HButton.getString(R.string.some_thing_went_wrong);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
            String string2 = ActivityCustomise4HButton.this.getString(R.string.please_try_again);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_try_again)");
            activityCustomise4HButton.F(string, string2);
        }
    }

    public static final void B(ActivityCustomise4HButton this$0, View view) {
        Customise4hButtonData customise4hButtonData;
        Customise4hButtonData customise4hButtonData2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0) && BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            Customise4hButtonData customise4hButtonData3 = this$0.v;
            Customise4hButtonData customise4hButtonData4 = null;
            if (customise4hButtonData3 != null && this$0.w != null && this$0.x != null) {
                this$0.showProgress();
                ActivityCustomise4hButtonViewModel activityCustomise4hButtonViewModel = this$0.q;
                if (activityCustomise4hButtonViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    activityCustomise4hButtonViewModel = null;
                }
                Customise4hButtonData customise4hButtonData5 = this$0.v;
                if (customise4hButtonData5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mShortPressSelectedItem");
                    customise4hButtonData5 = null;
                }
                Customise4hButtonData customise4hButtonData6 = this$0.w;
                if (customise4hButtonData6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mLongPressSelectedItem");
                } else {
                    customise4hButtonData4 = customise4hButtonData6;
                }
                GetButtonFunctionResponse getButtonFunctionResponse = this$0.x;
                Intrinsics.checkNotNull(getButtonFunctionResponse);
                activityCustomise4hButtonViewModel.saveButtonActionsToBand(customise4hButtonData5, customise4hButtonData4, getButtonFunctionResponse.getVersionNumber());
                return;
            } else if (customise4hButtonData3 != null && this$0.w == null) {
                int size = this$0.u.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        customise4hButtonData2 = null;
                        break;
                    }
                    GetButtonFunctionResponse getButtonFunctionResponse2 = this$0.x;
                    if (getButtonFunctionResponse2 != null && this$0.u.get(i).getFwCode() == getButtonFunctionResponse2.getLongPressFunction()) {
                        customise4hButtonData2 = new Customise4hButtonData(this$0.u.get(i).getActionId(), this$0.u.get(i).getName(), this$0.u.get(i).getFwCode(), false, false);
                        break;
                    }
                    i++;
                }
                this$0.showProgress();
                ActivityCustomise4hButtonViewModel activityCustomise4hButtonViewModel2 = this$0.q;
                if (activityCustomise4hButtonViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    activityCustomise4hButtonViewModel2 = null;
                }
                Customise4hButtonData customise4hButtonData7 = this$0.v;
                if (customise4hButtonData7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mShortPressSelectedItem");
                } else {
                    customise4hButtonData4 = customise4hButtonData7;
                }
                Intrinsics.checkNotNull(customise4hButtonData2);
                GetButtonFunctionResponse getButtonFunctionResponse3 = this$0.x;
                Intrinsics.checkNotNull(getButtonFunctionResponse3);
                activityCustomise4hButtonViewModel2.saveButtonActionsToBand(customise4hButtonData4, customise4hButtonData2, getButtonFunctionResponse3.getVersionNumber());
                return;
            } else if (customise4hButtonData3 != null || this$0.w == null) {
                return;
            } else {
                int size2 = this$0.u.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size2) {
                        customise4hButtonData = null;
                        break;
                    }
                    GetButtonFunctionResponse getButtonFunctionResponse4 = this$0.x;
                    if (getButtonFunctionResponse4 != null && this$0.u.get(i2).getFwCode() == getButtonFunctionResponse4.getShortPressFunction()) {
                        customise4hButtonData = new Customise4hButtonData(this$0.u.get(i2).getActionId(), this$0.u.get(i2).getName(), this$0.u.get(i2).getFwCode(), false, false);
                        break;
                    }
                    i2++;
                }
                this$0.showProgress();
                ActivityCustomise4hButtonViewModel activityCustomise4hButtonViewModel3 = this$0.q;
                if (activityCustomise4hButtonViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    activityCustomise4hButtonViewModel3 = null;
                }
                Intrinsics.checkNotNull(customise4hButtonData);
                Customise4hButtonData customise4hButtonData8 = this$0.w;
                if (customise4hButtonData8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mLongPressSelectedItem");
                } else {
                    customise4hButtonData4 = customise4hButtonData8;
                }
                GetButtonFunctionResponse getButtonFunctionResponse5 = this$0.x;
                Intrinsics.checkNotNull(getButtonFunctionResponse5);
                activityCustomise4hButtonViewModel3.saveButtonActionsToBand(customise4hButtonData, customise4hButtonData4, getButtonFunctionResponse5.getVersionNumber());
                return;
            }
        }
        this$0.dismissProgress();
        if (!AppUtils.isNetConnected(this$0)) {
            String string = this$0.getString(R.string.no_internet_connection);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.no_internet_connection)");
            String string2 = this$0.getString(R.string.please_check_your_internet);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_check_your_internet)");
            this$0.F(string, string2);
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            String string3 = this$0.getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.band_not_connected)");
            String string4 = this$0.getString(R.string.please_connect_the_device);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.please_connect_the_device)");
            this$0.F(string3, string4);
        }
    }

    public static final void E(ActivityCustomise4HButton this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void G(ActivityCustomise4HButton this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.r;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }
        this$0.finish();
    }

    public static final void I(ActivityCustomise4HButton this$0, ArrayList shortPressNewData, ArrayList longPressNewData) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(shortPressNewData, "$shortPressNewData");
        Intrinsics.checkNotNullParameter(longPressNewData, "$longPressNewData");
        this$0.dismissProgress();
        Customise4hButtonShortPressAdapter customise4hButtonShortPressAdapter = this$0.t;
        if (customise4hButtonShortPressAdapter != null) {
            customise4hButtonShortPressAdapter.updateData(shortPressNewData);
        }
        Customise4hButtonLongPressAdapter customise4hButtonLongPressAdapter = this$0.s;
        if (customise4hButtonLongPressAdapter != null) {
            customise4hButtonLongPressAdapter.updateData(longPressNewData);
        }
    }

    public static final void y(BottomSheetDialogTwoButtons dialog, ActivityCustomise4HButton this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void z(BottomSheetDialogTwoButtons dialog, ActivityCustomise4HButton this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.getBinding().btnSave.performClick();
    }

    public final void A() {
        getBinding().btnSave.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.n5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCustomise4HButton.B(ActivityCustomise4HButton.this, view);
            }
        });
    }

    public final void C() {
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback() { // from class: com.coveiot.android.leonardo.more.activities.ActivityCustomise4HButton$onBackPressedListener$1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                boolean z;
                z = ActivityCustomise4HButton.this.y;
                if (z) {
                    ActivityCustomise4HButton.this.x();
                } else {
                    ActivityCustomise4HButton.this.finish();
                }
            }
        });
    }

    public final void D() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.customise_4h_button));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.o5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCustomise4HButton.E(ActivityCustomise4HButton.this, view);
            }
        });
        getBinding().toolText.setText(getResources().getText(R.string.personalise_your_journey));
        w();
    }

    public final void F(String str, String str2) {
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage;
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = new BottomSheetDialogOneButtonTitleMessage(this, str, str2);
        this.r = bottomSheetDialogOneButtonTitleMessage2;
        String string = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage2.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.m5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCustomise4HButton.G(ActivityCustomise4HButton.this, view);
            }
        });
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.r;
        boolean z = false;
        if (bottomSheetDialogOneButtonTitleMessage3 != null && !bottomSheetDialogOneButtonTitleMessage3.isShowing()) {
            z = true;
        }
        if (!z || (bottomSheetDialogOneButtonTitleMessage = this.r) == null) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void H() {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = this.u.iterator();
        int i = 0;
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            Customise4hButtonData customise4hButtonData = (Customise4hButtonData) next;
            GetButtonFunctionResponse getButtonFunctionResponse = this.x;
            if (getButtonFunctionResponse != null && getButtonFunctionResponse.getShortPressFunction() == customise4hButtonData.getFwCode()) {
                arrayList.add(new Customise4hButtonData(customise4hButtonData.getActionId(), customise4hButtonData.getName(), customise4hButtonData.getFwCode(), true, true));
            } else {
                GetButtonFunctionResponse getButtonFunctionResponse2 = this.x;
                if ((getButtonFunctionResponse2 == null || getButtonFunctionResponse2.getLongPressFunction() != customise4hButtonData.getFwCode()) ? false : false) {
                    arrayList.add(new Customise4hButtonData(customise4hButtonData.getActionId(), customise4hButtonData.getName(), customise4hButtonData.getFwCode(), false, false));
                } else {
                    arrayList.add(customise4hButtonData);
                }
            }
            i = i2;
        }
        int i3 = 0;
        for (Object obj : this.u) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            Customise4hButtonData customise4hButtonData2 = (Customise4hButtonData) obj;
            GetButtonFunctionResponse getButtonFunctionResponse3 = this.x;
            if (getButtonFunctionResponse3 != null && getButtonFunctionResponse3.getLongPressFunction() == customise4hButtonData2.getFwCode()) {
                arrayList2.add(new Customise4hButtonData(customise4hButtonData2.getActionId(), customise4hButtonData2.getName(), customise4hButtonData2.getFwCode(), true, true));
            } else {
                GetButtonFunctionResponse getButtonFunctionResponse4 = this.x;
                if (getButtonFunctionResponse4 != null && getButtonFunctionResponse4.getShortPressFunction() == customise4hButtonData2.getFwCode()) {
                    arrayList2.add(new Customise4hButtonData(customise4hButtonData2.getActionId(), customise4hButtonData2.getName(), customise4hButtonData2.getFwCode(), false, false));
                } else {
                    arrayList2.add(customise4hButtonData2);
                }
            }
            i3 = i4;
        }
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.r5
            @Override // java.lang.Runnable
            public final void run() {
                ActivityCustomise4HButton.I(ActivityCustomise4HButton.this, arrayList, arrayList2);
            }
        });
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

    @NotNull
    public final ActivityCustomise4hButtonBinding getBinding() {
        ActivityCustomise4hButtonBinding activityCustomise4hButtonBinding = this.binding;
        if (activityCustomise4hButtonBinding != null) {
            return activityCustomise4hButtonBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetDialogOneButtonTitleMessage() {
        return this.r;
    }

    @Nullable
    public final Customise4hButtonLongPressAdapter getCustomise4hButtonLongPressAdapter() {
        return this.s;
    }

    @Nullable
    public final Customise4hButtonShortPressAdapter getCustomise4hButtonShortPressAdapter() {
        return this.t;
    }

    @NotNull
    public final ArrayList<Customise4hButtonData> getMData() {
        return this.u;
    }

    public final String getTAG() {
        return this.p;
    }

    @Override // com.coveiot.android.leonardo.more.listeners.Customise4hButtonLongPressListener
    public void longPressSelectedItem(@NotNull Customise4hButtonData selectedItem, @NotNull ArrayList<Customise4hButtonData> adapterData, int i) {
        Intrinsics.checkNotNullParameter(selectedItem, "selectedItem");
        Intrinsics.checkNotNullParameter(adapterData, "adapterData");
        boolean z = true;
        this.y = true;
        this.w = selectedItem;
        Button button = getBinding().btnSave;
        int i2 = 0;
        if (this.v == null && this.w == null) {
            z = false;
        }
        button.setEnabled(z);
        ArrayList<Customise4hButtonData> arrayList = new ArrayList<>();
        for (Object obj : adapterData) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            Customise4hButtonData customise4hButtonData = (Customise4hButtonData) obj;
            if (customise4hButtonData.isEnabled() && customise4hButtonData.isChecked()) {
                arrayList.add(new Customise4hButtonData(customise4hButtonData.getActionId(), customise4hButtonData.getName(), customise4hButtonData.getFwCode(), false, false));
            } else if (!customise4hButtonData.isEnabled() && !customise4hButtonData.isChecked()) {
                arrayList.add(new Customise4hButtonData(customise4hButtonData.getActionId(), customise4hButtonData.getName(), customise4hButtonData.getFwCode(), true, true));
            } else {
                arrayList.add(customise4hButtonData);
            }
            i2 = i3;
        }
        Customise4hButtonShortPressAdapter customise4hButtonShortPressAdapter = this.t;
        if (customise4hButtonShortPressAdapter != null) {
            customise4hButtonShortPressAdapter.updateData(arrayList);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityCustomise4hButtonBinding inflate = ActivityCustomise4hButtonBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        ActivityCustomise4hButtonViewModel activityCustomise4hButtonViewModel = (ActivityCustomise4hButtonViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityCustomise4hButtonViewModel.class);
        this.q = activityCustomise4hButtonViewModel;
        ActivityCustomise4hButtonViewModel activityCustomise4hButtonViewModel2 = null;
        if (activityCustomise4hButtonViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityCustomise4hButtonViewModel = null;
        }
        activityCustomise4hButtonViewModel.setMListener(this);
        ActivityCustomise4hButtonViewModel activityCustomise4hButtonViewModel3 = this.q;
        if (activityCustomise4hButtonViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            activityCustomise4hButtonViewModel2 = activityCustomise4hButtonViewModel3;
        }
        activityCustomise4hButtonViewModel2.setSaveActionsListener(this);
        showProgress();
        D();
        A();
        C();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        dismissProgress();
        String string = getString(R.string.failed);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.failed)");
        String string2 = getString(R.string.failed_to_save);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.failed_to_save)");
        F(string, string2);
    }

    @Override // com.coveiot.android.leonardo.more.listeners.GenericBandAppCoveApiListener
    public void onFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        dismissProgress();
        String string = getString(R.string.some_thing_went_wrong);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
        String string2 = getString(R.string.please_try_again);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_try_again)");
        F(string, string2);
    }

    @Override // com.coveiot.android.leonardo.more.listeners.GenericBandAppCoveApiListener
    public <T> void onSuccess(T t) {
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type com.coveiot.coveaccess.model.server.GetAppConfigRes.ActionItemsData");
        List<GetAppConfigRes.ActionItemsData.ActionItem> actions = ((GetAppConfigRes.ActionItemsData) t).getActions();
        Intrinsics.checkNotNullExpressionValue(actions, "res.actions");
        int i = 0;
        for (T t2 : actions) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            GetAppConfigRes.ActionItemsData.ActionItem actionItem = (GetAppConfigRes.ActionItemsData.ActionItem) t2;
            String actionId = actionItem.getActionId();
            Intrinsics.checkNotNullExpressionValue(actionId, "actionItem.actionId");
            String name = actionItem.getName();
            Intrinsics.checkNotNullExpressionValue(name, "actionItem.name");
            this.u.add(new Customise4hButtonData(actionId, name, actionItem.getFwCode(), true, false));
            i = i2;
        }
        this.s = new Customise4hButtonLongPressAdapter(this.u, this);
        getBinding().rvLongpress.setAdapter(this.s);
        this.t = new Customise4hButtonShortPressAdapter(this.u, this);
        getBinding().rvShortpress.setAdapter(this.t);
        H();
    }

    public final void setBinding(@NotNull ActivityCustomise4hButtonBinding activityCustomise4hButtonBinding) {
        Intrinsics.checkNotNullParameter(activityCustomise4hButtonBinding, "<set-?>");
        this.binding = activityCustomise4hButtonBinding;
    }

    public final void setBottomSheetDialogOneButtonTitleMessage(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.r = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setCustomise4hButtonLongPressAdapter(@Nullable Customise4hButtonLongPressAdapter customise4hButtonLongPressAdapter) {
        this.s = customise4hButtonLongPressAdapter;
    }

    public final void setCustomise4hButtonShortPressAdapter(@Nullable Customise4hButtonShortPressAdapter customise4hButtonShortPressAdapter) {
        this.t = customise4hButtonShortPressAdapter;
    }

    public final void setMData(@NotNull ArrayList<Customise4hButtonData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.u = arrayList;
    }

    public final void setTAG(String str) {
        this.p = str;
    }

    @Override // com.coveiot.android.leonardo.more.listeners.Customise4hButtonShortPressListener
    public void shortPressSelectedItem(@NotNull Customise4hButtonData selectedItem, @NotNull ArrayList<Customise4hButtonData> adapterData, int i) {
        Intrinsics.checkNotNullParameter(selectedItem, "selectedItem");
        Intrinsics.checkNotNullParameter(adapterData, "adapterData");
        boolean z = true;
        this.y = true;
        this.v = selectedItem;
        Button button = getBinding().btnSave;
        int i2 = 0;
        if (this.v == null && this.w == null) {
            z = false;
        }
        button.setEnabled(z);
        ArrayList<Customise4hButtonData> arrayList = new ArrayList<>();
        for (Object obj : adapterData) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            Customise4hButtonData customise4hButtonData = (Customise4hButtonData) obj;
            if (customise4hButtonData.isEnabled() && customise4hButtonData.isChecked()) {
                arrayList.add(new Customise4hButtonData(customise4hButtonData.getActionId(), customise4hButtonData.getName(), customise4hButtonData.getFwCode(), false, false));
            } else if (!customise4hButtonData.isEnabled() && !customise4hButtonData.isChecked()) {
                arrayList.add(new Customise4hButtonData(customise4hButtonData.getActionId(), customise4hButtonData.getName(), customise4hButtonData.getFwCode(), true, true));
            } else {
                arrayList.add(customise4hButtonData);
            }
            i2 = i3;
        }
        Customise4hButtonLongPressAdapter customise4hButtonLongPressAdapter = this.s;
        if (customise4hButtonLongPressAdapter != null) {
            customise4hButtonLongPressAdapter.updateData(arrayList);
        }
    }

    public final void w() {
        if (AppUtils.isNetConnected(this) && BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            ActivityCustomise4hButtonViewModel activityCustomise4hButtonViewModel = this.q;
            if (activityCustomise4hButtonViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                activityCustomise4hButtonViewModel = null;
            }
            activityCustomise4hButtonViewModel.getButtonActionsFromBand(new a());
            return;
        }
        dismissProgress();
        if (!AppUtils.isNetConnected(this)) {
            String string = getString(R.string.no_internet_connection);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.no_internet_connection)");
            String string2 = getString(R.string.please_check_your_internet);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_check_your_internet)");
            F(string, string2);
        } else if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            String string3 = getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.band_not_connected)");
            String string4 = getString(R.string.please_connect_the_device);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.please_connect_the_device)");
            F(string3, string4);
        }
    }

    public final void x() {
        String string = getString(R.string.confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…on.R.string.confirmation)");
        String string2 = getString(R.string.save_changes);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…on.R.string.save_changes)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.save_changes_btn);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.an….string.save_changes_btn)");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.q5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCustomise4HButton.z(BottomSheetDialogTwoButtons.this, this, view);
            }
        });
        String string4 = getString(R.string.discard);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(com.coveiot.an…igation.R.string.discard)");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.p5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCustomise4HButton.y(BottomSheetDialogTwoButtons.this, this, view);
            }
        });
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
        dismissProgress();
        String string = getString(R.string.success);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success)");
        String string2 = getString(R.string.setting_saved_successfully);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.setting_saved_successfully)");
        F(string, string2);
    }
}
