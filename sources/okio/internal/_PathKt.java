package okio.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.f;
import kotlin.collections.i;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.Path;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class _PathKt {
    @NotNull

    /* renamed from: a */
    public static final ByteString f14327a;
    @NotNull
    public static final ByteString b;
    @NotNull
    public static final ByteString c;
    @NotNull
    public static final ByteString d;
    @NotNull
    public static final ByteString e;

    static {
        ByteString.Companion companion = ByteString.Companion;
        f14327a = companion.encodeUtf8(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        b = companion.encodeUtf8("\\");
        c = companion.encodeUtf8("/\\");
        d = companion.encodeUtf8(".");
        e = companion.encodeUtf8("..");
    }

    public static final int a(Path path) {
        int lastIndexOf$default = ByteString.lastIndexOf$default(path.getBytes$okio(), f14327a, 0, 2, (Object) null);
        return lastIndexOf$default != -1 ? lastIndexOf$default : ByteString.lastIndexOf$default(path.getBytes$okio(), b, 0, 2, (Object) null);
    }

    public static final ByteString b(Path path) {
        ByteString bytes$okio = path.getBytes$okio();
        ByteString byteString = f14327a;
        if (ByteString.indexOf$default(bytes$okio, byteString, 0, 2, (Object) null) != -1) {
            return byteString;
        }
        ByteString bytes$okio2 = path.getBytes$okio();
        ByteString byteString2 = b;
        if (ByteString.indexOf$default(bytes$okio2, byteString2, 0, 2, (Object) null) != -1) {
            return byteString2;
        }
        return null;
    }

    public static final boolean c(Path path) {
        return path.getBytes$okio().endsWith(e) && (path.getBytes$okio().size() == 2 || path.getBytes$okio().rangeEquals(path.getBytes$okio().size() + (-3), f14327a, 0, 1) || path.getBytes$okio().rangeEquals(path.getBytes$okio().size() + (-3), b, 0, 1));
    }

    public static final int commonCompareTo(@NotNull Path path, @NotNull Path other) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return path.getBytes$okio().compareTo(other.getBytes$okio());
    }

    public static final boolean commonEquals(@NotNull Path path, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return (obj instanceof Path) && Intrinsics.areEqual(((Path) obj).getBytes$okio(), path.getBytes$okio());
    }

    public static final int commonHashCode(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return path.getBytes$okio().hashCode();
    }

    public static final boolean commonIsAbsolute(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return d(path) != -1;
    }

    public static final boolean commonIsRelative(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return d(path) == -1;
    }

    public static final boolean commonIsRoot(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return d(path) == path.getBytes$okio().size();
    }

    @NotNull
    public static final String commonName(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return path.nameBytes().utf8();
    }

    @NotNull
    public static final ByteString commonNameBytes(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        int a2 = a(path);
        if (a2 != -1) {
            return ByteString.substring$default(path.getBytes$okio(), a2 + 1, 0, 2, null);
        }
        return (path.volumeLetter() == null || path.getBytes$okio().size() != 2) ? path.getBytes$okio() : ByteString.EMPTY;
    }

    @NotNull
    public static final Path commonNormalized(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Path.Companion.get(path.toString(), true);
    }

    @Nullable
    public static final Path commonParent(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        if (Intrinsics.areEqual(path.getBytes$okio(), d) || Intrinsics.areEqual(path.getBytes$okio(), f14327a) || Intrinsics.areEqual(path.getBytes$okio(), b) || c(path)) {
            return null;
        }
        int a2 = a(path);
        if (a2 != 2 || path.volumeLetter() == null) {
            if (a2 == 1 && path.getBytes$okio().startsWith(b)) {
                return null;
            }
            if (a2 == -1 && path.volumeLetter() != null) {
                if (path.getBytes$okio().size() == 2) {
                    return null;
                }
                return new Path(ByteString.substring$default(path.getBytes$okio(), 0, 2, 1, null));
            } else if (a2 == -1) {
                return new Path(d);
            } else {
                if (a2 == 0) {
                    return new Path(ByteString.substring$default(path.getBytes$okio(), 0, 1, 1, null));
                }
                return new Path(ByteString.substring$default(path.getBytes$okio(), 0, a2, 1, null));
            }
        } else if (path.getBytes$okio().size() == 3) {
            return null;
        } else {
            return new Path(ByteString.substring$default(path.getBytes$okio(), 0, 3, 1, null));
        }
    }

    @NotNull
    public static final Path commonRelativeTo(@NotNull Path path, @NotNull Path other) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        if (Intrinsics.areEqual(path.getRoot(), other.getRoot())) {
            List<ByteString> segmentsBytes = path.getSegmentsBytes();
            List<ByteString> segmentsBytes2 = other.getSegmentsBytes();
            int min = Math.min(segmentsBytes.size(), segmentsBytes2.size());
            int i = 0;
            while (i < min && Intrinsics.areEqual(segmentsBytes.get(i), segmentsBytes2.get(i))) {
                i++;
            }
            if (i != min || path.getBytes$okio().size() != other.getBytes$okio().size()) {
                if (segmentsBytes2.subList(i, segmentsBytes2.size()).indexOf(e) == -1) {
                    Buffer buffer = new Buffer();
                    ByteString b2 = b(other);
                    if (b2 == null && (b2 = b(path)) == null) {
                        b2 = g(Path.DIRECTORY_SEPARATOR);
                    }
                    int size = segmentsBytes2.size();
                    for (int i2 = i; i2 < size; i2++) {
                        buffer.write(e);
                        buffer.write(b2);
                    }
                    int size2 = segmentsBytes.size();
                    while (i < size2) {
                        buffer.write(segmentsBytes.get(i));
                        buffer.write(b2);
                        i++;
                    }
                    return toPath(buffer, false);
                }
                throw new IllegalArgumentException(("Impossible relative path to resolve: " + path + " and " + other).toString());
            }
            return Path.Companion.get$default(Path.Companion, ".", false, 1, (Object) null);
        }
        throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + path + " and " + other).toString());
    }

    @NotNull
    public static final Path commonResolve(@NotNull Path path, @NotNull String child, boolean z) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(child, "child");
        return commonResolve(path, toPath(new Buffer().writeUtf8(child), false), z);
    }

    @Nullable
    public static final Path commonRoot(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        int d2 = d(path);
        if (d2 == -1) {
            return null;
        }
        return new Path(path.getBytes$okio().substring(0, d2));
    }

    @NotNull
    public static final List<String> commonSegments(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        ArrayList<ByteString> arrayList = new ArrayList();
        int d2 = d(path);
        if (d2 == -1) {
            d2 = 0;
        } else if (d2 < path.getBytes$okio().size() && path.getBytes$okio().getByte(d2) == ((byte) 92)) {
            d2++;
        }
        int size = path.getBytes$okio().size();
        int i = d2;
        while (d2 < size) {
            if (path.getBytes$okio().getByte(d2) == ((byte) 47) || path.getBytes$okio().getByte(d2) == ((byte) 92)) {
                arrayList.add(path.getBytes$okio().substring(i, d2));
                i = d2 + 1;
            }
            d2++;
        }
        if (i < path.getBytes$okio().size()) {
            arrayList.add(path.getBytes$okio().substring(i, path.getBytes$okio().size()));
        }
        ArrayList arrayList2 = new ArrayList(f.collectionSizeOrDefault(arrayList, 10));
        for (ByteString byteString : arrayList) {
            arrayList2.add(byteString.utf8());
        }
        return arrayList2;
    }

    @NotNull
    public static final List<ByteString> commonSegmentsBytes(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        ArrayList arrayList = new ArrayList();
        int d2 = d(path);
        if (d2 == -1) {
            d2 = 0;
        } else if (d2 < path.getBytes$okio().size() && path.getBytes$okio().getByte(d2) == ((byte) 92)) {
            d2++;
        }
        int size = path.getBytes$okio().size();
        int i = d2;
        while (d2 < size) {
            if (path.getBytes$okio().getByte(d2) == ((byte) 47) || path.getBytes$okio().getByte(d2) == ((byte) 92)) {
                arrayList.add(path.getBytes$okio().substring(i, d2));
                i = d2 + 1;
            }
            d2++;
        }
        if (i < path.getBytes$okio().size()) {
            arrayList.add(path.getBytes$okio().substring(i, path.getBytes$okio().size()));
        }
        return arrayList;
    }

    @NotNull
    public static final Path commonToPath(@NotNull String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toPath(new Buffer().writeUtf8(str), z);
    }

    @NotNull
    public static final String commonToString(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return path.getBytes$okio().utf8();
    }

    @Nullable
    public static final Character commonVolumeLetter(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        boolean z = false;
        if (ByteString.indexOf$default(path.getBytes$okio(), f14327a, 0, 2, (Object) null) == -1 && path.getBytes$okio().size() >= 2 && path.getBytes$okio().getByte(1) == ((byte) 58)) {
            char c2 = (char) path.getBytes$okio().getByte(0);
            if (!('a' <= c2 && c2 < '{')) {
                if ('A' <= c2 && c2 < '[') {
                    z = true;
                }
                if (!z) {
                    return null;
                }
            }
            return Character.valueOf(c2);
        }
        return null;
    }

    public static final int d(Path path) {
        if (path.getBytes$okio().size() == 0) {
            return -1;
        }
        boolean z = false;
        if (path.getBytes$okio().getByte(0) == ((byte) 47)) {
            return 1;
        }
        byte b2 = (byte) 92;
        if (path.getBytes$okio().getByte(0) == b2) {
            if (path.getBytes$okio().size() <= 2 || path.getBytes$okio().getByte(1) != b2) {
                return 1;
            }
            int indexOf = path.getBytes$okio().indexOf(b, 2);
            return indexOf == -1 ? path.getBytes$okio().size() : indexOf;
        } else if (path.getBytes$okio().size() > 2 && path.getBytes$okio().getByte(1) == ((byte) 58) && path.getBytes$okio().getByte(2) == b2) {
            char c2 = (char) path.getBytes$okio().getByte(0);
            if ('a' <= c2 && c2 < '{') {
                return 3;
            }
            if ('A' <= c2 && c2 < '[') {
                z = true;
            }
            return !z ? -1 : 3;
        } else {
            return -1;
        }
    }

    public static final boolean e(Buffer buffer, ByteString byteString) {
        if (Intrinsics.areEqual(byteString, b) && buffer.size() >= 2 && buffer.getByte(1L) == ((byte) 58)) {
            char c2 = (char) buffer.getByte(0L);
            if (!('a' <= c2 && c2 < '{')) {
                if (!('A' <= c2 && c2 < '[')) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static final ByteString f(byte b2) {
        if (b2 == 47) {
            return f14327a;
        }
        if (b2 == 92) {
            return b;
        }
        throw new IllegalArgumentException("not a directory separator: " + ((int) b2));
    }

    public static final ByteString g(String str) {
        if (Intrinsics.areEqual(str, MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            return f14327a;
        }
        if (Intrinsics.areEqual(str, "\\")) {
            return b;
        }
        throw new IllegalArgumentException("not a directory separator: " + str);
    }

    @NotNull
    public static final Path toPath(@NotNull Buffer buffer, boolean z) {
        ByteString byteString;
        ByteString readByteString;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Buffer buffer2 = new Buffer();
        ByteString byteString2 = null;
        int i = 0;
        while (true) {
            if (!buffer.rangeEquals(0L, f14327a)) {
                byteString = b;
                if (!buffer.rangeEquals(0L, byteString)) {
                    break;
                }
            }
            byte readByte = buffer.readByte();
            if (byteString2 == null) {
                byteString2 = f(readByte);
            }
            i++;
        }
        boolean z2 = i >= 2 && Intrinsics.areEqual(byteString2, byteString);
        if (z2) {
            Intrinsics.checkNotNull(byteString2);
            buffer2.write(byteString2);
            buffer2.write(byteString2);
        } else if (i > 0) {
            Intrinsics.checkNotNull(byteString2);
            buffer2.write(byteString2);
        } else {
            long indexOfElement = buffer.indexOfElement(c);
            if (byteString2 == null) {
                if (indexOfElement == -1) {
                    byteString2 = g(Path.DIRECTORY_SEPARATOR);
                } else {
                    byteString2 = f(buffer.getByte(indexOfElement));
                }
            }
            if (e(buffer, byteString2)) {
                if (indexOfElement == 2) {
                    buffer2.write(buffer, 3L);
                } else {
                    buffer2.write(buffer, 2L);
                }
            }
        }
        boolean z3 = buffer2.size() > 0;
        ArrayList arrayList = new ArrayList();
        while (!buffer.exhausted()) {
            long indexOfElement2 = buffer.indexOfElement(c);
            if (indexOfElement2 == -1) {
                readByteString = buffer.readByteString();
            } else {
                readByteString = buffer.readByteString(indexOfElement2);
                buffer.readByte();
            }
            ByteString byteString3 = e;
            if (Intrinsics.areEqual(readByteString, byteString3)) {
                if (!z3 || !arrayList.isEmpty()) {
                    if (z && (z3 || (!arrayList.isEmpty() && !Intrinsics.areEqual(CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList), byteString3)))) {
                        if (!z2 || arrayList.size() != 1) {
                            i.removeLastOrNull(arrayList);
                        }
                    } else {
                        arrayList.add(readByteString);
                    }
                }
            } else if (!Intrinsics.areEqual(readByteString, d) && !Intrinsics.areEqual(readByteString, ByteString.EMPTY)) {
                arrayList.add(readByteString);
            }
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                buffer2.write(byteString2);
            }
            buffer2.write((ByteString) arrayList.get(i2));
        }
        if (buffer2.size() == 0) {
            buffer2.write(d);
        }
        return new Path(buffer2.readByteString());
    }

    @NotNull
    public static final Path commonResolve(@NotNull Path path, @NotNull ByteString child, boolean z) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(child, "child");
        return commonResolve(path, toPath(new Buffer().write(child), false), z);
    }

    @NotNull
    public static final Path commonResolve(@NotNull Path path, @NotNull Buffer child, boolean z) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(child, "child");
        return commonResolve(path, toPath(child, false), z);
    }

    @NotNull
    public static final Path commonResolve(@NotNull Path path, @NotNull Path child, boolean z) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(child, "child");
        if (child.isAbsolute() || child.volumeLetter() != null) {
            return child;
        }
        ByteString b2 = b(path);
        if (b2 == null && (b2 = b(child)) == null) {
            b2 = g(Path.DIRECTORY_SEPARATOR);
        }
        Buffer buffer = new Buffer();
        buffer.write(path.getBytes$okio());
        if (buffer.size() > 0) {
            buffer.write(b2);
        }
        buffer.write(child.getBytes$okio());
        return toPath(buffer, z);
    }
}
