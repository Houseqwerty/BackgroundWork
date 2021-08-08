package com.example.backgroundwork.view.users.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.backgroundwork.R
import com.example.backgroundwork.data.api.user.UserApi
import com.example.backgroundwork.data.api.user.UserApiImpl
import com.example.backgroundwork.data.repository.UserRepository
import com.example.backgroundwork.data.repository.UserRepositoryImpl
import com.example.backgroundwork.data.store.UserStore
import com.example.backgroundwork.data.store.UserStoreImpl
import com.example.backgroundwork.domain.UsersInteractor
import com.example.backgroundwork.utils.SchedulersProvider
import com.example.backgroundwork.utils.SchedulersProviderImpl
import com.example.backgroundwork.view.users.UsersViewModel
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class UsersFragment : Fragment(R.layout.fragment_first) {

    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
                val json = Json {
                    ignoreUnknownKeys = true
                }
                val userApi: UserApi = UserApiImpl(okHttpClient, json)
                val userStore: UserStore = UserStoreImpl(
                    requireContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE),
                    json
                )
                val usersRepository: UserRepository = UserRepositoryImpl(userApi, userStore)
                val usersInteractor = UsersInteractor(usersRepository)
                val schedulersProvider: SchedulersProvider = SchedulersProviderImpl()

                return UsersViewModel(usersInteractor, schedulersProvider) as T
            }

        }).get(UsersViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UsersAdapter()
        val progressView = view.findViewById<View>(R.id.progress_bar)
        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            this.adapter = adapter
        }
        viewModel.users().observe(viewLifecycleOwner) { users ->
            adapter.submitItems(users)
        }
        viewModel.progress().observe(viewLifecycleOwner) { showProgress ->
            progressView.isVisible = showProgress
        }
        viewModel.errors().observe(viewLifecycleOwner) { throwable ->
            // TODO
        }

        viewModel.loadUsers()
    }

}