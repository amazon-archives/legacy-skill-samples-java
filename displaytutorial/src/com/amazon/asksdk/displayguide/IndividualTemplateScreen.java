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

import static com.amazon.asksdk.displayguide.TextUtils.*;

public class IndividualTemplateScreen extends RequestHandler {

    private static final String HINT_TEXT = "go back to the list of templates";

    private static final String RICH_TEXT_DESCRIPTION = wrapTextInSmallFont("The rich text area supports the following XML and custom tags:" + LINE_BREAK +
            escapeXML("<br/> Line Break") + LINE_BREAK +
            wrapTextInBold(escapeXML("<b> Bold")) + LINE_BREAK +
            wrapTextInItalics(escapeXML("<i> Italics")) + LINE_BREAK +
            wrapTextInUnderline(escapeXML("<u> Underline")) + LINE_BREAK) +
            wrapTextInExtraSmallFont(escapeXML("<font size=\"2\"> extra small font")) + wrapTextInSmallFont(LINE_BREAK) +
            wrapTextInSmallFont(escapeXML("<font size=\"3\"> small font") + LINE_BREAK) +
            wrapTextInMediumFont(escapeXML("<font size=\"5\"> medium font")) + wrapTextInSmallFont(LINE_BREAK) +
            wrapTextInLargeFont(escapeXML("<font size=\"7\"> large font")) + wrapTextInSmallFont(LINE_BREAK +
            LINE_BREAK +
            wrapTextInAction(escapeXML("<action> text wrapped in an action tag is selectable on-screen"),
                    Tokens.ACTION_RICH_TEXT_ELEMENT_ACTION_TOKEN) + LINE_BREAK + LINE_BREAK +
            wrapTextInAction(escapeXML("<img> include in-line images"),
                    Tokens.IMAGE_RICH_TEXT_ELEMENT_ACTION_TOKEN) + LINE_BREAK + LINE_BREAK +
            "Try clicking one of the blue actions above to learn more.");

    @Override
    public boolean canHandle(final SkillRequestInformation skillRequestInformation) {

        String intentName = skillRequestInformation.getIntentName();

        return IntentNames.INDIVIDUAL_TEMPLATE_INTENT.equals(intentName) ||
                (Tokens.TEMPLATE_OVERVIEW_SCREEN_TOKEN.equals(skillRequestInformation.getToken())
                        && IntentNames.SELECT_ITEM_INTENT.equals(intentName));
    }

    @Override
    public SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {
        Map<String,Slot> slots = skillRequestInformation.getSlots();
        String intentName = skillRequestInformation.getIntentName();

        Slot templateKindSlot = slots.get(SlotNames.TEMPLATE_KIND_SLOT_NAME);
        Slot numberSlot = slots.get(SlotNames.NUMBER_SLOT_NAME);

        TemplateNames name;

        if (IntentNames.INDIVIDUAL_TEMPLATE_INTENT.equals(intentName)) {
            name = TemplateNames.getNameFromKindAndNumber(templateKindSlot.getValue(), numberSlot.getValue());

        } else if (IntentNames.SELECT_ITEM_INTENT.equals(intentName)) {
            name = TemplateNames.getNameFromNumber(numberSlot.getValue());

        } else {
            throw new UnhandledRequestException(String.format("IndividualTemplateScreen could not handle intent: %s", intentName));
        }

        return getIndividualTemplateScreenResponse(name);
    }

    private SpeechletResponse getIndividualTemplateScreenResponse(TemplateNames templateName) {
        switch (templateName) {
            case BODY_TEMPLATE_1:
                return bodyTemplate1Example();
            case BODY_TEMPLATE_2:
                return bodyTemplate2Example();
            case BODY_TEMPLATE_3:
                return bodyTemplate3Example();
            case BODY_TEMPLATE_6:
                return bodyTemplate6Example();
            case LIST_TEMPLATE_1:
                return listTemplate1Example();
            case LIST_TEMPLATE_2:
                return listTemplate2Example();
            default:
                throw new UnhandledRequestException(String.format("Invalid Index for IndividualTemplateScreen: %s", templateName.ordinal()));
        }
    }

    private SpeechletResponse bodyTemplate1Example() {
        String title = "Body Template 1 Example";
        String shortDescription = "This template has a full-width text area that supports plain or rich text. ";
        String speechText = shortDescription + " Read on your Echo Show screen to learn about rich text elements.";
        String bodyText = wrapTextInMediumFont(shortDescription) + LINE_BREAK + LINE_BREAK + RICH_TEXT_DESCRIPTION;

        return new ResponseBuilder()
                .setShouldEndSession(null)
                .setSpeech(speechText)
                .setSimpleCard(title, shortDescription)
                .addHintDirective(HINT_TEXT)
                .addTemplateDirective( new BodyTemplate1Builder()
                        .setPrimaryText(makeRichText(bodyText))
                        .setTitle(title)
                        .setToken(Tokens.BODY_TEMPLATE_1_SCREEN_TOKEN)
                        .build())
                .build();
    }

    private SpeechletResponse bodyTemplate2Example() {
        String title = "Body Template 2 Example";
        String shortDescription = "Here is an example of Body Template 2. This template has an image on the right and a " +
                "scrolling text area on the left that supports plain or rich text.";
        String speechText = shortDescription + " Read on your Echo Show screen to learn about rich text elements.";
        String bodyText = wrapTextInMediumFont(shortDescription) + LINE_BREAK + LINE_BREAK + RICH_TEXT_DESCRIPTION;

        return new ResponseBuilder()
                .setShouldEndSession(null)
                .setSpeech(speechText)
                .setSimpleCard(title, speechText)
                .addHintDirective(HINT_TEXT)
                .addTemplateDirective(new BodyTemplate2Builder()
                        .setFgImage(ImageResources.ALEXA_LOGO.getImage())
                        .setTitle(title)
                        .setPrimaryText(makeRichText(bodyText))
                        .setToken(Tokens.BODY_TEMPLATE_2_SCREEN_TOKEN)
                        .build())
                .build();
    }

