package ua.meugen.android.pdfcomposer.ui.activities.viewrecent.fragment.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.text.DateFormat
import java.util.Collections
import java.util.Date
import java.util.Locale

import ua.meugen.android.pdfcomposer.R
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity

class PdfItemsAdapter(
        context: Context,
        private val listener: OnPdfItemClickListener) : RecyclerView.Adapter<PdfItemsAdapter.Holder>() {

    private val inflater: LayoutInflater
    private var items: List<PdfItemEntity>? = null

    init {
        this.inflater = LayoutInflater.from(context)
        items = emptyList()
    }

    fun swapItems(items: List<PdfItemEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int): Holder {
        val view = inflater.inflate(R.layout.item_exported_pdf,
                parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items!![position])
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    inner class Holder internal constructor(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val nameView: TextView
        private val sizeView: TextView
        private val dateView: TextView

        private var entity: PdfItemEntity? = null

        init {
            this.nameView = view.findViewById(R.id.name)
            this.sizeView = view.findViewById(R.id.size)
            this.dateView = view.findViewById(R.id.date)

            view.findViewById<View>(R.id.container).setOnClickListener(this)
        }

        override fun onClick(v: View) {
            listener.onPdfItemClick(entity!!)
        }

        fun bind(entity: PdfItemEntity) {
            this.entity = entity

            this.nameView.text = entity.name
            this.sizeView.text = sizeToText(entity.size)
            this.dateView.text = dateToText(entity.date)
        }

        private fun sizeToText(size: Long): String {
            val text: String

            val unit = 1024
            if (size < unit) {
                text = size.toString() + " B"
            } else {
                val exp = (Math.log(size.toDouble()) / Math.log(unit.toDouble())).toInt()
                val pre = "" + "KMGTPE"[exp - 1]
                text = String.format(Locale.ENGLISH,
                        "%.1f %sB",
                        size / Math.pow(unit.toDouble(), exp.toDouble()), pre)
            }
            return text
        }

        private fun dateToText(date: Date?): String {
            return DateFormat.getDateInstance().format(date)
        }
    }
}
