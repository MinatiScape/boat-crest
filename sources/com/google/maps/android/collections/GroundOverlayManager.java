package com.google.maps.android.collections;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.maps.android.collections.MapObjectManager;
/* loaded from: classes10.dex */
public class GroundOverlayManager extends MapObjectManager<GroundOverlay, Collection> implements GoogleMap.OnGroundOverlayClickListener {
    public GroundOverlayManager(@NonNull GoogleMap googleMap) {
        super(googleMap);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public void a() {
        GoogleMap googleMap = this.mMap;
        if (googleMap != null) {
            googleMap.setOnGroundOverlayClickListener(this);
        }
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnGroundOverlayClickListener
    public void onGroundOverlayClick(GroundOverlay groundOverlay) {
        Collection collection = (Collection) this.mAllObjects.get(groundOverlay);
        if (collection == null || collection.c == null) {
            return;
        }
        collection.c.onGroundOverlayClick(groundOverlay);
    }

    /* loaded from: classes10.dex */
    public class Collection extends MapObjectManager.Collection {
        public GoogleMap.OnGroundOverlayClickListener c;

        public Collection() {
            super();
        }

        public void addAll(java.util.Collection<GroundOverlayOptions> collection) {
            for (GroundOverlayOptions groundOverlayOptions : collection) {
                addGroundOverlay(groundOverlayOptions);
            }
        }

        public GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
            GroundOverlay addGroundOverlay = GroundOverlayManager.this.mMap.addGroundOverlay(groundOverlayOptions);
            super.add(addGroundOverlay);
            return addGroundOverlay;
        }

        public java.util.Collection<GroundOverlay> getGroundOverlays() {
            return getObjects();
        }

        public void hideAll() {
            for (GroundOverlay groundOverlay : getGroundOverlays()) {
                groundOverlay.setVisible(false);
            }
        }

        public boolean remove(GroundOverlay groundOverlay) {
            return super.remove((Collection) groundOverlay);
        }

        public void setOnGroundOverlayClickListener(GoogleMap.OnGroundOverlayClickListener onGroundOverlayClickListener) {
            this.c = onGroundOverlayClickListener;
        }

        public void showAll() {
            for (GroundOverlay groundOverlay : getGroundOverlays()) {
                groundOverlay.setVisible(true);
            }
        }

        public void addAll(java.util.Collection<GroundOverlayOptions> collection, boolean z) {
            for (GroundOverlayOptions groundOverlayOptions : collection) {
                addGroundOverlay(groundOverlayOptions).setVisible(z);
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.maps.android.collections.MapObjectManager
    public Collection newCollection() {
        return new Collection();
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public void removeObjectFromMap(GroundOverlay groundOverlay) {
        groundOverlay.remove();
    }
}
