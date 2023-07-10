package amr_handheld.network



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.databse.PostDatabase
import com.example.taskapp.repo.Repository
import com.example.taskapp.viewmodels.PostsViewModel

class MyViewModelFactory constructor(private val repository: Repository, private val  postDatabase: PostDatabase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            PostsViewModel(this.repository,this.postDatabase) as T
        }
        else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}