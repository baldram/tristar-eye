/*
 * Copyright 2016 Marcin Szałomski
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package pl.org.epf.client.shared.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class UserSettings {

    /**
     * Default row for user settings. In the futer it opens possibility
     * to define user profiles so that for instance user would be able
     * to have different favourites set (let' say: "weekend profile" and
     * "working day profile")
     */
    public static int FIRST_ROW = 1;

    @Id
    private int id;

    private Date lastUpdated;

    // TODO: The @ElementCollection annotation has not yet been implemented in Errai JPA;
    // to try to implement it, and send a pull request to Errai repo (https://developer.jboss.org/thread/248835?start=0&tstart=0)
    // @ElementCollection
    //private Set<Integer> favouriteCameras;

    private String favouriteCameraIds;

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getFavouriteCameraIds() {
        return favouriteCameraIds;
    }

    public void setFavouriteCameraIds(String favouriteCameraIds) {
        this.favouriteCameraIds = favouriteCameraIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
