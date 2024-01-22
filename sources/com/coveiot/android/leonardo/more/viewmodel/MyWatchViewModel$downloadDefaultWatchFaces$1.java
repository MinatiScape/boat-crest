package com.coveiot.android.leonardo.more.viewmodel;

import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfacecore.utils.Constants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SWatchFaceList;
import com.coveiot.coveaccess.watchface.WatchFaceApiManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.more.viewmodel.MyWatchViewModel$downloadDefaultWatchFaces$1", f = "MyWatchViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class MyWatchViewModel$downloadDefaultWatchFaces$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ MyWatchViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyWatchViewModel$downloadDefaultWatchFaces$1(MyWatchViewModel myWatchViewModel, Continuation<? super MyWatchViewModel$downloadDefaultWatchFaces$1> continuation) {
        super(2, continuation);
        this.this$0 = myWatchViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new MyWatchViewModel$downloadDefaultWatchFaces$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((MyWatchViewModel$downloadDefaultWatchFaces$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        String firmwareRevision;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (AppUtils.isNetConnected(this.this$0.getContext())) {
                BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this.this$0.getContext()).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                if (bleDeviceInfo != null && (firmwareRevision = bleDeviceInfo.getFirmwareRevision()) != null) {
                    final MyWatchViewModel myWatchViewModel = this.this$0;
                    WatchFaceApiManager.getWatchFaceList(new CoveApiListener<SWatchFaceList, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.MyWatchViewModel$downloadDefaultWatchFaces$1$1$1
                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        }

                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onSuccess(@Nullable SWatchFaceList sWatchFaceList) {
                            if (sWatchFaceList != null && sWatchFaceList.getData() != null) {
                                List<SWatchFaceList.DataBean.ItemsBean> items = sWatchFaceList.getData().getItems();
                                if (!(items == null || items.isEmpty())) {
                                    SWatchFaceList.DataBean.ItemsBean itemsBean = sWatchFaceList.getData().getItems().get(0);
                                    String uid = itemsBean.getUid();
                                    Intrinsics.checkNotNullExpressionValue(uid, "item.uid");
                                    String faceId = itemsBean.getFaceId();
                                    Intrinsics.checkNotNullExpressionValue(faceId, "item.faceId");
                                    MyWatchViewModel.this.getWatchFaceDownloadLiveData().postValue(new WatchFaceBean(uid, faceId, null, null, null, null, null, itemsBean.getPreviewImageUrl(), null, null, null, null, null, false, null, 32636, null));
                                }
                            }
                        }
                    }, null, Constants.WATCH_FACE_TYPE_DEFAULT, firmwareRevision, 0, 20);
                }
            } else {
                LogHelper.d(this.this$0.getTAG(), "internet is not connected.");
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
