package mobileApps.projekAkhir.kelompokSatu.data.database.dao

import androidx.room.*
import io.reactivex.Single
import mobileApps.projekAkhir.kelompokSatu.data.source.model.Transaksi

@Dao
interface TransaksiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Transaksi) : Single<Long>


    @Query("SELECT * FROM transaksi WHERE userId=:userId")
    fun get(userId:Int?): Single<List<Transaksi>>

    @Query("DELETE FROM transaksi WHERE id = :id")
    fun delete(id : Int?)

}