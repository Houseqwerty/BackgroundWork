package com.example.backgroundwork.view.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.backgroundwork.domain.UsersInteractor
import com.example.backgroundwork.models.presentation.UserModel
import com.example.backgroundwork.utils.SchedulersProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


class UsersViewModel(
    private val usersInteractor: UsersInteractor,
    private val schedulers: SchedulersProvider
) : AppViewModel() {

    private val users = MutableLiveData<List<UserModel>>()
    private val showProgress = MutableLiveData<Boolean>()
    private val errors = MutableLiveData<Throwable>()


    fun loadUsers() {
        Single.fromCallable { usersInteractor.getUsers() }
            .map { it.map { userDomain -> UserModel(userDomain.login, userDomain.avatarUrl) } }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doFinally { showProgress.value = false }
            .doOnSubscribe { showProgress.value = true }
            .subscribe(users::setValue, errors::setValue)
            .addTo(compositeDisposable)
    }

    fun users(): LiveData<List<UserModel>> = users

    fun progress(): LiveData<Boolean> = showProgress

    fun errors(): LiveData<Throwable> = errors

}

class UserViewModel(
    private val usersInteractor: UsersInteractor,
    private val schedulers: SchedulersProvider
) : AppViewModel() {

    init {
        loadUser()
    }


    private fun loadUser() {
        Single.fromCallable { usersInteractor.getUser("brynary") }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe()
            .addTo(compositeDisposable)
    }

}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)

open class AppViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}