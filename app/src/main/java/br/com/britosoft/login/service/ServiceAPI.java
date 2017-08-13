package br.com.britosoft.login.service;

import br.com.britosoft.login.model.CrachaValida;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by carlos on 13/08/17.
 */

public interface ServiceAPI {
    String BASE_URL = "http://192.168.1.2/webservice/";
    @GET("nutricao/")
    Call<CrachaValida> getRetorno(@Query("acao") String acao, @Query("cracha") String cracha);

    Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl( BASE_URL )
                                    .addConverterFactory(GsonConverterFactory.create() )
                                    .build();

}
