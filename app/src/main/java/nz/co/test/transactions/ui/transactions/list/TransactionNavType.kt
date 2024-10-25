package nz.co.test.transactions.ui.transactions.list

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson

internal class TransactionNavType : NavType<TransactionListItem>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): TransactionListItem? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): TransactionListItem {
        return Gson().fromJson(value, TransactionListItem::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: TransactionListItem) {
        bundle.putParcelable(key, value)
    }
}