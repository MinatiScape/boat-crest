package com.coveiot.android.qrtray.viewmodel;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.DeleteImageRequest;
import com.coveiot.android.bleabstract.request.GetQRCodeRequest;
import com.coveiot.android.bleabstract.request.QRTrayCodeRequest;
import com.coveiot.android.bleabstract.request.SendImageRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.qrtray.R;
import com.coveiot.android.qrtray.utils.QRCodeUtils;
import com.coveiot.android.qrtray.utils.SingleLiveEvent;
import com.coveiot.android.qrtray.viewmodel.QRTrayViewModel;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.mediauplaod.model.MediaListBean;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.qrtray.CoveQRTrayApi;
import com.coveiot.coveaccess.qrtray.model.QRTrayCategoriesRes;
import com.coveiot.coveaccess.qrtray.model.QRTrayCodeIDsReq;
import com.coveiot.coveaccess.qrtray.model.QRTrayCodesRes;
import com.coveiot.coveaccess.qrtray.model.QRTraySaveReq;
import com.coveiot.coveaccess.qrtray.model.QRTraySaveRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.QRTrayCodeData;
import com.coveiot.sdk.ble.model.QRCodeData;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Marker;
/* loaded from: classes5.dex */
public final class QRTrayViewModel extends AndroidViewModel {
    @NotNull
    public final SingleLiveEvent<ArrayList<QRTrayCategoriesRes.QRItem>> d;
    @NotNull
    public final MutableLiveData<List<QRTrayCodesRes.QRTrayCodeData>> e;
    @NotNull
    public Context f;
    @Nullable
    public QRTrayViewModelContract g;
    @NotNull
    public final SingleLiveEvent<ArrayList<QRCodeData>> h;
    @NotNull
    public final QRTrayCodeData i;

    /* loaded from: classes5.dex */
    public interface QRTrayViewModelContract {
        void deleteQRCodeMetaDataCheck(boolean z);

        void onDeleteImageCheck(boolean z);

        void onDeleteQRCheckFromServer(boolean z);

        void onEditCheck(boolean z, boolean z2);

        void onFailure(@Nullable String str);

        void onSuccess(boolean z);

        void uploadingImageCheck(boolean z);

        void uploadingQRCodeMetaDataCheck(boolean z);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QRTrayViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = new SingleLiveEvent<>();
        this.e = new MutableLiveData<>();
        Application application2 = getApplication();
        Intrinsics.checkNotNullExpressionValue(application2, "getApplication()");
        this.f = application2;
        this.h = new SingleLiveEvent<>();
        QRTrayCodeData qRTrayCodeDetails = SessionManager.getInstance(this.f).getQRTrayCodeDetails();
        Intrinsics.checkNotNullExpressionValue(qRTrayCodeDetails, "getInstance(mContext).qrTrayCodeDetails");
        this.i = qRTrayCodeDetails;
    }

    public static /* synthetic */ void editQRCodeToServer$default(QRTrayViewModel qRTrayViewModel, QRTraySaveReq qRTraySaveReq, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        qRTrayViewModel.editQRCodeToServer(qRTraySaveReq, str);
    }

