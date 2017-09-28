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

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.interfaces.display.request.ElementSelectedRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * The ElementSelectedRequestParser class is responsible for parsing information from the SpeechletRequestEnvelope
 * that is unique to an ElementSelecteRequest.
 */
public class ElementSelectedRequestParser implements RequestParser {

    /**
     * parses the token string in the ElementSelectedRequest into an IntentName and Slots -- this is a token naming
     * methodology, unique to the DisplayGuide skill, that allows for touch events and vocal intent requests to be
     * interpreted the same way by the derived classes of RequestHandler.
     * @param envelope SpeechletRequestEnvelope containing an ElementSelectedRequest
     * @return a SkillRequestInformation object containing the request type, the original token, and the intentName and
     * Slots parsed from the token
     */
    @Override
    public SkillRequestInformation parseRequestEnvelope(final SpeechletRequestEnvelope envelope) {

        // Parse the envelope for token
        ElementSelectedRequest request = (ElementSelectedRequest) envelope.getRequest();
        String token = request.getToken();

        //parse token for intentName and Slots
        String[] tokenContents = token.split("\\|");
        String intentName = tokenContents[0];

        Map<String, Slot> slots = new HashMap<>();
        for (String tokenSubString : tokenContents) {
            if (tokenSubString.equals(tokenContents[0])) {
                continue;
            }
            String[] slot = tokenSubString.split(",");
            if (slot.length == 2) {
                String slotName = slot[0];
                String slotValue = slot[1];

                slots.put(slotName,
                        Slot.builder()
                                .withValue(slotValue)
                                .withName(slotName)
                                .build());
            }
        }

        SkillRequestInformation skillRequestInformation = new SkillRequestInformation();
        skillRequestInformation.setRequestType(SkillRequestInformation.RequestType.ELEMENT_SELECTED_REQUEST);
        skillRequestInformation.setToken(token);
        skillRequestInformation.setIntentName(intentName);
        skillRequestInformation.setSlots(slots);

        return  skillRequestInformation;
    }
}
