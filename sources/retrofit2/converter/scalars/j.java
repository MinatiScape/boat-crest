package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class j implements Converter<ResponseBody, String> {

    /* renamed from: a  reason: collision with root package name */
    public static final j f15616a = new j();

    @Override // retrofit2.Converter
    /* renamed from: a */
    public String convert(ResponseBody responseBody) throws IOException {
        return responseBody.string();
    }
}
