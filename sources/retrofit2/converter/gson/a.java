package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class a<T> implements Converter<T, RequestBody> {
    public static final MediaType c = MediaType.get("application/json; charset=UTF-8");
    public static final Charset d = Charset.forName("UTF-8");

    /* renamed from: a  reason: collision with root package name */
    public final Gson f15605a;
    public final TypeAdapter<T> b;

    public a(Gson gson, TypeAdapter<T> typeAdapter) {
        this.f15605a = gson;
        this.b = typeAdapter;
    }

    @Override // retrofit2.Converter
    /* renamed from: a */
    public RequestBody convert(T t) throws IOException {
        Buffer buffer = new Buffer();
        JsonWriter newJsonWriter = this.f15605a.newJsonWriter(new OutputStreamWriter(buffer.outputStream(), d));
        this.b.write(newJsonWriter, t);
        newJsonWriter.close();
        return RequestBody.create(c, buffer.readByteString());
    }
}
