package com.arttttt.profirumvp.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arttttt.profirumvp.R
import com.arttttt.profirumvp.model.photo.base.PhotoRepository
import com.arttttt.profirumvp.presenter.usersadapter.UsersAdapterContract
import com.arttttt.profirumvp.presenter.usersadapter.UsersAdapterPresenter

class UsersAdapter(repository: PhotoRepository,
                   private val itemClick: (url: String, sharedViewId: Int, position: Int) -> Unit)
    : RecyclerView.Adapter<UsersViewHolder>(), UsersAdapterContract.View {

    val presenter: UsersAdapterContract.Presenter = UsersAdapterPresenter(this, repository)

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.user_item, parent, false)

        return UsersViewHolder(view) { position, sharedViewId ->
            val user = presenter.getItemAt(position)
            itemClick(user.photoUrl, sharedViewId, position)
        }
    }

    override fun getItemCount() = presenter.getUsersCount()

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) = presenter.bind(position, holder)
}