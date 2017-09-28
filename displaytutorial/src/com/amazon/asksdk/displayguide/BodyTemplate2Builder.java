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
import com.amazon.speech.speechlet.interfaces.display.element.TextField;
import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate2;

/**
 * The BodyTemplate2Builder class provides an external Builder class for 3rd party skill code to
 * construct a BodyTemplate2 template at anytime.
 */
public class BodyTemplate2Builder extends TemplateBuilder<BodyTemplate2Builder> {

    private Image fgImage;
    private BodyTemplate2.TextContent textContent = new BodyTemplate2.TextContent();

    /**
     * Sets the Image field of BodyTemplate2. The Image field of BodyTemplate2 is a foreground image on
     * the right side of the template.
     * @param fgImage an Image object containing a list of ImageInstance sources and an optional content description
     * @return the BodyTemplate2Builder object that the method was called upon with the new Image field
     */
    public BodyTemplate2Builder setFgImage(final Image fgImage) {
        this.fgImage = fgImage;
        return this;
    }

    /**
     * Sets the primaryText field of the TextContent field of BodyTemplate2.
     * @param primaryText a TextField object (either RichText or PlainText) containing the desired primaryText string
     * @return the BodyTemplate2Builder object that the method was called upon with the new primaryText field
     */
    public BodyTemplate2Builder setPrimaryText(final TextField primaryText) {
        this.textContent.setPrimaryText(primaryText);
        return this;
    }

    /**
     * Sets the secondaryText field of the TextContent field of BodyTemplate2.
     * @param secondaryText a TextField object (either RichText or PlainText) containing the desired secondaryText
     *                      string
     * @return the BodyTemplate2Builder object that the method was called upon with the new secondaryText field
     */
    public BodyTemplate2Builder setSecondaryText(final TextField secondaryText) {
        this.textContent.setSecondaryText(secondaryText);
        return this;
    }

    /**
     * Sets the tertiaryText field of the TextContent field of BodyTemplate2.
     * @param tertiaryText a TextField object (either RichText or PlainText) containing the desired tertiaryText string
     * @return the BodyTemplate1Builder object that the method was called upon with the new tertiaryText field
     */
    public BodyTemplate2Builder setTertiaryText(final TextField tertiaryText) {
        this.textContent.setTertiaryText(tertiaryText);
        return this;
    }

    /**
     * Builds a new BodyTemplate2 object with the current attributes of the BodyTemplate2Builder object.
     * @return a newly constructed BodyTemplate2 object
     */
    @Override
    public BodyTemplate2 build() {
        BodyTemplate2 template = new BodyTemplate2();
        template.setToken(token);
        template.setTitle(title);
        template.setImage(fgImage);
        template.setBackgroundImage(bgImage);
        template.setTextContent(textContent);
        template.setBackButtonBehavior(backButtonBehavior);
        return template;
    }
}
