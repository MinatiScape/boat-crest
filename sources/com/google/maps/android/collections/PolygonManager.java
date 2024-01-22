package com.google.maps.android.collections;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.collections.MapObjectManager;
/* loaded from: classes10.dex */
public class PolygonManager extends MapObjectManager<Polygon, Collection> implements GoogleMap.OnPolygonClickListener {
    public PolygonManager(GoogleMap googleMap) {
        super(googleMap);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public void a() {
        GoogleMap googleMap = this.mMap;
        if (googleMap != null) {
            googleMap.setOnPolygonClickListener(this);
        }
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnPolygonClickListener
    public void onPolygonClick(Polygon polygon) {
        Collection collection = (Collection) this.mAllObjects.get(polygon);
        if (collection == null || collection.c == null) {
            return;
        }
        collection.c.onPolygonClick(polygon);
    }

    /* loaded from: classes10.dex */
    public class Collection extends MapObjectManager.Collection {
        public GoogleMap.OnPolygonClickListener c;

        public Collection() {
            super();
        }

        public void addAll(java.util.Collection<PolygonOptions> collection) {
            for (PolygonOptions polygonOptions : collection) {
                addPolygon(polygonOptions);
            }
        }

        public Polygon addPolygon(PolygonOptions polygonOptions) {
            Polygon addPolygon = PolygonManager.this.mMap.addPolygon(polygonOptions);
            super.add(addPolygon);
            return addPolygon;
        }

        public java.util.Collection<Polygon> getPolygons() {
            return getObjects();
        }

        public void hideAll() {
            for (Polygon polygon : getPolygons()) {
                polygon.setVisible(false);
            }
        }

        public boolean remove(Polygon polygon) {
            return super.remove((Collection) polygon);
        }

        public void setOnPolygonClickListener(GoogleMap.OnPolygonClickListener onPolygonClickListener) {
            this.c = onPolygonClickListener;
        }

        public void showAll() {
            for (Polygon polygon : getPolygons()) {
                polygon.setVisible(true);
            }
        }

        public void addAll(java.util.Collection<PolygonOptions> collection, boolean z) {
            for (PolygonOptions polygonOptions : collection) {
                addPolygon(polygonOptions).setVisible(z);
            }
        }
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public Collection newCollection() {
        return new Collection();
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public void removeObjectFromMap(Polygon polygon) {
        polygon.remove();
    }
}
