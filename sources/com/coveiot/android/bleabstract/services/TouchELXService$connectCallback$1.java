package com.coveiot.android.bleabstract.services;

import com.coveiot.android.bleabstract.listeners.TGConnectCallback;
import com.coveiot.android.bleabstract.utils.touchUtils.TouchDeviceManager;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.utils.utility.LogHelper;
import com.touchgui.sdk.bean.TGBindResult;
import com.touchgui.sdk.bean.TGDevice;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TouchELXService$connectCallback$1 implements TGConnectCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TouchELXService f3940a;

    public TouchELXService$connectCallback$1(TouchELXService touchELXService) {
        this.f3940a = touchELXService;
    }

    public static final void a(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void b(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // com.coveiot.android.bleabstract.listeners.TGConnectCallback
    public void onConnectionState(@Nullable String str, int i) {
        boolean z;
        CloveBleState.BleState bleState;
        CloveBleState.BleState bleState2;
        CloveBleState.BleState bleState3;
        z = this.f3940a.M;
        if (z) {
            if (i == 0) {
                this.f3940a.d = CloveBleState.BleState.DISCONNECTED;
            } else if (i == 1) {
                this.f3940a.d = CloveBleState.BleState.CONNECTING;
            } else if (i == 2) {
                this.f3940a.d = CloveBleState.BleState.CONNECTED;
            } else if (i == 3) {
                this.f3940a.d = CloveBleState.BleState.DISCONNECTED;
            }
            bleState = this.f3940a.d;
            if (bleState == CloveBleState.BleState.CONNECTED) {
                TouchELXService touchELXService = this.f3940a;
                bleState3 = touchELXService.d;
                Intrinsics.checkNotNull(bleState3);
                touchELXService.updateConnectionState(bleState3, false);
                return;
            }
            TouchELXService touchELXService2 = this.f3940a;
            bleState2 = touchELXService2.d;
            Intrinsics.checkNotNull(bleState2);
            touchELXService2.updateConnectionState(bleState2, true);
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.TGConnectCallback
    public void onReady(@Nullable TGDevice tGDevice) {
        Disposable disposable;
        Observable<TGBindResult> bindDevice;
        Observable<TGBindResult> observeOn;
        TouchELXService touchELXService = this.f3940a;
        TouchDeviceManager mTouchDeviceManager = touchELXService.getMTouchDeviceManager();
        if (mTouchDeviceManager == null || (bindDevice = mTouchDeviceManager.bindDevice()) == null || (observeOn = bindDevice.observeOn(AndroidSchedulers.mainThread())) == null) {
            disposable = null;
        } else {
            final TouchELXService touchELXService2 = this.f3940a;
            final Function1<TGBindResult, Unit> function1 = new Function1<TGBindResult, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$connectCallback$1$onReady$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(TGBindResult tGBindResult) {
                    TGBindResult tGBindResult2 = tGBindResult;
                    Integer valueOf = tGBindResult2 != null ? Integer.valueOf(tGBindResult2.getResult()) : null;
                    if (valueOf != null && valueOf.intValue() == 0) {
                        TouchELXService.this.M = true;
                        TouchELXService.this.updateConnectionState(CloveBleState.BleState.CONNECTED, true);
                        LogHelper.i(TouchELXService.this.getTAG(), "TGBindResult.SUCCESS");
                        if (tGBindResult2.getRandomData() != null) {
                            String randomData = tGBindResult2.getRandomData();
                            Intrinsics.checkNotNull(randomData);
                            if (randomData.length() > 0) {
                                TouchELXService touchELXService3 = TouchELXService.this;
                                String randomData2 = tGBindResult2.getRandomData();
                                Intrinsics.checkNotNull(randomData2);
                                touchELXService3.a(randomData2);
                            }
                        }
                        TouchELXService.this.a();
                    } else if (valueOf != null && valueOf.intValue() == 1) {
                        LogHelper.i(TouchELXService.this.getTAG(), "TGBindResult.FAILURE");
                        TouchELXService.this.updateConnectionState(CloveBleState.BleState.DISCONNECTED, true);
                        TouchELXService.access$disconnect(TouchELXService.this, false);
                    } else if (valueOf != null && valueOf.intValue() == 2) {
                        LogHelper.i(TouchELXService.this.getTAG(), "TGBindResult.BOUND");
                    }
                    return Unit.INSTANCE;
                }
            };
            Consumer<? super TGBindResult> consumer = new Consumer() { // from class: com.coveiot.android.bleabstract.services.h7
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService$connectCallback$1.a(Function1.this, obj);
                }
            };
            final TouchELXService touchELXService3 = this.f3940a;
            final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$connectCallback$1$onReady$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    final Throwable th2 = th;
                    final TouchELXService touchELXService4 = TouchELXService.this;
                    new Function0<Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$connectCallback$1$onReady$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public Unit invoke() {
                            String tag = TouchELXService.this.getTAG();
                            StringBuilder sb = new StringBuilder();
                            sb.append("onReady error");
                            Throwable th3 = th2;
                            sb.append(th3 != null ? th3.getMessage() : null);
                            LogHelper.i(tag, sb.toString());
                            return Unit.INSTANCE;
                        }
                    };
                    return Unit.INSTANCE;
                }
            };
            disposable = observeOn.subscribe(consumer, new Consumer() { // from class: com.coveiot.android.bleabstract.services.i7
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService$connectCallback$1.b(Function1.this, obj);
                }
            });
        }
        touchELXService.i = disposable;
    }
}
