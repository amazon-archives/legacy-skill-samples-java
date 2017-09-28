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

import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;

import java.util.Map;

public abstract class RequestHandler {

    abstract boolean canHandle(final SkillRequestInformation skillRequestInformation);
    abstract SpeechletResponse handle(final SkillRequestInformation skillRequestInformation);

    protected boolean slotsContainNumberSlotWithValue(Map<String, Slot> slots, String value) {
        Slot slot = slots.get(SlotNames.NUMBER_SLOT_NAME);
        return (slot != null) && slot.getValue().equals(value);
    }

    protected boolean isIntentOrItemFromTopicList(final SkillRequestInformation skillRequestInformation, String intentName, String topicListIndex) {
        return intentName.equals(skillRequestInformation.getIntentName()) || IntentNames.SELECT_ITEM_INTENT.equals(skillRequestInformation.getIntentName())
                && Tokens.TOPICS_LIST_SCREEN_TOKEN.equals(skillRequestInformation.getToken())
                && slotsContainNumberSlotWithValue(skillRequestInformation.getSlots(), topicListIndex);
    }

}
