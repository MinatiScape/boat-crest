package com.coveiot.android.leonardo.more.viewmodel;

import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SWatchFaceList;
import com.coveiot.coveaccess.watchface.WatchFaceApiManager;
import com.coveiot.coveaccess.watchface.model.WatchfaceByIdRequest;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.more.viewmodel.MyWatchViewModel$downloadWatchFaceFromServer$1", f = "MyWatchViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class MyWatchViewModel$downloadWatchFaceFromServer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ WatchFaceBean $watchFaceBean;
    public int label;
    public final /* synthetic */ MyWatchViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyWatchViewModel$downloadWatchFaceFromServer$1(MyWatchViewModel myWatchViewModel, WatchFaceBean watchFaceBean, Continuation<? super MyWatchViewModel$downloadWatchFaceFromServer$1> continuation) {
        super(2, continuation);
        this.this$0 = myWatchViewModel;
        this.$watchFaceBean = watchFaceBean;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new MyWatchViewModel$downloadWatchFaceFromServer$1(this.this$0, this.$watchFaceBean, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((MyWatchViewModel$downloadWatchFaceFromServer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (AppUtils.isNetConnected(this.this$0.getContext())) {
                WatchFaceBean watchFaceBean = this.$watchFaceBean;
                if (watchFaceBean != null) {
                    String uid = watchFaceBean.getUid();
                    if (!(uid == null || uid.length() == 0)) {
                        WatchfaceByIdRequest watchfaceByIdRequest = new WatchfaceByIdRequest();
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(this.$watchFaceBean.getUid());
                        watchfaceByIdRequest.setUids(arrayList);
                        final WatchFaceBean watchFaceBean2 = this.$watchFaceBean;
                        final MyWatchViewModel myWatchViewModel = this.this$0;
                        WatchFaceApiManager.getWatchFaceListById(new CoveApiListener<SWatchFaceList, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.MyWatchViewModel$downloadWatchFaceFromServer$1.1
                            @Override // com.coveiot.coveaccess.CoveApiListener
                            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                                LogHelper.d(myWatchViewModel.getTAG(), "downloadWatchFaceFromServer failed.");
                            }

                            @Override // com.coveiot.coveaccess.CoveApiListener
                            public void onSuccess(@Nullable SWatchFaceList sWatchFaceList) {
                                if (sWatchFaceList == null || sWatchFaceList.getData() == null) {
                                    return;
                                }
                                List<SWatchFaceList.DataBean.ItemsBean> items = sWatchFaceList.getData().getItems();
                                if (items == null || items.isEmpty()) {
                                    return;
                                }
                                WatchFaceBean.this.setPreviewImageUrl(sWatchFaceList.getData().getItems().get(0).getPreviewImageUrl());
                                myWatchViewModel.getWatchFaceDownloadLiveData().postValue(WatchFaceBean.this);
                            }
                        }, watchfaceByIdRequest);
                    }
                }
                LogHelper.d(this.this$0.getTAG(), "No internet connection.");
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
