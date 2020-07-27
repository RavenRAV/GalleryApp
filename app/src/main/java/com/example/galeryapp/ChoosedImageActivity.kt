package com.example.galeryapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_choosed_image.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recycler_view

class ChoosedImageActivity : AppCompatActivity(), GalleryAdapter.GalleryListener {
    override fun onItemClick(image: GalleryImage) {

    }

    private var images = mutableListOf<GalleryImage>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosed_image)
        setupAdapter()
        var toolbar: androidx.appcompat.widget.Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Выбранные фотографии"

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun setupAdapter() {
        recycler_view2.layoutManager = GridLayoutManager(this, 3)
        recycler_view2.adapter = GalleryAdapter(selectedImages, images, this)
    }

    companion object{
        private var selectedImages = mutableListOf<GalleryImage>()
        fun start(activity: Activity?, selectedList: MutableList<GalleryImage>){
            selectedImages = selectedList
            activity?.startActivity(Intent(activity, ChoosedImageActivity::class.java))
        }
    }

}
