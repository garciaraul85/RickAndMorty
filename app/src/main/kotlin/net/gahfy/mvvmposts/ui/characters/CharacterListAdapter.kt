package net.gahfy.mvvmposts.ui.characters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import net.gahfy.mvvmposts.R
import net.gahfy.mvvmposts.databinding.ItemCharacterBinding
import net.gahfy.mvvmposts.model.Character

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    private lateinit var charactersList: List<Character>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCharacterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_character, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(charactersList[position])
    }

    override fun getItemCount(): Int {
        return if (::charactersList.isInitialized) charactersList.size else 0
    }

    fun updateCharacterList(charactersList:List<Character>){
        this.charactersList = charactersList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = CharacterViewModel()

        fun bind(character: Character) {
            viewModel.bind(character)
            binding.viewModel = viewModel
        }
    }

}