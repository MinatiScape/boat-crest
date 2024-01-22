package com.coveiot.android.respiratoryrate;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.BatterySaverConfigAbstract;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetBatterySaverConfigRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.respiratoryrate.BatterySaverModeRespiratoryRateHelper;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class BatterySaverModeRespiratoryRateHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5654a;

    /* loaded from: classes6.dex */
    public interface BatterySaverModeListener {
        void onBatterySavingSettingsReceived(boolean z, int i);
    }

    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<BatterySaverModeRespiratoryRateHelper, Context> {

        /* loaded from: classes6.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, BatterySaverModeRespiratoryRateHelper> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, BatterySaverModeRespiratoryRateHelper.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final BatterySaverModeRespiratoryRateHelper invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new BatterySaverModeRespiratoryRateHelper(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BatterySaverModeRespiratoryRateHelper(Context context) {
        this.f5654a = context;
    }

    public /* synthetic */ BatterySaverModeRespiratoryRateHelper(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void getBatterySaverMode(@NotNull final BatterySaverModeListener batterySaverModeListener) {
        Intrinsics.checkNotNullParameter(batterySaverModeListener, "batterySaverModeListener");
        BleApiManager.getInstance(this.f5654a).getBleApi().getData(new GetBatterySaverConfigRequest(), new DataResultListener() { // from class: com.coveiot.android.respiratoryrate.BatterySaverModeRespiratoryRateHelper$getBatterySaverMode$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d("PagerContainer", response.toString());
                if (response.getResponseData() instanceof BatterySaverConfigAbstract) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.models.BatterySaverConfigAbstract");
                    BatterySaverConfigAbstract batterySaverConfigAbstract = (BatterySaverConfigAbstract) responseData;
                    BatterySaverModeRespiratoryRateHelper.BatterySaverModeListener.this.onBatterySavingSettingsReceived(batterySaverConfigAbstract.isEnabled() && batterySaverConfigAbstract.isActive(), batterySaverConfigAbstract.getMode());
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5654a;
    }
}
