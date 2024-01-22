package com.coveiot.android.leonardo.more.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityContactUsFeedbackBinding;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.more.dialogs.FeedBackSuccessDialog;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityReportIssueViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.websupport.CoveJsInterface;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.actions.SearchIntents;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityContactusFeedback extends BaseActivity implements ViewModelListener, CoveJsInterface.OnJavascriptListener, FeedBackSuccessDialog.OnClickListener {
    public CoveJsInterface p;
    public ActivityContactUsFeedbackBinding q;
    public List<String> query;
    public boolean t;
    @Nullable
    public String u;
    public boolean v;
    public ActivityReportIssueViewModel viewModel;
    @Nullable
    public FeedBackSuccessDialog w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String r = ActivityContactusFeedback.class.getSimpleName();
    public boolean s = true;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<String[], Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(String[] strArr) {
            invoke2(strArr);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(String[] strArr) {
            if (strArr != null) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(ActivityContactusFeedback.this, (int) R.layout.spinner_text2, strArr);
                arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
                ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding = ActivityContactusFeedback.this.q;
                if (activityContactUsFeedbackBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactUsFeedbackBinding = null;
                }
                activityContactUsFeedbackBinding.selectQuerySpinner.setAdapter((SpinnerAdapter) arrayAdapter);
                if (ActivityContactusFeedback.this.v) {
                    return;
                }
                ActivityContactusFeedback.this.setQuery(ArraysKt___ArraysKt.toList(strArr));
            }
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<String[], Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(String[] strArr) {
            invoke2(strArr);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(String[] strArr) {
            if (strArr == null || !ActivityContactusFeedback.this.v) {
                return;
            }
            ActivityContactusFeedback.this.setQuery(ArraysKt___ArraysKt.toList(strArr));
        }
    }

    /* loaded from: classes5.dex */
    public static final class c extends Lambda implements Function1<Boolean, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean it) {
            ActivityContactusFeedback activityContactusFeedback = ActivityContactusFeedback.this;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            activityContactusFeedback.v = it.booleanValue();
            ActivityContactusFeedback activityContactusFeedback2 = ActivityContactusFeedback.this;
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding = activityContactusFeedback2.q;
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding2 = null;
            if (activityContactUsFeedbackBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding = null;
            }
            ConstraintLayout constraintLayout = activityContactUsFeedbackBinding.PreferedContactLayout;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.PreferedContactLayout");
            activityContactusFeedback2.visibleIf(constraintLayout, ActivityContactusFeedback.this.v);
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding3 = ActivityContactusFeedback.this.q;
            if (activityContactUsFeedbackBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding3 = null;
            }
            activityContactUsFeedbackBinding3.name.setEnabled(ActivityContactusFeedback.this.v);
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding4 = ActivityContactusFeedback.this.q;
            if (activityContactUsFeedbackBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding4 = null;
            }
            activityContactUsFeedbackBinding4.userEmail.setEnabled(ActivityContactusFeedback.this.v);
            if (ActivityContactusFeedback.this.v) {
                return;
            }
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding5 = ActivityContactusFeedback.this.q;
            if (activityContactUsFeedbackBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding5 = null;
            }
            activityContactUsFeedbackBinding5.ConstraintLayout3.setBackgroundResource(R.drawable.rounded_bg_1f1f20_10dp_radius);
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding6 = ActivityContactusFeedback.this.q;
            if (activityContactUsFeedbackBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding6 = null;
            }
            activityContactUsFeedbackBinding6.name.setTextColor(ContextCompat.getColor(ActivityContactusFeedback.this, R.color.color_666666));
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding7 = ActivityContactusFeedback.this.q;
            if (activityContactUsFeedbackBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding7 = null;
            }
            activityContactUsFeedbackBinding7.ConstraintLayoutEmail.setBackgroundResource(R.drawable.rounded_bg_1f1f20_10dp_radius);
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding8 = ActivityContactusFeedback.this.q;
            if (activityContactUsFeedbackBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityContactUsFeedbackBinding2 = activityContactUsFeedbackBinding8;
            }
            activityContactUsFeedbackBinding2.userEmail.setTextColor(ContextCompat.getColor(ActivityContactusFeedback.this, R.color.color_666666));
        }
    }

    public static final void A(ActivityContactusFeedback this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.r;
        LogHelper.d(str2, "onReceiveValue: " + str);
    }

    public static final void C(ActivityContactusFeedback this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
        this$0.setResult(0);
    }

    public static final void D(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void E(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void F(ActivityContactusFeedback this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.L();
    }

    public static final void G(ActivityContactusFeedback this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding = this$0.q;
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding2 = null;
            if (activityContactUsFeedbackBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding = null;
            }
            String obj = StringsKt__StringsKt.trim(activityContactUsFeedbackBinding.name.getText().toString()).toString();
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding3 = this$0.q;
            if (activityContactUsFeedbackBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding3 = null;
            }
            String obj2 = StringsKt__StringsKt.trim(activityContactUsFeedbackBinding3.PhnNumber.getText().toString()).toString();
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding4 = this$0.q;
            if (activityContactUsFeedbackBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding4 = null;
            }
            String obj3 = StringsKt__StringsKt.trim(activityContactUsFeedbackBinding4.userEmail.getText().toString()).toString();
            if (AppUtils.isEmpty(obj3)) {
                obj3 = AppConstants.NOT_AVAILABLE_EMAIL.getValue();
            }
            String str = obj3;
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding5 = this$0.q;
            if (activityContactUsFeedbackBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding5 = null;
            }
            String obj4 = StringsKt__StringsKt.trim(activityContactUsFeedbackBinding5.edittext.getText().toString()).toString();
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding6 = this$0.q;
            if (activityContactUsFeedbackBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding6 = null;
            }
            String str2 = activityContactUsFeedbackBinding6.viaPhoneNumber.isChecked() ? "PHONE_CALL" : "EMAIL";
            List<String> query = this$0.getQuery();
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding7 = this$0.q;
            if (activityContactUsFeedbackBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding7 = null;
            }
            String str3 = query.get(activityContactUsFeedbackBinding7.selectQuerySpinner.getSelectedItemPosition());
            if (this$0.N()) {
                if (this$0.v) {
                    this$0.showProgress();
                    this$0.getViewModel().onCallFeedbackApi(str3, str, obj4, obj, str2);
                } else {
                    this$0.H();
                }
            } else {
                ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding8 = this$0.q;
                if (activityContactUsFeedbackBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactUsFeedbackBinding8 = null;
                }
                activityContactUsFeedbackBinding8.userEmail.requestFocus();
                ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding9 = this$0.q;
                if (activityContactUsFeedbackBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactUsFeedbackBinding9 = null;
                }
                activityContactUsFeedbackBinding9.userEmail.setError(this$0.getString(R.string.please_enter_a_correct_email_id));
            }
            if (this$0.u != null) {
                try {
                    AnalyticsLog analyticsLog = new AnalyticsLog();
                    analyticsLog.setEventName(FirebaseEventParams.EventName.CV_TROUBLESHOOT_FEEDBACK.getValue());
                    String str4 = this$0.u;
                    Intrinsics.checkNotNull(str4);
                    String lowerCase = str4.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    analyticsLog.setPreviousScreenName(lowerCase.toString());
                    analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.CONTACT_US.getValue());
                    HashMap<String, String> hashMap = new HashMap<>();
                    ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding10 = this$0.q;
                    if (activityContactUsFeedbackBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityContactUsFeedbackBinding2 = activityContactUsFeedbackBinding10;
                    }
                    String str5 = activityContactUsFeedbackBinding2.viaPhoneNumber.isChecked() ? "phone_number" : "email";
                    hashMap.put(FirebaseEventParams.MetaData.CV_CUST_NAME.getValue(), obj);
                    hashMap.put(FirebaseEventParams.MetaData.CV_CUST_PH_NUM.getValue(), obj2);
                    hashMap.put(FirebaseEventParams.MetaData.CV_CUST_EMAIL.getValue(), str);
                    hashMap.put(FirebaseEventParams.MetaData.CV_CUST_QUERY_TYPE.getValue(), FirebaseEventParams.ScreenName.TROUBlESHOOTING.getValue());
                    hashMap.put(FirebaseEventParams.MetaData.CV_CUST_MESSAGE.getValue(), obj4);
                    hashMap.put(FirebaseEventParams.MetaData.CV_CUST_MODE_CON.getValue(), str5);
                    analyticsLog.setMapData(hashMap);
                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
        Toast.makeText(this$0, (int) R.string.noconnection, 0).show();
    }

    public static final void J(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void M(BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        bottomSheetDialogImageTitleMessage.dismiss();
    }

    public static final void z(final ActivityContactusFeedback this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.r;
        StringBuilder sb = new StringBuilder();
        sb.append("appOutScript: ");
        Intrinsics.checkNotNull(str);
        sb.append(str);
        LogHelper.d(str2, sb.toString());
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding = this$0.q;
        if (activityContactUsFeedbackBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding = null;
        }
        activityContactUsFeedbackBinding.contactUsWebView.evaluateJavascript(str, new ValueCallback() { // from class: com.coveiot.android.leonardo.more.activities.g5
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityContactusFeedback.A(ActivityContactusFeedback.this, (String) obj);
            }
        });
    }

    public final String B() {
        String firmwareRevision;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.dear_team));
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.i_have_concerns_here_based_on);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.i_have_concerns_here_based_on)");
        Object[] objArr = new Object[1];
        List<String> query = getQuery();
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding = this.q;
        String str = null;
        if (activityContactUsFeedbackBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding = null;
        }
        objArr[0] = query.get(activityContactUsFeedbackBinding.selectQuerySpinner.getSelectedItemPosition());
        String format = String.format(string, Arrays.copyOf(objArr, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        sb.append(format);
        StringBuilder sb2 = new StringBuilder();
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding2 = this.q;
        if (activityContactUsFeedbackBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding2 = null;
        }
        Editable text = activityContactUsFeedbackBinding2.edittext.getText();
        Intrinsics.checkNotNullExpressionValue(text, "binding.edittext.text");
        sb2.append((Object) StringsKt__StringsKt.trim(text));
        sb2.append("\n\n\n");
        sb.append(sb2.toString());
        sb.append(getString(R.string.dashed_line));
        String string2 = getString(R.string.mob);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.mob)");
        String format2 = String.format(string2, Arrays.copyOf(new Object[]{SessionManager.getInstance(this).getUserDetails().getMobileNumber()}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        sb.append(format2);
        String string3 = getString(R.string.name_dash);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.name_dash)");
        String format3 = String.format(string3, Arrays.copyOf(new Object[]{SessionManager.getInstance(this).getUserDetails().getName()}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(format, *args)");
        sb.append(format3);
        sb.append(getString(R.string.watch_details));
        String string4 = getString(R.string.watch_model);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.watch_model)");
        String format4 = String.format(string4, Arrays.copyOf(new Object[]{DeviceUtils.Companion.getWatchName(this)}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(format, *args)");
        sb.append(format4);
        String string5 = getString(R.string.watch_fw);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.watch_fw)");
        Object[] objArr2 = new Object[1];
        BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
        if (bleDeviceInfo != null && (firmwareRevision = bleDeviceInfo.getFirmwareRevision()) != null) {
            Locale ROOT = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
            str = firmwareRevision.toLowerCase(ROOT);
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(locale)");
        }
        objArr2[0] = str;
        String format5 = String.format(string5, Arrays.copyOf(objArr2, 1));
        Intrinsics.checkNotNullExpressionValue(format5, "format(format, *args)");
        sb.append(format5);
        sb.append(getString(R.string.app_details));
        String string6 = getString(R.string.boat_crest_app_ver);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.boat_crest_app_ver)");
        String format6 = String.format(string6, Arrays.copyOf(new Object[]{SessionManager.getInstance(this).getAppVersionName()}, 1));
        Intrinsics.checkNotNullExpressionValue(format6, "format(format, *args)");
        sb.append(format6);
        String string7 = getString(R.string.android_os);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.android_os)");
        String format7 = String.format(string7, Arrays.copyOf(new Object[]{Build.VERSION.RELEASE}, 1));
        Intrinsics.checkNotNullExpressionValue(format7, "format(format, *args)");
        sb.append(format7);
        String string8 = getString(R.string.phone_make_model);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.phone_make_model)");
        String format8 = String.format(string8, Arrays.copyOf(new Object[]{Build.MODEL}, 1));
        Intrinsics.checkNotNullExpressionValue(format8, "format(format, *args)");
        sb.append(format8);
        String sb3 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb3, "StringBuilder().apply(builderAction).toString()");
        return sb3;
    }

    public final void H() {
        Intent intent = new Intent("android.intent.action.VIEW");
        StringBuilder sb = new StringBuilder();
        sb.append("mailto:customer.support@coveiot.com?subject=");
        List<String> query = getQuery();
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding = this.q;
        if (activityContactUsFeedbackBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding = null;
        }
        sb.append(Uri.encode(query.get(activityContactUsFeedbackBinding.selectQuerySpinner.getSelectedItemPosition())));
        sb.append("&body=");
        sb.append(Uri.encode(B()));
        intent.setData(Uri.parse(sb.toString()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, getString(R.string.sending_feedback)));
            finish();
            return;
        }
        showErrorDialog();
    }

    public final void I() {
        MutableLiveData<Boolean> preferredContactVisibility = getViewModel().getPreferredContactVisibility();
        final c cVar = new c();
        preferredContactVisibility.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.i5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityContactusFeedback.J(Function1.this, obj);
            }
        });
    }

    public final void K() {
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding = this.q;
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding2 = null;
        if (activityContactUsFeedbackBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding = null;
        }
        boolean z = !AppUtils.isEmpty(activityContactUsFeedbackBinding.userEmail.getText().toString());
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding3 = this.q;
        if (activityContactUsFeedbackBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding3 = null;
        }
        activityContactUsFeedbackBinding3.viaEmail.setAlpha(z ? 1.0f : 0.5f);
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding4 = this.q;
        if (activityContactUsFeedbackBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding4 = null;
        }
        activityContactUsFeedbackBinding4.tvEmailWarning.setVisibility(z ? 8 : 0);
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding5 = this.q;
        if (activityContactUsFeedbackBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding5 = null;
        }
        activityContactUsFeedbackBinding5.viaEmail.setEnabled(z);
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding6 = this.q;
        if (activityContactUsFeedbackBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding6 = null;
        }
        activityContactUsFeedbackBinding6.viaEmail.setClickable(z);
        if (z) {
            return;
        }
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding7 = this.q;
        if (activityContactUsFeedbackBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding7 = null;
        }
        activityContactUsFeedbackBinding7.viaEmail.setChecked(false);
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding8 = this.q;
        if (activityContactUsFeedbackBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityContactUsFeedbackBinding2 = activityContactUsFeedbackBinding8;
        }
        activityContactUsFeedbackBinding2.viaPhoneNumber.setChecked(true);
    }

    public final void L() {
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding = this.q;
        if (activityContactUsFeedbackBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding = null;
        }
        activityContactUsFeedbackBinding.submit.setEnabled(O());
    }

    public final boolean N() {
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding = this.q;
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding2 = null;
        if (activityContactUsFeedbackBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding = null;
        }
        if (activityContactUsFeedbackBinding.viaEmail.isChecked()) {
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding3 = this.q;
            if (activityContactUsFeedbackBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityContactUsFeedbackBinding2 = activityContactUsFeedbackBinding3;
            }
            return AppUtils.isValidEmail(activityContactUsFeedbackBinding2.userEmail.getText().toString());
        }
        return true;
    }

    public final boolean O() {
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding = this.q;
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding2 = null;
        if (activityContactUsFeedbackBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding = null;
        }
        if (AppUtils.isEmpty(activityContactUsFeedbackBinding.name.getText().toString())) {
            return false;
        }
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding3 = this.q;
        if (activityContactUsFeedbackBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding3 = null;
        }
        if (AppUtils.isEmpty(activityContactUsFeedbackBinding3.edittext.getText().toString())) {
            return false;
        }
        if (this.v) {
            return true;
        }
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding4 = this.q;
        if (activityContactUsFeedbackBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityContactUsFeedbackBinding2 = activityContactUsFeedbackBinding4;
        }
        return !AppUtils.isEmpty(activityContactUsFeedbackBinding2.userEmail.getText().toString());
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

    @Override // com.coveiot.android.leonardo.websupport.CoveJsInterface.OnJavascriptListener
    public void evaluateJavascript(@Nullable final String str) {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.l5
            @Override // java.lang.Runnable
            public final void run() {
                ActivityContactusFeedback.z(ActivityContactusFeedback.this, str);
            }
        });
    }

    @Nullable
    public final FeedBackSuccessDialog getFeedBackSuccessDialog() {
        return this.w;
    }

    public final boolean getLoadingFinished() {
        return this.s;
    }

    @Nullable
    public final String getPreviousScreenName() {
        return this.u;
    }

    @NotNull
    public final List<String> getQuery() {
        List<String> list = this.query;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException(SearchIntents.EXTRA_QUERY);
        return null;
    }

    public final boolean getRedirect() {
        return this.t;
    }

    @NotNull
    public final ActivityReportIssueViewModel getViewModel() {
        ActivityReportIssueViewModel activityReportIssueViewModel = this.viewModel;
        if (activityReportIssueViewModel != null) {
            return activityReportIssueViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityContactUsFeedbackBinding inflate = ActivityContactUsFeedbackBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.q = inflate;
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("extra_key")) {
            this.u = intent.getStringExtra("extra_key");
        }
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.contact_us));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.d5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityContactusFeedback.C(ActivityContactusFeedback.this, view);
            }
        });
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding2 = this.q;
        if (activityContactUsFeedbackBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding2 = null;
        }
        ImageView imageView = activityContactUsFeedbackBinding2.emojiCove;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.emojiCove");
        ThemesUtils.setPoweredByLogoIcon(this, imageView, false);
        setViewModel((ActivityReportIssueViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityReportIssueViewModel.class));
        I();
        getViewModel().setViewModelListener(this);
        getViewModel().getContactUsFirebaseConfig();
        ProfileData userDetails = SessionManager.getInstance(this).getUserDetails();
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding3 = this.q;
        if (activityContactUsFeedbackBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding3 = null;
        }
        activityContactUsFeedbackBinding3.PhnNumber.setText(userDetails.getMobileNumber().toString());
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding4 = this.q;
        if (activityContactUsFeedbackBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding4 = null;
        }
        activityContactUsFeedbackBinding4.name.setText(userDetails.getName());
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding5 = this.q;
        if (activityContactUsFeedbackBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding5 = null;
        }
        activityContactUsFeedbackBinding5.name.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityContactusFeedback$onCreate$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                ActivityContactusFeedback.this.L();
            }
        });
        if (!AppUtils.isEmpty(userDetails.getEmail())) {
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding6 = this.q;
            if (activityContactUsFeedbackBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding6 = null;
            }
            activityContactUsFeedbackBinding6.userEmail.setText(userDetails.getEmail());
        }
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding7 = this.q;
        if (activityContactUsFeedbackBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding7 = null;
        }
        activityContactUsFeedbackBinding7.userEmail.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityContactusFeedback$onCreate$3
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                ActivityContactusFeedback.this.L();
                ActivityContactusFeedback.this.K();
            }
        });
        MutableLiveData<String[]> mSpinnerDataForUI = getViewModel().getMSpinnerDataForUI();
        final a aVar = new a();
        mSpinnerDataForUI.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.k5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityContactusFeedback.D(Function1.this, obj);
            }
        });
        MutableLiveData<String[]> mSpinnerData = getViewModel().getMSpinnerData();
        final b bVar = new b();
        mSpinnerData.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.j5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityContactusFeedback.E(Function1.this, obj);
            }
        });
        getViewModel().onCallCategoryApi();
        this.p = new CoveJsInterface(this, this);
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding8 = this.q;
        if (activityContactUsFeedbackBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding8 = null;
        }
        WebView webView = activityContactUsFeedbackBinding8.contactUsWebView;
        CoveJsInterface coveJsInterface = this.p;
        if (coveJsInterface == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coveJsInterface");
            coveJsInterface = null;
        }
        webView.addJavascriptInterface(coveJsInterface, "CoveJsInterface");
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding9 = this.q;
        if (activityContactUsFeedbackBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding9 = null;
        }
        activityContactUsFeedbackBinding9.contactUsWebView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        String value = AppConstants.SUPPORT_URL.getValue();
        if (!AppUtils.isEmpty(value)) {
            String deviceAgent = PreferenceManager.getInstance().getDeviceAgent();
            if (!AppUtils.isEmpty(deviceAgent)) {
                value = value + "?deviceAgent=" + deviceAgent;
            }
            LogHelper.d(this.r, "WEBVIEW URL: " + value);
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding10 = this.q;
            if (activityContactUsFeedbackBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding10 = null;
            }
            WebSettings settings = activityContactUsFeedbackBinding10.contactUsWebView.getSettings();
            Intrinsics.checkNotNullExpressionValue(settings, "binding.contactUsWebView.settings");
            settings.setJavaScriptEnabled(true);
            String string = getString(R.string.loading2);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.loading2)");
            showProgressWithTitle(string);
            settings.setMixedContentMode(0);
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(true);
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding11 = this.q;
            if (activityContactUsFeedbackBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding11 = null;
            }
            activityContactUsFeedbackBinding11.contactUsWebView.setWebViewClient(new WebViewClient() { // from class: com.coveiot.android.leonardo.more.activities.ActivityContactusFeedback$onCreate$6
                @Override // android.webkit.WebViewClient
                public void onPageFinished(@NotNull WebView view, @NotNull String url) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(url, "url");
                    if (!ActivityContactusFeedback.this.getRedirect()) {
                        ActivityContactusFeedback.this.setLoadingFinished(true);
                    }
                    if (!ActivityContactusFeedback.this.getLoadingFinished() || ActivityContactusFeedback.this.getRedirect()) {
                        ActivityContactusFeedback.this.setRedirect(false);
                    }
                    ActivityContactusFeedback.this.dismissProgress();
                }

                @Override // android.webkit.WebViewClient
                public void onPageStarted(@Nullable WebView webView2, @Nullable String str, @Nullable Bitmap bitmap) {
                    ActivityContactusFeedback.this.setLoadingFinished(false);
                }

                @Override // android.webkit.WebViewClient
                public void onReceivedError(@NotNull WebView view, int i, @NotNull String description, @NotNull String failingUrl) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(description, "description");
                    Intrinsics.checkNotNullParameter(failingUrl, "failingUrl");
                    ActivityContactusFeedback.this.dismissProgress();
                }

                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull String url) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(url, "url");
                    if (!ActivityContactusFeedback.this.getLoadingFinished()) {
                        ActivityContactusFeedback.this.setRedirect(true);
                    }
                    ActivityContactusFeedback.this.setLoadingFinished(false);
                    view.loadUrl(url);
                    return true;
                }
            });
            ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding12 = this.q;
            if (activityContactUsFeedbackBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactUsFeedbackBinding12 = null;
            }
            WebView webView2 = activityContactUsFeedbackBinding12.contactUsWebView;
            Intrinsics.checkNotNull(value);
            webView2.loadUrl(value);
        }
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding13 = this.q;
        if (activityContactUsFeedbackBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding13 = null;
        }
        activityContactUsFeedbackBinding13.edittext.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityContactusFeedback$onCreate$7
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                int length = charSequence != null ? charSequence.length() : 0;
                ActivityContactusFeedback.this.L();
                ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding14 = ActivityContactusFeedback.this.q;
                if (activityContactUsFeedbackBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactUsFeedbackBinding14 = null;
                }
                TextView textView = activityContactUsFeedbackBinding14.Mailnumber;
                textView.setText(length + "/500");
            }
        });
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding14 = this.q;
        if (activityContactUsFeedbackBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactUsFeedbackBinding14 = null;
        }
        activityContactUsFeedbackBinding14.RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.h5
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                ActivityContactusFeedback.F(ActivityContactusFeedback.this, radioGroup, i);
            }
        });
        ActivityContactUsFeedbackBinding activityContactUsFeedbackBinding15 = this.q;
        if (activityContactUsFeedbackBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityContactUsFeedbackBinding = activityContactUsFeedbackBinding15;
        }
        activityContactUsFeedbackBinding.submit.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.e5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityContactusFeedback.G(ActivityContactusFeedback.this, view);
            }
        });
        L();
        K();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        dismissProgress();
        Toast.makeText(this, message, 0).show();
    }

    @Override // com.coveiot.android.leonardo.more.dialogs.FeedBackSuccessDialog.OnClickListener
    public void onDoneClicked() {
        if (isFinishing()) {
            return;
        }
        setResult(-1);
        finish();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
        dismissProgress();
        showFeedbackSuccess();
    }

    @Override // com.coveiot.android.leonardo.websupport.CoveJsInterface.OnJavascriptListener
    public void openLinkInBrowser(@Nullable String str) {
        if (str != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            startActivity(intent);
        }
    }

    public final void setFeedBackSuccessDialog(@Nullable FeedBackSuccessDialog feedBackSuccessDialog) {
        this.w = feedBackSuccessDialog;
    }

    public final void setLoadingFinished(boolean z) {
        this.s = z;
    }

    public final void setPreviousScreenName(@Nullable String str) {
        this.u = str;
    }

    public final void setQuery(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.query = list;
    }

    public final void setRedirect(boolean z) {
        this.t = z;
    }

    public final void setViewModel(@NotNull ActivityReportIssueViewModel activityReportIssueViewModel) {
        Intrinsics.checkNotNullParameter(activityReportIssueViewModel, "<set-?>");
        this.viewModel = activityReportIssueViewModel;
    }

    public final void showErrorDialog() {
        Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.info_icon_new)");
        String string = getString(R.string.unable_to_process_your_request_at_the_moment);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unabl…ur_request_at_the_moment)");
        String string2 = getString(R.string.please_send_an_email_to);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_send_an_email_to)");
        final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, string, string2, true);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.leaderboard.R.string.ok)");
        bottomSheetDialogImageTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.f5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityContactusFeedback.M(BottomSheetDialogImageTitleMessage.this, view);
            }
        });
        bottomSheetDialogImageTitleMessage.show();
    }

    public final void showFeedbackSuccess() {
        try {
            FeedBackSuccessDialog feedBackSuccessDialog = new FeedBackSuccessDialog();
            this.w = feedBackSuccessDialog;
            Intrinsics.checkNotNull(feedBackSuccessDialog);
            feedBackSuccessDialog.setCancelable(false);
            FeedBackSuccessDialog feedBackSuccessDialog2 = this.w;
            Intrinsics.checkNotNull(feedBackSuccessDialog2);
            feedBackSuccessDialog2.setOnClickListener(this);
            FeedBackSuccessDialog feedBackSuccessDialog3 = this.w;
            Intrinsics.checkNotNull(feedBackSuccessDialog3);
            feedBackSuccessDialog3.show(getSupportFragmentManager(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
