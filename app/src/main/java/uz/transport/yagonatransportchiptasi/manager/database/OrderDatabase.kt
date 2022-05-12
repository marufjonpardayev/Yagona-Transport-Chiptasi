package uz.transport.yagonatransportchiptasi.manager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.transport.yagonatransportchiptasi.manager.dao.OrderDao
import uz.transport.yagonatransportchiptasi.model.Order

@Database(entities = [Order::class], version = 1)
abstract class OrderDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

    companion object {
        private var instance: OrderDatabase? = null

        @Synchronized
        fun getInstance(context: Context): OrderDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, OrderDatabase::class.java, "order.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}