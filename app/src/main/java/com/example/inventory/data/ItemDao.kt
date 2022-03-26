package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * this annotation annotate that this interface methods used for accessing SQLite db
 */
@Dao
interface ItemDao {

    // onConflict tells the room what to do in case of conflict
    // so what we done here is in case of conflict, Ignore the new item if it's id already exists in database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    // Now the Room will generate all the necessary code to insert the item into the database
    suspend fun insert(item: Item)
    // when execute this function, SQL query will be executed to insert the entity to database

    // update the entity that has the same primary key as the entity that's passed in. In this case is entity
    @Update
    suspend fun update(item: Item)

    @Delete
    // annotation to delete one item or list of items that has the same primary key as the entity
    // that's passed in
    suspend fun delete(item: Item)

    @Query("SELECT * FROM item")
    fun getItems(): Flow<List<Item>>

    // query for getting one entry based on id
    // to get value from arguments in the function,
    // use the colon notation before the name of the argument
    @Query("SELECT * FROM item WHERE id = :id")
    // this function return flow so it will ensure you get notified when there is changes in database
    // Because this function return Flow type, so query is still executed in backgroud Thread
    // so you don't need to explicitly make this suspend and call it in coroutine scope
    fun getItem(id: Int) : Flow<Item>



}