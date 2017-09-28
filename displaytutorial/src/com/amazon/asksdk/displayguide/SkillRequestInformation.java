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

import java.util.HashMap;
import java.util.Map;

/**
 * SkillRequestInformation is a class for storing data parsed from a SpeechletRequestEnvelope by a RequestParser
 * implementation
 */
public class SkillRequestInformation {

    private String intentName;
    private Map<String, Slot> slots = new HashMap<>();
    private String token;
    private RequestType requestType;

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public Map<String, Slot> getSlots() {
        return slots;
    }

    public void setSlots(Map<String, Slot> slots) {
        this.slots = slots;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    enum RequestType {
            INTENT_REQUEST, ELEMENT_SELECTED_REQUEST, LAUNCH_REQUEST
    }
}
