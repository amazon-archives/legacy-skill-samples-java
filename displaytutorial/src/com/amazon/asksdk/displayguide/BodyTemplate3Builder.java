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
import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate3;

/**
 * The BodyTemplate3Builder class provides an external Builder class for 3rd party skill code to
 * construct a BodyTemplate3 template at anytime.
 */
public class BodyTemplate3Builder extends TemplateBuilder<BodyTemplate3Builder> {

    private Image fgImage;
    private BodyTemplate3.TextContent textContent = new BodyTemplate3.TextContent();

    /**
     * Sets the Image field of BodyTemplate3. The Image field of BodyTemplate3 is a foreground image on
     * the left side of the template.
     * @param fgImage an Image object containing a list of ImageInstance sources and an optional content description
     * @return the BodyTemplate1Builder object that the method was called upon with the new Image field
     */
    public BodyTemplate3Builder setFgImage(final Image fgImage) {
        this.fgImage = fgImage;
        return this;
    }

    /**
     * Sets the primaryText field of the TextContent field of BodyTemplate1.
     * @param primaryText a TextField object (either RichText or PlainText) containing the desired primaryText string
     * @return the BodyTemplate1Builder object that the method was called upon with the new primaryText field
     */
    public BodyTemplate3Builder setPrimaryText(final TextField primaryText) {
        this.textContent.setPrimaryText(primaryText);
        return this;
    }

    /**
     * Sets the secondaryText field of the TextContent field of BodyTemplate1.
     * @param secondaryText a TextField object (either RichText or PlainText) containing the desired secondaryText
     *                      string
     * @return the BodyTemplate1Builder object that the method was called upon with the new secondaryText field
     */
    public BodyTemplate3Builder setSecondaryText(final TextField secondaryText) {
        this.textContent.setSecondaryText(secondaryText);
        return this;
    }

    /**
     * Sets the tertiaryText field of the TextContent field of BodyTemplate3.
     * @param tertiaryText a TextField object (either RichText or PlainText) containing the desired tertiaryText string
     * @return the BodyTemplate1Builder object that the method was called upon with the new tertiaryText field
     */
    public BodyTemplate3Builder setTertiaryText(final TextField tertiaryText) {
        this.textContent.setTertiaryText(tertiaryText);
        return this;
    }

    /**
     * Builds a new BodyTemplate3 object with the current attributes of the BodyTemplate3Builder object.
     * @return a newly constructed BodyTemplate2 object
     */
    @Override
    public BodyTemplate3 build() {
        BodyTemplate3 template = new BodyTemplate3();
        template.setTitle(title);
        template.setTextContent(textContent);
        template.setToken(token);
        template.setBackgroundImage(bgImage);
        template.setImage(fgImage);
        template.setBackButtonBehavior(backButtonBehavior);
        return template;
    }
}
