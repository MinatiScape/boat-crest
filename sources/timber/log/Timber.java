package timber.log;

import android.os.Build;
import android.util.Log;
import com.mappls.sdk.navigation.NavigationConstants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes13.dex */
public final class Timber {

    /* renamed from: a  reason: collision with root package name */
    public static final Tree[] f15709a;
    public static volatile Tree[] c;
    public static final List<Tree> b = new ArrayList();
    public static final Tree d = new a();

    /* loaded from: classes13.dex */
    public static class DebugTree extends Tree {
        public static final Pattern b = Pattern.compile("(\\$\\d+)+$");

        @Override // timber.log.Timber.Tree
        public final String b() {
            String b2 = super.b();
            if (b2 != null) {
                return b2;
            }
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace.length > 5) {
                return createStackElementTag(stackTrace[5]);
            }
            throw new IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }

        @Nullable
        public String createStackElementTag(@NotNull StackTraceElement stackTraceElement) {
            String className = stackTraceElement.getClassName();
            Matcher matcher = b.matcher(className);
            if (matcher.find()) {
                className = matcher.replaceAll("");
            }
            String substring = className.substring(className.lastIndexOf(46) + 1);
            return (substring.length() <= 23 || Build.VERSION.SDK_INT >= 24) ? substring : substring.substring(0, 23);
        }

        @Override // timber.log.Timber.Tree
        public void log(int i, String str, @NotNull String str2, Throwable th) {
            int min;
            if (str2.length() < 4000) {
                if (i == 7) {
                    Log.wtf(str, str2);
                    return;
                } else {
                    Log.println(i, str, str2);
                    return;
                }
            }
            int i2 = 0;
            int length = str2.length();
            while (i2 < length) {
                int indexOf = str2.indexOf(10, i2);
                if (indexOf == -1) {
                    indexOf = length;
                }
                while (true) {
                    min = Math.min(indexOf, i2 + NavigationConstants.UI_HANDLER_MAP_CONTROLS);
                    String substring = str2.substring(i2, min);
                    if (i == 7) {
                        Log.wtf(str, substring);
                    } else {
                        Log.println(i, str, substring);
                    }
                    if (min >= indexOf) {
                        break;
                    }
                    i2 = min;
                }
                i2 = min + 1;
            }
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Tree {

        /* renamed from: a  reason: collision with root package name */
        public final ThreadLocal<String> f15710a = new ThreadLocal<>();

        public final String a(Throwable th) {
            StringWriter stringWriter = new StringWriter(256);
            PrintWriter printWriter = new PrintWriter((Writer) stringWriter, false);
            th.printStackTrace(printWriter);
            printWriter.flush();
            return stringWriter.toString();
        }

        @Nullable
        public String b() {
            String str = this.f15710a.get();
            if (str != null) {
                this.f15710a.remove();
            }
            return str;
        }

        public final void c(int i, Throwable th, String str, Object... objArr) {
            String b = b();
            if (isLoggable(b, i)) {
                if (str != null && str.length() == 0) {
                    str = null;
                }
                if (str != null) {
                    if (objArr != null && objArr.length > 0) {
                        str = formatMessage(str, objArr);
                    }
                    if (th != null) {
                        str = str + "\n" + a(th);
                    }
                } else if (th == null) {
                    return;
                } else {
                    str = a(th);
                }
                log(i, b, str, th);
            }
        }

        public void d(String str, Object... objArr) {
            c(3, null, str, objArr);
        }

        public void e(String str, Object... objArr) {
            c(6, null, str, objArr);
        }

        public String formatMessage(@NotNull String str, @NotNull Object[] objArr) {
            return String.format(str, objArr);
        }

        public void i(String str, Object... objArr) {
            c(4, null, str, objArr);
        }

        @Deprecated
        public boolean isLoggable(int i) {
            return true;
        }

        public boolean isLoggable(@Nullable String str, int i) {
            return isLoggable(i);
        }

        public abstract void log(int i, @Nullable String str, @NotNull String str2, @Nullable Throwable th);

        public void log(int i, String str, Object... objArr) {
            c(i, null, str, objArr);
        }

        public void v(String str, Object... objArr) {
            c(2, null, str, objArr);
        }

        public void w(String str, Object... objArr) {
            c(5, null, str, objArr);
        }

        public void wtf(String str, Object... objArr) {
            c(7, null, str, objArr);
        }

        public void d(Throwable th, String str, Object... objArr) {
            c(3, th, str, objArr);
        }

        public void e(Throwable th, String str, Object... objArr) {
            c(6, th, str, objArr);
        }

        public void i(Throwable th, String str, Object... objArr) {
            c(4, th, str, objArr);
        }

        public void log(int i, Throwable th, String str, Object... objArr) {
            c(i, th, str, objArr);
        }

        public void v(Throwable th, String str, Object... objArr) {
            c(2, th, str, objArr);
        }

        public void w(Throwable th, String str, Object... objArr) {
            c(5, th, str, objArr);
        }

        public void wtf(Throwable th, String str, Object... objArr) {
            c(7, th, str, objArr);
        }

        public void d(Throwable th) {
            c(3, th, null, new Object[0]);
        }

        public void e(Throwable th) {
            c(6, th, null, new Object[0]);
        }

        public void i(Throwable th) {
            c(4, th, null, new Object[0]);
        }

        public void log(int i, Throwable th) {
            c(i, th, null, new Object[0]);
        }

        public void v(Throwable th) {
            c(2, th, null, new Object[0]);
        }

        public void w(Throwable th) {
            c(5, th, null, new Object[0]);
        }

        public void wtf(Throwable th) {
            c(7, th, null, new Object[0]);
        }
    }

    static {
        Tree[] treeArr = new Tree[0];
        f15709a = treeArr;
        c = treeArr;
    }

    public Timber() {
        throw new AssertionError("No instances.");
    }

    @NotNull
    public static Tree asTree() {
        return d;
    }

    public static void d(@NonNls String str, Object... objArr) {
        d.d(str, objArr);
    }

    public static void e(@NonNls String str, Object... objArr) {
        d.e(str, objArr);
    }

    @NotNull
    public static List<Tree> forest() {
        List<Tree> unmodifiableList;
        List<Tree> list = b;
        synchronized (list) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        }
        return unmodifiableList;
    }

