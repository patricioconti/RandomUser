package com.example.randomuser.viewmodels


import androidx.lifecycle.*
import com.example.randomuser.data.network.response.asDomainModel
import com.example.randomuser.data.repository.RandomUserRepository
import com.example.randomuser.domain.RandomUser
import kotlinx.coroutines.launch

enum class RandomUserApiStatus { LOADING, ERROR, DONE }

class RandomUserViewModel(private val randomUserRepository: RandomUserRepository) : ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<RandomUserApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<RandomUserApiStatus> = _status

    // MutableLiveData which stores a single RandomUser object
    private val _randomUser = MutableLiveData<RandomUser>()

    // The external immutable LiveData for randomUser
    val randomUser: LiveData<RandomUser> = _randomUser

    /**
     * Call getUserFromRepository() on init so we can display status immediately.
     */
    init {
        getUserFromRepository()
    }

    fun getUserFromRepository() {

        //Use try and catch to handle exception
        viewModelScope.launch {
            _status.value = RandomUserApiStatus.LOADING
            try {
                //RemoteSource answers with a list of results. But always returns only one result so we take
                //first item from the list.
                _randomUser.value = randomUserRepository.getUser().asDomainModel().first()
                _status.value = RandomUserApiStatus.DONE
            } catch (e: Exception) {
                _status.value = RandomUserApiStatus.ERROR
            }
        }
    }


}


class RandomUserViewModelFactory(private val randomUserRepository: RandomUserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RandomUserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            RandomUserViewModel(randomUserRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}