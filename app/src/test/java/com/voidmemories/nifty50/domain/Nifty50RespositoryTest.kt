package com.voidmemories.nifty50.domain

import com.voidmemories.nifty50.core.AppState
import com.voidmemories.nifty50.data.datasources.api.Nifty50Api
import com.voidmemories.nifty50.data.models.StockModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class Nifty50RespositoryTest {
    private lateinit var repository: Nifty50Repository

    @Mock
    private lateinit var mockNifty50Api: Nifty50Api
    private val mStockObject = StockModel(
        symbol = "IBM",
        identifier = "IBMQBN",
        lastPrice = 1239.58,
        change = 80.0,
        pChange = 12.5,
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = Nifty50RepositoryImpl(mockNifty50Api)
    }

    @Test
    fun `should return success state`() = runTest {
        `when`(mockNifty50Api.getNifty50Details())
            .thenReturn(Response.success(listOf(mStockObject)))

        val res = repository.getNifty50Details()
        assertEquals(listOf(mStockObject), (res as AppState.Success).data)
    }

    @Test
    fun `should return error state`() = runTest {
        `when`(mockNifty50Api.getNifty50Details()).thenReturn(
            Response.error(
                404,
                "{}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
        )

        val res = repository.getNifty50Details()
        Assert.assertTrue(res is AppState.Error)
    }
}