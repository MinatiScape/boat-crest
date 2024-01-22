package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.android.gms.common.Scopes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.WalkingOptions;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import java.io.IOException;
import java.util.List;
/* loaded from: classes11.dex */
public final class AutoValue_RouteOptions extends n {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<RouteOptions> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<String> f13182a;
        public volatile TypeAdapter<List<String>> b;
        public volatile TypeAdapter<Boolean> c;
        public volatile TypeAdapter<WalkingOptions> d;
        public volatile TypeAdapter<Integer> e;
        public final Gson f;

        public GsonTypeAdapter(Gson gson) {
            this.f = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public RouteOptions read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            RouteOptions.Builder builder = RouteOptions.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    char c = 65535;
                    switch (nextName.hashCode()) {
                        case -2075945000:
                            if (nextName.equals("banner_instructions")) {
                                c = 0;
                                break;
                            }
                            break;
                        case -1958245943:
                            if (nextName.equals("lessverbose")) {
                                c = 1;
                                break;
                            }
                            break;
                        case -1938933922:
                            if (nextName.equals("access_token")) {
                                c = 2;
                                break;
                            }
                            break;
                        case -1050878268:
                            if (nextName.equals("waypoint_targets")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 3601339:
                            if (nextName.equals("uuid")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 122594497:
                            if (nextName.equals("continueStraight")) {
                                c = 5;
                                break;
                            }
                            break;
                        case 241170578:
                            if (nextName.equals("waypoints")) {
                                c = 6;
                                break;
                            }
                            break;
                        case 605650314:
                            if (nextName.equals("waypoint_names")) {
                                c = 7;
                                break;
                            }
                            break;
                        case 607796817:
                            if (nextName.equals("sessionId")) {
                                c = '\b';
                                break;
                            }
                            break;
                        case 686900690:
                            if (nextName.equals("skip_waypoints")) {
                                c = '\t';
                                break;
                            }
                            break;
                        case 757376421:
                            if (nextName.equals("instructions")) {
                                c = '\n';
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            TypeAdapter<Boolean> typeAdapter = this.c;
                            if (typeAdapter == null) {
                                typeAdapter = this.f.getAdapter(Boolean.class);
                                this.c = typeAdapter;
                            }
                            builder.bannerInstructions(typeAdapter.read(jsonReader));
                            continue;
                        case 1:
                            TypeAdapter<Boolean> typeAdapter2 = this.c;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.f.getAdapter(Boolean.class);
                                this.c = typeAdapter2;
                            }
                            builder.lessVerbose(typeAdapter2.read(jsonReader));
                            continue;
                        case 2:
                            TypeAdapter<String> typeAdapter3 = this.f13182a;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.f.getAdapter(String.class);
                                this.f13182a = typeAdapter3;
                            }
                            builder.accessToken(typeAdapter3.read(jsonReader));
                            continue;
                        case 3:
                            TypeAdapter<String> typeAdapter4 = this.f13182a;
                            if (typeAdapter4 == null) {
                                typeAdapter4 = this.f.getAdapter(String.class);
                                this.f13182a = typeAdapter4;
                            }
                            builder.waypointTargets(typeAdapter4.read(jsonReader));
                            continue;
                        case 4:
                            TypeAdapter<String> typeAdapter5 = this.f13182a;
                            if (typeAdapter5 == null) {
                                typeAdapter5 = this.f.getAdapter(String.class);
                                this.f13182a = typeAdapter5;
                            }
                            builder.requestUuid(typeAdapter5.read(jsonReader));
                            continue;
                        case 5:
                            TypeAdapter<Boolean> typeAdapter6 = this.c;
                            if (typeAdapter6 == null) {
                                typeAdapter6 = this.f.getAdapter(Boolean.class);
                                this.c = typeAdapter6;
                            }
                            builder.continueStraight(typeAdapter6.read(jsonReader));
                            continue;
                        case 6:
                            TypeAdapter<String> typeAdapter7 = this.f13182a;
                            if (typeAdapter7 == null) {
                                typeAdapter7 = this.f.getAdapter(String.class);
                                this.f13182a = typeAdapter7;
                            }
                            builder.waypointIndices(typeAdapter7.read(jsonReader));
                            continue;
                        case 7:
                            TypeAdapter<String> typeAdapter8 = this.f13182a;
                            if (typeAdapter8 == null) {
                                typeAdapter8 = this.f.getAdapter(String.class);
                                this.f13182a = typeAdapter8;
                            }
                            builder.waypointNames(typeAdapter8.read(jsonReader));
                            continue;
                        case '\b':
                            TypeAdapter<String> typeAdapter9 = this.f13182a;
                            if (typeAdapter9 == null) {
                                typeAdapter9 = this.f.getAdapter(String.class);
                                this.f13182a = typeAdapter9;
                            }
                            builder.sessionId(typeAdapter9.read(jsonReader));
                            continue;
                        case '\t':
                            TypeAdapter<Boolean> typeAdapter10 = this.c;
                            if (typeAdapter10 == null) {
                                typeAdapter10 = this.f.getAdapter(Boolean.class);
                                this.c = typeAdapter10;
                            }
                            builder.skipWaypoints(typeAdapter10.read(jsonReader));
                            continue;
                        case '\n':
                            TypeAdapter<Boolean> typeAdapter11 = this.c;
                            if (typeAdapter11 == null) {
                                typeAdapter11 = this.f.getAdapter(Boolean.class);
                                this.c = typeAdapter11;
                            }
                            builder.instructions(typeAdapter11.read(jsonReader));
                            continue;
                        default:
                            if ("baseUrl".equals(nextName)) {
                                TypeAdapter<String> typeAdapter12 = this.f13182a;
                                if (typeAdapter12 == null) {
                                    typeAdapter12 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter12;
                                }
                                builder.baseUrl(typeAdapter12.read(jsonReader));
                                break;
                            } else if ("deviceID".equals(nextName)) {
                                TypeAdapter<String> typeAdapter13 = this.f13182a;
                                if (typeAdapter13 == null) {
                                    typeAdapter13 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter13;
                                }
                                builder.deviceID(typeAdapter13.read(jsonReader));
                                break;
                            } else if ("user".equals(nextName)) {
                                TypeAdapter<String> typeAdapter14 = this.f13182a;
                                if (typeAdapter14 == null) {
                                    typeAdapter14 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter14;
                                }
                                builder.user(typeAdapter14.read(jsonReader));
                                break;
                            } else if (Scopes.PROFILE.equals(nextName)) {
                                TypeAdapter<String> typeAdapter15 = this.f13182a;
                                if (typeAdapter15 == null) {
                                    typeAdapter15 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter15;
                                }
                                builder.profile(typeAdapter15.read(jsonReader));
                                break;
                            } else if ("resource".equals(nextName)) {
                                TypeAdapter<String> typeAdapter16 = this.f13182a;
                                if (typeAdapter16 == null) {
                                    typeAdapter16 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter16;
                                }
                                builder.resource(typeAdapter16.read(jsonReader));
                                break;
                            } else if ("coordinates".equals(nextName)) {
                                TypeAdapter<List<String>> typeAdapter17 = this.b;
                                if (typeAdapter17 == null) {
                                    typeAdapter17 = this.f.getAdapter(TypeToken.getParameterized(List.class, String.class));
                                    this.b = typeAdapter17;
                                }
                                builder.coordinates(typeAdapter17.read(jsonReader));
                                break;
                            } else if ("alternatives".equals(nextName)) {
                                TypeAdapter<Boolean> typeAdapter18 = this.c;
                                if (typeAdapter18 == null) {
                                    typeAdapter18 = this.f.getAdapter(Boolean.class);
                                    this.c = typeAdapter18;
                                }
                                builder.alternatives(typeAdapter18.read(jsonReader));
                                break;
                            } else if ("language".equals(nextName)) {
                                TypeAdapter<String> typeAdapter19 = this.f13182a;
                                if (typeAdapter19 == null) {
                                    typeAdapter19 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter19;
                                }
                                builder.language(typeAdapter19.read(jsonReader));
                                break;
                            } else if ("radiuses".equals(nextName)) {
                                TypeAdapter<String> typeAdapter20 = this.f13182a;
                                if (typeAdapter20 == null) {
                                    typeAdapter20 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter20;
                                }
                                builder.radiuses(typeAdapter20.read(jsonReader));
                                break;
                            } else if ("bearings".equals(nextName)) {
                                TypeAdapter<String> typeAdapter21 = this.f13182a;
                                if (typeAdapter21 == null) {
                                    typeAdapter21 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter21;
                                }
                                builder.bearings(typeAdapter21.read(jsonReader));
                                break;
                            } else if ("geometries".equals(nextName)) {
                                TypeAdapter<String> typeAdapter22 = this.f13182a;
                                if (typeAdapter22 == null) {
                                    typeAdapter22 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter22;
                                }
                                builder.geometries(typeAdapter22.read(jsonReader));
                                break;
                            } else if ("overview".equals(nextName)) {
                                TypeAdapter<String> typeAdapter23 = this.f13182a;
                                if (typeAdapter23 == null) {
                                    typeAdapter23 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter23;
                                }
                                builder.overview(typeAdapter23.read(jsonReader));
                                break;
                            } else if ("steps".equals(nextName)) {
                                TypeAdapter<Boolean> typeAdapter24 = this.c;
                                if (typeAdapter24 == null) {
                                    typeAdapter24 = this.f.getAdapter(Boolean.class);
                                    this.c = typeAdapter24;
                                }
                                builder.steps(typeAdapter24.read(jsonReader));
                                break;
                            } else if ("annotations".equals(nextName)) {
                                TypeAdapter<String> typeAdapter25 = this.f13182a;
                                if (typeAdapter25 == null) {
                                    typeAdapter25 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter25;
                                }
                                builder.annotations(typeAdapter25.read(jsonReader));
                                break;
                            } else if ("exclude".equals(nextName)) {
                                TypeAdapter<String> typeAdapter26 = this.f13182a;
                                if (typeAdapter26 == null) {
                                    typeAdapter26 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter26;
                                }
                                builder.exclude(typeAdapter26.read(jsonReader));
                                break;
                            } else if ("approaches".equals(nextName)) {
                                TypeAdapter<String> typeAdapter27 = this.f13182a;
                                if (typeAdapter27 == null) {
                                    typeAdapter27 = this.f.getAdapter(String.class);
                                    this.f13182a = typeAdapter27;
                                }
                                builder.approaches(typeAdapter27.read(jsonReader));
                                break;
                            } else if ("walkingOptions".equals(nextName)) {
                                TypeAdapter<WalkingOptions> typeAdapter28 = this.d;
                                if (typeAdapter28 == null) {
                                    typeAdapter28 = this.f.getAdapter(WalkingOptions.class);
                                    this.d = typeAdapter28;
                                }
                                builder.walkingOptions(typeAdapter28.read(jsonReader));
                                break;
                            } else if ("routeType".equals(nextName)) {
                                TypeAdapter<Integer> typeAdapter29 = this.e;
                                if (typeAdapter29 == null) {
                                    typeAdapter29 = this.f.getAdapter(Integer.class);
                                    this.e = typeAdapter29;
                                }
                                builder.routeType(typeAdapter29.read(jsonReader));
                                break;
                            } else if ("isSort".equals(nextName)) {
                                TypeAdapter<Boolean> typeAdapter30 = this.c;
                                if (typeAdapter30 == null) {
                                    typeAdapter30 = this.f.getAdapter(Boolean.class);
                                    this.c = typeAdapter30;
                                }
                                builder.isSort(typeAdapter30.read(jsonReader));
                                break;
                            } else if ("routeRefresh".equals(nextName)) {
                                TypeAdapter<Boolean> typeAdapter31 = this.c;
                                if (typeAdapter31 == null) {
                                    typeAdapter31 = this.f.getAdapter(Boolean.class);
                                    this.c = typeAdapter31;
                                }
                                builder.routeRefresh(typeAdapter31.read(jsonReader));
                                break;
                            } else if ("roundaboutExits".equals(nextName)) {
                                TypeAdapter<Boolean> typeAdapter32 = this.c;
                                if (typeAdapter32 == null) {
                                    typeAdapter32 = this.f.getAdapter(Boolean.class);
                                    this.c = typeAdapter32;
                                }
                                builder.roundaboutExits(typeAdapter32.read(jsonReader));
                                break;
                            } else {
                                jsonReader.skipValue();
                                continue;
                            }
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, RouteOptions routeOptions) throws IOException {
            if (routeOptions == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("baseUrl");
            if (routeOptions.baseUrl() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.f13182a;
                if (typeAdapter == null) {
                    typeAdapter = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, routeOptions.baseUrl());
            }
            jsonWriter.name("deviceID");
            if (routeOptions.deviceID() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.f13182a;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, routeOptions.deviceID());
            }
            jsonWriter.name("user");
            if (routeOptions.user() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.f13182a;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, routeOptions.user());
            }
            jsonWriter.name(Scopes.PROFILE);
            if (routeOptions.profile() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.f13182a;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, routeOptions.profile());
            }
            jsonWriter.name("resource");
            if (routeOptions.resource() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.f13182a;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, routeOptions.resource());
            }
            jsonWriter.name("coordinates");
            if (routeOptions.coordinates() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<String>> typeAdapter6 = this.b;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.f.getAdapter(TypeToken.getParameterized(List.class, String.class));
                    this.b = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, routeOptions.coordinates());
            }
            jsonWriter.name("alternatives");
            if (routeOptions.alternatives() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter7 = this.c;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.f.getAdapter(Boolean.class);
                    this.c = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, routeOptions.alternatives());
            }
            jsonWriter.name("language");
            if (routeOptions.language() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter8 = this.f13182a;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, routeOptions.language());
            }
            jsonWriter.name("radiuses");
            if (routeOptions.radiuses() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter9 = this.f13182a;
                if (typeAdapter9 == null) {
                    typeAdapter9 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter9;
                }
                typeAdapter9.write(jsonWriter, routeOptions.radiuses());
            }
            jsonWriter.name("bearings");
            if (routeOptions.bearings() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter10 = this.f13182a;
                if (typeAdapter10 == null) {
                    typeAdapter10 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter10;
                }
                typeAdapter10.write(jsonWriter, routeOptions.bearings());
            }
            jsonWriter.name("lessverbose");
            if (routeOptions.lessVerbose() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter11 = this.c;
                if (typeAdapter11 == null) {
                    typeAdapter11 = this.f.getAdapter(Boolean.class);
                    this.c = typeAdapter11;
                }
                typeAdapter11.write(jsonWriter, routeOptions.lessVerbose());
            }
            jsonWriter.name("geometries");
            if (routeOptions.geometries() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter12 = this.f13182a;
                if (typeAdapter12 == null) {
                    typeAdapter12 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter12;
                }
                typeAdapter12.write(jsonWriter, routeOptions.geometries());
            }
            jsonWriter.name("overview");
            if (routeOptions.overview() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter13 = this.f13182a;
                if (typeAdapter13 == null) {
                    typeAdapter13 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter13;
                }
                typeAdapter13.write(jsonWriter, routeOptions.overview());
            }
            jsonWriter.name("steps");
            if (routeOptions.steps() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter14 = this.c;
                if (typeAdapter14 == null) {
                    typeAdapter14 = this.f.getAdapter(Boolean.class);
                    this.c = typeAdapter14;
                }
                typeAdapter14.write(jsonWriter, routeOptions.steps());
            }
            jsonWriter.name("annotations");
            if (routeOptions.annotations() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter15 = this.f13182a;
                if (typeAdapter15 == null) {
                    typeAdapter15 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter15;
                }
                typeAdapter15.write(jsonWriter, routeOptions.annotations());
            }
            jsonWriter.name("exclude");
            if (routeOptions.exclude() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter16 = this.f13182a;
                if (typeAdapter16 == null) {
                    typeAdapter16 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter16;
                }
                typeAdapter16.write(jsonWriter, routeOptions.exclude());
            }
            jsonWriter.name("continueStraight");
            if (routeOptions.continueStraight() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter17 = this.c;
                if (typeAdapter17 == null) {
                    typeAdapter17 = this.f.getAdapter(Boolean.class);
                    this.c = typeAdapter17;
                }
                typeAdapter17.write(jsonWriter, routeOptions.continueStraight());
            }
            jsonWriter.name("banner_instructions");
            if (routeOptions.bannerInstructions() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter18 = this.c;
                if (typeAdapter18 == null) {
                    typeAdapter18 = this.f.getAdapter(Boolean.class);
                    this.c = typeAdapter18;
                }
                typeAdapter18.write(jsonWriter, routeOptions.bannerInstructions());
            }
            jsonWriter.name("instructions");
            if (routeOptions.instructions() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter19 = this.c;
                if (typeAdapter19 == null) {
                    typeAdapter19 = this.f.getAdapter(Boolean.class);
                    this.c = typeAdapter19;
                }
                typeAdapter19.write(jsonWriter, routeOptions.instructions());
            }
            jsonWriter.name("skip_waypoints");
            if (routeOptions.skipWaypoints() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter20 = this.c;
                if (typeAdapter20 == null) {
                    typeAdapter20 = this.f.getAdapter(Boolean.class);
                    this.c = typeAdapter20;
                }
                typeAdapter20.write(jsonWriter, routeOptions.skipWaypoints());
            }
            jsonWriter.name("access_token");
            if (routeOptions.accessToken() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter21 = this.f13182a;
                if (typeAdapter21 == null) {
                    typeAdapter21 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter21;
                }
                typeAdapter21.write(jsonWriter, routeOptions.accessToken());
            }
            jsonWriter.name("uuid");
            if (routeOptions.requestUuid() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter22 = this.f13182a;
                if (typeAdapter22 == null) {
                    typeAdapter22 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter22;
                }
                typeAdapter22.write(jsonWriter, routeOptions.requestUuid());
            }
            jsonWriter.name("sessionId");
            if (routeOptions.sessionId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter23 = this.f13182a;
                if (typeAdapter23 == null) {
                    typeAdapter23 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter23;
                }
                typeAdapter23.write(jsonWriter, routeOptions.sessionId());
            }
            jsonWriter.name("approaches");
            if (routeOptions.approaches() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter24 = this.f13182a;
                if (typeAdapter24 == null) {
                    typeAdapter24 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter24;
                }
                typeAdapter24.write(jsonWriter, routeOptions.approaches());
            }
            jsonWriter.name("waypoints");
            if (routeOptions.waypointIndices() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter25 = this.f13182a;
                if (typeAdapter25 == null) {
                    typeAdapter25 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter25;
                }
                typeAdapter25.write(jsonWriter, routeOptions.waypointIndices());
            }
            jsonWriter.name("waypoint_names");
            if (routeOptions.waypointNames() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter26 = this.f13182a;
                if (typeAdapter26 == null) {
                    typeAdapter26 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter26;
                }
                typeAdapter26.write(jsonWriter, routeOptions.waypointNames());
            }
            jsonWriter.name("waypoint_targets");
            if (routeOptions.waypointTargets() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter27 = this.f13182a;
                if (typeAdapter27 == null) {
                    typeAdapter27 = this.f.getAdapter(String.class);
                    this.f13182a = typeAdapter27;
                }
                typeAdapter27.write(jsonWriter, routeOptions.waypointTargets());
            }
            jsonWriter.name("walkingOptions");
            if (routeOptions.walkingOptions() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<WalkingOptions> typeAdapter28 = this.d;
                if (typeAdapter28 == null) {
                    typeAdapter28 = this.f.getAdapter(WalkingOptions.class);
                    this.d = typeAdapter28;
                }
                typeAdapter28.write(jsonWriter, routeOptions.walkingOptions());
            }
            jsonWriter.name("routeType");
            if (routeOptions.routeType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter29 = this.e;
                if (typeAdapter29 == null) {
                    typeAdapter29 = this.f.getAdapter(Integer.class);
                    this.e = typeAdapter29;
                }
                typeAdapter29.write(jsonWriter, routeOptions.routeType());
            }
            jsonWriter.name("isSort");
            if (routeOptions.isSort() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter30 = this.c;
                if (typeAdapter30 == null) {
                    typeAdapter30 = this.f.getAdapter(Boolean.class);
                    this.c = typeAdapter30;
                }
                typeAdapter30.write(jsonWriter, routeOptions.isSort());
            }
            jsonWriter.name("routeRefresh");
            if (routeOptions.routeRefresh() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter31 = this.c;
                if (typeAdapter31 == null) {
                    typeAdapter31 = this.f.getAdapter(Boolean.class);
                    this.c = typeAdapter31;
                }
                typeAdapter31.write(jsonWriter, routeOptions.routeRefresh());
            }
            jsonWriter.name("roundaboutExits");
            if (routeOptions.roundaboutExits() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter32 = this.c;
                if (typeAdapter32 == null) {
                    typeAdapter32 = this.f.getAdapter(Boolean.class);
                    this.c = typeAdapter32;
                }
                typeAdapter32.write(jsonWriter, routeOptions.roundaboutExits());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(RouteOptions)";
        }
    }

    public AutoValue_RouteOptions(String str, @Nullable String str2, String str3, String str4, String str5, List<String> list, @Nullable Boolean bool, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Boolean bool2, String str9, @Nullable String str10, @Nullable Boolean bool3, @Nullable String str11, @Nullable String str12, @Nullable Boolean bool4, @Nullable Boolean bool5, @Nullable Boolean bool6, @Nullable Boolean bool7, @Nullable String str13, @Nullable String str14, @Nullable String str15, @Nullable String str16, @Nullable String str17, @Nullable String str18, @Nullable String str19, @Nullable WalkingOptions walkingOptions, @Nullable Integer num, @Nullable Boolean bool8, @Nullable Boolean bool9, @Nullable Boolean bool10) {
        super(str, str2, str3, str4, str5, list, bool, str6, str7, str8, bool2, str9, str10, bool3, str11, str12, bool4, bool5, bool6, bool7, str13, str14, str15, str16, str17, str18, str19, walkingOptions, num, bool8, bool9, bool10);
    }
}
