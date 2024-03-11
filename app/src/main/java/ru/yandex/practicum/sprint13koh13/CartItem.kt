package ru.yandex.practicum.sprint13koh13

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.yandex.practicum.sprint13koh13.databinding.VCartItemBinding
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

data class CartItem(
    val id: String,
    val catalogItem: CatalogItem,
    val count: Int,
) {
    val sum: Long
        get() = catalogItem.price.times(count)
}


class CartItemViewHolder(
    parent: ViewGroup,
    val binding: VCartItemBinding = VCartItemBinding.inflate(
        LayoutInflater.from(
            parent.context
        ), parent, false
    )
) : RecyclerView.ViewHolder(
    binding.root
) {

    fun bind(item: CartItem) {
        binding.root

        Glide
            .with(binding.root.context)
            .load(item.catalogItem.imageUrl)
            .into(binding.image)
        binding.title.text = item.catalogItem.name

        binding.count.text = item.count.toString()
        val formatter = DecimalFormat("#,###.##")
        formatter.minimumFractionDigits = 2
        formatter.decimalFormatSymbols = DecimalFormatSymbols().apply {
            groupingSeparator = ' '
        }
        binding.sumProduct.text = formatter.format(item.sum.toFloat() / 100)
    }

}