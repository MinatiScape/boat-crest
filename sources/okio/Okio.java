package okio;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.OpenOption;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class Okio {
    @NotNull
    public static final Sink appendingSink(@NotNull File file) throws FileNotFoundException {
        return c.b(file);
    }

    @NotNull
    public static final FileSystem asResourceFileSystem(@NotNull ClassLoader classLoader) {
        return c.c(classLoader);
    }

    @JvmName(name = "blackhole")
    @NotNull
    public static final Sink blackhole() {
        return d.a();
    }

    @NotNull
    public static final BufferedSink buffer(@NotNull Sink sink) {
        return d.b(sink);
    }

    @NotNull
    public static final CipherSink cipherSink(@NotNull Sink sink, @NotNull Cipher cipher) {
        return c.d(sink, cipher);
    }

    @NotNull
    public static final CipherSource cipherSource(@NotNull Source source, @NotNull Cipher cipher) {
        return c.e(source, cipher);
    }

    @NotNull
    public static final HashingSink hashingSink(@NotNull Sink sink, @NotNull MessageDigest messageDigest) {
        return c.f(sink, messageDigest);
    }

    @NotNull
    public static final HashingSource hashingSource(@NotNull Source source, @NotNull MessageDigest messageDigest) {
        return c.h(source, messageDigest);
    }

    public static final boolean isAndroidGetsocknameError(@NotNull AssertionError assertionError) {
        return c.j(assertionError);
    }

    @NotNull
    public static final FileSystem openZip(@NotNull FileSystem fileSystem, @NotNull Path path) throws IOException {
        return c.k(fileSystem, path);
    }

    @JvmOverloads
    @NotNull
    public static final Sink sink(@NotNull File file) throws FileNotFoundException {
        return c.l(file);
    }

    @NotNull
    public static final Source source(@NotNull File file) throws FileNotFoundException {
        return c.r(file);
    }

    public static final <T extends Closeable, R> R use(T t, @NotNull Function1<? super T, ? extends R> function1) {
        return (R) d.d(t, function1);
    }

    @NotNull
    public static final BufferedSource buffer(@NotNull Source source) {
        return d.c(source);
    }

    @NotNull
    public static final HashingSink hashingSink(@NotNull Sink sink, @NotNull Mac mac) {
        return c.g(sink, mac);
    }

    @NotNull
    public static final HashingSource hashingSource(@NotNull Source source, @NotNull Mac mac) {
        return c.i(source, mac);
    }

    @JvmOverloads
    @NotNull
    public static final Sink sink(@NotNull File file, boolean z) throws FileNotFoundException {
        return c.m(file, z);
    }

    @NotNull
    public static final Source source(@NotNull InputStream inputStream) {
        return c.s(inputStream);
    }

    @NotNull
    public static final Sink sink(@NotNull OutputStream outputStream) {
        return c.n(outputStream);
    }

    @NotNull
    public static final Source source(@NotNull Socket socket) throws IOException {
        return c.t(socket);
    }

    @NotNull
    public static final Sink sink(@NotNull Socket socket) throws IOException {
        return c.o(socket);
    }

    @IgnoreJRERequirement
    @NotNull
    public static final Source source(@NotNull java.nio.file.Path path, @NotNull OpenOption... openOptionArr) throws IOException {
        return c.u(path, openOptionArr);
    }

    @IgnoreJRERequirement
    @NotNull
    public static final Sink sink(@NotNull java.nio.file.Path path, @NotNull OpenOption... openOptionArr) throws IOException {
        return c.p(path, openOptionArr);
    }
}
