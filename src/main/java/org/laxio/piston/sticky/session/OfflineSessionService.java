/*-
 * ========================LICENSE_START========================
 * StickyPiston
 * %%
 * Copyright (C) 2017 - 2018 Laxio
 * %%
 * This file is part of Piston, licensed under the MIT License (MIT).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * ========================LICENSE_END========================
 */
package org.laxio.piston.sticky.session;

import org.laxio.piston.piston.session.MinecraftSessionService;
import org.laxio.piston.piston.session.Profile;
import org.laxio.piston.piston.session.SessionResponse;

import java.nio.charset.Charset;
import java.util.UUID;

public class OfflineSessionService implements MinecraftSessionService {

    @Override
    public SessionResponse hasJoined(Profile profile, String serverId) {
        return hasJoined(profile, serverId, null);
    }

    @Override
    public SessionResponse hasJoined(Profile profile, String serverId, String ip) {
        String name = profile.getName();
        String offline = "OfflinePlayer:" + name;
        byte[] bytes = offline.getBytes(Charset.forName("UTF-8"));

        return new SessionResponse(name, UUID.nameUUIDFromBytes(bytes));
    }

}
