@file:Suppress("DEPRECATION")

package br.com.everis.sovamu.feature.home.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.everis.sovamu.R
import com.nhaarman.mockitokotlin2.atLeast
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.isA
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ScrumViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ScrumViewModel
    private val mDispatcher = Dispatchers.Unconfined

    @Mock
    lateinit var observer: Observer<ScrumViewAction>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `check state empty list - getNotification`() {

        viewModel = ScrumViewModel(mDispatcher, mDispatcher, listOf())
        viewModel.scrumView.observeForever(observer)
        viewModel.getNotification()

        assertNotNull(viewModel.scrumView.value)
        verify(observer, atLeast(2)).onChanged(isA(ScrumViewAction.Loading::class.java))
    }

    @Test
    fun `check state - getNotification`() {

        viewModel = ScrumViewModel(mDispatcher, mDispatcher)
        viewModel.scrumView.observeForever(observer)
        viewModel.getNotification()

        assertNotNull(viewModel.scrumView.value)
        verify(observer, atLeast(2)).onChanged(isA(ScrumViewAction.Loading::class.java))
        verify(observer).onChanged(isA(ScrumViewAction.ShowNotification::class.java))
    }

    @Test
    fun `check state startAnimation - actionNotification`() {

        viewModel = ScrumViewModel(mDispatcher, mDispatcher)
        viewModel.scrumView.observeForever(observer)
        viewModel.actionNotification(R.anim.return_from_left, R.anim.move_right_to_left)

        assertNotNull(viewModel.scrumView.value)
        verify(observer, atLeast(2)).onChanged(isA(ScrumViewAction.Loading::class.java))
        verify(observer).onChanged(isA(ScrumViewAction.StartAnimationNotification::class.java))
    }

    @Test
    fun `check state endAnimation - actionNotification`() {

        viewModel = ScrumViewModel(mDispatcher, mDispatcher, listOf())
        viewModel.scrumView.observeForever(observer)
        viewModel.actionNotification(R.anim.return_from_left, R.anim.move_right_to_left)

        assertNotNull(viewModel.scrumView.value)
        verify(observer, atLeast(2)).onChanged(isA(ScrumViewAction.Loading::class.java))
        verify(observer).onChanged(isA(ScrumViewAction.EndAnimationNotification::class.java))
    }

}