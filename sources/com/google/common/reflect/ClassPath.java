package com.google.common.reflect;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.eclipse.paho.client.mqttv3.MqttTopic;
@Beta
/* loaded from: classes10.dex */
public final class ClassPath {
    public static final Logger b = Logger.getLogger(ClassPath.class.getName());
    public static final Splitter c = Splitter.on(HexStringBuilder.DEFAULT_SEPARATOR).omitEmptyStrings();

    /* renamed from: a  reason: collision with root package name */
    public final ImmutableSet<ResourceInfo> f10725a;

    @Beta
    /* loaded from: classes10.dex */
    public static final class ClassInfo extends ResourceInfo {
        public final String c;

        public ClassInfo(File file, String str, ClassLoader classLoader) {
            super(file, str, classLoader);
            this.c = ClassPath.c(str);
        }

        public String getName() {
            return this.c;
        }

        public String getPackageName() {
            return Reflection.getPackageName(this.c);
        }

        public String getSimpleName() {
            int lastIndexOf = this.c.lastIndexOf(36);
            if (lastIndexOf != -1) {
                return CharMatcher.inRange('0', '9').trimLeadingFrom(this.c.substring(lastIndexOf + 1));
            }
            String packageName = getPackageName();
            if (packageName.isEmpty()) {
                return this.c;
            }
            return this.c.substring(packageName.length() + 1);
        }

        public boolean isTopLevel() {
            return this.c.indexOf(36) == -1;
        }

