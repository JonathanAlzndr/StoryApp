package com.dicoding.picodiploma.loginwithanimation

import com.dicoding.picodiploma.loginwithanimation.data.remote.response.ListStoryItem
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.StoryResponse
import kotlin.text.Typography.quote

object DataDummy {

    fun generateDummyListStoryItemResponse(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 0..100) {
            val listStoryItem = ListStoryItem(
                name = "name: $i",
                description = "description: $i"
            )
            items.add(listStoryItem)
        }
        return items
    }
}