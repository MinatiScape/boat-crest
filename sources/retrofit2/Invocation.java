package retrofit2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes13.dex */
public final class Invocation {

    /* renamed from: a  reason: collision with root package name */
    public final Method f15578a;
    public final List<?> b;

    public Invocation(Method method, List<?> list) {
        this.f15578a = method;
        this.b = Collections.unmodifiableList(list);
    }

    public static Invocation of(Method method, List<?> list) {
        Objects.requireNonNull(method, "method == null");
        Objects.requireNonNull(list, "arguments == null");
        return new Invocation(method, new ArrayList(list));
    }

    public List<?> arguments() {
        return this.b;
    }

    public Method method() {
        return this.f15578a;
    }

    public String toString() {
        return String.format("%s.%s() %s", this.f15578a.getDeclaringClass().getName(), this.f15578a.getName(), this.b);
    }
}
