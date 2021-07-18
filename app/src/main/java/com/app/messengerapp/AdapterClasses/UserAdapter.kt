package com.app.messengerapp.AdapterClasses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.app.messengerapp.ModelClasses.Users
import com.app.messengerapp.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*

class UserAdapter(
    mContext: Context ,
    mUsers: List<Users>,
    isChatCheck: Boolean
    ):RecyclerView.Adapter<UserAdapter.ViewHolder?>()
{
    private val mContext: Context
    private  val mUsers: List<Users>
    private val isChatCheck: Boolean

    init {
        this.mUsers=mUsers
        this.mContext=mContext
        this.isChatCheck=isChatCheck

    }


    override fun onCreateViewHolder(ViewGroup: ViewGroup, viewType: Int): ViewHolder {
       val view: View = LayoutInflater.from(mContext).inflate(R.layout.user_search_item_layout,
           ViewGroup,false)
        return UserAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }


    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val user: Users= mUsers[i]

        holder.userNameTxt.text = user!!.getUesrName()
        Picasso.get().load(user?.getProfile()).placeholder(R.drawable.profile).into(holder.profileImageView)
    }


    class ViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){

        var userNameTxt : TextView
        var profileImageView : CircleImageView
        var onlineImageView : CircleImageView
        var offlineImageView : CircleImageView
        var lastMessageTxt : TextView

        init {
            userNameTxt = itemView.findViewById(R.id.username)
            profileImageView = itemView.findViewById(R.id.profile_image)
            onlineImageView = itemView.findViewById(R.id.image_online)
            offlineImageView = itemView.findViewById(R.id.image_offline)
            lastMessageTxt = itemView.findViewById(R.id.message_last)
        }
    }



}