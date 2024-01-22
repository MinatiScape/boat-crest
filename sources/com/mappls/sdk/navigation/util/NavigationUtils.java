package com.mappls.sdk.navigation.util;

import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.navigation.routing.NavigationRoute;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.navigation.textinstructions.TextInstructionHelper;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.LegAnnotation;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.services.api.directions.models.RouteLeg;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/* loaded from: classes11.dex */
public class NavigationUtils {
    public static int getManeuverID(float f) {
        if (f <= 45.0f) {
            return 65;
        }
        if (f <= 90.0f) {
            return 66;
        }
        if (f <= 135.0f) {
            return 67;
        }
        if (f <= 180.0f) {
            return 68;
        }
        if (f <= 225.0f) {
            return 69;
        }
        return f <= 270.0f ? 70 : 71;
    }

    public static List<RouteLeg> mergeRouteLegs(DirectionsRoute directionsRoute) {
        ArrayList arrayList = new ArrayList();
        if (directionsRoute.legs() != null && directionsRoute.legs().size() != 0) {
            if (directionsRoute.legs().size() == 1) {
                arrayList.addAll(directionsRoute.legs());
                return arrayList;
            }
            List<RouteLeg> legs = directionsRoute.legs();
            RouteLeg.Builder builder = RouteLeg.builder();
            StringBuilder sb = new StringBuilder();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            ArrayList arrayList7 = new ArrayList();
            ArrayList arrayList8 = new ArrayList();
            ArrayList arrayList9 = new ArrayList();
            ArrayList arrayList10 = new ArrayList();
            ArrayList arrayList11 = new ArrayList();
            double d = 0.0d;
            double d2 = 0.0d;
            for (int i = 0; i < legs.size(); i++) {
                sb.append(legs.get(i).summary());
                if (i != legs.size() - 1) {
                    sb.append(Constants.SEPARATOR_COMMA);
                }
                d2 = legs.get(i).distance().doubleValue() + d2;
                d = legs.get(i).duration().doubleValue() + d;
                if (legs.get(i).annotation() != null && legs.get(i).annotation().congestion() != null) {
                    arrayList3.addAll(legs.get(i).annotation().congestion());
                }
                if (legs.get(i).annotation() != null && legs.get(i).annotation().distance() != null) {
                    arrayList4.addAll(legs.get(i).annotation().distance());
                }
                if (legs.get(i).annotation() != null && legs.get(i).annotation().duration() != null) {
                    arrayList5.addAll(legs.get(i).annotation().duration());
                }
                if (legs.get(i).annotation() != null && legs.get(i).annotation().nodes() != null) {
                    arrayList7.addAll(legs.get(i).annotation().nodes());
                }
                if (legs.get(i).annotation() != null && legs.get(i).annotation().maxspeed() != null) {
                    arrayList8.addAll(legs.get(i).annotation().maxspeed());
                }
                if (legs.get(i).annotation() != null && legs.get(i).annotation().speed() != null) {
                    arrayList9.addAll(legs.get(i).annotation().speed());
                }
                if (legs.get(i).annotation() != null && legs.get(i).annotation().speedLimit() != null) {
                    arrayList10.addAll(legs.get(i).annotation().speedLimit());
                }
                if (legs.get(i).annotation() != null && legs.get(i).annotation().tollRoad() != null) {
                    arrayList11.addAll(legs.get(i).annotation().tollRoad());
                }
                if (legs.get(i).annotation() != null && legs.get(i).annotation().baseDuration() != null) {
                    arrayList6.addAll(legs.get(i).annotation().baseDuration());
                }
                arrayList2.addAll(legs.get(i).steps());
            }
            builder.distance(Double.valueOf(d2));
            builder.duration(Double.valueOf(d));
            builder.summary(sb.toString());
            builder.steps(arrayList2);
            builder.annotation(LegAnnotation.builder().congestion(arrayList3).distance(arrayList4).duration(arrayList5).maxspeed(arrayList8).nodes(arrayList7).speed(arrayList9).speedLimit(arrayList10).tollRoad(arrayList11).baseDuration(arrayList6).build());
            arrayList.add(builder.build());
        }
        return arrayList;
    }

