package com.example.mobilewalletanalytics.presentation.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.mobilewalletanalytics.data.models.Transaction
import com.example.mobilewalletanalytics.data.models.TransactionDashboard
import com.example.mobilewalletanalytics.data.remote_interfaces.RemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val remoteRepo: RemoteRepo) : ViewModel() {

    private val _userIdLiveData = MutableLiveData<Int>()

    private val _postIdLiveData = MutableLiveData<Int>()

    private val _albumIdLiveData = MutableLiveData<Int>()

//    private val _addPostResponseLiveData = MutableLiveData<Event<String>>()
//
//    val addPostResponseLiveData: LiveData<Event<String>> get() = _addPostResponseLiveData

    //LiveData for users
    /**
     * All transactions history are fetched from here. There's no pagination here since the users are few(10)
     */
    private var _transactionsHistoryLiveData = MutableLiveData<List<Transaction>>()
    private var _transactionsDashoardData = MutableLiveData<TransactionDashboard>()

    val usersLiveData: LiveData<List<Transaction>> get() = _transactionsHistoryLiveData
    val dashboardLiveData: LiveData<TransactionDashboard> get() = _transactionsDashoardData
    init {
        viewModelScope.launch {
            _transactionsHistoryLiveData.value = remoteRepo.fetchTransactionsHistory("2023-10-01 08:33:57", "2023-10-01 11:29:55")
            _transactionsDashoardData.value = remoteRepo.fetchDashboardStatistics()
        }
    }

    //LiveData for posts
    /**
     * Fetch a user's posts when the [_userIdLiveData]'s value changes
     */
//    private var _postsLiveData = _userIdLiveData.switchMap {
//        remoteRepo.fetchPostsByUser(it).cachedIn(viewModelScope)
//    }
//    val postsLiveData: LiveData<PagingData<Post>> get() = _postsLiveData

    //LiveData for albums
    /**
     * Fetch a user's albums when [_userIdLiveData]'s value changes
     */
//    private var _albumsLiveData = _userIdLiveData.switchMap {
//        remoteRepo.fetchAlbumsByUser(it).cachedIn(viewModelScope)
//    }
//    val albumsLiveData: LiveData<PagingData<Album>> get() = _albumsLiveData

    //LiveData for photos
    /**
     * Fetch the photos in an album when the [_albumIdLiveData]'s value changes
     */
//    private var _photosLiveData = _albumIdLiveData.switchMap {
//        remoteRepo.fetchPhotosByUser(it).cachedIn(viewModelScope)
//    }
//    val photosLiveData: LiveData<PagingData<Photo>> get() = _photosLiveData
//
//    //LiveData for comments
//    /**
//     * Fetch the comments on a post when the [_postIdLiveData]'s value changes
//     */
//    private val _commentsLiveData = _postIdLiveData.switchMap {
//        remoteRepo.fetchCommentsByPost(it).cachedIn(viewModelScope)
//    }
//    val commentsLiveData: LiveData<PagingData<Comment>> get() = _commentsLiveData

    /**
     * Post new user
     */
//    fun postNewPost(title: String, body: String){
//
//        if (title.trim().isNotBlank() && body.trim().isNotBlank()){
//            val post = Post(
//                userId = 1,
//                id = 2500,
//                title = title,
//                body = body
//            )
//            viewModelScope.launch {
//                val postResponse = remoteRepo.postNewPost(post)
//                _addPostResponseLiveData.postValue(Event("Post has been added successfully"))
//            }
//        }else {
//            _addPostResponseLiveData.value = Event("All fields are required")
//        }
//
//    }

    /**
     * Updates the [_userIdLiveData] value only if there's a differing change
     */
    fun updateUserId(newUserId: Int){
        if(newUserId != _userIdLiveData.value){
            _userIdLiveData.value = newUserId
        }
    }

    /**
     * Updates the [_postIdLiveData] value only if there's a differing change
     */
    fun updatePostId(newPostId: Int){
        if(newPostId != _postIdLiveData.value){
            _postIdLiveData.value = newPostId
        }
    }

    /**
     * Updates the [_albumIdLiveData] value only if there's a differing change
     */
    fun updateAlbumId(newAlbumId: Int){
        if(newAlbumId != _albumIdLiveData.value){
            _albumIdLiveData.value = newAlbumId
        }
    }

}