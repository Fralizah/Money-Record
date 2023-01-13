package mobileApps.projekAkhir.kelompokSatu.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaksi")
data class Transaksi (
    @PrimaryKey(autoGenerate = true) @ColumnInfo val id : Int = 0,
    val userId: Int,
    val title : String,
    val amount : Int,
    val type : String,
    val date : String,
)