package com.example.inventory.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity(tableName = "item")
data class Item(

    // to make this property as primary key and automatically generate unique value for this entity id
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // to specify different name for the column names rather than using te variable names
    @ColumnInfo(name = "name")
    val itemName: String,

    @ColumnInfo(name = "price")
    val itemPrice: Double,

    @ColumnInfo(name = "quantity")
    val quantityInStock: Int,
)

// extension function to get formatted price of itemPrice property
fun Item.getFormattedPrice() : String = NumberFormat.getCurrencyInstance().format(itemPrice)