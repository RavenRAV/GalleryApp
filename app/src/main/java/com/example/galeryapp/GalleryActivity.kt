package com.example.galeryapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_gallery.*


class GalleryActivity : AppCompatActivity(), GalleryAdapter.GalleryListener {
    override fun onItemClick(image: GalleryImage) {
        if (image.isSelected) {
            selectedImages.add(image)
        } else {
            selectedImages.remove(image)
        }
        updateFloatingPanel()
    }

    val selectedImages = mutableListOf<GalleryImage>()
    private val PERMISSION_REQUEST_CODE = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isHasPermission()
        confirmAction()

    }


    private fun isHasPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        } else {
            setupAdapter()
        }
    }

    private fun setupAdapter() {
        recycler_view.layoutManager = GridLayoutManager(this, 3)
        val list = Helper().listOfImages(this)
        recycler_view.adapter = GalleryAdapter(list, selectedImages, this)
    }



    private fun updateFloatingPanel() {
        count.text = " ${selectedImages.size} фото"
    }


    private fun confirmAction() {
        confirm.setOnClickListener {
            ChoosedImageActivity.start(this, selectedImages)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                setupAdapter()
        } else {

        }
    }

//    companion object{
//        private var selectedImages = mutableListOf<GalleryImage>()
//        fun back(activity: Activity?, selectedList: MutableList<GalleryImage>){
//            selectedImages = selectedList
//            activity?.startActivity(Intent(activity, GalleryActivity::class.java))
//        }
//    }

}
