package es.voghdev.teamworksample.features.projects.api

import es.voghdev.teamworksample.features.projects.GetProjects
import es.voghdev.teamworksample.features.projects.api.model.GetProjectsApiResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GetProjectsApiDataSource(val apiToken: String) : GetProjects, ApiRequest {
    override fun getProjects(listener: GetProjects.Listener) {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()

        builder.addNetworkInterceptor(AuthInterceptor(apiToken))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(getEndPoint())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()

        val service: TeamworkService = retrofit.create(TeamworkService::class.java)

        val call = service.getProjects()

        call.enqueue(object : Callback<GetProjectsApiResponse> {
            override fun onResponse(call: Call<GetProjectsApiResponse>, rsp: Response<GetProjectsApiResponse>) {
                when {
                    rsp.body()?.STATUS.equals("OK") ->
                        listener.onSuccess(rsp.body()?.projects?.map { it.toDomain() }
                                ?: emptyList())
                    rsp.errorBody() != null ->
                        listener.onFailure(Exception(rsp.errorBody()?.string()))
                    else ->
                        listener.onFailure(Exception("Unknown error retrieving projects"))
                }
            }

            override fun onFailure(call: Call<GetProjectsApiResponse>, t: Throwable) {
                listener.onFailure(Exception(t.message))
            }
        })
    }
}
