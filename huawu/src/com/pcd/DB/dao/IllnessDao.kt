package com.example.com.pcd.DB.dao

import com.example.com.pcd.entity.Illness
import com.example.com.pcd.entity.Images

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/10/16-15:40
 */
abstract class IllnessDao {
    abstract fun insertIllness(image: Illness):Pair<String, String>?
    abstract fun queryAllIllness():List<Illness>
    abstract fun queryIllnessById(id:Int):List<Illness>
}