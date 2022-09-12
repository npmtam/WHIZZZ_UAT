package pageUI;

public class DiscoverUI {
    public static final String CREATE_NEW_BUTTON = "//span[text()='Create New']/ancestor::button";
    public static final String CREATE_NEW_CARD_BUTTON = "//a[@href='/knowledge-hub/card/create']";

    public static final String SURVEY_POPUP = "//div[@class='ant-modal-content']";
    public static final String SURVEY_10_POINTS = "//div[@class='ant-modal-body']//span[text()='10']";
    public static final String SURVEY_SUBMIT_BUTTON = "//div[@class='ant-modal-footer']//button";

    public static final String CARD_TITLE_TEXTBOX = "//textarea[@placeholder='Title*']";
    public static final String CARD_DESCRIPTION_TEXTAREA = "//p[@data-placeholder='Type your knowledge*']";
    public static final String SUBMIT_BUTTON = "//button[@type='submit']";
    public static final String POC_BUTTON = "//button[@title='Select & assign point of contacts']";
    public static final String POC_SEARCH_BAR = "//input[@placeholder='Add Point of Contact']";
    public static final String POC_FIRST_RESULT = "//div[@class='overflow-y-scroll mt-4 slim-scrollbar users-list']/div[1]";
    public static final String APPLY_BUTTON = "//button[contains(text(), 'Apply')]";
    public static final String OPTIONS_MENU_BUTTON = "//button[@type='submit']/following-sibling::button";
    public static final String CONTRIBUTOR_SEARCH_BAR = "//input[@placeholder='Add Contributors']";
    public static final String ADD_TAG_FIELD = "//input[@placeholder='+ Add a tag']";
    public static final String PREVIEW_MENU_BUTTON = "//span[text()='Preview']/parent::button";
    public static final String CONTRIBUTOR_MENU_BUTTON = "//span[text()='Contributors']/parent::button";
    public static final String TOAST_MESSAGE_CONTENT = "//div[@class='ant-notification-notice-message']";

    //PREVIEW
    public static final String NUMBER_OF_CONTRIBUTOR = "//div[contains(@class, 'mt-3')]/p[contains(@class,'text-gray-80')]";
    public static final String NAME_OF_POC = "(//div[@class='slick-list']//span)[2]";
    public static final String PUBLISH_BUTTON = "//span[text()='Publish']/ancestor::button";
    public static final String LAST_UPDATE_FIELD = "//div[@class='flex gap-4 items-center']/div";
    public static final String TAG_FIELD_IN_DETAILS_PAGE = "//div[@class='inline-block py-1 px-4']";


}
