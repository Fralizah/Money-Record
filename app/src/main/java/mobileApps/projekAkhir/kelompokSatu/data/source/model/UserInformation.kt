package mobileApps.projekAkhir.kelompokSatu.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userinformation")
data class UserInformation (
    @PrimaryKey(autoGenerate = true) @ColumnInfo val id : Int = 0,
    val username : String,
    val email : String,
    val password : String,
)