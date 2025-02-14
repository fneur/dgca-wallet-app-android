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
 *  Created by mykhailo.nester on 5/11/21 11:13 PM
 */

package dgca.wallet.app.android.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ClaimRequest(

    @JsonProperty("DGCI")
    val dgci: String? = null,

    @JsonProperty("certhash")
    val certHash: String? = null,

    @JsonProperty("TANHash")
    val tanHash: String? = null,

    val publicKey: PublicKeyData? = null,
    val sigAlg: String? = null,
    val signature: String? = null
)