package com.coveiot.android.activitymodes.workoutVideos;

import android.content.Context;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import com.coveiot.android.activitymodes.workoutVideos.models.WorkoutVideosBean;
import com.coveiot.coveaccess.model.server.SWorkoutVideosList;
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
/* loaded from: classes2.dex */
public final class WorkoutVideosPagingSource extends PagingSource<Integer, WorkoutVideosBean> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int NETWORK_PAGE_SIZE = 10;
    @NotNull
    public static final String TAG = "WorkoutVideosPagingSource";
    @NotNull
    public final Context b;
    @Nullable
    public final String c;
    @Nullable
    public final String d;
    @NotNull
    public final ProgressListener e;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public interface ProgressListener {
        void getTotalVideoCount(int i);

        void shouldShowProgressBar(boolean z);
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource", f = "WorkoutVideosPagingSource.kt", i = {0, 0, 0}, l = {143}, m = "load", n = {"this", "loadResult", DeviceKey.position}, s = {"L$0", "L$1", "I$0"})
    /* loaded from: classes2.dex */
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
            return WorkoutVideosPagingSource.this.load(null, this);
        }
    }

    public WorkoutVideosPagingSource(@NotNull Context mContext, @Nullable String str, @Nullable String str2, @NotNull ProgressListener listner) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.b = mContext;
        this.c = str;
        this.d = str2;
        this.e = listner;
    }

    @NotNull
    public final ProgressListener getListner() {
        return this.e;
    }

    @NotNull
    public final Context getMContext() {
        return this.b;
    }

    @NotNull
    public final List<WorkoutVideosBean> getWorkoutVideosBeans(@Nullable List<? extends SWorkoutVideosList.DataBean.ItemsBean> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && (!list.isEmpty())) {
            for (SWorkoutVideosList.DataBean.ItemsBean itemsBean : list) {
                String videoId = itemsBean.getVideoId();
                Intrinsics.checkNotNullExpressionValue(videoId, "itemBean.videoId");
                String ytVideoId = itemsBean.getYtVideoId();
                Intrinsics.checkNotNullExpressionValue(ytVideoId, "itemBean.ytVideoId");
                arrayList.add(new WorkoutVideosBean(videoId, ytVideoId, itemsBean.getTitle(), itemsBean.getDescription(), itemsBean.getDuration(), itemsBean.getVideoUrl(), itemsBean.getThumbnailUrl(), itemsBean.getCategoryIds()));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Type inference failed for: r2v4, types: [T, androidx.paging.PagingSource$LoadResult$Error] */
    @Override // androidx.paging.PagingSource
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull androidx.paging.PagingSource.LoadParams<java.lang.Integer> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.paging.PagingSource.LoadResult<java.lang.Integer, com.coveiot.android.activitymodes.workoutVideos.models.WorkoutVideosBean>> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource.a
            if (r0 == 0) goto L13
            r0 = r9
            com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource$a r0 = (com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource.a) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource$a r0 = new com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource$a
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r0 = r0.L$0
            com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource r0 = (com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto Lb5
        L32:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3a:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r8 = r8.getKey()
            java.lang.Integer r8 = (java.lang.Integer) r8
            if (r8 == 0) goto L4a
            int r8 = r8.intValue()
            goto L4b
        L4a:
            r8 = 0
        L4b:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r2 = "load->pageIndex "
            r9.append(r2)
            r9.append(r8)
            java.lang.String r2 = ", perPageMaxSize 10"
            r9.append(r2)
            java.lang.String r9 = r9.toString()
            java.lang.String r2 = "WorkoutVideosPagingSource"
            com.coveiot.utils.utility.LogHelper.d(r2, r9)
            kotlin.jvm.internal.Ref$ObjectRef r9 = new kotlin.jvm.internal.Ref$ObjectRef
            r9.<init>()
            androidx.paging.PagingSource$LoadResult$Error r2 = new androidx.paging.PagingSource$LoadResult$Error
            com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource$load$loadResult$1 r4 = new com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource$load$loadResult$1
            r4.<init>()
            r2.<init>(r4)
            r9.element = r2
            r0.L$0 = r7
            r0.L$1 = r9
            r0.I$0 = r8
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r2 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r0)
            r2.<init>(r4, r3)
            r2.initCancellability()
            com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource$ProgressListener r4 = r7.getListner()
            r4.shouldShowProgressBar(r3)
            com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource$load$2$1 r3 = new com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource$load$2$1
            r3.<init>()
            java.lang.String r4 = access$getCategory$p(r7)
            r5 = 10
            java.lang.String r6 = access$getWorkoutType$p(r7)
            com.coveiot.coveaccess.workoutvideos.WorkoutVideosManager.getVideosList(r3, r4, r8, r5, r6)
            java.lang.Object r8 = r2.getResult()
            java.lang.Object r2 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            if (r8 != r2) goto Lb1
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        Lb1:
            if (r8 != r1) goto Lb4
            return r1
        Lb4:
            r8 = r9
        Lb5:
            T r8 = r8.element
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource.load(androidx.paging.PagingSource$LoadParams, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.paging.PagingSource
    @Nullable
    public Integer getRefreshKey(@NotNull PagingState<Integer, WorkoutVideosBean> state) {
        Integer nextKey;
        Integer valueOf;
        Integer prevKey;
        Intrinsics.checkNotNullParameter(state, "state");
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition != null) {
            int intValue = anchorPosition.intValue();
            PagingSource.LoadResult.Page<Integer, WorkoutVideosBean> closestPageToPosition = state.closestPageToPosition(intValue);
            if (closestPageToPosition == null || (prevKey = closestPageToPosition.getPrevKey()) == null) {
                PagingSource.LoadResult.Page<Integer, WorkoutVideosBean> closestPageToPosition2 = state.closestPageToPosition(intValue);
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
