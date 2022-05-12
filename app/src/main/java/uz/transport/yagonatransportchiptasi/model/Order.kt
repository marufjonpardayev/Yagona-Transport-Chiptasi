package uz.transport.yagonatransportchiptasi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val from: String,
    val to: String,
    val date: String,
    val passengerFullName: String
)
