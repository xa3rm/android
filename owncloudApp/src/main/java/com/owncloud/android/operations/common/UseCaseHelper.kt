/**
 * ownCloud Android client application
 *
 * @author Abel García de Prada
 * Copyright (C) 2019 ownCloud GmbH.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2,
 * as published by the Free Software Foundation.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.owncloud.android.operations.common

import com.owncloud.android.domain.UseCaseResult
import com.owncloud.android.domain.authentication.usecases.LoginAsyncUseCase
import com.owncloud.android.domain.server.model.ServerInfo
import com.owncloud.android.domain.server.usecases.CheckPathExistenceAsyncUseCase
import com.owncloud.android.domain.server.usecases.GetServerInfoAsyncUseCase
import com.owncloud.android.domain.user.model.UserInfo
import com.owncloud.android.domain.user.usecases.GetUserInfoAsyncUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

/*
 * Helper to call usecases from java classes.
 * TODO: Remove this and call directly to usecases from ViewModel.
 */
class UseCaseHelper : KoinComponent {
    private val mGetUserInfoAsyncUseCase: GetUserInfoAsyncUseCase by inject()
    private val mCheckPathExistenceAsyncUseCase: CheckPathExistenceAsyncUseCase by inject()
    private val mGetServerInfoAsyncUseCase: GetServerInfoAsyncUseCase by inject()
    private val loginAsyncUseCase: LoginAsyncUseCase by inject()

    fun getUserInfo(): UseCaseResult<UserInfo> = mGetUserInfoAsyncUseCase.execute(Unit)

    fun checkPathExistence(remotePath: String): UseCaseResult<Any> =
        mCheckPathExistenceAsyncUseCase.execute(CheckPathExistenceAsyncUseCase.Params(remotePath, false))

    fun getServerInfo(serverUrl: String): UseCaseResult<ServerInfo> =
        mGetServerInfoAsyncUseCase.execute(GetServerInfoAsyncUseCase.Params(serverPath = serverUrl))

    fun login(serverUrl: String, username: String, password: String) =
        loginAsyncUseCase.execute(
            LoginAsyncUseCase.Params(
                serverPath = serverUrl,
                username = username,
                password = password
            )
        )
}
