package nz.co.test.transactions.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import nz.co.test.transactions.R
import nz.co.test.transactions.ui.transactions.detail.TransactionDetailViewModel
import nz.co.test.transactions.ui.transactions.list.TransactionListItem
import nz.co.test.transactions.ui.transactions.list.TransactionNavType
import nz.co.test.transactions.ui.transactions.list.TransactionsViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MaterialTheme {
                Scaffold {
                    NavigationComponent(navController)
                }
            }
        }
    }

    @Composable
    fun NavigationComponent(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                val transactionsViewModel = hiltViewModel<TransactionsViewModel>()
                TransactionListScreen(
                    viewModel = transactionsViewModel,
                    navHostController = navController
                )
            }
            composable("details/{transaction}",
                arguments = listOf(
                    navArgument("transaction") { type = TransactionNavType() }
                )
            ) {
                val transactionDetailViewModel = hiltViewModel<TransactionDetailViewModel>()
                TransactionDetailScreen(
                    viewModel = transactionDetailViewModel,
                    navHostController = navController
                )
            }
        }
    }

    @Composable
    private fun TransactionListScreen(
        viewModel: TransactionsViewModel,
        navHostController: NavHostController
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(viewModel.binding.value.title) })
            },
            content = {
                when {
                    viewModel.binding.value.loadingVisible -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    viewModel.binding.value.contentVisible -> {
                        Column {
                            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                                itemsIndexed(items = viewModel.binding.value.transactions) { _, item ->
                                    TransactionListItemView(item, onItemClicked = {
                                        val transactionJson = Uri.encode(Gson().toJson(item))
                                        navHostController.navigate("details/$transactionJson")
                                    })
                                }
                            }
                        }
                    }
                    viewModel.binding.value.errorVisible -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = "Error....")
                        }
                    }
                }
            }
        )
    }

    @Composable
    private fun TransactionListItemView(
        transactionListItem: TransactionListItem,
        onItemClicked: (item: TransactionListItem) -> Unit
    ) {
        Box(
            modifier = Modifier
                .background(
                    colorResource(
                        id = if (transactionListItem.isCredit) {
                            R.color.green
                        } else {
                            R.color.red
                        }
                    )
                )
                .fillMaxWidth()
                .height(70.dp)
                .clickable {
                    onItemClicked(transactionListItem)
                }
        ) {
            Text(
                transactionListItem.summary, modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp, 0.dp, 8.dp, 4.dp),
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                transactionListItem.transactionDate, modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp, 4.dp, 8.dp, 4.dp)
            )
            Text(
                if (transactionListItem.isCredit) {
                    stringResource(R.string.transactions_transaction_list_credit_text) + transactionListItem.creditPrice
                } else {
                    stringResource(R.string.transactions_transaction_list_debit_text) + transactionListItem.debitPrice
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp, 4.dp, 8.dp, 4.dp),
                style = MaterialTheme.typography.body1
            )
            Divider(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = 1.dp
            )
        }
    }

    @Composable
    private fun TransactionDetailScreen(
        viewModel: TransactionDetailViewModel,
        navHostController: NavHostController
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(viewModel.binding.value.title) },
                    navigationIcon = if (navHostController.previousBackStackEntry != null) {
                        {
                            IconButton(onClick = { navHostController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    } else {
                        null
                    }
                )
            },
            content = {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.transactions_transaction_detail_summary_text),
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        Text(
                            text = viewModel.binding.value.summary,
                            style = MaterialTheme.typography.h5,
                        )
                        Text(
                            text = stringResource(R.string.transactions_transaction_detail_transaction_date),
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        Text(
                            text = viewModel.binding.value.transactionDate,
                            style = MaterialTheme.typography.h5,
                        )
                        Text(
                            text = if (viewModel.binding.value.isCredit) {
                                stringResource(R.string.transactions_transaction_detail_credit_text)
                            } else {
                                stringResource(R.string.transactions_transaction_detail_debit_text)
                            },
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        Text(
                            text = if (viewModel.binding.value.isCredit) {
                                viewModel.binding.value.credit
                            } else {
                                viewModel.binding.value.debit
                            },
                            style = MaterialTheme.typography.h5,
                        )
                        Text(
                            text = stringResource(R.string.transactions_transaction_detail_gst_text),
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        Text(
                            text = viewModel.binding.value.gst,
                            style = MaterialTheme.typography.h5,
                        )
                    }
                }
            }
        )
    }
}