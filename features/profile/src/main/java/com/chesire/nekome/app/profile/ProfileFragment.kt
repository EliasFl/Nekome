package com.chesire.nekome.app.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.api.load
import coil.transform.CircleCropTransformation
import com.chesire.lifecyklelog.LogLifecykle
import com.chesire.nekome.app.profile.databinding.FragmentProfileBinding
import com.chesire.nekome.core.models.UserModel
import com.chesire.nekome.core.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Fragment to display to the user information about their profile.
 */
@LogLifecykle
class ProfileFragment : DaggerFragment(R.layout.fragment_profile) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<ProfileViewModel> { viewModelFactory }
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = requireNotNull(_binding) { "Binding not set" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        viewModel.user.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    setImagery(it)
                }
            }
        )
    }

    private fun setImagery(user: UserModel) {
        binding.profileAvatar.load(user.avatar.largest?.url) {
            transformations(CircleCropTransformation())
        }
    }
}
