package retrofit2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
/* loaded from: classes13.dex */
public abstract class m<T> {
    public static <T> m<T> b(Retrofit retrofit, Method method) {
        l b = l.b(retrofit, method);
        Type genericReturnType = method.getGenericReturnType();
        if (!o.j(genericReturnType)) {
            if (genericReturnType != Void.TYPE) {
                return f.f(retrofit, method, b);
            }
            throw o.m(method, "Service methods cannot return void.", new Object[0]);
        }
        throw o.m(method, "Method return type must not include a type variable or wildcard: %s", genericReturnType);
    }

    @Nullable
    public abstract T a(Object[] objArr);
}
