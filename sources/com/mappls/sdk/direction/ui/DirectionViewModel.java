package com.mappls.sdk.direction.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.util.Pair;
import androidx.annotation.Keep;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mappls.sdk.direction.ui.model.DirectionOptions;
import com.mappls.sdk.direction.ui.model.StopModel;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.MapplsDirectionManager;
import com.mappls.sdk.services.api.directions.MapplsDirections;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.event.route.MapplsRouteSummary;
import com.mappls.sdk.services.api.event.route.MapplsRouteSummaryManager;
import com.mappls.sdk.services.api.event.route.model.RouteReportSummaryResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import timber.log.Timber;
@Keep
/* loaded from: classes11.dex */
public class DirectionViewModel extends ViewModel {
    private DirectionsResponse directionsResponse;
    public DirectionOptions options;
    private RouteReportSummaryResponse routeReportSummaryResponse;
    private int selectedIndex;
    private int theme;
    public MutableLiveData<com.mappls.sdk.direction.ui.common.a<DirectionsResponse>> directionResponseResourceLiveData = new MutableLiveData<>();
    public MutableLiveData<com.mappls.sdk.direction.ui.common.a<RouteReportSummaryResponse>> routeReportSummaryResourceLiveData = new MutableLiveData<>();
    public MutableLiveData<com.mappls.sdk.direction.ui.common.a<Pair<Integer, CostEstimationResponse>>> costEstimationResponseLiveData = new MutableLiveData<>();
    public String profile = "driving";
    private List<StopModel> stopModels = new ArrayList();
    public final HashMap<Integer, CostEstimationResponse> tollHashMap = new HashMap<>();

