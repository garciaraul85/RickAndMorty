package net.gahfy.mvvmposts.ui.characters

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import net.gahfy.mvvmposts.R
import net.gahfy.mvvmposts.databinding.ActivityCharacterListBinding
import net.gahfy.mvvmposts.injection.ViewModelFactory

class CharacterListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterListBinding
    private lateinit var viewModel: CharacterListViewModel
    private var errorSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_list)
        binding.characterList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(CharacterListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {errorMessage ->
            if (errorMessage != null) {
                showError(errorMessage)
            } else {
                hideError()
            }
        })
        binding.viewModel = viewModel
    }

    private fun hideError() {
        errorSnackBar?.dismiss()
    }

    private fun showError(errorMessage: Int) {
        errorSnackBar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackBar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackBar?.show()
    }
}