    public static AdviseInfo toAdviseInfo(NavigationRoute.a aVar, boolean z, long j, int i, boolean z2, boolean z3, boolean z4, double d, boolean z5, NavLocation navLocation, NavigationRoute navigationRoute, float f) {
        NavigationStep navigationStep;
        NavigationStep navigationStep2;
        NavigationStep navigationStep3;
        NavigationStep navigationStep4;
        NavigationStep navigationStep5;
        NavigationStep navigationStep6;
        NavigationStep navigationStep7;
        NavigationStep navigationStep8;
        NavigationStep navigationStep9;
        long j2 = z ? 0L : j;
        AdviseInfo adviseInfo = new AdviseInfo();
        String str = "";
        adviseInfo.setText((aVar == null || (navigationStep9 = aVar.f12943a) == null) ? "" : navigationStep9.getDescriptionRoutePart());
        adviseInfo.setDistanceToNextAdvise(aVar != null ? aVar.b : 0);
        adviseInfo.setLeftDistance(i);
        adviseInfo.setLeftTime((int) j2);
        adviseInfo.setOnTollRoad(z2);
        adviseInfo.setLeftTimeStep((aVar == null || (navigationStep8 = aVar.f12943a) == null || aVar.b <= 0 || navigationStep8.getAverageSpeed() <= 0.0f) ? 0 : (int) (aVar.b / aVar.f12943a.getAverageSpeed()));
        long j3 = j2 * 1000;
        adviseInfo.setEtaInSecond(j3);
        adviseInfo.setEta(DateFormat.getTimeInstance(3).format(new Date(System.currentTimeMillis() + j3)));
        adviseInfo.setRouteBeingRecalculated(!z && z3);
        adviseInfo.setuTurnSuggestion(!z && z4);
        adviseInfo.setOnRoute(z || z5);
        adviseInfo.setPosition((aVar == null || (navigationStep7 = aVar.f12943a) == null) ? 0 : navigationStep7.getPosition());
        adviseInfo.setLocation(navLocation);
        adviseInfo.setDistanceFromRoute(d);
        adviseInfo.setInfo((aVar == null || (navigationStep6 = aVar.f12943a) == null) ? null : navigationStep6.getExtraInfo());
        adviseInfo.setNextInstructionInfo((aVar == null || (navigationStep5 = aVar.f12943a) == null) ? null : navigationStep5.getNextExtraInfo());
        adviseInfo.setNextInstructionText((aVar == null || (navigationStep4 = aVar.f12943a) == null || navigationStep4.getNextExtraInfo() == null) ? null : TextInstructionHelper.getInstance().getInstruction((LegStep) aVar.f12943a.getNextExtraInfo()));
        adviseInfo.setManeuverID((aVar == null || (navigationStep3 = aVar.f12943a) == null) ? -1L : navigationStep3.getManeuverID());
        if (aVar != null && (navigationStep2 = aVar.f12943a) != null) {
            str = navigationStep2.getShortInstruction();
        }
        adviseInfo.setShortText(str);
        if (navigationRoute != null) {
            adviseInfo.setCurrentRoadSpeed(Double.valueOf(navigationRoute.currentRoadSpeed()));
            adviseInfo.setOverSpeed(navLocation.hasSpeed() && ((double) navLocation.getSpeed()) * 3.6d > adviseInfo.getCurrentRoadSpeed().doubleValue() && adviseInfo.getCurrentRoadSpeed().doubleValue() > 0.0d);
        }
        adviseInfo.setIntermediate(false);
        if (adviseInfo.getDistanceToNextAdvise() < f && adviseInfo.getManeuverID() == 8 && aVar != null && (navigationStep = aVar.f12943a) != null) {
            if (navigationStep.isDestination()) {
                adviseInfo.setText("You have arrived");
            } else {
                adviseInfo.setIntermediate(true);
                adviseInfo.setText("You have reached your intermediate destination");
            }
        }
        if (z) {
            adviseInfo.setText("You have arrived");
            adviseInfo.setShortText("You have arrived");
            adviseInfo.setDistanceToNextAdvise(0);
            adviseInfo.setNextInstructionInfo(null);
            adviseInfo.setInfo(null);
            adviseInfo.setNextInstructionText(null);
            adviseInfo.setManeuverID(8L);
        }
        return adviseInfo;
    }
}
