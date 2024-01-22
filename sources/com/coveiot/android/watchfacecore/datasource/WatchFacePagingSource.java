package com.coveiot.android.watchfacecore.datasource;

import android.content.Context;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.coveaccess.model.server.SWatchFaceList;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class WatchFacePagingSource extends PagingSource<Integer, WatchFaceBean> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int NETWORK_PAGE_SIZE = 20;
    @NotNull
    public static final String TAG = "WatchFacePagingSource";
    @NotNull
    public final Context b;
    @NotNull
    public final String c;
    @Nullable
    public final String d;
    @Nullable
    public final String e;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource", f = "WatchFacePagingSource.kt", i = {0, 0, 0}, l = {123}, m = "load", n = {"this", "loadResult", DeviceKey.position}, s = {"L$0", "L$1", "I$0"})
    /* loaded from: classes7.dex */
    public static final class a extends ContinuationImpl {
        public int I$0;
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchFacePagingSource.this.load(null, this);
        }
    }

    public WatchFacePagingSource(@NotNull Context mContext, @NotNull String mFwVersion, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mFwVersion, "mFwVersion");
        this.b = mContext;
        this.c = mFwVersion;
        this.d = str;
        this.e = str2;
    }

    @NotNull
    public final Context getMContext() {
        return this.b;
    }

    @NotNull
    public final List<WatchFaceBean> getWatchFaceBeans(@Nullable List<? extends SWatchFaceList.DataBean.ItemsBean> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && (!list.isEmpty())) {
            for (SWatchFaceList.DataBean.ItemsBean itemsBean : list) {
                String uid = itemsBean.getUid();
                Intrinsics.checkNotNullExpressionValue(uid, "itemBean.uid");
                String faceId = itemsBean.getFaceId();
                Intrinsics.checkNotNullExpressionValue(faceId, "itemBean.faceId");
                String faceType = itemsBean.getFaceType();
                String title = itemsBean.getTitle();
                String downloadUrl = itemsBean.getDownloadUrl();
                String fileType = itemsBean.getFileType();
                String fileMd5Hash = itemsBean.getFileMd5Hash();
                String previewImageUrl = itemsBean.getPreviewImageUrl();
                List<String> tags = itemsBean.getTags();
                Integer seqNumber = itemsBean.getSeqNumber();
                arrayList.add(new WatchFaceBean(uid, faceId, faceType, title, downloadUrl, fileType, fileMd5Hash, previewImageUrl, tags, null, null, Integer.valueOf(seqNumber == null ? 0 : seqNumber.intValue()), null, false, itemsBean.getEditUrl(), 13824, null));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Type inference failed for: r12v5, types: [T, androidx.paging.PagingSource$LoadResult$Error] */
    @Override // androidx.paging.PagingSource
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull androidx.paging.PagingSource.LoadParams<java.lang.Integer> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.paging.PagingSource.LoadResult<java.lang.Integer, com.coveiot.android.watchfacecore.model.WatchFaceBean>> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource.a
            if (r0 == 0) goto L13
            r0 = r12
            com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource$a r0 = (com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource.a) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource$a r0 = new com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource$a
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r11 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r0 = r0.L$0
            com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource r0 = (com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource) r0
            kotlin.ResultKt.throwOnFailure(r12)
            goto Lb2
        L32:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3a:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r11 = r11.getKey()
            java.lang.Integer r11 = (java.lang.Integer) r11
            if (r11 == 0) goto L4a
            int r11 = r11.intValue()
            goto L4b
        L4a:
            r11 = 0
        L4b:
            r8 = r11
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "load->pageIndex "
            r11.append(r12)
            r11.append(r8)
            java.lang.String r12 = ", perPageMaxSize 20"
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            java.lang.String r12 = "WatchFacePagingSource"
            com.coveiot.utils.utility.LogHelper.d(r12, r11)
            kotlin.jvm.internal.Ref$ObjectRef r11 = new kotlin.jvm.internal.Ref$ObjectRef
            r11.<init>()
            androidx.paging.PagingSource$LoadResult$Error r12 = new androidx.paging.PagingSource$LoadResult$Error
            com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource$load$loadResult$1 r2 = new com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource$load$loadResult$1
            r2.<init>()
            r12.<init>(r2)
            r11.element = r12
            r0.L$0 = r10
            r0.L$1 = r11
            r0.I$0 = r8
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r12 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r0)
            r12.<init>(r2, r3)
            r12.initCancellability()
            com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource$load$2$1 r4 = new com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource$load$2$1
            r4.<init>()
            java.lang.String r5 = access$getEdition$p(r10)
            java.lang.String r6 = access$getFaceType$p(r10)
            java.lang.String r7 = access$getMFwVersion$p(r10)
            r9 = 20
            com.coveiot.coveaccess.watchface.WatchFaceApiManager.getWatchFaceList(r4, r5, r6, r7, r8, r9)
            java.lang.Object r12 = r12.getResult()
            java.lang.Object r2 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            if (r12 != r2) goto Laf
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        Laf:
            if (r12 != r1) goto Lb2
            return r1
        Lb2:
            T r11 = r11.element
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource.load(androidx.paging.PagingSource$LoadParams, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.paging.PagingSource
    @Nullable
    public Integer getRefreshKey(@NotNull PagingState<Integer, WatchFaceBean> state) {
        Integer nextKey;
        Integer valueOf;
        Integer prevKey;
        Intrinsics.checkNotNullParameter(state, "state");
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition != null) {
            int intValue = anchorPosition.intValue();
            PagingSource.LoadResult.Page<Integer, WatchFaceBean> closestPageToPosition = state.closestPageToPosition(intValue);
            if (closestPageToPosition == null || (prevKey = closestPageToPosition.getPrevKey()) == null) {
                PagingSource.LoadResult.Page<Integer, WatchFaceBean> closestPageToPosition2 = state.closestPageToPosition(intValue);
                if (closestPageToPosition2 == null || (nextKey = closestPageToPosition2.getNextKey()) == null) {
                    return null;
                }
                valueOf = Integer.valueOf(nextKey.intValue() - 1);
            } else {
                valueOf = Integer.valueOf(prevKey.intValue() + 1);
            }
            return valueOf;
        }
        return null;
    }
}
