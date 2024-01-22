package com.coveiot.android.watchfaceui.webSupport;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageWatchFace;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.android.theme.utils.FragmentUtilsKt;
import com.coveiot.android.theme.utils.ReviewAndRateUsConstants;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfacecore.utils.Constants;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.activities.ActivityWatchFace;
import com.coveiot.android.watchfaceui.listener.OnFailureListener;
import com.coveiot.android.watchfaceui.listener.OnSuccessListener;
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.android.watchfaceui.utils.ViewModelFactory;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWebViewerViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel;
import com.coveiot.android.watchfaceui.webSupport.JSInterface;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.healthbuddies.RequestStatus;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SWatchFaceList;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.watchface.WatchFaceApiManager;
import com.coveiot.coveaccess.watchface.model.WatchfaceByIdRequest;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes8.dex */
public final class ActivityInAppWebViewerWatchface extends BaseActivity implements JSInterface.OnJavascriptListener, JSInterface.OnPickerResult, JSInterface.OnCameraEvent, JSInterface.NavigationListener, JSInterface.OnApplyNowClicked, OnSuccessListener, OnFailureListener {
    public TextView backButtonIV;
    public WebView mWebView;
    public ActivityWebViewerViewModel q;
    public WatchFaceDiyViewModel r;
    public ActivityWatchFaceViewModel s;
    public ProgressBar t;
    public TextView u;
    public TextView v;
    @Nullable
    public BottomSheetDialog w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityInAppWebViewerWatchface";

