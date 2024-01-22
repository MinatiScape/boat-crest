package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import javax.annotation.Nullable;
import okhttp3.ResponseBody;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import retrofit2.Converter;
@IgnoreJRERequirement
/* loaded from: classes13.dex */
public final class h extends Converter.Factory {

    /* renamed from: a  reason: collision with root package name */
    public static final Converter.Factory f15619a = new h();

    @IgnoreJRERequirement
    /* loaded from: classes13.dex */
    public static final class a<T> implements Converter<ResponseBody, Optional<T>> {

        /* renamed from: a  reason: collision with root package name */
        public final Converter<ResponseBody, T> f15620a;

        public a(Converter<ResponseBody, T> converter) {
            this.f15620a = converter;
        }

        @Override // retrofit2.Converter
        /* renamed from: a */
        public Optional<T> convert(ResponseBody responseBody) throws IOException {
            return Optional.ofNullable(this.f15620a.convert(responseBody));
        }
    }

    @Override // retrofit2.Converter.Factory
    @Nullable
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (Converter.Factory.getRawType(type) != Optional.class) {
            return null;
        }
        return new a(retrofit.responseBodyConverter(Converter.Factory.getParameterUpperBound(0, (ParameterizedType) type), annotationArr));
    }
}
