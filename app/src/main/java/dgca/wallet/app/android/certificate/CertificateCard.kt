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
 *  Created by osarapulov on 5/11/21 12:20 AM
 */

package dgca.wallet.app.android.certificate

import dgca.wallet.app.android.data.CertificateModel
import dgca.wallet.app.android.data.local.CertificateEntity
import java.time.LocalDate

data class CertificateCard(
    val certificateId: Int,
    val qrCodeText: String,
    val certificate: CertificateModel,
    val tan: String,
    val dateTaken: LocalDate
) {
    constructor(certificateEntity: CertificateEntity, certificateModel: CertificateModel) : this(
        certificateEntity.id,
        certificateEntity.qrCodeText,
        certificateModel,
        certificateEntity.tan,
        certificateEntity.dateAdded
    )
}