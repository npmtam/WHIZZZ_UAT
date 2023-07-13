package pageUI;

public class DiscoverUI {
    public static final String CREATE_NEW_BUTTON = "//span[text()='Create New']/ancestor::button";
    public static final String CREATE_NEW_CARD_BUTTON = "//a[@href='/knowledge-hub/card/create']";
    public static final String CREATE_NEW_BOARD_BUTTON = "//a[@href='/knowledge-hub/board/create']";

    public static final String SURVEY_POPUP = "//div[@class='ant-modal-content']";
    public static final String SURVEY_10_POINTS = "//div[@class='ant-modal-body']//span[text()='10']";
    public static final String SURVEY_SUBMIT_BUTTON = "//div[@class='ant-modal-footer']//button";

    public static final String EMMA_LOGO = "//a[@href='/']";

    public static final String CARD_TITLE_TEXTBOX = "//textarea[@placeholder='Title*']";
    public static final String CARD_DESCRIPTION_TEXTAREA = "//div[@translate='no']/p";
    public static final String SUBMIT_BUTTON = "//button[@type='submit']";
    public static final String POC_BUTTON = "//button[@title='Select & assign point of contacts']";
    public static final String POC_SEARCH_BAR = "//input[@placeholder='Add Point of Contact']";
    public static final String POC_FIRST_RESULT = "//div[@class='overflow-y-scroll mt-4 slim-scrollbar users-list']/div[1]";
    public static final String APPLY_BUTTON = "//button[contains(text(), 'Apply')]";
    public static final String OPTIONS_MENU_BUTTON = "//button[@class='font-sans font-semibold text-center rounded-full select-none disabled:opacity-40 disabled:cursor-not-allowed inline-block w-14 h-14 bg-blue-10 text-blue-100 hover:bg-[#bddaed] transition-colors duration-300 ease-in-out cursor-pointer']";
    public static final String CONTRIBUTOR_SEARCH_BAR = "//input[@placeholder='Add Contributors']";
    public static final String ADD_TAG_FIELD = "//input[@placeholder='+ Add a tag']";
    public static final String PREVIEW_MENU_BUTTON = "//span[text()='Preview']/parent::button";
    public static final String CONTRIBUTOR_MENU_BUTTON = "//span[text()='Contributors']/parent::button";
    public static final String TOAST_MESSAGE_CONTENT = "//div[@class='ant-notification-notice-message']";
    public static final String GAMIFICATION_TOAST_MESSAGE = "//div[@class='ant-notification-notice-content']//div[@class='ant-notification-notice-description']//p/span";
    public static final String REVIEW_INTERVAL_DROPDOWN = "//div[contains(@class, 'dropdown-current-text')]";
    public static final String REVIEW_INTERVAL_OPTIONS = "//ul[contains(@class,'slim-scrollbar')]/li/button";
    public static final String EXPAND_POC_SECTION = "(//i[@aria-label='icon: right'])[3]";
    public static final String EXPAND_TAG_SECTION = "(//i[@aria-label='icon: right'])[4]";

    //PREVIEW
    public static final String NUMBER_OF_CONTRIBUTOR = "//div[contains(@class, 'mt-3')]/p[contains(@class,'text-gray-80')]";
    public static final String NAME_OF_POC = "(//div[@class='slick-list']//span)[2]";
    public static final String PUBLISH_BUTTON = "//span[text()='Publish']/ancestor::button";
    public static final String LAST_UPDATE_FIELD = "//div[@class='flex gap-4 items-center']/div";
    public static final String TAG_FIELD_IN_DETAILS_PAGE = "//div[@class='inline-block py-1 px-4']";
    public static final String TAG_NAME_IN_PUBLISHED_PAGE ="//div[@class='py-1 px-4']";
    public static final String LINK_PREVIEW_THUMBNAIL_IMG = "//img[contains(@class, 'no-media-preview')]";


    //CREATE BOARD
    public static final String ADD_CARD_TO_BOARD_BUTTON = "//button[text()='Add Cards']";
    public static String SELECT_CARD_BUTTON = "(//div[@class='flex flex-col h-full rounded-sm shadow-sm']//button)[%s]";
    public static String SELECTED_CARD_TITLE = "(//h5)[%s]";
    public static final String ADD_CARD_SELECTED_BUTTON = "//span[text()='Add Cards']/parent::button";
}
