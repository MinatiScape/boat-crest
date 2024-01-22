package com.facebook.stetho.inspector.network;

import com.crrepa.r.a;
import com.facebook.stetho.inspector.protocol.module.Page;
import org.slf4j.Marker;
/* loaded from: classes9.dex */
public class ResourceTypeHelper {
    private final MimeMatcher<Page.ResourceType> mMimeMatcher;

    public ResourceTypeHelper() {
        MimeMatcher<Page.ResourceType> mimeMatcher = new MimeMatcher<>();
        this.mMimeMatcher = mimeMatcher;
        mimeMatcher.addRule("text/css", Page.ResourceType.STYLESHEET);
        mimeMatcher.addRule(a.d, Page.ResourceType.IMAGE);
        mimeMatcher.addRule("application/x-javascript", Page.ResourceType.SCRIPT);
        Page.ResourceType resourceType = Page.ResourceType.XHR;
        mimeMatcher.addRule("text/javascript", resourceType);
        mimeMatcher.addRule("application/json", resourceType);
        mimeMatcher.addRule("text/*", Page.ResourceType.DOCUMENT);
        mimeMatcher.addRule(Marker.ANY_MARKER, Page.ResourceType.OTHER);
    }

    public Page.ResourceType determineResourceType(String str) {
        return this.mMimeMatcher.match(stripContentExtras(str));
    }

    public String stripContentExtras(String str) {
        int indexOf = str.indexOf(59);
        return indexOf >= 0 ? str.substring(0, indexOf) : str;
    }
}