    public static final void H(final ActivityInAppWebViewerWatchface this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        StringBuilder sb = new StringBuilder();
        sb.append("appOutScript: ");
        Intrinsics.checkNotNull(str);
        sb.append(str);
        LogHelper.d(str2, sb.toString());
        WebView mWebView = this$0.getMWebView();
        Intrinsics.checkNotNull(mWebView);
        mWebView.evaluateJavascript(str, new ValueCallback() { // from class: com.coveiot.android.watchfaceui.webSupport.l
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityInAppWebViewerWatchface.I(ActivityInAppWebViewerWatchface.this, (String) obj);
            }
        });
    }

    public static final void I(ActivityInAppWebViewerWatchface this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        LogHelper.d(str2, "onReceiveValue: " + str);
    }

    public static final void K(ActivityInAppWebViewerWatchface this$0, ProgressBean progressBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String title = progressBean.getTitle();
        TextView textView = null;
        if (title != null) {
            TextView textView2 = this$0.u;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressTitleTv");
                textView2 = null;
            }
            textView2.setText(title);
        }
        if (progressBean.getVisible()) {
            BottomSheetDialog bottomSheetDialog = this$0.w;
            if (bottomSheetDialog != null) {
                Intrinsics.checkNotNull(bottomSheetDialog);
                if (!bottomSheetDialog.isShowing()) {
                    BottomSheetDialog bottomSheetDialog2 = this$0.w;
                    Intrinsics.checkNotNull(bottomSheetDialog2);
                    bottomSheetDialog2.show();
                }
            }
            ProgressBar progressBar = this$0.t;
            if (progressBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pb");
                progressBar = null;
            }
            progressBar.setProgress(progressBean.getProgress());
            TextView textView3 = this$0.v;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressValue");
            } else {
                textView = textView3;
            }
            textView.setText(progressBean.getProgress() + " %");
            return;
        }
        BottomSheetDialog bottomSheetDialog3 = this$0.w;
        if (bottomSheetDialog3 != null) {
            Intrinsics.checkNotNull(bottomSheetDialog3);
            if (bottomSheetDialog3.isShowing()) {
                BottomSheetDialog bottomSheetDialog4 = this$0.w;
                Intrinsics.checkNotNull(bottomSheetDialog4);
                bottomSheetDialog4.dismiss();
            }
        }
    }

    public static final void L(ActivityInAppWebViewerWatchface this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void P(String str, String str2, int i, final ActivityInAppWebViewerWatchface this$0, String str3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("reqId", str);
        jSONObject.put("resType", str2);
        jSONObject.put("reqVer", i);
        jSONObject.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
        ActivityWebViewerViewModel activityWebViewerViewModel = this$0.q;
        ActivityWebViewerViewModel activityWebViewerViewModel2 = null;
        if (activityWebViewerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            activityWebViewerViewModel = null;
        }
        jSONObject.put("imgdata", activityWebViewerViewModel.getBase64Image());
        jSONObject.put("type", str3);
        ActivityWebViewerViewModel activityWebViewerViewModel3 = this$0.q;
        if (activityWebViewerViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            activityWebViewerViewModel3 = null;
        }
        activityWebViewerViewModel3.setPhotoObject(jSONObject);
        WebView mWebView = this$0.getMWebView();
        Intrinsics.checkNotNull(mWebView);
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:CoveJsInterface.appOut( ");
        ActivityWebViewerViewModel activityWebViewerViewModel4 = this$0.q;
        if (activityWebViewerViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
        } else {
            activityWebViewerViewModel2 = activityWebViewerViewModel4;
        }
        sb.append(activityWebViewerViewModel2.getPhotoObject());
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        mWebView.evaluateJavascript(sb.toString(), new ValueCallback() { // from class: com.coveiot.android.watchfaceui.webSupport.j
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityInAppWebViewerWatchface.Q(ActivityInAppWebViewerWatchface.this, (String) obj);
            }
        });
    }

    public static final void Q(ActivityInAppWebViewerWatchface this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        LogHelper.d(str2, "onReceiveValue: " + str);
    }

    public static final void R(final ActivityInAppWebViewerWatchface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("resId", 1);
        jSONObject.put("resType", "#BACK");
        jSONObject.put("reqVer", 1);
        jSONObject.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
        ActivityWebViewerViewModel activityWebViewerViewModel = this$0.q;
        ActivityWebViewerViewModel activityWebViewerViewModel2 = null;
        if (activityWebViewerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            activityWebViewerViewModel = null;
        }
        activityWebViewerViewModel.setBackObject(jSONObject);
        WebView mWebView = this$0.getMWebView();
        Intrinsics.checkNotNull(mWebView);
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:CoveJsInterface.appOut( ");
        ActivityWebViewerViewModel activityWebViewerViewModel3 = this$0.q;
        if (activityWebViewerViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
        } else {
            activityWebViewerViewModel2 = activityWebViewerViewModel3;
        }
        sb.append(activityWebViewerViewModel2.getBackObject());
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        mWebView.evaluateJavascript(sb.toString(), new ValueCallback() { // from class: com.coveiot.android.watchfaceui.webSupport.m
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityInAppWebViewerWatchface.S(ActivityInAppWebViewerWatchface.this, (String) obj);
            }
        });
    }

    public static final void S(ActivityInAppWebViewerWatchface this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        LogHelper.d(str2, "onReceiveValue: " + str);
    }

    public static final void T(final ActivityInAppWebViewerWatchface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("resId", 1);
        jSONObject.put("resType", "#BACK");
        jSONObject.put("reqVer", 1);
        jSONObject.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
        ActivityWebViewerViewModel activityWebViewerViewModel = this$0.q;
        ActivityWebViewerViewModel activityWebViewerViewModel2 = null;
        if (activityWebViewerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            activityWebViewerViewModel = null;
        }
        activityWebViewerViewModel.setBackObject(jSONObject);
        WebView mWebView = this$0.getMWebView();
        Intrinsics.checkNotNull(mWebView);
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:CoveJsInterface.appOut( ");
        ActivityWebViewerViewModel activityWebViewerViewModel3 = this$0.q;
        if (activityWebViewerViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
        } else {
            activityWebViewerViewModel2 = activityWebViewerViewModel3;
        }
        sb.append(activityWebViewerViewModel2.getBackObject());
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        mWebView.evaluateJavascript(sb.toString(), new ValueCallback() { // from class: com.coveiot.android.watchfaceui.webSupport.k
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityInAppWebViewerWatchface.U(ActivityInAppWebViewerWatchface.this, (String) obj);
            }
        });
    }

    public static final void U(ActivityInAppWebViewerWatchface this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        LogHelper.d(str2, "onReceiveValue: " + str);
    }

    public static final void V(ActivityInAppWebViewerWatchface this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void W(ActivityInAppWebViewerWatchface this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isBluetoothEnabled(this$0)) {
            Toast.makeText(this$0, R.string.bluetooth_off_message, 0).show();
        } else {
            Toast.makeText(this$0, str, 1).show();
        }
    }

    public static final void Z(String str, String str2, int i, String str3, final ActivityInAppWebViewerWatchface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("reqId", str);
        jSONObject.put("resType", str2);
        jSONObject.put("reqVer", i);
        jSONObject.put(NotificationCompat.CATEGORY_STATUS, RequestStatus.CANCELLED);
        jSONObject.put("imgdata", "-1");
        jSONObject.put("type", str3);
        ActivityWebViewerViewModel activityWebViewerViewModel = this$0.q;
        ActivityWebViewerViewModel activityWebViewerViewModel2 = null;
        if (activityWebViewerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            activityWebViewerViewModel = null;
        }
        activityWebViewerViewModel.setPhotoObject(jSONObject);
        WebView mWebView = this$0.getMWebView();
        Intrinsics.checkNotNull(mWebView);
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:CoveJsInterface.appOut( ");
        ActivityWebViewerViewModel activityWebViewerViewModel3 = this$0.q;
        if (activityWebViewerViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
        } else {
            activityWebViewerViewModel2 = activityWebViewerViewModel3;
        }
        sb.append(activityWebViewerViewModel2.getPhotoObject());
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        mWebView.evaluateJavascript(sb.toString(), new ValueCallback() { // from class: com.coveiot.android.watchfaceui.webSupport.n
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityInAppWebViewerWatchface.a0(ActivityInAppWebViewerWatchface.this, (String) obj);
            }
        });
    }

    public static final void a0(ActivityInAppWebViewerWatchface this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        LogHelper.d(str2, "onReceiveValue: " + str);
    }

    public static final void c0(ActivityInAppWebViewerWatchface this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0, 351);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void d0(BottomSheetDialogImageTitleMessageWatchFace bottomSheetDialogImageTitleMessageWatchFace, ActivityInAppWebViewerWatchface this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessageWatchFace, "$bottomSheetDialogImageTitleMessageWatchFace");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessageWatchFace.dismiss();
        if (!SessionManager.getInstance(this$0).isRateUsDialogShown()) {
            this$0.X();
        } else {
            this$0.O();
        }
    }

    public final String G(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "baos.toByteArray()");
        return "data:image/jpeg;base64," + Base64.encodeToString(byteArray, 2);
    }

    public final Bitmap J(Uri uri) {
        Bitmap bitmap;
        if (Build.VERSION.SDK_INT < 28) {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
        } else {
            ImageDecoder.Source createSource = ImageDecoder.createSource(getContentResolver(), uri);
            Intrinsics.checkNotNullExpressionValue(createSource, "createSource(this.conten…solver, selectedPhotoUri)");
            bitmap = ImageDecoder.decodeBitmap(createSource);
        }
        InputStream openInputStream = getContentResolver().openInputStream(uri);
        Intrinsics.checkNotNull(openInputStream);
        int attributeInt = new ExifInterface(openInputStream).getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
        if (attributeInt == 3) {
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
            return rotateImage(bitmap, 0.0f);
        } else if (attributeInt == 6) {
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
            return rotateImage(bitmap, 0.0f);
        } else if (attributeInt != 8) {
            return bitmap;
        } else {
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
            return rotateImage(bitmap, 0.0f);
        }
    }

    public final void M() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.DialogTheme);
        this.w = bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.requestFeature(1);
        BottomSheetDialog bottomSheetDialog2 = this.w;
        Intrinsics.checkNotNull(bottomSheetDialog2);
        bottomSheetDialog2.setContentView(R.layout.dialog_uploading_watchface);
        BottomSheetDialog bottomSheetDialog3 = this.w;
        Intrinsics.checkNotNull(bottomSheetDialog3);
        bottomSheetDialog3.setCancelable(false);
        BottomSheetDialog bottomSheetDialog4 = this.w;
        Intrinsics.checkNotNull(bottomSheetDialog4);
        View findViewById = bottomSheetDialog4.findViewById(R.id.tv_progress_title);
        Intrinsics.checkNotNull(findViewById);
        this.u = (TextView) findViewById;
        BottomSheetDialog bottomSheetDialog5 = this.w;
        Intrinsics.checkNotNull(bottomSheetDialog5);
        View findViewById2 = bottomSheetDialog5.findViewById(R.id.progress_value);
        Intrinsics.checkNotNull(findViewById2);
        this.v = (TextView) findViewById2;
        BottomSheetDialog bottomSheetDialog6 = this.w;
        Intrinsics.checkNotNull(bottomSheetDialog6);
        View findViewById3 = bottomSheetDialog6.findViewById(R.id.progress_update);
        Intrinsics.checkNotNull(findViewById3);
        this.t = (ProgressBar) findViewById3;
    }

    public final void N(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(CleverTapConstants.CustomEventProperties.SOURCE.getValue(), str);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getDeviceId(this));
        hashMap.putAll(companion.getWatchDetails(this));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.WFS_ACCESSED.getValue(), hashMap);
    }

    public final void O() {
        finish();
        Intent intent = new Intent(this, ActivityWatchFace.class);
        intent.setFlags(67108864);
        startActivity(intent);
    }

    public final void X() {
        Bundle bundle = new Bundle();
        bundle.putString("cv_prev_screen_name", ReviewAndRateUsConstants.CREATE_WATCHFACE.getValue());
        DialogFragment externalModuleDialogFragment = FragmentUtilsKt.getExternalModuleDialogFragment("com.coveiot.android.leonardo.rateus.RateUsDialogFragment");
        if (externalModuleDialogFragment != null) {
            externalModuleDialogFragment.setArguments(bundle);
        }
        if (externalModuleDialogFragment != null) {
            externalModuleDialogFragment.show(getSupportFragmentManager(), ActivityInAppWebViewerWatchface.class.getSimpleName());
        }
    }

    public final void Y() {
        ActivityWebViewerViewModel activityWebViewerViewModel = this.q;
        ActivityWebViewerViewModel activityWebViewerViewModel2 = null;
        if (activityWebViewerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            activityWebViewerViewModel = null;
        }
        if (activityWebViewerViewModel.getCameraObject() != null) {
            ActivityWebViewerViewModel activityWebViewerViewModel3 = this.q;
            if (activityWebViewerViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                activityWebViewerViewModel3 = null;
            }
            JSONObject cameraObject = activityWebViewerViewModel3.getCameraObject();
            Intrinsics.checkNotNull(cameraObject);
            final String string = cameraObject.getString("reqType");
            ActivityWebViewerViewModel activityWebViewerViewModel4 = this.q;
            if (activityWebViewerViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                activityWebViewerViewModel4 = null;
            }
            JSONObject cameraObject2 = activityWebViewerViewModel4.getCameraObject();
            Intrinsics.checkNotNull(cameraObject2);
            final int i = cameraObject2.getInt("reqVer");
            ActivityWebViewerViewModel activityWebViewerViewModel5 = this.q;
            if (activityWebViewerViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                activityWebViewerViewModel5 = null;
            }
            JSONObject cameraObject3 = activityWebViewerViewModel5.getCameraObject();
            Intrinsics.checkNotNull(cameraObject3);
            final String string2 = cameraObject3.getString("reqId");
            ActivityWebViewerViewModel activityWebViewerViewModel6 = this.q;
            if (activityWebViewerViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            } else {
                activityWebViewerViewModel2 = activityWebViewerViewModel6;
            }
            JSONObject cameraObject4 = activityWebViewerViewModel2.getCameraObject();
            Intrinsics.checkNotNull(cameraObject4);
            final String string3 = cameraObject4.getString("type");
            runOnUiThread(new Runnable() { // from class: com.coveiot.android.watchfaceui.webSupport.g
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityInAppWebViewerWatchface.Z(string2, string, i, string3, this);
                }
            });
        }
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

    public final void b0(String str) {
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.webSupport.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityInAppWebViewerWatchface.c0(ActivityInAppWebViewerWatchface.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    @Override // com.coveiot.android.watchfaceui.webSupport.JSInterface.OnJavascriptListener
    public void evaluateJavascript(@Nullable final String str) {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.watchfaceui.webSupport.e
            @Override // java.lang.Runnable
            public final void run() {
                ActivityInAppWebViewerWatchface.H(ActivityInAppWebViewerWatchface.this, str);
            }
        });
    }

    @NotNull
    public final TextView getBackButtonIV() {
        TextView textView = this.backButtonIV;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("backButtonIV");
        return null;
    }

    @NotNull
    public final WebView getMWebView() {
        WebView webView = this.mWebView;
        if (webView != null) {
            return webView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        return null;
    }

    public final void initDiyWatchFace() {
        WatchFaceDiyViewModel watchFaceDiyViewModel = this.r;
        WatchFaceDiyViewModel watchFaceDiyViewModel2 = null;
        if (watchFaceDiyViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel = null;
        }
        watchFaceDiyViewModel.setViewModelOnSuccessListener(this);
        WatchFaceDiyViewModel watchFaceDiyViewModel3 = this.r;
        if (watchFaceDiyViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel3 = null;
        }
        watchFaceDiyViewModel3.setViewModelOnFailureListener(this);
        WatchFaceDiyViewModel watchFaceDiyViewModel4 = this.r;
        if (watchFaceDiyViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel4 = null;
        }
        watchFaceDiyViewModel4.getProgressLiveData().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.webSupport.o
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityInAppWebViewerWatchface.K(ActivityInAppWebViewerWatchface.this, (ProgressBean) obj);
            }
        });
        WatchFaceDiyViewModel watchFaceDiyViewModel5 = this.r;
        if (watchFaceDiyViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
        } else {
            watchFaceDiyViewModel2 = watchFaceDiyViewModel5;
        }
        watchFaceDiyViewModel2.isUploadToWatchCanceled().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.webSupport.p
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityInAppWebViewerWatchface.L(ActivityInAppWebViewerWatchface.this, (Boolean) obj);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    @RequiresApi(28)
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        Bitmap bitmap;
        super.onActivityResult(i, i2, intent);
        ActivityWebViewerViewModel activityWebViewerViewModel = null;
        if (i == 1000) {
            ActivityWebViewerViewModel activityWebViewerViewModel2 = this.q;
            if (activityWebViewerViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                activityWebViewerViewModel2 = null;
            }
            if (activityWebViewerViewModel2.getUploadMessage() != null) {
                ActivityWebViewerViewModel activityWebViewerViewModel3 = this.q;
                if (activityWebViewerViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                    activityWebViewerViewModel3 = null;
                }
                ValueCallback<Uri[]> uploadMessage = activityWebViewerViewModel3.getUploadMessage();
                Intrinsics.checkNotNull(uploadMessage);
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(i2, intent));
                ActivityWebViewerViewModel activityWebViewerViewModel4 = this.q;
                if (activityWebViewerViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                    activityWebViewerViewModel4 = null;
                }
                activityWebViewerViewModel4.setUploadMessage(null);
            }
        } else if (i != 1001) {
        } else {
            if (i2 == -1) {
                ActivityWebViewerViewModel activityWebViewerViewModel5 = this.q;
                if (activityWebViewerViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                    activityWebViewerViewModel5 = null;
                }
                if (activityWebViewerViewModel5.getPhotoURI() != null) {
                    ActivityWebViewerViewModel activityWebViewerViewModel6 = this.q;
                    if (activityWebViewerViewModel6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                        activityWebViewerViewModel6 = null;
                    }
                    Uri photoURI = activityWebViewerViewModel6.getPhotoURI();
                    Intrinsics.checkNotNull(photoURI);
                    bitmap = J(photoURI);
                } else {
                    bitmap = null;
                }
                ActivityWebViewerViewModel activityWebViewerViewModel7 = this.q;
                if (activityWebViewerViewModel7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                    activityWebViewerViewModel7 = null;
                }
                activityWebViewerViewModel7.setBase64Image(bitmap != null ? G(bitmap) : null);
                ActivityWebViewerViewModel activityWebViewerViewModel8 = this.q;
                if (activityWebViewerViewModel8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                    activityWebViewerViewModel8 = null;
                }
                if (activityWebViewerViewModel8.getCameraObject() != null) {
                    ActivityWebViewerViewModel activityWebViewerViewModel9 = this.q;
                    if (activityWebViewerViewModel9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                        activityWebViewerViewModel9 = null;
                    }
                    JSONObject cameraObject = activityWebViewerViewModel9.getCameraObject();
                    Intrinsics.checkNotNull(cameraObject);
                    final String string = cameraObject.getString("reqType");
                    ActivityWebViewerViewModel activityWebViewerViewModel10 = this.q;
                    if (activityWebViewerViewModel10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                        activityWebViewerViewModel10 = null;
                    }
                    JSONObject cameraObject2 = activityWebViewerViewModel10.getCameraObject();
                    Intrinsics.checkNotNull(cameraObject2);
                    final int i3 = cameraObject2.getInt("reqVer");
                    ActivityWebViewerViewModel activityWebViewerViewModel11 = this.q;
                    if (activityWebViewerViewModel11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                        activityWebViewerViewModel11 = null;
                    }
                    JSONObject cameraObject3 = activityWebViewerViewModel11.getCameraObject();
                    Intrinsics.checkNotNull(cameraObject3);
                    final String string2 = cameraObject3.getString("reqId");
                    ActivityWebViewerViewModel activityWebViewerViewModel12 = this.q;
                    if (activityWebViewerViewModel12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                    } else {
                        activityWebViewerViewModel = activityWebViewerViewModel12;
                    }
                    JSONObject cameraObject4 = activityWebViewerViewModel.getCameraObject();
                    Intrinsics.checkNotNull(cameraObject4);
                    final String string3 = cameraObject4.getString("type");
                    runOnUiThread(new Runnable() { // from class: com.coveiot.android.watchfaceui.webSupport.f
                        @Override // java.lang.Runnable
                        public final void run() {
                            ActivityInAppWebViewerWatchface.P(string2, string, i3, this, string3);
                        }
                    });
                    return;
                }
                return;
            }
            Y();
        }
    }

    @Override // com.coveiot.android.watchfaceui.webSupport.JSInterface.NavigationListener
    public void onBackPress() {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.watchfaceui.webSupport.c
            @Override // java.lang.Runnable
            public final void run() {
                ActivityInAppWebViewerWatchface.R(ActivityInAppWebViewerWatchface.this);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.watchfaceui.webSupport.b
            @Override // java.lang.Runnable
            public final void run() {
                ActivityInAppWebViewerWatchface.T(ActivityInAppWebViewerWatchface.this);
            }
        });
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onBackgroundWatchFaceSuccess(boolean z) {
    }

    @Override // com.coveiot.android.watchfaceui.webSupport.JSInterface.OnCameraEvent
    public void onCamEvent(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            ActivityWebViewerViewModel activityWebViewerViewModel = this.q;
            if (activityWebViewerViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                activityWebViewerViewModel = null;
            }
            activityWebViewerViewModel.setCameraObject(jSONObject);
            takePhoto();
        }
    }

    @Override // com.coveiot.android.watchfaceui.webSupport.JSInterface.OnApplyNowClicked
    public void onClicked(@NotNull String uid, @NotNull String faceId, @NotNull String faceType) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(faceId, "faceId");
        Intrinsics.checkNotNullParameter(faceType, "faceType");
        WatchfaceByIdRequest watchfaceByIdRequest = new WatchfaceByIdRequest();
        ArrayList arrayList = new ArrayList();
        arrayList.add(uid);
        watchfaceByIdRequest.setUids(arrayList);
        watchfaceByIdRequest.setFaceType(faceType);
        WatchFaceApiManager.getWatchFaceListById(new CoveApiListener<SWatchFaceList, CoveApiErrorModel>() { // from class: com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface$onClicked$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                str = ActivityInAppWebViewerWatchface.this.p;
                LogHelper.d(str, "downloadWatchFaceFromServer failed.");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SWatchFaceList sWatchFaceList) {
                WatchFaceDiyViewModel watchFaceDiyViewModel;
                WatchFaceDiyViewModel watchFaceDiyViewModel2;
                if (sWatchFaceList == null || sWatchFaceList.getData() == null) {
                    return;
                }
                List<SWatchFaceList.DataBean.ItemsBean> items = sWatchFaceList.getData().getItems();
                if (items == null || items.isEmpty()) {
                    return;
                }
                SWatchFaceList.DataBean.ItemsBean itemsBean = sWatchFaceList.getData().getItems().get(0);
                String uid2 = itemsBean.getUid();
                Intrinsics.checkNotNullExpressionValue(uid2, "item.uid");
                String faceId2 = itemsBean.getFaceId();
                Intrinsics.checkNotNullExpressionValue(faceId2, "item.faceId");
                WatchFaceBean watchFaceBean = new WatchFaceBean(uid2, faceId2, itemsBean.getFaceType(), itemsBean.getTitle(), itemsBean.getDownloadUrl(), itemsBean.getFileType(), itemsBean.getFileMd5Hash(), itemsBean.getPreviewImageUrl(), itemsBean.getTags(), null, null, null, null, false, null, 32256, null);
                watchFaceDiyViewModel = ActivityInAppWebViewerWatchface.this.r;
                WatchFaceDiyViewModel watchFaceDiyViewModel3 = null;
                if (watchFaceDiyViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                    watchFaceDiyViewModel = null;
                }
                watchFaceDiyViewModel.setUserSelectedWatchFace(watchFaceBean);
                String str = (BleApiManager.getInstance(ActivityInAppWebViewerWatchface.this) == null || BleApiManager.getInstance(ActivityInAppWebViewerWatchface.this).getDeviceType() != DeviceType.matrix) ? "watchface" : Constants.WATCH_FACE_FILE_NAME_MATRIX;
                watchFaceDiyViewModel2 = ActivityInAppWebViewerWatchface.this.r;
                if (watchFaceDiyViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                } else {
                    watchFaceDiyViewModel3 = watchFaceDiyViewModel2;
                }
                String downloadUrl = watchFaceBean.getDownloadUrl();
                Intrinsics.checkNotNull(downloadUrl);
                watchFaceDiyViewModel3.downloadWatchFaceFromServerSendToWatch(str, downloadUrl, watchFaceBean);
            }
        }, watchfaceByIdRequest);
        arrayList.clear();
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onCloudWatchFaceSuccess(boolean z) {
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @RequiresApi(26)
    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(@Nullable Bundle bundle) {
        String watchFaceDiyUrl;
        String str;
        boolean z;
        String queryParameter;
        super.onCreate(bundle);
        setContentView(R.layout.activity_in_app_web_viewer_watchface);
        View findViewById = findViewById(R.id.web_view);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.web_view)");
        setMWebView((WebView) findViewById);
        View findViewById2 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.toolbar_back_arrow)");
        setBackButtonIV((TextView) findViewById2);
        getBackButtonIV().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.webSupport.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityInAppWebViewerWatchface.V(ActivityInAppWebViewerWatchface.this, view);
            }
        });
        Intent intent = getIntent();
        com.coveiot.android.watchfaceui.constants.Constants constants = com.coveiot.android.watchfaceui.constants.Constants.INTENT_EXTRA_URL;
        if (intent.hasExtra(constants.getValue())) {
            watchFaceDiyUrl = getIntent().getStringExtra(constants.getValue());
            Intrinsics.checkNotNull(watchFaceDiyUrl);
        } else {
            watchFaceDiyUrl = SessionManager.getInstance(this).getWatchFaceDiyUrl();
            Intrinsics.checkNotNullExpressionValue(watchFaceDiyUrl, "getInstance(this@Activit…atchface).watchFaceDiyUrl");
        }
        String stringExtra = getIntent().getStringExtra(com.coveiot.android.watchfaceui.constants.Constants.INTENT_EXTRA_TITLE.getValue());
        ViewModel viewModel = new ViewModelProvider(this, new ViewModelFactory(this)).get(ActivityWebViewerViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this, …werViewModel::class.java)");
        this.q = (ActivityWebViewerViewModel) viewModel;
        ViewModel viewModel2 = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WatchFaceDiyViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(\n            this,\n  …DiyViewModel::class.java)");
        this.r = (WatchFaceDiyViewModel) viewModel2;
        ViewModel viewModel3 = ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityWatchFaceViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel3, "of(\n            this,\n  …aceViewModel::class.java)");
        ActivityWatchFaceViewModel activityWatchFaceViewModel = (ActivityWatchFaceViewModel) viewModel3;
        this.s = activityWatchFaceViewModel;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.setViewModelOnSuccessListener(this);
        ActivityWatchFaceViewModel activityWatchFaceViewModel2 = this.s;
        if (activityWatchFaceViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel2 = null;
        }
        activityWatchFaceViewModel2.setViewModelOnFailureListener(this);
        boolean z2 = false;
        boolean booleanExtra = getIntent().getBooleanExtra(com.coveiot.android.watchfaceui.constants.Constants.INTENT_EXTRA_FROM_DASHBOARD.getValue(), false);
        boolean booleanExtra2 = getIntent().getBooleanExtra(ThemeConstants.FROM_SMART_GRID.getValue(), false);
        boolean booleanExtra3 = getIntent().getBooleanExtra(ThemeConstants.INTENT_EXTRA_FROM_NOTIFICATION.getValue(), false);
        String stringExtra2 = getIntent().getStringExtra(ThemeConstants.NOTIFY_IDENTIFIER.getValue());
        if (getIntent().getData() != null) {
            Uri data = getIntent().getData();
            if (data != null && (queryParameter = data.getQueryParameter(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE)) != null && queryParameter.equals("1")) {
                z2 = true;
            }
            if (z2) {
                String queryParameter2 = data != null ? data.getQueryParameter("__u") : null;
                String replace$default = queryParameter2 != null ? kotlin.text.m.replace$default(queryParameter2, "-", "+", false, 4, (Object) null) : null;
                watchFaceDiyUrl = String.valueOf(DeviceUtils.Companion.decryptRSA(this, (replace$default != null ? kotlin.text.m.replace$default(replace$default, "_", MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 4, (Object) null) : null) + '='));
            }
            str = watchFaceDiyUrl;
            z = true;
        } else {
            str = watchFaceDiyUrl;
            z = booleanExtra3;
        }
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            if (extras.getString("actionId") != null) {
                Bundle extras2 = getIntent().getExtras();
                Intrinsics.checkNotNull(extras2);
                boolean z3 = extras2.getBoolean("autoCancel", true);
                Bundle extras3 = getIntent().getExtras();
                Intrinsics.checkNotNull(extras3);
                int i = extras3.getInt(com.clevertap.android.sdk.Constants.PT_NOTIF_ID, -1);
                if (z3 && i > -1) {
                    Object systemService = getApplicationContext().getSystemService("notification");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                    ((NotificationManager) systemService).cancel(i);
                }
            }
        }
        if (!AppUtils.isEmpty(stringExtra)) {
            ((TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_title)).setText(stringExtra);
        } else {
            _$_findCachedViewById(R.id.toolbar).setVisibility(8);
        }
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_PAGE_VISIT.getValue());
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.CREATE_CUSTOM_WATCHFACE.getValue());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("cv_feature_name", "diy_watchface");
        if (z) {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.SYSTEM_NOTIFICATION.getValue());
            if (stringExtra2 != null) {
                hashMap.put("cv_notif_identifier", stringExtra2);
                analyticsLog.setMapData(hashMap);
            }
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } else {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.WATCHFACES.getValue());
            analyticsLog.setMapData(hashMap);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        }
        getMWebView().addJavascriptInterface(new JSInterface(this, this, this, this, this, this, booleanExtra, z, stringExtra2), "CoveJsInterface");
        getMWebView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        if (!AppUtils.isEmpty(str)) {
            String deviceAgent = PreferenceManager.getInstance().getDeviceAgent();
            if (!AppUtils.isEmpty(deviceAgent)) {
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                Intrinsics.checkNotNull(str);
                Intrinsics.checkNotNullExpressionValue(deviceAgent, "deviceAgent");
                str = companion.appendUri(str, deviceAgent);
            }
            getMWebView().clearCache(true);
            WebView mWebView = getMWebView();
            Intrinsics.checkNotNull(mWebView);
            WebSettings settings = mWebView.getSettings();
            Intrinsics.checkNotNullExpressionValue(settings, "mWebView!!.settings");
            settings.setJavaScriptEnabled(true);
            settings.setAllowFileAccess(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
            settings.setDomStorageEnabled(true);
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath("/data/data/" + getMWebView().getContext().getPackageName() + "/databases/");
            settings.setCacheMode(2);
            WebView mWebView2 = getMWebView();
            Intrinsics.checkNotNull(mWebView2);
            mWebView2.setWebViewClient(new WebViewClient() { // from class: com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface$onCreate$2
                /* JADX WARN: Code restructure failed: missing block: B:20:0x0051, code lost:
                    if (r3.getRedirect() != false) goto L23;
                 */
                @Override // android.webkit.WebViewClient
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onPageFinished(@org.jetbrains.annotations.NotNull android.webkit.WebView r3, @org.jetbrains.annotations.NotNull java.lang.String r4) {
                    /*
                        r2 = this;
                        java.lang.String r0 = "view"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                        java.lang.String r3 = "url"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
                        com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface r3 = com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface.this
                        com.coveiot.android.watchfaceui.viewmodel.ActivityWebViewerViewModel r3 = com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface.access$getMActivityWebViewerViewModel$p(r3)
                        r4 = 0
                        java.lang.String r0 = "mActivityWebViewerViewModel"
                        if (r3 != 0) goto L19
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                        r3 = r4
                    L19:
                        boolean r3 = r3.getRedirect()
                        if (r3 != 0) goto L2f
                        com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface r3 = com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface.this
                        com.coveiot.android.watchfaceui.viewmodel.ActivityWebViewerViewModel r3 = com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface.access$getMActivityWebViewerViewModel$p(r3)
                        if (r3 != 0) goto L2b
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                        r3 = r4
                    L2b:
                        r1 = 1
                        r3.setLoadingFinished(r1)
                    L2f:
                        com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface r3 = com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface.this
                        com.coveiot.android.watchfaceui.viewmodel.ActivityWebViewerViewModel r3 = com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface.access$getMActivityWebViewerViewModel$p(r3)
                        if (r3 != 0) goto L3b
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                        r3 = r4
                    L3b:
                        boolean r3 = r3.getLoadingFinished()
                        if (r3 == 0) goto L53
                        com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface r3 = com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface.this
                        com.coveiot.android.watchfaceui.viewmodel.ActivityWebViewerViewModel r3 = com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface.access$getMActivityWebViewerViewModel$p(r3)
                        if (r3 != 0) goto L4d
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                        r3 = r4
                    L4d:
                        boolean r3 = r3.getRedirect()
                        if (r3 == 0) goto L64
                    L53:
                        com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface r3 = com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface.this
                        com.coveiot.android.watchfaceui.viewmodel.ActivityWebViewerViewModel r3 = com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface.access$getMActivityWebViewerViewModel$p(r3)
                        if (r3 != 0) goto L5f
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                        goto L60
                    L5f:
                        r4 = r3
                    L60:
                        r3 = 0
                        r4.setRedirect(r3)
                    L64:
                        com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface r3 = com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface.this
                        r3.dismissProgress()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface$onCreate$2.onPageFinished(android.webkit.WebView, java.lang.String):void");
                }

                @Override // android.webkit.WebViewClient
                public void onPageStarted(@Nullable WebView webView, @Nullable String str2, @Nullable Bitmap bitmap) {
                    ActivityWebViewerViewModel activityWebViewerViewModel;
                    activityWebViewerViewModel = ActivityInAppWebViewerWatchface.this.q;
                    if (activityWebViewerViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                        activityWebViewerViewModel = null;
                    }
                    activityWebViewerViewModel.setLoadingFinished(false);
                }

                @Override // android.webkit.WebViewClient
                public void onReceivedError(@NotNull WebView view, int i2, @NotNull String description, @NotNull String failingUrl) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(description, "description");
                    Intrinsics.checkNotNullParameter(failingUrl, "failingUrl");
                    ActivityInAppWebViewerWatchface.this.dismissProgress();
                }

                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull String url) {
                    ActivityWebViewerViewModel activityWebViewerViewModel;
                    ActivityWebViewerViewModel activityWebViewerViewModel2;
                    ActivityWebViewerViewModel activityWebViewerViewModel3;
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(url, "url");
                    activityWebViewerViewModel = ActivityInAppWebViewerWatchface.this.q;
                    ActivityWebViewerViewModel activityWebViewerViewModel4 = null;
                    if (activityWebViewerViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                        activityWebViewerViewModel = null;
                    }
                    if (!activityWebViewerViewModel.getLoadingFinished()) {
                        activityWebViewerViewModel3 = ActivityInAppWebViewerWatchface.this.q;
                        if (activityWebViewerViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                            activityWebViewerViewModel3 = null;
                        }
                        activityWebViewerViewModel3.setRedirect(true);
                    }
                    activityWebViewerViewModel2 = ActivityInAppWebViewerWatchface.this.q;
                    if (activityWebViewerViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                    } else {
                        activityWebViewerViewModel4 = activityWebViewerViewModel2;
                    }
                    activityWebViewerViewModel4.setLoadingFinished(false);
                    view.loadUrl(url);
                    return true;
                }
            });
            WebView mWebView3 = getMWebView();
            Intrinsics.checkNotNull(mWebView3);
            mWebView3.setWebChromeClient(new WebChromeClient() { // from class: com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface$onCreate$3
                @Override // android.webkit.WebChromeClient
                public void onPermissionRequest(@Nullable PermissionRequest permissionRequest) {
                    if (permissionRequest != null) {
                        permissionRequest.grant(permissionRequest.getResources());
                    }
                }

                @Override // android.webkit.WebChromeClient
                public boolean onShowFileChooser(@Nullable WebView webView, @Nullable ValueCallback<Uri[]> valueCallback, @Nullable WebChromeClient.FileChooserParams fileChooserParams) {
                    ActivityWebViewerViewModel activityWebViewerViewModel;
                    ActivityWebViewerViewModel activityWebViewerViewModel2;
                    ActivityWebViewerViewModel activityWebViewerViewModel3;
                    ActivityWebViewerViewModel activityWebViewerViewModel4;
                    activityWebViewerViewModel = ActivityInAppWebViewerWatchface.this.q;
                    if (activityWebViewerViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                        activityWebViewerViewModel = null;
                    }
                    if (activityWebViewerViewModel.getUploadMessage() != null) {
                        activityWebViewerViewModel4 = ActivityInAppWebViewerWatchface.this.q;
                        if (activityWebViewerViewModel4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                            activityWebViewerViewModel4 = null;
                        }
                        ValueCallback<Uri[]> uploadMessage = activityWebViewerViewModel4.getUploadMessage();
                        if (uploadMessage != null) {
                            uploadMessage.onReceiveValue(null);
                        }
                    }
                    activityWebViewerViewModel2 = ActivityInAppWebViewerWatchface.this.q;
                    if (activityWebViewerViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                        activityWebViewerViewModel2 = null;
                    }
                    activityWebViewerViewModel2.setUploadMessage(valueCallback);
                    try {
                        ActivityInAppWebViewerWatchface.this.startActivityForResult(Utils.INSTANCE.getGalleryIntent(), 1000);
                        return true;
                    } catch (ActivityNotFoundException unused) {
                        activityWebViewerViewModel3 = ActivityInAppWebViewerWatchface.this.q;
                        if (activityWebViewerViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                            activityWebViewerViewModel3 = null;
                        }
                        activityWebViewerViewModel3.setUploadMessage(null);
                        return false;
                    }
                }
            });
            showProgress();
            WebView mWebView4 = getMWebView();
            Intrinsics.checkNotNull(str);
            mWebView4.loadUrl(str);
            if (booleanExtra) {
                if (booleanExtra2) {
                    N(CleverTapConstants.CustomEventValues.HP_GRID.getValue());
                } else {
                    N(CleverTapConstants.CustomEventValues.HP_BANNER.getValue());
                }
            } else if (z) {
                N(CleverTapConstants.CustomEventValues.APP_PUSH.getValue());
            } else {
                N(CleverTapConstants.CustomEventValues.MYCREATION.getValue());
            }
        }
        M();
        initDiyWatchFace();
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onCurrentWatchFetchPositionFetchSuccess(int i) {
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onDefaultWatchFaceSuccess(boolean z) {
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onDiyWatchFaceSuccess(boolean z) {
        WatchFaceDiyViewModel watchFaceDiyViewModel = this.r;
        if (watchFaceDiyViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel = null;
        }
        WatchFaceBean userSelectedWatchFace = watchFaceDiyViewModel.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace);
        String uid = userSelectedWatchFace.getUid();
        WatchFaceDiyViewModel watchFaceDiyViewModel2 = this.r;
        if (watchFaceDiyViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel2 = null;
        }
        WatchFaceBean userSelectedWatchFace2 = watchFaceDiyViewModel2.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace2);
        String faceId = userSelectedWatchFace2.getFaceId();
        WatchFaceDiyViewModel watchFaceDiyViewModel3 = this.r;
        if (watchFaceDiyViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel3 = null;
        }
        WatchFaceBean userSelectedWatchFace3 = watchFaceDiyViewModel3.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace3);
        String faceType = userSelectedWatchFace3.getFaceType();
        WatchFaceDiyViewModel watchFaceDiyViewModel4 = this.r;
        if (watchFaceDiyViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel4 = null;
        }
        WatchFaceBean userSelectedWatchFace4 = watchFaceDiyViewModel4.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace4);
        String title = userSelectedWatchFace4.getTitle();
        WatchFaceDiyViewModel watchFaceDiyViewModel5 = this.r;
        if (watchFaceDiyViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel5 = null;
        }
        WatchFaceBean userSelectedWatchFace5 = watchFaceDiyViewModel5.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace5);
        String downloadUrl = userSelectedWatchFace5.getDownloadUrl();
        WatchFaceDiyViewModel watchFaceDiyViewModel6 = this.r;
        if (watchFaceDiyViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel6 = null;
        }
        WatchFaceBean userSelectedWatchFace6 = watchFaceDiyViewModel6.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace6);
        String fileType = userSelectedWatchFace6.getFileType();
        WatchFaceDiyViewModel watchFaceDiyViewModel7 = this.r;
        if (watchFaceDiyViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel7 = null;
        }
        WatchFaceBean userSelectedWatchFace7 = watchFaceDiyViewModel7.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace7);
        String fileMd5Hash = userSelectedWatchFace7.getFileMd5Hash();
        WatchFaceDiyViewModel watchFaceDiyViewModel8 = this.r;
        if (watchFaceDiyViewModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel8 = null;
        }
        WatchFaceBean userSelectedWatchFace8 = watchFaceDiyViewModel8.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace8);
        String previewImageUrl = userSelectedWatchFace8.getPreviewImageUrl();
        WatchFaceDiyViewModel watchFaceDiyViewModel9 = this.r;
        if (watchFaceDiyViewModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel9 = null;
        }
        WatchFaceBean userSelectedWatchFace9 = watchFaceDiyViewModel9.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace9);
        List<String> tags = userSelectedWatchFace9.getTags();
        WatchFaceDiyViewModel watchFaceDiyViewModel10 = this.r;
        if (watchFaceDiyViewModel10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel10 = null;
        }
        WatchFaceBean userSelectedWatchFace10 = watchFaceDiyViewModel10.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace10);
        Integer faceResId = userSelectedWatchFace10.getFaceResId();
        WatchFaceDiyViewModel watchFaceDiyViewModel11 = this.r;
        if (watchFaceDiyViewModel11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel11 = null;
        }
        WatchFaceBean userSelectedWatchFace11 = watchFaceDiyViewModel11.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace11);
        WatchFaceBean watchFaceBean = new WatchFaceBean(uid, faceId, faceType, title, downloadUrl, fileType, fileMd5Hash, previewImageUrl, tags, null, faceResId, userSelectedWatchFace11.getFacePosition(), null, false, null, 29184, null);
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        companion.getInstance(this).saveSelectedDiyWatchFace(watchFaceBean);
        companion.getInstance(this).saveSelectedCloudWatchFace(null);
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.s;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.callDeviceSpecificSettingsApiDiy();
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnFailureListener
    public void onFailure(@Nullable final String str) {
        if (isFinishing()) {
            return;
        }
        dismissProgress();
        if (str != null) {
            if (str.length() > 0) {
                runOnUiThread(new Runnable() { // from class: com.coveiot.android.watchfaceui.webSupport.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityInAppWebViewerWatchface.W(ActivityInAppWebViewerWatchface.this, str);
                    }
                });
            }
        }
        finish();
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onLayoutWatchFaceSuccess(boolean z) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        ActivityWebViewerViewModel activityWebViewerViewModel = this.q;
        ActivityWebViewerViewModel activityWebViewerViewModel2 = null;
        if (activityWebViewerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            activityWebViewerViewModel = null;
        }
        if (activityWebViewerViewModel.getReq() != 351) {
            super.onRequestPermissionsResult(i, permissions, grantResults);
            return;
        }
        if (!(grantResults.length == 0)) {
            String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.CAMERA"});
            Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…                        )");
            if (!(checkPermissionsHasGranted.length == 0)) {
                Y();
                return;
            }
            ActivityWebViewerViewModel activityWebViewerViewModel3 = this.q;
            if (activityWebViewerViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            } else {
                activityWebViewerViewModel2 = activityWebViewerViewModel3;
            }
            activityWebViewerViewModel2.setPhotoURI(Utils.INSTANCE.takeCameraPictureFromActivity(this));
        }
    }

    @Override // com.coveiot.android.watchfaceui.webSupport.JSInterface.OnPickerResult
    public void onResult(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            String string = jSONObject.getString("reqType");
            int i = jSONObject.getInt("reqVer");
            String string2 = jSONObject.getString("reqId");
            JSONArray jSONArray = jSONObject.getJSONArray("options");
            JSONObject jSONObject2 = jSONObject.getJSONObject("selected");
            String option = jSONObject2.getString("option");
            int i2 = jSONObject2.getInt(FirebaseAnalytics.Param.INDEX);
            String string3 = jSONObject.getString("type");
            ArrayList<String> arrayList = new ArrayList<>();
            int length = jSONArray.length();
            for (int i3 = 0; i3 < length; i3++) {
                arrayList.add(jSONArray.get(i3).toString());
                LogHelper.d("arraylist :", String.valueOf(i3));
            }
            PickerDialog.Companion companion = PickerDialog.Companion;
            Intrinsics.checkNotNullExpressionValue(option, "option");
            companion.watchFaceWebViewPicker(this, arrayList, i2, option, new ActivityInAppWebViewerWatchface$onResult$1(this, string, i, string2, string3));
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.CAMERA"});
        Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…          )\n            )");
        if (checkPermissionsHasGranted.length == 0) {
            ActivityWebViewerViewModel activityWebViewerViewModel = this.q;
            if (activityWebViewerViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                activityWebViewerViewModel = null;
            }
            PermissionRequest permissionRequest = activityWebViewerViewModel.getPermissionRequest();
            if (permissionRequest != null) {
                ActivityWebViewerViewModel activityWebViewerViewModel2 = this.q;
                if (activityWebViewerViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                    activityWebViewerViewModel2 = null;
                }
                PermissionRequest permissionRequest2 = activityWebViewerViewModel2.getPermissionRequest();
                permissionRequest.grant(permissionRequest2 != null ? permissionRequest2.getResources() : null);
            }
            ActivityWebViewerViewModel activityWebViewerViewModel3 = this.q;
            if (activityWebViewerViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                activityWebViewerViewModel3 = null;
            }
            activityWebViewerViewModel3.setPermissionRequest(null);
        }
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onSuccess(boolean z) {
        if (isFinishing()) {
            return;
        }
        showSuccessDialog();
    }

    @Nullable
    public final Bitmap rotateImage(@NotNull Bitmap source, float f) {
        Intrinsics.checkNotNullParameter(source, "source");
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public final void setBackButtonIV(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.backButtonIV = textView;
    }

    public final void setMWebView(@NotNull WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<set-?>");
        this.mWebView = webView;
    }

    public final void showSuccessDialog() {
        Drawable drawable = getResources().getDrawable(R.drawable.success_image_new_img);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…le.success_image_new_img)");
        String string = getString(R.string.success_applied);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_applied)");
        final BottomSheetDialogImageTitleMessageWatchFace bottomSheetDialogImageTitleMessageWatchFace = new BottomSheetDialogImageTitleMessageWatchFace(this, drawable, string, "");
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogImageTitleMessageWatchFace.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.webSupport.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityInAppWebViewerWatchface.d0(BottomSheetDialogImageTitleMessageWatchFace.this, this, view);
            }
        });
        bottomSheetDialogImageTitleMessageWatchFace.setCancelable(false);
        bottomSheetDialogImageTitleMessageWatchFace.show();
    }

    public final void takePhoto() {
        ActivityWebViewerViewModel activityWebViewerViewModel = this.q;
        ActivityWebViewerViewModel activityWebViewerViewModel2 = null;
        if (activityWebViewerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            activityWebViewerViewModel = null;
        }
        activityWebViewerViewModel.setReq(351);
        ActivityWebViewerViewModel activityWebViewerViewModel3 = this.q;
        if (activityWebViewerViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            activityWebViewerViewModel3 = null;
        }
        activityWebViewerViewModel3.setPermission_array(PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.CAMERA"}));
        ActivityWebViewerViewModel activityWebViewerViewModel4 = this.q;
        if (activityWebViewerViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
            activityWebViewerViewModel4 = null;
        }
        String[] permission_array = activityWebViewerViewModel4.getPermission_array();
        Intrinsics.checkNotNull(permission_array);
        if (!(permission_array.length == 0)) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
                ActivityWebViewerViewModel activityWebViewerViewModel5 = this.q;
                if (activityWebViewerViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                    activityWebViewerViewModel5 = null;
                }
                String[] permission_array2 = activityWebViewerViewModel5.getPermission_array();
                Intrinsics.checkNotNull(permission_array2);
                ActivityWebViewerViewModel activityWebViewerViewModel6 = this.q;
                if (activityWebViewerViewModel6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
                } else {
                    activityWebViewerViewModel2 = activityWebViewerViewModel6;
                }
                ActivityCompat.requestPermissions(this, permission_array2, activityWebViewerViewModel2.getReq());
                return;
            }
            Y();
            String string = getString(R.string.camera_permission_required);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.camera_permission_required)");
            b0(string);
            return;
        }
        ActivityWebViewerViewModel activityWebViewerViewModel7 = this.q;
        if (activityWebViewerViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWebViewerViewModel");
        } else {
            activityWebViewerViewModel2 = activityWebViewerViewModel7;
        }
        activityWebViewerViewModel2.setPhotoURI(Utils.INSTANCE.takeCameraPictureFromActivity(this));
    }
}
