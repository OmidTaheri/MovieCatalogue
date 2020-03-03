/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package ir.satintech.filmbaz.ui.search;


import java.util.List;

import ir.satintech.filmbaz.data.network.model.Movie;
import ir.satintech.filmbaz.ui.base.MvpView;





public interface SearchMvpView extends MvpView {


    void setupListofSearch(List<Movie> items_list, String query);


    void sucssed_load_first_page(List<Movie> list);

    void error_load_first_page(int message, String query);

    void sucssed_load_next_page(List<Movie> list);

    void error_load_next_page(String query, int page, int message);


    void SetTotalPage(int total_page);


    void showMovieDetailActivity(int movie_id);

    void visibility_progressBar(boolean show);
}
