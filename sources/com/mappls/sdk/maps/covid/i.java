package com.mappls.sdk.maps.covid;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.annotations.BubbleLayout;
import java.lang.ref.WeakReference;
import java.util.HashMap;
/* loaded from: classes11.dex */
public class i extends AsyncTask<FeatureCollection, Void, HashMap<String, Bitmap>> {

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<String, View> f12718a = new HashMap<>();
    public final WeakReference<g> b;
    public MapView c;

    public i(g gVar, MapView mapView) {
        this.b = new WeakReference<>(gVar);
        this.c = mapView;
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public HashMap<String, Bitmap> doInBackground(FeatureCollection... featureCollectionArr) {
        if (this.b.get() != null) {
            HashMap<String, Bitmap> hashMap = new HashMap<>();
            LayoutInflater from = LayoutInflater.from(this.c.getContext());
            FeatureCollection featureCollection = featureCollectionArr[0];
            if (featureCollection.features() != null) {
                for (Feature feature : featureCollection.features()) {
                    BubbleLayout bubbleLayout = (BubbleLayout) from.inflate(R.layout.mappls_maps_info_window, (ViewGroup) null);
                    if (feature.hasProperty("properties")) {
                        JsonObject jsonObject = (JsonObject) feature.getProperty("properties");
                        String str = AppMeasurementSdk.ConditionalUserProperty.NAME;
                        if (jsonObject != null) {
                            String stringProperty = feature.hasProperty("id") ? feature.getStringProperty("id") : AppMeasurementSdk.ConditionalUserProperty.NAME;
                            if (jsonObject.has(Constants.ScionAnalytics.PARAM_LABEL) && !(jsonObject.get(Constants.ScionAnalytics.PARAM_LABEL) instanceof JsonNull)) {
                                ((TextView) bubbleLayout.findViewById(R.id.info_window_label)).setText(jsonObject.get(Constants.ScionAnalytics.PARAM_LABEL).getAsString());
                            }
                            if (jsonObject.has(AppMeasurementSdk.ConditionalUserProperty.NAME) && !(jsonObject.get(AppMeasurementSdk.ConditionalUserProperty.NAME) instanceof JsonNull)) {
                                ((TextView) bubbleLayout.findViewById(R.id.info_window_label)).setText(jsonObject.get(AppMeasurementSdk.ConditionalUserProperty.NAME).getAsString());
                            }
                            if (jsonObject.has("location_n") && !(jsonObject.get("location_n") instanceof JsonNull)) {
                                ((TextView) bubbleLayout.findViewById(R.id.info_window_label)).setText(jsonObject.get("location_n").getAsString());
                            }
                            if (jsonObject.has("placename") && !(jsonObject.get("placename") instanceof JsonNull)) {
                                ((TextView) bubbleLayout.findViewById(R.id.info_window_label)).setText(jsonObject.get("placename").getAsString());
                            }
                            if (jsonObject.has("total")) {
                                bubbleLayout.findViewById(R.id.layout_total_cases).setVisibility(0);
                                ((TextView) bubbleLayout.findViewById(R.id.total_cases)).setText(jsonObject.get("total").getAsString());
                            } else {
                                bubbleLayout.findViewById(R.id.layout_total_cases).setVisibility(8);
                            }
                            if (jsonObject.has("cured")) {
                                bubbleLayout.findViewById(R.id.layout_total_recover).setVisibility(0);
                                if (!(jsonObject.get("cured") instanceof JsonNull)) {
                                    ((TextView) bubbleLayout.findViewById(R.id.total_recovered)).setText(jsonObject.get("cured").getAsString());
                                }
                            } else {
                                bubbleLayout.findViewById(R.id.layout_total_recover).setVisibility(8);
                            }
                            if (jsonObject.has("death")) {
                                bubbleLayout.findViewById(R.id.layout_total_death).setVisibility(0);
                                if (!(jsonObject.get("death") instanceof JsonNull)) {
                                    ((TextView) bubbleLayout.findViewById(R.id.total_death)).setText(jsonObject.get("death").getAsString());
                                }
                            } else {
                                bubbleLayout.findViewById(R.id.layout_total_death).setVisibility(8);
                            }
                            if (jsonObject.has("hotspt_rmk")) {
                                int i = R.id.remarks;
                                bubbleLayout.findViewById(i).setVisibility(0);
                                if (!(jsonObject.get("hotspt_rmk") instanceof JsonNull)) {
                                    ((TextView) bubbleLayout.findViewById(i)).setText(jsonObject.get("hotspt_rmk").getAsString());
                                }
                            } else if (jsonObject.has("address")) {
                                int i2 = R.id.remarks;
                                bubbleLayout.findViewById(i2).setVisibility(0);
                                ((TextView) bubbleLayout.findViewById(i2)).setText(jsonObject.get("address").getAsString());
                            } else {
                                bubbleLayout.findViewById(R.id.remarks).setVisibility(8);
                            }
                            if (jsonObject.has("source")) {
                                int i3 = R.id.source;
                                bubbleLayout.findViewById(i3).setVisibility(0);
                                if (!(jsonObject.get("source") instanceof JsonNull)) {
                                    String asString = jsonObject.get("source").getAsString();
                                    ((TextView) bubbleLayout.findViewById(i3)).setText("Source: " + asString);
                                }
                            } else {
                                bubbleLayout.findViewById(R.id.source).setVisibility(8);
                            }
                            if (jsonObject.has("descriptio")) {
                                int i4 = R.id.description;
                                bubbleLayout.findViewById(i4).setVisibility(0);
                                if (!(jsonObject.get("descriptio") instanceof JsonNull)) {
                                    ((TextView) bubbleLayout.findViewById(i4)).setText(jsonObject.get("descriptio").getAsString());
                                }
                            } else if (jsonObject.has("relief_cam")) {
                                int i5 = R.id.description;
                                bubbleLayout.findViewById(i5).setVisibility(0);
                                if (!(jsonObject.get("relief_cam") instanceof JsonNull)) {
                                    String asString2 = jsonObject.get("relief_cam").getAsString();
                                    ((TextView) bubbleLayout.findViewById(i5)).setText("Relief Camp: " + asString2);
                                }
                            } else {
                                bubbleLayout.findViewById(R.id.description).setVisibility(8);
                            }
                            str = stringProperty;
                        }
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                        bubbleLayout.measure(makeMeasureSpec, makeMeasureSpec);
                        bubbleLayout.setArrowPosition((bubbleLayout.getMeasuredWidth() / 2.0f) - 5.0f);
                        hashMap.put(str, j.b(bubbleLayout));
                        this.f12718a.put(str, bubbleLayout);
                    }
                }
            }
            return hashMap;
        }
        return null;
    }

    @Override // android.os.AsyncTask
    /* renamed from: b */
    public void onPostExecute(HashMap<String, Bitmap> hashMap) {
        super.onPostExecute(hashMap);
        g gVar = this.b.get();
        if (gVar == null || hashMap == null) {
            return;
        }
        gVar.j(hashMap);
        gVar.l();
    }
}
