package mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.dasboard.component

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.toJson
import mobileApps.projekAkhir.kelompokSatu.data.source.model.Transaksi
import mobileApps.projekAkhir.kelompokSatu.databinding.ItemListHistoryBinding
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.detailtransaksi.pages.DetailTransaksiActivity
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.edittransaksi.pages.EditTransaksiActivity
import java.text.NumberFormat
import java.util.*

@SuppressLint("NotifyDataSetChanged")
class HistoryTransaksiAdapter :RecyclerView.Adapter<HistoryTransaksiAdapter.ViewHolder>() {

    private var _data= ArrayList<Transaksi>()

    inner class ViewHolder(private val itemBinding: ItemListHistoryBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item:Transaksi, position: Int){
            itemBinding.apply {
                val format: NumberFormat = NumberFormat.getCurrencyInstance()

                format.setMaximumFractionDigits(0)
                format.setCurrency(Currency.getInstance("IDR"))

                tvTitle.text = item.title
                tvDate.text = item.date
                tvAmount.text = format.format(item.amount)

                val context = root.context
                cvHistory.setOnClickListener {
                    context.intentActivity(DetailTransaksiActivity::class.java, item.toJson())
                    (context as Activity).finish()
                }

                btnUpdate.setOnClickListener {
                    context.intentActivity(EditTransaksiActivity::class.java, item.toJson())
                    (context as Activity).finish()
                }
            }
        }
    }

    fun addItems(items:List<Transaksi>){
        _data.clear()
        _data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_data[position], position)
    }

    override fun getItemCount(): Int {
        return _data.size
    }
}