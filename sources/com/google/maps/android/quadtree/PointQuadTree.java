package com.google.maps.android.quadtree;

import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.quadtree.PointQuadTree.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes10.dex */
public class PointQuadTree<T extends Item> {

    /* renamed from: a  reason: collision with root package name */
    public final Bounds f11566a;
    public final int b;
    public Set<T> c;
    public List<PointQuadTree<T>> d;

    /* loaded from: classes10.dex */
    public interface Item {
        Point getPoint();
    }

    public PointQuadTree(double d, double d2, double d3, double d4) {
        this(new Bounds(d, d2, d3, d4));
    }

    public final void a(double d, double d2, T t) {
        List<PointQuadTree<T>> list = this.d;
        if (list != null) {
            Bounds bounds = this.f11566a;
            if (d2 < bounds.midY) {
                if (d < bounds.midX) {
                    list.get(0).a(d, d2, t);
                    return;
                } else {
                    list.get(1).a(d, d2, t);
                    return;
                }
            } else if (d < bounds.midX) {
                list.get(2).a(d, d2, t);
                return;
            } else {
                list.get(3).a(d, d2, t);
                return;
            }
        }
        if (this.c == null) {
            this.c = new LinkedHashSet();
        }
        this.c.add(t);
        if (this.c.size() <= 50 || this.b >= 40) {
            return;
        }
        d();
    }

    public void add(T t) {
        Point point = t.getPoint();
        if (this.f11566a.contains(point.x, point.y)) {
            a(point.x, point.y, t);
        }
    }

    public final boolean b(double d, double d2, T t) {
        List<PointQuadTree<T>> list = this.d;
        if (list != null) {
            Bounds bounds = this.f11566a;
            if (d2 < bounds.midY) {
                if (d < bounds.midX) {
                    return list.get(0).b(d, d2, t);
                }
                return list.get(1).b(d, d2, t);
            } else if (d < bounds.midX) {
                return list.get(2).b(d, d2, t);
            } else {
                return list.get(3).b(d, d2, t);
            }
        }
        Set<T> set = this.c;
        if (set == null) {
            return false;
        }
        return set.remove(t);
    }

    public final void c(Bounds bounds, Collection<T> collection) {
        if (this.f11566a.intersects(bounds)) {
            List<PointQuadTree<T>> list = this.d;
            if (list != null) {
                for (PointQuadTree<T> pointQuadTree : list) {
                    pointQuadTree.c(bounds, collection);
                }
            } else if (this.c != null) {
                if (bounds.contains(this.f11566a)) {
                    collection.addAll(this.c);
                    return;
                }
                for (T t : this.c) {
                    if (bounds.contains(t.getPoint())) {
                        collection.add(t);
                    }
                }
            }
        }
    }

    public void clear() {
        this.d = null;
        Set<T> set = this.c;
        if (set != null) {
            set.clear();
        }
    }

    public final void d() {
        ArrayList arrayList = new ArrayList(4);
        this.d = arrayList;
        Bounds bounds = this.f11566a;
        arrayList.add(new PointQuadTree(bounds.minX, bounds.midX, bounds.minY, bounds.midY, this.b + 1));
        List<PointQuadTree<T>> list = this.d;
        Bounds bounds2 = this.f11566a;
        list.add(new PointQuadTree<>(bounds2.midX, bounds2.maxX, bounds2.minY, bounds2.midY, this.b + 1));
        List<PointQuadTree<T>> list2 = this.d;
        Bounds bounds3 = this.f11566a;
        list2.add(new PointQuadTree<>(bounds3.minX, bounds3.midX, bounds3.midY, bounds3.maxY, this.b + 1));
        List<PointQuadTree<T>> list3 = this.d;
        Bounds bounds4 = this.f11566a;
        list3.add(new PointQuadTree<>(bounds4.midX, bounds4.maxX, bounds4.midY, bounds4.maxY, this.b + 1));
        Set<T> set = this.c;
        this.c = null;
        for (T t : set) {
            a(t.getPoint().x, t.getPoint().y, t);
        }
    }

    public boolean remove(T t) {
        Point point = t.getPoint();
        if (this.f11566a.contains(point.x, point.y)) {
            return b(point.x, point.y, t);
        }
        return false;
    }

    public Collection<T> search(Bounds bounds) {
        ArrayList arrayList = new ArrayList();
        c(bounds, arrayList);
        return arrayList;
    }

    public PointQuadTree(Bounds bounds) {
        this(bounds, 0);
    }

    public PointQuadTree(double d, double d2, double d3, double d4, int i) {
        this(new Bounds(d, d2, d3, d4), i);
    }

    public PointQuadTree(Bounds bounds, int i) {
        this.d = null;
        this.f11566a = bounds;
        this.b = i;
    }
}
