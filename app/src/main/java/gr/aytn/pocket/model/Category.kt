package gr.aytn.pocket.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gr.aytn.pocket.enums.CategoryTypeEnum

@Entity
data class Category(
    @ColumnInfo(name = "icon") val icon: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "category_type") val categoryType: CategoryTypeEnum,
    @PrimaryKey(autoGenerate = true) val id: Int
    )
