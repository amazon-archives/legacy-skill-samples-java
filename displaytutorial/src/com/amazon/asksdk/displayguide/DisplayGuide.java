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
import com.amazon.speech.speechlet.*;
import com.amazon.speech.speechlet.interfaces.display.Display;
import com.amazon.speech.speechlet.interfaces.display.request.ElementSelectedRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The DisplayGuide class implements the SpeechletV2 and Display interfaces and shows how to use the Display features of
 * Echo devices.
 */
public class DisplayGuide implements SpeechletV2, Display {

    private static final Logger LOG = LoggerFactory.getLogger(DisplayGuide.class);

    /**
     * Logs information about the session, and is triggered at the start of a new session.
     *
     * @param envelope SpeechletRequestEnvelope containing the Context, Session, Version, and SessionStartedRequest
     */
    @Override
    public void onSessionStarted(final SpeechletRequestEnvelope<SessionStartedRequest> envelope) {
        LOG.info("onSessionStarted requestId={}, sessionId={}", envelope.getRequest().getRequestId(),
                envelope.getSession().getSessionId());
    }

    /**
     * Creates the SpeechletResponse for the Welcome Screen, and is triggered when handling a speech initiated request
     * to start the skill without providing an Intent. This method is only invoked when Session.isNew() is true.
     *
     * @param envelope SpeechletRequestEnvelope containing the Context, Session, Version, and LaunchRequest
     * @return SpeechletResponse containing information, spoken and visual,  for the Welcome Screen
     */
    @Override
    public SpeechletResponse onLaunch(final SpeechletRequestEnvelope<LaunchRequest> envelope) {
        LOG.info("onLaunch requestId={}, sessionId={}", envelope.getRequest().getRequestId(),
                envelope.getSession().getSessionId());

        RequestDispatcher dispatcher = new RequestDispatcher(new LaunchRequestParser());
        return dispatcher.dispatchRequest(envelope);
    }

    /**
     * Creates a SpeechletResponse depending on the Intent provided in the request, triggered when handling a
     * speech initiated request that has an Intent.
     *
     * @param envelope SpeechletRequestEnvelope containing the Context, Session, Version, and IntentRequest
     * @return a SpeechletResponse containing information, spoken and visual, for the Intent
     */
    @Override
    public SpeechletResponse onIntent(final SpeechletRequestEnvelope<IntentRequest> envelope) {
        LOG.info("onIntent requestId={}, sessionId={}", envelope.getRequest().getRequestId(),
                envelope.getSession().getSessionId());

        RequestDispatcher dispatcher = new RequestDispatcher(new IntentRequestParser());
        return dispatcher.dispatchRequest(envelope);
    }

    /**
     * Logs information about the session, and is triggered when the session ends for any reason.
     *
     * @param envelope SpeechletRequestEnvelope containing the Context, Session, Version, and SessionEndedRequest
     */
    @Override
    public void onSessionEnded(final SpeechletRequestEnvelope<SessionEndedRequest> envelope) {
        LOG.info("onSessionEnded requestId={}, sessionId={}", envelope.getRequest().getRequestId(),
                envelope.getSession().getSessionId());
    }

    /**
     * Triggered when the user taps an on-screen item. Returns the response based on the token.
     *
     * @param envelope
     * @return
     */
    @Override
    public SpeechletResponse onElementSelected(final SpeechletRequestEnvelope<ElementSelectedRequest> envelope) {
        String token = envelope.getRequest().getToken();

        LOG.info("onElementSelected requestId={}, token={}", envelope.getRequest().getRequestId(), token);
        LOG.debug("session={}", envelope.getSession());
        LOG.debug("sessionId={}", envelope.getSession().getSessionId());

        RequestDispatcher dispatcher = new RequestDispatcher(new ElementSelectedRequestParser());
        return dispatcher.dispatchRequest(envelope);
    }
}
