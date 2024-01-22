package com.mappls.sdk.turf;

import androidx.annotation.Keep;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.GeoJson;
import com.mappls.sdk.geojson.Point;
@Keep
/* loaded from: classes8.dex */
public final class TurfAssertions {
    private TurfAssertions() {
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void collectionOf(com.mappls.sdk.geojson.FeatureCollection r5, java.lang.String r6, java.lang.String r7) {
        /*
            if (r7 == 0) goto L9a
            int r0 = r7.length()
            if (r0 == 0) goto L9a
            r0 = 0
            r1 = 1
            if (r5 == 0) goto L8a
            java.lang.String r2 = r5.type()
            java.lang.String r3 = "FeatureCollection"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L8a
            java.util.List r2 = r5.features()
            if (r2 == 0) goto L8a
            java.util.List r5 = r5.features()
            java.util.Iterator r5 = r5.iterator()
        L26:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L89
            java.lang.Object r2 = r5.next()
            com.mappls.sdk.geojson.Feature r2 = (com.mappls.sdk.geojson.Feature) r2
            if (r2 == 0) goto L79
            java.lang.String r3 = r2.type()
            java.lang.String r4 = "Feature"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L79
            com.mappls.sdk.geojson.Geometry r3 = r2.geometry()
            if (r3 == 0) goto L79
            com.mappls.sdk.geojson.Geometry r3 = r2.geometry()
            if (r3 == 0) goto L5b
            com.mappls.sdk.geojson.Geometry r3 = r2.geometry()
            java.lang.String r3 = r3.type()
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L5b
            goto L26
        L5b:
            com.mappls.sdk.turf.TurfException r5 = new com.mappls.sdk.turf.TurfException
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r0] = r7
            r3[r1] = r6
            r6 = 2
            com.mappls.sdk.geojson.Geometry r7 = r2.geometry()
            java.lang.String r7 = r7.type()
            r3[r6] = r7
            java.lang.String r6 = "Invalid input to %s: must be a %s, given %s"
            java.lang.String r6 = java.lang.String.format(r6, r3)
            r5.<init>(r6)
            throw r5
        L79:
            com.mappls.sdk.turf.TurfException r5 = new com.mappls.sdk.turf.TurfException
            java.lang.Object[] r6 = new java.lang.Object[r1]
            r6[r0] = r7
            java.lang.String r7 = "Invalid input to %s, Feature with geometry required"
            java.lang.String r6 = java.lang.String.format(r7, r6)
            r5.<init>(r6)
            throw r5
        L89:
            return
        L8a:
            com.mappls.sdk.turf.TurfException r5 = new com.mappls.sdk.turf.TurfException
            java.lang.Object[] r6 = new java.lang.Object[r1]
            r6[r0] = r7
            java.lang.String r7 = "Invalid input to %s, FeatureCollection required"
            java.lang.String r6 = java.lang.String.format(r7, r6)
            r5.<init>(r6)
            throw r5
        L9a:
            com.mappls.sdk.turf.TurfException r5 = new com.mappls.sdk.turf.TurfException
            java.lang.String r6 = "collectionOf() requires a name"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.turf.TurfAssertions.collectionOf(com.mappls.sdk.geojson.FeatureCollection, java.lang.String, java.lang.String):void");
    }

    public static void featureOf(Feature feature, String str, String str2) {
        if (str2 != null && str2.length() != 0) {
            if (feature != null && feature.type().equals("Feature") && feature.geometry() != null) {
                if (feature.geometry() == null || !feature.geometry().type().equals(str)) {
                    throw new TurfException(String.format("Invalid input to %s: must be a %s, given %s", str2, str, feature.geometry().type()));
                }
                return;
            }
            throw new TurfException(String.format("Invalid input to %s, Feature with geometry required", str2));
        }
        throw new TurfException(".featureOf() requires a name");
    }

    public static void geojsonType(GeoJson geoJson, String str, String str2) {
        if (str != null && str.length() != 0 && str2 != null && str2.length() != 0) {
            if (geoJson == null || !geoJson.type().equals(str)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid input to ");
                sb.append(str2);
                sb.append(": must be a ");
                sb.append(str);
                sb.append(", given ");
                sb.append(geoJson != null ? geoJson.type() : " null");
                throw new TurfException(sb.toString());
            }
            return;
        }
        throw new TurfException("Type and name required");
    }

    @Deprecated
    public static Point getCoord(Feature feature) {
        return TurfMeta.getCoord(feature);
    }
}
