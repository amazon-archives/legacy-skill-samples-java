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

/**
 * The Tokens class contains String constants for all tokens assigned to elements in the skill
 */
public class Tokens {
    public static final String LAUNCH_REQUEST_TOKEN = "LaunchRequestToken";

    public static final String TEMPLATE_OVERVIEW_LIST_ELEMENT_TOKEN = IntentNames.TEMPLATE_OVERVIEW_INTENT;
    public static final String VIDEO_TOPIC_LIST_ELEMENT_TOKEN = IntentNames.VIDEO_INSTRUCTIONAL_INTENT;
    public static final String INLINE_IMAGE_TAG_LIST_ELEMENT_TOKEN = IntentNames.INLINE_IMAGE_TAG_INTENT;
    public static final String ACTION_TAG_LIST_ELEMENT_TOKEN = IntentNames.ACTION_TAG_INTENT;
    public static final String ITEM_SELECTION_LIST_ELEMENT_TOKEN = IntentNames.ITEM_SELECTION_INTENT;
    public static final String HINTS_LIST_ELEMENT_TOKEN = IntentNames.HINTS_INTENT;

    public static final String ACTION_RICH_TEXT_ELEMENT_ACTION_TOKEN = IntentNames.ACTION_TAG_INTENT;
    public static final String IMAGE_RICH_TEXT_ELEMENT_ACTION_TOKEN = IntentNames.INLINE_IMAGE_TAG_INTENT;
    public static final String WATCH_VIDEO_ACTION_TOKEN = IntentNames.WATCH_VIDEO_INTENT;

    private static final String TEMPLATE_TOKEN_FORMAT_STRING = String.format("%s|%s,%s|%s,%s", IntentNames.INDIVIDUAL_TEMPLATE_INTENT, SlotNames.TEMPLATE_KIND_SLOT_NAME, "%s", SlotNames.NUMBER_SLOT_NAME, "%s");
    private static final String BODY_TEMPLATE_TOKEN_FORMAT_STRING = String.format(TEMPLATE_TOKEN_FORMAT_STRING, "body", "%s");
    private static final String LIST_TEMPLATE_TOKEN_FORMAT_STRING = String.format(TEMPLATE_TOKEN_FORMAT_STRING, "list", "%s");

    public static final String BODY_TEMPLATE_1_LIST_ELEMENT_TOKEN = String.format(BODY_TEMPLATE_TOKEN_FORMAT_STRING, 1);
    public static final String BODY_TEMPLATE_2_LIST_ELEMENT_TOKEN = String.format(BODY_TEMPLATE_TOKEN_FORMAT_STRING, 2);
    public static final String BODY_TEMPLATE_3_LIST_ELEMENT_TOKEN = String.format(BODY_TEMPLATE_TOKEN_FORMAT_STRING, 3);
    public static final String BODY_TEMPLATE_6_LIST_ELEMENT_TOKEN = String.format(BODY_TEMPLATE_TOKEN_FORMAT_STRING, 6);
    public static final String LIST_TEMPLATE_1_LIST_ELEMENT_TOKEN = String.format(LIST_TEMPLATE_TOKEN_FORMAT_STRING, 1);
    public static final String LIST_TEMPLATE_2_LIST_ELEMENT_TOKEN = String.format(LIST_TEMPLATE_TOKEN_FORMAT_STRING, 2);

    public static final String WELCOME_SCREEN_TOKEN = "WelcomeScreenToken";
    public static final String HELP_SCREEN_TOKEN = "HelpScreenToken";
    public static final String TOPICS_LIST_SCREEN_TOKEN = "TopicsListScreenToken";
    public static final String TEMPLATE_OVERVIEW_SCREEN_TOKEN = "TemplateOverviewScreenToken";
    public static final String BODY_TEMPLATE_1_SCREEN_TOKEN = "BodyTemplate1ScreenToken";
    public static final String BODY_TEMPLATE_2_SCREEN_TOKEN = "BodyTemplate2ScreenToken";
    public static final String BODY_TEMPLATE_3_SCREEN_TOKEN = "BodyTemplate3ScreenToken";
    public static final String BODY_TEMPLATE_6_SCREEN_TOKEN = "BodyTemplate6ScreenToken";
    public static final String LIST_TEMPLATE_1_SCREEN_TOKEN = "ListTemplate1ScreenToken";
    public static final String LIST_TEMPLATE_2_SCREEN_TOKEN = "ListTemplate2ScreenToken";
    public static final String INLINE_IMAGE_SCREEN_TOKEN = "InlineImageScreenToken";
    public static final String ACTION_TAG_SCREEN_TOKEN = "ActionTagScreenToken";
    public static final String ITEM_SELECTION_SCREEN_TOKEN = "ItemSelectionScreenToken";
    public static final String HINTS_SCREEN_TOKEN = "HintsScreenToken";
}
