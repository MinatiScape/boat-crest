package com.google.maps.android.collections;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.maps.android.collections.MapObjectManager;
/* loaded from: classes10.dex */
public class CircleManager extends MapObjectManager<Circle, Collection> implements GoogleMap.OnCircleClickListener {
    public CircleManager(@NonNull GoogleMap googleMap) {
        super(googleMap);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public void a() {
        GoogleMap googleMap = this.mMap;
        if (googleMap != null) {
            googleMap.setOnCircleClickListener(this);
        }
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnCircleClickListener
    public void onCircleClick(Circle circle) {
        Collection collection = (Collection) this.mAllObjects.get(circle);
        if (collection == null || collection.c == null) {
            return;
        }
        collection.c.onCircleClick(circle);
    }

    /* loaded from: classes10.dex */
    public class Collection extends MapObjectManager.Collection {
        public GoogleMap.OnCircleClickListener c;

        public Collection() {
            super();
        }

        public void addAll(java.util.Collection<CircleOptions> collection) {
            for (CircleOptions circleOptions : collection) {
                addCircle(circleOptions);
            }
        }

        public Circle addCircle(CircleOptions circleOptions) {
            Circle addCircle = CircleManager.this.mMap.addCircle(circleOptions);
            super.add(addCircle);
            return addCircle;
        }

        public java.util.Collection<Circle> getCircles() {
            return getObjects();
        }

        public void hideAll() {
            for (Circle circle : getCircles()) {
                circle.setVisible(false);
            }
        }

        public boolean remove(Circle circle) {
            return super.remove((Collection) circle);
        }

        public void setOnCircleClickListener(GoogleMap.OnCircleClickListener onCircleClickListener) {
            this.c = onCircleClickListener;
        }

        public void showAll() {
            for (Circle circle : getCircles()) {
                circle.setVisible(true);
            }
        }

        public void addAll(java.util.Collection<CircleOptions> collection, boolean z) {
            for (CircleOptions circleOptions : collection) {
                addCircle(circleOptions).setVisible(z);
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.maps.android.collections.MapObjectManager
    public Collection newCollection() {
        return new Collection();
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public void removeObjectFromMap(Circle circle) {
        circle.remove();
    }
}
