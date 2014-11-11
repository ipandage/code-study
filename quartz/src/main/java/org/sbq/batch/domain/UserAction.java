/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sbq.batch.domain;

import java.util.Date;

/**
 * @author ilya40umov
 */
public class UserAction
{
    private int userActionId;
    private int userSessionId;
    private ActionType action;
    private Date created;

    public UserAction()
    {

    }

    public UserAction(int userSessionId, ActionType action, Date created)
    {
        this.userSessionId = userSessionId;
        this.action = action;
        this.created = created;
    }

    public int getUserActionId()
    {
        return userActionId;
    }

    public void setUserActionId(int userActionId)
    {
        this.userActionId = userActionId;
    }

    public int getUserSessionId()
    {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId)
    {
        this.userSessionId = userSessionId;
    }

    public ActionType getAction()
    {
        return action;
    }

    public void setAction(ActionType action)
    {
        this.action = action;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public static enum ActionType
    {
        DO_LOGIN, DO_LOGOUT, GO_WALKING, GO_CHATTING, GO_DANCING, GO_IDLE
    }

}
