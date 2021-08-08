package com.example.backgroundwork.view.users.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.backgroundwork.domain.UsersInteractor
import com.example.backgroundwork.models.converter.Converter
import com.example.backgroundwork.models.domain.UserDomain
import com.example.backgroundwork.models.presentation.UserModel
import com.example.backgroundwork.utils.SchedulersProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable


/**
 * Виьюмодель для пользователей
 *
 * @param usersInteractor логика пользователей
 * @param schedulersProvider шедлеры для переключения потоков
 */
class UsersViewModel(
    private val usersInteractor: UsersInteractor,
    private val schedulersProvider: SchedulersProvider,
    private val converter: Converter<UserDomain, UserModel>
) : ViewModel() {

    private val usersLiveData = MutableLiveData<List<UserModel>>()
    private val errorLiveData = MutableLiveData<Throwable>()
    private val progressLiveData = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    fun observeUsers(): LiveData<List<UserModel>> = usersLiveData

    fun observeErrors(): LiveData<Throwable> = errorLiveData

    /**
     * Зашгрузка списка пользователей
     */
    fun loadUsers() {
        val disposable =
            Single.fromCallable { usersInteractor.getUsers("z") }
                .map { users -> users.map(converter::convert) }
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .doOnSubscribe { progressLiveData.value = true }
                .doFinally { progressLiveData.value = false }
                .subscribe { users, throwable ->
                    users?.let(usersLiveData::setValue)
                    throwable?.let(errorLiveData::setValue)
                }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}