    public static void i(@NonNls String str, Object... objArr) {
        d.i(str, objArr);
    }

    public static void log(int i, @NonNls String str, Object... objArr) {
        d.log(i, str, objArr);
    }

    public static void plant(@NotNull Tree tree) {
        Objects.requireNonNull(tree, "tree == null");
        if (tree != d) {
            List<Tree> list = b;
            synchronized (list) {
                list.add(tree);
                c = (Tree[]) list.toArray(new Tree[list.size()]);
            }
            return;
        }
        throw new IllegalArgumentException("Cannot plant Timber into itself.");
    }

    @NotNull
    public static Tree tag(String str) {
        for (Tree tree : c) {
            tree.f15710a.set(str);
        }
        return d;
    }

    public static int treeCount() {
        int size;
        List<Tree> list = b;
        synchronized (list) {
            size = list.size();
        }
        return size;
    }

    public static void uproot(@NotNull Tree tree) {
        List<Tree> list = b;
        synchronized (list) {
            if (list.remove(tree)) {
                c = (Tree[]) list.toArray(new Tree[list.size()]);
            } else {
                throw new IllegalArgumentException("Cannot uproot tree which is not planted: " + tree);
            }
        }
    }

    public static void uprootAll() {
        List<Tree> list = b;
        synchronized (list) {
            list.clear();
            c = f15709a;
        }
    }

    public static void v(@NonNls String str, Object... objArr) {
        d.v(str, objArr);
    }

    public static void w(@NonNls String str, Object... objArr) {
        d.w(str, objArr);
    }

