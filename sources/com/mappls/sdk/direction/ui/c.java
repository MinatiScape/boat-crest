package com.mappls.sdk.direction.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse;
/* loaded from: classes11.dex */
public final class c extends ViewModel {

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<com.mappls.sdk.direction.ui.common.a<CostEstimationResponse>> f12581a = new MutableLiveData<>();

    /* loaded from: classes11.dex */
    public class a implements OnResponseCallback<CostEstimationResponse> {
        public a() {
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onError(int i, String str) {
            c.this.f12581a.postValue(new com.mappls.sdk.direction.ui.common.a<>(2, null, "Something went wrong"));
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onSuccess(CostEstimationResponse costEstimationResponse) {
            c.this.f12581a.postValue(new com.mappls.sdk.direction.ui.common.a<>(1, costEstimationResponse, null));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0037, code lost:
        if (r7.equals(com.mappls.sdk.services.api.costestimation.CostEstimationCriteria.VEHICLE_FUEL_TYPE_CNG) == false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.String r5, int r6, java.lang.String r7, java.lang.Integer r8, java.lang.Double r9, java.lang.String r10) {
        /*
            r4 = this;
            androidx.lifecycle.MutableLiveData<com.mappls.sdk.direction.ui.common.a<com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse>> r0 = r4.f12581a
            com.mappls.sdk.direction.ui.common.a r1 = new com.mappls.sdk.direction.ui.common.a
            r2 = 3
            r3 = 0
            r1.<init>(r2, r3, r3)
            r0.postValue(r1)
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimation$Builder r0 = com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.builder()
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimation$Builder r5 = r0.routeId(r5)
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimation$Builder r5 = r5.isTollEnabled(r0)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimation$Builder r5 = r5.routeIndex(r6)
            if (r7 == 0) goto L69
            r5.vehicleFuelType(r7)
            r6 = -1
            int r0 = r7.hashCode()
            switch(r0) {
                case -1331959846: goto L50;
                case -991657904: goto L45;
                case -17124067: goto L3a;
                case 66876: goto L31;
                default: goto L2f;
            }
        L2f:
            r2 = r6
            goto L5a
        L31:
            java.lang.String r0 = "CNG"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L5a
            goto L2f
        L3a:
            java.lang.String r0 = "electric"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L43
            goto L2f
        L43:
            r2 = 2
            goto L5a
        L45:
            java.lang.String r0 = "petrol"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L4e
            goto L2f
        L4e:
            r2 = 1
            goto L5a
        L50:
            java.lang.String r0 = "diesel"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L59
            goto L2f
        L59:
            r2 = 0
        L5a:
            switch(r2) {
                case 0: goto L64;
                case 1: goto L64;
                case 2: goto L61;
                case 3: goto L5e;
                default: goto L5d;
            }
        L5d:
            goto L69
        L5e:
            java.lang.String r6 = "km/kg"
            goto L66
        L61:
            java.lang.String r6 = "km/unit"
            goto L66
        L64:
            java.lang.String r6 = "km/l"
        L66:
            r5.fuelEfficiencyUnit(r6)
        L69:
            if (r8 == 0) goto L6e
            r5.fuelEfficiency(r8)
        L6e:
            if (r9 == 0) goto L73
            r5.fuelPrice(r9)
        L73:
            java.lang.String r6 = "driving"
            boolean r6 = r10.equalsIgnoreCase(r6)
            if (r6 == 0) goto L81
            java.lang.String r6 = "2AxlesAuto"
        L7d:
            r5.vehicleType(r6)
            goto L97
        L81:
            java.lang.String r6 = "biking"
            boolean r6 = r10.equalsIgnoreCase(r6)
            if (r6 == 0) goto L8c
            java.lang.String r6 = "2AxlesMoto"
            goto L7d
        L8c:
            java.lang.String r6 = "trucking"
            boolean r6 = r10.equalsIgnoreCase(r6)
            if (r6 == 0) goto L97
            java.lang.String r6 = "2AxlesTruck"
            goto L7d
        L97:
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimation r5 = r5.build()
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimationManager r5 = com.mappls.sdk.services.api.costestimation.MapplsCostEstimationManager.newInstance(r5)
            com.mappls.sdk.direction.ui.c$a r6 = new com.mappls.sdk.direction.ui.c$a
            r6.<init>()
            r5.call(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.direction.ui.c.a(java.lang.String, int, java.lang.String, java.lang.Integer, java.lang.Double, java.lang.String):void");
    }
}
