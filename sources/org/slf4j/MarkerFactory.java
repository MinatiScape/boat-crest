package org.slf4j;

import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMarkerBinder;
/* loaded from: classes13.dex */
public class MarkerFactory {

    /* renamed from: a  reason: collision with root package name */
    public static IMarkerFactory f15571a;

    static {
        try {
            f15571a = a();
        } catch (Exception e) {
            Util.report("Unexpected failure while binding MarkerFactory", e);
        } catch (NoClassDefFoundError unused) {
            f15571a = new BasicMarkerFactory();
        }
    }

    public static IMarkerFactory a() throws NoClassDefFoundError {
        try {
            return StaticMarkerBinder.getSingleton().getMarkerFactory();
        } catch (NoSuchMethodError unused) {
            return StaticMarkerBinder.SINGLETON.getMarkerFactory();
        }
    }

    public static Marker getDetachedMarker(String str) {
        return f15571a.getDetachedMarker(str);
    }

    public static IMarkerFactory getIMarkerFactory() {
        return f15571a;
    }

    public static Marker getMarker(String str) {
        return f15571a.getMarker(str);
    }
}
