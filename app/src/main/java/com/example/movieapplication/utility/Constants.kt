package com.example.movieapplication.utility

object Constants {
    const val MOVIE_INTENT_EXTRA = "MOVIE_INTENT_EXTRA"
    const val BASE_URL = "https://jainism.me/application/vigyadarshan/"
    const val API_URL = BASE_URL + "api/v1/"
    const val IMAGE_URL = BASE_URL + "public/storage/files_main"
    const val TOKEN = "Bearer 33|BfSSgZCx0LhMycrt5GkGVx92HVLbVx2nWNV3hjDO"
    const val Authorization = "Authorization:"
    const val PAGE_START = 1
    const val INITIAL_MOVIE_POSITION = 1
    const val VIEW_TYPE_LOADING = 100
    const val VIEW_TYPE_SHOW_LIST = 101

    /**
     * Set scrolling threshold here (for now i'm assuming 10 item in one page)
     */
    const val PAGE_SIZE = 10
}