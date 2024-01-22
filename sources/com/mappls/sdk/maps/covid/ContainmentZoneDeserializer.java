package com.mappls.sdk.maps.covid;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
/* loaded from: classes11.dex */
class ContainmentZoneDeserializer implements JsonDeserializer<ZoneInfo> {
    @Override // com.google.gson.JsonDeserializer
    /* renamed from: a */
    public ZoneInfo deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        ArrayList arrayList = new ArrayList();
        if (asJsonObject.has("results")) {
            JsonArray asJsonArray = asJsonObject.getAsJsonArray("results");
            for (int i = 0; i < asJsonArray.size(); i++) {
                ZoneInfo zoneInfo = new ZoneInfo();
                JsonObject asJsonObject2 = asJsonArray.get(0).getAsJsonObject();
                if (asJsonObject2.has("containment")) {
                    JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject("containment");
                    if (asJsonObject3.has("containmentName")) {
                        zoneInfo.setContainmentZoneName(asJsonObject3.get("containmentName").getAsString());
                    }
                    if (asJsonObject3.has("mapLink")) {
                        zoneInfo.setMapLink(asJsonObject3.get("mapLink").getAsString());
                    }
                    if (asJsonObject3.has("within")) {
                        zoneInfo.setInsideContainmentZone(asJsonObject3.get("within").getAsBoolean());
                    }
                    if (asJsonObject3.has("distance")) {
                        zoneInfo.setDistanceToNearestZone(asJsonObject3.get("distance").getAsInt());
                    }
                    if (asJsonObject2.has("zone")) {
                        JsonObject asJsonObject4 = asJsonObject2.getAsJsonObject("zone");
                        if (asJsonObject4.has("zoneName")) {
                            zoneInfo.setZoneType(asJsonObject4.get("zoneName").getAsString());
                        }
                        if (asJsonObject4.has("districtName")) {
                            zoneInfo.setDistrictName(asJsonObject4.get("districtName").getAsString());
                        }
                    }
                }
                arrayList.add(zoneInfo);
            }
        }
        if (arrayList.size() > 0) {
            return (ZoneInfo) arrayList.get(0);
        }
        return new ZoneInfo();
    }
}
