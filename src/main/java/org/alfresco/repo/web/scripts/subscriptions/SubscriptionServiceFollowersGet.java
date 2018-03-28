/*
 * #%L
 * Alfresco Remote API
 * %%
 * Copyright (C) 2005 - 2016 Alfresco Software Limited
 * %%
 * This file is part of the Alfresco software. 
 * If the software was purchased under a paid Alfresco license, the terms of 
 * the paid license agreement will prevail.  Otherwise, the software is 
 * provided under the following open source license terms:
 * 
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package org.alfresco.repo.web.scripts.subscriptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.alfresco.service.cmr.subscriptions.PagingFollowingResults;
import org.alfresco.util.json.jackson.AlfrescoDefaultObjectMapper;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

public class SubscriptionServiceFollowersGet extends AbstractSubscriptionServiceWebScript
{
    public JsonNode executeImpl(String userId, WebScriptRequest req, WebScriptResponse res)
    {
        PagingFollowingResults result = subscriptionService.getFollowers(userId, createPagingRequest(req));

        ObjectNode obj = AlfrescoDefaultObjectMapper.createObjectNode();
        obj.set("people", getUserArray(result.getPage()));
        obj.put("hasMoreItems", result.hasMoreItems());
        if (result.getTotalResultCount() != null)
        {
            obj.put("totalCount", result.getTotalResultCount().getFirst());
        }

        return obj;
    }
}
