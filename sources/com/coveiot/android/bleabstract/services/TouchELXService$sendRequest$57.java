package com.coveiot.android.bleabstract.services;

import com.coveiot.android.bleabstract.utils.touchUtils.TouchDeviceManager;
import com.coveiot.android.touchsdk.TouchELXResponseListener;
import com.coveiot.android.touchsdk.api.TouchELXBaseReq;
import com.coveiot.android.touchsdk.api.TouchELXBaseRes;
import com.coveiot.android.touchsdk.api.TouchGetWorkoutModesReq;
import com.coveiot.utils.utility.LogHelper;
import com.touchgui.sdk.bean.TGWorkoutMode;
import com.touchgui.sdk.bean.TGWorkoutSupportList;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes2.dex */
public final class TouchELXService$sendRequest$57 extends Lambda implements Function1<TGWorkoutSupportList, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TouchELXService f3993a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchELXService$sendRequest$57(TouchELXService touchELXService) {
        super(1);
        this.f3993a = touchELXService;
    }

    public static final void b(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void a(TGWorkoutSupportList tGWorkoutSupportList) {
        Disposable disposable;
        Observable<TGWorkoutMode> getConfiguredWorkoutList;
        Observable<TGWorkoutMode> observeOn;
        if (this.f3993a.getKhCurrentCommand() == null || !(this.f3993a.getKhCurrentCommand() instanceof TouchGetWorkoutModesReq)) {
            return;
        }
        LogHelper.i(this.f3993a.getTAG(), "getSupportedWorkoutList success");
        final TouchELXBaseRes touchELXBaseRes = new TouchELXBaseRes();
        TouchELXBaseReq khCurrentCommand = this.f3993a.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        touchELXBaseRes.setBaseReq(khCurrentCommand);
        touchELXBaseRes.setObj(tGWorkoutSupportList.getItems());
        TouchELXService touchELXService = this.f3993a;
        TouchDeviceManager mTouchDeviceManager = touchELXService.getMTouchDeviceManager();
        if (mTouchDeviceManager == null || (getConfiguredWorkoutList = mTouchDeviceManager.getGetConfiguredWorkoutList()) == null || (observeOn = getConfiguredWorkoutList.observeOn(AndroidSchedulers.mainThread())) == null) {
            disposable = null;
        } else {
            final TouchELXService touchELXService2 = this.f3993a;
            final Function1<TGWorkoutMode, Unit> function1 = new Function1<TGWorkoutMode, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$57.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(TGWorkoutMode tGWorkoutMode) {
                    TouchELXResponseListener responseListener;
                    TGWorkoutMode tGWorkoutMode2 = tGWorkoutMode;
                    if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetWorkoutModesReq)) {
                        LogHelper.i(TouchELXService.this.getTAG(), "getConfiguredWorkoutList success");
                        touchELXBaseRes.setObj2(tGWorkoutMode2.getWorkouts());
                        TouchELXBaseReq khCurrentCommand2 = TouchELXService.this.getKhCurrentCommand();
                        if (khCurrentCommand2 != null && (responseListener = khCurrentCommand2.getResponseListener()) != null) {
                            responseListener.onResponse(touchELXBaseRes);
                        }
                    }
                    return Unit.INSTANCE;
                }
            };
            Consumer<? super TGWorkoutMode> consumer = new Consumer() { // from class: com.coveiot.android.bleabstract.services.j7
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService$sendRequest$57.a(Function1.this, obj);
                }
            };
            final TouchELXService touchELXService3 = this.f3993a;
            final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$57.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    Throwable th2 = th;
                    if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetWorkoutModesReq)) {
                        TouchELXService touchELXService4 = TouchELXService.this;
                        String message = th2 != null ? th2.getMessage() : null;
                        Intrinsics.checkNotNull(message);
                        TouchELXService.access$sendCommandFailure(touchELXService4, message);
                    }
                    return Unit.INSTANCE;
                }
            };
            disposable = observeOn.subscribe(consumer, new Consumer() { // from class: com.coveiot.android.bleabstract.services.k7
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService$sendRequest$57.b(Function1.this, obj);
                }
            });
        }
        touchELXService.H = disposable;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(TGWorkoutSupportList tGWorkoutSupportList) {
        a(tGWorkoutSupportList);
        return Unit.INSTANCE;
    }

    public static final void a(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
