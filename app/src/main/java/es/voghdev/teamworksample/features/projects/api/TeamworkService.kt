package es.voghdev.teamworksample.features.projects.api

import es.voghdev.teamworksample.features.projects.api.model.GetProjectsApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface TeamworkService {
    @GET("projects.json")
    fun getProjects(): Call<GetProjectsApiResponse>
}