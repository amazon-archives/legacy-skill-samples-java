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
 * The HandlingListItemSelectionScreen handles touch and intent request events for the instructional screen regarding
 * handling selection of list items.
 */
public class HandlingListItemSelectionScreen extends RequestHandler {
    @Override
    boolean canHandle(final SkillRequestInformation skillRequestInformation) {
        return isIntentOrItemFromTopicList(skillRequestInformation, IntentNames.ITEM_SELECTION_INTENT, "5");
    }

    @Override
    SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {
        String title = "Handling List Item Selection by Voice and Touch";

        String speech = "There are two ways that a user can select an item from one of your list templates. You will "
                + "have to handle both of these in your skill code.  Look to your Echo Show screen to read more.";

        String bodyText = wrapTextInBold("1. User selects an item by voice, by saying either its name or its number: ")
                + LINE_BREAK
                + "You will need to implement a custom intent(s) to handle this." + LINE_BREAK + LINE_BREAK
                + wrapTextInBold("2. User clicks an item: ") + LINE_BREAK
                + "Your skill will receive a Display.ElementSelected request with whatever token you "
                + "assigned to the list item that was clicked.  You can then use this token to send your desired "
                + "response.";

        return new ResponseBuilder()
                .setSpeech(speech)
                .addTemplateDirective(new BodyTemplate1Builder()
                        .setPrimaryText(makeRichText(bodyText))
                        .setTitle(title)
                        .setToken(Tokens.ITEM_SELECTION_SCREEN_TOKEN)
                        .build())
                .build();
    }
}
