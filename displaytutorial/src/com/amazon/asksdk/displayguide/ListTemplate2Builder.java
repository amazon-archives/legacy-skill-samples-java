/*
 *Copyright 2016-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *Licensed under the Amazon Software License (the "License"). You may not use
 * this file except in compliance with the License. A copy of the License is located at
 *
 *http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amazon.asksdk.displayguide;

import com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2;

import java.util.List;

/**
 * The ListTemplate2Builder class provides an external Builder class for 3rd party skill code to
 * construct a ListTemplate2 template at anytime.
 */
public class ListTemplate2Builder extends TemplateBuilder<ListTemplate2Builder> {

    private List<ListTemplate2.ListItem> items;

    /**
     * Sets the List of items for ListTemplate2
     * @param items a List of type ListTemplate2.ListItem
     * @return the ListTemplate2Builder object that the method was called upon with the new list of items
     */
    public ListTemplate2Builder setListItems(List<ListTemplate2.ListItem> items) {
        this.items = items;
        return this;
    }

    /**
     * Builds a new ListTemplate2 object with the current attributes of the ListTemplate2Builder object
     * @return a newly constructed ListTemplate2 object
     */
    @Override
    public ListTemplate2 build() {
        ListTemplate2 template = new ListTemplate2();
        template.setToken(token);
        template.setTitle(title);
        template.setBackgroundImage(bgImage);
        template.setListItems(items);
        template.setBackButtonBehavior(backButtonBehavior);
        return template;
    }
}
