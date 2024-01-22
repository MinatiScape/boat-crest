package com.coveiot.android.dashboard2;

import android.content.Context;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class StatusApiDataHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4209a;
    @NotNull
    public final String b;

    /* loaded from: classes4.dex */
    public static final class Companion extends SingletonHolder<StatusApiDataHelper, Context> {

        /* loaded from: classes4.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, StatusApiDataHelper> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, StatusApiDataHelper.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final StatusApiDataHelper invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new StatusApiDataHelper(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes4.dex */
    public interface UploadCompletionListner {
        void onDataUploadeComplete();

        void onUploadFailed();
    }

    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConnectionStatus.values().length];
            try {
                iArr[ConnectionStatus.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConnectionStatus.DISCOVERING_SERVICES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConnectionStatus.SCANNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ConnectionStatus.CONNECTING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ConnectionStatus.DISCONNECTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public StatusApiDataHelper(Context context) {
        this.f4209a = context;
        this.b = "StatusApiDataHelper";
    }

    public /* synthetic */ StatusApiDataHelper(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static /* synthetic */ void saveStatusDataToServer$default(StatusApiDataHelper statusApiDataHelper, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        statusApiDataHelper.saveStatusDataToServer(z);
    }

    @NotNull
    public final Context getContext() {
        return this.f4209a;
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final void saveStatusDataToServer(boolean z) {
    }
}
