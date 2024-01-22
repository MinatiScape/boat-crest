package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class b implements Converter<ResponseBody, Boolean> {

    /* renamed from: a  reason: collision with root package name */
    public static final b f15608a = new b();

    @Override // retrofit2.Converter
    /* renamed from: a */
    public Boolean convert(ResponseBody responseBody) throws IOException {
        return Boolean.valueOf(responseBody.string());
    }
}
