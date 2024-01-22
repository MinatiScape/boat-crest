package com.google.maps.android.clustering.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.R;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.collections.MarkerManager;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.ui.IconGenerator;
import com.google.maps.android.ui.SquareTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes10.dex */
public class DefaultClusterRenderer<T extends ClusterItem> implements ClusterRenderer<T> {
    public static final int[] v = {10, 20, 50, 100, 200, 500, 1000};
    public static final TimeInterpolator w = new DecelerateInterpolator();

    /* renamed from: a  reason: collision with root package name */
    public final GoogleMap f11538a;
    public final IconGenerator b;
    public final ClusterManager<T> c;
    public final float d;
    public ShapeDrawable g;
    public Set<? extends Cluster<T>> l;
    public float n;
    public ClusterManager.OnClusterClickListener<T> p;
    public ClusterManager.OnClusterInfoWindowClickListener<T> q;
    public ClusterManager.OnClusterInfoWindowLongClickListener<T> r;
    public ClusterManager.OnClusterItemClickListener<T> s;
    public ClusterManager.OnClusterItemInfoWindowClickListener<T> t;
    public ClusterManager.OnClusterItemInfoWindowLongClickListener<T> u;
    public final Executor f = Executors.newSingleThreadExecutor();
    public Set<k> h = Collections.newSetFromMap(new ConcurrentHashMap());
    public SparseArray<BitmapDescriptor> i = new SparseArray<>();
    public i<T> j = new i<>(null);
    public int k = 4;
    public i<Cluster<T>> m = new i<>(null);
    public final DefaultClusterRenderer<T>.m o = new m(this, null);
    public boolean e = true;

    /* loaded from: classes10.dex */
    public class a implements GoogleMap.OnMarkerClickListener {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
        public boolean onMarkerClick(Marker marker) {
            return DefaultClusterRenderer.this.s != null && DefaultClusterRenderer.this.s.onClusterItemClick((ClusterItem) DefaultClusterRenderer.this.j.b(marker));
        }
    }

    /* loaded from: classes10.dex */
    public class b implements GoogleMap.OnInfoWindowClickListener {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
        public void onInfoWindowClick(Marker marker) {
            if (DefaultClusterRenderer.this.t != null) {
                DefaultClusterRenderer.this.t.onClusterItemInfoWindowClick((ClusterItem) DefaultClusterRenderer.this.j.b(marker));
            }
        }
    }

    /* loaded from: classes10.dex */
    public class c implements GoogleMap.OnInfoWindowLongClickListener {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.maps.GoogleMap.OnInfoWindowLongClickListener
        public void onInfoWindowLongClick(Marker marker) {
            if (DefaultClusterRenderer.this.u != null) {
                DefaultClusterRenderer.this.u.onClusterItemInfoWindowLongClick((ClusterItem) DefaultClusterRenderer.this.j.b(marker));
            }
        }
    }

    /* loaded from: classes10.dex */
    public class d implements GoogleMap.OnMarkerClickListener {
        public d() {
        }

        @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
        public boolean onMarkerClick(Marker marker) {
            return DefaultClusterRenderer.this.p != null && DefaultClusterRenderer.this.p.onClusterClick((Cluster) DefaultClusterRenderer.this.m.b(marker));
        }
    }

    /* loaded from: classes10.dex */
    public class e implements GoogleMap.OnInfoWindowClickListener {
        public e() {
        }

        @Override // com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
        public void onInfoWindowClick(Marker marker) {
            if (DefaultClusterRenderer.this.q != null) {
                DefaultClusterRenderer.this.q.onClusterInfoWindowClick((Cluster) DefaultClusterRenderer.this.m.b(marker));
            }
        }
    }

    /* loaded from: classes10.dex */
    public class f implements GoogleMap.OnInfoWindowLongClickListener {
        public f() {
        }

        @Override // com.google.android.gms.maps.GoogleMap.OnInfoWindowLongClickListener
        public void onInfoWindowLongClick(Marker marker) {
            if (DefaultClusterRenderer.this.r != null) {
                DefaultClusterRenderer.this.r.onClusterInfoWindowLongClick((Cluster) DefaultClusterRenderer.this.m.b(marker));
            }
        }
    }

