package com.example.com.pcd.DB.dao

import com.example.com.pcd.entity.Illness
import com.example.com.pcd.entity.Images

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/10/16-15:40
 */
abstract class ImagesDao {
    abstract fun insertImages(image: Images):Pair<String, String>?
    abstract fun queryAllImages():List<Images>
    abstract fun queryImageById(id:Int):List<Images>

}