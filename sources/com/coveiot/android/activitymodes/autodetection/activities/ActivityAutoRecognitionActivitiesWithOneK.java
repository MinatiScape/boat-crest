package com.coveiot.android.activitymodes.autodetection.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.OneKActivity;
import com.coveiot.android.activitymodes.activity1k.ResponseListener;
import com.coveiot.android.activitymodes.autodetection.adapter.AutoRecognitionAdapterOneK;
import com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK;
import com.coveiot.android.activitymodes.models.AutoRecognitonModelOneK;
import com.coveiot.android.activitymodes.utils.DialogListener;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AutoActivityDetectionData;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityAutoRecognitionActivitiesWithOneK extends BaseActivity implements DialogListener, AutoRecognitionAdapterOneK.OnItemClickListener {
    public TextView p;
    public ActivityAutoRecognitionViewModelWithOneK q;
    public boolean t;
    @Nullable
    public View u;
    @Nullable
    public AlertDialog v;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<AutoRecognitonModelOneK> r = new ArrayList<>();
    @NotNull
    public ArrayList<AutoRecognitonModelOneK> s = new ArrayList<>();

    public static final void B(ActivityAutoRecognitionActivitiesWithOneK this$0, View view) {
        Object obj;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isBluetoothEnabled(this$0)) {
            if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                if (AppUtils.isNetConnected(this$0)) {
                    this$0.showProgress();
                    AutoActivityDetectionData autoActivityDetectionDataL = UserDataManager.getInstance(this$0).getAutoActivityDetectionData();
                    ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = null;
                    if (autoActivityDetectionDataL == null) {
                        ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK2 = this$0.q;
                        if (activityAutoRecognitionViewModelWithOneK2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            activityAutoRecognitionViewModelWithOneK2 = null;
                        }
                        autoActivityDetectionDataL = activityAutoRecognitionViewModelWithOneK2.getDefaultAutoActivityDetectionData();
                    }
                    autoActivityDetectionDataL.setActivities(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                    int i = 0;
                    for (Object obj2 : this$0.r) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                        }
                        AutoRecognitonModelOneK autoRecognitonModelOneK = (AutoRecognitonModelOneK) obj2;
                        if (autoRecognitonModelOneK.isSelected()) {
                            autoActivityDetectionDataL.getActivities()[autoRecognitonModelOneK.getByteOrderInFW()] = 1;
                        }
                        i = i2;
                    }
                    AnalyticsLog analyticsLog = new AnalyticsLog();
                    analyticsLog.setEventName(FirebaseEventParams.EventName.CV_AUTO_ACTIVITY_SELECT.getValue());
                    DeviceModelBean deviceModelBean = SessionManager.getInstance(this$0).getDeviceModelBean();
                    if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
                        DeviceModelBean deviceModelBean2 = SessionManager.getInstance(this$0).getDeviceModelBean();
                        obj = deviceModelBean2 != null ? deviceModelBean2.getName() : null;
                    } else {
                        obj = Unit.INSTANCE;
                    }
                    analyticsLog.setCVPrevScreenName(obj + "_features");
                    analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.AUTO_ACTIVITY_DETECTION.getValue());
                    for (AutoRecognitonModelOneK autoRecognitonModelOneK2 : this$0.r) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        String activityName = autoRecognitonModelOneK2.getActivityName();
                        if (!(activityName == null || activityName.length() == 0)) {
                            if (autoRecognitonModelOneK2.isSelected()) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(FirebaseEventParams.MetaData.CV_.getValue());
                                String lowerCase = autoRecognitonModelOneK2.getActivityName().toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                                sb.append(lowerCase);
                                hashMap.put(sb.toString(), "enable");
                            } else {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(FirebaseEventParams.MetaData.CV_.getValue());
                                String lowerCase2 = autoRecognitonModelOneK2.getActivityName().toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                                sb2.append(lowerCase2);
                                hashMap.put(sb2.toString(), "disable");
                            }
                        }
                        analyticsLog.setMapData(hashMap);
                    }
                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                    ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK3 = this$0.q;
                    if (activityAutoRecognitionViewModelWithOneK3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        activityAutoRecognitionViewModelWithOneK = activityAutoRecognitionViewModelWithOneK3;
                    }
                    Intrinsics.checkNotNullExpressionValue(autoActivityDetectionDataL, "autoActivityDetectionDataL");
                    activityAutoRecognitionViewModelWithOneK.setAutoRecognitionToWatch(autoActivityDetectionDataL);
                    return;
                }
                Toast.makeText(this$0, this$0.getString(R.string.please_enable_internet), 0).show();
                return;
            }
            Toast.makeText(this$0, this$0.getString(R.string.band_not_connected), 0).show();
            return;
        }
        Toast.makeText(this$0, this$0.getString(R.string.bluetooth_off_message), 0).show();
    }

    public static final void C(ActivityAutoRecognitionActivitiesWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void D(ActivityAutoRecognitionActivitiesWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    public static final void E(ActivityAutoRecognitionActivitiesWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    public static final void F(BottomSheetDialogTwoButtons dialog, ActivityAutoRecognitionActivitiesWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        ((Button) this$0._$_findCachedViewById(R.id.btn_ok)).performClick();
    }

    public static final void G(BottomSheetDialogTwoButtons dialog, ActivityAutoRecognitionActivitiesWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void H(final ActivityAutoRecognitionActivitiesWithOneK this$0, ArrayList arrayList) {
        AutoRecognitonModelOneK copy;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (arrayList != null) {
            this$0.r.addAll(arrayList);
            Iterator<AutoRecognitonModelOneK> it = this$0.r.iterator();
            while (it.hasNext()) {
                AutoRecognitonModelOneK a2 = it.next();
                ArrayList<AutoRecognitonModelOneK> arrayList2 = this$0.s;
                Intrinsics.checkNotNullExpressionValue(a2, "a");
                copy = a2.copy((r20 & 1) != 0 ? a2.f2848a : null, (r20 & 2) != 0 ? a2.b : false, (r20 & 4) != 0 ? a2.c : false, (r20 & 8) != 0 ? a2.d : 0, (r20 & 16) != 0 ? a2.e : 0, (r20 & 32) != 0 ? a2.f : 0, (r20 & 64) != 0 ? a2.g : null, (r20 & 128) != 0 ? a2.h : 0, (r20 & 256) != 0 ? a2.i : null);
                arrayList2.add(copy);
            }
            ((RecyclerView) this$0._$_findCachedViewById(R.id.auto_recog_recyl)).setAdapter(new AutoRecognitionAdapterOneK(this$0, this$0.r, this$0));
            ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = this$0.q;
            ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK2 = null;
            if (activityAutoRecognitionViewModelWithOneK == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                activityAutoRecognitionViewModelWithOneK = null;
            }
            activityAutoRecognitionViewModelWithOneK.getListOfActivityLiveData().removeObservers(this$0);
            ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK3 = this$0.q;
            if (activityAutoRecognitionViewModelWithOneK3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                activityAutoRecognitionViewModelWithOneK2 = activityAutoRecognitionViewModelWithOneK3;
            }
            Intrinsics.checkNotNull(activityAutoRecognitionViewModelWithOneK2);
            activityAutoRecognitionViewModelWithOneK2.getCurrentActivitiesOnWatch(new ResponseListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.ActivityAutoRecognitionActivitiesWithOneK$onCreate$1$1$1
                @Override // com.coveiot.android.activitymodes.activity1k.ResponseListener
                public void onError() {
                    ActivityAutoRecognitionActivitiesWithOneK.this.dismissProgress();
                }

                @Override // com.coveiot.android.activitymodes.activity1k.ResponseListener
                public void onResponse() {
                    ActivityAutoRecognitionActivitiesWithOneK.this.dismissProgress();
                }
            });
        }
    }

    public static final void J(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityAutoRecognitionActivitiesWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void K(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityAutoRecognitionActivitiesWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void M(ActivityAutoRecognitionActivitiesWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, OneKActivity.class));
        AlertDialog alertDialog = this$0.v;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    public final void A() {
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionActivitiesWithOneK.B(ActivityAutoRecognitionActivitiesWithOneK.this, view);
            }
        });
    }

    public final void I() {
        startActivity(new Intent(this, ActivityAutoRecognitionSettingsWithOneK.class));
    }

    public final void L() {
        if (this.v == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
            LayoutInflater layoutInflater = getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "this.layoutInflater");
            View inflate = layoutInflater.inflate(R.layout.dialog_auto_activity_detection_warning, (ViewGroup) null);
            this.u = inflate;
            builder.setView(inflate);
            View view = this.u;
            Intrinsics.checkNotNull(view);
            ((Button) view.findViewById(R.id.okBtn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityAutoRecognitionActivitiesWithOneK.M(ActivityAutoRecognitionActivitiesWithOneK.this, view2);
                }
            });
            AlertDialog create = builder.create();
            this.v = create;
            Intrinsics.checkNotNull(create);
            create.setCancelable(false);
            AlertDialog alertDialog = this.v;
            Intrinsics.checkNotNull(alertDialog);
            alertDialog.setCanceledOnTouchOutside(false);
        }
        AlertDialog alertDialog2 = this.v;
        if (alertDialog2 == null || alertDialog2.isShowing()) {
            return;
        }
        alertDialog2.show();
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

    public final void buttonVisible() {
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(this.t);
    }

    @NotNull
    public final ArrayList<AutoRecognitonModelOneK> getActivityList() {
        return this.r;
    }

    @NotNull
    public final ArrayList<AutoRecognitonModelOneK> getActivityListPref() {
        return this.s;
    }

    public final boolean getBoolSaveVisible() {
        return this.t;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.auto_activity_detection));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionActivitiesWithOneK.C(ActivityAutoRecognitionActivitiesWithOneK.this, view);
            }
        });
        int i = R.id.share_iv;
        ((ImageView) findViewById(i)).setVisibility(8);
        ((ImageView) findViewById(i)).setImageDrawable(getDrawable(R.drawable.ic_setting));
        ((ImageView) findViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionActivitiesWithOneK.D(ActivityAutoRecognitionActivitiesWithOneK.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.manage)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionActivitiesWithOneK.E(ActivityAutoRecognitionActivitiesWithOneK.this, view);
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.p = textView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setVisibility(8);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.t) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAutoRecognitionActivitiesWithOneK.F(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            String string4 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAutoRecognitionActivitiesWithOneK.G(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            if (bottomSheetDialogTwoButtons.isShowing()) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_auto_recog_with_onek);
        ViewModel viewModel = ViewModelProviders.of(this).get(ActivityAutoRecognitionViewModelWithOneK.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this).get(ActivityAut…odelWithOneK::class.java)");
        ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = (ActivityAutoRecognitionViewModelWithOneK) viewModel;
        this.q = activityAutoRecognitionViewModelWithOneK;
        ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK2 = null;
        if (activityAutoRecognitionViewModelWithOneK == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityAutoRecognitionViewModelWithOneK = null;
        }
        activityAutoRecognitionViewModelWithOneK.setDialogListener(this);
        initToolbar();
        A();
        showProgress();
        buttonVisible();
        ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK3 = this.q;
        if (activityAutoRecognitionViewModelWithOneK3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityAutoRecognitionViewModelWithOneK3 = null;
        }
        activityAutoRecognitionViewModelWithOneK3.getListOfActivities();
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.auto_recog_recyl);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK4 = this.q;
        if (activityAutoRecognitionViewModelWithOneK4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            activityAutoRecognitionViewModelWithOneK2 = activityAutoRecognitionViewModelWithOneK4;
        }
        activityAutoRecognitionViewModelWithOneK2.getListOfActivityLiveData().observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.autodetection.activities.j
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityAutoRecognitionActivitiesWithOneK.H(ActivityAutoRecognitionActivitiesWithOneK.this, (ArrayList) obj);
            }
        });
    }

    @Override // com.coveiot.android.activitymodes.utils.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.activitymodes.autodetection.adapter.AutoRecognitionAdapterOneK.OnItemClickListener
    public void onItemClick(@NotNull AutoRecognitonModelOneK autoRecognitionModelOneK, int i) {
        RecyclerView.Adapter adapter;
        Intrinsics.checkNotNullParameter(autoRecognitionModelOneK, "autoRecognitionModelOneK");
        if (autoRecognitionModelOneK.isSelected()) {
            ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = this.q;
            if (activityAutoRecognitionViewModelWithOneK == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                activityAutoRecognitionViewModelWithOneK = null;
            }
            if (!activityAutoRecognitionViewModelWithOneK.isSelectedActivityPresentInWatch(autoRecognitionModelOneK.getFwActivityId())) {
                this.r.get(i).setSelected(false);
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.auto_recog_recyl);
                if (recyclerView != null && (adapter = recyclerView.getAdapter()) != null) {
                    adapter.notifyDataSetChanged();
                }
                L();
                return;
            }
        }
        int size = this.s.size();
        for (int i2 = 0; i2 < size; i2++) {
            boolean z = this.s.get(i2).isSelected() != this.r.get(i2).isSelected();
            this.t = z;
            if (z) {
                break;
            }
        }
        buttonVisible();
    }

    @Override // com.coveiot.android.activitymodes.utils.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setActivityList(@NotNull ArrayList<AutoRecognitonModelOneK> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.r = arrayList;
    }

    public final void setActivityListPref(@NotNull ArrayList<AutoRecognitonModelOneK> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.s = arrayList;
    }

    public final void setBoolSaveVisible(boolean z) {
        this.t = z;
    }

    @Override // com.coveiot.android.activitymodes.utils.DialogListener
    public void showErrorDialog() {
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionActivitiesWithOneK.J(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        if (bottomSheetDialogOneButtonOneTitle.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.activitymodes.utils.DialogListener
    public void showSuccessDialog() {
        ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = this.q;
        if (activityAutoRecognitionViewModelWithOneK == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityAutoRecognitionViewModelWithOneK = null;
        }
        activityAutoRecognitionViewModelWithOneK.saveSelectedActivitiesToPref(this.r);
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionActivitiesWithOneK.K(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        if (bottomSheetDialogOneButtonOneTitle.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }
}
