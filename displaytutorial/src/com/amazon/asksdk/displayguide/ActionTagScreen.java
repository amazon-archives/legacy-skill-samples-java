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

import static com.amazon.asksdk.displayguide.TextUtils.LINE_BREAK;
import static com.amazon.asksdk.displayguide.TextUtils.wrapTextInAction;
import static com.amazon.asksdk.displayguide.TextUtils.wrapTextInBold;
import static com.amazon.asksdk.displayguide.TextUtils.escapeXML;
import static com.amazon.asksdk.displayguide.TextUtils.makeRichText;

/**
 * The ActionTagScreen class handles touch and intent request events for the Action Tag Screen
 * which describes to the user how to use an Action tag, and best practices for using an Action
 * tag.
 */
public class ActionTagScreen extends RequestHandler {
    @Override
    boolean canHandle(final SkillRequestInformation skillRequestInformation) {
        return isIntentOrItemFromTopicList(skillRequestInformation, IntentNames.ACTION_TAG_INTENT, "4");
    }

    @Override
    SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {
        String title = "Including actions in rich text elements";
        String shortDescription = "Text that is wrapped in an action tag is selectable by touch on the screen. "
                + "Try clicking the Welcome Screen action above to go back to the Display Interface Guide's welcome "
                + "screen. ";

        String speech = shortDescription + " Look to your Echo Show screen to read more.";

        String bodyText = wrapTextInAction("Welcome Screen", IntentNames.WELCOME_INTENT) + LINE_BREAK + LINE_BREAK
                + shortDescription + LINE_BREAK + LINE_BREAK
                + "Keep actions separate from the rest of the text.  Make sure you also include an utterance that "
                + "matches the text of the action, so that users can say the action text and get the same response "
                + "as clicking the text. Don't display more than three actions per template. If you need to have more "
                + "than three selectable elements, use a list template instead."
                + LINE_BREAK + LINE_BREAK
                + "The format for including an action is: " + LINE_BREAK + LINE_BREAK
                + wrapTextInBold(escapeXML("<action value=\"token\"> Selectable Text</action>")) + LINE_BREAK
                + LINE_BREAK
                + "When a user clicks on the text, your skill will receive an event with a Display.ElementSelected "
                + "request with a token value of \"token\".  You, as the skill developer, can then use the token to map"
                + " the touch interaction to an appropriate response.  You can use your preferred methodology to name "
                + "the tokens.";

        return new ResponseBuilder()
                .setSpeech(speech)
                .addTemplateDirective(new BodyTemplate1Builder()
                        .setPrimaryText(makeRichText(bodyText))
                        .setTitle(title)
                        .setToken(Tokens.ACTION_TAG_SCREEN_TOKEN)
                        .build())
                .build();
    }
}
