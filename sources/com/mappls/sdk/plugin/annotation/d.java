package com.mappls.sdk.plugin.annotation;

import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.gestures.AndroidGesturesManager;
import com.mappls.sdk.gestures.MoveDistancesObject;
import com.mappls.sdk.gestures.MoveGestureDetector;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import java.util.ArrayList;
import java.util.List;
@UiThread
/* loaded from: classes11.dex */
public final class d {
    public static d j;

    /* renamed from: a  reason: collision with root package name */
    public MapView f13084a;
    public MapplsMap b;
    public List<AnnotationManager> c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    @Nullable
    public Annotation h;
    @Nullable
    public AnnotationManager i;

    /* loaded from: classes11.dex */
    public class a implements View.OnTouchListener {
        public final /* synthetic */ AndroidGesturesManager h;

        public a(AndroidGesturesManager androidGesturesManager) {
            this.h = androidGesturesManager;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Annotation annotation = d.this.h;
            this.h.onTouchEvent(motionEvent);
            return (d.this.h == null && annotation == null) ? false : true;
        }
    }

    /* loaded from: classes11.dex */
    public class b implements MoveGestureDetector.OnMoveGestureListener {
        public b() {
        }

        public /* synthetic */ b(d dVar, a aVar) {
            this();
        }

        @Override // com.mappls.sdk.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMove(MoveGestureDetector moveGestureDetector, float f, float f2) {
            return d.this.e(moveGestureDetector);
        }

        @Override // com.mappls.sdk.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMoveBegin(MoveGestureDetector moveGestureDetector) {
            return d.this.j(moveGestureDetector);
        }

        @Override // com.mappls.sdk.gestures.MoveGestureDetector.OnMoveGestureListener
        public void onMoveEnd(MoveGestureDetector moveGestureDetector, float f, float f2) {
            d.this.g();
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public d(MapView mapView, MapplsMap mapplsMap) {
        this(mapView, mapplsMap, new AndroidGesturesManager(mapView.getContext(), false), mapView.getScrollX(), mapView.getScrollY(), mapView.getMeasuredWidth(), mapView.getMeasuredHeight());
    }

    @VisibleForTesting
    public d(MapView mapView, MapplsMap mapplsMap, AndroidGesturesManager androidGesturesManager, int i, int i2, int i3, int i4) {
        this.c = new ArrayList();
        this.f13084a = mapView;
        this.b = mapplsMap;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = i4;
        androidGesturesManager.setMoveGestureListener(new b(this, null));
        mapView.setOnTouchListener(new a(androidGesturesManager));
    }

    public static d b(MapView mapView, MapplsMap mapplsMap) {
        d dVar = j;
        if (dVar == null || dVar.f13084a != mapView || dVar.b != mapplsMap) {
            j = new d(mapView, mapplsMap);
        }
        return j;
    }

    public static void c() {
        d dVar = j;
        if (dVar != null) {
            dVar.f13084a = null;
            dVar.b = null;
            j = null;
        }
    }

    public void d(AnnotationManager annotationManager) {
        this.c.add(annotationManager);
    }

    public boolean e(MoveGestureDetector moveGestureDetector) {
        if (this.h == null || (moveGestureDetector.getPointersCount() <= 1 && this.h.isDraggable())) {
            if (this.h != null) {
                MoveDistancesObject moveObject = moveGestureDetector.getMoveObject(0);
                PointF pointF = new PointF(moveObject.getCurrentX() - this.d, moveObject.getCurrentY() - this.e);
                float f = pointF.x;
                if (f >= 0.0f) {
                    float f2 = pointF.y;
                    if (f2 >= 0.0f && f <= this.f && f2 <= this.g) {
                        Geometry offsetGeometry = this.h.getOffsetGeometry(this.b.getProjection(), moveObject, this.d, this.e);
                        if (offsetGeometry != null) {
                            this.h.setGeometry(offsetGeometry);
                            this.i.internalUpdateSource();
                            for (OnAnnotationDragListener onAnnotationDragListener : this.i.getDragListeners()) {
                                onAnnotationDragListener.onAnnotationDrag(this.h);
                            }
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        h(this.h, this.i);
        return true;
    }

    public boolean f(@NonNull Annotation annotation, @NonNull AnnotationManager annotationManager) {
        if (annotation.isDraggable()) {
            for (OnAnnotationDragListener onAnnotationDragListener : annotationManager.getDragListeners()) {
                onAnnotationDragListener.onAnnotationDragStarted(annotation);
            }
            this.h = annotation;
            this.i = annotationManager;
            return true;
        }
        return false;
    }

    public void g() {
        h(this.h, this.i);
    }

    public void h(@Nullable Annotation annotation, @Nullable AnnotationManager annotationManager) {
        if (annotation != null && annotationManager != null) {
            for (OnAnnotationDragListener onAnnotationDragListener : annotationManager.getDragListeners()) {
                onAnnotationDragListener.onAnnotationDragFinished(annotation);
            }
        }
        this.h = null;
        this.i = null;
    }

    public void i(AnnotationManager annotationManager) {
        this.c.remove(annotationManager);
        if (this.c.isEmpty()) {
            c();
        }
    }

    public boolean j(MoveGestureDetector moveGestureDetector) {
        Annotation queryMapForFeatures;
        for (AnnotationManager annotationManager : this.c) {
            if (moveGestureDetector.getPointersCount() == 1 && (queryMapForFeatures = annotationManager.queryMapForFeatures(moveGestureDetector.getFocalPoint())) != null && f(queryMapForFeatures, annotationManager)) {
                return true;
            }
        }
        return false;
    }

    public void k() {
        h(this.h, this.i);
    }
}