    public static void wtf(@NonNls String str, Object... objArr) {
        d.wtf(str, objArr);
    }

    public static void d(Throwable th, @NonNls String str, Object... objArr) {
        d.d(th, str, objArr);
    }

    public static void e(Throwable th, @NonNls String str, Object... objArr) {
        d.e(th, str, objArr);
    }

    public static void i(Throwable th, @NonNls String str, Object... objArr) {
        d.i(th, str, objArr);
    }

    public static void log(int i, Throwable th, @NonNls String str, Object... objArr) {
        d.log(i, th, str, objArr);
    }

    public static void v(Throwable th, @NonNls String str, Object... objArr) {
        d.v(th, str, objArr);
    }

    public static void w(Throwable th, @NonNls String str, Object... objArr) {
        d.w(th, str, objArr);
    }

    public static void wtf(Throwable th, @NonNls String str, Object... objArr) {
        d.wtf(th, str, objArr);
    }

    /* loaded from: classes13.dex */
    public static class a extends Tree {
        @Override // timber.log.Timber.Tree
        public void d(String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.d(str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void e(String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.e(str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void i(String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.i(str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void log(int i, String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.log(i, str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void v(String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.v(str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void w(String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.w(str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void wtf(String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.wtf(str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void d(Throwable th, String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.d(th, str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void e(Throwable th, String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.e(th, str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void i(Throwable th, String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.i(th, str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void log(int i, Throwable th, String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.log(i, th, str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void v(Throwable th, String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.v(th, str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void w(Throwable th, String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.w(th, str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void wtf(Throwable th, String str, Object... objArr) {
            for (Tree tree : Timber.c) {
                tree.wtf(th, str, objArr);
            }
        }

        @Override // timber.log.Timber.Tree
        public void d(Throwable th) {
            for (Tree tree : Timber.c) {
                tree.d(th);
            }
        }

        @Override // timber.log.Timber.Tree
        public void e(Throwable th) {
            for (Tree tree : Timber.c) {
                tree.e(th);
            }
        }

        @Override // timber.log.Timber.Tree
        public void i(Throwable th) {
            for (Tree tree : Timber.c) {
                tree.i(th);
            }
        }

        @Override // timber.log.Timber.Tree
        public void log(int i, Throwable th) {
            for (Tree tree : Timber.c) {
                tree.log(i, th);
            }
        }

        @Override // timber.log.Timber.Tree
        public void v(Throwable th) {
            for (Tree tree : Timber.c) {
                tree.v(th);
            }
        }

        @Override // timber.log.Timber.Tree
        public void w(Throwable th) {
            for (Tree tree : Timber.c) {
                tree.w(th);
            }
        }

        @Override // timber.log.Timber.Tree
        public void wtf(Throwable th) {
            for (Tree tree : Timber.c) {
                tree.wtf(th);
            }
        }

        @Override // timber.log.Timber.Tree
        public void log(int i, String str, @NotNull String str2, Throwable th) {
            throw new AssertionError("Missing override for log method.");
        }
    }

    public static void d(Throwable th) {
        d.d(th);
    }

    public static void e(Throwable th) {
        d.e(th);
    }

    public static void i(Throwable th) {
        d.i(th);
    }

    public static void log(int i, Throwable th) {
        d.log(i, th);
    }

    public static void v(Throwable th) {
        d.v(th);
    }

    public static void w(Throwable th) {
        d.w(th);
    }

    public static void wtf(Throwable th) {
        d.wtf(th);
    }

    public static void plant(@NotNull Tree... treeArr) {
        Objects.requireNonNull(treeArr, "trees == null");
        for (Tree tree : treeArr) {
            Objects.requireNonNull(tree, "trees contains null");
            if (tree == d) {
                throw new IllegalArgumentException("Cannot plant Timber into itself.");
            }
        }
        List<Tree> list = b;
        synchronized (list) {
            Collections.addAll(list, treeArr);
            c = (Tree[]) list.toArray(new Tree[list.size()]);
        }
    }
}
