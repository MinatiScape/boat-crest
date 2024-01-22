package org.slf4j.helpers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;
/* loaded from: classes13.dex */
public class BasicMarkerFactory implements IMarkerFactory {

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentMap<String, Marker> f15574a = new ConcurrentHashMap();

    @Override // org.slf4j.IMarkerFactory
    public boolean detachMarker(String str) {
        return (str == null || this.f15574a.remove(str) == null) ? false : true;
    }

    @Override // org.slf4j.IMarkerFactory
    public boolean exists(String str) {
        if (str == null) {
            return false;
        }
        return this.f15574a.containsKey(str);
    }

    @Override // org.slf4j.IMarkerFactory
    public Marker getDetachedMarker(String str) {
        return new BasicMarker(str);
    }

    @Override // org.slf4j.IMarkerFactory
    public Marker getMarker(String str) {
        if (str != null) {
            Marker marker = this.f15574a.get(str);
            if (marker == null) {
                BasicMarker basicMarker = new BasicMarker(str);
                Marker putIfAbsent = this.f15574a.putIfAbsent(str, basicMarker);
                return putIfAbsent != null ? putIfAbsent : basicMarker;
            }
            return marker;
        }
        throw new IllegalArgumentException("Marker name cannot be null");
    }
}
