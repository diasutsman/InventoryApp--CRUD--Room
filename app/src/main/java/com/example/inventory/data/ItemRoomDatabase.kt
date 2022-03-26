package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * annotation used for annotate that this class is the database holder
 * entities parameter tell the list of entities in the database
 * version of database schema (increase this version every time there is changes in database table)
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {

    // tell the database what DAO that it's methods can access the databse
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null
        fun getDatabase(context: Context): ItemRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database")
                    /**
                     * when there is schema changes, you would you would have to provide a migration object with a migration strategy
                     * migration object that defines
                     * how you take all rows with the old schema and convert them to rows in the new schema,
                     * so that no data is lost,
                     * if you didn't define it, it will just destroy and rebuild the database when there is schema changes
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}