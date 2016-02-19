package pl.org.epf.client.local.view.widgets;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.jboss.errai.GwtMockitoRunnerExtension;

import pl.org.epf.client.local.event.PageChange;
import pl.org.epf.client.local.view.FavouritesViewModel;
import pl.org.epf.client.local.view.MapTabViewModel;

import javax.enterprise.event.Event;

@RunWith(GwtMockitoRunnerExtension.class)
public class TopNavigationPanelUnitTest {

  @InjectMocks
  private TopNavigationPanel testableInstance;

  @Mock
  private Event<PageChange> pageChangeEvent;

  @Captor
  private ArgumentCaptor<PageChange> pageChangeCaptor;

  @Test
  public void switchToFavourites() throws Exception {
    // The ClickEvent parameter does not matter here, just event has to be handled
    testableInstance.switchToFavourites(null);

    verify(pageChangeEvent).fire(pageChangeCaptor.capture());
    assertThat(pageChangeCaptor.getValue().getPageName(), is(equalTo(FavouritesViewModel.PAGE_NAME)));
  }

  @Test
  public void switchToMap() throws Exception {
    // The ClickEvent parameter does not matter here, just event has to be handled
    testableInstance.switchToMap(null);

    verify(pageChangeEvent).fire(pageChangeCaptor.capture());
    assertThat(pageChangeCaptor.getValue().getPageName(), is(equalTo(MapTabViewModel.PAGE_NAME)));
  }

}
