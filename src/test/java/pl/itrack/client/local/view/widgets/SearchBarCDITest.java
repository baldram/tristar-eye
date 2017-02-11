package pl.itrack.client.local.view.widgets;

import pl.itrack.client.local.AbstractTristarCDITest;

public class SearchBarCDITest extends AbstractTristarCDITest {

    private SearchBar searchBar;

    @Override
    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        searchBar = lookupBeanInstance(SearchBar.class);
    }

    public void testHasSearchMaterialIcon() {
        assertEquals(SearchBar.CSS_CLASS_SEARCH_BTN, searchBar.getSearchButton().getElement().getClassName());
    }

    // TODO: Implement Selenium tests for easier UI testing
}