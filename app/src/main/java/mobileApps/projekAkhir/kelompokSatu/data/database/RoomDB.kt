package mobileApps.projekAkhir.kelompokSatu.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mobileApps.projekAkhir.kelompokSatu.data.database.dao.TransaksiDao
import mobileApps.projekAkhir.kelompokSatu.data.database.dao.UserDao
import mobileApps.projekAkhir.kelompokSatu.data.source.model.Transaksi
import mobileApps.projekAkhir.kelompokSatu.data.source.model.UserInformation

@Database(entities = [UserInformation::class, Transaksi::class], version = 1, exportSchema = false)
abstract class RoomDB: RoomDatabase() {

    abstract fun user(): UserDao
    abstract fun transaksi(): TransaksiDao

    companion object{
        @Volatile

        private var _INSTANCE: RoomDB? = null

        fun getDatabase(context: Context): RoomDB{
            return _INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "money_db"
                ).build()
                _INSTANCE = instance
                instance
            }
        }
    }

}