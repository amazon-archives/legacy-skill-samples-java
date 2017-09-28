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

import com.amazon.speech.speechlet.interfaces.display.element.TripleTextContent;
import org.apache.commons.lang3.StringEscapeUtils;

import static com.amazon.asksdk.displayguide.TextUtils.makePlainText;
import static com.amazon.asksdk.displayguide.TextUtils.makeRichText;

/**
 * ListContentGenerator generates static content for List Templates used throughout the skill
 */
public class ListContentGenerator {

    static void generateSampleList(ItemList itemList) {
        Integer numberOfItems = 10;
        for (int i = 0; i < numberOfItems; i++) {

            TripleTextContent textContent = itemList.makeTextContent(
                    makePlainText("Sample "  + i),
                    makePlainText("Secondary Text"),
                    makePlainText("Tertiary Text"));

            itemList.addItem(ImageResources.ALEXA_LOGO.getImage(),
                    "SampleListTemplate1ElementToken" + i,
                    textContent);
        }
    }

    static void generateSampleListWithDifferentRatioImages(ItemList itemList) {
        ImageResources[] images = {ImageResources.PORTRAIT_SAMPLE,
                ImageResources.SQUARE_SAMPLE,
                ImageResources.WIDE_RATIO_SAMPLE,
                ImageResources.SUPER_WIDE_RATIO_SAMPLE};

        for (ImageResources image : images) {
            TripleTextContent textContent = itemList.makeTextContent(
                    makePlainText("Sample"),
                    makePlainText(image.name()),
                    makePlainText(""));

            itemList.addItem(image.getImage(), "token", textContent);
        }
    }

    static void generateTopicsList(ItemList itemList) {
        String[][] CONTENT = {
                { "Rendering Templates", Tokens.TEMPLATE_OVERVIEW_LIST_ELEMENT_TOKEN},
                { "Showing Videos", Tokens.VIDEO_TOPIC_LIST_ELEMENT_TOKEN},
                { "Inline Images", Tokens.INLINE_IMAGE_TAG_LIST_ELEMENT_TOKEN },
                { "Clickable Actions", Tokens.ACTION_TAG_LIST_ELEMENT_TOKEN},
                { "List Item Selection", Tokens.ITEM_SELECTION_LIST_ELEMENT_TOKEN},
                { "Hint Phrases", Tokens.HINTS_LIST_ELEMENT_TOKEN}
        };

        for (String[] itemContent : CONTENT) {
            String text = itemContent[0];
            String token = itemContent[1];

            TripleTextContent textContent = itemList.makeTextContent(makePlainText(text));
            itemList.addItem(ImageResources.ALEXA_LOGO.getImage(), token, textContent);
        }
    }

    static void generateHelpPhrasesList(ItemList itemList) {
        String [] CONTENT = {
                "How do you render a template?",
                "How do I play a video?",
                "How do you include clickable actions?",
                "Including images inline with text",
                "Handling list item selection",
                "What topics can I learn about?",
                "Body template 1 example"
        };

        for (String phrase : CONTENT) {
            String primaryText = String.format("<font size='3'> %s </font>", StringEscapeUtils.escapeXml(phrase));

            TripleTextContent textContent = itemList.makeTextContent(makeRichText(primaryText));

            itemList.addItem(ImageResources.ALEXA_LOGO.getImage(), "", textContent);
        }
    }

    static void generateTemplateOverviewList(ItemList itemList) {
        String [][] CONTENT = {
                {"List Template 1",
                        "Vertical list, for text-focused items",
                        Tokens.LIST_TEMPLATE_1_LIST_ELEMENT_TOKEN},
                {"List Template 2",
                        "Horizontal list, for items with unique images",
                        Tokens.LIST_TEMPLATE_2_LIST_ELEMENT_TOKEN},
                {"Body Template 1",
                        "Full-width text",
                        Tokens.BODY_TEMPLATE_1_LIST_ELEMENT_TOKEN},
                {"Body Template 2",
                        "Image right, scrolling text left",
                        Tokens.BODY_TEMPLATE_2_LIST_ELEMENT_TOKEN},
                {"Body Template 3",
                        "Image left, scrolling text right",
                        Tokens.BODY_TEMPLATE_3_LIST_ELEMENT_TOKEN},
                {"Body Template 6",
                        "Full-width text with no title",
                        Tokens.BODY_TEMPLATE_6_LIST_ELEMENT_TOKEN}
        };
        ImageResources[] IMAGES = {
                ImageResources.LIST_TEMPLATE_1,
                ImageResources.LIST_TEMPLATE_2,
                ImageResources.BODY_TEMPLATE_1,
                ImageResources.BODY_TEMPLATE_2,
                ImageResources.BODY_TEMPLATE_3,
                ImageResources.BODY_TEMPLATE_6
        };

        for (int i = 0; i < CONTENT.length; i++) {
            String [] itemContent = CONTENT[i];
            String name = itemContent[0];
            String description = itemContent[1];
            String token = itemContent[2];

            TripleTextContent textContent = itemList.makeTextContent(makePlainText(name), makePlainText(description));

            itemList.addItem(IMAGES[i].getImage(), token, textContent);
        }
    }
}