    /* loaded from: classes11.dex */
    public class a implements OnResponseCallback<DirectionsResponse> {
        public a() {
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onError(int i, String str) {
            Timber.d("Directions:- %s", str);
            DirectionViewModel.this.directionResponseResourceLiveData.postValue(new com.mappls.sdk.direction.ui.common.a<>(2, null, "Something went wrong"));
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onSuccess(DirectionsResponse directionsResponse) {
            DirectionsResponse directionsResponse2 = directionsResponse;
            DirectionViewModel.this.selectedIndex = 0;
            DirectionViewModel.this.directionsResponse = directionsResponse2;
            DirectionViewModel.this.directionResponseResourceLiveData.postValue(new com.mappls.sdk.direction.ui.common.a<>(1, directionsResponse2, null));
        }
    }

    /* loaded from: classes11.dex */
    public class b implements OnResponseCallback<RouteReportSummaryResponse> {
        public b() {
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onError(int i, String str) {
            DirectionViewModel.this.routeReportSummaryResourceLiveData.postValue(new com.mappls.sdk.direction.ui.common.a<>(2, null, "Something went wrong"));
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onSuccess(RouteReportSummaryResponse routeReportSummaryResponse) {
            DirectionViewModel.this.routeReportSummaryResourceLiveData.postValue(new com.mappls.sdk.direction.ui.common.a<>(1, routeReportSummaryResponse, null));
        }
    }

    /* loaded from: classes11.dex */
    public class c implements OnResponseCallback<CostEstimationResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f12565a;

        public c(int i) {
            this.f12565a = i;
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onError(int i, String str) {
            DirectionViewModel.this.costEstimationResponseLiveData.postValue(new com.mappls.sdk.direction.ui.common.a<>(2, null, "Something went wrong"));
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onSuccess(CostEstimationResponse costEstimationResponse) {
            DirectionViewModel.this.costEstimationResponseLiveData.postValue(new com.mappls.sdk.direction.ui.common.a<>(1, new Pair(Integer.valueOf(this.f12565a), costEstimationResponse), null));
        }
    }

    public void addStop(StopModel stopModel) {
        this.stopModels.add(stopModel);
    }

    public void addWayPoint(StopModel stopModel) {
        if (this.stopModels.size() >= 2) {
            List<StopModel> list = this.stopModels;
            list.add(list.size() - 1, stopModel);
        }
    }

    public String getDestinationLocation() {
        List<StopModel> list;
        List<StopModel> list2;
        List<StopModel> list3;
        int size;
        List<StopModel> list4;
        if (this.stopModels.get(list.size() - 1).getLocationType() != StopModel.TYPE_BLANK) {
            if (this.stopModels.get(list4.size() - 1).getLocationType() == StopModel.TYPE_CURRENT_LOCATION) {
                return "Your Current Location";
            }
            list3 = this.stopModels;
            size = list3.size() - 1;
        } else {
            if (this.stopModels.get(list2.size() - 2).getLocationType() == StopModel.TYPE_CURRENT_LOCATION) {
                return "Your Current Location";
            }
            list3 = this.stopModels;
            size = list3.size() - 2;
        }
        return list3.get(size).getPlaceName();
    }

    public DirectionsResponse getDirectionsResponse() {
        return this.directionsResponse;
    }

    @SuppressLint({"WrongConstant"})
    public void getRouteLiveData(Context context, String str, String str2, List<String> list) {
        this.directionResponseResourceLiveData.postValue(new com.mappls.sdk.direction.ui.common.a<>(3, null, null));
        MapplsDirections.Builder destination = MapplsDirections.builder().overview(this.options.overview()).resource(this.options.resource()).alternatives(this.options.showAlternative()).geometries(this.options.geometries()).instructions(this.options.instructions()).steps(this.options.steps()).origin(str).profile(this.profile).destination(str2);
        if (this.options.resource().equalsIgnoreCase(DirectionsCriteria.RESOURCE_ROUTE_ETA)) {
            destination.deviceId(Settings.Secure.getString(context.getContentResolver(), "android_id"));
            destination.routeRefresh(Boolean.TRUE);
        }
        if (this.options.routeType() != null) {
            destination.routeType(this.options.routeType());
        }
        if (this.options.annotation() != null) {
            String[] strArr = new String[this.options.annotation().size()];
            for (int i = 0; i < this.options.annotation().size(); i++) {
                strArr[i] = this.options.annotation().get(i);
            }
            destination.annotations(strArr);
        }
        if (this.options.excludes() != null) {
            String[] strArr2 = new String[this.options.excludes().size()];
            for (int i2 = 0; i2 < this.options.excludes().size(); i2++) {
                strArr2[i2] = this.options.excludes().get(i2);
            }
            destination.excludes(strArr2);
        }
        if (list != null) {
            for (String str3 : list) {
                if (str3 != null) {
                    destination.addWaypoint(str3);
                }
            }
        }
        if (this.options.isSort() != null) {
            destination.isSort(this.options.isSort());
        }
        if (this.options.lessVerbose() != null) {
            destination.lessVerbose(this.options.lessVerbose());
        }
        MapplsDirectionManager.newInstance(destination.build()).call(new a());
    }

    public RouteReportSummaryResponse getRouteReportSummaryResponse() {
        return this.routeReportSummaryResponse;
    }

    public void getRouteSummaryLiveData(String str) {
        this.routeReportSummaryResourceLiveData.postValue(new com.mappls.sdk.direction.ui.common.a<>(3, null, null));
        MapplsRouteSummaryManager.newInstance(MapplsRouteSummary.builder().routeId(str).isGroup(0).build()).call(new b());
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public String getSourceLocation() {
        return this.stopModels.get(0).getLocationType() == StopModel.TYPE_CURRENT_LOCATION ? "Your Current Location" : this.stopModels.get(0).getPlaceName();
    }

    public List<StopModel> getStopModels() {
        return this.stopModels;
    }

    public int getTheme() {
        return this.theme;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0037, code lost:
        if (r7.equals(com.mappls.sdk.services.api.costestimation.CostEstimationCriteria.VEHICLE_FUEL_TYPE_CNG) == false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void getTollCostLiveData(java.lang.String r5, int r6, java.lang.String r7, java.lang.Integer r8, java.lang.Double r9) {
        /*
            r4 = this;
            androidx.lifecycle.MutableLiveData<com.mappls.sdk.direction.ui.common.a<android.util.Pair<java.lang.Integer, com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse>>> r0 = r4.costEstimationResponseLiveData
            com.mappls.sdk.direction.ui.common.a r1 = new com.mappls.sdk.direction.ui.common.a
            r2 = 3
            r3 = 0
            r1.<init>(r2, r3, r3)
            r0.postValue(r1)
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimation$Builder r0 = com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.builder()
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimation$Builder r5 = r0.routeId(r5)
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimation$Builder r5 = r5.isTollEnabled(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimation$Builder r5 = r5.routeIndex(r0)
            if (r7 == 0) goto L69
            r5.vehicleFuelType(r7)
            r0 = -1
            int r1 = r7.hashCode()
            switch(r1) {
                case -1331959846: goto L50;
                case -991657904: goto L45;
                case -17124067: goto L3a;
                case 66876: goto L31;
                default: goto L2f;
            }
        L2f:
            r2 = r0
            goto L5a
        L31:
            java.lang.String r1 = "CNG"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L5a
            goto L2f
        L3a:
            java.lang.String r1 = "electric"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L43
            goto L2f
        L43:
            r2 = 2
            goto L5a
        L45:
            java.lang.String r1 = "petrol"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L4e
            goto L2f
        L4e:
            r2 = 1
            goto L5a
        L50:
            java.lang.String r1 = "diesel"
            boolean r7 = r7.equals(r1)
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
            java.lang.String r7 = "km/kg"
            goto L66
        L61:
            java.lang.String r7 = "km/unit"
            goto L66
        L64:
            java.lang.String r7 = "km/l"
        L66:
            r5.fuelEfficiencyUnit(r7)
        L69:
            if (r8 == 0) goto L6e
            r5.fuelEfficiency(r8)
        L6e:
            if (r9 == 0) goto L73
            r5.fuelPrice(r9)
        L73:
            com.mappls.sdk.direction.ui.model.DirectionOptions r7 = r4.options
            java.lang.String r7 = r7.profile()
            java.lang.String r8 = "driving"
            boolean r7 = r7.equalsIgnoreCase(r8)
            if (r7 == 0) goto L87
            java.lang.String r7 = "2AxlesAuto"
        L83:
            r5.vehicleType(r7)
            goto La9
        L87:
            com.mappls.sdk.direction.ui.model.DirectionOptions r7 = r4.options
            java.lang.String r7 = r7.profile()
            java.lang.String r8 = "biking"
            boolean r7 = r7.equalsIgnoreCase(r8)
            if (r7 == 0) goto L98
            java.lang.String r7 = "2AxlesMoto"
            goto L83
        L98:
            com.mappls.sdk.direction.ui.model.DirectionOptions r7 = r4.options
            java.lang.String r7 = r7.profile()
            java.lang.String r8 = "trucking"
            boolean r7 = r7.equalsIgnoreCase(r8)
            if (r7 == 0) goto La9
            java.lang.String r7 = "2AxlesTruck"
            goto L83
        La9:
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimation r5 = r5.build()
            com.mappls.sdk.services.api.costestimation.MapplsCostEstimationManager r5 = com.mappls.sdk.services.api.costestimation.MapplsCostEstimationManager.newInstance(r5)
            com.mappls.sdk.direction.ui.DirectionViewModel$c r7 = new com.mappls.sdk.direction.ui.DirectionViewModel$c
            r7.<init>(r6)
            r5.call(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.direction.ui.DirectionViewModel.getTollCostLiveData(java.lang.String, int, java.lang.String, java.lang.Integer, java.lang.Double):void");
    }

    @Keep
    public boolean isCurrentLocation() {
        return this.stopModels.get(0).getLocationType() == StopModel.TYPE_CURRENT_LOCATION && this.options.showStartNavigation().booleanValue();
    }

    @Keep
    public String setLocationText() {
        return (this.stopModels.get(0).getLocationType() == StopModel.TYPE_CURRENT_LOCATION && this.options.showStartNavigation().booleanValue()) ? "Start" : "Preview";
    }

    public void setRouteReportSummaryResponse(RouteReportSummaryResponse routeReportSummaryResponse) {
        this.routeReportSummaryResponse = routeReportSummaryResponse;
    }

    public void setSelectedIndex(int i) {
        this.selectedIndex = i;
    }

    public void setStopModels(List<StopModel> list) {
        this.stopModels = list;
    }

    public void setTheme(int i) {
        this.theme = i;
    }
}
