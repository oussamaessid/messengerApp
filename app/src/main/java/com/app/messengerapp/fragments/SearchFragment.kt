package com.app.messengerapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.messengerapp.AdapterClasses.UserAdapter
import com.app.messengerapp.ModelClasses.Users
import com.app.messengerapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {


    private var userAdapter: UserAdapter?= null
    private var mUsers: List<Users>?= null
    private var  recyclerView : RecyclerView?= null
    private var  searchUsersET : EditText?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)



        recyclerView= view.findViewById(R.id.searchList)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager= LinearLayoutManager(context)
        searchUsersET= view.findViewById(R.id.searchUsersET)

         mUsers = ArrayList()
        retrieveAllUsers()


        searchUsersET!!.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(cs: CharSequence?, start: Int, before: Int, count: Int) {
                searchForUsers(cs.toString().toLowerCase())
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        })

            return view
    }

    private fun retrieveAllUsers() {
        var firebaseUserID = FirebaseAuth.getInstance().currentUser!!.uid
        var refUsers= FirebaseDatabase.getInstance().reference.child("users").child(firebaseUserID)

        refUsers.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                (mUsers as ArrayList<Users>).clear()
               if (searchUsersET!!.text.toString() ==""){
                   for (snapshot in p0.children ){
                       val user: Users?= snapshot.getValue(Users::class.java)
                       if (!(user!!.getUID()).equals(firebaseUserID)){
                           (mUsers as ArrayList<Users>).add(user)
                       }
                   }
                   userAdapter= UserAdapter(context!!,mUsers!!,false)
                   recyclerView!!.adapter= userAdapter
               }
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })
    }



    private fun searchForUsers(str: String){
        var firebaseUserID = FirebaseAuth.getInstance().currentUser!!.uid
        var queryUsers= FirebaseDatabase.getInstance().reference
            .child("users").orderByChild("search").startAt(str)
            .endAt(str + "\uf8ff")

        queryUsers.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                (mUsers as ArrayList<Users>).clear()
                for (snapshot in p0.children ){
                    val user: Users?= snapshot.getValue(Users::class.java)
                    if (!(user!!.getUID()).equals(firebaseUserID)){
                        (mUsers as ArrayList<Users>).add(user)
                    }
                }
                userAdapter= UserAdapter(context!!,mUsers!!,false)
                recyclerView!!.adapter= userAdapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }


        })
    }

}
