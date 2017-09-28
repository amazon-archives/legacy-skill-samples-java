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

import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.template.Template;

/**
 * An abstract base class for all TemplateBuilders containing setters for all attributes common among all templates
 * @param <T> a derived class of TemplateBuilder - such as BodyTemplate1Builder
 */
public abstract class TemplateBuilder<T extends TemplateBuilder<T>> {
    protected String title;
    protected String token;
    protected Image bgImage = ImageResources.BACKGROUND.getImage();
    protected Template.BackButtonBehavior backButtonBehavior = Template.BackButtonBehavior.HIDDEN;

    /**
     * Sets the title field of the TemplateBuilder
     * @param title a String containing the desired title
     * @return the TemplateBuilder object that the method was called upon with the new title field
     */
    public T setTitle(String title) {
        this.title = title;
        return (T)this;
    }

    /**
     * Sets the token field  of the TemplateBuilder
     * @param token a String containing the desired token
     * @return the TemplateBuilder object that the method was called upon with the new token field
     */
    public T setToken(String token) {
        this.token = token;
        return (T)this;
    }

    /**
     * Sets the bgImage field of the TemplateBuilder
     * @param image an Image object containing a list of ImageInstance sources and an optional content description
     * @return the TemplateBuilder that the method was called upon with the new image field
     */
    public T setBgImage(Image image) {
        this.bgImage = image;
        return (T)this;
    }

    /**
     * Sets the backButtonBehavior field of the TemplateBuilder
     * @param backButtonBehavior a Template.BackButtonBehavior enum value - etiher HIDDEN or VISIBLE, the default is HIDDEN
     * @return the TemplateBuilder that the method was called upon with the new backButtonBehavior field
     */
    public T setBackButtonBehavior(Template.BackButtonBehavior backButtonBehavior) {
        this.backButtonBehavior = backButtonBehavior;
        return (T)this;
    }

    /**
     * Builds a new Template object based on the type of derived concrete class
     * @return a newly constructed Template object
     */
    public abstract Template build();
}
