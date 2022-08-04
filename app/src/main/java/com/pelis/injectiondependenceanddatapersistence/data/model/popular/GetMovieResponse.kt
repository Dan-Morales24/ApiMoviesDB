package com.pelis.injectiondependenceanddatapersistence.data.model.popular

data class GetMovieResponse(
       val page: Int,
       val results: List<MovieModel>,
       val total_results: Int,
       val total_pages: Int
   )