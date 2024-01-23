package com.example.githubrepoviewer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class RepositoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val description : String,
    val owner : String,
    val stars : Int,
)
