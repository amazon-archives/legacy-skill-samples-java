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

import com.amazon.speech.speechlet.SpeechletResponse;

import static com.amazon.asksdk.displayguide.TextUtils.*;

/**
 * The HintTextScreen class handles all requests for information about Hint Texts, and demonstrates to the user how to
 * use a hint phrase.
 */
public class HintTextsScreen extends RequestHandler {
    @Override
    boolean canHandle(final SkillRequestInformation skillRequestInformation) {
        return isIntentOrItemFromTopicList(skillRequestInformation, IntentNames.HINTS_INTENT, "6");
    }

    @Override
    SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {
        String shortDescription = "Hint phrases are optional, but a useful tool to help your user quickly understand "
                + "what to do next.  List Template 2 and Body Templates 2 and 6 support hint texts. ";

        String speechText = shortDescription + " Look to your Echo Show screen to read more.";

        String bodyText = wrapTextInMediumFont(shortDescription + LINE_BREAK + LINE_BREAK
                + " To add a hint to one of your templates, send a HintDirective along with "
                + "the TemplateDirective in your skill's response. Hints are automatically formatted like this: "
                + LINE_BREAK
                + wrapTextInItalics(wrapTextInBold(escapeXML("Try, <wake-word>, <your-hint-string>")))
                + LINE_BREAK
                + "This allows the device to change the wake word in the hint phrase, if the user is using one other "
                + "than the default. " + LINE_BREAK + LINE_BREAK
                + "Populate the hint field wherever possible. When you ask a question and listen for the user's "
                + "response, the blue voice chrome at the bottom of the screen partially blocks the hint. Because of "
                + "this, it's best not to add hints when asking a question; only use hints when the screen is static."
                + wrapTextInAction(".", "token"));

        String hintText = "what is a hint?";

       return new ResponseBuilder()
               .setShouldEndSession(null)
               .setSpeech(speechText)
               .addHintDirective(hintText)
               .addTemplateDirective(
                       new BodyTemplate6Builder()
                               .setPrimaryText(makeRichText(bodyText))
                               .setToken(Tokens.HINTS_SCREEN_TOKEN)
                               .build()
                )
                .build();
    }
}
