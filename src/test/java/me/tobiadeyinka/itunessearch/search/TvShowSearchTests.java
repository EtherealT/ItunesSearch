/*
 *  Copyright 2018 Oluwatobi Adeyinka
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package me.tobiadeyinka.itunessearch.search;

import com.neovisionaries.i18n.CountryCode;

import me.tobiadeyinka.itunessearch.entities.*;
import me.tobiadeyinka.itunessearch.exceptions.MissingRequiredParameterException;

import org.testng.annotations.Test;

/**
 * Tests for all tv show search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 23..
 */
public class TvShowSearchTests extends BaseSearchTest {

    private String searchTerm = "the";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForTvShowWithoutSearchTerm() {
        new TvShowSearch().execute();
    }

    @Test
    public void searchForTvShowWithDefaultParameters() {
        try {
            search = new TvShowSearch().with(searchTerm);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForTvShowUsingEpisodeAttribute() {
        try {
            search = new TvShowSearch()
                    .with(searchTerm)
                    .inAttribute(TvShowAttribute.TV_EPISODE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForTvShowInSpecificStore() {
        try {
            search = new TvShowSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.NG);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForTvShowWithLimit() {
        try {
            int limit = 5;
            search = new TvShowSearch()
                    .with(searchTerm)
                    .withLimit(limit);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForTvShowWithApiVersion1() {
        try {
            search = new TvShowSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForTvShowWithJapaneseResponse() {
        try {
            search = new TvShowSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForTvShowWithEpisodeReturnType() {
        try {
            search = new TvShowSearch()
                    .with(searchTerm)
                    .inAttribute(TvShowAttribute.TV_EPISODE)
                    .andReturn(TvShowSearchReturnType.EPISODE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForTvShowWithoutExplicitContent() {
        try {
            search = new TvShowSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void comprehensiveTvShowSearch() {
        try {
            int limit = 5;
            search = new TvShowSearch()
                    .with(searchTerm)
                    .withLimit(limit)
                    .inCountry(CountryCode.US)
                    .inAttribute(TvShowAttribute.TV_EPISODE)
                    .withReturnLanguage(ReturnLanguage.JAPANESE)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logUrlAndResponse(); }
    }
    
}
