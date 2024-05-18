package com.training.islamyapp.network;

import android.util.Log;

import androidx.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String TAG = "ApiClient";
    private static Retrofit instance = null;


    static OkHttpClient client = new OkHttpClient.Builder()
           .addInterceptor( new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
               @Override
               public void log(@NonNull String s) {
                   Log.e(TAG, s);
               }
           }).setLevel(HttpLoggingInterceptor.Level.BODY))
           .build();


    public static Retrofit getInstanceForSurah(){
        if (instance == null){
            instance = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://api.alquran.cloud/v1/")
                    .build();
        }
        return instance;
    }

    static Retrofit surahDetailsInstance;
    public static Retrofit getInstanceForSurahDetails(){
        if (surahDetailsInstance == null){
            surahDetailsInstance = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .baseUrl("https://quranenc.com/api/v1/translation/")
                    .build();
        }


        return surahDetailsInstance;
    }

    static Retrofit radioInstance;
    public static Retrofit getRadioInstance(){
        if (radioInstance == null) {
            radioInstance = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://api.mp3quran.net/")
                    .build();
        }

        return radioInstance;
    }

    private static Retrofit prayerTimesInstance;
    public static Retrofit getPrayerTimesInstance(){
        if (prayerTimesInstance == null){
            prayerTimesInstance = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://api.aladhan.com/v1/")
                    .build();
        }
        return prayerTimesInstance;
    }

    public static ApiServices getSurahServices(){
        return getInstanceForSurah().create(ApiServices.class);
    }

    public static ApiServices getSurahDetailsServices(){
        return getInstanceForSurahDetails().create(ApiServices.class);
    }

    public static ApiServices getRadioServices(){
        return getRadioInstance().create(ApiServices.class);
    }
    public static ApiServices getPrayerTimesServices(){
        return getPrayerTimesInstance().create(ApiServices.class);
    }
}
