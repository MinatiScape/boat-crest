package com.coveiot.android.leonardo.performance.custommessage;

import android.content.Context;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class Nudge2BestActivityManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "Nudge2BestActivityManager";

    /* loaded from: classes5.dex */
    public static final class Companion {

        @DebugMetadata(c = "com.coveiot.android.leonardo.performance.custommessage.Nudge2BestActivityManager$Companion$calculateNudge2AndSendToBLE$1", f = "Nudge2BestActivityManager.kt", i = {0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10, 10, 10, 10}, l = {49, 54, 64, 95, 116, 122, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, 162, 178, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 296}, m = "invokeSuspend", n = {"activityDataList", "customMessageConfiguration", "supportedCategories", "supportedActivityCodes", "activityDataList", "supportedCategories", "activityDataList", "supportedCategories", "activityDataList", "supportedActivityCodes", "activityDataList", "activityDataList", "activityMode", "activityDataList", "activityMode", "activityDataList", "activityDataList", "activityDataList", "bestActivities", "msg", "shareData", "activityMode"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$0", "L$2", "L$0", "L$2", "L$0", "L$0", "L$0", "L$0", "L$1", "L$2", "L$3"})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Context $context;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Context context, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:111:0x034b  */
            /* JADX WARN: Removed duplicated region for block: B:114:0x0357  */
            /* JADX WARN: Removed duplicated region for block: B:134:0x040f  */
            /* JADX WARN: Removed duplicated region for block: B:139:0x0449  */
            /* JADX WARN: Removed duplicated region for block: B:142:0x045f  */
            /* JADX WARN: Removed duplicated region for block: B:143:0x0472  */
            /* JADX WARN: Removed duplicated region for block: B:146:0x047e  */
            /* JADX WARN: Removed duplicated region for block: B:213:0x07ad  */
            /* JADX WARN: Removed duplicated region for block: B:218:0x07cd  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x012f  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x0162  */
            /* JADX WARN: Removed duplicated region for block: B:42:0x0178  */
            /* JADX WARN: Removed duplicated region for block: B:54:0x01c5  */
            /* JADX WARN: Removed duplicated region for block: B:57:0x01dc  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x0207 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:63:0x020c  */
            /* JADX WARN: Removed duplicated region for block: B:66:0x0222  */
            /* JADX WARN: Removed duplicated region for block: B:69:0x0247 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:72:0x024c  */
            /* JADX WARN: Removed duplicated region for block: B:75:0x0263  */
            /* JADX WARN: Removed duplicated region for block: B:86:0x02b1  */
            /* JADX WARN: Removed duplicated region for block: B:93:0x02de  */
            /* JADX WARN: Type inference failed for: r13v0, types: [T, java.util.ArrayList] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:103:0x032b -> B:105:0x032e). Please submit an issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:120:0x03c9 -> B:122:0x03cc). Please submit an issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:135:0x0442 -> B:137:0x0445). Please submit an issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x015b -> B:37:0x015e). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 2216
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.performance.custommessage.Nudge2BestActivityManager.Companion.a.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void calculateNudge2AndSendToBLE(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(context, null), 2, null);
        }
    }
}
