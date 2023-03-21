package id.ac.unpas.gymmember.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.gymmember.model.GymMember

@Dao
interface GymMemberDao {
    @Query("SELECT * FROM GymMember")
    fun loadAll(): LiveData<List<GymMember>>

    @Query("SELECT * FROM GymMember WHERE id = :id")
    fun find(id: String): GymMember?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: GymMember)

    @Delete
    fun delete(item: GymMember)
}