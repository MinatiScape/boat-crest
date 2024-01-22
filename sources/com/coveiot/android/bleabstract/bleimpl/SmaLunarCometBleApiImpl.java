package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.models.CloveSmaBleState;
import com.coveiot.android.smasdk.api.SmaBaseRes;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.squareup.otto.Subscribe;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SmaLunarCometBleApiImpl extends SmaLunarSeekBleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public String z;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SmaLunarCometBleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.SmaLunarCometBleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SmaLunarCometBleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3240a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SmaLunarCometBleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SmaLunarCometBleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SmaLunarCometBleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3240a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaLunarCometBleApiImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        String simpleName = SmaLunarSeekBleApiImpl.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SmaLunarSeekBleApiImpl::class.java.simpleName");
        this.z = simpleName;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaLunarSeekBleApiImpl, com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    @NotNull
    public String getTAG() {
        return this.z;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaLunarSeekBleApiImpl
    @Subscribe
    public void onConnectionStateChanged(@Nullable CloveSmaBleState cloveSmaBleState) {
        super.onConnectionStateChanged(cloveSmaBleState);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaLunarSeekBleApiImpl, com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl, com.coveiot.android.smasdk.SmaResponseListener
    public void onResponse(@NotNull SmaBaseRes baseRes) {
        Intrinsics.checkNotNullParameter(baseRes, "baseRes");
        super.onResponse(baseRes);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaLunarSeekBleApiImpl
    @Subscribe
    public void onResponseEventReceived(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        super.onResponseEventReceived(responseEvent);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaLunarSeekBleApiImpl, com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    public void setTAG(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.z = str;
    }
}
