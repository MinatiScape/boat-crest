package com.mappls.sdk.maps;

import com.mappls.sdk.maps.log.Logger;
/* loaded from: classes11.dex */
public abstract class LibraryLoader {

    /* renamed from: a  reason: collision with root package name */
    public static final LibraryLoader f12629a;
    public static volatile LibraryLoader b;
    public static boolean c;

    static {
        LibraryLoader defaultLibraryLoader = Mappls.getModuleProvider().createLibraryLoaderProvider().getDefaultLibraryLoader();
        f12629a = defaultLibraryLoader;
        b = defaultLibraryLoader;
    }

    public static synchronized void load() {
        synchronized (LibraryLoader.class) {
            try {
                if (!c) {
                    c = true;
                    b.load("mappls-gl");
                }
            } catch (UnsatisfiedLinkError e) {
                c = false;
                Logger.e("Mbgl-LibraryLoader", "Failed to load native shared library.", e);
                MapStrictMode.strictModeViolation("Failed to load native shared library.", e);
            }
        }
    }

    public static void setLibraryLoader(LibraryLoader libraryLoader) {
        b = libraryLoader;
    }

    public abstract void load(String str);
}
