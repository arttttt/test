package com.arttttt.profirumvp.presenter.users

import com.arttttt.profirumvp.model.user.base.UsersRepository

class UsersPresenter(private val view: UsersContract.View,
                     private val usersRepository: UsersRepository):
    UsersContract.Presenter {

    override fun getUsers() {
        view.showLoadingIndicator(true)
        usersRepository
            .getUsers({
                view.showLoadingIndicator(false)
                view.showUsers(it)
            }, {
                view.showLoadingIndicator(false)
                view.showErrorMessage(it)
            })
    }

    override fun openUserPhoto(sharedViewId: Int, position: Int,  url: String) {
        view.startPhotoActivity(sharedViewId, position, url)
    }
}