    @TargetApi(12)
    /* loaded from: classes10.dex */
    public class g extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        public final k h;
        public final Marker i;
        public final LatLng j;
        public final LatLng k;
        public boolean l;
        public MarkerManager m;

        public /* synthetic */ g(DefaultClusterRenderer defaultClusterRenderer, k kVar, LatLng latLng, LatLng latLng2, a aVar) {
            this(kVar, latLng, latLng2);
        }

        public void a() {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setInterpolator(DefaultClusterRenderer.w);
            ofFloat.addUpdateListener(this);
            ofFloat.addListener(this);
            ofFloat.start();
        }

        public void b(MarkerManager markerManager) {
            this.m = markerManager;
            this.l = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.l) {
                DefaultClusterRenderer.this.j.d(this.i);
                DefaultClusterRenderer.this.m.d(this.i);
                this.m.remove(this.i);
            }
            this.h.b = this.k;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            LatLng latLng = this.k;
            double d = latLng.latitude;
            LatLng latLng2 = this.j;
            double d2 = latLng2.latitude;
            double d3 = animatedFraction;
            double d4 = ((d - d2) * d3) + d2;
            double d5 = latLng.longitude - latLng2.longitude;
            if (Math.abs(d5) > 180.0d) {
                d5 -= Math.signum(d5) * 360.0d;
            }
            this.i.setPosition(new LatLng(d4, (d5 * d3) + this.j.longitude));
        }

        public g(k kVar, LatLng latLng, LatLng latLng2) {
            this.h = kVar;
            this.i = kVar.f11542a;
            this.j = latLng;
            this.k = latLng2;
        }
    }

    /* loaded from: classes10.dex */
    public class h {

        /* renamed from: a  reason: collision with root package name */
        public final Cluster<T> f11539a;
        public final Set<k> b;
        public final LatLng c;

        public h(Cluster<T> cluster, Set<k> set, LatLng latLng) {
            this.f11539a = cluster;
            this.b = set;
            this.c = latLng;
        }

        public final void b(DefaultClusterRenderer<T>.j jVar) {
            k kVar;
            k kVar2;
            if (DefaultClusterRenderer.this.shouldRenderAsCluster(this.f11539a)) {
                Marker a2 = DefaultClusterRenderer.this.m.a(this.f11539a);
                if (a2 == null) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLng latLng = this.c;
                    if (latLng == null) {
                        latLng = this.f11539a.getPosition();
                    }
                    MarkerOptions position = markerOptions.position(latLng);
                    DefaultClusterRenderer.this.onBeforeClusterRendered(this.f11539a, position);
                    a2 = DefaultClusterRenderer.this.c.getClusterMarkerCollection().addMarker(position);
                    DefaultClusterRenderer.this.m.c(this.f11539a, a2);
                    kVar = new k(a2, null);
                    LatLng latLng2 = this.c;
                    if (latLng2 != null) {
                        jVar.b(kVar, latLng2, this.f11539a.getPosition());
                    }
                } else {
                    kVar = new k(a2, null);
                    DefaultClusterRenderer.this.onClusterUpdated(this.f11539a, a2);
                }
                DefaultClusterRenderer.this.onClusterRendered(this.f11539a, a2);
                this.b.add(kVar);
                return;
            }
            for (T t : this.f11539a.getItems()) {
                Marker a3 = DefaultClusterRenderer.this.j.a(t);
                if (a3 == null) {
                    MarkerOptions markerOptions2 = new MarkerOptions();
                    LatLng latLng3 = this.c;
                    if (latLng3 != null) {
                        markerOptions2.position(latLng3);
                    } else {
                        markerOptions2.position(t.getPosition());
                    }
                    DefaultClusterRenderer.this.onBeforeClusterItemRendered(t, markerOptions2);
                    a3 = DefaultClusterRenderer.this.c.getMarkerCollection().addMarker(markerOptions2);
                    kVar2 = new k(a3, null);
                    DefaultClusterRenderer.this.j.c(t, a3);
                    LatLng latLng4 = this.c;
                    if (latLng4 != null) {
                        jVar.b(kVar2, latLng4, t.getPosition());
                    }
                } else {
                    kVar2 = new k(a3, null);
                    DefaultClusterRenderer.this.onClusterItemUpdated(t, a3);
                }
                DefaultClusterRenderer.this.onClusterItemRendered(t, a3);
                this.b.add(kVar2);
            }
        }
    }

    @SuppressLint({"HandlerLeak"})
    /* loaded from: classes10.dex */
    public class j extends Handler implements MessageQueue.IdleHandler {

        /* renamed from: a  reason: collision with root package name */
        public final Lock f11541a;
        public final Condition b;
        public Queue<DefaultClusterRenderer<T>.h> c;
        public Queue<DefaultClusterRenderer<T>.h> d;
        public Queue<Marker> e;
        public Queue<Marker> f;
        public Queue<DefaultClusterRenderer<T>.g> g;
        public boolean h;

        public /* synthetic */ j(DefaultClusterRenderer defaultClusterRenderer, a aVar) {
            this();
        }

        public void a(boolean z, DefaultClusterRenderer<T>.h hVar) {
            this.f11541a.lock();
            sendEmptyMessage(0);
            if (z) {
                this.d.add(hVar);
            } else {
                this.c.add(hVar);
            }
            this.f11541a.unlock();
        }

        public void b(k kVar, LatLng latLng, LatLng latLng2) {
            this.f11541a.lock();
            this.g.add(new g(DefaultClusterRenderer.this, kVar, latLng, latLng2, null));
            this.f11541a.unlock();
        }

        @TargetApi(11)
        public void c(k kVar, LatLng latLng, LatLng latLng2) {
            this.f11541a.lock();
            DefaultClusterRenderer<T>.g gVar = new g(DefaultClusterRenderer.this, kVar, latLng, latLng2, null);
            gVar.b(DefaultClusterRenderer.this.c.getMarkerManager());
            this.g.add(gVar);
            this.f11541a.unlock();
        }

        public boolean d() {
            boolean z;
            try {
                this.f11541a.lock();
                if (this.c.isEmpty() && this.d.isEmpty() && this.f.isEmpty() && this.e.isEmpty()) {
                    if (this.g.isEmpty()) {
                        z = false;
                        return z;
                    }
                }
                z = true;
                return z;
            } finally {
                this.f11541a.unlock();
            }
        }

        @TargetApi(11)
        public final void e() {
            if (!this.f.isEmpty()) {
                g(this.f.poll());
            } else if (!this.g.isEmpty()) {
                this.g.poll().a();
            } else if (!this.d.isEmpty()) {
                this.d.poll().b(this);
            } else if (!this.c.isEmpty()) {
                this.c.poll().b(this);
            } else if (this.e.isEmpty()) {
            } else {
                g(this.e.poll());
            }
        }

        public void f(boolean z, Marker marker) {
            this.f11541a.lock();
            sendEmptyMessage(0);
            if (z) {
                this.f.add(marker);
            } else {
                this.e.add(marker);
            }
            this.f11541a.unlock();
        }

        public final void g(Marker marker) {
            DefaultClusterRenderer.this.j.d(marker);
            DefaultClusterRenderer.this.m.d(marker);
            DefaultClusterRenderer.this.c.getMarkerManager().remove(marker);
        }

        public void h() {
            while (d()) {
                sendEmptyMessage(0);
                this.f11541a.lock();
                try {
                    try {
                        if (d()) {
                            this.b.await();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } finally {
                    this.f11541a.unlock();
                }
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (!this.h) {
                Looper.myQueue().addIdleHandler(this);
                this.h = true;
            }
            removeMessages(0);
            this.f11541a.lock();
            for (int i = 0; i < 10; i++) {
                try {
                    e();
                } finally {
                    this.f11541a.unlock();
                }
            }
            if (!d()) {
                this.h = false;
                Looper.myQueue().removeIdleHandler(this);
                this.b.signalAll();
            } else {
                sendEmptyMessageDelayed(0, 10L);
            }
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            sendEmptyMessage(0);
            return true;
        }

        public j() {
            super(Looper.getMainLooper());
            ReentrantLock reentrantLock = new ReentrantLock();
            this.f11541a = reentrantLock;
            this.b = reentrantLock.newCondition();
            this.c = new LinkedList();
            this.d = new LinkedList();
            this.e = new LinkedList();
            this.f = new LinkedList();
            this.g = new LinkedList();
        }
    }

    /* loaded from: classes10.dex */
    public static class k {

        /* renamed from: a  reason: collision with root package name */
        public final Marker f11542a;
        public LatLng b;

        public /* synthetic */ k(Marker marker, a aVar) {
            this(marker);
        }

        public boolean equals(Object obj) {
            if (obj instanceof k) {
                return this.f11542a.equals(((k) obj).f11542a);
            }
            return false;
        }

        public int hashCode() {
            return this.f11542a.hashCode();
        }

        public k(Marker marker) {
            this.f11542a = marker;
            this.b = marker.getPosition();
        }
    }

    /* loaded from: classes10.dex */
    public class l implements Runnable {
        public final Set<? extends Cluster<T>> h;
        public Runnable i;
        public Projection j;
        public SphericalMercatorProjection k;
        public float l;

        public /* synthetic */ l(DefaultClusterRenderer defaultClusterRenderer, Set set, a aVar) {
            this(set);
        }

        public void a(Runnable runnable) {
            this.i = runnable;
        }

        public void b(float f) {
            this.l = f;
            this.k = new SphericalMercatorProjection(Math.pow(2.0d, Math.min(f, DefaultClusterRenderer.this.n)) * 256.0d);
        }

        public void c(Projection projection) {
            this.j = projection;
        }

        @Override // java.lang.Runnable
        @SuppressLint({"NewApi"})
        public void run() {
            LatLngBounds build;
            ArrayList arrayList;
            if (this.h.equals(DefaultClusterRenderer.this.l)) {
                this.i.run();
                return;
            }
            ArrayList arrayList2 = null;
            j jVar = new j(DefaultClusterRenderer.this, null);
            float f = this.l;
            boolean z = f > DefaultClusterRenderer.this.n;
            float f2 = f - DefaultClusterRenderer.this.n;
            Set<k> set = DefaultClusterRenderer.this.h;
            try {
                build = this.j.getVisibleRegion().latLngBounds;
            } catch (Exception e) {
                e.printStackTrace();
                build = LatLngBounds.builder().include(new LatLng(0.0d, 0.0d)).build();
            }
            if (DefaultClusterRenderer.this.l == null || !DefaultClusterRenderer.this.e) {
                arrayList = null;
            } else {
                arrayList = new ArrayList();
                for (Cluster<T> cluster : DefaultClusterRenderer.this.l) {
                    if (DefaultClusterRenderer.this.shouldRenderAsCluster(cluster) && build.contains(cluster.getPosition())) {
                        arrayList.add(this.k.toPoint(cluster.getPosition()));
                    }
                }
            }
            Set newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
            for (Cluster<T> cluster2 : this.h) {
                boolean contains = build.contains(cluster2.getPosition());
                if (z && contains && DefaultClusterRenderer.this.e) {
                    Point v = DefaultClusterRenderer.this.v(arrayList, this.k.toPoint(cluster2.getPosition()));
                    if (v != null) {
                        jVar.a(true, new h(cluster2, newSetFromMap, this.k.toLatLng(v)));
                    } else {
                        jVar.a(true, new h(cluster2, newSetFromMap, null));
                    }
                } else {
                    jVar.a(contains, new h(cluster2, newSetFromMap, null));
                }
            }
            jVar.h();
            set.removeAll(newSetFromMap);
            if (DefaultClusterRenderer.this.e) {
                arrayList2 = new ArrayList();
                for (Cluster<T> cluster3 : this.h) {
                    if (DefaultClusterRenderer.this.shouldRenderAsCluster(cluster3) && build.contains(cluster3.getPosition())) {
                        arrayList2.add(this.k.toPoint(cluster3.getPosition()));
                    }
                }
            }
            for (k kVar : set) {
                boolean contains2 = build.contains(kVar.b);
                if (z || f2 <= -3.0f || !contains2 || !DefaultClusterRenderer.this.e) {
                    jVar.f(contains2, kVar.f11542a);
                } else {
                    Point v2 = DefaultClusterRenderer.this.v(arrayList2, this.k.toPoint(kVar.b));
                    if (v2 != null) {
                        jVar.c(kVar, kVar.b, this.k.toLatLng(v2));
                    } else {
                        jVar.f(true, kVar.f11542a);
                    }
                }
            }
            jVar.h();
            DefaultClusterRenderer.this.h = newSetFromMap;
            DefaultClusterRenderer.this.l = this.h;
            DefaultClusterRenderer.this.n = f;
            this.i.run();
        }

        public l(Set<? extends Cluster<T>> set) {
            this.h = set;
        }
    }

    public DefaultClusterRenderer(Context context, GoogleMap googleMap, ClusterManager<T> clusterManager) {
        this.f11538a = googleMap;
        this.d = context.getResources().getDisplayMetrics().density;
        IconGenerator iconGenerator = new IconGenerator(context);
        this.b = iconGenerator;
        iconGenerator.setContentView(x(context));
        iconGenerator.setTextAppearance(R.style.amu_ClusterIcon_TextAppearance);
        iconGenerator.setBackground(w());
        this.c = clusterManager;
    }

    public static double u(Point point, Point point2) {
        double d2 = point.x;
        double d3 = point2.x;
        double d4 = (d2 - d3) * (d2 - d3);
        double d5 = point.y;
        double d6 = point2.y;
        return d4 + ((d5 - d6) * (d5 - d6));
    }

    public int getBucket(@NonNull Cluster<T> cluster) {
        int size = cluster.getSize();
        int i2 = 0;
        if (size <= v[0]) {
            return size;
        }
        while (true) {
            int[] iArr = v;
            if (i2 < iArr.length - 1) {
                int i3 = i2 + 1;
                if (size < iArr[i3]) {
                    return iArr[i2];
                }
                i2 = i3;
            } else {
                return iArr[iArr.length - 1];
            }
        }
    }

    public Cluster<T> getCluster(Marker marker) {
        return this.m.b(marker);
    }

    public T getClusterItem(Marker marker) {
        return this.j.b(marker);
    }

    @NonNull
    public String getClusterText(int i2) {
        if (i2 < v[0]) {
            return String.valueOf(i2);
        }
        return i2 + "+";
    }

    public int getColor(int i2) {
        float min = 300.0f - Math.min(i2, 300.0f);
        return Color.HSVToColor(new float[]{((min * min) / 90000.0f) * 220.0f, 1.0f, 0.6f});
    }

    @NonNull
    public BitmapDescriptor getDescriptorForCluster(@NonNull Cluster<T> cluster) {
        int bucket = getBucket(cluster);
        BitmapDescriptor bitmapDescriptor = this.i.get(bucket);
        if (bitmapDescriptor == null) {
            this.g.getPaint().setColor(getColor(bucket));
            BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap(this.b.makeIcon(getClusterText(bucket)));
            this.i.put(bucket, fromBitmap);
            return fromBitmap;
        }
        return bitmapDescriptor;
    }

    public Marker getMarker(T t) {
        return this.j.a(t);
    }

    public int getMinClusterSize() {
        return this.k;
    }

    @Override // com.google.maps.android.clustering.view.ClusterRenderer
    public void onAdd() {
        this.c.getMarkerCollection().setOnMarkerClickListener(new a());
        this.c.getMarkerCollection().setOnInfoWindowClickListener(new b());
        this.c.getMarkerCollection().setOnInfoWindowLongClickListener(new c());
        this.c.getClusterMarkerCollection().setOnMarkerClickListener(new d());
        this.c.getClusterMarkerCollection().setOnInfoWindowClickListener(new e());
        this.c.getClusterMarkerCollection().setOnInfoWindowLongClickListener(new f());
    }

    public void onBeforeClusterItemRendered(@NonNull T t, @NonNull MarkerOptions markerOptions) {
        if (t.getTitle() != null && t.getSnippet() != null) {
            markerOptions.title(t.getTitle());
            markerOptions.snippet(t.getSnippet());
        } else if (t.getTitle() != null) {
            markerOptions.title(t.getTitle());
        } else if (t.getSnippet() != null) {
            markerOptions.title(t.getSnippet());
        }
    }

    public void onBeforeClusterRendered(@NonNull Cluster<T> cluster, @NonNull MarkerOptions markerOptions) {
        markerOptions.icon(getDescriptorForCluster(cluster));
    }

    public void onClusterItemRendered(@NonNull T t, @NonNull Marker marker) {
    }

    public void onClusterItemUpdated(@NonNull T t, @NonNull Marker marker) {
        boolean z = true;
        boolean z2 = false;
        if (t.getTitle() != null && t.getSnippet() != null) {
            if (!t.getTitle().equals(marker.getTitle())) {
                marker.setTitle(t.getTitle());
                z2 = true;
            }
            if (!t.getSnippet().equals(marker.getSnippet())) {
                marker.setSnippet(t.getSnippet());
                z2 = true;
            }
        } else {
            if (t.getSnippet() != null && !t.getSnippet().equals(marker.getTitle())) {
                marker.setTitle(t.getSnippet());
            } else if (t.getTitle() != null && !t.getTitle().equals(marker.getTitle())) {
                marker.setTitle(t.getTitle());
            }
            z2 = true;
        }
        if (marker.getPosition().equals(t.getPosition())) {
            z = z2;
        } else {
            marker.setPosition(t.getPosition());
        }
        if (z && marker.isInfoWindowShown()) {
            marker.showInfoWindow();
        }
    }

    public void onClusterRendered(@NonNull Cluster<T> cluster, @NonNull Marker marker) {
    }

    public void onClusterUpdated(@NonNull Cluster<T> cluster, @NonNull Marker marker) {
        marker.setIcon(getDescriptorForCluster(cluster));
    }

    @Override // com.google.maps.android.clustering.view.ClusterRenderer
    public void onClustersChanged(Set<? extends Cluster<T>> set) {
        this.o.a(set);
    }

    @Override // com.google.maps.android.clustering.view.ClusterRenderer
    public void onRemove() {
        this.c.getMarkerCollection().setOnMarkerClickListener(null);
        this.c.getMarkerCollection().setOnInfoWindowClickListener(null);
        this.c.getMarkerCollection().setOnInfoWindowLongClickListener(null);
        this.c.getClusterMarkerCollection().setOnMarkerClickListener(null);
        this.c.getClusterMarkerCollection().setOnInfoWindowClickListener(null);
        this.c.getClusterMarkerCollection().setOnInfoWindowLongClickListener(null);
    }

    @Override // com.google.maps.android.clustering.view.ClusterRenderer
    public void setAnimation(boolean z) {
        this.e = z;
    }

    public void setMinClusterSize(int i2) {
        this.k = i2;
    }

    @Override // com.google.maps.android.clustering.view.ClusterRenderer
    public void setOnClusterClickListener(ClusterManager.OnClusterClickListener<T> onClusterClickListener) {
        this.p = onClusterClickListener;
    }

    @Override // com.google.maps.android.clustering.view.ClusterRenderer
    public void setOnClusterInfoWindowClickListener(ClusterManager.OnClusterInfoWindowClickListener<T> onClusterInfoWindowClickListener) {
        this.q = onClusterInfoWindowClickListener;
    }

    @Override // com.google.maps.android.clustering.view.ClusterRenderer
    public void setOnClusterInfoWindowLongClickListener(ClusterManager.OnClusterInfoWindowLongClickListener<T> onClusterInfoWindowLongClickListener) {
        this.r = onClusterInfoWindowLongClickListener;
    }

    @Override // com.google.maps.android.clustering.view.ClusterRenderer
    public void setOnClusterItemClickListener(ClusterManager.OnClusterItemClickListener<T> onClusterItemClickListener) {
        this.s = onClusterItemClickListener;
    }

    @Override // com.google.maps.android.clustering.view.ClusterRenderer
    public void setOnClusterItemInfoWindowClickListener(ClusterManager.OnClusterItemInfoWindowClickListener<T> onClusterItemInfoWindowClickListener) {
        this.t = onClusterItemInfoWindowClickListener;
    }

    @Override // com.google.maps.android.clustering.view.ClusterRenderer
    public void setOnClusterItemInfoWindowLongClickListener(ClusterManager.OnClusterItemInfoWindowLongClickListener<T> onClusterItemInfoWindowLongClickListener) {
        this.u = onClusterItemInfoWindowLongClickListener;
    }

    public boolean shouldRenderAsCluster(@NonNull Cluster<T> cluster) {
        return cluster.getSize() >= this.k;
    }

    public final Point v(List<Point> list, Point point) {
        Point point2 = null;
        if (list != null && !list.isEmpty()) {
            int maxDistanceBetweenClusteredItems = this.c.getAlgorithm().getMaxDistanceBetweenClusteredItems();
            double d2 = maxDistanceBetweenClusteredItems * maxDistanceBetweenClusteredItems;
            for (Point point3 : list) {
                double u = u(point3, point);
                if (u < d2) {
                    point2 = point3;
                    d2 = u;
                }
            }
        }
        return point2;
    }

    public final LayerDrawable w() {
        this.g = new ShapeDrawable(new OvalShape());
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(-2130706433);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shapeDrawable, this.g});
        int i2 = (int) (this.d * 3.0f);
        layerDrawable.setLayerInset(1, i2, i2, i2, i2);
        return layerDrawable;
    }

    public final SquareTextView x(Context context) {
        SquareTextView squareTextView = new SquareTextView(context);
        squareTextView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        squareTextView.setId(R.id.amu_text);
        int i2 = (int) (this.d * 12.0f);
        squareTextView.setPadding(i2, i2, i2, i2);
        return squareTextView;
    }

    public Marker getMarker(Cluster<T> cluster) {
        return this.m.a(cluster);
    }

    /* loaded from: classes10.dex */
    public static class i<T> {

        /* renamed from: a  reason: collision with root package name */
        public Map<T, Marker> f11540a;
        public Map<Marker, T> b;

        public i() {
            this.f11540a = new HashMap();
            this.b = new HashMap();
        }

        public Marker a(T t) {
            return this.f11540a.get(t);
        }

        public T b(Marker marker) {
            return this.b.get(marker);
        }

        public void c(T t, Marker marker) {
            this.f11540a.put(t, marker);
            this.b.put(marker, t);
        }

        public void d(Marker marker) {
            T t = this.b.get(marker);
            this.b.remove(marker);
            this.f11540a.remove(t);
        }

        public /* synthetic */ i(a aVar) {
            this();
        }
    }

    @SuppressLint({"HandlerLeak"})
    /* loaded from: classes10.dex */
    public class m extends Handler {

        /* renamed from: a  reason: collision with root package name */
        public boolean f11543a;
        public DefaultClusterRenderer<T>.l b;

        /* loaded from: classes10.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                m.this.sendEmptyMessage(1);
            }
        }

        public m() {
            this.f11543a = false;
            this.b = null;
        }

        public void a(Set<? extends Cluster<T>> set) {
            synchronized (this) {
                this.b = new l(DefaultClusterRenderer.this, set, null);
            }
            sendEmptyMessage(0);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            DefaultClusterRenderer<T>.l lVar;
            if (message.what == 1) {
                this.f11543a = false;
                if (this.b != null) {
                    sendEmptyMessage(0);
                    return;
                }
                return;
            }
            removeMessages(0);
            if (this.f11543a || this.b == null) {
                return;
            }
            Projection projection = DefaultClusterRenderer.this.f11538a.getProjection();
            synchronized (this) {
                lVar = this.b;
                this.b = null;
                this.f11543a = true;
            }
            lVar.a(new a());
            lVar.c(projection);
            lVar.b(DefaultClusterRenderer.this.f11538a.getCameraPosition().zoom);
            DefaultClusterRenderer.this.f.execute(lVar);
        }

        public /* synthetic */ m(DefaultClusterRenderer defaultClusterRenderer, a aVar) {
            this();
        }
    }
}
