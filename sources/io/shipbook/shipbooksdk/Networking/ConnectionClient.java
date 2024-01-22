package io.shipbook.shipbooksdk.Networking;

import com.google.firebase.analytics.FirebaseAnalytics;
import io.shipbook.shipbooksdk.Models.BaseObj;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0015\u0010\u0016J3\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ-\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0086Hø\u0001\u0000¢\u0006\u0004\b\n\u0010\fJ/\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\rR\"\u0010\u0014\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/shipbook/shipbooksdk/Networking/ConnectionClient;", "", "", "url", "", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "data", "Lio/shipbook/shipbooksdk/Networking/HttpMethod;", FirebaseAnalytics.Param.METHOD, "Lio/shipbook/shipbooksdk/Networking/ResponseData;", "request", "(Ljava/lang/String;Ljava/util/List;Lio/shipbook/shipbooksdk/Networking/HttpMethod;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/lang/String;Lio/shipbook/shipbooksdk/Models/BaseObj;Lio/shipbook/shipbooksdk/Networking/HttpMethod;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/String;Lio/shipbook/shipbooksdk/Networking/HttpMethod;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "a", "Ljava/lang/String;", "getBaseUrl", "()Ljava/lang/String;", "setBaseUrl", "(Ljava/lang/String;)V", "baseUrl", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ConnectionClient {
    public static final ConnectionClient INSTANCE = new ConnectionClient();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static String f14040a = "https://api.shipbook.io/v1/";

    @DebugMetadata(c = "io.shipbook.shipbooksdk.Networking.ConnectionClient", f = "ConnectionClient.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {82, 83}, m = "request", n = {"this", "url", "data", FirebaseAnalytics.Param.METHOD, "urlString", "obj", "$receiver", "stream", "responseDataString", "responseData", "this", "url", "data", FirebaseAnalytics.Param.METHOD, "urlString", "obj", "$receiver", "stream", "responseDataString", "responseData"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9"})
    /* loaded from: classes12.dex */
    public static final class a extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public Object L$5;
        public Object L$6;
        public Object L$7;
        public Object L$8;
        public Object L$9;
        public int label;
        public /* synthetic */ Object result;

        public a(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ConnectionClient.this.request((String) null, (String) null, (HttpMethod) null, this);
        }
    }

    @Nullable
    public static /* synthetic */ Object request$default(ConnectionClient connectionClient, String str, List list, HttpMethod httpMethod, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            httpMethod = HttpMethod.GET;
        }
        return connectionClient.request(str, list, httpMethod, continuation);
    }

    @NotNull
    public final String getBaseUrl() {
        return f14040a;
    }

    @Nullable
    public final Object request(@NotNull String str, @NotNull List<? extends BaseObj> list, @NotNull HttpMethod httpMethod, @NotNull Continuation<? super ResponseData> continuation) {
        JSONArray jSONArray = new JSONArray();
        for (BaseObj baseObj : list) {
            jSONArray.put(baseObj.toJson());
        }
        return request(str, jSONArray.toString(), httpMethod, continuation);
    }

    public final void setBaseUrl(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        f14040a = str;
    }

    @Nullable
    public static /* synthetic */ Object request$default(ConnectionClient connectionClient, String str, BaseObj baseObj, HttpMethod httpMethod, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            httpMethod = HttpMethod.GET;
        }
        String jSONObject = baseObj.toJson().toString();
        InlineMarker.mark(0);
        Object request = connectionClient.request(str, jSONObject, httpMethod, continuation);
        InlineMarker.mark(1);
        return request;
    }

    @Nullable
    public static /* synthetic */ Object request$default(ConnectionClient connectionClient, String str, String str2, HttpMethod httpMethod, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            httpMethod = HttpMethod.GET;
        }
        return connectionClient.request(str, str2, httpMethod, continuation);
    }

    @Nullable
    public final Object request(@NotNull String str, @NotNull BaseObj baseObj, @NotNull HttpMethod httpMethod, @NotNull Continuation<? super ResponseData> continuation) {
        return request(str, baseObj.toJson().toString(), httpMethod, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01dc A[Catch: Exception -> 0x00a2, TryCatch #3 {Exception -> 0x00a2, blocks: (B:13:0x005a, B:16:0x0060, B:17:0x0064, B:21:0x0095, B:24:0x009d, B:25:0x00a1, B:31:0x00e4, B:33:0x00ea, B:35:0x0128, B:38:0x0142, B:40:0x0187, B:45:0x0196, B:55:0x01a4, B:62:0x01dc, B:64:0x01e5, B:66:0x01f6, B:71:0x0204, B:67:0x01f9, B:63:0x01e1, B:41:0x018a, B:43:0x0190), top: B:109:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01e1 A[Catch: Exception -> 0x00a2, TryCatch #3 {Exception -> 0x00a2, blocks: (B:13:0x005a, B:16:0x0060, B:17:0x0064, B:21:0x0095, B:24:0x009d, B:25:0x00a1, B:31:0x00e4, B:33:0x00ea, B:35:0x0128, B:38:0x0142, B:40:0x0187, B:45:0x0196, B:55:0x01a4, B:62:0x01dc, B:64:0x01e5, B:66:0x01f6, B:71:0x0204, B:67:0x01f9, B:63:0x01e1, B:41:0x018a, B:43:0x0190), top: B:109:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01f6 A[Catch: Exception -> 0x00a2, TryCatch #3 {Exception -> 0x00a2, blocks: (B:13:0x005a, B:16:0x0060, B:17:0x0064, B:21:0x0095, B:24:0x009d, B:25:0x00a1, B:31:0x00e4, B:33:0x00ea, B:35:0x0128, B:38:0x0142, B:40:0x0187, B:45:0x0196, B:55:0x01a4, B:62:0x01dc, B:64:0x01e5, B:66:0x01f6, B:71:0x0204, B:67:0x01f9, B:63:0x01e1, B:41:0x018a, B:43:0x0190), top: B:109:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01f9 A[Catch: Exception -> 0x00a2, TRY_LEAVE, TryCatch #3 {Exception -> 0x00a2, blocks: (B:13:0x005a, B:16:0x0060, B:17:0x0064, B:21:0x0095, B:24:0x009d, B:25:0x00a1, B:31:0x00e4, B:33:0x00ea, B:35:0x0128, B:38:0x0142, B:40:0x0187, B:45:0x0196, B:55:0x01a4, B:62:0x01dc, B:64:0x01e5, B:66:0x01f6, B:71:0x0204, B:67:0x01f9, B:63:0x01e1, B:41:0x018a, B:43:0x0190), top: B:109:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0291 A[Catch: Exception -> 0x02c9, TRY_LEAVE, TryCatch #5 {Exception -> 0x02c9, blocks: (B:87:0x0289, B:89:0x0291, B:73:0x0235, B:75:0x0243, B:77:0x024b, B:79:0x0251, B:81:0x0258, B:83:0x0260, B:102:0x02bf, B:103:0x02c8, B:69:0x01ff), top: B:109:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02b2  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object request(@org.jetbrains.annotations.NotNull java.lang.String r24, @org.jetbrains.annotations.Nullable java.lang.String r25, @org.jetbrains.annotations.NotNull io.shipbook.shipbooksdk.Networking.HttpMethod r26, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.shipbook.shipbooksdk.Networking.ResponseData> r27) {
        /*
            Method dump skipped, instructions count: 747
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.shipbook.shipbooksdk.Networking.ConnectionClient.request(java.lang.String, java.lang.String, io.shipbook.shipbooksdk.Networking.HttpMethod, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
