    @Test
    public void checkJavaWordInSearchResults() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "JAVA",
                "Cannot find 'Search…'",
                10
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                " Cannot find item containers '",
                5
        );
        List<WebElement> listOfElementsWithItemContainers =  driver.findElements(By.id("org.wikipedia:id/page_list_item_container")); //находим количество контейнеров

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Java')]"),
                "Cannot find 'Java '",
                5
        );
        List<WebElement> listOfElementsWithJava =  driver.findElements(By.xpath("//*[contains(@text,'Java')]")); //находим количество xpath с текстом 'Java'

        Assert.assertEquals(
                "List of containers is not equal to list of xpathes with 'Java' text",
                listOfElementsWithItemContainers.size(),
                listOfElementsWithJava.size()
        );
    }




    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {//написали метод который сначала дожидается какого-то
        //элемента которыый передается в xpath or id и затем происходит клик
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {//написали метод который сначала дожидается какого-то
        //элемента которыый передается в xpath и затем вводит значение
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {//метод проверяет что элемента нету
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
