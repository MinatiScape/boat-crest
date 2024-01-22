package com.coveiot.android.leonardo.more.fragments;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.adapters.DFUDevicesAdapter;
import com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.FileUtils;
import com.coveiot.android.leonardo.utils.FirmwareUtils;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.DeviceInfoManager;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.device.model.IOTUserDeviceReq;
import com.coveiot.coveaccess.device.model.IOTUserDeviceRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class DFUWatchListFragment extends BaseFragment implements DFUDevicesAdapter.DFUDeviceSelectionListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public BluetoothLeScanner o;
    public boolean p;
    public DFUDevicesAdapter s;
    @Nullable
    public ProgressBar t;
    @Nullable
    public TextView u;
    public BottomSheetDialog v;
    @Nullable
    public BottomSheetDialogImageTitleMessage w;
    @Nullable
    public BottomSheetDialogImageTitleMessageTwoBtns y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "javaClass";
    @NotNull
    public final Handler n = new Handler();
    public final long q = 30000;
    @NotNull
    public final ArrayList<BluetoothDevice> r = new ArrayList<>();
    @NotNull
    public final ScanCallback x = new ScanCallback() { // from class: com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$leScanCallback$1
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, @NotNull ScanResult result) {
            ArrayList arrayList;
            ArrayList arrayList2;
            DFUDevicesAdapter dFUDevicesAdapter;
            ArrayList arrayList3;
            Intrinsics.checkNotNullParameter(result, "result");
            super.onScanResult(i, result);
            if (result.getDevice().getName() != null) {
                String name = result.getDevice().getName();
                Intrinsics.checkNotNullExpressionValue(name, "result.device.name");
                DFUDevicesAdapter dFUDevicesAdapter2 = null;
                if (StringsKt__StringsKt.contains$default((CharSequence) name, (CharSequence) AppConstants.DFUTARG.getValue(), false, 2, (Object) null)) {
                    arrayList = DFUWatchListFragment.this.r;
                    if (!arrayList.contains(result.getDevice())) {
                        arrayList3 = DFUWatchListFragment.this.r;
                        arrayList3.add(result.getDevice());
                    }
                    DFUWatchListFragment dFUWatchListFragment = DFUWatchListFragment.this;
                    arrayList2 = dFUWatchListFragment.r;
                    dFUWatchListFragment.s = new DFUDevicesAdapter(arrayList2, DFUWatchListFragment.this);
                    RecyclerView recyclerView = (RecyclerView) DFUWatchListFragment.this._$_findCachedViewById(R.id.rcv_dfu_devices);
                    if (recyclerView == null) {
                        return;
                    }
                    dFUDevicesAdapter = DFUWatchListFragment.this.s;
                    if (dFUDevicesAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        dFUDevicesAdapter2 = dFUDevicesAdapter;
                    }
                    recyclerView.setAdapter(dFUDevicesAdapter2);
                }
            }
        }
    };
    @NotNull
    public final DFUWatchListFragment$mDfuProgressListener$1 z = new DFUWatchListFragment$mDfuProgressListener$1(this);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final DFUWatchListFragment newInstance() {
            DFUWatchListFragment dFUWatchListFragment = new DFUWatchListFragment();
            dFUWatchListFragment.setArguments(new Bundle());
            return dFUWatchListFragment;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$deleteFiles$1", f = "DFUWatchListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FileUtils fileUtils = FileUtils.INSTANCE;
                File filesDir = DFUWatchListFragment.this.requireContext().getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "requireContext().filesDir");
                fileUtils.deleteRecursive(fileUtils.getFirmwareFile(filesDir, AppConstants.LEONARDO_FIRMWARE_FILE_NAME.getValue()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$onUploadCanceled$1", f = "DFUWatchListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (DFUWatchListFragment.this.isAdded()) {
                    Toast.makeText(DFUWatchListFragment.this.requireContext(), DFUWatchListFragment.this.getString(R.string.upload_cancled), 0).show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$showErrorBottomSheet$1", f = "DFUWatchListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isContainOnlyMessages;
        public final /* synthetic */ String $message;
        public final /* synthetic */ String $title;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(String str, String str2, boolean z, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$title = str;
            this.$message = str2;
            this.$isContainOnlyMessages = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage, DFUWatchListFragment dFUWatchListFragment, View view) {
            bottomSheetDialogImageTitleMessage.dismiss();
            dFUWatchListFragment.requireActivity().finish();
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$title, this.$message, this.$isContainOnlyMessages, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (DFUWatchListFragment.this.isAdded()) {
                    if (DFUWatchListFragment.this.v == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                    }
                    BottomSheetDialog bottomSheetDialog = DFUWatchListFragment.this.v;
                    BottomSheetDialog bottomSheetDialog2 = null;
                    if (bottomSheetDialog == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                        bottomSheetDialog = null;
                    }
                    if (bottomSheetDialog.isShowing()) {
                        BottomSheetDialog bottomSheetDialog3 = DFUWatchListFragment.this.v;
                        if (bottomSheetDialog3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                        } else {
                            bottomSheetDialog2 = bottomSheetDialog3;
                        }
                        bottomSheetDialog2.dismiss();
                    }
                    Context requireContext = DFUWatchListFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    Drawable drawable = DFUWatchListFragment.this.getResources().getDrawable(R.drawable.failure_round_icon);
                    Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…wable.failure_round_icon)");
                    final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(requireContext, drawable, this.$title, this.$message, this.$isContainOnlyMessages);
                    bottomSheetDialogImageTitleMessage.setCancelable(false);
                    String string = DFUWatchListFragment.this.getString(R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ok)");
                    final DFUWatchListFragment dFUWatchListFragment = DFUWatchListFragment.this;
                    bottomSheetDialogImageTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.p
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            DFUWatchListFragment.c.invokeSuspend$lambda$0(BottomSheetDialogImageTitleMessage.this, dFUWatchListFragment, view);
                        }
                    });
                    bottomSheetDialogImageTitleMessage.show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$showFWUpdateSuccessDialog$1", f = "DFUWatchListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(DFUWatchListFragment dFUWatchListFragment, View view) {
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = dFUWatchListFragment.w;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            bottomSheetDialogImageTitleMessage.dismiss();
            dFUWatchListFragment.requireActivity().finish();
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (DFUWatchListFragment.this.isAdded()) {
                    if (DFUWatchListFragment.this.v == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                    }
                    BottomSheetDialog bottomSheetDialog = DFUWatchListFragment.this.v;
                    BottomSheetDialog bottomSheetDialog2 = null;
                    if (bottomSheetDialog == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                        bottomSheetDialog = null;
                    }
                    if (bottomSheetDialog.isShowing()) {
                        BottomSheetDialog bottomSheetDialog3 = DFUWatchListFragment.this.v;
                        if (bottomSheetDialog3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                        } else {
                            bottomSheetDialog2 = bottomSheetDialog3;
                        }
                        bottomSheetDialog2.dismiss();
                    }
                    if (DFUWatchListFragment.this.w == null) {
                        DFUWatchListFragment dFUWatchListFragment = DFUWatchListFragment.this;
                        Context requireContext = dFUWatchListFragment.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        Drawable drawable = DFUWatchListFragment.this.getResources().getDrawable(R.drawable.success_fw_icon);
                        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.success_fw_icon)");
                        String string = DFUWatchListFragment.this.getString(R.string.restored_successfully);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.restored_successfully)");
                        dFUWatchListFragment.w = new BottomSheetDialogImageTitleMessage(requireContext, drawable, string, "", false);
                    }
                    if (DFUWatchListFragment.this.w != null) {
                        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = DFUWatchListFragment.this.w;
                        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
                        bottomSheetDialogImageTitleMessage.setCancelable(false);
                        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = DFUWatchListFragment.this.w;
                        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                        String string2 = DFUWatchListFragment.this.getString(R.string.ok);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.leaderboard.R.string.ok)");
                        final DFUWatchListFragment dFUWatchListFragment2 = DFUWatchListFragment.this;
                        bottomSheetDialogImageTitleMessage2.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.q
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                DFUWatchListFragment.d.invokeSuspend$lambda$0(DFUWatchListFragment.this, view);
                            }
                        });
                        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = DFUWatchListFragment.this.w;
                        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
                        if (!bottomSheetDialogImageTitleMessage3.isShowing()) {
                            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = DFUWatchListFragment.this.w;
                            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
                            bottomSheetDialogImageTitleMessage4.show();
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @JvmStatic
    @NotNull
    public static final DFUWatchListFragment newInstance() {
        return Companion.newInstance();
    }

    public static final void r(DFUWatchListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.s();
    }

    public static final void t(DFUWatchListFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = false;
        this$0.dismissProgress();
        BluetoothLeScanner bluetoothLeScanner = this$0.o;
        if (bluetoothLeScanner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bluetoothLeScanner");
            bluetoothLeScanner = null;
        }
        bluetoothLeScanner.stopScan(this$0.x);
        if (this$0.r.size() == 0) {
            RecyclerView recyclerView = (RecyclerView) this$0._$_findCachedViewById(R.id.rcv_dfu_devices);
            if (recyclerView != null) {
                recyclerView.setVisibility(8);
            }
            TextView textView = (TextView) this$0._$_findCachedViewById(R.id.no_data_tv);
            if (textView == null) {
                return;
            }
            textView.setVisibility(0);
            return;
        }
        RecyclerView recyclerView2 = (RecyclerView) this$0._$_findCachedViewById(R.id.rcv_dfu_devices);
        if (recyclerView2 != null) {
            recyclerView2.setVisibility(0);
        }
        TextView textView2 = (TextView) this$0._$_findCachedViewById(R.id.no_data_tv);
        if (textView2 == null) {
            return;
        }
        textView2.setVisibility(8);
    }

    public static final void x(DFUWatchListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this$0.y;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
        bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
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

    @Nullable
    public final BottomSheetDialogImageTitleMessageTwoBtns getWarningDialog() {
        return this.y;
    }

    public final void n() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
    }

    public final void o() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.DialogThemeBottomSheet);
        this.v = bottomSheetDialog;
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.requestFeature(1);
        BottomSheetDialog bottomSheetDialog2 = this.v;
        BottomSheetDialog bottomSheetDialog3 = null;
        if (bottomSheetDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
            bottomSheetDialog2 = null;
        }
        bottomSheetDialog2.setContentView(R.layout.dialog_updating_fw);
        BottomSheetDialog bottomSheetDialog4 = this.v;
        if (bottomSheetDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
            bottomSheetDialog4 = null;
        }
        bottomSheetDialog4.setCancelable(false);
        BottomSheetDialog bottomSheetDialog5 = this.v;
        if (bottomSheetDialog5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
            bottomSheetDialog5 = null;
        }
        this.u = (TextView) bottomSheetDialog5.findViewById(R.id.tv_progress_title);
        BottomSheetDialog bottomSheetDialog6 = this.v;
        if (bottomSheetDialog6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
        } else {
            bottomSheetDialog3 = bottomSheetDialog6;
        }
        this.t = (ProgressBar) bottomSheetDialog3.findViewById(R.id.progress_update);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_d_f_u_watch_list, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.DFUDevicesAdapter.DFUDeviceSelectionListener
    public void onDeviceSelected(@NotNull BluetoothDevice device) {
        Intrinsics.checkNotNullParameter(device, "device");
        w(device);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rcv_dfu_devices);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        }
        DfuServiceListenerHelper.registerProgressListener(requireContext(), this.z);
        Object systemService = requireContext().getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothLeScanner bluetoothLeScanner = ((BluetoothManager) systemService).getAdapter().getBluetoothLeScanner();
        Intrinsics.checkNotNullExpressionValue(bluetoothLeScanner, "requireContext().getSyst…dapter.bluetoothLeScanner");
        this.o = bluetoothLeScanner;
        ((ImageView) _$_findCachedViewById(R.id.imv_refresh)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DFUWatchListFragment.r(DFUWatchListFragment.this, view2);
            }
        });
        o();
        FileUtils fileUtils = FileUtils.INSTANCE;
        File filesDir = requireContext().getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "requireContext().filesDir");
        File firmwareFile = fileUtils.getFirmwareFile(filesDir, AppConstants.LEONARDO_FIRMWARE_FILE_NAME.getValue());
        if (firmwareFile != null && firmwareFile.exists()) {
            s();
            return;
        }
        String string = getString(R.string.device_recovery_not_available);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.device_recovery_not_available)");
        v(string, "", true);
    }

    public final void p() {
        SessionManager.getInstance(requireContext()).setForceFirmwareUpdated(true);
        SessionManager.getInstance(requireContext()).deleteOptionalFirmwareBeanString();
        SessionManager.getInstance(requireContext()).deleteSoftwareUpdateResponseBeanString();
        BleApiManager.getInstance(requireContext()).getBleApi().registerConnectionStatus().observe(this, new DFUWatchListFragment$onTransferCompleted$1(this));
    }

    public final void q() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
    }

    @SuppressLint({"MissingPermission"})
    public final void s() {
        BluetoothLeScanner bluetoothLeScanner = null;
        if (!this.p) {
            this.n.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.fragments.k
                @Override // java.lang.Runnable
                public final void run() {
                    DFUWatchListFragment.t(DFUWatchListFragment.this);
                }
            }, this.q);
            this.p = true;
            if (ContextCompat.checkSelfPermission(requireContext(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
                return;
            }
            BaseFragment.showProgress$default(this, false, 1, null);
            BluetoothLeScanner bluetoothLeScanner2 = this.o;
            if (bluetoothLeScanner2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bluetoothLeScanner");
            } else {
                bluetoothLeScanner = bluetoothLeScanner2;
            }
            bluetoothLeScanner.startScan(this.x);
            return;
        }
        this.p = false;
        BluetoothLeScanner bluetoothLeScanner3 = this.o;
        if (bluetoothLeScanner3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bluetoothLeScanner");
        } else {
            bluetoothLeScanner = bluetoothLeScanner3;
        }
        bluetoothLeScanner.stopScan(this.x);
    }

    public final void setWarningDialog(@Nullable BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns) {
        this.y = bottomSheetDialogImageTitleMessageTwoBtns;
    }

    public final void showFWUpdateSuccessDialog() {
        n();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
    }

    public final void u(BleDeviceInfo bleDeviceInfo) {
        IOTUserDeviceReq iOTUserDeviceReq = new IOTUserDeviceReq(bleDeviceInfo);
        if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isBandSocialDistanceFeatureSupported()) {
            String str = bleDeviceInfo.getmDeviceName();
            Intrinsics.checkNotNullExpressionValue(str, "bleDeviceInfo.getmDeviceName()");
            String lowerCase = str.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            iOTUserDeviceReq.setAppTrackerId(lowerCase);
        }
        DeviceInfoManager.saveConnectedDeviceInfoOnServer(iOTUserDeviceReq, new CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$sendConnectedDeviceInfoToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                DFUWatchListFragment.this.dismissProgress();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable IOTUserDeviceRes iOTUserDeviceRes) {
                DFUWatchListFragment.this.showFWUpdateSuccessDialog();
            }
        });
    }

    public final void v(String str, String str2, boolean z) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(str, str2, z, null), 2, null);
    }

    public final void w(final BluetoothDevice bluetoothDevice) {
        if (this.y == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            Drawable drawable = requireContext().getDrawable(R.drawable.ic_alert_icon);
            Intrinsics.checkNotNull(drawable);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(R.string.watch_recovery_msg2);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.watch_recovery_msg2)");
            PayUtils payUtils = PayUtils.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            String format = String.format(string, Arrays.copyOf(new Object[]{payUtils.getDeviceNameForWatchRecovery(context)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            String string2 = getString(R.string.watch_recovery_msg3);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.watch_recovery_msg3)");
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = new BottomSheetDialogImageTitleMessageTwoBtns(requireContext, drawable, format, string2, true);
            this.y = bottomSheetDialogImageTitleMessageTwoBtns;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
            ImageView imageView = (ImageView) bottomSheetDialogImageTitleMessageTwoBtns.getConfirmPhoneNumberDialog().findViewById(R.id.close_icon);
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (imageView != null) {
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.j
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DFUWatchListFragment.x(DFUWatchListFragment.this, view);
                    }
                });
            }
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns2 = this.y;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns2);
            bottomSheetDialogImageTitleMessageTwoBtns2.setCancelable(false);
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns3 = this.y;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns3);
            String string3 = getString(R.string.start);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.start)");
            bottomSheetDialogImageTitleMessageTwoBtns3.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$showWarning$2
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    TextView textView;
                    BottomSheetDialogImageTitleMessageTwoBtns warningDialog = DFUWatchListFragment.this.getWarningDialog();
                    if (warningDialog != null) {
                        warningDialog.dismiss();
                    }
                    BottomSheetDialog bottomSheetDialog = DFUWatchListFragment.this.v;
                    BottomSheetDialog bottomSheetDialog2 = null;
                    if (bottomSheetDialog == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                        bottomSheetDialog = null;
                    }
                    if (!bottomSheetDialog.isShowing()) {
                        BottomSheetDialog bottomSheetDialog3 = DFUWatchListFragment.this.v;
                        if (bottomSheetDialog3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                        } else {
                            bottomSheetDialog2 = bottomSheetDialog3;
                        }
                        bottomSheetDialog2.show();
                        textView = DFUWatchListFragment.this.u;
                        if (textView != null) {
                            textView.setText(DFUWatchListFragment.this.getString(R.string.recovery_started));
                        }
                    }
                    FirmwareUtils firmwareUtils = FirmwareUtils.INSTANCE;
                    String address = bluetoothDevice.getAddress();
                    Intrinsics.checkNotNullExpressionValue(address, "device.address");
                    Context requireContext2 = DFUWatchListFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    firmwareUtils.initiateDFU(address, "DfuTarg", requireContext2);
                }
            });
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns4 = this.y;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns4);
            String string4 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
            bottomSheetDialogImageTitleMessageTwoBtns4.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$showWarning$3
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogImageTitleMessageTwoBtns warningDialog = DFUWatchListFragment.this.getWarningDialog();
                    Intrinsics.checkNotNull(warningDialog);
                    warningDialog.dismiss();
                }
            });
        }
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns5 = this.y;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns5);
        if (bottomSheetDialogImageTitleMessageTwoBtns5.isShowing()) {
            return;
        }
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns6 = this.y;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns6);
        bottomSheetDialogImageTitleMessageTwoBtns6.show();
    }

    public final void y() {
        BleApiManager.getInstance(requireContext()).getBleApi().getData(new DeviceInfoRequest.Builder().setIsFwVersion(true).build(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$updateNewFwVersionToPref$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                str = DFUWatchListFragment.this.m;
                LogHelper.d(str, "FW update: onDataError");
                DFUWatchListFragment.this.showFWUpdateSuccessDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                str = DFUWatchListFragment.this.m;
                LogHelper.d(str, "FW update: onDataResponse");
                if (response.getResponseData() instanceof DeviceInfoResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
                    DeviceInfoData deviceInfo = ((DeviceInfoResponse) responseData).getDeviceInfo();
                    PreferenceManagerAbstract.getInstance(DFUWatchListFragment.this.getContext()).saveDeviceVersionNumber(deviceInfo != null ? deviceInfo.getFwVersion() : null);
                    BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(DFUWatchListFragment.this.requireContext()).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                    if (bleDeviceInfo != null && deviceInfo != null) {
                        bleDeviceInfo.setFirmwareRevision(deviceInfo.getFwVersion());
                        SessionManager.getInstance(DFUWatchListFragment.this.requireContext()).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                    }
                    if (bleDeviceInfo == null || bleDeviceInfo.getSerialNumber() == null || bleDeviceInfo.getmManufacturerName() == null || bleDeviceInfo.getmModelNumber() == null || bleDeviceInfo.getFirmwareRevision() == null || bleDeviceInfo.getHwRevision() == null) {
                        return;
                    }
                    DFUWatchListFragment.this.u(bleDeviceInfo);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }
}
