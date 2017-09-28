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
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.interfaces.display.DisplayInterface;
import com.amazon.speech.speechlet.interfaces.display.DisplayState;

/**
 * The IntentRequestParser class is responsible for parsing information from the SpeechletRequestEnvelope that is unique
 * to an IntentRequest
 */
public class IntentRequestParser implements RequestParser {

    @Override
    public SkillRequestInformation parseRequestEnvelope(SpeechletRequestEnvelope envelope) {
        IntentRequest request = (IntentRequest) envelope.getRequest();
        Intent intent = request.getIntent();

        SkillRequestInformation skillRequestInformation = new SkillRequestInformation();
        skillRequestInformation.setRequestType(SkillRequestInformation.RequestType.INTENT_REQUEST);
        skillRequestInformation.setIntentName(intent.getName());
        skillRequestInformation.setSlots(intent.getSlots());
        skillRequestInformation.setToken(getDisplayStateToken(envelope));

        return skillRequestInformation;
    }

    private String getDisplayStateToken(SpeechletRequestEnvelope envelope) {
        DisplayState state = envelope.getContext().getState(DisplayInterface.class, DisplayState.class);
        String stateToken = "";
        if (state != null) {
            stateToken = state.getToken();
        }
        return stateToken;
    }
}
