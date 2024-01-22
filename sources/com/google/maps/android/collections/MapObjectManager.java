package com.google.maps.android.collections;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.collections.MapObjectManager.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public abstract class MapObjectManager<O, C extends Collection> {
    public final Map<String, C> h = new HashMap();
    public final Map<O, C> mAllObjects = new HashMap();
    public final GoogleMap mMap;

    /* loaded from: classes10.dex */
    public class Collection {

        /* renamed from: a  reason: collision with root package name */
        public final Set<O> f11544a = new HashSet();

        public Collection() {
        }

        public void add(O o) {
            this.f11544a.add(o);
            MapObjectManager.this.mAllObjects.put(o, this);
        }

        public void clear() {
            for (O o : this.f11544a) {
                MapObjectManager.this.removeObjectFromMap(o);
                MapObjectManager.this.mAllObjects.remove(o);
            }
            this.f11544a.clear();
        }

        public java.util.Collection<O> getObjects() {
            return Collections.unmodifiableCollection(this.f11544a);
        }

        public boolean remove(O o) {
            if (this.f11544a.remove(o)) {
                MapObjectManager.this.mAllObjects.remove(o);
                MapObjectManager.this.removeObjectFromMap(o);
                return true;
            }
            return false;
        }
    }

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MapObjectManager.this.a();
        }
    }

    public MapObjectManager(@NonNull GoogleMap googleMap) {
        this.mMap = googleMap;
        new Handler(Looper.getMainLooper()).post(new a());
    }

    public abstract void a();

    public C getCollection(String str) {
        return this.h.get(str);
    }

    public abstract C newCollection();

    public C newCollection(String str) {
        if (this.h.get(str) == null) {
            C newCollection = newCollection();
            this.h.put(str, newCollection);
            return newCollection;
        }
        throw new IllegalArgumentException("collection id is not unique: " + str);
    }

    public boolean remove(O o) {
        C c = this.mAllObjects.get(o);
        return c != null && c.remove(o);
    }

    public abstract void removeObjectFromMap(O o);
}
