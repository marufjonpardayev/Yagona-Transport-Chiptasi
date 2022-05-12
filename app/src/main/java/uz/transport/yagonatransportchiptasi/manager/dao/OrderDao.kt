package uz.transport.yagonatransportchiptasi.manager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.transport.yagonatransportchiptasi.model.Order

@Dao
interface OrderDao {

    @Insert()
    fun insertProduct(order: Order)

    @Query("SELECT * FROM `order`")
    fun getAllProduct(): List<Order>

    @Query("DELETE FROM `order`")
    fun clearOrders()
}