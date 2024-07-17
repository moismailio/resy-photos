package com.resy.photos.photosList.presentation

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

    private lateinit var loadPhotosUseCase: com.resy.photo_list_domain.usecases.LoadPhotosListUseCase
    private lateinit var viewModel: com.resy.photo_list_presentation.PhotosListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        loadPhotosUseCase = mockk(relaxed = true)
        viewModel = com.resy.photo_list_presentation.PhotosListViewModel(loadPhotosUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun test_loading_state() = runTest {
        coEvery { loadPhotosUseCase() } returns flowOf(com.resy.domain.Results.Loading)
        viewModel.handleAction(com.resy.photo_list_presentation.PhotosListUiAction.ReloadList)
        assert(viewModel.profileUiState.value is com.resy.photo_list_presentation.PhotosUiState.Loading)
    }

    @Test
    fun test_error_state() = runTest {
        val throwable: Throwable = mockk(relaxed = true)
        coEvery { loadPhotosUseCase() } returns flowOf(com.resy.domain.Results.Error(throwable))
        viewModel.handleAction(com.resy.photo_list_presentation.PhotosListUiAction.ReloadList)
        advanceUntilIdle()
        assert(viewModel.profileUiState.value is com.resy.photo_list_presentation.PhotosUiState.Error)
    }

    @Test
    fun test_success_state() = runTest {
        coEvery { loadPhotosUseCase() } returns flowOf(
            com.resy.domain.Results.Success(
                listOf(mockk(relaxed = true) {
                    every { id } returns 32L
                })
            )
        )
        viewModel.handleAction(com.resy.photo_list_presentation.PhotosListUiAction.ReloadList)
        advanceUntilIdle()
        assert(viewModel.profileUiState.value is com.resy.photo_list_presentation.PhotosUiState.Success)
        assertEquals((viewModel.profileUiState.value as com.resy.photo_list_presentation.PhotosUiState.Success).data.first().id, 32L)
    }
}