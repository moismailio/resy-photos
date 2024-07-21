package com.resy.photo_list_presentation

import com.resy.domain.Results
import com.resy.photo_list_domain.usecases.LoadPhotosListUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PhotosListViewModelTest {

    private lateinit var loadPhotosUseCase: LoadPhotosListUseCase
    private lateinit var viewModel: PhotosListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        loadPhotosUseCase = mockk(relaxed = true)
        viewModel = PhotosListViewModel(loadPhotosUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun test_loading_state() = runTest {
        coEvery { loadPhotosUseCase() } returns flowOf(Results.Loading)
        viewModel.handleAction(PhotosListUiAction.ReloadList)
        assert(viewModel.profileUiState.value is PhotosUiState.Loading)
    }

    @Test
    fun test_error_state() = runTest {
        val throwable: Throwable = mockk(relaxed = true)
        coEvery { loadPhotosUseCase() } returns flowOf(Results.Error(throwable))
        viewModel.handleAction(PhotosListUiAction.ReloadList)
        advanceUntilIdle()
        assert(viewModel.profileUiState.value is PhotosUiState.Error)
    }

    @Test
    fun test_success_state() = runTest {
        coEvery { loadPhotosUseCase() } returns flowOf(
            Results.Success(
                listOf(mockk(relaxed = true) {
                    every { id } returns 32L
                })
            )
        )
        viewModel.handleAction(PhotosListUiAction.ReloadList)
        advanceUntilIdle()
        assert(viewModel.profileUiState.value is PhotosUiState.Success)
        assertEquals((viewModel.profileUiState.value as PhotosUiState.Success).data.first().id, 32L)
    }
}