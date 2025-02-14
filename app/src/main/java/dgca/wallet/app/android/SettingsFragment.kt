/*
 *  ---license-start
 *  eu-digital-green-certificates / dgca-verifier-app-android
 *  ---
 *  Copyright (C) 2021 T-Systems International GmbH and all other contributors
 *  ---
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  ---license-end
 *
 *  Created by osarapulov on 5/12/21 2:55 PM
 */

package dgca.wallet.app.android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dgca.wallet.app.android.databinding.FragmentSettingsBinding

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val PRIVACY_POLICY = "https://op.europa.eu/en/web/about-us/legal-notices/eu-mobile-apps"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)

        binding.privacyInformation.setOnClickListener { launchWebIntent() }
        binding.licenses.setOnClickListener { openLicenses() }
        binding.version.text = getString(R.string.version, BuildConfig.VERSION_NAME)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.settings).isVisible = false
    }

    private fun launchWebIntent() {
        val page = Uri.parse(PRIVACY_POLICY)
        val intent = Intent(Intent.ACTION_VIEW, page)

        if (intent.resolveActivity(requireContext().packageManager) == null) {
            return
        }
        requireContext().startActivity(intent)
    }

    private fun openLicenses() {
        val intent = Intent(requireContext(), LicensesActivity::class.java)
        requireContext().apply {
            startActivity(intent)
        }
    }
}