/*
 * Copyright 2002-2010 the original author or authors
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package org.springsource.samples.service.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.integration.Message;
import org.springframework.integration.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springsource.samples.service.TwitterService;

/**
 * Implementation of the TwitterService interface.
 */
@Service("twitterService")
public class DefaultTwitterService implements TwitterService {

	/** Holds a collection of polled Twitter messages */
	private volatile Map<String, String> twitterMessages;

	/**
	 * Constructor that initializes the 'twitterMessages' Map as a simple LRU
	 * cache. @See http://blogs.oracle.com/swinger/entry/collections_trick_i_lru_cache
	 */
	public DefaultTwitterService() {

		twitterMessages = new LinkedHashMap<String, String>( 10, 0.75f, true ) {

			private static final long serialVersionUID = 1L;

			protected boolean removeEldestEntry( java.util.Map.Entry<String, String> entry ) {
				return size() > 10;
			}

		};

	}

	/** {@inheritDoc} */
	@Override
	public Collection<String> getTwitterMessages() {
		return twitterMessages.values();
	}

    /**
     * Called by Spring Integration to populate a simple LRU cache.
     *
     * @param tweet - The Spring Integration tweet object.
     */
	@Override
    public void addTwitterMessages(Message<String> message) {
        this.twitterMessages.put(message.getHeaders().get(MessageHeaders.TIMESTAMP).toString(), message.getPayload());
    }

}
