package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.SelectedAppDataForQrCodePush;
import com.coveiot.android.leonardo.more.viewmodel.AddQRCodeToWatchViewModel;
import com.coveiot.android.leonardo.utils.QRCodeUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.QrCodeData;
import com.coveiot.covepreferences.data.SavedQRCodeModel;
import com.coveiot.utils.utility.LogHelper;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.zxing.WriterException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AddQRCodeToWatchFragment extends BaseFragment implements AddQRCodeToWatchViewModel.AddQRCodeToWatchPushListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public SelectedAppDataForQrCodePush m;
    @NotNull
    public String n;
    public boolean o;
    public AddQRCodeToWatchViewModel p;
    @Nullable
    public String q;
    @Nullable
    public SessionManager r;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final AddQRCodeToWatchFragment newInstance(@NotNull SelectedAppDataForQrCodePush param1) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            AddQRCodeToWatchFragment addQRCodeToWatchFragment = new AddQRCodeToWatchFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("appData", param1);
            addQRCodeToWatchFragment.setArguments(bundle);
            return addQRCodeToWatchFragment;
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<List<Barcode>, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<Barcode> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<Barcode> barcodes) {
            if (barcodes == null || barcodes.isEmpty()) {
                String str = AddQRCodeToWatchFragment.this.n;
                LogHelper.d(str, "selected image does not has qr code " + AddQRCodeToWatchFragment.this.q);
                if (!AddQRCodeToWatchFragment.this.o) {
                    ((ImageView) AddQRCodeToWatchFragment.this._$_findCachedViewById(R.id.iv_qr_code_delete)).setImageResource(R.drawable.ic_qr_code_delete_disabled);
                }
                Toast.makeText(AddQRCodeToWatchFragment.this.requireContext(), AddQRCodeToWatchFragment.this.getString(R.string.the_qr_code_information_is_not_recognized), 1).show();
                return;
            }
            Intrinsics.checkNotNullExpressionValue(barcodes, "barcodes");
            AddQRCodeToWatchFragment addQRCodeToWatchFragment = AddQRCodeToWatchFragment.this;
            for (Barcode barcode : barcodes) {
                addQRCodeToWatchFragment.q = barcode.getRawValue();
                int i = R.id.iv_qr_code_holder;
                ((RoundedImageView) addQRCodeToWatchFragment._$_findCachedViewById(i)).setImageBitmap(QRCodeUtils.INSTANCE.getQRCodeBitmapFromMetaData(addQRCodeToWatchFragment.q));
                ((RoundedImageView) addQRCodeToWatchFragment._$_findCachedViewById(i)).setRadius(20);
                int i2 = R.id.iv_qr_code_delete;
                ((ImageView) addQRCodeToWatchFragment._$_findCachedViewById(i2)).setImageResource(R.drawable.ic_qr_code_delete_enabled);
                ((ImageView) addQRCodeToWatchFragment._$_findCachedViewById(i2)).setEnabled(true);
                addQRCodeToWatchFragment.o = true;
                Button btnUpload = (Button) addQRCodeToWatchFragment._$_findCachedViewById(R.id.btnUpload);
                Intrinsics.checkNotNullExpressionValue(btnUpload, "btnUpload");
                ViewUtilsKt.enable(btnUpload);
            }
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<List<Barcode>, Unit> {
        public final /* synthetic */ Ref.ObjectRef<Integer> $qrCodeAlreadyExitsAt;
        public final /* synthetic */ SavedQRCodeModel $savedData;
        public final /* synthetic */ AddQRCodeToWatchFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(SavedQRCodeModel savedQRCodeModel, Ref.ObjectRef<Integer> objectRef, AddQRCodeToWatchFragment addQRCodeToWatchFragment) {
            super(1);
            this.$savedData = savedQRCodeModel;
            this.$qrCodeAlreadyExitsAt = objectRef;
            this.this$0 = addQRCodeToWatchFragment;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<Barcode> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference failed for: r3v9, types: [T, java.lang.Integer] */
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<Barcode> barcodes) {
            Intrinsics.checkNotNullExpressionValue(barcodes, "barcodes");
            SavedQRCodeModel savedQRCodeModel = this.$savedData;
            Ref.ObjectRef<Integer> objectRef = this.$qrCodeAlreadyExitsAt;
            AddQRCodeToWatchFragment addQRCodeToWatchFragment = this.this$0;
            for (Barcode barcode : barcodes) {
                int i = 0;
                int size = savedQRCodeModel.getQrCodeList().size();
                while (true) {
                    if (i >= size) {
                        break;
                    } else if (Intrinsics.areEqual(savedQRCodeModel.getQrCodeList().get(i).getQrCodeMetaData(), barcode.getRawValue())) {
                        objectRef.element = Integer.valueOf(i);
                        break;
                    } else {
                        i++;
                    }
                }
                if (objectRef.element != null) {
                    ArrayList<QrCodeData> qrCodeList = savedQRCodeModel.getQrCodeList();
                    Integer num = objectRef.element;
                    Intrinsics.checkNotNull(num);
                    qrCodeList.remove(num.intValue());
                }
                SessionManager sessionManager = addQRCodeToWatchFragment.r;
                String connectedDeviceMacAddress = sessionManager != null ? sessionManager.getConnectedDeviceMacAddress() : null;
                Intrinsics.checkNotNull(connectedDeviceMacAddress);
                SavedQRCodeModel savedQRCodeModel2 = new SavedQRCodeModel(connectedDeviceMacAddress, savedQRCodeModel.getQrCodeList());
                SessionManager sessionManager2 = addQRCodeToWatchFragment.r;
                if (sessionManager2 != null) {
                    sessionManager2.saveQrCodeData(savedQRCodeModel2);
                }
                ((Button) addQRCodeToWatchFragment._$_findCachedViewById(R.id.btnUpload)).setText(addQRCodeToWatchFragment.getString(R.string.upload));
            }
        }
    }

    public AddQRCodeToWatchFragment() {
        String simpleName = AddQRCodeToWatchFragment.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "javaClass.simpleName");
        this.n = simpleName;
    }

    public static final void A(AddQRCodeToWatchFragment this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Toast.makeText(this$0.requireContext(), this$0.getString(R.string.unable_to_delete_qr_code), 1).show();
    }

    public static final void B(AddQRCodeToWatchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            AddQRCodeToWatchViewModel addQRCodeToWatchViewModel = null;
            BaseFragment.showProgress$default(this$0, false, 1, null);
            AddQRCodeToWatchViewModel addQRCodeToWatchViewModel2 = this$0.p;
            if (addQRCodeToWatchViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addQRCodeToWatchViewModel");
            } else {
                addQRCodeToWatchViewModel = addQRCodeToWatchViewModel2;
            }
            SelectedAppDataForQrCodePush selectedAppDataForQrCodePush = this$0.m;
            Intrinsics.checkNotNull(selectedAppDataForQrCodePush);
            int appType = selectedAppDataForQrCodePush.getAppType();
            String str = this$0.q;
            Intrinsics.checkNotNull(str);
            addQRCodeToWatchViewModel.pushQRCodeToWatch(appType, str);
            return;
        }
        Toast.makeText(this$0.requireContext(), this$0.getString(R.string.band_not_connected), 1).show();
    }

    @JvmStatic
    @NotNull
    public static final AddQRCodeToWatchFragment newInstance(@NotNull SelectedAppDataForQrCodePush selectedAppDataForQrCodePush) {
        return Companion.newInstance(selectedAppDataForQrCodePush);
    }

    public static final void t(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void u(AddQRCodeToWatchFragment this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        String str = this$0.n;
        StringBuilder sb = new StringBuilder();
        sb.append("selecting qr code addOnFailureListener ");
        String message = it.getMessage();
        if (message == null) {
            message = it.toString();
        }
        sb.append(message);
        LogHelper.e(str, sb.toString());
        if (!this$0.o) {
            ((ImageView) this$0._$_findCachedViewById(R.id.iv_qr_code_delete)).setImageResource(R.drawable.ic_qr_code_delete_disabled);
        }
        Toast.makeText(this$0.requireContext(), this$0.getString(R.string.the_qr_code_information_is_not_recognized), 1).show();
    }

    public static final void w(AddQRCodeToWatchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    public static final void x(AddQRCodeToWatchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivityForResult(Utils.INSTANCE.getGalleryIntent(), 1000);
    }

    public static final void y(final AddQRCodeToWatchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SavedQRCodeModel v = this$0.v();
        if (v != null) {
            String macAddress = v.getMacAddress();
            SessionManager sessionManager = this$0.r;
            if (Intrinsics.areEqual(macAddress, sessionManager != null ? sessionManager.getConnectedDeviceMacAddress() : null)) {
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                BarcodeScannerOptions build = new BarcodeScannerOptions.Builder().setBarcodeFormats(0, new int[0]).build();
                Intrinsics.checkNotNullExpressionValue(build, "Builder()\n              …RMAT_ALL_FORMATS).build()");
                Drawable drawable = ((RoundedImageView) this$0._$_findCachedViewById(R.id.iv_qr_code_holder)).getDrawable();
                Intrinsics.checkNotNullExpressionValue(drawable, "iv_qr_code_holder.drawable");
                InputImage fromBitmap = InputImage.fromBitmap(DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null), 0);
                Intrinsics.checkNotNullExpressionValue(fromBitmap, "fromBitmap(iv_qr_code_ho…r.drawable.toBitmap(), 0)");
                BarcodeScanner client = BarcodeScanning.getClient(build);
                Intrinsics.checkNotNullExpressionValue(client, "getClient(options)");
                Task<List<Barcode>> process = client.process(fromBitmap);
                final b bVar = new b(v, objectRef, this$0);
                process.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.leonardo.more.fragments.g
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        AddQRCodeToWatchFragment.z(Function1.this, obj);
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.more.fragments.f
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        AddQRCodeToWatchFragment.A(AddQRCodeToWatchFragment.this, exc);
                    }
                });
            }
        }
        int i = R.id.iv_qr_code_delete;
        ((ImageView) this$0._$_findCachedViewById(i)).setImageResource(R.drawable.ic_qr_code_delete_disabled);
        ((ImageView) this$0._$_findCachedViewById(i)).setEnabled(false);
        ((RoundedImageView) this$0._$_findCachedViewById(R.id.iv_qr_code_holder)).setImageResource(R.drawable.ic_qr_code_holder);
        Button btnUpload = (Button) this$0._$_findCachedViewById(R.id.btnUpload);
        Intrinsics.checkNotNullExpressionValue(btnUpload, "btnUpload");
        ViewUtilsKt.disable(btnUpload);
    }

    public static final void z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void C(String str) {
        dismissProgress();
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_add_qr_code, QRCodePushStatusFragment.Companion.newInstance(str)).addToBackStack(null).commit();
    }

    public final void D() {
        Integer num;
        SavedQRCodeModel v = v();
        int i = 0;
        if (v != null) {
            String macAddress = v.getMacAddress();
            SessionManager sessionManager = this.r;
            if (Intrinsics.areEqual(macAddress, sessionManager != null ? sessionManager.getConnectedDeviceMacAddress() : null)) {
                int size = v.getQrCodeList().size();
                while (true) {
                    if (i >= size) {
                        num = null;
                        break;
                    }
                    String appName = v.getQrCodeList().get(i).getAppName();
                    SelectedAppDataForQrCodePush selectedAppDataForQrCodePush = this.m;
                    if (Intrinsics.areEqual(appName, selectedAppDataForQrCodePush != null ? selectedAppDataForQrCodePush.getAppName() : null)) {
                        num = Integer.valueOf(i);
                        break;
                    }
                    i++;
                }
                if (num != null) {
                    v.getQrCodeList().remove(num.intValue());
                }
                SelectedAppDataForQrCodePush selectedAppDataForQrCodePush2 = this.m;
                Integer valueOf = selectedAppDataForQrCodePush2 != null ? Integer.valueOf(selectedAppDataForQrCodePush2.getAppType()) : null;
                String str = this.q;
                SelectedAppDataForQrCodePush selectedAppDataForQrCodePush3 = this.m;
                v.getQrCodeList().add(new QrCodeData(valueOf, str, selectedAppDataForQrCodePush3 != null ? selectedAppDataForQrCodePush3.getAppName() : null));
                SessionManager sessionManager2 = this.r;
                String connectedDeviceMacAddress = sessionManager2 != null ? sessionManager2.getConnectedDeviceMacAddress() : null;
                Intrinsics.checkNotNull(connectedDeviceMacAddress);
                SavedQRCodeModel savedQRCodeModel = new SavedQRCodeModel(connectedDeviceMacAddress, v.getQrCodeList());
                SessionManager sessionManager3 = this.r;
                if (sessionManager3 != null) {
                    sessionManager3.saveQrCodeData(savedQRCodeModel);
                    return;
                }
                return;
            }
        }
        if (this.r != null) {
            SelectedAppDataForQrCodePush selectedAppDataForQrCodePush4 = this.m;
            Integer valueOf2 = selectedAppDataForQrCodePush4 != null ? Integer.valueOf(selectedAppDataForQrCodePush4.getAppType()) : null;
            String str2 = this.q;
            SelectedAppDataForQrCodePush selectedAppDataForQrCodePush5 = this.m;
            QrCodeData qrCodeData = new QrCodeData(valueOf2, str2, selectedAppDataForQrCodePush5 != null ? selectedAppDataForQrCodePush5.getAppName() : null);
            SessionManager sessionManager4 = this.r;
            Intrinsics.checkNotNull(sessionManager4);
            String connectedDeviceMacAddress2 = sessionManager4.getConnectedDeviceMacAddress();
            Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress2, "sessionManager!!.connectedDeviceMacAddress");
            SavedQRCodeModel savedQRCodeModel2 = new SavedQRCodeModel(connectedDeviceMacAddress2, CollectionsKt__CollectionsKt.arrayListOf(qrCodeData));
            SessionManager sessionManager5 = this.r;
            Intrinsics.checkNotNull(sessionManager5);
            sessionManager5.saveQrCodeData(savedQRCodeModel2);
        }
    }

    public final void E() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_back_arrow);
        SelectedAppDataForQrCodePush selectedAppDataForQrCodePush = this.m;
        textView.setText(selectedAppDataForQrCodePush != null ? selectedAppDataForQrCodePush.getAppName() : null);
        int i = 0;
        ((ImageView) _$_findCachedViewById(R.id.iv_qr_code_delete)).setEnabled(false);
        SelectedAppDataForQrCodePush selectedAppDataForQrCodePush2 = this.m;
        if (Intrinsics.areEqual(selectedAppDataForQrCodePush2 != null ? selectedAppDataForQrCodePush2.getQrCodeCardType() : null, getString(R.string.wallet))) {
            ((TextView) _$_findCachedViewById(R.id.tv_upload_qr_code_instructions_1)).setText(getString(R.string.upload_steps_1_wallet));
        } else {
            ((TextView) _$_findCachedViewById(R.id.tv_upload_qr_code_instructions_1)).setText(getString(R.string.upload_steps_1_business));
        }
        SavedQRCodeModel v = v();
        if (v != null) {
            String macAddress = v.getMacAddress();
            SessionManager sessionManager = this.r;
            if (Intrinsics.areEqual(macAddress, sessionManager != null ? sessionManager.getConnectedDeviceMacAddress() : null)) {
                String str = this.n;
                LogHelper.d(str, "qrCodeList size " + v.getQrCodeList().size());
                for (Object obj : v.getQrCodeList()) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    QrCodeData qrCodeData = (QrCodeData) obj;
                    String appName = qrCodeData.getAppName();
                    SelectedAppDataForQrCodePush selectedAppDataForQrCodePush3 = this.m;
                    if (Intrinsics.areEqual(appName, selectedAppDataForQrCodePush3 != null ? selectedAppDataForQrCodePush3.getAppName() : null)) {
                        int i3 = R.id.btnUpload;
                        Button btnUpload = (Button) _$_findCachedViewById(i3);
                        Intrinsics.checkNotNullExpressionValue(btnUpload, "btnUpload");
                        ViewUtilsKt.enable(btnUpload);
                        ((Button) _$_findCachedViewById(i3)).setText(getString(R.string.reupload));
                        this.q = qrCodeData.getQrCodeMetaData();
                        int i4 = R.id.iv_qr_code_holder;
                        ((RoundedImageView) _$_findCachedViewById(i4)).setImageBitmap(QRCodeUtils.INSTANCE.getQRCodeBitmapFromMetaData(this.q));
                        ((RoundedImageView) _$_findCachedViewById(i4)).setRadius(20);
                        int i5 = R.id.iv_qr_code_delete;
                        ((ImageView) _$_findCachedViewById(i5)).setImageResource(R.drawable.ic_qr_code_delete_enabled);
                        ((ImageView) _$_findCachedViewById(i5)).setEnabled(true);
                        this.o = true;
                    }
                    i = i2;
                }
                return;
            }
        }
        LogHelper.d(this.n, "savedRQCodeData is null");
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

    public final void initClickListeners() {
        ((TextView) _$_findCachedViewById(R.id.tv_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddQRCodeToWatchFragment.w(AddQRCodeToWatchFragment.this, view);
            }
        });
        ((RoundedImageView) _$_findCachedViewById(R.id.iv_qr_code_holder)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddQRCodeToWatchFragment.x(AddQRCodeToWatchFragment.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.iv_qr_code_delete)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddQRCodeToWatchFragment.y(AddQRCodeToWatchFragment.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnUpload)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddQRCodeToWatchFragment.B(AddQRCodeToWatchFragment.this, view);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (i != 1000) {
            super.onActivityResult(i, i2, intent);
        } else if (intent != null) {
            try {
                Bitmap qrCodeBitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), intent.getData());
                Intrinsics.checkNotNullExpressionValue(qrCodeBitmap, "qrCodeBitmap");
                s(qrCodeBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable("appData");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.leonardo.model.SelectedAppDataForQrCodePush");
            this.m = (SelectedAppDataForQrCodePush) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_add_q_r_code_to_watch, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.more.viewmodel.AddQRCodeToWatchViewModel.AddQRCodeToWatchPushListener
    public void onFailure(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        String string = getString(R.string.failed);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.failed)");
        C(string);
    }

    @Override // com.coveiot.android.leonardo.more.viewmodel.AddQRCodeToWatchViewModel.AddQRCodeToWatchPushListener
    public void onSuccess() {
        D();
        String string = getString(R.string.success);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success)");
        C(string);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.p = (AddQRCodeToWatchViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(AddQRCodeToWatchViewModel.class);
        this.r = SessionManager.getInstance(requireContext());
        AddQRCodeToWatchViewModel addQRCodeToWatchViewModel = this.p;
        if (addQRCodeToWatchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addQRCodeToWatchViewModel");
            addQRCodeToWatchViewModel = null;
        }
        addQRCodeToWatchViewModel.setAddQRCodeToWatchPushListener(this);
        E();
        initClickListeners();
    }

    public final void s(Bitmap bitmap) {
        try {
            BarcodeScannerOptions build = new BarcodeScannerOptions.Builder().setBarcodeFormats(0, new int[0]).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder()\n              …RMAT_ALL_FORMATS).build()");
            InputImage fromBitmap = InputImage.fromBitmap(bitmap, 0);
            Intrinsics.checkNotNullExpressionValue(fromBitmap, "fromBitmap(qrCodeBitmap, 0)");
            BarcodeScanner client = BarcodeScanning.getClient(build);
            Intrinsics.checkNotNullExpressionValue(client, "getClient(options)");
            Task<List<Barcode>> process = client.process(fromBitmap);
            final a aVar = new a();
            process.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.leonardo.more.fragments.h
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    AddQRCodeToWatchFragment.t(Function1.this, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.more.fragments.e
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    AddQRCodeToWatchFragment.u(AddQRCodeToWatchFragment.this, exc);
                }
            });
        } catch (WriterException unused) {
            String str = this.n;
            LogHelper.d(str, "selected image does not has qr code " + this.q);
            ((RoundedImageView) _$_findCachedViewById(R.id.iv_qr_code_holder)).setImageResource(R.drawable.ic_qr_code_holder);
        } catch (Exception e) {
            String str2 = this.n;
            LogHelper.d(str2, "selected image does not has qr code " + this.q);
            if (!this.o) {
                ((ImageView) _$_findCachedViewById(R.id.iv_qr_code_delete)).setImageResource(R.drawable.ic_qr_code_delete_disabled);
            }
            Toast.makeText(requireContext(), getString(R.string.the_qr_code_information_is_not_recognized), 1).show();
            e.printStackTrace();
        }
    }

    public final SavedQRCodeModel v() {
        SessionManager sessionManager = this.r;
        if (sessionManager != null) {
            Intrinsics.checkNotNull(sessionManager);
            SavedQRCodeModel savedQRCode = sessionManager.getSavedQRCode();
            if (savedQRCode == null) {
                return null;
            }
            return savedQRCode;
        }
        return null;
    }
}
