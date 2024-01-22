package com.coveiot.repository.manualdata.datasources.server;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.manualdata.GetManualBPDataRes;
import com.coveiot.coveaccess.manualdata.ManualDataApiManager;
import com.coveiot.coveaccess.manualdata.SaveManualBPDataReq;
import com.coveiot.coveaccess.manualdata.SaveManualBpDataRes;
import com.coveiot.coveaccess.manualdata.SaveManualMixedDataReq;
import com.coveiot.coveaccess.manualdata.SaveManualMixedDataRes;
import com.coveiot.coveaccess.manualdata.model.ManualBpData;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.repository.Error;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
import com.coveiot.repository.manualdata.datasources.server.ManualDataApiHandler;
import com.coveiot.repository.manualdata.datasources.server.ServerFormator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class ManualDataApiHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public static final class Companion {

        /* loaded from: classes9.dex */
        public interface ManualDataApiListner {
            void onError(@NotNull String str);

            void onManualDataReceived(@NotNull List<? extends EntityManualData> list);
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void fetchManualBPDataFromServer(@NotNull final Context context, @NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final ManualDataApiListner listner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fromDate, "fromDate");
            Intrinsics.checkNotNullParameter(toDate, "toDate");
            Intrinsics.checkNotNullParameter(listner, "listner");
            ManualDataApiManager.getManualBPFrom(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), new CoveApiListener<GetManualBPDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.manualdata.datasources.server.ManualDataApiHandler$Companion$fetchManualBPDataFromServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ManualDataApiHandler.Companion.ManualDataApiListner manualDataApiListner = listner;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    manualDataApiListner.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetManualBPDataRes getManualBPDataRes) {
                    if (getManualBPDataRes != null) {
                        if (getManualBPDataRes.getManualBpDataList() != null && getManualBPDataRes.getManualBpDataList().size() > 0) {
                            ArrayList arrayList = new ArrayList();
                            for (ManualBpData manualBp : getManualBPDataRes.getManualBpDataList()) {
                                ServerFormator.Companion companion = ServerFormator.Companion;
                                Intrinsics.checkNotNullExpressionValue(manualBp, "manualBp");
                                String macAddress = BleApiManager.getInstance(context).getBleApi().getMacAddress();
                                String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
                                Intrinsics.checkNotNullExpressionValue(userDeviceID, "getInstance().userDeviceID");
                                arrayList.add(companion.getEntityFromServerObject(manualBp, macAddress, userDeviceID));
                            }
                            listner.onManualDataReceived(arrayList);
                            return;
                        }
                        listner.onManualDataReceived(CollectionsKt__CollectionsKt.emptyList());
                    }
                }
            });
        }

        public final void saveManualBpDataToServer(@NotNull List<? extends EntityManualData> manualDataList, @NotNull final SyncResultListner listner) {
            Intrinsics.checkNotNullParameter(manualDataList, "manualDataList");
            Intrinsics.checkNotNullParameter(listner, "listner");
            ArrayList arrayList = new ArrayList();
            for (EntityManualData entityManualData : manualDataList) {
                arrayList.add(ServerFormator.Companion.getServerObjectEntity(entityManualData));
            }
            ManualDataApiManager.saveBPData(new SaveManualBPDataReq(arrayList), new CoveApiListener<SaveManualBpDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.manualdata.datasources.server.ManualDataApiHandler$Companion$saveManualBpDataToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    if (coveApiErrorModel != null) {
                        SyncResultListner syncResultListner = SyncResultListner.this;
                        String msg = coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                        syncResultListner.onFailure(new Error(msg, Integer.valueOf(coveApiErrorModel.getCode()), "api"));
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveManualBpDataRes saveManualBpDataRes) {
                    SyncResultListner.this.onSuccess();
                }
            });
        }

        public final void saveManualHRVStressDataToServer(@NotNull List<? extends EntityManualData> manualDataList, @NotNull final SyncResultListner listner) {
            Intrinsics.checkNotNullParameter(manualDataList, "manualDataList");
            Intrinsics.checkNotNullParameter(listner, "listner");
            ArrayList arrayList = new ArrayList();
            for (EntityManualData entityManualData : manualDataList) {
                arrayList.add(ServerFormator.Companion.getHRVStressServerObjectEntity(entityManualData));
            }
            ManualDataApiManager.saveMixedData(new SaveManualMixedDataReq(arrayList), new CoveApiListener<SaveManualMixedDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.manualdata.datasources.server.ManualDataApiHandler$Companion$saveManualHRVStressDataToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    if (coveApiErrorModel != null) {
                        SyncResultListner syncResultListner = SyncResultListner.this;
                        String msg = coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                        syncResultListner.onFailure(new Error(msg, Integer.valueOf(coveApiErrorModel.getCode()), "api"));
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveManualMixedDataRes saveManualMixedDataRes) {
                    SyncResultListner.this.onSuccess();
                }
            });
        }
    }
}
