package mobileApps.projekAkhir.kelompokSatu.data.database.dao

import androidx.room.*
import io.reactivex.Single
import mobileApps.projekAkhir.kelompokSatu.data.source.model.UserInformation

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: UserInformation) : Single<Long>

    @Query("SELECT * FROM userinformation WHERE email =:email AND password =:password")
    fun login(email : String?, password: String?): Single<UserInformation>

    @Query("SELECT * FROM userinformation")
    fun get(): Single<List<UserInformation>>
}