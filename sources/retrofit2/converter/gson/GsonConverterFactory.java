package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Objects;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
/* loaded from: classes13.dex */
public final class GsonConverterFactory extends Converter.Factory {

    /* renamed from: a  reason: collision with root package name */
    public final Gson f15604a;

    public GsonConverterFactory(Gson gson) {
        this.f15604a = gson;
    }

    public static GsonConverterFactory create() {
        return create(new Gson());
    }

    @Override // retrofit2.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        return new a(this.f15604a, this.f15604a.getAdapter(TypeToken.get(type)));
    }

    @Override // retrofit2.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return new b(this.f15604a, this.f15604a.getAdapter(TypeToken.get(type)));
    }

    public static GsonConverterFactory create(Gson gson) {
        Objects.requireNonNull(gson, "gson == null");
        return new GsonConverterFactory(gson);
    }
}
