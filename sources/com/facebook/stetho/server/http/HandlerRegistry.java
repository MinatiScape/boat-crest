package com.facebook.stetho.server.http;

import androidx.annotation.Nullable;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class HandlerRegistry {
    private final ArrayList<PathMatcher> mPathMatchers = new ArrayList<>();
    private final ArrayList<HttpHandler> mHttpHandlers = new ArrayList<>();

    @Nullable
    public synchronized HttpHandler lookup(String str) {
        int size = this.mPathMatchers.size();
        for (int i = 0; i < size; i++) {
            if (this.mPathMatchers.get(i).match(str)) {
                return this.mHttpHandlers.get(i);
            }
        }
        return null;
    }

    public synchronized void register(PathMatcher pathMatcher, HttpHandler httpHandler) {
        this.mPathMatchers.add(pathMatcher);
        this.mHttpHandlers.add(httpHandler);
    }

    public synchronized boolean unregister(PathMatcher pathMatcher, HttpHandler httpHandler) {
        int indexOf = this.mPathMatchers.indexOf(pathMatcher);
        if (indexOf < 0 || httpHandler != this.mHttpHandlers.get(indexOf)) {
            return false;
        }
        this.mPathMatchers.remove(indexOf);
        this.mHttpHandlers.remove(indexOf);
        return true;
    }
}
