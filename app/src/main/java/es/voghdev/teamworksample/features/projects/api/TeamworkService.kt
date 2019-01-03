package es.voghdev.teamworksample.features.projects.api

import es.voghdev.teamworksample.features.projects.api.model.GetProjectsApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TeamworkService {
    @GET("projects.json")
    fun getProjects(
            @Header("Authorization") apiToken: String,
            @Header("Accept") mediaType: String
    ): Call<GetProjectsApiResponse>
}