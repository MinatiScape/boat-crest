package retrofit2.converter.scalars;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
/* loaded from: classes13.dex */
public final class ScalarsConverterFactory extends Converter.Factory {
    public static ScalarsConverterFactory create() {
        return new ScalarsConverterFactory();
    }

    @Override // retrofit2.Converter.Factory
    @Nullable
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        if (type == String.class || type == Boolean.TYPE || type == Boolean.class || type == Byte.TYPE || type == Byte.class || type == Character.TYPE || type == Character.class || type == Double.TYPE || type == Double.class || type == Float.TYPE || type == Float.class || type == Integer.TYPE || type == Integer.class || type == Long.TYPE || type == Long.class || type == Short.TYPE || type == Short.class) {
            return a.f15607a;
        }
        return null;
    }

    @Override // retrofit2.Converter.Factory
    @Nullable
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (type == String.class) {
            return j.f15616a;
        }
        if (type != Boolean.class && type != Boolean.TYPE) {
            if (type != Byte.class && type != Byte.TYPE) {
                if (type != Character.class && type != Character.TYPE) {
                    if (type != Double.class && type != Double.TYPE) {
                        if (type != Float.class && type != Float.TYPE) {
                            if (type != Integer.class && type != Integer.TYPE) {
                                if (type != Long.class && type != Long.TYPE) {
                                    if (type == Short.class || type == Short.TYPE) {
                                        return i.f15615a;
                                    }
                                    return null;
                                }
                                return h.f15614a;
                            }
                            return g.f15613a;
                        }
                        return f.f15612a;
                    }
                    return e.f15611a;
                }
                return d.f15610a;
            }
            return c.f15609a;
        }
        return b.f15608a;
    }
}
