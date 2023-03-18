package com.voidmemories.nifty50.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.voidmemories.nifty50.core.Resource
import com.voidmemories.nifty50.data.models.StockModel
import com.voidmemories.nifty50.domain.Nifty50RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var repository: Nifty50RepositoryImpl

    private val testDispatcher = StandardTestDispatcher()
    private val mStockObject = StockModel(
        symbol = "IBM",
        identifier = "IBMQBN",
        lastPrice = 1239.58,
        change = 80.0,
        pChange = 12.5,
    )

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(repository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should add a new data on success status`() = runTest {
        Mockito.`when`(repository.getNifty50Details())
            .thenReturn(Resource.success(listOf(mStockObject)))

        viewModel.setNifty50LiveData(listOf(mStockObject))
        viewModel.fetchNifty50()

        Assert.assertEquals(listOf(mStockObject), viewModel.nifty50LiveData.value)
    }

    @Test
    fun `should emit a null on failure status`() = runTest {
        Mockito.`when`(repository.getNifty50Details())
            .thenReturn(Resource.error("Network error", null))

        viewModel.fetchNifty50()

        Assert.assertEquals(null, viewModel.nifty50LiveData.value)
    }
}