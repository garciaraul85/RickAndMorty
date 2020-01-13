package net.gahfy.mvvmposts.ui.characters

import android.arch.lifecycle.MutableLiveData
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.gahfy.mvvmposts.R
import net.gahfy.mvvmposts.base.BaseViewModel
import net.gahfy.mvvmposts.model.Character
import net.gahfy.mvvmposts.model.CharacterDAO
import net.gahfy.mvvmposts.network.CharacterAPI
import javax.inject.Inject

class CharacterListViewModel(private val characterDAO: CharacterDAO) : BaseViewModel() {

    @Inject
    lateinit var characterAPI: CharacterAPI

    val characterListAdapter = CharacterListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCharacters() }

    private lateinit var subscription: Disposable

    init {
        loadCharacters()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadCharacters() {
        subscription = Observable.fromCallable { characterDAO.all }
                .concatMap {
                    dbCharacterList ->
                    if (dbCharacterList.isEmpty()) {
                        characterAPI.getAllCharacters().concatMap { response ->
                            characterDAO.insertAll(*response.results.toTypedArray())
                            Observable.just(response.results.toTypedArray())
                        }
                    } else {
                        Observable.just(dbCharacterList)
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveCharacterListStart() }
                .doOnTerminate { onRetrieveCharacterListFinish() }
                .subscribe(
                        { result -> onRetrieveCharacterListSuccess(result as List<Character>) },
                        { onRetrievePostListError(it) }
                )
    }

    private fun onRetrieveCharacterListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCharacterListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCharacterListSuccess(postList:List<Character>) {
        characterListAdapter.updateCharacterList(postList)
    }

    private fun onRetrievePostListError(throwable: Throwable) {
        throwable.printStackTrace()
        errorMessage.value = R.string.post_error
    }
}