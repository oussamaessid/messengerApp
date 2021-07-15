package com.app.messengerapp.ModelClasses

class Users {
    private  var uid :String=""
    private  var uesrname  :String=""
    private  var profile :String=""
    private  var cover  :String=""
    private  var status :String=""
    private  var search  :String=""
    private  var facebook :String=""
    private  var instgram :String=""
    private  var website :String=""

    constructor()
    constructor(
        uid: String,
        uesrname: String,
        profile: String,
        cover: String,
        status: String,
        search: String,
        facebook: String,
        instgram: String,
        website: String
    ) {
        this.uid = uid
        this.uesrname = uesrname
        this.profile = profile
        this.cover = cover
        this.status = status
        this.search = search
        this.facebook = facebook
        this.instgram = instgram
        this.website = website
    }

    fun getUID(): String?{
        return uid
    }
    fun setUID( uid: String){
        this.uid=uid
    }
    fun getUesrName(): String?{
        return uesrname
    }
    fun setUesrName( uesrname: String){
        this.uesrname=uesrname
    }
    fun getProfile(): String?{
        return profile
    }
    fun setProfile( profile: String){
        this.profile=profile
    }
    fun getCover(): String?{
        return cover
    }
    fun setCver( cover: String){
        this.cover=cover
    }
    fun getStatus(): String?{
        return status
    }
    fun setStatus( status: String){
        this.status=status
    }
    fun getSearch(): String?{
        return search
    }
    fun setSearch( search: String){
        this.search=search
    }
    fun getFacebook(): String?{
        return facebook
    }
    fun setFacebook( facebook: String){
        this.facebook=facebook
    }
    fun getInstgram(): String?{
        return instgram
    }
    fun setInstgram( instgram: String){
        this.instgram=instgram
    }
    fun getWebsite(): String?{
        return website
    }
    fun setWebsite( website: String){
        this.website=website
    }


}