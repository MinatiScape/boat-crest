package com.coveiot.android.fitnesschallenges.paging;

import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessChallengePagingSource extends PagingSource<Integer, BuddiesChallengeRes.Item> {
    @NotNull
    public final CoroutineScope b;
    @NotNull
    public final String c;
    public final int d;
    @NotNull
    public final FitnessChallengeListListener e;
    public int f;
    public int g;

    /* loaded from: classes2.dex */
    public interface FitnessChallengeListListener {
        void shouldShowEmptyLayout(boolean z);

        void shouldShowProgressBar(boolean z);
    }

    @DebugMetadata(c = "com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource", f = "FitnessChallengePagingSource.kt", i = {0, 0, 0, 1, 1, 1, 2, 2}, l = {140, 141, 144}, m = "load", n = {"this", "response", DeviceKey.position, "this", "response", DeviceKey.position, "this", DeviceKey.position}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0", "L$0", "I$0"})
    /* loaded from: classes2.dex */
    public static final class a extends ContinuationImpl {
        public int I$0;
        public int I$1;
        public Object L$0;
        public Object L$1;
        public Object L$2;
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
            return FitnessChallengePagingSource.this.load(null, this);
        }
    }

    public FitnessChallengePagingSource(@NotNull CoroutineScope viewModelScope, @NotNull String type, int i, @NotNull FitnessChallengeListListener listener) {
        Intrinsics.checkNotNullParameter(viewModelScope, "viewModelScope");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b = viewModelScope;
        this.c = type;
        this.d = i;
        this.e = listener;
    }

    @NotNull
    public final FitnessChallengeListListener getListener() {
        return this.e;
    }

    @NotNull
    public final String getType() {
        return this.c;
    }

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.b;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b5 A[Catch: Exception -> 0x011f, TryCatch #0 {Exception -> 0x011f, blocks: (B:14:0x0037, B:45:0x00f8, B:47:0x0105, B:49:0x010d, B:53:0x0119, B:52:0x0114, B:19:0x0054, B:41:0x00d8, B:22:0x006c, B:33:0x00ac, B:37:0x00b9, B:36:0x00b5, B:25:0x0073, B:27:0x007b, B:29:0x0082), top: B:58:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00cf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0105 A[Catch: Exception -> 0x011f, TryCatch #0 {Exception -> 0x011f, blocks: (B:14:0x0037, B:45:0x00f8, B:47:0x0105, B:49:0x010d, B:53:0x0119, B:52:0x0114, B:19:0x0054, B:41:0x00d8, B:22:0x006c, B:33:0x00ac, B:37:0x00b9, B:36:0x00b5, B:25:0x0073, B:27:0x007b, B:29:0x0082), top: B:58:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0114 A[Catch: Exception -> 0x011f, TryCatch #0 {Exception -> 0x011f, blocks: (B:14:0x0037, B:45:0x00f8, B:47:0x0105, B:49:0x010d, B:53:0x0119, B:52:0x0114, B:19:0x0054, B:41:0x00d8, B:22:0x006c, B:33:0x00ac, B:37:0x00b9, B:36:0x00b5, B:25:0x0073, B:27:0x007b, B:29:0x0082), top: B:58:0x0029 }] */
    @Override // androidx.paging.PagingSource
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull androidx.paging.PagingSource.LoadParams<java.lang.Integer> r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.paging.PagingSource.LoadResult<java.lang.Integer, com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes.Item>> r19) {
        /*
            Method dump skipped, instructions count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.load(androidx.paging.PagingSource$LoadParams, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.paging.PagingSource
    @Nullable
    public Integer getRefreshKey(@NotNull PagingState<Integer, BuddiesChallengeRes.Item> state) {
        Integer nextKey;
        Integer valueOf;
        Integer prevKey;
        Intrinsics.checkNotNullParameter(state, "state");
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition != null) {
            int intValue = anchorPosition.intValue();
            PagingSource.LoadResult.Page<Integer, BuddiesChallengeRes.Item> closestPageToPosition = state.closestPageToPosition(intValue);
            if (closestPageToPosition == null || (prevKey = closestPageToPosition.getPrevKey()) == null) {
                PagingSource.LoadResult.Page<Integer, BuddiesChallengeRes.Item> closestPageToPosition2 = state.closestPageToPosition(intValue);
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