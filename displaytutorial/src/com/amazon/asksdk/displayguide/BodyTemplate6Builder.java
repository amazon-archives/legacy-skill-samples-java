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

import com.amazon.speech.speechlet.interfaces.display.element.TextField;
import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate6;

/**
 * The BodyTemplate6Builder class provides an external Builder class for 3rd party skill code to
 * construct a BodyTemplate6 template at anytime.
 */
public class BodyTemplate6Builder extends TemplateBuilder<BodyTemplate6Builder> {

    private BodyTemplate6.TextContent textContent = new BodyTemplate6.TextContent();

    /**
     * Sets the primaryText field of the TextContent field of BodyTemplate6.
     * @param primaryText a TextField object (either RichText or PlainText) containing the desired primaryText string
     * @return the BodyTemplate6Builder object that the method was called upon with the new primaryText field
     */
    public BodyTemplate6Builder setPrimaryText(final TextField primaryText) {
        this.textContent.setPrimaryText(primaryText);
        return this;
    }

    /**
     * Sets the secondaryText field of the TextContent field of BodyTemplate6.
     * @param secondaryText a TextField object (either RichText or PlainText) containing the desired secondaryText
     *                      string
     * @return the BodyTemplate6Builder object that the method was called upon with the new secondaryText field
     */
     public BodyTemplate6Builder setSecondaryText(final TextField secondaryText) {
        this.textContent.setSecondaryText(secondaryText);
        return this;
    }

    /**
     * Sets the tertiaryText field of the TextContent field of BodyTemplate6.
     * @param tertiaryText a TextField object (either RichText or PlainText) containing the desired tertiaryText string
     * @return the BodyTemplate6Builder object that the method was called upon with the new tertiaryText field
     */
    public BodyTemplate6Builder setTertiaryText(final TextField tertiaryText) {
        this.textContent.setTertiaryText(tertiaryText);
        return this;
    }

    /**
     * Builds a new BodyTemplate6 object with the current attributes of the BodyTemplate6Builder object.
     * @return a newly constructed BodyTemplate6 object
     */
    @Override
    public BodyTemplate6 build() {
        BodyTemplate6 template = new BodyTemplate6();
        template.setTextContent(textContent);
        template.setToken(token);
        template.setBackgroundImage(bgImage);
        template.setBackButtonBehavior(backButtonBehavior);
        return template;
    }

}
