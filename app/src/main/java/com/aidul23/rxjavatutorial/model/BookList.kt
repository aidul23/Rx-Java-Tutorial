package com.aidul23.rxjavatutorial.model

import java.util.ArrayList

data class BookList(val items: ArrayList<VolumeInfo>)
data class VolumeInfo(val volumeInfo: BookInfo)
data class BookInfo(val title: String, val publisher: String, val description: String, val imageLinks: ImageLinks)
data class ImageLinks(val smallThumbnail: String)