    private SpeechletResponse bodyTemplate3Example() {
        String title = "Body Template 3 Example";
        String shortDescription = "Here is an example of Body Template 3. This template has an image on the left and a " +
                "scrolling text area on the right that supports plain or rich text";
        String speechText = shortDescription + " Read on your Echo Show screen to learn about rich text elements.";
        String bodyText = wrapTextInMediumFont(shortDescription) + LINE_BREAK + LINE_BREAK + RICH_TEXT_DESCRIPTION;

        return new ResponseBuilder()
                .setShouldEndSession(null)
                .setSpeech(speechText)
                .setSimpleCard(title, speechText)
                .addHintDirective(HINT_TEXT)
                .addTemplateDirective(new BodyTemplate3Builder()
                        .setPrimaryText(makeRichText(bodyText))
                        .setFgImage(ImageResources.ALEXA_LOGO.getImage())
                        .setTitle(title)
                        .setToken(Tokens.BODY_TEMPLATE_3_SCREEN_TOKEN)
                        .build())
                .build();
    }

    private SpeechletResponse bodyTemplate6Example() {
        String title = "Body Template 6 Example";
        String shortDescription = "Here is an example of Body Template 6. This template has a full-width text area that " +
                "supports plain or rich text, and is the only template that does not allow a title.";
        String speechText = shortDescription + " Read on your Echo Show screen to learn about rich text elements.";
        String bodyText = wrapTextInMediumFont(shortDescription) + LINE_BREAK + LINE_BREAK + RICH_TEXT_DESCRIPTION;

        return new ResponseBuilder()
                .setShouldEndSession(null)
                .setSpeech(speechText)
                .setSimpleCard(title, speechText)
                .addHintDirective(HINT_TEXT)
                .addTemplateDirective(new BodyTemplate6Builder()
                        .setPrimaryText(makeRichText(bodyText))
                        .setToken(Tokens.BODY_TEMPLATE_6_SCREEN_TOKEN)
                        .build())
                .build();
    }

    private SpeechletResponse listTemplate1Example() {
        String title = "List Template 1 Example";
        String shortDescription = "Use List Template 1 when the items don't have images, or don't have a unique image association.  " +
                "Each item in a list is selectable by touch.  ";
        String examples = "Example uses include: lists of sample utterances, quotes, transaction history, contacts, table of contents, etc.";

        String speechText = shortDescription;

        String cardText = "Use any list template when your skill responds with a set of entities that " +
                "the user can quickly browse, compare, select, or dig deeper on. " +
                shortDescription + examples;

        ItemListForListTemplate1 itemList = new ItemListForListTemplate1();
        ListContentGenerator.generateSampleList(itemList);

        return new ResponseBuilder()
                .setShouldEndSession(null)
                .setSpeech(speechText)
                .setSimpleCard(title, cardText)
                .addHintDirective(HINT_TEXT)
                .addTemplateDirective(new ListTemplate1Builder()
                        .setListItems(itemList.getItems())
                        .setTitle(title)
                        .setToken(Tokens.LIST_TEMPLATE_1_SCREEN_TOKEN)
                        .build())
                .build();
    }

    private SpeechletResponse listTemplate2Example() {
        String title = "List Template 2 Example";
        String shortDescription = "Use List Template 2 when the items have unique images that help the user recognize or select from the list.  " +
                "This template supports a variety of image aspect ratios.  " +
                "Each item in a list is selectable by touch. ";
        String examples = "Example uses include: lists of books, albums, videos, destinations, products, etc.  ";

        String speechText = shortDescription;

        String cardText = "Use any list template when your skill responds with a set of entities that " +
                "the user can quickly browse, compare, select, or dig deeper on. " + "\n\n" + shortDescription + "\n\n" + examples;

        String hintText = "how do you handle selection of list items?";

        ItemListForListTemplate2 itemList = new ItemListForListTemplate2();
        ListContentGenerator.generateSampleListWithDifferentRatioImages(itemList);

        return new ResponseBuilder()
                .setShouldEndSession(null)
                .setSpeech(speechText)
                .setSimpleCard(title, cardText)
                .addHintDirective(hintText)
                .addTemplateDirective(new ListTemplate2Builder()
                        .setListItems(itemList.getItems())
                        .setTitle(title)
                        .setToken(Tokens.LIST_TEMPLATE_2_SCREEN_TOKEN)
                        .build())
                .build();
    }

    private enum TemplateNames {
        LIST_TEMPLATE_1("list", "1"),
        LIST_TEMPLATE_2("list", "2"),
        BODY_TEMPLATE_1("body", "1"),
        BODY_TEMPLATE_2("body", "2"),
        BODY_TEMPLATE_3("body", "3"),
        BODY_TEMPLATE_6("body", "6");

        private String number;
        private String templateKind;

        TemplateNames(String templateKind, String number) {
            this.templateKind = templateKind;
            this.number = number;
        }

        static TemplateNames getNameFromKindAndNumber(String templateKind, String number) {
            for (TemplateNames templateName : TemplateNames.values()) {
                if (templateKind.equals(templateName.templateKind) && number.equals(templateName.number))
                    return templateName;
            }
            throw new RuntimeException(String.format("there is no template with kind: %s and number: %s", templateKind, number));
        }

        static TemplateNames getNameFromNumber(String number) {
            return TemplateNames.values()[Integer.parseInt(number) - 1];
        }

    }
}
