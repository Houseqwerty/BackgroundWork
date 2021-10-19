package com.example.backgroundwork.view.users.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.backgroundwork.R
import com.example.backgroundwork.data.api.OkHttpUsersApiImpl
import com.example.backgroundwork.data.api.UsersApi
import com.example.backgroundwork.data.repository.UsersRepositoryImpl
import com.example.backgroundwork.data.store.PreferencesUsersStoreImpl
import com.example.backgroundwork.data.store.UsersStore
import com.example.backgroundwork.domain.UsersInteractor
import com.example.backgroundwork.domain.UsersRepository
import com.example.backgroundwork.models.converter.Converter
import com.example.backgroundwork.models.converter.UserDomainToUserModelConverter
import com.example.backgroundwork.models.converter.UserResponseToUserStoreConverter
import com.example.backgroundwork.models.converter.UserStoreToUserDomainConverter
import com.example.backgroundwork.models.data.UserResponse
import com.example.backgroundwork.models.data.UserStore
import com.example.backgroundwork.models.domain.UserDomain
import com.example.backgroundwork.models.presentation.UserModel
import com.example.backgroundwork.utils.SchedulersProvider
import com.example.backgroundwork.utils.SchedulersProviderImpl
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class UsersFragment : Fragment(R.layout.fragment_first) {

    lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                val jsonSerializer = Json {
                    ignoreUnknownKeys = true
                }
                val okClient =
                    OkHttpClient.Builder()
                        .addNetworkInterceptor(
                            HttpLoggingInterceptor().setLevel(
                                HttpLoggingInterceptor.Level.BODY
                            )
                        )
                        .build()

                val preferences =
                    requireContext().getSharedPreferences("Users_prefs", Context.MODE_PRIVATE)

                val userStore: UsersStore = PreferencesUsersStoreImpl(preferences, jsonSerializer)
                val converterData: Converter<UserResponse, UserStore> =
                    UserResponseToUserStoreConverter()
                val userApi: UsersApi = OkHttpUsersApiImpl(okClient, jsonSerializer)
                val usersRepository: UsersRepository =
                    UsersRepositoryImpl(userApi, userStore, converterData)
                val converterDomain: Converter<UserStore, UserDomain> =
                    UserStoreToUserDomainConverter()
                val usersInteractor = UsersInteractor(usersRepository, converterDomain)
                val schedulersProvider: SchedulersProvider = SchedulersProviderImpl()
                val converterModel: Converter<UserDomain, UserModel> =
                    UserDomainToUserModelConverter()
                val localViewModel =
                    UsersViewModel(usersInteractor, schedulersProvider, converterModel)
                return localViewModel as T
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
        viewModel.observeUsers().observe(viewLifecycleOwner, adapter::submitItems)

        viewModel.loadUsers()
    }

}