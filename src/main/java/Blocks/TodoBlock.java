package Blocks;

import utils.ChromeWebDriver;

public class TodoBlock extends BaseBlock {
    public TodoBlock(ChromeWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HeaderTodoBlock getHeaderTodoBlock() {
        return new HeaderTodoBlock(driver);
    }

    public MainTodoBlock getMainTodoBlock() {
        return new MainTodoBlock(driver);
    }

    public FooterTodoBlock getFooterTodoBlock() {
        return new FooterTodoBlock(driver);
    }
}
