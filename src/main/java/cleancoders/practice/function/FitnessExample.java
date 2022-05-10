package cleancoders.practice.function;

import fitnesse.responders.run.SuiteResponder;
import fitnesse.wiki.PageCrawlerImpl;
import fitnesse.wiki.PageData;
import fitnesse.wiki.PathParser;
import fitnesse.wiki.WikiPage;
import fitnesse.wiki.WikiPagePath;

public class FitnessExample {

    private WikiPage wikiPage;
    private StringBuffer buffer;

    public String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
        return setupTeardownSurround(pageData, includeSuiteSetup);
    }

    private String setupTeardownSurround(PageData pageData, boolean includeSuiteSetup) throws Exception {
        wikiPage = pageData.getWikiPage();
        buffer = new StringBuffer();

        if (isTestPage(pageData)) {
            includeSetupAndTearDowns(pageData, includeSuiteSetup);
        }
        pageData.setContent(buffer.toString());
        return pageData.getHtml();
    }

    private void includeSetupAndTearDowns(PageData pageData, boolean includeSuiteSetup) throws Exception {
        includeSetups(includeSuiteSetup);
        buffer.append(pageData.getContent());
        includeTeardowns(includeSuiteSetup);
    }

    private boolean isTestPage(PageData pageData) throws Exception {
        return pageData.hasAttribute("Test");
    }

    private void includeTeardowns(boolean includeSuiteSetup) throws Exception {
        includeInherited("teardown", "TearDown");
        if (includeSuiteSetup) {
            includeInherited("teardown", SuiteResponder.SUITE_TEARDOWN_NAME);
        }
    }

    private void includeSetups(boolean includeSuiteSetup) throws Exception {
        if (includeSuiteSetup) {
            includeInherited("setup", SuiteResponder.SUITE_SETUP_NAME);
        }
        includeInherited("setup", "SetUp");
    }

    private void includeInherited(String mode, String pageName) throws Exception {
        WikiPage suiteSetup = PageCrawlerImpl.getInheritedPage(pageName, wikiPage);
        if (suiteSetup != null) {
            includePage(mode, suiteSetup);
        }
    }

    private void includePage(String mode, WikiPage suiteSetup) throws Exception {
        WikiPagePath pagePath = wikiPage.getPageCrawler().getFullPath(suiteSetup);
        String pagePathName = PathParser.render(pagePath);
        buffer.append("!include -" + mode + " .").append(pagePathName).append("\n");
    }
}
