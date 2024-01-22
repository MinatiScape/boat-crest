package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class a<T> implements Converter<T, RequestBody> {

    /* renamed from: a  reason: collision with root package name */
    public static final a<Object> f15607a = new a<>();
    public static final MediaType b = MediaType.get("text/plain; charset=UTF-8");

    @Override // retrofit2.Converter
    /* renamed from: a */
    public RequestBody convert(T t) throws IOException {
        return RequestBody.create(b, String.valueOf(t));
    }
}
