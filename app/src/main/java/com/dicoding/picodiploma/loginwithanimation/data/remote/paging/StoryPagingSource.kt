package com.dicoding.picodiploma.loginwithanimation.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.ListStoryItem
import com.dicoding.picodiploma.loginwithanimation.data.remote.retrofit.ApiService

class StoryPagingSource(private val apiService: ApiService, private val token: String) :
    PagingSource<Int, ListStoryItem>() {

    override fun getRefreshKey(state: PagingState<Int, ListStoryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListStoryItem> {
        return try {
            val pageNumber = params.key ?: INITIAL_PAGE_INDEX
            val response = apiService.getStories("Bearer $token", pageNumber, params.loadSize)

            val stories = response.listStory

            LoadResult.Page(
                data = stories,
                prevKey = if (pageNumber == INITIAL_PAGE_INDEX) null else pageNumber - 1,
                nextKey = if (stories.isEmpty()) null else pageNumber + 1
            )
        } catch(e: Exception) {
            LoadResult.Error(e)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}