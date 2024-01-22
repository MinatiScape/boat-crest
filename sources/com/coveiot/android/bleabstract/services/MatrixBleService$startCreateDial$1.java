package com.coveiot.android.bleabstract.services;

import android.graphics.Bitmap;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.WristbandManager;
import com.htsmart.wristband2.bean.DialBinInfo;
import com.htsmart.wristband2.dfu.DfuManager;
import com.htsmart.wristband2.dial.DialDrawer;
import com.htsmart.wristband2.dial.DialWriter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
@DebugMetadata(c = "com.coveiot.android.bleabstract.services.MatrixBleService$startCreateDial$1", f = "MatrixBleService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class MatrixBleService$startCreateDial$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MatrixBleService f3897a;
    public final /* synthetic */ Bitmap b;
    public final /* synthetic */ Bitmap c;
    public final /* synthetic */ DialDrawer.Position d;
    public final /* synthetic */ File e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MatrixBleService$startCreateDial$1(MatrixBleService matrixBleService, Bitmap bitmap, Bitmap bitmap2, DialDrawer.Position position, File file, Continuation<? super MatrixBleService$startCreateDial$1> continuation) {
        super(2, continuation);
        this.f3897a = matrixBleService;
        this.b = bitmap;
        this.c = bitmap2;
        this.d = position;
        this.e = file;
    }

    public static final void a(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    public static final void b(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new MatrixBleService$startCreateDial$1(this.f3897a, this.b, this.c, this.d, this.e, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MatrixBleService$startCreateDial$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        WristbandManager wristbandManager;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        try {
            MatrixBleService matrixBleService = this.f3897a;
            wristbandManager = matrixBleService.h;
            Intrinsics.checkNotNull(wristbandManager);
            Single<DialBinInfo> observeOn = wristbandManager.requestDialBinInfo().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
            final MatrixBleService matrixBleService2 = this.f3897a;
            final Bitmap bitmap = this.b;
            final Bitmap bitmap2 = this.c;
            final DialDrawer.Position position = this.d;
            final File file = this.e;
            final Function1<DialBinInfo, Unit> function1 = new Function1<DialBinInfo, Unit>() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$startCreateDial$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(DialBinInfo dialBinInfo) {
                    String str;
                    DfuManager dfuManager;
                    DialBinInfo dialBinInfo2 = dialBinInfo;
                    Intrinsics.checkNotNullParameter(dialBinInfo2, "dialBinInfo");
                    str = MatrixBleService.this.f3858a;
                    LogHelper.d(str, "requestDialBinInfo lcd - " + dialBinInfo2 + ".lcd");
                    DialDrawer.Shape createFromLcd = DialDrawer.Shape.createFromLcd(dialBinInfo2.getLcd());
                    Intrinsics.checkNotNull(createFromLcd);
                    if (createFromLcd.isShapeRectangle() && createFromLcd.width() != createFromLcd.height()) {
                        createFromLcd.setCorners(48);
                    }
                    Bitmap bitmap3 = bitmap;
                    DialDrawer.ScaleType scaleType = DialDrawer.ScaleType.CENTER_CROP;
                    DialWriter dialWriter = new DialWriter(file, DialDrawer.createDialBackground(bitmap3, createFromLcd, scaleType), DialDrawer.createDialPreview(bitmap, bitmap2, createFromLcd, scaleType, position, 800, createFromLcd.width(), createFromLcd.height()), position, false);
                    String parent = file.getParent();
                    dialWriter.setCopyFile(new File(parent, "temp_" + file.getName()));
                    dialWriter.setAutoScalePreview(true);
                    File execute = dialWriter.execute();
                    dfuManager = MatrixBleService.this.M;
                    if (dfuManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("dfuManager");
                        dfuManager = null;
                    }
                    dfuManager.upgradeDial(execute.getAbsolutePath(), (byte) 0);
                    return Unit.INSTANCE;
                }
            };
            Consumer<? super DialBinInfo> consumer = new Consumer() { // from class: com.coveiot.android.bleabstract.services.r3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj2) {
                    MatrixBleService$startCreateDial$1.a(Function1.this, obj2);
                }
            };
            final MatrixBleService matrixBleService3 = this.f3897a;
            final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$startCreateDial$1.2
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
            matrixBleService.H = observeOn.subscribe(consumer, new Consumer() { // from class: com.coveiot.android.bleabstract.services.s3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj2) {
                    MatrixBleService$startCreateDial$1.b(Function1.this, obj2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }
}
