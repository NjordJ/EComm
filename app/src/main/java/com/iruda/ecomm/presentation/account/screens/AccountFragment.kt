package com.iruda.ecomm.presentation.account.screens

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.iruda.ecomm.R
import com.iruda.ecomm.databinding.FragmentAccountBinding
import com.iruda.ecomm.presentation.account.viewmodels.AccountViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment(), MenuProvider {

    private val args: AccountFragmentArgs by navArgs()

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
            launchLoginFragment()
        }

        binding.imageButtonLogout.setOnClickListener {
            viewModel.logOutFromAccount()
            setVisibilityForAccountFragment()
        }

        lifecycleScope.launch {
            val user = viewModel.getUser().first()
            if (user.isAuthorized) {
                setVisibilityForLoginFragment()
            }
        }
    }

    private fun setVisibilityForAccountFragment() {
        binding.apply {
            cardLogin.visibility = View.VISIBLE
            imageButtonLogout.visibility = View.INVISIBLE
        }
    }

    private fun setVisibilityForLoginFragment() {
        binding.apply {
            cardLogin.visibility = args.viewVisibility?.get(0) ?: View.GONE
            imageButtonLogout.visibility = args.viewVisibility?.get(1) ?: View.VISIBLE
        }
    }

    private fun launchLoginFragment() {
        val action =
            AccountFragmentDirections.actionAccountFragmentToLoginFragment()

        findNavController().navigate(action)
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