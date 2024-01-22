package com.coveiot.android.leonardo.more.fragments;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentTroubleshootTestingBinding;
import com.coveiot.android.dashboard2.ViewModelFactory;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener;
import com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.model.TroubleshootingTestModel;
import com.coveiot.android.leonardo.more.activities.ActivityContactusFeedback;
import com.coveiot.android.leonardo.more.activities.ActivityDoNotDisturb;
import com.coveiot.android.leonardo.more.activities.ActivityNotifications;
import com.coveiot.android.leonardo.more.adapters.TroubleshootingTestingAdapter;
import com.coveiot.android.leonardo.more.fragments.TroubleshootTestingFragment;
import com.coveiot.android.leonardo.more.models.TestingStatus;
import com.coveiot.android.leonardo.more.models.TroubleshootTestCategory;
import com.coveiot.android.leonardo.more.models.TroubleshootTestType;
import com.coveiot.android.leonardo.more.models.TroubleshootTestUpdateModel;
import com.coveiot.android.leonardo.more.viewmodel.TroubleshootTestingViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleCustomMessageWithClose;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.EditPhoneNumberDialog;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.model.OnTestNotificationEvent;
import com.coveiot.android.theme.model.OnWatchBT3EnabledEvent;
import com.coveiot.android.theme.permissions.PermissionCheckDialog;
import com.coveiot.android.theme.utils.BT3Utils;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.phonevaildation.model.PhoneValidationResponse;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class TroubleshootTestingFragment extends BaseFragment implements TroubleshootingTestingAdapter.OnItemClickListener, IBT3ConnectionChangeListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public Handler A;
    @Nullable
    public BottomSheetDialogImageTitleMessage B;
    @NotNull
    public ActivityResultLauncher<IntentSenderRequest> C;
    @NotNull
    public ActivityResultLauncher<Intent> D;
    @Nullable
    public EditPhoneNumberDialog E;
    @Nullable
    public BottomSheetDialogTwoButtons F;
    @Nullable
    public FragmentTroubleshootTestingBinding n;
    @Nullable
    public String p;
    public TroubleshootTestingViewModel q;
    public TroubleshootingTestingAdapter r;
    public BT3CallViewModel s;
    @Nullable
    public TroubleshootingTestModel t;
    @Nullable
    public DataSyncViewModel u;
    @Nullable
    public BottomSheetDialogTwoButtons w;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle x;
    @Nullable
    public BottomSheetDialogTwoButtons y;
    @Nullable
    public BottomSheetDialogOneButtonTitleCustomMessageWithClose z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "TroubleshootTestingFragment";
    @NotNull
    public List<TroubleshootingTestModel> o = new ArrayList();
    public final int v = 1001;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final TroubleshootTestingFragment newInstance(@NotNull String item) {
            Intrinsics.checkNotNullParameter(item, "item");
            TroubleshootTestingFragment troubleshootTestingFragment = new TroubleshootTestingFragment();
            Bundle bundle = new Bundle();
            bundle.putString("arg_item", item);
            troubleshootTestingFragment.setArguments(bundle);
            return troubleshootTestingFragment;
        }
    }

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TroubleshootTestType.values().length];
            try {
                iArr[TroubleshootTestType.DND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TroubleshootTestType.APP_NOTIFICATION_SETTINGS_ON_APP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TroubleshootTestType.SMS_NOTIFICATION_SETTINGS_ON_APP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TroubleshootTestType.CALL_NOTIFICATION_SETTINGS_ON_APP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TroubleshootTestType.APP_NOTIFICATION_ACCESS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[TroubleshootTestType.BT3_CONNECTIVITY_STATUS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<TroubleshootTestUpdateModel, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(TroubleshootTestingFragment this$0, TroubleshootTestUpdateModel it) {
            TextView textView;
            TextView textView2;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (this$0.isAdded()) {
                this$0.S();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                this$0.l0(it);
                if (it.getTestingStatus() == TestingStatus.PASSED) {
                    FragmentTroubleshootTestingBinding G = this$0.G();
                    if (G != null && (textView2 = G.troubleshootFailTv) != null) {
                        this$0.gone(textView2);
                    }
                    this$0.E();
                } else if (it.getTestingStatus() == TestingStatus.FAILED) {
                    FragmentTroubleshootTestingBinding G2 = this$0.G();
                    if (G2 != null && (textView = G2.troubleshootFailTv) != null) {
                        this$0.visible(textView);
                    }
                    this$0.J(FirebaseEventParams.EventName.CV_TRUBLESHOOT_ERROR);
                    String extra = it.getExtra();
                    if ((extra == null || extra.length() == 0) || !kotlin.text.m.equals$default(it.getExtra(), TroubleshootTestingViewModel.Companion.getWATCH_DISCONNECTED(), false, 2, null)) {
                        return;
                    }
                    this$0.showWatchDisconnectedDialog1();
                }
            }
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(TroubleshootTestUpdateModel troubleshootTestUpdateModel) {
            invoke2(troubleshootTestUpdateModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(final TroubleshootTestUpdateModel troubleshootTestUpdateModel) {
            Handler troubleshootTestHandler;
            if (TroubleshootTestingFragment.this.isAdded()) {
                Handler troubleshootTestHandler2 = TroubleshootTestingFragment.this.getTroubleshootTestHandler();
                if (troubleshootTestHandler2 != null) {
                    troubleshootTestHandler2.removeCallbacksAndMessages(null);
                }
                if (troubleshootTestUpdateModel == null || (troubleshootTestHandler = TroubleshootTestingFragment.this.getTroubleshootTestHandler()) == null) {
                    return;
                }
                final TroubleshootTestingFragment troubleshootTestingFragment = TroubleshootTestingFragment.this;
                troubleshootTestHandler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.fragments.w2
                    @Override // java.lang.Runnable
                    public final void run() {
                        TroubleshootTestingFragment.a.invoke$lambda$0(TroubleshootTestingFragment.this, troubleshootTestUpdateModel);
                    }
                }, 3000L);
            }
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<PendingIntent, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PendingIntent pendingIntent) {
            invoke2(pendingIntent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull PendingIntent result) {
            Intrinsics.checkNotNullParameter(result, "result");
            try {
                TroubleshootTestingFragment.this.C.launch(new IntentSenderRequest.Builder(result).build());
            } catch (Exception unused) {
                LogHelper.e(TroubleshootTestingFragment.this.getTAG(), "Launching the PendingIntent failed.");
                if (TroubleshootTestingFragment.this.isAdded()) {
                    ProfileData userDetails = SessionManager.getInstance(TroubleshootTestingFragment.this.requireContext()).getUserDetails();
                    if (userDetails != null) {
                        String mobileNumber = userDetails.getMobileNumber();
                        if (!(mobileNumber == null || mobileNumber.length() == 0)) {
                            TroubleshootTestingFragment.this.k0();
                            return;
                        }
                    }
                    TroubleshootTestingFragment.this.c0();
                }
            }
        }
    }

    public TroubleshootTestingFragment() {
        ActivityResultLauncher<IntentSenderRequest> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.coveiot.android.leonardo.more.fragments.h2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                TroubleshootTestingFragment.R(TroubleshootTestingFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…)\n            }\n        }");
        this.C = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.coveiot.android.leonardo.more.fragments.i2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                TroubleshootTestingFragment.F(TroubleshootTestingFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…)\n            }\n        }");
        this.D = registerForActivityResult2;
    }

    public static final void F(TroubleshootTestingFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            if (activityResult.getResultCode() == -1 || activityResult.getResultCode() == 0) {
                this$0.requireActivity().setResult(activityResult.getResultCode(), activityResult.getData());
                this$0.requireActivity().finish();
            }
        }
    }

    public static final void L(TroubleshootTestingFragment this$0) {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.isAdded() || (bottomSheetDialogOneButtonOneTitle = this$0.x) == null) {
            return;
        }
        boolean z = true;
        if (bottomSheetDialogOneButtonOneTitle == null || !bottomSheetDialogOneButtonOneTitle.isShowing()) {
            z = false;
        }
        if (z && (bottomSheetDialogOneButtonOneTitle2 = this$0.x) != null) {
            bottomSheetDialogOneButtonOneTitle2.dismiss();
        }
        this$0.x = null;
        TroubleshootingTestModel troubleshootingTestModel = this$0.t;
        if (troubleshootingTestModel != null) {
            Intrinsics.checkNotNull(troubleshootingTestModel);
            if (troubleshootingTestModel.getTestingStatus() == TestingStatus.FAILED) {
                TroubleshootingTestModel troubleshootingTestModel2 = this$0.t;
                Intrinsics.checkNotNull(troubleshootingTestModel2);
                if (troubleshootingTestModel2.getTestType() == TroubleshootTestType.BT3_CONNECTIVITY_STATUS) {
                    this$0.E();
                }
            }
        }
    }

    public static final void M(final TroubleshootTestingFragment this$0, View view) {
        TroubleshootTestCategory troubleshootTestCategory;
        TroubleshootTestCategory troubleshootTestCategory2;
        TroubleshootTestCategory troubleshootTestCategory3;
        TroubleshootTestCategory troubleshootTestCategory4;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.J(FirebaseEventParams.EventName.CV_TROUBLESHOOT_CTA);
        String str = this$0.p;
        if (str != null) {
            TroubleshootTestingViewModel troubleshootTestingViewModel = this$0.q;
            if (troubleshootTestingViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                troubleshootTestingViewModel = null;
            }
            troubleshootTestCategory = troubleshootTestingViewModel.getTroubleshootCategory(str);
        } else {
            troubleshootTestCategory = null;
        }
        boolean z = true;
        if (troubleshootTestCategory == TroubleshootTestCategory.SMS_NOTIFICATION) {
            if (this$0.D()) {
                if (AppUtils.isNetConnected(this$0.requireContext())) {
                    BleApi bleApi = BleApiManager.getInstance(this$0.requireContext()).getBleApi();
                    if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                        DataSyncViewModel dataSyncViewModel = this$0.u;
                        if (dataSyncViewModel == null || !dataSyncViewModel.checkIsSyncing()) {
                            z = false;
                        }
                        if (!z) {
                            GetPhoneNumberHintIntentRequest build = GetPhoneNumberHintIntentRequest.builder().build();
                            Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
                            Task<PendingIntent> phoneNumberHintIntent = Identity.getSignInClient((Activity) this$0.requireActivity()).getPhoneNumberHintIntent(build);
                            final b bVar = new b();
                            phoneNumberHintIntent.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.leonardo.more.fragments.l2
                                @Override // com.google.android.gms.tasks.OnSuccessListener
                                public final void onSuccess(Object obj) {
                                    TroubleshootTestingFragment.N(Function1.this, obj);
                                }
                            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.more.fragments.k2
                                @Override // com.google.android.gms.tasks.OnFailureListener
                                public final void onFailure(Exception exc) {
                                    TroubleshootTestingFragment.O(TroubleshootTestingFragment.this, exc);
                                }
                            });
                            return;
                        }
                        Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.sync_is_in_progress), 0).show();
                        return;
                    }
                    this$0.showWatchDisconnectedDialog1();
                    return;
                }
                Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.no_internet_connection), 0).show();
                return;
            }
            return;
        }
        String str2 = this$0.p;
        if (str2 != null) {
            TroubleshootTestingViewModel troubleshootTestingViewModel2 = this$0.q;
            if (troubleshootTestingViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                troubleshootTestingViewModel2 = null;
            }
            troubleshootTestCategory2 = troubleshootTestingViewModel2.getTroubleshootCategory(str2);
        } else {
            troubleshootTestCategory2 = null;
        }
        if (troubleshootTestCategory2 == TroubleshootTestCategory.APP_NOTIFICATION) {
            if (AppUtils.isNetConnected(this$0.requireContext())) {
                BleApi bleApi2 = BleApiManager.getInstance(this$0.requireContext()).getBleApi();
                if ((bleApi2 != null ? bleApi2.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                    DataSyncViewModel dataSyncViewModel2 = this$0.u;
                    if (!(dataSyncViewModel2 != null && dataSyncViewModel2.checkIsSyncing())) {
                        NotificationManager.getInstance().notifyOnTroubleshootAppNotificationTest(this$0.getContext(), Integer.MAX_VALUE - kotlin.ranges.h.random(new IntRange(1, 10), Random.Default), this$0.getString(R.string.test_app_notification));
                        String string = this$0.getString(R.string.troubleshoot_notification1);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.troubleshoot_notification1)");
                        String string2 = this$0.getString(R.string.did_you_receive_a_app_notification_on_your_watch);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.did_y…tification_on_your_watch)");
                        this$0.showTroubleshootConfirmationDialog(string, string2);
                        return;
                    }
                    Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.sync_is_in_progress), 0).show();
                    return;
                }
                this$0.showWatchDisconnectedDialog1();
                return;
            }
            Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.no_internet_connection), 0).show();
            return;
        }
        String str3 = this$0.p;
        if (str3 != null) {
            TroubleshootTestingViewModel troubleshootTestingViewModel3 = this$0.q;
            if (troubleshootTestingViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                troubleshootTestingViewModel3 = null;
            }
            troubleshootTestCategory3 = troubleshootTestingViewModel3.getTroubleshootCategory(str3);
        } else {
            troubleshootTestCategory3 = null;
        }
        if (troubleshootTestCategory3 == TroubleshootTestCategory.CALL_NOTIFICATION) {
            if (AppUtils.isNetConnected(this$0.requireContext())) {
                BleApi bleApi3 = BleApiManager.getInstance(this$0.requireContext()).getBleApi();
                if ((bleApi3 != null ? bleApi3.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                    DataSyncViewModel dataSyncViewModel3 = this$0.u;
                    if (dataSyncViewModel3 == null || !dataSyncViewModel3.checkIsSyncing()) {
                        z = false;
                    }
                    if (!z) {
                        String string3 = this$0.getString(R.string.troubleshoot_notification1);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.troubleshoot_notification1)");
                        String string4 = this$0.getString(R.string.did_you_receive_a_call_notification_);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.did_y…ive_a_call_notification_)");
                        this$0.showTroubleshootConfirmationDialog(string3, string4);
                        return;
                    }
                    Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.sync_is_in_progress), 0).show();
                    return;
                }
                this$0.showWatchDisconnectedDialog1();
                return;
            }
            Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.no_internet_connection), 0).show();
            return;
        }
        String str4 = this$0.p;
        if (str4 != null) {
            TroubleshootTestingViewModel troubleshootTestingViewModel4 = this$0.q;
            if (troubleshootTestingViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                troubleshootTestingViewModel4 = null;
            }
            troubleshootTestCategory4 = troubleshootTestingViewModel4.getTroubleshootCategory(str4);
        } else {
            troubleshootTestCategory4 = null;
        }
        if (troubleshootTestCategory4 == TroubleshootTestCategory.BLUETOOTH_CALLING) {
            if (AppUtils.isNetConnected(this$0.requireContext())) {
                BleApi bleApi4 = BleApiManager.getInstance(this$0.requireContext()).getBleApi();
                if ((bleApi4 != null ? bleApi4.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                    DataSyncViewModel dataSyncViewModel4 = this$0.u;
                    if (dataSyncViewModel4 == null || !dataSyncViewModel4.checkIsSyncing()) {
                        z = false;
                    }
                    if (!z) {
                        String string5 = this$0.getString(R.string.troubleshoot_notification1);
                        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.troubleshoot_notification1)");
                        String string6 = this$0.getString(R.string.did_the_watch_show_a_bt_call_);
                        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.did_the_watch_show_a_bt_call_)");
                        this$0.showTroubleshootConfirmationDialog(string5, string6);
                        return;
                    }
                    Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.sync_is_in_progress), 0).show();
                    return;
                }
                this$0.showWatchDisconnectedDialog1();
                return;
            }
            Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.no_internet_connection), 0).show();
        }
    }

    public static final void N(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void O(TroubleshootTestingFragment this$0, Exception e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(e, "e");
        String str = this$0.m;
        e.printStackTrace();
        LogHelper.e(str, String.valueOf(Unit.INSTANCE));
        if (this$0.isAdded()) {
            ProfileData userDetails = SessionManager.getInstance(this$0.requireContext()).getUserDetails();
            if (userDetails != null) {
                String mobileNumber = userDetails.getMobileNumber();
                if (!(mobileNumber == null || mobileNumber.length() == 0)) {
                    this$0.k0();
                    return;
                }
            }
            this$0.c0();
        }
    }

    public static final void P(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void R(TroubleshootTestingFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            String phoneNumberFromIntent = Identity.getSignInClient((Activity) this$0.requireActivity()).getPhoneNumberFromIntent(activityResult.getData());
            Intrinsics.checkNotNullExpressionValue(phoneNumberFromIntent, "getSignInClient(requireA…erFromIntent(result.data)");
            this$0.U(phoneNumberFromIntent);
        } catch (Exception unused) {
            LogHelper.e(this$0.m, "Phone Number Hint failed");
        }
    }

    public static final void W(TroubleshootTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.J(FirebaseEventParams.EventName.CV_TROUBLESHOOT_BACK);
        this$0.getParentFragmentManager().popBackStack();
    }

    public static final void Z(BottomSheetDialogImageTitleMessage dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void b0(TroubleshootTestingFragment this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0.requireActivity(), -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void d0(TroubleshootTestingFragment this$0, EditText editText, EditText editText2, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PhoneValidationResponse isValidPhoneNumber = AppUtils.isValidPhoneNumber(this$0.getContext(), editText.getText().toString(), editText2.getText().toString());
        if (isValidPhoneNumber.getParsedMobileNumber() != null) {
            String parsedMobileNumber = isValidPhoneNumber.getParsedMobileNumber();
            Intrinsics.checkNotNullExpressionValue(parsedMobileNumber, "response.parsedMobileNumber");
            this$0.U(parsedMobileNumber);
            EditPhoneNumberDialog editPhoneNumberDialog = this$0.E;
            Intrinsics.checkNotNull(editPhoneNumberDialog);
            editPhoneNumberDialog.dismiss();
            return;
        }
        Toast.makeText(this$0.getContext(), this$0.getString(R.string.please_enter_valid_phone_no), 1).show();
    }

    public static final void e0(TroubleshootTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditPhoneNumberDialog editPhoneNumberDialog = this$0.E;
        Intrinsics.checkNotNull(editPhoneNumberDialog);
        editPhoneNumberDialog.dismiss();
    }

    public static final void f0(TroubleshootTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleCustomMessageWithClose bottomSheetDialogOneButtonTitleCustomMessageWithClose = this$0.z;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleCustomMessageWithClose);
        bottomSheetDialogOneButtonTitleCustomMessageWithClose.dismiss();
        this$0.requireActivity().onBackPressed();
    }

    public static final void g0(TroubleshootTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
        this$0.showSuccessDialog();
        this$0.I(true);
    }

    public static final void h0(TroubleshootTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
        this$0.Q();
        this$0.I(false);
    }

    public static final void i0(TroubleshootTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.y;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
        this$0.requireActivity().onBackPressed();
    }

    public static final void j0(TroubleshootTestingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.y;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
        this$0.requireActivity().onBackPressed();
    }

    public final String B(boolean z) {
        return z ? "enable" : "disable";
    }

    public final String C() {
        String string = Settings.Secure.getString(requireActivity().getContentResolver(), AppConstants.ENABLE_NOTIFICATION_LISTENERS.getValue());
        if (string != null) {
            String packageName = requireContext().getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "requireContext().packageName");
            if (StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName, false, 2, (Object) null)) {
                return "enable";
            }
        }
        return "disable";
    }

    public final boolean D() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(requireActivity(), "android.permission.SEND_SMS") != 0) {
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                String string = getString(R.string.send_test_sms_permission_desc);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.send_test_sms_permission_desc)");
                new PermissionCheckDialog(requireActivity, string, new PermissionCheckDialog.OnItemClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.TroubleshootTestingFragment$checkSMSPermissionGranted$smsPermissionDialog$1
                    @Override // com.coveiot.android.theme.permissions.PermissionCheckDialog.OnItemClickListener
                    public void onCancelItemClickListener() {
                    }

                    @Override // com.coveiot.android.theme.permissions.PermissionCheckDialog.OnItemClickListener
                    public void onOKayItemClickListener() {
                        if (ContextCompat.checkSelfPermission(TroubleshootTestingFragment.this.requireActivity(), "android.permission.SEND_SMS") == 0) {
                            ActivityCompat.requestPermissions(TroubleshootTestingFragment.this.requireActivity(), new String[]{"android.permission.SEND_SMS"}, TroubleshootTestingFragment.this.getREQUEST_SMS_PERMISSION());
                        } else if (ActivityCompat.shouldShowRequestPermissionRationale(TroubleshootTestingFragment.this.requireActivity(), "android.permission.SEND_SMS")) {
                            ActivityCompat.requestPermissions(TroubleshootTestingFragment.this.requireActivity(), new String[]{"android.permission.SEND_SMS"}, TroubleshootTestingFragment.this.getREQUEST_SMS_PERMISSION());
                        } else {
                            TroubleshootTestingFragment troubleshootTestingFragment = TroubleshootTestingFragment.this;
                            String string2 = troubleshootTestingFragment.getString(R.string.require_send_sms_permission_);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.require_send_sms_permission_)");
                            troubleshootTestingFragment.a0(string2);
                        }
                    }
                }).show(getChildFragmentManager(), "smsPermissionDialog");
                return false;
            }
            return true;
        }
        return false;
    }

    public final void E() {
        TroubleshootingTestingAdapter troubleshootingTestingAdapter;
        TroubleshootingTestModel troubleshootingTestModel;
        TextView textView;
        List<TroubleshootingTestModel> list = this.o;
        if (list == null || list.isEmpty()) {
            return;
        }
        List<TroubleshootingTestModel> list2 = this.o;
        Intrinsics.checkNotNull(list2);
        Iterator<TroubleshootingTestModel> it = list2.iterator();
        while (true) {
            troubleshootingTestingAdapter = null;
            if (!it.hasNext()) {
                troubleshootingTestModel = null;
                break;
            }
            troubleshootingTestModel = it.next();
            if (troubleshootingTestModel.getTestingStatus() != TestingStatus.FAILED) {
                if (troubleshootingTestModel.getTestingStatus() == TestingStatus.NOT_STARTED) {
                    break;
                }
            } else {
                break;
            }
        }
        if (troubleshootingTestModel != null) {
            this.t = troubleshootingTestModel;
            switch (WhenMappings.$EnumSwitchMapping$0[troubleshootingTestModel.getTestType().ordinal()]) {
                case 1:
                    TroubleshootTestingViewModel troubleshootTestingViewModel = this.q;
                    if (troubleshootTestingViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel = null;
                    }
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    TroubleshootTestingViewModel troubleshootTestingViewModel2 = this.q;
                    if (troubleshootTestingViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel2 = null;
                    }
                    String str = this.p;
                    Intrinsics.checkNotNull(str);
                    TroubleshootTestCategory troubleshootCategory = troubleshootTestingViewModel2.getTroubleshootCategory(str);
                    Intrinsics.checkNotNull(troubleshootCategory);
                    troubleshootTestingViewModel.checkDnd(requireContext, troubleshootCategory);
                    break;
                case 2:
                    TroubleshootTestingViewModel troubleshootTestingViewModel3 = this.q;
                    if (troubleshootTestingViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel3 = null;
                    }
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    TroubleshootTestingViewModel troubleshootTestingViewModel4 = this.q;
                    if (troubleshootTestingViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel4 = null;
                    }
                    String str2 = this.p;
                    Intrinsics.checkNotNull(str2);
                    TroubleshootTestCategory troubleshootCategory2 = troubleshootTestingViewModel4.getTroubleshootCategory(str2);
                    Intrinsics.checkNotNull(troubleshootCategory2);
                    troubleshootTestingViewModel3.checkAppNotificationSettingsOnApp(requireContext2, troubleshootCategory2);
                    break;
                case 3:
                    TroubleshootTestingViewModel troubleshootTestingViewModel5 = this.q;
                    if (troubleshootTestingViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel5 = null;
                    }
                    Context requireContext3 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    TroubleshootTestingViewModel troubleshootTestingViewModel6 = this.q;
                    if (troubleshootTestingViewModel6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel6 = null;
                    }
                    String str3 = this.p;
                    Intrinsics.checkNotNull(str3);
                    TroubleshootTestCategory troubleshootCategory3 = troubleshootTestingViewModel6.getTroubleshootCategory(str3);
                    Intrinsics.checkNotNull(troubleshootCategory3);
                    troubleshootTestingViewModel5.checkSmsNotificationSettingsOnApp(requireContext3, troubleshootCategory3);
                    break;
                case 4:
                    TroubleshootTestingViewModel troubleshootTestingViewModel7 = this.q;
                    if (troubleshootTestingViewModel7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel7 = null;
                    }
                    Context requireContext4 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                    TroubleshootTestingViewModel troubleshootTestingViewModel8 = this.q;
                    if (troubleshootTestingViewModel8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel8 = null;
                    }
                    String str4 = this.p;
                    Intrinsics.checkNotNull(str4);
                    TroubleshootTestCategory troubleshootCategory4 = troubleshootTestingViewModel8.getTroubleshootCategory(str4);
                    Intrinsics.checkNotNull(troubleshootCategory4);
                    troubleshootTestingViewModel7.checkCallNotificationSettingsOnApp(requireContext4, troubleshootCategory4);
                    break;
                case 5:
                    TroubleshootTestingViewModel troubleshootTestingViewModel9 = this.q;
                    if (troubleshootTestingViewModel9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel9 = null;
                    }
                    Context requireContext5 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                    TroubleshootTestingViewModel troubleshootTestingViewModel10 = this.q;
                    if (troubleshootTestingViewModel10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel10 = null;
                    }
                    String str5 = this.p;
                    Intrinsics.checkNotNull(str5);
                    TroubleshootTestCategory troubleshootCategory5 = troubleshootTestingViewModel10.getTroubleshootCategory(str5);
                    Intrinsics.checkNotNull(troubleshootCategory5);
                    troubleshootTestingViewModel9.checkNotificationAccessAvailable(requireContext5, troubleshootCategory5);
                    break;
                case 6:
                    TroubleshootTestingViewModel troubleshootTestingViewModel11 = this.q;
                    if (troubleshootTestingViewModel11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel11 = null;
                    }
                    Context requireContext6 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
                    TroubleshootTestingViewModel troubleshootTestingViewModel12 = this.q;
                    if (troubleshootTestingViewModel12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        troubleshootTestingViewModel12 = null;
                    }
                    String str6 = this.p;
                    Intrinsics.checkNotNull(str6);
                    TroubleshootTestCategory troubleshootCategory6 = troubleshootTestingViewModel12.getTroubleshootCategory(str6);
                    Intrinsics.checkNotNull(troubleshootCategory6);
                    troubleshootTestingViewModel11.checkBT3ConnectionStatus(requireContext6, troubleshootCategory6);
                    break;
            }
            FragmentTroubleshootTestingBinding G = G();
            if (G != null && (textView = G.troubleshootFailTv) != null) {
                gone(textView);
            }
            TroubleshootingTestModel troubleshootingTestModel2 = this.t;
            if (troubleshootingTestModel2 != null) {
                troubleshootingTestModel2.setTestingStatus(TestingStatus.IN_PROGRESS);
            }
            TroubleshootingTestingAdapter troubleshootingTestingAdapter2 = this.r;
            if (troubleshootingTestingAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                troubleshootingTestingAdapter = troubleshootingTestingAdapter2;
            }
            troubleshootingTestingAdapter.notifyDataSetChanged();
        }
    }

    public final FragmentTroubleshootTestingBinding G() {
        return this.n;
    }

    public final String H() {
        String str = this.p;
        if (str != null) {
            switch (str.hashCode()) {
                case -1804919247:
                    if (str.equals("SMS_NOTIFICATION")) {
                        return "sms_notification";
                    }
                    break;
                case -1031458829:
                    if (str.equals("BLUETOOTH_CALLING")) {
                        return "bluetooth_calling";
                    }
                    break;
                case 539125353:
                    if (str.equals("APP_NOTIFICATION")) {
                        return "app_notification";
                    }
                    break;
                case 1303556396:
                    if (str.equals("CALL_NOTIFICATION")) {
                        return "call_notification";
                    }
                    break;
            }
        }
        return "";
    }

    public final void I(boolean z) {
        try {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.CV_TROUBLESHOOT_POPUP.getValue());
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.TROUBlESHOOTING.getValue());
            analyticsLog.setCVScreenName(H());
            HashMap<String, String> hashMap = new HashMap<>();
            if (z) {
                hashMap.put(FirebaseEventParams.Description.CV_value.getValue(), "yes");
            } else {
                hashMap.put(FirebaseEventParams.Description.CV_value.getValue(), "no");
            }
            analyticsLog.setMapData(hashMap);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00bf A[Catch: Exception -> 0x01b0, TryCatch #0 {Exception -> 0x01b0, blocks: (B:3:0x0002, B:5:0x0038, B:7:0x003e, B:11:0x0047, B:13:0x004d, B:17:0x005a, B:19:0x0060, B:21:0x006a, B:23:0x0074, B:24:0x007d, B:26:0x0083, B:30:0x0092, B:32:0x0098, B:36:0x00a5, B:38:0x00b3, B:44:0x00bf, B:45:0x00c6, B:47:0x00cc, B:49:0x00d8, B:55:0x00e4, B:57:0x00ea, B:63:0x00f6, B:65:0x00fc, B:66:0x0109, B:68:0x0117, B:70:0x011d, B:74:0x0123), top: B:79:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0117 A[Catch: Exception -> 0x01b0, TryCatch #0 {Exception -> 0x01b0, blocks: (B:3:0x0002, B:5:0x0038, B:7:0x003e, B:11:0x0047, B:13:0x004d, B:17:0x005a, B:19:0x0060, B:21:0x006a, B:23:0x0074, B:24:0x007d, B:26:0x0083, B:30:0x0092, B:32:0x0098, B:36:0x00a5, B:38:0x00b3, B:44:0x00bf, B:45:0x00c6, B:47:0x00cc, B:49:0x00d8, B:55:0x00e4, B:57:0x00ea, B:63:0x00f6, B:65:0x00fc, B:66:0x0109, B:68:0x0117, B:70:0x011d, B:74:0x0123), top: B:79:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00e4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00c6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void J(com.coveiot.android.theme.FirebaseEventParams.EventName r19) {
        /*
            Method dump skipped, instructions count: 437
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.TroubleshootTestingFragment.J(com.coveiot.android.theme.FirebaseEventParams$EventName):void");
    }

    public final void K(TroubleshootingTestModel troubleshootingTestModel) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DataSyncViewModel dataSyncViewModel = this.u;
        boolean z = true;
        boolean z2 = false;
        if (!(dataSyncViewModel != null && dataSyncViewModel.checkIsSyncing())) {
            switch (WhenMappings.$EnumSwitchMapping$0[troubleshootingTestModel.getTestType().ordinal()]) {
                case 1:
                    startActivity(new Intent(getActivity(), ActivityDoNotDisturb.class));
                    return;
                case 2:
                    startActivity(new Intent(requireContext(), ActivityNotifications.class));
                    return;
                case 3:
                    startActivity(new Intent(requireContext(), ActivityNotifications.class));
                    return;
                case 4:
                    startActivity(new Intent(requireContext(), ActivityNotifications.class));
                    return;
                case 5:
                    String failureMessage = troubleshootingTestModel.getFailureMessage();
                    if (failureMessage == null || failureMessage.length() == 0) {
                        z2 = true;
                    }
                    if (!z2 && kotlin.text.m.equals(troubleshootingTestModel.getFailureMessage(), getString(R.string.turn_off_again_on_notification_access_), true)) {
                        startActivity(new Intent(AppConstants.NOTIFICATION_SETTING.getValue()));
                        return;
                    } else {
                        startActivity(new Intent(requireContext(), ActivityNotifications.class));
                        return;
                    }
                case 6:
                    BleApi bleApi = BleApiManager.getInstance(requireActivity()).getBleApi();
                    if ((bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) ? false : true) {
                        Integer bTDeviceBondedState = BT3Utils.getBTDeviceBondedState(UserDataManager.getInstance(requireActivity()).getConnectedBTCallDeviceMac(), requireActivity());
                        if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 10) {
                            DataSyncViewModel dataSyncViewModel2 = this.u;
                            if (dataSyncViewModel2 == null || dataSyncViewModel2.checkIsSyncing()) {
                                z = false;
                            }
                            if (z) {
                                BT3CallViewModel bT3CallViewModel = this.s;
                                if (bT3CallViewModel == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
                                    bT3CallViewModel = null;
                                }
                                bT3CallViewModel.connectToBT3Watch(false, false, BleApiManager.getInstance(requireActivity()).getBleApi().getDeviceSupportedFeatures().isOneClickToConnectSupported());
                                return;
                            }
                            Toast.makeText(requireActivity(), getString(R.string.syncing_please_wait), 0).show();
                            return;
                        }
                        Integer bTDeviceBondedState2 = BT3Utils.getBTDeviceBondedState(UserDataManager.getInstance(requireActivity()).getConnectedBTCallDeviceMac(), requireActivity());
                        if (bTDeviceBondedState2 != null && bTDeviceBondedState2.intValue() == 11) {
                            Toast.makeText(requireActivity(), getString(R.string.please_wait_pairing_is_already_in_progress), 0).show();
                            return;
                        }
                        Integer bTDeviceBondedState3 = BT3Utils.getBTDeviceBondedState(UserDataManager.getInstance(requireActivity()).getConnectedBTCallDeviceMac(), requireActivity());
                        if (bTDeviceBondedState3 != null && bTDeviceBondedState3.intValue() == 12) {
                            AppUtils.openBluetoothSettings(requireActivity());
                            return;
                        }
                        return;
                    }
                    AppUtils.openBluetoothSettings(requireActivity());
                    return;
                default:
                    return;
            }
        }
        Toast.makeText(requireActivity(), getString(R.string.sync_is_in_progress), 0).show();
    }

    public final void Q() {
        if (AppUtils.isNetConnected(requireActivity())) {
            Intent intent = new Intent(requireActivity(), ActivityContactusFeedback.class);
            intent.putExtra("extra_key", this.p);
            this.D.launch(intent);
            return;
        }
        Toast.makeText(requireActivity(), getString(R.string.no_internet_connection), 0).show();
    }

    public final void S() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage;
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.B;
        boolean z = true;
        if (bottomSheetDialogImageTitleMessage2 != null) {
            if ((bottomSheetDialogImageTitleMessage2 != null && bottomSheetDialogImageTitleMessage2.isShowing()) && (bottomSheetDialogImageTitleMessage = this.B) != null) {
                bottomSheetDialogImageTitleMessage.dismiss();
            }
            this.B = null;
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.x;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            if (bottomSheetDialogOneButtonOneTitle2 == null || !bottomSheetDialogOneButtonOneTitle2.isShowing()) {
                z = false;
            }
            if (z && (bottomSheetDialogOneButtonOneTitle = this.x) != null) {
                bottomSheetDialogOneButtonOneTitle.dismiss();
            }
            this.x = null;
        }
    }

    public final void T() {
        FragmentTroubleshootTestingBinding G = G();
        if (G != null) {
            TextView textView = G.sendTvNo;
            StringBuilder sb = new StringBuilder();
            List<TroubleshootingTestModel> list = this.o;
            sb.append(list != null ? list.size() + 1 : 0);
            sb.append('.');
            textView.setText(sb.toString());
            String str = this.p;
            if (str != null) {
                switch (str.hashCode()) {
                    case -1804919247:
                        if (str.equals("SMS_NOTIFICATION")) {
                            G.troubleshootSendTv.setText(getString(R.string.send_test_sms));
                            G.troubleShootSendInstTv.setVisibility(8);
                            return;
                        }
                        return;
                    case -1031458829:
                        if (str.equals("BLUETOOTH_CALLING")) {
                            G.troubleshootSendTv.setText(getString(R.string.please_ask_someone_to_call_you));
                            G.troubleShootSendInstTv.setVisibility(8);
                            return;
                        }
                        return;
                    case 539125353:
                        if (str.equals("APP_NOTIFICATION")) {
                            G.troubleshootSendTv.setText(getString(R.string.send_test_notification));
                            G.troubleShootSendInstTv.setVisibility(0);
                            G.troubleShootSendInstTv.setText(getString(R.string.you_should_receive_a_message_on_the_watch));
                            return;
                        }
                        return;
                    case 1303556396:
                        if (str.equals("CALL_NOTIFICATION")) {
                            G.troubleshootSendTv.setText(getString(R.string.please_ask_someone_to_call_you));
                            G.troubleShootSendInstTv.setVisibility(8);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public final void U(String str) {
        SmsManager smsManager = SmsManager.getDefault();
        Intrinsics.checkNotNullExpressionValue(smsManager, "getDefault()");
        smsManager.sendTextMessage(str, null, getString(R.string.test_sms), null, null);
        String string = getString(R.string.troubleshoot_notification1);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.troubleshoot_notification1)");
        String string2 = getString(R.string.did_you_receive_a_sms_on_your_watch);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.did_y…eive_a_sms_on_your_watch)");
        showTroubleshootConfirmationDialog(string, string2);
    }

    public final void V() {
        View view;
        TextView textView;
        View view2;
        if (this.p != null) {
            FragmentTroubleshootTestingBinding G = G();
            TroubleshootTestingViewModel troubleshootTestingViewModel = null;
            TextView textView2 = (G == null || (view2 = G.toolbar) == null) ? null : (TextView) view2.findViewById(R.id.toolbar_title);
            if (textView2 != null) {
                TroubleshootTestingViewModel troubleshootTestingViewModel2 = this.q;
                if (troubleshootTestingViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    troubleshootTestingViewModel = troubleshootTestingViewModel2;
                }
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String str = this.p;
                Intrinsics.checkNotNull(str);
                textView2.setText(troubleshootTestingViewModel.getTroubleshootCategoryName(requireContext, str));
            }
        }
        FragmentTroubleshootTestingBinding G2 = G();
        if (G2 == null || (view = G2.toolbar) == null || (textView = (TextView) view.findViewById(R.id.toolbar_back_arrow)) == null) {
            return;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.r2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                TroubleshootTestingFragment.W(TroubleshootTestingFragment.this, view3);
            }
        });
    }

    public final void X(String str) {
        if (this.x == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            this.x = new BottomSheetDialogOneButtonOneTitle(requireActivity, str);
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.x;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.setCancelable(false);
            Button yesButton = bottomSheetDialogOneButtonOneTitle.getYesButton();
            if (yesButton != null) {
                yesButton.setVisibility(8);
            }
            bottomSheetDialogOneButtonOneTitle.show();
        }
    }

    public final void Y(String str, boolean z) {
        if (this.B == null) {
            int i = z ? R.drawable.success_fw_icon : R.drawable.failure_round_icon;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            Drawable drawable = getResources().getDrawable(i);
            Intrinsics.checkNotNull(drawable);
            this.B = new BottomSheetDialogImageTitleMessage(requireActivity, drawable, str, "", true);
        }
        final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this.B;
        if (bottomSheetDialogImageTitleMessage != null) {
            bottomSheetDialogImageTitleMessage.setCancelable(false);
            String string = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ok)");
            bottomSheetDialogImageTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.g2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TroubleshootTestingFragment.Z(BottomSheetDialogImageTitleMessage.this, view);
                }
            });
            bottomSheetDialogImageTitleMessage.show();
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

    public final void a0(String str) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.v2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TroubleshootTestingFragment.b0(TroubleshootTestingFragment.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void c0() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        EditPhoneNumberDialog editPhoneNumberDialog = new EditPhoneNumberDialog(requireContext);
        this.E = editPhoneNumberDialog;
        Intrinsics.checkNotNull(editPhoneNumberDialog);
        editPhoneNumberDialog.setCancelable(false);
        EditPhoneNumberDialog editPhoneNumberDialog2 = this.E;
        Intrinsics.checkNotNull(editPhoneNumberDialog2);
        final EditText editText = (EditText) editPhoneNumberDialog2.findViewById(R.id.ed_country);
        EditPhoneNumberDialog editPhoneNumberDialog3 = this.E;
        Intrinsics.checkNotNull(editPhoneNumberDialog3);
        final EditText editText2 = (EditText) editPhoneNumberDialog3.findViewById(R.id.ed_phone_number);
        EditPhoneNumberDialog editPhoneNumberDialog4 = this.E;
        Intrinsics.checkNotNull(editPhoneNumberDialog4);
        final Button button = (Button) editPhoneNumberDialog4.findViewById(R.id.btn_confirm);
        EditPhoneNumberDialog editPhoneNumberDialog5 = this.E;
        Intrinsics.checkNotNull(editPhoneNumberDialog5);
        TextWatcher textWatcher = new TextWatcher() { // from class: com.coveiot.android.leonardo.more.fragments.TroubleshootTestingFragment$showPhoneNumberEditDialog$watcher$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                Editable text = editText.getText();
                boolean z = false;
                boolean z2 = !(text == null || text.length() == 0);
                Editable text2 = editText2.getText();
                boolean z3 = !(text2 == null || text2.length() == 0);
                Button button2 = button;
                if (z2 && z3) {
                    z = true;
                }
                button2.setEnabled(z);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }
        };
        editText.addTextChangedListener(textWatcher);
        editText2.addTextChangedListener(textWatcher);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.u2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TroubleshootTestingFragment.d0(TroubleshootTestingFragment.this, editText2, editText, view);
            }
        });
        ((ImageView) editPhoneNumberDialog5.findViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.p2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TroubleshootTestingFragment.e0(TroubleshootTestingFragment.this, view);
            }
        });
        EditPhoneNumberDialog editPhoneNumberDialog6 = this.E;
        Boolean valueOf = editPhoneNumberDialog6 != null ? Boolean.valueOf(editPhoneNumberDialog6.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        EditPhoneNumberDialog editPhoneNumberDialog7 = this.E;
        Intrinsics.checkNotNull(editPhoneNumberDialog7);
        editPhoneNumberDialog7.show();
        EditPhoneNumberDialog editPhoneNumberDialog8 = this.E;
        Intrinsics.checkNotNull(editPhoneNumberDialog8);
        Window window = editPhoneNumberDialog8.getWindow();
        if (window != null) {
            window.setLayout(-1, -2);
        }
        if (window != null) {
            window.setGravity(17);
        }
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleCustomMessageWithClose getBottomSheetDialogOneButtonTitleCustomMessageWithClose() {
        return this.z;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getBottomSheetDialogTwoButtons() {
        return this.F;
    }

    public final int getREQUEST_SMS_PERMISSION() {
        return this.v;
    }

    @NotNull
    public final String getTAG() {
        return this.m;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getTroubleshootConfirmationDialog() {
        return this.w;
    }

    @Nullable
    public final Handler getTroubleshootTestHandler() {
        return this.A;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getWatchDisconnectedDialog1() {
        return this.y;
    }

    public final void k0() {
        final ProfileData userDetails = SessionManager.getInstance(requireContext()).getUserDetails();
        if (this.F == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.confirm_phone_number);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirm_phone_number)");
            String string2 = getString(R.string.a_test_sma_will_sent, userDetails.getMobileNumber());
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.a_tes…t, userData.mobileNumber)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
            this.F = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.TroubleshootTestingFragment$showconfirmPhoneNumberDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    TroubleshootTestingFragment troubleshootTestingFragment = TroubleshootTestingFragment.this;
                    String mobileNumber = userDetails.getMobileNumber();
                    Intrinsics.checkNotNullExpressionValue(mobileNumber, "userData.mobileNumber");
                    troubleshootTestingFragment.U(mobileNumber);
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = TroubleshootTestingFragment.this.getBottomSheetDialogTwoButtons();
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
                    bottomSheetDialogTwoButtons2.dismiss();
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.F;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.change_number);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.change_number)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.TroubleshootTestingFragment$showconfirmPhoneNumberDialog$2
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    TroubleshootTestingFragment.this.c0();
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = TroubleshootTestingFragment.this.getBottomSheetDialogTwoButtons();
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
                    bottomSheetDialogTwoButtons3.dismiss();
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.F;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        if (bottomSheetDialogTwoButtons3.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.F;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        bottomSheetDialogTwoButtons4.show();
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00ef, code lost:
        if (r5.getTroubleshootCategory(r3) == com.coveiot.android.leonardo.more.models.TroubleshootTestCategory.BLUETOOTH_CALLING) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l0(com.coveiot.android.leonardo.more.models.TroubleshootTestUpdateModel r11) {
        /*
            Method dump skipped, instructions count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.TroubleshootTestingFragment.l0(com.coveiot.android.leonardo.more.models.TroubleshootTestUpdateModel):void");
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.v) {
            D();
        }
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3Connecting() {
        if (isAdded()) {
            S();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            if (companion.getModelNumber(requireActivity) != null) {
                String string = getString(R.string.waiting_for_bt_connectivity_confirmation);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.waiti…onnectivity_confirmation)");
                X(string);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.fragments.m2
                    @Override // java.lang.Runnable
                    public final void run() {
                        TroubleshootTestingFragment.L(TroubleshootTestingFragment.this);
                    }
                }, 30000L);
            }
        }
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3ConnectionFailure(@Nullable String str) {
        if (isAdded()) {
            S();
            if (str == null || str.length() == 0) {
                return;
            }
            Y(str, false);
        }
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3Disconnected() {
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.p = arguments.getString("arg_item");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.n = FragmentTroubleshootTestingBinding.inflate(inflater, viewGroup, false);
        FragmentTroubleshootTestingBinding G = G();
        if (G != null) {
            return G.getRoot();
        }
        return null;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onInitialCheckFailed(@Nullable String str) {
        if (isAdded()) {
            if (str == null || str.length() == 0) {
                return;
            }
            Toast.makeText(requireActivity(), str, 0).show();
        }
    }

    @Override // com.coveiot.android.leonardo.more.adapters.TroubleshootingTestingAdapter.OnItemClickListener
    public void onItemClick(@NotNull TroubleshootingTestModel item) {
        Intrinsics.checkNotNullParameter(item, "item");
        K(item);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        super.onResume();
        CoveEventBusManager.getInstance().getEventBus().register(this);
        TroubleshootingTestModel troubleshootingTestModel = this.t;
        if (troubleshootingTestModel != null) {
            Intrinsics.checkNotNull(troubleshootingTestModel);
            if (troubleshootingTestModel.getTestingStatus() == TestingStatus.FAILED) {
                TroubleshootingTestModel troubleshootingTestModel2 = this.t;
                Intrinsics.checkNotNull(troubleshootingTestModel2);
                if (troubleshootingTestModel2.getTestType() == TroubleshootTestType.BT3_CONNECTIVITY_STATUS && (bottomSheetDialogOneButtonOneTitle = this.x) != null) {
                    Intrinsics.checkNotNull(bottomSheetDialogOneButtonOneTitle);
                    if (bottomSheetDialogOneButtonOneTitle.isShowing()) {
                        return;
                    }
                }
                E();
            }
        }
    }

    @Subscribe
    public final void onTestNotificationEventReceived(@NotNull OnTestNotificationEvent onTestNotificationEvent) {
        Intrinsics.checkNotNullParameter(onTestNotificationEvent, "onTestNotificationEvent");
        LogHelper.d(this.m, "onTestNotificationEventReceived");
        if (isAdded() && onTestNotificationEvent.isReceived()) {
            Handler handler = this.A;
            TroubleshootTestingViewModel troubleshootTestingViewModel = null;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            TroubleshootTestingViewModel troubleshootTestingViewModel2 = this.q;
            if (troubleshootTestingViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                troubleshootTestingViewModel = troubleshootTestingViewModel2;
            }
            troubleshootTestingViewModel.updateOnNotificationAccessReceived();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Button button;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentTroubleshootTestingBinding G = G();
        TroubleshootTestingViewModel troubleshootTestingViewModel = null;
        ImageView imageView = G != null ? G.ivPoweredBy : null;
        Intrinsics.checkNotNull(imageView);
        ThemesUtils.setPoweredByLogoIcon(requireContext, imageView, true);
        this.A = new Handler(Looper.getMainLooper());
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.q = (TroubleshootTestingViewModel) new ViewModelProvider(requireActivity).get(TroubleshootTestingViewModel.class);
        FragmentActivity requireActivity2 = requireActivity();
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
        this.u = (DataSyncViewModel) ViewModelProviders.of(requireActivity2, new ViewModelFactory(requireActivity3)).get(DataSyncViewModel.class);
        FragmentActivity requireActivity4 = requireActivity();
        FragmentActivity requireActivity5 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity()");
        BT3CallViewModel bT3CallViewModel = (BT3CallViewModel) ViewModelProviders.of(requireActivity4, new com.coveiot.android.leonardo.utils.ViewModelFactory(requireActivity5)).get(BT3CallViewModel.class);
        this.s = bT3CallViewModel;
        if (bT3CallViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
            bT3CallViewModel = null;
        }
        bT3CallViewModel.setBT3ConnectionChangeListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rcv_troubleshoot_items);
        if (this.p != null) {
            TroubleshootTestingViewModel troubleshootTestingViewModel2 = this.q;
            if (troubleshootTestingViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                troubleshootTestingViewModel2 = null;
            }
            String str = this.p;
            Intrinsics.checkNotNull(str);
            TroubleshootTestCategory troubleshootCategory = troubleshootTestingViewModel2.getTroubleshootCategory(str);
            if (troubleshootCategory != null) {
                TroubleshootTestingViewModel troubleshootTestingViewModel3 = this.q;
                if (troubleshootTestingViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    troubleshootTestingViewModel3 = null;
                }
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                List<TroubleshootingTestModel> troubleshootTestModels = troubleshootTestingViewModel3.getTroubleshootTestModels(requireContext2, troubleshootCategory);
                Intrinsics.checkNotNull(troubleshootTestModels);
                this.o = CollectionsKt___CollectionsKt.toMutableList((Collection) troubleshootTestModels);
            }
        }
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        TroubleshootingTestingAdapter troubleshootingTestingAdapter = new TroubleshootingTestingAdapter(requireContext3, this.o);
        this.r = troubleshootingTestingAdapter;
        troubleshootingTestingAdapter.setOnItemClickListener(this);
        TroubleshootingTestingAdapter troubleshootingTestingAdapter2 = this.r;
        if (troubleshootingTestingAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            troubleshootingTestingAdapter2 = null;
        }
        recyclerView.setAdapter(troubleshootingTestingAdapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        V();
        T();
        TroubleshootTestingViewModel troubleshootTestingViewModel4 = this.q;
        if (troubleshootTestingViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            troubleshootTestingViewModel = troubleshootTestingViewModel4;
        }
        MutableLiveData<TroubleshootTestUpdateModel> troubleshootTestResultLiveData = troubleshootTestingViewModel.getTroubleshootTestResultLiveData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final a aVar = new a();
        troubleshootTestResultLiveData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.more.fragments.j2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TroubleshootTestingFragment.P(Function1.this, obj);
            }
        });
        FragmentTroubleshootTestingBinding G2 = G();
        if (G2 != null && (button = G2.testOkBtn) != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.n2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    TroubleshootTestingFragment.M(TroubleshootTestingFragment.this, view2);
                }
            });
        }
        E();
    }

    @Subscribe
    public final void onWatchBTEnableEventReceived(@NotNull OnWatchBT3EnabledEvent onWatchBT3EnabledEvent) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        Intrinsics.checkNotNullParameter(onWatchBT3EnabledEvent, "onWatchBT3EnabledEvent");
        if (isAdded()) {
            BleApi bleApi = BleApiManager.getInstance(requireActivity()).getBleApi();
            boolean z = true;
            if (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) {
                z = false;
            }
            if (z && onWatchBT3EnabledEvent.isEnabled()) {
                BleApi bleApi2 = BleApiManager.getInstance(requireActivity()).getBleApi();
                if ((bleApi2 != null ? bleApi2.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                    S();
                    TroubleshootingTestModel troubleshootingTestModel = this.t;
                    if (troubleshootingTestModel != null) {
                        Intrinsics.checkNotNull(troubleshootingTestModel);
                        if (troubleshootingTestModel.getTestingStatus() == TestingStatus.FAILED) {
                            TroubleshootingTestModel troubleshootingTestModel2 = this.t;
                            Intrinsics.checkNotNull(troubleshootingTestModel2);
                            if (troubleshootingTestModel2.getTestType() == TroubleshootTestType.BT3_CONNECTIVITY_STATUS) {
                                E();
                            }
                        }
                    }
                }
            }
        }
    }

    public final void setBottomSheetDialogOneButtonTitleCustomMessageWithClose(@Nullable BottomSheetDialogOneButtonTitleCustomMessageWithClose bottomSheetDialogOneButtonTitleCustomMessageWithClose) {
        this.z = bottomSheetDialogOneButtonTitleCustomMessageWithClose;
    }

    public final void setBottomSheetDialogTwoButtons(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.F = bottomSheetDialogTwoButtons;
    }

    public final void setTroubleshootConfirmationDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.w = bottomSheetDialogTwoButtons;
    }

    public final void setTroubleshootTestHandler(@Nullable Handler handler) {
        this.A = handler;
    }

    public final void setWatchDisconnectedDialog1(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.y = bottomSheetDialogTwoButtons;
    }

    public final void showSuccessDialog() {
        if (isAdded()) {
            if (this.z == null) {
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                String string = getString(R.string.success_);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_)");
                String string2 = getString(R.string.notifications_are_working_as_expected);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.notif…_are_working_as_expected)");
                BottomSheetDialogOneButtonTitleCustomMessageWithClose bottomSheetDialogOneButtonTitleCustomMessageWithClose = new BottomSheetDialogOneButtonTitleCustomMessageWithClose(requireActivity, string, string2);
                this.z = bottomSheetDialogOneButtonTitleCustomMessageWithClose;
                Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleCustomMessageWithClose);
                bottomSheetDialogOneButtonTitleCustomMessageWithClose.setCancelable(false);
                BottomSheetDialogOneButtonTitleCustomMessageWithClose bottomSheetDialogOneButtonTitleCustomMessageWithClose2 = this.z;
                Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleCustomMessageWithClose2);
                bottomSheetDialogOneButtonTitleCustomMessageWithClose2.getYesButton().setVisibility(8);
                BottomSheetDialogOneButtonTitleCustomMessageWithClose bottomSheetDialogOneButtonTitleCustomMessageWithClose3 = this.z;
                Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleCustomMessageWithClose3);
                bottomSheetDialogOneButtonTitleCustomMessageWithClose3.getCloseIcon().setVisibility(0);
                BottomSheetDialogOneButtonTitleCustomMessageWithClose bottomSheetDialogOneButtonTitleCustomMessageWithClose4 = this.z;
                Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleCustomMessageWithClose4);
                bottomSheetDialogOneButtonTitleCustomMessageWithClose4.getCloseIcon().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.q2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TroubleshootTestingFragment.f0(TroubleshootTestingFragment.this, view);
                    }
                });
            }
            BottomSheetDialogOneButtonTitleCustomMessageWithClose bottomSheetDialogOneButtonTitleCustomMessageWithClose5 = this.z;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleCustomMessageWithClose5);
            if (bottomSheetDialogOneButtonTitleCustomMessageWithClose5.isShowing()) {
                return;
            }
            BottomSheetDialogOneButtonTitleCustomMessageWithClose bottomSheetDialogOneButtonTitleCustomMessageWithClose6 = this.z;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleCustomMessageWithClose6);
            bottomSheetDialogOneButtonTitleCustomMessageWithClose6.show();
        }
    }

    public final void showTroubleshootConfirmationDialog(@NotNull String title, @NotNull String description) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        if (isAdded()) {
            if (this.w == null) {
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireActivity, title, description, false, 8, null);
                this.w = bottomSheetDialogTwoButtons;
                Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
                bottomSheetDialogTwoButtons.setCancelable(false);
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
                String string = getString(R.string.yes);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.yes)");
                bottomSheetDialogTwoButtons2.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.o2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TroubleshootTestingFragment.g0(TroubleshootTestingFragment.this, view);
                    }
                });
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
                String string2 = getString(R.string.f4085no);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.no)");
                bottomSheetDialogTwoButtons3.setNegativeButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.s2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TroubleshootTestingFragment.h0(TroubleshootTestingFragment.this, view);
                    }
                });
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.w;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
            if (bottomSheetDialogTwoButtons4.isShowing()) {
                return;
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.w;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons5);
            bottomSheetDialogTwoButtons5.show();
        }
    }

    public final void showWatchDisconnectedDialog1() {
        if (isAdded()) {
            if (this.y == null) {
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                String string = getString(R.string.watch_is_not_connected_);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.watch_is_not_connected_)");
                String string2 = getString(R.string.make_sure_watch_is_nearby_connected_in_case_);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.make_…earby_connected_in_case_)");
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireActivity, string, string2, false, 8, null);
                this.y = bottomSheetDialogTwoButtons;
                Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
                bottomSheetDialogTwoButtons.setCancelable(false);
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.y;
                Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
                String string3 = getString(R.string.yes);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
                bottomSheetDialogTwoButtons2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.t2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TroubleshootTestingFragment.i0(TroubleshootTestingFragment.this, view);
                    }
                });
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.y;
                Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
                String string4 = getString(R.string.f4085no);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
                bottomSheetDialogTwoButtons3.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.f2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TroubleshootTestingFragment.j0(TroubleshootTestingFragment.this, view);
                    }
                });
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.y;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
            if (bottomSheetDialogTwoButtons4.isShowing()) {
                return;
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.y;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons5);
            bottomSheetDialogTwoButtons5.show();
        }
    }
}
