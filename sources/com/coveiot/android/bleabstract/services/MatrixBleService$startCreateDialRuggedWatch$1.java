package com.coveiot.android.bleabstract.services;

import android.graphics.Bitmap;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.WristbandManager;
import com.htsmart.wristband2.bean.DialBinInfo;
import com.htsmart.wristband2.dfu.DfuManager;
import com.htsmart.wristband2.dial.DialDrawer;
import com.htsmart.wristband2.dial.DialWriter;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.services.MatrixBleService$startCreateDialRuggedWatch$1", f = "MatrixBleService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class MatrixBleService$startCreateDialRuggedWatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3900a;
    public final /* synthetic */ MatrixBleService b;
    public final /* synthetic */ Bitmap c;
    public final /* synthetic */ Bitmap d;
    public final /* synthetic */ DialDrawer.Position e;
    public final /* synthetic */ File f;

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            DeviceType.values();
            int[] iArr = new int[117];
            try {
                DeviceType deviceType = DeviceType.LUNARFIT;
                iArr[74] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MatrixBleService$startCreateDialRuggedWatch$1(int i, MatrixBleService matrixBleService, Bitmap bitmap, Bitmap bitmap2, DialDrawer.Position position, File file, Continuation<? super MatrixBleService$startCreateDialRuggedWatch$1> continuation) {
        super(2, continuation);
        this.f3900a = i;
        this.b = matrixBleService;
        this.c = bitmap;
        this.d = bitmap2;
        this.e = position;
        this.f = file;
    }

    public static final void a(MatrixBleService matrixBleService) {
        String str;
        str = matrixBleService.f3858a;
        LogHelper.i(str, "MatrixDIYComponentReq success");
    }

    public static final void b(MatrixBleService matrixBleService) {
        String str;
        str = matrixBleService.f3858a;
        LogHelper.i(str, "MatrixDIYComponentReq success");
    }

    public static final void c(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    public static final void d(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new MatrixBleService$startCreateDialRuggedWatch$1(this.f3900a, this.b, this.c, this.d, this.e, this.f, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MatrixBleService$startCreateDialRuggedWatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        WristbandManager wristbandManager;
        WristbandManager wristbandManager2;
        WristbandManager wristbandManager3;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        try {
            byte[] bArr = {1};
            bArr[0] = (byte) this.f3900a;
            DeviceType deviceType = BleApiManager.getInstance(this.b).getDeviceType();
            if ((deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()]) == 1) {
                MatrixBleService matrixBleService = this.b;
                wristbandManager3 = matrixBleService.h;
                Intrinsics.checkNotNull(wristbandManager3);
                Completable onErrorComplete = wristbandManager3.setDialComponents(4, bArr).observeOn(AndroidSchedulers.mainThread()).onErrorComplete();
                final MatrixBleService matrixBleService2 = this.b;
                Action action = new Action() { // from class: com.coveiot.android.bleabstract.services.t3
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                        MatrixBleService$startCreateDialRuggedWatch$1.a(MatrixBleService.this);
                    }
                };
                final MatrixBleService matrixBleService3 = this.b;
                final Function1<Throwable, Unit> function1 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$startCreateDialRuggedWatch$1.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public Unit invoke(Throwable th) {
                        String str;
                        str = MatrixBleService.this.f3858a;
                        LogHelper.i(str, "MatrixDIYComponentReq Failed");
                        return Unit.INSTANCE;
                    }
                };
                matrixBleService.H = onErrorComplete.subscribe(action, new Consumer() { // from class: com.coveiot.android.bleabstract.services.v3
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj2) {
                        MatrixBleService$startCreateDialRuggedWatch$1.a(Function1.this, obj2);
                    }
                });
            } else {
                MatrixBleService matrixBleService4 = this.b;
                wristbandManager = matrixBleService4.h;
                Intrinsics.checkNotNull(wristbandManager);
                Completable observeOn = wristbandManager.setDialComponents(0, bArr).observeOn(AndroidSchedulers.mainThread());
                final MatrixBleService matrixBleService5 = this.b;
                Action action2 = new Action() { // from class: com.coveiot.android.bleabstract.services.u3
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                        MatrixBleService$startCreateDialRuggedWatch$1.b(MatrixBleService.this);
                    }
                };
                final MatrixBleService matrixBleService6 = this.b;
                final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$startCreateDialRuggedWatch$1.4
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public Unit invoke(Throwable th) {
                        String str;
                        str = MatrixBleService.this.f3858a;
                        LogHelper.i(str, "MatrixDIYComponentReq Failed");
                        return Unit.INSTANCE;
                    }
                };
                matrixBleService4.H = observeOn.subscribe(action2, new Consumer() { // from class: com.coveiot.android.bleabstract.services.w3
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj2) {
                        MatrixBleService$startCreateDialRuggedWatch$1.b(Function1.this, obj2);
                    }
                });
            }
            MatrixBleService matrixBleService7 = this.b;
            wristbandManager2 = matrixBleService7.h;
            Intrinsics.checkNotNull(wristbandManager2);
            Single<DialBinInfo> observeOn2 = wristbandManager2.requestDialBinInfo().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
            final MatrixBleService matrixBleService8 = this.b;
            final Bitmap bitmap = this.c;
            final Bitmap bitmap2 = this.d;
            final DialDrawer.Position position = this.e;
            final File file = this.f;
            final Function1<DialBinInfo, Unit> function13 = new Function1<DialBinInfo, Unit>() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$startCreateDialRuggedWatch$1.5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(DialBinInfo dialBinInfo) {
                    String str;
                    DfuManager dfuManager;
                    String str2;
                    DfuManager dfuManager2;
                    DialBinInfo dialBinInfo2 = dialBinInfo;
                    Intrinsics.checkNotNullParameter(dialBinInfo2, "dialBinInfo");
                    str = MatrixBleService.this.f3858a;
                    LogHelper.d(str, "requestDialBinInfo lcd - " + dialBinInfo2.getLcd());
                    DialDrawer.Shape createFromLcd = DialDrawer.Shape.createFromLcd(dialBinInfo2.getLcd());
                    Intrinsics.checkNotNull(createFromLcd);
                    if (createFromLcd.isShapeRectangle() && createFromLcd.width() != createFromLcd.height()) {
                        createFromLcd.setCorners(48);
                    }
                    Bitmap bitmap3 = bitmap;
                    DialDrawer.ScaleType scaleType = DialDrawer.ScaleType.CENTER_CROP;
                    Bitmap createDialBackground = DialDrawer.createDialBackground(bitmap3, createFromLcd, scaleType);
                    DfuManager dfuManager3 = null;
                    if (BleApiManager.getInstance(MatrixBleService.this).getDeviceType() == DeviceType.LUNARFIT) {
                        str2 = MatrixBleService.this.f3858a;
                        LogHelper.d(str2, "lunarfit backgroundBitmap.height " + bitmap.getHeight() + " backgroundBitmap.width " + bitmap.getWidth() + " styleBitmap.height " + bitmap2.getHeight() + " styleBitmap.width " + bitmap2.getWidth());
                        try {
                            DialWriter dialWriter = new DialWriter(file, createDialBackground, DialDrawer.createDialPreview(bitmap, bitmap2, createFromLcd, scaleType, position, 932, createFromLcd.width(), createFromLcd.height()), position, true);
                            String parent = file.getParent();
                            dialWriter.setCopyFile(new File(parent, "temp_" + file.getName()));
                            dialWriter.setAutoScalePreview(true);
                            File execute = dialWriter.execute();
                            dfuManager2 = MatrixBleService.this.M;
                            if (dfuManager2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("dfuManager");
                            } else {
                                dfuManager3 = dfuManager2;
                            }
                            dfuManager3.upgradeDial(execute.getAbsolutePath(), (byte) -95);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            DialWriter dialWriter2 = new DialWriter(file, createDialBackground, DialDrawer.createDialPreview(bitmap, bitmap2, createFromLcd, scaleType, position, 480, createFromLcd.width(), createFromLcd.height()), position, true);
                            String parent2 = file.getParent();
                            dialWriter2.setCopyFile(new File(parent2, "temp_" + file.getName()));
                            dialWriter2.setAutoScalePreview(true);
                            File execute2 = dialWriter2.execute();
                            dfuManager = MatrixBleService.this.M;
                            if (dfuManager == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("dfuManager");
                            } else {
                                dfuManager3 = dfuManager;
                            }
                            dfuManager3.upgradeDial(execute2.getAbsolutePath(), (byte) -95);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    return Unit.INSTANCE;
                }
            };
            Consumer<? super DialBinInfo> consumer = new Consumer() { // from class: com.coveiot.android.bleabstract.services.x3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj2) {
                    MatrixBleService$startCreateDialRuggedWatch$1.c(Function1.this, obj2);
                }
            };
            final MatrixBleService matrixBleService9 = this.b;
            final Function1<Throwable, Unit> function14 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$startCreateDialRuggedWatch$1.6
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    String str;
                    str = MatrixBleService.this.f3858a;
                    LogHelper.i(str, "requestDialBinInfo Failed");
                    return Unit.INSTANCE;
                }
            };
            matrixBleService7.H = observeOn2.subscribe(consumer, new Consumer() { // from class: com.coveiot.android.bleabstract.services.y3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj2) {
                    MatrixBleService$startCreateDialRuggedWatch$1.d(Function1.this, obj2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }

    public static final void a(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    public static final void b(Function1 function1, Object obj) {
        function1.invoke(obj);
    }
}
