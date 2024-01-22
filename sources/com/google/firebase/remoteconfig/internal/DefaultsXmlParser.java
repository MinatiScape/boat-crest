package com.google.firebase.remoteconfig.internal;
/* loaded from: classes10.dex */
public class DefaultsXmlParser {
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0074, code lost:
        if (r10 == 1) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0076, code lost:
        android.util.Log.w(com.google.firebase.remoteconfig.FirebaseRemoteConfig.TAG, "Encountered an unexpected tag while parsing the defaults XML.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007c, code lost:
        r5 = r9.getText();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map<java.lang.String, java.lang.String> getDefaultsFromXml(android.content.Context r9, int r10) {
        /*
            java.lang.String r0 = "FirebaseRemoteConfig"
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            android.content.res.Resources r9 = r9.getResources()     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            if (r9 != 0) goto L13
            java.lang.String r9 = "Could not find the resources of the current context while trying to set defaults from an XML."
            android.util.Log.e(r0, r9)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            return r1
        L13:
            android.content.res.XmlResourceParser r9 = r9.getXml(r10)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            int r10 = r9.getEventType()     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            r2 = 0
            r3 = r2
            r4 = r3
            r5 = r4
        L1f:
            r6 = 1
            if (r10 == r6) goto L92
            r7 = 2
            if (r10 != r7) goto L2a
            java.lang.String r3 = r9.getName()     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            goto L85
        L2a:
            r7 = 3
            if (r10 != r7) goto L4a
            java.lang.String r10 = r9.getName()     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            java.lang.String r3 = "entry"
            boolean r10 = r10.equals(r3)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            if (r10 == 0) goto L48
            if (r4 == 0) goto L41
            if (r5 == 0) goto L41
            r1.put(r4, r5)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            goto L46
        L41:
            java.lang.String r10 = "An entry in the defaults XML has an invalid key and/or value tag."
            android.util.Log.w(r0, r10)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
        L46:
            r4 = r2
            r5 = r4
        L48:
            r3 = r2
            goto L85
        L4a:
            r7 = 4
            if (r10 != r7) goto L85
            if (r3 == 0) goto L85
            r10 = -1
            int r7 = r3.hashCode()     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            r8 = 106079(0x19e5f, float:1.48648E-40)
            if (r7 == r8) goto L69
            r8 = 111972721(0x6ac9171, float:6.4912916E-35)
            if (r7 == r8) goto L5f
            goto L72
        L5f:
            java.lang.String r7 = "value"
            boolean r7 = r3.equals(r7)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            if (r7 == 0) goto L72
            r10 = r6
            goto L72
        L69:
            java.lang.String r7 = "key"
            boolean r7 = r3.equals(r7)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            if (r7 == 0) goto L72
            r10 = 0
        L72:
            if (r10 == 0) goto L81
            if (r10 == r6) goto L7c
            java.lang.String r10 = "Encountered an unexpected tag while parsing the defaults XML."
            android.util.Log.w(r0, r10)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            goto L85
        L7c:
            java.lang.String r5 = r9.getText()     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            goto L85
        L81:
            java.lang.String r4 = r9.getText()     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
        L85:
            int r10 = r9.next()     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L8c
            goto L1f
        L8a:
            r9 = move-exception
            goto L8d
        L8c:
            r9 = move-exception
        L8d:
            java.lang.String r10 = "Encountered an error while parsing the defaults XML file."
            android.util.Log.e(r0, r10, r9)
        L92:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.internal.DefaultsXmlParser.getDefaultsFromXml(android.content.Context, int):java.util.Map");
    }
}
