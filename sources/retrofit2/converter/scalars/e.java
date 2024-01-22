package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class e implements Converter<ResponseBody, Double> {

    /* renamed from: a  reason: collision with root package name */
    public static final e f15611a = new e();

    @Override // retrofit2.Converter
    /* renamed from: a */
    public Double convert(ResponseBody responseBody) throws IOException {
        return Double.valueOf(responseBody.string());
    }
}
