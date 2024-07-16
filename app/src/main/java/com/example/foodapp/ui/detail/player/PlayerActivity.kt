package com.example.foodapp.ui.detail.player

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.widget.Toast
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityPlayerBinding
import com.example.foodapp.utils.VIDEO_ID
import com.example.foodapp.utils.YOUTUBE_API_KEY
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlin.math.log

@Suppress("DEPRECATION")
class PlayerActivity : AppCompatActivity() {

    //binding
    private var _binding : ActivityPlayerBinding? = null
    private val binding get() = _binding!!
    //other
    private var videoId = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayerBinding.inflate(layoutInflater)
        //Full Screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(binding.root)
        //get data
        videoId = intent.getStringExtra(VIDEO_ID).toString()
        //initViews
        binding.apply {
            val video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/$videoId\" title=\"YouTube video player\"" +
                    " frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\"" +
                    " allowfullscreen></iframe>"
            videoPlayer.loadData(video, "text/html", "utf-8")
            videoPlayer.settings.javaScriptEnabled = true
            videoPlayer.webChromeClient = WebChromeClient()
        }
    }
}