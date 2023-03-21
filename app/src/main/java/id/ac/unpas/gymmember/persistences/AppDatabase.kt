package id.ac.unpas.gymmember.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.gymmember.model.GymMember

@Database(entities = [GymMember::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun setoranSampahDao(): GymMemberDao
}