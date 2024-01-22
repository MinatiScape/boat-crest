package com.coveiot.android.smartalert.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.SmartAlertAppData;
import com.coveiot.android.bleabstract.request.GetSmartAlertConfigRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetSmartAlertConfigResponse;
import com.coveiot.android.smartalert.R;
import com.coveiot.android.smartalert.SmartAlertPreferenceManager;
import com.coveiot.android.smartalert.databinding.ActivitySmartAlertTestingBinding;
import com.coveiot.android.smartalert.util.SmartAlertUtils;
import com.coveiot.android.smartalert.viewmodel.SmartAlertViewModel;
import com.coveiot.android.smartalert.viewmodel.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class ActivitySmartAlertTesting extends BaseActivity implements AdapterView.OnItemSelectedListener {
    public ActivitySmartAlertTestingBinding p;
    public SmartAlertViewModel t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String q = ActivitySmartAlertTesting.class.getSimpleName();
    public int r = -1;
    @NotNull
    public String s = "com.ubercab";

    @DebugMetadata(c = "com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$3$1", f = "ActivitySmartAlertTesting.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Boolean $it;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Boolean bool, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$it = bool;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$it, continuation);
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
                ActivitySmartAlertTesting.this.dismissProgress();
                Boolean it = this.$it;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                if (it.booleanValue()) {
                    ActivitySmartAlertTesting activitySmartAlertTesting = ActivitySmartAlertTesting.this;
                    Toast.makeText(activitySmartAlertTesting, activitySmartAlertTesting.getString(R.string.setting_saved_successfully), 0).show();
                } else {
                    ActivitySmartAlertTesting activitySmartAlertTesting2 = ActivitySmartAlertTesting.this;
                    Toast.makeText(activitySmartAlertTesting2, activitySmartAlertTesting2.getString(R.string.setting_could_not_be_saved), 0).show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void u(ActivitySmartAlertTesting this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void v(ActivitySmartAlertTesting this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isFinishing()) {
            return;
        }
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getMain(), null, new a(bool, null), 2, null);
    }

    public static final void w(ActivitySmartAlertTesting this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding = this$0.p;
        if (activitySmartAlertTestingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySmartAlertTestingBinding = null;
        }
        Editable text = activitySmartAlertTestingBinding.title.getText();
        boolean z = true;
        if (!(text == null || text.length() == 0)) {
            ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding2 = this$0.p;
            if (activitySmartAlertTestingBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySmartAlertTestingBinding2 = null;
            }
            Editable text2 = activitySmartAlertTestingBinding2.content.getText();
            if (text2 != null && text2.length() != 0) {
                z = false;
            }
            if (!z) {
                this$0.showProgress();
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new ActivitySmartAlertTesting$onCreate$4$1(this$0, null), 2, null);
                return;
            }
        }
        Toast.makeText(this$0, "please enter title and content.", 0).show();
    }

    public static final void x(ActivitySmartAlertTesting this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showProgress();
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new ActivitySmartAlertTesting$onCreate$5$1(this$0, null), 2, null);
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
    public final String getAppPackageName() {
        return this.s;
    }

    public final int getMsgId() {
        return this.r;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivitySmartAlertTestingBinding inflate = ActivitySmartAlertTestingBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.smart_alert));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.smartalert.activity.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySmartAlertTesting.u(ActivitySmartAlertTesting.this, view);
            }
        });
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(SmartAlertViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…del::class.java\n        )");
        this.t = (SmartAlertViewModel) viewModel;
        BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
        if (bleDeviceInfo != null && bleDeviceInfo.getFirmwareRevision() != null) {
            SmartAlertUtils.Companion companion = SmartAlertUtils.Companion;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            SmartAlertUtils.Companion.getSmartAlertAppsFromServer$default(companion, applicationContext, null, false, 4, null);
        }
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this).getBleApi().getData(new GetSmartAlertConfigRequest(), new DataResultListener() { // from class: com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$2
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    String str;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = ActivitySmartAlertTesting.this.q;
                    LogHelper.d(str, "GetSmartAlertConfigRequest error " + error.getErrorMsg());
                    ActivitySmartAlertTesting activitySmartAlertTesting = ActivitySmartAlertTesting.this;
                    Toast.makeText(activitySmartAlertTesting, "GetSmartAlertConfigRequest error " + error.getErrorMsg(), 1).show();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    String str;
                    Intrinsics.checkNotNullParameter(response, "response");
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetSmartAlertConfigResponse");
                    GetSmartAlertConfigResponse getSmartAlertConfigResponse = (GetSmartAlertConfigResponse) responseData;
                    str = ActivitySmartAlertTesting.this.q;
                    LogHelper.d(str, "GetSmartAlertConfigRequest onDataResponse " + getSmartAlertConfigResponse);
                    ActivitySmartAlertTesting activitySmartAlertTesting = ActivitySmartAlertTesting.this;
                    Toast.makeText(activitySmartAlertTesting, "GetSmartAlertConfigRequest onDataResponse isEnabled " + Boolean.valueOf(getSmartAlertConfigResponse.isEnabled()), 1).show();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    String str;
                    Intrinsics.checkNotNullParameter(progress, "progress");
                    str = ActivitySmartAlertTesting.this.q;
                    LogHelper.d(str, "GetSmartAlertConfigRequest progress");
                    Toast.makeText(ActivitySmartAlertTesting.this, "GetSmartAlertConfigRequest progress", 1).show();
                }
            });
            showProgress();
            ArrayList arrayList = new ArrayList();
            SmartAlertUtils.Companion companion2 = SmartAlertUtils.Companion;
            SmartAlertAppData smartAlertAppData = new SmartAlertAppData(1, "UBER", 32, companion2.convertHexTo565("#ffffff"));
            SmartAlertAppData smartAlertAppData2 = new SmartAlertAppData(2, "SWIGGY", 32, companion2.convertHexTo565("#fc8019"));
            arrayList.add(smartAlertAppData);
            arrayList.add(smartAlertAppData2);
            SmartAlertViewModel smartAlertViewModel = this.t;
            if (smartAlertViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                smartAlertViewModel = null;
            }
            smartAlertViewModel.setSmartAlertConfig(arrayList);
        }
        SmartAlertViewModel smartAlertViewModel2 = this.t;
        if (smartAlertViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            smartAlertViewModel2 = null;
        }
        smartAlertViewModel2.getSetSmartAlertConfigLiveData().observe(this, new Observer() { // from class: com.coveiot.android.smartalert.activity.d
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivitySmartAlertTesting.v(ActivitySmartAlertTesting.this, (Boolean) obj);
            }
        });
        ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding2 = this.p;
        if (activitySmartAlertTestingBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySmartAlertTestingBinding2 = null;
        }
        activitySmartAlertTestingBinding2.submit.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.smartalert.activity.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySmartAlertTesting.w(ActivitySmartAlertTesting.this, view);
            }
        });
        ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding3 = this.p;
        if (activitySmartAlertTestingBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySmartAlertTestingBinding = activitySmartAlertTestingBinding3;
        }
        activitySmartAlertTestingBinding.autoTestBtn.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.smartalert.activity.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySmartAlertTesting.x(ActivitySmartAlertTesting.this, view);
            }
        });
        View findViewById = findViewById(R.id.apps_spinner);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.apps_spinner)");
        Spinner spinner = (Spinner) findViewById;
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(this, R.array.apps_array, 17367048);
        createFromResource.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter) createFromResource);
        spinner.setSelection(!Intrinsics.areEqual(SmartAlertPreferenceManager.Companion.getInstance(this).getSmartAlertAppIdForTesting(), "com.ubercab") ? 1 : 0);
        spinner.setOnItemSelectedListener(this);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i, long j) {
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) view).setTextColor(-1);
        if (i == 0) {
            this.s = "com.ubercab";
        } else if (i == 1) {
            this.s = "in.swiggy.android";
        }
        this.r = -1;
        SmartAlertPreferenceManager.Companion.getInstance(this).saveSmartAlertAppIdForTesting(this.s);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
    }

    public final void setAppPackageName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.s = str;
    }

    public final void setMsgId(int i) {
        this.r = i;
    }
}
