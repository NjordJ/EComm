package com.iruda.ecomm.presentation.account.screens

import android.os.Bundle
import android.view.*
import android.view.View.OnClickListener
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.iruda.ecomm.R
import com.iruda.ecomm.databinding.FragmentAccountBinding
import com.iruda.ecomm.presentation.account.viewmodels.AccountViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment(), MenuProvider {

    private val viewModel by viewModel<AccountViewModel>()

    private var _binding: FragmentAccountBinding? = null
    private val binding: FragmentAccountBinding
        get() = _binding ?: throw  RuntimeException("FragmentAccountBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardLogin.setOnClickListener {
            observeViewModel()
        }

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.authorize()
        }
//        viewModel.getUser().observe(viewLifecycleOwner) {
//            binding.apply {
//                textViewUserName.text = it.userName
//                textViewUserEmail.text = it.email
//            }
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_account, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.notification_action_appbar -> {
                true
            }
            else -> false
        }
    }

}