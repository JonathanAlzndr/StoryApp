package com.dicoding.picodiploma.loginwithanimation.view.detail

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.ListStoryItem
import com.dicoding.picodiploma.loginwithanimation.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var contentImage: ImageView
    private lateinit var name: TextView
    private lateinit var description: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contentImage = binding.ivContentImage
        name = binding.tvTitle
        description = binding.tvDescription

        val storyItem = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_STORY, ListStoryItem::class.java)
        } else {
            @Suppress("Deprecation")
            intent.getParcelableExtra(EXTRA_STORY)
        }

        if (storyItem != null) {

            Glide.with(this)
                .load(storyItem.photoUrl)
                .into(contentImage)

            name.text = storyItem.name.toString()
            description.text = storyItem.description.toString()
        }
    }

    companion object {
        const val EXTRA_STORY = "extra_story"
    }
}