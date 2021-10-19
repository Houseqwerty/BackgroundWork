package com.example.backgroundwork

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.backgroundwork.models.domain.UserDomain
import com.example.backgroundwork.models.presentation.UserModel
import com.example.backgroundwork.utils.SchedulersProviderImplStub
import com.example.backgroundwork.view.users.UsersViewModel
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    lateinit var viewModel: UsersViewModel

    private val interactor: UsersInteractor = mockk(relaxed = true)

    private val usersObserver: Observer<List<UserModel>> = mockk()
    private val progressObserver: Observer<Boolean> = mockk()
    private val errorObserver: Observer<Throwable> = mockk()

    @Before
    fun setUp() {
        viewModel = UsersViewModel(interactor, SchedulersProviderImplStub())

        viewModel.users().observeForever(usersObserver)
        viewModel.progress().observeForever(progressObserver)
        viewModel.errors().observeForever(errorObserver)

        every { usersObserver.onChanged(any()) } just Runs
        every { progressObserver.onChanged(any()) } just Runs
        every { errorObserver.onChanged(any()) } just Runs
    }

    private fun usersDomain() = listOf(UserDomain("login", "avatar"))

    private fun userModel(): List<UserModel> = listOf(UserModel("login", "avatar"))

    @Test()
    fun testUsers() {
        every { interactor.getUsers() } returns usersDomain()
        viewModel.loadUsers()
        verifySequence {
            progressObserver.onChanged(true)
            usersObserver.onChanged(userModel())
            progressObserver.onChanged(false)
        }
        verify { errorObserver wasNot Called }
    }

    @Test
    fun testError() {
        val exception = IOException("Test")
        every { interactor.getUsers() } throws exception
        viewModel.loadUsers()
        verifySequence {
            progressObserver.onChanged(true)
            errorObserver.onChanged(exception)
            progressObserver.onChanged(false)
        }
        verify { usersObserver wasNot Called }

    }

}