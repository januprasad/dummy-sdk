package in.hatio.dummysdk;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by jenuprasad on 20/06/17.
 */

public class ApiController {

    private ApiService apiService;
    private DeviceParams deviceParams;
    public static String ENDPOINT = "http://192.168.1.1:19080";
    public static String BASEURL = ENDPOINT + "/api/v1/";

    public static ApiController getInstance(DeviceParams d) {
        return new ApiController(d);
    }

    private ApiController(DeviceParams d) {
        deviceParams = d;
    }


    public ApiService getApiService(String authHeader) {
        apiService = buildApiService(authHeader);
        return apiService;
    }

    private ApiService buildApiService(final String authHeader) {
        if (deviceParams == null)
            return null;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = null;
        if (deviceParams.getClient() != null && authHeader != null) {
            httpClient = new OkHttpClient.Builder().
                    addNetworkInterceptor(getAuthHeaderInterceptor(authHeader)).
                    addInterceptor(logging).
                    readTimeout(1000, TimeUnit.SECONDS).
                    connectTimeout(1000, TimeUnit.SECONDS).
                    build();
        } else {
            httpClient = new OkHttpClient.Builder().
                    addInterceptor(logging).
                    readTimeout(1000, TimeUnit.SECONDS).
                    connectTimeout(1000, TimeUnit.SECONDS).
                    build();
        }


        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        apiService = getRetrofit(httpClient, gson).create(ApiService.class);
        return apiService;
    }

    protected Retrofit getRetrofit(OkHttpClient httpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(httpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private Interceptor getAuthHeaderInterceptor(final String authHeader) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request;
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Authorization", authHeader);

                request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

}