    public final void checkQRCodesFromWatch() {
        BleApiManager.getInstance(this.f).getBleApi().getData(new GetQRCodeRequest(), new DataResultListener() { // from class: com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$checkQRCodesFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.onFailure(error.getErrorMsg());
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                QRTrayViewModel.this.getQrCodeSFromWatch().postValue((ArrayList) response.getResponseData());
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void deleteQRCodeFromServer(@NotNull List<? extends QRTrayCodesRes.QRTrayCodeData> qrCodeDataList) {
        Intrinsics.checkNotNullParameter(qrCodeDataList, "qrCodeDataList");
        ArrayList arrayList = new ArrayList();
        for (QRTrayCodesRes.QRTrayCodeData qRTrayCodeData : qrCodeDataList) {
            String id = qRTrayCodeData.getId();
            Intrinsics.checkNotNullExpressionValue(id, "qrCode.id");
            arrayList.add(id);
        }
        CoveQRTrayApi.deleteQRTray(new QRTrayCodeIDsReq(arrayList), new CoveApiListener<CommonResponseClass, CoveApiErrorModel>() { // from class: com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$deleteQRCodeFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                Intrinsics.checkNotNullParameter(object, "object");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.onDeleteQRCheckFromServer(false);
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseClass commonResponseClass) {
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.onDeleteQRCheckFromServer(true);
                }
            }
        });
    }

    public final void deleteQRCodeImageFromWatch(int i) {
        BleApiManager.getInstance(this.f).getBleApi().setUserSettings(new DeleteImageRequest(i), new SettingsResultListener() { // from class: com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$deleteQRCodeImageFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.onDeleteImageCheck(false);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.onDeleteImageCheck(true);
                }
            }
        });
    }

    public final void deleteQRCodesMetaDataToWatch(@NotNull ArrayList<QRCodeData> qrCodes) {
        Intrinsics.checkNotNullParameter(qrCodes, "qrCodes");
        BleApiManager.getInstance(this.f).getBleApi().setUserSettings(new QRTrayCodeRequest(qrCodes), new SettingsResultListener() { // from class: com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$deleteQRCodesMetaDataToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.deleteQRCodeMetaDataCheck(false);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.deleteQRCodeMetaDataCheck(true);
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Object] */
    public final void editQRCodeToServer(@NotNull final QRTraySaveReq qRTraySaveEditReq, @NotNull String currentID) {
        Intrinsics.checkNotNullParameter(qRTraySaveEditReq, "qRTraySaveEditReq");
        Intrinsics.checkNotNullParameter(currentID, "currentID");
        String mediaId = qRTraySaveEditReq.getMediaId();
        qRTraySaveEditReq.setMediaId(mediaId != null ? StringsKt__StringsKt.substringAfterLast$default(mediaId, "media/", (String) null, 2, (Object) null) : null);
        if (currentID.length() == 0) {
            currentID = qRTraySaveEditReq.getPreviousAppliedId();
        }
        CoveQRTrayApi.editQRTrayCode(qRTraySaveEditReq, currentID, new CoveApiListener<CommonResponseGeneric<QRTraySaveRes>, CoveApiErrorModel>() { // from class: com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$editQRCodeToServer$2
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                Intrinsics.checkNotNullParameter(object, "object");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    Boolean applied = qRTraySaveEditReq.getApplied();
                    Intrinsics.checkNotNullExpressionValue(applied, "qRTraySaveEditReq.applied");
                    listener.onEditCheck(applied.booleanValue(), false);
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseGeneric<QRTraySaveRes> commonResponseGeneric) {
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    Boolean applied = qRTraySaveEditReq.getApplied();
                    Intrinsics.checkNotNullExpressionValue(applied, "qRTraySaveEditReq.applied");
                    listener.onEditCheck(applied.booleanValue(), true);
                }
            }
        });
    }

    public final void getCategories() {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new QRTrayViewModel$getCategories$1(this, null), 2, null);
    }

    @NotNull
    public final SingleLiveEvent<ArrayList<QRTrayCategoriesRes.QRItem>> getCategoryMutableList() {
        return this.d;
    }

    @Nullable
    public final QRTrayViewModelContract getListener() {
        return this.g;
    }

    public final void getQRCodesFromServer() {
        if (AppUtils.isNetConnected(this.f)) {
            CoveQRTrayApi.getQRTrayCodes(new CoveApiListener<QRTrayCodesRes, CoveApiErrorModel>() { // from class: com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$getQRCodesFromServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@NotNull CoveApiErrorModel object) {
                    Intrinsics.checkNotNullParameter(object, "object");
                    QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                    if (listener != null) {
                        listener.onFailure(object.getMsg());
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable QRTrayCodesRes qRTrayCodesRes) {
                    Context context;
                    if (qRTrayCodesRes != null) {
                        if (qRTrayCodesRes.getItems() != null) {
                            QRTrayViewModel.this.getQrCodeMutableList().postValue(qRTrayCodesRes.getItems());
                            return;
                        } else {
                            QRTrayViewModel.this.getQrCodeMutableList().postValue(CollectionsKt__CollectionsKt.emptyList());
                            return;
                        }
                    }
                    QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                    if (listener != null) {
                        context = QRTrayViewModel.this.f;
                        listener.onFailure(context.getString(R.string.something_went_wrong));
                    }
                }
            });
            return;
        }
        QRTrayViewModelContract qRTrayViewModelContract = this.g;
        if (qRTrayViewModelContract != null) {
            qRTrayViewModelContract.onFailure(this.f.getString(R.string.no_internet_connection));
        }
    }

    @NotNull
    public final QRTrayCodeData getQrCodeDetailsOfWatchFromFirebaseRemoteConfig() {
        return this.i;
    }

    @NotNull
    public final MutableLiveData<List<QRTrayCodesRes.QRTrayCodeData>> getQrCodeMutableList() {
        return this.e;
    }

    @NotNull
    public final SingleLiveEvent<ArrayList<QRCodeData>> getQrCodeSFromWatch() {
        return this.h;
    }

    public final void saveQRCodeToServer(@NotNull final QRTraySaveReq qrTraySaveEditReq) {
        Intrinsics.checkNotNullParameter(qrTraySaveEditReq, "qrTraySaveEditReq");
        CoveQRTrayApi.saveQRTrayCode(qrTraySaveEditReq, new CoveApiListener<CommonResponseGeneric<QRTraySaveRes>, CoveApiErrorModel>() { // from class: com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$saveQRCodeToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                Intrinsics.checkNotNullParameter(object, "object");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.onFailure(object.getMsg());
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseGeneric<QRTraySaveRes> commonResponseGeneric) {
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    Boolean applied = qrTraySaveEditReq.getApplied();
                    Intrinsics.checkNotNullExpressionValue(applied, "qrTraySaveEditReq.applied");
                    listener.onSuccess(applied.booleanValue());
                }
            }
        });
    }

    public final void setListener(@Nullable QRTrayViewModelContract qRTrayViewModelContract) {
        this.g = qRTrayViewModelContract;
    }

    public final void setQRCodesMetaDataToWatch(@NotNull ArrayList<QRCodeData> qrCodes) {
        Intrinsics.checkNotNullParameter(qrCodes, "qrCodes");
        BleApiManager.getInstance(this.f).getBleApi().setUserSettings(new QRTrayCodeRequest(qrCodes), new SettingsResultListener() { // from class: com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$setQRCodesMetaDataToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.uploadingQRCodeMetaDataCheck(false);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.uploadingQRCodeMetaDataCheck(true);
                }
            }
        });
    }

    public final void uploadQRCodeImageToWatch(@NotNull QRCodeData qrCodeData, @NotNull Bitmap qrCodeBitmap) {
        Intrinsics.checkNotNullParameter(qrCodeData, "qrCodeData");
        Intrinsics.checkNotNullParameter(qrCodeBitmap, "qrCodeBitmap");
        String resolution = this.i.getResolution();
        Intrinsics.checkNotNullExpressionValue(resolution, "qrCodeDetailsOfWatchFrom…seRemoteConfig.resolution");
        String[] strArr = (String[]) StringsKt__StringsKt.split$default((CharSequence) resolution, new String[]{Marker.ANY_MARKER}, false, 0, 6, (Object) null).toArray(new String[0]);
        QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
        BleApiManager.getInstance(this.f).getBleApi().getData(new SendImageRequest(qrCodeData.getImageId(), qRCodeUtils.saveBitmapAsJPEG(this.f, qRCodeUtils.resizeBitmap(qrCodeBitmap, Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]))), 0, 0, 0, 0, Integer.parseInt(strArr[1]), Integer.parseInt(strArr[0])), new DataResultListener() { // from class: com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$uploadQRCodeImageToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.uploadingImageCheck(false);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                if (listener != null) {
                    listener.uploadingImageCheck(true);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void uploadQRCodePicAndSaveToServer(@NotNull Bitmap qrCodeBitmap, @NotNull final QRTraySaveReq qRTraySaveEditReq) {
        Intrinsics.checkNotNullParameter(qrCodeBitmap, "qrCodeBitmap");
        Intrinsics.checkNotNullParameter(qRTraySaveEditReq, "qRTraySaveEditReq");
        CoveQRTrayApi.uploadQRTrayCode(QRCodeUtils.INSTANCE.saveBitmapAsJPEG(this.f, qrCodeBitmap), new CoveApiListener<MediaListBean, CoveApiErrorModel>() { // from class: com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$uploadQRCodePicAndSaveToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                QRTrayViewModel.QRTrayViewModelContract listener = this.getListener();
                if (listener != null) {
                    listener.onFailure(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable MediaListBean mediaListBean) {
                if (mediaListBean != null) {
                    if (mediaListBean.getMediaId() != null) {
                        QRTraySaveReq.this.setMediaId(mediaListBean.getMediaId());
                        this.saveQRCodeToServer(QRTraySaveReq.this);
                        return;
                    }
                    QRTrayViewModel.QRTrayViewModelContract listener = this.getListener();
                    if (listener != null) {
                        listener.onFailure(null);
                        return;
                    }
                    return;
                }
                QRTrayViewModel.QRTrayViewModelContract listener2 = this.getListener();
                if (listener2 != null) {
                    listener2.onFailure(null);
                }
            }
        });
    }
}
