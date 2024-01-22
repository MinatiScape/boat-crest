package com.google.firebase.platforminfo;

import android.content.Context;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Dependency;
/* loaded from: classes10.dex */
public class LibraryVersionComponent {

    /* loaded from: classes10.dex */
    public interface VersionExtractor<T> {
        String extract(T t);
    }

    public static /* synthetic */ c b(String str, VersionExtractor versionExtractor, ComponentContainer componentContainer) {
        return c.a(str, versionExtractor.extract((Context) componentContainer.get(Context.class)));
    }

    public static Component<?> create(String str, String str2) {
        return Component.intoSet(c.a(str, str2), c.class);
    }

    public static Component<?> fromContext(final String str, final VersionExtractor<Context> versionExtractor) {
        return Component.intoSetBuilder(c.class).add(Dependency.required(Context.class)).factory(new ComponentFactory() { // from class: com.google.firebase.platforminfo.d
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                c b;
                b = LibraryVersionComponent.b(str, versionExtractor, componentContainer);
                return b;
            }
        }).build();
    }
}
