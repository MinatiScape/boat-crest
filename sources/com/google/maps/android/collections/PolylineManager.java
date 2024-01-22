package com.google.maps.android.collections;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.collections.MapObjectManager;
/* loaded from: classes10.dex */
public class PolylineManager extends MapObjectManager<Polyline, Collection> implements GoogleMap.OnPolylineClickListener {
    public PolylineManager(@NonNull GoogleMap googleMap) {
        super(googleMap);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public void a() {
        GoogleMap googleMap = this.mMap;
        if (googleMap != null) {
            googleMap.setOnPolylineClickListener(this);
        }
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnPolylineClickListener
    public void onPolylineClick(Polyline polyline) {
        Collection collection = (Collection) this.mAllObjects.get(polyline);
        if (collection == null || collection.c == null) {
            return;
        }
        collection.c.onPolylineClick(polyline);
    }

    /* loaded from: classes10.dex */
    public class Collection extends MapObjectManager.Collection {
        public GoogleMap.OnPolylineClickListener c;

        public Collection() {
            super();
        }

        public void addAll(java.util.Collection<PolylineOptions> collection) {
            for (PolylineOptions polylineOptions : collection) {
                addPolyline(polylineOptions);
            }
        }

        public Polyline addPolyline(PolylineOptions polylineOptions) {
            Polyline addPolyline = PolylineManager.this.mMap.addPolyline(polylineOptions);
            super.add(addPolyline);
            return addPolyline;
        }

        public java.util.Collection<Polyline> getPolylines() {
            return getObjects();
        }

        public void hideAll() {
            for (Polyline polyline : getPolylines()) {
                polyline.setVisible(false);
            }
        }

        public boolean remove(Polyline polyline) {
            return super.remove((Collection) polyline);
        }

        public void setOnPolylineClickListener(GoogleMap.OnPolylineClickListener onPolylineClickListener) {
            this.c = onPolylineClickListener;
        }

        public void showAll() {
            for (Polyline polyline : getPolylines()) {
                polyline.setVisible(true);
            }
        }

        public void addAll(java.util.Collection<PolylineOptions> collection, boolean z) {
            for (PolylineOptions polylineOptions : collection) {
                addPolyline(polylineOptions).setVisible(z);
            }
        }
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public Collection newCollection() {
        return new Collection();
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public void removeObjectFromMap(Polyline polyline) {
        polyline.remove();
    }
}