        public Class<?> load() {
            try {
                return this.b.loadClass(this.c);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.reflect.ClassPath.ResourceInfo
        public String toString() {
            return this.c;
        }
    }

    @Beta
    /* loaded from: classes10.dex */
    public static class ResourceInfo {

        /* renamed from: a  reason: collision with root package name */
        public final String f10726a;
        public final ClassLoader b;

        public ResourceInfo(File file, String str, ClassLoader classLoader) {
            File file2 = (File) Preconditions.checkNotNull(file);
            this.f10726a = (String) Preconditions.checkNotNull(str);
            this.b = (ClassLoader) Preconditions.checkNotNull(classLoader);
        }

        public static ResourceInfo a(File file, String str, ClassLoader classLoader) {
            if (str.endsWith(".class")) {
                return new ClassInfo(file, str, classLoader);
            }
            return new ResourceInfo(file, str, classLoader);
        }

        public final ByteSource asByteSource() {
            return Resources.asByteSource(url());
        }

        public final CharSource asCharSource(Charset charset) {
            return Resources.asCharSource(url(), charset);
        }

        public boolean equals(Object obj) {
            if (obj instanceof ResourceInfo) {
                ResourceInfo resourceInfo = (ResourceInfo) obj;
                return this.f10726a.equals(resourceInfo.f10726a) && this.b == resourceInfo.b;
            }
            return false;
        }

        public final String getResourceName() {
            return this.f10726a;
        }

        public int hashCode() {
            return this.f10726a.hashCode();
        }

        public String toString() {
            return this.f10726a;
        }

        public final URL url() {
            URL resource = this.b.getResource(this.f10726a);
            if (resource != null) {
                return resource;
            }
            throw new NoSuchElementException(this.f10726a);
        }
    }

    /* loaded from: classes10.dex */
    public class a implements Predicate<ClassInfo> {
        public a(ClassPath classPath) {
        }

        @Override // com.google.common.base.Predicate
        /* renamed from: a */
        public boolean apply(ClassInfo classInfo) {
            return classInfo.isTopLevel();
        }
    }

    /* loaded from: classes10.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final File f10727a;
        public final ClassLoader b;

        public b(File file, ClassLoader classLoader) {
            this.f10727a = (File) Preconditions.checkNotNull(file);
            this.b = (ClassLoader) Preconditions.checkNotNull(classLoader);
        }

        public final File a() {
            return this.f10727a;
        }

        public final void b(File file, Set<File> set, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            try {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        c(file, builder);
                    } else {
                        e(file, set, builder);
                    }
                }
            } catch (SecurityException e) {
                Logger logger = ClassPath.b;
                String valueOf = String.valueOf(file);
                String valueOf2 = String.valueOf(e);
                StringBuilder sb = new StringBuilder(valueOf.length() + 16 + valueOf2.length());
                sb.append("Cannot access ");
                sb.append(valueOf);
                sb.append(": ");
                sb.append(valueOf2);
                logger.warning(sb.toString());
            }
        }

        public final void c(File file, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            HashSet hashSet = new HashSet();
            hashSet.add(file.getCanonicalFile());
            d(file, "", hashSet, builder);
        }

        public final void d(File file, String str, Set<File> set, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                Logger logger = ClassPath.b;
                String valueOf = String.valueOf(file);
                StringBuilder sb = new StringBuilder(valueOf.length() + 22);
                sb.append("Cannot read directory ");
                sb.append(valueOf);
                logger.warning(sb.toString());
                return;
            }
            for (File file2 : listFiles) {
                String name = file2.getName();
                if (file2.isDirectory()) {
                    File canonicalFile = file2.getCanonicalFile();
                    if (set.add(canonicalFile)) {
                        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(name).length());
                        sb2.append(str);
                        sb2.append(name);
                        sb2.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
                        d(canonicalFile, sb2.toString(), set, builder);
                        set.remove(canonicalFile);
                    }
                } else {
                    String valueOf2 = String.valueOf(str);
                    String valueOf3 = String.valueOf(name);
                    String concat = valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2);
                    if (!concat.equals("META-INF/MANIFEST.MF")) {
                        builder.add((ImmutableSet.Builder<ResourceInfo>) ResourceInfo.a(file2, concat, this.b));
                    }
                }
            }
        }

        public final void e(File file, Set<File> set, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    UnmodifiableIterator<File> it = ClassPath.f(file, jarFile.getManifest()).iterator();
                    while (it.hasNext()) {
                        File next = it.next();
                        if (set.add(next.getCanonicalFile())) {
                            b(next, set, builder);
                        }
                    }
                    f(jarFile, builder);
                } finally {
                    try {
                        jarFile.close();
                    } catch (IOException unused) {
                    }
                }
            } catch (IOException unused2) {
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                return this.f10727a.equals(bVar.f10727a) && this.b.equals(bVar.b);
            }
            return false;
        }

        public final void f(JarFile jarFile, ImmutableSet.Builder<ResourceInfo> builder) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry nextElement = entries.nextElement();
                if (!nextElement.isDirectory() && !nextElement.getName().equals("META-INF/MANIFEST.MF")) {
                    builder.add((ImmutableSet.Builder<ResourceInfo>) ResourceInfo.a(new File(jarFile.getName()), nextElement.getName(), this.b));
                }
            }
        }

        public ImmutableSet<ResourceInfo> g(Set<File> set) throws IOException {
            ImmutableSet.Builder<ResourceInfo> builder = ImmutableSet.builder();
            set.add(this.f10727a);
            b(this.f10727a, set, builder);
            return builder.build();
        }

        public int hashCode() {
            return this.f10727a.hashCode();
        }

        public String toString() {
            return this.f10727a.toString();
        }
    }

    public ClassPath(ImmutableSet<ResourceInfo> immutableSet) {
        this.f10725a = immutableSet;
    }

    public static ImmutableList<URL> b(ClassLoader classLoader) {
        if (classLoader instanceof URLClassLoader) {
            return ImmutableList.copyOf(((URLClassLoader) classLoader).getURLs());
        }
        if (classLoader.equals(ClassLoader.getSystemClassLoader())) {
            return h();
        }
        return ImmutableList.of();
    }

    @VisibleForTesting
    public static String c(String str) {
        return str.substring(0, str.length() - 6).replace('/', '.');
    }

    @VisibleForTesting
    public static ImmutableMap<File, ClassLoader> d(ClassLoader classLoader) {
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        ClassLoader parent = classLoader.getParent();
        if (parent != null) {
            newLinkedHashMap.putAll(d(parent));
        }
        UnmodifiableIterator<URL> it = b(classLoader).iterator();
        while (it.hasNext()) {
            URL next = it.next();
            if (next.getProtocol().equals("file")) {
                File i = i(next);
                if (!newLinkedHashMap.containsKey(i)) {
                    newLinkedHashMap.put(i, classLoader);
                }
            }
        }
        return ImmutableMap.copyOf((Map) newLinkedHashMap);
    }

    @VisibleForTesting
    public static URL e(File file, String str) throws MalformedURLException {
        return new URL(file.toURI().toURL(), str);
    }

    @VisibleForTesting
    public static ImmutableSet<File> f(File file, @NullableDecl Manifest manifest) {
        if (manifest == null) {
            return ImmutableSet.of();
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        String value = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
        if (value != null) {
            for (String str : c.split(value)) {
                try {
                    URL e = e(file, str);
                    if (e.getProtocol().equals("file")) {
                        builder.add((ImmutableSet.Builder) i(e));
                    }
                } catch (MalformedURLException unused) {
                    Logger logger = b;
                    String valueOf = String.valueOf(str);
                    logger.warning(valueOf.length() != 0 ? "Invalid Class-Path entry: ".concat(valueOf) : new String("Invalid Class-Path entry: "));
                }
            }
        }
        return builder.build();
    }

    public static ClassPath from(ClassLoader classLoader) throws IOException {
        ImmutableSet<b> g = g(classLoader);
        HashSet hashSet = new HashSet();
        UnmodifiableIterator<b> it = g.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().a());
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<b> it2 = g.iterator();
        while (it2.hasNext()) {
            builder.addAll((Iterable) it2.next().g(hashSet));
        }
        return new ClassPath(builder.build());
    }

    public static ImmutableSet<b> g(ClassLoader classLoader) {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<Map.Entry<File, ClassLoader>> it = d(classLoader).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<File, ClassLoader> next = it.next();
            builder.add((ImmutableSet.Builder) new b(next.getKey(), next.getValue()));
        }
        return builder.build();
    }

    @VisibleForTesting
    public static ImmutableList<URL> h() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (String str : Splitter.on(StandardSystemProperty.PATH_SEPARATOR.value()).split(StandardSystemProperty.JAVA_CLASS_PATH.value())) {
            try {
                try {
                    builder.add((ImmutableList.Builder) new File(str).toURI().toURL());
                } catch (SecurityException unused) {
                    builder.add((ImmutableList.Builder) new URL("file", (String) null, new File(str).getAbsolutePath()));
                }
            } catch (MalformedURLException e) {
                Logger logger = b;
                Level level = Level.WARNING;
                String valueOf = String.valueOf(str);
                logger.log(level, valueOf.length() != 0 ? "malformed classpath entry: ".concat(valueOf) : new String("malformed classpath entry: "), (Throwable) e);
            }
        }
        return builder.build();
    }

    @VisibleForTesting
    public static File i(URL url) {
        Preconditions.checkArgument(url.getProtocol().equals("file"));
        try {
            return new File(url.toURI());
        } catch (URISyntaxException unused) {
            return new File(url.getPath());
        }
    }

    public ImmutableSet<ClassInfo> getAllClasses() {
        return FluentIterable.from(this.f10725a).filter(ClassInfo.class).toSet();
    }

    public ImmutableSet<ResourceInfo> getResources() {
        return this.f10725a;
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses() {
        return FluentIterable.from(this.f10725a).filter(ClassInfo.class).filter(new a(this)).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClassesRecursive(String str) {
        Preconditions.checkNotNull(str);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1);
        sb.append(str);
        sb.append('.');
        String sb2 = sb.toString();
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo next = it.next();
            if (next.getName().startsWith(sb2)) {
                builder.add((ImmutableSet.Builder) next);
            }
        }
        return builder.build();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses(String str) {
        Preconditions.checkNotNull(str);
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo next = it.next();
            if (next.getPackageName().equals(str)) {
                builder.add((ImmutableSet.Builder) next);
            }
        }
        return builder.build();
    }
}
