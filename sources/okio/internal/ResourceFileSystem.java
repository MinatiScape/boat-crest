package okio.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.f;
import kotlin.collections.i;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Path;
import okio.Sink;
import okio.Source;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ResourceFileSystem extends FileSystem {
    @NotNull
    public static final a b = new a(null);
    @Deprecated
    @NotNull
    public static final Path c = Path.Companion.get$default(Path.Companion, MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 1, (Object) null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Lazy f14323a;

    /* loaded from: classes12.dex */
    public static final class a {

        /* renamed from: okio.internal.ResourceFileSystem$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0898a extends Lambda implements Function1<ZipEntry, Boolean> {
            public static final C0898a INSTANCE = new C0898a();

            public C0898a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Boolean invoke(@NotNull ZipEntry entry) {
                Intrinsics.checkNotNullParameter(entry, "entry");
                return Boolean.valueOf(ResourceFileSystem.b.c(entry.getCanonicalPath()));
            }
        }

        public a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Path b() {
            return ResourceFileSystem.c;
        }

        public final boolean c(Path path) {
            return !m.endsWith(path.name(), ".class", true);
        }

        @NotNull
        public final Path d(@NotNull Path path, @NotNull Path base) {
            Intrinsics.checkNotNullParameter(path, "<this>");
            Intrinsics.checkNotNullParameter(base, "base");
            return b().resolve(m.replace$default(StringsKt__StringsKt.removePrefix(path.toString(), (CharSequence) base.toString()), '\\', '/', false, 4, (Object) null));
        }

        @NotNull
        public final List<Pair<FileSystem, Path>> e(@NotNull ClassLoader classLoader) {
            Intrinsics.checkNotNullParameter(classLoader, "<this>");
            Enumeration<URL> resources = classLoader.getResources("");
            Intrinsics.checkNotNullExpressionValue(resources, "getResources(\"\")");
            ArrayList<URL> list = Collections.list(resources);
            Intrinsics.checkNotNullExpressionValue(list, "list(this)");
            ArrayList arrayList = new ArrayList();
            for (URL it : list) {
                a aVar = ResourceFileSystem.b;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Pair<FileSystem, Path> f = aVar.f(it);
                if (f != null) {
                    arrayList.add(f);
                }
            }
            Enumeration<URL> resources2 = classLoader.getResources("META-INF/MANIFEST.MF");
            Intrinsics.checkNotNullExpressionValue(resources2, "getResources(\"META-INF/MANIFEST.MF\")");
            ArrayList<URL> list2 = Collections.list(resources2);
            Intrinsics.checkNotNullExpressionValue(list2, "list(this)");
            ArrayList arrayList2 = new ArrayList();
            for (URL it2 : list2) {
                a aVar2 = ResourceFileSystem.b;
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                Pair<FileSystem, Path> g = aVar2.g(it2);
                if (g != null) {
                    arrayList2.add(g);
                }
            }
            return CollectionsKt___CollectionsKt.plus((Collection) arrayList, (Iterable) arrayList2);
        }

        @Nullable
        public final Pair<FileSystem, Path> f(@NotNull URL url) {
            Intrinsics.checkNotNullParameter(url, "<this>");
            if (Intrinsics.areEqual(url.getProtocol(), "file")) {
                return TuplesKt.to(FileSystem.SYSTEM, Path.Companion.get$default(Path.Companion, new File(url.toURI()), false, 1, (Object) null));
            }
            return null;
        }

        @Nullable
        public final Pair<FileSystem, Path> g(@NotNull URL url) {
            int lastIndexOf$default;
            Intrinsics.checkNotNullParameter(url, "<this>");
            String url2 = url.toString();
            Intrinsics.checkNotNullExpressionValue(url2, "toString()");
            if (m.startsWith$default(url2, "jar:file:", false, 2, null) && (lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) url2, "!", 0, false, 6, (Object) null)) != -1) {
                Path.Companion companion = Path.Companion;
                String substring = url2.substring(4, lastIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                return TuplesKt.to(ZipKt.openZip(Path.Companion.get$default(companion, new File(URI.create(substring)), false, 1, (Object) null), FileSystem.SYSTEM, C0898a.INSTANCE), b());
            }
            return null;
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Lambda implements Function0<List<? extends Pair<? extends FileSystem, ? extends Path>>> {
        public final /* synthetic */ ClassLoader $classLoader;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(ClassLoader classLoader) {
            super(0);
            this.$classLoader = classLoader;
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final List<? extends Pair<? extends FileSystem, ? extends Path>> invoke() {
            return ResourceFileSystem.b.e(this.$classLoader);
        }
    }

    public ResourceFileSystem(@NotNull ClassLoader classLoader, boolean z) {
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        this.f14323a = LazyKt__LazyJVMKt.lazy(new b(classLoader));
        if (z) {
            b().size();
        }
    }

    public final Path a(Path path) {
        return c.resolve(path, true);
    }

    @Override // okio.FileSystem
    @NotNull
    public Sink appendingSink(@NotNull Path file, boolean z) {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void atomicMove(@NotNull Path source, @NotNull Path target) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        throw new IOException(this + " is read-only");
    }

    public final List<Pair<FileSystem, Path>> b() {
        return (List) this.f14323a.getValue();
    }

    public final String c(Path path) {
        return a(path).relativeTo(c).toString();
    }

    @Override // okio.FileSystem
    @NotNull
    public Path canonicalize(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        return a(path);
    }

    @Override // okio.FileSystem
    public void createDirectory(@NotNull Path dir, boolean z) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void createSymlink(@NotNull Path source, @NotNull Path target) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void delete(@NotNull Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "path");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    @NotNull
    public List<Path> list(@NotNull Path dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        String c2 = c(dir);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        boolean z = false;
        for (Pair<FileSystem, Path> pair : b()) {
            FileSystem component1 = pair.component1();
            Path component2 = pair.component2();
            try {
                List<Path> list = component1.list(component2.resolve(c2));
                ArrayList<Path> arrayList = new ArrayList();
                for (Object obj : list) {
                    if (b.c((Path) obj)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList(f.collectionSizeOrDefault(arrayList, 10));
                for (Path path : arrayList) {
                    arrayList2.add(b.d(path, component2));
                }
                i.addAll(linkedHashSet, arrayList2);
                z = true;
            } catch (IOException unused) {
            }
        }
        if (z) {
            return CollectionsKt___CollectionsKt.toList(linkedHashSet);
        }
        throw new FileNotFoundException("file not found: " + dir);
    }

    @Override // okio.FileSystem
    @Nullable
    public List<Path> listOrNull(@NotNull Path dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        String c2 = c(dir);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Pair<FileSystem, Path>> it = b().iterator();
        boolean z = false;
        while (true) {
            ArrayList arrayList = null;
            if (!it.hasNext()) {
                break;
            }
            Pair<FileSystem, Path> next = it.next();
            Path component2 = next.component2();
            List<Path> listOrNull = next.component1().listOrNull(component2.resolve(c2));
            if (listOrNull != null) {
                ArrayList<Path> arrayList2 = new ArrayList();
                for (Object obj : listOrNull) {
                    if (b.c((Path) obj)) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = new ArrayList(f.collectionSizeOrDefault(arrayList2, 10));
                for (Path path : arrayList2) {
                    arrayList3.add(b.d(path, component2));
                }
                arrayList = arrayList3;
            }
            if (arrayList != null) {
                i.addAll(linkedHashSet, arrayList);
                z = true;
            }
        }
        if (z) {
            return CollectionsKt___CollectionsKt.toList(linkedHashSet);
        }
        return null;
    }

    @Override // okio.FileSystem
    @Nullable
    public FileMetadata metadataOrNull(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        if (b.c(path)) {
            String c2 = c(path);
            for (Pair<FileSystem, Path> pair : b()) {
                FileMetadata metadataOrNull = pair.component1().metadataOrNull(pair.component2().resolve(c2));
                if (metadataOrNull != null) {
                    return metadataOrNull;
                }
            }
            return null;
        }
        return null;
    }

    @Override // okio.FileSystem
    @NotNull
    public FileHandle openReadOnly(@NotNull Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (b.c(file)) {
            String c2 = c(file);
            for (Pair<FileSystem, Path> pair : b()) {
                try {
                    return pair.component1().openReadOnly(pair.component2().resolve(c2));
                } catch (FileNotFoundException unused) {
                }
            }
            throw new FileNotFoundException("file not found: " + file);
        }
        throw new FileNotFoundException("file not found: " + file);
    }

    @Override // okio.FileSystem
    @NotNull
    public FileHandle openReadWrite(@NotNull Path file, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new IOException("resources are not writable");
    }

    @Override // okio.FileSystem
    @NotNull
    public Sink sink(@NotNull Path file, boolean z) {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    @NotNull
    public Source source(@NotNull Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (b.c(file)) {
            String c2 = c(file);
            for (Pair<FileSystem, Path> pair : b()) {
                try {
                    return pair.component1().source(pair.component2().resolve(c2));
                } catch (FileNotFoundException unused) {
                }
            }
            throw new FileNotFoundException("file not found: " + file);
        }
        throw new FileNotFoundException("file not found: " + file);
    }
}
